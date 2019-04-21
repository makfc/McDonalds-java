package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.DPoint;
import java.io.File;
import java.math.BigDecimal;

/* renamed from: com.amap.api.mapcore.util.bb */
public class OffsetUtil {
    /* renamed from: a */
    static double f1313a = 3.141592653589793d;
    /* renamed from: b */
    private static boolean f1314b = false;

    /* renamed from: a */
    public static LatLng m1726a(Context context, LatLng latLng) {
        if (context == null) {
            return null;
        }
        String a = SDKCoordinatorDownload.m2480a(context, "libwgs2gcj.so");
        if (!(TextUtils.isEmpty(a) || !new File(a).exists() || f1314b)) {
            try {
                System.load(a);
                f1314b = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        DPoint a2 = OffsetUtil.m1730a(new DPoint(latLng.longitude, latLng.latitude), f1314b);
        return new LatLng(a2.f4559y, a2.f4558x, false);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    private static com.autonavi.amap.mapcore.DPoint m1730a(com.autonavi.amap.mapcore.DPoint r6, boolean r7) {
        /*
        r0 = 2;
        r0 = new double[r0];	 Catch:{ Throwable -> 0x0045 }
        if (r7 == 0) goto L_0x003b;
    L_0x0005:
        r1 = 2;
        r1 = new double[r1];	 Catch:{ Throwable -> 0x0045 }
        r2 = 0;
        r4 = r6.f4558x;	 Catch:{ Throwable -> 0x0045 }
        r1[r2] = r4;	 Catch:{ Throwable -> 0x0045 }
        r2 = 1;
        r4 = r6.f4559y;	 Catch:{ Throwable -> 0x0045 }
        r1[r2] = r4;	 Catch:{ Throwable -> 0x0045 }
        r1 = com.autonavi.amap.mapcore.CoordUtil.convertToGcj(r1, r0);	 Catch:{ Throwable -> 0x002e }
        if (r1 == 0) goto L_0x0020;
    L_0x0018:
        r0 = r6.f4558x;	 Catch:{ Throwable -> 0x002e }
        r2 = r6.f4559y;	 Catch:{ Throwable -> 0x002e }
        r0 = com.amap.api.mapcore.util.C0852gd.m2843a(r0, r2);	 Catch:{ Throwable -> 0x002e }
    L_0x0020:
        r1 = r0;
    L_0x0021:
        r0 = new com.autonavi.amap.mapcore.DPoint;	 Catch:{ Throwable -> 0x0045 }
        r2 = 0;
        r2 = r1[r2];	 Catch:{ Throwable -> 0x0045 }
        r4 = 1;
        r4 = r1[r4];	 Catch:{ Throwable -> 0x0045 }
        r0.<init>(r2, r4);	 Catch:{ Throwable -> 0x0045 }
        r6 = r0;
    L_0x002d:
        return r6;
    L_0x002e:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Throwable -> 0x0045 }
        r0 = r6.f4558x;	 Catch:{ Throwable -> 0x0045 }
        r2 = r6.f4559y;	 Catch:{ Throwable -> 0x0045 }
        r0 = com.amap.api.mapcore.util.C0852gd.m2843a(r0, r2);	 Catch:{ Throwable -> 0x0045 }
        goto L_0x0020;
    L_0x003b:
        r0 = r6.f4558x;	 Catch:{ Throwable -> 0x0045 }
        r2 = r6.f4559y;	 Catch:{ Throwable -> 0x0045 }
        r0 = com.amap.api.mapcore.util.C0852gd.m2843a(r0, r2);	 Catch:{ Throwable -> 0x0045 }
        r1 = r0;
        goto L_0x0021;
    L_0x0045:
        r0 = move-exception;
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.OffsetUtil.m1730a(com.autonavi.amap.mapcore.DPoint, boolean):com.autonavi.amap.mapcore.DPoint");
    }

    /* renamed from: b */
    public static LatLng m1733b(Context context, LatLng latLng) {
        try {
            DPoint c = OffsetUtil.m1734c(latLng.longitude, latLng.latitude);
            return OffsetUtil.m1726a(context, new LatLng(c.f4559y, c.f4558x, false));
        } catch (Throwable th) {
            th.printStackTrace();
            return latLng;
        }
    }

    /* renamed from: a */
    public static double m1724a(double d, double d2) {
        return (Math.cos(d2 / 100000.0d) * (d / 18000.0d)) + (Math.sin(d / 100000.0d) * (d2 / 9000.0d));
    }

    /* renamed from: b */
    public static double m1732b(double d, double d2) {
        return (Math.sin(d2 / 100000.0d) * (d / 18000.0d)) + (Math.cos(d / 100000.0d) * (d2 / 9000.0d));
    }

    /* renamed from: c */
    private static DPoint m1734c(double d, double d2) {
        double d3 = (double) (((long) (100000.0d * d)) % 36000000);
        double d4 = (double) (((long) (100000.0d * d2)) % 36000000);
        int i = (int) ((-OffsetUtil.m1732b(d3, d4)) + d4);
        int i2 = (int) (((double) (d3 > 0.0d ? 1 : -1)) + ((-OffsetUtil.m1724a((double) ((int) ((-OffsetUtil.m1724a(d3, d4)) + d3)), (double) i)) + d3));
        return new DPoint(((double) i2) / 100000.0d, ((double) ((int) (((double) (d4 > 0.0d ? 1 : -1)) + ((-OffsetUtil.m1732b((double) i2, (double) i)) + d4)))) / 100000.0d);
    }

    /* renamed from: a */
    public static LatLng m1727a(LatLng latLng) {
        if (latLng != null) {
            try {
                DPoint a = OffsetUtil.m1729a(new DPoint(latLng.longitude, latLng.latitude), 2);
                return new LatLng(a.f4559y, a.f4558x, false);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return latLng;
    }

    /* renamed from: a */
    private static double m1723a(double d) {
        return Math.sin((3000.0d * d) * (f1313a / 180.0d)) * 2.0E-5d;
    }

    /* renamed from: b */
    private static double m1731b(double d) {
        return Math.cos((3000.0d * d) * (f1313a / 180.0d)) * 3.0E-6d;
    }

    /* renamed from: d */
    private static DPoint m1735d(double d, double d2) {
        DPoint dPoint = new DPoint();
        double sin = (Math.sin(OffsetUtil.m1731b(d) + Math.atan2(d2, d)) * (OffsetUtil.m1723a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.006d;
        dPoint.f4558x = OffsetUtil.m1725a((Math.cos(OffsetUtil.m1731b(d) + Math.atan2(d2, d)) * (OffsetUtil.m1723a(d2) + Math.sqrt((d * d) + (d2 * d2)))) + 0.0065d, 8);
        dPoint.f4559y = OffsetUtil.m1725a(sin, 8);
        return dPoint;
    }

    /* renamed from: a */
    private static double m1725a(double d, int i) {
        return new BigDecimal(d).setScale(i, 4).doubleValue();
    }

    /* renamed from: a */
    private static DPoint m1729a(DPoint dPoint, int i) {
        double d = 0.006401062d;
        double d2 = 0.0060424805d;
        int i2 = 0;
        DPoint dPoint2 = null;
        while (i2 < i) {
            DPoint a = OffsetUtil.m1728a(dPoint.f4558x, dPoint.f4559y, d, d2);
            d = dPoint.f4558x - a.f4558x;
            d2 = dPoint.f4559y - a.f4559y;
            i2++;
            dPoint2 = a;
        }
        return dPoint2;
    }

    /* renamed from: a */
    private static DPoint m1728a(double d, double d2, double d3, double d4) {
        DPoint dPoint = new DPoint();
        double d5 = d - d3;
        double d6 = d2 - d4;
        DPoint d7 = OffsetUtil.m1735d(d5, d6);
        dPoint.f4558x = OffsetUtil.m1725a((d5 + d) - d7.f4558x, 8);
        dPoint.f4559y = OffsetUtil.m1725a((d2 + d6) - d7.f4559y, 8);
        return dPoint;
    }
}
