package com.amap.api.maps2d;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import com.amap.api.mapcore2d.C0956cl;
import com.amap.api.mapcore2d.C0957cm;
import com.amap.api.mapcore2d.C0963co;
import com.amap.api.mapcore2d.C0977cv.C0976a;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.NaviPara;
import com.amap.api.maps2d.model.PoiPara;
import com.amap.api.maps2d.model.RoutePara;

public class AMapUtils {
    public static final int BUS_COMFORT = 4;
    public static final int BUS_MONEY_LITTLE = 1;
    public static final int BUS_NO_SUBWAY = 5;
    public static final int BUS_TIME_FIRST = 0;
    public static final int BUS_TRANSFER_LITTLE = 2;
    public static final int BUS_WALK_LITTLE = 3;
    public static final int DRIVING_AVOID_CONGESTION = 4;
    public static final int DRIVING_DEFAULT = 0;
    public static final int DRIVING_NO_HIGHWAY = 3;
    public static final int DRIVING_NO_HIGHWAY_AVOID_CONGESTION = 6;
    public static final int DRIVING_NO_HIGHWAY_AVOID_SHORT_MONEY = 5;
    public static final int DRIVING_NO_HIGHWAY_SAVE_MONEY_AVOID_CONGESTION = 8;
    public static final int DRIVING_SAVE_MONEY = 1;
    public static final int DRIVING_SAVE_MONEY_AVOID_CONGESTION = 7;
    public static final int DRIVING_SHORT_DISTANCE = 2;

    /* renamed from: com.amap.api.maps2d.AMapUtils$a */
    static class C1064a extends Thread {
        /* renamed from: a */
        String f3360a = "";
        /* renamed from: b */
        Context f3361b;

        public C1064a(String str, Context context) {
            this.f3360a = str;
            if (context != null) {
                this.f3361b = context.getApplicationContext();
            }
        }

        public void run() {
            if (this.f3361b != null) {
                try {
                    C0963co.m3932a(this.f3361b, new C0976a(this.f3360a, "2.9.0", "AMAP_SDK_Android_2DMap_2.9.0").mo10170a(new String[]{"com.amap.api.maps"}).mo10171a());
                    interrupt();
                } catch (C0956cl e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static float calculateLineDistance(LatLng latLng, LatLng latLng2) {
        if (latLng == null || latLng2 == null) {
            try {
                throw new AMapException("非法坐标值");
            } catch (AMapException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }
        double d = latLng.longitude;
        double d2 = latLng.latitude;
        d *= 0.01745329251994329d;
        d2 *= 0.01745329251994329d;
        double d3 = latLng2.longitude * 0.01745329251994329d;
        double d4 = latLng2.latitude * 0.01745329251994329d;
        double sin = Math.sin(d);
        double sin2 = Math.sin(d2);
        d = Math.cos(d);
        d2 = Math.cos(d2);
        double sin3 = Math.sin(d3);
        double sin4 = Math.sin(d4);
        d3 = Math.cos(d3);
        d4 = Math.cos(d4);
        r18 = new double[3];
        double[] dArr = new double[]{d * d2, d2 * sin, sin2};
        dArr[0] = d4 * d3;
        dArr[1] = d4 * sin3;
        dArr[2] = sin4;
        return (float) (Math.asin(Math.sqrt((((r18[0] - dArr[0]) * (r18[0] - dArr[0])) + ((r18[1] - dArr[1]) * (r18[1] - dArr[1]))) + ((r18[2] - dArr[2]) * (r18[2] - dArr[2]))) / 2.0d) * 1.27420015798544E7d);
    }

    public static float calculateArea(LatLng latLng, LatLng latLng2) {
        if (latLng == null || latLng2 == null) {
            try {
                throw new AMapException("非法坐标值");
            } catch (AMapException e) {
                e.printStackTrace();
                return 0.0f;
            }
        }
        double sin = Math.sin((latLng.latitude * 3.141592653589793d) / 180.0d) - Math.sin((latLng2.latitude * 3.141592653589793d) / 180.0d);
        double d = (latLng2.longitude - latLng.longitude) / 360.0d;
        if (d < 0.0d) {
            d += 1.0d;
        }
        return (float) (d * ((6378137.0d * (6.283185307179586d * 6378137.0d)) * sin));
    }

    public static void getLatestAMapApp(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(276824064);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("http://wap.amap.com/"));
            new C1064a("glaa", context).start();
            context.startActivity(intent);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void openAMapNavi(NaviPara naviPara, Context context) throws AMapException {
        if (!m4562a(context)) {
            throw new AMapException("移动设备上未安装高德地图或高德地图版本较旧");
        } else if (naviPara.getTargetPoint() != null) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(m4559a(naviPara, context)));
                intent.setPackage("com.autonavi.minimap");
                new C1064a("oan", context).start();
                context.startActivity(intent);
            } catch (Throwable th) {
                AMapException aMapException = new AMapException("移动设备上未安装高德地图或高德地图版本较旧");
            }
        } else {
            throw new AMapException("非法导航参数");
        }
    }

    public static void openAMapPoiNearbySearch(PoiPara poiPara, Context context) throws AMapException {
        if (!m4562a(context)) {
            throw new AMapException("移动设备上未安装高德地图或高德地图版本较旧");
        } else if (poiPara.getKeywords() == null || poiPara.getKeywords().trim().length() <= 0) {
            throw new AMapException("非法导航参数");
        } else {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(m4560a(poiPara, context)));
                intent.setPackage("com.autonavi.minimap");
                new C1064a("oan", context).start();
                context.startActivity(intent);
            } catch (Throwable th) {
                AMapException aMapException = new AMapException("移动设备上未安装高德地图或高德地图版本较旧");
            }
        }
    }

    public static void openAMapDrivingRoute(RoutePara routePara, Context context) throws AMapException {
        m4564b(routePara, context, 2);
    }

    public static void openAMapTransitRoute(RoutePara routePara, Context context) throws AMapException {
        m4564b(routePara, context, 1);
    }

    /* renamed from: a */
    private static String m4560a(PoiPara poiPara, Context context) {
        String format = String.format("androidamap://arroundpoi?sourceApplication=%s&keywords=%s&dev=0", new Object[]{C0957cm.m3902b(context), poiPara.getKeywords()});
        if (poiPara.getCenter() != null) {
            return format + "&lat=" + poiPara.getCenter().latitude + "&lon=" + poiPara.getCenter().longitude;
        }
        return format;
    }

    public static void openAMapWalkingRoute(RoutePara routePara, Context context) throws AMapException {
        m4564b(routePara, context, 4);
    }

    /* renamed from: a */
    private static String m4561a(RoutePara routePara, Context context, int i) {
        String format = String.format("androidamap://route?sourceApplication=%s&slat=%f&slon=%f&sname=%s&dlat=%f&dlon=%f&dname=%s&dev=0&t=%d", new Object[]{C0957cm.m3902b(context), Double.valueOf(routePara.getStartPoint().latitude), Double.valueOf(routePara.getStartPoint().longitude), routePara.getStartName(), Double.valueOf(routePara.getEndPoint().latitude), Double.valueOf(routePara.getEndPoint().longitude), routePara.getEndName(), Integer.valueOf(i)});
        if (i == 1) {
            return format + "&m=" + routePara.getTransitRouteStyle();
        }
        if (i == 2) {
            return format + "&m=" + routePara.getDrivingRouteStyle();
        }
        return format;
    }

    /* renamed from: b */
    private static void m4564b(RoutePara routePara, Context context, int i) throws AMapException {
        if (!m4562a(context)) {
            throw new AMapException("移动设备上未安装高德地图或高德地图版本较旧");
        } else if (m4563a(routePara)) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(276824064);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(m4561a(routePara, context, i)));
                intent.setPackage("com.autonavi.minimap");
                new C1064a("oan", context).start();
                context.startActivity(intent);
            } catch (Throwable th) {
                AMapException aMapException = new AMapException("移动设备上未安装高德地图或高德地图版本较旧");
            }
        } else {
            throw new AMapException("非法导航参数");
        }
    }

    /* renamed from: a */
    private static boolean m4563a(RoutePara routePara) {
        if (routePara.getStartPoint() == null || routePara.getEndPoint() == null || routePara.getStartName() == null || routePara.getStartName().trim().length() <= 0 || routePara.getEndName() == null || routePara.getEndName().trim().length() <= 0) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private static String m4559a(NaviPara naviPara, Context context) {
        return String.format("androidamap://navi?sourceApplication=%s&lat=%f&lon=%f&dev=0&style=%d", new Object[]{C0957cm.m3902b(context), Double.valueOf(naviPara.getTargetPoint().latitude), Double.valueOf(naviPara.getTargetPoint().longitude), Integer.valueOf(naviPara.getNaviStyle())});
    }

    /* renamed from: a */
    private static boolean m4562a(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo("com.autonavi.minimap", 0) != null) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
