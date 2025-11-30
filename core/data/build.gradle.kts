plugins {
    alias(libs.plugins.nowinandroid.kmp.library)
    alias(libs.plugins.nowinandroid.kmp.network)
}

kotlin {
    android {
        namespace = "leegroup.module.data"
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.coreKtx)
                implementation(libs.bundles.kmp.room)
                implementation(libs.bundles.kmp.datastore)
                implementation(libs.bundles.kmp.crypto)

                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.json)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.bundles.kmp.test)
            }
        }

        androidMain {
            dependencies {
            }
        }

        iosMain {
            dependencies {
            }
        }
    }
}