plugins {
    alias(libs.plugins.nowinandroid.kmp.library)
}

kotlin {
    sourceSets.commonMain.dependencies {
        implementation(kotlin("test"))
        implementation(libs.bundles.kmp.test)
        implementation(projects.core.coreKtx)
    }
}

android {
    namespace = "leegroup.module.test"
}

dependencies {
    implementation(libs.bundles.test)
}