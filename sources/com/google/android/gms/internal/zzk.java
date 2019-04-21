package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.facebook.stetho.common.Utf8Charset;
import com.newrelic.agent.android.payload.PayloadController;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public abstract class zzk<T> implements Comparable<zzk<T>> {
    private final zza zzC;
    private final int zzD;
    private final String zzE;
    private final int zzF;
    private final com.google.android.gms.internal.zzm.zza zzG;
    private Integer zzH;
    private zzl zzI;
    private boolean zzJ;
    private boolean zzL;
    private long zzM;
    private zzo zzN;
    private com.google.android.gms.internal.zzb.zza zzO;

    public enum zza {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    private byte[] zza(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (UnsupportedEncodingException e) {
            Throwable th = e;
            String str2 = "Encoding not supported: ";
            String valueOf = String.valueOf(str);
            throw new RuntimeException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
        }
    }

    public Map<String, String> getHeaders() throws zza {
        return Collections.emptyMap();
    }

    public int getMethod() {
        return this.zzD;
    }

    public String getUrl() {
        return this.zzE;
    }

    public boolean isCanceled() {
        return false;
    }

    public String toString() {
        String str = "0x";
        String valueOf = String.valueOf(Integer.toHexString(zzf()));
        Object concat = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        str = "[ ] ";
        String valueOf2 = String.valueOf(getUrl());
        String valueOf3 = String.valueOf(zzr());
        String valueOf4 = String.valueOf(this.zzH);
        return new StringBuilder(((((String.valueOf(str).length() + 3) + String.valueOf(valueOf2).length()) + String.valueOf(concat).length()) + String.valueOf(valueOf3).length()) + String.valueOf(valueOf4).length()).append(str).append(valueOf2).append(" ").append(concat).append(" ").append(valueOf3).append(" ").append(valueOf4).toString();
    }

    public zzk<?> zza(com.google.android.gms.internal.zzb.zza zza) {
        this.zzO = zza;
        return this;
    }

    public abstract zzm<T> zza(zzi zzi);

    public abstract void zza(T t);

    /* Access modifiers changed, original: protected */
    public zzr zzb(zzr zzr) {
        return zzr;
    }

    /* renamed from: zzc */
    public int compareTo(zzk<T> zzk) {
        zza zzr = zzr();
        zza zzr2 = zzk.zzr();
        return zzr == zzr2 ? this.zzH.intValue() - zzk.zzH.intValue() : zzr2.ordinal() - zzr.ordinal();
    }

    public void zzc(zzr zzr) {
        if (this.zzG != null) {
            this.zzG.zze(zzr);
        }
    }

    public void zzc(String str) {
        if (zza.zzaj) {
            this.zzC.zza(str, Thread.currentThread().getId());
        } else if (this.zzM == 0) {
            this.zzM = SystemClock.elapsedRealtime();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void zzd(final String str) {
        if (this.zzI != null) {
            this.zzI.zzf(this);
        }
        if (zza.zzaj) {
            final long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        zzk.this.zzC.zza(str, id);
                        zzk.this.zzC.zzd(toString());
                    }
                });
                return;
            }
            this.zzC.zza(str, id);
            this.zzC.zzd(toString());
            return;
        }
        if (SystemClock.elapsedRealtime() - this.zzM >= PayloadController.PAYLOAD_COLLECTOR_TIMEOUT) {
            zzs.zzb("%d ms: %s", Long.valueOf(SystemClock.elapsedRealtime() - this.zzM), toString());
        }
    }

    public int zzf() {
        return this.zzF;
    }

    public String zzg() {
        return getUrl();
    }

    public com.google.android.gms.internal.zzb.zza zzh() {
        return this.zzO;
    }

    /* Access modifiers changed, original: protected */
    @Deprecated
    public Map<String, String> zzi() throws zza {
        return zzm();
    }

    /* Access modifiers changed, original: protected */
    @Deprecated
    public String zzj() {
        return zzn();
    }

    @Deprecated
    public String zzk() {
        return zzo();
    }

    @Deprecated
    public byte[] zzl() throws zza {
        Map zzi = zzi();
        return (zzi == null || zzi.size() <= 0) ? null : zza(zzi, zzj());
    }

    /* Access modifiers changed, original: protected */
    public Map<String, String> zzm() throws zza {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public String zzn() {
        return Utf8Charset.NAME;
    }

    public String zzo() {
        String str = "application/x-www-form-urlencoded; charset=";
        String valueOf = String.valueOf(zzn());
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public byte[] zzp() throws zza {
        Map zzm = zzm();
        return (zzm == null || zzm.size() <= 0) ? null : zza(zzm, zzn());
    }

    public final boolean zzq() {
        return this.zzJ;
    }

    public zza zzr() {
        return zza.NORMAL;
    }

    public final int zzs() {
        return this.zzN.zzc();
    }

    public zzo zzt() {
        return this.zzN;
    }

    public void zzu() {
        this.zzL = true;
    }

    public boolean zzv() {
        return this.zzL;
    }
}
