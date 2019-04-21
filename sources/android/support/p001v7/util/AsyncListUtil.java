package android.support.p001v7.util;

import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.p001v7.util.ThreadUtil.BackgroundCallback;
import android.support.p001v7.util.ThreadUtil.MainThreadCallback;
import android.support.p001v7.util.TileList.Tile;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

/* renamed from: android.support.v7.util.AsyncListUtil */
public class AsyncListUtil<T> {
    boolean mAllowScrollHints;
    final BackgroundCallback<T> mBackgroundProxy;
    final DataCallback<T> mDataCallback;
    int mDisplayedGeneration;
    int mItemCount;
    final MainThreadCallback<T> mMainThreadProxy;
    final SparseIntArray mMissingPositions;
    final int[] mPrevRange;
    int mRequestedGeneration;
    private int mScrollHint;
    final Class<T> mTClass;
    final TileList<T> mTileList;
    final int mTileSize;
    final int[] mTmpRange;
    final int[] mTmpRangeExtended;
    final ViewCallback mViewCallback;

    /* renamed from: android.support.v7.util.AsyncListUtil$1 */
    class C03391 implements MainThreadCallback<T> {
        final /* synthetic */ AsyncListUtil this$0;

        public void updateItemCount(int generation, int itemCount) {
            if (isRequestedGeneration(generation)) {
                this.this$0.mItemCount = itemCount;
                this.this$0.mViewCallback.onDataRefresh();
                this.this$0.mDisplayedGeneration = this.this$0.mRequestedGeneration;
                recycleAllTiles();
                this.this$0.mAllowScrollHints = false;
                this.this$0.updateRange();
            }
        }

        public void addTile(int generation, Tile<T> tile) {
            if (isRequestedGeneration(generation)) {
                Tile<T> duplicate = this.this$0.mTileList.addOrReplace(tile);
                if (duplicate != null) {
                    Log.e("AsyncListUtil", "duplicate tile @" + duplicate.mStartPosition);
                    this.this$0.mBackgroundProxy.recycleTile(duplicate);
                }
                int endPosition = tile.mStartPosition + tile.mItemCount;
                int index = 0;
                while (index < this.this$0.mMissingPositions.size()) {
                    int position = this.this$0.mMissingPositions.keyAt(index);
                    if (tile.mStartPosition > position || position >= endPosition) {
                        index++;
                    } else {
                        this.this$0.mMissingPositions.removeAt(index);
                        this.this$0.mViewCallback.onItemLoaded(position);
                    }
                }
                return;
            }
            this.this$0.mBackgroundProxy.recycleTile(tile);
        }

        public void removeTile(int generation, int position) {
            if (isRequestedGeneration(generation)) {
                Tile<T> tile = this.this$0.mTileList.removeAtPos(position);
                if (tile == null) {
                    Log.e("AsyncListUtil", "tile not found @" + position);
                } else {
                    this.this$0.mBackgroundProxy.recycleTile(tile);
                }
            }
        }

        private void recycleAllTiles() {
            for (int i = 0; i < this.this$0.mTileList.size(); i++) {
                this.this$0.mBackgroundProxy.recycleTile(this.this$0.mTileList.getAtIndex(i));
            }
            this.this$0.mTileList.clear();
        }

        private boolean isRequestedGeneration(int generation) {
            return generation == this.this$0.mRequestedGeneration;
        }
    }

    /* renamed from: android.support.v7.util.AsyncListUtil$2 */
    class C03402 implements BackgroundCallback<T> {
        private int mFirstRequiredTileStart;
        private int mGeneration;
        private int mItemCount;
        private int mLastRequiredTileStart;
        final SparseBooleanArray mLoadedTiles;
        private Tile<T> mRecycledRoot;
        final /* synthetic */ AsyncListUtil this$0;

        public void refresh(int generation) {
            this.mGeneration = generation;
            this.mLoadedTiles.clear();
            this.mItemCount = this.this$0.mDataCallback.refreshData();
            this.this$0.mMainThreadProxy.updateItemCount(this.mGeneration, this.mItemCount);
        }

        public void updateRange(int rangeStart, int rangeEnd, int extRangeStart, int extRangeEnd, int scrollHint) {
            if (rangeStart <= rangeEnd) {
                int firstVisibleTileStart = getTileStart(rangeStart);
                int lastVisibleTileStart = getTileStart(rangeEnd);
                this.mFirstRequiredTileStart = getTileStart(extRangeStart);
                this.mLastRequiredTileStart = getTileStart(extRangeEnd);
                if (scrollHint == 1) {
                    requestTiles(this.mFirstRequiredTileStart, lastVisibleTileStart, scrollHint, true);
                    requestTiles(this.this$0.mTileSize + lastVisibleTileStart, this.mLastRequiredTileStart, scrollHint, false);
                    return;
                }
                requestTiles(firstVisibleTileStart, this.mLastRequiredTileStart, scrollHint, false);
                requestTiles(this.mFirstRequiredTileStart, firstVisibleTileStart - this.this$0.mTileSize, scrollHint, true);
            }
        }

        private int getTileStart(int position) {
            return position - (position % this.this$0.mTileSize);
        }

        private void requestTiles(int firstTileStart, int lastTileStart, int scrollHint, boolean backwards) {
            int i = firstTileStart;
            while (i <= lastTileStart) {
                int tileStart;
                if (backwards) {
                    tileStart = (lastTileStart + firstTileStart) - i;
                } else {
                    tileStart = i;
                }
                this.this$0.mBackgroundProxy.loadTile(tileStart, scrollHint);
                i += this.this$0.mTileSize;
            }
        }

        public void loadTile(int position, int scrollHint) {
            if (!isTileLoaded(position)) {
                Tile<T> tile = acquireTile();
                tile.mStartPosition = position;
                tile.mItemCount = Math.min(this.this$0.mTileSize, this.mItemCount - tile.mStartPosition);
                this.this$0.mDataCallback.fillData(tile.mItems, tile.mStartPosition, tile.mItemCount);
                flushTileCache(scrollHint);
                addTile(tile);
            }
        }

        public void recycleTile(Tile<T> tile) {
            this.this$0.mDataCallback.recycleData(tile.mItems, tile.mItemCount);
            tile.mNext = this.mRecycledRoot;
            this.mRecycledRoot = tile;
        }

        private Tile<T> acquireTile() {
            if (this.mRecycledRoot == null) {
                return new Tile(this.this$0.mTClass, this.this$0.mTileSize);
            }
            Tile<T> result = this.mRecycledRoot;
            this.mRecycledRoot = this.mRecycledRoot.mNext;
            return result;
        }

        private boolean isTileLoaded(int position) {
            return this.mLoadedTiles.get(position);
        }

        private void addTile(Tile<T> tile) {
            this.mLoadedTiles.put(tile.mStartPosition, true);
            this.this$0.mMainThreadProxy.addTile(this.mGeneration, tile);
        }

        private void removeTile(int position) {
            this.mLoadedTiles.delete(position);
            this.this$0.mMainThreadProxy.removeTile(this.mGeneration, position);
        }

        private void flushTileCache(int scrollHint) {
            int cacheSizeLimit = this.this$0.mDataCallback.getMaxCachedTiles();
            while (this.mLoadedTiles.size() >= cacheSizeLimit) {
                int firstLoadedTileStart = this.mLoadedTiles.keyAt(0);
                int lastLoadedTileStart = this.mLoadedTiles.keyAt(this.mLoadedTiles.size() - 1);
                int startMargin = this.mFirstRequiredTileStart - firstLoadedTileStart;
                int endMargin = lastLoadedTileStart - this.mLastRequiredTileStart;
                if (startMargin > 0 && (startMargin >= endMargin || scrollHint == 2)) {
                    removeTile(firstLoadedTileStart);
                } else if (endMargin <= 0) {
                    return;
                } else {
                    if (startMargin < endMargin || scrollHint == 1) {
                        removeTile(lastLoadedTileStart);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /* renamed from: android.support.v7.util.AsyncListUtil$DataCallback */
    public static abstract class DataCallback<T> {
        @WorkerThread
        public abstract void fillData(T[] tArr, int i, int i2);

        @WorkerThread
        public abstract int refreshData();

        @WorkerThread
        public void recycleData(T[] tArr, int itemCount) {
        }

        @WorkerThread
        public int getMaxCachedTiles() {
            return 10;
        }
    }

    /* renamed from: android.support.v7.util.AsyncListUtil$ViewCallback */
    public static abstract class ViewCallback {
        @UiThread
        public abstract void getItemRangeInto(int[] iArr);

        @UiThread
        public abstract void onDataRefresh();

        @UiThread
        public abstract void onItemLoaded(int i);

        @UiThread
        public void extendRangeInto(int[] range, int[] outRange, int scrollHint) {
            int i;
            int fullRange = (range[1] - range[0]) + 1;
            int halfRange = fullRange / 2;
            int i2 = range[0];
            if (scrollHint == 1) {
                i = fullRange;
            } else {
                i = halfRange;
            }
            outRange[0] = i2 - i;
            i = range[1];
            if (scrollHint != 2) {
                fullRange = halfRange;
            }
            outRange[1] = i + fullRange;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void updateRange() {
        this.mViewCallback.getItemRangeInto(this.mTmpRange);
        if (this.mTmpRange[0] <= this.mTmpRange[1] && this.mTmpRange[0] >= 0 && this.mTmpRange[1] < this.mItemCount) {
            if (!this.mAllowScrollHints) {
                this.mScrollHint = 0;
            } else if (this.mTmpRange[0] > this.mPrevRange[1] || this.mPrevRange[0] > this.mTmpRange[1]) {
                this.mScrollHint = 0;
            } else if (this.mTmpRange[0] < this.mPrevRange[0]) {
                this.mScrollHint = 1;
            } else if (this.mTmpRange[0] > this.mPrevRange[0]) {
                this.mScrollHint = 2;
            }
            this.mPrevRange[0] = this.mTmpRange[0];
            this.mPrevRange[1] = this.mTmpRange[1];
            this.mViewCallback.extendRangeInto(this.mTmpRange, this.mTmpRangeExtended, this.mScrollHint);
            this.mTmpRangeExtended[0] = Math.min(this.mTmpRange[0], Math.max(this.mTmpRangeExtended[0], 0));
            this.mTmpRangeExtended[1] = Math.max(this.mTmpRange[1], Math.min(this.mTmpRangeExtended[1], this.mItemCount - 1));
            this.mBackgroundProxy.updateRange(this.mTmpRange[0], this.mTmpRange[1], this.mTmpRangeExtended[0], this.mTmpRangeExtended[1], this.mScrollHint);
        }
    }
}
