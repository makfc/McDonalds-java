package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amap.api.mapcore.util.TileOverlayDelegateImp.C0738a;
import com.amap.api.maps.model.Tile;
import com.amap.api.maps.model.TileProvider;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;

/* renamed from: com.amap.api.mapcore.util.db */
public class ImageFetcher extends ImageResizer {
    /* renamed from: e */
    private TileProvider f1740e = null;

    public ImageFetcher(Context context, int i, int i2) {
        super(context, i, i2);
        m2315a(context);
    }

    /* renamed from: a */
    private void m2315a(Context context) {
        m2316b(context);
    }

    /* renamed from: a */
    public void mo9267a(TileProvider tileProvider) {
        this.f1740e = tileProvider;
    }

    /* renamed from: b */
    private void m2316b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
        }
    }

    /* renamed from: c */
    private Bitmap m2317c(C0738a c0738a) {
        try {
            Tile tile = this.f1740e.getTile(c0738a.f1207a, c0738a.f1208b, c0738a.f1209c);
            if (tile == null || tile == TileProvider.NO_TILE) {
                return null;
            }
            return BitmapFactoryInstrumentation.decodeByteArray(tile.data, 0, tile.data.length);
        } catch (Throwable th) {
            return null;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public Bitmap mo9249a(Object obj) {
        return m2317c((C0738a) obj);
    }
}
