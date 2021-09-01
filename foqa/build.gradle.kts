android {
    defaultConfig {
        consumerProguardFile("consumer-progruard-rules.pro")
    }
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
    implementation(Dependencies.debugDb)

    implementation(project(":device_info_plugin"))
    implementation(project(":font_scale_plugin"))
    implementation(project(":chucker_plugin"))
}
