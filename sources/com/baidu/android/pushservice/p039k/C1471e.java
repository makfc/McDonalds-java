package com.baidu.android.pushservice.p039k;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.text.TextUtils;
import com.baidu.android.pushservice.jni.BaiduAppSSOJni;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import javax.crypto.Cipher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.k.e */
public final class C1471e {
    /* renamed from: d */
    private static C1470b f5173d;
    /* renamed from: a */
    private final Context f5174a;
    /* renamed from: b */
    private int f5175b = 0;
    /* renamed from: c */
    private PublicKey f5176c;

    /* renamed from: com.baidu.android.pushservice.k.e$1 */
    class C14681 implements Comparator<C1469a> {
        C14681() {
        }

        /* renamed from: a */
        public int compare(C1469a c1469a, C1469a c1469a2) {
            int i = c1469a2.f5167b - c1469a.f5167b;
            return i == 0 ? (c1469a.f5169d && c1469a2.f5169d) ? 0 : c1469a.f5169d ? -1 : c1469a2.f5169d ? 1 : i : i;
        }
    }

    /* renamed from: com.baidu.android.pushservice.k.e$a */
    private static class C1469a {
        /* renamed from: a */
        public ApplicationInfo f5166a;
        /* renamed from: b */
        public int f5167b;
        /* renamed from: c */
        public boolean f5168c;
        /* renamed from: d */
        public boolean f5169d;

        private C1469a() {
            this.f5167b = 0;
            this.f5168c = false;
            this.f5169d = false;
        }

        /* synthetic */ C1469a(C14681 c14681) {
            this();
        }
    }

    /* renamed from: com.baidu.android.pushservice.k.e$b */
    private static class C1470b {
        /* renamed from: a */
        public String f5170a;
        /* renamed from: b */
        public String f5171b;
        /* renamed from: c */
        public int f5172c;

        private C1470b() {
            this.f5172c = 2;
        }

        /* synthetic */ C1470b(C14681 c14681) {
            this();
        }

        /* renamed from: a */
        public static C1470b m6684a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject init = JSONObjectInstrumentation.init(str);
                String string = init.getString("deviceid");
                String string2 = init.getString("imei");
                int i = init.getInt("ver");
                if (TextUtils.isEmpty(string) || string2 == null) {
                    return null;
                }
                C1470b c1470b = new C1470b();
                c1470b.f5170a = string;
                c1470b.f5171b = string2;
                c1470b.f5172c = i;
                return c1470b;
            } catch (JSONException e) {
                C1471e.m6702b(e);
                return null;
            }
        }

        /* renamed from: a */
        public String mo13944a() {
            try {
                JSONObject put = new JSONObject().put("deviceid", this.f5170a).put("imei", this.f5171b).put("ver", this.f5172c);
                return !(put instanceof JSONObject) ? put.toString() : JSONObjectInstrumentation.toString(put);
            } catch (JSONException e) {
                C1471e.m6702b(e);
                return null;
            }
        }

        /* renamed from: b */
        public String mo13945b() {
            String str = this.f5171b;
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            return this.f5170a + "|" + new StringBuffer(str).reverse().toString();
        }
    }

    private C1471e(Context context) {
        this.f5174a = context.getApplicationContext();
        m6691a();
    }

    /* renamed from: a */
    public static String m6687a(Context context) {
        return C1471e.m6707d(context).mo13945b();
    }

    /* renamed from: a */
    private static String m6688a(File file) {
        Throwable e;
        Throwable th;
        Object obj;
        String str = null;
        FileReader fileReader;
        CharArrayWriter obj2;
        try {
            fileReader = new FileReader(file);
            try {
                char[] cArr = new char[8192];
                obj2 = new CharArrayWriter();
                while (true) {
                    try {
                        int read = fileReader.read(cArr);
                        if (read <= 0) {
                            break;
                        }
                        obj2.write(cArr, 0, read);
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            C1471e.m6702b(e);
                            C1403b.m6265a(fileReader, obj2);
                            return str;
                        } catch (Throwable th2) {
                            th = th2;
                            C1403b.m6265a(fileReader, obj2);
                            throw th;
                        }
                    }
                }
                str = obj2.toString();
                C1403b.m6265a(fileReader, obj2);
            } catch (Exception e3) {
                e = e3;
                obj2 = str;
                C1471e.m6702b(e);
                C1403b.m6265a(fileReader, obj2);
                return str;
            } catch (Throwable e4) {
                obj2 = str;
                th = e4;
                C1403b.m6265a(fileReader, obj2);
                throw th;
            }
        } catch (Exception e5) {
            e4 = e5;
            obj2 = str;
            Object obj3 = str;
            C1471e.m6702b(e4);
            C1403b.m6265a(fileReader, obj2);
            return str;
        } catch (Throwable e42) {
            obj2 = str;
            fileReader = str;
            th = e42;
            C1403b.m6265a(fileReader, obj2);
            throw th;
        }
        return str;
    }

    /* renamed from: a */
    private static String m6689a(byte[] bArr) {
        if (bArr == null) {
            try {
                throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
            } catch (Exception e) {
                C1425a.m6442c("DeviceId", "IllegalArgumentException: Argument b ( byte array ) is null!");
            }
        }
        String str = "";
        str = "";
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            str = toHexString.length() == 1 ? str + "0" + toHexString : str + toHexString;
        }
        return str.toLowerCase();
    }

    /* renamed from: a */
    private List<C1469a> m6690a(Intent intent, boolean z) {
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = this.f5174a.getPackageManager();
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers != null) {
            for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                if (!(resolveInfo.activityInfo == null || resolveInfo.activityInfo.applicationInfo == null)) {
                    try {
                        Bundle bundle = packageManager.getReceiverInfo(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), 128).metaData;
                        if (bundle != null) {
                            String string = bundle.getString("galaxy_data");
                            if (!TextUtils.isEmpty(string)) {
                                byte[] a = C1465b.m6679a(string.getBytes("utf-8"));
                                JSONObject init = JSONObjectInstrumentation.init(new String(a));
                                C1469a c1469a = new C1469a();
                                c1469a.f5167b = init.getInt(HexAttributes.HEX_ATTR_THREAD_PRI);
                                c1469a.f5166a = resolveInfo.activityInfo.applicationInfo;
                                if (this.f5174a.getPackageName().equals(resolveInfo.activityInfo.applicationInfo.packageName)) {
                                    c1469a.f5169d = true;
                                }
                                if (z) {
                                    String string2 = bundle.getString("galaxy_sf");
                                    if (!TextUtils.isEmpty(string2)) {
                                        int i;
                                        PackageInfo packageInfo = packageManager.getPackageInfo(resolveInfo.activityInfo.applicationInfo.packageName, 64);
                                        JSONArray jSONArray = init.getJSONArray("sigs");
                                        String[] strArr = new String[jSONArray.length()];
                                        for (i = 0; i < strArr.length; i++) {
                                            strArr[i] = jSONArray.getString(i);
                                        }
                                        if (m6695a(strArr, m6697a(packageInfo.signatures))) {
                                            byte[] a2 = C1471e.m6696a(C1465b.m6679a(string2.getBytes()), this.f5176c);
                                            i = (a2 == null || !Arrays.equals(a2, C1474h.m6720a(a))) ? 0 : 1;
                                            if (i != 0) {
                                                c1469a.f5168c = true;
                                            }
                                        }
                                    }
                                }
                                arrayList.add(c1469a);
                            }
                        }
                    } catch (Exception e) {
                        C1425a.m6439a("DeviceId", "[collectBuddyInfos]", e);
                    }
                }
            }
        }
        Collections.sort(arrayList, new C14681());
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x003d A:{SYNTHETIC, Splitter:B:22:0x003d} */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0030 A:{SYNTHETIC, Splitter:B:15:0x0030} */
    /* renamed from: a */
    private void m6691a() {
        /*
        r4 = this;
        r2 = 0;
        r1 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x0025, all -> 0x0039 }
        r0 = com.baidu.android.pushservice.p039k.C1467d.m6682a();	 Catch:{ Exception -> 0x0025, all -> 0x0039 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x0025, all -> 0x0039 }
        r0 = "X.509";
        r0 = java.security.cert.CertificateFactory.getInstance(r0);	 Catch:{ Exception -> 0x0048 }
        r0 = r0.generateCertificate(r1);	 Catch:{ Exception -> 0x0048 }
        r0 = r0.getPublicKey();	 Catch:{ Exception -> 0x0048 }
        r4.f5176c = r0;	 Catch:{ Exception -> 0x0048 }
        if (r1 == 0) goto L_0x001f;
    L_0x001c:
        r1.close();	 Catch:{ Exception -> 0x0020 }
    L_0x001f:
        return;
    L_0x0020:
        r0 = move-exception;
        com.baidu.android.pushservice.p039k.C1471e.m6702b(r0);
        goto L_0x001f;
    L_0x0025:
        r0 = move-exception;
        r1 = r2;
    L_0x0027:
        r2 = "DeviceId";
        r3 = "[initPublicKey]";
        com.baidu.android.pushservice.p036h.C1425a.m6439a(r2, r3, r0);	 Catch:{ all -> 0x0046 }
        if (r1 == 0) goto L_0x001f;
    L_0x0030:
        r1.close();	 Catch:{ Exception -> 0x0034 }
        goto L_0x001f;
    L_0x0034:
        r0 = move-exception;
        com.baidu.android.pushservice.p039k.C1471e.m6702b(r0);
        goto L_0x001f;
    L_0x0039:
        r0 = move-exception;
        r1 = r2;
    L_0x003b:
        if (r1 == 0) goto L_0x0040;
    L_0x003d:
        r1.close();	 Catch:{ Exception -> 0x0041 }
    L_0x0040:
        throw r0;
    L_0x0041:
        r1 = move-exception;
        com.baidu.android.pushservice.p039k.C1471e.m6702b(r1);
        goto L_0x0040;
    L_0x0046:
        r0 = move-exception;
        goto L_0x003b;
    L_0x0048:
        r0 = move-exception;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p039k.C1471e.m6691a():void");
    }

    /* renamed from: a */
    private boolean m6693a(String str) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = this.f5174a.openFileOutput("libcuid.so", 1);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            if (fileOutputStream == null) {
                return true;
            }
            try {
                fileOutputStream.close();
                return true;
            } catch (Exception e) {
                C1471e.m6702b(e);
                return true;
            }
        } catch (Exception e2) {
            C1471e.m6702b(e2);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e22) {
                    C1471e.m6702b(e22);
                }
            }
            return false;
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e3) {
                    C1471e.m6702b(e3);
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m6694a(String str, String str2) {
        try {
            return System.putString(this.f5174a.getContentResolver(), str, str2);
        } catch (Exception e) {
            C1471e.m6702b(e);
            return false;
        }
    }

    /* renamed from: a */
    private boolean m6695a(String[] strArr, String[] strArr2) {
        int i = 0;
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            return false;
        }
        HashSet hashSet = new HashSet();
        for (Object add : strArr) {
            hashSet.add(add);
        }
        HashSet hashSet2 = new HashSet();
        int length = strArr2.length;
        while (i < length) {
            hashSet2.add(strArr2[i]);
            i++;
        }
        return hashSet.equals(hashSet2);
    }

    /* renamed from: a */
    private static byte[] m6696a(byte[] bArr, PublicKey publicKey) throws Exception {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(2, publicKey);
        return instance.doFinal(bArr);
    }

    /* renamed from: a */
    private String[] m6697a(Signature[] signatureArr) {
        String[] strArr = new String[signatureArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = C1471e.m6689a(C1474h.m6720a(signatureArr[i].toByteArray()));
        }
        return strArr;
    }

    /* renamed from: b */
    private C1470b m6698b() {
        boolean z;
        C1469a c1469a;
        boolean z2;
        String str;
        String str2;
        C1470b a;
        C1470b e;
        String str3 = null;
        int i = 0;
        List a2 = m6690a(new Intent("com.baidu.intent.action.GALAXY").setPackage(this.f5174a.getPackageName()), true);
        int i2;
        if (a2 == null || a2.size() == 0) {
            for (i2 = 0; i2 < 3; i2++) {
                C1425a.m6443d("DeviceId", "galaxy lib host missing meta-data,make sure you know the right way to integrate galaxy");
            }
            z = false;
        } else {
            c1469a = (C1469a) a2.get(0);
            z2 = c1469a.f5168c;
            if (!c1469a.f5168c) {
                for (i2 = 0; i2 < 3; i2++) {
                    C1425a.m6443d("DeviceId", "galaxy config err, In the release version of the signature should be matched");
                }
            }
            z = z2;
        }
        File file = new File(this.f5174a.getFilesDir(), "libcuid.so");
        C1470b a3 = file.exists() ? C1470b.m6684a(C1471e.m6711f(C1471e.m6688a(file))) : null;
        if (a3 == null) {
            this.f5175b |= 16;
            List<C1469a> a4 = m6690a(new Intent("com.baidu.intent.action.GALAXY"), z);
            if (a4 != null) {
                str = "files";
                file = this.f5174a.getFilesDir();
                if (file == null || str.equals(file.getName())) {
                    str2 = str;
                } else {
                    C1425a.m6444e("DeviceId", "fetal error:: app files dir name is unexpectedly :: " + file.getAbsolutePath());
                    str2 = file.getName();
                }
                for (C1469a c1469a2 : a4) {
                    if (!c1469a2.f5169d) {
                        File file2 = new File(new File(c1469a2.f5166a.dataDir, str2), "libcuid.so");
                        if (file2.exists()) {
                            a = C1470b.m6684a(C1471e.m6711f(C1471e.m6688a(file2)));
                            if (a != null) {
                                break;
                            }
                        }
                        a = a3;
                        a3 = a;
                    }
                }
            }
        }
        a = a3;
        if (a == null) {
            a = C1470b.m6684a(C1471e.m6711f(m6700b("com.baidu.deviceid.v2")));
        }
        boolean c = m6705c("android.permission.READ_EXTERNAL_STORAGE");
        if (a == null && c) {
            this.f5175b |= 2;
            e = m6709e();
        } else {
            e = a;
        }
        if (e == null) {
            this.f5175b |= 8;
            e = m6706d();
        }
        if (e == null && c) {
            this.f5175b |= 1;
            str = m6713h("");
            e = m6708d(str);
            i = 1;
        } else {
            str = null;
        }
        if (e == null) {
            this.f5175b |= 4;
            if (i == 0) {
                str = m6713h("");
            }
            C1470b c1470b = new C1470b();
            str2 = C1471e.m6703c(this.f5174a);
            if (VERSION.SDK_INT < 23) {
                String uuid = UUID.randomUUID().toString();
                C1425a.m6442c("DeviceId", "uuid: " + uuid);
                str2 = str + str2 + uuid;
            } else {
                str2 = "com.baidu" + str2;
            }
            c1470b.f5170a = C1472f.m6716a(str2.getBytes(), true);
            c1470b.f5171b = str;
            a = c1470b;
        } else {
            a = e;
        }
        file = new File(this.f5174a.getFilesDir(), "libcuid.so");
        if (!((this.f5175b & 16) == 0 && file.exists())) {
            str2 = TextUtils.isEmpty(null) ? C1471e.m6710e(a.mo13944a()) : null;
            m6693a(str2);
            str3 = str2;
        }
        z2 = m6704c();
        if (z2 && ((this.f5175b & 2) != 0 || TextUtils.isEmpty(m6700b("com.baidu.deviceid.v2")))) {
            if (TextUtils.isEmpty(str3)) {
                str3 = C1471e.m6710e(a.mo13944a());
            }
            m6694a("com.baidu.deviceid.v2", str3);
        }
        if (m6705c("android.permission.WRITE_EXTERNAL_STORAGE")) {
            File file3 = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
            if (!((this.f5175b & 8) == 0 && file3.exists())) {
                if (TextUtils.isEmpty(str3)) {
                    str3 = C1471e.m6710e(a.mo13944a());
                }
                C1471e.m6712g(str3);
            }
        }
        if (z2 && ((this.f5175b & 1) != 0 || TextUtils.isEmpty(m6700b("com.baidu.deviceid")))) {
            m6694a("com.baidu.deviceid", a.f5170a);
            m6694a("bd_setting_i", a.f5171b);
        }
        if (z2 && !TextUtils.isEmpty(a.f5171b)) {
            file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
            if (!((this.f5175b & 2) == 0 && file.exists())) {
                C1471e.m6701b(a.f5171b, a.f5170a);
            }
        }
        return a;
    }

    /* renamed from: b */
    public static String m6699b(Context context) {
        return C1471e.m6707d(context).f5170a;
    }

    /* renamed from: b */
    private String m6700b(String str) {
        try {
            return System.getString(this.f5174a.getContentResolver(), str);
        } catch (Exception e) {
            C1471e.m6702b(e);
            return null;
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x00b9=Splitter:B:18:0x00b9, B:22:0x00ca=Splitter:B:22:0x00ca, B:26:0x00db=Splitter:B:26:0x00db} */
    /* renamed from: b */
    private static void m6701b(java.lang.String r12, java.lang.String r13) {
        /*
        r11 = 1;
        r10 = 0;
        r0 = android.text.TextUtils.isEmpty(r12);
        if (r0 == 0) goto L_0x0010;
    L_0x0008:
        r0 = "DeviceId";
        r1 = "IMEI is null while writing ext!";
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r0, r1);
    L_0x000f:
        return;
    L_0x0010:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0.append(r12);
        r1 = "=";
        r0.append(r1);
        r0.append(r13);
        r2 = new java.io.File;
        r1 = android.os.Environment.getExternalStorageDirectory();
        r3 = "backups/.SystemConfig";
        r2.<init>(r1, r3);
        r3 = new java.io.File;
        r1 = ".cuid";
        r3.<init>(r2, r1);
        r1 = 0;
        r4 = r2.exists();	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        if (r4 == 0) goto L_0x0078;
    L_0x0039:
        r4 = r2.isDirectory();	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        if (r4 != 0) goto L_0x0078;
    L_0x003f:
        r4 = new java.util.Random;	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r4.<init>();	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r5 = r2.getParentFile();	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r6 = r2.getName();	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
    L_0x004c:
        r7 = new java.io.File;	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r8 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r8.<init>();	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r8 = r8.append(r6);	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r9 = r4.nextInt();	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r9 = ".tmp";
        r8 = r8.append(r9);	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r8 = r8.toString();	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r7.<init>(r5, r8);	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r8 = r7.exists();	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        if (r8 != 0) goto L_0x004c;
    L_0x0072:
        r2.renameTo(r7);	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r7.delete();	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
    L_0x0078:
        r2.mkdirs();	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r2 = new java.io.FileWriter;	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r4 = 0;
        r2.<init>(r3, r4);	 Catch:{ IOException -> 0x00b8, Exception -> 0x00c9, UnsatisfiedLinkError -> 0x00da }
        r0 = r0.toString();	 Catch:{ IOException -> 0x00fb, Exception -> 0x00f8, UnsatisfiedLinkError -> 0x00f5, all -> 0x00f2 }
        r1 = 2;
        r0 = com.baidu.android.pushservice.jni.BaiduAppSSOJni.encryptAES(r0, r1);	 Catch:{ IOException -> 0x00fb, Exception -> 0x00f8, UnsatisfiedLinkError -> 0x00f5, all -> 0x00f2 }
        r1 = "utf-8";
        r0 = com.baidu.android.pushservice.p039k.C1465b.m6678a(r0, r1);	 Catch:{ IOException -> 0x00fb, Exception -> 0x00f8, UnsatisfiedLinkError -> 0x00f5, all -> 0x00f2 }
        r1 = "DeviceId";
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00fb, Exception -> 0x00f8, UnsatisfiedLinkError -> 0x00f5, all -> 0x00f2 }
        r3.<init>();	 Catch:{ IOException -> 0x00fb, Exception -> 0x00f8, UnsatisfiedLinkError -> 0x00f5, all -> 0x00f2 }
        r4 = ">>> Write encoded ：\r\n";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x00fb, Exception -> 0x00f8, UnsatisfiedLinkError -> 0x00f5, all -> 0x00f2 }
        r3 = r3.append(r0);	 Catch:{ IOException -> 0x00fb, Exception -> 0x00f8, UnsatisfiedLinkError -> 0x00f5, all -> 0x00f2 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x00fb, Exception -> 0x00f8, UnsatisfiedLinkError -> 0x00f5, all -> 0x00f2 }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r1, r3);	 Catch:{ IOException -> 0x00fb, Exception -> 0x00f8, UnsatisfiedLinkError -> 0x00f5, all -> 0x00f2 }
        r2.write(r0);	 Catch:{ IOException -> 0x00fb, Exception -> 0x00f8, UnsatisfiedLinkError -> 0x00f5, all -> 0x00f2 }
        r2.flush();	 Catch:{ IOException -> 0x00fb, Exception -> 0x00f8, UnsatisfiedLinkError -> 0x00f5, all -> 0x00f2 }
        r0 = new java.io.Closeable[r11];
        r0[r10] = r2;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
        goto L_0x000f;
    L_0x00b8:
        r0 = move-exception;
    L_0x00b9:
        r2 = "DeviceId";
        r3 = "Write sdcard backup fail!\r\n";
        com.baidu.android.pushservice.p036h.C1425a.m6439a(r2, r3, r0);	 Catch:{ all -> 0x00e9 }
        r0 = new java.io.Closeable[r11];
        r0[r10] = r1;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
        goto L_0x000f;
    L_0x00c9:
        r0 = move-exception;
    L_0x00ca:
        r2 = "DeviceId";
        r3 = "Encode sdcard backup fail!\r\n";
        com.baidu.android.pushservice.p036h.C1425a.m6439a(r2, r3, r0);	 Catch:{ all -> 0x00e9 }
        r0 = new java.io.Closeable[r11];
        r0[r10] = r1;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
        goto L_0x000f;
    L_0x00da:
        r0 = move-exception;
    L_0x00db:
        r2 = "DeviceId";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r0);	 Catch:{ all -> 0x00e9 }
        r0 = new java.io.Closeable[r11];
        r0[r10] = r1;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
        goto L_0x000f;
    L_0x00e9:
        r0 = move-exception;
    L_0x00ea:
        r2 = new java.io.Closeable[r11];
        r2[r10] = r1;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r2);
        throw r0;
    L_0x00f2:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00ea;
    L_0x00f5:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00db;
    L_0x00f8:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00ca;
    L_0x00fb:
        r0 = move-exception;
        r1 = r2;
        goto L_0x00b9;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p039k.C1471e.m6701b(java.lang.String, java.lang.String):void");
    }

    /* renamed from: b */
    private static void m6702b(Throwable th) {
        C1425a.m6439a("DeviceId", "[handleThrowable] exception ", th);
    }

    /* renamed from: c */
    public static String m6703c(Context context) {
        String str = "";
        str = Secure.getString(context.getContentResolver(), "android_id");
        return TextUtils.isEmpty(str) ? "" : str;
    }

    /* renamed from: c */
    private boolean m6704c() {
        return m6705c("android.permission.WRITE_SETTINGS");
    }

    /* renamed from: c */
    private boolean m6705c(String str) {
        return this.f5174a.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    /* renamed from: d */
    private C1470b m6706d() {
        CharSequence b = m6700b("com.baidu.deviceid");
        String b2 = m6700b("bd_setting_i");
        if (TextUtils.isEmpty(b2)) {
            b2 = m6713h("");
            if (!TextUtils.isEmpty(b2)) {
                m6694a("bd_setting_i", b2);
            }
        }
        if (TextUtils.isEmpty(b)) {
            b = m6700b(C1472f.m6716a(("com.baidu" + b2 + C1471e.m6703c(this.f5174a)).getBytes(), true));
        }
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        C1470b c1470b = new C1470b();
        c1470b.f5170a = b;
        c1470b.f5171b = b2;
        return c1470b;
    }

    /* renamed from: d */
    private static C1470b m6707d(Context context) {
        if (f5173d == null) {
            synchronized (C1470b.class) {
                if (f5173d == null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    f5173d = new C1471e(context).m6698b();
                    C1425a.m6442c("DeviceId", "[getOrCreateCUIDInfo] ts = " + (SystemClock.uptimeMillis() - uptimeMillis));
                }
            }
        }
        return f5173d;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00cf A:{SYNTHETIC, Splitter:B:35:0x00cf} */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00cf A:{SYNTHETIC, Splitter:B:35:0x00cf} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0065  */
    /* renamed from: d */
    private com.baidu.android.pushservice.p039k.C1471e.C1470b m6708d(java.lang.String r11) {
        /*
        r10 = this;
        r9 = 2;
        r2 = 0;
        r3 = 0;
        r4 = 1;
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 23;
        if (r0 >= r1) goto L_0x001c;
    L_0x000a:
        r5 = r4;
    L_0x000b:
        if (r5 == 0) goto L_0x001e;
    L_0x000d:
        r0 = android.text.TextUtils.isEmpty(r11);
        if (r0 == 0) goto L_0x001e;
    L_0x0013:
        r0 = "DeviceId";
        r1 = "IMEI is null while reading ext!";
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r0, r1);
        r0 = r2;
    L_0x001b:
        return r0;
    L_0x001c:
        r5 = r3;
        goto L_0x000b;
    L_0x001e:
        r0 = "";
        r1 = new java.io.File;
        r6 = android.os.Environment.getExternalStorageDirectory();
        r7 = "baidu/.cuid";
        r1.<init>(r6, r7);
        r6 = r1.exists();
        if (r6 == 0) goto L_0x0070;
    L_0x0031:
        r4 = "DeviceId";
        r6 = "Old backup is exists!";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r4, r6);
    L_0x0038:
        r4 = new java.io.FileReader;	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r4.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r1 = new java.io.BufferedReader;	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r1.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r4 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r4.<init>();	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
    L_0x0047:
        r6 = r1.readLine();	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        if (r6 == 0) goto L_0x007d;
    L_0x004d:
        r4.append(r6);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r6 = "\r\n";
        r4.append(r6);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        goto L_0x0047;
    L_0x0056:
        r1 = move-exception;
        r1 = r11;
    L_0x0058:
        r3 = "DeviceId";
        r4 = "No sdcard backup found!";
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r3, r4);
    L_0x005f:
        r3 = android.text.TextUtils.isEmpty(r0);
        if (r3 != 0) goto L_0x0107;
    L_0x0065:
        r3 = new com.baidu.android.pushservice.k.e$b;
        r3.<init>(r2);
        r3.f5170a = r0;
        r3.f5171b = r1;
        r0 = r3;
        goto L_0x001b;
    L_0x0070:
        r1 = new java.io.File;
        r3 = android.os.Environment.getExternalStorageDirectory();
        r6 = "backups/.SystemConfig/.cuid";
        r1.<init>(r3, r6);
        r3 = r4;
        goto L_0x0038;
    L_0x007d:
        r6 = "DeviceId";
        r7 = new java.lang.StringBuilder;	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r7.<init>();	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r8 = "<<< Read encoded ：\r\n";
        r7 = r7.append(r8);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r8 = r4.toString();	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r7 = r7.append(r8);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r7 = r7.toString();	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r6, r7);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r1.close();	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r1 = r4.toString();	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r1 = r1.getBytes();	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r1 = com.baidu.android.pushservice.p039k.C1465b.m6679a(r1);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r4 = new java.lang.String;	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r6 = r1.length;	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r7 = 2;
        r1 = com.baidu.android.pushservice.jni.BaiduAppSSOJni.decryptAES(r1, r6, r7);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r4.<init>(r1);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r1 = "=";
        r1 = r4.split(r1);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        if (r1 == 0) goto L_0x0110;
    L_0x00bb:
        r4 = r1.length;	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        if (r4 != r9) goto L_0x0110;
    L_0x00be:
        if (r5 == 0) goto L_0x00d5;
    L_0x00c0:
        r4 = 0;
        r4 = r1[r4];	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r4 = r11.equals(r4);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        if (r4 == 0) goto L_0x00d5;
    L_0x00c9:
        r4 = 1;
        r0 = r1[r4];	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r1 = r11;
    L_0x00cd:
        if (r3 != 0) goto L_0x005f;
    L_0x00cf:
        com.baidu.android.pushservice.p039k.C1471e.m6701b(r1, r0);	 Catch:{ FileNotFoundException -> 0x00d3, IOException -> 0x010e, Exception -> 0x010c, UnsatisfiedLinkError -> 0x010a }
        goto L_0x005f;
    L_0x00d3:
        r3 = move-exception;
        goto L_0x0058;
    L_0x00d5:
        if (r5 != 0) goto L_0x0110;
    L_0x00d7:
        r4 = android.text.TextUtils.isEmpty(r11);	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        if (r4 == 0) goto L_0x00e0;
    L_0x00dd:
        r4 = 1;
        r11 = r1[r4];	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
    L_0x00e0:
        r4 = 1;
        r0 = r1[r4];	 Catch:{ FileNotFoundException -> 0x0056, IOException -> 0x00e5, Exception -> 0x00f1, UnsatisfiedLinkError -> 0x00fd }
        r1 = r11;
        goto L_0x00cd;
    L_0x00e5:
        r1 = move-exception;
        r3 = r1;
        r1 = r11;
    L_0x00e8:
        r4 = "DeviceId";
        r5 = "Read sdcard backup fail!\r\n";
        com.baidu.android.pushservice.p036h.C1425a.m6439a(r4, r5, r3);
        goto L_0x005f;
    L_0x00f1:
        r1 = move-exception;
        r3 = r1;
        r1 = r11;
    L_0x00f4:
        r4 = "DeviceId";
        r5 = "Decode sdcard backup fail!\r\n";
        com.baidu.android.pushservice.p036h.C1425a.m6439a(r4, r5, r3);
        goto L_0x005f;
    L_0x00fd:
        r1 = move-exception;
        r3 = r1;
        r1 = r11;
    L_0x0100:
        r4 = "DeviceId";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r4, r3);
        goto L_0x005f;
    L_0x0107:
        r0 = r2;
        goto L_0x001b;
    L_0x010a:
        r3 = move-exception;
        goto L_0x0100;
    L_0x010c:
        r3 = move-exception;
        goto L_0x00f4;
    L_0x010e:
        r3 = move-exception;
        goto L_0x00e8;
    L_0x0110:
        r1 = r11;
        goto L_0x00cd;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p039k.C1471e.m6708d(java.lang.String):com.baidu.android.pushservice.k.e$b");
    }

    /* renamed from: e */
    private C1470b m6709e() {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (file.exists()) {
            String a = C1471e.m6688a(file);
            if (!TextUtils.isEmpty(a)) {
                try {
                    byte[] a2 = C1465b.m6679a(a.getBytes());
                    if (a2 != null && a2.length > 0) {
                        return C1470b.m6684a(new String(BaiduAppSSOJni.decryptAES(a2, a2.length, 2)));
                    }
                } catch (Exception e) {
                    C1425a.m6440a("DeviceId", e);
                } catch (UnsatisfiedLinkError e2) {
                    C1425a.m6440a("DeviceId", e2);
                }
            }
        }
        return null;
    }

    /* renamed from: e */
    private static String m6710e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return C1465b.m6678a(BaiduAppSSOJni.encryptAES(str, 2), "utf-8");
        } catch (UnsupportedEncodingException e) {
            C1471e.m6702b(e);
        } catch (Exception e2) {
            C1471e.m6702b(e2);
        } catch (UnsatisfiedLinkError e22) {
            C1471e.m6702b(e22);
        }
        return "";
    }

    /* renamed from: f */
    private static String m6711f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] a = C1465b.m6679a(str.getBytes());
            return new String(BaiduAppSSOJni.decryptAES(a, a.length, 2));
        } catch (Exception e) {
            C1471e.m6702b(e);
            C1425a.m6439a("DeviceId", "[decrypt] ex", e);
        } catch (UnsatisfiedLinkError e2) {
            C1471e.m6702b(e2);
            C1425a.m6440a("DeviceId", e2);
        }
        return "";
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:15:0x0072=Splitter:B:15:0x0072, B:19:0x0082=Splitter:B:19:0x0082} */
    /* renamed from: g */
    private static void m6712g(java.lang.String r11) {
        /*
        r10 = 1;
        r9 = 0;
        r0 = new java.io.File;
        r1 = android.os.Environment.getExternalStorageDirectory();
        r2 = "backups/.SystemConfig";
        r0.<init>(r1, r2);
        r3 = new java.io.File;
        r1 = ".cuid2";
        r3.<init>(r0, r1);
        r1 = 0;
        r2 = r0.exists();	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        if (r2 == 0) goto L_0x005a;
    L_0x001b:
        r2 = r0.isDirectory();	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        if (r2 != 0) goto L_0x005a;
    L_0x0021:
        r2 = new java.util.Random;	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r2.<init>();	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r4 = r0.getParentFile();	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r5 = r0.getName();	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
    L_0x002e:
        r6 = new java.io.File;	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r7 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r7.<init>();	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r7 = r7.append(r5);	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r8 = r2.nextInt();	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r7 = r7.append(r8);	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r8 = ".tmp";
        r7 = r7.append(r8);	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r7 = r7.toString();	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r6.<init>(r4, r7);	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r7 = r6.exists();	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        if (r7 != 0) goto L_0x002e;
    L_0x0054:
        r0.renameTo(r6);	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r6.delete();	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
    L_0x005a:
        r0.mkdirs();	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r2 = new java.io.FileWriter;	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r0 = 0;
        r2.<init>(r3, r0);	 Catch:{ IOException -> 0x0071, Exception -> 0x0081 }
        r2.write(r11);	 Catch:{ IOException -> 0x00a0, Exception -> 0x009d, all -> 0x009a }
        r2.flush();	 Catch:{ IOException -> 0x00a0, Exception -> 0x009d, all -> 0x009a }
        r0 = new java.io.Closeable[r10];
        r0[r9] = r2;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
    L_0x0070:
        return;
    L_0x0071:
        r0 = move-exception;
    L_0x0072:
        r2 = "DeviceId";
        r3 = "Write sdcard backup fail!\r\n";
        com.baidu.android.pushservice.p036h.C1425a.m6439a(r2, r3, r0);	 Catch:{ all -> 0x0091 }
        r0 = new java.io.Closeable[r10];
        r0[r9] = r1;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
        goto L_0x0070;
    L_0x0081:
        r0 = move-exception;
    L_0x0082:
        r2 = "DeviceId";
        r3 = "Encode sdcard backup fail!\r\n";
        com.baidu.android.pushservice.p036h.C1425a.m6439a(r2, r3, r0);	 Catch:{ all -> 0x0091 }
        r0 = new java.io.Closeable[r10];
        r0[r9] = r1;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r0);
        goto L_0x0070;
    L_0x0091:
        r0 = move-exception;
    L_0x0092:
        r2 = new java.io.Closeable[r10];
        r2[r9] = r1;
        com.baidu.android.pushservice.p034f.C1403b.m6265a(r2);
        throw r0;
    L_0x009a:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0092;
    L_0x009d:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0082;
    L_0x00a0:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p039k.C1471e.m6712g(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:11:? A:{SYNTHETIC, RETURN, ORIG_RETURN} */
    /* renamed from: h */
    private java.lang.String m6713h(java.lang.String r5) {
        /*
        r4 = this;
        r1 = 0;
        r0 = r4.f5174a;	 Catch:{ Exception -> 0x001c }
        r2 = "phone";
        r0 = r0.getSystemService(r2);	 Catch:{ Exception -> 0x001c }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Exception -> 0x001c }
        if (r0 == 0) goto L_0x0024;
    L_0x000d:
        r0 = r0.getDeviceId();	 Catch:{ Exception -> 0x001c }
    L_0x0011:
        r0 = com.baidu.android.pushservice.p039k.C1471e.m6714i(r0);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 == 0) goto L_0x0026;
    L_0x001b:
        return r5;
    L_0x001c:
        r0 = move-exception;
        r2 = "DeviceId";
        r3 = "Read IMEI failed";
        com.baidu.android.pushservice.p036h.C1425a.m6439a(r2, r3, r0);
    L_0x0024:
        r0 = r1;
        goto L_0x0011;
    L_0x0026:
        r5 = r0;
        goto L_0x001b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p039k.C1471e.m6713h(java.lang.String):java.lang.String");
    }

    /* renamed from: i */
    private static String m6714i(String str) {
        return (str == null || !str.contains(":")) ? str : "";
    }
}
