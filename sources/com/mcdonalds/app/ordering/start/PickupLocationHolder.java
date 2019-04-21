package com.mcdonalds.app.ordering.start;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class PickupLocationHolder {
    private View mContainer;
    private ImageView mDisclosureIcon;
    private TextView mDistance;
    private View mExtraView;
    private ImageView mInfoIcon;
    private View mPickupStoreView;
    private TextView mStoreName;
    private TextView mTitle;

    public PickupLocationHolder(View container) {
        this.mContainer = container;
        this.mPickupStoreView = container.findViewById(C2358R.C2357id.pickup_store_button);
        this.mExtraView = container.findViewById(C2358R.C2357id.pickup_store_extra);
        this.mTitle = (TextView) container.findViewById(C2358R.C2357id.pickup_store_title);
        this.mStoreName = (TextView) container.findViewById(C2358R.C2357id.pickup_store_name);
        this.mDistance = (TextView) container.findViewById(C2358R.C2357id.pickup_store_distance);
        this.mDisclosureIcon = (ImageView) container.findViewById(C2358R.C2357id.pickup_store_image);
        this.mInfoIcon = (ImageView) container.findViewById(C2358R.C2357id.pickup_store_info_button);
    }

    public View getContainer() {
        Ensighten.evaluateEvent(this, "getContainer", null);
        return this.mContainer;
    }

    public View getPickupStoreView() {
        Ensighten.evaluateEvent(this, "getPickupStoreView", null);
        return this.mPickupStoreView;
    }

    public TextView getStoreName() {
        Ensighten.evaluateEvent(this, "getStoreName", null);
        return this.mStoreName;
    }

    public ImageView getDisclosureIcon() {
        Ensighten.evaluateEvent(this, "getDisclosureIcon", null);
        return this.mDisclosureIcon;
    }

    public View getExtraView() {
        Ensighten.evaluateEvent(this, "getExtraView", null);
        return this.mExtraView;
    }

    public ImageView getInfoIcon() {
        Ensighten.evaluateEvent(this, "getInfoIcon", null);
        return this.mInfoIcon;
    }

    public TextView getDistance() {
        Ensighten.evaluateEvent(this, "getDistance", null);
        return this.mDistance;
    }
}
