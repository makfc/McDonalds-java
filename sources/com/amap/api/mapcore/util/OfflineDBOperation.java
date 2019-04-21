package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.amap.api.mapcore.util.bx */
public class OfflineDBOperation {
    /* renamed from: a */
    private static volatile OfflineDBOperation f1449a;
    /* renamed from: b */
    private static DBOperation f1450b;
    /* renamed from: c */
    private Context f1451c;

    /* renamed from: a */
    public static OfflineDBOperation m1975a(Context context) {
        if (f1449a == null) {
            synchronized (OfflineDBOperation.class) {
                if (f1449a == null) {
                    f1449a = new OfflineDBOperation(context);
                }
            }
        }
        return f1449a;
    }

    private OfflineDBOperation(Context context) {
        this.f1451c = context;
        f1450b = m1978b(this.f1451c);
    }

    /* renamed from: b */
    private DBOperation m1978b(Context context) {
        try {
            return new DBOperation(context, OfflineDBCreator.m1970a());
        } catch (Throwable th) {
            SDKLogHandler.m2563a(th, "OfflineDB", "getDB");
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private boolean m1979b() {
        if (f1450b == null) {
            f1450b = m1978b(this.f1451c);
        }
        if (f1450b == null) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public ArrayList<UpdateItem> mo8975a() {
        ArrayList<UpdateItem> arrayList = new ArrayList();
        if (!m1979b()) {
            return arrayList;
        }
        for (UpdateItem add : f1450b.mo9332b("", UpdateItem.class)) {
            arrayList.add(add);
        }
        return arrayList;
    }

    /* renamed from: a */
    public synchronized void mo8977a(UpdateItem updateItem) {
        if (m1979b()) {
            f1450b.mo9326a((Object) updateItem, DTInfo.m1941d(updateItem.mo8955g()));
            m1977a(updateItem.mo8955g(), updateItem.mo8963c());
        }
    }

    /* renamed from: a */
    private void m1977a(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            String a = DTFileInfo.m1963a(str);
            if (f1450b.mo9332b(a, DTFileInfo.class).size() > 0) {
                f1450b.mo9328a(a, DTFileInfo.class);
            }
            String[] split = str2.split(";");
            List arrayList = new ArrayList();
            for (String dTFileInfo : split) {
                arrayList.add(new DTFileInfo(str, dTFileInfo));
            }
            f1450b.mo9331a(arrayList);
        }
    }

    /* renamed from: a */
    public synchronized List<String> mo8976a(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        if (m1979b()) {
            arrayList.addAll(m1976a(f1450b.mo9332b(DTFileInfo.m1963a(str), DTFileInfo.class)));
        }
        return arrayList;
    }

    /* renamed from: b */
    public synchronized List<String> mo8981b(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        if (m1979b()) {
            arrayList.addAll(m1976a(f1450b.mo9332b(DTFileInfo.m1964b(str), DTFileInfo.class)));
        }
        return arrayList;
    }

    /* renamed from: a */
    private List<String> m1976a(List<DTFileInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (list.size() > 0) {
            for (DTFileInfo a : list) {
                arrayList.add(a.mo8970a());
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    public synchronized void mo8982c(String str) {
        if (m1979b()) {
            f1450b.mo9328a(DTInfo.m1941d(str), DTInfo.class);
            f1450b.mo9328a(DTFileInfo.m1963a(str), DTFileInfo.class);
            f1450b.mo9328a(DTDownloadInfo.m1957a(str), DTDownloadInfo.class);
        }
    }

    /* renamed from: a */
    public void mo8978a(String str, int i, long j, long j2, long j3) {
        if (m1979b()) {
            mo8979a(str, i, j, new long[]{j2, 0, 0, 0, 0}, new long[]{j3, 0, 0, 0, 0});
        }
    }

    /* renamed from: a */
    public synchronized void mo8979a(String str, int i, long j, long[] jArr, long[] jArr2) {
        if (m1979b()) {
            f1450b.mo9326a(new DTDownloadInfo(str, j, i, jArr[0], jArr2[0]), DTDownloadInfo.m1957a(str));
        }
    }

    /* renamed from: a */
    public synchronized long[] mo8980a(String str, int i) {
        long[] jArr;
        if (m1979b()) {
            long a;
            long b;
            List b2 = f1450b.mo9332b(DTDownloadInfo.m1957a(str), DTDownloadInfo.class);
            if (b2.size() > 0) {
                a = ((DTDownloadInfo) b2.get(0)).mo8966a(i);
                b = ((DTDownloadInfo) b2.get(0)).mo8968b(i);
            } else {
                b = 0;
                a = 0;
            }
            jArr = new long[]{a, b};
        } else {
            jArr = new long[]{0, 0};
        }
        return jArr;
    }

    /* renamed from: d */
    public synchronized int mo8983d(String str) {
        int i = 0;
        synchronized (this) {
            if (m1979b()) {
                List b = f1450b.mo9332b(DTDownloadInfo.m1957a(str), DTDownloadInfo.class);
                long j = 0;
                if (b.size() > 0) {
                    j = ((DTDownloadInfo) b.get(0)).mo8965a();
                }
                i = (int) j;
            }
        }
        return i;
    }

    /* renamed from: e */
    public synchronized String mo8984e(String str) {
        String str2;
        str2 = null;
        if (m1979b()) {
            List b = f1450b.mo9332b(DTInfo.m1941d(str), DTInfo.class);
            if (b.size() > 0) {
                str2 = ((DTInfo) b.get(0)).mo8954f();
            }
        }
        return str2;
    }

    /* renamed from: f */
    public synchronized boolean mo8985f(String str) {
        boolean z = false;
        synchronized (this) {
            if (m1979b()) {
                if (f1450b.mo9332b(DTDownloadInfo.m1957a(str), DTDownloadInfo.class).size() > 0) {
                    z = true;
                }
            }
        }
        return z;
    }
}
