package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.district.DistrictResult;
import com.amap.api.services.district.DistrictSearchQuery;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.core.e */
public class DistrictServerHandler extends C1076q<DistrictSearchQuery, DistrictResult> {
    public DistrictServerHandler(Context context, DistrictSearchQuery districtSearchQuery) {
        super(context, districtSearchQuery);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a_ */
    public String mo11977a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json");
        stringBuffer.append("&page=").append(((DistrictSearchQuery) this.f3612a).getPageNum() + 1);
        stringBuffer.append("&offset=").append(((DistrictSearchQuery) this.f3612a).getPageSize());
        stringBuffer.append("&showChild=").append(((DistrictSearchQuery) this.f3612a).isShowChild());
        if (((DistrictSearchQuery) this.f3612a).checkKeyWords()) {
            stringBuffer.append("&keywords=").append(mo11979c(((DistrictSearchQuery) this.f3612a).getKeywords()));
        }
        if (((DistrictSearchQuery) this.f3612a).checkLevels()) {
            stringBuffer.append("&level=").append(((DistrictSearchQuery) this.f3612a).getKeywordsLevel());
        }
        stringBuffer.append("&key=" + C1134v.m5087f(this.f3615d));
        return stringBuffer.toString();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public DistrictResult mo11978b(String str) throws AMapException {
        ArrayList arrayList = new ArrayList();
        DistrictResult districtResult = new DistrictResult((DistrictSearchQuery) this.f3612a, arrayList);
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            districtResult.setPageCount(init.optInt("count"));
            JSONArray optJSONArray = init.optJSONArray("districts");
            if (optJSONArray == null) {
                return districtResult;
            }
            JSONHelper.m5006a(optJSONArray, arrayList, null);
            return districtResult;
        } catch (JSONException e) {
            C1128d.m4975a(e, "DistrictServerHandler", "paseJSONJSONException");
        } catch (Exception e2) {
            C1128d.m4975a(e2, "DistrictServerHandler", "paseJSONException");
        }
    }

    /* renamed from: b */
    public String mo11971b() {
        return C1127c.m4969a() + "/config/district?";
    }
}
