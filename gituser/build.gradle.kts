plugins {
    alias(libs.plugins.nowinandroid.kmp.library)
    alias(libs.plugins.nowinandroid.kmp.library.compose)
    alias(libs.plugins.nowinandroid.kmp.koin)
    alias(libs.plugins.nowinandroid.kmp.network)
    alias(libs.plugins.nowinandroid.kmp.room)
    alias(libs.plugins.kotlinSerialization)
}


kotlin {
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "GituserKit"
            isStatic = true
        }
    }

    // Source set declarations.
    // Declaring a target automatically creates a source set with the same name. By default, the
    // Kotlin Gradle Plugin creates additional source sets that depend on each other, since it is
    // common to share sources between related targets.
    // See: https://kotlinlang.org/docs/multiplatform-hierarchy.html
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.coreKtx)
                implementation(projects.core.data)
                implementation(projects.core.designsystem)

                implementation(libs.bundles.jetbrain)
                implementation(libs.bundles.datastore)

                implementation(libs.bundles.coil)

                implementation(compose.materialIconsExtended)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.bundles.test)
            }
        }
    }
}

android {
    namespace = "leegroup.module.gituser"
//    compileSdk = libs.versions.android.compileSdk.get().toInt()
//    minSdk = libs.versions.android.minSdk.get().toInt()
}

room {
    schemaDirectory("$projectDir/schemas")
}