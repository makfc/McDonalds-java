package com.facebook.stetho.inspector.network;

import java.io.IOException;

public class DefaultResponseHandler implements ResponseHandler {
    private int mBytesRead = 0;
    private int mDecodedBytesRead = -1;
    private final NetworkEventReporter mEventReporter;
    private final String mRequestId;

    public DefaultResponseHandler(NetworkEventReporter eventReporter, String requestId) {
        this.mEventReporter = eventReporter;
        this.mRequestId = requestId;
    }

    public void onRead(int numBytes) {
        this.mBytesRead += numBytes;
    }

    public void onReadDecoded(int numBytes) {
        if (this.mDecodedBytesRead == -1) {
            this.mDecodedBytesRead = 0;
        }
        this.mDecodedBytesRead += numBytes;
    }

    public void onEOF() {
        reportDataReceived();
        this.mEventReporter.responseReadFinished(this.mRequestId);
    }

    public void onError(IOException e) {
        reportDataReceived();
        this.mEventReporter.responseReadFailed(this.mRequestId, e.toString());
    }

    private void reportDataReceived() {
        this.mEventReporter.dataReceived(this.mRequestId, this.mBytesRead, this.mDecodedBytesRead >= 0 ? this.mDecodedBytesRead : this.mBytesRead);
    }
}
