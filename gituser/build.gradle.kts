plugins {
    alias(libs.plugins.nowinandroid.kmp.library)
    alias(libs.plugins.nowinandroid.kmp.library.compose)
    alias(libs.plugins.nowinandroid.kmp.koin)
    alias(libs.plugins.nowinandroid.kmp.network)
    alias(libs.plugins.nowinandroid.kmp.room)
    alias(libs.plugins.kotlinSerialization)
}


kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(projects.core.coreKtx)
                implementation(projects.core.data)
                implementation(projects.core.designsystem)

                implementation(libs.bundles.jetbrain)
                implementation(libs.bundles.kmp.datastore)

                implementation(libs.bundles.kmp.coil)

                implementation(compose.materialIconsExtended)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.bundles.kmp.test)
            }
        }
    }
}

android {
    namespace = "leegroup.module.gituser"
}

room {
    schemaDirectory("$projectDir/schemas")
}