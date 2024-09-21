plugins {
    id("java")
    id("com.diffplug.spotless") version "6.20.0"
}

group = "org.fcilito.codigo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

spotless {
    java {
        eclipse().configFile()
    }
    kotlin {
        ktlint()
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
