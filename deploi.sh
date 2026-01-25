docker comose up -d db
sleep 10
docker compose run osm_import import
docker compose up -d osm_server
docker compose up -d app