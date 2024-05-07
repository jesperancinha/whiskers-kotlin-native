include ../../Makefile.mk

b: build
build-all: postgres-build build
build:
	gcc -c explicit_bzero_chk.c -o explicit_bzero_chk.o
	ar rcs postgres.a postgres-master/src/interfaces/libpq/*.o postgres-master/src/common/*.o postgres-master/src/interfaces/ecpg/preproc/*.o postgres-master/src/port/*.o postgres-master/src/backend/libpq/*.o postgres-master/src/backend/lib/*.o explicit_bzero_chk.o
install-native: install-kotlin-native-linux install-database
install-database:
	sudo apt install libreadline-dev -y
	if [ -d "postgres-master" ]; then rm -r postgres-master; fi
	unzip -o postgres.zip
postgres-build:
	cd postgres-master && ./configure
	cd postgres-master && make all
