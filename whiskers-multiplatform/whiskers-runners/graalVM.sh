#!/usr/bin/env sh

echo -e '\033[92mChanging to the first available SDK MAN GraalVM version.\033[0m'
echo -e '\033[91mRemember to run this command like this: . ./graalVM.sh\033[0m'
sdk install java 22.3.r17-grl
sdk use java $(sdk list java | grep installed | grep "\-grl" |  cut -d'|' -f6- | cut -d' ' -f2- )
