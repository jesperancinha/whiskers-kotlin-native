#!/bin/bash
GITHUB_RUN_ID=${GITHUB_RUN_ID:-123}

function checkServiceByNameAndMessage() {
    name=$1
    message=$2
    docker ps -a -q --filter="name=$name" | xargs -I {} docker logs {} > "logs"
    string=$(cat logs)
    counter=0
    echo "Project $GITHUB_RUN_ID"
    echo -n "Starting service $name "
    while [[ "$string" != *"$message"* ]]
    do
      echo -e -n "\e[93m-\e[39m"
      docker ps -a -q --filter="name=$name" | xargs -I {} docker logs {} > "logs"
      string=$(cat logs)
      sleep 1
      counter=$((counter+1))
      if [ $counter -eq 200 ]; then
          echo -e "\e[91mFailed after $counter tries! Cypress tests may fail!!\e[39m"
          echo "$string"
          exit 1
      fi
    done
    counter=$((counter+1))
    echo -e "\e[92m Succeeded starting $name Service after $counter tries!\e[39m"
    docker ps -a -q --filter="name=$name" | xargs -I {} docker stats {} --no-stream
    docker ps -a -q --filter="name=$name" | xargs -I {} docker stats {} --no-stream > result-mem-"$name".txt
}

checkServiceByNameAndMessage "$1" 'started'
