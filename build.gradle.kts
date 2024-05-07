plugins {
    id("org.graalvm.buildtools.native") version "0.10.1" apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}
