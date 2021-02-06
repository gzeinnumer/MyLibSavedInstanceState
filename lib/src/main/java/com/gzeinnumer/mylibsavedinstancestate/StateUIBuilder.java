package com.gzeinnumer.mylibsavedinstancestate;

import android.content.Context;
import android.util.Log;

public class StateUIBuilder {
    public static final String TAG = "StateUIB_uilder";
    public static StateUI Build(Class<?> classActivity, Context applicationContext){
        StateUI stateUI = new StateUI(applicationContext);
        stateUI.addViewState(classActivity.getSimpleName());
        return stateUI;
    }

    public static StateUI Build(String classActivity, Context applicationContext){
        StateUI stateUI = new StateUI(applicationContext);
        stateUI.addViewState(classActivity);
        return stateUI;
    }
}
