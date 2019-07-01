# nukr

Social media connections

You are tasked with making Nukr, a new social media product by Nu Everything S/A. The initial step is to create a prototype service that provides a REST API where we can simulate connections between people, and explore how we would offer new connection suggestions.

These are the features required:
- Be able to add a new profile;
- Be able to tag two profiles as connected;
- Be able to generate a list of new connection suggestions for a certain profile, taking the stance that the more connections a profile has with another profile's connections, the better ranked the suggestion should be;
- Some profiles can, for privacy reasons, opt to be hidden from the connection suggestions.

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
![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgQ3JlYXRlIFVzZXIgUHJvZmlsZQoKbm90ZSBvdmVyIGFwaSxjb250cm9sbGVyOiBSZXF1ZXN0IGMAMQZ1c2VyIHdpdGggYm9keSByABgGCmFwaS0-ACsMUE9TVCB0bwArDAoATQotPmRiOiBWZXJpZnkgaWYAUAZleGl0IGJ5IGVtYWlsCmFsdABnBm5vdCBleGlzdHMKICAgIGRiAFsOUmV0dXJucyBuaWwKZWxzAIEbBwAOIwCBDgVlbmQAURkAgSMMYXBpAGEKMjAxAIIIB2QAPwcAZBQAJhlFeGNlcHRpb24Agn4GAIFVB2VuZAoKCgoKCgABBQo&s=roundgreen)

# Update User
![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgVXBkYXRlIHVzZXIKCmFwaS0-Y29udHJvbGxlcjogUFVUABYMIGJ5IElECgAYCi0-ZGI6ABUNYXQgREIKZGIAOg5SZXR1cm4APgZ1AG0FZAA5DQAZCVN0YXR1cyBjb2RlIDIwMCAtIFUAKAw&s=roundgreen)

# Delete User
![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgRGVsZXRlIHVzZXIKCmFwaS0-ZGI6IExvYWQgYWxsABIGYWx0IFVzZXIgbm90IGV4aXN0cwogICAgYXBpOiBSZXR1cm4gNDA0ABoKZm91bmQKZWxzZQAyBgAmDgBdBgBtDCAgICBkYi0ASwUAZAVyZW1vdmVkAFURMgBeCAAZCGVuZAo&s=roundgreen)

# Get user by ID
![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgRGVsZXRlIHVzZXIKCmFwaS0-ZGI6IExvYWQgYWxsABIGYWx0IFVzZXIgbm90IGV4aXN0cwogICAgZGItPmFwaTogUmV0dXJuIDQwNAAeCmZvdW5kCmVsc2UANgYALQsAXglHZXQAdAYAOhRVABEIAIERBQAKECAtIFN0YXR1cyAyMDEKZW5kCg&s=roundgreen)

## Diagram add friendships

# Add Friend
![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgR2V0IGZyaWVuZHMgYnkgdXNlciBJRAoKYXBpLT5jb250cm9sbGVyOiBWZXJpZnkAJQcgZXhpc3QALQVpZAoAHQotPmRiAA4dYWx0IEYANwwKICAgIGRiAF4OUmV0dXJuAIEIBQAbBQBREEFkZACBLQhoaXAAJxwAFw8AgR4MYXBpAGEJAIENBnNoaXAgLSBTdGF0dXMgMjAxCmVsc2UAgSgIbm90AIERI25pbABNHQBWBzQwNABMDGZvdW5kCmVuZACCCgUK&s=roundgreen)