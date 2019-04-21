package com.alipay.sdk.sys;

import android.content.Context;
import com.alipay.sdk.app.statistic.C0590a;
import com.alipay.sdk.data.C0617c;
import com.alipay.sdk.util.C0646c;
import com.p044ta.utdid2.device.UTDevice;
import java.io.File;

/* renamed from: com.alipay.sdk.sys.b */
public class C0640b {
    /* renamed from: a */
    private static C0640b f619a;
    /* renamed from: b */
    private Context f620b;

    private C0640b() {
    }

    /* renamed from: a */
    public static C0640b m974a() {
        if (f619a == null) {
            f619a = new C0640b();
        }
        return f619a;
    }

    /* renamed from: b */
    public Context mo8088b() {
        return this.f620b;
    }

    /* renamed from: a */
    public void mo8087a(Context context, C0617c c0617c) {
        this.f620b = context.getApplicationContext();
    }

    /* renamed from: c */
    public C0617c mo8089c() {
        return C0617c.m879b();
    }

    /* renamed from: d */
    public static boolean m975d() {
        for (String file : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: e */
    public String mo8090e() {
        String str = "";
        try {
            return UTDevice.getUtdid(this.f620b);
        } catch (Throwable th) {
            C0646c.m1016a(th);
            C0590a.m802a("third", "GetUtdidEx", th);
            return str;
        }
    }
}
