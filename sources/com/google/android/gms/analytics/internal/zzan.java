package com.google.android.gms.analytics.internal;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;

public class zzan implements zzp {
    public String zzTJ;
    public double zzZi = -1.0d;
    public int zzZj = -1;
    public int zzZk = -1;
    public int zzZl = -1;
    public int zzZm = -1;
    public Map<String, String> zzZn = new HashMap();

    public String zzbV(String str) {
        String str2 = (String) this.zzZn.get(str);
        return str2 != null ? str2 : str;
    }

    public String zzr(Activity activity) {
        return zzbV(activity.getClass().getCanonicalName());
    }
}
