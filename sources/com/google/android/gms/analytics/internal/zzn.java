package com.google.android.gms.analytics.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzaa;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class zzn extends zzd {
    private volatile String zzVz;
    private Future<String> zzXm;

    /* renamed from: com.google.android.gms.analytics.internal.zzn$1 */
    class C20901 implements Callable<String> {
        C20901() {
        }

        /* renamed from: zzmU */
        public String call() throws Exception {
            return zzn.this.zzmR();
        }
    }

    /* renamed from: com.google.android.gms.analytics.internal.zzn$2 */
    class C20912 implements Callable<String> {
        C20912() {
        }

        /* renamed from: zzmU */
        public String call() throws Exception {
            return zzn.this.zzmS();
        }
    }

    protected zzn(zzf zzf) {
        super(zzf);
    }

    private boolean zzi(Context context, String str) {
        zzaa.zzdl(str);
        zzaa.zzdd("ClientId should be saved from worker thread");
        FileOutputStream fileOutputStream = null;
        try {
            zza("Storing clientId", str);
            fileOutputStream = context.openFileOutput("gaClientId", 0);
            fileOutputStream.write(str.getBytes());
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    zze("Failed to close clientId writing stream", e);
                }
            }
            return true;
        } catch (FileNotFoundException e2) {
            zze("Error creating clientId file", e2);
            if (fileOutputStream == null) {
                return false;
            }
            try {
                fileOutputStream.close();
                return false;
            } catch (IOException e3) {
                zze("Failed to close clientId writing stream", e3);
                return false;
            }
        } catch (IOException e32) {
            zze("Error writing to clientId file", e32);
            if (fileOutputStream == null) {
                return false;
            }
            try {
                fileOutputStream.close();
                return false;
            } catch (IOException e322) {
                zze("Failed to close clientId writing stream", e322);
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e3222) {
                    zze("Failed to close clientId writing stream", e3222);
                }
            }
        }
    }

    private String zzmS() {
        String zzmT = zzmT();
        try {
            return !zzi(zzlT().getContext(), zzmT) ? "0" : zzmT;
        } catch (Exception e) {
            zze("Error saving clientId file", e);
            return "0";
        }
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:64:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075 A:{SYNTHETIC, Splitter:B:33:0x0075} */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x008e A:{SYNTHETIC, Splitter:B:42:0x008e} */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x009e A:{SYNTHETIC, Splitter:B:49:0x009e} */
    public java.lang.String zzZ(android.content.Context r7) {
        /*
        r6 = this;
        r0 = 0;
        r1 = "ClientId should be loaded from worker thread";
        com.google.android.gms.common.internal.zzaa.zzdd(r1);
        r1 = "gaClientId";
        r2 = r7.openFileInput(r1);	 Catch:{ FileNotFoundException -> 0x0071, IOException -> 0x0080, all -> 0x0099 }
        r1 = 36;
        r3 = new byte[r1];	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        r1 = 0;
        r4 = 36;
        r4 = r2.read(r3, r1, r4);	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        r1 = r2.available();	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        if (r1 <= 0) goto L_0x0037;
    L_0x001d:
        r1 = "clientId file seems corrupted, deleting it.";
        r6.zzbJ(r1);	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        r2.close();	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        r1 = "gaClientId";
        r7.deleteFile(r1);	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        if (r2 == 0) goto L_0x002f;
    L_0x002c:
        r2.close();	 Catch:{ IOException -> 0x0030 }
    L_0x002f:
        return r0;
    L_0x0030:
        r1 = move-exception;
        r2 = "Failed to close client id reading stream";
        r6.zze(r2, r1);
        goto L_0x002f;
    L_0x0037:
        r1 = 14;
        if (r4 >= r1) goto L_0x0055;
    L_0x003b:
        r1 = "clientId file is empty, deleting it.";
        r6.zzbJ(r1);	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        r2.close();	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        r1 = "gaClientId";
        r7.deleteFile(r1);	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        if (r2 == 0) goto L_0x002f;
    L_0x004a:
        r2.close();	 Catch:{ IOException -> 0x004e }
        goto L_0x002f;
    L_0x004e:
        r1 = move-exception;
        r2 = "Failed to close client id reading stream";
        r6.zze(r2, r1);
        goto L_0x002f;
    L_0x0055:
        r2.close();	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        r1 = new java.lang.String;	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        r5 = 0;
        r1.<init>(r3, r5, r4);	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        r3 = "Read client id from disk";
        r6.zza(r3, r1);	 Catch:{ FileNotFoundException -> 0x00ad, IOException -> 0x00ab }
        if (r2 == 0) goto L_0x0068;
    L_0x0065:
        r2.close();	 Catch:{ IOException -> 0x006a }
    L_0x0068:
        r0 = r1;
        goto L_0x002f;
    L_0x006a:
        r0 = move-exception;
        r2 = "Failed to close client id reading stream";
        r6.zze(r2, r0);
        goto L_0x0068;
    L_0x0071:
        r1 = move-exception;
        r1 = r0;
    L_0x0073:
        if (r1 == 0) goto L_0x002f;
    L_0x0075:
        r1.close();	 Catch:{ IOException -> 0x0079 }
        goto L_0x002f;
    L_0x0079:
        r1 = move-exception;
        r2 = "Failed to close client id reading stream";
        r6.zze(r2, r1);
        goto L_0x002f;
    L_0x0080:
        r1 = move-exception;
        r2 = r0;
    L_0x0082:
        r3 = "Error reading client id file, deleting it";
        r6.zze(r3, r1);	 Catch:{ all -> 0x00a9 }
        r1 = "gaClientId";
        r7.deleteFile(r1);	 Catch:{ all -> 0x00a9 }
        if (r2 == 0) goto L_0x002f;
    L_0x008e:
        r2.close();	 Catch:{ IOException -> 0x0092 }
        goto L_0x002f;
    L_0x0092:
        r1 = move-exception;
        r2 = "Failed to close client id reading stream";
        r6.zze(r2, r1);
        goto L_0x002f;
    L_0x0099:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x009c:
        if (r2 == 0) goto L_0x00a1;
    L_0x009e:
        r2.close();	 Catch:{ IOException -> 0x00a2 }
    L_0x00a1:
        throw r0;
    L_0x00a2:
        r1 = move-exception;
        r2 = "Failed to close client id reading stream";
        r6.zze(r2, r1);
        goto L_0x00a1;
    L_0x00a9:
        r0 = move-exception;
        goto L_0x009c;
    L_0x00ab:
        r1 = move-exception;
        goto L_0x0082;
    L_0x00ad:
        r1 = move-exception;
        r1 = r2;
        goto L_0x0073;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzn.zzZ(android.content.Context):java.lang.String");
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
    }

    public String zzmP() {
        String str;
        zzma();
        synchronized (this) {
            if (this.zzVz == null) {
                this.zzXm = zzlT().zzc(new C20901());
            }
            if (this.zzXm != null) {
                try {
                    this.zzVz = (String) this.zzXm.get();
                } catch (InterruptedException e) {
                    zzd("ClientId loading or generation was interrupted", e);
                    this.zzVz = "0";
                } catch (ExecutionException e2) {
                    zze("Failed to load or generate client id", e2);
                    this.zzVz = "0";
                }
                if (this.zzVz == null) {
                    this.zzVz = "0";
                }
                zza("Loaded clientId", this.zzVz);
                this.zzXm = null;
            }
            str = this.zzVz;
        }
        return str;
    }

    /* Access modifiers changed, original: 0000 */
    public String zzmQ() {
        synchronized (this) {
            this.zzVz = null;
            this.zzXm = zzlT().zzc(new C20912());
        }
        return zzmP();
    }

    /* Access modifiers changed, original: 0000 */
    public String zzmR() {
        String zzZ = zzZ(zzlT().getContext());
        return zzZ == null ? zzmS() : zzZ;
    }

    /* Access modifiers changed, original: protected */
    public String zzmT() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
