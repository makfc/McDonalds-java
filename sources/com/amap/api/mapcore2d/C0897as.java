package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: MapOverlayImageView */
/* renamed from: com.amap.api.mapcore2d.as */
class C0897as extends View {
    /* renamed from: a */
    AMapDelegateImpGLSurfaceView f2283a;
    /* renamed from: b */
    C0896a f2284b = new C0896a();
    /* renamed from: c */
    private ArrayList<ITextDelegate> f2285c = new ArrayList(8);
    /* renamed from: d */
    private ArrayList<IMarkerDelegate> f2286d = new ArrayList(8);
    /* renamed from: e */
    private volatile int f2287e = 0;
    /* renamed from: f */
    private Handler f2288f = new Handler();
    /* renamed from: g */
    private Runnable f2289g = new C08951();
    /* renamed from: h */
    private IPoint f2290h;
    /* renamed from: i */
    private IMarkerDelegate f2291i;
    /* renamed from: j */
    private IMarkerDelegate f2292j = null;
    /* renamed from: k */
    private float f2293k = 0.0f;
    /* renamed from: l */
    private CopyOnWriteArrayList<Integer> f2294l = new CopyOnWriteArrayList();

    /* compiled from: MapOverlayImageView */
    /* renamed from: com.amap.api.mapcore2d.as$1 */
    class C08951 implements Runnable {
        C08951() {
        }

        public synchronized void run() {
            try {
                Collections.sort(C0897as.this.f2286d, C0897as.this.f2284b);
                Collections.sort(C0897as.this.f2285c, C0897as.this.f2284b);
                C0897as.this.invalidate();
            } catch (Throwable th) {
                C0990dd.m4098b(th, "MapOverlayImageView", "changeOverlayIndex");
            }
            return;
        }
    }

    /* compiled from: MapOverlayImageView */
    /* renamed from: com.amap.api.mapcore2d.as$a */
    static class C0896a implements Serializable, Comparator<IMarkerText> {
        C0896a() {
        }

        /* renamed from: a */
        public int compare(IMarkerText iMarkerText, IMarkerText iMarkerText2) {
            if (!(iMarkerText == null || iMarkerText2 == null)) {
                try {
                    if (iMarkerText.mo9613r() > iMarkerText2.mo9613r()) {
                        return 1;
                    }
                    if (iMarkerText.mo9613r() < iMarkerText2.mo9613r()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    C0955ck.m3888a(th, "MapOverlayImageView", "compare");
                }
            }
            return 0;
        }
    }

    /* renamed from: h */
    private int m3183h() {
        int i = this.f2287e;
        this.f2287e = i + 1;
        return i;
    }

    /* renamed from: a */
    public AMapDelegateImpGLSurfaceView mo9821a() {
        return this.f2283a;
    }

    public C0897as(Context context, AttributeSet attributeSet, AMapDelegateImpGLSurfaceView aMapDelegateImpGLSurfaceView) {
        super(context, attributeSet);
        this.f2283a = aMapDelegateImpGLSurfaceView;
    }

    /* renamed from: a */
    public synchronized IMarkerDelegate mo9820a(String str) throws RemoteException {
        IMarkerDelegate iMarkerDelegate;
        Iterator it = this.f2286d.iterator();
        while (it.hasNext()) {
            iMarkerDelegate = (IMarkerDelegate) it.next();
            if (iMarkerDelegate != null && iMarkerDelegate.mo9634d().equals(str)) {
                break;
            }
        }
        iMarkerDelegate = null;
        return iMarkerDelegate;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public int mo9826b() {
        return this.f2286d.size();
    }

    /* renamed from: c */
    public synchronized void mo9830c() {
        String str = "clear";
        try {
            if (this.f2286d != null) {
                Iterator it = this.f2286d.iterator();
                while (it.hasNext()) {
                    ((IMarkerDelegate) it.next()).mo9642l();
                }
                this.f2286d.clear();
            }
            if (this.f2285c != null) {
                this.f2285c.clear();
            }
        } catch (Throwable th) {
            C0955ck.m3888a(th, "MapOverlayImageView", str);
        }
        return;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: d */
    public void mo9832d() {
        this.f2288f.removeCallbacks(this.f2289g);
        this.f2288f.postDelayed(this.f2289g, 5);
    }

    /* renamed from: a */
    public synchronized void mo9823a(IMarkerDelegate iMarkerDelegate) {
        String str = "addMarker";
        try {
            mo9835e(iMarkerDelegate);
            iMarkerDelegate.mo9611b(m3183h());
            this.f2286d.remove(iMarkerDelegate);
            this.f2286d.add(iMarkerDelegate);
            mo9832d();
        } catch (Throwable th) {
            C0955ck.m3888a(th, "MapOverlayImageView", str);
        }
        return;
    }

    /* renamed from: a */
    public synchronized void mo9824a(ITextDelegate iTextDelegate) throws RemoteException {
        this.f2285c.remove(iTextDelegate);
        iTextDelegate.mo9611b(m3183h());
        this.f2285c.add(iTextDelegate);
        mo9832d();
    }

    /* renamed from: b */
    public synchronized void mo9827b(ITextDelegate iTextDelegate) {
        this.f2285c.remove(iTextDelegate);
        postInvalidate();
    }

    /* renamed from: b */
    public synchronized boolean mo9829b(IMarkerDelegate iMarkerDelegate) {
        boolean remove;
        mo9835e(iMarkerDelegate);
        remove = this.f2286d.remove(iMarkerDelegate);
        postInvalidate();
        return remove;
    }

    /* renamed from: c */
    public synchronized void mo9831c(IMarkerDelegate iMarkerDelegate) {
        if (this.f2292j != iMarkerDelegate) {
            if (this.f2292j != null && this.f2292j.mo9613r() == 2.14748365E9f) {
                this.f2292j.mo9610b(this.f2293k);
            }
            this.f2293k = iMarkerDelegate.mo9613r();
            this.f2292j = iMarkerDelegate;
            iMarkerDelegate.mo9610b(2.14748365E9f);
            mo9832d();
        }
    }

    /* renamed from: d */
    public void mo9833d(IMarkerDelegate iMarkerDelegate) {
        String str = "showInfoWindow";
        if (this.f2290h == null) {
            this.f2290h = new IPoint();
        }
        Rect b = iMarkerDelegate.mo9630b();
        this.f2290h = new IPoint(b.left + (iMarkerDelegate.mo9644n() / 2), b.top);
        this.f2291i = iMarkerDelegate;
        try {
            this.f2283a.mo10019a(mo9834e());
        } catch (RemoteException e) {
            C0955ck.m3888a(e, "MapOverlayImageView", str);
        }
    }

    /* renamed from: e */
    public void mo9835e(IMarkerDelegate iMarkerDelegate) {
        if (mo9837f(iMarkerDelegate)) {
            this.f2283a.mo10053t();
        }
    }

    /* renamed from: f */
    public boolean mo9837f(IMarkerDelegate iMarkerDelegate) {
        return this.f2283a.mo10025b(iMarkerDelegate);
    }

    /* renamed from: a */
    public synchronized void mo9822a(Canvas canvas) {
        m3184i();
        Rect rect = new Rect(0, 0, this.f2283a.mo9977c(), this.f2283a.mo9981d());
        IPoint iPoint = new IPoint();
        Iterator it = this.f2286d.iterator();
        Iterator it2 = this.f2285c.iterator();
        IMarkerDelegate b = m3181b(it, rect, iPoint);
        ITextDelegate a = m3179a(it2, rect, iPoint);
        while (true) {
            if (b != null || a != null) {
                if (b == null) {
                    a.mo9686a(canvas);
                    a = m3179a(it2, rect, iPoint);
                } else if (a == null) {
                    b.mo9622a(canvas, this.f2283a);
                    b = m3181b(it, rect, iPoint);
                } else if (b.mo9613r() < a.mo9613r() || (b.mo9613r() == a.mo9613r() && b.mo9617v() < a.mo9617v())) {
                    b.mo9622a(canvas, this.f2283a);
                    b = m3181b(it, rect, iPoint);
                } else {
                    a.mo9686a(canvas);
                    a = m3179a(it2, rect, iPoint);
                }
            }
        }
    }

    /* renamed from: a */
    private ITextDelegate m3179a(Iterator<ITextDelegate> it, Rect rect, IPoint iPoint) {
        while (it.hasNext()) {
            ITextDelegate iTextDelegate = (ITextDelegate) it.next();
            LatLng t = iTextDelegate.mo9615t();
            if (t != null) {
                this.f2283a.mo9971b(t.latitude, t.longitude, iPoint);
                if (mo9825a(rect, iPoint.f2229a, iPoint.f2230b)) {
                    return iTextDelegate;
                }
            }
        }
        return null;
    }

    /* renamed from: b */
    private IMarkerDelegate m3181b(Iterator<IMarkerDelegate> it, Rect rect, IPoint iPoint) {
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            LatLng c = iMarkerDelegate.mo9633c();
            if (c != null) {
                this.f2283a.mo9971b(c.latitude, c.longitude, iPoint);
                if (mo9825a(rect, iPoint.f2229a, iPoint.f2230b)) {
                    return iMarkerDelegate;
                }
            }
        }
        return null;
    }

    /* renamed from: i */
    private void m3184i() {
        String str = "redrawInfoWindow";
        Iterator it = this.f2286d.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            if (this.f2291i != null && this.f2291i.mo9634d().equals(iMarkerDelegate.mo9634d())) {
                try {
                    if (this.f2291i.mo9647q()) {
                        return;
                    }
                } catch (RemoteException e) {
                    C0955ck.m3888a(e, "MapOverlayImageView", str);
                }
                Rect b = iMarkerDelegate.mo9630b();
                this.f2290h = new IPoint((iMarkerDelegate.mo9644n() / 2) + b.left, b.top);
                this.f2283a.mo10054u();
            }
        }
    }

    /* renamed from: e */
    public IMarkerDelegate mo9834e() {
        return this.f2291i;
    }

    /* renamed from: a */
    public synchronized IMarkerDelegate mo9819a(MotionEvent motionEvent) {
        IMarkerDelegate iMarkerDelegate;
        for (int size = this.f2286d.size() - 1; size >= 0; size--) {
            iMarkerDelegate = (IMarkerDelegate) this.f2286d.get(size);
            if (mo9825a(iMarkerDelegate.mo9630b(), (int) motionEvent.getX(), (int) motionEvent.getY())) {
                break;
            }
        }
        iMarkerDelegate = null;
        return iMarkerDelegate;
    }

    /* renamed from: f */
    public void mo9836f() {
        String str = "destory";
        try {
            if (this.f2288f != null) {
                this.f2288f.removeCallbacksAndMessages(null);
            }
            mo9830c();
        } catch (Exception e) {
            C0955ck.m3888a(e, "MapOverlayImageView", str);
            Log.d("amapApi", "MapOverlayImageView clear erro" + e.getMessage());
        }
    }

    /* renamed from: b */
    public synchronized boolean mo9828b(MotionEvent motionEvent) {
        boolean z;
        for (int size = this.f2286d.size() - 1; size >= 0; size--) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) this.f2286d.get(size);
            Rect b = iMarkerDelegate.mo9630b();
            boolean a = mo9825a(b, (int) motionEvent.getX(), (int) motionEvent.getY());
            if (a) {
                this.f2290h = new IPoint(b.left + (iMarkerDelegate.mo9644n() / 2), b.top);
                this.f2291i = iMarkerDelegate;
                z = a;
                break;
            }
        }
        z = false;
        return z;
    }

    /* renamed from: g */
    public synchronized List<Marker> mo9838g() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Rect rect = new Rect(0, 0, this.f2283a.mo9977c(), this.f2283a.mo9981d());
        IPoint iPoint = new IPoint();
        Iterator it = this.f2286d.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            LatLng c = iMarkerDelegate.mo9633c();
            if (c == null) {
                break;
            }
            this.f2283a.mo9971b(c.latitude, c.longitude, iPoint);
            if (mo9825a(rect, iPoint.f2229a, iPoint.f2230b)) {
                arrayList.add(new Marker(iMarkerDelegate));
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public boolean mo9825a(Rect rect, int i, int i2) {
        return rect.contains(i, i2);
    }
}
