package com.ensighten;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

/* renamed from: com.ensighten.t */
public final class C1858t implements CookieStore {
    /* renamed from: a */
    private final ConcurrentHashMap<String, Cookie> f5978a = new ConcurrentHashMap();
    /* renamed from: b */
    private final SharedPreferences f5979b;

    public C1858t(Context context) {
        int i = 0;
        this.f5979b = context.getSharedPreferences("CookiePrefsFile", 0);
        String string = this.f5979b.getString("names", null);
        if (string != null) {
            String[] split = TextUtils.split(string, ",");
            int length = split.length;
            while (i < length) {
                String str = split[i];
                String string2 = this.f5979b.getString("cookie_" + str, null);
                if (string2 != null) {
                    Cookie a = m7419a(string2);
                    if (a != null) {
                        this.f5978a.put(str, a);
                    }
                }
                i++;
            }
            clearExpired(new Date());
        }
    }

    public final void addCookie(Cookie cookie) {
        String str = cookie.getName() + cookie.getDomain();
        if (cookie.isExpired(new Date())) {
            this.f5978a.remove(str);
        } else {
            this.f5978a.put(str, cookie);
        }
        Editor edit = this.f5979b.edit();
        edit.putString("names", TextUtils.join(",", this.f5978a.keySet()));
        edit.putString("cookie_" + str, m7417a(new C1862w(cookie)));
        edit.commit();
    }

    public final void clear() {
        Editor edit = this.f5979b.edit();
        for (String str : this.f5978a.keySet()) {
            edit.remove("cookie_" + str);
        }
        edit.remove("names");
        edit.commit();
        this.f5978a.clear();
    }

    public final boolean clearExpired(Date date) {
        boolean z;
        boolean z2 = false;
        Editor edit = this.f5979b.edit();
        Iterator it = this.f5978a.entrySet().iterator();
        while (true) {
            z = z2;
            if (!it.hasNext()) {
                break;
            }
            Entry entry = (Entry) it.next();
            String str = (String) entry.getKey();
            if (((Cookie) entry.getValue()).isExpired(date)) {
                this.f5978a.remove(str);
                edit.remove("cookie_" + str);
                z2 = true;
            } else {
                z2 = z;
            }
        }
        if (z) {
            edit.putString("names", TextUtils.join(",", this.f5978a.keySet()));
        }
        edit.commit();
        return z;
    }

    public final List<Cookie> getCookies() {
        return new ArrayList(this.f5978a.values());
    }

    /* renamed from: a */
    private String m7417a(C1862w c1862w) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(c1862w);
            return C1858t.m7418a(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    private Cookie m7419a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        try {
            C1862w c1862w = (C1862w) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
            Cookie cookie = c1862w.f5999a;
            if (c1862w.f6000b != null) {
                return c1862w.f6000b;
            }
            return cookie;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static String m7418a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                stringBuffer.append('0');
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        return stringBuffer.toString().toUpperCase();
    }
}
