# whiskers-kotlin-native-command-line

---

[![Twitter URL](https://img.shields.io/twitter/url?logoColor=blue&style=social&url=https%3A%2F%2Fimg.shields.io%2Ftwitter%2Furl%3Fstyle%3Dsocial)](https://twitter.com/intent/tweet?text=%20Checkout%20this%20%40github%20repo%20by%20%40joaofse%20%F0%9F%91%A8%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB%3A%20https%3A//github.com/jesperancinha/whiskers-kotlin-native-command-line)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Whiskers%20Kotlin%20Native%20🐈&color=informational)](https://github.com/jesperancinha/whiskers-kotlin-native-command-line)

[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

[![Build, Test, Coverage and Report](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers.yml/badge.svg)](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers.yml)
[![e2e-whiskers](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers-e2e.yml/badge.svg)](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers-e2e.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/2902fbf1084a47e28746af3102c98060)](https://www.codacy.com/gh/jesperancinha/whiskers-kotlin-native/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/whiskers-kotlin-native&amp;utm_campaign=Badge_Grade)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/good-story.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/good-story.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/good-story.svg)](#)

---

## Introduction

This project is an in-depth investigation about all things Native and JVM with focus on `Kotlin Native`.

<details>
<summary><b>Stable releases</b></summary>

---
As a datasource, I have continued my novel, [Good-Story](https://github.com/jesperancinha/good-story/blob/main/docs/good.story/GoodStory.md), which links several projects focused on backend developments.

If you are interested, please check [Chapter II - The cat that helped Lucy](./docs/good.story/good.story.chapter.2.md) for the complete data source and context.

[![](https://img.shields.io/badge/Kotlin%20Native%20and%20GraalVM%20-%20The%20Story%20So%20Far-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://itnext.io/kotlin-native-and-graalvm-the-story-so-far-e10d7e9cfc91)

#### Stable releases

-   [1.0.0](https://github.com/jesperancinha/whiskers-kotlin-native/tree/1.0.0) - [6cbd9b0701259dc0145b6f6714295cbed66cbbb1](https://github.com/jesperancinha/whiskers-kotlin-native/tree/6cbd9b0701259dc0145b6f6714295cbed66cbbb1)
-   [1.0.1](https://github.com/jesperancinha/whiskers-kotlin-native/tree/1.0.1) - [2a79e23a821942ed6a0d2360117c5ab9947b6ecc](https://github.com/jesperancinha/whiskers-kotlin-native/tree/2a79e23a821942ed6a0d2360117c5ab9947b6ecc) - Last version without TOML
-   [2.0.0](https://github.com/jesperancinha/whiskers-kotlin-native/tree/2.0.0) - [1c6ffe4100fe0063813960a54e8476083d96f453](https://github.com/jesperancinha/whiskers-kotlin-native/tree/1c6ffe4100fe0063813960a54e8476083d96f453) - First TOML version


---
</details>


---

## How to run

#### 1.  Install all binaries (Optional)

1.  Linux

```bash
make install-kotlin-native-linux
```

2.  MAC-OS
3.  WINDOWS

#### 2.  Compile all c libraries

```bash
. ./init.sh
```

#### 3.  Build all

```shell
make b
```

#### 4.  Performe measure tests

```shell
make measure-all-sts
```

--- 

## Project Structure

-   [good-feel](good-feel) - A simple Kotlin Native project without the use of C bindings
-   [plus](plus) - A Kotlin Native project exploring other functions provided by Kotlin Native
-   [whiskers-cloudnative](./whiskers-cloudnative) - Spring Native way of creating a docker container as a full implementation of the Onion Model used in Microservices
-   [whiskers-graalvm](./whiskers-graalvm) - Spring Native way of creating a GraalVM self-contained executable command as a full implementation of the Onion Model used in Microservices
-   [whiskers-ktor-no-db](whiskers-ktor-no-db) - Ktor Service implemented with hard-coded configuration
-   [whiskers-ktor](whiskers-ktor) - Ktor Service containing a full implementation of the Onion Model used in Microservices

--- 

## Project layout

1.  [Good Feel](good-feel) -  A project to make you feel good. If you run into a situation where things get tough, then run this command to make you feel good. This project is the first test-drive about native use in this whole project
2.  [Plus](plus) - Plus just means a step forward. This module is fully dedicated to the use of external libraries.

---

## References

<details>
<summary><b>Videos</b></summary>

---
-   [Ktor From the Ground Up](https://www.youtube.com/watch?v=WlvK6zYo8Sw)
-   [Applied Event Streaming With Apache Kafka, Kotlin, and Ktor](https://www.youtube.com/watch?v=6qxkawU0qKA")
---
</details>

<details>
<summary><b>Websites</b></summary>

---
-   [Why GraalVM](https://www.graalvm.org/why-graalvm/)
-   [Simplifying the cloud native journey with GraalVM and Helidon by Alina Yurenko](https://blogs.oracle.com/java/post/simplifying-the-cloud-native-journey-with-graalvm-and-helidon)
-   [AOT compilation](https://en.wikipedia.org/wiki/Ahead-of-time_compilation)
-   [Native AOT Deployment](https://learn.microsoft.com/en-us/dotnet/core/deploying/native-aot/)
-   [Angular AOT compilation](https://angular.io/guide/aot-compiler)
-   [JIT compilation](https://en.wikipedia.org/wiki/Just-in-time_compilation)
-   [Kotlin(-Native) as a Go alternative in 2021](https://discuss.kotlinlang.org/t/kotlin-native-as-a-go-alternative-in-2021/23665)
-   [ps output – Difference between VSZ vs RSS memory usage](https://linuxconfig.org/ps-output-difference-between-vsz-vs-rss-memory-usage)
-   [Ktor demo](https://github.com/antonarhipov/ktor-demo)
-   [Ktor mini demo](https://github.com/antonarhipov/ktor-mini-demo)
-   [Ktor simple demo](https://github.com/antonarhipov/ktor-simple-demo)
-   [Ktor Examples](https://github.com/ktorio/ktor-samples)
-   [Koin — Kotlin Native Dependency Injection Library](https://medium.com/android-dev-hacks/koin-kotlin-native-dependency-injection-library-f1daddc1ef99)
-   [Realm Kotlin Multiplatform SDK](https://blog.jetbrains.com/kotlin/2021/04/realm-kotlin-multiplatform-sdk/)
-   [Ktorm @ GitHub](https://github.com/kotlin-orm/ktorm)
-   [Ktorm](https://www.ktorm.org/)
-   [Getting started with a Ktor Server](https://ktor.io/docs/intellij-idea.html)
-   [Getting started with a Ktor Client](https://ktor.io/docs/getting-started-ktor-client.html)
-   [Kotlin Native Support #571](https://github.com/ktorio/ktor/issues/571)
-   [can we use Spring with Kotlin/Native? @ Reddit](https://www.reddit.com/r/Kotlin/comments/fkn5ko/can_we_use_spring_with_kotlinnative/)
-   [GS Rest Service @ GitHub](https://github.com/spring-guides/gs-rest-service)
-   [Spring Native Overview](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#overview)
-   [Build Java Modules into a Native Executable](https://www.graalvm.org/22.2/reference-manual/native-image/guides/build-java-modules-into-native-executable/)
-   [Picocli GraalVM info](https://picocli.info/#_graalvm_native_image)
-   [Support external JAR libraries? #1212](https://github.com/JetBrains/kotlin-native/issues/1212)
-   [How to Convert Runnable .jar File to Native Code?](https://stackoverflow.com/questions/52738484/how-to-convert-runnable-jar-file-to-native-code)
-   [Incubator @ KongHQ](https://incubator.konghq.com/)
-   [Get started with Kotlin/Native in IntelliJ IDEA](https://kotlinlang.org/docs/native-get-started.html#0)

---
</details>

## Thanks

-   Special thanks to [hfhbd @ GitHub](https://github.com/hfhbd/postgres-native-sqldelight), for giving me the necessary pointers to go PostgreSQL native

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
