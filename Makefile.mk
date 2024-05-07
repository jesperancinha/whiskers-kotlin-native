SHELL := /bin/bash
GITHUB_RUN_ID ?=123
GRADLE_VERSION ?= 8.7

b:

buildw:

build:

wrapper:
	gradle wrapper --gradle-version $(GRADLE_VERSION);