package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.route.RouteSearch.WalkRouteQuery;
import com.amap.api.services.route.WalkRouteResult;

/* renamed from: com.amap.api.services.core.t */
public class WalkRouteSearchHandler extends C1076q<WalkRouteQuery, WalkRouteResult> {
    public WalkRouteSearchHandler(Context context, WalkRouteQuery walkRouteQuery) {
        super(context, walkRouteQuery);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a_ */
    public String mo11977a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(C1134v.m5087f(this.f3615d));
        stringBuffer.append("&origin=").append(C1128d.m4972a(((WalkRouteQuery) this.f3612a).getFromAndTo().getFrom()));
        stringBuffer.append("&destination=").append(C1128d.m4972a(((WalkRouteQuery) this.f3612a).getFromAndTo().getTo()));
        stringBuffer.append("&multipath=0");
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public WalkRouteResult mo11978b(String str) throws AMapException {
        return JSONHelper.m5022d(str);
    }

    /* renamed from: b */
    public String mo11971b() {
        return C1127c.m4969a() + "/direction/walking?";
    }
}
