package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.bm */
public class OfflineMapDownloadList {
    /* renamed from: a */
    public ArrayList<OfflineMapProvince> f1409a = new ArrayList();
    /* renamed from: b */
    private OfflineDBOperation f1410b;
    /* renamed from: c */
    private Context f1411c;
    /* renamed from: d */
    private Handler f1412d;

    public OfflineMapDownloadList(Context context, Handler handler) {
        this.f1411c = context;
        this.f1412d = handler;
        this.f1410b = OfflineDBOperation.m1975a(context);
    }

    /* renamed from: a */
    private void m1889a(UpdateItem updateItem) {
        if (this.f1410b != null && updateItem != null) {
            this.f1410b.mo8977a(updateItem);
        }
    }

    /* renamed from: d */
    private void m1895d(String str) {
        if (this.f1410b != null) {
            this.f1410b.mo8982c(str);
        }
    }

    /* renamed from: a */
    private boolean m1892a(int i, int i2) {
        return i2 != 1 || i <= 2 || i >= 98;
    }

    /* renamed from: b */
    private boolean m1894b(int i) {
        if (i == 4) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m1893a(OfflineMapProvince offlineMapProvince) {
        if (offlineMapProvince == null) {
            return false;
        }
        Iterator it = offlineMapProvince.getCityList().iterator();
        while (it.hasNext()) {
            if (((OfflineMapCity) it.next()).getState() != 4) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public ArrayList<OfflineMapProvince> mo8918a() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f1409a.iterator();
        while (it.hasNext()) {
            arrayList.add((OfflineMapProvince) it.next());
        }
        return arrayList;
    }

    /* renamed from: a */
    public OfflineMapCity mo8917a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        Iterator it = this.f1409a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                OfflineMapCity offlineMapCity = (OfflineMapCity) it2.next();
                if (offlineMapCity.getCode().equals(str)) {
                    return offlineMapCity;
                }
            }
        }
        return null;
    }

    /* renamed from: b */
    public OfflineMapCity mo8922b(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        Iterator it = this.f1409a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                OfflineMapCity offlineMapCity = (OfflineMapCity) it2.next();
                if (offlineMapCity.getCity().trim().equalsIgnoreCase(str.trim())) {
                    return offlineMapCity;
                }
            }
        }
        return null;
    }

    /* renamed from: c */
    public OfflineMapProvince mo8924c(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        Iterator it = this.f1409a.iterator();
        while (it.hasNext()) {
            OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
            if (offlineMapProvince.getProvinceName().trim().equalsIgnoreCase(str.trim())) {
                return offlineMapProvince;
            }
        }
        return null;
    }

    /* renamed from: b */
    public ArrayList<OfflineMapCity> mo8923b() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f1409a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                arrayList.add((OfflineMapCity) it2.next());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public void mo8920a(List<OfflineMapProvince> list) {
        OfflineMapProvince offlineMapProvince;
        if (this.f1409a.size() > 0) {
            for (int i = 0; i < this.f1409a.size(); i++) {
                offlineMapProvince = (OfflineMapProvince) this.f1409a.get(i);
                OfflineMapProvince offlineMapProvince2 = (OfflineMapProvince) list.get(i);
                m1891a(offlineMapProvince, offlineMapProvince2);
                ArrayList cityList = offlineMapProvince.getCityList();
                ArrayList cityList2 = offlineMapProvince2.getCityList();
                for (int i2 = 0; i2 < cityList.size(); i2++) {
                    m1890a((OfflineMapCity) cityList.get(i2), (OfflineMapCity) cityList2.get(i2));
                }
            }
            return;
        }
        for (OfflineMapProvince offlineMapProvince3 : list) {
            this.f1409a.add(offlineMapProvince3);
        }
    }

    /* renamed from: a */
    private void m1890a(OfflineMapCity offlineMapCity, OfflineMapCity offlineMapCity2) {
        offlineMapCity.setUrl(offlineMapCity2.getUrl());
        offlineMapCity.setVersion(offlineMapCity2.getVersion());
    }

    /* renamed from: a */
    private void m1891a(OfflineMapProvince offlineMapProvince, OfflineMapProvince offlineMapProvince2) {
        offlineMapProvince.setUrl(offlineMapProvince2.getUrl());
        offlineMapProvince.setVersion(offlineMapProvince2.getVersion());
    }

    /* renamed from: c */
    public ArrayList<OfflineMapCity> mo8925c() {
        ArrayList arrayList;
        synchronized (this.f1409a) {
            arrayList = new ArrayList();
            Iterator it = this.f1409a.iterator();
            while (it.hasNext()) {
                for (OfflineMapCity offlineMapCity : ((OfflineMapProvince) it.next()).getCityList()) {
                    if (offlineMapCity.getState() == 4) {
                        arrayList.add(offlineMapCity);
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: d */
    public ArrayList<OfflineMapProvince> mo8926d() {
        ArrayList arrayList;
        synchronized (this.f1409a) {
            arrayList = new ArrayList();
            Iterator it = this.f1409a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (offlineMapProvince.getState() == 4) {
                    arrayList.add(offlineMapProvince);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: e */
    public ArrayList<OfflineMapCity> mo8927e() {
        ArrayList arrayList;
        synchronized (this.f1409a) {
            arrayList = new ArrayList();
            Iterator it = this.f1409a.iterator();
            while (it.hasNext()) {
                for (OfflineMapCity offlineMapCity : ((OfflineMapProvince) it.next()).getCityList()) {
                    if (mo8921a(offlineMapCity.getState())) {
                        arrayList.add(offlineMapCity);
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: f */
    public ArrayList<OfflineMapProvince> mo8928f() {
        ArrayList arrayList;
        synchronized (this.f1409a) {
            arrayList = new ArrayList();
            Iterator it = this.f1409a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (mo8921a(offlineMapProvince.getState())) {
                    arrayList.add(offlineMapProvince);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public boolean mo8921a(int i) {
        return i == 0 || i == 2 || i == 3 || i == 1;
    }

    /* renamed from: a */
    public void mo8919a(CityObject cityObject) {
        String adcode = cityObject.getAdcode();
        synchronized (this.f1409a) {
            Iterator it = this.f1409a.iterator();
            loop0:
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                for (OfflineMapCity offlineMapCity : offlineMapProvince.getCityList()) {
                    if (offlineMapCity.getAdcode().trim().equals(adcode.trim())) {
                        m1887a(cityObject, offlineMapCity);
                        m1888a(cityObject, offlineMapProvince);
                        break loop0;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m1887a(CityObject cityObject, OfflineMapCity offlineMapCity) {
        int b = cityObject.mo8856c().mo9210b();
        if (cityObject.mo8856c().equals(cityObject.f1361a)) {
            m1895d(cityObject.getAdcode());
        } else {
            if (cityObject.mo8856c().equals(cityObject.f1366f)) {
                Utility.m2180a("saveJSONObjectToFile  CITY " + cityObject.getCity());
                cityObject.mo8870w().mo8964d();
            }
            if (m1892a(cityObject.getcompleteCode(), cityObject.mo8856c().mo9210b())) {
                m1889a(cityObject.mo8870w());
            }
        }
        offlineMapCity.setState(b);
        offlineMapCity.setCompleteCode(cityObject.getcompleteCode());
    }

    /* renamed from: a */
    private void m1888a(CityObject cityObject, OfflineMapProvince offlineMapProvince) {
        int b = cityObject.mo8856c().mo9210b();
        if (b == 6) {
            offlineMapProvince.setState(b);
            offlineMapProvince.setCompleteCode(0);
            m1895d(offlineMapProvince.getProvinceCode());
            try {
                Utility.m2181a(offlineMapProvince.getProvinceCode(), this.f1411c);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (m1894b(b) && m1893a(offlineMapProvince)) {
            UpdateItem updateItem;
            if (cityObject.getAdcode().equals(offlineMapProvince.getProvinceCode())) {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(cityObject.getcompleteCode());
                offlineMapProvince.setVersion(cityObject.getVersion());
                offlineMapProvince.setUrl(cityObject.getUrl());
                updateItem = new UpdateItem(offlineMapProvince, this.f1411c);
                updateItem.mo8960a(cityObject.mo8851a());
                updateItem.mo8952c(cityObject.getCode());
            } else {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(100);
                updateItem = new UpdateItem(offlineMapProvince, this.f1411c);
            }
            updateItem.mo8964d();
            m1889a(updateItem);
            Utility.m2180a("saveJSONObjectToFile  province " + updateItem.mo8953e());
        }
    }

    /* renamed from: g */
    public void mo8929g() {
        mo8930h();
        this.f1412d = null;
        this.f1410b = null;
        this.f1411c = null;
    }

    /* renamed from: h */
    public void mo8930h() {
        this.f1409a.clear();
    }
}
