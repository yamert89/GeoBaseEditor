plugins {
    java
    kotlin("jvm") version "1.4.20"
    kotlin("plugin.serialization") version "1.4.20"
}

group = "roslesinforg"
version = "0.3"
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
}
sourceSets{

}
project.configurations.implementation.isCanBeResolved = true
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    val archieveN = "GeoBaseEditor-${project.version}.jar"

    val fatJar = register("fatJar", Jar::class){
        dependsOn(compileKotlin)
        manifest{
            attributes["Main-Class"] = "roslesinforg.porokhin.geobaseeditor.view.MainViewKt"
        }

        exclude("META-INF/*.RSA", "META-INF/*.SF","META-INF/*.DSA")
        archiveFileName.set(archieveN)
        //from(configurations.runtimeClasspath.get()/*.filter { it.name.startsWith("poi") }*/.map { if(it.isDirectory) it else zipTree(it) })
        //with(jar.get() as CopySpec)

        from(configurations.implementation.get().files.map{ if(it.isDirectory) it else zipTree(it)})
        with(jar.get() as CopySpec)
        //from(file("${System.getProperty("pathRepo")}/JavaDDEx64.dll"))
    }

    val startFolder = file("${System.getProperty("pathJars")}GeoBaseEditor\\")

    register("cleanStartDir"){
        dependsOn(fatJar)
        startFolder.listFiles()?.filter { it.name.endsWith(".jar") }?.forEach { delete(it.absolutePath) }
    }

    register<Copy>("copy"){
        dependsOn(getByName("cleanStartDir"))
        val buildD = "$projectDir/build/libs/"
        from(file("$buildD/$archieveN"))
        into(startFolder)
        println("GeoBaseEditor built with version $version to $startFolder")
    }
}