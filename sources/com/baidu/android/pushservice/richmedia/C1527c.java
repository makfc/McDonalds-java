package com.baidu.android.pushservice.richmedia;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.util.LangUtils;

/* renamed from: com.baidu.android.pushservice.richmedia.c */
public class C1527c {
    /* renamed from: a */
    public String f5346a;
    /* renamed from: b */
    public String f5347b;
    /* renamed from: c */
    public String f5348c;
    /* renamed from: d */
    public String f5349d;
    /* renamed from: e */
    protected C1526a f5350e;
    /* renamed from: f */
    public HashMap<String, String> f5351f = new HashMap();
    /* renamed from: g */
    public boolean f5352g = true;
    /* renamed from: h */
    public boolean f5353h = false;
    /* renamed from: i */
    private String f5354i;
    /* renamed from: j */
    private Map<String, String> f5355j = new HashMap();
    /* renamed from: k */
    private String f5356k;
    /* renamed from: l */
    private String f5357l;
    /* renamed from: m */
    private String f5358m;

    /* renamed from: com.baidu.android.pushservice.richmedia.c$a */
    public enum C1526a {
        REQ_TYPE_GET_ZIP
    }

    /* renamed from: a */
    public C1526a mo14045a() {
        return this.f5350e;
    }

    /* renamed from: a */
    public void mo14046a(C1526a c1526a) {
        this.f5350e = c1526a;
    }

    /* renamed from: a */
    public void mo14047a(String str) {
        this.f5356k = str;
    }

    /* renamed from: b */
    public String mo14048b() {
        return this.f5356k == null ? "GET" : this.f5356k;
    }

    /* renamed from: b */
    public void mo14049b(String str) {
        this.f5357l = str;
    }

    /* renamed from: c */
    public String mo14050c() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.f5357l != null) {
            stringBuffer.append(this.f5357l);
        }
        this.f5357l = stringBuffer.toString();
        return this.f5357l.endsWith("&") ? this.f5357l.substring(0, this.f5357l.length() - 1) : this.f5357l;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1527c)) {
            return false;
        }
        C1527c c1527c = (C1527c) obj;
        if (this.f5354i != null ? !this.f5354i.equals(c1527c.f5354i) : c1527c.f5354i != null) {
            if (this.f5350e != null ? !this.f5350e.equals(c1527c.f5350e) : c1527c.f5350e != null) {
                if (this.f5355j != null ? !this.f5355j.equals(c1527c.f5355j) : c1527c.f5355j != null) {
                    if (this.f5356k != null ? !this.f5356k.equals(c1527c.f5356k) : c1527c.f5356k != null) {
                        if (this.f5357l != null ? !this.f5357l.equals(c1527c.f5357l) : c1527c.f5357l != null) {
                            if (this.f5358m != null ? !this.f5358m.equals(c1527c.f5358m) : c1527c.f5358m != null) {
                                if (this.f5351f != null ? !this.f5351f.equals(c1527c.f5351f) : c1527c.f5351f != null) {
                                    if (this.f5353h == c1527c.f5353h) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        return LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(LangUtils.hashCode(17, this.f5354i), this.f5350e), this.f5355j), this.f5356k), this.f5357l), this.f5358m), this.f5351f), this.f5353h);
    }
}
