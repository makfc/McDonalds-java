package com.amap.api.services.core;

import android.content.Context;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Parameters;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;

/* compiled from: ProtocalHandler */
/* renamed from: com.amap.api.services.core.q */
public abstract class C1076q<T, V> extends C1075bs {
    /* renamed from: a */
    protected T f3612a;
    /* renamed from: b */
    protected int f3613b = 1;
    /* renamed from: c */
    protected String f3614c = "";
    /* renamed from: d */
    protected Context f3615d;
    /* renamed from: h */
    private int f3616h = 1;

    /* renamed from: a_ */
    public abstract String mo11977a_();

    /* renamed from: b */
    public abstract V mo11978b(String str) throws AMapException;

    public C1076q(Context context, T t) {
        m4665a(context, t);
    }

    /* renamed from: a */
    private void m4665a(Context context, T t) {
        this.f3615d = context;
        this.f3612a = t;
        this.f3613b = 1;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public V mo11976a(byte[] bArr) throws AMapException {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Exception e) {
            C1128d.m4975a(e, "ProtocalHandler", "loadData");
            str = null;
        }
        if (str == null || str.equals("")) {
            return null;
        }
        C1128d.m4977b(str);
        return mo11978b(str);
    }

    /* renamed from: g */
    public V mo11981g() throws AMapException {
        if (this.f3612a != null) {
            return mo12060f();
        }
        return null;
    }

    /* renamed from: f */
    private V mo12060f() throws AMapException {
        int i = 0;
        V v = null;
        while (i < this.f3613b) {
            try {
                byte[] a;
                int protocol = ServiceSettings.getInstance().getProtocol();
                C1123br a2 = C1123br.m4953a(false);
                mo11970a(C1078ab.m4690a(this.f3615d));
                if (protocol == 1) {
                    a = a2.mo12094a((C1075bs) this);
                } else if (protocol == 2) {
                    a = a2.mo12096c(this);
                } else {
                    a = null;
                }
                v = m4666b(a);
                i = this.f3613b;
            } catch (AMapException e) {
                C1128d.m4975a(e, "ProtocalHandler", "getDataMayThrowAMapException");
                i++;
                if (i >= this.f3613b) {
                    throw new AMapException(e.getErrorMessage());
                }
            } catch (C1133u e2) {
                C1128d.m4975a(e2, "ProtocalHandler", "getDataMayThrowAMapCoreException");
                i++;
                if (i < this.f3613b) {
                    try {
                        Thread.sleep((long) (this.f3616h * 1000));
                    } catch (InterruptedException e3) {
                        C1128d.m4975a(e2, "ProtocalHandler", "getDataMayThrowInterruptedException");
                        throw new AMapException(e2.getMessage());
                    }
                }
                mo11982h();
                throw new AMapException(e2.mo12115a());
            }
        }
        return v;
    }

    /* renamed from: e */
    public HttpEntity mo11974e() {
        try {
            String a_ = mo11977a_();
            String a = mo11983a(a_);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(a_);
            a_ = C1136x.m5090a();
            stringBuffer.append("&ts=" + a_);
            stringBuffer.append("&scode=" + C1136x.m5094a(this.f3615d, a_, a));
            return new StringEntity(stringBuffer.toString());
        } catch (UnsupportedEncodingException e) {
            C1128d.m4975a(e, "ProtocalHandler", "getEntity");
            return null;
        }
    }

    /* renamed from: c_ */
    public Map<String, String> mo11972c_() {
        return null;
    }

    /* renamed from: d_ */
    public Map<String, String> mo11973d_() {
        HashMap hashMap = new HashMap();
        hashMap.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/x-www-form-urlencoded");
        hashMap.put("Accept-Encoding", "gzip");
        hashMap.put("User-Agent", "AMAP SDK Android Search 2.4.0");
        hashMap.put("X-INFO", C1136x.m5093a(this.f3615d, ManifestConfig.f3792a, null));
        hashMap.put("ia", "1");
        hashMap.put("ec", "1");
        hashMap.put(Parameters.API_KEY, C1134v.m5087f(this.f3615d));
        return hashMap;
    }

    /* renamed from: b */
    private V m4666b(byte[] bArr) throws AMapException {
        return mo11976a(bArr);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: h */
    public V mo11982h() {
        return null;
    }

    /* renamed from: a */
    private String mo11983a(String str) {
        String[] split = str.split("&");
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String d : split) {
            stringBuffer.append(mo11980d(d));
            stringBuffer.append("&");
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.length() > 1) {
            return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
        }
        return str;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: c */
    public String mo11979c(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            C1128d.m4975a(e, "ProtocalHandler", "strEncoderUnsupportedEncodingException");
        } catch (Exception e2) {
            C1128d.m4975a(e2, "ProtocalHandler", "strEncoderException");
        }
        return new String();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: d */
    public String mo11980d(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            C1128d.m4975a(e, "ProtocalHandler", "strReEncoder");
        } catch (Exception e2) {
            C1128d.m4975a(e2, "ProtocalHandler", "strReEncoderException");
        }
        return new String();
    }
}
