package com.ensighten;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ensighten.D */
public final class C1692D {
    /* renamed from: a */
    private JSONObject f5598a;
    /* renamed from: b */
    private int f5599b = 0;
    /* renamed from: c */
    private boolean f5600c = false;

    public C1692D(JSONObject jSONObject) {
        this.f5598a = jSONObject;
    }

    /* renamed from: a */
    public final String mo15016a() {
        try {
            return this.f5598a.getString("opcode");
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    public final String mo15017a(String str) {
        try {
            return this.f5598a.getString(str);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    public final int mo15018b() {
        if (!this.f5600c) {
            try {
                JSONArray names = this.f5598a.names();
                int length = names.length();
                for (int i = 0; i < length; i++) {
                    if (names.getString(i).startsWith("arg")) {
                        this.f5599b++;
                    }
                }
            } catch (Exception e) {
            }
            this.f5600c = true;
        }
        return this.f5599b;
    }

    /* renamed from: c */
    public final String mo15019c() {
        try {
            return this.f5598a.getString("value");
        } catch (Exception e) {
            return null;
        }
    }
}
