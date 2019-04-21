package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.Marker;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.ae */
class MapOverlayImageView extends View {
    /* renamed from: a */
    IAMapDelegate f986a;
    /* renamed from: b */
    C0733a f987b = new C0733a();
    /* renamed from: c */
    private CopyOnWriteArrayList<IMarkerDelegate> f988c = new CopyOnWriteArrayList(new ArrayList(VTMCDataCache.MAXSIZE));
    /* renamed from: d */
    private CopyOnWriteArrayList<OverlayTextureItem> f989d = new CopyOnWriteArrayList();
    /* renamed from: e */
    private CopyOnWriteArrayList<Integer> f990e = new CopyOnWriteArrayList();
    /* renamed from: f */
    private IPoint f991f;
    /* renamed from: g */
    private IMarkerDelegate f992g;
    /* renamed from: h */
    private Handler f993h = new Handler();
    /* renamed from: i */
    private Runnable f994i = new C0734af(this);
    /* renamed from: j */
    private final Handler f995j = new Handler();
    /* renamed from: k */
    private final Runnable f996k = new C0735ag(this);

    /* compiled from: MapOverlayImageView */
    /* renamed from: com.amap.api.mapcore.util.ae$a */
    static class C0733a implements Serializable, Comparator<Object> {
        C0733a() {
        }

        public int compare(Object obj, Object obj2) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) obj;
            IMarkerDelegate iMarkerDelegate2 = (IMarkerDelegate) obj2;
            if (!(iMarkerDelegate == null || iMarkerDelegate2 == null)) {
                try {
                    if (iMarkerDelegate.getZIndex() > iMarkerDelegate2.getZIndex()) {
                        return 1;
                    }
                    if (iMarkerDelegate.getZIndex() < iMarkerDelegate2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    SDKLogHandler.m2563a(th, "MapOverlayImageView", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    /* renamed from: a */
    public IAMapDelegate mo8493a() {
        return this.f986a;
    }

    public MapOverlayImageView(Context context) {
        super(context);
    }

    public MapOverlayImageView(Context context, AttributeSet attributeSet, IAMapDelegate iAMapDelegate) {
        super(context, attributeSet);
        this.f986a = iAMapDelegate;
    }

    /* renamed from: a */
    public synchronized IMarkerDelegate mo8495a(String str) throws RemoteException {
        IMarkerDelegate iMarkerDelegate;
        Iterator it = this.f988c.iterator();
        while (it.hasNext()) {
            iMarkerDelegate = (IMarkerDelegate) it.next();
            if (iMarkerDelegate != null && iMarkerDelegate.getId().equals(str)) {
                break;
            }
        }
        iMarkerDelegate = null;
        return iMarkerDelegate;
    }

    /* renamed from: a */
    public synchronized boolean mo8501a(IMarkerDelegate iMarkerDelegate) {
        return this.f988c.contains(iMarkerDelegate);
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: b */
    public synchronized int mo8502b() {
        return this.f988c.size();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0044 A:{SYNTHETIC, Splitter:B:24:0x0044} */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016 A:{Catch:{ RemoteException -> 0x002c }} */
    /* renamed from: b */
    public synchronized void mo8504b(java.lang.String r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        if (r4 == 0) goto L_0x000d;
    L_0x0003:
        r0 = r4.trim();	 Catch:{ RemoteException -> 0x002c }
        r0 = r0.length();	 Catch:{ RemoteException -> 0x002c }
        if (r0 != 0) goto L_0x0039;
    L_0x000d:
        r0 = 1;
    L_0x000e:
        r1 = 0;
        r3.f992g = r1;	 Catch:{ RemoteException -> 0x002c }
        r1 = 0;
        r3.f991f = r1;	 Catch:{ RemoteException -> 0x002c }
        if (r0 == 0) goto L_0x0044;
    L_0x0016:
        r0 = r3.f988c;	 Catch:{ RemoteException -> 0x002c }
        r1 = r0.iterator();	 Catch:{ RemoteException -> 0x002c }
    L_0x001c:
        r0 = r1.hasNext();	 Catch:{ RemoteException -> 0x002c }
        if (r0 == 0) goto L_0x003b;
    L_0x0022:
        r0 = r1.next();	 Catch:{ RemoteException -> 0x002c }
        r0 = (com.autonavi.amap.mapcore.interfaces.IMarkerDelegate) r0;	 Catch:{ RemoteException -> 0x002c }
        r0.remove();	 Catch:{ RemoteException -> 0x002c }
        goto L_0x001c;
    L_0x002c:
        r0 = move-exception;
        r1 = "MapOverlayImageView";
        r2 = "clear";
        com.amap.api.mapcore.util.SDKLogHandler.m2563a(r0, r1, r2);	 Catch:{ all -> 0x0041 }
        r0.printStackTrace();	 Catch:{ all -> 0x0041 }
    L_0x0037:
        monitor-exit(r3);
        return;
    L_0x0039:
        r0 = 0;
        goto L_0x000e;
    L_0x003b:
        r0 = r3.f988c;	 Catch:{ RemoteException -> 0x002c }
        r0.clear();	 Catch:{ RemoteException -> 0x002c }
        goto L_0x0037;
    L_0x0041:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x0044:
        r0 = r3.f988c;	 Catch:{ RemoteException -> 0x002c }
        r1 = r0.iterator();	 Catch:{ RemoteException -> 0x002c }
    L_0x004a:
        r0 = r1.hasNext();	 Catch:{ RemoteException -> 0x002c }
        if (r0 == 0) goto L_0x0037;
    L_0x0050:
        r0 = r1.next();	 Catch:{ RemoteException -> 0x002c }
        r0 = (com.autonavi.amap.mapcore.interfaces.IMarkerDelegate) r0;	 Catch:{ RemoteException -> 0x002c }
        r2 = r0.getId();	 Catch:{ RemoteException -> 0x002c }
        r2 = r4.equals(r2);	 Catch:{ RemoteException -> 0x002c }
        if (r2 != 0) goto L_0x004a;
    L_0x0060:
        r0.remove();	 Catch:{ RemoteException -> 0x002c }
        goto L_0x004a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.MapOverlayImageView.mo8504b(java.lang.String):void");
    }

    /* renamed from: b */
    public synchronized void mo8503b(IMarkerDelegate iMarkerDelegate) {
        this.f988c.add(iMarkerDelegate);
        mo8516i();
    }

    /* renamed from: c */
    public synchronized boolean mo8507c(IMarkerDelegate iMarkerDelegate) {
        mo8513f(iMarkerDelegate);
        return this.f988c.remove(iMarkerDelegate);
    }

    /* renamed from: d */
    public synchronized void mo8508d(IMarkerDelegate iMarkerDelegate) {
        try {
            if (this.f988c.remove(iMarkerDelegate)) {
                m1520l();
                this.f988c.add(iMarkerDelegate);
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MapOverlayImageView", "set2Top");
        }
        return;
    }

    /* renamed from: e */
    public void mo8511e(IMarkerDelegate iMarkerDelegate) {
        if (this.f991f == null) {
            this.f991f = new IPoint();
        }
        Rect rect = iMarkerDelegate.getRect();
        this.f991f = new IPoint(rect.left + (rect.width() / 2), rect.top);
        this.f992g = iMarkerDelegate;
        try {
            this.f986a.showInfoWindow(this.f992g);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MapOverlayImageView", "showInfoWindow");
            th.printStackTrace();
        }
    }

    /* renamed from: f */
    public void mo8513f(IMarkerDelegate iMarkerDelegate) {
        try {
            if (iMarkerDelegate.isInfoWindowShown()) {
                this.f986a.hiddenInfoWindowShown();
                this.f992g = null;
            } else if (this.f992g != null && this.f992g.getId() == iMarkerDelegate.getId()) {
                this.f992g = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: c */
    public synchronized void mo8506c() {
        Iterator it = this.f988c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            try {
                if (iMarkerDelegate.isVisible()) {
                    iMarkerDelegate.calFPoint();
                }
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "MapOverlayImageView", "calFPoint");
                th.printStackTrace();
            }
        }
    }

    /* renamed from: l */
    private void m1520l() {
        try {
            ArrayList arrayList = new ArrayList(this.f988c);
            Collections.sort(arrayList, this.f987b);
            this.f988c = new CopyOnWriteArrayList(arrayList);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MapOverlayImageView", "changeOverlayIndex");
        }
    }

    /* renamed from: a */
    public void mo8499a(GL10 gl10) {
        Iterator it = this.f990e.iterator();
        while (it.hasNext()) {
            gl10.glDeleteTextures(1, new int[]{((Integer) it.next()).intValue()}, 0);
            this.f986a.deleteTexsureId(r0.intValue());
        }
        this.f990e.clear();
        if (!(this.f992g == null || this.f992g.isViewMode())) {
            mo8518k();
        }
        it = this.f988c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            if (iMarkerDelegate.checkInBounds() || iMarkerDelegate.isInfoWindowShown()) {
                iMarkerDelegate.drawMarker(gl10, this.f986a);
            }
        }
    }

    /* renamed from: d */
    public synchronized boolean mo8509d() {
        boolean z;
        Iterator it = this.f988c.iterator();
        while (it.hasNext()) {
            if (!((IMarkerDelegate) it.next()).isAllowLow()) {
                z = false;
                break;
            }
        }
        z = true;
        return z;
    }

    /* renamed from: e */
    public IMarkerDelegate mo8510e() {
        return this.f992g;
    }

    /* renamed from: a */
    public IMarkerDelegate mo8494a(MotionEvent motionEvent) {
        Iterator it = this.f988c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            if ((iMarkerDelegate instanceof MarkerDelegateImp) && mo8500a(iMarkerDelegate.getRect(), (int) motionEvent.getX(), (int) motionEvent.getY())) {
                this.f992g = iMarkerDelegate;
                return this.f992g;
            }
        }
        return null;
    }

    /* renamed from: a */
    public synchronized void mo8497a(OverlayTextureItem overlayTextureItem) {
        if (overlayTextureItem != null) {
            if (overlayTextureItem.mo8622b() != 0) {
                this.f989d.add(overlayTextureItem);
            }
        }
    }

    /* renamed from: a */
    public synchronized void mo8496a(int i) {
        Iterator it = this.f989d.iterator();
        while (it.hasNext()) {
            OverlayTextureItem overlayTextureItem = (OverlayTextureItem) it.next();
            if (overlayTextureItem.mo8622b() == i) {
                this.f989d.remove(overlayTextureItem);
            }
        }
    }

    /* renamed from: a */
    public void mo8498a(Integer num) {
        if (num.intValue() != 0) {
            this.f990e.add(num);
        }
    }

    /* renamed from: a */
    public synchronized int mo8492a(BitmapDescriptor bitmapDescriptor) {
        int b;
        if (bitmapDescriptor != null) {
            if (!(bitmapDescriptor.getBitmap() == null || bitmapDescriptor.getBitmap().isRecycled())) {
                for (int i = 0; i < this.f989d.size(); i++) {
                    OverlayTextureItem overlayTextureItem = (OverlayTextureItem) this.f989d.get(i);
                    if (overlayTextureItem.mo8621a().equals(bitmapDescriptor)) {
                        b = overlayTextureItem.mo8622b();
                        break;
                    }
                }
                b = 0;
            }
        }
        b = 0;
        return b;
    }

    /* renamed from: f */
    public synchronized void mo8512f() {
        try {
            Iterator it = this.f988c.iterator();
            while (it.hasNext()) {
                IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
                if (iMarkerDelegate != null) {
                    iMarkerDelegate.destroy();
                }
            }
            mo8504b(null);
            it = this.f989d.iterator();
            while (it.hasNext()) {
                ((OverlayTextureItem) it.next()).mo8621a().recycle();
            }
            this.f989d.clear();
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MapOverlayImageView", "destroy");
            th.printStackTrace();
            Log.d("amapApi", "MapOverlayImageView clear erro" + th.getMessage());
        }
        return;
    }

    /* renamed from: b */
    public boolean mo8505b(MotionEvent motionEvent) throws RemoteException {
        Iterator it = this.f988c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            if ((iMarkerDelegate instanceof MarkerDelegateImp) && iMarkerDelegate.isVisible()) {
                Rect rect = iMarkerDelegate.getRect();
                boolean a = mo8500a(rect, (int) motionEvent.getX(), (int) motionEvent.getY());
                if (a) {
                    this.f991f = new IPoint(rect.left + (rect.width() / 2), rect.top);
                    this.f992g = iMarkerDelegate;
                    return a;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo8500a(Rect rect, int i, int i2) {
        return rect.contains(i, i2);
    }

    /* renamed from: g */
    public synchronized List<Marker> mo8514g() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        try {
            Rect rect = new Rect(0, 0, this.f986a.getMapWidth(), this.f986a.getMapHeight());
            IPoint iPoint = new IPoint();
            Iterator it = this.f988c.iterator();
            while (it.hasNext()) {
                IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
                if (!(iMarkerDelegate instanceof TextDelegateImp)) {
                    FPoint mapPosition = iMarkerDelegate.getMapPosition();
                    if (mapPosition != null) {
                        this.f986a.getMapProjection().map2Win(mapPosition.f4560x, mapPosition.f4561y, iPoint);
                        if (mo8500a(rect, iPoint.f4562x, iPoint.f4563y)) {
                            arrayList.add(new Marker(iMarkerDelegate));
                        }
                    }
                }
            }
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "MapOverlayImageView", "getMapScreenMarkers");
            th.printStackTrace();
        }
        return arrayList;
    }

    /* renamed from: h */
    public synchronized void mo8515h() {
        Iterator it = this.f988c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            if (iMarkerDelegate.isDestory()) {
                iMarkerDelegate.realDestroy();
            }
        }
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: i */
    public synchronized void mo8516i() {
        this.f993h.removeCallbacks(this.f994i);
        this.f993h.postDelayed(this.f994i, 10);
    }

    /* renamed from: j */
    public void mo8517j() {
        Iterator it = this.f988c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            if (iMarkerDelegate != null) {
                iMarkerDelegate.reLoadTexture();
            }
        }
        if (this.f989d != null) {
            this.f989d.clear();
        }
    }

    /* renamed from: k */
    public void mo8518k() {
        this.f995j.post(this.f996k);
    }
}
