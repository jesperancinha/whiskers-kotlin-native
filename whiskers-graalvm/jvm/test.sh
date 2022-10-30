#!/usr/bin/env bash
java -jar whiskers-graalvm.jar > logs & echo $! > pid.file
test=$(<pid.file)
date +%s | xargs -I {} echo "jvm,"{} > ../../result-no-container-jvm.csv
test=$(<pid.file)
string=$(<logs)
while [[ "$string" != *"Netty started"* ]]
  do
    string=$(<logs)
    sleep 0.01
  done
date +%s | xargs -I {} echo "jvm,"{} >> ../../result-no-container-jvm.csv
ps -p "$test" -o rss | tail -n 1 | xargs -I {} echo "jvm,"{} >> ../../result-no-container-jvm.csv
echo -e "\033[92mStarting process $test\033[0m"
date +%s | xargs -I {} echo "jvm,"{} > ../../result-test-no-container-jvm.csv
make perform-tests
date +%s | xargs -I {} echo "jvm,"{} >> ../../result-test-no-container-jvm.csv
date +%s | xargs -I {} echo "jvm-encoded,"{} >> ../../result-test-no-container-jvm.csv
make perform-tests-encoded
date +%s | xargs -I {} echo "jvm-encoded,"{} >> ../../result-test-no-container-jvm.csv
date +%s | xargs -I {} echo "jvm-encoded,"{} >> ../../result-test-no-container-jvm.csv
make perform-tests-encoded
date +%s | xargs -I {} echo "jvm-encoded,"{} >> ../../result-test-no-container-jvm.csv
kill "$test"
echo -e "\033[92mProcess $test has ended!\033[0m"
