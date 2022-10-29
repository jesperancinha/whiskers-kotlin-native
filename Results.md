# Whiskers Performance results

| Architecture | Sartup time (seconds) | Memory used(Mbytes) | Test with DB connection(s) | Test Mixed DB + Algorithm(s) | Test Algorithm (s) |
|---|---|---|---|---|---|
|cloudnative|6|  45.87MiB / 15.43GiB|25|37|13|
|graalvm|13|  63.33MiB / 15.43GiB|25|37|12|
|jvm|13|  182.4MiB / 15.43GiB|26|17|21|
|ktor|11|  2.285MiB / 15.43GiB|46|96|74|
|ktor-no-db|11|  2.352MiB / 15.43GiB|9|1|43|
