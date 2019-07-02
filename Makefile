.PHONY: help

help:  ## This help
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}' $(MAKEFILE_LIST) | sort

fix-format-code: ## Formats the code for the entire project
	@lein cljfmt fix

lint: ## Check format code of the all project
	@lein cljfmt check

run: ## Run server
	@lein run

tests: ## Run tests
	@lein test

install-dependencies: ## Install dependencies
	@lein deps

run-repl: ## Run clojure repl
	@lein repl

all-check: lint tests

uberjar: ## Build an uberjar of your service
	@lein uberjar

heroku-deploy: uberjar
	@heroku deploy:jar target/nukr.jar --app nukr-gilmar

build-image: ## Build a Docker image
	@lein sudo docker build -t nukr .

run-docker-image: ## Run your Docker image
	docker run -p 8080:8080 nukr
