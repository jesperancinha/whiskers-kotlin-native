#!/usr/bin/env bash
./build/bin/native/releaseExecutable/whiskers-ktor.kexe > logs & echo $! > pid.file
date +%s | xargs -I {} echo "ktor,"{} > ../result-no-container-ktor.csv
test=$(<pid.file)
string=$(<logs)
while [[ "$string" != *"Application started"* ]]
  do
    string=$(<logs)
    sleep 0.01
  done
date +%s | xargs -I {} echo "ktor,"{} >> ../result-no-container-ktor.csv
pmap "$test" | tail -n 1 | xargs -I {} echo "ktor,"{} >> ../result-no-container-ktor.csv
echo -e "\033[92mStarting process $test\033[0m"
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
echo -e "\033[92mProcess $test has ended!\033[0m"
