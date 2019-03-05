# FoQA

<img src="seal.svg" alt="Logo" width="200"/>

Container for various Quality Assurance utilities to be included in QA/testing variants of Android apps.

## Description

Integrate various Quality Assurance tools into Android application by adding just one line to `build.gradle`.

## Usage

Add FoQA dependency to `build.gradle` of the application module:

```groovy
dependencies {
    debugImplementation 'com.github.droidsonroids:foqa:0.0.4'
    debugImplementation 'com.github.droidsonroids:foqa:0.0.6'
}
```

If not already present add Maven Central repository:
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```

## Contents

- [Hyperion](https://github.com/willowtreeapps/Hyperion-Android) with core plugins
- [Hyperion Chuck](https://github.com/Commit451/Hyperion-Chuck)
- [Hyperion App Info](https://github.com/willowtreeapps/Hyperion-Android)
- [Android Debug Database](https://github.com/amitshekhariitbhu/Android-Debug-Database)
- Hyperion menu item with Device market name [DeviceInfoPlugin](device_info_plugin)
- Hyperion menu item with font scale changer [FontScalePlugin](font_scale_plugin)

See [build.gradle](library/build.gradle) for a complete list of components. And [build.gradle](build.gradle) for versions.

## License

MIT License