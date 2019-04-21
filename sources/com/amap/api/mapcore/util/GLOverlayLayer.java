package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IGroundOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IPolylineDelegate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.util.v */
class GLOverlayLayer {
    /* renamed from: c */
    private static int f2169c = 0;
    /* renamed from: a */
    IAMapDelegate f2170a;
    /* renamed from: b */
    C0875a f2171b = new C0875a();
    /* renamed from: d */
    private CopyOnWriteArrayList<IOverlayDelegate> f2172d = new CopyOnWriteArrayList(new ArrayList(VTMCDataCache.MAXSIZE));
    /* renamed from: e */
    private CopyOnWriteArrayList<Integer> f2173e = new CopyOnWriteArrayList();
    /* renamed from: f */
    private Handler f2174f = new Handler();
    /* renamed from: g */
    private Runnable f2175g = new C0876w(this);

    /* compiled from: GLOverlayLayer */
    /* renamed from: com.amap.api.mapcore.util.v$a */
    static class C0875a implements Serializable, Comparator<Object> {
        C0875a() {
        }

        public int compare(Object obj, Object obj2) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) obj;
            IOverlayDelegate iOverlayDelegate2 = (IOverlayDelegate) obj2;
            if (!(iOverlayDelegate == null || iOverlayDelegate2 == null)) {
                try {
                    if (iOverlayDelegate.getZIndex() > iOverlayDelegate2.getZIndex()) {
                        return 1;
                    }
                    if (iOverlayDelegate.getZIndex() < iOverlayDelegate2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    SDKLogHandler.m2563a(th, "GLOverlayLayer", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    /* renamed from: a */
    static String m2918a(String str) {
        f2169c++;
        return str + f2169c;
    }

    public GLOverlayLayer(IAMapDelegate iAMapDelegate) {
        this.f2170a = iAMapDelegate;
    }

    /* renamed from: b */
    public synchronized void mo9565b(String str) {
        if (str != null) {
            try {
                if (str.trim().length() != 0) {
                    Iterator it = this.f2172d.iterator();
                    while (it.hasNext()) {
                        IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it.next();
                        if (!str.equals(iOverlayDelegate.getId())) {
                            this.f2172d.remove(iOverlayDelegate);
                        }
                    }
                }
            } catch (Throwable th) {
                SDKLogHandler.m2563a(th, "GLOverlayLayer", "clear");
                th.printStackTrace();
                Log.d("amapApi", "GLOverlayLayer clear erro" + th.getMessage());
            }
        }
        this.f2172d.clear();
        f2169c = 0;
        return;
    }

    /* renamed from: a */
    public synchronized void mo9560a() {
        try {
            Iterator it = this.f2172d.iterator();
            while (it.hasNext()) {
                ((IOverlayDelegate) it.next()).destroy();
            }
            mo9565b(null);
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "GLOverlayLayer", "destory");
            th.printStackTrace();
            Log.d("amapApi", "GLOverlayLayer destory erro" + th.getMessage());
        }
        return;
    }

    /* renamed from: d */
    private synchronized IOverlayDelegate m2921d(String str) throws RemoteException {
        IOverlayDelegate iOverlayDelegate;
        Iterator it = this.f2172d.iterator();
        while (it.hasNext()) {
            iOverlayDelegate = (IOverlayDelegate) it.next();
            if (iOverlayDelegate != null && iOverlayDelegate.getId().equals(str)) {
                break;
            }
        }
        iOverlayDelegate = null;
        return iOverlayDelegate;
    }

    /* renamed from: a */
    public synchronized void mo9561a(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        this.f2172d.add(iOverlayDelegate);
        mo9564b();
    }

    /* renamed from: c */
    public synchronized boolean mo9567c(String str) throws RemoteException {
        boolean remove;
        IOverlayDelegate d = m2921d(str);
        if (d != null) {
            remove = this.f2172d.remove(d);
        } else {
            remove = false;
        }
        return remove;
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: b */
    public synchronized void mo9564b() {
        this.f2174f.removeCallbacks(this.f2175g);
        this.f2174f.postDelayed(this.f2175g, 10);
    }

    /* renamed from: a */
    public void mo9563a(GL10 gl10, boolean z, int i) {
        Iterator it = this.f2173e.iterator();
        while (it.hasNext()) {
            gl10.glDeleteTextures(1, new int[]{((Integer) it.next()).intValue()}, 0);
            this.f2170a.deleteTexsureId(r0.intValue());
        }
        this.f2173e.clear();
        int size = this.f2172d.size();
        Iterator it2 = this.f2172d.iterator();
        while (it2.hasNext()) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it2.next();
            try {
                if (iOverlayDelegate.isVisible()) {
                    if (size > 20) {
                        if (iOverlayDelegate.checkInBounds()) {
                            if (z) {
                                if (iOverlayDelegate.getZIndex() <= ((float) i)) {
                                    iOverlayDelegate.draw(gl10);
                                }
                            } else if (iOverlayDelegate.getZIndex() > ((float) i)) {
                                iOverlayDelegate.draw(gl10);
                            }
                        }
                    } else if (z) {
                        if (iOverlayDelegate.getZIndex() <= ((float) i)) {
                            iOverlayDelegate.draw(gl10);
                        }
                    } else if (iOverlayDelegate.getZIndex() > ((float) i)) {
                        iOverlayDelegate.draw(gl10);
                    }
                }
            } catch (RemoteException e) {
                SDKLogHandler.m2563a(e, "GLOverlayLayer", "draw");
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void mo9562a(Integer num) {
        if (num.intValue() != 0) {
            this.f2173e.add(num);
        }
    }

    /* renamed from: c */
    public synchronized void mo9566c() {
        Iterator it = this.f2172d.iterator();
        while (it.hasNext()) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it.next();
            if (iOverlayDelegate != null) {
                try {
                    iOverlayDelegate.calMapFPoint();
                } catch (RemoteException e) {
                    SDKLogHandler.m2563a(e, "GLOverlayLayer", "calMapFPoint");
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: d */
    public boolean mo9568d() {
        Iterator it = this.f2172d.iterator();
        while (it.hasNext()) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it.next();
            if (iOverlayDelegate != null && !iOverlayDelegate.isDrawFinish()) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public synchronized IOverlayDelegate mo9559a(LatLng latLng) {
        IOverlayDelegate iOverlayDelegate;
        Iterator it = this.f2172d.iterator();
        while (it.hasNext()) {
            iOverlayDelegate = (IOverlayDelegate) it.next();
            if (iOverlayDelegate != null && iOverlayDelegate.isDrawFinish() && (iOverlayDelegate instanceof IPolylineDelegate) && ((IPolylineDelegate) iOverlayDelegate).contains(latLng)) {
                break;
            }
        }
        iOverlayDelegate = null;
        return iOverlayDelegate;
    }

    /* renamed from: e */
    public void mo9569e() {
        Iterator it = this.f2172d.iterator();
        while (it.hasNext()) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it.next();
            if (iOverlayDelegate != null) {
                if (iOverlayDelegate instanceof IPolylineDelegate) {
                    ((IPolylineDelegate) iOverlayDelegate).reLoadTexture();
                } else if (iOverlayDelegate instanceof IGroundOverlayDelegate) {
                    ((IGroundOverlayDelegate) iOverlayDelegate).reLoadTexture();
                }
            }
        }
    }
}
