SHELL := /bin/bash

b: build-gradle
build-gradle:
	gradle build
run:
	./build/bin/native/debugExecutable/whiskers-runners-knative.kexe
run-test:
	date +%s | xargs -I {} echo "knative,"{} > ../../result-single-run-knative.csv
	for ((i=0; i<=500; i++)); do make run; done
	date +%s | xargs -I {} echo "knative,"{} >> ../../result-single-run-knative.csv
