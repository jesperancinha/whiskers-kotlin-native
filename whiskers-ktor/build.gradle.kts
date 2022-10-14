plugins {
    application
    kotlin("multiplatform") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
}

group = "org.jesperancinha.native"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    maven { url = uri("https://repo1.maven.org/maven2/") }
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
                val libcurl by creating
            }
        }
        binaries {
            executable {
                val sysRoot = "/"
                val libGccVersion = "11.2.0"
                val libGcc = "/lib/gcc/x86_64-pc-linux-gnu/$libGccVersion"
                val overriddenProperties = "targetSysRoot.linux_x64=$sysRoot;libGcc.linux_x64=$libGcc"
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-Xoverride-konan-properties=${overriddenProperties}"
                )
                entryPoint = "main"
            }
        }
    }
    val ktorVersion = "2.1.2"
    sourceSets {
        val libcurl by creating

        val nativeMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                implementation("io.ktor:ktor-server-cio:$ktorVersion")
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
            }
        }
        val nativeTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

}

