package leegroup.module.buildlogic

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinMultiplatform(
    kotlin: KotlinMultiplatformExtension
) {

    kotlin.androidTarget()
    kotlin.iosX64()
    kotlin.iosArm64()
    kotlin.iosSimulatorArm64()

    kotlin.sourceSets.getByName("androidMain").dependencies {
    }

    kotlin.sourceSets.getByName("commonMain").dependencies {
        implementation(libs.findLibrary("kotlinx-coroutines-core").get())
        implementation(libs.findLibrary("kotlinx-serialization-json").get())
        implementation(libs.findLibrary("kotlinx-datetime").get())
        implementation(libs.findLibrary("kotlin-stdlib").get())
    }
}
