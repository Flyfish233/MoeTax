import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {

    androidTarget()

    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
            apiVersion = "2.0"
        }
    }

    @OptIn(ExperimentalWasmDsl::class) wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }


    // For iToddlers
    listOf(
        iosX64(), iosArm64(), iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)

            implementation(libs.material.kolor)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "com.flyfish233.moetax"
    compileSdk = 34

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.flyfish233.moetax"
        minSdk = 24
        targetSdk = 34
        versionCode = 4
        versionName = versionCode.toString()
    }

    packaging {
        resources {
            excludes += "/META-INF/AL2.0"
            excludes += "/META-INF/LGPL2.1"
            excludes += "/META-INF/LICENSE-notice.md"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/NOTICE.md"
            excludes += "/META-INF/NOTICE"
            excludes += "/META-INF/LICENSE"
            excludes += "/META-INF/NOTICE.txt"
            excludes += "/META-INF/LICENSE.txt"
            excludes += "/META-INF/INDEX.LIST"
            excludes += "/META-INF/MANIFEST.MF"
            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/skiko.kotlin_module"
            excludes += "/META-INF/desktop.kotlin_module"
            excludes += "/META-INF/library.kotlin_module"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.flyfish233.moetax"
            packageVersion = "1.0.4"
        }

        buildTypes.release.proguard {
            obfuscate.set(true)
            configurationFiles.from(project.file("compose-desktop.pro"))
        }
    }
}