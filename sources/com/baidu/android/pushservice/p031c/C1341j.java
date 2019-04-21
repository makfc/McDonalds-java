package com.baidu.android.pushservice.p031c;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.pushservice.jni.BaiduAppSSOJni;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1465b;
import com.baidu.android.pushservice.util.C1535c;
import com.baidu.android.pushservice.util.C1578v;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.baidu.android.pushservice.c.j */
public class C1341j extends C1336e {
    /* renamed from: d */
    private static volatile C1341j f4741d;
    /* renamed from: e */
    private static String f4742e = "SDKClientManager";

    private C1341j(Context context) {
        super(context, C1333c.SDK_CLIENT);
    }

    /* renamed from: a */
    private C1340i m6053a(String str, ArrayList<C1331a> arrayList) {
        C1340i c1340i = null;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C1331a c1331a = (C1331a) it.next();
            if (!TextUtils.isEmpty(c1331a.mo13584a()) && c1331a.mo13584a().equals(str) && C1578v.m7106c(this.f4736b, c1331a.mo13589c())) {
                if (c1340i == null || c1340i.mo13590d() < c1331a.mo13590d()) {
                    C1340i c1340i2 = (C1340i) c1331a;
                    this.f4735a.add(c1340i2);
                    return c1340i2;
                }
            }
        }
        return c1340i;
    }

    /* renamed from: a */
    public static synchronized C1341j m6054a(Context context) {
        C1341j c1341j;
        synchronized (C1341j.class) {
            if (f4741d == null) {
                f4741d = new C1341j(context);
            } else {
                f4741d.f4736b = context.getApplicationContext();
            }
            c1341j = f4741d;
        }
        return c1341j;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public java.lang.String mo13616a(com.baidu.android.pushservice.p031c.C1340i r7) {
        /*
        r6 = this;
        r1 = 0;
        r2 = r6.f4735a;
        monitor-enter(r2);
        r0 = r7.mo13584a();	 Catch:{ all -> 0x007f }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x007f }
        if (r0 != 0) goto L_0x0082;
    L_0x000e:
        r0 = r6.f4735a;	 Catch:{ all -> 0x007f }
        r3 = r0.iterator();	 Catch:{ all -> 0x007f }
    L_0x0014:
        r0 = r3.hasNext();	 Catch:{ all -> 0x007f }
        if (r0 == 0) goto L_0x0082;
    L_0x001a:
        r0 = r3.next();	 Catch:{ all -> 0x007f }
        r0 = (com.baidu.android.pushservice.p031c.C1331a) r0;	 Catch:{ all -> 0x007f }
        r4 = r0.mo13587b();	 Catch:{ all -> 0x007f }
        r5 = r7.mo13587b();	 Catch:{ all -> 0x007f }
        r4 = r4.equals(r5);	 Catch:{ all -> 0x007f }
        if (r4 == 0) goto L_0x0014;
    L_0x002e:
        r1 = r7.f4718c;	 Catch:{ all -> 0x007f }
        r0.mo13588b(r1);	 Catch:{ all -> 0x007f }
        r1 = r7.mo13584a();	 Catch:{ all -> 0x007f }
        r0.mo13586a(r1);	 Catch:{ all -> 0x007f }
        r0 = 1;
    L_0x003b:
        if (r0 == 0) goto L_0x0075;
    L_0x003d:
        r0 = r6.f4735a;	 Catch:{ all -> 0x007f }
        r0 = r6.mo13605a(r0);	 Catch:{ all -> 0x007f }
        r1 = 0;
        r0 = com.baidu.android.pushservice.jni.BaiduAppSSOJni.encryptAES(r0, r1);	 Catch:{ Exception -> 0x0058, UnsatisfiedLinkError -> 0x0078 }
        r1 = "utf-8";
        r0 = com.baidu.android.pushservice.p039k.C1465b.m6678a(r0, r1);	 Catch:{ Exception -> 0x0058, UnsatisfiedLinkError -> 0x0078 }
        r1 = r6.f4736b;	 Catch:{ Exception -> 0x0058, UnsatisfiedLinkError -> 0x0078 }
        r3 = "com.baidu.push.sdkr";
        com.baidu.android.pushservice.util.C1535c.m6903a(r1, r3, r0);	 Catch:{ Exception -> 0x0058, UnsatisfiedLinkError -> 0x0078 }
        monitor-exit(r2);	 Catch:{ all -> 0x007f }
    L_0x0057:
        return r0;
    L_0x0058:
        r0 = move-exception;
        r1 = f4742e;	 Catch:{ all -> 0x007f }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x007f }
        r3.<init>();	 Catch:{ all -> 0x007f }
        r4 = "error : ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x007f }
        r0 = r0.getMessage();	 Catch:{ all -> 0x007f }
        r0 = r3.append(r0);	 Catch:{ all -> 0x007f }
        r0 = r0.toString();	 Catch:{ all -> 0x007f }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r1, r0);	 Catch:{ all -> 0x007f }
    L_0x0075:
        monitor-exit(r2);	 Catch:{ all -> 0x007f }
        r0 = 0;
        goto L_0x0057;
    L_0x0078:
        r0 = move-exception;
        r1 = f4742e;	 Catch:{ all -> 0x007f }
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r1, r0);	 Catch:{ all -> 0x007f }
        goto L_0x0075;
    L_0x007f:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x007f }
        throw r0;
    L_0x0082:
        r0 = r1;
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p031c.C1341j.mo13616a(com.baidu.android.pushservice.c.i):java.lang.String");
    }

    /* renamed from: a */
    public String mo13605a(List<C1331a> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                return stringBuffer.toString();
            }
            C1331a c1331a = (C1331a) list.get(i2);
            stringBuffer.append(c1331a.mo13587b());
            stringBuffer.append(",");
            stringBuffer.append(c1331a.mo13589c());
            stringBuffer.append(",");
            stringBuffer.append(c1331a.mo13590d());
            stringBuffer.append(",");
            stringBuffer.append(c1331a.mo13584a());
            if (i2 != list.size() - 1) {
                stringBuffer.append(";");
            }
            i = i2 + 1;
        }
    }

    /* renamed from: a */
    public ArrayList<C1331a> mo13606a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        for (String trim : str.trim().split(";")) {
            String[] split = trim.trim().trim().split(",");
            if (split.length >= 2) {
                C1340i c1340i = new C1340i(split[0], split[1]);
                try {
                    if (split.length == 3) {
                        c1340i.mo13585a(Integer.parseInt(split[2]));
                    } else if (split.length == 4) {
                        c1340i.mo13585a(Integer.parseInt(split[2]));
                        c1340i.mo13586a(split[3]);
                    }
                } catch (Exception e) {
                    C1425a.m6442c(f4742e, "str2Clients E: " + e);
                }
                arrayList.add(c1340i);
            }
        }
        return arrayList;
    }

    /* renamed from: f */
    public C1340i mo13609b(String str) {
        C1340i a = m6053a(str, this.f4735a);
        if (a != null) {
            return a;
        }
        try {
            String a2 = C1535c.m6900a(this.f4736b, "com.baidu.push.sdkr");
            if (TextUtils.isEmpty(a2)) {
                C1425a.m6441b(f4742e, "ClientManager init strApps empty.");
                return a;
            }
            byte[] a3 = C1465b.m6679a(a2.getBytes());
            return m6053a(str, mo13606a(new String(BaiduAppSSOJni.decryptAES(a3, a3.length, 0))));
        } catch (Exception e) {
            C1425a.m6444e(f4742e, "error : " + e.getMessage());
            return a;
        } catch (UnsatisfiedLinkError e2) {
            C1425a.m6440a(f4742e, e2);
            return a;
        }
    }
}
