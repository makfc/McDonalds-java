package com.amap.api.mapcore2d;

import android.content.Context;
import com.amap.api.maps2d.AMapException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.amap.api.mapcore2d.bs */
class TileServer extends AsyncServer<TileCoordinate, TileCoordinate> implements ViewProportyChangedListenner {
    /* renamed from: d */
    private Context f2621d;
    /* renamed from: e */
    private C0886am f2622e;
    /* renamed from: f */
    private Mediator f2623f;
    /* renamed from: g */
    private InprocessingTiles f2624g = new InprocessingTiles();

    /* renamed from: b */
    public void mo9855b() {
        super.mo9855b();
        this.f2624g.clear();
        if (this.f2623f != null && this.f2623f.f2384c != null) {
            this.f2623f.f2384c.mo9901b((ViewProportyChangedListenner) this);
        }
    }

    public TileServer(Mediator mediator, Context context, C0886am c0886am) {
        super(mediator, context);
        this.f2622e = c0886am;
        this.f2621d = context;
        this.f2613a = new C0936bo();
        mediator.f2384c.mo9896a((ViewProportyChangedListenner) this);
        this.f2623f = mediator;
        mo10111a();
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: a */
    public ArrayList<TileCoordinate> mo10110a(ArrayList<TileCoordinate> arrayList) throws AMapException {
        if (arrayList == null || arrayList.size() == 0 || this.f2324b == null || this.f2324b.f2386e == null || this.f2324b.f2386e.f2355a == null) {
            return null;
        }
        mo10119a((List) arrayList);
        if (arrayList.size() == 0) {
            return null;
        }
        ArrayList<TileCoordinate> arrayList2;
        if (this.f2622e.f2246j == null && this.f2622e.f2247k == null) {
            arrayList2 = null;
        } else {
            TileServerHandler tileServerHandler = new TileServerHandler(this.f2621d, arrayList, this.f2622e.f2247k);
            tileServerHandler.mo10121a(this.f2622e);
            arrayList2 = (ArrayList) tileServerHandler.mo10076a();
            tileServerHandler.mo10121a(null);
        }
        m3765c(arrayList);
        if (this.f2324b == null || this.f2324b.f2386e == null) {
            return arrayList2;
        }
        this.f2324b.f2386e.mo9883b();
        return arrayList2;
    }

    /* renamed from: a */
    public void mo10119a(List<TileCoordinate> list) {
        if (list != null) {
            int size = list.size();
            if (size != 0) {
                int i = 0;
                while (i < size) {
                    int i2;
                    if (this.f2624g.mo9756b((TileCoordinate) list.get(i))) {
                        i2 = i;
                        i = size;
                    } else {
                        list.remove(i);
                        i2 = i - 1;
                        i = size - 1;
                    }
                    size = i;
                    i = i2 + 1;
                }
            }
        }
    }

    /* renamed from: c */
    private void m3765c(ArrayList<TileCoordinate> arrayList) {
        if (arrayList != null && this.f2624g != null) {
            int size = arrayList.size();
            if (size != 0) {
                for (int i = 0; i < size; i++) {
                    this.f2624g.mo9755a((TileCoordinate) arrayList.get(i));
                }
            }
        }
    }

    /* renamed from: a */
    private void m3763a(ArrayList<TileCoordinate> arrayList, boolean z) {
        if (this.f2613a != null && arrayList != null && arrayList.size() != 0) {
            this.f2613a.mo10088a((List) arrayList, z);
        }
    }

    /* renamed from: i */
    public void mo9859i() {
        mo10118a(false, false);
    }

    /* renamed from: j */
    private boolean m3766j() {
        if (this.f2324b == null || this.f2324b.f2386e == null || this.f2324b.f2386e.f2355a == null || this.f2324b.f2386e.f2355a.size() <= 0) {
            return false;
        }
        return this.f2622e.mo9759a();
    }

    /* renamed from: a */
    private ArrayList<TileCoordinate> m3762a(ArrayList<TileCoordinate> arrayList, C0886am c0886am, float f, boolean z) {
        ArrayList<TileCoordinate> arrayList2 = new ArrayList();
        if (arrayList == null || c0886am == null || !c0886am.mo9759a() || c0886am.f2252p == null) {
            return null;
        }
        c0886am.f2252p.clear();
        if (f > ((float) c0886am.f2239c) || f < ((float) c0886am.f2240d)) {
            return null;
        }
        int size = arrayList.size();
        if (size <= 0) {
            return null;
        }
        for (int i = 0; i < size; i++) {
            TileCoordinate tileCoordinate = (TileCoordinate) arrayList.get(i);
            if (tileCoordinate != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(tileCoordinate.f2595b);
                stringBuilder.append("-");
                stringBuilder.append(tileCoordinate.f2596c);
                stringBuilder.append("-");
                stringBuilder.append(tileCoordinate.f2597d);
                int a = c0886am.f2250n.mo9918a(stringBuilder.toString());
                TileCoordinate tileCoordinate2 = new TileCoordinate(tileCoordinate.f2595b, tileCoordinate.f2596c, tileCoordinate.f2597d, c0886am.f2248l);
                tileCoordinate2.f2600g = a;
                tileCoordinate2.f2599f = tileCoordinate.f2599f;
                c0886am.f2252p.add(tileCoordinate2);
                if (!(!m3764a(tileCoordinate2) || z || this.f2624g.contains(tileCoordinate2))) {
                    if (!c0886am.f2242f) {
                        tileCoordinate2.f2594a = -1;
                    }
                    arrayList2.add(tileCoordinate2);
                }
            }
        }
        return arrayList2;
    }

    /* renamed from: a */
    public void mo10118a(boolean z, boolean z2) {
        if (m3766j()) {
            ArrayList a = this.f2324b.f2390i.mo9847a(this.f2324b.f2390i.f2316l, this.f2324b.f2390i.mo9851b(), this.f2324b.f2384c.mo9903c(), this.f2324b.f2384c.mo9904d());
            if (a != null && a.size() > 0) {
                ArrayList a2 = m3762a(a, this.f2622e, this.f2324b.f2384c.mo9905e(), z2);
                if (a2 != null) {
                    m3763a(a2, true);
                    a2.clear();
                }
                a.clear();
                this.f2324b.f2384c.mo9907g().invalidate();
            }
        }
    }

    /* renamed from: a */
    private boolean m3764a(TileCoordinate tileCoordinate) {
        return tileCoordinate == null || tileCoordinate.f2600g < 0;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: g */
    public int mo10115g() {
        return 1;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: f */
    public int mo10114f() {
        return 4;
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public ArrayList<TileCoordinate> mo10112b(ArrayList<TileCoordinate> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        int i = 0;
        ArrayList<TileCoordinate> arrayList2 = null;
        while (i < size) {
            int i2;
            int i3;
            ArrayList<TileCoordinate> arrayList3;
            TileCoordinate tileCoordinate = (TileCoordinate) arrayList.get(i);
            if (tileCoordinate == null) {
                i2 = i;
                i3 = size;
                arrayList3 = arrayList2;
            } else if (this.f2324b == null || this.f2324b.f2386e == null || this.f2324b.f2386e.f2355a == null) {
                return null;
            } else {
                this.f2324b.f2386e.f2355a.size();
                if (this.f2622e.f2242f) {
                    int a = this.f2622e.f2251o.mo10322a(tileCoordinate);
                    if (a >= 0) {
                        arrayList.remove(i);
                        size--;
                        i3 = i - 1;
                        SyncList syncList = this.f2622e.f2252p;
                        if (syncList == null) {
                            i2 = i3;
                            arrayList3 = arrayList2;
                            i3 = size;
                        } else {
                            synchronized (syncList) {
                                Iterator it = syncList.iterator();
                                while (it.hasNext()) {
                                    TileCoordinate tileCoordinate2 = (TileCoordinate) it.next();
                                    if (tileCoordinate2 != null && tileCoordinate2.equals(tileCoordinate)) {
                                        tileCoordinate2.f2600g = a;
                                        this.f2324b.f2386e.mo9883b();
                                        break;
                                    }
                                }
                            }
                            i2 = i3;
                            arrayList3 = arrayList2;
                            i3 = size;
                        }
                    } else {
                        ArrayList<TileCoordinate> arrayList4;
                        if (arrayList2 == null) {
                            arrayList4 = new ArrayList();
                        } else {
                            arrayList4 = arrayList2;
                        }
                        TileCoordinate tileCoordinate3 = new TileCoordinate(tileCoordinate);
                        tileCoordinate3.f2594a = -1;
                        arrayList4.add(tileCoordinate3);
                        i2 = i;
                        arrayList3 = arrayList4;
                        i3 = size;
                    }
                } else {
                    i2 = i;
                    i3 = size;
                    arrayList3 = arrayList2;
                }
            }
            arrayList2 = arrayList3;
            size = i3;
            i = i2 + 1;
        }
        return arrayList2;
    }
}
