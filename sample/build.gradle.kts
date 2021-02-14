plugins {
    id("com.android.application")
}

android {
    compileSdkVersion(Dependencies.compileSdk)
    defaultConfig {
        versionCode = 1
        applicationId = "pl.droidsonroids.foqa.sample"
        minSdkVersion(Dependencies.minSdk)
        targetSdkVersion(Dependencies.targetSdk)
        multiDexEnabled = true
    }

    lintOptions {
        isCheckDependencies = true
        xmlOutput = rootProject.file("build/reports/lint/lint-results.xml")
    }

    buildTypes {
        named("debug") {
            setMatchingFallbacks("release")
        }
    }
}

dependencies {
    implementation(project(":foqa"))
    implementation(Dependencies.multidex)
}
