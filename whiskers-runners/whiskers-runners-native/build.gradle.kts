plugins {
    application
    kotlin("multiplatform") version "1.9.22"
}

group = "org.jesperancinha"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        compilations.getByName("main") {
            cinterops {
                val redcat by creating {
                    defFile(project.file("src/nativeInterop/cinterop/redcat.def"))
                    packageName("org.jesperancinha.knative")
                    headers("src/nativeInterop/cinterop/code/redcat.h")
                    includeDirs.allHeaders("code","src/nativeInterop/cinterop/code")
                }
            }
        }
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        val nativeMain by getting
        val nativeTest by getting
    }

}
