package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.util.zze;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class zzq extends zzaa {

    @WorkerThread
    interface zza {
        void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map);
    }

    @WorkerThread
    private static class zzb implements Runnable {
        private final int zzDO;
        private final String zzZC;
        private final zza zzbdl;
        private final Throwable zzbdm;
        private final byte[] zzbdn;
        private final Map<String, List<String>> zzbdo;

        private zzb(String str, zza zza, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
            zzaa.zzz(zza);
            this.zzbdl = zza;
            this.zzDO = i;
            this.zzbdm = th;
            this.zzbdn = bArr;
            this.zzZC = str;
            this.zzbdo = map;
        }

        public void run() {
            this.zzbdl.zza(this.zzZC, this.zzDO, this.zzbdm, this.zzbdn, this.zzbdo);
        }
    }

    @WorkerThread
    private class zzc implements Runnable {
        private final URL zzCd;
        private final String zzZC;
        private final byte[] zzbdp;
        private final zza zzbdq;
        private final Map<String, String> zzbdr;

        public zzc(String str, URL url, byte[] bArr, Map<String, String> map, zza zza) {
            zzaa.zzdl(str);
            zzaa.zzz(url);
            zzaa.zzz(zza);
            this.zzCd = url;
            this.zzbdp = bArr;
            this.zzbdq = zza;
            this.zzZC = str;
            this.zzbdr = map;
        }

        /* JADX WARNING: Removed duplicated region for block: B:37:0x00ee A:{SYNTHETIC, Splitter:B:37:0x00ee} */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00f3  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00ee A:{SYNTHETIC, Splitter:B:37:0x00ee} */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00f3  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00ee A:{SYNTHETIC, Splitter:B:37:0x00ee} */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00f3  */
        public void run() {
            /*
            r13 = this;
            r4 = 0;
            r0 = com.google.android.gms.measurement.internal.zzq.this;
            r0.zzFb();
            r3 = 0;
            r0 = com.google.android.gms.measurement.internal.zzq.this;	 Catch:{ IOException -> 0x012e, all -> 0x00e7 }
            r1 = r13.zzZC;	 Catch:{ IOException -> 0x012e, all -> 0x00e7 }
            r0.zzbS(r1);	 Catch:{ IOException -> 0x012e, all -> 0x00e7 }
            r0 = com.google.android.gms.measurement.internal.zzq.this;	 Catch:{ IOException -> 0x012e, all -> 0x00e7 }
            r1 = r13.zzCd;	 Catch:{ IOException -> 0x012e, all -> 0x00e7 }
            r2 = r0.zzc(r1);	 Catch:{ IOException -> 0x012e, all -> 0x00e7 }
            r0 = r13.zzbdr;	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            if (r0 == 0) goto L_0x0069;
        L_0x001a:
            r0 = r13.zzbdr;	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = r0.entrySet();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r5 = r0.iterator();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
        L_0x0024:
            r0 = r5.hasNext();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            if (r0 == 0) goto L_0x0069;
        L_0x002a:
            r0 = r5.next();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = (java.util.Map.Entry) r0;	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r1 = r0.getKey();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r1 = (java.lang.String) r1;	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = r0.getValue();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = (java.lang.String) r0;	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r2.addRequestProperty(r1, r0);	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            goto L_0x0024;
        L_0x0040:
            r9 = move-exception;
            r11 = r4;
            r8 = r3;
            r0 = r4;
            r1 = r2;
        L_0x0045:
            if (r0 == 0) goto L_0x004a;
        L_0x0047:
            r0.close();	 Catch:{ IOException -> 0x00d5 }
        L_0x004a:
            if (r1 == 0) goto L_0x004f;
        L_0x004c:
            r1.disconnect();
        L_0x004f:
            r0 = com.google.android.gms.measurement.internal.zzq.this;
            r0.zzik();
            r0 = com.google.android.gms.measurement.internal.zzq.this;
            r0 = r0.zzFl();
            r5 = new com.google.android.gms.measurement.internal.zzq$zzb;
            r6 = r13.zzZC;
            r7 = r13.zzbdq;
            r10 = r4;
            r12 = r4;
            r5.<init>(r6, r7, r8, r9, r10, r11);
            r0.zzg(r5);
        L_0x0068:
            return;
        L_0x0069:
            r0 = r13.zzbdp;	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            if (r0 == 0) goto L_0x00a9;
        L_0x006d:
            r0 = com.google.android.gms.measurement.internal.zzq.this;	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = r0.zzFi();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r1 = r13.zzbdp;	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r1 = r0.zzh(r1);	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = com.google.android.gms.measurement.internal.zzq.this;	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = r0.zzFm();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = r0.zzFL();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r5 = "Uploading data. size";
            r6 = r1.length;	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r6 = java.lang.Integer.valueOf(r6);	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0.zzj(r5, r6);	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = 1;
            r2.setDoOutput(r0);	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = "Content-Encoding";
            r5 = "gzip";
            r2.addRequestProperty(r0, r5);	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = r1.length;	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r2.setFixedLengthStreamingMode(r0);	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r2.connect();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = r2.getOutputStream();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0.write(r1);	 Catch:{ IOException -> 0x0135, all -> 0x0126 }
            r0.close();	 Catch:{ IOException -> 0x0135, all -> 0x0126 }
        L_0x00a9:
            r3 = r2.getResponseCode();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r6 = r2.getHeaderFields();	 Catch:{ IOException -> 0x0040, all -> 0x0121 }
            r0 = com.google.android.gms.measurement.internal.zzq.this;	 Catch:{ IOException -> 0x013b, all -> 0x012a }
            r5 = r0.zzc(r2);	 Catch:{ IOException -> 0x013b, all -> 0x012a }
            if (r2 == 0) goto L_0x00bc;
        L_0x00b9:
            r2.disconnect();
        L_0x00bc:
            r0 = com.google.android.gms.measurement.internal.zzq.this;
            r0.zzik();
            r0 = com.google.android.gms.measurement.internal.zzq.this;
            r8 = r0.zzFl();
            r0 = new com.google.android.gms.measurement.internal.zzq$zzb;
            r1 = r13.zzZC;
            r2 = r13.zzbdq;
            r7 = r4;
            r0.<init>(r1, r2, r3, r4, r5, r6);
            r8.zzg(r0);
            goto L_0x0068;
        L_0x00d5:
            r0 = move-exception;
            r2 = com.google.android.gms.measurement.internal.zzq.this;
            r2 = r2.zzFm();
            r2 = r2.zzFE();
            r3 = "Error closing HTTP compressed POST connection output stream";
            r2.zzj(r3, r0);
            goto L_0x004a;
        L_0x00e7:
            r0 = move-exception;
            r8 = r0;
            r6 = r4;
            r2 = r4;
            r0 = r4;
        L_0x00ec:
            if (r0 == 0) goto L_0x00f1;
        L_0x00ee:
            r0.close();	 Catch:{ IOException -> 0x0110 }
        L_0x00f1:
            if (r2 == 0) goto L_0x00f6;
        L_0x00f3:
            r2.disconnect();
        L_0x00f6:
            r0 = com.google.android.gms.measurement.internal.zzq.this;
            r0.zzik();
            r0 = com.google.android.gms.measurement.internal.zzq.this;
            r9 = r0.zzFl();
            r0 = new com.google.android.gms.measurement.internal.zzq$zzb;
            r1 = r13.zzZC;
            r2 = r13.zzbdq;
            r5 = r4;
            r7 = r4;
            r0.<init>(r1, r2, r3, r4, r5, r6);
            r9.zzg(r0);
            throw r8;
        L_0x0110:
            r0 = move-exception;
            r1 = com.google.android.gms.measurement.internal.zzq.this;
            r1 = r1.zzFm();
            r1 = r1.zzFE();
            r5 = "Error closing HTTP compressed POST connection output stream";
            r1.zzj(r5, r0);
            goto L_0x00f1;
        L_0x0121:
            r0 = move-exception;
            r8 = r0;
            r6 = r4;
            r0 = r4;
            goto L_0x00ec;
        L_0x0126:
            r1 = move-exception;
            r8 = r1;
            r6 = r4;
            goto L_0x00ec;
        L_0x012a:
            r0 = move-exception;
            r8 = r0;
            r0 = r4;
            goto L_0x00ec;
        L_0x012e:
            r9 = move-exception;
            r11 = r4;
            r8 = r3;
            r0 = r4;
            r1 = r4;
            goto L_0x0045;
        L_0x0135:
            r9 = move-exception;
            r11 = r4;
            r8 = r3;
            r1 = r2;
            goto L_0x0045;
        L_0x013b:
            r9 = move-exception;
            r11 = r6;
            r8 = r3;
            r0 = r4;
            r1 = r2;
            goto L_0x0045;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzq$zzc.run():void");
        }
    }

    public zzq(zzx zzx) {
        super(zzx);
    }

    @WorkerThread
    private byte[] zzc(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            return toByteArray;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public /* bridge */ /* synthetic */ void zzFb() {
        super.zzFb();
    }

    public /* bridge */ /* synthetic */ zzc zzFc() {
        return super.zzFc();
    }

    public /* bridge */ /* synthetic */ zzac zzFd() {
        return super.zzFd();
    }

    public /* bridge */ /* synthetic */ zzn zzFe() {
        return super.zzFe();
    }

    public /* bridge */ /* synthetic */ zzg zzFf() {
        return super.zzFf();
    }

    public /* bridge */ /* synthetic */ zzad zzFg() {
        return super.zzFg();
    }

    public /* bridge */ /* synthetic */ zze zzFh() {
        return super.zzFh();
    }

    public /* bridge */ /* synthetic */ zzal zzFi() {
        return super.zzFi();
    }

    public /* bridge */ /* synthetic */ zzv zzFj() {
        return super.zzFj();
    }

    public /* bridge */ /* synthetic */ zzaf zzFk() {
        return super.zzFk();
    }

    public /* bridge */ /* synthetic */ zzw zzFl() {
        return super.zzFl();
    }

    public /* bridge */ /* synthetic */ zzp zzFm() {
        return super.zzFm();
    }

    public /* bridge */ /* synthetic */ zzt zzFn() {
        return super.zzFn();
    }

    public /* bridge */ /* synthetic */ zzd zzFo() {
        return super.zzFo();
    }

    @WorkerThread
    public void zza(String str, URL url, Map<String, String> map, zza zza) {
        zzkN();
        zzma();
        zzaa.zzz(url);
        zzaa.zzz(zza);
        zzFl().zzh(new zzc(str, url, null, map, zza));
    }

    @WorkerThread
    public void zza(String str, URL url, byte[] bArr, Map<String, String> map, zza zza) {
        zzkN();
        zzma();
        zzaa.zzz(url);
        zzaa.zzz(bArr);
        zzaa.zzz(zza);
        zzFl().zzh(new zzc(str, url, bArr, map, zza));
    }

    /* Access modifiers changed, original: protected */
    public void zzbS(String str) {
    }

    /* Access modifiers changed, original: protected */
    @WorkerThread
    public HttpURLConnection zzc(URL url) throws IOException {
        URLConnection openConnection = HttpInstrumentation.openConnection(url.openConnection());
        if (openConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout((int) zzFo().zzEK());
            httpURLConnection.setReadTimeout((int) zzFo().zzEL());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain HTTP connection");
    }

    /* Access modifiers changed, original: protected */
    public void zzik() {
    }

    public /* bridge */ /* synthetic */ void zzkN() {
        super.zzkN();
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
    }

    public /* bridge */ /* synthetic */ void zzlP() {
        super.zzlP();
    }

    public /* bridge */ /* synthetic */ zze zzlQ() {
        return super.zzlQ();
    }

    public boolean zzod() {
        NetworkInfo activeNetworkInfo;
        zzma();
        try {
            activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            activeNetworkInfo = null;
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
