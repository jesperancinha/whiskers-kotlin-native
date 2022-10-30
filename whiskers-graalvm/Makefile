b: build-gradle
build-gradle:
	if [ -d "build" ]; then rm -r build; fi
	./gradlew
	./gradlew nativeCompile
	make build-jar
	cp build/libs/whiskers-graalvm-0.0.1-SNAPSHOT.jar jvm/whiskers-graalvm.jar
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
run-test:
	bash test.sh
perform-tests:
	cd .. && make perform-tests
perform-tests-encoded:
	cd .. && make perform-tests-encoded
perform-tests-encoded-no-db:
	cd .. && make perform-tests-encoded-no-db
