import com.android.build.gradle.LibraryExtension
import leegroup.module.buildlogic.configureKotlinAndroid
import leegroup.module.buildlogic.configureKotlinMultiplatform
import leegroup.module.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.multiplatform")
            }

            val androidExtension = extensions.getByType<LibraryExtension>()
            androidExtension.buildTypes {
                create("uat")
                create("staging")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk =
                    libs.findVersion("android.targetSdk").get().toString().toInt()
            }

            val kotlin = extensions.getByType<KotlinMultiplatformExtension>()
            configureKotlinMultiplatform(kotlin)
        }
    }
}