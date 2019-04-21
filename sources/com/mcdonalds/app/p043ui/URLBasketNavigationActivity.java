package com.mcdonalds.app.p043ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.services.data.LocalDataManager;

/* renamed from: com.mcdonalds.app.ui.URLBasketNavigationActivity */
public abstract class URLBasketNavigationActivity extends URLActionBarActivity {
    protected View mBasketBadgeContainer;
    private Boolean mBasketBadgeContainerIsHidden = Boolean.valueOf(true);
    private TextView mBasketBadgeLabel;
    int mCount;

    /* renamed from: com.mcdonalds.app.ui.URLBasketNavigationActivity$1 */
    class C29201 implements OnClickListener {
        C29201() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            URLBasketNavigationActivity.this.navigateToBasket();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBasketBadgeView();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        adjustBasketBadgePosition(true);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    private void setupBasketBadgeView() {
        Ensighten.evaluateEvent(this, "setupBasketBadgeView", null);
        this.mBasketBadgeContainer = findViewById(C2358R.C2357id.basket_badge);
        this.mBasketBadgeLabel = (TextView) this.mBasketBadgeContainer.findViewById(C2358R.C2357id.basket_badge_label);
        this.mBasketBadgeContainer.setOnClickListener(new C29201());
        adjustBasketBadgePosition(false);
        updateHiddenState();
        DataLayerClickListener.setDataLayerTag(this.mBasketBadgeContainer, "ShowBasketButtonPressed");
    }

    /* Access modifiers changed, original: protected */
    public void navigateToBasket() {
        Ensighten.evaluateEvent(this, "navigateToBasket", null);
        if (ModuleManager.getSharedInstance().getCurrentOrder() != null) {
            AnalyticsUtils.trackOnClickEvent("/home", "Basket");
            startActivity(BasketActivity.class, "basket");
        }
    }

    public void updateBasketBadge() {
        Ensighten.evaluateEvent(this, "updateBasketBadge", null);
        adjustBasketBadgePosition(true);
    }

    public void bringBasketToFront() {
        Ensighten.evaluateEvent(this, "bringBasketToFront", null);
        this.mBasketBadgeContainer.bringToFront();
    }

    private void adjustBasketBadgePosition(boolean animated) {
        Ensighten.evaluateEvent(this, "adjustBasketBadgePosition", new Object[]{new Boolean(animated)});
        updateHiddenState();
        if (this.mCount == 0) {
            this.mBasketBadgeLabel.setVisibility(8);
        } else {
            this.mBasketBadgeLabel.setText(Integer.toString(this.mCount));
            this.mBasketBadgeLabel.setVisibility(0);
        }
        int translationY = 0;
        if (this.mBasketBadgeContainerIsHidden.booleanValue()) {
            translationY = -this.mBasketBadgeContainer.getHeight();
        }
        if (animated) {
            this.mBasketBadgeContainer.animate().translationY((float) translationY);
        } else {
            this.mBasketBadgeContainer.setTranslationY((float) translationY);
        }
    }

    private void updateHiddenState() {
        boolean z = true;
        Ensighten.evaluateEvent(this, "updateHiddenState", null);
        Order order = OrderingManager.getInstance().getCurrentOrder();
        if (order == null) {
            this.mBasketBadgeContainerIsHidden = Boolean.valueOf(true);
        } else {
            this.mCount = order.getTotalOrderCount();
            if (this.mCount != 0) {
                z = false;
            }
            this.mBasketBadgeContainerIsHidden = Boolean.valueOf(z);
        }
        if (this.mCount == 0 && order != null && order.getOffers() != null && order.getOffers().size() > 0) {
            this.mBasketBadgeContainerIsHidden = Boolean.valueOf(false);
        }
        if (this.mBasketBadgeContainerIsHidden.booleanValue()) {
            this.mBasketBadgeContainer.setVisibility(8);
        } else {
            this.mBasketBadgeContainer.setVisibility(0);
        }
        LocalDataManager.getSharedInstance().setCurrentOrder(order);
    }
}
