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

Save UI State like `SavedInstanceState` with `Bundle`. but with this library, you can keep data/value in View as long as you want,
or you can **Clear Cache** from your app in `Application Settings`. State will keep save even when you `kill` your app process.

### Activity StateUI
#### Make instance from StateUI
> **Java**
```java
public class MainActivity extends AppCompatActivity {

    private StateUI stateUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...

        stateUI = StateUIBuilder.Build(MainActivity.class, getApplicationContext());

        ...
    }
}
```
#
#### Add Value To Keep

Use function `addView(KEY, VALUE)` on `onPause()` add value that you want to keep and use `saveState()` to submit your value. When `onPause()` called StateUI will keep your value.
```java
public class MainActivity extends AppCompatActivity {

    private StateUI stateUI;

    ...

    @Override
    protected void onPause() {
        super.onPause();

        stateUI.addView("binding.edUsername", binding.edUsername.getText().toString());
        stateUI.addView("binding.edPass", binding.edPass.getText().toString());
        stateUI.saveState();
    }
}
````
#
#### Get Value To Display Again

Use function `onResume()` to get value that you have been save before. Check value in `StateUI` is exists, if exists than get value with it's own `KEY` with `getValue(KEY)` and set value to your view again.
```java
public class MainActivity extends AppCompatActivity {

    private StateUI stateUI;

    ...

    @Override
    protected void onResume() {
        super.onResume();
        if (stateUI.getState()) {
            String userName = stateUI.getValue("binding.edUsername");
            binding.edUsername.setText(userName);
            String pass = stateUI.getValue("binding.edPass");
            binding.edPass.setText(pass);
        }
    }
}
```
#
#### Clear Value From StateUI

Clear your state when you no need it anymore with.
```java
stateUI.clearState();
```

Here is Full Code
[MainActivity.java](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/app/src/main/java/com/gzeinnumer/mylibsavedinstancestate/activity/MainActivity.java)
[activity_main.xml](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/app/src/main/res/layout/activity_main.xml)

Preview:

|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example1.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example2.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example3.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example4.gif)|
|---|---|---|---|
|Data lost in `onBackPressed()`|Data lost in `onDestroy()`|Data keep in `onBackPressed()`|Data keep in `onDestroy()`|

# Fragment
|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example5.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example6.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example7.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example8.gif)|
|---|---|---|---|
|Data lost in `onBackPressed()`|Data lost in `onDestroy()`|Data keep in `onBackPressed()`|Data keep in `onDestroy()`|

# Image
|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example9.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example10.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example11.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example12.gif)|
|---|---|---|---|
|Data lost in `onBackPressed()`|Data lost in `onDestroy()`|Data keep in `onBackPressed()`|Data keep in `onDestroy()`|

# RecyclerView
|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example13.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example14.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example15.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example16.gif)|
|---|---|---|---|
|Data lost in `onBackPressed()`|Data lost in `onDestroy()`|Data keep in `onBackPressed()`|Data keep in `onDestroy()`|

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
