package com.autonavi.amap.mapcore;

import com.amap.api.mapcore.util.C0820dq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionManager extends SingalThread {
    private static final int MAX_THREAD_COUNT = 1;
    private ArrayList<BaseMapLoader> connPool = new ArrayList();
    MapCore mGLMapEngine;
    boolean threadFlag = true;
    private ExecutorService threadPool = Executors.newFixedThreadPool(1);
    private ArrayList<C1274a> threadPoolList = new ArrayList();

    public ConnectionManager(MapCore mapCore) {
        this.mGLMapEngine = mapCore;
    }

    public void shutDown() {
        if (this.connPool != null) {
            this.threadPool.shutdownNow();
        }
    }

    public void insertConntionTask(BaseMapLoader baseMapLoader) {
        synchronized (this.connPool) {
            this.connPool.add(baseMapLoader);
        }
        doAwake();
    }

    /* Access modifiers changed, original: 0000 */
    public void checkListPoolOld() {
        Iterator it = this.threadPoolList.iterator();
        while (it.hasNext()) {
            BaseMapLoader baseMapLoader = ((C1274a) it.next()).f4564a;
            if (!baseMapLoader.isRequestValid() || baseMapLoader.hasFinished()) {
                baseMapLoader.doCancel();
                it.remove();
            }
        }
    }

    private void checkListPool() {
        ArrayList arrayList = new ArrayList();
        int size = this.threadPoolList.size();
        for (int i = 0; i < size; i++) {
            C1274a c1274a = (C1274a) this.threadPoolList.get(i);
            BaseMapLoader baseMapLoader = c1274a.f4564a;
            if (!baseMapLoader.isRequestValid() || baseMapLoader.hasFinished()) {
                arrayList.add(c1274a);
                baseMapLoader.doCancel();
            }
        }
        this.threadPoolList.removeAll(arrayList);
    }

    public void run() {
        try {
            C0820dq.m2424a();
            doAsyncRequest();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void doAsyncRequest() {
        while (this.threadFlag) {
            Object obj;
            synchronized (this.connPool) {
                checkListPool();
                while (this.connPool.size() > 0) {
                    if (this.threadPoolList.size() > 1) {
                        obj = 1;
                        break;
                    }
                    C1274a c1274a = new C1274a((BaseMapLoader) this.connPool.remove(0));
                    this.threadPoolList.add(c1274a);
                    if (!this.threadPool.isShutdown()) {
                        this.threadPool.execute(c1274a);
                    }
                }
                obj = null;
            }
            if (obj != null) {
                try {
                    sleep(30);
                } catch (Exception e) {
                }
            } else if (this.threadFlag) {
                try {
                    doWait();
                } catch (Throwable th) {
                }
            }
        }
    }
}
