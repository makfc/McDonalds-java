package android.support.p001v7.util;

import android.util.SparseArray;
import java.lang.reflect.Array;

/* renamed from: android.support.v7.util.TileList */
class TileList<T> {
    Tile<T> mLastAccessedTile;
    private final SparseArray<Tile<T>> mTiles;

    /* renamed from: android.support.v7.util.TileList$Tile */
    public static class Tile<T> {
        public int mItemCount;
        public final T[] mItems;
        Tile<T> mNext;
        public int mStartPosition;

        public Tile(Class<T> klass, int size) {
            this.mItems = (Object[]) Array.newInstance(klass, size);
        }
    }

    public int size() {
        return this.mTiles.size();
    }

    public void clear() {
        this.mTiles.clear();
    }

    public Tile<T> getAtIndex(int index) {
        return (Tile) this.mTiles.valueAt(index);
    }

    public Tile<T> addOrReplace(Tile<T> newTile) {
        int index = this.mTiles.indexOfKey(newTile.mStartPosition);
        if (index < 0) {
            this.mTiles.put(newTile.mStartPosition, newTile);
            return null;
        }
        Tile<T> oldTile = (Tile) this.mTiles.valueAt(index);
        this.mTiles.setValueAt(index, newTile);
        if (this.mLastAccessedTile != oldTile) {
            return oldTile;
        }
        this.mLastAccessedTile = newTile;
        return oldTile;
    }

    public Tile<T> removeAtPos(int startPosition) {
        Tile<T> tile = (Tile) this.mTiles.get(startPosition);
        if (this.mLastAccessedTile == tile) {
            this.mLastAccessedTile = null;
        }
        this.mTiles.delete(startPosition);
        return tile;
    }
}
