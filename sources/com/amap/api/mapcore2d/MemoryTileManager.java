package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.view.InputDeviceCompat;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.util.List;

/* renamed from: com.amap.api.mapcore2d.az */
class MemoryTileManager {
    /* renamed from: a */
    protected final C0913a[] f2402a;
    /* renamed from: b */
    protected final int f2403b;
    /* renamed from: c */
    protected final int f2404c;
    /* renamed from: d */
    protected final C0913a[] f2405d;
    /* renamed from: e */
    private boolean f2406e = false;
    /* renamed from: f */
    private long f2407f = 0;
    /* renamed from: g */
    private C0886am f2408g;
    /* renamed from: h */
    private Paint f2409h = null;
    /* renamed from: i */
    private Path f2410i = null;

    /* compiled from: MemoryTileManager */
    /* renamed from: com.amap.api.mapcore2d.az$a */
    private class C0913a {
        /* renamed from: a */
        Bitmap f2394a = null;
        /* renamed from: b */
        String f2395b = "";
        /* renamed from: c */
        boolean f2396c = false;
        /* renamed from: d */
        long f2397d = 0;
        /* renamed from: e */
        int f2398e = -1;
        /* renamed from: f */
        long f2399f = 0;
        /* renamed from: g */
        List<TrafSegment> f2400g = null;
    }

    public MemoryTileManager(int i, int i2, boolean z, long j, C0886am c0886am) {
        this.f2403b = i;
        this.f2404c = i2;
        this.f2408g = c0886am;
        this.f2406e = z;
        this.f2407f = 1000000 * j;
        if (this.f2403b > 0) {
            this.f2402a = new C0913a[this.f2403b];
            this.f2405d = new C0913a[this.f2404c];
            return;
        }
        this.f2402a = null;
        this.f2405d = null;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public int mo9918a(String str) {
        if (str == null || str.equals("")) {
            return -1;
        }
        int i = 0;
        while (i < this.f2403b) {
            if (this.f2402a[i] == null || !this.f2402a[i].f2395b.equals(str)) {
                i++;
            } else if (!this.f2402a[i].f2396c) {
                return -1;
            } else {
                if (this.f2406e && m3358d() - this.f2402a[i].f2399f > this.f2407f) {
                    this.f2402a[i].f2396c = false;
                    return -1;
                } else if (this.f2402a[i].f2394a == null) {
                    return -1;
                } else {
                    this.f2402a[i].f2397d = m3358d();
                    return i;
                }
            }
        }
        return -1;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public Bitmap mo9920a(int i) {
        if (i < 0 || i >= this.f2403b || this.f2402a[i] == null) {
            return null;
        }
        return this.f2402a[i].f2394a;
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: a */
    public synchronized int mo9919a(byte[] bArr, byte[] bArr2, boolean z, List<TrafSegment> list, String str) {
        int i = -1;
        synchronized (this) {
            if (!(bArr == null && bArr2 == null && list == null)) {
                int b = mo9921b();
                if (b < 0) {
                    b = mo9917a();
                }
                if (b >= 0 && this.f2402a != null) {
                    if (!(this.f2402a[b] == null || this.f2402a[b].f2394a == null || this.f2402a[b].f2394a.isRecycled())) {
                        this.f2402a[b].f2394a.recycle();
                        this.f2402a[b].f2394a = null;
                    }
                    if (this.f2402a[b].f2400g != null) {
                        this.f2402a[b].f2400g.clear();
                        this.f2402a[b].f2400g = null;
                    }
                    if (z && bArr != null) {
                        try {
                            this.f2402a[b].f2394a = BitmapFactoryInstrumentation.decodeByteArray(bArr, 0, bArr.length);
                        } catch (OutOfMemoryError e) {
                        } catch (Throwable th) {
                            C0955ck.m3888a(th, "BitmapManager", "setBitmapData");
                        }
                    } else if (bArr2 != null) {
                        try {
                            this.f2402a[b].f2394a = BitmapFactoryInstrumentation.decodeByteArray(bArr2, 0, bArr2.length);
                        } catch (OutOfMemoryError e2) {
                        } catch (Throwable th2) {
                            C0955ck.m3888a(th2, "BitmapManager", "setBitmapData");
                        }
                    }
                    if (list != null) {
                        this.f2402a[b].f2394a = Bitmap.createBitmap(this.f2408g.f2237a.f2305a, this.f2408g.f2237a.f2305a, Config.ARGB_4444);
                        m3356a(this.f2402a[b].f2394a, (List) list);
                    }
                    if (!(this.f2402a[b].f2394a == null && this.f2402a[b].f2400g == null)) {
                        if (this.f2402a[b] != null) {
                            this.f2402a[b].f2396c = true;
                            this.f2402a[b].f2395b = str;
                            this.f2402a[b].f2397d = m3358d();
                            if (this.f2406e) {
                                this.f2402a[b].f2399f = m3358d();
                            }
                        }
                        i = b;
                    }
                }
            }
        }
        return i;
    }

    /* renamed from: d */
    private long m3358d() {
        return System.nanoTime();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public int mo9917a() {
        int i;
        for (i = 0; i < this.f2404c; i++) {
            this.f2405d[i] = null;
        }
        for (int i2 = 0; i2 < this.f2403b; i2++) {
            C0913a c0913a = this.f2402a[i2];
            int i3 = 0;
            while (i3 < this.f2404c) {
                if (this.f2405d[i3] == null) {
                    this.f2405d[i3] = c0913a;
                    break;
                }
                C0913a c0913a2;
                if (this.f2405d[i3].f2397d > c0913a.f2397d) {
                    c0913a2 = this.f2405d[i3];
                    this.f2405d[i3] = c0913a;
                } else {
                    c0913a2 = c0913a;
                }
                i3++;
                c0913a = c0913a2;
            }
        }
        int i4 = -1;
        for (i = 0; i < this.f2404c; i++) {
            if (this.f2405d[i] != null) {
                this.f2405d[i].f2396c = false;
                if (i4 < 0) {
                    i4 = this.f2405d[i].f2398e;
                }
            }
        }
        return i4;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public int mo9921b() {
        int i = -1;
        for (int i2 = 0; i2 < this.f2403b; i2++) {
            if (this.f2402a[i2] == null) {
                this.f2402a[i2] = new C0913a();
                this.f2402a[i2].f2398e = i2;
                return i2;
            }
            if (!this.f2402a[i2].f2396c && i < 0) {
                i = i2;
            }
        }
        return i;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public void mo9922c() {
        int i = 0;
        while (i < this.f2403b) {
            if (this.f2402a[i] != null) {
                if (!(this.f2402a[i].f2394a == null || this.f2402a[i].f2394a.isRecycled())) {
                    this.f2402a[i].f2394a.recycle();
                }
                this.f2402a[i].f2394a = null;
            }
            i++;
        }
    }

    /* renamed from: a */
    private void m3356a(Bitmap bitmap, final List<TrafSegment> list) {
        BitmapMaker c09121 = new BitmapMaker() {
            /* renamed from: a */
            public void mo9916a(Canvas canvas) {
                if (MemoryTileManager.this.f2409h == null) {
                    MemoryTileManager.this.f2409h = new Paint();
                    MemoryTileManager.this.f2409h.setStyle(Style.STROKE);
                    MemoryTileManager.this.f2409h.setDither(true);
                    MemoryTileManager.this.f2409h.setAntiAlias(true);
                    MemoryTileManager.this.f2409h.setStrokeJoin(Join.ROUND);
                    MemoryTileManager.this.f2409h.setStrokeCap(Cap.ROUND);
                }
                if (MemoryTileManager.this.f2410i == null) {
                    MemoryTileManager.this.f2410i = new Path();
                }
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    TrafSegment trafSegment = (TrafSegment) list.get(i);
                    MemoryTileManager.this.f2409h.setStrokeWidth(3.0f);
                    int b = trafSegment.mo10125b();
                    if (b == 1) {
                        MemoryTileManager.this.f2409h.setColor(SupportMenu.CATEGORY_MASK);
                    } else if (b == 2) {
                        MemoryTileManager.this.f2409h.setColor(InputDeviceCompat.SOURCE_ANY);
                    } else if (b == 3) {
                        MemoryTileManager.this.f2409h.setColor(-16711936);
                    }
                    List a = trafSegment.mo10124a();
                    int size2 = a.size();
                    int i2 = 0;
                    boolean z = true;
                    while (i2 < size2) {
                        boolean z2;
                        PointF pointF = (PointF) a.get(i2);
                        if (z) {
                            MemoryTileManager.this.f2410i.moveTo(pointF.x, pointF.y);
                            z2 = false;
                        } else {
                            MemoryTileManager.this.f2410i.lineTo(pointF.x, pointF.y);
                            z2 = z;
                        }
                        i2++;
                        z = z2;
                    }
                    canvas.drawPath(MemoryTileManager.this.f2410i, MemoryTileManager.this.f2409h);
                    MemoryTileManager.this.f2410i.reset();
                }
            }
        };
        BitmapDrawer bitmapDrawer = new BitmapDrawer(null);
        bitmapDrawer.mo10291a(bitmap);
        bitmapDrawer.mo10292a(c09121);
    }
}
