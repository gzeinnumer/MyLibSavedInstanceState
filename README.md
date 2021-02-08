<p align="center">
  <img src="https://cdn.wallpapersafari.com/9/40/TP9KsW.jpg"/>
</p>

<h1 align="center">
    MyLibSavedInstanceState
</h1>

<p align="center">
    <a><img src="https://img.shields.io/badge/Version-1.0.2-brightgreen.svg?style=flat"></a>
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

  //required for example
  implementation 'com.github.gzeinnumer:MyLibRecyclerViewAdapterBuilder:1.3.1'
}
```

---
# Feature List
- [x] [Activity StateUI](#activity-stateui)
- [x] [Fragment StateUI](#fragment-stateui)
- [x] [Recyclerview StateUI](#recyclerview-stateui)
- [x] [Image StateUI](#image-stateui)
- [x] [Variable With StateUI](#variable-with-stateui)
- [x] [Clear Value StateUI onStop](#clear-value-stateui-onstop)
- [x] [Clear All StateUI](#clear-all-stateui)

---
# Tech stack and 3rd library
- [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences?hl=id)
- [Hashmap](https://developer.android.com/reference/java/util/HashMap)

---
# Usage

Save UI State like `SavedInstanceState` with `Bundle`. but with this library, you can keep data/value in View as long as you want,
or you can **Clear Cache** from your app in `Application Settings`. State will keep save even when you `kill` your app process.

#
### Activity StateUI
#### Make instance from StateUI
```java
public class MainActivity extends AppCompatActivity {

    private StateUI stateUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ...

        stateUI = StateUIBuilder.Build(MainActivity.class, getApplicationContext());
    }
}
```
#
#### Add Value To Keep On StateUI

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
//login and clear last value on form by onClick
binding.btnLogin.setOnClickListener(v -> {
    stateUI.clearState();
    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
});
```

Here is Full Code
[MainActivity.java](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/app/src/main/java/com/gzeinnumer/mylibsavedinstancestate/activity/MainActivity.java)
 & [activity_main.xml](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/app/src/main/res/layout/activity_main.xml)

Preview:

|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example1.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example2.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example3.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example4.gif)|
|---|---|---|---|
|**(Before)** Data lost in `onBackPressed()`|**(Before)** Data lost in `onDestroy()`|**(After)** Data keep in `onBackPressed()`|**(After)** Data keep in `onDestroy()`|

#
### Fragment StateUI

Seems like [Activity StateUI](#activity-stateui). but you need to use `onViewCreated` and `Override` `public onPauseonResume()` and `public onResume()`.
```java
public class HomeFragment extends Fragment {

    private StateUI stateUI;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ...

        stateUI = StateUIBuilder.Build(HomeFragment.class, requireContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        stateUI.addView("binding.edUsername", binding.edUsername.getText().toString());
        stateUI.addView("binding.edPass", binding.edPass.getText().toString());
        stateUI.saveState();
    }

    @Override
    public void onResume() {
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

Here is Full Code
[HomeFragment.java](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/app/src/main/java/com/gzeinnumer/mylibsavedinstancestate/fragment/HomeFragment.java)
 & [fragment_home.xml](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/app/src/main/res/layout/fragment_home.xml)

Preview:

|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example5.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example6.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example7.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example8.gif)|
|---|---|---|---|
|**(Before)** Data lost in `onBackPressed()`|**(Before)** Data lost in `onDestroy()`|**(After)** Data keep in `onBackPressed()`|**(After)** Data keep in `onDestroy()`|

#
### RecyclerView StateUI

You can save `List` to StateUI. Use function `addViewList(KEY, ListStateReceiver<MyModel>)` to set value and `getValueList(KEY, ListStateCallBack<MyModel>)` to get value.

```java
public class RecyclerViewActivity extends AppCompatActivity {
    private List<MyModel> list = new ArrayList<>();
    private StateUI stateUI;

    ...

    @Override
    protected void onPause() {
        super.onPause();
        stateUI.addViewList("binding.rv", new ListStateReceiver<MyModel>() {
            @Override
            public List<MyModel> listReceived() {
                return list;
            }
        });
        stateUI.saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stateUI.getState()) {
            stateUI.getValueList("binding.rv", new ListStateCallBack<MyModel>() {
                @Override
                public Type setListModel() {
                    // need converter for Gson, put your ModelPojo on TypeToken
                    return new TypeToken<List<MyModel>>(){}.getType();
                }

                @Override
                public void listCallBack(List<MyModel> listFromState) {
                    list = new ArrayList<>(listFromState);
                    initAdapter();
                }
            });
        }
    }
}
```

Here is Full Code
[RecyclerViewActivity.java](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/app/src/main/java/com/gzeinnumer/mylibsavedinstancestate/recyclerView/RecyclerViewActivity.java)
 & [MyModel.java](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/app/src/main/java/com/gzeinnumer/mylibsavedinstancestate/recyclerView/MyModel.java)
 & [activity_recycler_view.xml](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/app/src/main/res/layout/activity_recycler_view.xml)

Preview:

|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example13.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example14.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example15.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example16.gif)|
|---|---|---|---|
|**(Before)** Data lost in `onBackPressed()` |**(Before)** Data lost in `onDestroy()`|**(After)** Data keep in `onBackPressed()`|**(After)** Data keep in `onDestroy()`|

#
### Image StateUI

#### Save As Bitmap
You can save `Bitmap` from your `ImageView`. Use function `addViewBitmap()` to set value and `getValueBitmap()` to get value.
```java
public class ImageActivity extends AppCompatActivity {

    private StateUI stateUI;

    ...

    @Override
    protected void onPause() {
        super.onPause();
        try {
            stateUI.addViewBitmap("binding.img", (BitmapDrawable) binding.img.getDrawable());
        } catch (Exception e) {
            e.printStackTrace();
        }
        stateUI.saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stateUI.getState()) {
            Bitmap bitmap = stateUI.getValueBitmap("binding.img");
            if (bitmap != null) {
                binding.img.setImageBitmap(bitmap);
            }
        }
    }

    ...
}
```

#### Save As Path
You can save `Path` from your `ImageView`. Use function `addViewPath()` to set value and `getValuePath()` to get value.

**Required Permission Storage**
```java
public class ImageActivity extends AppCompatActivity {

    private StateUI stateUI;

    ...

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        ...

        //take image with camera or explisit intent
        String path = "/storage/emulated/0/ExternalFolder/Foto/JPEG_FILE_NAME.jpg";
        stateUI.addViewPath("binding.img", path);

        ...
    }

    @Override
    protected void onPause() {
        super.onPause();

        stateUI.saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (stateUI.getState()) {
            String path = stateUI.getValuePath("binding.img");
            if (path != null){
                Glide.with(this).load(new File(path)).error(R.mipmap.ic_launcher).into(binding.img);
            }
        }
    }

    ...
}
```

Here is Full Code
[ImageActivity.java](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/app/src/main/java/com/gzeinnumer/mylibsavedinstancestate/image/ImageActivity.java)
 & [activity_image.xml](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/app/src/main/res/layout/activity_image.xml)

Preview:

|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example9.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example10.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example11.gif)|![](https://github.com/gzeinnumer/MyLibSavedInstanceState/blob/master/preview/example12.gif)|
|---|---|---|---|
|**(Before)** Data lost in `onBackPressed()`|**(Before)** Data lost in `onDestroy()`|**(After)** Data keep in `onBackPressed()`|**(After)** Data keep in `onDestroy()`|

#
### Variable With StateUI

Variable
```java
//onPause()
String data = "sentThisDataToState";
stateUI.addView("data", data);

//onResume()
String data = stateUI.getValue("data");
```
ViewModel
```java
//onPause()
stateUI.addView("vm.data.getValue()", vm.data.getValue());

//onResume()
String data = stateUI.getValue("vm.data.getValue()");
vm.setData(data);
```

#
### Clear Value StateUI onStop

> **READ CAREFULLY!!! IF YOU USE THIS STEP. APP WILL KILL STATE-UI IN YOUR CURRENT ACTIVITY WHEN YOU CLOSE(NOT MINIMIZE) YOUR APP, ONLY ON CURRENT ACTIVITY**

You can clear StateUI if you `WONT` keep it when app killed, put `stateUI.clearState()` on function `onStop()`.
```java
//ignore this if you want to keep value even when your app Killed
@Override
protected void onStop() {
    super.onStop();
    stateUI.clearState();
}
```

#
### Clear All StateUI

Call this function every where, this function will Clear All Of Your StateUI that you have been save before, in every `Activity/Fragment`
```java
stateUI.destroyStateUI();
```

---
# Example Code/App

[Sample Code And App](https://github.com/gzeinnumer/MyLibSavedInstanceStateExample)

---
# Version
- **1.0.0**
  - First Release
- **1.0.1**
  - Clear All StateUI
- **1.0.2**
  - Image

---
# Contribution
You can sent your constibution to `branch` `open-pull`.

---

```
Copyright 2021 M. Fadli Zein
```
