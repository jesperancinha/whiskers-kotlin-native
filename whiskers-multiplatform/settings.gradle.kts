rootProject.name = "whiskers-multiplatform"

include(":whiskers-ktor")
include(":whiskers-ktor-no-db")
include(":good-feel")
include(":plus")
include(":whiskers-runners:whiskers-runners-graalvm")
include(":whiskers-runners:whisksers-runners-native")
include(":whiskers-runners:whiksers-runners-knative")

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://repo.spring.io/release") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
}
