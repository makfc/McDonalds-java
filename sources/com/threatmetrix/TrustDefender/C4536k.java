package com.threatmetrix.TrustDefender;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.threatmetrix.TrustDefender.k */
class C4536k {
    /* renamed from: a */
    private static final String f7785a = C4549w.m8585a(C4536k.class);
    /* renamed from: b */
    private CountDownLatch f7786b = new CountDownLatch(1);
    /* renamed from: c */
    private C4535a f7787c = new C4535a(this.f7786b);

    /* renamed from: com.threatmetrix.TrustDefender.k$a */
    static class C4535a implements ServiceConnection {
        /* renamed from: a */
        private IBinder f7782a = null;
        /* renamed from: b */
        private String f7783b = null;
        /* renamed from: c */
        private CountDownLatch f7784c;

        public C4535a(CountDownLatch latch) {
            this.f7784c = latch;
        }

        public final void onServiceConnected(ComponentName name, IBinder service) {
            if (service != null) {
                this.f7782a = service;
                this.f7783b = m8534b();
                this.f7784c.countDown();
            }
        }

        public final void onServiceDisconnected(ComponentName name) {
        }

        /* Access modifiers changed, original: final */
        /* renamed from: a */
        public final String mo34239a() {
            return this.f7783b;
        }

        /* renamed from: b */
        private String m8534b() {
            String replyString;
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            try {
                data.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f7782a.transact(1, data, reply, 0);
                reply.readException();
                replyString = reply.readString();
            } catch (Exception e) {
                C4536k.f7785a;
                replyString = null;
            } finally {
                reply.recycle();
                data.recycle();
            }
            return replyString;
        }
    }

    /* renamed from: a */
    public final boolean mo34243a(Context context) {
        Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
        return context.bindService(intent, this.f7787c, 1);
    }

    /* renamed from: a */
    public final String mo34242a(int timeout) {
        String str;
        try {
            if (this.f7786b.await((long) timeout, TimeUnit.MILLISECONDS)) {
                return this.f7787c.mo34239a();
            }
            str = f7785a;
            return null;
        } catch (InterruptedException e) {
            str = f7785a;
        } catch (Exception e2) {
            C4549w.m8594c(f7785a, e2.getMessage());
        }
    }
}
