package com.mcdonalds.app.ordering.menu;

import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.List;

public class LastOrderActivity extends URLActionBarActivity {
    private View mBasketBadgeContainer;
    private Boolean mBasketBadgeContainerIsHidden = Boolean.valueOf(true);
    private TextView mBasketBadgeLabel;
    private int mCount;

    /* renamed from: com.mcdonalds.app.ordering.menu.LastOrderActivity$1 */
    class C35311 implements OnClickListener {
        C35311() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            LastOrderActivity.this.navigateToBasket();
        }
    }

    /* Access modifiers changed, original: protected */
    public int getContainerResource() {
        Ensighten.evaluateEvent(this, "getContainerResource", null);
        return C2358R.C2357id.container;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle extras = getIntent().getExtras();
        OrderDetailsFragment fragment = new OrderDetailsFragment();
        if (extras == null || extras.getSerializable(JiceArgs.EVENT_SUBMIT_ORDER) == null) {
            List<CustomerOrder> orders = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getRecentOrders();
            if (!ListUtils.isEmpty(orders)) {
                CustomerOrder lastOrder = (CustomerOrder) orders.get(0);
                extras = new Bundle();
                extras.putSerializable(JiceArgs.EVENT_SUBMIT_ORDER, lastOrder);
                fragment.setArguments(extras);
            }
        } else {
            fragment.setArguments(extras);
        }
        transaction.replace(getContainerResource(), fragment);
        transaction.commit();
        setupBasketBadgeView();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        adjustBasketBadgePosition(true);
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }

    private void adjustBasketBadgePosition(boolean animated) {
        Ensighten.evaluateEvent(this, "adjustBasketBadgePosition", new Object[]{new Boolean(animated)});
        updateHiddenState();
        this.mBasketBadgeLabel.setText(Integer.toString(this.mCount));
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
        if (this.mBasketBadgeContainerIsHidden.booleanValue()) {
            this.mBasketBadgeContainer.setVisibility(8);
        } else {
            this.mBasketBadgeContainer.setVisibility(0);
        }
        LocalDataManager.getSharedInstance().setCurrentOrder(order);
    }

    private void setupBasketBadgeView() {
        Ensighten.evaluateEvent(this, "setupBasketBadgeView", null);
        this.mBasketBadgeContainer = findViewById(C2358R.C2357id.basket_badge);
        this.mBasketBadgeLabel = (TextView) this.mBasketBadgeContainer.findViewById(C2358R.C2357id.basket_badge_label);
        this.mBasketBadgeContainer.setOnClickListener(new C35311());
        adjustBasketBadgePosition(false);
        updateHiddenState();
    }

    /* Access modifiers changed, original: protected */
    public void navigateToBasket() {
        Ensighten.evaluateEvent(this, "navigateToBasket", null);
        if (ModuleManager.getSharedInstance().getCurrentOrder() != null) {
            AnalyticsUtils.trackOnClickEvent("/home", "Basket");
            startActivity(BasketActivity.class, "basket");
        }
    }
}
