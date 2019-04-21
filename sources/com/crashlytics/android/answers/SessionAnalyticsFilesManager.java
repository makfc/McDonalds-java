package com.crashlytics.android.answers;

import android.content.Context;
import java.io.IOException;
import java.util.UUID;
import p041io.fabric.sdk.android.services.common.CurrentTimeProvider;
import p041io.fabric.sdk.android.services.events.EventsFilesManager;
import p041io.fabric.sdk.android.services.events.EventsStorage;
import p041io.fabric.sdk.android.services.settings.AnalyticsSettingsData;

class SessionAnalyticsFilesManager extends EventsFilesManager<SessionEvent> {
    private AnalyticsSettingsData analyticsSettingsData;

    SessionAnalyticsFilesManager(Context context, SessionEventTransform transform, CurrentTimeProvider currentTimeProvider, EventsStorage eventStorage) throws IOException {
        super(context, transform, currentTimeProvider, eventStorage, 100);
    }

    /* Access modifiers changed, original: protected */
    public String generateUniqueRollOverFileName() {
        return "sa" + "_" + UUID.randomUUID().toString() + "_" + this.currentTimeProvider.getCurrentTimeMillis() + ".tap";
    }

    /* Access modifiers changed, original: protected */
    public int getMaxFilesToKeep() {
        return this.analyticsSettingsData == null ? super.getMaxFilesToKeep() : this.analyticsSettingsData.maxPendingSendFileCount;
    }

    /* Access modifiers changed, original: protected */
    public int getMaxByteSizePerFile() {
        return this.analyticsSettingsData == null ? super.getMaxByteSizePerFile() : this.analyticsSettingsData.maxByteSizePerFile;
    }

    /* Access modifiers changed, original: 0000 */
    public void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData) {
        this.analyticsSettingsData = analyticsSettingsData;
    }
}
