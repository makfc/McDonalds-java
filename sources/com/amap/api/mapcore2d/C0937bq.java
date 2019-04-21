package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import com.amap.api.maps2d.model.TileOverlayOptions;

/* compiled from: TileOverlayDelegateImp */
/* renamed from: com.amap.api.mapcore2d.bq */
public class C0937bq implements ITileOverlayDelegate {
    /* renamed from: a */
    private static int f2602a = 0;
    /* renamed from: b */
    private C0940br f2603b;
    /* renamed from: c */
    private C0886am f2604c;
    /* renamed from: d */
    private boolean f2605d;
    /* renamed from: e */
    private String f2606e;
    /* renamed from: f */
    private float f2607f;

    public C0937bq(TileOverlayOptions tileOverlayOptions, C0940br c0940br, MapProjection mapProjection, Mediator mediator, Context context) {
        this.f2603b = c0940br;
        this.f2604c = new C0886am(mapProjection);
        this.f2604c.f2242f = tileOverlayOptions.getDiskCacheEnabled();
        this.f2604c.f2252p = new SyncList();
        this.f2604c.f2247k = tileOverlayOptions.getTileProvider();
        this.f2604c.f2250n = new MemoryTileManager(mediator.f2386e.f2359e, mediator.f2386e.f2360f, false, 0, this.f2604c);
        String diskCacheDir = tileOverlayOptions.getDiskCacheDir();
        if (TextUtils.isEmpty(diskCacheDir)) {
            this.f2604c.f2242f = false;
        }
        this.f2604c.f2249m = diskCacheDir;
        this.f2604c.f2251o = new DiskCachManager(c0940br.getContext(), false, this.f2604c);
        this.f2604c.f2236q = new TileServer(mediator, context, this.f2604c);
        this.f2604c.mo9758a(true);
        this.f2605d = tileOverlayOptions.isVisible();
        this.f2606e = mo9707c();
        this.f2607f = tileOverlayOptions.getZIndex();
    }

    /* renamed from: a */
    private static String m3724a(String str) {
        f2602a++;
        return str + f2602a;
    }

    /* renamed from: a */
    public void mo9700a() {
        try {
            this.f2603b.mo10105b(this);
            this.f2604c.mo9760b();
            this.f2604c.f2236q.mo9855b();
        } catch (Throwable th) {
            C0955ck.m3888a(th, "TileOverlayDelegateImp", "remove");
        }
    }

    /* renamed from: b */
    public void mo9705b() {
        try {
            this.f2604c.mo9760b();
        } catch (Throwable th) {
            C0955ck.m3888a(th, "TileOverlayDelegateImp", "remove");
        }
    }

    /* renamed from: c */
    public String mo9707c() {
        if (this.f2606e == null) {
            this.f2606e = C0937bq.m3724a("TileOverlay");
        }
        return this.f2606e;
    }

    /* renamed from: a */
    public void mo9701a(float f) {
        this.f2607f = f;
    }

    /* renamed from: d */
    public float mo9708d() {
        return this.f2607f;
    }

    /* renamed from: a */
    public void mo9703a(boolean z) {
        this.f2605d = z;
        this.f2604c.mo9758a(z);
    }

    /* renamed from: e */
    public boolean mo9709e() {
        return this.f2605d;
    }

    /* renamed from: a */
    public void mo9702a(Canvas canvas) {
        this.f2604c.mo9757a(canvas);
    }

    /* renamed from: a */
    public boolean mo9704a(ITileOverlayDelegate iTileOverlayDelegate) {
        return false;
    }

    /* renamed from: f */
    public int mo9710f() {
        return 0;
    }

    /* renamed from: b */
    public void mo9706b(boolean z) {
    }

    /* renamed from: g */
    public void mo9711g() {
        this.f2604c.f2236q.mo9856c();
    }

    /* renamed from: h */
    public void mo9712h() {
        this.f2604c.f2236q.mo9857d();
    }

    /* renamed from: i */
    public void mo9713i() {
        this.f2604c.f2236q.mo9855b();
    }
}
