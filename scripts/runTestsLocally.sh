#!/bin/bash
./runDocker.sh

echo "================================="
echo "===== Wait 30s to run tests ====="
echo "================================="
sleep 30s

echo "================================="
echo "= Installing test environnement ="
echo "================================="
cd ..
npm install

echo "================================="
echo "====== Running tests suite ======"
echo "================================="
npx codeceptjs run --steps
