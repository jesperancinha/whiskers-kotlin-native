# whiskas-kotlin-native-command-line


[![Build, Test, Coverage and Report](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers.yml/badge.svg)](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers.yml)
[![e2e-whiskers](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers-e2e.yml/badge.svg)](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers-e2e.yml)

🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧 !!! [Under construction...](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/UnderConstruction.md) !!! 🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧

## Introduction

This project is an in-depth investigation about all things Native and JVM with focus on `Kotlin Native`.

As a datasource, I have continued my novel, [Good-Story](https://github.com/jesperancinha/good-story/blob/main/docs/good.story/GoodStory.md), which links several projects focused on backend developments.

If you are interested, please check [Chapter II - The cat that helped Lucy](./docs/good.story/good.story.chapter.2.md) for the complete data source and context.

## How to run

#### 1. Install all binaries (Optional)

1. Linux

```bash
make install-kotlin-native-linux
```

2. MAC-OS
3. WINDOWS

#### 2. Compile all c libraries

```bash
. ./init.sh
```

#### 3. Build all

```shell
make b
```


## Project Structure

- [good-feel](./good-feel) - A simple Kotlin Native project without the use of C bindings
- [plus](./plus) - A Kotlin Native project exploring other functions provided by Kotlin Native
- [whiskers-cloudnative](./whiskers-cloudnative) - Spring Native way of creating a docker container as a full implementation of the Onion Model used in Microservices
- [whiskers-graalvm](./whiskers-graalvm) - Spring Native way of creating a GraalVM self-contained executable command as a full implementation of the Onion Model used in Microservices
- [whiskers-ktor-harcoded](./whiskers-ktor-harcoded) - Ktor Service implemented with hard-coded configuration
- [whiskers-ktor](./whiskers-ktor) - Ktor Service containing a full implementation of the Onion Model used in Microservices

## Project layout

1. [Good Feel](./good-feel) -  A project to make you feel good. If you run into a situation where things get tough, then run this command to make you feel good. This project is the first test-drive about native use in this whole project
2. [Plus](./plus) - Plus just means a step forward. This module is fully dedicated to the use of external libraries.

## References

- [Koin — Kotlin Native Dependency Injection Library](https://medium.com/android-dev-hacks/koin-kotlin-native-dependency-injection-library-f1daddc1ef99)
- [Realm Kotlin Multiplatform SDK](https://blog.jetbrains.com/kotlin/2021/04/realm-kotlin-multiplatform-sdk/)
- [Ktorm @ GitHub](https://github.com/kotlin-orm/ktorm)
- [Ktorm](https://www.ktorm.org/)
- [Getting started with a Ktor Server](https://ktor.io/docs/intellij-idea.html)
- [Getting started with a Ktor Client](https://ktor.io/docs/getting-started-ktor-client.html)
- [Kotlin Native Support #571](https://github.com/ktorio/ktor/issues/571)
- [can we use Spring with Kotlin/Native? @ Reddit](https://www.reddit.com/r/Kotlin/comments/fkn5ko/can_we_use_spring_with_kotlinnative/)
- [GS Rest Service @ GitHub](https://github.com/spring-guides/gs-rest-service)
- [Spring Native Overview](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#overview)
- [Build Java Modules into a Native Executable](https://www.graalvm.org/22.2/reference-manual/native-image/guides/build-java-modules-into-native-executable/)
- [Picocli GraalVM info](https://picocli.info/#_graalvm_native_image)
- [Support external JAR libraries? #1212](https://github.com/JetBrains/kotlin-native/issues/1212)
- [How to Convert Runnable .jar File to Native Code?](https://stackoverflow.com/questions/52738484/how-to-convert-runnable-jar-file-to-native-code)
- [Incubator @ KongHQ](https://incubator.konghq.com/)
- [Get started with Kotlin/Native in IntelliJ IDEA](https://kotlinlang.org/docs/native-get-started.html#0)

## Thanks

- Special thanks to [hfhbd @ GitHub](https://github.com/hfhbd/postgres-native-sqldelight), for giving me the necessary pointers to go PostgreSQL native
