package com.baidu.android.pushservice.p031c;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.baidu.android.pushservice.jni.BaiduAppSSOJni;
import com.baidu.android.pushservice.p033e.C1373g;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1465b;
import com.baidu.android.pushservice.util.C1550n;
import com.baidu.android.pushservice.util.C1574s;
import com.baidu.android.pushservice.util.C1575t;
import com.baidu.android.pushservice.util.C1578v;
import com.baidu.android.pushservice.util.C1579w;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: com.baidu.android.pushservice.c.b */
public final class C1332b {
    /* renamed from: b */
    private static volatile C1332b f4720b;
    /* renamed from: a */
    public ArrayList<C1339h> f4721a = new ArrayList();
    /* renamed from: c */
    private Context f4722c;
    /* renamed from: d */
    private HashMap<String, C1373g> f4723d = new HashMap();

    private C1332b(Context context) {
        int i;
        this.f4722c = context.getApplicationContext();
        String g = C1574s.m7031g(context);
        if (TextUtils.isEmpty(g) && C1578v.m7056E(this.f4722c)) {
            g = C1579w.m7157a(this.f4722c, this.f4722c.getPackageName() + ".push_sync", "r_v2");
        }
        if (TextUtils.isEmpty(g)) {
            C1425a.m6443d("ClientManager", "ClientManager init strAppsV2 empty.");
        } else {
            try {
                g = C1332b.m6021a(g);
                C1425a.m6441b("ClientManager", "ClientManager init strAppsV2 : " + g);
                ArrayList h = m6026h(g);
                if (h != null) {
                    Iterator it = h.iterator();
                    while (it.hasNext()) {
                        this.f4721a.add((C1339h) it.next());
                    }
                }
            } catch (Exception e) {
                C1425a.m6440a("ClientManager", e);
            }
        }
        int b = C1550n.m6960b(this.f4722c, "com.baidu.push.sync.vn", -1);
        try {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e2) {
            C1425a.m6442c("ClientManager", "Clientmanager not found " + context.getPackageName());
            i = 0;
        }
        if (b < i) {
            m6023a();
            C1550n.m6956a(this.f4722c, "com.baidu.push.sync.vn", i);
        }
    }

    /* renamed from: a */
    public static synchronized C1332b m6020a(Context context) {
        C1332b c1332b;
        synchronized (C1332b.class) {
            if (f4720b == null) {
                f4720b = new C1332b(context);
            }
            c1332b = f4720b;
        }
        return c1332b;
    }

    /* renamed from: a */
    public static String m6021a(String str) {
        String str2 = "";
        try {
            String str3;
            if (!TextUtils.isEmpty(str)) {
                byte[] a = C1465b.m6679a(str.getBytes());
                if (a != null && a.length > 0) {
                    str3 = new String(BaiduAppSSOJni.decryptAES(a, a.length, 0));
                    return str3;
                }
            }
            str3 = str2;
            return str3;
        } catch (Exception e) {
            C1425a.m6440a("ClientManager", e);
            return str2;
        } catch (UnsatisfiedLinkError e2) {
            C1425a.m6440a("ClientManager", e2);
            return str2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006d  */
    /* renamed from: a */
    private java.lang.String m6022a(java.util.List<com.baidu.android.pushservice.p031c.C1339h> r5) {
        /*
        r4 = this;
        if (r5 == 0) goto L_0x0008;
    L_0x0002:
        r0 = r5.size();
        if (r0 != 0) goto L_0x000b;
    L_0x0008:
        r0 = "";
    L_0x000a:
        return r0;
    L_0x000b:
        r2 = 0;
        r1 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x0070 }
        r1.<init>();	 Catch:{ Exception -> 0x0070 }
        r0 = 0;
        r2 = r0;
    L_0x0013:
        r0 = r5.size();	 Catch:{ Exception -> 0x0060 }
        if (r2 >= r0) goto L_0x0066;
    L_0x0019:
        r0 = r5.get(r2);	 Catch:{ Exception -> 0x0060 }
        r0 = (com.baidu.android.pushservice.p031c.C1339h) r0;	 Catch:{ Exception -> 0x0060 }
        if (r0 == 0) goto L_0x0056;
    L_0x0021:
        r3 = r0.f4718c;	 Catch:{ Exception -> 0x0060 }
        r1.append(r3);	 Catch:{ Exception -> 0x0060 }
        r3 = ",";
        r1.append(r3);	 Catch:{ Exception -> 0x0060 }
        r3 = r0.f4716a;	 Catch:{ Exception -> 0x0060 }
        r1.append(r3);	 Catch:{ Exception -> 0x0060 }
        r3 = ",";
        r1.append(r3);	 Catch:{ Exception -> 0x0060 }
        r3 = r0.f4739e;	 Catch:{ Exception -> 0x0060 }
        r1.append(r3);	 Catch:{ Exception -> 0x0060 }
        r3 = ",";
        r1.append(r3);	 Catch:{ Exception -> 0x0060 }
        r0 = r0.f4740f;	 Catch:{ Exception -> 0x0060 }
        if (r0 == 0) goto L_0x005a;
    L_0x0043:
        r0 = "true";
        r1.append(r0);	 Catch:{ Exception -> 0x0060 }
    L_0x0049:
        r0 = r5.size();	 Catch:{ Exception -> 0x0060 }
        r0 = r0 + -1;
        if (r2 == r0) goto L_0x0056;
    L_0x0051:
        r0 = ";";
        r1.append(r0);	 Catch:{ Exception -> 0x0060 }
    L_0x0056:
        r0 = r2 + 1;
        r2 = r0;
        goto L_0x0013;
    L_0x005a:
        r0 = "false";
        r1.append(r0);	 Catch:{ Exception -> 0x0060 }
        goto L_0x0049;
    L_0x0060:
        r0 = move-exception;
    L_0x0061:
        r2 = "ClientManager";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r2, r0);
    L_0x0066:
        if (r1 == 0) goto L_0x006d;
    L_0x0068:
        r0 = r1.toString();
        goto L_0x000a;
    L_0x006d:
        r0 = "";
        goto L_0x000a;
    L_0x0070:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0061;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p031c.C1332b.m6022a(java.util.List):java.lang.String");
    }

    /* renamed from: a */
    private void m6023a() {
        m6024a("r_v2", this.f4721a);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x00ac A:{Catch:{ Exception -> 0x00f1 }} */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0026 A:{SYNTHETIC} */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0116 A:{Catch:{ Exception -> 0x00f1 }} */
    /* renamed from: a */
    private void m6024a(java.lang.String r13, java.util.ArrayList<com.baidu.android.pushservice.p031c.C1339h> r14) {
        /*
        r12 = this;
        r5 = 0;
        r2 = 1;
        r3 = 0;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r12.f4722c;
        r1 = r1.getPackageName();
        r0 = r0.append(r1);
        r1 = ".push_sync";
        r0 = r0.append(r1);
        r6 = r0.toString();
        r0 = r12.f4722c;
        r0 = com.baidu.android.pushservice.util.C1578v.m7135o(r0);
        r7 = r0.iterator();
    L_0x0026:
        r0 = r7.hasNext();
        if (r0 == 0) goto L_0x0140;
    L_0x002c:
        r0 = r7.next();
        r0 = (android.content.pm.ResolveInfo) r0;
        r1 = r0.activityInfo;	 Catch:{ Exception -> 0x00f1 }
        r4 = r1.packageName;	 Catch:{ Exception -> 0x00f1 }
        r1 = r12.f4722c;	 Catch:{ Exception -> 0x00f1 }
        r1 = com.baidu.android.pushservice.util.C1578v.m7056E(r1);	 Catch:{ Exception -> 0x00f1 }
        if (r1 == 0) goto L_0x0169;
    L_0x003e:
        r1 = r12.f4722c;	 Catch:{ Exception -> 0x00f1 }
        r8 = r0.activityInfo;	 Catch:{ Exception -> 0x00f1 }
        r8 = r8.packageName;	 Catch:{ Exception -> 0x00f1 }
        r8 = com.baidu.android.pushservice.util.C1578v.m7143s(r1, r8);	 Catch:{ Exception -> 0x00f1 }
        if (r8 == 0) goto L_0x0169;
    L_0x004a:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f1 }
        r1.<init>();	 Catch:{ Exception -> 0x00f1 }
        r9 = r0.activityInfo;	 Catch:{ Exception -> 0x00f1 }
        r9 = r9.packageName;	 Catch:{ Exception -> 0x00f1 }
        r1 = r1.append(r9);	 Catch:{ Exception -> 0x00f1 }
        r9 = ".push_sync";
        r1 = r1.append(r9);	 Catch:{ Exception -> 0x00f1 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00f1 }
        r1 = com.baidu.android.pushservice.util.C1579w.m7157a(r8, r1, r13);	 Catch:{ Exception -> 0x00f1 }
        r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f1 }
        r9.<init>();	 Catch:{ Exception -> 0x00f1 }
        r0 = r0.activityInfo;	 Catch:{ Exception -> 0x00f1 }
        r0 = r0.packageName;	 Catch:{ Exception -> 0x00f1 }
        r0 = r9.append(r0);	 Catch:{ Exception -> 0x00f1 }
        r9 = ".self_push_sync";
        r0 = r0.append(r9);	 Catch:{ Exception -> 0x00f1 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00f1 }
        r9 = "bindinfo";
        r0 = com.baidu.android.pushservice.util.C1579w.m7157a(r8, r0, r9);	 Catch:{ Exception -> 0x00f1 }
    L_0x0082:
        r8 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x00f1 }
        if (r8 != 0) goto L_0x008e;
    L_0x0088:
        r8 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x00f1 }
        if (r8 == 0) goto L_0x0165;
    L_0x008e:
        r8 = r12.f4722c;	 Catch:{ Exception -> 0x00f1 }
        r8 = com.baidu.android.pushservice.util.C1578v.m7127k(r8, r4);	 Catch:{ Exception -> 0x00f1 }
        r9 = 50;
        if (r8 <= r9) goto L_0x0165;
    L_0x0098:
        r0 = r12.f4722c;	 Catch:{ Exception -> 0x00f1 }
        r1 = com.baidu.android.pushservice.util.C1575t.m7041h(r0, r4);	 Catch:{ Exception -> 0x00f1 }
        r0 = r12.f4722c;	 Catch:{ Exception -> 0x00f1 }
        r0 = com.baidu.android.pushservice.util.C1575t.m7042i(r0, r4);	 Catch:{ Exception -> 0x00f1 }
        r4 = r0;
        r0 = r1;
    L_0x00a6:
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x00f1 }
        if (r1 != 0) goto L_0x0110;
    L_0x00ac:
        r0 = com.baidu.android.pushservice.p031c.C1332b.m6021a(r0);	 Catch:{ Exception -> 0x00f1 }
        r0 = r12.m6026h(r0);	 Catch:{ Exception -> 0x00f1 }
        if (r0 == 0) goto L_0x0110;
    L_0x00b6:
        r8 = r0.iterator();	 Catch:{ Exception -> 0x00f1 }
    L_0x00ba:
        r0 = r8.hasNext();	 Catch:{ Exception -> 0x00f1 }
        if (r0 == 0) goto L_0x0110;
    L_0x00c0:
        r0 = r8.next();	 Catch:{ Exception -> 0x00f1 }
        r0 = (com.baidu.android.pushservice.p031c.C1339h) r0;	 Catch:{ Exception -> 0x00f1 }
        r9 = r14.iterator();	 Catch:{ Exception -> 0x00f1 }
    L_0x00ca:
        r1 = r9.hasNext();	 Catch:{ Exception -> 0x00f1 }
        if (r1 == 0) goto L_0x0163;
    L_0x00d0:
        r1 = r9.next();	 Catch:{ Exception -> 0x00f1 }
        r1 = (com.baidu.android.pushservice.p031c.C1339h) r1;	 Catch:{ Exception -> 0x00f1 }
        r10 = r0.f4718c;	 Catch:{ Exception -> 0x00f1 }
        r11 = r1.f4718c;	 Catch:{ Exception -> 0x00f1 }
        r10 = android.text.TextUtils.equals(r10, r11);	 Catch:{ Exception -> 0x00f1 }
        if (r10 != 0) goto L_0x00ea;
    L_0x00e0:
        r10 = r0.f4716a;	 Catch:{ Exception -> 0x00f1 }
        r1 = r1.f4716a;	 Catch:{ Exception -> 0x00f1 }
        r1 = android.text.TextUtils.equals(r10, r1);	 Catch:{ Exception -> 0x00f1 }
        if (r1 == 0) goto L_0x00ca;
    L_0x00ea:
        r1 = r2;
    L_0x00eb:
        if (r1 != 0) goto L_0x00ba;
    L_0x00ed:
        r14.add(r0);	 Catch:{ Exception -> 0x00f1 }
        goto L_0x00ba;
    L_0x00f1:
        r0 = move-exception;
        r1 = "ClientManager";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r8 = "syncRegisterDataImpl Exception: ";
        r4 = r4.append(r8);
        r0 = r0.getMessage();
        r0 = r4.append(r0);
        r0 = r0.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r1, r0);
        goto L_0x0026;
    L_0x0110:
        r0 = android.text.TextUtils.isEmpty(r4);	 Catch:{ Exception -> 0x00f1 }
        if (r0 != 0) goto L_0x0026;
    L_0x0116:
        r0 = com.baidu.android.pushservice.p031c.C1332b.m6021a(r4);	 Catch:{ Exception -> 0x00f1 }
        r1 = r12.m6025g(r0);	 Catch:{ Exception -> 0x00f1 }
        r4 = r14.iterator();	 Catch:{ Exception -> 0x00f1 }
    L_0x0122:
        r0 = r4.hasNext();	 Catch:{ Exception -> 0x00f1 }
        if (r0 == 0) goto L_0x0161;
    L_0x0128:
        r0 = r4.next();	 Catch:{ Exception -> 0x00f1 }
        r0 = (com.baidu.android.pushservice.p031c.C1339h) r0;	 Catch:{ Exception -> 0x00f1 }
        r8 = r1.f4718c;	 Catch:{ Exception -> 0x00f1 }
        r0 = r0.f4718c;	 Catch:{ Exception -> 0x00f1 }
        r0 = android.text.TextUtils.equals(r8, r0);	 Catch:{ Exception -> 0x00f1 }
        if (r0 == 0) goto L_0x0122;
    L_0x0138:
        r0 = r2;
    L_0x0139:
        if (r0 != 0) goto L_0x0026;
    L_0x013b:
        r14.add(r1);	 Catch:{ Exception -> 0x00f1 }
        goto L_0x0026;
    L_0x0140:
        r0 = r14.size();
        if (r0 <= 0) goto L_0x0160;
    L_0x0146:
        r0 = r12.m6022a(r14);
        r0 = r12.mo13596b(r0);
        r1 = r12.f4722c;
        com.baidu.android.pushservice.util.C1574s.m7030f(r1, r0);
        r1 = r12.f4722c;
        r1 = com.baidu.android.pushservice.util.C1578v.m7056E(r1);
        if (r1 == 0) goto L_0x0160;
    L_0x015b:
        r1 = r12.f4722c;
        com.baidu.android.pushservice.util.C1579w.m7158a(r1, r6, r13, r0);
    L_0x0160:
        return;
    L_0x0161:
        r0 = r3;
        goto L_0x0139;
    L_0x0163:
        r1 = r3;
        goto L_0x00eb;
    L_0x0165:
        r4 = r0;
        r0 = r1;
        goto L_0x00a6;
    L_0x0169:
        r0 = r5;
        r1 = r5;
        goto L_0x0082;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p031c.C1332b.m6024a(java.lang.String, java.util.ArrayList):void");
    }

    /* renamed from: g */
    private C1339h m6025g(String str) {
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.trim().split(",");
            if (!(split == null || split.length < 3 || TextUtils.isEmpty(split[0]))) {
                C1339h c1339h = new C1339h();
                c1339h.f4718c = split[0].trim();
                c1339h.f4716a = split[1].trim();
                c1339h.f4739e = split[2].trim();
                if (split.length <= 3) {
                    return c1339h;
                }
                c1339h.f4740f = split[3].trim().equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                return c1339h;
            }
        }
        return null;
    }

    /* renamed from: h */
    private ArrayList<C1339h> m6026h(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String trim : str.trim().split(";")) {
            String[] split = trim.trim().split(",");
            if (split.length >= 3) {
                C1339h c1339h = new C1339h();
                c1339h.f4718c = split[0].trim();
                c1339h.f4716a = split[1].trim();
                c1339h.f4739e = split[2].trim();
                if (split.length > 3) {
                    c1339h.f4740f = split[3].trim().equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                }
                arrayList.add(c1339h);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public C1339h mo13591a(int i, boolean z) {
        Iterator it = this.f4721a.iterator();
        while (it.hasNext()) {
            C1339h c1339h = (C1339h) it.next();
            if (c1339h.f4719d >= i && ((!z || c1339h.f4740f) && C1578v.m7106c(this.f4722c, c1339h.mo13589c()))) {
                return c1339h;
            }
        }
        return null;
    }

    /* renamed from: a */
    public String mo13592a(C1339h c1339h, boolean z) {
        C1425a.m6442c("ClientManager", "syncV2 addOrRemove:" + z + ", " + c1339h);
        C1425a.m6442c("ClientManager", "client.packageName=" + c1339h.f4718c + " client.appId=" + c1339h.f4716a + " client.userId=" + c1339h.f4739e);
        return mo13593a(c1339h, z, this.f4721a, "r_v2");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0063  */
    /* renamed from: a */
    public java.lang.String mo13593a(com.baidu.android.pushservice.p031c.C1339h r6, boolean r7, java.util.ArrayList<com.baidu.android.pushservice.p031c.C1339h> r8, java.lang.String r9) {
        /*
        r5 = this;
        monitor-enter(r8);
        r1 = 0;
        r2 = r8.iterator();	 Catch:{ all -> 0x0083 }
    L_0x0006:
        r0 = r2.hasNext();	 Catch:{ all -> 0x0083 }
        if (r0 == 0) goto L_0x0086;
    L_0x000c:
        r0 = r2.next();	 Catch:{ all -> 0x0083 }
        r0 = (com.baidu.android.pushservice.p031c.C1339h) r0;	 Catch:{ all -> 0x0083 }
        r3 = r0.f4718c;	 Catch:{ all -> 0x0083 }
        r4 = r6.f4718c;	 Catch:{ all -> 0x0083 }
        r3 = r3.equals(r4);	 Catch:{ all -> 0x0083 }
        if (r3 != 0) goto L_0x0026;
    L_0x001c:
        r3 = r0.f4716a;	 Catch:{ all -> 0x0083 }
        r4 = r6.f4716a;	 Catch:{ all -> 0x0083 }
        r3 = r3.equals(r4);	 Catch:{ all -> 0x0083 }
        if (r3 == 0) goto L_0x0006;
    L_0x0026:
        r8.remove(r0);	 Catch:{ all -> 0x0083 }
        if (r7 == 0) goto L_0x002e;
    L_0x002b:
        r8.add(r6);	 Catch:{ all -> 0x0083 }
    L_0x002e:
        r0 = 1;
    L_0x002f:
        if (r0 != 0) goto L_0x0036;
    L_0x0031:
        if (r7 == 0) goto L_0x0036;
    L_0x0033:
        r8.add(r6);	 Catch:{ all -> 0x0083 }
    L_0x0036:
        r0 = r5.m6022a(r8);	 Catch:{ all -> 0x0083 }
        r1 = "ClientManager";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0083 }
        r2.<init>();	 Catch:{ all -> 0x0083 }
        r3 = "sync  strApps: ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0083 }
        r2 = r2.append(r0);	 Catch:{ all -> 0x0083 }
        r2 = r2.toString();	 Catch:{ all -> 0x0083 }
        com.baidu.android.pushservice.p036h.C1425a.m6441b(r1, r2);	 Catch:{ all -> 0x0083 }
        r0 = r5.mo13596b(r0);	 Catch:{ all -> 0x0083 }
        r1 = r5.f4722c;	 Catch:{ all -> 0x0083 }
        com.baidu.android.pushservice.util.C1574s.m7030f(r1, r0);	 Catch:{ all -> 0x0083 }
        r1 = r5.f4722c;	 Catch:{ all -> 0x0083 }
        r1 = com.baidu.android.pushservice.util.C1578v.m7056E(r1);	 Catch:{ all -> 0x0083 }
        if (r1 == 0) goto L_0x0081;
    L_0x0063:
        r1 = r5.f4722c;	 Catch:{ all -> 0x0083 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0083 }
        r2.<init>();	 Catch:{ all -> 0x0083 }
        r3 = r5.f4722c;	 Catch:{ all -> 0x0083 }
        r3 = r3.getPackageName();	 Catch:{ all -> 0x0083 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0083 }
        r3 = ".push_sync";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0083 }
        r2 = r2.toString();	 Catch:{ all -> 0x0083 }
        com.baidu.android.pushservice.util.C1579w.m7158a(r1, r2, r9, r0);	 Catch:{ all -> 0x0083 }
    L_0x0081:
        monitor-exit(r8);	 Catch:{ all -> 0x0083 }
        return r0;
    L_0x0083:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x0083 }
        throw r0;
    L_0x0086:
        r0 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p031c.C1332b.mo13593a(com.baidu.android.pushservice.c.h, boolean, java.util.ArrayList, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public void mo13594a(String str, C1373g c1373g) {
        this.f4723d.put(str, c1373g);
    }

    /* renamed from: a */
    public synchronized void mo13595a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            C1425a.m6442c("ClientManager", "ClientManager init strApps empty.");
        } else {
            ArrayList h = m6026h(C1332b.m6021a(str2));
            String str3 = "";
            if (!(f4720b == null || h == null)) {
                try {
                    if (str.equals("r_v2")) {
                        ArrayList arrayList = new ArrayList();
                        Iterator it = h.iterator();
                        while (it.hasNext()) {
                            arrayList.add(((C1339h) it.next()).f4718c);
                        }
                        int i = 0;
                        while (i < f4720b.f4721a.size()) {
                            int i2;
                            if (arrayList.contains(((C1339h) f4720b.f4721a.get(i)).f4718c)) {
                                f4720b.f4721a.remove(i);
                                i2 = i - 1;
                            } else {
                                i2 = i;
                            }
                            i = i2 + 1;
                        }
                        f4720b.f4721a.addAll(h);
                        str3 = m6022a(f4720b.f4721a);
                    }
                    str3 = mo13596b(str3);
                    C1574s.m7030f(this.f4722c, str3);
                    if (C1578v.m7056E(this.f4722c)) {
                        C1579w.m7158a(this.f4722c, this.f4722c.getPackageName() + ".push_sync", str, str3);
                    }
                } catch (Exception e) {
                    C1425a.m6440a("ClientManager", e);
                }
            }
        }
        return;
    }

    /* renamed from: b */
    public String mo13596b(String str) {
        String str2 = "";
        try {
            return C1465b.m6678a(BaiduAppSSOJni.encryptAES(str, 0), "utf-8");
        } catch (Exception e) {
            C1425a.m6444e("ClientManager", "encrypted init strApps exception.");
            return str2;
        } catch (UnsatisfiedLinkError e2) {
            C1425a.m6440a("ClientManager", e2);
            return str2;
        }
    }

    /* renamed from: b */
    public synchronized void mo13597b(Context context) {
        String g = C1574s.m7031g(context);
        if (TextUtils.isEmpty(g) && C1578v.m7056E(this.f4722c)) {
            g = C1579w.m7157a(this.f4722c, this.f4722c.getPackageName() + ".push_sync", "r_v2");
        }
        ArrayList h = !TextUtils.isEmpty(g) ? m6026h(C1332b.m6021a(g)) : null;
        if (!(f4720b == null || h == null)) {
            ArrayList arrayList = new ArrayList();
            Iterator it = h.iterator();
            while (it.hasNext()) {
                arrayList.add(((C1339h) it.next()).f4718c);
            }
            int i = 0;
            while (i < f4720b.f4721a.size()) {
                int i2;
                if (arrayList.contains(((C1339h) f4720b.f4721a.get(i)).f4718c)) {
                    f4720b.f4721a.remove(i);
                    i2 = i - 1;
                } else {
                    i2 = i;
                }
                i = i2 + 1;
            }
            f4720b.f4721a.addAll(h);
        }
    }

    /* renamed from: b */
    public boolean mo13598b(String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) ? false : this.f4723d.containsKey(str) && str2.equals(((C1373g) this.f4723d.get(str)).mo13730a());
    }

    /* renamed from: c */
    public C1339h mo13599c(String str) {
        Iterator it = this.f4721a.iterator();
        while (it.hasNext()) {
            C1339h c1339h = (C1339h) it.next();
            if (c1339h.f4718c.equals(str)) {
                return c1339h;
            }
        }
        return null;
    }

    /* renamed from: d */
    public C1339h mo13600d(String str) {
        if (!TextUtils.isEmpty(str)) {
            C1339h c1339h;
            Iterator it = this.f4721a.iterator();
            while (it.hasNext()) {
                c1339h = (C1339h) it.next();
                if (!TextUtils.isEmpty(c1339h.f4716a) && c1339h.f4716a.equals(str)) {
                    return c1339h;
                }
            }
            try {
                CharSequence charSequence = null;
                for (ResolveInfo resolveInfo : C1578v.m7135o(this.f4722c)) {
                    if (C1578v.m7056E(this.f4722c)) {
                        Context s = C1578v.m7143s(this.f4722c, resolveInfo.activityInfo.packageName);
                        if (s != null) {
                            charSequence = C1579w.m7157a(s, resolveInfo.activityInfo.packageName + ".push_sync", "r_v2");
                        }
                    }
                    if (TextUtils.isEmpty(charSequence)) {
                        charSequence = C1575t.m7041h(this.f4722c, resolveInfo.activityInfo.packageName);
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        String a = C1332b.m6021a(charSequence);
                        if (a.contains(str)) {
                            ArrayList h = m6026h(a);
                            if (!(h == null || h.isEmpty())) {
                                Iterator it2 = h.iterator();
                                while (it2.hasNext()) {
                                    c1339h = (C1339h) it2.next();
                                    if (!TextUtils.isEmpty(c1339h.f4716a) && c1339h.f4716a.equals(str)) {
                                        this.f4721a.add(c1339h);
                                        return c1339h;
                                    }
                                }
                                continue;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            } catch (Exception e) {
                C1425a.m6440a("ClientManager", e);
            }
        }
        return null;
    }

    /* renamed from: e */
    public String mo13601e(String str) {
        return this.f4723d.get(str) != null ? ((C1373g) this.f4723d.get(str)).mo13731b() : "";
    }

    /* renamed from: f */
    public void mo13602f(String str) {
        if (this.f4723d.containsKey(str)) {
            this.f4723d.remove(str);
        }
    }
}
