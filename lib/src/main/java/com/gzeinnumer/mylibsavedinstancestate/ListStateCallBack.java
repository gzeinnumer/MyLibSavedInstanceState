package com.gzeinnumer.mylibsavedinstancestate;

import java.lang.reflect.Type;
import java.util.List;

public interface ListStateCallBack<T> {
    Type setListModel();

    void listCallBack(List<T> listFromState);
}
