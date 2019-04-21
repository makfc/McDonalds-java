package com.aps;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.amap.api.location.core.ClientInfoUtil;
import com.amap.api.location.core.Encrypt;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: com.aps.d */
public class Cache {
    /* renamed from: a */
    String f4405a = null;
    /* renamed from: b */
    private LinkedHashMap<String, List<C1253a>> f4406b = new LinkedHashMap();
    /* renamed from: c */
    private SimpleDiskCache f4407c = null;
    /* renamed from: d */
    private long f4408d = 0;

    /* compiled from: Cache */
    /* renamed from: com.aps.d$a */
    static class C1253a {
        /* renamed from: a */
        private AmapLoc f4403a = null;
        /* renamed from: b */
        private String f4404b = null;

        protected C1253a() {
        }

        /* renamed from: a */
        public AmapLoc mo13238a() {
            return this.f4403a;
        }

        /* renamed from: a */
        public void mo13239a(AmapLoc amapLoc) {
            this.f4403a = amapLoc;
        }

        /* renamed from: b */
        public String mo13241b() {
            return this.f4404b;
        }

        /* renamed from: a */
        public void mo13240a(String str) {
            this.f4404b = str.replace("##", "#");
        }
    }

    Cache(Context context) {
        try {
            if (this.f4405a == null) {
                this.f4405a = Encrypt.m1464a("MD5", ClientInfoUtil.m1425b());
            }
            if (context != null) {
                File a = m5608a(context);
                if (a != null) {
                    this.f4407c = SimpleDiskCache.m5703a(a, 1, 1048576);
                }
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    private File m5608a(Context context) {
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getExternalCacheDir().getAbsolutePath()).append(File.separator);
        stringBuilder.append("locationCache");
        try {
            m5609a(new File(stringBuilder.toString()));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder.append(context.getExternalCacheDir().getAbsolutePath()).append(File.separator);
        stringBuilder.append("newlocationCache");
        return new File(stringBuilder.toString());
    }

    /* renamed from: a */
    private void m5609a(File file) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                for (File a : listFiles) {
                    m5609a(a);
                }
                file.delete();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:96:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0113  */
    /* renamed from: a */
    public void mo13244a(java.lang.String r10, com.aps.AmapLoc r11, java.lang.StringBuilder r12) {
        /*
        r9 = this;
        r2 = 0;
        r0 = com.aps.Const.f4445l;
        if (r0 != 0) goto L_0x0009;
    L_0x0005:
        r9.mo13243a();
    L_0x0008:
        return;
    L_0x0009:
        r0 = r9.mo13245a(r10, r11);
        if (r0 == 0) goto L_0x0008;
    L_0x000f:
        r0 = r11.mo13218k();
        r1 = "mem";
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x0008;
    L_0x001b:
        if (r10 == 0) goto L_0x0144;
    L_0x001d:
        r0 = "wifi";
        r0 = r10.contains(r0);
        if (r0 == 0) goto L_0x0144;
    L_0x0026:
        r0 = android.text.TextUtils.isEmpty(r12);
        if (r0 != 0) goto L_0x0008;
    L_0x002c:
        r0 = r11.mo13214i();
        r1 = 1133903872; // 0x43960000 float:300.0 double:5.60222949E-315;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 < 0) goto L_0x0138;
    L_0x0036:
        r0 = r12.toString();
        r1 = "#";
        r3 = r0.split(r1);
        r4 = r3.length;
        r1 = r2;
        r0 = r2;
    L_0x0043:
        if (r1 >= r4) goto L_0x0054;
    L_0x0045:
        r5 = r3[r1];
        r6 = ",";
        r5 = r5.contains(r6);
        if (r5 == 0) goto L_0x0051;
    L_0x004f:
        r0 = r0 + 1;
    L_0x0051:
        r1 = r1 + 1;
        goto L_0x0043;
    L_0x0054:
        r1 = 6;
        if (r0 >= r1) goto L_0x0008;
    L_0x0057:
        r0 = com.aps.C1269v.m5737b();
        r9.f4408d = r0;
        r1 = new com.aps.d$a;
        r1.<init>();
        r0 = "mem";
        r11.mo13211g(r0);
        r1.mo13239a(r11);
        if (r12 == 0) goto L_0x0073;
    L_0x006c:
        r0 = r12.toString();
        r1.mo13240a(r0);
    L_0x0073:
        r0 = r9.f4406b;
        if (r0 != 0) goto L_0x007e;
    L_0x0077:
        r0 = new java.util.LinkedHashMap;
        r0.<init>();
        r9.f4406b = r0;
    L_0x007e:
        if (r10 == 0) goto L_0x0008;
    L_0x0080:
        r0 = r9.f4406b;
        r0 = r0.containsKey(r10);
        if (r0 == 0) goto L_0x0159;
    L_0x0088:
        r0 = r9.f4406b;
        r0 = r0.get(r10);
        r0 = (java.util.List) r0;
        if (r0 == 0) goto L_0x009b;
    L_0x0092:
        r3 = r0.contains(r1);
        if (r3 != 0) goto L_0x009b;
    L_0x0098:
        r0.add(r2, r1);
    L_0x009b:
        if (r0 == 0) goto L_0x00a7;
    L_0x009d:
        r1 = r9.f4406b;
        r1.remove(r10);
        r1 = r9.f4406b;
        r1.put(r10, r0);
    L_0x00a7:
        r0 = r9.f4405a;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        if (r0 != 0) goto L_0x00b7;
    L_0x00ab:
        r0 = "MD5";
        r1 = com.amap.api.location.core.ClientInfoUtil.m1425b();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0 = com.amap.api.location.core.Encrypt.m1464a(r0, r1);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r9.f4405a = r0;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
    L_0x00b7:
        if (r12 == 0) goto L_0x00bf;
    L_0x00b9:
        r0 = r12.length();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        if (r0 != 0) goto L_0x00c6;
    L_0x00bf:
        r12 = new java.lang.StringBuilder;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0 = "cell#";
        r12.<init>(r0);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
    L_0x00c6:
        r0 = 0;
        r1 = r9.f4407c;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        if (r1 == 0) goto L_0x01b9;
    L_0x00cb:
        r0 = r9.f4407c;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0 = r0.mo13293a(r10);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r4 = r0;
    L_0x00d2:
        r0 = r12.toString();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0 = r0.getBytes();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r1 = r9.f4405a;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r5 = com.amap.api.location.core.Encrypt.m1477d(r0, r1);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0 = r11.mo13237w();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0 = r0.getBytes();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r1 = r9.f4405a;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r6 = com.amap.api.location.core.Encrypt.m1477d(r0, r1);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        if (r4 == 0) goto L_0x00f6;
    L_0x00f0:
        r0 = r4.size();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        if (r0 != 0) goto L_0x0168;
    L_0x00f6:
        r0 = new java.util.HashMap;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0.<init>();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0.put(r5, r6);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r1 = r9.f4407c;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        if (r1 == 0) goto L_0x0107;
    L_0x0102:
        r1 = r9.f4407c;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r1.mo13295b(r10, r0);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
    L_0x0107:
        r0 = "";
        r0 = r9.f4406b;
        r0 = r0.size();
        r1 = 360; // 0x168 float:5.04E-43 double:1.78E-321;
        if (r0 <= r1) goto L_0x0008;
    L_0x0113:
        r0 = r9.f4406b;
        r0 = r0.entrySet();
        r0 = r0.iterator();
        if (r0 == 0) goto L_0x0008;
    L_0x011f:
        r1 = r0.hasNext();
        if (r1 == 0) goto L_0x0008;
    L_0x0125:
        r0 = r0.next();
        r0 = (java.util.Map.Entry) r0;
        r0 = r0.getKey();
        r0 = (java.lang.String) r0;
        r1 = r9.f4406b;
        r1.remove(r0);
        goto L_0x0008;
    L_0x0138:
        r0 = r11.mo13214i();
        r1 = 1092616192; // 0x41200000 float:10.0 double:5.398241246E-315;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 > 0) goto L_0x0057;
    L_0x0142:
        goto L_0x0008;
    L_0x0144:
        if (r10 == 0) goto L_0x0057;
    L_0x0146:
        r0 = "cell";
        r0 = r10.contains(r0);
        if (r0 == 0) goto L_0x0057;
    L_0x014e:
        r0 = ",";
        r0 = r12.indexOf(r0);
        r1 = -1;
        if (r0 == r1) goto L_0x0057;
    L_0x0157:
        goto L_0x0008;
    L_0x0159:
        r0 = new java.util.ArrayList;
        r0.<init>();
        r0.add(r1);
        r1 = r9.f4406b;
        r1.put(r10, r0);
        goto L_0x00a7;
    L_0x0168:
        r0 = r4.entrySet();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r7 = r0.iterator();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r3 = 1;
    L_0x0171:
        if (r7 == 0) goto L_0x01b7;
    L_0x0173:
        r0 = r7.hasNext();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        if (r0 == 0) goto L_0x01b7;
    L_0x0179:
        r0 = r7.next();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r1 = r0.getKey();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r1 = (java.lang.String) r1;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r8 = r9.f4405a;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r1 = com.amap.api.location.core.Encrypt.m1470b(r1, r8);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r8 = r12.toString();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r1 = r9.m5611a(r1, r8);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        if (r1 == 0) goto L_0x0171;
    L_0x0195:
        r0 = r0.getKey();	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r4.remove(r0);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r4.put(r5, r6);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0 = r9.f4407c;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0.mo13295b(r10, r4);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0 = r2;
    L_0x01a5:
        if (r0 == 0) goto L_0x0107;
    L_0x01a7:
        r4.put(r5, r6);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0 = r9.f4407c;	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        r0.mo13295b(r10, r4);	 Catch:{ IOException | Exception -> 0x01b1, Exception -> 0x01b4 }
        goto L_0x0107;
    L_0x01b1:
        r0 = move-exception;
        goto L_0x0107;
    L_0x01b4:
        r0 = move-exception;
        goto L_0x0107;
    L_0x01b7:
        r0 = r3;
        goto L_0x01a5;
    L_0x01b9:
        r4 = r0;
        goto L_0x00d2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aps.Cache.mo13244a(java.lang.String, com.aps.c, java.lang.StringBuilder):void");
    }

    /* Access modifiers changed, original: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x010c  */
    /* JADX WARNING: Missing block: B:19:0x003a, code skipped:
            if (r8.length() == 0) goto L_0x003c;
     */
    /* renamed from: a */
    public com.aps.AmapLoc mo13242a(java.lang.String r7, java.lang.StringBuilder r8, java.lang.String r9) {
        /*
        r6 = this;
        r1 = -1;
        r2 = 0;
        r0 = "mem";
        r0 = r9.equals(r0);
        if (r0 == 0) goto L_0x0012;
    L_0x000a:
        r0 = com.aps.Const.f4445l;
        if (r0 != 0) goto L_0x0012;
    L_0x000e:
        r6.mo13243a();
    L_0x0011:
        return r2;
    L_0x0012:
        r0 = "";
        if (r7 == 0) goto L_0x00c2;
    L_0x0016:
        r0 = "#cellwifi";
        r0 = r7.indexOf(r0);
        if (r0 == r1) goto L_0x00c2;
    L_0x001e:
        r0 = "#cellwifi";
        r0 = r6.m5607a(r7, r8, r0, r9);
        if (r0 == 0) goto L_0x00be;
    L_0x0026:
        r1 = "found#cellwifi";
    L_0x0028:
        r1 = r6.f4405a;
        if (r1 != 0) goto L_0x0032;
    L_0x002c:
        r1 = com.amap.api.location.core.ClientInfoUtil.m1425b();
        r6.f4405a = r1;
    L_0x0032:
        if (r0 != 0) goto L_0x0178;
    L_0x0034:
        if (r8 == 0) goto L_0x003c;
    L_0x0036:
        r1 = r8.length();	 Catch:{ JSONException -> 0x0165, IOException -> 0x015a, Throwable -> 0x014f }
        if (r1 != 0) goto L_0x0043;
    L_0x003c:
        r8 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0165, IOException -> 0x015a, Throwable -> 0x014f }
        r1 = "cell#";
        r8.<init>(r1);	 Catch:{ JSONException -> 0x0165, IOException -> 0x015a, Throwable -> 0x014f }
    L_0x0043:
        r1 = r6.f4407c;	 Catch:{ JSONException -> 0x0165, IOException -> 0x015a, Throwable -> 0x014f }
        if (r1 == 0) goto L_0x0175;
    L_0x0047:
        r1 = r6.f4407c;	 Catch:{ JSONException -> 0x0165, IOException -> 0x015a, Throwable -> 0x014f }
        r1 = r1.mo13293a(r7);	 Catch:{ JSONException -> 0x0165, IOException -> 0x015a, Throwable -> 0x014f }
    L_0x004d:
        if (r1 == 0) goto L_0x0172;
    L_0x004f:
        r1 = r1.entrySet();	 Catch:{ JSONException -> 0x0165, IOException -> 0x015a, Throwable -> 0x014f }
        r4 = r1.iterator();	 Catch:{ JSONException -> 0x0165, IOException -> 0x015a, Throwable -> 0x014f }
        r3 = r0;
    L_0x0058:
        if (r4 == 0) goto L_0x00b5;
    L_0x005a:
        r0 = r4.hasNext();	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        if (r0 == 0) goto L_0x00b5;
    L_0x0060:
        r0 = r4.next();	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r1 = r0.getKey();	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r1 = (java.lang.String) r1;	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r5 = r6.f4405a;	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r1 = com.amap.api.location.core.Encrypt.m1470b(r1, r5);	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r5 = r8.toString();	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r1 = r6.m5611a(r1, r5);	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        if (r1 == 0) goto L_0x0170;
    L_0x007c:
        r0 = r0.getValue();	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r0 = (java.lang.String) r0;	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r1 = r6.f4405a;	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r0 = com.amap.api.location.core.Encrypt.m1470b(r0, r1);	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r0 = com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation.init(r0);	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r5 = new com.aps.c;	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r5.<init>(r0);	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r0 = "mem";
        r5.mo13211g(r0);	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r1 = new com.aps.d$a;	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r1.<init>();	 Catch:{ JSONException -> 0x0169, IOException -> 0x015e, Throwable -> 0x0153 }
        r1.mo13239a(r5);	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r0 = r8.toString();	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r1.mo13240a(r0);	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r0 = r6.f4406b;	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        if (r0 != 0) goto L_0x00b2;
    L_0x00ab:
        r0 = new java.util.LinkedHashMap;	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r0.<init>();	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r6.f4406b = r0;	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
    L_0x00b2:
        if (r7 != 0) goto L_0x0114;
    L_0x00b4:
        r3 = r1;
    L_0x00b5:
        r1 = r3;
    L_0x00b6:
        if (r1 == 0) goto L_0x0011;
    L_0x00b8:
        r2 = r1.mo13238a();
        goto L_0x0011;
    L_0x00be:
        r1 = "no found";
        goto L_0x0028;
    L_0x00c2:
        if (r7 == 0) goto L_0x00dc;
    L_0x00c4:
        r0 = "#wifi";
        r0 = r7.indexOf(r0);
        if (r0 == r1) goto L_0x00dc;
    L_0x00cc:
        r0 = "#wifi";
        r0 = r6.m5607a(r7, r8, r0, r9);
        if (r0 == 0) goto L_0x00d8;
    L_0x00d4:
        r1 = "found#wifi";
        goto L_0x0028;
    L_0x00d8:
        r1 = "no found";
        goto L_0x0028;
    L_0x00dc:
        if (r7 == 0) goto L_0x017d;
    L_0x00de:
        r0 = "#cell";
        r0 = r7.indexOf(r0);
        if (r0 == r1) goto L_0x017d;
    L_0x00e6:
        r0 = "mem";
        r0 = r9.equals(r0);
        if (r0 == 0) goto L_0x017b;
    L_0x00ee:
        r0 = r6.f4406b;
        r0 = r0.get(r7);
        r0 = (java.util.List) r0;
        if (r0 == 0) goto L_0x017b;
    L_0x00f8:
        r1 = r0.size();
        if (r1 <= 0) goto L_0x017b;
    L_0x00fe:
        r1 = r0.size();
        r1 = r1 + -1;
        r0 = r0.get(r1);
        r0 = (com.aps.Cache.C1253a) r0;
    L_0x010a:
        if (r0 == 0) goto L_0x0110;
    L_0x010c:
        r1 = "found#cell";
        goto L_0x0028;
    L_0x0110:
        r1 = "no found";
        goto L_0x0028;
    L_0x0114:
        r0 = r6.f4406b;	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r0 = r0.containsKey(r7);	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        if (r0 == 0) goto L_0x0140;
    L_0x011c:
        r0 = r6.f4406b;	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r0 = r0.get(r7);	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r0 = (java.util.List) r0;	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        if (r0 == 0) goto L_0x0130;
    L_0x0126:
        r3 = r0.contains(r1);	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        if (r3 != 0) goto L_0x0130;
    L_0x012c:
        r3 = 0;
        r0.add(r3, r1);	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
    L_0x0130:
        if (r0 == 0) goto L_0x013c;
    L_0x0132:
        r3 = r6.f4406b;	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r3.remove(r7);	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r3 = r6.f4406b;	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r3.put(r7, r0);	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
    L_0x013c:
        r0 = r1;
    L_0x013d:
        r3 = r0;
        goto L_0x0058;
    L_0x0140:
        r0 = new java.util.ArrayList;	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r0.<init>();	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r0.add(r1);	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r3 = r6.f4406b;	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r3.put(r7, r0);	 Catch:{ JSONException -> 0x016d, IOException -> 0x0162, IOException | Throwable | JSONException -> 0x0157 }
        r0 = r1;
        goto L_0x013d;
    L_0x014f:
        r1 = move-exception;
        r1 = r0;
        goto L_0x00b6;
    L_0x0153:
        r0 = move-exception;
        r1 = r3;
        goto L_0x00b6;
    L_0x0157:
        r0 = move-exception;
        goto L_0x00b6;
    L_0x015a:
        r1 = move-exception;
        r1 = r0;
        goto L_0x00b6;
    L_0x015e:
        r0 = move-exception;
        r1 = r3;
        goto L_0x00b6;
    L_0x0162:
        r0 = move-exception;
        goto L_0x00b6;
    L_0x0165:
        r1 = move-exception;
        r1 = r0;
        goto L_0x00b6;
    L_0x0169:
        r0 = move-exception;
        r1 = r3;
        goto L_0x00b6;
    L_0x016d:
        r0 = move-exception;
        goto L_0x00b6;
    L_0x0170:
        r0 = r3;
        goto L_0x013d;
    L_0x0172:
        r3 = r0;
        goto L_0x00b5;
    L_0x0175:
        r1 = r2;
        goto L_0x004d;
    L_0x0178:
        r1 = r0;
        goto L_0x00b6;
    L_0x017b:
        r0 = r2;
        goto L_0x010a;
    L_0x017d:
        r0 = r2;
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.aps.Cache.mo13242a(java.lang.String, java.lang.StringBuilder, java.lang.String):com.aps.c");
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public boolean mo13245a(String str, AmapLoc amapLoc) {
        if (str == null || amapLoc == null || str.indexOf("#network") == -1 || amapLoc.mo13210g() == 0.0d) {
            return false;
        }
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo13243a() {
        this.f4408d = 0;
        this.f4406b.clear();
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo13246b() {
        if (this.f4407c != null) {
            this.f4407c.mo13294a();
        }
    }

    /* renamed from: a */
    private C1253a m5607a(String str, StringBuilder stringBuilder, String str2, String str3) {
        Iterator it;
        C1253a c1253a = null;
        Hashtable hashtable = new Hashtable();
        Hashtable hashtable2 = new Hashtable();
        Hashtable hashtable3 = new Hashtable();
        if (str3.equals("mem")) {
            it = this.f4406b.entrySet().iterator();
        } else {
            it = null;
        }
        Object obj = 1;
        while (it != null && it.hasNext()) {
            List list;
            Object obj2;
            String str4;
            if (obj != null) {
                str4 = str;
                list = (List) this.f4406b.get(str);
                obj2 = null;
            } else {
                Entry entry = (Entry) it.next();
                str4 = (String) entry.getKey();
                list = (List) entry.getValue();
                obj2 = obj;
            }
            if (c1253a != null) {
                break;
            } else if (list == null) {
                obj = obj2;
            } else {
                C1253a c1253a2;
                int i = 0;
                while (true) {
                    int i2 = i;
                    if (i2 >= list.size()) {
                        c1253a2 = c1253a;
                        break;
                    }
                    c1253a2 = (C1253a) list.get(i2);
                    if (!(TextUtils.isEmpty(c1253a2.mo13241b()) || TextUtils.isEmpty(stringBuilder) || str4.indexOf(str2) == -1)) {
                        Object obj3;
                        String str5;
                        if (!m5612a(c1253a2.mo13241b(), stringBuilder)) {
                            obj3 = null;
                        } else if (c1253a2.mo13238a().mo13214i() > 300.0f) {
                            obj3 = null;
                        } else {
                            int obj32 = 1;
                        }
                        m5610a(c1253a2.mo13241b(), hashtable);
                        m5610a(stringBuilder.toString(), hashtable2);
                        hashtable3.clear();
                        for (String str52 : hashtable.keySet()) {
                            hashtable3.put(str52, "");
                        }
                        for (String str522 : hashtable2.keySet()) {
                            hashtable3.put(str522, "");
                        }
                        Set keySet = hashtable3.keySet();
                        double[] dArr = new double[keySet.size()];
                        double[] dArr2 = new double[keySet.size()];
                        int i3 = 0;
                        Iterator it2 = keySet.iterator();
                        while (true) {
                            int i4 = i3;
                            if (!it2.hasNext()) {
                                break;
                            }
                            str522 = (String) it2.next();
                            dArr[i4] = hashtable.containsKey(str522) ? 1.0d : 0.0d;
                            dArr2[i4] = hashtable2.containsKey(str522) ? 1.0d : 0.0d;
                            i3 = i4 + 1;
                        }
                        keySet.clear();
                        double a = m5606a(dArr, dArr2);
                        if (str3.equals("mem")) {
                            if ((obj32 != null && a > 0.8500000238418579d) || a > 0.8500000238418579d) {
                                break;
                            }
                        } else {
                            if (str3.equals("db") && a > 0.8500000238418579d) {
                                break;
                            }
                        }
                    }
                    i = i2 + 1;
                }
                obj = obj2;
                c1253a = c1253a2;
            }
        }
        hashtable.clear();
        hashtable2.clear();
        hashtable3.clear();
        return c1253a;
    }

    /* renamed from: a */
    private boolean m5611a(String str, String str2) {
        String str3;
        Hashtable hashtable = new Hashtable();
        Hashtable hashtable2 = new Hashtable();
        Hashtable hashtable3 = new Hashtable();
        m5610a(str2, hashtable);
        m5610a(str, hashtable2);
        hashtable3.clear();
        for (String str32 : hashtable.keySet()) {
            hashtable3.put(str32, "");
        }
        for (String str322 : hashtable2.keySet()) {
            hashtable3.put(str322, "");
        }
        Set keySet = hashtable3.keySet();
        double[] dArr = new double[keySet.size()];
        double[] dArr2 = new double[keySet.size()];
        int i = 0;
        Iterator it = keySet.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                break;
            }
            str322 = (String) it.next();
            dArr[i2] = hashtable.containsKey(str322) ? 1.0d : 0.0d;
            dArr2[i2] = hashtable2.containsKey(str322) ? 1.0d : 0.0d;
            i = i2 + 1;
        }
        keySet.clear();
        double a = m5606a(dArr, dArr2);
        hashtable.clear();
        hashtable2.clear();
        hashtable3.clear();
        if (a > 0.8500000238418579d) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m5612a(String str, StringBuilder stringBuilder) {
        int indexOf = str.indexOf(",access");
        if (indexOf == -1 || indexOf < 17 || stringBuilder.indexOf(",access") == -1) {
            return false;
        }
        if (stringBuilder.toString().indexOf(str.substring(indexOf - 17, indexOf) + ",access") != -1) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private void m5610a(String str, Hashtable<String, String> hashtable) {
        hashtable.clear();
        for (String str2 : str.split("#")) {
            if (str2.length() > 0) {
                hashtable.put(str2, "");
            }
        }
    }

    /* renamed from: a */
    private double m5606a(double[] dArr, double[] dArr2) {
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            d2 += dArr[i] * dArr[i];
            d += dArr2[i] * dArr2[i];
            d3 += dArr[i] * dArr2[i];
        }
        return d3 / (Math.sqrt(d2) * Math.sqrt(d));
    }
}
