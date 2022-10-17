SHELL := /bin/bash

b: build build-native
build:
	gcc -c "-I$(PWD)/postgres-master/src/include" "-I$(PWD)/postgres-master/src/port" postgres-master/src/interfaces/libpq/fe-connect.c -o fe-connect.o
	ar rcs fe-connect.a fe-connect.o
build-native: build
	./kotlin.native/bin/cinterop -def redcat.def -compiler-options "-I$(PWD)" -compiler-options "-I$(PWD)/postgres-master/src/include" -compiler-options "-I$(PWD)/postgres-master/src/port" -o redcat.klib
	./kotlin.native/bin/konanc -l redcat.klib main.kt -linker-options fe-connect.a -o main
install-kotlin-native-linux: library-download
	wget -O  kotlin.native.tar.gz https://github.com/JetBrains/kotlin/releases/download/v1.7.20/kotlin-native-linux-x86_64-1.7.20.tar.gz
	tar -xvzf kotlin.native.tar.gz
	mv kotlin-native-linux-x86_64-1.7.20 kotlin.native
	sudo apt install libreadline-dev
	wget -O postgres.zip https://github.com/postgres/postgres/archive/refs/heads/master.zip
	unzip postgres.zip
	cd postgres-master && ./configure
	cd postgres-master && make all
library-download:
	wget -O stdbool.h https://raw.githubusercontent.com/openbsd/src/master/include/stdbool.h
	wget -O stdint.h https://raw.githubusercontent.com/openbsd/src/master/sys/sys/stdint.h
	wget -O stdlib.h https://raw.githubusercontent.com/openbsd/src/master/include/stdlib.h
	mkdir -p machine && cd machine && wget -O _types.h https://raw.githubusercontent.com/openbsd/src/master/sys/sys/_types.h
	cd machine && wget -O cdefs.h https://raw.githubusercontent.com/openbsd/src/master/sys/sys/cdefs.h
	mkdir -p sys && cd sys && wget -O cdefs.h https://raw.githubusercontent.com/openbsd/src/master/sys/sys/cdefs.h
run:
	./main.kexe
