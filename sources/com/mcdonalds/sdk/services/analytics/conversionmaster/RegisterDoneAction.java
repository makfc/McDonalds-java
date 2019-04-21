package com.mcdonalds.sdk.services.analytics.conversionmaster;

import android.content.Context;
import com.admaster.square.api.ConvMobiSDK;

public class RegisterDoneAction implements Action {
    public void doAction(Context c) {
        ConvMobiSDK.doRegisterEvent("no valid username at this point", 0);
    }
}
