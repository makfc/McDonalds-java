package com.mcdonalds.sdk.services.analytics.conversionmaster;

import android.content.Context;
import com.admaster.square.api.ConvMobiSDK;

public class LoginAction implements Action {
    private String mUserId;

    public LoginAction(String userId) {
        this.mUserId = userId;
    }

    public void doAction(Context c) {
        ConvMobiSDK.doLoginEvent(this.mUserId, 0);
    }
}
