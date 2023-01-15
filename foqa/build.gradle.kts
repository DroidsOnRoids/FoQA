android {
    defaultConfig {
        consumerProguardFile("consumer-progruard-rules.pro")
    }
    namespace = "pl.droidsonroids.foqa"
}

dependencies {
    api(Dependencies.hyperionCore)
    api(Dependencies.hyperionPlugin)
    implementation(Dependencies.hyperionAttr)
    implementation(Dependencies.hyperionMeasurement)
    implementation(Dependencies.hyperionDisk)
    implementation(Dependencies.hyperionRecorder)
    implementation(Dependencies.hyperionPhoenix)
    implementation(Dependencies.hyperionCrash)
    implementation(Dependencies.hyperionSharedPreferences)
    implementation(Dependencies.hyperionGeigerCounter)
    implementation(Dependencies.hyperionTimber)
    implementation(Dependencies.hyperionBuildConfig)
    implementation(Dependencies.hyperionAppInfo)
    api(Dependencies.chucker)
    api(platform(Dependencies.okHttpBom))
    api(Dependencies.okHttp)
    implementation(Dependencies.debugDb)

    implementation(project(":device_info_plugin"))
    implementation(project(":font_scale_plugin"))
    implementation(project(":chucker_plugin"))
}
