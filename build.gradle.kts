plugins {
    java
    kotlin("jvm") version "1.4.10"
}

group = "roslesinforg"
version = "0.1"

repositories {
    mavenCentral()
    flatDir {
        dirs("C:/localrepo/")
    }
}
java{
    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
    targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("no.tornado:tornadofx:1.7.19")
    implementation("roslesinforg:areatypes:1.6.0")
    implementation("roslesinforg:nab-parser:1.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}