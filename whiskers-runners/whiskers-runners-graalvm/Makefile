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
