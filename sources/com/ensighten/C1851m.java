package com.ensighten;

import android.support.p000v4.widget.ExploreByTouchHelper;
import android.view.View;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.ensighten.m */
public final class C1851m {
    /* renamed from: a */
    JSONObject f5959a = new JSONObject();
    /* renamed from: b */
    Map<String, JSONArray> f5960b = new HashMap();
    /* renamed from: c */
    Map<String, Pattern> f5961c = new HashMap();
    /* renamed from: d */
    Map<String, Pattern> f5962d = new HashMap();

    /* renamed from: a */
    public final JSONArray mo15503a(String str, Object[] objArr, String str2) throws JSONException {
        View view;
        JSONObject jSONObject = null;
        if (objArr == null || objArr.length <= 0 || !(objArr[0] instanceof View)) {
            view = null;
        } else {
            view = (View) objArr[0];
        }
        int id = view != null ? view.getId() : ExploreByTouchHelper.INVALID_ID;
        JSONArray jSONArray = (JSONArray) this.f5960b.get(str2 + "|" + str);
        if (jSONArray == null && view != null) {
            jSONArray = (JSONArray) this.f5960b.get(str2 + "|" + str + "|" + id);
        }
        if (jSONArray == null) {
            if (C1845i.m7357d()) {
                C1845i.m7345a("Looking for rules.");
            }
            if (!(view == null || ExploreByTouchHelper.INVALID_ID == id || this.f5959a == null)) {
                JSONObject jSONObject2 = this.f5959a.has("objects") ? this.f5959a.getJSONObject("objects") : null;
                if (jSONObject2 != null && jSONObject2.has(String.valueOf(id))) {
                    jSONArray = jSONObject2.getJSONObject(String.valueOf(id)).getJSONArray("rules");
                    this.f5960b.put(str2 + "|" + str + "|" + id, jSONArray);
                    if (C1845i.m7357d()) {
                        C1845i.m7345a("Found rules for object");
                    }
                }
            }
            JSONArray jSONArray2 = jSONArray;
            if (jSONArray2 == null) {
                JSONObject jSONObject3 = this.f5959a.has("classes") ? this.f5959a.getJSONObject("classes") : null;
                if (jSONObject3 != null) {
                    if (jSONObject3.has(str2)) {
                        jSONObject = jSONObject3.getJSONObject(str2);
                    }
                    if (jSONObject == null) {
                        for (String str3 : this.f5961c.keySet()) {
                            if (((Pattern) this.f5961c.get(str3)).matcher(str2).matches()) {
                                jSONObject = jSONObject3.getJSONObject(str3);
                                break;
                            }
                        }
                    }
                    if (jSONObject != null) {
                        if (jSONObject.has(str)) {
                            jSONArray = jSONObject.getJSONObject(str).getJSONArray("rules");
                        } else {
                            for (String str32 : this.f5962d.keySet()) {
                                if (((Pattern) this.f5962d.get(str32)).matcher(str).matches() && jSONObject.has(str32)) {
                                    jSONArray = jSONObject.getJSONObject(str32).getJSONArray("rules");
                                    break;
                                }
                            }
                            jSONArray = jSONArray2;
                        }
                        if (jSONArray != null) {
                            this.f5960b.put(str2 + "|" + str, jSONArray);
                            if (C1845i.m7357d()) {
                                C1845i.m7345a("Found rules for class");
                            }
                        }
                    }
                }
            }
            jSONArray = jSONArray2;
        }
        if (jSONArray == null) {
            if (C1845i.m7357d()) {
                C1845i.m7345a("Inserted empty rule into cache.");
            }
            this.f5960b.put(str2 + "|" + str, C1743a.f5830b);
        }
        return jSONArray;
    }
}
