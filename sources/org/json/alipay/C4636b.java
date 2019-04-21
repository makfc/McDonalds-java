package org.json.alipay;

import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: org.json.alipay.b */
public class C4636b {
    /* renamed from: a */
    public static final Object f7866a = new C4635a();
    /* renamed from: b */
    private Map f7867b;

    /* renamed from: org.json.alipay.b$a */
    private static final class C4635a {
        private C4635a() {
        }

        /* synthetic */ C4635a(byte b) {
            this();
        }

        /* Access modifiers changed, original: protected|final */
        public final Object clone() {
            return this;
        }

        public final boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public final String toString() {
            return SafeJsonPrimitive.NULL_STRING;
        }
    }

    public C4636b() {
        this.f7867b = new HashMap();
    }

    public C4636b(String str) {
        this(new C4637c(str));
    }

    public C4636b(Map map) {
        if (map == null) {
            map = new HashMap();
        }
        this.f7867b = map;
    }

    public C4636b(C4637c c4637c) {
        this();
        if (c4637c.mo34964c() != '{') {
            throw c4637c.mo34961a("A JSONObject text must begin with '{'");
        }
        while (true) {
            switch (c4637c.mo34964c()) {
                case 0:
                    throw c4637c.mo34961a("A JSONObject text must end with '}'");
                case '}':
                    return;
                default:
                    c4637c.mo34962a();
                    String obj = c4637c.mo34965d().toString();
                    char c = c4637c.mo34964c();
                    if (c == '=') {
                        if (c4637c.mo34963b() != '>') {
                            c4637c.mo34962a();
                        }
                    } else if (c != ':') {
                        throw c4637c.mo34961a("Expected a ':' after a key");
                    }
                    Object d = c4637c.mo34965d();
                    if (obj == null) {
                        throw new JSONException("Null key.");
                    }
                    if (d != null) {
                        C4636b.m8650b(d);
                        this.f7867b.put(obj, d);
                    } else {
                        this.f7867b.remove(obj);
                    }
                    switch (c4637c.mo34964c()) {
                        case ',':
                        case ';':
                            if (c4637c.mo34964c() != '}') {
                                c4637c.mo34962a();
                            } else {
                                return;
                            }
                        case '}':
                            return;
                        default:
                            throw c4637c.mo34961a("Expected a ',' or '}'");
                    }
            }
        }
    }

    /* renamed from: a */
    static String m8649a(Object obj) {
        if (obj == null || obj.equals(null)) {
            return SafeJsonPrimitive.NULL_STRING;
        }
        if (!(obj instanceof Number)) {
            return ((obj instanceof Boolean) || (obj instanceof C4636b) || (obj instanceof C4634a)) ? obj.toString() : obj instanceof Map ? new C4636b((Map) obj).toString() : obj instanceof Collection ? new C4634a((Collection) obj).toString() : obj.getClass().isArray() ? new C4634a(obj).toString() : C4636b.m8651c(obj.toString());
        } else {
            obj = (Number) obj;
            if (obj == null) {
                throw new JSONException("Null pointer");
            }
            C4636b.m8650b(obj);
            String obj2 = obj.toString();
            if (obj2.indexOf(46) <= 0 || obj2.indexOf(101) >= 0 || obj2.indexOf(69) >= 0) {
                return obj2;
            }
            while (obj2.endsWith("0")) {
                obj2 = obj2.substring(0, obj2.length() - 1);
            }
            return obj2.endsWith(".") ? obj2.substring(0, obj2.length() - 1) : obj2;
        }
    }

    /* renamed from: b */
    private static void m8650b(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Double) {
            if (((Double) obj).isInfinite() || ((Double) obj).isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        } else if (!(obj instanceof Float)) {
        } else {
            if (((Float) obj).isInfinite() || ((Float) obj).isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        }
    }

    /* renamed from: c */
    public static String m8651c(String str) {
        int i = 0;
        if (str == null || str.length() == 0) {
            return "\"\"";
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 4);
        stringBuffer.append('\"');
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case 8:
                    stringBuffer.append("\\b");
                    break;
                case 9:
                    stringBuffer.append("\\t");
                    break;
                case 10:
                    stringBuffer.append("\\n");
                    break;
                case 12:
                    stringBuffer.append("\\f");
                    break;
                case 13:
                    stringBuffer.append("\\r");
                    break;
                case '\"':
                case '\\':
                    stringBuffer.append('\\');
                    stringBuffer.append(charAt);
                    break;
                case '/':
                    if (i2 == 60) {
                        stringBuffer.append('\\');
                    }
                    stringBuffer.append(charAt);
                    break;
                default:
                    if (charAt >= SafeJsonPrimitive.NULL_CHAR && ((charAt < 128 || charAt >= 160) && (charAt < 8192 || charAt >= 8448))) {
                        stringBuffer.append(charAt);
                        break;
                    }
                    String str2 = "000" + Integer.toHexString(charAt);
                    stringBuffer.append("\\u" + str2.substring(str2.length() - 4));
                    break;
                    break;
            }
            i++;
            char i22 = charAt;
        }
        stringBuffer.append('\"');
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public final Object mo34957a(String str) {
        Object obj = str == null ? null : this.f7867b.get(str);
        if (obj != null) {
            return obj;
        }
        throw new JSONException("JSONObject[" + C4636b.m8651c(str) + "] not found.");
    }

    /* renamed from: a */
    public final Iterator mo34958a() {
        return this.f7867b.keySet().iterator();
    }

    /* renamed from: b */
    public final boolean mo34959b(String str) {
        return this.f7867b.containsKey(str);
    }

    public String toString() {
        try {
            Iterator a = mo34958a();
            StringBuffer stringBuffer = new StringBuffer("{");
            while (a.hasNext()) {
                if (stringBuffer.length() > 1) {
                    stringBuffer.append(',');
                }
                Object next = a.next();
                stringBuffer.append(C4636b.m8651c(next.toString()));
                stringBuffer.append(':');
                stringBuffer.append(C4636b.m8649a(this.f7867b.get(next)));
            }
            stringBuffer.append('}');
            return stringBuffer.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
