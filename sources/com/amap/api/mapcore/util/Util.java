package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.RemoteException;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amap.api.mapcore.util.SDKInfo.C0824a;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.MapTilsCacheAndResManager;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.mapcore.util.dj */
public class Util {
    /* renamed from: a */
    static final int[] f1748a = new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    /* renamed from: b */
    static final double[] f1749b = new double[]{7453.642d, 3742.9905d, 1873.333d, 936.89026d, 468.472d, 234.239d, 117.12d, 58.56d, 29.28d, 14.64d, 7.32d, 3.66d, 1.829d, 0.915d, 0.4575d, 0.228d, 0.1144d};
    /* renamed from: c */
    public static Handler f1750c = new Handler();

    /* renamed from: a */
    public static Bitmap m2344a(Context context, String str) {
        try {
            InputStream open = ResourcesUtil.m2327a(context).open(str);
            Bitmap decodeStream = BitmapFactoryInstrumentation.decodeStream(open);
            open.close();
            return decodeStream;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "Util", "fromAsset");
            return null;
        }
    }

    /* renamed from: a */
    public static void m2358a(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    /* renamed from: a */
    public static String m2353a(String str, Object obj) {
        return str + "=" + String.valueOf(obj);
    }

    /* renamed from: a */
    public static float m2338a(float f, float f2) {
        if (f <= 40.0f) {
            return f;
        }
        if (f2 <= 15.0f) {
            return 40.0f;
        }
        if (f2 <= 16.0f) {
            return 50.0f;
        }
        if (f2 <= 17.0f) {
            return 54.0f;
        }
        if (f2 <= 18.0f) {
            return 57.0f;
        }
        return 60.0f;
    }

    /* renamed from: a */
    public static float m2337a(float f) {
        if (f > ConfigableConst.f2126f) {
            return ConfigableConst.f2126f;
        }
        if (f < 3.0f) {
            return 3.0f;
        }
        return f;
    }

    /* renamed from: a */
    public static FloatBuffer m2355a(float[] fArr) {
        try {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
            allocateDirect.order(ByteOrder.nativeOrder());
            FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
            asFloatBuffer.put(fArr);
            asFloatBuffer.position(0);
            return asFloatBuffer;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "Util", "makeFloatBuffer1");
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static FloatBuffer m2356a(float[] fArr, FloatBuffer floatBuffer) {
        try {
            floatBuffer.clear();
            floatBuffer.put(fArr);
            floatBuffer.position(0);
            return floatBuffer;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "Util", "makeFloatBuffer2");
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static int m2341a(GL10 gl10, Bitmap bitmap) {
        return Util.m2342a(gl10, bitmap, false);
    }

    /* renamed from: a */
    public static int m2342a(GL10 gl10, Bitmap bitmap, boolean z) {
        return Util.m2340a(gl10, 0, bitmap, z);
    }

    /* renamed from: a */
    public static int m2340a(GL10 gl10, int i, Bitmap bitmap, boolean z) {
        int b = Util.m2367b(gl10, i, bitmap, z);
        if (bitmap != null) {
            bitmap.recycle();
        }
        return b;
    }

    /* renamed from: b */
    public static int m2367b(GL10 gl10, int i, Bitmap bitmap, boolean z) {
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        if (i == 0) {
            int[] iArr = new int[]{0};
            gl10.glGenTextures(1, iArr, 0);
            i = iArr[0];
        }
        gl10.glEnable(3553);
        gl10.glBindTexture(3553, i);
        gl10.glTexParameterf(3553, 10241, 9729.0f);
        gl10.glTexParameterf(3553, 10240, 9729.0f);
        if (z) {
            gl10.glTexParameterf(3553, 10242, 10497.0f);
            gl10.glTexParameterf(3553, 10243, 10497.0f);
        } else {
            gl10.glTexParameterf(3553, 10242, 33071.0f);
            gl10.glTexParameterf(3553, 10243, 33071.0f);
        }
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        gl10.glDisable(3553);
        return i;
    }

    /* renamed from: a */
    public static int m2339a(int i) {
        int log = (int) (Math.log((double) i) / Math.log(2.0d));
        if ((1 << log) >= i) {
            return 1 << log;
        }
        return 1 << (log + 1);
    }

    /* renamed from: a */
    public static String m2354a(String... strArr) {
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
    public static int m2343a(Object[] objArr) {
        return Arrays.hashCode(objArr);
    }

    /* renamed from: a */
    public static Bitmap m2346a(Bitmap bitmap, int i, int i2) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, bitmap.hasAlpha() ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m2345a(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        return Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * f), (int) (((float) bitmap.getHeight()) * f), true);
    }

    /* renamed from: a */
    public static String m2350a(Context context) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return context.getCacheDir().toString() + File.separator;
        }
        File file;
        if (MapsInitializer.sdcardDir == null || MapsInitializer.sdcardDir.equals("")) {
            file = new File(Environment.getExternalStorageDirectory(), MapTilsCacheAndResManager.AUTONAVI_PATH);
        } else {
            file = new File(MapsInitializer.sdcardDir);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, MapTilsCacheAndResManager.AUTONAVI_DATA_PATH);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file2.toString() + File.separator;
    }

    /* renamed from: b */
    public static String m2369b(Context context) {
        String a = Util.m2350a(context);
        if (a == null) {
            return null;
        }
        File file = new File(a, "VMAP2");
        if (!file.exists()) {
            file.mkdir();
        }
        return file.toString() + File.separator;
    }

    /* renamed from: b */
    public static String m2368b(int i) {
        if (i < 1000) {
            return i + "m";
        }
        return (i / 1000) + "km";
    }

    /* renamed from: c */
    public static boolean m2375c(Context context) {
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        State state = activeNetworkInfo.getState();
        if (state == null || state == State.DISCONNECTED || state == State.DISCONNECTING) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m2361a() {
        return VERSION.SDK_INT >= 8;
    }

    /* renamed from: b */
    public static boolean m2371b() {
        return VERSION.SDK_INT >= 9;
    }

    /* renamed from: c */
    public static boolean m2374c() {
        return VERSION.SDK_INT >= 11;
    }

    /* renamed from: d */
    public static boolean m2376d() {
        return VERSION.SDK_INT >= 12;
    }

    /* renamed from: a */
    public static void m2360a(GL10 gl10, int i) {
        gl10.glDeleteTextures(1, new int[]{i}, 0);
    }

    /* renamed from: a */
    public static String m2352a(InputStream inputStream) {
        try {
            return new String(Util.m2373b(inputStream), "utf-8");
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "Util", "decodeAssetResData");
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m2373b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            int read = inputStream.read(bArr, 0, 2048);
            if (read == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:92:0x00c3 A:{SYNTHETIC, Splitter:B:92:0x00c3} */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x00c8 A:{SYNTHETIC, Splitter:B:95:0x00c8} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030 A:{SYNTHETIC, Splitter:B:15:0x0030} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0035 A:{SYNTHETIC, Splitter:B:18:0x0035} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0096 A:{SYNTHETIC, Splitter:B:67:0x0096} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x009b A:{SYNTHETIC, Splitter:B:70:0x009b} */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x00c3 A:{SYNTHETIC, Splitter:B:92:0x00c3} */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x00c8 A:{SYNTHETIC, Splitter:B:95:0x00c8} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030 A:{SYNTHETIC, Splitter:B:15:0x0030} */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0035 A:{SYNTHETIC, Splitter:B:18:0x0035} */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0096 A:{SYNTHETIC, Splitter:B:67:0x0096} */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x009b A:{SYNTHETIC, Splitter:B:70:0x009b} */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x00c3 A:{SYNTHETIC, Splitter:B:92:0x00c3} */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x00c8 A:{SYNTHETIC, Splitter:B:95:0x00c8} */
    /* renamed from: a */
    public static java.lang.String m2351a(java.io.File r6) {
        /*
        r2 = 0;
        r4 = new java.lang.StringBuffer;
        r4.<init>();
        r3 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x00fa, IOException -> 0x0088, all -> 0x00bf }
        r3.<init>(r6);	 Catch:{ FileNotFoundException -> 0x00fa, IOException -> 0x0088, all -> 0x00bf }
        r1 = new java.io.BufferedReader;	 Catch:{ FileNotFoundException -> 0x00fe, IOException -> 0x00f5 }
        r0 = new java.io.InputStreamReader;	 Catch:{ FileNotFoundException -> 0x00fe, IOException -> 0x00f5 }
        r5 = "utf-8";
        r0.<init>(r3, r5);	 Catch:{ FileNotFoundException -> 0x00fe, IOException -> 0x00f5 }
        r1.<init>(r0);	 Catch:{ FileNotFoundException -> 0x00fe, IOException -> 0x00f5 }
    L_0x0018:
        r0 = r1.readLine();	 Catch:{ FileNotFoundException -> 0x0022, IOException -> 0x00f7, all -> 0x00ee }
        if (r0 == 0) goto L_0x003d;
    L_0x001e:
        r4.append(r0);	 Catch:{ FileNotFoundException -> 0x0022, IOException -> 0x00f7, all -> 0x00ee }
        goto L_0x0018;
    L_0x0022:
        r0 = move-exception;
        r2 = r3;
    L_0x0024:
        r3 = "Util";
        r5 = "readFile fileNotFound";
        com.amap.api.mapcore.util.SDKLogHandler.m2563a(r0, r3, r5);	 Catch:{ all -> 0x00f1 }
        r0.printStackTrace();	 Catch:{ all -> 0x00f1 }
        if (r2 == 0) goto L_0x0033;
    L_0x0030:
        r2.close();	 Catch:{ IOException -> 0x006d }
    L_0x0033:
        if (r1 == 0) goto L_0x0038;
    L_0x0035:
        r1.close();	 Catch:{ IOException -> 0x0068 }
    L_0x0038:
        r0 = r4.toString();
        return r0;
    L_0x003d:
        if (r3 == 0) goto L_0x0042;
    L_0x003f:
        r3.close();	 Catch:{ IOException -> 0x004d }
    L_0x0042:
        if (r1 == 0) goto L_0x0038;
    L_0x0044:
        r1.close();	 Catch:{ IOException -> 0x0048 }
        goto L_0x0038;
    L_0x0048:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0038;
    L_0x004d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x005c }
        if (r1 == 0) goto L_0x0038;
    L_0x0053:
        r1.close();	 Catch:{ IOException -> 0x0057 }
        goto L_0x0038;
    L_0x0057:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0038;
    L_0x005c:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0062;
    L_0x005f:
        r1.close();	 Catch:{ IOException -> 0x0063 }
    L_0x0062:
        throw r0;
    L_0x0063:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0062;
    L_0x0068:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0038;
    L_0x006d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x007c }
        if (r1 == 0) goto L_0x0038;
    L_0x0073:
        r1.close();	 Catch:{ IOException -> 0x0077 }
        goto L_0x0038;
    L_0x0077:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0038;
    L_0x007c:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0082;
    L_0x007f:
        r1.close();	 Catch:{ IOException -> 0x0083 }
    L_0x0082:
        throw r0;
    L_0x0083:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0082;
    L_0x0088:
        r0 = move-exception;
        r3 = r2;
    L_0x008a:
        r1 = "Util";
        r5 = "readFile io";
        com.amap.api.mapcore.util.SDKLogHandler.m2563a(r0, r1, r5);	 Catch:{ all -> 0x00ec }
        r0.printStackTrace();	 Catch:{ all -> 0x00ec }
        if (r3 == 0) goto L_0x0099;
    L_0x0096:
        r3.close();	 Catch:{ IOException -> 0x00a4 }
    L_0x0099:
        if (r2 == 0) goto L_0x0038;
    L_0x009b:
        r2.close();	 Catch:{ IOException -> 0x009f }
        goto L_0x0038;
    L_0x009f:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0038;
    L_0x00a4:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00b3 }
        if (r2 == 0) goto L_0x0038;
    L_0x00aa:
        r2.close();	 Catch:{ IOException -> 0x00ae }
        goto L_0x0038;
    L_0x00ae:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0038;
    L_0x00b3:
        r0 = move-exception;
        if (r2 == 0) goto L_0x00b9;
    L_0x00b6:
        r2.close();	 Catch:{ IOException -> 0x00ba }
    L_0x00b9:
        throw r0;
    L_0x00ba:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00b9;
    L_0x00bf:
        r0 = move-exception;
        r3 = r2;
    L_0x00c1:
        if (r3 == 0) goto L_0x00c6;
    L_0x00c3:
        r3.close();	 Catch:{ IOException -> 0x00d1 }
    L_0x00c6:
        if (r2 == 0) goto L_0x00cb;
    L_0x00c8:
        r2.close();	 Catch:{ IOException -> 0x00cc }
    L_0x00cb:
        throw r0;
    L_0x00cc:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00cb;
    L_0x00d1:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ all -> 0x00e0 }
        if (r2 == 0) goto L_0x00cb;
    L_0x00d7:
        r2.close();	 Catch:{ IOException -> 0x00db }
        goto L_0x00cb;
    L_0x00db:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00cb;
    L_0x00e0:
        r0 = move-exception;
        if (r2 == 0) goto L_0x00e6;
    L_0x00e3:
        r2.close();	 Catch:{ IOException -> 0x00e7 }
    L_0x00e6:
        throw r0;
    L_0x00e7:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x00e6;
    L_0x00ec:
        r0 = move-exception;
        goto L_0x00c1;
    L_0x00ee:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00c1;
    L_0x00f1:
        r0 = move-exception;
        r3 = r2;
        r2 = r1;
        goto L_0x00c1;
    L_0x00f5:
        r0 = move-exception;
        goto L_0x008a;
    L_0x00f7:
        r0 = move-exception;
        r2 = r1;
        goto L_0x008a;
    L_0x00fa:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0024;
    L_0x00fe:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.Util.m2351a(java.io.File):java.lang.String");
    }

    /* renamed from: a */
    public static void m2359a(String str) throws AMapException {
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.has("status")) {
                String string = init.getString("status");
                if (string.equals("E6008")) {
                    throw new AMapException("key为空");
                } else if (string.equals("E6012")) {
                    throw new AMapException("key不存在");
                } else if (string.equals("E6018")) {
                    throw new AMapException("key被锁定");
                }
            }
        } catch (JSONException e) {
            SDKLogHandler.m2563a(e, "Util", "paseAuthFailurJson");
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static boolean m2363a(LatLng latLng, List<LatLng> list) {
        int i = 0;
        double d = latLng.longitude;
        double d2 = latLng.latitude;
        double d3 = latLng.latitude;
        if (list.size() < 3) {
            return false;
        }
        if (!((LatLng) list.get(0)).equals(list.get(list.size() - 1))) {
            list.add(list.get(0));
        }
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
                if (Util.m2372b(d, d2, d4, d5, d6, d7)) {
                    return true;
                }
                if (Math.abs(d7 - d5) >= 1.0E-9d) {
                    if (Util.m2372b(d4, d5, d, d2, 180.0d, d3)) {
                        if (d5 > d7) {
                            i4++;
                        }
                    } else if (Util.m2372b(d6, d7, d, d2, 180.0d, d3)) {
                        if (d7 > d5) {
                            i4++;
                        }
                    } else if (Util.m2362a(d4, d5, d6, d7, d, d2, 180.0d, d3)) {
                        i4++;
                    }
                }
                i = i4;
                i2 = i3 + 1;
            }
        }
    }

    /* renamed from: a */
    public static double m2335a(double d, double d2, double d3, double d4, double d5, double d6) {
        return ((d3 - d) * (d6 - d2)) - ((d5 - d) * (d4 - d2));
    }

    /* renamed from: b */
    public static boolean m2372b(double d, double d2, double d3, double d4, double d5, double d6) {
        if (Math.abs(Util.m2335a(d, d2, d3, d4, d5, d6)) >= 1.0E-9d || (d - d3) * (d - d5) > 0.0d || (d2 - d4) * (d2 - d6) > 0.0d) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m2362a(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
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
    public static List<FPoint> m2357a(IAMapDelegate iAMapDelegate, List<FPoint> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(list);
        FPoint[] a = Util.m2366a(iAMapDelegate);
        int i = 0;
        while (i < 4) {
            int i2;
            arrayList.clear();
            int size = arrayList2.size();
            int i3 = 0;
            while (true) {
                if (z) {
                    i2 = size;
                } else {
                    i2 = size - 1;
                }
                if (i3 >= i2) {
                    break;
                }
                FPoint fPoint = (FPoint) arrayList2.get(i3 % size);
                FPoint fPoint2 = (FPoint) arrayList2.get((i3 + 1) % size);
                if (i3 == 0 && Util.m2364a(fPoint, a[i], a[(i + 1) % a.length])) {
                    arrayList.add(fPoint);
                }
                if (Util.m2364a(fPoint, a[i], a[(i + 1) % a.length])) {
                    if (Util.m2364a(fPoint2, a[i], a[(i + 1) % a.length])) {
                        arrayList.add(fPoint2);
                    } else {
                        arrayList.add(Util.m2349a(a[i], a[(i + 1) % a.length], fPoint, fPoint2));
                    }
                } else if (Util.m2364a(fPoint2, a[i], a[(i + 1) % a.length])) {
                    arrayList.add(Util.m2349a(a[i], a[(i + 1) % a.length], fPoint, fPoint2));
                    arrayList.add(fPoint2);
                }
                i3++;
            }
            arrayList2.clear();
            for (i2 = 0; i2 < arrayList.size(); i2++) {
                arrayList2.add(arrayList.get(i2));
            }
            byte i4 = (byte) (i4 + 1);
        }
        return arrayList2;
    }

    /* renamed from: a */
    private static FPoint[] m2366a(IAMapDelegate iAMapDelegate) {
        FPoint[] fPointArr = new FPoint[4];
        FPoint fPoint = new FPoint();
        iAMapDelegate.pixel2Map(-100, -100, fPoint);
        fPointArr[0] = fPoint;
        fPoint = new FPoint();
        iAMapDelegate.pixel2Map(iAMapDelegate.getMapWidth() + 100, -100, fPoint);
        fPointArr[1] = fPoint;
        fPoint = new FPoint();
        iAMapDelegate.pixel2Map(iAMapDelegate.getMapWidth() + 100, iAMapDelegate.getMapHeight() + 100, fPoint);
        fPointArr[2] = fPoint;
        fPoint = new FPoint();
        iAMapDelegate.pixel2Map(-100, iAMapDelegate.getMapHeight() + 100, fPoint);
        fPointArr[3] = fPoint;
        return fPointArr;
    }

    /* renamed from: a */
    private static FPoint m2349a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3, FPoint fPoint4) {
        FPoint fPoint5 = new FPoint(0.0f, 0.0f);
        double d = (double) (((fPoint2.f4561y - fPoint.f4561y) * (fPoint.f4560x - fPoint3.f4560x)) - ((fPoint2.f4560x - fPoint.f4560x) * (fPoint.f4561y - fPoint3.f4561y)));
        double d2 = (double) (((fPoint2.f4561y - fPoint.f4561y) * (fPoint4.f4560x - fPoint3.f4560x)) - ((fPoint2.f4560x - fPoint.f4560x) * (fPoint4.f4561y - fPoint3.f4561y)));
        fPoint5.f4560x = (float) (((double) fPoint3.f4560x) + ((((double) (fPoint4.f4560x - fPoint3.f4560x)) * d) / d2));
        fPoint5.f4561y = (float) (((d * ((double) (fPoint4.f4561y - fPoint3.f4561y))) / d2) + ((double) fPoint3.f4561y));
        return fPoint5;
    }

    /* renamed from: a */
    private static boolean m2364a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3) {
        if (((double) (((fPoint3.f4560x - fPoint2.f4560x) * (fPoint.f4561y - fPoint2.f4561y)) - ((fPoint.f4560x - fPoint2.f4560x) * (fPoint3.f4561y - fPoint2.f4561y)))) >= 0.0d) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static float m2336a(double d, double d2, double d3, double d4) {
        if (d > 0.0d) {
            float a = (float) Util.m2334a(d, d3);
            if (d2 > 0.0d) {
                return (float) Math.min((double) a, Util.m2334a(d2, d4));
            }
            return a;
        } else if (d2 > 0.0d) {
            return (float) Util.m2334a(d2, d4);
        } else {
            return 0.0f;
        }
    }

    /* renamed from: a */
    public static double m2334a(double d, double d2) {
        if (d2 > 0.0d) {
            return Math.log((1048576.0d * d) / d2) / Math.log(2.0d);
        }
        return 0.0d;
    }

    /* renamed from: e */
    public static SDKInfo m2377e() {
        try {
            if (ConfigableConst.f2128h != null) {
                return ConfigableConst.f2128h;
            }
            return new C0824a(ConfigableConst.f2122b, "3.3.2", ConfigableConst.f2124d).mo9290a(new String[]{"com.amap.api.maps"}).mo9291a();
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: b */
    private static void m2370b(View view) {
        boolean z = false;
        if (view instanceof ViewGroup) {
            while (true) {
                boolean z2 = z;
                if (z2 < ((ViewGroup) view).getChildCount()) {
                    Util.m2370b(((ViewGroup) view).getChildAt(z2));
                    z = z2 + 1;
                } else {
                    return;
                }
            }
        } else if (view instanceof TextView) {
            ((TextView) view).setHorizontallyScrolling(false);
        }
    }

    /* renamed from: a */
    public static Bitmap m2347a(View view) {
        try {
            Util.m2370b(view);
            view.destroyDrawingCache();
            view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            Bitmap copy = view.getDrawingCache().copy(Config.ARGB_8888, false);
            view.destroyDrawingCache();
            return copy;
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "Utils", "getBitmapFromView");
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static DPoint m2348a(LatLng latLng) {
        double d = (latLng.longitude / 360.0d) + 0.5d;
        double sin = Math.sin(Math.toRadians(latLng.latitude));
        return new DPoint(d * 1.0d, (((Math.log((1.0d + sin) / (1.0d - sin)) * 0.5d) / -6.283185307179586d) + 0.5d) * 1.0d);
    }

    /* renamed from: a */
    public static float[] m2365a(IAMapDelegate iAMapDelegate, int i, FPoint fPoint, float f, int i2, int i3, float f2, float f3) throws RemoteException {
        float f4 = fPoint.f4560x;
        float f5 = fPoint.f4561y;
        float[] fArr = new float[12];
        float toMapLenWithWin = iAMapDelegate.getProjection().toMapLenWithWin(i2);
        float toMapLenWithWin2 = iAMapDelegate.getProjection().toMapLenWithWin(i3);
        float[] fArr2 = new float[16];
        float[] fArr3 = new float[4];
        Matrix.setIdentityM(fArr2, 0);
        if (i == 3) {
            Matrix.translateM(fArr2, 0, f4, f5, 0.0f);
            Matrix.rotateM(fArr2, 0, iAMapDelegate.getMapProjection().getMapAngle(), 0.0f, 0.0f, 1.0f);
            Matrix.translateM(fArr2, 0, -f4, -f5, 0.0f);
            Matrix.translateM(fArr2, 0, f4 - (toMapLenWithWin / 2.0f), f5 - (toMapLenWithWin2 / 2.0f), 0.0f);
            Matrix.rotateM(fArr2, 0, -iAMapDelegate.getMapProjection().getCameraHeaderAngle(), 1.0f, 0.0f, 0.0f);
            Matrix.translateM(fArr2, 0, (toMapLenWithWin / 2.0f) - f4, (toMapLenWithWin2 / 2.0f) - f5, 0.0f);
        } else if (i == 1) {
            Matrix.translateM(fArr2, 0, f4, f5, 0.0f);
            Matrix.rotateM(fArr2, 0, f, 0.0f, 0.0f, 1.0f);
            Matrix.translateM(fArr2, 0, -f4, -f5, 0.0f);
        } else {
            Matrix.translateM(fArr2, 0, f4, f5, 0.0f);
            Matrix.rotateM(fArr2, 0, iAMapDelegate.getMapProjection().getMapAngle(), 0.0f, 0.0f, 1.0f);
            Matrix.rotateM(fArr2, 0, -iAMapDelegate.getMapProjection().getCameraHeaderAngle(), 1.0f, 0.0f, 0.0f);
            Matrix.rotateM(fArr2, 0, f, 0.0f, 0.0f, 1.0f);
            Matrix.translateM(fArr2, 0, -f4, -f5, 0.0f);
        }
        float[] fArr4 = new float[]{f4 - (toMapLenWithWin * f2), ((1.0f - f3) * toMapLenWithWin2) + f5, 0.0f, 1.0f};
        Matrix.multiplyMV(fArr4, 0, fArr2, 0, fArr3, 0);
        fArr[0] = fArr4[0];
        fArr[1] = fArr4[1];
        fArr[2] = fArr4[2];
        fArr3[0] = ((1.0f - f2) * toMapLenWithWin) + f4;
        fArr3[1] = ((1.0f - f3) * toMapLenWithWin2) + f5;
        fArr3[2] = 0.0f;
        fArr3[3] = 1.0f;
        Matrix.multiplyMV(fArr4, 0, fArr2, 0, fArr3, 0);
        fArr[3] = fArr4[0];
        fArr[4] = fArr4[1];
        fArr[5] = fArr4[2];
        fArr3[0] = ((1.0f - f2) * toMapLenWithWin) + f4;
        fArr3[1] = f5 - (toMapLenWithWin2 * f3);
        fArr3[2] = 0.0f;
        fArr3[3] = 1.0f;
        Matrix.multiplyMV(fArr4, 0, fArr2, 0, fArr3, 0);
        fArr[6] = fArr4[0];
        fArr[7] = fArr4[1];
        fArr[8] = fArr4[2];
        fArr3[0] = f4 - (toMapLenWithWin * f2);
        fArr3[1] = f5 - (toMapLenWithWin2 * f3);
        fArr3[2] = 0.0f;
        fArr3[3] = 1.0f;
        Matrix.multiplyMV(fArr4, 0, fArr2, 0, fArr3, 0);
        fArr[9] = fArr4[0];
        fArr[10] = fArr4[1];
        fArr[11] = fArr4[2];
        return fArr;
    }
}
