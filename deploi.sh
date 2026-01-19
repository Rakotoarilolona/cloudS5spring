#!/bin/bash

# Script de déploiement pour Spring Boot avec Docker
# Usage: ./deploi.sh

echo "🔧 Démarrage du script de déploiement..."

# Vérifier si Docker est en cours d'exécution
if ! docker info > /dev/null 2>&1; then
    echo "❌ Docker n'est pas en cours d'exécution"
    exit 1
fi

# Vérifier si le conteneur Spring existe
if ! docker ps -a --format '{{.Names}}' | grep -q 'clouds5_spring'; then
    echo "❌ Le conteneur clouds5_spring n'existe pas"
    echo "⚠️  Lancement avec: docker-compose up --build"
    exit 1
fi

# Vérifier si le conteneur Spring est en cours d'exécution
if ! docker ps --format '{{.Names}}' | grep -q 'clouds5_spring'; then
    echo "⚠️  Le conteneur clouds5_spring n'est pas démarré"
    read -p "Voulez-vous le démarrer? (o/n): " -n 1 -r
    echo
    if [[ $REPLY =~ ^[Oo]$ ]]; then
        docker-compose up -d app
        echo "⏳ Attente du démarrage du conteneur..."
        sleep 10
    else
        echo "❌ Déploiement annulé"
        exit 1
    fi
fi

echo "📦 Nettoyage et construction du projet..."

# Option 1: Exécuter mvn clean package dans le conteneur
echo "🔄 Exécution de mvn clean package dans le conteneur..."
if docker exec -it clouds5_spring mvn clean package -DskipTests; then
    echo "✅ Build réussi!"
    
    # Arrêter l'application en cours d'exécution si elle tourne
    echo "⏸️  Arrêt de l'application en cours..."
    docker exec -it clouds5_spring pkill -f "java.*jar" || true
    sleep 3
    
    # Relancer l'application avec le nouveau .war
    echo "🚀 Démarrage de l'application..."
    docker exec -d clouds5_spring java -jar /app/target/*.war
    
    echo "✅ Déploiement terminé avec succès!"
    echo "🌐 L'application est disponible sur: http://localhost:8081"
else
    echo "❌ Échec du build Maven"
    exit 1
fi

# Option 2: Afficher les logs
echo ""
read -p "Voulez-vous voir les logs de l'application? (o/n): " -n 1 -r
echo
if [[ $REPLY =~ ^[Oo]$ ]]; then
    echo "📋 Affichage des logs..."
    docker logs -f clouds5_spring
fi