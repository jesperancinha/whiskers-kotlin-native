b: build
build: build-gradle build-gradle-graalvm
build-gradle: build-gradle-ktor
	cd good-feel && make b
	cd plus && make b
	make release-gradle
build-gradle-ktor:
	cd whiskers-ktor && make b
	cd whiskers-ktor-harcoded && make b
build-gradle-graalvm:
	mkdir -p bin
	cd whiskers-cloudnative && make b
	cd whiskers-graalvm && make b
	make release-gradle-graalvm
release-gradle:
	mkdir -p bin
	cp good-feel/build/bin/native/debugExecutable/good-feel.kexe bin/good-feel
	cp plus/build/bin/native/debugExecutable/plus.kexe bin/plus
	cp whiskers-ktor/build/bin/native/releaseExecutable/whiskers-ktor.kexe bin/whiskers-ktor
	cp whiskers-ktor-harcoded/build/bin/native/releaseExecutable/whiskers-ktor-harcoded.kexe bin/whiskers-ktor-harcoded
release-gradle-graalvm:
	cp whiskers-graalvm/build/native/nativeCompile/whiskers-graalvm bin/whiskers-graalvm