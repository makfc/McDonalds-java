package com.amap.api.services.poisearch;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.C1128d;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.ManifestConfig;
import com.amap.api.services.core.QueryInternal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PoiSearch {
    public static final String CHINESE = "zh-CN";
    public static final String ENGLISH = "en";
    /* renamed from: j */
    private static HashMap<Integer, PoiResult> f4004j;
    /* renamed from: a */
    Handler f4005a = new C1165i(this);
    /* renamed from: b */
    private SearchBound f4006b;
    /* renamed from: c */
    private Query f4007c;
    /* renamed from: d */
    private Context f4008d;
    /* renamed from: e */
    private OnPoiSearchListener f4009e;
    /* renamed from: f */
    private String f4010f = CHINESE;
    /* renamed from: g */
    private Query f4011g;
    /* renamed from: h */
    private SearchBound f4012h;
    /* renamed from: i */
    private int f4013i;

    /* renamed from: com.amap.api.services.poisearch.PoiSearch$1 */
    class C11561 extends Thread {
        C11561() {
        }

        public void run() {
            Message message = new Message();
            message.what = 100;
            Bundle bundle = new Bundle();
            Object obj = null;
            try {
                obj = PoiSearch.this.searchPOI();
                bundle.putInt("errorCode", 0);
            } catch (AMapException e) {
                C1128d.m4975a(e, "PoiSearch", "searchPOIAsyn");
                bundle.putInt("errorCode", e.getErrorCode());
            } finally {
                message.obj = obj;
                message.setData(bundle);
                PoiSearch.this.f4005a.sendMessage(message);
            }
        }
    }

    public interface OnPoiSearchListener {
        void onPoiItemDetailSearched(PoiItemDetail poiItemDetail, int i);

        void onPoiSearched(PoiResult poiResult, int i);
    }

    public static class Query implements Cloneable {
        /* renamed from: a */
        private String f3989a;
        /* renamed from: b */
        private String f3990b;
        /* renamed from: c */
        private String f3991c;
        /* renamed from: d */
        private int f3992d;
        /* renamed from: e */
        private int f3993e;
        /* renamed from: f */
        private boolean f3994f;
        /* renamed from: g */
        private boolean f3995g;
        /* renamed from: h */
        private String f3996h;

        public Query(String str, String str2) {
            this(str, str2, null);
        }

        public Query(String str, String str2, String str3) {
            this.f3992d = 0;
            this.f3993e = 20;
            this.f3996h = PoiSearch.CHINESE;
            this.f3989a = str;
            this.f3990b = str2;
            this.f3991c = str3;
        }

        public String getQueryString() {
            return this.f3989a;
        }

        /* Access modifiers changed, original: protected */
        public void setQueryLanguage(String str) {
            if ("en".equals(str)) {
                this.f3996h = "en";
            } else {
                this.f3996h = PoiSearch.CHINESE;
            }
        }

        /* Access modifiers changed, original: protected */
        public String getQueryLanguage() {
            return this.f3996h;
        }

        public void setLimitGroupbuy(boolean z) {
            this.f3994f = z;
        }

        public boolean hasGroupBuyLimit() {
            return this.f3994f;
        }

        public void setLimitDiscount(boolean z) {
            this.f3995g = z;
        }

        public boolean hasDiscountLimit() {
            return this.f3995g;
        }

        public String getCategory() {
            if (this.f3990b == null || this.f3990b.equals("00") || this.f3990b.equals("00|")) {
                return m5164a();
            }
            return this.f3990b;
        }

        /* renamed from: a */
        private String m5164a() {
            return "";
        }

        public String getCity() {
            return this.f3991c;
        }

        public int getPageNum() {
            return this.f3992d;
        }

        public void setPageNum(int i) {
            this.f3992d = i;
        }

        public void setPageSize(int i) {
            this.f3993e = i;
        }

        public int getPageSize() {
            return this.f3993e;
        }

        public boolean queryEquals(Query query) {
            if (query == null) {
                return false;
            }
            if (query == this) {
                return true;
            }
            if (PoiSearch.m5177b(query.f3989a, this.f3989a) && PoiSearch.m5177b(query.f3990b, this.f3990b) && PoiSearch.m5177b(query.f3996h, this.f3996h) && PoiSearch.m5177b(query.f3991c, this.f3991c) && query.f3993e == this.f3993e) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i;
            int i2 = 1231;
            int i3 = 0;
            int hashCode = ((this.f3991c == null ? 0 : this.f3991c.hashCode()) + (((this.f3990b == null ? 0 : this.f3990b.hashCode()) + 31) * 31)) * 31;
            if (this.f3995g) {
                i = 1231;
            } else {
                i = 1237;
            }
            i = (i + hashCode) * 31;
            if (!this.f3994f) {
                i2 = 1237;
            }
            i = ((((((this.f3996h == null ? 0 : this.f3996h.hashCode()) + ((i + i2) * 31)) * 31) + this.f3992d) * 31) + this.f3993e) * 31;
            if (this.f3989a != null) {
                i3 = this.f3989a.hashCode();
            }
            return i + i3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Query query = (Query) obj;
            if (this.f3990b == null) {
                if (query.f3990b != null) {
                    return false;
                }
            } else if (!this.f3990b.equals(query.f3990b)) {
                return false;
            }
            if (this.f3991c == null) {
                if (query.f3991c != null) {
                    return false;
                }
            } else if (!this.f3991c.equals(query.f3991c)) {
                return false;
            }
            if (this.f3995g != query.f3995g) {
                return false;
            }
            if (this.f3994f != query.f3994f) {
                return false;
            }
            if (this.f3996h == null) {
                if (query.f3996h != null) {
                    return false;
                }
            } else if (!this.f3996h.equals(query.f3996h)) {
                return false;
            }
            if (this.f3992d != query.f3992d) {
                return false;
            }
            if (this.f3993e != query.f3993e) {
                return false;
            }
            if (this.f3989a == null) {
                if (query.f3989a != null) {
                    return false;
                }
                return true;
            } else if (this.f3989a.equals(query.f3989a)) {
                return true;
            } else {
                return false;
            }
        }

        public Query clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                C1128d.m4975a(e, "PoiSearch", "queryclone");
            }
            Query query = new Query(this.f3989a, this.f3990b, this.f3991c);
            query.setPageNum(this.f3992d);
            query.setPageSize(this.f3993e);
            query.setLimitDiscount(this.f3995g);
            query.setLimitGroupbuy(this.f3994f);
            query.setQueryLanguage(this.f3996h);
            return query;
        }
    }

    public static class SearchBound implements Cloneable {
        public static final String BOUND_SHAPE = "Bound";
        public static final String ELLIPSE_SHAPE = "Ellipse";
        public static final String POLYGON_SHAPE = "Polygon";
        public static final String RECTANGLE_SHAPE = "Rectangle";
        /* renamed from: a */
        private LatLonPoint f3997a;
        /* renamed from: b */
        private LatLonPoint f3998b;
        /* renamed from: c */
        private int f3999c;
        /* renamed from: d */
        private LatLonPoint f4000d;
        /* renamed from: e */
        private String f4001e;
        /* renamed from: f */
        private boolean f4002f;
        /* renamed from: g */
        private List<LatLonPoint> f4003g;

        public SearchBound(LatLonPoint latLonPoint, int i) {
            this.f4002f = true;
            this.f4001e = BOUND_SHAPE;
            this.f3999c = i;
            this.f4000d = latLonPoint;
            m5169a(latLonPoint, C1128d.m4971a(i), C1128d.m4971a(i));
        }

        public SearchBound(LatLonPoint latLonPoint, int i, boolean z) {
            this.f4002f = true;
            this.f4001e = BOUND_SHAPE;
            this.f3999c = i;
            this.f4000d = latLonPoint;
            m5169a(latLonPoint, C1128d.m4971a(i), C1128d.m4971a(i));
            this.f4002f = z;
        }

        public SearchBound(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.f4002f = true;
            this.f4001e = RECTANGLE_SHAPE;
            m5170a(latLonPoint, latLonPoint2);
        }

        public SearchBound(List<LatLonPoint> list) {
            this.f4002f = true;
            this.f4001e = POLYGON_SHAPE;
            this.f4003g = list;
        }

        private SearchBound(LatLonPoint latLonPoint, LatLonPoint latLonPoint2, int i, LatLonPoint latLonPoint3, String str, List<LatLonPoint> list, boolean z) {
            this.f4002f = true;
            this.f3997a = latLonPoint;
            this.f3998b = latLonPoint2;
            this.f3999c = i;
            this.f4000d = latLonPoint3;
            this.f4001e = str;
            this.f4003g = list;
            this.f4002f = z;
        }

        /* renamed from: a */
        private void m5170a(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.f3997a = latLonPoint;
            this.f3998b = latLonPoint2;
            if (this.f3997a.getLatitude() >= this.f3998b.getLatitude() || this.f3997a.getLongitude() >= this.f3998b.getLongitude()) {
                throw new IllegalArgumentException("invalid rect ");
            }
            this.f4000d = new LatLonPoint((this.f3997a.getLatitude() + this.f3998b.getLatitude()) / 2.0d, (this.f3997a.getLongitude() + this.f3998b.getLongitude()) / 2.0d);
        }

        /* renamed from: a */
        private void m5169a(LatLonPoint latLonPoint, double d, double d2) {
            double d3 = d / 2.0d;
            double d4 = d2 / 2.0d;
            double latitude = latLonPoint.getLatitude();
            double longitude = latLonPoint.getLongitude();
            m5170a(new LatLonPoint(latitude - d3, longitude - d4), new LatLonPoint(d3 + latitude, d4 + longitude));
        }

        public LatLonPoint getLowerLeft() {
            return this.f3997a;
        }

        public LatLonPoint getUpperRight() {
            return this.f3998b;
        }

        public LatLonPoint getCenter() {
            return this.f4000d;
        }

        public double getLonSpanInMeter() {
            if (RECTANGLE_SHAPE.equals(getShape())) {
                return this.f3998b.getLongitude() - this.f3997a.getLongitude();
            }
            return 0.0d;
        }

        public double getLatSpanInMeter() {
            if (RECTANGLE_SHAPE.equals(getShape())) {
                return this.f3998b.getLatitude() - this.f3997a.getLatitude();
            }
            return 0.0d;
        }

        public int getRange() {
            return this.f3999c;
        }

        public String getShape() {
            return this.f4001e;
        }

        public boolean isDistanceSort() {
            return this.f4002f;
        }

        public List<LatLonPoint> getPolyGonList() {
            return this.f4003g;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.f4003g == null ? 0 : this.f4003g.hashCode()) + (((this.f3998b == null ? 0 : this.f3998b.hashCode()) + (((this.f3997a == null ? 0 : this.f3997a.hashCode()) + (((this.f4002f ? 1231 : 1237) + (((this.f4000d == null ? 0 : this.f4000d.hashCode()) + 31) * 31)) * 31)) * 31)) * 31)) * 31) + this.f3999c) * 31;
            if (this.f4001e != null) {
                i = this.f4001e.hashCode();
            }
            return hashCode + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            SearchBound searchBound = (SearchBound) obj;
            if (this.f4000d == null) {
                if (searchBound.f4000d != null) {
                    return false;
                }
            } else if (!this.f4000d.equals(searchBound.f4000d)) {
                return false;
            }
            if (this.f4002f != searchBound.f4002f) {
                return false;
            }
            if (this.f3997a == null) {
                if (searchBound.f3997a != null) {
                    return false;
                }
            } else if (!this.f3997a.equals(searchBound.f3997a)) {
                return false;
            }
            if (this.f3998b == null) {
                if (searchBound.f3998b != null) {
                    return false;
                }
            } else if (!this.f3998b.equals(searchBound.f3998b)) {
                return false;
            }
            if (this.f4003g == null) {
                if (searchBound.f4003g != null) {
                    return false;
                }
            } else if (!this.f4003g.equals(searchBound.f4003g)) {
                return false;
            }
            if (this.f3999c != searchBound.f3999c) {
                return false;
            }
            if (this.f4001e == null) {
                if (searchBound.f4001e != null) {
                    return false;
                }
                return true;
            } else if (this.f4001e.equals(searchBound.f4001e)) {
                return true;
            } else {
                return false;
            }
        }

        public SearchBound clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                C1128d.m4975a(e, "PoiSearch", "SearchBoundClone");
            }
            return new SearchBound(this.f3997a, this.f3998b, this.f3999c, this.f4000d, this.f4001e, this.f4003g, this.f4002f);
        }
    }

    public PoiSearch(Context context, Query query) {
        this.f4008d = context.getApplicationContext();
        setQuery(query);
    }

    public void setOnPoiSearchListener(OnPoiSearchListener onPoiSearchListener) {
        this.f4009e = onPoiSearchListener;
    }

    public void setLanguage(String str) {
        if ("en".equals(str)) {
            this.f4010f = "en";
        } else {
            this.f4010f = CHINESE;
        }
    }

    public String getLanguage() {
        return this.f4010f;
    }

    /* renamed from: a */
    private boolean m5173a() {
        return (C1128d.m4976a(this.f4007c.f3989a) && C1128d.m4976a(this.f4007c.f3990b)) ? false : true;
    }

    /* renamed from: b */
    private boolean m5176b() {
        SearchBound bound = getBound();
        if (bound != null && bound.getShape().equals(SearchBound.BOUND_SHAPE)) {
            return true;
        }
        return false;
    }

    public PoiResult searchPOI() throws AMapException {
        ManifestConfig.m5058a(this.f4008d);
        if (m5176b() || m5173a()) {
            this.f4007c.setQueryLanguage(this.f4010f);
            if ((!this.f4007c.queryEquals(this.f4011g) && this.f4006b == null) || !(this.f4007c.queryEquals(this.f4011g) || this.f4006b.equals(this.f4012h))) {
                this.f4013i = 0;
                this.f4011g = this.f4007c.clone();
                if (this.f4006b != null) {
                    this.f4012h = this.f4006b.clone();
                }
                if (f4004j != null) {
                    f4004j.clear();
                }
            }
            SearchBound searchBound = null;
            if (this.f4006b != null) {
                searchBound = this.f4006b.clone();
            }
            PoiSearchKeywordsHandler poiSearchKeywordsHandler;
            PoiResult a;
            if (this.f4013i == 0) {
                poiSearchKeywordsHandler = new PoiSearchKeywordsHandler(this.f4008d, new QueryInternal(this.f4007c.clone(), searchBound));
                poiSearchKeywordsHandler.mo12619a(this.f4007c.f3992d);
                poiSearchKeywordsHandler.mo12620b(this.f4007c.f3993e);
                a = PoiResult.m5163a(poiSearchKeywordsHandler, (ArrayList) poiSearchKeywordsHandler.mo11981g());
                m5172a(a);
                return a;
            }
            PoiResult pageLocal = getPageLocal(this.f4007c.getPageNum());
            if (pageLocal != null) {
                return pageLocal;
            }
            poiSearchKeywordsHandler = new PoiSearchKeywordsHandler(this.f4008d, new QueryInternal(this.f4007c.clone(), searchBound));
            poiSearchKeywordsHandler.mo12619a(this.f4007c.f3992d);
            poiSearchKeywordsHandler.mo12620b(this.f4007c.f3993e);
            a = PoiResult.m5163a(poiSearchKeywordsHandler, (ArrayList) poiSearchKeywordsHandler.mo11981g());
            f4004j.put(Integer.valueOf(this.f4007c.f3992d), a);
            return a;
        }
        throw new AMapException("无效的参数 - IllegalArgumentException");
    }

    public void searchPOIAsyn() {
        new C11561().start();
    }

    public PoiItemDetail searchPOIDetail(String str) throws AMapException {
        ManifestConfig.m5058a(this.f4008d);
        return (PoiItemDetail) new PoiSearchIdHandler(this.f4008d, str, this.f4010f).mo11981g();
    }

    public void searchPOIDetailAsyn(final String str) {
        new Thread() {
            public void run() {
                Message message = new Message();
                message.what = 101;
                Bundle bundle = new Bundle();
                Object obj = null;
                try {
                    obj = PoiSearch.this.searchPOIDetail(str);
                    bundle.putInt("errorCode", 0);
                } catch (AMapException e) {
                    C1128d.m4975a(e, "PoiSearch", "searchPOIDetailAsyn");
                    bundle.putInt("errorCode", e.getErrorCode());
                } finally {
                    message.obj = obj;
                    message.setData(bundle);
                    PoiSearch.this.f4005a.sendMessage(message);
                }
            }
        }.start();
    }

    public void setQuery(Query query) {
        this.f4007c = query;
    }

    public void setBound(SearchBound searchBound) {
        this.f4006b = searchBound;
    }

    public Query getQuery() {
        return this.f4007c;
    }

    public SearchBound getBound() {
        return this.f4006b;
    }

    /* renamed from: b */
    private static boolean m5177b(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    /* renamed from: a */
    private void m5172a(PoiResult poiResult) {
        f4004j = new HashMap();
        if (this.f4007c != null && poiResult != null && this.f4013i > 0 && this.f4013i > this.f4007c.getPageNum()) {
            f4004j.put(Integer.valueOf(this.f4007c.getPageNum()), poiResult);
        }
    }

    /* Access modifiers changed, original: protected */
    public PoiResult getPageLocal(int i) {
        if (m5174a(i)) {
            return (PoiResult) f4004j.get(Integer.valueOf(i));
        }
        throw new IllegalArgumentException("page out of range");
    }

    /* renamed from: a */
    private boolean m5174a(int i) {
        return i <= this.f4013i && i >= 0;
    }
}
