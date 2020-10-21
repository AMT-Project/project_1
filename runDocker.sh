echo "=============================="
echo "=== Kill & stop containers ==="
echo "=============================="
# https://gist.github.com/evanscottgray/8571828#gistcomment-1830482
docker kill stack_db stackoverflow

docker rm stack_db stackoverflow

echo "==========================================================="
echo "=== Remove server image to regenerate with latest build ==="
echo "==========================================================="
docker rmi openliberty/stack

./build-image.sh

# volumes persists between runs (db was never changed)
echo "=========================================="
echo "=== Delete docker volumes between runs ==="
echo "=========================================="
docker-compose down -v

echo "=================="
echo "=== Run server ==="
echo "=================="
docker-compose up &

