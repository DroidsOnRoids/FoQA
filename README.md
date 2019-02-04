# FoQA

Container for various Quality Assurance utilities to be included in QA/testing variants of Android apps.

## Description

Integrate various Quality Assurance tools into Android application by adding just one line to `build.gradle`.

## Usage

Add FoQA dependency to `build.gradle` of the application module:

```groovy
dependencies {
    debugImplementation 'pl.droidsonroids:foqa:0.0.1'
}
```

If not already present add Maven Central repository:
```groovy
repositories {
    mavenCentral()
}
```

## Contents

- [Hyperion](https://github.com/willowtreeapps/Hyperion-Android) with plugins
- [Android Debug Database](https://github.com/amitshekhariitbhu/Android-Debug-Database)

See [build.gradle](library/build.gradle) for a complete list of components. And [build.gradle](build.gradle) for versions.

## License

MIT License