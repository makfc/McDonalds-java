package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.C2063R;
import com.google.android.gms.common.internal.zzy;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.common.util.zzl;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzpv;
import com.google.android.gms.internal.zzpw;
import java.util.concurrent.atomic.AtomicBoolean;

public class zze {
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzqZ();
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    static final AtomicBoolean zzakA = new AtomicBoolean();
    private static final AtomicBoolean zzakB = new AtomicBoolean();
    public static boolean zzaku = false;
    public static boolean zzakv = false;
    static boolean zzakw = false;
    private static String zzakx = null;
    private static int zzaky = 0;
    private static boolean zzakz = false;

    zze() {
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return zzc.zzqV().getErrorResolutionPendingIntent(context, i, i2);
    }

    @Deprecated
    public static String getErrorString(int i) {
        return ConnectionResult.getStatusString(i);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    @java.lang.Deprecated
    public static java.lang.String getOpenSourceSoftwareLicenseInfo(android.content.Context r4) {
        /*
        r1 = 0;
        r0 = new android.net.Uri$Builder;
        r0.<init>();
        r2 = "android.resource";
        r0 = r0.scheme(r2);
        r2 = "com.google.android.gms";
        r0 = r0.authority(r2);
        r2 = "raw";
        r0 = r0.appendPath(r2);
        r2 = "third_party_licenses";
        r0 = r0.appendPath(r2);
        r0 = r0.build();
        r2 = r4.getContentResolver();	 Catch:{ Exception -> 0x004e }
        r2 = r2.openInputStream(r0);	 Catch:{ Exception -> 0x004e }
        r0 = new java.util.Scanner;	 Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
        r0.<init>(r2);	 Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
        r3 = "\\A";
        r0 = r0.useDelimiter(r3);	 Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
        r0 = r0.next();	 Catch:{ NoSuchElementException -> 0x003f, all -> 0x0047 }
        if (r2 == 0) goto L_0x003e;
    L_0x003b:
        r2.close();	 Catch:{ Exception -> 0x004e }
    L_0x003e:
        return r0;
    L_0x003f:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0045;
    L_0x0042:
        r2.close();	 Catch:{ Exception -> 0x004e }
    L_0x0045:
        r0 = r1;
        goto L_0x003e;
    L_0x0047:
        r0 = move-exception;
        if (r2 == 0) goto L_0x004d;
    L_0x004a:
        r2.close();	 Catch:{ Exception -> 0x004e }
    L_0x004d:
        throw r0;	 Catch:{ Exception -> 0x004e }
    L_0x004e:
        r0 = move-exception;
        r0 = r1;
        goto L_0x003e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zze.getOpenSourceSoftwareLicenseInfo(android.content.Context):java.lang.String");
    }

    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            context.getResources().getString(C2063R.string.common_google_play_services_unknown_issue);
        } catch (Throwable th) {
            Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
        if (!"com.google.android.gms".equals(context.getPackageName())) {
            zzaj(context);
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
            zzf zzaq = zzf.zzaq(context);
            if (!zzi.zzaB(context)) {
                try {
                    if (zzaq.zza(packageManager.getPackageInfo("com.android.vending", 8256), zzd.zzakt) == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
                        return 9;
                    }
                    if (zzaq.zza(packageInfo, zzaq.zza(packageManager.getPackageInfo("com.android.vending", 8256), zzd.zzakt)) == null) {
                        Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                        return 9;
                    }
                } catch (NameNotFoundException e) {
                    Log.w("GooglePlayServicesUtil", "Google Play Store is neither installed nor updating.");
                    return 9;
                }
            } else if (zzaq.zza(packageInfo, zzd.zzakt) == null) {
                Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
                return 9;
            }
            if (zzl.zzcx(packageInfo.versionCode) < zzl.zzcx(GOOGLE_PLAY_SERVICES_VERSION_CODE)) {
                Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires " + GOOGLE_PLAY_SERVICES_VERSION_CODE + " but found " + packageInfo.versionCode);
                return 2;
            }
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo == null) {
                try {
                    applicationInfo = packageManager.getApplicationInfo("com.google.android.gms", 0);
                } catch (NameNotFoundException e2) {
                    Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", e2);
                    return 1;
                }
            }
            return !applicationInfo.enabled ? 3 : 0;
        } catch (NameNotFoundException e3) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 1;
        }
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 9:
                return true;
            default:
                return false;
        }
    }

    @Deprecated
    public static void zzaa(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = zzc.zzqV().isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable != 0) {
            Intent zza = zzc.zzqV().zza(context, isGooglePlayServicesAvailable, "e");
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + isGooglePlayServicesAvailable);
            if (zza == null) {
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
            }
            throw new GooglePlayServicesRepairableException(isGooglePlayServicesAvailable, "Google Play Services not available", zza);
        }
    }

    @Deprecated
    public static int zzae(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return i;
        }
    }

    @Deprecated
    public static void zzag(Context context) {
        if (!zzakA.getAndSet(true)) {
            try {
                ((NotificationManager) context.getSystemService("notification")).cancel(10436);
            } catch (SecurityException e) {
            }
        }
    }

    private static void zzaj(Context context) {
        if (!zzakB.get()) {
            zzao(context);
            if (zzaky == 0) {
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
            } else if (zzaky != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                int i = GOOGLE_PLAY_SERVICES_VERSION_CODE;
                int i2 = zzaky;
                String valueOf = String.valueOf("com.google.android.gms.version");
                throw new IllegalStateException(new StringBuilder(String.valueOf(valueOf).length() + 290).append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ").append(i).append(" but found ").append(i2).append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"").append(valueOf).append("\" android:value=\"@integer/google_play_services_version\" />").toString());
            }
        }
    }

    public static boolean zzak(Context context) {
        zzao(context);
        return zzakw;
    }

    public static boolean zzal(Context context) {
        return zzak(context) || !zzra();
    }

    public static String zzam(Context context) {
        String str = context.getApplicationInfo().name;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        ApplicationInfo applicationInfo;
        str = context.getPackageName();
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            applicationInfo = zzpw.zzaH(context).getApplicationInfo(context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            applicationInfo = null;
        }
        return applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo).toString() : str;
    }

    @TargetApi(18)
    public static boolean zzan(Context context) {
        if (zzs.zzvd()) {
            Bundle applicationRestrictions = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
            if (applicationRestrictions != null && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(applicationRestrictions.getString("restricted_profile"))) {
                return true;
            }
        }
        return false;
    }

    private static void zzao(Context context) {
        if (!zzakz) {
            zzap(context);
        }
    }

    private static void zzap(Context context) {
        try {
            zzakx = context.getPackageName();
            zzpv zzaH = zzpw.zzaH(context);
            zzaky = zzy.zzax(context);
            PackageInfo packageInfo = zzaH.getPackageInfo("com.google.android.gms", 64);
            if (packageInfo != null) {
                if (zzf.zzaq(context).zza(packageInfo, zzd.zzakt[1]) != null) {
                    zzakw = true;
                    zzakz = true;
                }
            }
            zzakw = false;
            zzakz = true;
        } catch (NameNotFoundException e) {
            Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e);
            zzakz = true;
        } catch (Throwable th) {
            zzakz = true;
            throw th;
        }
    }

    @Deprecated
    public static Intent zzbC(int i) {
        return zzc.zzqV().zza(null, i, null);
    }

    static boolean zzbD(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 18:
            case 42:
                return true;
            default:
                return false;
        }
    }

    @Deprecated
    public static boolean zzc(Context context, int i) {
        return i == 18 ? true : i == 1 ? zzk(context, "com.google.android.gms") : false;
    }

    @Deprecated
    public static boolean zzd(Context context, int i) {
        return i == 9 ? zzk(context, "com.android.vending") : false;
    }

    @Deprecated
    public static boolean zze(Context context, int i) {
        return com.google.android.gms.common.util.zzy.zze(context, i);
    }

    @TargetApi(21)
    static boolean zzk(Context context, String str) {
        if (zzs.zzvg()) {
            for (SessionInfo appPackageName : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                if (str.equals(appPackageName.getAppPackageName())) {
                    return true;
                }
            }
        }
        if (zzan(context)) {
            return false;
        }
        try {
            return context.getPackageManager().getApplicationInfo(str, 8192).enabled;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    private static int zzqZ() {
        return com.google.android.gms.common.internal.zze.zzaqR;
    }

    @Deprecated
    public static boolean zzra() {
        return "user".equals(Build.TYPE);
    }
}
