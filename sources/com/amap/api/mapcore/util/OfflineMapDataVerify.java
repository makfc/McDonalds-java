package com.amap.api.mapcore.util;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.bl */
public class OfflineMapDataVerify extends Thread {
    /* renamed from: a */
    private Context f1407a;
    /* renamed from: b */
    private OfflineDBOperation f1408b;

    public OfflineMapDataVerify(Context context) {
        this.f1407a = context;
        this.f1408b = OfflineDBOperation.m1975a(context);
    }

    public void run() {
        m1885a();
    }

    /* renamed from: a */
    private void m1885a() {
        ArrayList a;
        Object obj;
        ArrayList arrayList = new ArrayList();
        ArrayList a2 = this.f1408b.mo8975a();
        if (a2.size() < 1) {
            a = m1884a(this.f1407a);
            obj = 1;
        } else {
            a = a2;
            obj = null;
        }
        Iterator it = a.iterator();
        while (it.hasNext()) {
            UpdateItem updateItem = (UpdateItem) it.next();
            if (!(updateItem == null || updateItem.mo8953e() == null || updateItem.mo8955g().length() < 1)) {
                if (Thread.interrupted()) {
                    break;
                }
                if (obj != null) {
                    arrayList.add(updateItem);
                }
                if ((updateItem.f1438l == 4 || updateItem.f1438l == 7) && !m1886a(updateItem.mo8955g())) {
                    updateItem.mo8961b();
                    try {
                        Utility.m2181a(updateItem.mo8955g(), this.f1407a);
                    } catch (Exception e) {
                    }
                    arrayList.add(updateItem);
                }
            }
        }
        OfflineDownloadManager a3 = OfflineDownloadManager.m1830a(this.f1407a);
        if (a3 != null) {
            a3.mo8885a(arrayList);
        }
    }

    /* renamed from: a */
    private ArrayList<UpdateItem> m1884a(Context context) {
        ArrayList arrayList = new ArrayList();
        File file = new File(Util.m2369b(context));
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.getName().endsWith(".zip.tmp.dt")) {
                        UpdateItem a = m1883a(file2);
                        if (!(a == null || a.mo8953e() == null)) {
                            arrayList.add(a);
                            this.f1408b.mo8977a(a);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private UpdateItem m1883a(File file) {
        String a = Util.m2351a(file);
        UpdateItem updateItem = new UpdateItem();
        updateItem.mo8962b(a);
        return updateItem;
    }

    /* renamed from: a */
    private boolean m1886a(String str) {
        List<String> a = this.f1408b.mo8976a(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Util.m2350a(this.f1407a));
        stringBuilder.append("vmap/");
        int length = stringBuilder.length();
        for (String replace : a) {
            stringBuilder.replace(length, stringBuilder.length(), replace);
            if (!new File(stringBuilder.toString()).exists()) {
                return false;
            }
        }
        return true;
    }

    public void destroy() {
        this.f1407a = null;
        this.f1408b = null;
    }
}
