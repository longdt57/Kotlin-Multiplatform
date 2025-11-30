import com.android.build.api.dsl.ApplicationExtension
import leegroup.module.buildlogic.configureKotlinAndroid
import leegroup.module.buildlogic.configureKotlinMultiplatform
import leegroup.module.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.multiplatform")

            val kotlin = extensions.getByType<KotlinMultiplatformExtension>()
            configureKotlinMultiplatform(kotlin)

            val androidExtension = extensions.getByType<ApplicationExtension>()
            androidExtension.buildTypes {
                create("uat")
                create("staging")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk =
                    libs.findVersion("android.targetSdk").get().toString().toInt()
            }

        }
    }

}
