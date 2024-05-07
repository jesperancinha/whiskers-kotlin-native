rootProject.name = "whiskers-kotlin-native"

include(":whiskers-graalvm")
include(":whiskers-cloudnative")

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://repo.spring.io/release") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
}
