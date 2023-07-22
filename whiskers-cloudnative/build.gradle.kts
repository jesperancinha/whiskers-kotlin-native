import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
	id("org.springframework.boot") version "3.1.1"
	id("io.spring.dependency-management") version "1.1.2"
//	id("org.springframework.experimental.aot") version "0.12.1"
	id("org.graalvm.buildtools.native") version "0.9.23"
	kotlin("jvm") version "1.9.0"
	kotlin("plugin.spring") version "1.9.0"
}

group = "org.jesperancinha.native"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenLocal()
	mavenCentral()
	maven { url = uri("https://repo.spring.io/release") }
}

extra["testcontainersVersion"] = "1.17.4"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.1.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:r2dbc")
}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}
//
//tasks.getByName<BootBuildImage>("bootBuildImage") {
//	builder = "paketobuildpacks/builder:tiny"
//	environment = mapOf(
//		"BP_NATIVE_IMAGE" to "true"
//	)
//	buildpacks = listOf("gcr.io/paketo-buildpacks/java-native-image:7.34.0")
//}

tasks.withType<Test> {
	useJUnitPlatform()
}
