package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.AMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/* renamed from: com.amap.api.mapcore.util.br */
public class TaskManager {
    /* renamed from: a */
    private static TaskManager f1423a;
    /* renamed from: b */
    private ThreadPool f1424b;
    /* renamed from: c */
    private LinkedHashMap<String, ThreadTask> f1425c = new LinkedHashMap();
    /* renamed from: d */
    private boolean f1426d = true;

    /* renamed from: a */
    public static TaskManager m1934a(int i) {
        return TaskManager.m1935a(true, i);
    }

    /* renamed from: a */
    private static synchronized TaskManager m1935a(boolean z, int i) {
        TaskManager taskManager;
        synchronized (TaskManager.class) {
            try {
                if (f1423a == null) {
                    f1423a = new TaskManager(z, i);
                } else if (z) {
                    if (f1423a.f1424b == null) {
                        f1423a.f1424b = ThreadPool.m2831a(i);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            taskManager = f1423a;
        }
        return taskManager;
    }

    private TaskManager(boolean z, int i) {
        if (z) {
            try {
                this.f1424b = ThreadPool.m2831a(i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void mo8945a() {
        synchronized (this.f1425c) {
            if (this.f1425c.size() < 1) {
                return;
            }
            for (Entry entry : this.f1425c.entrySet()) {
                entry.getKey();
                ((OfflineMapDownloadTask) entry.getValue()).mo8938b();
            }
            this.f1425c.clear();
        }
    }

    /* renamed from: a */
    public void mo8946a(TaskItem taskItem) {
        synchronized (this.f1425c) {
            OfflineMapDownloadTask offlineMapDownloadTask = (OfflineMapDownloadTask) this.f1425c.get(taskItem.mo8834b());
            if (offlineMapDownloadTask == null) {
                return;
            }
            offlineMapDownloadTask.mo8938b();
        }
    }

    /* renamed from: a */
    public void mo8947a(TaskItem taskItem, Context context, AMap aMap) throws AMapCoreException {
        if (this.f1424b == null) {
        }
        if (!this.f1425c.containsKey(taskItem.mo8834b())) {
            OfflineMapDownloadTask offlineMapDownloadTask = new OfflineMapDownloadTask((IDownloadItem) taskItem, context.getApplicationContext(), aMap);
            synchronized (this.f1425c) {
                this.f1425c.put(taskItem.mo8834b(), offlineMapDownloadTask);
            }
        }
        this.f1424b.mo9434a((ThreadTask) this.f1425c.get(taskItem.mo8834b()));
    }

    /* renamed from: b */
    public void mo8948b() {
        mo8945a();
        ThreadPool threadPool = this.f1424b;
        ThreadPool.m2832a();
        this.f1424b = null;
        f1423a = null;
    }

    /* renamed from: b */
    public void mo8949b(TaskItem taskItem) {
        OfflineMapDownloadTask offlineMapDownloadTask = (OfflineMapDownloadTask) this.f1425c.get(taskItem.mo8834b());
        if (offlineMapDownloadTask != null) {
            synchronized (this.f1425c) {
                offlineMapDownloadTask.mo8939c();
                this.f1425c.remove(taskItem.mo8834b());
            }
        }
    }
}
