package com.aps;

import android.content.ContentResolver;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Settings.Global;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.widget.Toast;
import com.amap.api.location.core.ClientInfoUtil;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.params.HttpParams;

/* compiled from: Utils */
/* renamed from: com.aps.v */
public class C1269v {
    private C1269v() {
    }

    /* renamed from: a */
    public static void m5730a(Object... objArr) {
    }

    /* renamed from: a */
    static void m5727a(Context context, String str) {
        CharSequence str2;
        int i;
        if (str2 == null) {
            str2 = SafeJsonPrimitive.NULL_STRING;
        }
        if (ClientInfoUtil.m1444k().indexOf("test") != -1) {
            i = 1;
        } else if (Const.f4437d.indexOf("test") != -1) {
            i = 1;
        } else {
            char[] cArr = null;
            if (ClientInfoUtil.m1444k().length() > 0) {
                cArr = ClientInfoUtil.m1444k().substring(7, 8).toCharArray();
            }
            if (cArr == null || !Character.isLetter(cArr[0])) {
                i = 1;
            } else {
                i = 0;
            }
        }
        if (i != 0 && context != null) {
            Toast.makeText(context, str2, 0).show();
            C1269v.m5730a(str2);
        }
    }

    /* renamed from: a */
    public static void m5728a(Throwable th) {
    }

    /* renamed from: a */
    static boolean m5732a(AmapLoc amapLoc) {
        if (amapLoc == null || amapLoc.mo13220l().equals("5") || amapLoc.mo13220l().equals("6")) {
            return false;
        }
        double g = amapLoc.mo13210g();
        double h = amapLoc.mo13212h();
        float i = amapLoc.mo13214i();
        if (g == 0.0d && h == 0.0d && ((double) i) == 0.0d) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    static int m5724a(int i) {
        return (i * 2) - 113;
    }

    /* renamed from: a */
    public static String[] m5735a(TelephonyManager telephonyManager) {
        int i = 1;
        int i2 = 0;
        String[] strArr = new String[]{"0", "0"};
        CharSequence charSequence = null;
        if (telephonyManager != null) {
            try {
                charSequence = telephonyManager.getNetworkOperator();
            } catch (Exception e) {
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            i = i2;
        } else if (!TextUtils.isDigitsOnly(charSequence)) {
            i = i2;
        } else if (charSequence.length() <= 4) {
            i = i2;
        }
        if (i != 0) {
            strArr[0] = charSequence.substring(0, 3);
            char[] toCharArray = charSequence.substring(3).toCharArray();
            i = i2;
            while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                i++;
            }
            strArr[1] = charSequence.substring(3, i + 3);
        }
        try {
            i2 = Integer.parseInt(strArr[0]);
        } catch (Exception e2) {
        }
        if (i2 == 0) {
            strArr[0] = "0";
        }
        return strArr;
    }

    /* renamed from: a */
    static int m5725a(CellLocation cellLocation, Context context) {
        if (C1269v.m5731a(context)) {
            C1269v.m5730a("air plane mode on");
            return 9;
        } else if (cellLocation instanceof GsmCellLocation) {
            return 1;
        } else {
            try {
                Class.forName("android.telephony.cdma.CdmaCellLocation");
                return 2;
            } catch (Throwable th) {
                th.printStackTrace();
                return 9;
            }
        }
    }

    /* renamed from: a */
    static long m5726a() {
        return System.currentTimeMillis();
    }

    /* renamed from: b */
    static long m5737b() {
        return SystemClock.elapsedRealtime();
    }

    /* renamed from: a */
    static boolean m5731a(Context context) {
        boolean z = true;
        if (context == null) {
            return false;
        }
        ContentResolver contentResolver = context.getContentResolver();
        if (C1269v.m5740c() < 17) {
            try {
                if (System.getInt(contentResolver, "airplane_mode_on", 0) != 1) {
                    z = false;
                }
                return z;
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        try {
            if (Global.getInt(contentResolver, "airplane_mode_on", 0) != 1) {
                z = false;
            }
            return z;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    static float m5723a(double[] dArr) {
        if (dArr.length != 4) {
            return 0.0f;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(dArr[0], dArr[1], dArr[2], dArr[3], fArr);
        return fArr[0];
    }

    /* renamed from: b */
    static Object m5739b(Context context, String str) {
        if (context == null) {
            return null;
        }
        return context.getApplicationContext().getSystemService(str);
    }

    /* renamed from: a */
    static void m5729a(HttpParams httpParams, int i) {
        httpParams.setIntParameter("http.connection.timeout", i);
        httpParams.setIntParameter("http.socket.timeout", i);
        httpParams.setLongParameter("http.conn-manager.timeout", (long) i);
    }

    /* renamed from: c */
    static int m5740c() {
        int i = 0;
        try {
            return VERSION.SDK_INT;
        } catch (Throwable th) {
            th.printStackTrace();
            C1269v.m5728a(th);
            return i;
        }
    }

    /* renamed from: a */
    public static byte[] m5734a(byte[] bArr) {
        byte[] toByteArray;
        Throwable th;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                th.printStackTrace();
                return toByteArray;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            toByteArray = null;
            th = th4;
        }
        return toByteArray;
    }

    /* renamed from: b */
    static NetworkInfo m5738b(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) C1269v.m5739b(context, "connectivity");
        if (connectivityManager != null) {
            try {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            } catch (SecurityException e) {
                return null;
            }
        }
        activeNetworkInfo = null;
        return activeNetworkInfo;
    }

    /* renamed from: a */
    public static boolean m5733a(String str) {
        if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
            return ",111,123,134,199,202,204,206,208,212,213,214,216,218,219,220,222,225,226,228,230,231,232,234,235,238,240,242,244,246,247,248,250,255,257,259,260,262,266,268,270,272,274,276,278,280,282,283,284,286,288,289,290,292,293,294,295,297,302,308,310,311,312,313,314,315,316,310,330,332,334,338,340,342,344,346,348,350,352,354,356,358,360,362,363,364,365,366,368,370,372,374,376,400,401,402,404,405,406,410,412,413,414,415,416,417,418,419,420,421,422,424,425,426,427,428,429,430,431,432,434,436,437,438,440,441,450,452,454,455,456,457,466,467,470,472,502,505,510,514,515,520,525,528,530,534,535,536,537,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,555,560,598,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,645,646,647,648,649,650,651,652,653,654,655,657,659,665,702,704,706,708,710,712,714,716,722,724,730,732,734,736,738,740,742,744,746,748,750,850,901,".contains("," + str + ",");
        }
        return false;
    }

    /* renamed from: b */
    public static float m5736b(double[] dArr) {
        if (dArr.length != 4) {
            return 0.0f;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(dArr[0], dArr[1], dArr[2], dArr[3], fArr);
        return fArr[0];
    }

    /* renamed from: a */
    public static float m5722a(AmapLoc amapLoc, AmapLoc amapLoc2) {
        return C1269v.m5736b(new double[]{amapLoc.mo13212h(), amapLoc.mo13210g(), amapLoc2.mo13212h(), amapLoc2.mo13210g()});
    }
}
