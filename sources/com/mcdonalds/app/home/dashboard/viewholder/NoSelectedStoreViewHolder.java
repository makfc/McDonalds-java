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

public class NoSelectedStoreViewHolder extends ViewHolder {
    private TextView mMsgView;

    public NoSelectedStoreViewHolder(View view) {
        super(view);
        this.mMsgView = (TextView) view.findViewById(C2358R.C2357id.no_selected_store_msg);
    }

    public static ViewHolder create(LayoutInflater inflater, final DashboardListener dashboardListener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.NoSelectedStoreViewHolder", "create", new Object[]{inflater, dashboardListener});
        View view = inflater.inflate(C2658R.layout.no_selected_store_view_holder, null);
        ((TextView) view.findViewById(C2358R.C2357id.no_selected_store_msg)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                dashboardListener.onSelectStoreClicked();
            }
        });
        return new NoSelectedStoreViewHolder(view);
    }

    public static void bind(NoSelectedStoreViewHolder viewHolder, boolean isDelivery) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.NoSelectedStoreViewHolder", "bind", new Object[]{viewHolder, new Boolean(isDelivery)});
        viewHolder.mMsgView.setText(isDelivery ? C2658R.string.offer_section_no_address_selected : C2658R.string.offer_section_no_store_selected);
    }
}
