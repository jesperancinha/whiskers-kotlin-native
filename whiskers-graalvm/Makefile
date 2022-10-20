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
