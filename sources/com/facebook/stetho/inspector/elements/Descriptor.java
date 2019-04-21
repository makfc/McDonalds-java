package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.ThreadBound;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class Descriptor implements NodeDescriptor {
    private Host mHost;

    public interface Host extends ThreadBound {
        @Nullable
        Descriptor getDescriptor(@Nullable Object obj);

        void onAttributeModified(Object obj, String str, String str2);

        void onAttributeRemoved(Object obj, String str);
    }

    protected Descriptor() {
    }

    /* Access modifiers changed, original: final */
    public final void initialize(Host host) {
        Util.throwIfNull(host);
        Util.throwIfNotNull(this.mHost);
        this.mHost = host;
    }

    /* Access modifiers changed, original: final */
    public final boolean isInitialized() {
        return this.mHost != null;
    }

    /* Access modifiers changed, original: protected|final */
    public final Host getHost() {
        return this.mHost;
    }

    public final boolean checkThreadAccess() {
        return getHost().checkThreadAccess();
    }

    public final void verifyThreadAccess() {
        getHost().verifyThreadAccess();
    }

    public final <V> V postAndWait(UncheckedCallable<V> c) {
        return getHost().postAndWait((UncheckedCallable) c);
    }

    public final void postAndWait(Runnable r) {
        getHost().postAndWait(r);
    }

    public final void postDelayed(Runnable r, long delayMillis) {
        getHost().postDelayed(r, delayMillis);
    }

    public final void removeCallbacks(Runnable r) {
        getHost().removeCallbacks(r);
    }

    protected static Map<String, String> parseSetAttributesAsTextArg(String text) {
        String value = "";
        String key = "";
        StringBuilder buffer = new StringBuilder();
        Map<String, String> keyValuePairs = new HashMap();
        boolean isInsideQuotes = false;
        int N = text.length();
        for (int i = 0; i < N; i++) {
            char c = text.charAt(i);
            if (c == '=') {
                key = buffer.toString();
                buffer.setLength(0);
            } else if (c == '\"') {
                if (isInsideQuotes) {
                    value = buffer.toString();
                    buffer.setLength(0);
                }
                isInsideQuotes = !isInsideQuotes;
            } else if (c != SafeJsonPrimitive.NULL_CHAR || isInsideQuotes) {
                buffer.append(c);
            } else {
                keyValuePairs.put(key, value);
            }
        }
        if (!(key.isEmpty() || value.isEmpty())) {
            keyValuePairs.put(key, value);
        }
        return keyValuePairs;
    }
}
