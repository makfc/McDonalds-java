package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: GLOverlayLayer */
/* renamed from: com.amap.api.mapcore2d.t */
class C1047t {
    /* renamed from: a */
    private static int f3056a = 0;
    /* renamed from: b */
    private CopyOnWriteArrayList<IOverlayDelegate> f3057b = new CopyOnWriteArrayList();
    /* renamed from: c */
    private C1046a f3058c = new C1046a(this, null);
    /* renamed from: d */
    private Handler f3059d = new Handler();
    /* renamed from: e */
    private Runnable f3060e = new C10451();

    /* compiled from: GLOverlayLayer */
    /* renamed from: com.amap.api.mapcore2d.t$1 */
    class C10451 implements Runnable {
        C10451() {
        }

        public synchronized void run() {
            try {
                Object[] toArray = C1047t.this.f3057b.toArray();
                Arrays.sort(toArray, C1047t.this.f3058c);
                C1047t.this.f3057b.clear();
                for (Object obj : toArray) {
                    C1047t.this.f3057b.add((IOverlayDelegate) obj);
                }
            } catch (Throwable th) {
                C0990dd.m4098b(th, "MapOverlayImageView", "changeOverlayIndex");
            }
            return;
        }
    }

    /* compiled from: GLOverlayLayer */
    /* renamed from: com.amap.api.mapcore2d.t$a */
    private class C1046a implements Comparator<Object> {
        private C1046a() {
        }

        /* synthetic */ C1046a(C1047t c1047t, C10451 c10451) {
            this();
        }

        public int compare(Object obj, Object obj2) {
            String str = "compare";
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) obj;
            IOverlayDelegate iOverlayDelegate2 = (IOverlayDelegate) obj2;
            if (!(iOverlayDelegate == null || iOverlayDelegate2 == null)) {
                try {
                    if (iOverlayDelegate.mo9655d() > iOverlayDelegate2.mo9655d()) {
                        return 1;
                    }
                    if (iOverlayDelegate.mo9655d() < iOverlayDelegate2.mo9655d()) {
                        return -1;
                    }
                } catch (Exception e) {
                    C0955ck.m3888a(e, "GLOverlayLayer", str);
                }
            }
            return 0;
        }
    }

    C1047t() {
    }

    /* renamed from: a */
    static String m4384a(String str) {
        f3056a++;
        return str + f3056a;
    }

    /* renamed from: a */
    public void mo10327a() {
        String str = "clear";
        Iterator it = this.f3057b.iterator();
        while (it.hasNext()) {
            ((IOverlayDelegate) it.next()).mo9658l();
        }
        try {
            it = this.f3057b.iterator();
            while (it.hasNext()) {
                ((IOverlayDelegate) it.next()).mo9658l();
            }
            this.f3057b.clear();
        } catch (Exception e) {
            C0955ck.m3888a(e, "GLOverlayLayer", str);
            Log.d("amapApi", "GLOverlayLayer clear erro" + e.getMessage());
        }
    }

    /* renamed from: b */
    public void mo10330b() {
        String str = "destory";
        try {
            Iterator it = this.f3057b.iterator();
            while (it.hasNext()) {
                ((IOverlayDelegate) it.next()).mo9658l();
            }
            mo10327a();
        } catch (Exception e) {
            C0955ck.m3888a(e, "GLOverlayLayer", str);
            Log.d("amapApi", "GLOverlayLayer destory erro" + e.getMessage());
        }
    }

    /* renamed from: c */
    private IOverlayDelegate m4387c(String str) throws RemoteException {
        Iterator it = this.f3057b.iterator();
        while (it.hasNext()) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it.next();
            if (iOverlayDelegate != null && iOverlayDelegate.mo9654c().equals(str)) {
                return iOverlayDelegate;
            }
        }
        return null;
    }

    /* renamed from: a */
    public void mo10329a(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        mo10331b(iOverlayDelegate.mo9654c());
        this.f3057b.add(iOverlayDelegate);
        m4388c();
    }

    /* renamed from: b */
    public boolean mo10331b(String str) throws RemoteException {
        IOverlayDelegate c = m4387c(str);
        if (c != null) {
            return this.f3057b.remove(c);
        }
        return false;
    }

    /* renamed from: c */
    private void m4388c() {
        this.f3059d.removeCallbacks(this.f3060e);
        this.f3059d.postDelayed(this.f3060e, 10);
    }

    /* renamed from: a */
    public void mo10328a(Canvas canvas) {
        String str = "draw";
        Object[] toArray = this.f3057b.toArray();
        Arrays.sort(toArray, this.f3058c);
        this.f3057b.clear();
        for (Object obj : toArray) {
            try {
                this.f3057b.add((IOverlayDelegate) obj);
            } catch (Throwable th) {
                C0955ck.m3888a(th, "GLOverlayLayer", str);
            }
        }
        int size = this.f3057b.size();
        Iterator it = this.f3057b.iterator();
        while (it.hasNext()) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it.next();
            try {
                if (iOverlayDelegate.mo9656e()) {
                    if (size <= 20) {
                        iOverlayDelegate.mo9649a(canvas);
                    } else if (iOverlayDelegate.mo9651a()) {
                        iOverlayDelegate.mo9649a(canvas);
                    }
                }
            } catch (RemoteException e) {
                C0955ck.m3888a(e, "GLOverlayLayer", str);
            }
        }
    }
}
