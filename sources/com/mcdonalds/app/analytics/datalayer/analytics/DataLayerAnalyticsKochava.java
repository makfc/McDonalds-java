package com.mcdonalds.app.analytics.datalayer.analytics;

import android.content.Context;
import com.ensighten.Ensighten;
import com.kochava.base.Tracker;
import com.kochava.base.Tracker.Event;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.Map;
import org.json.JSONObject;

public class DataLayerAnalyticsKochava implements DataLayerAnalytics {
    public DataLayerAnalyticsKochava(Context context, Configuration configuration) {
        String appId = configuration.getStringForKey("analytics.DataLayer.kochavaParamsDictionary.kochavaAppId");
        boolean limitAdTracking = shouldLimitAdTracking(configuration);
        Tracker.configure(new Tracker.Configuration(context.getApplicationContext()).setAppGuid(appId).setAppLimitAdTracking(limitAdTracking).setLogLevel(getLogLevel(configuration)));
    }

    public void log(String eventName, Map<String, Object> eventValue) {
        Ensighten.evaluateEvent(this, "log", new Object[]{eventName, eventValue});
        Tracker.sendEvent(new Event(eventName).addCustom(new JSONObject(eventValue)));
    }

    private boolean shouldLimitAdTracking(Configuration configuration) {
        Ensighten.evaluateEvent(this, "shouldLimitAdTracking", new Object[]{configuration});
        return "1".equals(configuration.getStringForKey("analytics.DataLayer.kochavaParamsDictionary.limitAdTracking"));
    }

    private int getLogLevel(Configuration configuration) {
        Ensighten.evaluateEvent(this, "getLogLevel", new Object[]{configuration});
        int result = 0;
        try {
            return Integer.parseInt(configuration.getStringForKey("analytics.DataLayer.kochavaParamsDictionary.enableLogging"));
        } catch (NumberFormatException e) {
            return result;
        }
    }
}
