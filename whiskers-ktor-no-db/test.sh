#!/usr/bin/env bash
./build/bin/native/releaseExecutable/whiskers-ktor-no-db.kexe & echo $! > ../pid.file
test=$(<pid.file)
echo -e "\033[92m$test\033[0m"
date +%s | xargs -I {} echo "ktor-no-db,"{} > result-test-ktor-no-db.csv
make perform-tests
date +%s | xargs -I {} echo "ktor-no-db,"{} >> result-test-ktor-no-db.csv
date +%s | xargs -I {} echo "ktor-no-db-encoded,"{} >> result-test-ktor-no-db.csv
make perform-tests-encoded
date +%s | xargs -I {} echo "ktor-no-db-encoded,"{} >> result-test-ktor-no-db.csv
date +%s | xargs -I {} echo "ktor-no-db-encoded-no-db,"{} >> result-test-ktor-no-db.csv
make perform-tests-encoded-no-db
date +%s | xargs -I {} echo "ktor-no-db-encoded-no-db,"{} >> result-test-ktor-no-db.csv
kill "$test"