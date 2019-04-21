package com.google.api.client.util;

import java.io.IOException;

public interface BackOff {
    public static final BackOff STOP_BACKOFF = new C27672();
    public static final BackOff ZERO_BACKOFF = new C27661();

    /* renamed from: com.google.api.client.util.BackOff$1 */
    static class C27661 implements BackOff {
        C27661() {
        }

        public long nextBackOffMillis() throws IOException {
            return 0;
        }
    }

    /* renamed from: com.google.api.client.util.BackOff$2 */
    static class C27672 implements BackOff {
        C27672() {
        }

        public long nextBackOffMillis() throws IOException {
            return -1;
        }
    }

    long nextBackOffMillis() throws IOException;
}
