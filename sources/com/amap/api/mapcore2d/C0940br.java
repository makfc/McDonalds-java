package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: TileOverlayView */
/* renamed from: com.amap.api.mapcore2d.br */
class C0940br extends View {
    /* renamed from: a */
    CopyOnWriteArrayList<Integer> f2609a = new CopyOnWriteArrayList();
    /* renamed from: b */
    private IAMapDelegate f2610b;
    /* renamed from: c */
    private CopyOnWriteArrayList<ITileOverlayDelegate> f2611c = new CopyOnWriteArrayList();
    /* renamed from: d */
    private C0939a f2612d = new C0939a();

    /* compiled from: TileOverlayView */
    /* renamed from: com.amap.api.mapcore2d.br$a */
    private class C0939a implements Comparator<Object> {
        private C0939a() {
        }

        public int compare(Object obj, Object obj2) {
            String str = "compare";
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) obj;
            ITileOverlayDelegate iTileOverlayDelegate2 = (ITileOverlayDelegate) obj2;
            if (!(iTileOverlayDelegate == null || iTileOverlayDelegate2 == null)) {
                try {
                    if (iTileOverlayDelegate.mo9708d() > iTileOverlayDelegate2.mo9708d()) {
                        return 1;
                    }
                    if (iTileOverlayDelegate.mo9708d() < iTileOverlayDelegate2.mo9708d()) {
                        return -1;
                    }
                } catch (Exception e) {
                    C0955ck.m3888a(e, "TileOverlayView", str);
                }
            }
            return 0;
        }
    }

    public C0940br(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.f2610b = iAMapDelegate;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo10100a(Canvas canvas) {
        Iterator it = this.f2611c.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate.mo9709e()) {
                iTileOverlayDelegate.mo9702a(canvas);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public boolean mo10103a() {
        if (this.f2611c.size() > 0) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public void mo10104b() {
        Iterator it = this.f2611c.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.mo9700a();
            }
        }
        this.f2611c.clear();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: c */
    public void mo10106c() {
        Object[] toArray = this.f2611c.toArray();
        Arrays.sort(toArray, this.f2612d);
        this.f2611c.clear();
        for (Object obj : toArray) {
            this.f2611c.add((ITileOverlayDelegate) obj);
        }
    }

    /* renamed from: a */
    public void mo10101a(ITileOverlayDelegate iTileOverlayDelegate) {
        mo10105b(iTileOverlayDelegate);
        this.f2611c.add(iTileOverlayDelegate);
        mo10106c();
    }

    /* renamed from: b */
    public boolean mo10105b(ITileOverlayDelegate iTileOverlayDelegate) {
        return this.f2611c.remove(iTileOverlayDelegate);
    }

    /* renamed from: a */
    public void mo10102a(boolean z) {
        Iterator it = this.f2611c.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null && iTileOverlayDelegate.mo9709e()) {
                iTileOverlayDelegate.mo9706b(z);
            }
        }
    }

    /* renamed from: d */
    public void mo10107d() {
        Iterator it = this.f2611c.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.mo9711g();
            }
        }
    }

    /* renamed from: e */
    public void mo10108e() {
        Iterator it = this.f2611c.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.mo9712h();
            }
        }
    }

    /* renamed from: f */
    public void mo10109f() {
        Iterator it = this.f2611c.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.mo9713i();
            }
        }
    }
}
