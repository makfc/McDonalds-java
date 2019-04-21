package org.json.alipay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/* renamed from: org.json.alipay.a */
public class C4634a {
    /* renamed from: a */
    private ArrayList f7865a;

    public C4634a() {
        this.f7865a = new ArrayList();
    }

    public C4634a(Object obj) {
        this();
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                this.f7865a.add(Array.get(obj, i));
            }
            return;
        }
        throw new JSONException("JSONArray initial value should be a string or collection or array.");
    }

    public C4634a(String str) {
        this(new C4637c(str));
    }

    public C4634a(Collection collection) {
        this.f7865a = collection == null ? new ArrayList() : new ArrayList(collection);
    }

    public C4634a(C4637c c4637c) {
        this();
        char c = c4637c.mo34964c();
        if (c == '[') {
            c = ']';
        } else if (c == '(') {
            c = ')';
        } else {
            throw c4637c.mo34961a("A JSONArray text must start with '['");
        }
        if (c4637c.mo34964c() != ']') {
            c4637c.mo34962a();
            while (true) {
                if (c4637c.mo34964c() == ',') {
                    c4637c.mo34962a();
                    this.f7865a.add(null);
                } else {
                    c4637c.mo34962a();
                    this.f7865a.add(c4637c.mo34965d());
                }
                char c2 = c4637c.mo34964c();
                switch (c2) {
                    case ')':
                    case ']':
                        if (c != c2) {
                            throw c4637c.mo34961a("Expected a '" + new Character(c) + "'");
                        }
                        return;
                    case ',':
                    case ';':
                        if (c4637c.mo34964c() != ']') {
                            c4637c.mo34962a();
                        } else {
                            return;
                        }
                    default:
                        throw c4637c.mo34961a("Expected a ',' or ']'");
                }
            }
        }
    }

    /* renamed from: a */
    private String m8646a(String str) {
        int size = this.f7865a.size();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuffer.append(str);
            }
            stringBuffer.append(C4636b.m8649a(this.f7865a.get(i)));
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public final int mo34951a() {
        return this.f7865a.size();
    }

    /* renamed from: a */
    public final Object mo34952a(int i) {
        Object obj = (i < 0 || i >= this.f7865a.size()) ? null : this.f7865a.get(i);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public String toString() {
        try {
            return "[" + m8646a(",") + ']';
        } catch (Exception e) {
            return null;
        }
    }
}
