package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;

/* renamed from: com.amap.api.services.core.f */
public class DriveRouteSearchHandler extends C1076q<DriveRouteQuery, DriveRouteResult> {
    public DriveRouteSearchHandler(Context context, DriveRouteQuery driveRouteQuery) {
        super(context, driveRouteQuery);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a_ */
    public String mo11977a_() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(C1134v.m5087f(this.f3615d));
        stringBuffer.append("&origin=").append(C1128d.m4972a(((DriveRouteQuery) this.f3612a).getFromAndTo().getFrom()));
        if (!JSONHelper.m5033h(((DriveRouteQuery) this.f3612a).getFromAndTo().getStartPoiID())) {
            stringBuffer.append("&originid=").append(((DriveRouteQuery) this.f3612a).getFromAndTo().getStartPoiID());
        }
        stringBuffer.append("&destination=").append(C1128d.m4972a(((DriveRouteQuery) this.f3612a).getFromAndTo().getTo()));
        if (!JSONHelper.m5033h(((DriveRouteQuery) this.f3612a).getFromAndTo().getDestinationPoiID())) {
            stringBuffer.append("&destinationid=").append(((DriveRouteQuery) this.f3612a).getFromAndTo().getDestinationPoiID());
        }
        stringBuffer.append("&strategy=").append("" + ((DriveRouteQuery) this.f3612a).getMode());
        stringBuffer.append("&extensions=all");
        if (((DriveRouteQuery) this.f3612a).hasPassPoint()) {
            stringBuffer.append("&waypoints=").append(((DriveRouteQuery) this.f3612a).getPassedPointStr());
        }
        if (((DriveRouteQuery) this.f3612a).hasAvoidpolygons()) {
            stringBuffer.append("&avoidpolygons=").append(((DriveRouteQuery) this.f3612a).getAvoidpolygonsStr());
        }
        if (((DriveRouteQuery) this.f3612a).hasAvoidRoad()) {
            stringBuffer.append("&avoidroad=").append(((DriveRouteQuery) this.f3612a).getAvoidRoad());
        }
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public DriveRouteResult mo11978b(String str) throws AMapException {
        return JSONHelper.m5017c(str);
    }

    /* renamed from: b */
    public String mo11971b() {
        return C1127c.m4969a() + "/direction/driving?";
    }
}
