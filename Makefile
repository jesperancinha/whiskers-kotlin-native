b: build
build: build-gradle build-gradle-graalvm
build-gradle: build-gradle-ktor
	cd good-feel && make b
	cd plus && make b
	make release-gradle
build-gradle-ktor:
	cd whiskers-ktor && make && make b
	cd whiskers-ktor-harcoded && make b
build-gradle-graalvm:
	mkdir -p bin
	cd whiskers-cloudnative && make b
	cd whiskers-graalvm && make b
	make release-gradle-graalvm
build-gradle-redcat:
	mkdir -p bin
	cd whiskers-red-cat && make b
	make release-gradle-redcat
build-gradle-redcat-db:
	mkdir -p bin
	cd whiskers-red-cat-db && make b
	make release-gradle-redcat-db
release-gradle:
	mkdir -p bin
	cp good-feel/build/bin/native/debugExecutable/good-feel.kexe bin/good-feel
	cp plus/build/bin/native/debugExecutable/plus.kexe bin/plus
	cp whiskers-ktor/build/bin/native/releaseExecutable/whiskers-ktor.kexe bin/whiskers-ktor
	cp whiskers-ktor-harcoded/build/bin/native/releaseExecutable/whiskers-ktor-harcoded.kexe bin/whiskers-ktor-harcoded
release-gradle-graalvm:
	cp whiskers-graalvm/build/native/nativeCompile/whiskers-graalvm bin/whiskers-graalvm
release-gradle-redcat:
	cp whiskers-red-cat/main.kexe bin/whiskers-red-cat
release-gradle-redcat-db:
	cp whiskers-red-cat-db/main.kexe bin/whiskers-red-cat-db
stop:
	docker ps -a -q --filter="name=whiskers" | xargs -I {} docker stop {}
	docker ps -a -q --filter="name=whiskers" | xargs -I {} docker rm {}
docker-clean: stop
	docker-compose down -v
	docker-compose rm -svf
docker-stop-all:
	docker ps -a --format '{{.ID}}' | xargs -I {}  docker stop {}
	docker network prune
dcd: docker-clean
	docker-compose -f docker-compose.yml -f docker-compose.override.yml down
dcup-light: dcd
	docker-compose up -d whiskers-db
	make kong-config
kong-config:
	cd kong && make kong-config
install-kotlin-native-linux:install-kotlin-native-linux-ktor install-kotlin-native-linux-rc install-kotlin-native-linux-rcdb
install-kotlin-native-linux-ktor:
	cd whiskers-ktor/c && make install-kotlin-native-linux
	cd whiskers-ktor/postgresql && make install-kotlin-native-linux
install-kotlin-native-linux-rc:
	cd whiskers-red-cat && make install-kotlin-native-linux
install-kotlin-native-linux-rcdb:
	cd whiskers-red-cat-db && make install-kotlin-native-linux
