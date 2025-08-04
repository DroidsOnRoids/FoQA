import org.gradle.api.JavaVersion

object Dependencies {
    const val compileSdk = 35
    const val targetSdk = 35
    const val minSdk = 21
    val JAVA_VERSION = JavaVersion.VERSION_21

    const val autoService = "com.google.auto.service:auto-service:1.1.1"
    const val androidxAnnotations = "androidx.annotation:annotation:1.9.1"
    const val androidxStartup = "androidx.startup:startup-runtime:1.2.0"
    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:1.23.8"
    const val detektCli = "io.gitlab.arturbosch.detekt:detekt-cli:1.23.8"
    const val hyperionCore = "com.willowtreeapps.hyperion:hyperion-core:0.9.38"
    const val hyperionPlugin = "com.willowtreeapps.hyperion:hyperion-plugin:0.9.38"
    const val hyperionAttr = "com.willowtreeapps.hyperion:hyperion-attr:0.9.38"
    const val hyperionMeasurement = "com.willowtreeapps.hyperion:hyperion-measurement:0.9.38"
    const val hyperionDisk = "com.willowtreeapps.hyperion:hyperion-disk:0.9.38"
    const val hyperionRecorder = "com.willowtreeapps.hyperion:hyperion-recorder:0.9.38"
    const val hyperionPhoenix = "com.willowtreeapps.hyperion:hyperion-phoenix:0.9.38"
    const val hyperionCrash = "com.willowtreeapps.hyperion:hyperion-crash:0.9.38"
    const val hyperionSharedPreferences =
        "com.willowtreeapps.hyperion:hyperion-shared-preferences:0.9.38"
    const val hyperionGeigerCounter = "com.willowtreeapps.hyperion:hyperion-geiger-counter:0.9.38"
    const val hyperionTimber = "com.willowtreeapps.hyperion:hyperion-timber:0.9.38"
    const val hyperionBuildConfig = "com.willowtreeapps.hyperion:hyperion-build-config:0.9.38"
    const val hyperionAppInfo = "com.star-zero:hyperion-appinfo:2.0.0"
    const val chucker = "com.github.chuckerteam.chucker:library:4.2.0"
    const val deviceNames = "com.jaredrummler:android-device-names:2.1.1"
    const val multidex = "androidx.multidex:multidex:2.0.1"
    const val androidGradlePlugin = "com.android.tools.build:gradle:8.12.0"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.0"
    const val publishGradlePlugin = "com.vanniktech:gradle-maven-publish-plugin:0.34.0"
    const val okHttpBom = "com.squareup.okhttp3:okhttp-bom:5.1.0"
    const val okHttp = "com.squareup.okhttp3:okhttp"
    const val gson = "com.google.code.gson:gson:2.13.1"
    const val room = "androidx.room:room-runtime:2.7.2"
}
