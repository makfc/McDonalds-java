package com.mcdonalds.app.social;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class SocialLoginViewHolder {
    private LinearLayout mItemsContainer = ((LinearLayout) this.mView.findViewById(C2358R.C2357id.social_items_container));
    private TextView mTitle = ((TextView) this.mView.findViewById(C2358R.C2357id.social_title));
    private View mView;

    public SocialLoginViewHolder(View container) {
        this.mView = container;
    }

    public LinearLayout getItemsContainer() {
        Ensighten.evaluateEvent(this, "getItemsContainer", null);
        return this.mItemsContainer;
    }
}
