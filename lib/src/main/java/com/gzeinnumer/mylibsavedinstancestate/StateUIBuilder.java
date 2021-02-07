package com.gzeinnumer.mylibsavedinstancestate;

import android.content.Context;

public class StateUIBuilder {

    /**
     * Use this method in onPause() to add view that you want to keep in StateUI
     *
     * @param classActivity      name of Activity/Fragment/Dialog it mush use unix name.
     * @param applicationContext context
     */
    public static StateUI Build(Class<?> classActivity, Context applicationContext) {
        return new StateUI(applicationContext, classActivity.getSimpleName());
    }

    /**
     * Use this method in onPause() to add view that you want to keep in StateUI
     *
     * @param classActivity      name of Activity/Fragment/Dialog it mush use unix name.
     * @param applicationContext context
     */
    public static StateUI Build(String classActivity, Context applicationContext) {
        return new StateUI(applicationContext, classActivity);
    }
}
