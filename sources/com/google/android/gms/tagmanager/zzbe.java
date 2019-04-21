package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class zzbe {
    private static String zzbpr;
    static Map<String, String> zzbps = new HashMap();

    public static String zzad(String str, String str2) {
        if (str2 == null) {
            return str.length() > 0 ? str : null;
        } else {
            String str3 = "http://hostname/?";
            String valueOf = String.valueOf(str);
            return Uri.parse(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3)).getQueryParameter(str2);
        }
    }

    public static String zzf(Context context, String str, String str2) {
        String str3 = (String) zzbps.get(str);
        if (str3 == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            str3 = sharedPreferences != null ? sharedPreferences.getString(str, "") : "";
            zzbps.put(str, str3);
        }
        return zzad(str3, str2);
    }

    public static void zzgB(String str) {
        synchronized (zzbe.class) {
            zzbpr = str;
        }
    }

    static void zzr(Context context, String str) {
        zzdc.zzb(context, "gtm_install_referrer", "referrer", str);
        zzt(context, str);
    }

    public static String zzs(Context context, String str) {
        if (zzbpr == null) {
            synchronized (zzbe.class) {
                if (zzbpr == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        zzbpr = sharedPreferences.getString("referrer", "");
                    } else {
                        zzbpr = "";
                    }
                }
            }
        }
        return zzad(zzbpr, str);
    }

    public static void zzt(Context context, String str) {
        String zzad = zzad(str, "conv");
        if (zzad != null && zzad.length() > 0) {
            zzbps.put(zzad, str);
            zzdc.zzb(context, "gtm_click_referrers", zzad, str);
        }
    }
}
