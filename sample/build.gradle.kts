plugins {
    alias(libs.plugins.android.application)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        versionCode = 1
        applicationId = "pl.droidsonroids.foqa.sample"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }

    lint {
        checkDependencies = true
        xmlOutput = rootProject.file("build/reports/lint/lint-results.xml")
    }

    buildTypes {
        named("debug") {
            matchingFallbacks += "release"
        }
    }
    namespace = "pl.droidsonroids.foqa.sample"
}

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

dependencies {
    implementation(projects.foqa)
}
