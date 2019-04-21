package org.json.alipay;

import com.facebook.internal.ServerProtocol;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* renamed from: org.json.alipay.c */
public final class C4637c {
    /* renamed from: a */
    private int f7868a;
    /* renamed from: b */
    private Reader f7869b;
    /* renamed from: c */
    private char f7870c;
    /* renamed from: d */
    private boolean f7871d;

    private C4637c(Reader reader) {
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader);
        }
        this.f7869b = reader;
        this.f7871d = false;
        this.f7868a = 0;
    }

    public C4637c(String str) {
        this(new StringReader(str));
    }

    /* renamed from: a */
    private String m8655a(int i) {
        int i2 = 0;
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        if (this.f7871d) {
            this.f7871d = false;
            cArr[0] = this.f7870c;
            i2 = 1;
        }
        while (i2 < i) {
            try {
                int read = this.f7869b.read(cArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        this.f7868a += i2;
        if (i2 < i) {
            throw mo34961a("Substring bounds error");
        }
        this.f7870c = cArr[i - 1];
        return new String(cArr);
    }

    /* renamed from: a */
    public final JSONException mo34961a(String str) {
        return new JSONException(str + toString());
    }

    /* renamed from: a */
    public final void mo34962a() {
        if (this.f7871d || this.f7868a <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.f7868a--;
        this.f7871d = true;
    }

    /* renamed from: b */
    public final char mo34963b() {
        if (this.f7871d) {
            this.f7871d = false;
            if (this.f7870c != 0) {
                this.f7868a++;
            }
            return this.f7870c;
        }
        try {
            int read = this.f7869b.read();
            if (read <= 0) {
                this.f7870c = 0;
                return 0;
            }
            this.f7868a++;
            this.f7870c = (char) read;
            return this.f7870c;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    /* JADX WARNING: Missing block: B:7:0x001b, code skipped:
            if (r1 == 10) goto L_0x0006;
     */
    /* JADX WARNING: Missing block: B:8:0x001d, code skipped:
            if (r1 == 13) goto L_0x0006;
     */
    /* JADX WARNING: Missing block: B:12:0x0024, code skipped:
            if (r1 != '*') goto L_0x002f;
     */
    /* JADX WARNING: Missing block: B:14:0x002a, code skipped:
            if (mo34963b() == '/') goto L_0x0006;
     */
    /* JADX WARNING: Missing block: B:15:0x002c, code skipped:
            mo34962a();
     */
    /* JADX WARNING: Missing block: B:17:0x0033, code skipped:
            if (r1 != 0) goto L_0x0022;
     */
    /* JADX WARNING: Missing block: B:19:0x003b, code skipped:
            throw mo34961a("Unclosed comment");
     */
    /* renamed from: c */
    public final char mo34964c() {
        /*
        r5 = this;
        r4 = 13;
        r3 = 10;
        r0 = 47;
    L_0x0006:
        r1 = r5.mo34963b();
        if (r1 != r0) goto L_0x003c;
    L_0x000c:
        r1 = r5.mo34963b();
        switch(r1) {
            case 42: goto L_0x002f;
            case 47: goto L_0x0017;
            default: goto L_0x0013;
        };
    L_0x0013:
        r5.mo34962a();
    L_0x0016:
        return r0;
    L_0x0017:
        r1 = r5.mo34963b();
        if (r1 == r3) goto L_0x0006;
    L_0x001d:
        if (r1 == r4) goto L_0x0006;
    L_0x001f:
        if (r1 != 0) goto L_0x0017;
    L_0x0021:
        goto L_0x0006;
    L_0x0022:
        r2 = 42;
        if (r1 != r2) goto L_0x002f;
    L_0x0026:
        r1 = r5.mo34963b();
        if (r1 == r0) goto L_0x0006;
    L_0x002c:
        r5.mo34962a();
    L_0x002f:
        r1 = r5.mo34963b();
        if (r1 != 0) goto L_0x0022;
    L_0x0035:
        r0 = "Unclosed comment";
        r0 = r5.mo34961a(r0);
        throw r0;
    L_0x003c:
        r2 = 35;
        if (r1 != r2) goto L_0x004b;
    L_0x0040:
        r1 = r5.mo34963b();
        if (r1 == r3) goto L_0x0006;
    L_0x0046:
        if (r1 == r4) goto L_0x0006;
    L_0x0048:
        if (r1 != 0) goto L_0x0040;
    L_0x004a:
        goto L_0x0006;
    L_0x004b:
        if (r1 == 0) goto L_0x0051;
    L_0x004d:
        r2 = 32;
        if (r1 <= r2) goto L_0x0006;
    L_0x0051:
        r0 = r1;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.C4637c.mo34964c():char");
    }

    /* renamed from: d */
    public final Object mo34965d() {
        char c = mo34964c();
        switch (c) {
            case '\"':
            case '\'':
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    char b = mo34963b();
                    switch (b) {
                        case 0:
                        case 10:
                        case 13:
                            throw mo34961a("Unterminated string");
                        case '\\':
                            b = mo34963b();
                            switch (b) {
                                case 'b':
                                    stringBuffer.append(8);
                                    break;
                                case 'f':
                                    stringBuffer.append(12);
                                    break;
                                case 'n':
                                    stringBuffer.append(10);
                                    break;
                                case 'r':
                                    stringBuffer.append(13);
                                    break;
                                case 't':
                                    stringBuffer.append(9);
                                    break;
                                case 'u':
                                    stringBuffer.append((char) Integer.parseInt(m8655a(4), 16));
                                    break;
                                case 'x':
                                    stringBuffer.append((char) Integer.parseInt(m8655a(2), 16));
                                    break;
                                default:
                                    stringBuffer.append(b);
                                    break;
                            }
                        default:
                            if (b != c) {
                                stringBuffer.append(b);
                                break;
                            }
                            return stringBuffer.toString();
                    }
                }
            case '(':
            case '[':
                mo34962a();
                return new C4634a(this);
            case '{':
                mo34962a();
                return new C4636b(this);
            default:
                StringBuffer stringBuffer2 = new StringBuffer();
                int i = c;
                while (i >= 32 && ",:]}/\\\"[{;=#".indexOf(i) < 0) {
                    stringBuffer2.append(i);
                    i = mo34963b();
                }
                mo34962a();
                String trim = stringBuffer2.toString().trim();
                if (trim.equals("")) {
                    throw mo34961a("Missing value");
                } else if (trim.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                    return Boolean.TRUE;
                } else {
                    if (trim.equalsIgnoreCase("false")) {
                        return Boolean.FALSE;
                    }
                    if (trim.equalsIgnoreCase(SafeJsonPrimitive.NULL_STRING)) {
                        return C4636b.f7866a;
                    }
                    if ((c < '0' || c > '9') && c != '.' && c != '-' && c != '+') {
                        return trim;
                    }
                    if (c == '0') {
                        if (trim.length() <= 2 || !(trim.charAt(1) == 'x' || trim.charAt(1) == 'X')) {
                            try {
                                return new Integer(Integer.parseInt(trim, 8));
                            } catch (Exception e) {
                            }
                        } else {
                            try {
                                return new Integer(Integer.parseInt(trim.substring(2), 16));
                            } catch (Exception e2) {
                            }
                        }
                    }
                    try {
                        return new Integer(trim);
                    } catch (Exception e3) {
                        try {
                            return new Long(trim);
                        } catch (Exception e4) {
                            try {
                                return new Double(trim);
                            } catch (Exception e5) {
                                return trim;
                            }
                        }
                    }
                }
        }
    }

    public final String toString() {
        return " at character " + this.f7868a;
    }
}
