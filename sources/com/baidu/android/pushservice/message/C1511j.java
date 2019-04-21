package com.baidu.android.pushservice.message;

import android.content.Context;
import com.baidu.android.pushservice.message.p040a.C1481d;
import com.baidu.android.pushservice.message.p040a.C1497l;
import com.baidu.android.pushservice.message.p040a.C1498m;
import com.baidu.android.pushservice.p026j.C1281c;
import com.baidu.android.pushservice.p026j.C1462d;
import com.baidu.android.pushservice.p031c.C1333c;
import com.baidu.android.pushservice.p031c.C1334d;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.p036h.C1426b;
import com.baidu.android.pushservice.p037i.C1449n;
import com.baidu.android.pushservice.p037i.C1451p;
import com.baidu.android.pushservice.util.C1546j;
import com.baidu.android.pushservice.util.C1568q;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.message.j */
public class C1511j extends C1503d {
    /* renamed from: b */
    private static final String f5278b = C1511j.class.getSimpleName();
    /* renamed from: c */
    private Context f5279c;
    /* renamed from: d */
    private long f5280d;
    /* renamed from: e */
    private long f5281e;
    /* renamed from: f */
    private long f5282f;
    /* renamed from: g */
    private int f5283g;
    /* renamed from: h */
    private String f5284h;
    /* renamed from: i */
    private short f5285i;
    /* renamed from: j */
    private final int f5286j = 140;
    /* renamed from: k */
    private final int f5287k = 64;

    public C1511j(Context context) {
        super(context);
        this.f5279c = context.getApplicationContext();
    }

    /* renamed from: a */
    private String m6827a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        int i = 0;
        while (i < bArr.length) {
            if (bArr[i] == (byte) 0) {
                break;
            }
            i++;
        }
        i = 0;
        return new String(bArr, 0, i);
    }

    /* renamed from: a */
    public C1508h mo13975a(C1506f c1506f) {
        c1506f.f5262e = true;
        C1508h c1508h = new C1508h();
        c1508h.mo13991a(-1);
        byte[] bArr = c1506f.f5260c;
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            C1546j c1546j = new C1546j(byteArrayInputStream);
            this.f5285i = (short) 0;
            try {
                C1512k c1512k = new C1512k();
                byte[] bArr2 = new byte[128];
                c1546j.mo14067a(bArr2);
                c1512k.mo13997a(m6827a(bArr2));
                c1512k.mo14006d(c1546j.mo14070d());
                int c = c1546j.mo14069c();
                c1512k.mo13995a(c);
                this.f5285i = c1546j.mo14069c();
                byte[] bArr3 = new byte[64];
                c1546j.mo14067a(bArr3);
                c1512k.mo13999a(bArr3);
                C1425a.m6442c(f5278b, "Alarm Message Received  ctxlength = " + this.f5285i);
                if (this.f5285i <= (short) 0) {
                    c1512k.mo13998a(false);
                    C1425a.m6442c(f5278b, "Alarm Message Json = setIsAlarm false");
                } else if (C1498m.MSG_TYPE_ALARM_NOTIFICATION.mo13970a() == c || C1498m.MSG_TYPE_ALARM_MESSAGE.mo13970a() == c || C1498m.MSG_TYPE_ALARM_AD_NOTIFICATION.mo13970a() == c) {
                    c1512k.mo13998a(true);
                    C1462d.m6637a().mo13938a(new C1281c("deleteInvalidAlarmMsg", (short) 95) {
                        /* renamed from: a */
                        public void mo13487a() {
                            C1568q.m7006c(C1511j.this.f5254a);
                            C1425a.m6442c(C1511j.f5278b, "deleteInvalidAlarmMsg");
                        }
                    });
                    bArr2 = new byte[this.f5285i];
                    c1546j.mo14067a(bArr2);
                    try {
                        JSONObject init = JSONObjectInstrumentation.init(new String(bArr2));
                        if (!init.isNull("alarmmsgid")) {
                            this.f5284h = init.getString("alarmmsgid");
                            if (!init.isNull("alarmmsgenable")) {
                                this.f5283g = init.getInt("alarmmsgenable");
                                c = C1568q.m6981a(this.f5254a, this.f5284h, this.f5283g);
                                c1506f.mo13983a(c1512k);
                                C1425a.m6442c(f5278b, "Alarm Message Json setMsg MsgID = " + this.f5284h + "  Enable  = " + this.f5283g);
                                c1508h.mo13991a(c < 0 ? 3 : 0);
                            }
                        }
                        if (!init.isNull("sendtime")) {
                            this.f5280d = init.getLong("sendtime");
                            c1512k.mo13996a(this.f5280d);
                        }
                        if (!init.isNull("showtime")) {
                            this.f5281e = init.getLong("showtime");
                            c1512k.mo14002b(this.f5281e);
                        }
                        if (!init.isNull("expiretime")) {
                            this.f5282f = init.getLong("expiretime");
                            c1512k.mo14004c(this.f5282f);
                        }
                        C1425a.m6442c(f5278b, "Alarm Message  sendtime = " + this.f5280d + "   showtime = " + this.f5281e + "  expiretime =  " + this.f5282f + "  mAlarmMsgId = " + this.f5284h + " msgIDEnable =  " + this.f5283g + "  msgid = " + c1512k.mo14009g());
                    } catch (Exception e) {
                        C1425a.m6440a(f5278b, e);
                    }
                } else {
                    c1512k.mo13998a(false);
                    C1425a.m6442c(f5278b, "Alarm Message Json = setIsAlarm false and message is not alarm Type!");
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                if (c1546j != null) {
                    c1546j.mo14066a();
                }
                c1506f.mo13983a(c1512k);
                this.f5285i = this.f5285i > (short) 0 ? this.f5285i : (short) 0;
                int i = this.f5285i + 204;
                c = bArr.length - i;
                if (c <= 0) {
                    c = 0;
                }
                byte[] bArr4 = new byte[c];
                System.arraycopy(bArr, i + this.f5285i, bArr4, 0, c);
                C1425a.m6441b(f5278b, "New MSG: " + c1512k.toString());
                C1578v.m7095b("New MSG: " + c1512k.toString(), this.f5254a);
                if (C1568q.m7010e(this.f5254a, c1512k.mo14009g())) {
                    C1498m a = C1498m.m6780a(c1512k.mo14010h());
                    C1481d a2 = new C1497l(this.f5254a).mo13969a(a);
                    if (a2 != null) {
                        c1508h = (C1498m.MSG_TYPE_ALARM_NOTIFICATION.mo13970a() == c1512k.mo14010h() || C1498m.MSG_TYPE_ALARM_MESSAGE.mo13970a() == c1512k.mo14010h() || C1498m.MSG_TYPE_ALARM_AD_NOTIFICATION.mo13970a() == c1512k.mo14010h()) ? a2.mo13966a(c1512k, bArr4) : a2.mo13967a(c1512k.mo14007e(), c1512k.mo14009g(), c1512k.mo14010h(), c1512k.mo14011i(), bArr4);
                    } else {
                        c1508h.mo13991a(2);
                    }
                    C1425a.m6441b(f5278b, "push message handle messageType = " + a + " msgId: " + c1512k.mo14008f() + " result: " + c1508h.mo13990a());
                    int i2 = C1449n.f5116a;
                    if (C1334d.m6039a(this.f5254a, c1512k.mo14007e()).mo13603a() == C1333c.LIGHT_APP_CLIENT_NEW) {
                        i2 = C1449n.f5117b;
                    }
                    C1451p.m6590a(this.f5254a, c1512k.mo14007e(), c1512k.mo14009g(), c1512k.mo14010h(), bArr4, c1508h.mo13990a(), i2);
                } else {
                    C1426b.m6445a(f5278b, "Message ID(" + c1512k.mo14009g() + ") received duplicated, ack success to server directly.", this.f5279c);
                    C1451p.m6590a(this.f5254a, c1512k.mo14007e(), c1512k.mo14009g(), c1512k.mo14010h(), bArr4, 4, C1449n.f5116a);
                    c1508h.mo13991a(4);
                }
            } catch (IOException e2) {
                C1426b.m6447b(f5278b, "error : " + e2.getMessage(), this.f5279c);
            }
        }
        return c1508h;
    }
}
