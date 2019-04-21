package com.google.android.gms.analytics.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.facebook.stetho.common.Utf8Charset;
import com.google.android.gms.common.internal.zzaa;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;

class zzah extends zzd {
    private static final byte[] zzYT = "\n".getBytes();
    private final String zzCy = zza("GoogleAnalytics", zze.VERSION, VERSION.RELEASE, zzao.zza(Locale.getDefault()), Build.MODEL, Build.ID);
    private final zzal zzYS;

    private class zza {
        private int zzYU;
        private ByteArrayOutputStream zzYV = new ByteArrayOutputStream();

        public byte[] getPayload() {
            return this.zzYV.toByteArray();
        }

        public boolean zzj(zzab zzab) {
            zzaa.zzz(zzab);
            if (this.zzYU + 1 > zzah.this.zzlS().zzni()) {
                return false;
            }
            String zza = zzah.this.zza(zzab, false);
            if (zza == null) {
                zzah.this.zzlR().zza(zzab, "Error formatting hit");
                return true;
            }
            byte[] bytes = zza.getBytes();
            int length = bytes.length;
            if (length > zzah.this.zzlS().zzna()) {
                zzah.this.zzlR().zza(zzab, "Hit size exceeds the maximum size limit");
                return true;
            }
            if (this.zzYV.size() > 0) {
                length++;
            }
            if (length + this.zzYV.size() > zzah.this.zzlS().zznc()) {
                return false;
            }
            try {
                if (this.zzYV.size() > 0) {
                    this.zzYV.write(zzah.zzYT);
                }
                this.zzYV.write(bytes);
                this.zzYU++;
                return true;
            } catch (IOException e) {
                zzah.this.zze("Failed to write payload when batching hits", e);
                return true;
            }
        }

        public int zzog() {
            return this.zzYU;
        }
    }

    zzah(zzf zzf) {
        super(zzf);
        this.zzYS = new zzal(zzf.zzlQ());
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x007b A:{SYNTHETIC, Splitter:B:24:0x007b} */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0093 A:{SYNTHETIC, Splitter:B:34:0x0093} */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0098  */
    private int zza(java.net.URL r6, byte[] r7) {
        /*
        r5 = this;
        r1 = 0;
        com.google.android.gms.common.internal.zzaa.zzz(r6);
        com.google.android.gms.common.internal.zzaa.zzz(r7);
        r0 = "POST bytes, url";
        r2 = r7.length;
        r2 = java.lang.Integer.valueOf(r2);
        r5.zzb(r0, r2, r6);
        r0 = r5.zziW();
        if (r0 == 0) goto L_0x0021;
    L_0x0017:
        r0 = "Post payload\n";
        r2 = new java.lang.String;
        r2.<init>(r7);
        r5.zza(r0, r2);
    L_0x0021:
        r0 = r5.getContext();	 Catch:{ IOException -> 0x0072, all -> 0x008f }
        r0 = r0.getPackageName();	 Catch:{ IOException -> 0x0072, all -> 0x008f }
        r5.zzbS(r0);	 Catch:{ IOException -> 0x0072, all -> 0x008f }
        r2 = r5.zzc(r6);	 Catch:{ IOException -> 0x0072, all -> 0x008f }
        r0 = 1;
        r2.setDoOutput(r0);	 Catch:{ IOException -> 0x00a8 }
        r0 = r7.length;	 Catch:{ IOException -> 0x00a8 }
        r2.setFixedLengthStreamingMode(r0);	 Catch:{ IOException -> 0x00a8 }
        r2.connect();	 Catch:{ IOException -> 0x00a8 }
        r1 = r2.getOutputStream();	 Catch:{ IOException -> 0x00a8 }
        r1.write(r7);	 Catch:{ IOException -> 0x00a8 }
        r5.zzb(r2);	 Catch:{ IOException -> 0x00a8 }
        r0 = r2.getResponseCode();	 Catch:{ IOException -> 0x00a8 }
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 != r3) goto L_0x0054;
    L_0x004d:
        r3 = r5.zzkw();	 Catch:{ IOException -> 0x00a8 }
        r3.zzlN();	 Catch:{ IOException -> 0x00a8 }
    L_0x0054:
        r3 = "POST status";
        r4 = java.lang.Integer.valueOf(r0);	 Catch:{ IOException -> 0x00a8 }
        r5.zzb(r3, r4);	 Catch:{ IOException -> 0x00a8 }
        if (r1 == 0) goto L_0x0062;
    L_0x005f:
        r1.close();	 Catch:{ IOException -> 0x006b }
    L_0x0062:
        if (r2 == 0) goto L_0x0067;
    L_0x0064:
        r2.disconnect();
    L_0x0067:
        r5.zzik();
    L_0x006a:
        return r0;
    L_0x006b:
        r1 = move-exception;
        r3 = "Error closing http post connection output stream";
        r5.zze(r3, r1);
        goto L_0x0062;
    L_0x0072:
        r0 = move-exception;
        r2 = r1;
    L_0x0074:
        r3 = "Network POST connection error";
        r5.zzd(r3, r0);	 Catch:{ all -> 0x00a6 }
        if (r1 == 0) goto L_0x007e;
    L_0x007b:
        r1.close();	 Catch:{ IOException -> 0x0088 }
    L_0x007e:
        if (r2 == 0) goto L_0x0083;
    L_0x0080:
        r2.disconnect();
    L_0x0083:
        r5.zzik();
        r0 = 0;
        goto L_0x006a;
    L_0x0088:
        r0 = move-exception;
        r1 = "Error closing http post connection output stream";
        r5.zze(r1, r0);
        goto L_0x007e;
    L_0x008f:
        r0 = move-exception;
        r2 = r1;
    L_0x0091:
        if (r1 == 0) goto L_0x0096;
    L_0x0093:
        r1.close();	 Catch:{ IOException -> 0x009f }
    L_0x0096:
        if (r2 == 0) goto L_0x009b;
    L_0x0098:
        r2.disconnect();
    L_0x009b:
        r5.zzik();
        throw r0;
    L_0x009f:
        r1 = move-exception;
        r3 = "Error closing http post connection output stream";
        r5.zze(r3, r1);
        goto L_0x0096;
    L_0x00a6:
        r0 = move-exception;
        goto L_0x0091;
    L_0x00a8:
        r0 = move-exception;
        goto L_0x0074;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzah.zza(java.net.URL, byte[]):int");
    }

    private static String zza(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, str5, str6});
    }

    private void zza(StringBuilder stringBuilder, String str, String str2) throws UnsupportedEncodingException {
        if (stringBuilder.length() != 0) {
            stringBuilder.append('&');
        }
        stringBuilder.append(URLEncoder.encode(str, Utf8Charset.NAME));
        stringBuilder.append('=');
        stringBuilder.append(URLEncoder.encode(str2, Utf8Charset.NAME));
    }

    private int zzb(URL url) {
        zzaa.zzz(url);
        zzb("GET request", url);
        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = zzc(url);
            httpURLConnection.connect();
            zzb(httpURLConnection);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                zzkw().zzlN();
            }
            zzb("GET status", Integer.valueOf(responseCode));
            if (httpURLConnection == null) {
                return responseCode;
            }
            httpURLConnection.disconnect();
            return responseCode;
        } catch (IOException e) {
            zzd("Network GET connection error", e);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return 0;
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca A:{SYNTHETIC, Splitter:B:42:0x00ca} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca A:{SYNTHETIC, Splitter:B:42:0x00ca} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ca A:{SYNTHETIC, Splitter:B:42:0x00ca} */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00cf  */
    private int zzb(java.net.URL r11, byte[] r12) {
        /*
        r10 = this;
        r1 = 0;
        com.google.android.gms.common.internal.zzaa.zzz(r11);
        com.google.android.gms.common.internal.zzaa.zzz(r12);
        r0 = r10.getContext();	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r0 = r0.getPackageName();	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r10.zzbS(r0);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r4 = zzh(r12);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r0 = "POST compressed size, ratio %, url";
        r2 = r4.length;	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r6 = 100;
        r3 = r4.length;	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r8 = (long) r3;	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r6 = r6 * r8;
        r3 = r12.length;	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r8 = (long) r3;	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r6 = r6 / r8;
        r3 = java.lang.Long.valueOf(r6);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r10.zza(r0, r2, r3, r11);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r0 = r4.length;	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r2 = r12.length;	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        if (r0 <= r2) goto L_0x003f;
    L_0x0030:
        r0 = "Compressed payload is larger then uncompressed. compressed, uncompressed";
        r2 = r4.length;	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r3 = r12.length;	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r10.zzc(r0, r2, r3);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
    L_0x003f:
        r0 = r10.zziW();	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        if (r0 == 0) goto L_0x005f;
    L_0x0045:
        r2 = "Post payload";
        r3 = "\n";
        r0 = new java.lang.String;	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r0.<init>(r12);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r5 = r0.length();	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        if (r5 == 0) goto L_0x00a3;
    L_0x0058:
        r0 = r3.concat(r0);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
    L_0x005c:
        r10.zza(r2, r0);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
    L_0x005f:
        r3 = r10.zzc(r11);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r0 = 1;
        r3.setDoOutput(r0);	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
        r0 = "Content-Encoding";
        r2 = "gzip";
        r3.addRequestProperty(r0, r2);	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
        r0 = r4.length;	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
        r3.setFixedLengthStreamingMode(r0);	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
        r3.connect();	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
        r2 = r3.getOutputStream();	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
        r2.write(r4);	 Catch:{ IOException -> 0x00e8, all -> 0x00df }
        r2.close();	 Catch:{ IOException -> 0x00e8, all -> 0x00df }
        r10.zzb(r3);	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
        r0 = r3.getResponseCode();	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 != r2) goto L_0x0091;
    L_0x008a:
        r2 = r10.zzkw();	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
        r2.zzlN();	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
    L_0x0091:
        r2 = "POST status";
        r4 = java.lang.Integer.valueOf(r0);	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
        r10.zzb(r2, r4);	 Catch:{ IOException -> 0x00e5, all -> 0x00dd }
        if (r3 == 0) goto L_0x009f;
    L_0x009c:
        r3.disconnect();
    L_0x009f:
        r10.zzik();
    L_0x00a2:
        return r0;
    L_0x00a3:
        r0 = new java.lang.String;	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        r0.<init>(r3);	 Catch:{ IOException -> 0x00a9, all -> 0x00c6 }
        goto L_0x005c;
    L_0x00a9:
        r0 = move-exception;
        r2 = r1;
    L_0x00ab:
        r3 = "Network compressed POST connection error";
        r10.zzd(r3, r0);	 Catch:{ all -> 0x00e2 }
        if (r1 == 0) goto L_0x00b5;
    L_0x00b2:
        r1.close();	 Catch:{ IOException -> 0x00bf }
    L_0x00b5:
        if (r2 == 0) goto L_0x00ba;
    L_0x00b7:
        r2.disconnect();
    L_0x00ba:
        r10.zzik();
        r0 = 0;
        goto L_0x00a2;
    L_0x00bf:
        r0 = move-exception;
        r1 = "Error closing http compressed post connection output stream";
        r10.zze(r1, r0);
        goto L_0x00b5;
    L_0x00c6:
        r0 = move-exception;
        r3 = r1;
    L_0x00c8:
        if (r1 == 0) goto L_0x00cd;
    L_0x00ca:
        r1.close();	 Catch:{ IOException -> 0x00d6 }
    L_0x00cd:
        if (r3 == 0) goto L_0x00d2;
    L_0x00cf:
        r3.disconnect();
    L_0x00d2:
        r10.zzik();
        throw r0;
    L_0x00d6:
        r1 = move-exception;
        r2 = "Error closing http compressed post connection output stream";
        r10.zze(r2, r1);
        goto L_0x00cd;
    L_0x00dd:
        r0 = move-exception;
        goto L_0x00c8;
    L_0x00df:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00c8;
    L_0x00e2:
        r0 = move-exception;
        r3 = r2;
        goto L_0x00c8;
    L_0x00e5:
        r0 = move-exception;
        r2 = r3;
        goto L_0x00ab;
    L_0x00e8:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x00ab;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzah.zzb(java.net.URL, byte[]):int");
    }

    private URL zzb(zzab zzab, String str) {
        String valueOf;
        String valueOf2;
        if (zzab.zznV()) {
            valueOf2 = String.valueOf(zzlS().zznk());
            valueOf = String.valueOf(zzlS().zznm());
            valueOf = new StringBuilder(((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf).length()) + String.valueOf(str).length()).append(valueOf2).append(valueOf).append("?").append(str).toString();
        } else {
            valueOf2 = String.valueOf(zzlS().zznl());
            valueOf = String.valueOf(zzlS().zznm());
            valueOf = new StringBuilder(((String.valueOf(valueOf2).length() + 1) + String.valueOf(valueOf).length()) + String.valueOf(str).length()).append(valueOf2).append(valueOf).append("?").append(str).toString();
        }
        try {
            return new URL(valueOf);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private void zzb(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            do {
            } while (inputStream.read(new byte[1024]) > 0);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    zze("Error closing http connection input stream", e);
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    zze("Error closing http connection input stream", e2);
                }
            }
        }
    }

    private boolean zzg(zzab zzab) {
        zzaa.zzz(zzab);
        String zza = zza(zzab, !zzab.zznV());
        if (zza == null) {
            zzlR().zza(zzab, "Error formatting hit for upload");
            return true;
        } else if (zza.length() <= zzlS().zzmZ()) {
            URL zzb = zzb(zzab, zza);
            if (zzb != null) {
                return zzb(zzb) == 200;
            } else {
                zzbK("Failed to build collect GET endpoint url");
                return false;
            }
        } else {
            zza = zza(zzab, false);
            if (zza == null) {
                zzlR().zza(zzab, "Error formatting hit for POST upload");
                return true;
            }
            byte[] bytes = zza.getBytes();
            if (bytes.length > zzlS().zznb()) {
                zzlR().zza(zzab, "Hit payload exceeds size limit");
                return true;
            }
            URL zzh = zzh(zzab);
            if (zzh != null) {
                return zza(zzh, bytes) == 200;
            } else {
                zzbK("Failed to build collect POST endpoint url");
                return false;
            }
        }
    }

    private URL zzh(zzab zzab) {
        String valueOf;
        String valueOf2;
        if (zzab.zznV()) {
            valueOf = String.valueOf(zzlS().zznk());
            valueOf2 = String.valueOf(zzlS().zznm());
            valueOf = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        } else {
            valueOf = String.valueOf(zzlS().zznl());
            valueOf2 = String.valueOf(zzlS().zznm());
            valueOf = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        try {
            return new URL(valueOf);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private static byte[] zzh(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private String zzi(zzab zzab) {
        return String.valueOf(zzab.zznS());
    }

    private URL zzoe() {
        String valueOf = String.valueOf(zzlS().zznk());
        String valueOf2 = String.valueOf(zzlS().zznn());
        try {
            return new URL(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf));
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public String zza(zzab zzab, boolean z) {
        zzaa.zzz(zzab);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : zzab.zzm().entrySet()) {
                String str = (String) entry.getKey();
                if (!("ht".equals(str) || "qt".equals(str) || "AppUID".equals(str) || "z".equals(str) || "_gmsv".equals(str))) {
                    zza(stringBuilder, str, (String) entry.getValue());
                }
            }
            zza(stringBuilder, "ht", String.valueOf(zzab.zznT()));
            zza(stringBuilder, "qt", String.valueOf(zzlQ().currentTimeMillis() - zzab.zznT()));
            if (zzlS().zzmW()) {
                zza(stringBuilder, "_gmsv", zze.VERSION);
            }
            if (z) {
                long zznW = zzab.zznW();
                zza(stringBuilder, "z", zznW != 0 ? String.valueOf(zznW) : zzi(zzab));
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public List<Long> zza(List<zzab> list, boolean z) {
        zzaa.zzaj(!list.isEmpty());
        zza("Uploading batched hits. compression, count", Boolean.valueOf(z), Integer.valueOf(list.size()));
        zza zza = new zza();
        ArrayList arrayList = new ArrayList();
        for (zzab zzab : list) {
            if (!zza.zzj(zzab)) {
                break;
            }
            arrayList.add(Long.valueOf(zzab.zznS()));
        }
        if (zza.zzog() == 0) {
            return arrayList;
        }
        URL zzoe = zzoe();
        if (zzoe == null) {
            zzbK("Failed to build batching endpoint url");
            return Collections.emptyList();
        }
        int zzb = z ? zzb(zzoe, zza.getPayload()) : zza(zzoe, zza.getPayload());
        if (200 == zzb) {
            zza("Batched upload completed. Hits batched", Integer.valueOf(zza.zzog()));
            return arrayList;
        }
        zza("Network error uploading hits. status code", Integer.valueOf(zzb));
        if (zzlS().zznq().contains(Integer.valueOf(zzb))) {
            zzbJ("Server instructed the client to stop batching");
            this.zzYS.start();
        }
        return Collections.emptyList();
    }

    /* Access modifiers changed, original: protected */
    public void zzbS(String str) {
    }

    /* Access modifiers changed, original: 0000 */
    public HttpURLConnection zzc(URL url) throws IOException {
        URLConnection openConnection = HttpInstrumentation.openConnection(url.openConnection());
        if (openConnection instanceof HttpURLConnection) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setDefaultUseCaches(false);
            httpURLConnection.setConnectTimeout(zzlS().zznz());
            httpURLConnection.setReadTimeout(zzlS().zznA());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty("User-Agent", this.zzCy);
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        }
        throw new IOException("Failed to obtain http connection");
    }

    /* Access modifiers changed, original: protected */
    public void zzik() {
    }

    /* Access modifiers changed, original: protected */
    public void zzkO() {
        zza("Network initialized. User agent", this.zzCy);
    }

    public boolean zzod() {
        NetworkInfo activeNetworkInfo;
        zzkN();
        zzma();
        try {
            activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            activeNetworkInfo = null;
        }
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzbG("No network connectivity");
        return false;
    }

    public List<Long> zzs(List<zzab> list) {
        boolean z;
        boolean z2 = true;
        zzkN();
        zzma();
        zzaa.zzz(list);
        if (zzlS().zznq().isEmpty() || !this.zzYS.zzx(zzlS().zznj() * 1000)) {
            z2 = false;
            z = false;
        } else {
            z = zzlS().zzno() != zzm.zzXf;
            if (zzlS().zznp() != zzo.GZIP) {
                z2 = false;
            }
        }
        return z ? zza((List) list, z2) : zzt(list);
    }

    /* Access modifiers changed, original: 0000 */
    public List<Long> zzt(List<zzab> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (zzab zzab : list) {
            if (!zzg(zzab)) {
                break;
            }
            arrayList.add(Long.valueOf(zzab.zznS()));
            if (arrayList.size() >= zzlS().zznh()) {
                break;
            }
        }
        return arrayList;
    }
}
