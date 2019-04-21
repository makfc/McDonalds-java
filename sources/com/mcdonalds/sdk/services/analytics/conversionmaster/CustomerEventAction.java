package com.mcdonalds.sdk.services.analytics.conversionmaster;

import android.content.Context;
import com.admaster.square.api.ConvMobiSDK;
import com.admaster.square.api.CustomEvent;

public class CustomerEventAction implements Action {
    private CustomEvent mCustomEvent;

    public CustomerEventAction(CustomEvent e) {
        this.mCustomEvent = e;
    }

    public void doAction(Context c) {
        ConvMobiSDK.doCustomerEvent(this.mCustomEvent, 0);
    }
}
