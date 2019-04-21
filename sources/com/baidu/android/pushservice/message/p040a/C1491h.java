package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.message.C1512k;
import com.baidu.android.pushservice.p031c.C1331a;
import com.baidu.android.pushservice.p031c.C1334d;
import com.baidu.android.pushservice.p031c.C1341j;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.agentdata.HexAttributes;

/* renamed from: com.baidu.android.pushservice.message.a.h */
public class C1491h extends C1481d {
    /* renamed from: b */
    private static final String f5209b = C1491h.class.getSimpleName();

    public C1491h(Context context) {
        super(context);
    }

    /* renamed from: a */
    public C1508h mo13966a(C1512k c1512k, byte[] bArr) {
        return null;
    }

    /* renamed from: a */
    public C1508h mo13967a(String str, String str2, int i, byte[] bArr, byte[] bArr2) {
        int i2 = 0;
        String str3 = new String(bArr2);
        C1334d a = C1334d.m6039a(this.f5198a, str);
        Intent intent;
        switch (a.mo13603a()) {
            case PUSH_CLIENT:
                byte[] a2 = C1578v.m7089a(this.f5198a, str2, bArr2, bArr, a.f4730a.mo13589c());
                try {
                    this.f5198a.getPackageManager().getPackageInfo(a.f4730a.mo13589c(), 128);
                    intent = new Intent();
                    intent.putExtra("app_id", str);
                    intent.putExtra("msg_id", str2);
                    intent.putExtra(HexAttributes.HEX_ATTR_MESSAGE, bArr2);
                    intent.putExtra("message_string", str3);
                    intent.putExtra("message_id", str2);
                    intent.putExtra("baidu_message_type", i);
                    intent.putExtra("baidu_message_body", bArr2);
                    intent.putExtra("baidu_message_secur_info", a2);
                    i2 = C1578v.m7063a(this.f5198a, intent, "com.baidu.android.pushservice.action.MESSAGE", a.f4730a.mo13589c());
                    str3 = ">>> Deliver message to client: " + a.f4730a.mo13589c() + " result: " + i2;
                    C1425a.m6442c(f5209b, str3);
                    C1578v.m7095b(str3, this.f5198a);
                    break;
                } catch (NameNotFoundException e) {
                    String str4 = ">>> NOT deliver to app: " + a.f4730a.mo13589c() + ", package has been uninstalled.";
                    C1489g.m6760a(this.f5198a, str);
                    C1425a.m6441b(f5209b, str4);
                    C1578v.m7095b(str4, this.f5198a);
                    i2 = 7;
                    break;
                }
            case SDK_CLIENT:
                try {
                    byte[] a3 = C1578v.m7089a(this.f5198a, str2, bArr2, bArr, a.f4731b.mo13589c());
                    this.f5198a.getPackageManager().getPackageInfo(a.f4731b.mo13589c(), 128);
                    C1425a.m6442c(f5209b, "receive sdk message " + str3 + " pkgName = " + a.f4731b.mo13589c());
                    intent = new Intent();
                    intent.setPackage(a.f4731b.mo13589c());
                    intent.putExtra(HexAttributes.HEX_ATTR_MESSAGE, bArr2);
                    intent.putExtra("message_string", str3);
                    intent.putExtra("baidu_message_type", i);
                    intent.putExtra("baidu_message_body", bArr2);
                    intent.putExtra("baidu_message_secur_info", a3);
                    intent.putExtra("message_id", str2);
                    C1578v.m7094b(this.f5198a, intent, "com.baidu.android.pushservice.action.SDK_MESSAGE", a.f4731b.mo13589c());
                    break;
                } catch (NameNotFoundException e2) {
                    C1341j.m6054a(this.f5198a).mo13604a((C1331a) a.f4731b, false);
                    i2 = 8;
                    break;
                }
            case LIGHT_APP_CLIENT_NEW:
                Intent intent2 = new Intent("com.baidu.android.pushservice.action.LAPP_MESSAGE");
                intent2.putExtra(HexAttributes.HEX_ATTR_MESSAGE, bArr2);
                intent2.putExtra("message_string", str3);
                intent2.setFlags(32);
                this.f5198a.sendBroadcast(intent2);
                break;
            default:
                C1425a.m6441b(f5209b, "NOT delivere message to app: client not found appid " + str);
                C1489g.m6760a(this.f5198a, str);
                C1425a.m6442c(f5209b, "msgbody " + str3);
                C1578v.m7095b(">>> Don't found app  in OldPrivateMessage " + str3, this.f5198a);
                i2 = 7;
                break;
        }
        C1508h c1508h = new C1508h();
        c1508h.mo13991a(i2);
        return c1508h;
    }
}
