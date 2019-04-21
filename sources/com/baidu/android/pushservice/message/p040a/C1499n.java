package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.message.C1512k;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;

/* renamed from: com.baidu.android.pushservice.message.a.n */
public class C1499n extends C1481d {
    /* renamed from: b */
    private static final String f5235b = C1499n.class.getSimpleName();

    public C1499n(Context context) {
        super(context);
    }

    /* renamed from: a */
    public C1508h mo13966a(C1512k c1512k, byte[] bArr) {
        return null;
    }

    /* renamed from: a */
    public C1508h mo13967a(String str, String str2, int i, byte[] bArr, byte[] bArr2) {
        int i2;
        PublicMsg a = C1495k.m6776a(this.f5198a, str2, str, bArr2);
        if (a == null || TextUtils.isEmpty(a.mUrl)) {
            C1425a.m6442c(f5235b, ">>> Don't Show rich media Notification! url is null");
            C1578v.m7095b(">>> Don't Show rich media Notification! url is null", this.f5198a);
            i2 = 2;
        } else {
            C1339h d = C1332b.m6020a(this.f5198a).mo13600d(str);
            if (d == null || d.mo13589c() == null) {
                i2 = 7;
                C1425a.m6442c(f5235b, ">>> RichMediaMessage client Not found");
            } else {
                a.mPkgName = d.mo13589c();
                byte[] a2 = C1578v.m7089a(this.f5198a, str2, bArr2, bArr, a.mPkgName);
                try {
                    this.f5198a.getPackageManager().getPackageInfo(d.mo13589c(), 128);
                    C1489g.m6761a(this.f5198a, str, a, str2, i, a2, bArr2);
                    i2 = 1;
                    C1425a.m6442c(f5235b, ">>> Show rich media Notification!");
                    C1578v.m7095b(">>> Show rich media Notification!", this.f5198a);
                } catch (NameNotFoundException e) {
                    i2 = 8;
                    String str3 = ">>> NOT deliver to app: " + d.mo13589c() + ", package has been uninstalled.";
                    C1489g.m6760a(this.f5198a, str);
                    C1425a.m6441b(f5235b, str3);
                    C1578v.m7095b(str3, this.f5198a);
                }
            }
        }
        C1508h c1508h = new C1508h();
        c1508h.mo13991a(i2);
        return c1508h;
    }
}
