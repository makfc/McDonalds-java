package com.mcdonalds.app.analytics.NewRelic;

import android.app.Application;
import com.ensighten.Ensighten;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.C2623NewRelic;

public class NewRelicWrapper {
    public static void startNewRelic(Application application) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.analytics.NewRelic.NewRelicWrapper", "startNewRelic", new Object[]{application});
        String appToken = (String) Configuration.getSharedInstance().getValueForKey("analytics.NewRelic.appToken");
        if (appToken != null) {
            String newRelicTag = "";
            if (Configuration.getSharedInstance().hasKey("connectors.Middleware.marketId")) {
                newRelicTag = (String) Configuration.getSharedInstance().getValueForKey("connectors.Middleware.marketId");
            }
            C2623NewRelic.withApplicationToken(appToken).withBuildIdentifier(newRelicTag + "4.8.10").start(application);
        }
    }
}
