SHELL := /bin/bash

b: build-gradle
build-gradle:
	gradle build
run:
	./build/bin/native/debugExecutable/whiskers-runners-knative.kexe
run-test:
	date +%s | xargs -I {} echo "knative,"{} > ../../result-single-run-knative.csv
	for ((i=0; i<=1000; i++)); do make run; done
	date +%s | xargs -I {} echo "knative,"{} >> ../../result-single-run-knative.csv
run-test-multiple:
	date +%s | xargs -I {} echo "knative,"{} > ../../result-multiple-run-knative.csv
	./build/bin/native/debugExecutable/whiskers-runners-knative.kexe 10000000
	date +%s | xargs -I {} echo "knative,"{} >> ../../result-multiple-run-knative.csv
