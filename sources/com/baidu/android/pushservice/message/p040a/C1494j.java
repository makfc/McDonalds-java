package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.message.C1512k;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;

/* renamed from: com.baidu.android.pushservice.message.a.j */
public class C1494j extends C1481d {
    /* renamed from: b */
    private static final String f5212b = C1494j.class.getSimpleName();

    public C1494j(Context context) {
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
        if (a == null || TextUtils.isEmpty(a.mTitle) || TextUtils.isEmpty(a.mDescription) || TextUtils.isEmpty(a.mUrl)) {
            C1425a.m6444e(f5212b, ">>> pMsg JSON parsing error!");
            C1578v.m7095b(">>> pMsg JSON parsing error!", this.f5198a);
            i2 = 2;
        } else if (C1489g.m6764a(this.f5198a, a) && C1578v.m7116e(this.f5198a, this.f5198a.getPackageName())) {
            C1425a.m6442c(f5212b, ">>> Show pMsg Notification!");
            C1578v.m7095b(">>> Show pMsg Notification!", this.f5198a);
            C1489g.m6757a(this.f5198a, a, str2);
            i2 = 1;
        } else {
            String str3 = ">>> Don't Show pMsg Notification! --- IsBaiduApp = " + C1578v.m7116e(this.f5198a, this.f5198a.getPackageName());
            C1425a.m6442c(f5212b, str3);
            C1578v.m7095b(str3, this.f5198a);
            i2 = 0;
        }
        C1508h c1508h = new C1508h();
        c1508h.mo13991a(i2);
        return c1508h;
    }
}
