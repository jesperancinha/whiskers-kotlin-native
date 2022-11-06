# whiskas-kotlin-native-command-line


[![Build, Test, Coverage and Report](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers.yml/badge.svg)](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers.yml)
[![e2e-whiskers](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers-e2e.yml/badge.svg)](https://github.com/jesperancinha/whiskers-kotlin-native/actions/workflows/whiskers-e2e.yml)

🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧 !!! [Under construction...](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/UnderConstruction.md) !!! 🚧🚧🚧🚧🚧🚧🚧🚧🚧🚧

## Introduction

This project is an in-depth investigation about all things Native and JVM with focus on `Kotlin Native`.

As a datasource, I have continued my novel, [Good-Story](https://github.com/jesperancinha/good-story/blob/main/docs/good.story/GoodStory.md), which links several projects focused on backend developments.

If you are interested, please check [Chapter II - The cat that helped Lucy](./docs/good.story/good.story.chapter.2.md) for the complete data source and context.

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

## Project Structure

-   [good-feel](./good-feel) - A simple Kotlin Native project without the use of C bindings
-   [plus](./plus) - A Kotlin Native project exploring other functions provided by Kotlin Native
-   [whiskers-cloudnative](./whiskers-cloudnative) - Spring Native way of creating a docker container as a full implementation of the Onion Model used in Microservices
-   [whiskers-graalvm](./whiskers-graalvm) - Spring Native way of creating a GraalVM self-contained executable command as a full implementation of the Onion Model used in Microservices
-   [whiskers-ktor-no-db](./whiskers-ktor-no-db) - Ktor Service implemented with hard-coded configuration
-   [whiskers-ktor](./whiskers-ktor) - Ktor Service containing a full implementation of the Onion Model used in Microservices

## Project layout

1.  [Good Feel](./good-feel) -  A project to make you feel good. If you run into a situation where things get tough, then run this command to make you feel good. This project is the first test-drive about native use in this whole project
2.  [Plus](./plus) - Plus just means a step forward. This module is fully dedicated to the use of external libraries.

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
-   [Get started with Kotlin/Native in IntelliJ IDEA](https://kotlinlang.org/docs/native-get-started.html#0)

## Thanks

-   Special thanks to [hfhbd @ GitHub](https://github.com/hfhbd/postgres-native-sqldelight), for giving me the necessary pointers to go PostgreSQL native

## About me 👨🏽‍💻🚀🏳️‍🌈

[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/JEOrgLogo-20.png "João Esperancinha Homepage")](http://joaofilipesabinoesperancinha.nl)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/medium-20.png "Medium")](https://medium.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bmc-20.png "Buy me a Coffe")](https://www.buymeacoffee.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/credly-20.png "Credly")](https://www.credly.com/users/joao-esperancinha)
[![Generic badge](https://img.shields.io/static/v1.svg?label=WWW&message=joaofilipesabinoesperancinha.nl&color=6495ED "João Esperancinha Homepage")](https://joaofilipesabinoesperancinha.nl/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/google-apps-20.png "Google Apps")](https://play.google.com/store/apps/developer?id=Joao+Filipe+Sabino+Esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/sonatype-20.png "Sonatype Search Repos")](https://search.maven.org/search?q=org.jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/docker-20.png "Docker Images")](https://hub.docker.com/u/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/stack-overflow-20.png)](https://stackoverflow.com/users/3702839/joao-esperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/reddit-20.png "Reddit")](https://www.reddit.com/user/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/devto-20.png "Dev To")](https://dev.to/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackernoon-20.jpeg "Hackernoon")](https://hackernoon.com/@jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codeproject-20.png "Code Project")](https://www.codeproject.com/Members/jesperancinha)
[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=social "GitHub")](https://github.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/bitbucket-20.png "BitBucket")](https://bitbucket.org/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/gitlab-20.png "GitLab")](https://gitlab.com/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/coursera-20.png "Coursera")](https://www.coursera.org/user/da3ff90299fa9297e283ee8e65364ffb)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/free-code-camp-20.jpg "FreeCodeCamp")](https://www.freecodecamp.org/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hackerrank-20.png "HackerRank")](https://www.hackerrank.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/leet-code-20.png "LeetCode")](https://leetcode.com/jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codebyte-20.png "Codebyte")](https://coderbyte.com/profile/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codewars-20.png "CodeWars")](https://www.codewars.com/users/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/codepen-20.png "Code Pen")](https://codepen.io/jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-earth-20.png "Hacker Earth")](https://www.hackerearth.com/@jofisaes)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/khan-academy-20.png "Khan Academy")](https://www.khanacademy.org/profile/jofisaes)
[![Twitter Follow](https://img.shields.io/twitter/follow/joaofse?label=João%20Esperancinha&style=social "Twitter")](https://twitter.com/joaofse)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/hacker-news-20.png "Hacker News")](https://news.ycombinator.com/user?id=jesperancinha)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/infoq-20.png "InfoQ")](https://www.infoq.com/profile/Joao-Esperancinha.2/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/linkedin-20.png "LinkedIn")](https://www.linkedin.com/in/joaoesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/xing-20.png "Xing")](https://www.xing.com/profile/Joao_Esperancinha/cv)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/tumblr-20.png "Tumblr")](https://jofisaes.tumblr.com/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/pinterest-20.png "Pinterest")](https://nl.pinterest.com/jesperancinha/)
[![alt text](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/icons-20/quora-20.png "Quora")](https://nl.quora.com/profile/Jo%C3%A3o-Esperancinha)
[![VMware Spring Professional 2021](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/vmware-spring-professional-2021-20.png "VMware Spring Professional 2021")](https://www.credly.com/badges/762fa7a4-9cf4-417d-bd29-7e072d74cdb7)
[![Oracle Certified Professional, JEE 7 Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-ee-7-application-developer-20.png "Oracle Certified Professional, JEE7 Developer")](https://www.credly.com/badges/27a14e06-f591-4105-91ca-8c3215ef39a2)
[![Oracle Certified Professional, Java SE 11 Programmer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/oracle-certified-professional-java-se-11-developer-20.png "Oracle Certified Professional, Java SE 11 Programmer")](https://www.credly.com/badges/87609d8e-27c5-45c9-9e42-60a5e9283280)
[![IBM Cybersecurity Analyst Professional](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/ibm-cybersecurity-analyst-professional-certificate-20.png "IBM Cybersecurity Analyst Professional")](https://www.credly.com/badges/ad1f4abe-3dfa-4a8c-b3c7-bae4669ad8ce)
[![Certified Advanced JavaScript Developer](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/cancanit-badge-1462-20.png "Certified Advanced JavaScript Developer")](https://cancanit.com/certified/1462/)
[![Certified Neo4j Professional](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/professional_neo4j_developer-20.png "Certified Neo4j Professional")](https://graphacademy.neo4j.com/certificates/c279afd7c3988bd727f8b3acb44b87f7504f940aac952495ff827dbfcac024fb.pdf)
[![Deep Learning](https://raw.githubusercontent.com/jesperancinha/project-signer/master/project-signer-templates/badges/deep-learning-20.png "Deep Learning")](https://www.credly.com/badges/8d27e38c-869d-4815-8df3-13762c642d64)
[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=JEsperancinhaOrg&color=yellow "jesperancinha.org dependencies")](https://github.com/JEsperancinhaOrg)
[![Generic badge](https://img.shields.io/static/v1.svg?label=All%20Badges&message=Badges&color=red "All badges")](https://joaofilipesabinoesperancinha.nl/badges)
[![Generic badge](https://img.shields.io/static/v1.svg?label=Status&message=Project%20Status&color=red "Project statuses")](https://github.com/jesperancinha/project-signer/blob/master/project-signer-quality/Build.md)
