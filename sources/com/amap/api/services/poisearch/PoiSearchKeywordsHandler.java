package com.amap.api.services.poisearch;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.C1127c;
import com.amap.api.services.core.C1128d;
import com.amap.api.services.core.C1134v;
import com.amap.api.services.core.JSONHelper;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.QueryInternal;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiSearch.Query;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.api.services.poisearch.k */
class PoiSearchKeywordsHandler extends PoiHandler<QueryInternal, ArrayList<PoiItem>> {
    /* renamed from: h */
    private int f4029h = 1;
    /* renamed from: i */
    private int f4030i = 20;
    /* renamed from: j */
    private int f4031j = 0;
    /* renamed from: k */
    private List<String> f4032k = new ArrayList();
    /* renamed from: l */
    private List<SuggestionCity> f4033l = new ArrayList();

    public PoiSearchKeywordsHandler(Context context, QueryInternal queryInternal) {
        super(context, queryInternal);
    }

    /* renamed from: a */
    public void mo12619a(int i) {
        this.f4029h = i + 1;
    }

    /* renamed from: b */
    public void mo12620b(int i) {
        int i2;
        int i3 = 30;
        if (i > 30) {
            i2 = 30;
        } else {
            i2 = i;
        }
        if (i2 > 0) {
            i3 = i2;
        }
        this.f4030i = i3;
    }

    /* renamed from: f */
    public int mo12060f() {
        return this.f4030i;
    }

    /* renamed from: i */
    public int mo12622i() {
        return this.f4031j;
    }

    /* renamed from: j */
    public Query mo12623j() {
        return ((QueryInternal) this.f3612a).f3802a;
    }

    /* renamed from: k */
    public SearchBound mo12624k() {
        return ((QueryInternal) this.f3612a).f3803b;
    }

    /* renamed from: l */
    public List<String> mo12625l() {
        return this.f4032k;
    }

    /* renamed from: m */
    public List<SuggestionCity> mo12626m() {
        return this.f4033l;
    }

    /* renamed from: b */
    public String mo11971b() {
        String str = C1127c.m4969a() + "/place";
        if (((QueryInternal) this.f3612a).f3803b == null) {
            return str + "/text?";
        }
        if (((QueryInternal) this.f3612a).f3803b.getShape().equals(SearchBound.BOUND_SHAPE)) {
            return str + "/around?";
        }
        return (((QueryInternal) this.f3612a).f3803b.getShape().equals(SearchBound.RECTANGLE_SHAPE) || ((QueryInternal) this.f3612a).f3803b.getShape().equals(SearchBound.POLYGON_SHAPE)) ? str + "/polygon?" : str;
    }

    /* renamed from: e */
    public ArrayList<PoiItem> mo11978b(String str) throws AMapException {
        ArrayList<PoiItem> arrayList = new ArrayList();
        if (str != null) {
            try {
                JSONObject init = JSONObjectInstrumentation.init(str);
                this.f4031j = init.optInt("count");
                arrayList = JSONHelper.m5018c(init);
                if (init.has("suggestion")) {
                    init = init.optJSONObject("suggestion");
                    if (init != null) {
                        this.f4033l = JSONHelper.m4998a(init);
                        this.f4032k = JSONHelper.m5012b(init);
                    }
                }
            } catch (JSONException e) {
                C1128d.m4975a(e, "PoiSearchKeywordHandler", "paseJSONJSONException");
            } catch (Exception e2) {
                C1128d.m4975a(e2, "PoiSearchKeywordHandler", "paseJSONException");
            }
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a_ */
    public String mo11977a_() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("output=json");
        if (((QueryInternal) this.f3612a).f3803b != null) {
            double a;
            if (((QueryInternal) this.f3612a).f3803b.getShape().equals(SearchBound.BOUND_SHAPE)) {
                a = C1128d.m4970a(((QueryInternal) this.f3612a).f3803b.getCenter().getLongitude());
                stringBuilder.append("&location=").append(a + "," + C1128d.m4970a(((QueryInternal) this.f3612a).f3803b.getCenter().getLatitude()));
                stringBuilder.append("&radius=").append(((QueryInternal) this.f3612a).f3803b.getRange());
                stringBuilder.append("&sortrule=").append(m5199n());
            } else if (((QueryInternal) this.f3612a).f3803b.getShape().equals(SearchBound.RECTANGLE_SHAPE)) {
                LatLonPoint lowerLeft = ((QueryInternal) this.f3612a).f3803b.getLowerLeft();
                LatLonPoint upperRight = ((QueryInternal) this.f3612a).f3803b.getUpperRight();
                double a2 = C1128d.m4970a(lowerLeft.getLatitude());
                a = C1128d.m4970a(lowerLeft.getLongitude());
                stringBuilder.append("&polygon=" + a + "," + a2 + ";" + C1128d.m4970a(upperRight.getLongitude()) + "," + C1128d.m4970a(upperRight.getLatitude()));
            } else if (((QueryInternal) this.f3612a).f3803b.getShape().equals(SearchBound.POLYGON_SHAPE)) {
                List polyGonList = ((QueryInternal) this.f3612a).f3803b.getPolyGonList();
                if (polyGonList != null && polyGonList.size() > 0) {
                    stringBuilder.append("&polygon=" + C1128d.m4974a(polyGonList));
                }
            }
        }
        String city = ((QueryInternal) this.f3612a).f3802a.getCity();
        if (!mo11983a(city)) {
            stringBuilder.append("&city=").append(mo11979c(city));
        }
        if (!C1128d.m4976a(m5200o())) {
            stringBuilder.append(m5200o());
        }
        stringBuilder.append("&keywords=" + mo11979c(((QueryInternal) this.f3612a).f3802a.getQueryString()));
        stringBuilder.append("&language=" + ((QueryInternal) this.f3612a).f3802a.getQueryLanguage());
        stringBuilder.append("&offset=" + this.f4030i);
        stringBuilder.append("&page=" + this.f4029h);
        stringBuilder.append("&types=" + mo11979c(((QueryInternal) this.f3612a).f3802a.getCategory()));
        stringBuilder.append("&extensions=all");
        stringBuilder.append("&key=" + C1134v.m5087f(this.f3615d));
        return stringBuilder.toString();
    }

    /* renamed from: n */
    private String m5199n() {
        if (((QueryInternal) this.f3612a).f3803b.isDistanceSort()) {
            return "distance";
        }
        return "weight";
    }

    /* renamed from: o */
    private String m5200o() {
        StringBuffer stringBuffer = new StringBuffer();
        if (((QueryInternal) this.f3612a).f3802a.hasGroupBuyLimit() && ((QueryInternal) this.f3612a).f3802a.hasDiscountLimit()) {
            stringBuffer.append("&filter=groupbuy:1|discount:1");
            return stringBuffer.toString();
        }
        if (((QueryInternal) this.f3612a).f3802a.hasGroupBuyLimit()) {
            stringBuffer.append("&filter=");
            stringBuffer.append("groupbuy:1");
        }
        if (((QueryInternal) this.f3612a).f3802a.hasDiscountLimit()) {
            stringBuffer.append("&filter=");
            stringBuffer.append("discount:1");
        }
        return stringBuffer.toString();
    }
}
