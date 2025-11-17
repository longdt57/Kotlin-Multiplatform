import leegroup.module.buildlogic.configureKotlinComposeMultiplatform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "org.jetbrains.compose")

            // Get the Kotlin Multiplatform Extension
            val kotlin = extensions.getByType<KotlinMultiplatformExtension>()
            configureKotlinComposeMultiplatform(kotlin)
        }
    }
}
