package com.amap.api.mapcore2d;

import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.util.HashMap;
import java.util.Map;

/* compiled from: LogUpdateRequest */
/* renamed from: com.amap.api.mapcore2d.dc */
public class C0986dc extends C0931eg {
    /* renamed from: a */
    private byte[] f2813a;
    /* renamed from: b */
    private String f2814b = "1";

    public C0986dc(byte[] bArr) {
        this.f2813a = (byte[]) bArr.clone();
    }

    public C0986dc(byte[] bArr, String str) {
        this.f2813a = (byte[]) bArr.clone();
        this.f2814b = str;
    }

    /* renamed from: b */
    private String m4087b() {
        byte[] a = C0978cw.m4050a(C0981cz.f2797a);
        byte[] bArr = new byte[(a.length + 50)];
        System.arraycopy(this.f2813a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        return C0970cs.m3993a(bArr);
    }

    /* renamed from: e */
    public Map<String, String> mo10071e() {
        HashMap hashMap = new HashMap();
        hashMap.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/zip");
        hashMap.put(TransactionStateUtil.CONTENT_LENGTH_HEADER, String.valueOf(this.f2813a.length));
        return hashMap;
    }

    /* renamed from: f */
    public Map<String, String> mo10072f() {
        return null;
    }

    /* renamed from: g */
    public String mo10073g() {
        return String.format(C0981cz.f2798b, new Object[]{"1", this.f2814b, "1", "open", m4087b()});
    }

    /* renamed from: a_ */
    public byte[] mo10069a_() {
        return this.f2813a;
    }
}
