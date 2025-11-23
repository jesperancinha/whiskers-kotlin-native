SHELL := /bin/bash
GITHUB_RUN_ID ?=123
GRADLE_VERSION ?= 9.2.1

b:

buildw:

build:

wrapper:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION) --no-validate-url;

download-kotlin-native:
	if [[ ! -f "kotlin.native.tar.gz" ]]; then wget -O kotlin.native.tar.gz https://github.com/JetBrains/kotlin/releases/download/v2.1.10/kotlin-native-prebuilt-linux-x86_64-2.1.10.tar.gz; fi

install-kotlin-native-linux:
	tar -xvzf kotlin.native.tar.gz
	if [ -d "kotlin.native" ]; then rm -r kotlin.native; fi
	mv kotlin-native-prebuilt-linux-x86_64-2.1.10 kotlin.native