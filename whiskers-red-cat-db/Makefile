SHELL := /bin/bash

b: build-native
build-all: postgres-build build-native
build:
	gcc -c explicit_bzero_chk.c -o explicit_bzero_chk.o
	ar rcs libpq.a postgres-*/src/interfaces/libpq/*.o postgres-*/src/common/*.o postgres-*/src/interfaces/ecpg/preproc/*.o postgres-*/src/port/*.o postgres-*/src/backend/libpq/*.o postgres-*/src/backend/lib/*.o explicit_bzero_chk.o
build-native: build
	#./kotlin.native/bin/cinterop -def redcat.def -compiler-options "-I$(PWD)" -compiler-options "-I$(PWD)/postgres-*/src/include" -compiler-options "-I$(PWD)/postgres-*/src/port" -o redcat.klib
	./kotlin.native/bin/cinterop -def redcat.def -o redcat.klib
	./kotlin.native/bin/konanc -l redcat.klib main.kt -linker-options libpq.a -o main -linker-option
install-kotlin-native-linux:
	tar -xvzf kotlin.native.tar.gz
	if [ -d "kotlin.native" ]; then rm -r kotlin.native; fi
	if [ -d "postgres-*" ]; then rm -r postgres-*; fi
	mv kotlin-native-prebuilt-linux-x86_64-2.1.10 kotlin.native
	sudo apt install libreadline-dev -y
	unzip -o postgres.zip
postgres-build:
	cd postgres-* && ./configure
	cd postgres-* && make all
library-download:
	wget -O stdbool.h https://raw.githubusercontent.com/openbsd/src/master/include/stdbool.h
	wget -O stdint.h https://raw.githubusercontent.com/openbsd/src/master/sys/sys/stdint.h
	wget -O stdlib.h https://raw.githubusercontent.com/openbsd/src/master/include/stdlib.h
	wget -O stdio.h https://raw.githubusercontent.com/openbsd/src/master/include/stdio.h
	mkdir -p machine && cd machine && wget -O _types.h https://raw.githubusercontent.com/openbsd/src/master/sys/sys/_types.h
	cd machine && wget -O cdefs.h https://raw.githubusercontent.com/openbsd/src/master/sys/sys/cdefs.h
	mkdir -p sys && cd sys && wget -O cdefs.h https://raw.githubusercontent.com/openbsd/src/master/sys/sys/cdefs.h
	cd sys && wget -O _null.h https://raw.githubusercontent.com/openbsd/src/master/sys/sys/_null.h
	cd sys && wget -O _types.h https://raw.githubusercontent.com/openbsd/src/master/sys/sys/_types.h
	cd sys && wget -O stdint.h 	wget -O stdint.h https://raw.githubusercontent.com/openbsd/src/master/sys/sys/stdint.h
run:
	./main.kexe
