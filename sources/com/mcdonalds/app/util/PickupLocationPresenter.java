package com.mcdonalds.app.util;

import android.databinding.Bindable;
import android.databinding.Observable;

public interface PickupLocationPresenter extends Observable {
    @Bindable
    String getStoreName();
}
