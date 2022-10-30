#!/usr/bin/env bash
./build/bin/native/releaseExecutable/whiskers-ktor-no-db.kexe > logs & echo $! > pid.file
date +%s | xargs -I {} echo "ktor-no-db,"{} > ../result-no-container-ktor-no-db.csv
test=$(<pid.file)
string=$(<logs)
while [[ "$string" != *"Application started"* ]]
  do
    string=$(<logs)
    sleep 0.01
  done
date +%s | xargs -I {} echo "ktor-no-db,"{} >> ../result-no-container-ktor-no-db.csv
ps -p "$test" -o rss | tail -n 1 | xargs -I {} echo "ktor-no-db,"{} >> ../result-no-container-ktor-no-db.csv
echo -e "\033[92mStarting process $test\033[0m"
date +%s | xargs -I {} echo "ktor-no-db,"{} > ../result-test-no-container-ktor-no-db.csv
make perform-tests
date +%s | xargs -I {} echo "ktor-no-db,"{} >> ../result-test-no-container-ktor-no-db.csv
date +%s | xargs -I {} echo "ktor-no-db-encoded,"{} >> ../result-test-no-container-ktor-no-db.csv
make perform-tests-encoded
date +%s | xargs -I {} echo "ktor-no-db-encoded,"{} >> ../result-test-no-container-ktor-no-db.csv
date +%s | xargs -I {} echo "ktor-no-db-encoded-no-db,"{} >> ../result-test-no-container-ktor-no-db.csv
make perform-tests-encoded-no-db
date +%s | xargs -I {} echo "ktor-no-db-encoded-no-db,"{} >> ../result-test-no-container-ktor-no-db.csv
kill "$test"
echo -e "\033[92mProcess $test has ended!\033[0m"
