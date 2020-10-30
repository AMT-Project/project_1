# StackOverflow
Do not forget to edit your `/etc/hosts` file to add:
```
127.0.0.1	stack.ch
```

# Pull image and run
```bash
./pullAndRun.sh
```
Open: stack.ch:9080/stack/
# Dev
## Package and deploy website
```bash
  ./runDocker.sh
```
Open: stack.ch:9080/stack/
## Testing
### End-to-end tests
```bash
./runTestsLocally.sh
```
After testing, you can still consult it at: stack.ch:9080/stack/
**Note**: You may want to rerun the script since the script assumes you already have downloaded *openliberty/open-liberty:kernel-java11-openj9-ubi* image. The script waits 30s for the image to be built and launches the server. 
### JMeter
We have a load test plan you can open at `jmeter/stack_test_plan.jmx` with JMeter.

Before starting the test, deploy a website with
```bash
./pullAndRun.sh
```
And checkout out the votes at stack.ch:9080/stack/
