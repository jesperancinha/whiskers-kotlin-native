include Makefile.mk

MODULE_LOCATIONS := whiskers-cloudnative \
					whiskers-graalvm \
					whiskers-ktor \
					whiskers-ktor-no-db \
					whiskers-runners \
					whiskers-runners/whiskers-runners-graalvm \
					whiskers-runners/whiskers-runners-knative \
					whiskers-runners/whiskers-runners-native \
					plus \
					good-feel

b: build
clean:
	if [ -d build ]; then rm -f build; fi;
build-one-gradle: clean
	 export GRADLE_VERSION=$(GRADLE_VERSION); \
	./gradlew build
build: clean build-gradle build-gradle-graalvm build-runners
build-runners:
	cd whiskers-runners && make b
build-gradle-good-feel:
	cd good-feel && make b
build-gradle-plus:
	cd plus && make b
build-gradle: build-gradle-all-ktor build-gradle-good-feel build-gradle-plus
	make release-gradle
build-gradle-ktor:
	cd whiskers-ktor && make b
build-gradle-ktor-no-db:
	cd whiskers-ktor-no-db && make b
build-gradle-all-ktor: build-gradle-ktor build-gradle-ktor-no-db
build-gradle-exec-graalvm:
	mkdir -p bin; \
	cd whiskers-graalvm; \
	export GRADLE_VERSION=$(GRADLE_VERSION); \
	gradle wrapper --gradle-version $(GRADLE_VERSION); \
	make b
build-gradle-cloud-graalvm: wrapper
	mkdir -p bin; \
	cd whiskers-cloudnative; \
	make b
build-gradle-graalvm: build-gradle-exec-graalvm build-gradle-cloud-graalvm
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
	cp whiskers-ktor-no-db/build/bin/native/releaseExecutable/whiskers-ktor-no-db.kexe bin/whiskers-ktor-no-db
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
download-binaries: download-kotlin-native
	if [[ ! -f "postgres.zip" ]]; then wget -O postgres.zip https://github.com/postgres/postgres/archive/refs/heads/master.zip; fi
copy-binaries:
	cp kotlin*.tar.gz whiskers-ktor/postgresql
	cp kotlin*.tar.gz whiskers-ktor/c
	cp postgres.zip whiskers-ktor/postgresql
	cp kotlin*.tar.gz whiskers-red-cat
	cp kotlin*.tar.gz whiskers-red-cat-db
	cp kotlin*.tar.gz whiskers-runners/whiskers-runners-native
	cp postgres.zip whiskers-red-cat-db
setup-binaries: download-binaries copy-binaries
install-libs:
	sudo apt install libreadline-dev libpq-dev bison flex build-essential libz-dev zlib1g-dev -y
install-kotlin-native-linux: setup-binaries install-libs install-kotlin-native-linux-ktor install-kotlin-native-linux-rc install-kotlin-native-linux-rcdb
install-kotlin-native-linux-ktor:
	cd whiskers-ktor/c && make install-native
	cd whiskers-ktor/postgresql && make install-native
install-kotlin-native-linux-rc:
	cd whiskers-red-cat && make install-kotlin-native-linux
install-kotlin-native-linux-rcdb:
	cd whiskers-red-cat-db && make install-kotlin-native-linux
install-python:
	sudo apt-get install python3-distutils -y
	sudo apt-get install python3-apt -y
run-paragraph-sender:
	cd whiskers-paragraph-sender && python3 paragraph_sender.py
dcup-ktor: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor/docker-compose.yml -f whiskers-ktor/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor/docker-compose.yml -f whiskers-ktor/docker-compose.override.yml up -d whiskers-db
	make db-wait
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor/docker-compose.yml -f whiskers-ktor/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-ktor ktor
dcup-test-ktor: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor/docker-compose.yml -f whiskers-ktor/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor/docker-compose.yml -f whiskers-ktor/docker-compose.override.yml up -d whiskers-db
	make db-wait
	date +%s | xargs -I {} echo "ktor,"{} > result-ktor.csv
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor/docker-compose.yml -f whiskers-ktor/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-ktor ktor
	date +%s | xargs -I {} echo "ktor,"{} >> result-ktor.csv
	date +%s | xargs -I {} echo "ktor,"{} > result-test-ktor.csv
	make perform-tests
	date +%s | xargs -I {} echo "ktor,"{} >> result-test-ktor.csv
	date +%s | xargs -I {} echo "ktor-encoded,"{} >> result-test-ktor.csv
	make perform-tests-encoded
	date +%s | xargs -I {} echo "ktor-encoded,"{} >> result-test-ktor.csv
	date +%s | xargs -I {} echo "ktor-encoded-no-db,"{} >> result-test-ktor.csv
	make perform-tests-encoded-no-db
	date +%s | xargs -I {} echo "ktor-encoded-no-db,"{} >> result-test-ktor.csv
dcd-ktor:
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f  whiskers-ktor/docker-compose.yml -f whiskers-ktor/docker-compose.override.yml down
dcup-ktor-no-db: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor-no-db/docker-compose.yml -f whiskers-ktor-no-db/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor-no-db/docker-compose.yml -f whiskers-ktor-no-db/docker-compose.override.yml up -d whiskers-db
	make db-wait
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor-no-db/docker-compose.yml -f whiskers-ktor-no-db/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-ktor-no-db ktor-no-db
dcup-test-ktor-no-db: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor-no-db/docker-compose.yml -f whiskers-ktor-no-db/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor-no-db/docker-compose.yml -f whiskers-ktor-no-db/docker-compose.override.yml up -d whiskers-db
	make db-wait
	date +%s | xargs -I {} echo "ktor-no-db,"{} > result-ktor-no-db.csv
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-ktor-no-db/docker-compose.yml -f whiskers-ktor-no-db/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-ktor-no-db ktor-no-db
	date +%s | xargs -I {} echo "ktor-no-db,"{} >> result-ktor-no-db.csv
	date +%s | xargs -I {} echo "ktor-no-db,"{} > result-test-ktor-no-db.csv
	make perform-tests
	date +%s | xargs -I {} echo "ktor-no-db,"{} >> result-test-ktor-no-db.csv
	date +%s | xargs -I {} echo "ktor-no-db-encoded,"{} >> result-test-ktor-no-db.csv
	make perform-tests-encoded
	date +%s | xargs -I {} echo "ktor-no-db-encoded,"{} >> result-test-ktor-no-db.csv
	date +%s | xargs -I {} echo "ktor-no-db-encoded-no-db,"{} >> result-test-ktor-no-db.csv
	make perform-tests-encoded-no-db
	date +%s | xargs -I {} echo "ktor-no-db-encoded-no-db,"{} >> result-test-ktor-no-db.csv
dcd-ktor-no-db:
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f  whiskers-ktor-no-db/docker-compose.yml -f whiskers-ktor-no-db/docker-compose.override.yml down
dcup-graalvm: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose.yml -f whiskers-graalvm/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose.yml -f whiskers-graalvm/docker-compose.override.yml up -d whiskers-db
	make db-wait
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose.yml -f whiskers-graalvm/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-graalvm graalvm
dcup-test-graalvm: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose.yml -f whiskers-graalvm/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose.yml -f whiskers-graalvm/docker-compose.override.yml up -d whiskers-db
	make db-wait
	date +%s | xargs -I {} echo "graalvm,"{} > result-graalvm.csv
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose.yml -f whiskers-graalvm/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-graalvm graalvm
	date +%s | xargs -I {} echo "graalvm,"{} >> result-graalvm.csv
	date +%s | xargs -I {} echo "graalvm,"{} > result-test-graalvm.csv
	make perform-tests
	date +%s | xargs -I {} echo "graalvm,"{} >> result-test-graalvm.csv
	date +%s | xargs -I {} echo "graalvm-encoded,"{} >> result-test-graalvm.csv
	make perform-tests-encoded
	date +%s | xargs -I {} echo "graalvm-encoded,"{} >> result-test-graalvm.csv
	date +%s | xargs -I {} echo "graalvm-encoded-no-db,"{} >> result-test-graalvm.csv
	make perform-tests-encoded-no-db
	date +%s | xargs -I {} echo "graalvm-encoded-no-db,"{} >> result-test-graalvm.csv
dcd-graalvm:
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f  whiskers-graalvm/docker-compose.yml -f whiskers-graalvm/docker-compose.override.yml down
dcup-cloudnative: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-cloudnative/docker-compose.yml -f whiskers-cloudnative/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-cloudnative/docker-compose.yml -f whiskers-cloudnative/docker-compose.override.yml up -d whiskers-db
	make db-wait
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-cloudnative/docker-compose.yml -f whiskers-cloudnative/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-cloudnative cloudnative
dcup-test-cloudnative: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-cloudnative/docker-compose.yml -f whiskers-cloudnative/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-cloudnative/docker-compose.yml -f whiskers-cloudnative/docker-compose.override.yml up -d whiskers-db
	make db-wait
	date +%s | xargs -I {} echo "cloudnative,"{} > result-cloudnative.csv
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-cloudnative/docker-compose.yml -f whiskers-cloudnative/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-cloudnative cloudnative
	date +%s | xargs -I {} echo "cloudnative,"{} >> result-cloudnative.csv
	date +%s | xargs -I {} echo "cloudnative,"{} > result-test-cloudnative.csv
	make perform-tests
	date +%s | xargs -I {} echo "cloudnative,"{} >> result-test-cloudnative.csv
	date +%s | xargs -I {} echo "cloudnative-encoded,"{} >> result-test-cloudnative.csv
	make perform-tests-encoded
	date +%s | xargs -I {} echo "cloudnative-encoded,"{} >> result-test-cloudnative.csv
	date +%s | xargs -I {} echo "cloudnative-encoded-no-db,"{} >> result-test-cloudnative.csv
	make perform-tests-encoded-no-db
	date +%s | xargs -I {} echo "cloudnative-encoded-no-db,"{} >> result-test-cloudnative.csv
dcd-cloudnative:
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f  whiskers-cloudnative/docker-compose.yml -f whiskers-cloudnative/docker-compose.override.yml down
dcup-jvm: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose-jvm.yml -f whiskers-graalvm/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose-jvm.yml -f whiskers-graalvm/docker-compose.override.yml up -d whiskers-db
	make db-wait
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose-jvm.yml -f whiskers-graalvm/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-graalvm jvm
dcup-test-jvm: stop
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose-jvm.yml -f whiskers-graalvm/docker-compose.override.yml build
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose-jvm.yml -f whiskers-graalvm/docker-compose.override.yml up -d whiskers-db
	make db-wait
	date +%s | xargs -I {} echo "jvm,"{} > result-jvm.csv
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f whiskers-graalvm/docker-compose-jvm.yml -f whiskers-graalvm/docker-compose.override.yml up -d
	bash whiskers_wait.sh whiskers-graalvm jvm
	date +%s | xargs -I {} echo "jvm,"{} >> result-jvm.csv
	date +%s | xargs -I {} echo "jvm,"{} > result-test-jvm.csv
	make perform-tests
	date +%s | xargs -I {} echo "jvm,"{} >> result-test-jvm.csv
	date +%s | xargs -I {} echo "jvm-encoded,"{} >> result-test-jvm.csv
	make perform-tests-encoded
	date +%s | xargs -I {} echo "jvm-encoded,"{} >> result-test-jvm.csv
	date +%s | xargs -I {} echo "jvm-encoded-no-db,"{} >> result-test-jvm.csv
	make perform-tests-encoded-no-db
	date +%s | xargs -I {} echo "jvm-encoded-no-db,"{} >> result-test-jvm.csv
dcd-jvm:
	docker-compose -p ${GITHUB_RUN_ID} -f docker-compose.yml -f docker-compose.override.yml -f  whiskers-graalvm/docker-compose-jvm.yml -f whiskers-graalvm/docker-compose.override.yml down
db-wait:
	bash db_wait.sh
measure-all-sts: dcup-test-ktor dcd-ktor dcup-test-ktor-no-db dcd-ktor-no-db dcup-test-graalvm dcd-graalvm dcup-test-jvm dcd-jvm dcup-test-cloudnative dcd-cloudnative
measure-all-no-container-sts: test-ktor-no-db test-ktor test-graalvm test-graalvm-jvm
measure-all-runners-sts: runnable-test-graalvm runnable-test-knative runnable-test-native runnable-test-jvm
measure-all: measure-all-sts measure-all-no-container-sts measure-all-runners-sts stats
stats:
	cd whiskers-paragraph-sender && python3 make_stats.py
cat-sayings-run:
	cd whiskers-paragraph-sender && make cat-sayings-run
cat-sayings-list:
	cd whiskers-paragraph-sender && make cat-sayings-list
perform-tests:
	cd whiskers-paragraph-sender && python3 test_all.py
perform-tests-encoded:
	cd whiskers-paragraph-sender && python3 test_all_encoded.py
perform-tests-encoded-no-db:
	cd whiskers-paragraph-sender && python3 test_all_encoded_no_db.py
test-ktor-no-db:
	cd whiskers-ktor-no-db && make run-test
test-ktor: dcup-light db-wait
	cd whiskers-ktor && make run-test
test-graalvm: dcup-light db-wait
	cd whiskers-graalvm && make run-test
test-graalvm-jvm: dcup-light db-wait
	cd whiskers-graalvm && make run-test-jvm
runnable-test-graalvm:
	cd whiskers-runners/whiskers-runners-graalvm && make run-test
runnable-test-knative:
	cd whiskers-runners/whiskers-runners-knative && make run-test
runnable-test-native:
	cd whiskers-runners/whiskers-runners-native && make run-test
runnable-test-jvm:
	cd whiskers-runners/whiskers-runners-graalvm && make run-test-jar
upgrade:
	@for location in $(MODULE_LOCATIONS); do \
  		export CURRENT=$(shell pwd); \
  		echo "Upgrading $$location..."; \
		cd $$location; \
		gradle wrapper --gradle-version $(GRADLE_VERSION); \
		cd $$CURRENT; \
	done
	gradle wrapper --gradle-version $(GRADLE_VERSION)
upgrade-gradle:
	sudo apt upgrade -y
	sudo apt update -y
	export SDKMAN_DIR="$(HOME)/.sdkman"; \
	[[ -s "$(HOME)/.sdkman/bin/sdkman-init.sh" ]]; \
	source "$(HOME)/.sdkman/bin/sdkman-init.sh"; \
	sdk update; \
	gradleOnlineVersion=$(shell curl -s https://services.gradle.org/versions/current | jq .version | xargs -I {} echo {}); \
	if [[ -z "$$gradleOnlineVersion" ]]; then \
		sdk install gradle $(GRADLE_VERSION); \
		sdk use gradle $(GRADLE_VERSION); \
	else \
		sdk install gradle $$gradleOnlineVersion; \
		sdk use gradle $$gradleOnlineVersion; \
		export GRADLE_VERSION=$$gradleOnlineVersion; \
	fi; \
	make upgrade
install-linux:
	sudo apt-get install jq curl -y
	curl https://services.gradle.org/versions/current
local-pipeline-ktor-no-db: build-gradle-ktor-no-db
local-pipeline-ktor: install-libs setup-binaries install-kotlin-native-linux-ktor
	cd whiskers-ktor/postgresql/postgres-master && ./configure && make all
	make build-gradle-ktor
local-pipeline-good-feel: build-gradle-good-feel
local-pipeline-plus: build-gradle-plus
local-pipeline-graal-exec: build-gradle-exec-graalvm
local-pipeline-graal-cloud: build-gradle-cloud-graalvm
local-pipeline-linux: setup-binaries install-kotlin-native-linux install-kotlin-native-linux-ktor install-kotlin-native-linux-rc install-kotlin-native-linux-rcdb local-pipeline-good-feel local-pipeline-plus local-pipeline-ktor-no-db local-pipeline-ktor  local-pipeline-graal-exec local-pipeline-graal-cloud
github-pipeline-ktor:
	sudo apt install libreadline-dev; \
  	sudo apt-get install libpq-dev; \
    sudo apt install bison; \
    sudo apt install flex; \
	make setup-binaries; \
	export GRADLE_OPTS="-Xmx2048m -Dorg.gradle.jvmargs='-Xmx2048m -XX:MaxPermSize=2048m'"; \
	make install-kotlin-native-linux-ktor; \
	cd whiskers-ktor/postgresql/postgres-master && ./configure && make all; \
	cd ../../../ ; \
	make build-gradle-ktor
local-pipeline: local-pipeline-good-feel local-pipeline-plus local-pipeline-ktor local-pipeline-ktor-no-db local-pipeline-graal-exec local-pipeline-graal-cloud
deps-plugins-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/pluginUpdatesOne.sh | bash -s -- $(PARAMS)
deps-java-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/javaUpdatesOne.sh | bash
deps-gradle-update:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/gradleUpdatesOne.sh | bash
deps-quick-update: deps-plugins-update deps-java-update deps-gradle-update
accept-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/acceptPR.sh | bash
update-repo-prs:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/update-all-repo-prs.sh | bash
dc-migration:
	curl -sL https://raw.githubusercontent.com/jesperancinha/project-signer/master/setupDockerCompose.sh | bash
