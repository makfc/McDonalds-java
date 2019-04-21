package com.threatmetrix.TrustDefender;

import java.util.HashMap;
import java.util.Locale;

/* renamed from: com.threatmetrix.TrustDefender.n */
class C4540n extends HashMap<String, String> {
    /* renamed from: a */
    private static final String f7804a = C4549w.m8585a(C4540n.class);
    /* renamed from: b */
    private int f7805b = 0;
    /* renamed from: c */
    private HashMap<String, Integer> f7806c = new HashMap();

    C4540n() {
    }

    /* renamed from: a */
    public final int mo34248a() {
        return this.f7805b;
    }

    /* renamed from: a */
    public final void mo34253a(int postValueLengthLimit) {
        this.f7805b = 255;
    }

    /* renamed from: a */
    public final Integer mo34252a(String key) {
        return (Integer) this.f7806c.get(key);
    }

    /* renamed from: a */
    public final C4540n mo34249a(String name, String value) {
        mo34251a(name, value, false);
        return this;
    }

    /* renamed from: a */
    public final C4540n mo34250a(String name, String value, Integer maxLength) {
        this.f7806c.put(name, maxLength);
        mo34251a(name, value, false);
        return this;
    }

    /* renamed from: a */
    public final C4540n mo34251a(String name, String value, boolean foldToLowercase) {
        if (!foldToLowercase || value == null || value.isEmpty()) {
            put(name, value);
        } else {
            put(name, value.toLowerCase(Locale.US));
        }
        return this;
    }

    /* renamed from: b */
    public final String mo34254b() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        for (String key : keySet()) {
            String value = (String) get(key);
            if (!(value == null || value.isEmpty())) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(key);
                Integer length = (Integer) this.f7806c.get(key);
                if (length != null && value.length() > length.intValue()) {
                    value = value.substring(0, length.intValue());
                }
                if (length == null && this.f7805b != 0 && value.length() > this.f7805b) {
                    value = value.substring(0, this.f7805b);
                }
                sb.append("=");
                sb.append(C4491ai.m8338a(value));
            }
        }
        return sb.toString();
    }
}
