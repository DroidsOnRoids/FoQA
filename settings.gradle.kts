plugins {
    id("com.gradle.enterprise") version "3.6.3"
}

include(":foqa")
include(":debug-db")
include(":device_info_plugin")
include(":font_scale_plugin")
include(":chucker_plugin")
include(":sample")

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}
