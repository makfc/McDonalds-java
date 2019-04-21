package com.amap.api.services.district;

import android.content.Context;
import android.os.Handler;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.DistrictServerHandler;
import com.amap.api.services.core.ManifestConfig;
import java.util.HashMap;

public class DistrictSearch {
    /* renamed from: g */
    private static HashMap<Integer, DistrictResult> f3833g;
    /* renamed from: a */
    Handler f3834a = new C1143c(this);
    /* renamed from: b */
    private Context f3835b;
    /* renamed from: c */
    private DistrictSearchQuery f3836c;
    /* renamed from: d */
    private OnDistrictSearchListener f3837d;
    /* renamed from: e */
    private DistrictSearchQuery f3838e;
    /* renamed from: f */
    private int f3839f;

    /* renamed from: com.amap.api.services.district.DistrictSearch$1 */
    class C11401 extends Thread {
        C11401() {
        }

        /* JADX WARNING: Failed to extract finally block: empty outs */
        /* JADX WARNING: Failed to extract finally block: empty outs */
        public void run() {
            /*
            r5 = this;
            r2 = new android.os.Message;
            r2.<init>();
            r1 = new com.amap.api.services.district.DistrictResult;
            r1.<init>();
            r0 = com.amap.api.services.district.DistrictSearch.this;
            r0 = r0.f3836c;
            r1.setQuery(r0);
            r0 = com.amap.api.services.district.DistrictSearch.this;	 Catch:{ AMapException -> 0x0033, Throwable -> 0x004e }
            r1 = r0.m5138b();	 Catch:{ AMapException -> 0x0033, Throwable -> 0x004e }
            if (r1 == 0) goto L_0x0023;
        L_0x001b:
            r0 = new com.amap.api.services.core.AMapException;	 Catch:{ AMapException -> 0x0033, Throwable -> 0x004e }
            r0.<init>();	 Catch:{ AMapException -> 0x0033, Throwable -> 0x004e }
            r1.setAMapException(r0);	 Catch:{ AMapException -> 0x0033, Throwable -> 0x004e }
        L_0x0023:
            r2.obj = r1;
            r0 = com.amap.api.services.district.DistrictSearch.this;
            r0 = r0.f3834a;
            if (r0 == 0) goto L_0x0032;
        L_0x002b:
            r0 = com.amap.api.services.district.DistrictSearch.this;
            r0 = r0.f3834a;
            r0.sendMessage(r2);
        L_0x0032:
            return;
        L_0x0033:
            r0 = move-exception;
            r3 = "DistrictSearch";
            r4 = "searchDistrictAnsy";
            com.amap.api.services.core.C1128d.m4975a(r0, r3, r4);	 Catch:{ all -> 0x0066 }
            r1.setAMapException(r0);	 Catch:{ all -> 0x0066 }
            r2.obj = r1;
            r0 = com.amap.api.services.district.DistrictSearch.this;
            r0 = r0.f3834a;
            if (r0 == 0) goto L_0x0032;
        L_0x0046:
            r0 = com.amap.api.services.district.DistrictSearch.this;
            r0 = r0.f3834a;
            r0.sendMessage(r2);
            goto L_0x0032;
        L_0x004e:
            r0 = move-exception;
            r3 = "DistrictSearch";
            r4 = "searchDistrictAnsyThrowable";
            com.amap.api.services.core.C1128d.m4975a(r0, r3, r4);	 Catch:{ all -> 0x0066 }
            r2.obj = r1;
            r0 = com.amap.api.services.district.DistrictSearch.this;
            r0 = r0.f3834a;
            if (r0 == 0) goto L_0x0032;
        L_0x005e:
            r0 = com.amap.api.services.district.DistrictSearch.this;
            r0 = r0.f3834a;
            r0.sendMessage(r2);
            goto L_0x0032;
        L_0x0066:
            r0 = move-exception;
            r2.obj = r1;
            r1 = com.amap.api.services.district.DistrictSearch.this;
            r1 = r1.f3834a;
            if (r1 == 0) goto L_0x0076;
        L_0x006f:
            r1 = com.amap.api.services.district.DistrictSearch.this;
            r1 = r1.f3834a;
            r1.sendMessage(r2);
        L_0x0076:
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.district.DistrictSearch$C11401.run():void");
        }
    }

    public interface OnDistrictSearchListener {
        void onDistrictSearched(DistrictResult districtResult);
    }

    public DistrictSearch(Context context) {
        this.f3835b = context.getApplicationContext();
    }

    /* renamed from: a */
    private void m5135a(DistrictResult districtResult) {
        f3833g = new HashMap();
        if (this.f3836c != null && districtResult != null && this.f3839f > 0 && this.f3839f > this.f3836c.getPageNum()) {
            f3833g.put(Integer.valueOf(this.f3836c.getPageNum()), districtResult);
        }
    }

    public DistrictSearchQuery getQuery() {
        return this.f3836c;
    }

    public void setQuery(DistrictSearchQuery districtSearchQuery) {
        this.f3836c = districtSearchQuery;
    }

    /* renamed from: a */
    private boolean m5136a() {
        if (this.f3836c == null) {
            return false;
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    public DistrictResult getPageLocal(int i) throws AMapException {
        if (m5137a(i)) {
            return (DistrictResult) f3833g.get(Integer.valueOf(i));
        }
        throw new AMapException("无效的参数 - IllegalArgumentException");
    }

    /* renamed from: a */
    private boolean m5137a(int i) {
        return i < this.f3839f && i >= 0;
    }

    /* renamed from: b */
    private DistrictResult m5138b() throws AMapException {
        DistrictResult districtResult = new DistrictResult();
        ManifestConfig.m5058a(this.f3835b);
        if (!m5136a()) {
            this.f3836c = new DistrictSearchQuery();
        }
        districtResult.setQuery(this.f3836c.clone());
        if (!this.f3836c.weakEquals(this.f3838e)) {
            this.f3839f = 0;
            this.f3838e = this.f3836c.clone();
            if (f3833g != null) {
                f3833g.clear();
            }
        }
        if (this.f3839f == 0) {
            districtResult = (DistrictResult) new DistrictServerHandler(this.f3835b, this.f3836c.clone()).mo11981g();
            if (districtResult != null) {
                this.f3839f = districtResult.getPageCount();
                m5135a(districtResult);
            }
        } else {
            districtResult = getPageLocal(this.f3836c.getPageNum());
            if (districtResult == null) {
                districtResult = (DistrictResult) new DistrictServerHandler(this.f3835b, this.f3836c.clone()).mo11981g();
                if (this.f3836c != null && districtResult != null && this.f3839f > 0 && this.f3839f > this.f3836c.getPageNum()) {
                    f3833g.put(Integer.valueOf(this.f3836c.getPageNum()), districtResult);
                }
            }
        }
        return districtResult;
    }

    public void searchDistrictAnsy() {
        new C11401().start();
    }

    public void setOnDistrictSearchListener(OnDistrictSearchListener onDistrictSearchListener) {
        this.f3837d = onDistrictSearchListener;
    }
}
