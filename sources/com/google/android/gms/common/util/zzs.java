package com.google.android.gms.common.util;

import android.os.Build.VERSION;

public final class zzs {
    private static boolean zzcy(int i) {
        return VERSION.SDK_INT >= i;
    }

    public static boolean zzuX() {
        return zzcy(11);
    }

    public static boolean zzuZ() {
        return zzcy(13);
    }

    public static boolean zzva() {
        return zzcy(14);
    }

    public static boolean zzvb() {
        return zzcy(16);
    }

    public static boolean zzvd() {
        return zzcy(18);
    }

    public static boolean zzve() {
        return zzcy(19);
    }

    public static boolean zzvf() {
        return zzcy(20);
    }

    public static boolean zzvg() {
        return zzcy(21);
    }
}
