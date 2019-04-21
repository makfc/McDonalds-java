package com.facebook;

import android.os.Handler;
import com.facebook.Request.Callback;
import com.facebook.Request.OnProgressCallback;

class RequestProgress {
    private final Handler callbackHandler;
    private long lastReportedProgress;
    private long maxProgress;
    private long progress;
    private final Request request;
    private final long threshold = Settings.getOnProgressThreshold();

    RequestProgress(Handler callbackHandler, Request request) {
        this.request = request;
        this.callbackHandler = callbackHandler;
    }

    /* Access modifiers changed, original: 0000 */
    public long getProgress() {
        return this.progress;
    }

    /* Access modifiers changed, original: 0000 */
    public long getMaxProgress() {
        return this.maxProgress;
    }

    /* Access modifiers changed, original: 0000 */
    public void addProgress(long size) {
        this.progress += size;
        if (this.progress >= this.lastReportedProgress + this.threshold || this.progress >= this.maxProgress) {
            reportProgress();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void addToMax(long size) {
        this.maxProgress += size;
    }

    /* Access modifiers changed, original: 0000 */
    public void reportProgress() {
        if (this.progress > this.lastReportedProgress) {
            Callback callback = this.request.getCallback();
            if (this.maxProgress > 0 && (callback instanceof OnProgressCallback)) {
                final long currentCopy = this.progress;
                final long maxProgressCopy = this.maxProgress;
                final OnProgressCallback callbackCopy = (OnProgressCallback) callback;
                if (this.callbackHandler == null) {
                    callbackCopy.onProgress(currentCopy, maxProgressCopy);
                } else {
                    this.callbackHandler.post(new Runnable() {
                        public void run() {
                            callbackCopy.onProgress(currentCopy, maxProgressCopy);
                        }
                    });
                }
                this.lastReportedProgress = this.progress;
            }
        }
    }
}
