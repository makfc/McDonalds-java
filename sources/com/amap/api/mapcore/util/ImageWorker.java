package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import com.amap.api.mapcore.util.ImageCache.C0808a;
import com.amap.api.mapcore.util.TileOverlayDelegateImp.C0738a;
import java.lang.ref.WeakReference;

/* renamed from: com.amap.api.mapcore.util.dd */
public abstract class ImageWorker {
    /* renamed from: a */
    private ImageCache f1729a;
    /* renamed from: b */
    private C0808a f1730b;
    /* renamed from: c */
    protected boolean f1731c = false;
    /* renamed from: d */
    protected Resources f1732d;
    /* renamed from: e */
    private boolean f1733e = false;
    /* renamed from: f */
    private final Object f1734f = new Object();

    /* compiled from: ImageWorker */
    /* renamed from: com.amap.api.mapcore.util.dd$a */
    public class C0809a extends AsyncTask<Boolean, Void, Bitmap> {
        /* renamed from: e */
        private final WeakReference<C0738a> f1736e;

        public C0809a(C0738a c0738a) {
            this.f1736e = new WeakReference(c0738a);
        }

        /* Access modifiers changed, original: protected|varargs */
        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* renamed from: a */
        public android.graphics.Bitmap mo8706a(java.lang.Boolean... r7) {
            /*
            r6 = this;
            r1 = 0;
            r0 = 0;
            r0 = r7[r0];	 Catch:{ Throwable -> 0x0056 }
            r3 = r0.booleanValue();	 Catch:{ Throwable -> 0x0056 }
            r0 = r6.f1736e;	 Catch:{ Throwable -> 0x0056 }
            r0 = r0.get();	 Catch:{ Throwable -> 0x0056 }
            r0 = (com.amap.api.mapcore.util.TileOverlayDelegateImp.C0738a) r0;	 Catch:{ Throwable -> 0x0056 }
            if (r0 != 0) goto L_0x0014;
        L_0x0012:
            r0 = r1;
        L_0x0013:
            return r0;
        L_0x0014:
            r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0056 }
            r2.<init>();	 Catch:{ Throwable -> 0x0056 }
            r4 = r0.f1207a;	 Catch:{ Throwable -> 0x0056 }
            r2.append(r4);	 Catch:{ Throwable -> 0x0056 }
            r4 = "-";
            r2.append(r4);	 Catch:{ Throwable -> 0x0056 }
            r4 = r0.f1208b;	 Catch:{ Throwable -> 0x0056 }
            r2.append(r4);	 Catch:{ Throwable -> 0x0056 }
            r4 = "-";
            r2.append(r4);	 Catch:{ Throwable -> 0x0056 }
            r4 = r0.f1209c;	 Catch:{ Throwable -> 0x0056 }
            r2.append(r4);	 Catch:{ Throwable -> 0x0056 }
            r4 = r2.toString();	 Catch:{ Throwable -> 0x0056 }
            r2 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f1734f;	 Catch:{ Throwable -> 0x0056 }
            monitor-enter(r2);	 Catch:{ Throwable -> 0x0056 }
        L_0x003d:
            r5 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ all -> 0x0053 }
            r5 = r5.f1731c;	 Catch:{ all -> 0x0053 }
            if (r5 == 0) goto L_0x005c;
        L_0x0043:
            r5 = r6.mo8714d();	 Catch:{ all -> 0x0053 }
            if (r5 != 0) goto L_0x005c;
        L_0x0049:
            r5 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ all -> 0x0053 }
            r5 = r5.f1734f;	 Catch:{ all -> 0x0053 }
            r5.wait();	 Catch:{ all -> 0x0053 }
            goto L_0x003d;
        L_0x0053:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x0053 }
            throw r0;	 Catch:{ Throwable -> 0x0056 }
        L_0x0056:
            r0 = move-exception;
            r0.printStackTrace();
            r0 = r1;
            goto L_0x0013;
        L_0x005c:
            monitor-exit(r2);	 Catch:{ all -> 0x0053 }
            r2 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f1729a;	 Catch:{ Throwable -> 0x0056 }
            if (r2 == 0) goto L_0x00b8;
        L_0x0065:
            r2 = r6.mo8714d();	 Catch:{ Throwable -> 0x0056 }
            if (r2 != 0) goto L_0x00b8;
        L_0x006b:
            r2 = r6.m2300e();	 Catch:{ Throwable -> 0x0056 }
            if (r2 == 0) goto L_0x00b8;
        L_0x0071:
            r2 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f1733e;	 Catch:{ Throwable -> 0x0056 }
            if (r2 != 0) goto L_0x00b8;
        L_0x0079:
            r2 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f1729a;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.mo9245b(r4);	 Catch:{ Throwable -> 0x0056 }
        L_0x0083:
            if (r3 == 0) goto L_0x00b6;
        L_0x0085:
            if (r2 != 0) goto L_0x00b6;
        L_0x0087:
            r3 = r6.mo8714d();	 Catch:{ Throwable -> 0x0056 }
            if (r3 != 0) goto L_0x00b6;
        L_0x008d:
            r3 = r6.m2300e();	 Catch:{ Throwable -> 0x0056 }
            if (r3 == 0) goto L_0x00b6;
        L_0x0093:
            r3 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0056 }
            r3 = r3.f1733e;	 Catch:{ Throwable -> 0x0056 }
            if (r3 != 0) goto L_0x00b6;
        L_0x009b:
            r2 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0056 }
            r0 = r2.mo9249a(r0);	 Catch:{ Throwable -> 0x0056 }
        L_0x00a1:
            if (r0 == 0) goto L_0x0013;
        L_0x00a3:
            r2 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f1729a;	 Catch:{ Throwable -> 0x0056 }
            if (r2 == 0) goto L_0x0013;
        L_0x00ab:
            r2 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0056 }
            r2 = r2.f1729a;	 Catch:{ Throwable -> 0x0056 }
            r2.mo9244a(r4, r0);	 Catch:{ Throwable -> 0x0056 }
            goto L_0x0013;
        L_0x00b6:
            r0 = r2;
            goto L_0x00a1;
        L_0x00b8:
            r2 = r1;
            goto L_0x0083;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ImageWorker$C0809a.mo8706a(java.lang.Boolean[]):android.graphics.Bitmap");
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void mo8707a(Bitmap bitmap) {
            try {
                if (mo8714d() || ImageWorker.this.f1733e) {
                    bitmap = null;
                }
                C0738a e = m2300e();
                if (bitmap != null && !bitmap.isRecycled() && e != null) {
                    e.mo8698a(bitmap);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: b */
        public void mo8710b(Bitmap bitmap) {
            super.mo8710b((Object) bitmap);
            synchronized (ImageWorker.this.f1734f) {
                try {
                    ImageWorker.this.f1734f.notifyAll();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }

        /* renamed from: e */
        private C0738a m2300e() {
            C0738a c0738a = (C0738a) this.f1736e.get();
            return this == ImageWorker.m2285c(c0738a) ? c0738a : null;
        }
    }

    /* compiled from: ImageWorker */
    /* renamed from: com.amap.api.mapcore.util.dd$b */
    protected class C0810b extends AsyncTask<Object, Void, Void> {
        protected C0810b() {
        }

        /* Access modifiers changed, original: protected|varargs */
        /* renamed from: d */
        public java.lang.Void mo8706a(java.lang.Object... r2) {
            /*
            r1 = this;
            r0 = 0;
            r0 = r2[r0];	 Catch:{ Throwable -> 0x0014 }
            r0 = (java.lang.Integer) r0;	 Catch:{ Throwable -> 0x0014 }
            r0 = r0.intValue();	 Catch:{ Throwable -> 0x0014 }
            switch(r0) {
                case 0: goto L_0x000e;
                case 1: goto L_0x0019;
                case 2: goto L_0x001f;
                case 3: goto L_0x0025;
                default: goto L_0x000c;
            };	 Catch:{ Throwable -> 0x0014 }
        L_0x000c:
            r0 = 0;
            return r0;
        L_0x000e:
            r0 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0014 }
            r0.mo9256c();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x0014:
            r0 = move-exception;
            r0.printStackTrace();
            goto L_0x000c;
        L_0x0019:
            r0 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0014 }
            r0.mo9254b();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x001f:
            r0 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0014 }
            r0.mo9257d();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
        L_0x0025:
            r0 = com.amap.api.mapcore.util.ImageWorker.this;	 Catch:{ Throwable -> 0x0014 }
            r0.mo9258e();	 Catch:{ Throwable -> 0x0014 }
            goto L_0x000c;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ImageWorker$C0810b.mo8706a(java.lang.Object[]):java.lang.Void");
        }
    }

    /* renamed from: a */
    public abstract Bitmap mo9249a(Object obj);

    protected ImageWorker(Context context) {
        this.f1732d = context.getResources();
    }

    /* renamed from: a */
    public void mo9253a(boolean z, C0738a c0738a) {
        if (c0738a != null) {
            Bitmap bitmap = null;
            try {
                if (this.f1729a != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(c0738a.f1207a);
                    stringBuilder.append("-");
                    stringBuilder.append(c0738a.f1208b);
                    stringBuilder.append("-");
                    stringBuilder.append(c0738a.f1209c);
                    bitmap = this.f1729a.mo9242a(stringBuilder.toString());
                }
                if (bitmap != null) {
                    c0738a.mo8698a(bitmap);
                    return;
                }
                C0809a c0809a = new C0809a(c0738a);
                c0738a.f1216j = c0809a;
                c0809a.mo8705a(AsyncTask.f1222d, (Object[]) new Boolean[]{Boolean.valueOf(z)});
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void mo9251a(C0808a c0808a) {
        this.f1730b = c0808a;
        this.f1729a = ImageCache.m2264a(this.f1730b);
        new C0810b().mo8712c(Integer.valueOf(1));
    }

    /* renamed from: a */
    public void mo9252a(boolean z) {
        this.f1733e = z;
        mo9255b(false);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public ImageCache mo9250a() {
        return this.f1729a;
    }

    /* renamed from: a */
    public static void m2282a(C0738a c0738a) {
        C0809a c = ImageWorker.m2285c(c0738a);
        if (c != null) {
            c.mo8708a(true);
        }
    }

    /* renamed from: c */
    private static C0809a m2285c(C0738a c0738a) {
        if (c0738a != null) {
            return c0738a.f1216j;
        }
        return null;
    }

    /* renamed from: b */
    public void mo9255b(boolean z) {
        synchronized (this.f1734f) {
            this.f1731c = z;
            if (!this.f1731c) {
                try {
                    this.f1734f.notifyAll();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo9254b() {
        if (this.f1729a != null) {
            this.f1729a.mo9243a();
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public void mo9256c() {
        if (this.f1729a != null) {
            this.f1729a.mo9246b();
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: d */
    public void mo9257d() {
        if (this.f1729a != null) {
            this.f1729a.mo9247c();
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: e */
    public void mo9258e() {
        if (this.f1729a != null) {
            this.f1729a.mo9248d();
            this.f1729a = null;
        }
    }

    /* renamed from: f */
    public void mo9259f() {
        new C0810b().mo8712c(Integer.valueOf(0));
    }

    /* renamed from: g */
    public void mo9260g() {
        new C0810b().mo8712c(Integer.valueOf(2));
    }

    /* renamed from: h */
    public void mo9261h() {
        new C0810b().mo8712c(Integer.valueOf(3));
    }
}
