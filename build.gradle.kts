import com.android.build.gradle.LibraryExtension
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(Dependencies.androidGradlePlugin)
        classpath(Dependencies.kotlinGradlePlugin)
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version ("1.16.0-RC1")
    id("org.shipkit.java") version ("2.3.5")
    id("com.github.ben-manes.versions") version ("0.36.0")
}

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlinx/")
}

dependencies {
    detekt(Dependencies.detektFormatting)
    detekt(Dependencies.detektCli)
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
