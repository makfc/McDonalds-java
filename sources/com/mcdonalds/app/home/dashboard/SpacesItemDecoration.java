package com.mcdonalds.app.home.dashboard;

import android.graphics.Rect;
import android.support.p001v7.widget.RecyclerView;
import android.support.p001v7.widget.RecyclerView.ItemDecoration;
import android.support.p001v7.widget.RecyclerView.State;
import android.view.View;
import com.ensighten.Ensighten;

public class SpacesItemDecoration extends ItemDecoration {
    private final int mSpace;

    public SpacesItemDecoration(int space) {
        this.mSpace = space;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        Ensighten.evaluateEvent(this, "getItemOffsets", new Object[]{outRect, view, parent, state});
        outRect.left = this.mSpace;
        outRect.right = this.mSpace;
        outRect.bottom = this.mSpace;
        if (parent.getChildPosition(view) == 0) {
            outRect.top = this.mSpace;
        }
    }
}
