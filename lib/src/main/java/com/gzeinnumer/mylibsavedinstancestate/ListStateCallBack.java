package com.gzeinnumer.mylibsavedinstancestate;

import java.lang.reflect.Type;
import java.util.List;

public interface ListStateCallBack<T> {
    /**
     * Use this method set Type for Gson Converter
     *
     * @return new TypeToken<List<MyModel>>() {}.getType();
     */
    Type setListModel();

    /**
     * Use this method to teka back list that you have been save before
     * @param listFromState you list data from StateUI, put it on your List
     */
    void listCallBack(List<T> listFromState);
}
