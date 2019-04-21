package com.p044ta.utdid2.device;

import android.content.Context;
import android.os.Binder;
import android.provider.Settings.System;
import android.text.TextUtils;
import com.p044ta.utdid2.p055a.p056a.C4315b;
import com.p044ta.utdid2.p055a.p056a.C4316c;
import com.p044ta.utdid2.p055a.p056a.C4317d;
import com.p044ta.utdid2.p055a.p056a.C4320e;
import com.p044ta.utdid2.p055a.p056a.C4321f;
import com.p044ta.utdid2.p057b.p058a.C4327c;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.ta.utdid2.device.c */
public class C4334c {
    /* renamed from: a */
    private static C4334c f6759a = null;
    /* renamed from: e */
    private static final Object f6760e = new Object();
    /* renamed from: j */
    private static final String f6761j = (".UTSystemConfig" + File.separator + "Global");
    /* renamed from: a */
    private C4327c f6762a = null;
    /* renamed from: a */
    private C4335d f6763a = null;
    /* renamed from: b */
    private C4327c f6764b = null;
    /* renamed from: b */
    private Pattern f6765b = Pattern.compile("[^0-9a-zA-Z=/+]+");
    /* renamed from: g */
    private String f6766g = null;
    /* renamed from: h */
    private String f6767h = "xx_utdid_key";
    /* renamed from: i */
    private String f6768i = "xx_utdid_domain";
    private Context mContext = null;

    private C4334c(Context context) {
        this.mContext = context;
        this.f6764b = new C4327c(context, f6761j, "Alvin2", false, true);
        this.f6762a = new C4327c(context, ".DataStorage", "ContextData", false, true);
        this.f6763a = new C4335d();
        this.f6767h = String.format("K_%d", new Object[]{Integer.valueOf(C4321f.hashCode(this.f6767h))});
        this.f6768i = String.format("D_%d", new Object[]{Integer.valueOf(C4321f.hashCode(this.f6768i))});
    }

    /* renamed from: c */
    private void m8668c() {
        Object obj = 1;
        if (this.f6764b != null) {
            if (C4321f.isEmpty(this.f6764b.getString("UTDID2"))) {
                String string = this.f6764b.getString("UTDID");
                if (!C4321f.isEmpty(string)) {
                    m7833d(string);
                }
            }
            Object obj2 = null;
            if (!C4321f.isEmpty(this.f6764b.getString("DID"))) {
                this.f6764b.remove("DID");
                obj2 = 1;
            }
            if (!C4321f.isEmpty(this.f6764b.getString("EI"))) {
                this.f6764b.remove("EI");
                obj2 = 1;
            }
            if (C4321f.isEmpty(this.f6764b.getString("SI"))) {
                obj = obj2;
            } else {
                this.f6764b.remove("SI");
            }
            if (obj != null) {
                this.f6764b.commit();
            }
        }
    }

    /* renamed from: a */
    public static C4334c m7827a(Context context) {
        if (context != null && f6759a == null) {
            synchronized (f6760e) {
                if (f6759a == null) {
                    f6759a = new C4334c(context);
                    f6759a.m7831c();
                }
            }
        }
        return f6759a;
    }

    /* renamed from: d */
    private void m7833d(String str) {
        if (m7828a(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.length() == 24 && this.f6764b != null) {
                this.f6764b.putString("UTDID2", str);
                this.f6764b.commit();
            }
        }
    }

    /* renamed from: e */
    private void m7834e(String str) {
        if (str != null && this.f6762a != null && !str.equals(this.f6762a.getString(this.f6767h))) {
            this.f6762a.putString(this.f6767h, str);
            this.f6762a.commit();
        }
    }

    /* renamed from: f */
    private void m7836f(String str) {
        if (mo33749e() && m7828a(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length()) {
                String str2 = null;
                try {
                    str2 = System.getString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk");
                } catch (Exception e) {
                }
                if (!m7828a(str2)) {
                    try {
                        System.putString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk", str);
                    } catch (Exception e2) {
                    }
                }
            }
        }
    }

    /* renamed from: g */
    private void m7837g(String str) {
        Object obj = null;
        try {
            obj = System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
        } catch (Exception e) {
        }
        if (!str.equals(obj)) {
            try {
                System.putString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp", str);
            } catch (Exception e2) {
            }
        }
    }

    /* renamed from: h */
    private void m7838h(String str) {
        if (mo33749e() && str != null) {
            m7837g(str);
        }
    }

    /* renamed from: c */
    private String m7831c() {
        if (this.f6764b != null) {
            String string = this.f6764b.getString("UTDID2");
            if (!(C4321f.isEmpty(string) || this.f6763a.mo33751c(string) == null)) {
                return string;
            }
        }
        return null;
    }

    /* renamed from: a */
    private boolean m7828a(String str) {
        if (str == null) {
            return false;
        }
        if (str.endsWith("\n")) {
            str = str.substring(0, str.length() - 1);
        }
        if (24 != str.length() || this.f6765b.matcher(str).find()) {
            return false;
        }
        return true;
    }

    public synchronized String getValue() {
        String str;
        if (this.f6766g != null) {
            str = this.f6766g;
        } else {
            str = mo33748d();
        }
        return str;
    }

    /* renamed from: d */
    public synchronized String mo33748d() {
        String c;
        this.f6766g = mo33749e();
        if (TextUtils.isEmpty(this.f6766g)) {
            try {
                byte[] b = m7830b();
                if (b != null) {
                    this.f6766g = C4315b.encodeToString(b, 2);
                    m7833d(this.f6766g);
                    c = this.f6763a.mo33752c(b);
                    if (c != null) {
                        m7838h(c);
                        m7834e(c);
                    }
                    c = this.f6766g;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            c = null;
        } else {
            c = this.f6766g;
        }
        return c;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x0075 A:{Catch:{ Exception -> 0x0085 }} */
    /* renamed from: e */
    public synchronized java.lang.String m8669e() {
        /*
        r6 = this;
        r1 = 0;
        monitor-enter(r6);
        r0 = "";
        r2 = r6.mContext;	 Catch:{ Exception -> 0x00ec }
        r2 = r2.getContentResolver();	 Catch:{ Exception -> 0x00ec }
        r3 = "mqBRboGZkQPcAkyk";
        r0 = android.provider.Settings.System.getString(r2, r3);	 Catch:{ Exception -> 0x00ec }
    L_0x0010:
        r2 = r6.m7828a(r0);	 Catch:{ all -> 0x003e }
        if (r2 == 0) goto L_0x0018;
    L_0x0016:
        monitor-exit(r6);
        return r0;
    L_0x0018:
        r4 = new com.ta.utdid2.device.e;	 Catch:{ all -> 0x003e }
        r4.<init>();	 Catch:{ all -> 0x003e }
        r2 = 0;
        r0 = r6.mContext;	 Catch:{ Exception -> 0x0041 }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x0041 }
        r3 = "dxCRMxhQkdGePGnp";
        r3 = android.provider.Settings.System.getString(r0, r3);	 Catch:{ Exception -> 0x0041 }
    L_0x002a:
        r0 = com.p044ta.utdid2.p055a.p056a.C4321f.isEmpty(r3);	 Catch:{ all -> 0x003e }
        if (r0 != 0) goto L_0x0088;
    L_0x0030:
        r0 = r4.mo33755e(r3);	 Catch:{ all -> 0x003e }
        r5 = r6.m7828a(r0);	 Catch:{ all -> 0x003e }
        if (r5 == 0) goto L_0x0044;
    L_0x003a:
        r6.m7836f(r0);	 Catch:{ all -> 0x003e }
        goto L_0x0016;
    L_0x003e:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x0041:
        r0 = move-exception;
        r3 = r1;
        goto L_0x002a;
    L_0x0044:
        r0 = r4.mo33754d(r3);	 Catch:{ all -> 0x003e }
        r5 = r6.m7828a(r0);	 Catch:{ all -> 0x003e }
        if (r5 == 0) goto L_0x00ef;
    L_0x004e:
        r5 = r6.f6763a;	 Catch:{ all -> 0x003e }
        r0 = r5.mo33751c(r0);	 Catch:{ all -> 0x003e }
        r5 = com.p044ta.utdid2.p055a.p056a.C4321f.isEmpty(r0);	 Catch:{ all -> 0x003e }
        if (r5 != 0) goto L_0x00ef;
    L_0x005a:
        r6.m7838h(r0);	 Catch:{ all -> 0x003e }
        r0 = r6.mContext;	 Catch:{ Exception -> 0x0085 }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x0085 }
        r5 = "dxCRMxhQkdGePGnp";
        r0 = android.provider.Settings.System.getString(r0, r5);	 Catch:{ Exception -> 0x0085 }
    L_0x0069:
        r3 = r6.f6763a;	 Catch:{ all -> 0x003e }
        r3 = r3.mo33753d(r0);	 Catch:{ all -> 0x003e }
        r5 = r6.m7828a(r3);	 Catch:{ all -> 0x003e }
        if (r5 == 0) goto L_0x008a;
    L_0x0075:
        r6.f6766g = r3;	 Catch:{ all -> 0x003e }
        r6.m7833d(r3);	 Catch:{ all -> 0x003e }
        r6.m7834e(r0);	 Catch:{ all -> 0x003e }
        r0 = r6.f6766g;	 Catch:{ all -> 0x003e }
        r6.m7836f(r0);	 Catch:{ all -> 0x003e }
        r0 = r6.f6766g;	 Catch:{ all -> 0x003e }
        goto L_0x0016;
    L_0x0085:
        r0 = move-exception;
        r0 = r3;
        goto L_0x0069;
    L_0x0088:
        r0 = 1;
        r2 = r0;
    L_0x008a:
        r0 = r6.m7831c();	 Catch:{ all -> 0x003e }
        r3 = r6.m7828a(r0);	 Catch:{ all -> 0x003e }
        if (r3 == 0) goto L_0x00a9;
    L_0x0094:
        r1 = r6.f6763a;	 Catch:{ all -> 0x003e }
        r1 = r1.mo33751c(r0);	 Catch:{ all -> 0x003e }
        if (r2 == 0) goto L_0x009f;
    L_0x009c:
        r6.m7838h(r1);	 Catch:{ all -> 0x003e }
    L_0x009f:
        r6.m7836f(r0);	 Catch:{ all -> 0x003e }
        r6.m7834e(r1);	 Catch:{ all -> 0x003e }
        r6.f6766g = r0;	 Catch:{ all -> 0x003e }
        goto L_0x0016;
    L_0x00a9:
        r0 = r6.f6762a;	 Catch:{ all -> 0x003e }
        r3 = r6.f6767h;	 Catch:{ all -> 0x003e }
        r3 = r0.getString(r3);	 Catch:{ all -> 0x003e }
        r0 = com.p044ta.utdid2.p055a.p056a.C4321f.isEmpty(r3);	 Catch:{ all -> 0x003e }
        if (r0 != 0) goto L_0x00e9;
    L_0x00b7:
        r0 = r4.mo33754d(r3);	 Catch:{ all -> 0x003e }
        r4 = r6.m7828a(r0);	 Catch:{ all -> 0x003e }
        if (r4 != 0) goto L_0x00c7;
    L_0x00c1:
        r0 = r6.f6763a;	 Catch:{ all -> 0x003e }
        r0 = r0.mo33753d(r3);	 Catch:{ all -> 0x003e }
    L_0x00c7:
        r3 = r6.m7828a(r0);	 Catch:{ all -> 0x003e }
        if (r3 == 0) goto L_0x00e9;
    L_0x00cd:
        r3 = r6.f6763a;	 Catch:{ all -> 0x003e }
        r3 = r3.mo33751c(r0);	 Catch:{ all -> 0x003e }
        r4 = com.p044ta.utdid2.p055a.p056a.C4321f.isEmpty(r0);	 Catch:{ all -> 0x003e }
        if (r4 != 0) goto L_0x00e9;
    L_0x00d9:
        r6.f6766g = r0;	 Catch:{ all -> 0x003e }
        if (r2 == 0) goto L_0x00e0;
    L_0x00dd:
        r6.m7838h(r3);	 Catch:{ all -> 0x003e }
    L_0x00e0:
        r0 = r6.f6766g;	 Catch:{ all -> 0x003e }
        r6.m7833d(r0);	 Catch:{ all -> 0x003e }
        r0 = r6.f6766g;	 Catch:{ all -> 0x003e }
        goto L_0x0016;
    L_0x00e9:
        r0 = r1;
        goto L_0x0016;
    L_0x00ec:
        r2 = move-exception;
        goto L_0x0010;
    L_0x00ef:
        r0 = r3;
        goto L_0x0069;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p044ta.utdid2.device.C4334c.m8669e():java.lang.String");
    }

    /* renamed from: b */
    private byte[] m7830b() throws Exception {
        String imei;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nextInt = new Random().nextInt();
        byte[] bytes = C4316c.getBytes(currentTimeMillis);
        byte[] bytes2 = C4316c.getBytes(nextInt);
        byteArrayOutputStream.write(bytes, 0, 4);
        byteArrayOutputStream.write(bytes2, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        try {
            imei = C4317d.getImei(this.mContext);
        } catch (Exception e) {
            imei = "" + new Random().nextInt();
        }
        byteArrayOutputStream.write(C4316c.getBytes(C4321f.hashCode(imei)), 0, 4);
        byteArrayOutputStream.write(C4316c.getBytes(C4321f.hashCode(C4334c.m7829b(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: b */
    public static String m7829b(byte[] bArr) throws Exception {
        byte[] bArr2 = new byte[]{(byte) 69, (byte) 114, (byte) 116, (byte) -33, (byte) 125, (byte) -54, (byte) -31, (byte) 86, (byte) -11, (byte) 11, (byte) -78, (byte) -96, (byte) -17, (byte) -99, (byte) 64, (byte) 23, (byte) -95, (byte) -126, (byte) -82, (byte) -64, (byte) 113, (byte) 116, (byte) -16, (byte) -103, (byte) 49, (byte) -30, (byte) 9, (byte) -39, (byte) 33, (byte) -80, (byte) -68, (byte) -78, (byte) -117, (byte) 53, (byte) 30, (byte) -122, (byte) 64, (byte) -104, (byte) 74, (byte) -49, (byte) 106, (byte) 85, (byte) -38, (byte) -93};
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(new SecretKeySpec(C4320e.m7761a(bArr2), instance.getAlgorithm()));
        return C4315b.encodeToString(instance.doFinal(bArr), 2);
    }

    /* renamed from: e */
    private boolean mo33749e() {
        return this.mContext.checkPermission("android.permission.WRITE_SETTINGS", Binder.getCallingPid(), Binder.getCallingUid()) == 0;
    }
}
