b: build-gradle
build-gradle: build-binaries
	gradle build
build-binaries:
	cd c && make build
run-gradle: build-binaries
	./gradlew :runReleaseExecutableNative
run:
	./build/bin/native/releaseExecutable/whiskers-ktor.kexe
