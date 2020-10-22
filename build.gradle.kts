plugins {
    kotlin("jvm") version "1.3.72"
}

group = "roslesinforg"
version = "0.1"

repositories {
    mavenCentral()
    flatDir {
        dirs("C:/localrepo/")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("roslesinforg:areatypes:1.6.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}