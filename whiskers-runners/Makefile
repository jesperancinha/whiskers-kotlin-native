include ../Makefile.mk

b: build-gradle
build-gradle:
	cd whiskers-runners-graalvm && make b
	cd whiskers-runners-knative && make b
	cd whiskers-runners-native && make b