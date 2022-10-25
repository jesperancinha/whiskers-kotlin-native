b: build-gradle
build-gradle:
	./gradlew
	./gradlew nativeCompile
run:
	cat ./build/native/nativeCompile/whiskers-graalvm.build_artifacts.txt
	./build/native/nativeCompile/whiskers-graalvm
update-run: b run
dcup-light:
	cd .. && make dcup-light
build-jar:
	echo "Please run this with JDK17!"
	./gradlew bootJar
run-jar:
	java -jar build/libs/whiskers-graalvm-0.0.1-SNAPSHOT.jar
