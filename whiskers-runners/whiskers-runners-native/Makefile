SHELL := /bin/bash

b: build-native buildw
buildw:
	gradle build
build-native:
	if [ -d "build" ]; then rm -r build; fi
	gcc -c "-I$(PWD)" src/nativeInterop/cinterop/code/redcat.c -o src/nativeInterop/cinterop/code/redcat.o
	ar rcs src/nativeInterop/cinterop/code/redcat.a src/nativeInterop/cinterop/code/redcat.o
	./kotlin.native/bin/cinterop -def src/nativeInterop/cinterop/redcat.def -compiler-options "-I$(PWD)/src/nativeInterop/cinterop/code" -o src/nativeInterop/cinterop/code/redcat.klib
	./kotlin.native/bin/konanc -l src/nativeInterop/cinterop/code/redcat.klib src/nativeMain/kotlin/Main.kt -linker-options src/nativeInterop/cinterop/code/redcat.a -o main
install-kotlin-native-linux:
	tar -xvzf kotlin.native.tar.gz
	if [ -d "kotlin.native" ]; then rm -r kotlin.native; fi
	mv kotlin-native-linux-x86_64-1.7.20 kotlin.native
copy-binaries:
	cd ../../ && make copy-binaries
run:
	./build/bin/native/debugExecutable/whiskers-runners-native.kexe
run-test:
	date +%s | xargs -I {} echo "native,"{} > ../../result-single-run-native.csv
	for ((i=0; i<=1000; i++)); do make run; done
	date +%s | xargs -I {} echo "native,"{} >> ../../result-single-run-native.csv
run-test-multiple:
	date +%s | xargs -I {} echo "native,"{} > ../../result-multiple-run-native.csv
	./build/bin/native/debugExecutable/whiskers-runners-native.kexe 10000000
	date +%s | xargs -I {} echo "native,"{} >> ../../result-multiple-run-native.csv

