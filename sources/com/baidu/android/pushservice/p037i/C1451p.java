package com.baidu.android.pushservice.p037i;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.android.pushservice.message.p040a.C1498m;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.baidu.android.pushservice.util.C1578v;

/* renamed from: com.baidu.android.pushservice.i.p */
public class C1451p {
    /* renamed from: a */
    private static void m6589a(Context context, C1339h c1339h, C1450o c1450o, C1449n c1449n) {
        if (c1339h != null) {
            c1449n.mo13861d(c1339h.mo13589c());
            c1449n = C1578v.m7066a(c1449n, context, c1339h.mo13589c());
        }
        try {
            C1456u.m6613a(context, c1450o);
            C1456u.m6612a(context, c1449n);
        } catch (Exception e) {
            C1425a.m6441b("PushBehaviorHelper", "MH insert db exception");
        }
    }

    /* renamed from: a */
    public static void m6590a(Context context, String str, String str2, int i, byte[] bArr, int i2, int i3) {
        C1450o c1450o = new C1450o();
        c1450o.f5036f = "010101";
        c1450o.f5119a = str2;
        c1450o.f5037g = System.currentTimeMillis();
        c1450o.f5038h = C1432b.m6486c(context);
        c1450o.f5120b = new String(bArr).length();
        c1450o.f5039i = i2;
        c1450o.f5121c = i;
        c1450o.f5040j = str;
        C1449n c1449n = new C1449n(str);
        c1449n.mo13918c(i3);
        C1339h d = C1332b.m6020a(context).mo13600d(str);
        if (d != null) {
            c1449n.mo13859c(C1578v.m7069a(d.f4739e));
            c1449n.mo13857b(d.f4739e);
            c1449n.mo13861d(d.mo13589c());
        } else {
            c1449n.mo13859c("0");
            c1449n.mo13857b("0");
            c1449n.mo13861d("NP");
        }
        C1451p.m6589a(context, d, c1450o, c1449n);
    }

    /* renamed from: a */
    public static void m6591a(Context context, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                C1450o c1450o = new C1450o();
                String str4 = null;
                if ("com.baidu.android.pushservice.action.passthrough.notification.CLICK".equals(str3)) {
                    str4 = "010601";
                } else if ("com.baidu.android.pushservice.action.passthrough.notification.DELETE".equals(str3)) {
                    str4 = "010602";
                } else if ("com.baidu.android.pushservice.action.passthrough.notification.NOTIFIED".equals(str3)) {
                    str4 = "010603";
                }
                c1450o.f5036f = str4;
                c1450o.f5119a = str;
                c1450o.f5037g = System.currentTimeMillis();
                c1450o.f5038h = C1432b.m6486c(context);
                c1450o.f5121c = C1498m.MSG_TYPE_PRIVATE_MESSAGE.mo13970a();
                c1450o.f5040j = str2;
                C1339h d = C1332b.m6020a(context).mo13600d(str2);
                if (d != null) {
                    C1451p.m6589a(context, d, c1450o, new C1449n(str2));
                }
            } catch (Exception e) {
                C1425a.m6444e("PushBehaviorHelper", "error " + e.getMessage());
            }
        }
    }

    /* renamed from: b */
    public static void m6592b(Context context, String str, String str2, int i, byte[] bArr, int i2, int i3) {
        C1450o c1450o = new C1450o();
        c1450o.f5036f = "019901";
        c1450o.f5119a = str2;
        c1450o.f5037g = System.currentTimeMillis();
        c1450o.f5038h = C1432b.m6486c(context);
        c1450o.f5120b = new String(bArr).length();
        c1450o.f5039i = i2;
        c1450o.f5121c = i;
        c1450o.f5040j = str;
        C1449n c1449n = new C1449n(str);
        c1449n.mo13918c(i3);
        C1339h d = C1332b.m6020a(context).mo13600d(str);
        if (d != null) {
            c1449n.mo13859c(C1578v.m7069a(d.f4739e));
            c1449n.mo13857b(d.f4739e);
            c1449n.mo13861d(d.mo13589c());
        } else {
            c1449n.mo13859c("0");
            c1449n.mo13857b("0");
            c1449n.mo13861d("NP");
        }
        C1451p.m6589a(context, d, c1450o, c1449n);
    }
}
