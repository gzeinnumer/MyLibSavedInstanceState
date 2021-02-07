package com.gzeinnumer.mylibsavedinstancestate;

import android.content.Context;

public class StateUIBuilder {
    public static StateUI Build(Class<?> classActivity, Context applicationContext){
        return new StateUI(applicationContext, classActivity.getSimpleName());
    }

    public static StateUI Build(String classActivity, Context applicationContext){
        return new StateUI(applicationContext, classActivity);
    }
}
