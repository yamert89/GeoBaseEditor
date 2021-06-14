import org.jetbrains.kotlin.cli.common.toBooleanLenient
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.20"
    kotlin("plugin.serialization") version "1.4.20"
}

group = "roslesinforg"
version = "1.0.0"
repositories {
    flatDir {
        dirs("c:/localrepo")
    }
}

java{
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("no.tornado:tornadofx:1.7.20")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("com.charleskorn.kaml:kaml:0.29.0")
    implementation("org.apache.poi:poi-ooxml:4.1.2")
    implementation("org.apache.logging.log4j:log4j-api:2.14.0")
    implementation("org.apache.logging.log4j:log4j-core:2.14.0")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.0.0")
    implementation("no.tornado:tornadofx-controlsfx:0.1")
    //implementation(files("pretty-tools-JDDE-2.1.0"))
    implementation(project(":areatypes2"))
    implementation(project(":nab_parser"))
    implementation(project(":area-writer"))
    implementation(project(":fileComparator"))
    implementation(project(":RawToXlsConverter"))
    implementation("com.pretty_tools:pretty-tools-JDDE-2.1.0")
    implementation("com.jacob:jacob")
    testImplementation("junit:junit:4.13.2")
}

val archieveN = "GeoBaseEditor.jar"
fun String.prop() = System.getProperty(this)
val workPlaceConfiguration = "workplaceConfiguration".prop().toBooleanLenient()!!
var startPath = ""
var deployPath = ""
if (workPlaceConfiguration) {
    startPath = "pathJarsWorkplace".prop()
    deployPath = "pathDeployWorkplace".prop()
} else {
    startPath = "pathJarsHome".prop()
    deployPath = "pathDeployHome".prop()
}

startPath = "${startPath}GeoBaseEditor\\"
project.configurations.implementation.get().isCanBeResolved = true
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    val fatJar = register("fatJar", Jar::class){
        dependsOn(compileKotlin)
        manifest{
            attributes["Main-Class"] = "roslesinforg.porokhin.geobaseeditor.view.MainViewKt"
        }
        exclude("META-INF/*.RSA", "META-INF/*.SF","META-INF/*.DSA")
        archiveFileName.set(archieveN)
        from(configurations.implementation.get().files.map{ if(it.isDirectory) it else zipTree(it)})
        with(jar.get() as CopySpec)
    }

    val startFolder = file(startPath)


    val cleanStartDir = register("cleanStartDir"){
        dependsOn(fatJar)
        startFolder.listFiles()?.filter { it.name.endsWith(".jar") }?.forEach { delete(it.absolutePath) }
    }



    val copyLists = register<Copy>("copyLists"){
        dependsOn(cleanStartDir)
        from(fileTree("${project.parent!!.projectDir}/areatypes2/src/main/resources").filter { it.name.endsWith("yml") })
        into(file("$startPath/lists"))
    }

    val buildD = "$projectDir/build/libs/"
    val j = file("$buildD/$archieveN")

    val deploy = register<Copy>("deploy"){
        dependsOn(cleanStartDir)
        println("Deploy path: $deployPath")
        from(j)
        into(deployPath)
    }

    val deployVersion = register<Copy>("deployVersion"){
        dependsOn(cleanStartDir)
        from(file("$projectDir/src/main/resources/currentversion.yml"))
        into(deployPath)
        rename { _ -> "version.yml" }
    }

    val copy = register<Copy>("copy"){
        dependsOn(copyLists, deploy, deployVersion)
        from(j)
        into(startFolder)
        println("GeoBaseEditor built with version $version to $startFolder")
    }
}
val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}