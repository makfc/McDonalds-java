package com.google.api.client.util;

public interface NanoClock {
    public static final NanoClock SYSTEM = new C27701();

    /* renamed from: com.google.api.client.util.NanoClock$1 */
    static class C27701 implements NanoClock {
        C27701() {
        }

        public long nanoTime() {
            return System.nanoTime();
        }
    }

    long nanoTime();
}
