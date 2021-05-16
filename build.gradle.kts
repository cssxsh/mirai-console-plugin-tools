plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")

    id("net.mamoe.mirai-console")
}

group = "xyz.cssxsh.mirai.plugin"
version = "0.1.0"

repositories {
    mavenLocal()
    // gradle-plugin
    maven(url = "https://maven.aliyun.com/repository/gradle-plugin")
    gradlePluginPortal()
    // central
    maven(url = "https://maven.aliyun.com/repository/central")
    mavenCentral()
    // jcenter
    maven(url = "https://maven.aliyun.com/repository/jcenter")
    jcenter()
}

dependencies {

    testImplementation(junit("api", Versions.junit))
    testRuntimeOnly(junit("engine", Versions.junit))
}

tasks {
    test {
        useJUnitPlatform()
    }
}
