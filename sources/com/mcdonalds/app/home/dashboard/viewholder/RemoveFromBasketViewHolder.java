package com.mcdonalds.app.home.dashboard.viewholder;

import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.home.dashboard.DashboardAdapter.DashboardListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.OrderOffer;

public class RemoveFromBasketViewHolder extends ViewHolder implements OnClickListener {
    private OrderOffer mOrderOffer;
    private RemoveFromBasketViewHolderListener mViewHolderListener;

    public interface RemoveFromBasketViewHolderListener {
        void onRemoveClicked();
    }

    public void setOrderOffer(OrderOffer orderOffer) {
        Ensighten.evaluateEvent(this, "setOrderOffer", new Object[]{orderOffer});
        this.mOrderOffer = orderOffer;
    }

    public RemoveFromBasketViewHolder(View itemView, RemoveFromBasketViewHolderListener viewHolderListener) {
        super(itemView);
        this.mViewHolderListener = viewHolderListener;
        ((TextView) itemView.findViewById(C2358R.C2357id.remove_from_basket_button)).setOnClickListener(this);
    }

    public static RemoveFromBasketViewHolder create(LayoutInflater inflater, final DashboardListener dashboardListener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.RemoveFromBasketViewHolder", "create", new Object[]{inflater, dashboardListener});
        final RemoveFromBasketViewHolder viewHolder = new RemoveFromBasketViewHolder(inflater.inflate(C2658R.layout.dashboard_applied_offer_layout, null), null);
        viewHolder.setViewHolderListener(new RemoveFromBasketViewHolderListener() {
            public void onRemoveClicked() {
                Ensighten.evaluateEvent(this, "onRemoveClicked", null);
                dashboardListener.onRemoveFromBasketClicked(viewHolder.getOrderOffer());
            }
        });
        return viewHolder;
    }

    public static void bind(RemoveFromBasketViewHolder viewHolder, OrderOffer orderOffer) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.RemoveFromBasketViewHolder", "bind", new Object[]{viewHolder, orderOffer});
        viewHolder.setOrderOffer(orderOffer);
    }

    public OrderOffer getOrderOffer() {
        Ensighten.evaluateEvent(this, "getOrderOffer", null);
        return this.mOrderOffer;
    }

    public void setViewHolderListener(RemoveFromBasketViewHolderListener viewHolderListener) {
        Ensighten.evaluateEvent(this, "setViewHolderListener", new Object[]{viewHolderListener});
        this.mViewHolderListener = viewHolderListener;
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (this.mViewHolderListener != null) {
            this.mViewHolderListener.onRemoveClicked();
        }
    }
}
