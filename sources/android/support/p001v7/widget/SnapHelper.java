package android.support.p001v7.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.widget.ExploreByTouchHelper;
import android.support.p001v7.widget.RecyclerView.LayoutManager;
import android.support.p001v7.widget.RecyclerView.OnFlingListener;
import android.support.p001v7.widget.RecyclerView.OnScrollListener;
import android.support.p001v7.widget.RecyclerView.SmoothScroller;
import android.support.p001v7.widget.RecyclerView.SmoothScroller.Action;
import android.support.p001v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider;
import android.support.p001v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Scroller;

/* renamed from: android.support.v7.widget.SnapHelper */
public abstract class SnapHelper extends OnFlingListener {
    private Scroller mGravityScroller;
    RecyclerView mRecyclerView;
    private final OnScrollListener mScrollListener = new C03951();

    /* renamed from: android.support.v7.widget.SnapHelper$1 */
    class C03951 extends OnScrollListener {
        boolean mScrolled = false;

        C03951() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == 0 && this.mScrolled) {
                this.mScrolled = false;
                SnapHelper.this.snapToTargetExistingView();
            }
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (dx != 0 || dy != 0) {
                this.mScrolled = true;
            }
        }
    }

    @Nullable
    public abstract int[] calculateDistanceToFinalSnap(@NonNull LayoutManager layoutManager, @NonNull View view);

    @Nullable
    public abstract View findSnapView(LayoutManager layoutManager);

    public abstract int findTargetSnapPosition(LayoutManager layoutManager, int i, int i2);

    public boolean onFling(int velocityX, int velocityY) {
        LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null || this.mRecyclerView.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.mRecyclerView.getMinFlingVelocity();
        if ((Math.abs(velocityY) > minFlingVelocity || Math.abs(velocityX) > minFlingVelocity) && snapFromFling(layoutManager, velocityX, velocityY)) {
            return true;
        }
        return false;
    }

    public int[] calculateScrollDistance(int velocityX, int velocityY) {
        outDist = new int[2];
        this.mGravityScroller.fling(0, 0, velocityX, velocityY, ExploreByTouchHelper.INVALID_ID, Integer.MAX_VALUE, ExploreByTouchHelper.INVALID_ID, Integer.MAX_VALUE);
        outDist[0] = this.mGravityScroller.getFinalX();
        outDist[1] = this.mGravityScroller.getFinalY();
        return outDist;
    }

    private boolean snapFromFling(@NonNull LayoutManager layoutManager, int velocityX, int velocityY) {
        if (!(layoutManager instanceof ScrollVectorProvider)) {
            return false;
        }
        SmoothScroller smoothScroller = createSnapScroller(layoutManager);
        if (smoothScroller == null) {
            return false;
        }
        int targetPosition = findTargetSnapPosition(layoutManager, velocityX, velocityY);
        if (targetPosition == -1) {
            return false;
        }
        smoothScroller.setTargetPosition(targetPosition);
        layoutManager.startSmoothScroll(smoothScroller);
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public void snapToTargetExistingView() {
        if (this.mRecyclerView != null) {
            LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
            if (layoutManager != null) {
                View snapView = findSnapView(layoutManager);
                if (snapView != null) {
                    int[] snapDistance = calculateDistanceToFinalSnap(layoutManager, snapView);
                    if (snapDistance[0] != 0 || snapDistance[1] != 0) {
                        this.mRecyclerView.smoothScrollBy(snapDistance[0], snapDistance[1]);
                    }
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    @Nullable
    public LinearSmoothScroller createSnapScroller(LayoutManager layoutManager) {
        if (layoutManager instanceof ScrollVectorProvider) {
            return new LinearSmoothScroller(this.mRecyclerView.getContext()) {
                /* Access modifiers changed, original: protected */
                public void onTargetFound(View targetView, State state, Action action) {
                    int[] snapDistances = SnapHelper.this.calculateDistanceToFinalSnap(SnapHelper.this.mRecyclerView.getLayoutManager(), targetView);
                    int dx = snapDistances[0];
                    int dy = snapDistances[1];
                    int time = calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)));
                    if (time > 0) {
                        action.update(dx, dy, time, this.mDecelerateInterpolator);
                    }
                }

                /* Access modifiers changed, original: protected */
                public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    return 100.0f / ((float) displayMetrics.densityDpi);
                }
            };
        }
        return null;
    }
}
