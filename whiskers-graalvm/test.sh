#!/usr/bin/env bash
./build/native/nativeCompile/whiskers-graalvm > logs & echo $! > pid.file
test=$(<pid.file)
date +%s | xargs -I {} echo "graalvm,"{} > ../result-no-container-graalvm.csv
test=$(<pid.file)
string=$(<logs)
while [[ "$string" != *"Started application"* ]]
  do
    string=$(<logs)
    sleep 0.01
  done
date +%s | xargs -I {} echo "graalvm,"{} >> ../result-no-container-graalvm.csv
pmap "$test" | tail -n 1 | xargs -I {} echo "graalvm,"{} >> ../result-no-container-graalvm.csv
echo -e "\033[92mStarting process $test\033[0m"
date +%s | xargs -I {} echo "graalvm,"{} > ../result-test-no-container-graalvm.csv
make perform-tests
date +%s | xargs -I {} echo "graalvm,"{} >> ../result-test-no-container-graalvm.csv
date +%s | xargs -I {} echo "graalvm-encoded,"{} >> ../result-test-no-container-graalvm.csv
make perform-tests-encoded
date +%s | xargs -I {} echo "graalvm-encoded,"{} >> ../result-test-no-container-graalvm.csv
date +%s | xargs -I {} echo "graalvm-encoded,"{} >> ../result-test-no-container-graalvm.csv
make perform-tests-encoded
date +%s | xargs -I {} echo "graalvm-encoded,"{} >> ../result-test-no-container-graalvm.csv
kill "$test"
echo -e "\033[92mProcess $test has ended!\033[0m"
