dependencies {
    api(Dependencies.chucker)
    api(platform(Dependencies.okHttpBom))
    api(Dependencies.okHttp)
}
android {
    namespace = "pl.droidsonroids.foqa.chucker"
}
