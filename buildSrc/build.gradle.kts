import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

configurations.create("ktlint")

dependencies {
    "ktlint"("com.pinterest:ktlint:0.50.0")
}
