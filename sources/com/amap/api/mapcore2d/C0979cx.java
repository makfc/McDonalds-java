package com.amap.api.mapcore2d;

import java.util.HashMap;
import java.util.Map;

@Deprecated
/* compiled from: AuthRequest */
/* renamed from: com.amap.api.mapcore2d.cx */
class C0979cx extends C0931eg {
    /* renamed from: a */
    private Map<String, String> f2793a = new HashMap();
    /* renamed from: b */
    private String f2794b;
    /* renamed from: f */
    private Map<String, String> f2795f = new HashMap();

    C0979cx() {
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo10179a(Map<String, String> map) {
        this.f2793a.clear();
        this.f2793a.putAll(map);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo10178a(String str) {
        this.f2794b = str;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo10180b(Map<String, String> map) {
        this.f2795f.clear();
        this.f2795f.putAll(map);
    }

    /* renamed from: g */
    public String mo10073g() {
        return this.f2794b;
    }

    /* renamed from: e */
    public Map<String, String> mo10071e() {
        return this.f2793a;
    }

    /* renamed from: f */
    public Map<String, String> mo10072f() {
        return this.f2795f;
    }
}
