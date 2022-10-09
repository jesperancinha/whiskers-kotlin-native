b: build
build: build-gradle
build-gradle:
	cd good-feel && make b
	cd plus && make b
	cd whiskers-cloudnative && make b
	cd whiskers-graalvm && make b
	make release
release:
	mkdir -p bin
	cp good-feel/build/bin/native/debugExecutable/good-feel.kexe bin/good-feel
	cp plus/build/bin/native/debugExecutable/plus.kexe bin/plus
