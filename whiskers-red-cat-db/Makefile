SHELL := /bin/bash

b: build build-native
build:
	gcc -c explicit_bzero_chk.c -o explicit_bzero_chk.o
	ar rcs libpq.a postgres-master/src/interfaces/libpq/*.o postgres-master/src/common/*.o postgres-master/src/interfaces/ecpg/preproc/*.o postgres-master/src/port/*.o postgres-master/src/backend/libpq/*.o postgres-master/src/backend/lib/*.o explicit_bzero_chk.o
build-native: build
	#./kotlin.native/bin/cinterop -def redcat.def -compiler-options "-I$(PWD)" -compiler-options "-I$(PWD)/postgres-master/src/include" -compiler-options "-I$(PWD)/postgres-master/src/port" -o redcat.klib
	./kotlin.native/bin/cinterop -def redcat.def -o redcat.klib
	./kotlin.native/bin/konanc -l redcat.klib main.kt -linker-options libpq.a -o main
install-kotlin-native-linux:
	wget -O  kotlin.native.tar.gz https://github.com/JetBrains/kotlin/releases/download/v1.7.20/kotlin-native-linux-x86_64-1.7.20.tar.gz
	tar -xvzf kotlin.native.tar.gz
	mv kotlin-native-linux-x86_64-1.7.20 kotlin.native
	sudo apt install libreadline-dev
	wget -O postgres.zip https://github.com/postgres/postgres/archive/refs/heads/master.zip
	unzip postgres.zip
	make postgres-build
postgres-build:
	cd postgres-master && ./configure
	cd postgres-master && make all
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
