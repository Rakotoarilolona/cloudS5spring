-- gis.sql
-- Création de la base de données pour OSM
CREATE DATABASE gis;

-- Connexion à la base gis
\c gis;

-- Activation des extensions nécessaires pour OSM
CREATE EXTENSION IF NOT EXISTS postgis;
CREATE EXTENSION IF NOT EXISTS hstore;
CREATE EXTENSION IF NOT EXISTS postgis_topology;

-- Configuration des permissions
ALTER TABLE geometry_columns OWNER TO postgres;
ALTER TABLE spatial_ref_sys OWNER TO postgres;

-- Création de l'utilisateur renderer pour OSM (optionnel mais recommandé)
CREATE USER renderer;
GRANT ALL PRIVILEGES ON DATABASE gis TO renderer;
GRANT ALL PRIVILEGES ON SCHEMA public TO renderer;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO renderer;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO renderer;