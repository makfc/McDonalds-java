package com.mcdonalds.app.util;

import android.databinding.Bindable;
import android.databinding.Observable;

public interface ProductDetailsItemPresenter extends Observable {
    @Bindable
    boolean getChecked();

    @Bindable
    int getHatButtonResourceId();

    @Bindable
    String getNameDetails();

    @Bindable
    String getProductName();

    @Bindable
    String getProductUplift();

    @Bindable
    boolean getShowCheckBox();

    @Bindable
    boolean getShowDisclosureArrow();

    @Bindable
    boolean getShowHatButton();

    @Bindable
    boolean getShowInfoButton();

    @Bindable
    boolean getShowNameDetails();

    @Bindable
    boolean getShowUplift();

    @Bindable
    String getSpecialInstructions();

    @Bindable
    String getThumbnailImageUrl();
}
