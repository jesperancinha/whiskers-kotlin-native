SHELL := /bin/bash

build:
	gcc -c "-I$(PWD)" redcat.c -o redcat.o
	ar rcs redcat.a redcat.o
build-native: build
	cinterop -def redcat.def -compilerOpts "-I$(pwd)" -o redcat.klib
	konanc -l redcat.klib main.kt -linkerOpts redcat.a -o main
install-kotlin-native:
	wget -O  kotlin.native.tar.gz https://github.com/JetBrains/kotlin/releases/download/v1.7.20/kotlin-native-linux-x86_64-1.7.20.tar.gz
	tar -xvzf kotlin-native.tar.gz
