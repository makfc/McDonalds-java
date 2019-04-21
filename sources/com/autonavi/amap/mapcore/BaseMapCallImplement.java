package com.autonavi.amap.mapcore;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

public abstract class BaseMapCallImplement implements IBaseMapCallback, IMapCallback {
    private ArrayList<MapSourceGridData> bldReqMapGrids = new ArrayList();
    ConnectionManager connectionManager = null;
    private ArrayList<MapSourceGridData> curBldMapGrids = new ArrayList();
    private ArrayList<MapSourceGridData> curIndoorMapGirds = new ArrayList();
    private ArrayList<MapSourceGridData> curPoiMapGrids = new ArrayList();
    private ArrayList<MapSourceGridData> curRegionMapGrids = new ArrayList();
    private ArrayList<MapSourceGridData> curRoadMapGrids = new ArrayList();
    private ArrayList<MapSourceGridData> curScreenGirds = new ArrayList();
    private ArrayList<MapSourceGridData> curStiMapGirds = new ArrayList();
    private ArrayList<MapSourceGridData> curVectmcMapGirds = new ArrayList();
    private ArrayList<MapSourceGridData> indoorMapGrids = new ArrayList();
    Object mapGridFillLock = new Object();
    private ArrayList<MapSourceGridData> poiReqMapGrids = new ArrayList();
    private ArrayList<MapSourceGridData> regionReqMapGrids = new ArrayList();
    private ArrayList<MapSourceGridData> roadReqMapGrids = new ArrayList();
    private ArrayList<MapSourceGridData> stiReqMapGirds = new ArrayList();
    TextTextureGenerator textTextureGenerator = null;
    C1276d tileProcessCtrl = null;
    private ArrayList<MapSourceGridData> vectmcReqMapGirds = new ArrayList();
    private ArrayList<MapSourceGridData> versionMapGrids = new ArrayList();

    public void OnMapDataRequired(MapCore mapCore, int i, String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            ArrayList reqGridList = getReqGridList(i);
            if (reqGridList != null) {
                reqGridList.clear();
                for (String mapSourceGridData : strArr) {
                    reqGridList.add(new MapSourceGridData(mapSourceGridData, i));
                }
                if (i != 5) {
                    proccessRequiredData(mapCore, reqGridList, i);
                }
            }
        }
    }

    public void OnMapProcessEvent(MapCore mapCore) {
    }

    public ArrayList<MapSourceGridData> getReqGridList(int i) {
        if (i == 0) {
            return this.roadReqMapGrids;
        }
        if (i == 1) {
            return this.bldReqMapGrids;
        }
        if (i == 7) {
            return this.regionReqMapGrids;
        }
        if (i == 8) {
            return this.poiReqMapGrids;
        }
        if (i == 4) {
            return this.vectmcReqMapGirds;
        }
        if (i == 5) {
            return this.curScreenGirds;
        }
        if (i == 3) {
            return this.stiReqMapGirds;
        }
        if (i == 9) {
            return this.versionMapGrids;
        }
        if (i == 10) {
            return this.indoorMapGrids;
        }
        return null;
    }

    public void OnMapSurfaceRenderer(GL10 gl10, MapCore mapCore, int i) {
        if (i == 11) {
            synchronized (this.mapGridFillLock) {
                mapCore.fillCurGridListWithDataType(this.curPoiMapGrids, 8);
                mapCore.fillCurGridListWithDataType(this.curRoadMapGrids, 0);
                mapCore.fillCurGridListWithDataType(this.curRegionMapGrids, 7);
                mapCore.fillCurGridListWithDataType(this.curBldMapGrids, 1);
                mapCore.fillCurGridListWithDataType(this.curVectmcMapGirds, 4);
                mapCore.fillCurGridListWithDataType(this.curStiMapGirds, 3);
                mapCore.fillCurGridListWithDataType(this.curIndoorMapGirds, 10);
            }
        }
    }

    public ArrayList<MapSourceGridData> getCurGridList(int i) {
        if (i == 0) {
            return this.curRoadMapGrids;
        }
        if (i == 1) {
            return this.curBldMapGrids;
        }
        if (i == 7) {
            return this.curRegionMapGrids;
        }
        if (i == 8) {
            return this.curPoiMapGrids;
        }
        if (i == 4) {
            return this.curVectmcMapGirds;
        }
        if (i == 5) {
            return this.curScreenGirds;
        }
        if (i == 10) {
            return this.curIndoorMapGirds;
        }
        if (i == 3) {
            return this.curStiMapGirds;
        }
        return null;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public boolean isGridsInScreen(java.util.ArrayList<com.autonavi.amap.mapcore.MapSourceGridData> r7, int r8) {
        /*
        r6 = this;
        r2 = 1;
        r1 = 0;
        r0 = r7.size();	 Catch:{ Exception -> 0x0042 }
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        r0 = r1;
    L_0x0009:
        return r0;
    L_0x000a:
        r0 = r6.isMapEngineValid();	 Catch:{ Exception -> 0x0042 }
        if (r0 != 0) goto L_0x0012;
    L_0x0010:
        r0 = r1;
        goto L_0x0009;
    L_0x0012:
        r4 = r6.mapGridFillLock;	 Catch:{ Exception -> 0x0042 }
        monitor-enter(r4);	 Catch:{ Exception -> 0x0042 }
        r5 = r6.getCurGridList(r8);	 Catch:{ all -> 0x003f }
        if (r5 != 0) goto L_0x001e;
    L_0x001b:
        monitor-exit(r4);	 Catch:{ all -> 0x003f }
        r0 = r2;
        goto L_0x0009;
    L_0x001e:
        r3 = r1;
    L_0x001f:
        r0 = r7.size();	 Catch:{ all -> 0x003f }
        if (r3 >= r0) goto L_0x003c;
    L_0x0025:
        r0 = r7.get(r3);	 Catch:{ all -> 0x003f }
        r0 = (com.autonavi.amap.mapcore.MapSourceGridData) r0;	 Catch:{ all -> 0x003f }
        r0 = r0.getGridName();	 Catch:{ all -> 0x003f }
        r0 = r6.isGridInList(r0, r5);	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x0038;
    L_0x0035:
        monitor-exit(r4);	 Catch:{ all -> 0x003f }
        r0 = r2;
        goto L_0x0009;
    L_0x0038:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x001f;
    L_0x003c:
        monitor-exit(r4);	 Catch:{ all -> 0x003f }
        r0 = r1;
        goto L_0x0009;
    L_0x003f:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003f }
        throw r0;	 Catch:{ Exception -> 0x0042 }
    L_0x0042:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r2;
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.amap.mapcore.BaseMapCallImplement.isGridsInScreen(java.util.ArrayList, int):boolean");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public boolean isGridInScreen(int r5, java.lang.String r6) {
        /*
        r4 = this;
        r1 = 1;
        r0 = 0;
        r2 = r4.isMapEngineValid();	 Catch:{ Exception -> 0x001e }
        if (r2 != 0) goto L_0x0009;
    L_0x0008:
        return r0;
    L_0x0009:
        r2 = r4.mapGridFillLock;	 Catch:{ Exception -> 0x001e }
        monitor-enter(r2);	 Catch:{ Exception -> 0x001e }
        r3 = r4.getCurGridList(r5);	 Catch:{ all -> 0x001b }
        r3 = r4.isGridInList(r6, r3);	 Catch:{ all -> 0x001b }
        if (r3 == 0) goto L_0x0019;
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x001b }
        r0 = r1;
        goto L_0x0008;
    L_0x0019:
        monitor-exit(r2);	 Catch:{ all -> 0x001b }
        goto L_0x0008;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001b }
        throw r0;	 Catch:{ Exception -> 0x001e }
    L_0x001e:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.amap.mapcore.BaseMapCallImplement.isGridInScreen(int, java.lang.String):boolean");
    }

    private boolean isGridInList(String str, ArrayList<MapSourceGridData> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (((MapSourceGridData) arrayList.get(i)).getGridName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public boolean isIndoorGridsInScreen(java.util.ArrayList<com.autonavi.amap.mapcore.MapSourceGridData> r7, int r8) {
        /*
        r6 = this;
        r2 = 1;
        r1 = 0;
        r0 = r7.size();	 Catch:{ Exception -> 0x0042 }
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        r0 = r1;
    L_0x0009:
        return r0;
    L_0x000a:
        r0 = r6.isMapEngineValid();	 Catch:{ Exception -> 0x0042 }
        if (r0 != 0) goto L_0x0012;
    L_0x0010:
        r0 = r1;
        goto L_0x0009;
    L_0x0012:
        r4 = r6.mapGridFillLock;	 Catch:{ Exception -> 0x0042 }
        monitor-enter(r4);	 Catch:{ Exception -> 0x0042 }
        r5 = r6.getCurGridList(r8);	 Catch:{ all -> 0x003f }
        if (r5 != 0) goto L_0x001e;
    L_0x001b:
        monitor-exit(r4);	 Catch:{ all -> 0x003f }
        r0 = r2;
        goto L_0x0009;
    L_0x001e:
        r3 = r1;
    L_0x001f:
        r0 = r7.size();	 Catch:{ all -> 0x003f }
        if (r3 >= r0) goto L_0x003c;
    L_0x0025:
        r0 = r7.get(r3);	 Catch:{ all -> 0x003f }
        r0 = (com.autonavi.amap.mapcore.MapSourceGridData) r0;	 Catch:{ all -> 0x003f }
        r0 = r0.getKeyGridName();	 Catch:{ all -> 0x003f }
        r0 = r6.isIndoorGridInList(r0, r5);	 Catch:{ all -> 0x003f }
        if (r0 == 0) goto L_0x0038;
    L_0x0035:
        monitor-exit(r4);	 Catch:{ all -> 0x003f }
        r0 = r2;
        goto L_0x0009;
    L_0x0038:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x001f;
    L_0x003c:
        monitor-exit(r4);	 Catch:{ all -> 0x003f }
        r0 = r1;
        goto L_0x0009;
    L_0x003f:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x003f }
        throw r0;	 Catch:{ Exception -> 0x0042 }
    L_0x0042:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r2;
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.amap.mapcore.BaseMapCallImplement.isIndoorGridsInScreen(java.util.ArrayList, int):boolean");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    public boolean isIndoorGridInScreen(int r7, java.lang.String r8, short r9) {
        /*
        r6 = this;
        r1 = 1;
        r0 = 0;
        r2 = r6.isMapEngineValid();	 Catch:{ Exception -> 0x003f }
        if (r2 != 0) goto L_0x0009;
    L_0x0008:
        return r0;
    L_0x0009:
        r2 = r6.mapGridFillLock;	 Catch:{ Exception -> 0x003f }
        monitor-enter(r2);	 Catch:{ Exception -> 0x003f }
        r3 = r6.getCurGridList(r7);	 Catch:{ all -> 0x003c }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003c }
        r4.<init>();	 Catch:{ all -> 0x003c }
        r4 = r4.append(r7);	 Catch:{ all -> 0x003c }
        r5 = "-";
        r4 = r4.append(r5);	 Catch:{ all -> 0x003c }
        r4 = r4.append(r8);	 Catch:{ all -> 0x003c }
        r5 = "-";
        r4 = r4.append(r5);	 Catch:{ all -> 0x003c }
        r4 = r4.append(r9);	 Catch:{ all -> 0x003c }
        r4 = r4.toString();	 Catch:{ all -> 0x003c }
        r3 = r6.isIndoorGridInList(r4, r3);	 Catch:{ all -> 0x003c }
        if (r3 == 0) goto L_0x003a;
    L_0x0037:
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
        r0 = r1;
        goto L_0x0008;
    L_0x003a:
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
        goto L_0x0008;
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x003c }
        throw r0;	 Catch:{ Exception -> 0x003f }
    L_0x003f:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.amap.mapcore.BaseMapCallImplement.isIndoorGridInScreen(int, java.lang.String, short):boolean");
    }

    private boolean isIndoorGridInList(String str, ArrayList<MapSourceGridData> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (((MapSourceGridData) arrayList.get(i)).getKeyGridName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void proccessRequiredData(MapCore mapCore, ArrayList<MapSourceGridData> arrayList, int i) {
        MapSourceGridData mapSourceGridData;
        int i2 = 0;
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            mapSourceGridData = (MapSourceGridData) arrayList.get(i3);
            if (this.tileProcessCtrl == null || !this.tileProcessCtrl.mo13428b(mapSourceGridData.getKeyGridName())) {
                if (i == 4) {
                    VTMCDataCache instance = VTMCDataCache.getInstance();
                    C1278f data = instance.getData(mapSourceGridData.getGridName(), true);
                    C1278f data2 = instance.getData(mapSourceGridData.getGridName(), false);
                    if (data != null) {
                        mapSourceGridData.obj = data.f4580d;
                    }
                    if (data2 == null || data2.f4577a == null || data2.f4577a.length <= 0) {
                        arrayList2.add(mapSourceGridData);
                    } else {
                        mapCore.putMapData(data2.f4577a, 0, data2.f4577a.length, i, data2.f4579c);
                    }
                } else {
                    try {
                        String str = mapSourceGridData.gridName;
                        if (i == 10) {
                            str = mapSourceGridData.gridName + "-" + mapSourceGridData.mIndoorIndex;
                        }
                        C1277e recoder = VMapDataCache.getInstance().getRecoder(str, i);
                        if (recoder == null || recoder.f4573a == null || recoder.f4573a.length() > 0) {
                        }
                        arrayList2.add(mapSourceGridData);
                    } catch (Exception e) {
                    }
                }
            }
        }
        if (arrayList2.size() > 0) {
            BaseMapLoader indoorMapLoader;
            if (i == 10) {
                indoorMapLoader = new IndoorMapLoader(mapCore, this, i);
            } else if (i == 11) {
                indoorMapLoader = null;
            } else {
                indoorMapLoader = new NormalMapLoader(mapCore, this, i);
            }
            while (i2 < arrayList2.size()) {
                mapSourceGridData = (MapSourceGridData) arrayList2.get(i2);
                this.tileProcessCtrl.mo13429c(mapSourceGridData.getKeyGridName());
                indoorMapLoader.addReuqestTiles(mapSourceGridData);
                i2++;
            }
            if (this.connectionManager != null) {
                this.connectionManager.insertConntionTask(indoorMapLoader);
            }
        }
    }

    public void OnMapSurfaceCreate(MapCore mapCore) {
    }

    public synchronized void onPause() {
        try {
            if (this.connectionManager != null) {
                this.connectionManager.threadFlag = false;
                if (this.connectionManager.isAlive()) {
                    this.connectionManager.interrupt();
                    this.connectionManager.shutDown();
                    this.connectionManager = null;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return;
    }

    public synchronized void onResume(MapCore mapCore) {
        try {
            this.connectionManager = new ConnectionManager(mapCore);
            this.tileProcessCtrl = new C1276d();
            this.connectionManager.start();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return;
    }

    public void OnMapDestory(MapCore mapCore) {
        try {
            destoryMap(mapCore);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public byte[] OnMapCharsWidthsRequired(MapCore mapCore, int[] iArr, int i, int i2) {
        if (this.textTextureGenerator == null) {
            this.textTextureGenerator = new TextTextureGenerator();
        }
        return this.textTextureGenerator.getCharsWidths(iArr);
    }

    public void OnMapLabelsRequired(MapCore mapCore, int[] iArr, int i) {
        if (iArr != null && i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = iArr[i2];
                this.textTextureGenerator = new TextTextureGenerator();
                byte[] textPixelBuffer = this.textTextureGenerator.getTextPixelBuffer(i3);
                if (textPixelBuffer != null) {
                    mapCore.putCharbitmap(i3, textPixelBuffer);
                }
            }
        }
    }

    public synchronized void newMap(MapCore mapCore) {
        this.connectionManager = new ConnectionManager(mapCore);
        this.tileProcessCtrl = new C1276d();
        this.connectionManager.start();
    }

    public synchronized void destoryMap(MapCore mapCore) {
        if (this.connectionManager != null) {
            this.connectionManager.threadFlag = false;
            if (this.connectionManager.isAlive()) {
                try {
                    this.connectionManager.interrupt();
                    this.connectionManager.shutDown();
                    this.connectionManager = null;
                } catch (Throwable th) {
                    this.connectionManager.shutDown();
                    this.connectionManager = null;
                }
            }
        }
        if (this.tileProcessCtrl != null) {
            this.tileProcessCtrl.mo13425a();
        }
        return;
    }
}
