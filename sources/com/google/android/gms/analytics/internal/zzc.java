package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;

public class zzc {
    private final zzf zzWg;

    protected zzc(zzf zzf) {
        zzaa.zzz(zzf);
        this.zzWg = zzf;
    }

    private void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zzaf zzaf = null;
        if (this.zzWg != null) {
            zzaf = this.zzWg.zzmd();
        }
        if (zzaf != null) {
            zzaf.zza(i, str, obj, obj2, obj3);
            return;
        }
        String str2 = (String) zzy.zzXF.get();
        if (Log.isLoggable(str2, i)) {
            Log.println(i, str2, zzc(str, obj, obj2, obj3));
        }
    }

    protected static String zzc(String str, Object obj, Object obj2, Object obj3) {
        CharSequence str2;
        if (str2 == null) {
            str2 = "";
        }
        String zzk = zzk(obj);
        String zzk2 = zzk(obj2);
        String zzk3 = zzk(obj3);
        StringBuilder stringBuilder = new StringBuilder();
        String str3 = "";
        if (!TextUtils.isEmpty(str2)) {
            stringBuilder.append(str2);
            str3 = ": ";
        }
        if (!TextUtils.isEmpty(zzk)) {
            stringBuilder.append(str3);
            stringBuilder.append(zzk);
            str3 = ", ";
        }
        if (!TextUtils.isEmpty(zzk2)) {
            stringBuilder.append(str3);
            stringBuilder.append(zzk2);
            str3 = ", ";
        }
        if (!TextUtils.isEmpty(zzk3)) {
            stringBuilder.append(str3);
            stringBuilder.append(zzk3);
            str3 = ", ";
        }
        return stringBuilder.toString();
    }

    private static String zzk(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (!(obj instanceof Boolean)) {
            return obj instanceof Throwable ? ((Throwable) obj).toString() : obj.toString();
        } else {
            return obj == Boolean.TRUE ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
        }
    }

    /* Access modifiers changed, original: protected */
    public Context getContext() {
        return this.zzWg.getContext();
    }

    public void zza(String str, Object obj) {
        zza(2, str, obj, null, null);
    }

    public void zza(String str, Object obj, Object obj2) {
        zza(2, str, obj, obj2, null);
    }

    public void zza(String str, Object obj, Object obj2, Object obj3) {
        zza(3, str, obj, obj2, obj3);
    }

    public void zzb(String str, Object obj) {
        zza(3, str, obj, null, null);
    }

    public void zzb(String str, Object obj, Object obj2) {
        zza(3, str, obj, obj2, null);
    }

    public void zzb(String str, Object obj, Object obj2, Object obj3) {
        zza(5, str, obj, obj2, obj3);
    }

    public void zzbG(String str) {
        zza(2, str, null, null, null);
    }

    public void zzbH(String str) {
        zza(3, str, null, null, null);
    }

    public void zzbI(String str) {
        zza(4, str, null, null, null);
    }

    public void zzbJ(String str) {
        zza(5, str, null, null, null);
    }

    public void zzbK(String str) {
        zza(6, str, null, null, null);
    }

    public void zzc(String str, Object obj) {
        zza(4, str, obj, null, null);
    }

    public void zzc(String str, Object obj, Object obj2) {
        zza(5, str, obj, obj2, null);
    }

    public void zzd(String str, Object obj) {
        zza(5, str, obj, null, null);
    }

    public void zzd(String str, Object obj, Object obj2) {
        zza(6, str, obj, obj2, null);
    }

    public void zze(String str, Object obj) {
        zza(6, str, obj, null, null);
    }

    public boolean zziW() {
        return Log.isLoggable((String) zzy.zzXF.get(), 2);
    }

    /* Access modifiers changed, original: protected */
    public void zzkN() {
        this.zzWg.zzkN();
    }

    public GoogleAnalytics zzkq() {
        return this.zzWg.zzme();
    }

    /* Access modifiers changed, original: protected */
    public zzb zzkw() {
        return this.zzWg.zzkw();
    }

    /* Access modifiers changed, original: protected */
    public zzap zzkx() {
        return this.zzWg.zzkx();
    }

    public zzf zzlO() {
        return this.zzWg;
    }

    /* Access modifiers changed, original: protected */
    public void zzlP() {
        if (zzlS().zzmW()) {
            throw new IllegalStateException("Call only supported on the client side");
        }
    }

    /* Access modifiers changed, original: protected */
    public zze zzlQ() {
        return this.zzWg.zzlQ();
    }

    /* Access modifiers changed, original: protected */
    public zzaf zzlR() {
        return this.zzWg.zzlR();
    }

    /* Access modifiers changed, original: protected */
    public zzr zzlS() {
        return this.zzWg.zzlS();
    }

    /* Access modifiers changed, original: protected */
    public zzi zzlT() {
        return this.zzWg.zzlT();
    }

    /* Access modifiers changed, original: protected */
    public zzv zzlU() {
        return this.zzWg.zzlU();
    }

    /* Access modifiers changed, original: protected */
    public zzai zzlV() {
        return this.zzWg.zzlV();
    }

    /* Access modifiers changed, original: protected */
    public zzn zzlW() {
        return this.zzWg.zzmh();
    }

    /* Access modifiers changed, original: protected */
    public zza zzlX() {
        return this.zzWg.zzmg();
    }

    /* Access modifiers changed, original: protected */
    public zzk zzlY() {
        return this.zzWg.zzlY();
    }

    /* Access modifiers changed, original: protected */
    public zzu zzlZ() {
        return this.zzWg.zzlZ();
    }
}
