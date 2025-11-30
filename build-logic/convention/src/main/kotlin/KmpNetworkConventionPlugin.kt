import leegroup.module.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpNetworkConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {

            val kotlin = extensions.getByType<KotlinMultiplatformExtension>()

            kotlin.sourceSets.getByName("androidMain").dependencies {
                implementation(libs.findLibrary("ktor-client-android").get())
                implementation(libs.findLibrary("ktor-client-okhttp").get())
            }

            kotlin.sourceSets.getByName("commonMain").dependencies {
                implementation(libs.findLibrary("ktor-client-core").get())
                implementation(libs.findLibrary("ktor-client-serialization").get())
                implementation(libs.findLibrary("ktor-client-negotiation").get())
                implementation(libs.findLibrary("ktor-client-logging").get())
            }
            kotlin.sourceSets.maybeCreate("iosMain").dependencies {
                implementation(libs.findLibrary("ktor-client-darwin").get())
            }
        }
    }
}
