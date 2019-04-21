package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.message.C1512k;
import com.baidu.android.pushservice.p031c.C1332b;
import com.baidu.android.pushservice.p031c.C1339h;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1539f;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.message.a.o */
public class C1500o extends C1481d {
    /* renamed from: b */
    private static final String f5236b = C1500o.class.getSimpleName();

    public C1500o(Context context) {
        super(context);
    }

    /* renamed from: a */
    private C1508h m6784a(String str, long j, String str2, byte[] bArr, String str3) {
        Intent intent = new Intent();
        intent.setAction("com.baidu.android.pushservice.action.CROSS_REQUEST");
        intent.putExtra("message_id", str2);
        intent.putExtra("baidu_message_body", str3);
        intent.putExtra("baidu_message_secur_info", bArr);
        intent.setFlags(32);
        intent.setPackage(str);
        intent.setClassName(str, "com.baidu.android.pushservice.PushService");
        intent.putExtra("bd.cross.request.COMMAND_TYPE", "bd.cross.command.ULTRON_DELIVER");
        intent.putExtra("bd.cross.request.SOURCE_SERVICE", "com.baidu.android.pushservice.PushService");
        intent.putExtra("bd.cross.request.SOURCE_PACKAGE", this.f5198a.getPackageName());
        return new C1539f(this.f5198a, intent).mo14063b();
    }

    /* renamed from: a */
    public C1508h mo13966a(C1512k c1512k, byte[] bArr) {
        return null;
    }

    /* renamed from: a */
    public C1508h mo13967a(String str, String str2, int i, byte[] bArr, byte[] bArr2) {
        C1508h c1508h = new C1508h();
        String str3 = null;
        int i2 = 0;
        String str4 = new String(bArr2);
        C1425a.m6442c(f5236b, "ultronMsg: " + str4);
        try {
            C1339h d;
            String str5;
            C1508h a;
            JSONObject init = JSONObjectInstrumentation.init(str4);
            int optInt = init.optInt("version_require", -1);
            int optInt2 = init.optInt("command_type");
            String optString = init.optString("command_body");
            C1425a.m6442c(f5236b, "ultronMsg, vr: " + optInt + " ct: " + optInt2 + " b: " + optString);
            if (!(TextUtils.isEmpty(str) || str.equals("0"))) {
                d = C1332b.m6020a(this.f5198a).mo13600d(str);
                if (d == null || d.mo13589c() == null) {
                    if (optInt2 == 1) {
                        i2 = 7;
                    }
                } else if (C1578v.m7106c(this.f5198a, d.mo13589c())) {
                    str3 = d.mo13589c();
                    if (optInt2 == 1 && d.mo13590d() < optInt) {
                        i2 = 6;
                    }
                } else if (optInt2 == 1) {
                    i2 = 8;
                }
            }
            if (str3 == null && i2 == 0) {
                d = C1332b.m6020a(this.f5198a).mo13591a(optInt, optInt2 == 2);
                if (d == null) {
                    optInt = 6;
                    str5 = str3;
                } else {
                    optInt = i2;
                    str5 = d.mo13589c();
                }
            } else {
                optInt = i2;
                str5 = str3;
            }
            if (optInt == 0) {
                C1425a.m6442c(f5236b, "ultronMsg, handleMsg: " + str5);
                a = m6784a(str5, 0, str2, bArr, optString);
            } else {
                a = c1508h;
            }
            a.mo13991a(optInt);
            return a;
        } catch (JSONException e) {
            c1508h.mo13991a(2);
            return c1508h;
        }
    }
}
