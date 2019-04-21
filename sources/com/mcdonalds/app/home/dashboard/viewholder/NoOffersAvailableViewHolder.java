package com.mcdonalds.app.home.dashboard.viewholder;

import android.content.Context;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class NoOffersAvailableViewHolder extends ViewHolder {
    private TextView mCheckBackPrompt;
    private View mContainer;
    private ProgressBar mProgressBar;

    private NoOffersAvailableViewHolder(View itemView) {
        super(itemView);
        this.mContainer = itemView.findViewById(C2358R.C2357id.check_back_container);
        this.mProgressBar = (ProgressBar) itemView.findViewById(C2358R.C2357id.progress_bar);
        this.mCheckBackPrompt = (TextView) itemView.findViewById(C2358R.C2357id.check_back_prompt);
    }

    public static ViewHolder create(LayoutInflater inflater) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.NoOffersAvailableViewHolder", "create", new Object[]{inflater});
        return new NoOffersAvailableViewHolder(inflater.inflate(C2658R.layout.check_back_for_offers, null));
    }

    public static void bind(Context context, NoOffersAvailableViewHolder viewHolder) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.NoOffersAvailableViewHolder", "bind", new Object[]{context, viewHolder});
        viewHolder.mCheckBackPrompt.setText(UIUtils.getStringByName(context, Configuration.getSharedInstance().getStringForKey("interface.dashboard.offersTextNoOffers")));
        viewHolder.mContainer.setVisibility(0);
        viewHolder.mProgressBar.setVisibility(4);
    }
}
