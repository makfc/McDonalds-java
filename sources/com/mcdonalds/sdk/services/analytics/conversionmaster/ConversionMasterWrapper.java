package com.mcdonalds.sdk.services.analytics.conversionmaster;

import android.content.Context;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.AnalyticsWrapper;

public class ConversionMasterWrapper extends AnalyticsWrapper {
    public static final String CONFIG_KEY = "ConversionMaster";
    private static final String TAG = "ConversionMasterWrapper";

    public ConversionMasterWrapper(Context context) {
        super(context);
    }

    public void initialize() {
    }

    public synchronized void log(AnalyticType event, AnalyticsArgs args) {
        switch (event) {
            case Custom:
            case Event:
                handleEvent(args);
                break;
        }
    }

    private void handleEvent(AnalyticsArgs args) {
        Object obj = args.get(AnalyticsArgs.DATAKEY_CONVERSION_MASTER);
        if (obj != null) {
            ((Action) obj).doAction(this.mContext);
        }
    }
}
