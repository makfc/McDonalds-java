package com.ensighten;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.p000v4.widget.ExploreByTouchHelper;
import com.autonavi.amap.mapcore.VTMCDataCache;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.ensighten.S */
public final class C1722S implements Runnable {
    /* renamed from: a */
    public AtomicBoolean f5722a = new AtomicBoolean(false);
    /* renamed from: b */
    private final long f5723b = 950;
    /* renamed from: c */
    private List<C1721R> f5724c;
    /* renamed from: d */
    private int f5725d = ExploreByTouchHelper.INVALID_ID;
    /* renamed from: e */
    private int f5726e;
    /* renamed from: f */
    private C1728T f5727f;

    public C1722S(C1728T c1728t, List<C1721R> list) {
        this.f5727f = c1728t;
        this.f5724c = list;
    }

    public final void run() {
        Object obj = null;
        int i = -1;
        while (true) {
            try {
                Object obj2 = obj;
                int i2 = i;
                if (!this.f5722a.get()) {
                    int i3;
                    int i4;
                    String str = this.f5727f.f5749g;
                    MediaPlayer b = this.f5727f.mo15067b();
                    if (b == null) {
                        i3 = ExploreByTouchHelper.INVALID_ID;
                    } else {
                        i3 = b.getCurrentPosition();
                    }
                    MediaPlayer b2 = this.f5727f.mo15067b();
                    if (b2 == null) {
                        i4 = ExploreByTouchHelper.INVALID_ID;
                    } else {
                        i4 = b2.getDuration();
                    }
                    int i5 = i3 / 1000;
                    i = i4 / 1000;
                    Handler handler = this.f5727f.f5748f;
                    if (i2 != i5) {
                        int size;
                        int i6;
                        C1729U c1729u;
                        Object obj3;
                        Message a;
                        boolean equals;
                        int i7 = i - 1;
                        ArrayList arrayList = (ArrayList) Ensighten.getEventManager().f5946a.get(i5);
                        if (arrayList != null) {
                            size = arrayList.size();
                            for (i6 = 0; i6 < size; i6++) {
                                c1729u = (C1729U) arrayList.get(i6);
                                int i8 = c1729u.f5758e;
                                String str2 = c1729u.f5755b;
                                obj3 = i8 != C1728T.f5744b.f5758e ? 1 : null;
                                boolean equals2 = str2.equals(C1728T.f5744b.f5755b);
                                if (obj3 != null || !equals2) {
                                    a = C1728T.m7259a(str, str2, i3, i4);
                                    equals = str2.equals("adStart");
                                    if (this.f5727f.mo15066a() || equals) {
                                        if (C1728T.f5746d != null) {
                                            handler.sendMessage(C1728T.f5746d);
                                            C1728T.f5746d = null;
                                        }
                                        if (equals) {
                                            Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['adStartTime'] = " + c1729u.f5756c + ";");
                                            Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['adLength'] = " + c1729u.f5757d + ";");
                                            Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['adPlayerName'] = 'MediaPlayer';");
                                            Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['adPosition'] = " + i8 + ";");
                                        }
                                        handler.sendMessage(a);
                                    } else {
                                        C1728T.f5746d = a;
                                    }
                                }
                                C1728T.f5744b = c1729u;
                            }
                        }
                        if (i5 == i7) {
                            arrayList = (ArrayList) Ensighten.getEventManager().f5946a.get(i);
                            if (arrayList != null) {
                                i7 = arrayList.size();
                                for (i6 = 0; i6 < i7; i6++) {
                                    c1729u = (C1729U) arrayList.get(i6);
                                    size = c1729u.f5758e;
                                    String str3 = c1729u.f5755b;
                                    obj3 = size != C1728T.f5744b.f5758e ? 1 : null;
                                    equals = str3.equals(C1728T.f5744b.f5755b);
                                    if (obj3 != null || !equals) {
                                        a = C1728T.m7259a(str, str3, i3, i4);
                                        equals = str3.equals("adStart");
                                        if (this.f5727f.mo15066a() || str3.equals("adStart")) {
                                            if (C1728T.f5746d != null) {
                                                handler.sendMessage(C1728T.f5746d);
                                                C1728T.f5746d = null;
                                            }
                                            if (equals) {
                                                Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['adStartTime'] = " + c1729u.f5756c + ";");
                                                Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['adLength'] = " + c1729u.f5757d + ";");
                                                Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['adPlayerName'] = 'MediaPlayer';");
                                                Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['adPosition'] = " + size + ";");
                                            }
                                            handler.sendMessage(a);
                                        } else {
                                            C1728T.f5746d = a;
                                        }
                                    }
                                    C1728T.f5744b = c1729u;
                                }
                            }
                        }
                        m7255a(i3, i5, i4, i, str, handler);
                        i = i5;
                    } else {
                        i = i2;
                    }
                    if (this.f5727f.mo15066a()) {
                        Object obj4;
                        this.f5727f.mo15068b(false);
                        if (!Ensighten.getEventManager().f5949d || C1728T.f5743a.get()) {
                            obj4 = obj2;
                        } else {
                            Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['videoLength'] = " + i4 + ";");
                            Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['videoName'] = '" + str + "';");
                            Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['videoPlayerName'] = 'MediaPlayer';");
                            Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['videoPlayhead'] = " + i3 + ";");
                            handler.sendMessage(C1728T.m7259a(str, "videoLoad", i3, i4));
                            C1728T.f5743a.set(true);
                            handler.sendMessage(C1728T.m7259a(str, "resume", i3, i4));
                            obj4 = 1;
                        }
                        if (this.f5727f.f5753k.get()) {
                            this.f5727f.mo15065a(false);
                            if (obj4 == null) {
                                handler.sendMessage(C1728T.m7259a(str, "resume", i3, i4));
                            }
                            obj4 = null;
                        }
                        for (C1721R c1721r : this.f5724c) {
                            r10 = new int[2];
                            int i9 = (c1721r.f5720a * i4) / 100;
                            r10[0] = i9 - 500;
                            r10[1] = i9 + VTMCDataCache.MAXSIZE;
                            if (r10[0] <= i3 && i3 <= r10[1]) {
                                if (System.currentTimeMillis() - c1721r.f5721b > 1000) {
                                    handler.sendMessage(C1728T.m7259a(str, "keyframe:" + c1721r.f5720a, i3, i4));
                                    c1721r.f5721b = System.currentTimeMillis();
                                    break;
                                }
                            }
                        }
                        this.f5725d = i3;
                        this.f5726e = i4;
                        obj = obj4;
                    } else {
                        if (!(this.f5727f.f5753k.get() || this.f5725d == ExploreByTouchHelper.INVALID_ID || this.f5727f.f5752j.get())) {
                            this.f5727f.mo15065a(true);
                            handler.sendMessage(C1728T.m7259a(str, "pause", this.f5725d, this.f5726e));
                        }
                        obj = obj2;
                    }
                    try {
                        Thread.sleep(950);
                    } catch (InterruptedException e) {
                    }
                } else {
                    return;
                }
            } catch (Exception e2) {
                if (C1845i.m7357d()) {
                    C1845i.m7353c(e2);
                    return;
                }
                return;
            }
        }
    }

    /* renamed from: a */
    private void m7255a(int i, int i2, int i3, int i4, String str, Handler handler) {
        int size;
        int i5;
        C1729U c1729u;
        Object obj;
        Message a;
        boolean equals;
        int i6 = i4 - 1;
        ArrayList arrayList = (ArrayList) Ensighten.getEventManager().f5947b.get(i2);
        if (arrayList != null) {
            size = arrayList.size();
            for (i5 = 0; i5 < size; i5++) {
                c1729u = (C1729U) arrayList.get(i5);
                int i7 = c1729u.f5758e;
                String str2 = c1729u.f5755b;
                obj = i7 != C1728T.f5745c.f5758e ? 1 : null;
                boolean equals2 = str2.equals(C1728T.f5745c.f5755b);
                if (obj != null || !equals2) {
                    a = C1728T.m7259a(str, str2, i, i3);
                    equals = str2.equals("chapterStart");
                    if (this.f5727f.mo15066a() || equals) {
                        if (C1728T.f5747e != null) {
                            handler.sendMessage(C1728T.f5747e);
                            C1728T.f5747e = null;
                        }
                        if (equals) {
                            Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['chapterStartTime'] = " + c1729u.f5756c + ";");
                            Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['chapterLength'] = " + c1729u.f5757d + ";");
                            Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['chapterPosition'] = " + i7 + ";");
                        }
                        handler.sendMessage(a);
                    } else {
                        C1728T.f5747e = a;
                    }
                }
                C1728T.f5745c = c1729u;
            }
        }
        if (i2 == i6) {
            arrayList = (ArrayList) Ensighten.getVideoHeartbeatChapters().get(i4);
            if (arrayList != null) {
                i6 = arrayList.size();
                for (i5 = 0; i5 < i6; i5++) {
                    c1729u = (C1729U) arrayList.get(i5);
                    size = c1729u.f5758e;
                    String str3 = c1729u.f5755b;
                    obj = size != C1728T.f5745c.f5758e ? 1 : null;
                    equals = str3.equals(C1728T.f5745c.f5755b);
                    if (obj != null || !equals) {
                        a = C1728T.m7259a(str, str3, i, i3);
                        boolean equals3 = str3.equals("chapterStart");
                        if (this.f5727f.mo15066a() || equals3) {
                            if (C1728T.f5747e != null) {
                                handler.sendMessage(C1728T.f5747e);
                                C1728T.f5747e = null;
                            }
                            if (equals3) {
                                Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['chapterStartTime'] = " + c1729u.f5756c + ";");
                                Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['chapterLength'] = " + c1729u.f5757d + ";");
                                Ensighten.evaluateJS("Bootstrapper.mobile.dataModel['chapterPosition'] = " + size + ";");
                            }
                            handler.sendMessage(a);
                        } else {
                            C1728T.f5747e = a;
                        }
                    }
                    C1728T.f5745c = c1729u;
                }
            }
        }
    }
}
