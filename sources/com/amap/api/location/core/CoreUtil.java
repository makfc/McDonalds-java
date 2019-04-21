package com.amap.api.location.core;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.LocationProviderProxy;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.location.core.d */
public class CoreUtil {
    /* renamed from: a */
    public static String f928a = "";
    /* renamed from: b */
    public static String f929b = "";
    /* renamed from: c */
    static int f930c = 2048;
    /* renamed from: d */
    static String f931d = null;
    /* renamed from: e */
    private static String f932e = null;
    /* renamed from: f */
    private static SharedPreferences f933f = null;
    /* renamed from: g */
    private static Editor f934g = null;
    /* renamed from: h */
    private static Method f935h;

    /* renamed from: a */
    public static boolean m1460a(Context context) {
        if (context == null) {
            return false;
        }
        try {
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
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static AMapLocation m1461b(Context context) {
        try {
            if (f932e == null) {
                f932e = Encrypt.m1464a("MD5", context.getApplicationContext().getPackageName());
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("last_known_location", 0);
            AMapLocation aMapLocation = new AMapLocation("");
            aMapLocation.setProvider(LocationProviderProxy.AMapNetwork);
            double parseDouble = Double.parseDouble(Encrypt.m1470b(sharedPreferences.getString("a", ""), f932e));
            double parseDouble2 = Double.parseDouble(Encrypt.m1470b(sharedPreferences.getString("b", ""), f932e));
            aMapLocation.setLatitude(parseDouble);
            aMapLocation.setLongitude(parseDouble2);
            aMapLocation.setProvince(Encrypt.m1470b(sharedPreferences.getString("c", ""), f932e));
            aMapLocation.setCity(Encrypt.m1470b(sharedPreferences.getString("d", ""), f932e));
            aMapLocation.setDistrict(Encrypt.m1470b(sharedPreferences.getString("e", ""), f932e));
            aMapLocation.setCityCode(Encrypt.m1470b(sharedPreferences.getString("f", ""), f932e));
            aMapLocation.setAdCode(Encrypt.m1470b(sharedPreferences.getString("g", ""), f932e));
            aMapLocation.setAccuracy(Float.parseFloat(Encrypt.m1470b(sharedPreferences.getString("h", ""), f932e)));
            aMapLocation.setTime(Long.parseLong(Encrypt.m1470b(sharedPreferences.getString("i", ""), f932e)));
            aMapLocation.setAddress(Encrypt.m1470b(sharedPreferences.getString("j", ""), f932e));
            aMapLocation.setRoad(Encrypt.m1470b(sharedPreferences.getString("k", ""), f932e));
            aMapLocation.setPoiId(Encrypt.m1470b(sharedPreferences.getString("l", ""), f932e));
            aMapLocation.setPoiName(Encrypt.m1470b(sharedPreferences.getString("m", ""), f932e));
            return aMapLocation;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    public static void m1457a(Context context, AMapLocation aMapLocation) {
        try {
            if (f933f == null) {
                f933f = context.getSharedPreferences("last_known_location", 0);
            }
            if (f934g == null) {
                f934g = f933f.edit();
            }
            if (f932e == null) {
                f932e = Encrypt.m1464a("MD5", ClientInfoUtil.m1425b());
            }
            f934g.putString("a", Encrypt.m1477d(String.valueOf(aMapLocation.getLatitude()).getBytes(), f932e));
            f934g.putString("b", Encrypt.m1477d(String.valueOf(aMapLocation.getLongitude()).getBytes(), f932e));
            String province = aMapLocation.getProvince();
            if (province != null && province.length() > 0) {
                f934g.putString("c", Encrypt.m1477d(province.getBytes(), f932e));
            }
            province = aMapLocation.getCity();
            if (province != null && province.length() > 0) {
                f934g.putString("d", Encrypt.m1477d(province.getBytes(), f932e));
            }
            province = aMapLocation.getDistrict();
            if (province != null && province.length() > 0) {
                f934g.putString("e", Encrypt.m1477d(province.getBytes(), f932e));
            }
            province = aMapLocation.getCityCode();
            if (province != null && province.length() > 0) {
                f934g.putString("f", Encrypt.m1477d(province.getBytes(), f932e));
            }
            province = aMapLocation.getAdCode();
            if (province != null && province.length() > 0) {
                f934g.putString("g", Encrypt.m1477d(province.getBytes(), f932e));
            }
            province = aMapLocation.getAccuracy() + "";
            if (province != null && province.length() > 0) {
                f934g.putString("h", Encrypt.m1477d(province.getBytes(), f932e));
            }
            province = aMapLocation.getTime() + "";
            if (province != null && province.length() > 0) {
                f934g.putString("i", Encrypt.m1477d(province.getBytes(), f932e));
            }
            province = aMapLocation.getAddress() + "";
            if (province != null && province.length() > 0) {
                f934g.putString("j", Encrypt.m1477d(province.getBytes(), f932e));
            }
            province = aMapLocation.getRoad() + "";
            if (province != null && province.length() > 0) {
                f934g.putString("k", Encrypt.m1477d(province.getBytes(), f932e));
            }
            province = aMapLocation.getPoiId() + "";
            if (province != null && province.length() > 0) {
                f934g.putString("l", Encrypt.m1477d(province.getBytes(), f932e));
            }
            province = aMapLocation.getPoiName() + "";
            if (province != null && province.length() > 0) {
                f934g.putString("m", Encrypt.m1477d(province.getBytes(), f932e));
            }
            CoreUtil.m1458a(f934g);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    private static void m1458a(Editor editor) {
        if (editor != null) {
            if (VERSION.SDK_INT >= 9) {
                try {
                    if (f935h == null) {
                        f935h = Editor.class.getDeclaredMethod("apply", new Class[0]);
                    }
                    f935h.invoke(editor, new Object[0]);
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                    editor.commit();
                    return;
                }
            }
            editor.commit();
        }
    }

    /* renamed from: a */
    public static String m1455a() {
        try {
            String valueOf = String.valueOf(System.currentTimeMillis());
            int length = valueOf.length();
            return valueOf.substring(0, length - 2) + "1" + valueOf.substring(length - 1);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m1456a(String str, String str2) {
        try {
            if (f931d == null || f931d.length() == 0) {
                f931d = ClientInfoUtil.m1421a(null).mo8407h();
            }
            return MD5.m1480a(f931d + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m1459a(String str) throws AMapLocException {
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.has("status") && init.has("info")) {
                String string = init.getString("status");
                String string2 = init.getString("info");
                if (!string.equals("1") && string.equals("0")) {
                    if (string2.equals("INVALID_USER_KEY") || string2.equals("INSUFFICIENT_PRIVILEGES") || string2.equals("USERKEY_PLAT_NOMATCH") || string2.equals("INVALID_USER_SCODE")) {
                        throw new AMapLocException("key鉴权失败");
                    } else if (string2.equals("SERVICE_NOT_EXIST") || string2.equals("SERVICE_RESPONSE_ERROR") || string2.equals("UNKNOWN_ERROR")) {
                        throw new AMapLocException(AMapLocException.ERROR_UNKNOWN);
                    } else if (string2.equals("INVALID_PARAMS")) {
                        throw new AMapLocException("无效的参数 - IllegalArgumentException");
                    } else if (string2.equals("OVER_QUOTA")) {
                        throw new AMapLocException(AMapLocException.ERROR_OVER_QUOTA);
                    }
                }
            }
        } catch (JSONException e) {
        }
    }

    /* renamed from: c */
    public static boolean m1462c(Context context) {
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                    if (runningAppProcessInfo.importance != 100) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }
}
