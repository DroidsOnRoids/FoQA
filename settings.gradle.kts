plugins {
    id("com.gradle.enterprise") version "3.6.3"
}

include(":foqa", ":device_info_plugin", ":font_scale_plugin", ":chucker_plugin", ":sample")

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}