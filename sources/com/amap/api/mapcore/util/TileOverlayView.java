package com.amap.api.mapcore.util;

import android.content.Context;
import android.view.View;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.UrlTileProvider;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.ITileOverlayDelegate;
import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.aw */
public class TileOverlayView extends View {
    /* renamed from: a */
    CopyOnWriteArrayList<ITileOverlayDelegate> f1266a = new CopyOnWriteArrayList();
    /* renamed from: b */
    C0749a f1267b = new C0749a();
    /* renamed from: c */
    CopyOnWriteArrayList<Integer> f1268c = new CopyOnWriteArrayList();
    /* renamed from: d */
    TileOverlayDelegateImp f1269d = null;
    /* renamed from: e */
    private IAMapDelegate f1270e;

    /* compiled from: TileOverlayView */
    /* renamed from: com.amap.api.mapcore.util.aw$a */
    static class C0749a implements Serializable, Comparator<Object> {
        C0749a() {
        }

        public int compare(Object obj, Object obj2) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) obj;
            ITileOverlayDelegate iTileOverlayDelegate2 = (ITileOverlayDelegate) obj2;
            if (!(iTileOverlayDelegate == null || iTileOverlayDelegate2 == null)) {
                try {
                    if (iTileOverlayDelegate.getZIndex() > iTileOverlayDelegate2.getZIndex()) {
                        return 1;
                    }
                    if (iTileOverlayDelegate.getZIndex() < iTileOverlayDelegate2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    SDKLogHandler.m2563a(th, "TileOverlayView", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    public TileOverlayView(Context context) {
        super(context);
    }

    public TileOverlayView(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.f1270e = iAMapDelegate;
        this.f1269d = new TileOverlayDelegateImp(new TileOverlayOptions().tileProvider(new UrlTileProvider(256, 256) {
            public URL getTileUrl(int i, int i2, int i3) {
                try {
                    return new URL(String.format("http://grid.amap.com/grid/%d/%d/%d?dpiType=webrd&lang=zh_cn&pack=%s&version=3.3.2", new Object[]{Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2), ConfigableConst.f2123c}));
                } catch (Throwable th) {
                    return null;
                }
            }
        }), this, true);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public IAMapDelegate mo8743a() {
        return this.f1270e;
    }

    /* renamed from: a */
    public void mo8745a(GL10 gl10) {
        try {
            Iterator it = this.f1268c.iterator();
            while (it.hasNext()) {
                Util.m2360a(gl10, ((Integer) it.next()).intValue());
            }
            this.f1268c.clear();
            this.f1269d.drawTiles(gl10);
            it = this.f1266a.iterator();
            while (it.hasNext()) {
                ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
                if (iTileOverlayDelegate.isVisible()) {
                    iTileOverlayDelegate.drawTiles(gl10);
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public void mo8747b() {
        Iterator it = this.f1266a.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.remove();
            }
        }
        this.f1266a.clear();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: c */
    public void mo8750c() {
        Object[] toArray = this.f1266a.toArray();
        Arrays.sort(toArray, this.f1267b);
        this.f1266a.clear();
        for (Object obj : toArray) {
            this.f1266a.add((ITileOverlayDelegate) obj);
        }
    }

    /* renamed from: a */
    public void mo8744a(ITileOverlayDelegate iTileOverlayDelegate) {
        mo8749b(iTileOverlayDelegate);
        this.f1266a.add(iTileOverlayDelegate);
        mo8750c();
    }

    /* renamed from: b */
    public boolean mo8749b(ITileOverlayDelegate iTileOverlayDelegate) {
        return this.f1266a.remove(iTileOverlayDelegate);
    }

    /* renamed from: a */
    public void mo8746a(boolean z) {
        this.f1269d.refresh(z);
        Iterator it = this.f1266a.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null && iTileOverlayDelegate.isVisible()) {
                iTileOverlayDelegate.refresh(z);
            }
        }
    }

    /* renamed from: d */
    public void mo8751d() {
        this.f1269d.onResume();
        Iterator it = this.f1266a.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.onResume();
            }
        }
    }

    /* renamed from: b */
    public void mo8748b(boolean z) {
        this.f1269d.onFling(z);
        Iterator it = this.f1266a.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.onFling(z);
            }
        }
    }

    /* renamed from: e */
    public void mo8752e() {
        this.f1269d.remove();
        this.f1269d = null;
    }

    /* renamed from: f */
    public void mo8753f() {
        this.f1269d.reLoadTexture();
        Iterator it = this.f1266a.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.reLoadTexture();
            }
        }
    }
}
