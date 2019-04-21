package com.facebook;

import android.os.Handler;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

class ProgressNoopOutputStream extends OutputStream implements RequestOutputStream {
    private int batchMax;
    private final Handler callbackHandler;
    private Request currentRequest;
    private RequestProgress currentRequestProgress;
    private final Map<Request, RequestProgress> progressMap = new HashMap();

    ProgressNoopOutputStream(Handler callbackHandler) {
        this.callbackHandler = callbackHandler;
    }

    public void setCurrentRequest(Request currentRequest) {
        this.currentRequest = currentRequest;
        this.currentRequestProgress = currentRequest != null ? (RequestProgress) this.progressMap.get(currentRequest) : null;
    }

    /* Access modifiers changed, original: 0000 */
    public int getMaxProgress() {
        return this.batchMax;
    }

    /* Access modifiers changed, original: 0000 */
    public Map<Request, RequestProgress> getProgressMap() {
        return this.progressMap;
    }

    /* Access modifiers changed, original: 0000 */
    public void addProgress(long size) {
        if (this.currentRequestProgress == null) {
            this.currentRequestProgress = new RequestProgress(this.callbackHandler, this.currentRequest);
            this.progressMap.put(this.currentRequest, this.currentRequestProgress);
        }
        this.currentRequestProgress.addToMax(size);
        this.batchMax = (int) (((long) this.batchMax) + size);
    }

    public void write(byte[] buffer) {
        addProgress((long) buffer.length);
    }

    public void write(byte[] buffer, int offset, int length) {
        addProgress((long) length);
    }

    public void write(int oneByte) {
        addProgress(1);
    }
}
