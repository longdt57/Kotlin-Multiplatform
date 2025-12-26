plugins {
    alias(libs.plugins.nowinandroid.kmp.library)
    alias(libs.plugins.nowinandroid.kmp.library.compose)
    alias(libs.plugins.nowinandroid.kmp.koin)
    alias(libs.plugins.nowinandroid.kmp.network)
    alias(libs.plugins.nowinandroid.kmp.room)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinx.kover)
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
                implementation(projects.core.test)
            }
        }
    }
}

android {
    namespace = "leegroup.module.gituser"

    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}