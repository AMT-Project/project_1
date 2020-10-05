#!/bin/bash

echo "================================="
echo "= Installing test environnement ="
echo "================================="
npm install

echo "================================="
echo "=== Build & launch test image ==="
echo "================================="
build-image.sh

docker-compose up &

echo "================================="
echo "===== Wait 10s to run tests ====="
echo "================================="
sleep 10s

echo "================================="
echo "====== Running tests suite ======"
echo "================================="
npx codeceptjs run --steps

echo "================================="
echo "======== Kill test image ========"
echo "================================="
docker kill stackoverflow
