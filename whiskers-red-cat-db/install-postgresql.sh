#!/usr/bin/env sh
cd postgres-master || exit
./configure
make all
cd ..
