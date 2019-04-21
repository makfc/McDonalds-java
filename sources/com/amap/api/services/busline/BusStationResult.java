package com.amap.api.services.busline;

import com.amap.api.services.core.BusSearchServerHandler;
import com.amap.api.services.core.SuggestionCity;
import java.util.ArrayList;
import java.util.List;

public final class BusStationResult {
    /* renamed from: a */
    private int f3565a;
    /* renamed from: b */
    private ArrayList<BusStationItem> f3566b = new ArrayList();
    /* renamed from: c */
    private BusStationQuery f3567c;
    /* renamed from: d */
    private List<String> f3568d = new ArrayList();
    /* renamed from: e */
    private List<SuggestionCity> f3569e = new ArrayList();

    /* renamed from: a */
    static BusStationResult m4648a(BusSearchServerHandler busSearchServerHandler, ArrayList<?> arrayList) {
        return new BusStationResult(busSearchServerHandler, arrayList);
    }

    private BusStationResult(BusSearchServerHandler busSearchServerHandler, ArrayList<?> arrayList) {
        this.f3567c = (BusStationQuery) busSearchServerHandler.mo12058c();
        this.f3565a = m4647a(busSearchServerHandler.mo12059d());
        this.f3569e = busSearchServerHandler.mo12060f();
        this.f3568d = busSearchServerHandler.mo12057b_();
        this.f3566b = arrayList;
    }

    /* renamed from: a */
    private int m4647a(int i) {
        int pageSize = this.f3567c.getPageSize();
        pageSize = ((i + pageSize) - 1) / pageSize;
        if (pageSize > 30) {
            return 30;
        }
        return pageSize;
    }

    public int getPageCount() {
        return this.f3565a;
    }

    public BusStationQuery getQuery() {
        return this.f3567c;
    }

    public List<String> getSearchSuggestionKeywords() {
        return this.f3568d;
    }

    public List<SuggestionCity> getSearchSuggestionCities() {
        return this.f3569e;
    }

    public List<BusStationItem> getBusStations() {
        return this.f3566b;
    }
}
