b: build
build: build-gradle
build-gradle: build-binaries
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