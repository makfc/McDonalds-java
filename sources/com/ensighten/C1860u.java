package com.ensighten;

import com.facebook.stetho.common.Utf8Charset;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* renamed from: com.ensighten.u */
public final class C1860u {
    /* renamed from: d */
    private static String f5983d = Utf8Charset.NAME;
    /* renamed from: a */
    protected ConcurrentHashMap<String, String> f5984a = new ConcurrentHashMap();
    /* renamed from: b */
    protected ConcurrentHashMap<String, C1859a> f5985b = new ConcurrentHashMap();
    /* renamed from: c */
    protected ConcurrentHashMap<String, ArrayList<String>> f5986c = new ConcurrentHashMap();

    /* renamed from: com.ensighten.u$a */
    static class C1859a {
        /* renamed from: a */
        public InputStream f5980a;
        /* renamed from: b */
        public String f5981b;
        /* renamed from: c */
        public String f5982c;

        public C1859a(InputStream inputStream, String str, String str2) {
            this.f5980a = inputStream;
            this.f5981b = str;
            this.f5982c = str2;
        }

        /* renamed from: a */
        public final String mo15516a() {
            if (this.f5981b != null) {
                return this.f5981b;
            }
            return "nofilename";
        }
    }

    /* renamed from: a */
    public final void mo15519a(String str, String str2) {
        if (str2 != null) {
            this.f5984a.put(str, str2);
        }
    }

    /* renamed from: a */
    public final void mo15518a(String str, InputStream inputStream, String str2, String str3) {
        if (str != null && inputStream != null) {
            this.f5985b.put(str, new C1859a(inputStream, str2, null));
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.f5984a.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        for (Entry entry2 : this.f5985b.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILE");
        }
        for (Entry entry22 : this.f5986c.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            ArrayList arrayList = (ArrayList) entry22.getValue();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < arrayList.size()) {
                    if (i2 != 0) {
                        stringBuilder.append("&");
                    }
                    stringBuilder.append((String) entry22.getKey());
                    stringBuilder.append("=");
                    stringBuilder.append((String) arrayList.get(i2));
                    i = i2 + 1;
                }
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public final HttpEntity mo15517a() {
        if (this.f5985b.isEmpty()) {
            try {
                return new UrlEncodedFormEntity(m7421c(), f5983d);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        Entry entry;
        Iterator it;
        C1863x c1863x = new C1863x();
        for (Entry entry2 : this.f5984a.entrySet()) {
            c1863x.mo15526a((String) entry2.getKey(), (String) entry2.getValue());
        }
        for (Entry entry22 : this.f5986c.entrySet()) {
            it = ((ArrayList) entry22.getValue()).iterator();
            while (it.hasNext()) {
                c1863x.mo15526a((String) entry22.getKey(), (String) it.next());
            }
        }
        int i = 0;
        int size = this.f5985b.entrySet().size() - 1;
        it = this.f5985b.entrySet().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return c1863x;
            }
            entry22 = (Entry) it.next();
            C1859a c1859a = (C1859a) entry22.getValue();
            if (c1859a.f5980a != null) {
                if (i2 == size) {
                }
                if (c1859a.f5982c != null) {
                    c1863x.mo15527a((String) entry22.getKey(), c1859a.mo15516a(), c1859a.f5980a, c1859a.f5982c);
                } else {
                    c1863x.mo15527a((String) entry22.getKey(), c1859a.mo15516a(), c1859a.f5980a, "application/octet-stream");
                }
            }
            i = i2 + 1;
        }
    }

    /* renamed from: c */
    private List<BasicNameValuePair> m7421c() {
        LinkedList linkedList = new LinkedList();
        for (Entry entry : this.f5984a.entrySet()) {
            linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        for (Entry entry2 : this.f5986c.entrySet()) {
            Iterator it = ((ArrayList) entry2.getValue()).iterator();
            while (it.hasNext()) {
                linkedList.add(new BasicNameValuePair((String) entry2.getKey(), (String) it.next()));
            }
        }
        return linkedList;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final String mo15520b() {
        return URLEncodedUtils.format(m7421c(), f5983d);
    }
}
