package com.amap.api.mapcore2d;

import android.content.Context;
import com.amap.api.maps2d.AMapException;
import com.amap.api.maps2d.MapsInitializer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/* renamed from: com.amap.api.mapcore2d.e */
abstract class AsyncServer<T, V> extends MapServerBase {
    /* renamed from: a */
    protected TaskPool<T> f2613a;
    /* renamed from: d */
    private volatile boolean f2614d = true;
    /* renamed from: e */
    private Vector<Thread> f2615e = null;
    /* renamed from: f */
    private Runnable f2616f = new C09411();
    /* renamed from: g */
    private Runnable f2617g = new C09422();
    /* renamed from: h */
    private C0935bn f2618h;

    /* compiled from: AsyncServer */
    /* renamed from: com.amap.api.mapcore2d.e$1 */
    class C09411 implements Runnable {
        C09411() {
        }

        public void run() {
            String str = "run";
            Thread currentThread = Thread.currentThread();
            if (currentThread != null) {
                currentThread.setName("TaskRunDownLoad");
            }
            try {
                if (AsyncServer.this.f2615e != null) {
                    AsyncServer.this.f2615e.add(currentThread);
                }
                List list = null;
                ArrayList arrayList = null;
                while (AsyncServer.this.f2614d && !Thread.interrupted()) {
                    if (AsyncServer.this.f2324b == null) {
                        AsyncServer.this.f2614d = false;
                    } else if (MapsInitializer.getNetworkEnable()) {
                        if (AsyncServer.this.f2613a != null) {
                            arrayList = AsyncServer.this.f2613a.mo10086a(AsyncServer.this.mo10115g(), false);
                        }
                        if (arrayList == null || arrayList.size() != 0) {
                            if (AsyncServer.this.f2614d) {
                                List list2;
                                if (arrayList == null) {
                                    list2 = list;
                                } else if (!AsyncServer.this.f2614d) {
                                    return;
                                } else {
                                    if (!(AsyncServer.this.f2324b == null || AsyncServer.this.f2324b.f2387f == null)) {
                                        try {
                                            list2 = AsyncServer.this.mo10110a(arrayList);
                                        } catch (AMapException e) {
                                            C0955ck.m3888a(e, "AsyncServer", str);
                                            list2 = list;
                                        }
                                        if (list2 != null) {
                                            if (AsyncServer.this.f2613a != null) {
                                                AsyncServer.this.f2613a.mo10088a(list2, false);
                                            }
                                        }
                                    }
                                }
                                if (!AsyncServer.this.f2614d || Thread.interrupted()) {
                                    list = list2;
                                } else {
                                    try {
                                        Thread.sleep(50);
                                        list = list2;
                                    } catch (InterruptedException e2) {
                                        Thread.currentThread().interrupt();
                                        list = list2;
                                    } catch (Exception e3) {
                                        C0955ck.m3888a(e3, "AsyncServer", str);
                                        list = list2;
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                    } else {
                        try {
                            if (!Thread.interrupted()) {
                                Thread.sleep(200);
                            }
                        } catch (InterruptedException e4) {
                            Thread.currentThread().interrupt();
                        } catch (Exception e5) {
                            C0955ck.m3888a(e5, "AsyncServer", str);
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            } catch (Exception e52) {
                C0955ck.m3888a(e52, "AsyncServer", str);
            }
        }
    }

    /* compiled from: AsyncServer */
    /* renamed from: com.amap.api.mapcore2d.e$2 */
    class C09422 implements Runnable {
        C09422() {
        }

        public void run() {
            ArrayList arrayList = null;
            String str = "run";
            Thread currentThread = Thread.currentThread();
            if (currentThread != null) {
                currentThread.setName("TaskRunCach");
            }
            try {
                if (AsyncServer.this.f2615e != null) {
                    AsyncServer.this.f2615e.add(currentThread);
                }
                List list = null;
                while (AsyncServer.this.f2614d && !Thread.interrupted()) {
                    if (AsyncServer.this.f2324b == null) {
                        AsyncServer.this.f2614d = false;
                    } else {
                        ArrayList a;
                        if (AsyncServer.this.f2613a != null) {
                            a = AsyncServer.this.f2613a.mo10086a(AsyncServer.this.mo10115g(), true);
                        } else {
                            a = arrayList;
                        }
                        if (a != null && a.size() == 0) {
                            arrayList = a;
                        } else if (AsyncServer.this.f2614d) {
                            List b;
                            try {
                                b = AsyncServer.this.mo10112b(a);
                            } catch (AMapException e) {
                                C0955ck.m3888a(e, "AsyncServer", str);
                                b = list;
                            }
                            if (b != null) {
                                if (AsyncServer.this.f2613a != null && C0955ck.m3891a(AsyncServer.this.f2325c)) {
                                    AsyncServer.this.f2613a.mo10088a(b, false);
                                }
                            }
                            if (!AsyncServer.this.f2614d || Thread.interrupted()) {
                                list = b;
                                arrayList = a;
                            } else {
                                try {
                                    Thread.sleep(50);
                                    list = b;
                                    arrayList = a;
                                } catch (InterruptedException e2) {
                                    Thread.currentThread().interrupt();
                                    list = b;
                                    arrayList = a;
                                } catch (Exception e3) {
                                    C0955ck.m3888a(e3, "AsyncServer", str);
                                    list = b;
                                    arrayList = a;
                                }
                            }
                        } else {
                            return;
                        }
                    }
                }
            } catch (Exception e4) {
                C0955ck.m3888a(e4, "AsyncServer", str);
            }
        }
    }

    /* renamed from: a */
    public abstract ArrayList<T> mo10110a(ArrayList<T> arrayList) throws AMapException;

    /* renamed from: b */
    public abstract ArrayList<T> mo10112b(ArrayList<T> arrayList) throws AMapException;

    /* renamed from: f */
    public abstract int mo10114f();

    /* renamed from: g */
    public abstract int mo10115g();

    public AsyncServer(Mediator mediator, Context context) {
        super(mediator, context);
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public void mo10111a() {
        if (this.f2615e == null) {
            this.f2615e = new Vector();
        }
        this.f2618h = new C0935bn(mo10114f(), this.f2617g, this.f2616f);
        this.f2618h.mo10092a();
    }

    /* renamed from: b */
    public void mo9855b() {
        if (this.f2613a != null) {
            this.f2613a.mo10087a();
        }
        mo10113e();
        if (this.f2613a != null) {
            this.f2613a.mo10091c();
        }
        this.f2613a = null;
        this.f2617g = null;
        this.f2616f = null;
        this.f2324b = null;
        this.f2325c = null;
    }

    /* renamed from: c */
    public void mo9856c() {
        super.mo9856c();
        mo10113e();
    }

    /* renamed from: d */
    public void mo9857d() {
        if (!this.f2614d) {
            this.f2614d = true;
            if (this.f2615e == null) {
                this.f2615e = new Vector();
            }
            if (this.f2618h == null) {
                this.f2618h = new C0935bn(mo10114f(), this.f2617g, this.f2616f);
                this.f2618h.mo10092a();
            }
        }
    }

    /* renamed from: e */
    public void mo10113e() {
        this.f2614d = false;
        if (this.f2615e != null) {
            int size = this.f2615e.size();
            for (int i = 0; i < size; i++) {
                Thread thread = (Thread) this.f2615e.get(0);
                if (thread != null) {
                    thread.interrupt();
                    this.f2615e.remove(0);
                }
            }
            this.f2615e = null;
        }
        if (this.f2618h != null) {
            this.f2618h.mo10093b();
            this.f2618h = null;
        }
    }
}
