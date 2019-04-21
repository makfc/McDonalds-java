package com.crashlytics.android.answers;

import java.io.IOException;
import p041io.fabric.sdk.android.services.settings.AnalyticsSettingsData;

class DisabledSessionAnalyticsManagerStrategy implements SessionAnalyticsManagerStrategy {
    DisabledSessionAnalyticsManagerStrategy() {
    }

    public void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData, String protocolAndHostOverride) {
    }

    public void processEvent(Builder builder) {
    }

    public void sendEvents() {
    }

    public void deleteAllEvents() {
    }

    public boolean rollFileOver() throws IOException {
        return false;
    }

    public void cancelTimeBasedFileRollOver() {
    }
}
