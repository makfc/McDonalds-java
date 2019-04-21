package com.baidu.android.pushservice.p031c;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.baidu.android.pushservice.jni.BaiduAppSSOJni;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1465b;
import com.baidu.android.pushservice.util.C1535c;
import com.baidu.android.pushservice.util.C1574s;
import com.baidu.android.pushservice.util.C1575t;
import com.baidu.android.pushservice.util.C1578v;
import com.baidu.android.pushservice.util.C1579w;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.baidu.android.pushservice.c.e */
public abstract class C1336e {
    /* renamed from: a */
    protected ArrayList<C1331a> f4735a = new ArrayList();
    /* renamed from: b */
    protected Context f4736b;
    /* renamed from: c */
    protected C1333c f4737c;

    public C1336e(Context context, C1333c c1333c) {
        this.f4736b = context.getApplicationContext();
        this.f4737c = c1333c;
        mo13607a();
    }

    /* renamed from: a */
    private void m6041a(String str, ArrayList<C1331a> arrayList) {
        String str2 = this.f4736b.getPackageName() + ".push_sync";
        for (ResolveInfo resolveInfo : C1578v.m7135o(this.f4736b)) {
            try {
                CharSequence e = "com.baidu.push.webr".equals(str) ? C1575t.m7038e(this.f4736b, resolveInfo.activityInfo.packageName) : "com.baidu.push.lappr".equals(str) ? C1575t.m7039f(this.f4736b, resolveInfo.activityInfo.packageName) : "com.baidu.push.sdkr".equals(str) ? C1575t.m7040g(this.f4736b, resolveInfo.activityInfo.packageName) : null;
                if (!TextUtils.isEmpty(e)) {
                    ArrayList a = mo13606a(C1336e.m6042d(e));
                    if (a != null) {
                        Iterator it = a.iterator();
                        while (it.hasNext()) {
                            Object obj;
                            C1331a c1331a = (C1331a) it.next();
                            Iterator it2 = arrayList.iterator();
                            while (it2.hasNext()) {
                                C1331a c1331a2 = (C1331a) it2.next();
                                if (!TextUtils.equals(c1331a.f4718c, c1331a2.f4718c)) {
                                    if (TextUtils.equals(c1331a.f4716a, c1331a2.f4716a)) {
                                    }
                                }
                                obj = 1;
                            }
                            obj = null;
                            if (obj == null) {
                                arrayList.add(c1331a);
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                C1425a.m6444e("IClientManager", "syncRegisterDataImpl Exception: " + e2.getMessage());
            }
        }
        if (arrayList.size() > 0) {
            String e3 = mo13611e(mo13605a((List) arrayList));
            if ("com.baidu.push.webr".equals(str)) {
                C1574s.m7024c(this.f4736b, e3);
            } else if ("com.baidu.push.lappr".equals(str)) {
                C1574s.m7026d(this.f4736b, e3);
            } else if ("com.baidu.push.sdkr".equals(str)) {
                C1574s.m7028e(this.f4736b, e3);
            }
            if (C1578v.m7056E(this.f4736b)) {
                C1579w.m7158a(this.f4736b, str2, str, e3);
            }
        }
    }

    /* renamed from: d */
    public static String m6042d(String str) {
        String str2 = "";
        try {
            byte[] a = C1465b.m6679a(str.getBytes());
            String str3 = (a == null || a.length <= 0) ? str2 : new String(BaiduAppSSOJni.decryptAES(a, a.length, 0));
            return str3;
        } catch (Exception e) {
            C1425a.m6440a("IClientManager", e);
            return str2;
        } catch (UnsatisfiedLinkError e2) {
            C1425a.m6440a("IClientManager", e2);
            return str2;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    public java.lang.String mo13604a(com.baidu.android.pushservice.p031c.C1331a r8, boolean r9) {
        /*
        r7 = this;
        r2 = 0;
        r1 = 1;
        r0 = "IClientManager";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "client sync addOrRemove:";
        r3 = r3.append(r4);
        r3 = r3.append(r9);
        r4 = ", ";
        r3 = r3.append(r4);
        r3 = r3.append(r8);
        r3 = r3.toString();
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r3);
        r3 = r7.f4735a;
        monitor-enter(r3);
        r0 = r8.mo13584a();	 Catch:{ all -> 0x017c }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x017c }
        if (r0 != 0) goto L_0x00c1;
    L_0x0031:
        r0 = r7.f4735a;	 Catch:{ all -> 0x017c }
        r4 = r0.iterator();	 Catch:{ all -> 0x017c }
    L_0x0037:
        r0 = r4.hasNext();	 Catch:{ all -> 0x017c }
        if (r0 == 0) goto L_0x0182;
    L_0x003d:
        r0 = r4.next();	 Catch:{ all -> 0x017c }
        r0 = (com.baidu.android.pushservice.p031c.C1331a) r0;	 Catch:{ all -> 0x017c }
        r5 = r8.mo13587b();	 Catch:{ all -> 0x017c }
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x017c }
        if (r5 != 0) goto L_0x005b;
    L_0x004d:
        r5 = r0.mo13587b();	 Catch:{ all -> 0x017c }
        r6 = r8.mo13587b();	 Catch:{ all -> 0x017c }
        r5 = android.text.TextUtils.equals(r5, r6);	 Catch:{ all -> 0x017c }
        if (r5 != 0) goto L_0x0069;
    L_0x005b:
        r5 = r8.mo13584a();	 Catch:{ all -> 0x017c }
        r6 = r0.mo13584a();	 Catch:{ all -> 0x017c }
        r5 = r5.equals(r6);	 Catch:{ all -> 0x017c }
        if (r5 == 0) goto L_0x0037;
    L_0x0069:
        r2 = r7.f4735a;	 Catch:{ all -> 0x017c }
        r2.remove(r0);	 Catch:{ all -> 0x017c }
        if (r9 == 0) goto L_0x0075;
    L_0x0070:
        r0 = r7.f4735a;	 Catch:{ all -> 0x017c }
        r0.add(r8);	 Catch:{ all -> 0x017c }
    L_0x0075:
        r0 = r1;
    L_0x0076:
        r1 = r0;
    L_0x0077:
        if (r1 != 0) goto L_0x0080;
    L_0x0079:
        if (r9 == 0) goto L_0x0080;
    L_0x007b:
        r0 = r7.f4735a;	 Catch:{ all -> 0x017c }
        r0.add(r8);	 Catch:{ all -> 0x017c }
    L_0x0080:
        r0 = r7.f4735a;	 Catch:{ all -> 0x017c }
        r2 = r7.mo13605a(r0);	 Catch:{ all -> 0x017c }
        r0 = "IClientManager";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x017c }
        r1.<init>();	 Catch:{ all -> 0x017c }
        r4 = "sync  strApps: ";
        r1 = r1.append(r4);	 Catch:{ all -> 0x017c }
        r1 = r1.append(r2);	 Catch:{ all -> 0x017c }
        r1 = r1.toString();	 Catch:{ all -> 0x017c }
        com.baidu.android.pushservice.p036h.C1425a.m6442c(r0, r1);	 Catch:{ all -> 0x017c }
        r0 = "";
        r1 = com.baidu.android.pushservice.p031c.C1336e.C13351.f4734a;	 Catch:{ all -> 0x017c }
        r4 = r7.f4737c;	 Catch:{ all -> 0x017c }
        r4 = r4.ordinal();	 Catch:{ all -> 0x017c }
        r1 = r1[r4];	 Catch:{ all -> 0x017c }
        switch(r1) {
            case 1: goto L_0x0141;
            case 2: goto L_0x0146;
            case 3: goto L_0x014b;
            case 4: goto L_0x0150;
            default: goto L_0x00ad;
        };
    L_0x00ad:
        r1 = r0;
    L_0x00ae:
        r0 = 0;
        r0 = com.baidu.android.pushservice.jni.BaiduAppSSOJni.encryptAES(r2, r0);	 Catch:{ Exception -> 0x0153, UnsatisfiedLinkError -> 0x0175 }
        r2 = "utf-8";
        r0 = com.baidu.android.pushservice.p039k.C1465b.m6678a(r0, r2);	 Catch:{ Exception -> 0x0153, UnsatisfiedLinkError -> 0x0175 }
        r2 = r7.f4736b;	 Catch:{ Exception -> 0x0153, UnsatisfiedLinkError -> 0x0175 }
        com.baidu.android.pushservice.util.C1535c.m6903a(r2, r1, r0);	 Catch:{ Exception -> 0x0153, UnsatisfiedLinkError -> 0x0175 }
        monitor-exit(r3);	 Catch:{ all -> 0x017c }
    L_0x00c0:
        return r0;
    L_0x00c1:
        r0 = r7.f4737c;	 Catch:{ all -> 0x017c }
        r4 = com.baidu.android.pushservice.p031c.C1333c.SDK_CLIENT;	 Catch:{ all -> 0x017c }
        if (r0 != r4) goto L_0x010b;
    L_0x00c7:
        r0 = r7.f4735a;	 Catch:{ all -> 0x017c }
        r4 = r0.iterator();	 Catch:{ all -> 0x017c }
    L_0x00cd:
        r0 = r4.hasNext();	 Catch:{ all -> 0x017c }
        if (r0 == 0) goto L_0x017f;
    L_0x00d3:
        r0 = r4.next();	 Catch:{ all -> 0x017c }
        r0 = (com.baidu.android.pushservice.p031c.C1331a) r0;	 Catch:{ all -> 0x017c }
        r5 = r8.mo13587b();	 Catch:{ all -> 0x017c }
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x017c }
        if (r5 != 0) goto L_0x00cd;
    L_0x00e3:
        r5 = r0.mo13587b();	 Catch:{ all -> 0x017c }
        r6 = r8.mo13587b();	 Catch:{ all -> 0x017c }
        r5 = android.text.TextUtils.equals(r5, r6);	 Catch:{ all -> 0x017c }
        if (r5 == 0) goto L_0x00cd;
    L_0x00f1:
        r5 = r8.mo13589c();	 Catch:{ all -> 0x017c }
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x017c }
        if (r5 != 0) goto L_0x00cd;
    L_0x00fb:
        r0 = r0.mo13589c();	 Catch:{ all -> 0x017c }
        r5 = r8.mo13589c();	 Catch:{ all -> 0x017c }
        r0 = android.text.TextUtils.equals(r0, r5);	 Catch:{ all -> 0x017c }
        if (r0 == 0) goto L_0x00cd;
    L_0x0109:
        goto L_0x0077;
    L_0x010b:
        r0 = r7.f4735a;	 Catch:{ all -> 0x017c }
        r4 = r0.iterator();	 Catch:{ all -> 0x017c }
    L_0x0111:
        r0 = r4.hasNext();	 Catch:{ all -> 0x017c }
        if (r0 == 0) goto L_0x017f;
    L_0x0117:
        r0 = r4.next();	 Catch:{ all -> 0x017c }
        r0 = (com.baidu.android.pushservice.p031c.C1331a) r0;	 Catch:{ all -> 0x017c }
        r5 = r8.mo13587b();	 Catch:{ all -> 0x017c }
        r5 = android.text.TextUtils.isEmpty(r5);	 Catch:{ all -> 0x017c }
        if (r5 != 0) goto L_0x0135;
    L_0x0127:
        r5 = r0.mo13587b();	 Catch:{ all -> 0x017c }
        r6 = r8.mo13587b();	 Catch:{ all -> 0x017c }
        r5 = android.text.TextUtils.equals(r5, r6);	 Catch:{ all -> 0x017c }
        if (r5 != 0) goto L_0x0077;
    L_0x0135:
        r0 = r0.mo13584a();	 Catch:{ all -> 0x017c }
        r0 = android.text.TextUtils.isEmpty(r0);	 Catch:{ all -> 0x017c }
        if (r0 == 0) goto L_0x0111;
    L_0x013f:
        goto L_0x0077;
    L_0x0141:
        r0 = "com.baidu.push.webr";
        r1 = r0;
        goto L_0x00ae;
    L_0x0146:
        r0 = "com.baidu.push.lappr";
        r1 = r0;
        goto L_0x00ae;
    L_0x014b:
        r0 = "com.baidu.push.sdkr";
        r1 = r0;
        goto L_0x00ae;
    L_0x0150:
        r1 = r0;
        goto L_0x00ae;
    L_0x0153:
        r0 = move-exception;
        r1 = "IClientManager";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x017c }
        r2.<init>();	 Catch:{ all -> 0x017c }
        r4 = "error : ";
        r2 = r2.append(r4);	 Catch:{ all -> 0x017c }
        r0 = r0.getMessage();	 Catch:{ all -> 0x017c }
        r0 = r2.append(r0);	 Catch:{ all -> 0x017c }
        r0 = r0.toString();	 Catch:{ all -> 0x017c }
        com.baidu.android.pushservice.p036h.C1425a.m6444e(r1, r0);	 Catch:{ all -> 0x017c }
    L_0x0170:
        monitor-exit(r3);	 Catch:{ all -> 0x017c }
        r0 = "";
        goto L_0x00c0;
    L_0x0175:
        r0 = move-exception;
        r1 = "IClientManager";
        com.baidu.android.pushservice.p036h.C1425a.m6440a(r1, r0);	 Catch:{ all -> 0x017c }
        goto L_0x0170;
    L_0x017c:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x017c }
        throw r0;
    L_0x017f:
        r1 = r2;
        goto L_0x0077;
    L_0x0182:
        r0 = r2;
        goto L_0x0076;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.android.pushservice.p031c.C1336e.mo13604a(com.baidu.android.pushservice.c.a, boolean):java.lang.String");
    }

    /* Access modifiers changed, original: protected */
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
            stringBuffer.append(c1331a.mo13584a());
            if (i2 != list.size() - 1) {
                stringBuffer.append(";");
            }
            i = i2 + 1;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public ArrayList<C1331a> mo13606a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String trim : str.trim().split(";")) {
            String[] split = trim.trim().trim().split(",");
            if (split.length == 1 || split.length == 2) {
                C1331a c1331a = new C1331a(split[0]);
                if (split.length == 2) {
                    c1331a.mo13586a(split[1]);
                }
                arrayList.add(c1331a);
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo13607a() {
        String str;
        String str2 = "";
        switch (this.f4737c) {
            case WEBAPP_CLIENT:
                str = "com.baidu.push.webr";
                break;
            case LIGHT_APP_CLIENT_NEW:
                str = "com.baidu.push.lappr";
                break;
            case SDK_CLIENT:
                str = "com.baidu.push.sdkr";
                break;
            case PUSH_CLIENT:
                str = str2;
                break;
            default:
                str = str2;
                break;
        }
        CharSequence charSequence = null;
        if (C1578v.m7056E(this.f4736b)) {
            charSequence = C1535c.m6900a(this.f4736b, str);
        }
        if (TextUtils.isEmpty(charSequence)) {
            if ("com.baidu.push.webr".equals(str)) {
                charSequence = C1574s.m7025d(this.f4736b);
            } else if ("com.baidu.push.lappr".equals(str)) {
                charSequence = C1574s.m7027e(this.f4736b);
            } else if ("com.baidu.push.sdkr".equals(str)) {
                charSequence = C1574s.m7029f(this.f4736b);
            }
        }
        if (TextUtils.isEmpty(charSequence)) {
            C1425a.m6441b("IClientManager", "ClientManager init strApps empty.");
            return;
        }
        try {
            str2 = C1336e.m6042d(charSequence);
            C1425a.m6441b("IClientManager", "ClientManager init strApps : " + str2);
            ArrayList a = mo13606a(str2);
            if (a != null) {
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    this.f4735a.add((C1331a) it.next());
                }
            }
            m6041a(str, this.f4735a);
        } catch (Exception e) {
            C1425a.m6444e("IClientManager", "error : " + e.getMessage());
        } catch (UnsatisfiedLinkError e2) {
            C1425a.m6440a("IClientManager", e2);
        }
    }

    /* renamed from: a */
    public synchronized void mo13608a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            C1425a.m6442c("IClientManager", "ClientManager init strApps empty.");
        } else {
            ArrayList a = mo13606a(C1336e.m6042d(str2));
            String str3 = "";
            if (a != null) {
                ArrayList arrayList = new ArrayList();
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    arrayList.add(((C1331a) it.next()).f4718c);
                }
                int i = 0;
                while (i < this.f4735a.size()) {
                    int i2;
                    if (arrayList.contains(((C1331a) this.f4735a.get(i)).f4718c)) {
                        this.f4735a.remove(i);
                        i2 = i - 1;
                    } else {
                        i2 = i;
                    }
                    i = i2 + 1;
                }
                this.f4735a.addAll(a);
                try {
                    str3 = mo13611e(mo13605a(this.f4735a));
                    if ("com.baidu.push.webr".equals(str)) {
                        C1574s.m7024c(this.f4736b, str3);
                    } else if ("com.baidu.push.lappr".equals(str)) {
                        C1574s.m7026d(this.f4736b, str3);
                    } else if ("com.baidu.push.sdkr".equals(str)) {
                        C1574s.m7028e(this.f4736b, str3);
                    }
                    if (C1578v.m7056E(this.f4736b)) {
                        C1579w.m7158a(this.f4736b, this.f4736b.getPackageName() + ".push_sync", str, str3);
                    }
                } catch (Exception e) {
                    C1425a.m6440a("IClientManager", e);
                }
            }
        }
        return;
    }

    /* renamed from: b */
    public C1331a mo13609b(String str) {
        Iterator it = this.f4735a.iterator();
        while (it.hasNext()) {
            C1331a c1331a = (C1331a) it.next();
            if (!TextUtils.isEmpty(c1331a.mo13584a()) && c1331a.mo13584a().equals(str)) {
                return c1331a;
            }
        }
        return null;
    }

    /* renamed from: c */
    public C1331a mo13610c(String str) {
        Iterator it = this.f4735a.iterator();
        while (it.hasNext()) {
            C1331a c1331a = (C1331a) it.next();
            if (!TextUtils.isEmpty(c1331a.mo13587b()) && c1331a.mo13587b().equals(str)) {
                return c1331a;
            }
        }
        return null;
    }

    /* renamed from: e */
    public String mo13611e(String str) {
        String str2 = "";
        try {
            return C1465b.m6678a(BaiduAppSSOJni.encryptAES(str, 0), "utf-8");
        } catch (Exception e) {
            C1425a.m6444e("IClientManager", "encrypted init strApps exception.");
            return str2;
        } catch (UnsatisfiedLinkError e2) {
            C1425a.m6440a("IClientManager", e2);
            return str2;
        }
    }
}
