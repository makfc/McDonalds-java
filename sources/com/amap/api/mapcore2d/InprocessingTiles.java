package com.amap.api.mapcore2d;

/* renamed from: com.amap.api.mapcore2d.al */
class InprocessingTiles extends SyncList<TileCoordinate> {
    InprocessingTiles() {
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* renamed from: a */
    public synchronized void mo9755a(TileCoordinate tileCoordinate) {
        remove((Object) tileCoordinate);
    }

    /* Access modifiers changed, original: declared_synchronized */
    /* renamed from: b */
    public synchronized boolean mo9756b(TileCoordinate tileCoordinate) {
        boolean z = true;
        synchronized (this) {
            if (contains(tileCoordinate)) {
                z = false;
            } else {
                mo9731a(tileCoordinate);
            }
        }
        return z;
    }
}
