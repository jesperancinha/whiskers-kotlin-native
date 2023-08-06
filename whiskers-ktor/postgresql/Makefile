SHELL := /bin/bash

b: build
build-all: postgres-build build
build:
	gcc -c explicit_bzero_chk.c -o explicit_bzero_chk.o
	ar rcs postgres.a postgres-master/src/interfaces/libpq/*.o postgres-master/src/common/*.o postgres-master/src/interfaces/ecpg/preproc/*.o postgres-master/src/port/*.o postgres-master/src/backend/libpq/*.o postgres-master/src/backend/lib/*.o explicit_bzero_chk.o
install-kotlin-native-linux:
	tar -xvzf kotlin.native.tar.gz
	if [ -d "kotlin.native" ]; then rm -r kotlin.native; fi
	if [ -d "postgres-master" ]; then rm -r postgres-master; fi
	mv kotlin-native-linux-x86_64-1.7.20 kotlin.native
	sudo apt install libreadline-dev -y
	unzip -o postgres.zip
postgres-build:
	cd postgres-master && ./configure
	cd postgres-master && make all
