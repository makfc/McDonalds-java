package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.message.C1512k;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.p031c.C1331a;
import com.baidu.android.pushservice.p031c.C1334d;
import com.baidu.android.pushservice.p031c.C1341j;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.message.a.i */
public class C1493i extends C1481d {
    /* renamed from: b */
    private static final String f5211b = C1493i.class.getSimpleName();

    public C1493i(Context context) {
        super(context);
    }

    /* renamed from: a */
    public static String[] m6771a(Context context, int i, String str, String str2, byte[] bArr, byte[] bArr2) {
        if (!C1578v.m7086a(context, bArr, str, str2, bArr2)) {
            return null;
        }
        String[] strArr = new String[2];
        if (i == C1498m.MSG_TYPE_SINGLE_PRIVATE.mo13970a() || i == C1498m.MSG_TYPE_MULTI_PRIVATE.mo13970a()) {
            strArr[0] = new String(bArr2);
            strArr[1] = null;
        } else if (i == C1498m.MSG_TYPE_PRIVATE_MESSAGE.mo13970a()) {
            PublicMsg a = C1495k.m6776a(context, str2, str, bArr2);
            strArr[0] = a.mDescription;
            strArr[1] = a.mCustomContent;
        }
        return strArr;
    }

    /* renamed from: a */
    public C1508h mo13966a(C1512k c1512k, byte[] bArr) {
        return null;
    }

    /* renamed from: a */
    public C1508h mo13967a(String str, String str2, int i, byte[] bArr, byte[] bArr2) {
        int i2 = 0;
        C1334d a = C1334d.m6039a(this.f5198a, str);
        byte[] a2;
        Intent intent;
        String str3;
        JSONObject init;
        switch (a.mo13603a()) {
            case PUSH_CLIENT:
                a2 = C1578v.m7089a(this.f5198a, str2, bArr2, bArr, a.f4730a.mo13589c());
                try {
                    this.f5198a.getPackageManager().getPackageInfo(a.f4730a.mo13589c(), 128);
                    PublicMsg a3 = C1495k.m6776a(this.f5198a, str2, str, bArr2);
                    boolean a4 = mo13968a(bArr2);
                    if (a3 != null) {
                        String str4;
                        intent = new Intent();
                        str3 = "";
                        if (a4) {
                            str4 = "com.baidu.android.pushservice.action.FB_MESSAGE";
                        } else {
                            str3 = "com.baidu.android.pushservice.action.MESSAGE";
                            intent.putExtra("msg_id", str2);
                            str4 = str3;
                        }
                        intent.putExtra("message_string", a3.mDescription);
                        intent.putExtra("message_id", str2);
                        intent.putExtra("baidu_message_type", i);
                        intent.putExtra("baidu_message_body", bArr2);
                        intent.putExtra("app_id", str);
                        intent.putExtra("baidu_message_secur_info", a2);
                        if (!TextUtils.isEmpty(a3.mCustomContent)) {
                            try {
                                init = JSONObjectInstrumentation.init(a3.mCustomContent);
                                Iterator keys = init.keys();
                                while (keys.hasNext()) {
                                    str3 = (String) keys.next();
                                    intent.putExtra(str3, init.getString(str3));
                                }
                                intent.putExtra("extra_extra_custom_content", a3.mCustomContent);
                            } catch (JSONException e) {
                                C1425a.m6444e(f5211b, "Custom content to JSONObject exception::" + e.getMessage());
                            }
                        }
                        i2 = C1578v.m7063a(this.f5198a, intent, str4, a.f4730a.mo13589c());
                        str4 = ">>> Deliver message to client: " + a.f4730a.mo13589c() + " msg: " + a3.mDescription + " result: " + i2;
                        C1425a.m6441b(f5211b, str4);
                        C1578v.m7095b(str4, this.f5198a);
                        break;
                    }
                } catch (NameNotFoundException e2) {
                    str3 = ">>> NOT deliver to app: " + a.f4730a.mo13589c() + ", package has been uninstalled.";
                    C1489g.m6760a(this.f5198a, str);
                    C1425a.m6441b(f5211b, str3);
                    C1578v.m7095b(str3, this.f5198a);
                    i2 = 8;
                    break;
                }
                break;
            case SDK_CLIENT:
                a2 = C1578v.m7089a(this.f5198a, str2, bArr2, bArr, a.f4731b.mo13589c());
                C1425a.m6442c(f5211b, "receive sdk message " + new String(bArr2) + " pkgName = " + a.f4731b.mo13589c());
                String str5 = "";
                try {
                    str5 = JSONObjectInstrumentation.init(new String(bArr2)).optString("description");
                    if (!TextUtils.isEmpty(str5)) {
                        try {
                            this.f5198a.getPackageManager().getPackageInfo(a.f4731b.mo13589c(), 128);
                            intent = new Intent();
                            intent.setPackage(a.f4731b.mo13589c());
                            intent.putExtra(HexAttributes.HEX_ATTR_MESSAGE, bArr2);
                            intent.putExtra("message_string", str5);
                            intent.setFlags(32);
                            intent.putExtra("baidu_message_body", bArr2);
                            intent.putExtra("baidu_message_secur_info", a2);
                            intent.putExtra("message_id", str2);
                            intent.putExtra("baidu_message_type", i);
                            C1578v.m7094b(this.f5198a, intent, "com.baidu.android.pushservice.action.SDK_MESSAGE", a.f4731b.mo13589c());
                            break;
                        } catch (NameNotFoundException e3) {
                            str3 = ">>> NOT deliver to app: " + a.f4731b.mo13589c() + ", package has been uninstalled.";
                            C1341j.m6054a(this.f5198a).mo13604a((C1331a) a.f4731b, false);
                            C1425a.m6441b(f5211b, str3);
                            i2 = 8;
                            break;
                        }
                    }
                    i2 = 2;
                    break;
                } catch (JSONException e4) {
                    C1425a.m6442c(f5211b, "description is null return invalid");
                    i2 = 2;
                    break;
                }
            case LIGHT_APP_CLIENT_NEW:
                CharSequence charSequence = "";
                try {
                    init = JSONObjectInstrumentation.init(new String(bArr2));
                    if (!init.isNull("description")) {
                        charSequence = init.getString("description");
                    }
                } catch (JSONException e5) {
                    i2 = 2;
                }
                if (!TextUtils.isEmpty(charSequence)) {
                    Intent intent2 = new Intent();
                    intent2.putExtra(HexAttributes.HEX_ATTR_MESSAGE, bArr2);
                    intent2.putExtra("message_string", charSequence);
                    C1578v.m7094b(this.f5198a, intent2, "com.baidu.android.pushservice.action.LAPP_MESSAGE", a.f4732c.mo13589c());
                    break;
                }
                break;
            default:
                i2 = 7;
                String str6 = ">>> NOT found client for privateMessageHandler appid " + str;
                C1489g.m6760a(this.f5198a, str);
                C1425a.m6441b(f5211b, str6 + " msgbody " + new String(bArr2));
                C1578v.m7095b(str6, this.f5198a);
                break;
        }
        C1508h c1508h = new C1508h();
        c1508h.mo13991a(i2);
        return c1508h;
    }
}
