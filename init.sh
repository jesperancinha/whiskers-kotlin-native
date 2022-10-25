make install-kotlin-native-linux
cd whiskers-ktor/postgresql || exit
./install-postgresql.sh
cd ../../
cd whiskers-red-cat-db || exit
./install-postgresql.sh
