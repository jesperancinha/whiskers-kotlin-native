include ../Makefile.mk

b: build-gradle
build-gradle:
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	if [ -d "build" ]; then rm -r build; fi; \
	gradle wrapper --no-validate-url; \
	./gradlew; \
	./gradlew nativeCompile; \
	make build-jar; \
	cp build/libs/whiskers-graalvm-0.0.1-SNAPSHOT.jar jvm/whiskers-graalvm.jar
run:
	cat ./build/native/nativeCompile/whiskers-graalvm.build_artifacts.txt
	./build/native/nativeCompile/whiskers-graalvm
update-run: b run
dcup-light:
	cd .. && make dcup-light
build-jar:
	./gradlew bootJar
run-jar:
	java -jar build/libs/whiskers-graalvm-0.0.1-SNAPSHOT.jar
run-test:
	bash test.sh
run-test-jvm:
	cd jvm && make run-test
perform-tests:
	cd .. && make perform-tests
perform-tests-encoded:
	cd .. && make perform-tests-encoded
perform-tests-encoded-no-db:
	cd .. && make perform-tests-encoded-no-db
