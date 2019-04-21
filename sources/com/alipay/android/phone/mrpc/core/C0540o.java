package com.alipay.android.phone.mrpc.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

/* renamed from: com.alipay.android.phone.mrpc.core.o */
public final class C0540o extends C0539t {
    /* renamed from: b */
    private String f367b;
    /* renamed from: c */
    private byte[] f368c;
    /* renamed from: d */
    private String f369d = "application/x-www-form-urlencoded";
    /* renamed from: e */
    private ArrayList<Header> f370e = new ArrayList();
    /* renamed from: f */
    private Map<String, String> f371f = new HashMap();
    /* renamed from: g */
    private boolean f372g;

    public C0540o(String str) {
        this.f367b = str;
    }

    /* renamed from: a */
    public final String mo7906a() {
        return this.f367b;
    }

    /* renamed from: a */
    public final void mo7907a(String str) {
        this.f369d = str;
    }

    /* renamed from: a */
    public final void mo7908a(String str, String str2) {
        if (this.f371f == null) {
            this.f371f = new HashMap();
        }
        this.f371f.put(str, str2);
    }

    /* renamed from: a */
    public final void mo7909a(Header header) {
        this.f370e.add(header);
    }

    /* renamed from: a */
    public final void mo7910a(boolean z) {
        this.f372g = z;
    }

    /* renamed from: a */
    public final void mo7911a(byte[] bArr) {
        this.f368c = bArr;
    }

    /* renamed from: b */
    public final String mo7912b(String str) {
        return this.f371f == null ? null : (String) this.f371f.get(str);
    }

    /* renamed from: b */
    public final byte[] mo7913b() {
        return this.f368c;
    }

    /* renamed from: c */
    public final String mo7914c() {
        return this.f369d;
    }

    /* renamed from: d */
    public final ArrayList<Header> mo7915d() {
        return this.f370e;
    }

    /* renamed from: e */
    public final boolean mo7916e() {
        return this.f372g;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        C0540o c0540o = (C0540o) obj;
        if (this.f368c == null) {
            if (c0540o.f368c != null) {
                return false;
            }
        } else if (!this.f368c.equals(c0540o.f368c)) {
            return false;
        }
        return this.f367b == null ? c0540o.f367b == null : this.f367b.equals(c0540o.f367b);
    }

    public final int hashCode() {
        int i = 1;
        if (this.f371f != null && this.f371f.containsKey("id")) {
            i = ((String) this.f371f.get("id")).hashCode() + 31;
        }
        return (this.f367b == null ? 0 : this.f367b.hashCode()) + (i * 31);
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", new Object[]{this.f367b, this.f370e});
    }
}
