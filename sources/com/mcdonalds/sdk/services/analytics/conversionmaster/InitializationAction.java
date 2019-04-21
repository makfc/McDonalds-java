package com.mcdonalds.sdk.services.analytics.conversionmaster;

import android.content.Context;
import com.admaster.square.api.ConvMobiSDK;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class InitializationAction implements Action {
    private static final String APP_ID = "analytics.ConversionMaster.appId";
    private static final String CHANNEL_ID = "analytics.ConversionMaster.channelID";
    private static final String CHANNEL_ID_VALUES = "analytics.ConversionMaster.channelIDValues.";

    public void doAction(Context c) {
        Configuration cfg = Configuration.getSharedInstance();
        String m2id = cfg.getStringForKey(APP_ID);
        String choice = cfg.getStringForKey(CHANNEL_ID);
        String chId = null;
        if (!(choice == null || choice.isEmpty())) {
            chId = cfg.getStringForKey(CHANNEL_ID_VALUES + choice);
        }
        if (chId == null || chId.isEmpty()) {
            ConvMobiSDK.initial(c, m2id);
        } else {
            ConvMobiSDK.initial(c, m2id, chId);
        }
    }
}
