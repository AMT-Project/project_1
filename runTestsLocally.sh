#!/bin/bash
./runDocker.sh

echo "================================="
echo "===== Wait 10s to run tests ====="
echo "================================="
sleep 30s

echo "================================="
echo "= Installing test environnement ="
echo "================================="
npm install

echo "================================="
echo "====== Running tests suite ======"
echo "================================="
npx codeceptjs run --steps

echo "=============================="
echo "=== Kill & stop containers ==="
echo "=============================="
#docker kill stack_db stackoverflow

#docker rm stack_db stackoverflow
