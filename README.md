# nukr

FIXME

# Makefile
## All commands Makefile:
```
Commmands:           Descriptions:

build-image          Build a Docker image
fix-format-code      Formats the code for the entire project
help                 This help
install-dependencies Install dependencies
lint                 Check format code of the all project
run                  Run server
run-docker-image     Run your Docker image
run-repl             Run clojure repl
tests                Run tests
uberjar              Build an uberjar of your service
```


## Getting Started

1. Start the application: `lein run` or `make run`
2. Go to [localhost:8080](http://localhost:8080/) to see: `Swagger Documentation`

## Run Tests

1. Run your app's tests with `lein test` or `make tests`.

## Configuration

To configure logging see config/logback.xml. By default, the app logs to stdout and logs/.
To learn more about configuring Logback, read its [documentation](http://logback.qos.ch/documentation.html).


## Developing your service

### [Docker](https://www.docker.com/) container support

1. Build an uberjar of your service: `lein uberjar`
2. Build a Docker image: `sudo docker build -t nukr .`
3. Run your Docker image: `docker run -p 8080:8080 nukr`

### [OSv](http://osv.io/) unikernel support with [Capstan](http://osv.io/capstan/)

1. Build and run your image: `capstan run -f "8080:8080"`

Once the image it built, it's cached.  To delete the image and build a new one:

1. `capstan rmi nukr; capstan build`


## Links
* [Other examples](https://github.com/pedestal/samples)

# User registration diagram

## Create User
![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgQ3JlYXRlIFVzZXIgUHJvZmlsZQoKbm90ZSBvdmVyIGFwaSxjb250cm9sbGVyOiBSZXF1ZXN0IGMAMQZ1c2VyIHdpdGggYm9keSByABgGCmFwaS0-ACsMUE9TVCB0bwArDAoATQotPmRiOiBWZXJpZnkgaWYAUAZleGl0IGJ5IGVtYWlsCmRiAEMOUmV0dXJucwB7BmlmIGFscmVhAH0FZ2lzdGVyZWQgb3IgbmlsAFoNYXBpADEKMjAxAIFAB2QAgUIGb3IgRXhjZXB0aW9uAIILBmV4aXN0cwAxElN0YXR1ADwGLQCBBAcgb2JqZWN0AIIMBgBQBwoKCgoKCgoK&s=roundgreen)

# Update User
![]()

## Diagram add friendships
![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgTnVrciBDUlVEIEZyaWVuZHNoaXAKCm5vdGUgb3ZlciBVc2VyLE51a3I6IEFkZAAZDFVzZXItPgAVBkNyZWF0ZQA4CzogcGF0aC1wYXJhbTogOmlkIGFuZCBib2R5AA4GCk51a3ItPkFUT00AKxMgb24gQVRPTQpBVE9NAFwIUmV0dXJuIGYAgR4JcyBjAHIFZCBTdGF0dXMgQ29kZTogMjAxAFcHVXNlACQUAC8IAIFQF0dldCBhbGwAggULcyBmb3IgdXNlcgCBaA1HZXQAgQENAIFoCiA6aWQAgVUNABsQAEgILWlkAIFFHwCBLR5zAIFrEDAAgl8FOgCDMgUAgm4FAIE7CwCDQBdSZW1vdgCCcQ0AgVkVACAHAIFXGgCDRhwADDEAg2YTbmlsAINEFACDcQsgMjA0Cg&s=roundgreen)