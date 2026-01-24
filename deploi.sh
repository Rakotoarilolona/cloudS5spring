# 1. Démarrer uniquement la base de données PostgreSQL
docker compose up -d db

# 2. Vérifier que la base de données est prête
docker compose logs db

# Attendre de voir ce message : "database system is ready to accept connections"

# 3. Importer les données OSM (une seule fois, peut être long)
docker compose up osm-import

# L'import terminera automatiquement quand c'est fini