package com.crashlytics.android.answers;

import android.content.Context;
import android.os.Looper;
import java.io.IOException;
import p041io.fabric.sdk.android.services.common.SystemCurrentTimeProvider;
import p041io.fabric.sdk.android.services.events.GZIPQueueFileEventStorage;
import p041io.fabric.sdk.android.services.persistence.FileStore;

class AnswersFilesManagerProvider {
    final Context context;
    final FileStore fileStore;

    public AnswersFilesManagerProvider(Context context, FileStore fileStore) {
        this.context = context;
        this.fileStore = fileStore;
    }

    public SessionAnalyticsFilesManager getAnalyticsFilesManager() throws IOException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("AnswersFilesManagerProvider cannot be called on the main thread");
        }
        return new SessionAnalyticsFilesManager(this.context, new SessionEventTransform(), new SystemCurrentTimeProvider(), new GZIPQueueFileEventStorage(this.context, this.fileStore.getFilesDir(), "session_analytics.tap", "session_analytics_to_send"));
    }
}
