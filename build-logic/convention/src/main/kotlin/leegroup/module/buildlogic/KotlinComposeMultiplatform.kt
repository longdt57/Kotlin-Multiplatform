package leegroup.module.buildlogic

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKotlinComposeMultiplatform(
    kotlin: KotlinMultiplatformExtension
) {
    kotlin.sourceSets.getByName("commonMain").dependencies {
        implementation(libs.findLibrary("compose-multiplatform-runtime").get())
        implementation(libs.findLibrary("compose-multiplatform-ui").get())
        implementation(libs.findLibrary("compose-multiplatform-foundation").get())
        // implementation(libs.findLibrary("compose-multiplatform-material").get())
        implementation(libs.findLibrary("compose-multiplatform-material3").get())
        implementation(libs.findLibrary("compose-multiplatform-resources").get())
        implementation(libs.findLibrary("compose-multiplatform-ui-tooling-preview").get())

        implementation(libs.findLibrary("androidx-lifecycle-viewmodel").get())
        implementation(libs.findLibrary("navigation-compose").get())
    }

    kotlin.sourceSets.getByName("androidMain").dependencies {
//        implementation(libs.findLibrary("androidx-activity-compose").get())
//        implementation(libs.findLibrary("androidx-compose-material3-windowSizeClass").get())
//        implementation(libs.findLibrary("androidx-lifecycle-viewmodelCompose").get())
//        implementation(libs.findLibrary("androidx-lifecycle-runtimeCompose").get())
//        implementation(libs.findLibrary("androidx-navigation-compose").get())
    }
}
