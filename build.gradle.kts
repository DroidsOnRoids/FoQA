import com.android.build.gradle.LibraryExtension
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0-alpha06"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30"
//            kotlin             : '1.4.30',
//            hyperion           : '0.9.31',
//            debugDb            : '1.0.6',
//            chucker            : '3.4.0',
//            hyperionAppInfo    : '1.1.0',
//            deviceNames        : '2.0.0',
//            autoService        : '1.0-rc7',
//            shipkit            : '2.3.0',
//            detekt             : '1.11.1',
//            androidxAnnotations: '1.2.0-beta01',
//            multidex           : '2.0.1',
//    ]
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(androidGradlePlugin)
        classpath(kotlinGradlePlugin)
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version ("1.16.0-RC1")
    id("org.shipkit.java") version ("2.3.5")
    id("com.github.ben-manes.versions") version ("0.36.0")
}

dependencies {
    detekt(Dependencies.detektFormatting)
    detekt(Dependencies.detektCli)
}

repositories {
    mavenCentral()
}

detekt {
    input = files(projectDir)
    config = rootProject.files("detekt-config.yml")
}

tasks.withType(Detekt::class) {
    exclude("build/")
    exclude("buildSrc/build/")
    parallel = true
    reports {
        xml.enabled = true
        html.enabled = false
        txt.enabled = false
    }
}

tasks.withType(KotlinCompile::class).all {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xexplicit-api=strict")
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }

    group = "pl.droidsonroids.foqa"

    if (name == "sample") {
        return@subprojects
    }

    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.shipkit.bintray")
    apply(plugin = "org.shipkit.android-publish")

    with(extensions.getByName("android") as LibraryExtension) {
        compileSdkVersion(Dependencies.compileSdk)
        defaultConfig {
            setMinSdkVersion(Dependencies.minSdk)
            setTargetSdkVersion(Dependencies.targetSdk)
        }

        resourcePrefix = "foqa_"

        variantFilter {
            ignore = name == "debug"
        }
    }

    dependencies {
        "implementation"(kotlin("stdlib-jdk7"))
        "implementation"(Dependencies.hyperionPlugin)
        "kapt"(Dependencies.autoService)
    }
}
