package com.mcdonalds.app.util;

import android.databinding.Bindable;
import android.databinding.Observable;

public interface InvoicePresenter extends Observable {
    @Bindable
    boolean getInvoiceEnabled();

    @Bindable
    String getPayer();

    @Bindable
    void setInvoiceEnabled(boolean z);

    @Bindable
    void setPayer(String str);
}
