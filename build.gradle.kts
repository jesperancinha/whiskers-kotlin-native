plugins {
    alias(libs.plugins.graalvm.buildtools.native) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}
