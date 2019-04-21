package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;

/* renamed from: com.amap.api.services.core.a */
public class BusRouteSearchHandler extends C1076q<BusRouteQuery, BusRouteResult> {
    public BusRouteSearchHandler(Context context, BusRouteQuery busRouteQuery) {
        super(context, busRouteQuery);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a_ */
    public String mo11977a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(C1134v.m5087f(this.f3615d));
        stringBuffer.append("&origin=").append(C1128d.m4972a(((BusRouteQuery) this.f3612a).getFromAndTo().getFrom()));
        stringBuffer.append("&destination=").append(C1128d.m4972a(((BusRouteQuery) this.f3612a).getFromAndTo().getTo()));
        String city = ((BusRouteQuery) this.f3612a).getCity();
        if (!JSONHelper.m5033h(city)) {
            stringBuffer.append("&city=").append(mo11979c(city));
        }
        stringBuffer.append("&strategy=").append("" + ((BusRouteQuery) this.f3612a).getMode());
        stringBuffer.append("&nightflag=").append(((BusRouteQuery) this.f3612a).getNightFlag());
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public BusRouteResult mo11978b(String str) throws AMapException {
        return JSONHelper.m5010b(str);
    }

    /* renamed from: b */
    public String mo11971b() {
        return C1127c.m4969a() + "/direction/transit/integrated?";
    }
}
