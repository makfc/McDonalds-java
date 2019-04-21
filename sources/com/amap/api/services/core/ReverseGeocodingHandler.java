package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.core.s */
public class ReverseGeocodingHandler extends C1076q<RegeocodeQuery, RegeocodeAddress> {
    public ReverseGeocodingHandler(Context context, RegeocodeQuery regeocodeQuery) {
        super(context, regeocodeQuery);
    }

    /* renamed from: b */
    public String mo11971b() {
        return C1127c.m4969a() + "/geocode/regeo?";
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public RegeocodeAddress mo11978b(String str) throws AMapException {
        RegeocodeAddress regeocodeAddress = new RegeocodeAddress();
        try {
            JSONObject optJSONObject = JSONObjectInstrumentation.init(str).optJSONObject("regeocode");
            if (optJSONObject != null) {
                regeocodeAddress.setFormatAddress(JSONHelper.m5011b(optJSONObject, "formatted_address"));
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("addressComponent");
                if (optJSONObject2 != null) {
                    JSONHelper.m5007a(optJSONObject2, regeocodeAddress);
                }
                regeocodeAddress.setPois(JSONHelper.m5018c(optJSONObject));
                JSONArray optJSONArray = optJSONObject.optJSONArray("roads");
                if (optJSONArray != null) {
                    JSONHelper.m5015b(optJSONArray, regeocodeAddress);
                }
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("roadinters");
                if (optJSONArray2 != null) {
                    JSONHelper.m5005a(optJSONArray2, regeocodeAddress);
                }
            }
        } catch (JSONException e) {
            C1128d.m4975a(e, "ReverseGeocodingHandler", "paseJSON");
        }
        return regeocodeAddress;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a_ */
    public String mo11977a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json").append("&extensions=all").append("&location=").append(((RegeocodeQuery) this.f3612a).getPoint().getLongitude()).append(",").append(((RegeocodeQuery) this.f3612a).getPoint().getLatitude());
        stringBuffer.append("&radius=").append(((RegeocodeQuery) this.f3612a).getRadius());
        stringBuffer.append("&coordsys=").append(((RegeocodeQuery) this.f3612a).getLatLonType());
        stringBuffer.append("&key=" + C1134v.m5087f(this.f3615d));
        return stringBuffer.toString();
    }
}
