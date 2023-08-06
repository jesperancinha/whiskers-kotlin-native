buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}


plugins {
    id("jacoco")
    id( "org.jesperancinha.plugins.omni") version "0.3.1"
}