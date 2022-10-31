SHELL := /bin/bash

install:
	gu install native-image
	sudo apt-get install build-essential libz-dev zlib1g-dev
b: buildw
buildw:
	gradle fatJar
run:
	java -jar build/libs/whiskers-runners-graalvm-standalone.jar
create-native:
	$(JAVA_HOME)/bin/native-image -jar build/libs/whiskers-runners-graalvm-standalone.jar
run-test:
	date +%s | xargs -I {} echo "graalvm,"{} > ../../result-single-run-graalvm.csv
	for ((i=0; i<=100; i++)); do make run; done
	date +%s | xargs -I {} echo "graalvm,"{} >> ../../result-single-run-graalvm.csv
