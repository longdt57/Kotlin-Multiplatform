import leegroup.module.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpKoinConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {

            val kotlin = extensions.getByType<KotlinMultiplatformExtension>()

            kotlin.sourceSets.getByName("androidMain").dependencies {
                implementation(libs.findLibrary("koin-android").get())
                implementation(libs.findLibrary("koin-androidx-compose").get())
                implementation(libs.findLibrary("koin-androidx-navigation").get())
            }

            kotlin.sourceSets.getByName("commonMain").dependencies {
                implementation(libs.findLibrary("koin-core").get())
                implementation(libs.findLibrary("koin-compose-viewmodel").get())
            }
            kotlin.sourceSets.maybeCreate("iosMain").dependencies {
                implementation(libs.findLibrary("koin-core").get())
            }
        }
    }
}
