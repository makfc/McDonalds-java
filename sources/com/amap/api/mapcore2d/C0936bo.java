package com.amap.api.mapcore2d;

import java.util.ArrayList;

/* compiled from: TaskPool */
/* renamed from: com.amap.api.mapcore2d.bo */
class C0936bo extends TaskPool<TileCoordinate> {
    C0936bo() {
    }

    /* Access modifiers changed, original: protected|declared_synchronized */
    /* renamed from: b */
    public synchronized ArrayList<TileCoordinate> mo10089b(int i, boolean z) {
        ArrayList<TileCoordinate> arrayList;
        int i2 = 0;
        synchronized (this) {
            if (this.f2575a == null) {
                arrayList = null;
            } else {
                int size = this.f2575a.size();
                if (i > size) {
                    i = size;
                }
                ArrayList<TileCoordinate> arrayList2 = new ArrayList(i);
                int i3 = 0;
                while (i2 < size) {
                    int i4;
                    TileCoordinate tileCoordinate = (TileCoordinate) this.f2575a.get(i2);
                    if (tileCoordinate == null) {
                        i4 = i2;
                        i2 = i3;
                        i3 = size;
                    } else {
                        int i5 = tileCoordinate.f2594a;
                        if (z) {
                            if (i5 == 0) {
                                arrayList2.add(tileCoordinate);
                                this.f2575a.remove(i2);
                                i4 = i2 - 1;
                                i2 = i3 + 1;
                                i3 = size - 1;
                            }
                            i4 = i2;
                            i2 = i3;
                            i3 = size;
                        } else {
                            if (i5 < 0) {
                                arrayList2.add(tileCoordinate);
                                this.f2575a.remove(i2);
                                i4 = i2 - 1;
                                i2 = i3 + 1;
                                i3 = size - 1;
                            }
                            i4 = i2;
                            i2 = i3;
                            i3 = size;
                        }
                        if (i2 >= i) {
                            break;
                        }
                    }
                    size = i3;
                    i3 = i2;
                    i2 = i4 + 1;
                }
                mo10090b();
                arrayList = arrayList2;
            }
        }
        return arrayList;
    }
}
