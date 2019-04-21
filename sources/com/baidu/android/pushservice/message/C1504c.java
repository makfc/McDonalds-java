package com.baidu.android.pushservice.message;

import android.content.Context;
import com.baidu.android.pushservice.C1475k;
import com.baidu.android.pushservice.p033e.C1370b;
import com.baidu.android.pushservice.p036h.C1425a;
import com.baidu.android.pushservice.util.C1578v;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.message.c */
public class C1504c extends C1503d {
    /* renamed from: b */
    private static final String f5255b = C1504c.class.getSimpleName();

    public C1504c(Context context) {
        super(context);
    }

    /* renamed from: a */
    public C1508h mo13975a(C1506f c1506f) {
        JSONObject init;
        int i = -1;
        C1508h c1508h = new C1508h();
        String str = new String(c1506f.f5260c);
        C1425a.m6441b(f5255b, "handleMessage MSG_ID_HANDSHAKE : " + str);
        try {
            init = JSONObjectInstrumentation.init(str);
        } catch (JSONException e) {
            C1425a.m6444e(f5255b, "error : " + e.getMessage());
            init = null;
        }
        if (init != null) {
            i = init.optInt("ret", -1);
        }
        C1425a.m6441b(f5255b, "handleMessage MSG_ID_HANDSHAKE : result = " + i);
        if (i == 0) {
            C1370b.m6203a(this.f5254a);
        } else if (i == 5003) {
            C1370b.m6203a(this.f5254a);
        } else if (i == 2002) {
            C1475k.m6721a(this.f5254a).mo13948a(null, null);
            C1578v.m7121h(this.f5254a);
        }
        c1508h.mo13991a(i);
        return c1508h;
    }
}
