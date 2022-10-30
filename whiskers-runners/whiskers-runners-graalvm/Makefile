install:
	gu install native-image
	sudo apt-get install build-essential libz-dev zlib1g-dev
build:
	gradle fatJar
run:
	java -jar build/libs/whiskers-runners-graalvm-standalone.jar
