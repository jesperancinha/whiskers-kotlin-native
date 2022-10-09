rootProject.name = "whiskers-graalvm"

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://repo.spring.io/release") }
        maven { url = uri( "https://plugins.gradle.org/m2/") }
    }
}
