package com.baidu.android.pushservice.message;

import android.content.Context;
import com.baidu.android.pushservice.p036h.C1425a;

/* renamed from: com.baidu.android.pushservice.message.a */
public class C1501a {
    /* renamed from: a */
    private static final String f5237a = C1501a.class.getSimpleName();
    /* renamed from: b */
    private Context f5238b;

    public C1501a(Context context) {
        this.f5238b = context;
    }

    /* renamed from: a */
    public C1503d mo13971a(C1509i c1509i) {
        switch (c1509i) {
            case MSG_ID_HANDSHAKE:
                return new C1504c(this.f5238b);
            case MSG_ID_HEARTBEAT_SERVER:
            case MSG_ID_TINY_HEARTBEAT_SERVER:
                return new C1513l(this.f5238b);
            case MSG_ID_HEARTBEAT_CLIENT:
                C1425a.m6441b(f5237a, "handleMessage MSG_ID_HEARTBEAT_CLIENT");
                return null;
            case MSG_ID_TINY_HEARTBEAT_CLIENT:
                C1425a.m6441b(f5237a, "handleMessage MSG_ID_TIMY_HEARTBEAT_CLIENT");
                return null;
            case MSG_ID_PUSH_MSG:
                return new C1511j(this.f5238b);
            default:
                C1425a.m6444e(f5237a, "handleMessage invalid messageType");
                return null;
        }
    }
}
