package com.mcdonalds.app.util;

import android.databinding.Bindable;

public interface ProductQuantityControlsPresenter extends TotalCaloriePricePresenter {
    @Bindable
    boolean getEnableMinusButton();

    @Bindable
    boolean getEnablePlusButton();

    @Bindable
    String getQuantity();
}
