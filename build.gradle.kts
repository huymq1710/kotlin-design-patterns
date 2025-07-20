plugins {
    kotlin("jvm") version "1.9.22"
}

kotlin {
    jvmToolchain(21)
}

group = "me.huymac"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}

tasks.test {
    useJUnitPlatform()
}
