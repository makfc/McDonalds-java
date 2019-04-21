package com.mcdonalds.app.account;

import android.graphics.Canvas;
import android.support.p001v7.widget.RecyclerView;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.support.p001v7.widget.helper.ItemTouchHelper.Callback;
import com.ensighten.Ensighten;

public class AddressesSwipeCallback extends Callback {
    private SwipeListener listener;

    public interface SwipeListener {
        void onItemDismissed(int i);
    }

    public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
        Ensighten.evaluateEvent(this, "getMovementFlags", new Object[]{recyclerView, viewHolder});
        return Callback.makeMovementFlags(0, 32);
    }

    public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder target) {
        Ensighten.evaluateEvent(this, "onMove", new Object[]{recyclerView, viewHolder, target});
        return false;
    }

    public void onSwiped(ViewHolder viewHolder, int direction) {
        Ensighten.evaluateEvent(this, "onSwiped", new Object[]{viewHolder, new Integer(direction)});
        if (this.listener != null) {
            this.listener.onItemDismissed(viewHolder.getAdapterPosition());
        }
    }

    public boolean isItemViewSwipeEnabled() {
        Ensighten.evaluateEvent(this, "isItemViewSwipeEnabled", null);
        return true;
    }

    public void onChildDraw(Canvas c, RecyclerView recyclerView, ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Ensighten.evaluateEvent(this, "onChildDraw", new Object[]{c, recyclerView, viewHolder, new Float(dX), new Float(dY), new Integer(actionState), new Boolean(isCurrentlyActive)});
        if (actionState == 1 && (viewHolder instanceof AddressesAdapter.ViewHolder)) {
            ((AddressesAdapter.ViewHolder) viewHolder).container.setTranslationX(dX);
        }
    }
}
