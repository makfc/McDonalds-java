package com.autonavi.amap.mapcore;

import java.util.ArrayList;
import java.util.Hashtable;

public class VTMCDataCache {
    public static final int MAXSIZE = 500;
    public static final int MAX_EXPIREDTIME = 300;
    private static VTMCDataCache instance;
    static Hashtable<String, C1278f> vtmcHs = new Hashtable();
    static ArrayList<String> vtmcList = new ArrayList();
    public int mNewestTimeStamp = 0;

    public static VTMCDataCache getInstance() {
        if (instance == null) {
            instance = new VTMCDataCache();
        }
        return instance;
    }

    public void reset() {
        vtmcList.clear();
        vtmcHs.clear();
    }

    public int getSize() {
        return vtmcList.size();
    }

    private void deleteData(String str) {
        vtmcHs.remove(str);
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= vtmcList.size()) {
                return;
            }
            if (((String) vtmcList.get(i2)).equals(str)) {
                vtmcList.remove(i2);
                return;
            }
            i = i2 + 1;
        }
    }

    public synchronized C1278f getData(String str, boolean z) {
        C1278f c1278f;
        c1278f = (C1278f) vtmcHs.get(str);
        if (!z) {
            if (c1278f == null) {
                c1278f = null;
            } else if (((int) (System.currentTimeMillis() / 1000)) - c1278f.f4579c > 300) {
                c1278f = null;
            } else if (this.mNewestTimeStamp > c1278f.f4581e) {
                c1278f = null;
            }
        }
        return c1278f;
    }

    public synchronized C1278f putData(byte[] bArr) {
        C1278f c1278f;
        C1278f c1278f2 = new C1278f(bArr);
        if (this.mNewestTimeStamp < c1278f2.f4581e) {
            this.mNewestTimeStamp = c1278f2.f4581e;
        }
        c1278f = (C1278f) vtmcHs.get(c1278f2.f4578b);
        if (c1278f != null) {
            if (c1278f.f4580d.equals(c1278f2.f4580d)) {
                c1278f.mo13430a(this.mNewestTimeStamp);
            } else {
                deleteData(c1278f2.f4578b);
            }
        }
        if (vtmcList.size() > MAXSIZE) {
            vtmcHs.remove(vtmcList.get(0));
            vtmcList.remove(0);
        }
        vtmcHs.put(c1278f2.f4578b, c1278f2);
        vtmcList.add(c1278f2.f4578b);
        c1278f = c1278f2;
        return c1278f;
    }
}
