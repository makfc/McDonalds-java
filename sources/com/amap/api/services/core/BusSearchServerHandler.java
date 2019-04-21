package com.amap.api.services.core;

import android.content.Context;
import com.amap.api.services.busline.BusLineQuery;
import com.amap.api.services.busline.BusLineQuery.SearchType;
import com.amap.api.services.busline.BusStationQuery;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.core.b */
public class BusSearchServerHandler<T> extends C1076q<T, ArrayList<?>> {
    /* renamed from: h */
    private int f3707h = 0;
    /* renamed from: i */
    private List<String> f3708i = new ArrayList();
    /* renamed from: j */
    private List<SuggestionCity> f3709j = new ArrayList();

    public BusSearchServerHandler(Context context, T t) {
        super(context, t);
    }

    /* renamed from: c */
    public T mo12058c() {
        return this.f3612a;
    }

    /* renamed from: d */
    public int mo12059d() {
        return this.f3707h;
    }

    /* renamed from: b */
    public String mo11971b() {
        String str = this.f3612a instanceof BusLineQuery ? ((BusLineQuery) this.f3612a).getCategory() == SearchType.BY_LINE_ID ? "lineid" : ((BusLineQuery) this.f3612a).getCategory() == SearchType.BY_LINE_NAME ? "linename" : "" : "stopname";
        return C1127c.m4969a() + "/bus/" + str + "?";
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public ArrayList<?> mo11978b(String str) throws AMapException {
        try {
            JSONObject init = JSONObjectInstrumentation.init(str);
            JSONObject optJSONObject = init.optJSONObject("suggestion");
            if (optJSONObject != null) {
                this.f3709j = JSONHelper.m4998a(optJSONObject);
                this.f3708i = JSONHelper.m5012b(optJSONObject);
            }
            this.f3707h = init.optInt("count");
            if (this.f3612a instanceof BusLineQuery) {
                return JSONHelper.m5035i(init);
            }
            return JSONHelper.m5026e(init);
        } catch (Exception e) {
            C1128d.m4975a(e, "BusSearchServerHandler", "paseJSON");
            return null;
        }
    }

    /* renamed from: b_ */
    public List<String> mo12057b_() {
        return this.f3708i;
    }

    /* renamed from: f */
    public List<SuggestionCity> mo12060f() {
        return this.f3709j;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a_ */
    public String mo11977a_() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("output=json");
        String city;
        if (this.f3612a instanceof BusLineQuery) {
            BusLineQuery busLineQuery = (BusLineQuery) this.f3612a;
            stringBuilder.append("&extensions=all");
            if (busLineQuery.getCategory() == SearchType.BY_LINE_ID) {
                stringBuilder.append("&id=").append(mo11979c(((BusLineQuery) this.f3612a).getQueryString()));
            } else {
                city = busLineQuery.getCity();
                if (!JSONHelper.m5033h(city)) {
                    stringBuilder.append("&city=").append(mo11979c(city));
                }
                stringBuilder.append("&keywords=" + mo11979c(busLineQuery.getQueryString()));
                stringBuilder.append("&offset=" + busLineQuery.getPageSize());
                stringBuilder.append("&page=" + (busLineQuery.getPageNumber() + 1));
            }
        } else {
            BusStationQuery busStationQuery = (BusStationQuery) this.f3612a;
            city = busStationQuery.getCity();
            if (!JSONHelper.m5033h(city)) {
                stringBuilder.append("&city=").append(mo11979c(city));
            }
            stringBuilder.append("&keywords=" + mo11979c(busStationQuery.getQueryString()));
            stringBuilder.append("&offset=" + busStationQuery.getPageSize());
            stringBuilder.append("&page=" + (busStationQuery.getPageNumber() + 1));
        }
        stringBuilder.append("&key=" + C1134v.m5087f(this.f3615d));
        return stringBuilder.toString();
    }
}
