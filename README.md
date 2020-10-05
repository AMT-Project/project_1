# StackOverflow

## Build image
Run inside projet folder the command:
```bash
  ./build-image.sh
```

If everything goes well, you can run
```bash
docker-compose up
```

Now, your terminal is stuck. Great. Open <http://localhost:9080>
Additionnaly, you can open <http://localhost:9443>

# run locally
```bash
  ./build-run-locally.sh
```

# pull image and run
pull the image from ghcr.io:

```shell
docker pull ghcr.io/pabloheigvd/flow:latest
```

Run the image with:

```shell
docker run -p 9000:9080 ghcr.io/pabloheigvd/flow
```

Access <http://localhost:9000/stack/login> to view the website

# Run test suite
```bash
./runTestsLocally.sh
```
