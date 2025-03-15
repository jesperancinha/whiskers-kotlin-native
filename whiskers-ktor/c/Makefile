include ../../Makefile.mk

build:
	gcc -c "-I$(PWD)" redcat.c -o redcat.o
	ar rcs redcat.a redcat.o
build-native: build
	./kotlin.native/bin/cinterop -def redcat.def -compiler-options "-I$(PWD)" -o redcat.klib
	./kotlin.native/bin/konanc -l redcat.klib main.kt -linker-options redcat.a -o main -linker-option
install-native: install-kotlin-native-linux
