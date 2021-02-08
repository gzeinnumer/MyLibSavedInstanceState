package com.gzeinnumer.mylibsavedinstancestate;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class StateUI {
    private final String clss;
    private final Gson gson;
    private final HashMap<Object, String> data;
    private final SharedPreferences pref;
    private SharedPreferences.Editor mEditor;
    private boolean isClear = false;

    private final StateImageUI stateImageUI;

    public StateUI(Context context, String clss) {
        String PREF_NAME = "gzeinnumer_save_state";
        this.pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
        this.data = new HashMap<>();
        this.clss = clss;
        this.stateImageUI = new StateImageUI(context);
    }

    /**
     * Use this method in onPause() to add view that you want to keep in StateUI
     *
     * @param objectName name of variable in String as KEY to get value in function onResume().
     * @param value      value that you want to save with Integer type.
     */
    public void addView(Object objectName, int value) {
        addView(objectName, String.valueOf(value));
    }


    /**
     * Use this method in onPause() to add view that you want to keep in StateUI
     *
     * @param objectName name of variable in String as KEY to get value in function onResume().
     * @param value      value that you want to save with float type.
     */
    public void addView(Object objectName, float value) {
        addView(objectName, String.valueOf(value));
    }

    /**
     * Use this method in onPause() to add view that you want to keep in StateUI
     *
     * @param objectName name of variable in String as KEY to get value in function onResume().
     * @param value      value that you want to save with double type.
     */
    public void addView(Object objectName, double value) {
        addView(objectName, String.valueOf(value));
    }

    /**
     * Use this method in onPause() to add view that you want to keep in StateUI
     *
     * @param objectName name of variable in String as KEY to get value in function onResume().
     * @param value      value that you want to save with String type.
     */
    public void addView(Object objectName, String value) {
        data.put(String.valueOf(objectName), value);
    }

    /**
     * Use this method in onPause() to add view that you want to keep in StateUI
     * Note : put this function inside Try Catch
     *
     * @param objectName name of variable in String as KEY to get value in function onResume().
     * @param value      value that you want to save with BitmapDrawable type.
     *                   <p>
     *                   if you never change preview of your ImageView catch will triggered
     */
    public void addViewBitmap(Object objectName, BitmapDrawable value) throws Exception {
        Bitmap bitmap = value.getBitmap();
//        this.data.put(String.valueOf(objectName), bitMapToString(bitmap));
        this.stateImageUI.setImage(String.valueOf(objectName), bitMapToString(bitmap));
    }

    /**
     * Use this method in onPause() to add view that you want to keep in StateUI
     *
     * @param objectName        name of variable in String as KEY to get value in function onResume().
     * @param listStateReceiver value that you want to save with List<T> type.
     *                          <p>
     *                          if you never change preview of your ImageView catch will triggered
     */
    public <T> void addViewList(Object objectName, ListStateReceiver<T> listStateReceiver) {
        List<T> d = listStateReceiver.listReceived();
        String value = this.gson.toJson(d);
        data.put(String.valueOf(objectName), value);
    }

    /**
     * Use this method in onPause() to save all view in StateUI storage
     * note : after you use method clearState(), this method wont work even when onPause() called
     */
    public void saveState() {
        if (!isClear) {
            mEditor = pref.edit();
            mEditor.putString(clss, gson.toJson(data)).apply();
        }
    }

    /**
     * Use this method to check is your StateUI exists
     *
     * @return false if your not set addView() after StateUIBuilder
     */
    public boolean getState() {
        return pref.getString(clss, null) != null;
    }

    /**
     * Use this method in onResume() to get value that you set before
     *
     * @param objectName name of variable in String as KEY to get value from function onPause().
     * @return String
     */
    public String getValue(Object objectName) {
        try {
            String str = this.pref.getString(this.clss, null);
            java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {
            }.getType();
            HashMap<String, String> testHashMap2 = this.gson.fromJson(str, type);
            return testHashMap2.get(String.valueOf(objectName));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * <p>Use this method in onResume() to get value that you set from RecyclerView with List<T> type</p>
     * <p></p>
     * <p>setListModel(){ </p>
     * <p>&nbsp;&nbsp;&nbsp;return new TypeToken&#60;List&#60;ModelPojo&#62;&#62;(){}.getType();</p>
     * <p>}</p>
     * <p>listCallBack() to get List from StateUI.</p>
     *
     * @param objectName        name of variable in String as KEY to get value from function onPause().
     * @param listStateCallBack use this callBack to set your ModelPojo and getList
     *
     */
    public <T> void getValueList(Object objectName, ListStateCallBack<T> listStateCallBack) {
        try {
            String str = pref.getString(clss, null);
            java.lang.reflect.Type typeMap = new TypeToken<HashMap<String, String>>() {
            }.getType();
            HashMap<String, String> testHashMap2 = gson.fromJson(str, typeMap);
            String value = testHashMap2.get(String.valueOf(objectName));
            Type type = listStateCallBack.setListModel();
            List<T> list = gson.fromJson(value, type);
            listStateCallBack.listCallBack(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Use this method to add view that you want to keep value from RecyclerView with List<T> type
     *
     * @param objectName name of variable in String as KEY to get value from function onPause().
     * @return BitMap if Success / null if you never change ImageView preview.
     */
    public Bitmap getValueBitmap(Object objectName) {
//        try {
//            String str = pref.getString(clss, null);
//            java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {
//            }.getType();
//            HashMap<String, String> testHashMap2 = gson.fromJson(str, type);
//            return stringToBitMap(testHashMap2.get(String.valueOf(objectName)));
//        } catch (Exception e) {
//            return null;
//        }
        return stringToBitMap(this.stateImageUI.getImage(String.valueOf(objectName)));
    }

    /**
     * Use this method to clear all StateUI from your view
     */
    public void clearState() {
        isClear = true;
        try {
            mEditor = pref.edit();
            mEditor.remove(clss).apply();
            mEditor.putString(clss, null).apply();
            stateImageUI.destroyStateUIImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Use this method to clear all StateUI in your app
     */
    public void destroyStateUI() {
        mEditor = pref.edit();
        mEditor.clear().apply();
        stateImageUI.destroyStateUIImage();
    }

    private String bitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    private Bitmap stringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}