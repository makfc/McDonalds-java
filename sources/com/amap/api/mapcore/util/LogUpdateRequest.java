package com.amap.api.mapcore.util;

import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.amap.api.mapcore.util.ed */
public class LogUpdateRequest extends Request {
    /* renamed from: a */
    private byte[] f1858a;
    /* renamed from: b */
    private String f1859b = "1";

    public LogUpdateRequest(byte[] bArr) {
        this.f1858a = (byte[]) bArr.clone();
    }

    public LogUpdateRequest(byte[] bArr, String str) {
        this.f1858a = (byte[]) bArr.clone();
        this.f1859b = str;
    }

    /* renamed from: e */
    private String m2553e() {
        byte[] a = Utils.m2515a(ConstConfig.f1842a);
        byte[] bArr = new byte[(a.length + 50)];
        System.arraycopy(this.f1858a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        return C0822ds.m2462a(bArr);
    }

    /* renamed from: c */
    public Map<String, String> mo8907c() {
        HashMap hashMap = new HashMap();
        hashMap.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/zip");
        hashMap.put(TransactionStateUtil.CONTENT_LENGTH_HEADER, String.valueOf(this.f1858a.length));
        return hashMap;
    }

    /* renamed from: b */
    public Map<String, String> mo8905b() {
        return null;
    }

    /* renamed from: a */
    public String mo8901a() {
        return String.format(ConstConfig.f1843b, new Object[]{"1", this.f1859b, "1", "open", m2553e()});
    }

    /* renamed from: a_ */
    public byte[] mo8904a_() {
        return this.f1858a;
    }
}
