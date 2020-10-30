#!/bin/bash
echo "==============================="
echo "=== Pulling & Running image ==="
echo "==============================="
cd ..
docker pull ghcr.io/pabloheigvd/flow
docker kill stackoverflow stack_db
docker rm stackoverflow stack_db
cd docker
docker-compose down -v
docker-compose up &