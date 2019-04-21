package com.amap.api.services.busline;

import com.amap.api.services.core.BusSearchServerHandler;
import com.amap.api.services.core.SuggestionCity;
import java.util.ArrayList;
import java.util.List;

public final class BusLineResult {
    /* renamed from: a */
    private int f3542a;
    /* renamed from: b */
    private ArrayList<BusLineItem> f3543b = new ArrayList();
    /* renamed from: c */
    private BusLineQuery f3544c;
    /* renamed from: d */
    private List<String> f3545d = new ArrayList();
    /* renamed from: e */
    private List<SuggestionCity> f3546e = new ArrayList();

    /* renamed from: a */
    static BusLineResult m4640a(BusSearchServerHandler busSearchServerHandler, ArrayList<?> arrayList) {
        return new BusLineResult(busSearchServerHandler, arrayList);
    }

    private BusLineResult(BusSearchServerHandler busSearchServerHandler, ArrayList<?> arrayList) {
        this.f3544c = (BusLineQuery) busSearchServerHandler.mo12058c();
        this.f3542a = m4639a(busSearchServerHandler.mo12059d());
        this.f3546e = busSearchServerHandler.mo12060f();
        this.f3545d = busSearchServerHandler.mo12057b_();
        this.f3543b = arrayList;
    }

    /* renamed from: a */
    private int m4639a(int i) {
        int pageSize = this.f3544c.getPageSize();
        pageSize = ((i + pageSize) - 1) / pageSize;
        if (pageSize > 30) {
            return 30;
        }
        return pageSize;
    }

    public int getPageCount() {
        return this.f3542a;
    }

    public BusLineQuery getQuery() {
        return this.f3544c;
    }

    public List<String> getSearchSuggestionKeywords() {
        return this.f3545d;
    }

    public List<SuggestionCity> getSearchSuggestionCities() {
        return this.f3546e;
    }

    public List<BusLineItem> getBusLines() {
        return this.f3543b;
    }
}
