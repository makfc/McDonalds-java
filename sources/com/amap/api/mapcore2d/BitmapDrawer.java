package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;

/* renamed from: com.amap.api.mapcore2d.h */
class BitmapDrawer {
    /* renamed from: a */
    private Bitmap f2933a = null;
    /* renamed from: b */
    private Canvas f2934b = null;
    /* renamed from: c */
    private Config f2935c;

    public BitmapDrawer(Config config) {
        this.f2935c = config;
    }

    /* renamed from: a */
    public void mo10291a(Bitmap bitmap) {
        this.f2933a = bitmap;
        this.f2934b = new Canvas(this.f2933a);
    }

    /* renamed from: a */
    public void mo10292a(BitmapMaker bitmapMaker) {
        this.f2934b.save(1);
        bitmapMaker.mo9916a(this.f2934b);
        this.f2934b.restore();
    }
}
