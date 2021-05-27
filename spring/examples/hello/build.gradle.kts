plugins {
    kotlin("jvm")
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks

compileKotlin.kotlinOptions.jvmTarget = "1.8"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.4.5")
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.4.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.4.5")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation(kotlin("test"))
}

kotlin {
    sourceSets["main"].apply {
        kotlin.srcDir("src/main/kotlin")
    }
}

val test by tasks.existing(Test::class) {

    testLogging.showStackTraces = true
}

tasks.test {
    useJUnitPlatform()
}