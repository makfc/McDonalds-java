package com.amap.api.services.core;

import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;

/* compiled from: LogUpdateRequest */
/* renamed from: com.amap.api.services.core.bg */
public class C1108bg extends C1075bs {
    /* renamed from: a */
    private byte[] f3728a;

    public C1108bg(byte[] bArr) {
        this.f3728a = (byte[]) bArr.clone();
    }

    /* renamed from: f */
    private String m4879f() {
        byte[] bytes = C1091at.f3679a.getBytes();
        byte[] bArr = new byte[(bytes.length + 50)];
        System.arraycopy(this.f3728a, 0, bArr, 0, 50);
        System.arraycopy(bytes, 0, bArr, 50, bytes.length);
        return C1077aa.m4683a(bArr);
    }

    /* renamed from: d_ */
    public Map<String, String> mo11973d_() {
        HashMap hashMap = new HashMap();
        hashMap.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/zip");
        hashMap.put(TransactionStateUtil.CONTENT_LENGTH_HEADER, String.valueOf(this.f3728a.length));
        return hashMap;
    }

    /* renamed from: c_ */
    public Map<String, String> mo11972c_() {
        return null;
    }

    /* renamed from: b */
    public String mo11971b() {
        return String.format(C1091at.f3680b, new Object[]{"1", "1", "1", "open", m4879f()});
    }

    /* renamed from: e_ */
    public byte[] mo11975e_() {
        return this.f3728a;
    }

    /* renamed from: e */
    public HttpEntity mo11974e() {
        return null;
    }
}
