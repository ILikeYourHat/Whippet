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
    implementation(compose.desktop.currentOs)
    implementation(libs.compose.ui.tooling)
    implementation(libs.kotlinx.coroutinesSwing)
    implementation(libs.metrox.viewmodel.compose)
}

compose.desktop {
    application {
        mainClass = "io.github.ilikeyourhat.whippet.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "io.github.ilikeyourhat.whippet"
            packageVersion = "1.0.0"
        }
    }
}
