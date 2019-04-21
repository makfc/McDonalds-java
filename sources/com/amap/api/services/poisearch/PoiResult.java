package com.amap.api.services.poisearch;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.poisearch.PoiSearch.Query;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import java.util.ArrayList;
import java.util.List;

public final class PoiResult {
    /* renamed from: a */
    private int f3983a;
    /* renamed from: b */
    private ArrayList<PoiItem> f3984b = new ArrayList();
    /* renamed from: c */
    private PoiSearchKeywordsHandler f3985c;

    /* renamed from: a */
    static PoiResult m5163a(PoiSearchKeywordsHandler poiSearchKeywordsHandler, ArrayList<PoiItem> arrayList) {
        return new PoiResult(poiSearchKeywordsHandler, arrayList);
    }

    private PoiResult(PoiSearchKeywordsHandler poiSearchKeywordsHandler, ArrayList<PoiItem> arrayList) {
        this.f3985c = poiSearchKeywordsHandler;
        this.f3983a = m5162a(poiSearchKeywordsHandler.mo12622i());
        this.f3984b = arrayList;
    }

    public int getPageCount() {
        return this.f3983a;
    }

    public Query getQuery() {
        return this.f3985c.mo12623j();
    }

    public SearchBound getBound() {
        return this.f3985c.mo12624k();
    }

    public ArrayList<PoiItem> getPois() {
        return this.f3984b;
    }

    public List<String> getSearchSuggestionKeywords() {
        return this.f3985c.mo12625l();
    }

    public List<SuggestionCity> getSearchSuggestionCitys() {
        return this.f3985c.mo12626m();
    }

    /* renamed from: a */
    private int m5162a(int i) {
        int f = this.f3985c.mo12060f();
        f = ((i + f) - 1) / f;
        if (f > 30) {
            return 30;
        }
        return f;
    }
}
