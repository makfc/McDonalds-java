package com.baidu.android.pushservice.message.p040a;

import android.content.Context;
import com.baidu.android.pushservice.message.C1508h;
import com.baidu.android.pushservice.message.C1512k;
import com.baidu.android.pushservice.message.PublicMsg;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1568q;
import com.baidu.android.pushservice.util.C1578v;

/* renamed from: com.baidu.android.pushservice.message.a.b */
public class C1483b extends C1481d {
    /* renamed from: b */
    private static final String f5200b = C1483b.class.getSimpleName();

    public C1483b(Context context) {
        super(context);
    }

    /* renamed from: a */
    public C1508h mo13966a(C1512k c1512k, byte[] bArr) {
        long b = c1512k.mo14001b();
        long c = c1512k.mo14003c();
        long d = c1512k.mo14005d();
        C1498m a = C1498m.m6780a(c1512k.mo14010h());
        String g = c1512k.mo14009g();
        String e = c1512k.mo14007e();
        c -= b;
        b = d - b;
        PublicMsg publicMsg = new PublicMsg();
        C1425a.m6442c(f5200b, "Alarm Message Type = " + a);
        C1508h c1508h = new C1508h();
        if (!c1512k.mo14000a() || (c <= 0 && b > 0)) {
            C1508h a2;
            C1498m c1498m = a.equals(C1498m.MSG_TYPE_ALARM_NOTIFICATION) ? C1498m.MSG_TYPE_MULTI_PRIVATE_NOTIFICATION : a.equals(C1498m.MSG_TYPE_ALARM_MESSAGE) ? C1498m.MSG_TYPE_PRIVATE_MESSAGE : a.equals(C1498m.MSG_TYPE_ALARM_AD_NOTIFICATION) ? C1498m.MSG_TYPE_ADVERTISE : a;
            C1481d a3 = new C1497l(this.f5198a).mo13969a(c1498m);
            if (a3 != null) {
                a2 = a3.mo13967a(c1512k.mo14007e(), c1512k.mo14009g(), c1498m.mo13970a(), c1512k.mo14011i(), bArr);
                C1425a.m6442c(f5200b, "handle normal  message msgType = " + c1498m);
                C1568q.m7008d(this.f5198a, c1512k.mo14009g());
                if (c1498m.equals(C1498m.MSG_TYPE_MULTI_PRIVATE_NOTIFICATION)) {
                    publicMsg.handleAlarmMessage(this.f5198a, "010701", g, e);
                } else if (c1498m.equals(C1498m.MSG_TYPE_PRIVATE_MESSAGE)) {
                    publicMsg.handleAlarmMessage(this.f5198a, "010702", g, e);
                } else if (c1498m.equals(C1498m.MSG_TYPE_ADVERTISE)) {
                    publicMsg.handleAlarmMessage(this.f5198a, "010703", g, e);
                }
            } else {
                C1425a.m6441b(f5200b, "message type invalid ");
                a2 = c1508h;
            }
            return a2;
        } else if (b <= 0) {
            publicMsg.handleAlarmMessage(this.f5198a, "010704", g, e);
            C1568q.m7008d(this.f5198a, c1512k.mo14009g());
            C1425a.m6442c(f5200b, "alarm message is expired!");
            return c1508h;
        } else {
            c1512k.mo14002b(System.currentTimeMillis() + (1000 * c));
            c1512k.mo14004c(System.currentTimeMillis() + (1000 * b));
            C1425a.m6442c(f5200b, "lastshowtime = " + c + "   lastexpiretime = " + b);
            C1578v.m7074a(this.f5198a, c1512k, bArr);
            c1508h.mo13991a(1);
            return c1508h;
        }
    }

    /* renamed from: a */
    public C1508h mo13967a(String str, String str2, int i, byte[] bArr, byte[] bArr2) {
        return null;
    }
}
