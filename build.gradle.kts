import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    idea
    war
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.noarg") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
    kotlin("plugin.serialization") version "1.3.72"
    jacoco
}

group = "syktykpyk"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("com.machinepublishers:jbrowserdriver:1.0.0")
    implementation("org.apache.commons:commons-pool2:2.8.0")
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("org.imgscalr:imgscalr-lib:4.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
    implementation("io.github.microutils:kotlin-logging:1.7.9")
    implementation("com.amazonaws:aws-java-sdk-s3:1.11.784")
    implementation("com.amazonaws:aws-java-sdk-sqs:1.11.797")
    implementation("com.amazonaws:aws-java-sdk-ecs:1.11.797")
    implementation("com.amazonaws:aws-java-sdk-apigatewaymanagementapi:1.11.477")
    implementation("com.google.code.gson:gson:2.8.5")
    runtime("org.springframework.boot:spring-boot-starter-tomcat")
    runtime("com.h2database:h2")
    runtime("mysql:mysql-connector-java")
    testImplementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.github.rest-driver:rest-client-driver:2.0.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

apply {
    plugin("kotlin-jpa")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.isEnabled = true
        html.destination = file("$buildDir/reports/coverage")
    }
}

val testCoverage by tasks.registering {
    group = "verification"
    description = "Runs the unit tests with coverage."

    dependsOn(":test", ":jacocoTestReport", ":jacocoTestCoverageVerification")
    val jacocoTestReport = tasks.findByName("jacocoTestReport")
    jacocoTestReport?.mustRunAfter(tasks.findByName("test"))
    tasks.findByName("jacocoTestCoverageVerification")?.mustRunAfter(jacocoTestReport)
}