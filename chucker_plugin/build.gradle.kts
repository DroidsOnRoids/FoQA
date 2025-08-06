dependencies {
    api(libs.chucker)
    api(platform(libs.okhttp.bom))
    api(libs.okhttp)
}
android {
    namespace = "pl.droidsonroids.foqa.chucker"
}
