package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.core.g */
public class GeocodingHandler extends C1076q<GeocodeQuery, ArrayList<GeocodeAddress>> {
    public GeocodingHandler(Context context, GeocodeQuery geocodeQuery) {
        super(context, geocodeQuery);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public ArrayList<GeocodeAddress> mo11978b(String str) throws AMapException {
        ArrayList<GeocodeAddress> arrayList = new ArrayList();
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            if (init.has("count") && init.getInt("count") > 0) {
                return JSONHelper.m5043n(init);
            }
            return arrayList;
        } catch (JSONException e) {
            C1128d.m4975a(e, "GeocodingHandler", "paseJSONJSONException");
            return arrayList;
        } catch (Exception e2) {
            C1128d.m4975a(e2, "GeocodingHandler", "paseJSONException");
            return arrayList;
        }
    }

    /* renamed from: b */
    public String mo11971b() {
        return C1127c.m4969a() + "/geocode/geo?";
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a_ */
    public String mo11977a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("output=json").append("&address=").append(mo11979c(((GeocodeQuery) this.f3612a).getLocationName()));
        String city = ((GeocodeQuery) this.f3612a).getCity();
        if (!JSONHelper.m5033h(city)) {
            stringBuffer.append("&city=").append(mo11979c(city));
        }
        stringBuffer.append("&key=" + C1134v.m5087f(this.f3615d));
        return stringBuffer.toString();
    }
}
