package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Environment;
import com.amap.api.mapcore2d.C0977cv.C0976a;
import com.amap.api.maps2d.MapsInitializer;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/* compiled from: Util */
/* renamed from: com.amap.api.mapcore2d.ck */
public class C0955ck {
    /* renamed from: a */
    public static double[] f2698a = new double[]{7453.642d, 3742.9905d, 1873.333d, 936.89026d, 468.472d, 234.239d, 117.12d, 58.56d, 29.28d, 14.64d, 7.32d, 3.66d, 1.829d, 0.915d, 0.4575d, 0.228d, 0.1144d};

    /* renamed from: a */
    public static Bitmap m3882a(String str) {
        try {
            InputStream resourceAsStream = BitmapDescriptorFactory.class.getResourceAsStream("/assets/" + str);
            Bitmap decodeStream = BitmapFactoryInstrumentation.decodeStream(resourceAsStream);
            resourceAsStream.close();
            return decodeStream;
        } catch (Throwable th) {
            C0955ck.m3888a(th, "Util", "fromAsset");
            return null;
        }
    }

    /* renamed from: a */
    public static String m3886a(String str, Object obj) {
        return str + "=" + String.valueOf(obj);
    }

    /* renamed from: a */
    public static float m3879a(float f) {
        if (f < 0.0f) {
            return 0.0f;
        }
        if (f > 45.0f) {
            return 45.0f;
        }
        return f;
    }

    /* renamed from: b */
    public static float m3895b(float f) {
        if (f > ((float) C1042p.f3033c)) {
            return (float) C1042p.f3033c;
        }
        if (f < ((float) C1042p.f3034d)) {
            return (float) C1042p.f3034d;
        }
        return f;
    }

    /* renamed from: a */
    public static String m3887a(String... strArr) {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        int length = strArr.length;
        int i2 = 0;
        while (i < length) {
            stringBuilder.append(strArr[i]);
            if (i2 != strArr.length - 1) {
                stringBuilder.append(",");
            }
            i2++;
            i++;
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static int m3880a(Object[] objArr) {
        return Arrays.hashCode(objArr);
    }

    /* renamed from: a */
    public static Bitmap m3881a(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * f), (int) (((float) bitmap.getHeight()) * f), true);
    }

    /* renamed from: a */
    public static double m3878a(LatLng latLng, LatLng latLng2) {
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
        return Math.asin(Math.sqrt((((r18[0] - dArr[0]) * (r18[0] - dArr[0])) + ((r18[1] - dArr[1]) * (r18[1] - dArr[1]))) + ((r18[2] - dArr[2]) * (r18[2] - dArr[2]))) / 2.0d) * 1.27420015798544E7d;
    }

    /* renamed from: a */
    public static String m3885a(int i) {
        if (i < 1000) {
            return i + "m";
        }
        return (i / 1000) + "km";
    }

    /* renamed from: a */
    public static GeoPoint m3884a(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new GeoPoint((int) (latLng.latitude * 1000000.0d), (int) (latLng.longitude * 1000000.0d));
    }

    /* renamed from: a */
    public static boolean m3892a(LatLng latLng, List<LatLng> list) {
        int i = 0;
        double d = latLng.longitude;
        double d2 = latLng.latitude;
        double d3 = latLng.latitude;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            int i4 = i;
            if (i3 >= list.size() - 1) {
                return i4 % 2 != 0;
            } else {
                double d4 = ((LatLng) list.get(i3)).longitude;
                double d5 = ((LatLng) list.get(i3)).latitude;
                double d6 = ((LatLng) list.get(i3 + 1)).longitude;
                double d7 = ((LatLng) list.get(i3 + 1)).latitude;
                if (C0955ck.m3889a(d, d2, d4, d5, d6, d7)) {
                    return true;
                }
                if (Math.abs(d7 - d5) >= 1.0E-9d) {
                    if (C0955ck.m3889a(d4, d5, d, d2, 180.0d, d3)) {
                        if (d5 > d7) {
                            i4++;
                        }
                    } else if (C0955ck.m3889a(d6, d7, d, d2, 180.0d, d3)) {
                        if (d7 > d5) {
                            i4++;
                        }
                    } else if (C0955ck.m3890a(d4, d5, d6, d7, d, d2, 180.0d, d3)) {
                        i4++;
                    }
                }
                i = i4;
                i2 = i3 + 1;
            }
        }
    }

    /* renamed from: a */
    public static boolean m3889a(double d, double d2, double d3, double d4, double d5, double d6) {
        if (Math.abs(C0955ck.m3894b(d, d2, d3, d4, d5, d6)) >= 1.0E-9d || (d - d3) * (d - d5) > 0.0d || (d2 - d4) * (d2 - d6) > 0.0d) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public static double m3894b(double d, double d2, double d3, double d4, double d5, double d6) {
        return ((d3 - d) * (d6 - d2)) - ((d5 - d) * (d4 - d2));
    }

    /* renamed from: a */
    public static boolean m3890a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double d9 = ((d3 - d) * (d8 - d6)) - ((d4 - d2) * (d7 - d5));
        if (d9 == 0.0d) {
            return false;
        }
        double d10 = (((d2 - d6) * (d7 - d5)) - ((d - d5) * (d8 - d6))) / d9;
        d9 = (((d2 - d6) * (d3 - d)) - ((d - d5) * (d4 - d2))) / d9;
        if (d10 < 0.0d || d10 > 1.0d || d9 < 0.0d || d9 > 1.0d) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static synchronized boolean m3891a(Context context) {
        boolean z;
        synchronized (C0955ck.class) {
            if (context == null) {
                z = false;
            } else {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager == null) {
                    z = false;
                } else {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo == null) {
                        z = false;
                    } else {
                        State state = activeNetworkInfo.getState();
                        z = (state == null || state == State.DISCONNECTED || state == State.DISCONNECTING) ? false : true;
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public static void m3888a(Throwable th, String str, String str2) {
        try {
            C0990dd a = C0990dd.m4095a();
            if (a != null) {
                a.mo10187c(th, str, str2);
            }
            th.printStackTrace();
        } catch (Throwable th2) {
        }
    }

    /* renamed from: b */
    public static String m3896b(Context context) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return context.getFilesDir().getPath();
        }
        File file;
        if (MapsInitializer.sdcardDir == null || MapsInitializer.sdcardDir.equals("")) {
            file = new File(Environment.getExternalStorageDirectory(), "AMap");
            if (!file.exists()) {
                file.mkdir();
            }
            return file.toString() + "/";
        }
        File file2 = new File(MapsInitializer.sdcardDir);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        file = new File(file2, "Amap");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString() + "/";
    }

    /* renamed from: a */
    public static boolean m3893a(File file) throws IOException, Exception {
        if (file == null || !file.exists()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    if (!listFiles[i].delete()) {
                        return false;
                    }
                } else if (!C0955ck.m3893a(listFiles[i])) {
                    return false;
                } else {
                    listFiles[i].delete();
                }
            }
        }
        return true;
    }

    /* renamed from: a */
    public static C0977cv m3883a() {
        try {
            if (C1042p.f3045o != null) {
                return C1042p.f3045o;
            }
            return new C0976a("2dmap", "2.9.0", "AMAP_SDK_Android_2DMap_2.9.0").mo10170a(new String[]{"com.amap.api.maps"}).mo10171a();
        } catch (Throwable th) {
            return null;
        }
    }
}
