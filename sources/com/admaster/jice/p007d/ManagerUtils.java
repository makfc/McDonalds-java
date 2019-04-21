package com.admaster.jice.p007d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.util.Base64;
import com.admaster.jice.p006c.DeviceInfoApi;
import com.admaster.jice.p006c.Reflection;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONObject;

/* renamed from: com.admaster.jice.d.e */
public class ManagerUtils {
    /* renamed from: a */
    public static String m232a(Context context, String str) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                obj = null;
            } else {
                obj = applicationInfo.metaData.get(str);
            }
        } catch (NameNotFoundException e) {
            LOG.m224a("JiceSDK.ManagerUtils", "Could not read meta-data from AndroidManifest.xml.");
            obj = null;
        }
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    /* renamed from: b */
    public static boolean m236b(Context context, String str) {
        int a;
        if (Reflection.m192a(context)) {
            a = Reflection.m191a(context, str);
        } else {
            a = 0;
        }
        if (a == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m233a(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (ManagerUtils.m236b(context, "android.permission.ACCESS_NETWORK_STATE")) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && activeNetworkInfo.getState() == State.CONNECTED) {
                        return true;
                    }
                }
                return false;
            }
            LOG.m224a("JiceSDK", "Don't have permission to check connectivity");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m235b(Context context) {
        return ManagerUtils.m236b(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    /* renamed from: a */
    public static int m229a(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: c */
    public static int m237c(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        if (configuration != null) {
            return configuration.orientation;
        }
        return 1;
    }

    /* renamed from: a */
    public static String m231a(int i) {
        String str = "Portrait";
        if (i == 1) {
            return "Portrait";
        }
        if (i == 2) {
            return "Landscape";
        }
        return str;
    }

    /* renamed from: a */
    public static Bitmap m230a(String str) {
        Bitmap bitmap = null;
        try {
            byte[] decode = Base64.decode(str, 0);
            return BitmapFactoryInstrumentation.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap;
        }
    }

    /* renamed from: d */
    public static boolean m239d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* renamed from: b */
    public static String m234b(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes(Utf8Charset.NAME));
            StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                if ((b & 255) < 16) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(b & 255));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* renamed from: e */
    public static boolean m240e(Context context) {
        return Reflection.m193b(context);
    }

    /* renamed from: c */
    public static JSONObject m238c(Context context, String str) {
        JSONObject jSONObject = new JSONObject(DeviceInfoApi.m168a(context));
        if (jSONObject != null) {
            try {
                jSONObject.put("sdkv", "1.0.1");
                jSONObject.put("appid", str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }
}
