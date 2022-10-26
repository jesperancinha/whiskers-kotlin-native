SHELL := /bin/bash
GITHUB_RUN_ID ?=123

b: build
build: build-gradle build-gradle-graalvm
build-gradle: build-gradle-ktor
	cd good-feel && make b
	cd plus && make b
	make release-gradle
build-gradle-ktor:
	cd whiskers-ktor && make b
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
	docker-compose -p ${GITHUB_RUN_ID} up -d whiskers-db
	make kong-config
kong-config:
	cd kong && make kong-config
download-binaries:
	if [[ ! -f "kotlin.native.tar.gz" ]]; then wget -O kotlin.native.tar.gz https://github.com/JetBrains/kotlin/releases/download/v1.7.20/kotlin-native-linux-x86_64-1.7.20.tar.gz; fi
	if [[ ! -f "postgres.zip" ]]; then wget -O postgres.zip https://github.com/postgres/postgres/archive/refs/heads/master.zip; fi
copy-binaries:
	cp kotlin*.tar.gz whiskers-ktor/postgresql
	cp kotlin*.tar.gz whiskers-ktor/c
	cp postgres.zip whiskers-ktor/postgresql
	cp kotlin*.tar.gz whiskers-red-cat
	cp kotlin*.tar.gz whiskers-red-cat-db
	cp postgres.zip whiskers-red-cat-db
setup-binaries: download-binaries copy-binaries
install-kotlin-native-linux: setup-binaries install-kotlin-native-linux-ktor install-kotlin-native-linux-rc install-kotlin-native-linux-rcdb
install-kotlin-native-linux-ktor:
	cd whiskers-ktor/c && make install-kotlin-native-linux
	cd whiskers-ktor/postgresql && make install-kotlin-native-linux
install-kotlin-native-linux-rc:
	cd whiskers-red-cat && make install-kotlin-native-linux
install-kotlin-native-linux-rcdb:
	cd whiskers-red-cat-db && make install-kotlin-native-linux
install-python:
	sudo apt-get install python3-distutils
	sudo apt-get install python3-apt
run-paragraph-sender:
	cd whiskers-paragraph-sender && python paragraph_sender.py
dcup-ktor: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor/docker-compose.yml -f whiskers-ktor/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor/docker-compose.yml -f whiskers-ktor/docker-compose.override.yml up -d whiskers-db
	make db-wait
	date +%s | xargs -I {} echo "ktor,"{} > result-ktor.csv
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor/docker-compose.yml -f whiskers-ktor/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-ktor
	date +%s | xargs -I {} echo "ktor,"{} >> result-ktor.csv
dcd-ktor:
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f  whiskers-ktor/docker-compose.yml -f whiskers-ktor/docker-compose.override.yml down
dcup-graalvm: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose.yml -f whiskers-graalvm/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose.yml -f whiskers-graalvm/docker-compose.override.yml up -d whiskers-db
	make db-wait
	date +%s | xargs -I {} echo "graalvm,"{} > result-graalvm.csv
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose.yml -f whiskers-graalvm/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-graalvm
	date +%s | xargs -I {} echo "graalvm,"{} >> result-graalvm.csv
dcd-graalvm:
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f  whiskers-graalvm/docker-compose.yml -f whiskers-graalvm/docker-compose.override.yml down
dcup-cloudnative: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-cloudnative/docker-compose.yml -f whiskers-cloudnative/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-cloudnative/docker-compose.yml -f whiskers-cloudnative/docker-compose.override.yml up -d whiskers-db
	make db-wait
	date +%s | xargs -I {} echo "cloudnative,"{} > result-cloudnative.csv
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-cloudnative/docker-compose.yml -f whiskers-cloudnative/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-cloudnative
	date +%s | xargs -I {} echo "cloudnative,"{} >> result-cloudnative.csv
dcd-cloudnative:
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f  whiskers-cloudnative/docker-compose.yml -f whiskers-cloudnative/docker-compose.override.yml down
dcup-jvm: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose-jvm.yml -f whiskers-graalvm/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose-jvm.yml -f whiskers-graalvm/docker-compose.override.yml up -d whiskers-db
	make db-wait
	date +%s | xargs -I {} echo "jvm,"{} > result-jvm.csv
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose-jvm.yml -f whiskers-graalvm/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-graalvm
	date +%s | xargs -I {} echo "jvm,"{} >> result-jvm.csv
dcd-jvm:
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f  whiskers-graalvm/docker-compose-jvm.yml -f whiskers-graalvm/docker-compose.override.yml down
db-wait:
	bash db_wait.sh
measure-all-sts: dcup-ktor dcd-ktor dcup-graalvm dcd-graalvm dcup-cloudnative dcd-cloudnative dcup-jvm dcd-jvm
cat-sayings-run:
	cd whiskers-paragraph-sender && make cat-sayings-run
perform-tests:
	cd whiskers-paragraph-sender && python3 test_all.py
