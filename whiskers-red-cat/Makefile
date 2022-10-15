SHELL := /bin/bash

b: build
build:
	gcc -c "-I$(PWD)" redcat.c -o redcat.o
	ar rcs redcat.a redcat.o
build-native: build
	./kotlin.native/bin/cinterop -def redcat.def -compiler-options "-I$(PWD)" -o redcat.klib
	./kotlin.native/bin/konanc -l redcat.klib main.kt -linker-options redcat.a -o main
install-kotlin-native-linux:
	wget -O  kotlin.native.tar.gz https://github.com/JetBrains/kotlin/releases/download/v1.7.20/kotlin-native-linux-x86_64-1.7.20.tar.gz
	tar -xvzf kotlin.native.tar.gz
	mv kotlin-native-linux-x86_64-1.7.20 kotlin.native
run:
	./main.kexe