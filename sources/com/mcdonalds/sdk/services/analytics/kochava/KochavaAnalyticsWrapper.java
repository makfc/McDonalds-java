package com.mcdonalds.sdk.services.analytics.kochava;

import android.content.Context;
import android.util.Log;
import com.kochava.base.Tracker;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.AnalyticsWrapper;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class KochavaAnalyticsWrapper extends AnalyticsWrapper {
    public static final String APP_ID_KEY = "analytics.Kochava.appId";
    public static final String CONFIG_KEY = "Kochava";
    public static final String ENABLED_KEY = "analytics.Kochava.enabled";
    private boolean mInitialized = false;
    private final boolean mIsEnabled = Configuration.getSharedInstance().getBooleanForKey(ENABLED_KEY);

    public KochavaAnalyticsWrapper(Context context) {
        super(context);
    }

    public void initialize() {
        if (!this.mInitialized && this.mIsEnabled) {
            Tracker.configure(new Tracker.Configuration(this.mContext).setAppGuid((String) Configuration.getSharedInstance().getValueForKey(APP_ID_KEY)).setLogLevel(3));
            this.mInitialized = true;
        }
    }

    public void log(AnalyticType event, AnalyticsArgs args) {
        if (this.mIsEnabled) {
            switch (event) {
                case ScreenLoad:
                    logScreenLoad(args);
                    return;
                case Event:
                    logEvent(args);
                    return;
                case Transaction:
                    logTransaction(args);
                    return;
                default:
                    return;
            }
        }
    }

    private void logScreenLoad(AnalyticsArgs args) {
        Tracker.sendEvent("Screen", (String) args.get("ACTION"));
        Log.d(CONFIG_KEY, "Screen " + args.get("ACTION"));
    }

    private void logEvent(AnalyticsArgs args) {
        Map<String, Object> businessArgs = (Map) args.remove(AnalyticsArgs.DATAKEY_BUSINESS);
        JSONObject json = new JSONObject(args);
        Tracker.sendEvent("Event", !(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json));
        Log.d(CONFIG_KEY, "Event " + (!(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json)));
        logBusiness(businessArgs);
    }

    private void logTransaction(AnalyticsArgs args) {
        Map<String, Object> businessArgs = (Map) args.get(AnalyticsArgs.TRANSACTION_MAP);
        businessArgs.remove(AnalyticsArgs.TRANSACTION_PRODUCTS);
        JSONObject json = new JSONObject(businessArgs);
        Tracker.sendEvent("Transaction", !(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json));
        Log.d(CONFIG_KEY, "Transaction" + (!(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json)));
        String transactionId = (String) businessArgs.get(AnalyticsArgs.TRANSACTION_ID);
        for (Map<String, Object> prod : (List) args.get(AnalyticsArgs.PRODUCT_LIST)) {
            prod.put(AnalyticsArgs.TRANSACTION_ID, transactionId);
            prod.remove(AnalyticsArgs.DATAKEY_CATEGORY);
            json = new JSONObject(prod);
            Tracker.sendEvent("Product", !(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json));
            Log.d(CONFIG_KEY, "Product" + (!(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json)));
        }
    }

    private void logBusiness(Map<String, Object> args) {
        if (args != null) {
            String value;
            if (args.get(BusinessArgs.KEY_EVENT_VALUE) instanceof String) {
                value = (String) args.get(BusinessArgs.KEY_EVENT_VALUE);
            } else {
                JSONObject json = new JSONObject((Map) args.get(BusinessArgs.KEY_EVENT_VALUE));
                value = !(json instanceof JSONObject) ? json.toString() : JSONObjectInstrumentation.toString(json);
            }
            Tracker.sendEvent((String) args.get(BusinessArgs.KEY_EVENT_TITLE), value);
            Log.d(CONFIG_KEY, "Business: " + args.get(BusinessArgs.KEY_EVENT_TITLE) + " : " + value);
        }
    }
}
