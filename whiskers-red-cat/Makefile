SHELL := /bin/bash

b: build build-native
build:
	gcc -c "-I$(PWD)" redcat.c -o redcat.o
	ar rcs redcat.a redcat.o
build-native: build
	./kotlin.native/bin/cinterop -def redcat.def -compiler-options "-I$(PWD)" -o redcat.klib
	./kotlin.native/bin/konanc -l redcat.klib main.kt -linker-options redcat.a -o main
install-kotlin-native-linux:
	tar -xvzf kotlin.native.tar.gz
	if [ -d "kotlin.native" ]; then rm -r kotlin.native; fi
	mv kotlin-native-linux-x86_64-1.7.20 kotlin.native
run:
	./main.kexe