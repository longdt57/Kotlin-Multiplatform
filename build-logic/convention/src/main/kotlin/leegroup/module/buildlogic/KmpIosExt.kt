package leegroup.module.buildlogic

import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.cocoapods.CocoapodsExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType
import kotlin.apply
import kotlin.collections.forEach
import kotlin.collections.set

@OptIn(ExperimentalKotlinGradlePluginApi::class)
fun Project.configureIosFramework(
    baseName: String,
    isStatic: Boolean = false,
    transitiveExport: Boolean = true,
    frameworkConfig: Framework.() -> Unit = {},
    cocoapodsConfigure: CocoapodsExtension.() -> Unit = {}
) {
    val kmp = extensions.getByType<KotlinMultiplatformExtension>()
    kmp.apply {
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                this.baseName = baseName
                this.isStatic = isStatic
            }
        }
    }
    kmp.extensions.getByType<CocoapodsExtension>().apply {
        this.name = baseName
        this.homepage = "https://abc.com"
        this.summary = "Kmp $baseName"
        this.version = "1.0"
        this.ios.deploymentTarget = "13.0"

        framework {
            this.baseName = baseName
            this.isStatic = isStatic
            this.transitiveExport = transitiveExport
            frameworkConfig()
        }
        cocoapodsConfigure()
        xcodeConfigurationToNativeBuildType["Debug"] = NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["Uat"] = NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["Staging"] = NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["Release"] = NativeBuildType.RELEASE
    }
}
