package com.mcdonalds.app.home.dashboard.viewholder;

import android.content.Context;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.account.OfferPreferencesActivity;
import com.mcdonalds.app.account.OfferPreferencesFragment;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;

public class NotSubscribedToOffersViewHolder extends ViewHolder {
    TextView mCheckBackPrompt;
    View mContainer;
    TextView mJoinNow;
    ProgressBar mProgressBar;

    private NotSubscribedToOffersViewHolder(View itemView) {
        super(itemView);
        this.mContainer = itemView.findViewById(C2358R.C2357id.check_back_container);
        this.mProgressBar = (ProgressBar) itemView.findViewById(C2358R.C2357id.progress_bar);
        this.mCheckBackPrompt = (TextView) itemView.findViewById(C2358R.C2357id.check_back_prompt);
        this.mJoinNow = (TextView) itemView.findViewById(C2358R.C2357id.join_now);
    }

    public static ViewHolder create(LayoutInflater inflater) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.NotSubscribedToOffersViewHolder", "create", new Object[]{inflater});
        return new NotSubscribedToOffersViewHolder(inflater.inflate(C2658R.layout.check_back_for_offers, null));
    }

    public static void bind(final Context context, NotSubscribedToOffersViewHolder viewHolder) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.NotSubscribedToOffersViewHolder", "bind", new Object[]{context, viewHolder});
        viewHolder.mCheckBackPrompt.setText(UIUtils.getStringByName(context, Configuration.getSharedInstance().getStringForKey("interface.dashboard.offersTextNotSubscribed")));
        viewHolder.mContainer.setVisibility(0);
        viewHolder.mProgressBar.setVisibility(4);
        viewHolder.mJoinNow.setVisibility(0);
        viewHolder.mJoinNow.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                ((URLNavigationActivity) context).startActivityForResult(OfferPreferencesActivity.class, OfferPreferencesFragment.NAME, null, 326);
            }
        });
    }
}
