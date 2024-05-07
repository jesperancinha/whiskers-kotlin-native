b: build
build: build-gradle
build-gradle: build-binaries
	if [ -d "build" ]; then rm -r build; fi
	gradle build
build-binaries:
	cd c && make build
	cd postgresql && make build
run-gradle: build-binaries
	./gradlew :runReleaseExecutableNative
run:
	./build/bin/native/releaseExecutable/whiskers-ktor.kexe
dcup-light:
	cd .. && make dcup-light
update-run: build
	make run
install-kotlin-native-linux-ktor:
	cd c && make install-kotlin-native-linux
	cd postgresql && make install-kotlin-native-linux
run-test:
	bash test.sh
perform-tests:
	cd .. && make perform-tests
perform-tests-encoded:
	cd .. && make perform-tests-encoded
perform-tests-encoded-no-db:
	cd .. && make perform-tests-encoded-no-db
