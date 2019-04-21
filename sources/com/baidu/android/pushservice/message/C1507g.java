package com.baidu.android.pushservice.message;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.amap.api.location.LocationManagerProxy;
import com.baidu.android.pushservice.C1328a;
import com.baidu.android.pushservice.C1475k;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.jni.BaiduAppSSOJni;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p039k.C1465b;
import com.baidu.android.pushservice.p039k.C1471e;
import com.baidu.android.pushservice.util.C1546j;
import com.baidu.android.pushservice.util.C1547k;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.message.g */
public class C1507g extends C1505e {
    /* renamed from: b */
    private C1546j f5265b;

    public C1507g(Context context) {
        super(context);
    }

    /* renamed from: a */
    private byte[] m6806a(long j, C1508h c1508h) {
        boolean z = false;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C1547k c1547k = new C1547k(byteArrayOutputStream);
        try {
            c1547k.mo14073a(j);
            c1547k.mo14075b(c1508h.mo13990a());
            c1547k.mo14075b(0);
            String str = "MessageHandler";
            StringBuilder append = new StringBuilder().append("reply null? ");
            if (c1508h.mo13993b() == null) {
                z = true;
            }
            C1425a.m6442c(str, append.append(z).toString());
            if (c1508h.mo13993b() != null) {
                c1547k.mo14074a(c1508h.mo13993b());
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                C1425a.m6440a("MessageHandler", e);
            }
            try {
                c1547k.mo14071a();
                return toByteArray;
            } catch (IOException e2) {
                C1425a.m6440a("MessageHandler", e2);
                return toByteArray;
            }
        } catch (Exception e3) {
            C1425a.m6444e("MessageHandler", "wrapMsgHead error : " + e3.getMessage());
            try {
                byteArrayOutputStream.close();
            } catch (IOException e4) {
                C1425a.m6440a("MessageHandler", e4);
            }
            try {
                c1547k.mo14071a();
            } catch (IOException e42) {
                C1425a.m6440a("MessageHandler", e42);
            }
            return null;
        } catch (Throwable e422) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e22) {
                C1425a.m6440a("MessageHandler", e22);
            }
            try {
                c1547k.mo14071a();
            } catch (IOException e222) {
                C1425a.m6440a("MessageHandler", e222);
            }
            throw e422;
        }
    }

    /* renamed from: a */
    private byte[] m6807a(String str, int i) {
        byte[] bArr = new byte[i];
        if (str != null) {
            byte[] bytes = str.getBytes();
            System.arraycopy(bytes, 0, bArr, 0, Math.min(bArr.length, bytes.length));
        }
        return bArr;
    }

    /* renamed from: a */
    private byte[] m6808a(short s, byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C1547k c1547k = new C1547k(byteArrayOutputStream);
        int length = bArr != null ? bArr.length : 0;
        try {
            c1547k.mo14072a((int) s);
            if (!(s == C1509i.MSG_ID_TINY_HEARTBEAT_CLIENT.mo13994a() || s == C1509i.MSG_ID_TINY_HEARTBEAT_SERVER.mo13994a())) {
                c1547k.mo14072a(C1328a.m6003a());
                c1547k.mo14075b(0);
                c1547k.mo14074a(m6807a(C1578v.m7116e(this.f5256a, this.f5256a.getPackageName()) ? "BaiduApp" : "DevApp", 16));
                c1547k.mo14075b(-76508268);
                c1547k.mo14075b(1);
                c1547k.mo14075b(length);
                if (bArr != null) {
                    c1547k.mo14074a(bArr);
                }
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                C1425a.m6444e("MessageHandler", "error " + e.getMessage());
            }
            try {
                c1547k.mo14071a();
                return toByteArray;
            } catch (IOException e2) {
                C1425a.m6444e("MessageHandler", "error " + e2.getMessage());
                return toByteArray;
            }
        } catch (IOException e3) {
            C1425a.m6444e("MessageHandler", "error " + e3.getMessage());
            try {
                byteArrayOutputStream.close();
            } catch (IOException e32) {
                C1425a.m6444e("MessageHandler", "error " + e32.getMessage());
            }
            try {
                c1547k.mo14071a();
            } catch (IOException e322) {
                C1425a.m6444e("MessageHandler", "error " + e322.getMessage());
            }
            return null;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e22) {
                C1425a.m6444e("MessageHandler", "error " + e22.getMessage());
            }
            try {
                c1547k.mo14071a();
            } catch (IOException e222) {
                C1425a.m6444e("MessageHandler", "error " + e222.getMessage());
            }
            throw th;
        }
    }

    /* renamed from: d */
    private String m6809d() {
        try {
            switch (C1578v.m7145t(this.f5256a)) {
                case 1:
                    return "wifi";
                case 2:
                    return "2g";
                case 3:
                    return "3g";
                case 4:
                    return "4g";
                default:
                    return null;
            }
        } catch (Exception e) {
            C1425a.m6440a("MessageHandler", e);
            return null;
        }
    }

    /* renamed from: e */
    private String m6810e() {
        try {
            Display defaultDisplay = ((WindowManager) this.f5256a.getSystemService("window")).getDefaultDisplay();
            return defaultDisplay.getHeight() + "_" + defaultDisplay.getWidth();
        } catch (Exception e) {
            C1425a.m6440a("MessageHandler", e);
            return null;
        }
    }

    /* renamed from: f */
    private String m6811f() {
        try {
            String simOperator = ((TelephonyManager) this.f5256a.getSystemService("phone")).getSimOperator();
            if (simOperator != null) {
                if (simOperator.equals("46000") || simOperator.equals("46002") || simOperator.equals("46007")) {
                    return "cm";
                }
                if (simOperator.equals("46001")) {
                    return "uni";
                }
                if (simOperator.equals("46003")) {
                    return "ct";
                }
            }
        } catch (Exception e) {
            C1425a.m6440a("MessageHandler", e);
        }
        return null;
    }

    /* renamed from: g */
    private String m6812g() {
        try {
            if (C1578v.m7142r(this.f5256a, "android.permission.READ_PHONE_STATE")) {
                return ((TelephonyManager) this.f5256a.getSystemService("phone")).getSubscriberId();
            }
        } catch (Exception e) {
            C1425a.m6440a("MessageHandler", e);
        }
        return null;
    }

    /* renamed from: h */
    private String m6813h() {
        try {
            return ((WifiManager) this.f5256a.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            C1425a.m6440a("MessageHandler", e);
            return null;
        }
    }

    /* renamed from: a */
    public C1506f mo13976a(byte[] bArr, int i) throws IOException {
        int i2 = 20480;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        this.f5265b = new C1546j(byteArrayInputStream);
        short c = this.f5265b.mo14069c();
        C1506f c1506f = new C1506f(c);
        if (c == C1509i.MSG_ID_TINY_HEARTBEAT_SERVER.mo13994a() || c == C1509i.MSG_ID_TINY_HEARTBEAT_CLIENT.mo13994a()) {
            C1425a.m6441b("MessageHandler", "readMessage tiny heart beat from server, msgType:" + c);
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            if (this.f5265b != null) {
                this.f5265b.mo14066a();
            }
            return c1506f;
        }
        byte[] bArr2;
        short c2 = this.f5265b.mo14069c();
        int b = this.f5265b.mo14068b();
        this.f5265b.mo14067a(new byte[16]);
        int b2 = this.f5265b.mo14068b();
        int b3 = this.f5265b.mo14068b();
        int b4 = this.f5265b.mo14068b();
        C1425a.m6441b("MessageHandler", "readMessage nshead, msgId:" + c + " magicNum:" + Integer.toHexString(b2) + " length:" + b4 + " version =" + c2 + " logId =" + b + " reserved = " + b3);
        if (b4 > 0) {
            if (b4 <= 20480) {
                i2 = b4;
            }
            bArr2 = new byte[i2];
            this.f5265b.mo14067a(bArr2);
        } else {
            bArr2 = null;
        }
        c1506f.f5260c = bArr2;
        if (byteArrayInputStream != null) {
            byteArrayInputStream.close();
        }
        if (this.f5265b != null) {
            this.f5265b.mo14066a();
        }
        return c1506f;
    }

    /* renamed from: a */
    public void mo13978a(int i) {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("channel_token", C1475k.m6721a(this.f5256a).mo13949b());
            jSONObject.put("channel_id", C1475k.m6721a(this.f5256a).mo13946a());
            jSONObject.put("sa_mode", ModeConfig.getInstance(this.f5256a).getCurrentMode());
            jSONObject.put("highest_version", ModeConfig.getInstance(this.f5256a).getHighestVersion());
            jSONObject.put("period", 1800);
            jSONObject.put("channel_type", 3);
            jSONObject.put("tinyheart", 1);
            jSONObject.put("connect_version", 2);
            jSONObject.put("tiny_msghead", 1);
            jSONObject.put("alarm_function", 1);
            JSONObject jSONObject2 = new JSONObject();
            str = Build.MODEL;
            if (!(str == null || str == "")) {
                jSONObject2.put("model", str);
            }
            str = m6811f();
            if (!(str == null || str == "")) {
                jSONObject2.put(AnalyticAttribute.CARRIER_ATTRIBUTE, str);
            }
            str = m6810e();
            if (!(str == null || str == "")) {
                jSONObject2.put("resolution", str);
            }
            str = m6809d();
            if (!(str == null || str == "")) {
                jSONObject2.put(LocationManagerProxy.NETWORK_PROVIDER, str);
            }
            str = m6813h();
            if (!(str == null || str == "")) {
                jSONObject2.put("mac", str);
            }
            str = C1471e.m6687a(this.f5256a);
            if (!(str == null || str == "")) {
                jSONObject2.put("cuid", str);
            }
            str = m6812g();
            if (!(str == null || str == "")) {
                jSONObject2.put("imsi", str);
            }
            int length = (!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : JSONObjectInstrumentation.toString(jSONObject2)).length();
            C1425a.m6442c("MessageHandler", "jsonDevInfo = " + (!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : JSONObjectInstrumentation.toString(jSONObject2)));
            str = C1465b.m6678a(BaiduAppSSOJni.encryptAES(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : JSONObjectInstrumentation.toString(jSONObject2), 1), "utf-8");
            C1425a.m6442c("MessageHandler", "devinfo = " + str);
            C1425a.m6442c("MessageHandler", "devinfolength = " + length);
            jSONObject.put("devinfo", str);
            jSONObject.put("devinfolength", length);
            str = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : JSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception e) {
            C1425a.m6440a("MessageHandler", e);
            str = null;
        } catch (UnsatisfiedLinkError e2) {
            C1425a.m6440a("MessageHandler", e2);
            str = null;
        }
        C1425a.m6441b("MessageHandler", "onSessionOpened, send handshake msg :" + str);
        if (!TextUtils.isEmpty(str)) {
            byte[] a = m6808a(C1509i.MSG_ID_HANDSHAKE.mo13994a(), str.getBytes());
            C1506f c1506f = new C1506f(C1509i.MSG_ID_HANDSHAKE.mo13994a());
            c1506f.f5260c = a;
            c1506f.f5261d = true;
            c1506f.mo13984a(false);
            mo13979a(c1506f);
        }
    }

    /* renamed from: b */
    public void mo13980b() {
    }

    /* renamed from: b */
    public void mo13981b(C1506f c1506f) throws Exception {
        if (c1506f != null) {
            C1501a c1501a = new C1501a(this.f5256a);
            C1509i a = C1509i.m6823a(c1506f.f5258a);
            C1503d a2 = c1501a.mo13971a(a);
            if (a2 != null) {
                C1508h a3 = a2.mo13975a(c1506f);
                if (c1506f.f5262e) {
                    C1506f c1506f2 = new C1506f(c1506f.f5258a);
                    if (a == C1509i.MSG_ID_PUSH_MSG) {
                        C1425a.m6442c("MessageHandler", "message need reply , send msg msgId :" + c1506f.mo13988d().mo14008f() + " ret: " + a3.mo13990a());
                        c1506f2.f5260c = m6808a(C1509i.MSG_ID_PUSH_MSG.mo13994a(), m6806a(c1506f.mo13988d().mo14008f(), a3));
                    } else if (a == C1509i.MSG_ID_TINY_HEARTBEAT_SERVER || a == C1509i.MSG_ID_HEARTBEAT_SERVER) {
                        C1425a.m6441b("MessageHandler", "handleServerHeartbeatMsg, send handshake return msg ");
                        c1506f2.f5260c = m6808a(c1506f.f5258a, null);
                    }
                    mo13979a(c1506f2);
                }
            }
        }
    }

    /* renamed from: c */
    public void mo13982c() {
        C1425a.m6441b("MessageHandler", "sendHeartbeatMessage ");
        byte[] a = m6808a(C1509i.MSG_ID_TINY_HEARTBEAT_CLIENT.mo13994a(), null);
        C1506f c1506f = new C1506f(C1509i.MSG_ID_TINY_HEARTBEAT_CLIENT.mo13994a());
        c1506f.f5260c = a;
        c1506f.f5261d = true;
        c1506f.mo13984a(true);
        mo13979a(c1506f);
    }
}
