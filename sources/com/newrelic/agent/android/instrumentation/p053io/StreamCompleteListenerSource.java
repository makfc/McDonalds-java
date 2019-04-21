package com.newrelic.agent.android.instrumentation.p053io;

/* renamed from: com.newrelic.agent.android.instrumentation.io.StreamCompleteListenerSource */
public interface StreamCompleteListenerSource {
    void addStreamCompleteListener(StreamCompleteListener streamCompleteListener);

    void removeStreamCompleteListener(StreamCompleteListener streamCompleteListener);
}
