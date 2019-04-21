package com.amap.api.mapcore.util;

import java.util.HashMap;
import java.util.Map;

@Deprecated
/* renamed from: com.amap.api.mapcore.util.dy */
class AuthRequest extends Request {
    /* renamed from: a */
    private Map<String, String> f1837a = new HashMap();
    /* renamed from: b */
    private String f1838b;
    /* renamed from: c */
    private Map<String, String> f1839c = new HashMap();

    AuthRequest() {
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo9299a(Map<String, String> map) {
        this.f1837a.clear();
        this.f1837a.putAll(map);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: a */
    public void mo9298a(String str) {
        this.f1838b = str;
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public void mo9300b(Map<String, String> map) {
        this.f1839c.clear();
        this.f1839c.putAll(map);
    }

    /* renamed from: a */
    public String mo8901a() {
        return this.f1838b;
    }

    /* renamed from: c */
    public Map<String, String> mo8907c() {
        return this.f1837a;
    }

    /* renamed from: b */
    public Map<String, String> mo8905b() {
        return this.f1839c;
    }
}
