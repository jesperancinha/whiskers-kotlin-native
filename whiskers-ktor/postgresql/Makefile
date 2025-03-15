include ../../Makefile.mk

b: build
build-all: postgres-build build
build:
	gcc -c explicit_bzero_chk.c -o explicit_bzero_chk.o
	ar rcs postgres.a postgres-*/src/interfaces/libpq/*.o postgres-*/src/common/*.o postgres-*/src/interfaces/ecpg/preproc/*.o postgres-*/src/port/*.o postgres-*/src/backend/libpq/*.o postgres-*/src/backend/lib/*.o explicit_bzero_chk.o
install-native: install-kotlin-native-linux install-database
install-database:
	sudo apt install libreadline-dev -y
	if [ -d "postgres-*" ]; then rm -r postgres-*; fi
	unzip -o postgres.zip
postgres-build:
	cd postgres-* && ./configure
	cd postgres-* && make all
