import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa") version "2.2.10"
}

group = "ru.vood.state"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(platform("org.springframework.statemachine:spring-statemachine-bom:4.0.1"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.retry:spring-retry")
    implementation("org.springframework:spring-aspects")
//    implementation("net.devh:grpc-server-spring-boot-starter:2.14.0.RELEASE")
//    implementation("net.devh:grpc-client-spring-boot-starter:2.14.0.RELEASE")
    implementation("org.springframework.statemachine:spring-statemachine-starter")
//    implementation("org.springframework.statemachine:spring-statemachine-core:3.2.0.RELEASE")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
