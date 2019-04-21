package com.newrelic.agent.android.instrumentation.p053io;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.newrelic.agent.android.instrumentation.io.StreamCompleteListenerManager */
class StreamCompleteListenerManager {
    private boolean streamComplete = false;
    private ArrayList<StreamCompleteListener> streamCompleteListeners = new ArrayList();

    StreamCompleteListenerManager() {
    }

    public boolean isComplete() {
        boolean z;
        synchronized (this) {
            z = this.streamComplete;
        }
        return z;
    }

    public void addStreamCompleteListener(StreamCompleteListener streamCompleteListener) {
        synchronized (this.streamCompleteListeners) {
            this.streamCompleteListeners.add(streamCompleteListener);
        }
    }

    public void removeStreamCompleteListener(StreamCompleteListener streamCompleteListener) {
        synchronized (this.streamCompleteListeners) {
            this.streamCompleteListeners.remove(streamCompleteListener);
        }
    }

    public void notifyStreamComplete(StreamCompleteEvent ev) {
        if (!checkComplete()) {
            for (StreamCompleteListener listener : getStreamCompleteListeners()) {
                listener.streamComplete(ev);
            }
        }
    }

    public void notifyStreamError(StreamCompleteEvent ev) {
        if (!checkComplete()) {
            for (StreamCompleteListener listener : getStreamCompleteListeners()) {
                listener.streamError(ev);
            }
        }
    }

    private boolean checkComplete() {
        boolean streamComplete;
        synchronized (this) {
            streamComplete = isComplete();
            if (!streamComplete) {
                this.streamComplete = true;
            }
        }
        return streamComplete;
    }

    private List<StreamCompleteListener> getStreamCompleteListeners() {
        ArrayList<StreamCompleteListener> listeners;
        synchronized (this.streamCompleteListeners) {
            listeners = new ArrayList(this.streamCompleteListeners);
            this.streamCompleteListeners.clear();
        }
        return listeners;
    }
}
