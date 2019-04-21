package com.amap.api.maps.offlinemap;

import android.content.Context;
import android.os.Handler;
import com.amap.api.mapcore.util.CityObject;
import com.amap.api.mapcore.util.OfflineDownloadManager;
import com.amap.api.mapcore.util.OfflineDownloadManager.C0762a;
import com.amap.api.mapcore.util.OfflineMapDownloadList;
import com.amap.api.mapcore.util.SDKLogHandler;
import com.amap.api.mapcore.util.Util;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapException;
import java.util.ArrayList;
import java.util.Iterator;

public final class OfflineMapManager {
    /* renamed from: a */
    OfflineMapDownloadList f3302a;
    /* renamed from: b */
    OfflineDownloadManager f3303b;
    /* renamed from: c */
    private Context f3304c;
    /* renamed from: d */
    private AMap f3305d;
    /* renamed from: e */
    private OfflineMapDownloadListener f3306e;
    /* renamed from: f */
    private Handler f3307f = new Handler();
    /* renamed from: g */
    private Handler f3308g = new Handler();

    /* renamed from: com.amap.api.maps.offlinemap.OfflineMapManager$1 */
    class C10571 implements C0762a {
        C10571() {
        }

        /* renamed from: a */
        public void mo8877a(final CityObject cityObject) {
            if (!(OfflineMapManager.this.f3306e == null || cityObject == null)) {
                OfflineMapManager.this.f3307f.post(new Runnable() {
                    public void run() {
                        OfflineMapManager.this.f3306e.onDownload(cityObject.mo8856c().mo9210b(), cityObject.getcompleteCode(), cityObject.getCity());
                    }
                });
            }
            if (OfflineMapManager.this.f3305d != null && cityObject.mo8856c().mo9209a(cityObject.f1366f)) {
                OfflineMapManager.this.f3305d.setLoadOfflineData(false);
                OfflineMapManager.this.f3305d.setLoadOfflineData(true);
            }
        }

        /* renamed from: b */
        public void mo8878b(final CityObject cityObject) {
            if (OfflineMapManager.this.f3306e != null && cityObject != null) {
                OfflineMapManager.this.f3307f.post(new Runnable() {
                    public void run() {
                        if (cityObject.mo8856c().equals(cityObject.f1367g) || cityObject.mo8856c().equals(cityObject.f1361a)) {
                            OfflineMapManager.this.f3306e.onCheckUpdate(true, cityObject.getCity());
                        } else {
                            OfflineMapManager.this.f3306e.onCheckUpdate(false, cityObject.getCity());
                        }
                    }
                });
            }
        }

        /* renamed from: c */
        public void mo8879c(final CityObject cityObject) {
            if (OfflineMapManager.this.f3306e != null && cityObject != null) {
                OfflineMapManager.this.f3307f.post(new Runnable() {
                    public void run() {
                        if (cityObject.mo8856c().equals(cityObject.f1361a)) {
                            OfflineMapManager.this.f3306e.onRemove(true, cityObject.getCity(), "");
                        } else {
                            OfflineMapManager.this.f3306e.onRemove(false, cityObject.getCity(), "");
                        }
                    }
                });
            }
        }
    }

    public interface OfflineMapDownloadListener {
        void onCheckUpdate(boolean z, String str);

        void onDownload(int i, int i2, String str);

        void onRemove(boolean z, String str, String str2);
    }

    public OfflineMapManager(Context context, OfflineMapDownloadListener offlineMapDownloadListener) {
        this.f3306e = offlineMapDownloadListener;
        m4500a(context);
    }

    public OfflineMapManager(Context context, OfflineMapDownloadListener offlineMapDownloadListener, AMap aMap) {
        this.f3306e = offlineMapDownloadListener;
        this.f3305d = aMap;
        m4500a(context);
    }

    /* renamed from: a */
    private void m4500a(Context context) {
        this.f3304c = context.getApplicationContext();
        OfflineDownloadManager.f1381b = false;
        this.f3303b = OfflineDownloadManager.m1830a(context);
        this.f3302a = this.f3303b.f1386f;
        this.f3303b.mo8883a(new C10571());
    }

    public void downloadByCityCode(String str) throws AMapException {
        this.f3303b.mo8897e(str);
    }

    public void downloadByCityName(String str) throws AMapException {
        this.f3303b.mo8894d(str);
    }

    public void downloadByProvinceName(String str) throws AMapException {
        try {
            m4499a();
            OfflineMapProvince itemByProvinceName = getItemByProvinceName(str);
            if (itemByProvinceName == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            Iterator it = itemByProvinceName.getCityList().iterator();
            while (it.hasNext()) {
                final String city = ((OfflineMapCity) it.next()).getCity();
                this.f3308g.post(new Runnable() {
                    public void run() {
                        try {
                            OfflineMapManager.this.f3303b.mo8894d(city);
                        } catch (AMapException e) {
                            SDKLogHandler.m2563a(e, "OfflineMapManager", "downloadByProvinceName");
                        }
                    }
                });
            }
        } catch (Throwable th) {
            if (th instanceof AMapException) {
                AMapException aMapException = (AMapException) th;
            } else {
                SDKLogHandler.m2563a(th, "OfflineMapManager", "downloadByProvinceName");
            }
        }
    }

    public void remove(String str) {
        if (this.f3303b.mo8888b(str)) {
            this.f3303b.mo8891c(str);
            return;
        }
        OfflineMapProvince c = this.f3302a.mo8924c(str);
        if (c != null && c.getCityList() != null) {
            Iterator it = c.getCityList().iterator();
            while (it.hasNext()) {
                final String city = ((OfflineMapCity) it.next()).getCity();
                this.f3308g.post(new Runnable() {
                    public void run() {
                        OfflineMapManager.this.f3303b.mo8891c(city);
                    }
                });
            }
        } else if (this.f3306e != null) {
            this.f3306e.onRemove(false, str, "没有该城市");
        }
    }

    public ArrayList<OfflineMapProvince> getOfflineMapProvinceList() {
        return this.f3302a.mo8918a();
    }

    public OfflineMapCity getItemByCityCode(String str) {
        return this.f3302a.mo8917a(str);
    }

    public OfflineMapCity getItemByCityName(String str) {
        return this.f3302a.mo8922b(str);
    }

    public OfflineMapProvince getItemByProvinceName(String str) {
        return this.f3302a.mo8924c(str);
    }

    public ArrayList<OfflineMapCity> getOfflineMapCityList() {
        return this.f3302a.mo8923b();
    }

    public ArrayList<OfflineMapCity> getDownloadingCityList() {
        return this.f3302a.mo8927e();
    }

    public ArrayList<OfflineMapProvince> getDownloadingProvinceList() {
        return this.f3302a.mo8928f();
    }

    public ArrayList<OfflineMapCity> getDownloadOfflineMapCityList() {
        return this.f3302a.mo8925c();
    }

    public ArrayList<OfflineMapProvince> getDownloadOfflineMapProvinceList() {
        return this.f3302a.mo8926d();
    }

    /* renamed from: a */
    private void m4501a(String str, String str2) throws AMapException {
        this.f3303b.mo8884a(str);
    }

    public void updateOfflineCityByCode(String str) throws AMapException {
        OfflineMapCity itemByCityCode = getItemByCityCode(str);
        if (itemByCityCode == null || itemByCityCode.getCity() == null) {
            throw new AMapException("无效的参数 - IllegalArgumentException");
        }
        m4501a(itemByCityCode.getCity(), "cityname");
    }

    public void updateOfflineCityByName(String str) throws AMapException {
        m4501a(str, "cityname");
    }

    public void updateOfflineMapProvinceByName(String str) throws AMapException {
        m4501a(str, "cityname");
    }

    /* renamed from: a */
    private void m4499a() throws AMapException {
        if (!Util.m2375c(this.f3304c)) {
            throw new AMapException("http连接失败 - ConnectionException");
        }
    }

    public void restart() {
    }

    public void stop() {
        this.f3303b.mo8886b();
    }

    public void pause() {
        this.f3303b.mo8889c();
    }

    public void destroy() {
        this.f3303b.mo8892d();
        m4503b();
        this.f3305d = null;
        this.f3307f.removeCallbacksAndMessages(null);
        this.f3307f = null;
        this.f3308g.removeCallbacksAndMessages(null);
        this.f3308g = null;
    }

    /* renamed from: b */
    private void m4503b() {
        this.f3306e = null;
    }
}
