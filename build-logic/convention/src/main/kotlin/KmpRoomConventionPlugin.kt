import leegroup.module.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpRoomConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("androidx.room")
            }

            val kotlin = extensions.getByType<KotlinMultiplatformExtension>()

            kotlin.sourceSets.getByName("androidMain").dependencies {
                implementation(libs.findLibrary("androidx-room-sqlite-wrapper").get())
            }

            kotlin.sourceSets.getByName("commonMain").dependencies {
                implementation(libs.findLibrary("androidx-room-runtime").get())
                implementation(libs.findLibrary("androidx-sqlite-bundled").get())
            }

            dependencies {

                // KSP compiler for each target
                add("kspAndroid", libs.findLibrary("androidx-room-compiler").get())
                add("kspIosSimulatorArm64", libs.findLibrary("androidx-room-compiler").get())
                add("kspIosX64", libs.findLibrary("androidx-room-compiler").get())
                add("kspIosArm64", libs.findLibrary("androidx-room-compiler").get())
                // Add other KSP targets as needed for your project (e.g., kspDesktop)
            }
        }
    }
}
