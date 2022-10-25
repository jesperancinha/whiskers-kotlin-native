make install-kotlin-native-linux
cd whiskers-ktor/postgresql || exit
pwd
./install-postgresql.sh
cd ../../
cd whiskers-red-cat-db || exit
pwd
./install-postgresql.sh
cd ../
