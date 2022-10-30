#!/usr/bin/env bash
./build/bin/native/releaseExecutable/whiskers-ktor.kexe > logs & echo $! > pid.file
date +%s | xargs -I {} echo "ktor,"{} > ../result-ktor.csv
test=$(<pid.file)
string=$(<logs)
while [[ "$string" != *"Application started"* ]]
  do
    string=$(<logs)
    sleep 0.01
  done
date +%s | xargs -I {} echo "ktor,"{} >> ../result-ktor.csv
pmap "$test" | tail -n 1 | xargs -I {} echo "ktor,"{} >> ../result-ktor.csv
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
