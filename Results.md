# Whiskers Performance results

| Architecture | Startup time (seconds) | Memory used(Mbytes) | Test with DB connection(s) | Test Mixed DB + Algorithm(s) | Test Algorithm (s) |
|---|---|---|---|---|---|
|cloudnative|6|  99.43MiB / 15.43GiB|38|2|27|
|graalvm|13|  92.16MiB / 15.43GiB|24|2|29|
|jvm|14|  183.3MiB / 15.43GiB|31|2|27|
|ktor|11|  6.254MiB / 15.43GiB|25|5|33|
|ktor-no-db|11|  4.871MiB / 15.43GiB|15|2|33|
|graalvm|0|graalvm,105484|24|2|3|
|jvm|2|jvm,281288|32|2|3|
|ktor|0|ktor,4148|25|5|7|
|ktor-no-db|0|ktor-no-db,3488|15|2|17|
