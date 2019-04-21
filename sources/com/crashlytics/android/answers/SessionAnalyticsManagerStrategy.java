package com.crashlytics.android.answers;

import p041io.fabric.sdk.android.services.events.FileRollOverManager;
import p041io.fabric.sdk.android.services.settings.AnalyticsSettingsData;

interface SessionAnalyticsManagerStrategy extends FileRollOverManager {
    void deleteAllEvents();

    void processEvent(Builder builder);

    void sendEvents();

    void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData, String str);
}
