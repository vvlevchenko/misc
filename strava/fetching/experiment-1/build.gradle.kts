import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    java
    id("io.swagger.core.v3.swagger-gradle-plugin") version "2.1.9"
}

var ktor_version = "1.5.4"


dependencies {
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("com.google.code.gson:gson:2.8.7")
    implementation("com.squareup.okio:okio:2.10.0")
    implementation("com.squareup.okhttp:okhttp:2.7.5")
    implementation("com.squareup.okhttp:logging-interceptor:2.7.5")
    implementation("org.threeten:threetenbp:1.5.1")
    implementation("io.gsonfire:gson-fire:1.8.5")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("io.swagger:swagger-annotations:1.6.2")
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}
sourceSets {
    main {
        java {
            srcDir("src/gen/src/main/java")
        }
    }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}