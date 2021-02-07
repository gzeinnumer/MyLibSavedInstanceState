<p align="center">
  <img src="https://cdn.wallpapersafari.com/9/40/TP9KsW.jpg"/>
</p>

<h1 align="center">
    MyLibSavedInstanceState
</h1>

<p align="center">
    <a><img src="https://img.shields.io/badge/Version-1.0.0-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/ID-gzeinnumer-blue.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Java-Suport-green?logo=java&style=flat"></a>
    <a><img src="https://img.shields.io/badge/kotlin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/gzeinnumer"><img src="https://img.shields.io/github/followers/gzeinnumer?label=follow&style=social"></a>
    <br>
    <p>Simple Save State Instance.</p>
</p>

---
# Content List
* [Download](#download)
* [Feature List](#feature-list)
* [Tech stack and 3rd library](#tech-stack-and-3rd-library)
* [Usage](#usage)
* [Example Code/App](#example-codeapp)
* [Version](#version)
* [Contribution](#contribution)

---
# Download
Add maven `jitpack.io` and `dependencies` in `build.gradle (Project)` :
```gradle
// build.gradle project
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

// build.gradle app/module
dependencies {
  ...
  implementation 'com.github.gzeinnumer:MyLibSavedInstanceState:version'
}
```

---
# Feature List
- [x] []()

---
# Tech stack and 3rd library
- [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences?hl=id)
- [Hashmap](https://developer.android.com/reference/java/util/HashMap)

---
# Usage

### **File To Base64.**
File Image From Path and convert to `Base64` with format `data:image/jpeg;base64,` + `....kagsfkajha`
> **Java**
```java
String filePath = "/storage/emulated/0/YourFolder/file_image.jpg";
val result_9 = MBBase64.convertToBase64FromPath(filePath);
Log.d(TAG, "onCreate_9: "+ result_8); //   data:image/jpeg;base64,kasgfkaghaksfakgshalgal
```
> **Kotlin**
```kotlin
```

|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example1.gif)|
|---|

---
# Example Code/App

[]()

[Sample Code And App](https://github.com/gzeinnumer/MyLibSavedInstanceStateExample)

---
# Version
- **1.0.0**
  - First Release

---
# Contribution
You can sent your constibution to `branch` `open-pull`.

---

```
Copyright 2021 M. Fadli Zein
```
