package com.mcdonalds.app.home.dashboard.viewholder;

import android.content.Context;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.home.HomeListItem;
import com.mcdonalds.app.home.dashboard.DashboardAdapter.DashboardListener;
import com.mcdonalds.app.home.dashboard.DashboardItem;
import com.mcdonalds.gma.hongkong.C2658R;
import java.util.Arrays;
import java.util.List;

public class HomeItemViewHolder extends ViewHolder implements OnClickListener {
    private final HomeItemClickListener mClickListener;
    public ImageView mIcon;
    public View mRootView;
    public TextView mSubtitle;
    public TextView mTitle;

    public interface HomeItemClickListener {
        void onHomeItemClick(int i);
    }

    private HomeItemViewHolder(View itemView, HomeItemClickListener clickListener) {
        super(itemView);
        this.mClickListener = clickListener;
        this.mRootView = itemView.findViewById(C2358R.C2357id.container);
        this.mIcon = (ImageView) itemView.findViewById(C2358R.C2357id.icon_imageview);
        this.mTitle = (TextView) itemView.findViewById(C2358R.C2357id.title_textview);
        this.mSubtitle = (TextView) itemView.findViewById(C2358R.C2357id.subtitle_textview);
        itemView.setOnClickListener(this);
    }

    public static ViewHolder create(LayoutInflater layoutInflater, final DashboardListener dashboardListener, final List<DashboardItem> dataSet) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.HomeItemViewHolder", "create", new Object[]{layoutInflater, dashboardListener, dataSet});
        return new HomeItemViewHolder(layoutInflater.inflate(C2658R.layout.view_home_list_item, null), new HomeItemClickListener() {
            public void onHomeItemClick(int clickPosition) {
                Ensighten.evaluateEvent(this, "onHomeItemClick", new Object[]{new Integer(clickPosition)});
                if (dashboardListener != null) {
                    dashboardListener.onHomeListItemClicked((HomeListItem) ((DashboardItem) dataSet.get(clickPosition)).getObject());
                }
            }
        });
    }

    public static void bind(Context context, HomeItemViewHolder viewHolder, HomeListItem homeListItem) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.HomeItemViewHolder", "bind", new Object[]{context, viewHolder, homeListItem});
        viewHolder.mTitle.setText(homeListItem.getTitle());
        viewHolder.mSubtitle.setText(homeListItem.getSubTitle());
        viewHolder.mIcon.setImageResource(homeListItem.getIconImageResource());
        if (Arrays.asList(new String[]{context.getString(C2658R.string.last_order_title), context.getString(C2658R.string.scheduled_order)}).contains(homeListItem.getTitle())) {
            viewHolder.mRootView.setBackgroundResource(17170445);
        }
        DataLayerClickListener.setDataLayerTag(viewHolder.itemView, homeListItem.getDataLayerTag());
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (this.mClickListener != null) {
            this.mClickListener.onHomeItemClick(getPosition());
        }
    }
}
