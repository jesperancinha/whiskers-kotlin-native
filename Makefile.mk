SHELL := /bin/bash
GITHUB_RUN_ID ?=123
GRADLE_VERSION ?= 8.7

b:

buildw:

build:

wrapper:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --no-validate-url;