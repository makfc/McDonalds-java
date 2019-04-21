package com.ensighten;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/* renamed from: com.ensighten.z */
public final class C1866z {
    /* renamed from: a */
    public String f6012a;
    /* renamed from: b */
    public int f6013b = -1;
    /* renamed from: c */
    public String f6014c = "unknown";
    /* renamed from: d */
    public String f6015d = Ensighten.getStringFromSharedPrefsForKey("ensightenPrevVersion");
    /* renamed from: e */
    public C1865a f6016e = C1865a.REGULAR;

    /* renamed from: com.ensighten.z$a */
    public enum C1865a {
        INSTALL,
        UPGRADE,
        REGULAR
    }

    public C1866z(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.f6013b = packageInfo.versionCode;
            this.f6014c = packageInfo.versionName;
            this.f6012a = packageInfo.versionName;
        } catch (NameNotFoundException e) {
            this.f6012a = "0.0";
        }
        if (this.f6015d == null || "".equals(this.f6015d)) {
            Ensighten.saveStringToSharedPrefs("ensightenPrevVersion", this.f6012a);
            this.f6016e = C1865a.INSTALL;
        } else if (-1 == this.f6015d.indexOf(this.f6012a)) {
            this.f6015d += "|" + this.f6012a;
            Ensighten.saveStringToSharedPrefs("ensightenPrevVersion", this.f6015d);
            this.f6016e = C1865a.UPGRADE;
        }
    }
}
