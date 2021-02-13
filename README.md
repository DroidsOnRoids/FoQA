# FoQA

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/pl.droidsonroids.foqa/foqa/badge.svg)](https://maven-badges.herokuapp.com/maven-central/pl.droidsonroids.foqa/foqa)
<img src="art/seal.svg" alt="Logo" width="200"/>

Container for various Quality Assurance utilities to be included in QA/testing variants of Android apps.

## Description

Integrate various Quality Assurance tools into Android application by adding just one line to `build.gradle`.
FoQA [Hyperion](https://github.com/willowtreeapps/Hyperion-Android) plugins can also be used separately.

## Usage

Add FoQA dependency to `build.gradle` of the application module:

```groovy
dependencies {
    debugImplementation 'pl.droidsonroids.foqa:foqa:<latest version>'
}
```
```kotlin
dependencies {
    debugImplementation("pl.droidsonroids.foqa:foqa:<latest version>")
}
```
Latest version number: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/pl.droidsonroids.foqa/foqa/badge.svg)](https://maven-badges.herokuapp.com/maven-central/pl.droidsonroids.foqa/foqa)

If not already present add Maven Central repository:
```groovy
repositories {
    mavenCentral()
}
```

## Contents

- [Hyperion](https://github.com/willowtreeapps/Hyperion-Android) with core plugins
- [Hyperion App Info](https://github.com/willowtreeapps/Hyperion-Android)
- [Android Debug Database](https://github.com/amitshekhariitbhu/Android-Debug-Database)
- Hyperion menu item with Device market name [Hyperion Device Info](#device-info-plugin)
- Hyperion menu item with font scale changer [Hyperion Font Scale](#font-scale-plugin)
- Hyperion menu item with Chucker launcher [Hyperion FoQA Chucker](#chucker-plugin)

See [build.gradle](foqa/build.gradle) for a complete list of components. And [build.gradle](build.gradle) for versions.

## FoQA Hyperion plugins

### Device Info plugin

Shows device market name and Android version.

<img src="art/device_info_pixel.png" alt="Device info plugin demo"/>
<img src="art/device_info_sgs4.png" alt="Device info plugin demo"/>

### Usage

```groovy
dependencies {
    debugImplementation 'pl.droidsonroids.foqa:deviceinfo:<latest version>'
}
```

```kotlin
dependencies {
    debugImplementation("pl.droidsonroids.foqa:deviceinfo:<latest version>")
}
```

Latest version number: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/pl.droidsonroids.foqa/deviceinfo/badge.svg)](https://maven-badges.herokuapp.com/maven-central/pl.droidsonroids.foqa/deviceinfo)

### Font Scale plugin

Allows to quickly change system font scale in range from `0.4` to `1.6`.
System settings write prompt will appear when setting scale for the first time. 

<img src="art/font_scale.gif" alt="Font scale plugin demo"/>

### Usage

```groovy
dependencies {
    debugImplementation 'pl.droidsonroids.foqa:fontscale:<latest version>'
}
```

```kotlin
dependencies {
    debugImplementation("pl.droidsonroids.foqa:fontscale:<latest version>")
}
```

Latest version number: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/pl.droidsonroids.foqa/fontscale/badge.svg)](https://maven-badges.herokuapp.com/maven-central/pl.droidsonroids.foqa/fontscale)

### Chucker plugin

Allows to quickly launch [Chucker](https://github.com/ChuckerTeam/chucker).
Based on [Hyperion Chuck](https://github.com/Commit451/Hyperion-Chuck)

### Usage

```groovy
dependencies {
    debugImplementation 'pl.droidsonroids.foqa:chucker:<latest version>'
}
```

```kotlin
dependencies {
    debugImplementation("pl.droidsonroids.foqa:chucker:<latest version>")
}
```

Latest version number: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/pl.droidsonroids.foqa/chucker/badge.svg)](https://maven-badges.herokuapp.com/maven-central/pl.droidsonroids.foqa/chucker)

## License

[MIT License](/LICENSE)
