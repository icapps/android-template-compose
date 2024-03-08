import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties

plugins {
    id("com.android.application")
    kotlin("android")
//    TODO: Enable once google-services.json is added
//    id("com.google.gms.google-services")
//    id("com.google.firebase.crashlytics")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    id("com.google.devtools.ksp")
}

kotlin {
    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
    }
}

android {
    namespace = "com.icapps.template"
    compileSdk = libs.versions.compileSdk.get().toInt()

    val properties = Properties()
    if (project.rootProject.file("local.properties").canRead()) {
        properties.load(project.rootProject.file("local.properties").inputStream())
    }

    // TODO Add build number from pipeline!
    val buildNumber = 1

    defaultConfig {
        applicationId = "com.icapps.template"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = buildNumber
        versionName = "1.0.0"
        versionNameSuffix = ".$buildNumber"
        resourceConfigurations.addAll(listOf("en", "nl"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("keystore/debug.keystore")
            storePassword = "androidkey"
            keyAlias = "androidkey"
            keyPassword = "androidkey"
        }
        create("release") {
            storeFile = file("keystore/production.keystore")
            storePassword = project.findProperty("keyStorePassword") as String? ?: properties.getProperty("keyStorePassword") ?: ""
            keyAlias = project.findProperty("keyAlias") as String? ?: properties.getProperty("keyAlias") ?: ""
            keyPassword = project.findProperty("keyPassword") as String? ?: properties.getProperty("keyPassword") ?: ""
            enableV2Signing = true
            enableV3Signing = true
            enableV4Signing = true
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles.addAll(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    file("proguard-rules.pro"),
                ),
            )

        }
        getByName("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles.addAll(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    file("proguard-rules.pro"),
                ),
            )
        }
    }

    flavorDimensions.add("app")
    productFlavors {
        create("dev") {
            dimension = "app"
            applicationIdSuffix = ".dev"
        }
        create("acc") {
            dimension = "app"
            applicationIdSuffix = ".acc"
        }
        create("production") {
            dimension = "app"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }

    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

// Execute ktLintFormat before every build
tasks.named("preBuild") {
    dependsOn("ktLintFormat")
}

apply {
    from("dependencies.gradle")
}
