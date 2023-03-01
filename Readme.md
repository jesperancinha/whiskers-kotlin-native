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

As a datasource, I have continued my novel, [Good-Story](https://github.com/jesperancinha/good-story/blob/main/docs/good.story/GoodStory.md), which links several projects focused on backend developments.

If you are interested, please check [Chapter II - The cat that helped Lucy](./docs/good.story/good.story.chapter.2.md) for the complete data source and context.

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://itnext.io/kotlin-native-and-graalvm-the-story-so-far-e10d7e9cfc91) [Kotlin Native and GraalVM — The Story So Far](https://itnext.io/kotlin-native-and-graalvm-the-story-so-far-e10d7e9cfc91)

<div align="center">
      <a title="Kotlin Native and GraalVM — The Story So Far" href="https://itnext.io/kotlin-native-and-graalvm-the-story-so-far-e10d7e9cfc91">
     <img 
          src="./docs/article.whiskers.banner.png" 
          style="width:100%;">
      </a>
</div>

#### Stable releases

-   [1.0.0](https://github.com/jesperancinha/whiskers-kotlin-native/tree/1.0.0) - [6cbd9b0701259dc0145b6f6714295cbed66cbbb1](https://github.com/jesperancinha/whiskers-kotlin-native/tree/1.0.0)


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

-   [good-feel](./good-feel) - A simple Kotlin Native project without the use of C bindings
-   [plus](./plus) - A Kotlin Native project exploring other functions provided by Kotlin Native
-   [whiskers-cloudnative](./whiskers-cloudnative) - Spring Native way of creating a docker container as a full implementation of the Onion Model used in Microservices
-   [whiskers-graalvm](./whiskers-graalvm) - Spring Native way of creating a GraalVM self-contained executable command as a full implementation of the Onion Model used in Microservices
-   [whiskers-ktor-no-db](./whiskers-ktor-no-db) - Ktor Service implemented with hard-coded configuration
-   [whiskers-ktor](./whiskers-ktor) - Ktor Service containing a full implementation of the Onion Model used in Microservices

--- 

## Project layout

1.  [Good Feel](./good-feel) -  A project to make you feel good. If you run into a situation where things get tough, then run this command to make you feel good. This project is the first test-drive about native use in this whole project
2.  [Plus](./plus) - Plus just means a step forward. This module is fully dedicated to the use of external libraries.

---

## References

### Videos

<div align="center">
      <a title="Ktor From the Ground Up" href="https://www.youtube.com/watch?v=WlvK6zYo8Sw">
     <img alt="Ktor From the Ground Up"
          src="https://img.youtube.com/vi/WlvK6zYo8Sw/0.jpg" 
          style="width:30%;">
      </a>
      <a title="Applied Event Streaming With Apache Kafka, Kotlin, and Ktor" href="https://www.youtube.com/watch?v=6qxkawU0qKA">
     <img alt="Applied Event Streaming With Apache Kafka, Kotlin, and Ktor"
          src="https://img.youtube.com/vi/6qxkawU0qKA/0.jpg" 
          style="width:30%;">
      </a>
</div>

### Online

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
-   [Get started with Kotlin/Native in IntelliJ IDEA](https://kotlinlang.org/ docs/native-get-started.html#0)

## Thanks

-   Special thanks to [hfhbd @ GitHub](https://github.com/hfhbd/postgres-native-sqldelight), for giving me the necessary pointers to go PostgreSQL native

## About me

<div align="center">

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-100/JEOrgLogo-27.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![](https://img.shields.io/badge/youtube-%230077B5.svg?style=for-the-badge&logo=youtube&color=FF0000)](https://www.youtube.com/@joaoesperancinha)
[![](https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://medium.com/@jofisaes)
[![](https://img.shields.io/badge/Buy%20Me%20A%20Coffee-%230077B5.svg?style=for-the-badge&logo=buymeacoffee&color=yellow)](https://www.buymeacoffee.com/jesperancinha)
[![](https://img.shields.io/badge/Twitter-%230077B5.svg?style=for-the-badge&logo=twitter&color=white)](https://twitter.com/joaofse)
[![](https://img.shields.io/badge/Mastodon-%230077B5.svg?style=for-the-badge&logo=mastodon&color=afd7f7)](https://masto.ai/@jesperancinha)
[![](https://img.shields.io/badge/Sessionize-%230077B5.svg?style=for-the-badge&logo=sessionize&color=cffff6)](https://sessionize.com/joao-esperancinha)
[![](https://img.shields.io/badge/Instagram-%230077B5.svg?style=for-the-badge&logo=instagram&color=purple)](https://www.instagram.com/joaofisaes)
[![](https://img.shields.io/badge/Tumblr-%230077B5.svg?style=for-the-badge&logo=tumblr&color=192841)](https://jofisaes.tumblr.com)
[![](https://img.shields.io/badge/Spotify-1ED760?style=for-the-badge&logo=spotify&logoColor=white)](https://open.spotify.com/user/jlnozkcomrxgsaip7yvffpqqm)
[![](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/joaoesperancinha/)
[![](https://img.shields.io/badge/Xing-%230077B5.svg?style=for-the-badge&logo=xing&color=064e40)](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![](https://img.shields.io/badge/YCombinator-%230077B5.svg?style=for-the-badge&logo=ycombinator&color=d0d9cd)](https://news.ycombinator.com/user?id=jesperancinha)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
[![](https://img.shields.io/badge/bitbucket-%230077B5.svg?style=for-the-badge&logo=bitbucket&color=blue)](https://bitbucket.org/jesperancinha)
[![](https://img.shields.io/badge/gitlab-%230077B5.svg?style=for-the-badge&logo=gitlab&color=orange)](https://gitlab.com/jesperancinha)
[![](https://img.shields.io/badge/Stack%20Overflow-%230077B5.svg?style=for-the-badge&logo=stackoverflow&color=5A5A5A)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![](https://img.shields.io/badge/Credly-%230077B5.svg?style=for-the-badge&logo=credly&color=064e40)](https://www.credly.com/users/joao-esperancinha)
[![](https://img.shields.io/badge/Coursera-%230077B5.svg?style=for-the-badge&logo=coursera&color=000080)](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![](https://img.shields.io/badge/Docker-%230077B5.svg?style=for-the-badge&logo=docker&color=cyan)](https://hub.docker.com/u/jesperancinha)
[![](https://img.shields.io/badge/Reddit-%230077B5.svg?style=for-the-badge&logo=reddit&color=black)](https://www.reddit.com/user/jesperancinha/)
[![](https://img.shields.io/badge/Hackernoon-%230077B5.svg?style=for-the-badge&logo=hackernoon&color=0a5d00)](https://hackernoon.com/@jesperancinha)
[![](https://img.shields.io/badge/Code%20Project-%230077B5.svg?style=for-the-badge&logo=codeproject&color=063b00)](https://www.codeproject.com/Members/jesperancinha)
[![](https://img.shields.io/badge/Free%20Code%20Camp-%230077B5.svg?style=for-the-badge&logo=freecodecamp&color=0a5d00)](https://www.freecodecamp.org/jofisaes)
[![](https://img.shields.io/badge/Hackerrank-%230077B5.svg?style=for-the-badge&logo=hackerrank&color=1e2f97)](https://www.hackerrank.com/jofisaes)
[![](https://img.shields.io/badge/LeetCode-%230077B5.svg?style=for-the-badge&logo=leetcode&color=002647)](https://leetcode.com/jofisaes)
[![](https://img.shields.io/badge/Codewars-%230077B5.svg?style=for-the-badge&logo=codewars&color=722F37)](https://www.codewars.com/users/jesperancinha)
[![](https://img.shields.io/badge/CodePen-%230077B5.svg?style=for-the-badge&logo=codepen&color=black)](https://codepen.io/jesperancinha)
[![](https://img.shields.io/badge/HackerEarth-%230077B5.svg?style=for-the-badge&logo=hackerearth&color=00035b)](https://www.hackerearth.com/@jofisaes)
[![](https://img.shields.io/badge/Khan%20Academy-%230077B5.svg?style=for-the-badge&logo=khanacademy&color=00035b)](https://www.khanacademy.org/profile/jofisaes)
[![](https://img.shields.io/badge/Pinterest-%230077B5.svg?style=for-the-badge&logo=pinterest&color=FF0000)](https://nl.pinterest.com/jesperancinha)
[![](https://img.shields.io/badge/Quora-%230077B5.svg?style=for-the-badge&logo=quora&color=FF0000)](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
[![](https://img.shields.io/badge/Google%20Play-%230077B5.svg?style=for-the-badge&logo=googleplay&color=purple)](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
| [Sonatype Search Repos](https://search.maven.org/search?q=org.jesperancinha)
| [Dev.TO](https://dev.to/jofisaes)
| [Codebyte](https://coderbyte.com/profile/jesperancinha)
| [InfoQ](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![](https://img.shields.io/badge/OCP%20Java%2011-%230077B5.svg?style=for-the-badge&logo=oracle&color=064e40)](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
[![](https://img.shields.io/badge/OCP%20JEE%207-%230077B5.svg?style=for-the-badge&logo=oracle&color=064e40)](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
[![](https://img.shields.io/badge/VMWare%20Spring%20Professional%202021-%230077B5.svg?style=for-the-badge&logo=spring&color=064e40)](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
[![](https://img.shields.io/badge/IBM%20Cybersecurity%20Analyst%20Professional-%230077B5.svg?style=for-the-badge&logo=ibm&color=064e40)](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
[![](https://img.shields.io/badge/Deep%20Learning-%230077B5.svg?style=for-the-badge&logo=ibm&color=064e40)](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
[![](https://img.shields.io/badge/Certified%20Neo4j%20Professional-%230077B5.svg?style=for-the-badge&logo=neo4j&color=064e40)](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
[![](https://img.shields.io/badge/Certified%20Advanced%20JavaScript%20Developer-%230077B5.svg?style=for-the-badge&logo=javascript&color=064e40)](https://cancanit.com/certified/1462/)
[![](https://img.shields.io/badge/Kong%20Champions-%230077B5.svg?style=for-the-badge&logo=kong&color=064e40)](https://konghq.com/kong-champions)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=064e40&style=for-the-badge "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=064e40&style=for-the-badge "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=orange&style=for-the-badge "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)

</div>
