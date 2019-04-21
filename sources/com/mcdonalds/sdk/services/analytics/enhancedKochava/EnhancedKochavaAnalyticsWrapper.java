package com.mcdonalds.sdk.services.analytics.enhancedKochava;

import android.content.Context;
import android.text.TextUtils;
import com.kochava.base.Tracker;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.AnalyticsWrapper;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONObject;

public class EnhancedKochavaAnalyticsWrapper extends AnalyticsWrapper {
    public static final String CONFIG_KEY = "EnhancedKochava";
    public static final String KEY_ENABLED = "analytics.EnhancedKochava.enabled";
    private static final String KEY_MARKET_ID = "analytics.EnhancedKochava.marketId";
    private static final String KEY_NULL = "null";
    private static final String KEY_PARAMS_DICTIONARY_APP_ID = "analytics.EnhancedKochava.paramsDictionary.kochavaAppId";

    public EnhancedKochavaAnalyticsWrapper(Context context) {
        super(context);
        this.mContext = context;
    }

    public static String getMarketId() {
        return Configuration.getSharedInstance().getStringForKey(KEY_MARKET_ID);
    }

    public void initialize() {
        Tracker.configure(new Tracker.Configuration(this.mContext).setAppGuid((String) Configuration.getSharedInstance().getValueForKey(KEY_PARAMS_DICTIONARY_APP_ID)).setLogLevel(3));
    }

    public void log(AnalyticType event, AnalyticsArgs args) {
        String eventName = (String) args.remove(AnalyticsArgs.DATAKEY_LABEL);
        String eventValue = String.valueOf(args.get(AnalyticsArgs.DATAKEY_VALUE));
        if (TextUtils.isEmpty(eventValue) || eventValue.equals("null")) {
            if (args.isEmpty()) {
                eventValue = "";
            } else {
                JSONObject json = new JSONObject(args);
                eventValue = !(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json);
            }
        }
        if (!TextUtils.isEmpty(eventName) && !eventName.equals("null")) {
            Tracker.sendEvent(eventName, eventValue);
        }
    }
}
