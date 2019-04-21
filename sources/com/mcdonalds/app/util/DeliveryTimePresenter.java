package com.mcdonalds.app.util;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.text.Spanned;

public interface DeliveryTimePresenter extends Observable {
    @Bindable
    Spanned getDeliveryHeaderText();
}
