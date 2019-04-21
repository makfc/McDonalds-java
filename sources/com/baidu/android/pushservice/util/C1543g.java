package com.baidu.android.pushservice.util;

import android.content.Context;
import android.os.Build;
import com.baidu.android.pushservice.PushSettings;
import com.baidu.android.pushservice.config.ModeConfig;
import com.baidu.android.pushservice.message.p040a.C1498m;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p033e.C1370b;
import com.baidu.android.pushservice.p034f.C1403b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p037i.C1450o;
import com.baidu.android.pushservice.p037i.C1456u;
import com.baidu.android.pushservice.p037i.p038a.C1432b;
import com.facebook.internal.ServerProtocol;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.HashMap;

/* renamed from: com.baidu.android.pushservice.util.g */
public class C1543g {
    /* renamed from: a */
    public static void m6921a(Context context, String str, int i, int i2, String str2, int i3) {
        final Context context2 = context;
        final String str3 = str;
        final int i4 = i2;
        final String str4 = str2;
        final int i5 = i;
        final int i6 = i3;
        C1462d.m6637a().mo13938a(new C1281c("sendHttpACK", (short) 90) {
            /* renamed from: a */
            public void mo13487a() {
                HashMap a = C1543g.m6925b(context2, str3, i4, str4, true, i5);
                try {
                    String str = "https://hack.tuisong.baidu.com/statistics/msg_ack";
                    if (i6 == 2) {
                        str = "https://hack.tuisong.baidu.com/statistics/xiaomi/msg_ack";
                    }
                    C1425a.m6442c("HttpMessageAction", "sendHttpACK, msgId: " + str3 + " ACK: " + i5 + " result: " + C1403b.m6260a(str, "POST", a, "").mo13745b());
                } catch (Exception e) {
                    C1425a.m6444e("HttpMessageAction", "sendHttpACK, Exception: " + e.getMessage());
                    C1425a.m6440a("HttpMessageAction", e);
                }
            }
        });
    }

    /* renamed from: a */
    public static void m6922a(Context context, String str, int i, String str2) {
        final Context context2 = context;
        final String str3 = str;
        final int i2 = i;
        final String str4 = str2;
        C1462d.m6637a().mo13938a(new C1281c("sendHttpNotificationDeleted", (short) 90) {
            /* renamed from: a */
            public void mo13487a() {
                HashMap a = C1543g.m6925b(context2, str3, i2, str4, false, 2);
                try {
                    C1543g.m6926b(context2, str3, "010202");
                    C1425a.m6442c("HttpMessageAction", "sendHttpNotificationDeleted, msgId: " + str3 + " result: " + C1403b.m6260a("https://hack.tuisong.baidu.com/statistics/msg_action", "POST", a, "BCCS_SDK/3.0").mo13745b());
                } catch (Exception e) {
                    C1425a.m6444e("HttpMessageAction", "sendHttpNotificationDeleted, Exception: " + e.getMessage());
                    C1425a.m6440a("HttpMessageAction", e);
                }
            }
        });
    }

    /* renamed from: a */
    public static void m6923a(Context context, String str, int i, String str2, int i2) {
        final Context context2 = context;
        final String str3 = str;
        final int i3 = i;
        final String str4 = str2;
        final int i4 = i2;
        C1462d.m6637a().mo13938a(new C1281c("sendHttpNotificationClicked", (short) 90) {
            /* renamed from: a */
            public void mo13487a() {
                HashMap a = C1543g.m6925b(context2, str3, i3, str4, false, 1);
                C1543g.m6926b(context2, str3, "010201");
                try {
                    String str = "https://hack.tuisong.baidu.com/statistics/msg_action";
                    if (i4 == 2) {
                        str = "https://hack.tuisong.baidu.com/statistics/xiaomi/msg_action";
                    }
                    C1425a.m6442c("HttpMessageAction", "sendHttpNotificationClicked, msgId: " + str3 + " result: " + C1403b.m6260a(str, "POST", a, "BCCS_SDK/3.0").mo13745b());
                } catch (Exception e) {
                    C1425a.m6444e("HttpMessageAction", "sendHttpNotificationClicked, Exception: " + e.getMessage());
                    C1425a.m6440a("HttpMessageAction", e);
                }
            }
        });
    }

    /* renamed from: b */
    private static HashMap<String, String> m6925b(Context context, String str, int i, String str2, boolean z, int i2) {
        HashMap hashMap = new HashMap();
        C1370b.m6206b(hashMap);
        hashMap.put("appid", PushSettings.m5878b(context));
        hashMap.put("channelid", PushSettings.m5874a(context));
        hashMap.put("msg_id", str);
        hashMap.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, System.currentTimeMillis() + "");
        hashMap.put("push_type", i + "");
        if (i == 2) {
            hashMap.put("gid", str2);
        }
        int currentMode = ModeConfig.getInstance(context).getCurrentMode();
        hashMap.put("cur_mode", ModeConfig.getInstance(context).getCurrentMode() + "");
        if (currentMode == ModeConfig.MODE_I_HW) {
            hashMap.put(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, "HUAWEI");
            hashMap.put(ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN, C1551o.m6964a(context));
        } else if (currentMode == ModeConfig.MODE_I_XM) {
            hashMap.put(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, "Xiaomi");
            hashMap.put(ServerProtocol.DIALOG_RESPONSE_TYPE_TOKEN, C1551o.m6967b(context));
        } else {
            hashMap.put(AnalyticAttribute.APPLICATION_PLATFORM_ATTRIBUTE, Build.MANUFACTURER);
        }
        if (z) {
            hashMap.put("ack_value", i2 + "");
        } else {
            hashMap.put("action_value", i2 + "");
        }
        C1425a.m6442c("HttpMessageAction", "buildHttpActionParams: " + hashMap.toString());
        return hashMap;
    }

    /* renamed from: b */
    private static void m6926b(Context context, String str, String str2) {
        C1450o c1450o = new C1450o();
        c1450o.f5036f = str2;
        c1450o.f5119a = str;
        c1450o.f5037g = System.currentTimeMillis();
        c1450o.f5038h = C1432b.m6486c(context);
        c1450o.f5121c = C1498m.MSG_TYPE_MULTI_PRIVATE_NOTIFICATION.mo13970a();
        c1450o.f5040j = PushSettings.m5878b(context);
        C1456u.m6613a(context, c1450o);
    }
}
