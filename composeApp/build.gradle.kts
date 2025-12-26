import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import leegroup.module.buildlogic.configureIosFramework
import org.jetbrains.kotlin.gradle.plugin.cocoapods.KotlinCocoapodsPlugin

plugins {
    alias(libs.plugins.nowinandroid.kmp.application)
    alias(libs.plugins.nowinandroid.kmp.application.compose)
    alias(libs.plugins.nowinandroid.kmp.koin)
    alias(libs.plugins.nowinandroid.kmp.network)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.kotlinx.kover)
}

// ----- iOS: read Xcode configuration (Debug/UAT/Staging/Release) -----
val iosAppConfig =
    (project.findProperty(KotlinCocoapodsPlugin.CONFIGURATION_PROPERTY) as? String) ?: "Staging"

// ---------- Android: map task/variant names to clean build type ----------
val androidTasks = project.gradle.startParameter.taskNames
    .joinToString(" ")
    .lowercase()

val androidAppConfig: String = when {
    "uat" in androidTasks -> "Uat"
    "staging" in androidTasks -> "Staging"
    "release" in androidTasks -> "Release"
    else -> "Debug"
}

buildkonfig {
    packageName = "leegroup.app.kmm.gituser"

    defaultConfigs {
        // default for non-android/non-ios tasks
        buildConfigField(STRING, "BUILD_TYPE", "DEBUG")
    }

    targetConfigs {
        create("android") {
            buildConfigField(STRING, "BUILD_TYPE", androidAppConfig)
        }
        // ✅ match real iOS target names
        listOf("iosArm64", "iosX64", "iosSimulatorArm64").forEach {
            create(it) {
                buildConfigField(STRING, "BUILD_TYPE", iosAppConfig)
            }
        }
    }
}

kotlin {
    configureIosFramework("ComposeApp")

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.timber)
            implementation(libs.androidx.room.sqlite.wrapper)
        }

        commonMain.dependencies {
            implementation(projects.core.designsystem)
            implementation(projects.core.coreKtx)
            implementation(projects.core.data)

            implementation(projects.gituser)

            implementation(libs.bundles.jetbrain)
            implementation(libs.bundles.kmp.coil)

            // Logging
            implementation(libs.kermit)
        }
    }
}

android {
    namespace = "leegroup.app.kmm.gituser"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "leegroup.app.kmm.gituser"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

dependencies {
    kover(projects.core.coreKtx)
    kover(projects.core.data)
    kover(projects.core.designsystem)
    kover(projects.gituser)
}

kover {
    reports {
        filters {
            includes {
                classes("*ViewModel")
                classes("*UseCase")
                classes("*UseCase")
                classes("*Mapper")
                classes("*MapperImpl")
                classes("*Mapping")
                classes("*Repository")
                classes("*RepositoryImpl")
                classes("*Util")
                classes("*Helper")
                classes("*HelperImpl")
                classes("*Formatter")
                classes("*FormatterImpl")
                classes("*Converter")
                classes("*ConverterImpl")
                classes("*UiState")
                classes("*Event")
                classes("*Builder")
                classes("*Controller")
                classes("*Data")
                classes("*Analytic")
                classes("*Analytics")
                classes("*AnalyticManager")
            }
        }
    }
}
