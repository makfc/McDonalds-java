package com.aps;

import android.location.Location;
import android.net.wifi.ScanResult;
import android.telephony.CellLocation;
import java.util.List;

/* renamed from: com.aps.ba */
public final class C1244ba {
    /* renamed from: c */
    private static int f4352c = 10;
    /* renamed from: d */
    private static int f4353d = 100;
    /* renamed from: f */
    private static float f4354f = 0.5f;
    /* renamed from: a */
    protected C1248be f4355a = new C1248be(this);
    /* renamed from: b */
    protected C1245bb f4356b = new C1245bb(this);
    /* renamed from: e */
    private C1225am f4357e;

    protected C1244ba(C1225am c1225am) {
        this.f4357e = c1225am;
    }

    /* renamed from: a */
    protected static void m5538a() {
    }

    /* renamed from: a */
    protected static void m5539a(int i) {
        f4352c = i;
    }

    /* renamed from: b */
    protected static void m5540b(int i) {
        f4353d = i;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: a */
    public final boolean mo13185a(Location location) {
        boolean z = false;
        if (this.f4357e != null) {
            List j = this.f4357e.mo13118j();
            if (!(j == null || location == null)) {
                "cell.list.size: " + j.size();
                C1246bc c1246bc = null;
                if (j.size() >= 2) {
                    C1246bc c1246bc2 = new C1246bc((CellLocation) j.get(1));
                    if (this.f4356b.f4359b == null) {
                        c1246bc = c1246bc2;
                        z = true;
                    } else {
                        boolean z2 = location.distanceTo(this.f4356b.f4359b) > ((float) f4353d);
                        if (!z2) {
                            int i;
                            c1246bc = this.f4356b.f4358a;
                            if (c1246bc2.f4364e == c1246bc.f4364e && c1246bc2.f4363d == c1246bc.f4363d && c1246bc2.f4362c == c1246bc.f4362c && c1246bc2.f4361b == c1246bc.f4361b && c1246bc2.f4360a == c1246bc.f4360a) {
                                i = 1;
                            } else {
                                z2 = false;
                            }
                            z2 = i == 0;
                        }
                        "collect cell?: " + z2;
                        z = z2;
                        c1246bc = c1246bc2;
                    }
                }
                if (z) {
                    this.f4356b.f4358a = c1246bc;
                }
            }
        }
        return z;
    }

    /* Access modifiers changed, original: protected|final */
    /* renamed from: b */
    public final boolean mo13186b(Location location) {
        int i = 0;
        if (this.f4357e == null) {
            return false;
        }
        boolean z;
        List list;
        List k = this.f4357e.mo13119k();
        if (k.size() >= 2) {
            List list2 = (List) k.get(1);
            if (this.f4355a.f4367b == null) {
                z = true;
            } else if (list2 == null || list2.size() <= 0) {
                list = list2;
                z = false;
            } else {
                z = location.distanceTo(this.f4355a.f4367b) > ((float) f4352c);
                if (z) {
                    list = list2;
                } else {
                    int i2;
                    List list3 = this.f4355a.f4366a;
                    float f = f4354f;
                    if (list2 == null || list3 == null) {
                        i2 = 0;
                    } else if (list2 == null || list3 == null) {
                        i2 = 0;
                    } else {
                        int size = list2.size();
                        int size2 = list3.size();
                        float f2 = (float) (size + size2);
                        if (size == 0 && size2 == 0) {
                            i2 = 1;
                        } else if (size == 0 || size2 == 0) {
                            i2 = 0;
                        } else {
                            int i3 = 0;
                            int i4 = 0;
                            while (i3 < size) {
                                String str = ((ScanResult) list2.get(i3)).BSSID;
                                if (str != null) {
                                    for (int i5 = 0; i5 < size2; i5++) {
                                        if (str.equals(((C1247bd) list3.get(i5)).f4365a)) {
                                            i2 = i4 + 1;
                                            break;
                                        }
                                    }
                                }
                                i2 = i4;
                                i3++;
                                i4 = i2;
                            }
                            i2 = ((float) (i4 << 1)) >= f2 * f ? 1 : 0;
                        }
                    }
                    z = i2 == 0;
                }
            }
            list = list2;
        } else {
            list = null;
            z = false;
        }
        if (z) {
            this.f4355a.f4366a.clear();
            int size3 = list.size();
            while (i < size3) {
                this.f4355a.f4366a.add(new C1247bd(((ScanResult) list.get(i)).BSSID));
                i++;
            }
        }
        return z;
    }
}
