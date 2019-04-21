package com.mcdonalds.app.ordering.preparepayment;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.storelocator.Store;

public class PickupLocationView extends FrameLayout {
    private ImageView mDisclosureIcon;
    private Store mStore;
    private TextView mStoreName;

    public PickupLocationView(Context context) {
        super(context);
        inflate(context);
    }

    public PickupLocationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context);
    }

    private void inflate(Context context) {
        Ensighten.evaluateEvent(this, "inflate", new Object[]{context});
        inflate(context, C2658R.layout.pickup_location, this);
        if (!isInEditMode()) {
            initWidgets();
        }
    }

    private void initWidgets() {
        Ensighten.evaluateEvent(this, "initWidgets", null);
        this.mStoreName = (TextView) findViewById(C2358R.C2357id.pickup_store_name);
        this.mDisclosureIcon = (ImageView) findViewById(C2358R.C2357id.pickup_store_image);
        this.mDisclosureIcon.setVisibility(8);
    }

    private void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        this.mStoreName.setText(this.mStore.getName());
    }

    public void setStore(Store store) {
        Ensighten.evaluateEvent(this, "setStore", new Object[]{store});
        this.mStore = store;
        refresh();
    }
}
