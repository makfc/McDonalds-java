package com.mcdonalds.sdk.services.error;

import com.mcdonalds.sdk.AsyncException;

public class ErrorManager {
    private static ErrorManager instance;
    private AsyncException lastError;

    public static ErrorManager getInstance() {
        if (instance == null) {
            instance = new ErrorManager();
        }
        return instance;
    }

    public AsyncException getLastError() {
        return this.lastError;
    }

    public void setLastError(AsyncException lastError) {
        this.lastError = lastError;
    }
}
