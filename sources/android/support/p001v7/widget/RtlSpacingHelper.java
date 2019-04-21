package android.support.p001v7.widget;

import android.support.p000v4.widget.ExploreByTouchHelper;

/* renamed from: android.support.v7.widget.RtlSpacingHelper */
class RtlSpacingHelper {
    private int mEnd = ExploreByTouchHelper.INVALID_ID;
    private int mExplicitLeft = 0;
    private int mExplicitRight = 0;
    private boolean mIsRelative = false;
    private boolean mIsRtl = false;
    private int mLeft = 0;
    private int mRight = 0;
    private int mStart = ExploreByTouchHelper.INVALID_ID;

    RtlSpacingHelper() {
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getRight() {
        return this.mRight;
    }

    public int getStart() {
        return this.mIsRtl ? this.mRight : this.mLeft;
    }

    public int getEnd() {
        return this.mIsRtl ? this.mLeft : this.mRight;
    }

    public void setRelative(int start, int end) {
        this.mStart = start;
        this.mEnd = end;
        this.mIsRelative = true;
        if (this.mIsRtl) {
            if (end != ExploreByTouchHelper.INVALID_ID) {
                this.mLeft = end;
            }
            if (start != ExploreByTouchHelper.INVALID_ID) {
                this.mRight = start;
                return;
            }
            return;
        }
        if (start != ExploreByTouchHelper.INVALID_ID) {
            this.mLeft = start;
        }
        if (end != ExploreByTouchHelper.INVALID_ID) {
            this.mRight = end;
        }
    }

    public void setAbsolute(int left, int right) {
        this.mIsRelative = false;
        if (left != ExploreByTouchHelper.INVALID_ID) {
            this.mExplicitLeft = left;
            this.mLeft = left;
        }
        if (right != ExploreByTouchHelper.INVALID_ID) {
            this.mExplicitRight = right;
            this.mRight = right;
        }
    }

    public void setDirection(boolean isRtl) {
        if (isRtl != this.mIsRtl) {
            this.mIsRtl = isRtl;
            if (!this.mIsRelative) {
                this.mLeft = this.mExplicitLeft;
                this.mRight = this.mExplicitRight;
            } else if (isRtl) {
                this.mLeft = this.mEnd != ExploreByTouchHelper.INVALID_ID ? this.mEnd : this.mExplicitLeft;
                this.mRight = this.mStart != ExploreByTouchHelper.INVALID_ID ? this.mStart : this.mExplicitRight;
            } else {
                this.mLeft = this.mStart != ExploreByTouchHelper.INVALID_ID ? this.mStart : this.mExplicitLeft;
                this.mRight = this.mEnd != ExploreByTouchHelper.INVALID_ID ? this.mEnd : this.mExplicitRight;
            }
        }
    }
}
