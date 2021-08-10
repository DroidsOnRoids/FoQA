import com.android.build.gradle.LibraryExtension
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.shipkit.changelog.GenerateChangelogTask
import org.shipkit.github.release.GithubReleaseTask

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
    id("io.gitlab.arturbosch.detekt") version "1.18.0-RC3"
    id("com.github.ben-manes.versions") version "0.39.0"
    id("org.shipkit.shipkit-changelog") version "1.1.15"
    id("org.shipkit.shipkit-github-release") version "1.1.15"
    id("org.shipkit.shipkit-auto-version") version "1.1.19"
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
    source = files(projectDir)
    config = rootProject.files("detekt-config.yml")
}

tasks {
    withType(GenerateChangelogTask::class) {
        previousRevision = project.ext["shipkit-auto-version.previous-tag"] as String
        githubToken = System.getenv("GH_READ_TOKEN")
        repository = "DroidsOnRoids/FoQA"
    }
    withType(GithubReleaseTask::class) {
        repository = "DroidsOnRoids/FoQA"
        changelog = File(buildDir, "changelog.md")
        githubToken = System.getenv("GH_WRITE_TOKEN")
        newTagRevision = System.getenv("BITRISE_GIT_COMMIT")
    }
    withType(Detekt::class) {
        exclude("build/")
        exclude("buildSrc/build/")
        parallel = true
        reports {
            xml.enabled = true
            html.enabled = false
            txt.enabled = false
        }
    }
    withType(KotlinCompile::class).all {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xexplicit-api=strict")
        }
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

    with(extensions.getByName("android") as LibraryExtension) {
        compileSdk = Dependencies.compileSdk
        defaultConfig {
            minSdk = Dependencies.minSdk
            targetSdk = Dependencies.targetSdk
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
