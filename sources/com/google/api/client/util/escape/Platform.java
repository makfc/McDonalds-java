package com.google.api.client.util.escape;

final class Platform {
    private static final ThreadLocal<char[]> DEST_TL = new C27751();

    /* renamed from: com.google.api.client.util.escape.Platform$1 */
    static class C27751 extends ThreadLocal<char[]> {
        C27751() {
        }

        /* Access modifiers changed, original: protected */
        public char[] initialValue() {
            return new char[1024];
        }
    }

    private Platform() {
    }

    static char[] charBufferFromThreadLocal() {
        return (char[]) DEST_TL.get();
    }
}
