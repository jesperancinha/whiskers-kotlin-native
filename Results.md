# Whiskers Performance results

## Service (Container) test results

| Architecture | Startup time (seconds) | Memory used(Mbytes) | Test with DB connection(s) | Test Mixed DB + Algorithm(s) | Test Algorithm (s) |
|--------------|------------------------|---------------------|----------------------------|------------------------------|--------------------|
| cloudnative  | 7                      | 100.3MiB / 15.43GiB | 26                         | 3                            | 34                 |
| graalvm      | 12                     | 93.88MiB / 15.43GiB | 23                         | 2                            | 31                 |
| jvm          | 14                     | 190.2MiB / 15.43GiB | 32                         | 2                            | 28                 |
| ktor         | 11                     | 6.121MiB / 15.43GiB | 28                         | 4                            | 31                 |
| ktor-no-db   | 11                     | 4.855MiB / 15.43GiB | 15                         | 1                            | 35                 |

## Service (Exec) test results

| Architecture | Startup time (seconds) | Memory used(Mbytes) | Test with DB connection(s) | Test Mixed DB + Algorithm(s) | Test Algorithm (s) |
|--------------|------------------------|---------------------|----------------------------|------------------------------|--------------------|
| graalvm      | 0                      | 107288K             | 30                         | 2                            | 3                  |
| jvm          | 3                      | 331308K             | 31                         | 2                            | 2                  |
| ktor         | 0                      | 5904K               | 23                         | 5                            | 7                  |
| ktor-no-db   | 0                      | 3424K               | 14                         | 1                            | 16                 |

## Runner test results

| Architecture | Runtime (1000 restart run) | Runtime (1 run 10_000_000 algorithm runs) |
|--------------|----------------------------|-------------------------------------------|
| graalvm      | 2                          | 22                                        |
| knative      | 2                          | 161                                       |
| native       | 2                          | 28                                        |
| jvm          | 54                         | 8                                         |
