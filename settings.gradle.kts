rootProject.name = "demo-state-machine"
pluginManagement {
    val kotlinVersion: String by settings
    val kotlinSerialization: String by settings
    val kotlinSpring: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version kotlinSerialization
        kotlin("plugin.spring") version kotlinSpring
    }

    repositories {
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
}