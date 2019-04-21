package com.mcdonalds.sdk;

public class BlockingListener<T> implements AsyncListener<T> {
    AsyncException mException;
    T mResponse;

    public synchronized void onResponse(T response, AsyncToken token, AsyncException exception) {
        this.mResponse = response;
        this.mException = exception;
        notify();
    }

    public synchronized T getResponse() throws InterruptedException, AsyncException {
        wait();
        if (this.mException != null) {
            throw this.mException;
        }
        return this.mResponse;
    }
}
