package com.amap.api.services.busline;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.BusSearchServerHandler;
import com.amap.api.services.core.C1128d;
import com.amap.api.services.core.ManifestConfig;
import java.util.ArrayList;

public class BusLineSearch {
    /* renamed from: a */
    Handler f3548a = new C1072b(this);
    /* renamed from: b */
    private Context f3549b;
    /* renamed from: c */
    private OnBusLineSearchListener f3550c;
    /* renamed from: d */
    private BusLineQuery f3551d;
    /* renamed from: e */
    private BusLineQuery f3552e;
    /* renamed from: f */
    private int f3553f;
    /* renamed from: g */
    private ArrayList<BusLineResult> f3554g = new ArrayList();

    /* renamed from: com.amap.api.services.busline.BusLineSearch$1 */
    class C10691 implements Runnable {
        C10691() {
        }

        public void run() {
            Message message = new Message();
            try {
                BusLineResult searchBusLine = BusLineSearch.this.searchBusLine();
                message.what = 0;
                message.obj = searchBusLine;
            } catch (AMapException e) {
                C1128d.m4975a(e, "BusLineSearch", "searchBusLineAsyn");
                message.what = e.getErrorCode();
            } finally {
                BusLineSearch.this.f3548a.sendMessage(message);
            }
        }
    }

    public interface OnBusLineSearchListener {
        void onBusLineSearched(BusLineResult busLineResult, int i);
    }

    public BusLineSearch(Context context, BusLineQuery busLineQuery) {
        this.f3549b = context.getApplicationContext();
        this.f3551d = busLineQuery;
        this.f3552e = busLineQuery.clone();
    }

    public BusLineResult searchBusLine() throws AMapException {
        ManifestConfig.m5058a(this.f3549b);
        if (!this.f3551d.weakEquals(this.f3552e)) {
            this.f3552e = this.f3551d.clone();
            this.f3553f = 0;
            if (this.f3554g != null) {
                this.f3554g.clear();
            }
        }
        BusSearchServerHandler busSearchServerHandler;
        BusLineResult a;
        if (this.f3553f == 0) {
            busSearchServerHandler = new BusSearchServerHandler(this.f3549b, this.f3551d.clone());
            a = BusLineResult.m4640a(busSearchServerHandler, (ArrayList) busSearchServerHandler.mo11981g());
            this.f3553f = a.getPageCount();
            m4642a(a);
            return a;
        }
        a = m4644b(this.f3551d.getPageNumber());
        if (a != null) {
            return a;
        }
        busSearchServerHandler = new BusSearchServerHandler(this.f3549b, this.f3551d);
        a = BusLineResult.m4640a(busSearchServerHandler, (ArrayList) busSearchServerHandler.mo11981g());
        this.f3554g.set(this.f3551d.getPageNumber(), a);
        return a;
    }

    /* renamed from: a */
    private void m4642a(BusLineResult busLineResult) {
        this.f3554g = new ArrayList();
        for (int i = 0; i < this.f3553f; i++) {
            this.f3554g.add(null);
        }
        if (this.f3553f >= 0 && m4643a(this.f3551d.getPageNumber())) {
            this.f3554g.set(this.f3551d.getPageNumber(), busLineResult);
        }
    }

    /* renamed from: a */
    private boolean m4643a(int i) {
        return i < this.f3553f && i >= 0;
    }

    /* renamed from: b */
    private BusLineResult m4644b(int i) {
        if (m4643a(i)) {
            return (BusLineResult) this.f3554g.get(i);
        }
        throw new IllegalArgumentException("page out of range");
    }

    public void setOnBusLineSearchListener(OnBusLineSearchListener onBusLineSearchListener) {
        this.f3550c = onBusLineSearchListener;
    }

    public void searchBusLineAsyn() {
        new Thread(new C10691()).start();
    }

    public void setQuery(BusLineQuery busLineQuery) {
        if (!this.f3551d.weakEquals(busLineQuery)) {
            this.f3551d = busLineQuery;
            this.f3552e = busLineQuery.clone();
        }
    }

    public BusLineQuery getQuery() {
        return this.f3551d;
    }
}
