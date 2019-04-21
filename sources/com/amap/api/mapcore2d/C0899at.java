package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Point;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import com.amap.api.maps2d.model.LatLng;

/* compiled from: MapOverlayViewGroup */
/* renamed from: com.amap.api.mapcore2d.at */
class C0899at extends ViewGroup {
    /* renamed from: a */
    private IAMapDelegate f2300a;

    /* compiled from: MapOverlayViewGroup */
    /* renamed from: com.amap.api.mapcore2d.at$a */
    public static class C0898a extends LayoutParams {
        /* renamed from: a */
        public int f2295a;
        /* renamed from: b */
        public LatLng f2296b;
        /* renamed from: c */
        public int f2297c;
        /* renamed from: d */
        public int f2298d;
        /* renamed from: e */
        public int f2299e;

        public C0898a(int i, int i2, LatLng latLng, int i3, int i4, int i5) {
            super(i, i2);
            this.f2295a = 1;
            this.f2296b = null;
            this.f2297c = 0;
            this.f2298d = 0;
            this.f2299e = 51;
            this.f2295a = 0;
            this.f2296b = latLng;
            this.f2297c = i3;
            this.f2298d = i4;
            this.f2299e = i5;
        }

        public C0898a(LayoutParams layoutParams) {
            super(layoutParams);
            this.f2295a = 1;
            this.f2296b = null;
            this.f2297c = 0;
            this.f2298d = 0;
            this.f2299e = 51;
        }
    }

    public C0899at(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.f2300a = iAMapDelegate;
        setWillNotDraw(false);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt != null) {
                if (childAt.getLayoutParams() instanceof C0898a) {
                    C0898a c0898a = (C0898a) childAt.getLayoutParams();
                    if (c0898a.f2295a == 0) {
                        m3209b(childAt, c0898a);
                    } else {
                        m3207a(childAt, c0898a);
                    }
                } else {
                    m3207a(childAt, new C0898a(childAt.getLayoutParams()));
                }
            }
        }
    }

    /* renamed from: a */
    public void mo9839a() {
        onLayout(false, 0, 0, 0, 0);
    }

    /* renamed from: a */
    private void m3207a(View view, C0898a c0898a) {
        int[] iArr = new int[2];
        m3206a(view, c0898a.width, c0898a.height, iArr);
        m3205a(view, iArr[0], iArr[1], c0898a.f2297c, c0898a.f2298d, c0898a.f2299e);
    }

    /* renamed from: b */
    private void m3209b(View view, C0898a c0898a) {
        String str = "layoutMap";
        int[] iArr = new int[2];
        m3206a(view, c0898a.width, c0898a.height, iArr);
        if (view instanceof C0952cb) {
            m3208a((C0952cb) view, iArr, c0898a.f2299e);
        } else if (view instanceof C0889ao) {
            m3205a(view, iArr[0], iArr[1], getWidth() - iArr[0], iArr[1], c0898a.f2299e);
        } else if (view instanceof C1040o) {
            m3205a(view, iArr[0], iArr[1], 0, 0, c0898a.f2299e);
        } else if (c0898a.f2296b != null) {
            Point a;
            try {
                a = this.f2300a.mo9999s().mo9908a(new GeoPoint((int) (c0898a.f2296b.latitude * 1000000.0d), (int) (c0898a.f2296b.longitude * 1000000.0d)), null);
            } catch (RemoteException e) {
                C0955ck.m3888a(e, "MapOverlayViewGroup", str);
                a = null;
            }
            if (a != null) {
                a.x += c0898a.f2297c;
                a.y += c0898a.f2298d;
                m3205a(view, iArr[0], iArr[1], a.x, a.y, c0898a.f2299e);
            }
        }
    }

    /* renamed from: a */
    private void m3208a(C0952cb c0952cb, int[] iArr, int i) {
        int b = c0952cb.mo10151b();
        if (b == 1) {
            m3205a(c0952cb, iArr[0], iArr[1], getWidth() - iArr[0], (getHeight() + iArr[1]) / 2, i);
        } else if (b == 0) {
            m3205a(c0952cb, iArr[0], iArr[1], getWidth() - iArr[0], getHeight(), i);
        }
    }

    /* renamed from: a */
    private void m3206a(View view, int i, int i2, int[] iArr) {
        if (view instanceof ListView) {
            View view2 = (View) view.getParent();
            if (view2 != null) {
                iArr[0] = view2.getWidth();
                iArr[1] = view2.getHeight();
            }
        }
        if (i <= 0 || i2 <= 0) {
            view.measure(0, 0);
        }
        if (i == -2) {
            iArr[0] = view.getMeasuredWidth();
        } else if (i == -1) {
            iArr[0] = getMeasuredWidth();
        } else {
            iArr[0] = i;
        }
        if (i2 == -2) {
            iArr[1] = view.getMeasuredHeight();
        } else if (i2 == -1) {
            iArr[1] = getMeasuredHeight();
        } else {
            iArr[1] = i2;
        }
    }

    /* renamed from: a */
    private void m3205a(View view, int i, int i2, int i3, int i4, int i5) {
        int i6 = i5 & 7;
        int i7 = i5 & 112;
        if (i6 == 5) {
            i3 -= i;
        } else if (i6 == 1) {
            i3 -= i / 2;
        }
        if (i7 == 80) {
            i4 -= i2;
        } else if (i7 == 16) {
            i4 -= i2 / 2;
        }
        view.layout(i3, i4, i3 + i, i4 + i2);
    }
}
