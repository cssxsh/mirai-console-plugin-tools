plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")

    id("net.mamoe.mirai-console")
}

group = "xyz.cssxsh.mirai.plugin"
version = "0.1.0"

repositories {
    mavenLocal()
    maven(url = "https://maven.aliyun.com/repository/central")
    maven(url = "https://maven.aliyun.com/repository/jcenter")
    maven(url = "https://maven.aliyun.com/repository/gradle-plugin")
    mavenCentral()
    jcenter()
    gradlePluginPortal()
}

dependencies {
    compileOnly(kotlin("stdlib"))
    testImplementation(junit("api", Versions.junit))
    testRuntimeOnly(junit("engine", Versions.junit))
}

tasks {
    test {
        useJUnitPlatform()
    }
}
