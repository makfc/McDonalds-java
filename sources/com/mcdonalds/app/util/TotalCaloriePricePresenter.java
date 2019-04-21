package com.mcdonalds.app.util;

import android.databinding.Bindable;
import android.databinding.Observable;

public interface TotalCaloriePricePresenter extends Observable {
    @Bindable
    String getTotalPrice();
}
