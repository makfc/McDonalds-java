package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.AMapException;
import com.mcdonalds.sdk.connectors.autonavi.AutoNaviConnector;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.amap.api.mapcore.util.cj */
public abstract class ProtocalHandler<T, V> extends Request {
    /* renamed from: a */
    protected T f1400a;
    /* renamed from: b */
    protected int f1401b = 1;
    /* renamed from: c */
    protected String f1402c = "";
    /* renamed from: d */
    protected Context f1403d;
    /* renamed from: e */
    protected final int f1404e = 5000;
    /* renamed from: f */
    protected final int f1405f = AutoNaviConnector.DEFAULT_SEARCH_RADIUS;
    /* renamed from: j */
    private int f1406j = 1;

    /* renamed from: b */
    public abstract V mo8910b(String str) throws AMapException;

    public ProtocalHandler(Context context, T t) {
        m1872a(context, t);
    }

    /* renamed from: a */
    private void m1872a(Context context, T t) {
        this.f1403d = context;
        this.f1400a = t;
    }

    /* renamed from: d */
    public V mo8912d() throws AMapException {
        if (this.f1400a != null) {
            return m1873h();
        }
        return null;
    }

    /* renamed from: h */
    private V m1873h() throws AMapException {
        int i = 0;
        V v = null;
        while (i < this.f1401b) {
            try {
                NetManger a = NetManger.m2819a(false);
                mo8903a(ProxyUtil.m2471a(this.f1403d));
                v = mo8942a(a.mo9429d(this));
                i = this.f1401b;
            } catch (AMapException e) {
                SDKLogHandler.m2563a(e, "ProtocalHandler", "getDataMayThrow AMapException");
                e.printStackTrace();
                i++;
                if (i >= this.f1401b) {
                    throw new AMapException(e.getErrorMessage());
                }
            } catch (AMapCoreException e2) {
                SDKLogHandler.m2563a(e2, "ProtocalHandler", "getDataMayThrow AMapCoreException");
                e2.printStackTrace();
                i++;
                if (i < this.f1401b) {
                    try {
                        Thread.sleep((long) (this.f1406j * 1000));
                    } catch (InterruptedException e3) {
                        SDKLogHandler.m2563a(e2, "ProtocalHandler", "getDataMayThrow InterruptedException");
                        e2.printStackTrace();
                        throw new AMapException(e2.getMessage());
                    }
                }
                mo8913e();
                throw new AMapException(e2.mo9283a());
            }
        }
        return v;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public V mo8911b(byte[] bArr) throws AMapException {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Exception e) {
            SDKLogHandler.m2563a(e, "ProtocalHandler", "loadData Exception");
            e.printStackTrace();
            str = null;
        }
        if (str == null || str.equals("")) {
            return null;
        }
        Util.m2359a(str);
        return mo8910b(str);
    }

    /* renamed from: c */
    public Map<String, String> mo8907c() {
        HashMap hashMap = new HashMap();
        hashMap.put(TransactionStateUtil.CONTENT_TYPE_HEADER, "application/x-www-form-urlencoded");
        hashMap.put("Accept-Encoding", "gzip");
        hashMap.put("User-Agent", ConfigableConst.f2124d);
        hashMap.put("X-INFO", ClientInfo.m2400a(this.f1403d, Util.m2377e(), null, false));
        return hashMap;
    }

    /* renamed from: a */
    private V mo8942a(byte[] bArr) throws AMapException {
        return mo8911b(bArr);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: e */
    public V mo8913e() {
        return null;
    }
}
