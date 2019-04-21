package com.facebook.stetho.common.android;

import android.os.Handler;
import android.os.Looper;
import com.facebook.stetho.common.UncheckedCallable;
import com.facebook.stetho.common.Util;

public final class HandlerUtil {

    private static abstract class WaitableRunnable<V> implements Runnable {
        private Exception mException;
        private boolean mIsDone;
        private V mValue;

        public abstract V onRun();

        protected WaitableRunnable() {
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        public final void run() {
            /*
            r3 = this;
            r1 = r3.onRun();	 Catch:{ Exception -> 0x0015 }
            r3.mValue = r1;	 Catch:{ Exception -> 0x0015 }
            r1 = 0;
            r3.mException = r1;	 Catch:{ Exception -> 0x0015 }
            monitor-enter(r3);
            r1 = 1;
            r3.mIsDone = r1;	 Catch:{ all -> 0x0012 }
            r3.notifyAll();	 Catch:{ all -> 0x0012 }
            monitor-exit(r3);	 Catch:{ all -> 0x0012 }
        L_0x0011:
            return;
        L_0x0012:
            r1 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0012 }
            throw r1;
        L_0x0015:
            r0 = move-exception;
            r1 = 0;
            r3.mValue = r1;	 Catch:{ all -> 0x0027 }
            r3.mException = r0;	 Catch:{ all -> 0x0027 }
            monitor-enter(r3);
            r1 = 1;
            r3.mIsDone = r1;	 Catch:{ all -> 0x0024 }
            r3.notifyAll();	 Catch:{ all -> 0x0024 }
            monitor-exit(r3);	 Catch:{ all -> 0x0024 }
            goto L_0x0011;
        L_0x0024:
            r1 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0024 }
            throw r1;
        L_0x0027:
            r1 = move-exception;
            monitor-enter(r3);
            r2 = 1;
            r3.mIsDone = r2;	 Catch:{ all -> 0x0031 }
            r3.notifyAll();	 Catch:{ all -> 0x0031 }
            monitor-exit(r3);	 Catch:{ all -> 0x0031 }
            throw r1;
        L_0x0031:
            r1 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x0031 }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.common.android.HandlerUtil$WaitableRunnable.run():void");
        }

        public V invoke(Handler handler) {
            if (handler.post(this)) {
                join();
                if (this.mException == null) {
                    return this.mValue;
                }
                throw new RuntimeException(this.mException);
            }
            throw new RuntimeException("Handler.post() returned false");
        }

        private void join() {
            synchronized (this) {
                while (!this.mIsDone) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    private HandlerUtil() {
    }

    public static boolean checkThreadAccess(Handler handler) {
        return Looper.myLooper() == handler.getLooper();
    }

    public static void verifyThreadAccess(Handler handler) {
        Util.throwIfNot(checkThreadAccess(handler));
    }

    public static <V> V postAndWait(Handler handler, final UncheckedCallable<V> c) {
        if (!checkThreadAccess(handler)) {
            return new WaitableRunnable<V>() {
                /* Access modifiers changed, original: protected */
                public V onRun() {
                    return c.call();
                }
            }.invoke(handler);
        }
        try {
            return c.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void postAndWait(Handler handler, final Runnable r) {
        if (checkThreadAccess(handler)) {
            try {
                r.run();
                return;
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
        new WaitableRunnable<Void>() {
            /* Access modifiers changed, original: protected */
            public Void onRun() {
                r.run();
                return null;
            }
        }.invoke(handler);
    }
}
