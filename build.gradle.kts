import com.android.build.gradle.LibraryExtension
import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
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
        classpath(Dependencies.publishGradlePlugin)
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.23.4"
    id("com.github.ben-manes.versions") version "0.42.0"
    id("org.shipkit.shipkit-changelog") version "1.2.0"
    id("org.shipkit.shipkit-github-release") version "1.2.0"
    id("org.shipkit.shipkit-auto-version") version "1.2.2"
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
    }

    group = "pl.droidsonroids.foqa"

    if (name == "sample") {
        return@subprojects
    }

    apply(plugin = "com.android.library")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "com.vanniktech.maven.publish")

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

    extensions.findByType(MavenPublishBaseExtension::class)?.apply {
        publishToMavenCentral(
            host = SonatypeHost.DEFAULT,
            automaticRelease = true // instead of closeAndReleaseRepository
        )
        signAllPublications()
    }

    dependencies {
        "implementation"(kotlin("stdlib-jdk7"))
        "implementation"(Dependencies.hyperionPlugin)
        "kapt"(Dependencies.autoService)
    }
}
