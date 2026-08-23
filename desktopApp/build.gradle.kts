import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.metro)
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.room.runtime)
    implementation(libs.appdirs)
    implementation(libs.compose.components.resources)
    implementation(compose.desktop.currentOs)
    implementation(libs.compose.ui.tooling)
    implementation(libs.kotlinx.coroutinesSwing)
    implementation(libs.metrox.viewmodel.compose)
}

compose.resources {
    packageOfResClass = "io.github.ilikeyourhat.whippet"
}

compose.desktop {
    application {
        mainClass = "io.github.ilikeyourhat.whippet.MainKt"

        nativeDistributions {
            packageName = "Whippet"
            packageVersion = "1.0.0"

            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            windows {
                iconFile.set(project.file("icons/app-icon.ico"))
            }
            macOS {
                iconFile.set(project.file("icons/app-icon.icns"))
            }
            linux {
                iconFile.set(project.file("icons/app-icon.png"))
            }
        }
    }
}
