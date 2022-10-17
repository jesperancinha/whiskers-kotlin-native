#!/usr/bin/env bash
cd postgres-master || exit
./configure
make all
cd ..
