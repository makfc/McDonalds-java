package com.alipay.android.phone.mrpc.core.p012a;

import com.alipay.android.phone.mrpc.core.RpcException;
import com.alipay.p009a.p010a.C0502f;
import java.util.ArrayList;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.alipay.android.phone.mrpc.core.a.e */
public final class C0515e extends C0513b {
    /* renamed from: c */
    private int f319c;
    /* renamed from: d */
    private Object f320d;

    public C0515e(int i, String str, Object obj) {
        super(str, obj);
        this.f319c = i;
    }

    /* renamed from: a */
    public final void mo7864a(Object obj) {
        this.f320d = obj;
    }

    /* renamed from: a */
    public final byte[] mo7865a() {
        try {
            ArrayList arrayList = new ArrayList();
            if (this.f320d != null) {
                arrayList.add(new BasicNameValuePair("extParam", C0502f.m525a(this.f320d)));
            }
            arrayList.add(new BasicNameValuePair("operationType", this.f317a));
            arrayList.add(new BasicNameValuePair("id", this.f319c));
            new StringBuilder("mParams is:").append(this.f318b);
            arrayList.add(new BasicNameValuePair("requestData", this.f318b == null ? "[]" : C0502f.m525a(this.f318b)));
            return URLEncodedUtils.format(arrayList, "utf-8").getBytes();
        } catch (Exception e) {
            Exception exception = e;
            throw new RpcException(Integer.valueOf(9), new StringBuilder("request  =").append(this.f318b).append(":").append(exception).toString() == null ? "" : exception.getMessage(), exception);
        }
    }
}
