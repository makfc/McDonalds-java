package com.facebook.stetho.inspector;

public class MismatchedResponseException extends MessageHandlingException {
    public long mRequestId;

    public MismatchedResponseException(long requestId) {
        super("Response for request id " + requestId + ", but no such request is pending");
        this.mRequestId = requestId;
    }

    public long getRequestId() {
        return this.mRequestId;
    }
}
