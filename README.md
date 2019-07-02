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

## Development Options
`make install-dependencies`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;Install all dependencies of project<br/>
`make run`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;Start the application | Go to [localhost:8080](http://localhost:8080/) to see: `Swagger Documentation`<br/>
`make tests`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;Run your app's tests<br/>
`make lint`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;Check format code of the all project (using cljfmt)<br/>
`make fix-format-code`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;Formats the code for the entire project<br/>
`make run-repl`<br/>
&nbsp;&nbsp;&nbsp;&nbsp;Run clojure repl<br/>


# User Registration diagram

## Create User

[SWAGGER DOCUMENTATION TO CREATE USER](https://nukr-gilmar.herokuapp.com/index.html#!/users/create_user)

![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgQ3JlYXRlIFVzZXIgUHJvZmlsZQoKbm90ZSBvdmVyIGFwaSxjb250cm9sbGVyOiBSZXF1ZXN0IGMAMQZ1c2VyIHdpdGggYm9keSByABgGCmFwaS0-ACsMUE9TVCB0bwArDAoATQotPmRiOiBWZXJpZnkgaWYAUAZleGl0IGJ5IGVtYWlsCmFsdABnBm5vdCBleGlzdHMKICAgIGRiAFsOUmV0dXJucyBuaWwKZWxzAIEbBwAOIwCBDgVlbmQAURkAgSMMYXBpAGEKMjAxAIIIB2QAPwcAZBQAJhlFeGNlcHRpb24Agn4GAIFVB2VuZAoKCgoKCgABBQo&s=roundgreen)

# Update User

[SWAGGER DOCUMENTATION TO UPDATE USER](https://nukr-gilmar.herokuapp.com/index.html#!/users/update_user)

![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgVXBkYXRlIHVzZXIKCmFwaS0-Y29udHJvbGxlcjogUFVUABYMIGJ5IElECgAYCi0-ZGI6ABUNYXQgREIKZGIAOg5SZXR1cm4APgZ1AG0FZAA5DQAZCVN0YXR1cyBjb2RlIDIwMCAtIFUAKAw&s=roundgreen)

# Delete User

[SWAGGER DOCUMENTATION TO DELETE USER](https://nukr-gilmar.herokuapp.com/index.html#!/users/delete_user)

![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgRGVsZXRlIHVzZXIKCmFwaS0-ZGI6IExvYWQgYWxsABIGYWx0IFVzZXIgbm90IGV4aXN0cwogICAgYXBpOiBSZXR1cm4gNDA0ABoKZm91bmQKZWxzZQAyBgAmDgBdBgBtDCAgICBkYi0ASwUAZAVyZW1vdmVkAFURMgBeCAAZCGVuZAo&s=roundgreen)

# Get user by ID

[SWAGGER DOCUMENTATION TO GET USER BY ID](https://nukr-gilmar.herokuapp.com/index.html#!/users/get_user)

![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgRGVsZXRlIHVzZXIKCmFwaS0-ZGI6IExvYWQgYWxsABIGYWx0IFVzZXIgbm90IGV4aXN0cwogICAgZGItPmFwaTogUmV0dXJuIDQwNAAeCmZvdW5kCmVsc2UANgYALQsAXglHZXQAdAYAOhRVABEIAIERBQAKECAtIFN0YXR1cyAyMDEKZW5kCg&s=roundgreen)

## Friendships Diagram

# Add Friend

[SWAGGER DOCUMENTATION TO ADD FRIEND](https://nukr-gilmar.herokuapp.com/index.html#!/users/add_friend)

![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgR2V0IGZyaWVuZHMgYnkgdXNlciBJRAoKYXBpLT5jb250cm9sbGVyOiBWZXJpZnkAJQcgZXhpc3QALQVpZAoAHQotPmRiAA4dYWx0IEYANwwKICAgIGRiAF4OUmV0dXJuAIEIBQAbBQBREEFkZACBLQhoaXAAJxwAFw8AgR4MYXBpAGEJAIENBnNoaXAgLSBTdGF0dXMgMjAxCmVsc2UAgSgIbm90AIERI25pbABNHQBWBzQwNABMDGZvdW5kCmVuZACCCgUK&s=roundgreen)

# Get Friendships

[SWAGGER DOCUMENTATION TO GET ALL FRIENDS](https://nukr-gilmar.herokuapp.com/index.html#!/users/all_friends)

![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgR2V0IEZyaWVuZHNoaXAgYnkgdXNlciBpZAoKYXBpLT5jb250cm9sbGVyOgAnBWYAFxUAHAotPmRiAA4cZGIARg5SZXR1cm4ATAtzAEAYYXBpABoVLSBTdGF0dXMgMjAwCg&s=roundgreen)

# Remove Friendship

[SWAGGER DOCUMENTATION TO REMOVE FRIEND](https://nukr-gilmar.herokuapp.com/index.html#!/users/remove_friend)

![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgUmVtb3ZlIEZyaWVuZHNoaXAgYnkgdXNlciBpZAoKYXBpLT5jb250cm9sbGVyOgAnCGYAGhUAHwotPmRiAA4fZGIAShB0dXJuAE8LcyB3aXRoIG91dACBCAggc2VsZWN0ZQBcDmFwaQAQLiAtIFN0YXR1cyAyMDQK&s=roundgreen)

# Recommendation Friends Diagram

[SWAGGER DOCUMENTATION TO GET RECOMMENDATION FRIENDSHIPS](https://nukr-gilmar.herokuapp.com/index.html#!/users/recommendations)

![](https://www.websequencediagrams.com/cgi-bin/cdraw?lz=dGl0bGUgRnJpZW5kcyBSZWNvbW1lbmRhdGlvbgoKYXBpLT5jb250cm9sbGVyOiBHZXQgZgAoBSByAB8NIGJ5IHVzZXItaWQKACcKLT5sb2dpYwARJwAnBQAmDQBWDnMgYWNjb3JkaW5nIHRvIHRoZQBuBSdzAIEMB3MAOggAgSYMUmV0dXJuABoIAIEnDwCBHg1hcGkAEB8gLSBTdGF0dXMgMjAwCg&s=roundgreen)