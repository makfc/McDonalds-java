package com.amap.api.mapcore2d;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

/* renamed from: com.amap.api.mapcore2d.bl */
class TaskPool<T> {
    /* renamed from: a */
    protected LinkedList<T> f2575a = new LinkedList();
    /* renamed from: b */
    protected final Semaphore f2576b = new Semaphore(0, false);
    /* renamed from: c */
    protected boolean f2577c = true;

    TaskPool() {
    }

    /* renamed from: a */
    public void mo10087a() {
        this.f2577c = false;
        this.f2576b.release(100);
    }

    /* renamed from: a */
    public synchronized void mo10088a(List<T> list, boolean z) {
        if (this.f2575a != null) {
            if (z) {
                this.f2575a.clear();
            }
            if (list != null) {
                this.f2575a.addAll(list);
            }
            mo10090b();
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public void mo10090b() {
        if (this.f2575a != null && this.f2577c && this.f2575a.size() != 0) {
            this.f2576b.release();
        }
    }

    /* renamed from: c */
    public void mo10091c() {
        if (this.f2575a != null) {
            this.f2575a.clear();
        }
    }

    /* renamed from: a */
    public ArrayList<T> mo10086a(int i, boolean z) {
        if (this.f2575a == null) {
            return null;
        }
        try {
            this.f2576b.acquire();
        } catch (InterruptedException e) {
        }
        try {
            if (this.f2577c) {
                return mo10089b(i, z);
            }
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: b */
    public synchronized ArrayList<T> mo10089b(int i, boolean z) {
        ArrayList<T> arrayList;
        synchronized (this) {
            if (this.f2575a == null) {
                arrayList = null;
            } else {
                int size = this.f2575a.size();
                if (i > size) {
                    i = size;
                }
                arrayList = new ArrayList(i);
                for (int i2 = 0; i2 < i; i2++) {
                    arrayList.add(this.f2575a.get(0));
                    this.f2575a.removeFirst();
                }
                mo10090b();
            }
        }
        return arrayList;
    }
}
