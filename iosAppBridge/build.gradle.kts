plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.metro)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "AppBridge"
            binaryOption("bundleId", "io.github.ilikeyourhat.whippet")
            isStatic = true
        }
    }

    sourceSets {
        iosMain.dependencies {
            implementation(project(":shared"))
            implementation(libs.androidx.room.runtime)
            implementation(libs.metrox.viewmodel.compose)
        }
    }
}
