package com.autonavi.amap.mapcore;

import java.util.ArrayList;
import java.util.HashMap;

public class VMapDataCache {
    private static final int MAXSIZE = 400;
    private static VMapDataCache instance;
    HashMap<String, C1277e> vCancelMapDataHs = new HashMap();
    ArrayList<String> vCancelMapDataList = new ArrayList();
    HashMap<String, C1277e> vMapDataHs = new HashMap();
    ArrayList<String> vMapDataList = new ArrayList();

    public static VMapDataCache getInstance() {
        if (instance == null) {
            instance = new VMapDataCache();
        }
        return instance;
    }

    public synchronized void reset() {
        this.vMapDataHs.clear();
        this.vMapDataList.clear();
        this.vCancelMapDataHs.clear();
        this.vCancelMapDataList.clear();
    }

    public int getSize() {
        return this.vMapDataHs.size();
    }

    static String getKey(String str, int i) {
        return str + "-" + i;
    }

    public synchronized C1277e getRecoder(String str, int i) {
        C1277e c1277e;
        c1277e = (C1277e) this.vMapDataHs.get(getKey(str, i));
        if (c1277e != null) {
            c1277e.f4576d++;
        }
        return c1277e;
    }

    public synchronized C1277e getCancelRecoder(String str, int i) {
        C1277e c1277e;
        c1277e = (C1277e) this.vCancelMapDataHs.get(getKey(str, i));
        if (c1277e != null && (System.currentTimeMillis() / 1000) - ((long) c1277e.f4574b) > 10) {
            c1277e = null;
        }
        return c1277e;
    }

    public synchronized C1277e putRecoder(byte[] bArr, String str, int i) {
        C1277e c1277e;
        c1277e = new C1277e(str, i);
        if (c1277e.f4573a == null) {
            c1277e = null;
        } else {
            if (this.vMapDataHs.size() > 400) {
                this.vMapDataHs.remove(this.vMapDataList.get(0));
                this.vMapDataList.remove(0);
            }
            this.vMapDataHs.put(getKey(str, i), c1277e);
            this.vMapDataList.add(getKey(str, i));
        }
        return c1277e;
    }

    public synchronized C1277e putCancelRecoder(byte[] bArr, String str, int i) {
        C1277e c1277e = null;
        synchronized (this) {
            if (getRecoder(str, i) == null) {
                C1277e c1277e2 = new C1277e(str, i);
                if (c1277e2.f4573a != null) {
                    if (this.vCancelMapDataHs.size() > 400) {
                        this.vCancelMapDataHs.remove(this.vMapDataList.get(0));
                        this.vCancelMapDataList.remove(0);
                    }
                    this.vCancelMapDataHs.put(getKey(str, i), c1277e2);
                    this.vCancelMapDataList.add(getKey(str, i));
                    c1277e = c1277e2;
                }
            }
        }
        return c1277e;
    }
}
