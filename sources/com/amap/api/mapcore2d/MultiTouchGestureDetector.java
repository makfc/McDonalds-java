package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: com.amap.api.mapcore2d.ba */
abstract class MultiTouchGestureDetector {
    /* renamed from: j */
    static float f2506j = 1.0f;
    /* renamed from: p */
    private static Method f2507p;
    /* renamed from: q */
    private static Method f2508q;
    /* renamed from: r */
    private static boolean f2509r = false;
    /* renamed from: s */
    private static boolean f2510s = false;
    /* renamed from: a */
    C0921b f2511a;
    /* renamed from: b */
    int f2512b = 0;
    /* renamed from: c */
    Matrix f2513c = new Matrix();
    /* renamed from: d */
    Matrix f2514d = new Matrix();
    /* renamed from: e */
    PointF f2515e = new PointF();
    /* renamed from: f */
    PointF f2516f = new PointF();
    /* renamed from: g */
    PointF f2517g = new PointF();
    /* renamed from: h */
    float f2518h = 1.0f;
    /* renamed from: i */
    float f2519i = 1.0f;
    /* renamed from: k */
    boolean f2520k = false;
    /* renamed from: l */
    boolean f2521l = false;
    /* renamed from: m */
    boolean f2522m = false;
    /* renamed from: n */
    public int f2523n = 0;
    /* renamed from: o */
    public long f2524o = 0;

    /* compiled from: MultiTouchGestureDetector */
    /* renamed from: com.amap.api.mapcore2d.ba$b */
    public interface C0921b {
        /* renamed from: a */
        boolean mo9928a(float f, float f2);

        /* renamed from: a */
        boolean mo9929a(float f, PointF pointF);

        /* renamed from: a */
        boolean mo9930a(Matrix matrix);

        /* renamed from: a */
        boolean mo9931a(PointF pointF);

        /* renamed from: b */
        boolean mo9932b(float f, PointF pointF);
    }

    /* compiled from: MultiTouchGestureDetector */
    /* renamed from: com.amap.api.mapcore2d.ba$a */
    protected static class C0925a extends MultiTouchGestureDetector {
        /* renamed from: p */
        float f2525p;
        /* renamed from: q */
        float f2526q;
        /* renamed from: r */
        float f2527r;
        /* renamed from: s */
        float f2528s;
        /* renamed from: t */
        long f2529t = 0;
        /* renamed from: u */
        int f2530u = 0;
        /* renamed from: v */
        int f2531v = 0;
        /* renamed from: w */
        private long f2532w = 0;

        protected C0925a() {
        }

        /* JADX WARNING: Missing block: B:24:0x011c, code skipped:
            if ((r12.getEventTime() - r11.f2529t) < 30) goto L_0x001f;
     */
        /* renamed from: a */
        public boolean mo10056a(android.view.MotionEvent r12, int r13, int r14) {
            /*
            r11 = this;
            r3 = 1092616192; // 0x41200000 float:10.0 double:5.398241246E-315;
            r4 = 2;
            r10 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r1 = 0;
            r0 = 1;
            r11.f2530u = r13;
            r11.f2531v = r14;
            com.amap.api.mapcore2d.MultiTouchGestureDetector.m3593b(r12);
            r2 = com.amap.api.mapcore2d.MultiTouchGestureDetector.f2509r;
            if (r2 != 0) goto L_0x0015;
        L_0x0014:
            return r1;
        L_0x0015:
            r2 = r12.getAction();
            r2 = r2 & 255;
            switch(r2) {
                case 0: goto L_0x0021;
                case 1: goto L_0x0091;
                case 2: goto L_0x00cb;
                case 3: goto L_0x009d;
                case 4: goto L_0x001e;
                case 5: goto L_0x0047;
                case 6: goto L_0x009d;
                default: goto L_0x001e;
            };
        L_0x001e:
            r0 = r1;
        L_0x001f:
            r1 = r0;
            goto L_0x0014;
        L_0x0021:
            r2 = r12.getEventTime();
            r11.f2529t = r2;
            r2 = r12.getX();
            r11.f2525p = r2;
            r2 = r12.getY();
            r11.f2526q = r2;
            r2 = r11.f2514d;
            r3 = r11.f2513c;
            r2.set(r3);
            r2 = r11.f2515e;
            r3 = r11.f2525p;
            r4 = r11.f2526q;
            r2.set(r3, r4);
            r11.f2512b = r0;
            r0 = r1;
            goto L_0x001f;
        L_0x0047:
            r2 = r11.f2523n;
            r2 = r2 + 1;
            r11.f2523n = r2;
            r2 = r11.f2523n;
            if (r2 != r0) goto L_0x001e;
        L_0x0051:
            r11.f2522m = r0;
            f2506j = r10;
            r2 = r11.m3596b(r12);
            r11.f2518h = r2;
            r2 = r11.f2518h;
            r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
            if (r2 <= 0) goto L_0x001e;
        L_0x0061:
            r2 = r11.f2513c;
            r2.reset();
            r2 = r11.f2514d;
            r2.reset();
            r2 = r11.f2514d;
            r3 = r11.f2513c;
            r2.set(r3);
            r2 = r11.f2516f;
            r11.m3595a(r2, r12);
            r11.f2512b = r4;
            r11.f2520k = r0;
            r0 = r11.f2511a;
            r2 = r11.f2515e;
            r0 = r0.mo9931a(r2);
            r0 = r0 | r1;
            r1 = r11.f2516f;
            r1 = r1.x;
            r11.f2527r = r1;
            r1 = r11.f2516f;
            r1 = r1.y;
            r11.f2528s = r1;
            goto L_0x001f;
        L_0x0091:
            r2 = r12.getEventTime();
            r11.f2524o = r2;
            r11.f2520k = r1;
            r11.f2512b = r1;
            r0 = r1;
            goto L_0x001f;
        L_0x009d:
            r2 = r11.f2523n;
            r2 = r2 + -1;
            r11.f2523n = r2;
            r2 = r11.f2523n;
            if (r2 != r0) goto L_0x00ab;
        L_0x00a7:
            r11.f2522m = r0;
            r11.f2512b = r4;
        L_0x00ab:
            r0 = r11.f2523n;
            if (r0 != 0) goto L_0x001e;
        L_0x00af:
            r0 = r11.f2516f;
            r11.m3595a(r0, r12);
            r11.f2521l = r1;
            r11.f2522m = r1;
            r0 = r11.f2520k;
            if (r0 == 0) goto L_0x001e;
        L_0x00bc:
            r0 = r11.f2511a;
            r2 = r11.f2519i;
            r3 = r11.f2516f;
            r0 = r0.mo9932b(r2, r3);
            r0 = r0 | r1;
            r11.f2512b = r1;
            goto L_0x001f;
        L_0x00cb:
            r2 = r11.f2512b;
            if (r2 != r0) goto L_0x0120;
        L_0x00cf:
            r2 = r12.getX();
            r3 = r12.getY();
            r4 = r11.f2513c;
            r5 = r11.f2514d;
            r4.set(r5);
            r4 = r11.f2513c;
            r5 = r12.getX();
            r6 = r11.f2515e;
            r6 = r6.x;
            r5 = r5 - r6;
            r6 = r12.getY();
            r7 = r11.f2515e;
            r7 = r7.y;
            r6 = r6 - r7;
            r4.postTranslate(r5, r6);
            r4 = r11.f2511a;
            r5 = r11.f2525p;
            r5 = r2 - r5;
            r6 = r11.f2526q;
            r6 = r3 - r6;
            r4 = r4.mo9928a(r5, r6);
            r1 = r1 | r4;
            r11.f2525p = r2;
            r11.f2526q = r3;
            r2 = r11.f2511a;
            r3 = r11.f2513c;
            r2 = r2.mo9930a(r3);
            r1 = r1 | r2;
            r2 = r12.getEventTime();
            r4 = r11.f2529t;
            r2 = r2 - r4;
            r4 = 30;
            r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r2 >= 0) goto L_0x001e;
        L_0x011e:
            goto L_0x001f;
        L_0x0120:
            r2 = r11.f2512b;
            if (r2 != r4) goto L_0x001e;
        L_0x0124:
            r2 = r11.m3596b(r12);
            r11.f2519i = r10;
            r4 = r12.getEventTime();
            r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
            if (r3 <= 0) goto L_0x001e;
        L_0x0132:
            r3 = r11.f2518h;
            r3 = r2 - r3;
            r3 = java.lang.Math.abs(r3);
            r6 = 1084227584; // 0x40a00000 float:5.0 double:5.356796015E-315;
            r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1));
            if (r3 <= 0) goto L_0x001e;
        L_0x0140:
            r6 = r11.f2532w;
            r6 = r4 - r6;
            r8 = 10;
            r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
            if (r3 <= 0) goto L_0x001e;
        L_0x014a:
            r11.f2532w = r4;
            r3 = r11.f2518h;
            r3 = r2 / r3;
            r11.f2519i = r3;
            f2506j = r10;
            r11.f2518h = r2;
            r2 = r11.f2517g;
            r11.m3595a(r2, r12);
            r2 = r11.f2511a;
            r3 = r11.f2517g;
            r3 = r3.x;
            r4 = r11.f2527r;
            r3 = r3 - r4;
            r4 = r11.f2517g;
            r4 = r4.y;
            r5 = r11.f2528s;
            r4 = r4 - r5;
            r2 = r2.mo9928a(r3, r4);
            r1 = r1 | r2;
            r2 = r11.f2517g;
            r2 = r2.x;
            r11.f2527r = r2;
            r2 = r11.f2517g;
            r2 = r2.y;
            r11.f2528s = r2;
            r2 = r11.f2511a;
            r3 = r11.f2519i;
            r4 = r11.f2516f;
            r2 = r2.mo9929a(r3, r4);
            r1 = r1 | r2;
            r11.f2521l = r0;
            goto L_0x001e;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore2d.MultiTouchGestureDetector$C0925a.mo10056a(android.view.MotionEvent, int, int):boolean");
        }

        /* renamed from: b */
        private float m3596b(MotionEvent motionEvent) {
            float floatValue;
            float f = 0.0f;
            String str = "distance";
            try {
                floatValue = ((Float) MultiTouchGestureDetector.f2507p.invoke(motionEvent, new Object[]{Integer.valueOf(0)})).floatValue() - ((Float) MultiTouchGestureDetector.f2507p.invoke(motionEvent, new Object[]{Integer.valueOf(1)})).floatValue();
            } catch (IllegalArgumentException e) {
                C0955ck.m3888a(e, "MutiTouchGestureDetector", str);
                floatValue = 0.0f;
            } catch (IllegalAccessException e2) {
                C0955ck.m3888a(e2, "MutiTouchGestureDetector", str);
                floatValue = 0.0f;
            } catch (InvocationTargetException e3) {
                C0955ck.m3888a(e3, "MutiTouchGestureDetector", str);
                floatValue = 0.0f;
            }
            try {
                f = ((Float) MultiTouchGestureDetector.f2508q.invoke(motionEvent, new Object[]{Integer.valueOf(0)})).floatValue() - ((Float) MultiTouchGestureDetector.f2508q.invoke(motionEvent, new Object[]{Integer.valueOf(1)})).floatValue();
            } catch (IllegalArgumentException e4) {
                C0955ck.m3888a(e4, "MutiTouchGestureDetector", str);
            } catch (IllegalAccessException e22) {
                C0955ck.m3888a(e22, "MutiTouchGestureDetector", str);
            } catch (InvocationTargetException e32) {
                C0955ck.m3888a(e32, "MutiTouchGestureDetector", str);
            }
            return (float) Math.sqrt((double) ((floatValue * floatValue) + (f * f)));
        }

        /* renamed from: a */
        private void m3595a(PointF pointF, MotionEvent motionEvent) {
            float floatValue;
            float f = 0.0f;
            String str = "midPoint";
            try {
                floatValue = ((Float) MultiTouchGestureDetector.f2507p.invoke(motionEvent, new Object[]{Integer.valueOf(1)})).floatValue() + ((Float) MultiTouchGestureDetector.f2507p.invoke(motionEvent, new Object[]{Integer.valueOf(0)})).floatValue();
            } catch (IllegalArgumentException e) {
                C0955ck.m3888a(e, "MutiTouchGestureDetector", str);
                floatValue = 0.0f;
            } catch (IllegalAccessException e2) {
                C0955ck.m3888a(e2, "MutiTouchGestureDetector", str);
                floatValue = 0.0f;
            } catch (InvocationTargetException e3) {
                C0955ck.m3888a(e3, "MutiTouchGestureDetector", str);
                floatValue = 0.0f;
            }
            try {
                f = ((Float) MultiTouchGestureDetector.f2508q.invoke(motionEvent, new Object[]{Integer.valueOf(0)})).floatValue() + ((Float) MultiTouchGestureDetector.f2508q.invoke(motionEvent, new Object[]{Integer.valueOf(1)})).floatValue();
            } catch (IllegalArgumentException e4) {
                C0955ck.m3888a(e4, "MutiTouchGestureDetector", str);
            } catch (IllegalAccessException e22) {
                C0955ck.m3888a(e22, "MutiTouchGestureDetector", str);
            } catch (InvocationTargetException e32) {
                C0955ck.m3888a(e32, "MutiTouchGestureDetector", str);
            }
            if (!(this.f2530u == 0 || this.f2531v == 0)) {
                floatValue = (float) this.f2530u;
                f = (float) this.f2531v;
            }
            pointF.set(floatValue / 2.0f, f / 2.0f);
        }
    }

    MultiTouchGestureDetector() {
    }

    /* renamed from: a */
    public static C0925a m3589a(Context context, C0921b c0921b) {
        C0925a c0925a = new C0925a();
        c0925a.f2511a = c0921b;
        return c0925a;
    }

    /* renamed from: b */
    private static void m3593b(MotionEvent motionEvent) {
        String str = "checkSDKForMuti";
        if (!f2510s) {
            f2510s = true;
            try {
                f2507p = motionEvent.getClass().getMethod("getX", new Class[]{Integer.TYPE});
                f2508q = motionEvent.getClass().getMethod("getY", new Class[]{Integer.TYPE});
                if (f2507p != null && f2508q != null) {
                    f2509r = true;
                }
            } catch (Exception e) {
                C0955ck.m3888a(e, "MutiTouchGestureDetector", str);
            }
        }
    }
}
