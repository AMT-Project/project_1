# TODO run build and run stack_db image only with this file to go with
# pulled image
echo "===================================="
echo "=== At the end, do the following ==="
echo "===================================="
echo "get IP:"
echo "docker inspect stack_overflow"
echo "Connect to IPAddress:9443/stack/login"
sleep 5

docker kill stackoverflow stack_db
docker rm stackoverflow stack_db

echo "==============================="
echo "=== Pulling & Running image ==="
echo "==============================="
cd docker
docker-compose down -v
docker-compose up &