b: build
build: build-gradle build-gradle-graalvm
build-gradle:
	cd good-feel && make b
	cd plus && make b
	cd whiskers-cloudnative && make b
	make release-gradle
build-gradle-graalvm:
	cd whiskers-graalvm && make b
	make release-gradle-graalvm
release-gradle:
	mkdir -p bin
	cp good-feel/build/bin/native/debugExecutable/good-feel.kexe bin/good-feel
	cp plus/build/bin/native/debugExecutable/plus.kexe bin/plus
release-gradle-graalvm:
	cp whiskers-graalvm/build/native/nativeCompile/whiskers-graalvm bin/whiskers-graalvm