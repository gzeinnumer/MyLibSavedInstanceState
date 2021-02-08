package com.gzeinnumer.mylibsavedinstancestate;

import android.content.Context;
import android.content.SharedPreferences;

public class StateImageUI {

    private final SharedPreferences pref;
    private SharedPreferences.Editor mEditor;

    public StateImageUI(Context context) {
        String PREF_NAME = "gzeinnumer_save_state_image";
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setImage(String key, String bitmap) {
        mEditor = pref.edit();
        mEditor.putString(key, bitmap).apply();
    }

    public String getImage(String objectName) {
        return this.pref.getString(objectName, null);
    }

    public void destroyStateUIImage() {
        mEditor = pref.edit();
        mEditor.clear().apply();
    }
}
