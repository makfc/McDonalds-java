package com.tencent.p050mm.sdk.p066b;

import android.os.Debug;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.tencent.p050mm.sdk.p066b.C4360g.C4357a;
import junit.framework.Assert;

/* renamed from: com.tencent.mm.sdk.b.e */
final class C4358e extends Handler implements C4357a {
    /* renamed from: aN */
    private Looper f6793aN = getLooper();
    /* renamed from: aO */
    private Callback f6794aO;
    /* renamed from: aP */
    C4355a f6795aP;

    /* renamed from: com.tencent.mm.sdk.b.e$a */
    public interface C4355a {
        /* renamed from: a */
        void mo33771a(Runnable runnable, C4360g c4360g);

        /* renamed from: b */
        void mo33772b(Runnable runnable, C4360g c4360g);
    }

    C4358e(C4355a c4355a) {
        this.f6795aP = c4355a;
    }

    /* renamed from: c */
    public final void mo33775c(Runnable runnable, C4360g c4360g) {
        if (this.f6795aP != null) {
            this.f6795aP.mo33772b(runnable, c4360g);
        }
    }

    public final void dispatchMessage(Message message) {
        if (message.getCallback() == null && this.f6794aO == null) {
            System.currentTimeMillis();
            Debug.threadCpuTimeNanos();
            handleMessage(message);
            if (this.f6795aP != null) {
                this.f6793aN.getThread();
                System.currentTimeMillis();
                Debug.threadCpuTimeNanos();
                return;
            }
            return;
        }
        super.dispatchMessage(message);
    }

    public final void handleMessage(Message message) {
    }

    public final boolean sendMessageAtTime(Message message, long j) {
        Assert.assertTrue("msg is null", message != null);
        Runnable callback = message.getCallback();
        if (callback == null) {
            return super.sendMessageAtTime(message, j);
        }
        long uptimeMillis = j - SystemClock.uptimeMillis();
        C4360g c4360g = new C4360g(this.f6793aN.getThread(), message.getTarget() == null ? this : message.getTarget(), callback, message.obj, this);
        if (uptimeMillis > 0) {
            c4360g.f6806aY = uptimeMillis;
        }
        Message obtain = Message.obtain(message.getTarget(), c4360g);
        obtain.what = message.what;
        obtain.arg1 = message.arg1;
        obtain.arg2 = message.arg2;
        obtain.obj = message.obj;
        obtain.replyTo = message.replyTo;
        obtain.setData(message.getData());
        message.recycle();
        if (this.f6795aP != null) {
            this.f6795aP.mo33771a(callback, c4360g);
        }
        boolean sendMessageAtTime = super.sendMessageAtTime(obtain, j);
        if (!(sendMessageAtTime || this.f6795aP == null)) {
            this.f6795aP.mo33772b(callback, c4360g);
        }
        return sendMessageAtTime;
    }

    public final String toString() {
        return "MMInnerHandler{listener = " + this.f6795aP + "}";
    }
}
