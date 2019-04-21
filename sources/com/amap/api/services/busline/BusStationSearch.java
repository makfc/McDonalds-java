package com.amap.api.services.busline;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.BusSearchServerHandler;
import com.amap.api.services.core.C1128d;
import com.amap.api.services.core.ManifestConfig;
import java.util.ArrayList;

public class BusStationSearch {
    /* renamed from: a */
    Handler f3571a = new C1074d(this);
    /* renamed from: b */
    private Context f3572b;
    /* renamed from: c */
    private OnBusStationSearchListener f3573c;
    /* renamed from: d */
    private BusStationQuery f3574d;
    /* renamed from: e */
    private BusStationQuery f3575e;
    /* renamed from: f */
    private ArrayList<BusStationResult> f3576f = new ArrayList();
    /* renamed from: g */
    private int f3577g;

    /* renamed from: com.amap.api.services.busline.BusStationSearch$1 */
    class C10701 implements Runnable {
        C10701() {
        }

        public void run() {
            Message message = new Message();
            try {
                BusStationResult searchBusStation = BusStationSearch.this.searchBusStation();
                message.what = 0;
                message.obj = searchBusStation;
            } catch (AMapException e) {
                C1128d.m4975a(e, "BusStationSearch", "searchBusStationAsyn");
                message.what = e.getErrorCode();
            } finally {
                BusStationSearch.this.f3571a.sendMessage(message);
            }
        }
    }

    public interface OnBusStationSearchListener {
        void onBusStationSearched(BusStationResult busStationResult, int i);
    }

    public BusStationSearch(Context context, BusStationQuery busStationQuery) {
        this.f3572b = context.getApplicationContext();
        this.f3574d = busStationQuery;
    }

    public BusStationResult searchBusStation() throws AMapException {
        ManifestConfig.m5058a(this.f3572b);
        if (!this.f3574d.weakEquals(this.f3575e)) {
            this.f3575e = this.f3574d.clone();
            this.f3577g = 0;
            if (this.f3576f != null) {
                this.f3576f.clear();
            }
        }
        BusSearchServerHandler busSearchServerHandler;
        BusStationResult a;
        if (this.f3577g == 0) {
            busSearchServerHandler = new BusSearchServerHandler(this.f3572b, this.f3574d);
            a = BusStationResult.m4648a(busSearchServerHandler, (ArrayList) busSearchServerHandler.mo11981g());
            this.f3577g = a.getPageCount();
            m4650a(a);
            return a;
        }
        a = m4652b(this.f3574d.getPageNumber());
        if (a != null) {
            return a;
        }
        busSearchServerHandler = new BusSearchServerHandler(this.f3572b, this.f3574d);
        a = BusStationResult.m4648a(busSearchServerHandler, (ArrayList) busSearchServerHandler.mo11981g());
        this.f3576f.set(this.f3574d.getPageNumber(), a);
        return a;
    }

    /* renamed from: a */
    private void m4650a(BusStationResult busStationResult) {
        this.f3576f = new ArrayList();
        for (int i = 0; i <= this.f3577g; i++) {
            this.f3576f.add(null);
        }
        if (this.f3577g > 0) {
            this.f3576f.set(this.f3574d.getPageNumber(), busStationResult);
        }
    }

    /* renamed from: a */
    private boolean m4651a(int i) {
        return i <= this.f3577g && i >= 0;
    }

    /* renamed from: b */
    private BusStationResult m4652b(int i) {
        if (m4651a(i)) {
            return (BusStationResult) this.f3576f.get(i);
        }
        throw new IllegalArgumentException("page out of range");
    }

    public void setOnBusStationSearchListener(OnBusStationSearchListener onBusStationSearchListener) {
        this.f3573c = onBusStationSearchListener;
    }

    public void searchBusStationAsyn() {
        new Thread(new C10701()).start();
    }

    public void setQuery(BusStationQuery busStationQuery) {
        if (!busStationQuery.weakEquals(this.f3574d)) {
            this.f3574d = busStationQuery;
        }
    }

    public BusStationQuery getQuery() {
        return this.f3574d;
    }
}
