package com.crashlytics.android.answers;

import java.io.File;
import java.util.List;
import p041io.fabric.sdk.android.services.concurrency.internal.DefaultRetryPolicy;
import p041io.fabric.sdk.android.services.concurrency.internal.ExponentialBackoff;
import p041io.fabric.sdk.android.services.concurrency.internal.RetryState;
import p041io.fabric.sdk.android.services.events.FilesSender;

class AnswersRetryFilesSender implements FilesSender {
    private final SessionAnalyticsFilesSender filesSender;
    private final RetryManager retryManager;

    public static AnswersRetryFilesSender build(SessionAnalyticsFilesSender filesSender) {
        return new AnswersRetryFilesSender(filesSender, new RetryManager(new RetryState(new RandomBackoff(new ExponentialBackoff(1000, 8), 0.1d), new DefaultRetryPolicy(5))));
    }

    AnswersRetryFilesSender(SessionAnalyticsFilesSender filesSender, RetryManager retryManager) {
        this.filesSender = filesSender;
        this.retryManager = retryManager;
    }

    public boolean send(List<File> files) {
        long currentNanoTime = System.nanoTime();
        if (!this.retryManager.canRetry(currentNanoTime)) {
            return false;
        }
        if (this.filesSender.send(files)) {
            this.retryManager.reset();
            return true;
        }
        this.retryManager.recordRetry(currentNanoTime);
        return false;
    }
}
