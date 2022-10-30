# Whiskers Performance results

| Architecture | Startup time (seconds) | Memory used(Mbytes) | Test with DB connection(s) | Test Mixed DB + Algorithm(s) | Test Algorithm (s) |
|---|---|---|---|---|---|
|cloudnative|4|  44.81MiB / 15.43GiB|14|0|19|
|graalvm|11|  45.17MiB / 15.43GiB|13|1|18|
|jvm|13|  161.8MiB / 15.43GiB|20|1|16|
|ktor|11|  2.742MiB / 15.43GiB|14|1|22|
|ktor-no-db|11|  1.875MiB / 15.43GiB|9|0|23|
|graalvm|0|611264K|19|1|0|
|ktor|0|730108K|13|1|1|
|ktor-no-db|0|728588K|8|0|16|
