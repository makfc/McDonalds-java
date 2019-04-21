package com.google.api.client.util;

public interface Clock {
    public static final Clock SYSTEM = new C27641();

    /* renamed from: com.google.api.client.util.Clock$1 */
    static class C27641 implements Clock {
        C27641() {
        }

        public long currentTimeMillis() {
            return System.currentTimeMillis();
        }
    }

    long currentTimeMillis();
}
