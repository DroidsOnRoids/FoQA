import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.shipkit.changelog.GenerateChangelogTask
import org.shipkit.github.release.GithubReleaseTask

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.shipkit.changelog)
    alias(libs.plugins.shipkit.github.release)
    alias(libs.plugins.shipkit.auto.version)
    alias(libs.plugins.detekt)
    alias(libs.plugins.versions)
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
}

dependencies {
    detekt(libs.detekt.formatting)
    detekt(libs.detekt.cli)
}

detekt {
    source.setFrom(files(projectDir))
    config.setFrom(rootProject.files("detekt-config.yml"))
}

tasks {
    withType(GenerateChangelogTask::class) {
        previousRevision = project.ext["shipkit-auto-version.previous-tag"] as String
        githubToken = System.getenv("GH_READ_TOKEN")
        repository = "DroidsOnRoids/FoQA"
    }
    withType(GithubReleaseTask::class) {
        repository = "DroidsOnRoids/FoQA"
        changelog = layout.buildDirectory.file("changelog.md").get().asFile
        githubToken = System.getenv("GH_WRITE_TOKEN")
        newTagRevision = System.getenv("BITRISE_GIT_COMMIT")
    }
    withType(Detekt::class) {
        exclude("build/")
        exclude("buildSrc/build/")
        parallel = true
        reports {
            xml.required.set(true)
            html.required.set(false)
            txt.required.set(false)
        }
    }
}

subprojects {
    group = "pl.droidsonroids.foqa"

    apply(plugin = rootProject.libs.plugins.kotlin.android.get().pluginId)

    if (name == "sample") {
        return@subprojects
    }

    apply(plugin = rootProject.libs.plugins.android.library.get().pluginId)
    apply(plugin = rootProject.libs.plugins.kotlin.kapt.get().pluginId)
    apply(plugin = rootProject.libs.plugins.maven.publish.get().pluginId)

    extensions.findByType(LibraryExtension::class)?.apply {
        compileSdk = rootProject.libs.versions.compileSdk.get().toInt()
        defaultConfig {
            minSdk = rootProject.libs.versions.minSdk.get().toInt()
        }
        lint {
            targetSdk = rootProject.libs.versions.targetSdk.get().toInt()
        }

        resourcePrefix = "foqa_"
    }

    extensions.findByType(AndroidComponentsExtension::class)?.apply {
        beforeVariants {
            if (it.buildType == "debug") {
                it.enable = false
            }
        }
    }

    extensions.findByType(KotlinProjectExtension::class)?.apply {
        jvmToolchain(rootProject.libs.versions.java.get().toInt())
    }

    dependencies {
        "implementation"(rootProject.libs.hyperion.plugin)
        "kapt"(rootProject.libs.auto.service)
    }
}
