package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.message.C1512k;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.p031c.C1331a;
import com.baidu.android.pushservice.p031c.C1333c;
import com.baidu.android.pushservice.p031c.C1334d;
import com.baidu.android.pushservice.p031c.C1341j;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;

/* renamed from: com.baidu.android.pushservice.message.a.f */
public class C1488f extends C1481d {
    /* renamed from: b */
    private static final String f5207b = C1488f.class.getSimpleName();

    public C1488f(Context context) {
        super(context);
    }

    /* renamed from: a */
    public static PublicMsg m6750a(Context context, String str, String str2, byte[] bArr, byte[] bArr2) {
        if (!C1578v.m7086a(context, bArr, str, str2, bArr2)) {
            return null;
        }
        PublicMsg a = C1495k.m6776a(context, str2, str, bArr2);
        a.mPkgName = context.getPackageName();
        if (!TextUtils.isEmpty(a.mTitle)) {
            return a;
        }
        a.mTitle = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
        return a;
    }

    /* renamed from: a */
    public C1508h mo13966a(C1512k c1512k, byte[] bArr) {
        return null;
    }

    /* renamed from: a */
    public C1508h mo13967a(String str, String str2, int i, byte[] bArr, byte[] bArr2) {
        int i2;
        PublicMsg a = C1495k.m6776a(this.f5198a, str2, str, bArr2);
        if (a != null && !TextUtils.isEmpty(a.mDescription)) {
            C1334d a2 = C1334d.m6039a(this.f5198a, str);
            if (a2.mo13603a() == C1333c.PUSH_CLIENT) {
                a.mPkgName = a2.f4730a.mo13589c();
            } else if (a2.mo13603a() == C1333c.SDK_CLIENT) {
                a.mPkgName = a2.f4731b.mo13589c();
            }
            switch (a2.mo13603a()) {
                case PUSH_CLIENT:
                case SDK_CLIENT:
                    byte[] a3 = C1578v.m7089a(this.f5198a, str2, bArr2, bArr, a.mPkgName);
                    PackageManager packageManager = this.f5198a.getPackageManager();
                    try {
                        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(a.mPkgName, 128);
                        if (TextUtils.isEmpty(a.mTitle)) {
                            a.mTitle = packageManager.getApplicationLabel(applicationInfo).toString();
                        }
                        C1489g.m6759a(this.f5198a, a, str2, str, i, a3, bArr2);
                        C1425a.m6442c(f5207b, ">>> Show pMsg private Notification!");
                        C1578v.m7095b(">>> Show pMsg private Notification!", this.f5198a);
                        i2 = 1;
                        break;
                    } catch (NameNotFoundException e) {
                        C1425a.m6440a(f5207b, e);
                        if (a2.mo13603a() == C1333c.PUSH_CLIENT) {
                            C1489g.m6760a(this.f5198a, str);
                        } else if (a2.mo13603a() == C1333c.SDK_CLIENT) {
                            C1341j.m6054a(this.f5198a).mo13604a((C1331a) a2.f4731b, false);
                        }
                        i2 = 8;
                        break;
                    }
                case WEBAPP_CLIENT:
                    if (TextUtils.isEmpty(a.mTitle)) {
                        a.mTitle = str;
                    }
                    C1489g.m6767b(this.f5198a, a, str2, str);
                    C1425a.m6442c(f5207b, ">>> Show pMsg private web Notification!");
                    C1578v.m7095b(">>> Show pMsg private Notification!", this.f5198a);
                    i2 = 1;
                    break;
                case LIGHT_APP_CLIENT_NEW:
                    i2 = C1489g.m6753a(this.f5198a, str, str2, bArr2, a);
                    C1425a.m6442c(f5207b, ">>> Handle light app notification!");
                    C1578v.m7095b(">>> Handle light app notification!", this.f5198a);
                    break;
                default:
                    C1425a.m6442c(f5207b, ">>> Don't Show pMsg private Notification! package name is null");
                    C1489g.m6760a(this.f5198a, str);
                    C1578v.m7095b(">>> Don't Show pMsg private Notification! package name is null", this.f5198a);
                    i2 = 7;
                    break;
            }
        }
        C1425a.m6444e(f5207b, ">>> pMsg JSON parsing error!");
        C1578v.m7095b(">>> pMsg JSON parsing error!", this.f5198a);
        i2 = 2;
        C1508h c1508h = new C1508h();
        c1508h.mo13991a(i2);
        return c1508h;
    }
}
