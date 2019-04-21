package com.mcdonalds.app.home.dashboard.viewholder;

import android.content.Context;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.home.dashboard.DashboardAdapter.DashboardListener;
import com.mcdonalds.app.home.dashboard.DashboardItem;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Offer;
import java.util.ArrayList;

public class PunchcardOfferListViewHolder extends ViewHolder implements OnClickListener {
    View mAddedToOrderView;
    private final ImageView mIcon;
    private final View mItemView;
    private final TextView mNumberOfPunchesTextView;
    private final OfferListItemClickListener mOfferListItemClickListener;
    private final TextView mPunchNumberSubtitleTextView;
    private final TextView mSubtitle;
    private final TextView mTitle;

    public interface OfferListItemClickListener {
        void onListItemClick(int i);
    }

    private PunchcardOfferListViewHolder(View itemView, OfferListItemClickListener offerListItemClickListener) {
        super(itemView);
        this.mItemView = itemView;
        this.mItemView.setOnClickListener(this);
        this.mOfferListItemClickListener = offerListItemClickListener;
        this.mIcon = (ImageView) itemView.findViewById(2131820643);
        this.mTitle = (TextView) itemView.findViewById(C2358R.C2357id.name);
        this.mSubtitle = (TextView) itemView.findViewById(C2358R.C2357id.expiration);
        this.mNumberOfPunchesTextView = (TextView) itemView.findViewById(C2358R.C2357id.number_of_punches);
        this.mPunchNumberSubtitleTextView = (TextView) itemView.findViewById(C2358R.C2357id.punch_number_subtitle);
        this.mAddedToOrderView = itemView.findViewById(C2358R.C2357id.added_to_order_icon);
    }

    public static ViewHolder create(LayoutInflater layoutInflater, final ArrayList<DashboardItem> dataSet, final DashboardListener dashboardListener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.PunchcardOfferListViewHolder", "create", new Object[]{layoutInflater, dataSet, dashboardListener});
        return new PunchcardOfferListViewHolder(layoutInflater.inflate(C2658R.layout.punchcard_offer_list_item, null), new OfferListItemClickListener() {
            public void onListItemClick(int clickPosition) {
                Ensighten.evaluateEvent(this, "onListItemClick", new Object[]{new Integer(clickPosition)});
                if (dashboardListener != null) {
                    dashboardListener.onGridItemClick((Offer) ((DashboardItem) dataSet.get(clickPosition)).getObject());
                }
            }
        });
    }

    public static void bind(Context context, PunchcardOfferListViewHolder viewHolder, Offer offer, boolean isAddedToOrder) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.PunchcardOfferListViewHolder", "bind", new Object[]{context, viewHolder, offer, new Boolean(isAddedToOrder)});
        viewHolder.mAddedToOrderView.setVisibility(isAddedToOrder ? 0 : 8);
        viewHolder.mTitle.setText(offer.getName());
        viewHolder.mSubtitle.setText(context.getString(C2658R.string.expires) + UIUtils.formatDateMonthDayYear(offer.getLocalValidThrough()));
        int totalPunch = 0;
        int currentPunch = 0;
        if (offer.getTotalPunch() != null) {
            totalPunch = offer.getTotalPunch().intValue();
        }
        if (offer.getCurrentPunch() != null) {
            currentPunch = offer.getCurrentPunch().intValue();
        }
        int punchesLeft = totalPunch - currentPunch;
        if (currentPunch == 0) {
            viewHolder.mPunchNumberSubtitleTextView.setText(context.getResources().getString(C2658R.string.start_your_punchcard));
            viewHolder.mPunchNumberSubtitleTextView.setTextColor(context.getResources().getColor(17170444));
            viewHolder.mNumberOfPunchesTextView.setVisibility(8);
        } else if (currentPunch == totalPunch) {
            viewHolder.mPunchNumberSubtitleTextView.setText(context.getResources().getString(C2658R.string.claim_your_reward));
            viewHolder.mPunchNumberSubtitleTextView.setTextColor(context.getResources().getColor(C2658R.color.mcd_red));
            viewHolder.mNumberOfPunchesTextView.setVisibility(8);
        } else {
            CharSequence string;
            viewHolder.mNumberOfPunchesTextView.setText(String.valueOf(punchesLeft));
            viewHolder.mPunchNumberSubtitleTextView.setTextColor(context.getResources().getColor(17170444));
            TextView textView = viewHolder.mPunchNumberSubtitleTextView;
            if (punchesLeft == 1) {
                string = context.getString(C2658R.string.punch_left);
            } else {
                string = context.getString(C2658R.string.punches_left);
            }
            textView.setText(string);
        }
        Glide.with(context).load(offer.getSmallImagePath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.transparent).into(viewHolder.mIcon);
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (this.mOfferListItemClickListener != null) {
            this.mOfferListItemClickListener.onListItemClick(getPosition());
        }
    }
}
