package com.gzeinnumer.mylibsavedinstancestate;

import java.util.List;

public interface ListStateReceiver<T> {
    List<T> listReceived();
}
