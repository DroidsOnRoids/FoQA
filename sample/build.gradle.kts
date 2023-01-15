plugins {
    id("com.android.application")
}

android {
    compileSdk = Dependencies.compileSdk
    defaultConfig {
        versionCode = 1
        applicationId = "pl.droidsonroids.foqa.sample"
        minSdk = Dependencies.minSdk
        targetSdk = Dependencies.targetSdk
        multiDexEnabled = true
    }

    lint {
        checkDependencies = true
        xmlOutput = rootProject.file("build/reports/lint/lint-results.xml")
        baseline = file("lint-baseline.xml")
    }

    buildTypes {
        named("debug") {
            matchingFallbacks += "release"
        }
    }
    namespace = "pl.droidsonroids.foqa.sample"
}

dependencies {
    implementation(project(":foqa"))
    implementation(Dependencies.multidex)
}
