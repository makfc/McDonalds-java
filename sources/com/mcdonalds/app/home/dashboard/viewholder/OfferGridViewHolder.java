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

public class OfferGridViewHolder extends ViewHolder implements OnClickListener {
    protected View mAddedToOrderView;
    protected TextView mExpirationTextView;
    protected ImageView mFoodImageView;
    protected TextView mFoodNameTextView;
    protected View mGridItem;
    private final OfferGridItemClickListener mOfferGridItemClickListener;
    protected View mOfferTypeContainer;

    public interface OfferGridItemClickListener {
        void onGridItemClicked(int i);
    }

    private OfferGridViewHolder(View itemView, OfferGridItemClickListener offerGridItemClickListener) {
        super(itemView);
        this.mOfferGridItemClickListener = offerGridItemClickListener;
        this.mOfferTypeContainer = itemView.findViewById(C2358R.C2357id.offer_type_containter);
        this.mFoodNameTextView = (TextView) itemView.findViewById(C2358R.C2357id.name);
        this.mFoodImageView = (ImageView) itemView.findViewById(2131820643);
        this.mGridItem = itemView.findViewById(C2358R.C2357id.grid_item);
        this.mExpirationTextView = (TextView) itemView.findViewById(C2358R.C2357id.expiration);
        this.mAddedToOrderView = itemView.findViewById(C2358R.C2357id.added_to_order_icon);
        itemView.setOnClickListener(this);
    }

    public static ViewHolder create(LayoutInflater inflater, final List<DashboardItem> dataSet, final DashboardListener dashboardListener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.OfferGridViewHolder", "create", new Object[]{inflater, dataSet, dashboardListener});
        return new OfferGridViewHolder(inflater.inflate(C2658R.layout.offer_grid_item, null), new OfferGridItemClickListener() {
            public void onGridItemClicked(int clickPosition) {
                Ensighten.evaluateEvent(this, "onGridItemClicked", new Object[]{new Integer(clickPosition)});
                if (dashboardListener != null) {
                    dashboardListener.onGridItemClick((Offer) ((DashboardItem) dataSet.get(clickPosition)).getObject());
                }
            }
        });
    }

    public static void bind(OfferGridViewHolder viewHolder, Offer offer, boolean isAddedToOrder, Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.OfferGridViewHolder", "bind", new Object[]{viewHolder, offer, new Boolean(isAddedToOrder), context});
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
        viewHolder.mGridItem.setVisibility(0);
        Glide.with(context).load(offer.getSmallImagePath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.icon_meal_gray).into(viewHolder.mFoodImageView);
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        DataLayerManager.getInstance().logItemClick("OfferItemPressed", getPosition());
        if (this.mOfferGridItemClickListener != null) {
            this.mOfferGridItemClickListener.onGridItemClicked(getPosition());
        }
    }
}
