plugins {
    kotlin("jvm")
    id("org.jetbrains.intellij") version "0.7.2"
}

repositories {
    mavenCentral()
}

intellij {
    version = "IU-2021.1"
}

kotlin {
    sourceSets["main"].apply {
        kotlin.srcDir("src/main/kotlin")
    }
}