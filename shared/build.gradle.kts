import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.ksp)
    alias(libs.plugins.metro)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    android {
        namespace = "io.github.ilikeyourhat.whippet"
        compileSdk = 37
        minSdk = 26
        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
        withHostTest {}
    }
    iosArm64()
    iosSimulatorArm64()
    jvm()

    compilerOptions.freeCompilerArgs.add("-Xexpect-actual-classes")

    sourceSets {
        commonMain.dependencies {
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite)
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.material.icons)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.kotlinx.datetime)
            implementation(libs.metrox.viewmodel.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

room3 {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    listOf(
        "kspAndroid",
        "kspIosArm64",
        "kspIosSimulatorArm64",
        "kspJvm"
    ).forEach {
        add(it, libs.androidx.room.compiler)
    }
}
