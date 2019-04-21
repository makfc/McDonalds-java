package com.newrelic.agent.android.instrumentation.p053io;

/* renamed from: com.newrelic.agent.android.instrumentation.io.StreamCompleteListener */
public interface StreamCompleteListener {
    void streamComplete(StreamCompleteEvent streamCompleteEvent);

    void streamError(StreamCompleteEvent streamCompleteEvent);
}
