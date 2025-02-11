buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://dl.bintray.com/icapps/maven")
        maven("https://dl.bintray.com/nicolaverbeeck/maven")
        maven("https://plugins.gradle.org/m2/")
        gradlePluginPortal()
    }
    dependencies {
        classpath(libs.google.services)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt.gradle) apply false
    alias(libs.plugins.androidTest) apply false
}
