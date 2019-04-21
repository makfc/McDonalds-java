package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.zzae;

public final class zzc {
    public static String zzaa(int i) {
        return zzb("&cd", i);
    }

    public static String zzab(int i) {
        return zzb("cd", i);
    }

    public static String zzad(int i) {
        return zzb("cm", i);
    }

    public static String zzae(int i) {
        return zzb("&pr", i);
    }

    public static String zzaf(int i) {
        return zzb("pr", i);
    }

    public static String zzag(int i) {
        return zzb("&promo", i);
    }

    public static String zzah(int i) {
        return zzb("promo", i);
    }

    public static String zzai(int i) {
        return zzb("pi", i);
    }

    public static String zzaj(int i) {
        return zzb("&il", i);
    }

    public static String zzak(int i) {
        return zzb("il", i);
    }

    public static String zzal(int i) {
        return zzb("cd", i);
    }

    public static String zzam(int i) {
        return zzb("cm", i);
    }

    private static String zzb(String str, int i) {
        if (i >= 1) {
            return new StringBuilder(String.valueOf(str).length() + 11).append(str).append(i).toString();
        }
        zzae.zzf("index out of range for prefix", str);
        return "";
    }
}
