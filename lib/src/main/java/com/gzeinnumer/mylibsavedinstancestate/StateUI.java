package com.gzeinnumer.mylibsavedinstancestate;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StateUI {
    public static final String TAG = "State_UI";

    private String clss;
    private final Gson gson;
    private final HashMap<Object, String> data;
    private final SharedPreferences pref;
    private SharedPreferences.Editor mEditor;
    private boolean isClear = false;

    public StateUI(Context context) {
        String PREF_NAME = "gzeinnumer_save_state";
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        data = new HashMap<>();
    }

    public String getId() {
        return pref.getString(clss, null);
    }

    public void clearState() {
        isClear = true;
        try {
            mEditor = pref.edit();
            mEditor.remove(clss).apply();
            mEditor.putString(clss, null).apply();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addViewState(String clss) {
        this.clss = clss;
    }

    public void addView(Object object, String value) {

        data.put(String.valueOf(object), value);
    }

    public String getValue(Object object) {
        try {
            String str = pref.getString(clss, null);
            java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {
            }.getType();
            HashMap<String, String> testHashMap2 = gson.fromJson(str, type);
            return testHashMap2.get(String.valueOf(object));
        } catch (Exception e) {
            return null;
        }
    }

    public void saveState() {
        if (!isClear){
            mEditor = pref.edit();
            mEditor.putString(clss, gson.toJson(data)).apply();
        }
    }

    public boolean getState() {
        return pref.getString(clss, null) != null;
    }
}