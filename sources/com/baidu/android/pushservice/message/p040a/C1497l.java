package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;

/* renamed from: com.baidu.android.pushservice.message.a.l */
public class C1497l {
    /* renamed from: a */
    private static final String f5214a = C1497l.class.getSimpleName();
    /* renamed from: b */
    private Context f5215b;

    public C1497l(Context context) {
        this.f5215b = context;
    }

    /* renamed from: a */
    public C1481d mo13969a(C1498m c1498m) {
        switch (c1498m) {
            case MSG_TYPE_SINGLE_PRIVATE:
            case MSG_TYPE_MULTI_PRIVATE:
                return new C1491h(this.f5215b);
            case MSG_TYPE_PRIVATE_MESSAGE:
                return new C1493i(this.f5215b);
            case MSG_TYPE_SINGLE_PUBLIC:
            case MSG_TYPE_MULTI_PUBLIC:
                return new C1494j(this.f5215b);
            case MSG_TYPE_MULTI_PRIVATE_NOTIFICATION:
                return new C1488f(this.f5215b);
            case MSG_TYPE_RICH_MEDIA:
                return new C1499n(this.f5215b);
            case MSG_TYPE_BAIDU_SUPPER:
                return new C1484c(this.f5215b);
            case MSG_TYPE_ADVERTISE:
                return new C1482a(this.f5215b);
            case MSG_TYPE_INNERBIND:
                return new C1486e(this.f5215b);
            case MSG_TYPE_ALARM_MESSAGE:
            case MSG_TYPE_ALARM_NOTIFICATION:
            case MSG_TYPE_ALARM_AD_NOTIFICATION:
                C1425a.m6442c(f5214a, ">>> MSG_TYPE_ALARM msg_type : " + c1498m);
                return new C1483b(this.f5215b);
            case MSG_TYPE_ULTRON_COMMAND:
                return new C1500o(this.f5215b);
            default:
                C1425a.m6444e(f5214a, ">>> Unknown msg_type : " + c1498m);
                C1578v.m7095b(">>> Unknown msg_type : " + c1498m, this.f5215b);
                return null;
        }
    }
}
