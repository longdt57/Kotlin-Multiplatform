plugins {
    alias(libs.plugins.nowinandroid.kmp.library)
    alias(libs.plugins.nowinandroid.kmp.library.compose)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.kover)
}

kotlin {

    android {
        namespace = "leegroup.module.designsystem"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.coreKtx)
            implementation(projects.core.data)

            implementation(libs.bundles.kmp.network)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.androidx.lifecycle.viewmodel)

            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)

        }

        commonTest {
            dependencies {
                implementation(libs.bundles.kmp.test)
                implementation(projects.core.test)
            }
        }

        androidMain {
            dependencies {
                // Add Android-specific dependencies here. Note that this source set depends on
                // commonMain by default and will correctly pull the Android artifacts of any KMP
                // dependencies declared in commonMain.
            }
        }

        iosMain {
            dependencies {
                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
            }
        }
    }

}

dependencies {


    testImplementation(libs.bundles.test)
    testImplementation(projects.core.test)
}

compose.resources {
    // Can public resources to use in parent module but is not available in preview UI so far.
    // Currently apply actual/expect data type to get the resource.
    publicResClass = true
    // optional custom package res class:
    // packageOfResClass = "kmpbase.core.resources.generated.resources"
}
