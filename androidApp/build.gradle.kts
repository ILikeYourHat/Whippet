import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import pl.allegro.tech.build.axion.release.domain.VersionConfig

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.axion)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.metro)
}

android {
    namespace = "io.github.ilikeyourhat.whippet.app"

    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "io.github.ilikeyourhat.whippet"
        minSdk = 26
        targetSdk = 37
        versionCode = scmVersion.versionCode
        versionName = scmVersion.version

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            val keystoreFile = rootProject.file("keystore/keystore.jks")
            if (keystoreFile.exists()) {
                storeFile = keystoreFile
                storePassword = System.getenv("ANDROID_KEYSTORE_PASSWORD")
                keyAlias = System.getenv("ANDROID_KEY_ALIAS")
                keyPassword = System.getenv("ANDROID_KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    lint {
        warningsAsErrors = true
        lintConfig = file("$rootDir/lint.xml")
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.ui.tooling)
    implementation(libs.androidx.room.runtime)
    implementation(libs.metrox.viewmodel.compose)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.testExt.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

private val VersionConfig.versionCode: Int
    get() {
        val semVerRegex = """(\d+)\.(\d{1,2})\.(\d{1,2})(\D.*)?""".toRegex()
        val groups = semVerRegex.matchEntire(version)?.destructured?.toList()
            ?: throw GradleException("Version must be in SemVer format, but was $this")
        val (major, minor, patch) = groups.take(3).map { it.toInt() }
        return major * 10000 + minor * 100 + patch
    }
