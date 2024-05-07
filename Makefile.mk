SHELL := /bin/bash
GITHUB_RUN_ID ?=123
GRADLE_VERSION ?= 8.7

b:

buildw:

build:

wrapper:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --no-validate-url;

install-kotlin-native-linux:
	tar -xvzf kotlin.native.tar.gz
	if [ -d "kotlin.native" ]; then rm -r kotlin.native; fi
	mv kotlin-native-linux-x86_64-1.9.22 kotlin.native