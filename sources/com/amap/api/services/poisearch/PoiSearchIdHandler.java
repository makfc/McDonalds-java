package com.amap.api.services.poisearch;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.C1127c;
import com.amap.api.services.core.C1128d;
import com.amap.api.services.core.C1134v;
import com.amap.api.services.core.JSONHelper;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.poisearch.j */
class PoiSearchIdHandler extends PoiHandler<String, PoiItemDetail> {
    /* renamed from: h */
    private String f4028h = PoiSearch.CHINESE;

    public PoiSearchIdHandler(Context context, String str, String str2) {
        super(context, str);
        if ("en".equals(str2)) {
            this.f4028h = str2;
        }
    }

    /* renamed from: b */
    public String mo11971b() {
        return C1127c.m4969a() + "/place/detail?";
    }

    /* renamed from: e */
    public PoiItemDetail mo11978b(String str) throws AMapException {
        PoiItemDetail poiItemDetail = null;
        try {
            return m5193a(JSONObjectInstrumentation.init(str));
        } catch (JSONException e) {
            C1128d.m4975a(e, "PoiSearchIdHandler", "paseJSONJSONException");
            return poiItemDetail;
        } catch (Exception e2) {
            C1128d.m4975a(e2, "PoiSearchIdHandler", "paseJSONException");
            return poiItemDetail;
        }
    }

    /* renamed from: a */
    private PoiItemDetail m5193a(JSONObject jSONObject) throws JSONException {
        PoiItemDetail poiItemDetail = null;
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("pois");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(0);
                if (optJSONObject != null) {
                    poiItemDetail = JSONHelper.m5021d(optJSONObject);
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("rich_content");
                    if (optJSONObject2 != null) {
                        JSONHelper.m5002a(poiItemDetail, optJSONObject2);
                    }
                    optJSONObject2 = optJSONObject.optJSONObject("deep_info");
                    if (optJSONObject2 != null) {
                        JSONHelper.m5027e(poiItemDetail, optJSONObject2, optJSONObject);
                    }
                }
            }
        }
        return poiItemDetail;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a_ */
    public String mo11977a_() {
        return m5194f();
    }

    /* renamed from: f */
    private String m5194f() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id=").append((String) this.f3612a);
        stringBuilder.append("&output=json");
        stringBuilder.append("&extensions=all");
        stringBuilder.append("&language=" + this.f4028h);
        stringBuilder.append("&key=" + C1134v.m5087f(this.f3615d));
        return stringBuilder.toString();
    }
}
