#!/usr/bin/env bash
./build/bin/native/releaseExecutable/whiskers-ktor.kexe & echo $! > pid.file
test=$(<pid.file)
echo -e "\033[92m$test\033[0m"
date +%s | xargs -I {} echo "ktor,"{} > ../result-test-no-container-ktor.csv
make perform-tests
date +%s | xargs -I {} echo "ktor,"{} >> ../result-test-no-container-ktor.csv
date +%s | xargs -I {} echo "ktor-encoded,"{} >> ../result-test-no-container-ktor.csv
make perform-tests-encoded
date +%s | xargs -I {} echo "ktor-encoded,"{} >> ../result-test-no-container-ktor.csv
date +%s | xargs -I {} echo "ktor-encoded,"{} >> ../result-test-no-container-ktor.csv
make perform-tests-encoded
date +%s | xargs -I {} echo "ktor-encoded,"{} >> ../result-test-no-container-ktor.csv
kill "$test"
