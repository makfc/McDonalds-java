package com.google.api.client.util;

public interface Sleeper {
    public static final Sleeper DEFAULT = new C27681();

    /* renamed from: com.google.api.client.util.Sleeper$1 */
    static class C27681 implements Sleeper {
        C27681() {
        }

        public void sleep(long millis) throws InterruptedException {
            Thread.sleep(millis);
        }
    }

    void sleep(long j) throws InterruptedException;
}
