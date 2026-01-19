@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

echo 🔧 Démarrage du script de déploiement...

REM Vérifier si Docker est en cours d'exécution
docker info >nul 2>&1
if errorlevel 1 (
    echo ❌ Docker n'est pas en cours d'exécution
    pause
    exit /b 1
)

REM Vérifier si le conteneur Spring existe
docker ps -a --format "{{.Names}}" | findstr /C:"clouds5_spring" >nul
if errorlevel 1 (
    echo ❌ Le conteneur clouds5_spring n'existe pas
    echo ⚠️  Lancement avec: docker-compose up --build
    pause
    exit /b 1
)

REM Vérifier si le conteneur Spring est en cours d'exécution
docker ps --format "{{.Names}}" | findstr /C:"clouds5_spring" >nul
if errorlevel 1 (
    echo ⚠️  Le conteneur clouds5_spring n'est pas démarré
    set /p start="Voulez-vous le démarrer? (o/n): "
    if /i "!start!"=="o" (
        docker-compose up -d app
        echo ⏳ Attente du démarrage du conteneur...
        timeout /t 10 /nobreak >nul
    ) else (
        echo ❌ Déploiement annulé
        pause
        exit /b 1
    )
)

echo 📦 Nettoyage et construction du projet...
echo 🔄 Exécution de mvn clean package dans le conteneur...

REM Exécuter mvn clean package dans le conteneur
docker exec -it clouds5_spring mvn clean package -DskipTests
if errorlevel 1 (
    echo ❌ Échec du build Maven
    pause
    exit /b 1
)

echo ✅ Build réussi!

REM Arrêter l'application en cours d'exécution
echo ⏸️  Arrêt de l'application en cours...
docker exec -it clouds5_spring pkill -f "java.*jar" >nul 2>&1 || echo "Aucune application en cours d'exécution"
timeout /t 3 /nobreak >nul

REM Relancer l'application avec le nouveau .war
echo 🚀 Démarrage de l'application...
docker exec -d clouds5_spring java -jar /app/target/*.war

echo ✅ Déploiement terminé avec succès!
echo 🌐 L'application est disponible sur: http://localhost:8081

REM Option pour voir les logs
echo.
set /p logs="Voulez-vous voir les logs de l'application? (o/n): "
if /i "!logs!"=="o" (
    echo 📋 Affichage des logs...
    docker logs -f clouds5_spring
) else (
    echo.
    echo Pour voir les logs plus tard: docker logs -f clouds5_spring
    pause
)