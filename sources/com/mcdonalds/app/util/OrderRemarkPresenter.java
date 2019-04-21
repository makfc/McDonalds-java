package com.mcdonalds.app.util;

import android.databinding.Bindable;
import android.databinding.Observable;

public interface OrderRemarkPresenter extends Observable {
    @Bindable
    String getRemark();

    @Bindable
    boolean getRemarkEnabled();

    @Bindable
    void setRemark(String str);

    @Bindable
    void setRemarkEnabled(boolean z);
}
