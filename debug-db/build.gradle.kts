android {
    namespace = "pl.droidsonroids.foqa.debug_db"

    defaultConfig {
        resValue("string", "PORT_NUMBER", "8080")
    }
}

dependencies {
    implementation(Dependencies.gson)
    implementation(Dependencies.room)
}
