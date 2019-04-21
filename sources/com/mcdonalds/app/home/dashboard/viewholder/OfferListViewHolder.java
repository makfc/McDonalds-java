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

public class OfferListViewHolder extends ViewHolder implements OnClickListener {
    View mAddedToOrderView;
    private final ImageView mIcon;
    private final View mItemView;
    private final OfferListItemClickListener mOfferListItemClickListener;
    private final TextView mSubtitle;
    private final TextView mTitle;

    public interface OfferListItemClickListener {
        void onListItemClick(int i);
    }

    private OfferListViewHolder(View itemView, OfferListItemClickListener offerListItemClickListener) {
        super(itemView);
        this.mItemView = itemView;
        this.mItemView.setOnClickListener(this);
        this.mOfferListItemClickListener = offerListItemClickListener;
        this.mIcon = (ImageView) itemView.findViewById(C2358R.C2357id.icon_imageview);
        this.mTitle = (TextView) itemView.findViewById(C2358R.C2357id.title_textview);
        this.mSubtitle = (TextView) itemView.findViewById(C2358R.C2357id.subtitle_textview);
        this.mAddedToOrderView = itemView.findViewById(C2358R.C2357id.added_to_order_icon);
    }

    public static ViewHolder create(LayoutInflater layoutInflater, final ArrayList<DashboardItem> dataSet, final DashboardListener dashboardListener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.OfferListViewHolder", "create", new Object[]{layoutInflater, dataSet, dashboardListener});
        return new OfferListViewHolder(layoutInflater.inflate(C2658R.layout.offer_list_item, null), new OfferListItemClickListener() {
            public void onListItemClick(int clickPosition) {
                Ensighten.evaluateEvent(this, "onListItemClick", new Object[]{new Integer(clickPosition)});
                if (dashboardListener != null) {
                    dashboardListener.onListItemClick((Offer) ((DashboardItem) dataSet.get(clickPosition)).getObject());
                }
            }
        });
    }

    public static void bind(OfferListViewHolder viewHolder, Offer offer, boolean isAddedToOrder, Context context) {
        int i = 0;
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.OfferListViewHolder", "bind", new Object[]{viewHolder, offer, new Boolean(isAddedToOrder), context});
        View view = viewHolder.mAddedToOrderView;
        if (!isAddedToOrder) {
            i = 8;
        }
        view.setVisibility(i);
        viewHolder.mTitle.setText(offer.getName());
        viewHolder.mSubtitle.setText(context.getString(C2658R.string.expires) + UIUtils.formatDateMonthDayYear(offer.getLocalValidThrough()));
        Glide.with(context).load(offer.getSmallImagePath()).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.transparent).into(viewHolder.mIcon);
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (this.mOfferListItemClickListener != null) {
            this.mOfferListItemClickListener.onListItemClick(getPosition());
        }
    }
}
