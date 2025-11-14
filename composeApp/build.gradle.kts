plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.easylauncher)
}

kotlin {
    androidTarget()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.bundles.androidNetwork)
            implementation(libs.bundles.androidKoin)
            implementation(libs.androidx.activity.compose)
            implementation(libs.timber)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.koin.core)
        }

        commonMain.dependencies {
            implementation(projects.core.designsystem)
            implementation(projects.core.coreKtx)
            implementation(projects.core.data)
            implementation(projects.gituser)

            implementation(libs.bundles.network)
            implementation(libs.bundles.jetbrain)
            implementation(libs.bundles.koin)

            implementation(libs.kamel.image)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

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