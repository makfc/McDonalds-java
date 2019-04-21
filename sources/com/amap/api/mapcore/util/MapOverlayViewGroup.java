package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;

/* renamed from: com.amap.api.mapcore.util.ah */
class MapOverlayViewGroup extends ViewGroup {
    /* renamed from: a */
    private IAMapDelegate f1003a;

    /* compiled from: MapOverlayViewGroup */
    /* renamed from: com.amap.api.mapcore.util.ah$a */
    public static class C0736a extends LayoutParams {
        /* renamed from: a */
        public FPoint f999a = null;
        /* renamed from: b */
        public int f1000b = 0;
        /* renamed from: c */
        public int f1001c = 0;
        /* renamed from: d */
        public int f1002d = 51;

        public C0736a(int i, int i2, FPoint fPoint, int i3, int i4, int i5) {
            super(i, i2);
            this.f999a = fPoint;
            this.f1000b = i3;
            this.f1001c = i4;
            this.f1002d = i5;
        }

        public C0736a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public C0736a(LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public MapOverlayViewGroup(Context context) {
        super(context);
    }

    public MapOverlayViewGroup(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.f1003a = iAMapDelegate;
        setBackgroundColor(-1);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt != null) {
                if (childAt.getLayoutParams() instanceof C0736a) {
                    m1551a(childAt, (C0736a) childAt.getLayoutParams());
                } else {
                    m1550a(childAt, childAt.getLayoutParams());
                }
            }
        }
    }

    /* renamed from: a */
    private void m1550a(View view, LayoutParams layoutParams) {
        int[] iArr = new int[2];
        m1549a(view, layoutParams.width, layoutParams.height, iArr);
        if (view instanceof IndoorFloorSwitchView) {
            m1548a(view, iArr[0], iArr[1], 20, (this.f1003a.getWaterMarkerPositon().y - 80) - iArr[1], 51);
            return;
        }
        m1548a(view, iArr[0], iArr[1], 0, 0, 51);
    }

    /* renamed from: a */
    private void m1551a(View view, C0736a c0736a) {
        int[] iArr = new int[2];
        m1549a(view, c0736a.width, c0736a.height, iArr);
        if (view instanceof ZoomControllerView) {
            m1548a(view, iArr[0], iArr[1], getWidth() - iArr[0], getHeight(), c0736a.f1002d);
        } else if (view instanceof LocationView) {
            m1548a(view, iArr[0], iArr[1], getWidth() - iArr[0], iArr[1], c0736a.f1002d);
        } else if (view instanceof CompassView) {
            m1548a(view, iArr[0], iArr[1], 0, 0, c0736a.f1002d);
        } else if (c0736a.f999a != null) {
            IPoint iPoint = new IPoint();
            this.f1003a.getMapProjection().map2Win(c0736a.f999a.f4560x, c0736a.f999a.f4561y, iPoint);
            iPoint.f4562x += c0736a.f1000b;
            iPoint.f4563y += c0736a.f1001c;
            m1548a(view, iArr[0], iArr[1], iPoint.f4562x, iPoint.f4563y, c0736a.f1002d);
            if (view.getVisibility() == 0) {
                mo8521a();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo8521a() {
    }

    /* renamed from: a */
    private void m1549a(View view, int i, int i2, int[] iArr) {
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
    private void m1548a(View view, int i, int i2, int i3, int i4, int i5) {
        int i6 = i5 & 7;
        int i7 = i5 & 112;
        if (i6 == 5) {
            i3 -= i;
        } else if (i6 == 1) {
            i3 -= i / 2;
        }
        if (i7 == 80) {
            i4 -= i2;
        } else if (i7 == 17) {
            i4 -= i2 / 2;
        } else if (i7 == 16) {
            i4 = (i4 / 2) - (i2 / 2);
        }
        view.layout(i3, i4, i3 + i, i4 + i2);
    }
}
