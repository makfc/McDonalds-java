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
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.home.dashboard.DashboardAdapter.DashboardListener;
import com.mcdonalds.app.home.dashboard.DashboardItem;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.List;

public class PunchcardOfferGridViewHolder extends ViewHolder implements OnClickListener {
    protected View mAddedToOrderView;
    protected TextView mExpirationTextView;
    protected ImageView mFoodImageView;
    protected TextView mFoodNameTextView;
    protected View mGridItem;
    private final TextView mNumberOfPunchesTextView;
    private final OfferGridItemClickListener mOfferGridItemClickListener;
    protected View mOfferTypeContainer;
    private final TextView mPunchNumberSubtitleTextView;

    public interface OfferGridItemClickListener {
        void onGridItemClicked(int i);
    }

    private PunchcardOfferGridViewHolder(View itemView, OfferGridItemClickListener offerGridItemClickListener) {
        super(itemView);
        this.mOfferGridItemClickListener = offerGridItemClickListener;
        this.mOfferTypeContainer = itemView.findViewById(C2358R.C2357id.offer_type_containter);
        this.mFoodNameTextView = (TextView) itemView.findViewById(C2358R.C2357id.name);
        this.mFoodImageView = (ImageView) itemView.findViewById(2131820643);
        this.mGridItem = itemView.findViewById(C2358R.C2357id.grid_item);
        this.mExpirationTextView = (TextView) itemView.findViewById(C2358R.C2357id.expiration);
        this.mNumberOfPunchesTextView = (TextView) itemView.findViewById(C2358R.C2357id.number_of_punches);
        this.mPunchNumberSubtitleTextView = (TextView) itemView.findViewById(C2358R.C2357id.punch_number_subtitle);
        this.mAddedToOrderView = itemView.findViewById(C2358R.C2357id.added_to_order_icon);
        itemView.setOnClickListener(this);
    }

    public static ViewHolder create(LayoutInflater inflater, final List<DashboardItem> dataSet, final DashboardListener dashboardListener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.PunchcardOfferGridViewHolder", "create", new Object[]{inflater, dataSet, dashboardListener});
        return new PunchcardOfferGridViewHolder(inflater.inflate(C2658R.layout.punchcard_offer_grid_item, null), new OfferGridItemClickListener() {
            public void onGridItemClicked(int clickPosition) {
                Ensighten.evaluateEvent(this, "onGridItemClicked", new Object[]{new Integer(clickPosition)});
                if (dashboardListener != null) {
                    dashboardListener.onGridItemClick((Offer) ((DashboardItem) dataSet.get(clickPosition)).getObject());
                }
            }
        });
    }

    public static void bind(Context context, PunchcardOfferGridViewHolder viewHolder, Offer offer, boolean isAddedToOrder) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.PunchcardOfferGridViewHolder", "bind", new Object[]{context, viewHolder, offer, new Boolean(isAddedToOrder)});
        if (Configuration.getSharedInstance().getBooleanForKey("interface.offers.showPickupDeliveryFlag")) {
            boolean isDeliveryOnly = offer.isDeliveryOffer() && !offer.isPickupOffer();
            boolean isPickUpOnly = offer.isPickupOffer() && !offer.isDeliveryOffer();
            if (isDeliveryOnly || isPickUpOnly) {
                viewHolder.mOfferTypeContainer.setVisibility(0);
                ImageView mIcon = (ImageView) viewHolder.mOfferTypeContainer.findViewById(C2358R.C2357id.offer_type_icon);
                TextView mText = (TextView) viewHolder.mOfferTypeContainer.findViewById(C2358R.C2357id.offer_type_text);
                if (isDeliveryOnly) {
                    mIcon.setImageResource(C2358R.C2359drawable.ic_mcd_order_bike);
                    mText.setText(C2658R.string.delivery_only);
                } else if (isPickUpOnly) {
                    mIcon.setImageResource(C2358R.C2359drawable.ic_mcd_meal_icon_red);
                    mText.setText(C2658R.string.pickup_or_in_restaurant_only);
                }
            } else {
                viewHolder.mOfferTypeContainer.setVisibility(8);
            }
        }
        viewHolder.mAddedToOrderView.setVisibility(isAddedToOrder ? 0 : 8);
        viewHolder.mFoodNameTextView.setText(offer.getName());
        viewHolder.mExpirationTextView.setText(context.getString(C2658R.string.expires) + UIUtils.formatDateMonthDayYear(offer.getLocalValidThrough()));
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
        } else if (currentPunch >= totalPunch) {
            viewHolder.mPunchNumberSubtitleTextView.setText(context.getResources().getString(C2658R.string.claim_your_reward));
            viewHolder.mPunchNumberSubtitleTextView.setTextColor(context.getResources().getColor(C2658R.color.mcd_red));
            viewHolder.mNumberOfPunchesTextView.setVisibility(8);
        } else {
            CharSequence string;
            viewHolder.mNumberOfPunchesTextView.setText(String.valueOf(punchesLeft));
            viewHolder.mNumberOfPunchesTextView.setVisibility(0);
            viewHolder.mPunchNumberSubtitleTextView.setTextColor(context.getResources().getColor(17170444));
            TextView textView = viewHolder.mPunchNumberSubtitleTextView;
            if (punchesLeft == 1) {
                string = context.getString(C2658R.string.punch_left);
            } else {
                string = context.getString(C2658R.string.punches_left);
            }
            textView.setText(string);
        }
        viewHolder.mGridItem.setVisibility(0);
        Glide.with(context).load(offer.getSmallImagePath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.icon_meal_gray).into(viewHolder.mFoodImageView);
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        DataLayerManager.getInstance().logItemClick("PunchCardOfferItemPressed", getPosition());
        if (this.mOfferGridItemClickListener != null) {
            this.mOfferGridItemClickListener.onGridItemClicked(getPosition());
        }
    }
}
