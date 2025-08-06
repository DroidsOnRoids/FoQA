android {
    defaultConfig {
        consumerProguardFile("consumer-progruard-rules.pro")
    }
    namespace = "pl.droidsonroids.foqa"
}

dependencies {
    api(libs.hyperion.core)
    api(libs.hyperion.plugin)
    implementation(libs.hyperion.attr)
    implementation(libs.hyperion.measurement)
    implementation(libs.hyperion.disk)
    implementation(libs.hyperion.recorder)
    implementation(libs.hyperion.phoenix)
    implementation(libs.hyperion.crash)
    implementation(libs.hyperion.shared.preferences)
    implementation(libs.hyperion.geiger.counter)
    implementation(libs.hyperion.timber)
    implementation(libs.hyperion.build.config)
    implementation(libs.hyperion.app.info)
    implementation(libs.androidx.startup)
    api(libs.chucker)
    api(platform(libs.okhttp.bom))
    api(libs.okhttp)

    implementation(projects.debugDb)
    implementation(projects.deviceInfoPlugin)
    implementation(projects.fontScalePlugin)
    implementation(projects.chuckerPlugin)
}
