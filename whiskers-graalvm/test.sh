#!/usr/bin/env bash
./build/native/nativeCompile/whiskers-graalvm & echo $! > pid.file
test=$(<pid.file)
echo -e "\033[92m$test\033[0m"
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
