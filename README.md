# FoQA

Container for various Quality Assurance utilities to be included in QA/testing variants of Android apps.

## Description

Integrate various Quality Assurance tools into Android application by adding just one line to `build.gradle`.

## Usage

Add FoQA dependency to `build.gradle` of the application module:

```groovy
dependencies {
    debugImplementation 'com.github.droidsonroids:foqa:0.0.2'
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
- [Hyperion Simple Item](https://github.com/takahirom/Hyperion-Simple-Item)
- [Android Debug Database](https://github.com/amitshekhariitbhu/Android-Debug-Database)
- Custom Hyperion menu item with Device market name [DeviceInfoPlugin](library/src/main/java/pl/droidsonroids/foqa/DeviceInfoPlugin.java)

See [build.gradle](library/build.gradle) for a complete list of components. And [build.gradle](build.gradle) for versions.

## License

MIT License