package android.support.p001v7.widget;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.p000v4.view.ViewCompat;
import android.support.p001v7.widget.RecyclerView.ItemDecoration;
import android.support.p001v7.widget.RecyclerView.State;
import android.view.View;

/* renamed from: android.support.v7.widget.DividerItemDecoration */
public class DividerItemDecoration extends ItemDecoration {
    private static final int[] ATTRS = new int[]{16843284};
    private final Rect mBounds;
    private Drawable mDivider;
    private int mOrientation;

    public void onDraw(Canvas c, RecyclerView parent, State state) {
        if (parent.getLayoutManager() != null) {
            if (this.mOrientation == 1) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }
        }
    }

    @SuppressLint({"NewApi"})
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        int left;
        int right;
        canvas.save();
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, this.mBounds);
            int bottom = this.mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
            this.mDivider.setBounds(left, bottom - this.mDivider.getIntrinsicHeight(), right, bottom);
            this.mDivider.draw(canvas);
        }
        canvas.restore();
    }

    @SuppressLint({"NewApi"})
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        int top;
        int bottom;
        canvas.save();
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, this.mBounds);
            int right = this.mBounds.right + Math.round(ViewCompat.getTranslationX(child));
            this.mDivider.setBounds(right - this.mDivider.getIntrinsicWidth(), top, right, bottom);
            this.mDivider.draw(canvas);
        }
        canvas.restore();
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        if (this.mOrientation == 1) {
            outRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
        } else {
            outRect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
        }
    }
}
