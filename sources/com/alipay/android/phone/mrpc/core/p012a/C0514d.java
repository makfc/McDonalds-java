package com.alipay.android.phone.mrpc.core.p012a;

import com.alipay.android.phone.mrpc.core.RpcException;
import com.alipay.p009a.p010a.C0501e;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.lang.reflect.Type;
import org.json.JSONObject;

/* renamed from: com.alipay.android.phone.mrpc.core.a.d */
public final class C0514d extends C0511a {
    public C0514d(Type type, byte[] bArr) {
        super(type, bArr);
    }

    /* renamed from: a */
    public final Object mo7863a() {
        try {
            String str = new String(this.f316b);
            new StringBuilder("threadid = ").append(Thread.currentThread().getId()).append("; rpc response:  ").append(str);
            JSONObject init = JSONObjectInstrumentation.init(str);
            int i = init.getInt("resultStatus");
            if (i == 1000) {
                return this.f315a == String.class ? init.optString("result") : C0501e.m524a(init.optString("result"), this.f315a);
            } else {
                throw new RpcException(Integer.valueOf(i), init.optString("tips"));
            }
        } catch (Exception e) {
            throw new RpcException(Integer.valueOf(10), new StringBuilder("response  =").append(new String(this.f316b)).append(":").append(e).toString() == null ? "" : e.getMessage());
        }
    }
}
