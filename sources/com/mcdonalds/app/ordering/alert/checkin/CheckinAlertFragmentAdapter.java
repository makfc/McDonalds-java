package com.mcdonalds.app.ordering.alert.checkin;

import android.graphics.drawable.Drawable;
import android.support.p000v4.content.ContextCompat;
import android.support.p001v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.List;

public class CheckinAlertFragmentAdapter extends Adapter<ViewHolder> {
    private List<ProblematicProduct> dataSet;
    private boolean showWarning = Configuration.getSharedInstance().getBooleanForKey("interface.ordering.showWarningOnAlertForOOS");

    public static class ProblematicProduct {
        public boolean mainProduct;
        public String productName;
        public boolean shouldShowAlert;

        public ProblematicProduct(String productName, boolean shouldShowAlert) {
            this.productName = productName;
            this.shouldShowAlert = shouldShowAlert;
        }

        public ProblematicProduct(String productName, boolean shouldShowAlert, boolean mainProduct) {
            this.productName = productName;
            this.shouldShowAlert = shouldShowAlert;
            this.mainProduct = mainProduct;
        }
    }

    protected class ViewHolder extends android.support.p001v7.widget.RecyclerView.ViewHolder {
        private TextView itemName;
        private View spacing;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemName = (TextView) itemView.findViewById(C2358R.C2357id.item_name);
            this.spacing = itemView.findViewById(C2358R.C2357id.spacing);
        }
    }

    public CheckinAlertFragmentAdapter(List<ProblematicProduct> dataSet) {
        this.dataSet = dataSet;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C2658R.layout.checkin_alert_list_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Ensighten.evaluateEvent(this, "onBindViewHolder", new Object[]{holder, new Integer(position)});
        ProblematicProduct problematicProduct = (ProblematicProduct) this.dataSet.get(position);
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter$ViewHolder", "access$000", new Object[]{holder}).setText(problematicProduct.productName);
        if (this.showWarning && problematicProduct.shouldShowAlert) {
            Drawable drawable = ContextCompat.getDrawable(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter$ViewHolder", "access$000", new Object[]{holder}).getContext(), C2358R.C2359drawable.icon_warn);
            int bounds = UIUtils.dpAsPixels(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter$ViewHolder", "access$000", new Object[]{holder}).getContext(), 24);
            drawable.setBounds(0, 0, bounds, bounds);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter$ViewHolder", "access$000", new Object[]{holder}).setCompoundDrawables(null, null, drawable, null);
        } else {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter$ViewHolder", "access$000", new Object[]{holder}).setCompoundDrawables(null, null, null, null);
        }
        if (problematicProduct.mainProduct) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter$ViewHolder", "access$100", new Object[]{holder}).setVisibility(0);
        } else {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter$ViewHolder", "access$100", new Object[]{holder}).setVisibility(8);
        }
    }

    public int getItemCount() {
        Ensighten.evaluateEvent(this, "getItemCount", null);
        return this.dataSet.size();
    }
}
