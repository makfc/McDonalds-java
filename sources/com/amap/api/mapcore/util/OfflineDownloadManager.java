package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

/* renamed from: com.amap.api.mapcore.util.bi */
public class OfflineDownloadManager {
    /* renamed from: a */
    public static String f1380a = "";
    /* renamed from: b */
    public static boolean f1381b = false;
    /* renamed from: d */
    public static String f1382d = "";
    /* renamed from: j */
    private static volatile OfflineDownloadManager f1383j;
    /* renamed from: c */
    CopyOnWriteArrayList<CityObject> f1384c = new CopyOnWriteArrayList();
    /* renamed from: e */
    C0763b f1385e = null;
    /* renamed from: f */
    public OfflineMapDownloadList f1386f;
    /* renamed from: g */
    OfflineMapRemoveTask f1387g;
    /* renamed from: h */
    OfflineMapDataVerify f1388h = null;
    /* renamed from: i */
    private Context f1389i;
    /* renamed from: k */
    private C0762a f1390k;
    /* renamed from: l */
    private TaskManager f1391l;
    /* renamed from: m */
    private OfflineDBOperation f1392m;
    /* renamed from: n */
    private ExecutorService f1393n = null;
    /* renamed from: o */
    private ExecutorService f1394o = null;

    /* compiled from: OfflineDownloadManager */
    /* renamed from: com.amap.api.mapcore.util.bi$a */
    public interface C0762a {
        /* renamed from: a */
        void mo8877a(CityObject cityObject);

        /* renamed from: b */
        void mo8878b(CityObject cityObject);

        /* renamed from: c */
        void mo8879c(CityObject cityObject);
    }

    /* compiled from: OfflineDownloadManager */
    /* renamed from: com.amap.api.mapcore.util.bi$b */
    class C0763b extends Handler {
        public C0763b(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                message.getData();
                Object obj = message.obj;
                if (obj instanceof CityObject) {
                    CityObject cityObject = (CityObject) obj;
                    Utility.m2180a("OfflineMapHandler handleMessage CitObj  name: " + cityObject.getCity() + " complete: " + cityObject.getcompleteCode() + " status: " + cityObject.getState());
                    if (OfflineDownloadManager.this.f1390k != null) {
                        OfflineDownloadManager.this.f1390k.mo8877a(cityObject);
                        return;
                    }
                    return;
                }
                Utility.m2180a("Do not callback by CityObject! ");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private OfflineDownloadManager(Context context) {
        this.f1389i = context;
        m1835f();
    }

    /* renamed from: a */
    public static OfflineDownloadManager m1830a(Context context) {
        if (f1383j == null) {
            synchronized (OfflineDownloadManager.class) {
                if (f1383j == null && !f1381b) {
                    f1383j = new OfflineDownloadManager(context.getApplicationContext());
                }
            }
        }
        return f1383j;
    }

    /* renamed from: f */
    private void m1835f() {
        this.f1392m = OfflineDBOperation.m1975a(this.f1389i.getApplicationContext());
        this.f1385e = new C0763b(this.f1389i.getMainLooper());
        this.f1386f = new OfflineMapDownloadList(this.f1389i, this.f1385e);
        this.f1391l = TaskManager.m1934a(1);
        f1380a = Util.m2369b(this.f1389i);
        m1838g();
        this.f1388h = new OfflineMapDataVerify(this.f1389i);
        this.f1388h.start();
        Iterator it = this.f1386f.mo8918a().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                this.f1384c.add(new CityObject(this.f1389i, (OfflineMapCity) it2.next()));
            }
        }
        m1840h();
    }

    /* renamed from: g */
    private void m1838g() {
        if (!Util.m2369b(this.f1389i).equals("")) {
            String c;
            File file = new File(Util.m2369b(this.f1389i) + "offlinemapv4.png");
            if (file.exists()) {
                c = Utility.m2186c(file);
            } else {
                c = Utility.m2179a(this.f1389i, "offlinemapv4.png");
            }
            if (c != null) {
                try {
                    m1836f(c);
                } catch (JSONException e) {
                    SDKLogHandler.m2563a(e, "MapDownloadManager", "paseJson io");
                    e.printStackTrace();
                }
            }
        }
    }

    /* renamed from: f */
    private void m1836f(String str) throws JSONException {
        List b = Utility.m2183b(str);
        if (b != null && b.size() != 0) {
            this.f1386f.mo8920a(b);
        }
    }

    /* renamed from: h */
    private void m1840h() {
        Iterator it = this.f1392m.mo8975a().iterator();
        while (it.hasNext()) {
            UpdateItem updateItem = (UpdateItem) it.next();
            if (!(updateItem == null || updateItem.mo8953e() == null || updateItem.mo8955g().length() < 1)) {
                if (!(updateItem.f1438l == 4 || updateItem.f1438l == 7 || updateItem.f1438l < 0)) {
                    updateItem.f1438l = 3;
                }
                CityObject g = m1837g(updateItem.mo8953e());
                if (g != null) {
                    String f = updateItem.mo8954f();
                    if (f == null || f.equals(f1382d)) {
                        g.mo8852a(updateItem.f1438l);
                        g.setCompleteCode(updateItem.mo8958j());
                    } else {
                        this.f1392m.mo8982c(updateItem.mo8955g());
                        g.mo8852a(7);
                    }
                    List<String> a = this.f1392m.mo8976a(updateItem.mo8955g());
                    StringBuffer stringBuffer = new StringBuffer();
                    for (String append : a) {
                        stringBuffer.append(append);
                        stringBuffer.append(";");
                    }
                    g.mo8855a(stringBuffer.toString());
                    this.f1386f.mo8919a(g);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo8885a(ArrayList<UpdateItem> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            UpdateItem updateItem = (UpdateItem) it.next();
            CityObject g = m1837g(updateItem.mo8953e());
            if (g != null) {
                g.mo8853a(updateItem);
                mo8890c(g);
            }
        }
    }

    /* renamed from: a */
    public void mo8884a(final String str) {
        if (str != null) {
            if (this.f1393n == null) {
                this.f1393n = Executors.newSingleThreadExecutor();
            }
            this.f1393n.execute(new Runnable() {
                public void run() {
                    CityObject a = OfflineDownloadManager.this.m1837g(str);
                    try {
                        if (a.mo8856c().equals(a.f1366f)) {
                            String adcode = a.getAdcode();
                            if (adcode.length() > 0) {
                                adcode = OfflineDownloadManager.this.f1392m.mo8984e(adcode);
                                if (OfflineDownloadManager.f1382d.length() > 0 && !adcode.equals(OfflineDownloadManager.f1382d)) {
                                    a.mo8862i();
                                    OfflineDownloadManager.this.f1390k.mo8878b(a);
                                    return;
                                }
                            }
                            OfflineDownloadManager.this.m1841i();
                            OfflineInitBean offlineInitBean = (OfflineInitBean) new OfflineInitHandler(OfflineDownloadManager.this.f1389i, OfflineDownloadManager.f1382d).mo8912d();
                            if (OfflineDownloadManager.this.f1390k != null) {
                                if (offlineInitBean == null) {
                                    OfflineDownloadManager.this.f1390k.mo8878b(a);
                                    return;
                                } else if (offlineInitBean.mo8900a()) {
                                    OfflineDownloadManager.this.mo8881a();
                                }
                            }
                            OfflineDownloadManager.this.f1390k.mo8878b(a);
                        }
                    } catch (Exception e) {
                    } finally {
                        OfflineDownloadManager.this.f1390k.mo8878b(a);
                    }
                }
            });
        } else if (this.f1390k != null) {
            this.f1390k.mo8878b(null);
        }
    }

    /* renamed from: i */
    private void m1841i() throws AMapException {
        if (!Util.m2375c(this.f1389i)) {
            throw new AMapException("http连接失败 - ConnectionException");
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo8881a() throws AMapException {
        OfflineUpdateCityHandler offlineUpdateCityHandler = new OfflineUpdateCityHandler(this.f1389i, "");
        offlineUpdateCityHandler.mo8943a(this.f1389i);
        List list = (List) offlineUpdateCityHandler.mo8912d();
        if (this.f1384c != null) {
            this.f1386f.mo8920a(list);
        }
        Iterator it = this.f1384c.iterator();
        while (it.hasNext()) {
            CityObject cityObject = (CityObject) it.next();
            String version = cityObject.getVersion();
            if (cityObject.getState() == 4 && f1382d.length() > 0 && !version.equals(f1382d)) {
                cityObject.mo8862i();
            }
        }
    }

    /* renamed from: b */
    public boolean mo8888b(String str) {
        if (m1837g(str) == null) {
            return false;
        }
        return true;
    }

    /* renamed from: c */
    public void mo8891c(String str) {
        CityObject g = m1837g(str);
        if (g != null) {
            mo8893d(g);
            mo8882a(g);
        } else if (this.f1390k != null) {
            this.f1390k.mo8879c(g);
        }
    }

    /* renamed from: a */
    public void mo8882a(final CityObject cityObject) {
        if (this.f1387g == null) {
            this.f1387g = new OfflineMapRemoveTask(this.f1389i);
        }
        if (this.f1394o == null) {
            this.f1394o = Executors.newSingleThreadExecutor();
        }
        this.f1394o.execute(new Runnable() {
            public void run() {
                if (cityObject.mo8856c().equals(cityObject.f1361a)) {
                    OfflineDownloadManager.this.f1390k.mo8879c(cityObject);
                } else if (cityObject.getState() == 7 || cityObject.getState() == -1) {
                    OfflineDownloadManager.this.f1387g.mo8940a(cityObject);
                } else {
                    OfflineDownloadManager.this.f1387g.mo8940a(cityObject);
                    OfflineDownloadManager.this.f1390k.mo8879c(cityObject);
                }
            }
        });
    }

    /* renamed from: b */
    public void mo8887b(CityObject cityObject) {
        try {
            this.f1391l.mo8947a(cityObject, this.f1389i, null);
        } catch (AMapCoreException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    public void mo8890c(CityObject cityObject) {
        this.f1386f.mo8919a(cityObject);
        Message obtainMessage = this.f1385e.obtainMessage();
        obtainMessage.obj = cityObject;
        this.f1385e.sendMessage(obtainMessage);
    }

    /* renamed from: b */
    public void mo8886b() {
        Iterator it = this.f1384c.iterator();
        while (it.hasNext()) {
            CityObject cityObject = (CityObject) it.next();
            if (cityObject.mo8856c().equals(cityObject.f1363c) || cityObject.mo8856c().equals(cityObject.f1362b)) {
                cityObject.mo8859f();
            }
        }
    }

    /* renamed from: c */
    public void mo8889c() {
        Iterator it = this.f1384c.iterator();
        while (it.hasNext()) {
            CityObject cityObject = (CityObject) it.next();
            if (cityObject.mo8856c().equals(cityObject.f1363c)) {
                cityObject.mo8859f();
                return;
            }
        }
    }

    /* renamed from: d */
    public void mo8892d() {
        if (!(this.f1393n == null || this.f1393n.isShutdown())) {
            this.f1393n.shutdownNow();
        }
        if (this.f1388h != null) {
            if (this.f1388h.isAlive()) {
                this.f1388h.interrupt();
            }
            this.f1388h = null;
        }
        this.f1391l.mo8948b();
        this.f1386f.mo8929g();
        mo8895e();
        f1383j = null;
        f1381b = true;
    }

    /* renamed from: g */
    private CityObject m1837g(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        Iterator it = this.f1384c.iterator();
        while (it.hasNext()) {
            CityObject cityObject = (CityObject) it.next();
            if (str.equals(cityObject.getCity())) {
                return cityObject;
            }
        }
        return null;
    }

    /* renamed from: h */
    private CityObject m1839h(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        Iterator it = this.f1384c.iterator();
        while (it.hasNext()) {
            CityObject cityObject = (CityObject) it.next();
            if (str.equals(cityObject.getCode())) {
                return cityObject;
            }
        }
        return null;
    }

    /* renamed from: d */
    public void mo8894d(String str) throws AMapException {
        CityObject g = m1837g(str);
        if (g != null) {
            g.setVersion(f1382d);
            g.mo8859f();
            return;
        }
        throw new AMapException("无效的参数 - IllegalArgumentException");
    }

    /* renamed from: e */
    public void mo8897e(String str) throws AMapException {
        CityObject h = m1839h(str);
        if (h != null) {
            h.mo8859f();
            return;
        }
        throw new AMapException("无效的参数 - IllegalArgumentException");
    }

    /* renamed from: d */
    public void mo8893d(CityObject cityObject) {
        this.f1391l.mo8946a((TaskItem) cityObject);
    }

    /* renamed from: e */
    public void mo8896e(CityObject cityObject) {
        this.f1391l.mo8949b(cityObject);
    }

    /* renamed from: a */
    public void mo8883a(C0762a c0762a) {
        this.f1390k = c0762a;
    }

    /* renamed from: e */
    public void mo8895e() {
        this.f1390k = null;
    }
}
