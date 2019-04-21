package com.mcdonalds.sdk;

import android.os.Handler;
import android.os.Looper;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.util.ArrayList;
import java.util.List;

public class AsyncCounter<T> {
    private boolean finished = false;
    private int mCount = 0;
    private AsyncListener<List<T>> mListener;
    private int mMax = -1;
    private List<T> mObjects;

    public AsyncCounter(int finalCount, final AsyncListener<List<T>> listener) {
        if (finalCount < 0) {
            throw new RuntimeException("Max Count < 0!");
        } else if (finalCount == 0) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    listener.onResponse(new ArrayList(), null, null);
                }
            });
        } else {
            this.mMax = finalCount;
            this.mObjects = new ArrayList();
            this.mListener = listener;
        }
    }

    public void success(T obj) {
        if (!this.finished) {
            this.mCount++;
            if (this.mCount > this.mMax) {
                throw new RuntimeException("AsyncCounter has exceeded maximum iterations: " + this.mCount + " > " + this.mMax);
            }
            if (obj != null) {
                this.mObjects.add(obj);
            }
            if (this.mCount == this.mMax) {
                this.finished = true;
                this.mListener.onResponse(this.mObjects, null, null);
            }
        }
    }

    public void error(AsyncException exception) {
        if (!this.finished) {
            MCDLog.temp(exception != null ? "AsyncCounter: Failure: " + exception.getMessage() : "AsyncCounter: Failure");
            this.finished = true;
            this.mListener.onResponse(null, null, exception);
        }
    }

    public void incrementMax() {
        this.mMax++;
    }

    public boolean hasActiveProcesses() {
        return this.mCount > 0;
    }
}
