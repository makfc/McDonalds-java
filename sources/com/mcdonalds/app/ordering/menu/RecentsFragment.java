package com.mcdonalds.app.ordering.menu;

import android.os.Bundle;
import android.support.p000v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.nutrition.OrderPagerAdapter;
import com.mcdonalds.app.nutrition.RecentsListActivity;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.OfferUtils;
import com.mcdonalds.app.widget.PagerIndicator;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.util.ArrayList;
import java.util.List;

public class RecentsFragment extends URLNavigationFragment {
    private AsyncListener<List<CustomerOrder>> mCustomerOrderListener = new C35762();
    View mNoRecentOrders;
    private AsyncListener<List<Order>> mOrderListener = new C35773();
    ViewPager mOrderPager;
    OrderPagerAdapter mPagerAdapter;
    PagerIndicator mPagerIndicator;
    ProgressBar mProgressBar;
    List<Order> mRecentOrders = new ArrayList();
    TextView mSeeAll;

    /* renamed from: com.mcdonalds.app.ordering.menu.RecentsFragment$1 */
    class C35751 implements OnClickListener {
        C35751() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (RecentsFragment.this.mRecentOrders.size() > 0) {
                Bundle bundle = new Bundle();
                bundle.putInt("ARG_RECENT_ORDERS_LIST_KEY", DataPasser.getInstance().putData(RecentsFragment.this.mRecentOrders));
                RecentsFragment.this.getNavigationActivity().startActivity(RecentsListActivity.class, bundle);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.RecentsFragment$2 */
    class C35762 implements AsyncListener<List<CustomerOrder>> {
        C35762() {
        }

        public void onResponse(List<CustomerOrder> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (RecentsFragment.this.getActivity() != null && !RecentsFragment.this.getActivity().isFinishing() && !RecentsFragment.this.getActivity().isDestroyed()) {
                if (exception != null) {
                    RecentsFragment.access$000(RecentsFragment.this, false);
                    AsyncException.report(exception);
                } else if (response != null) {
                    OrderUtils.ordersFromCustomerOrders(OfferUtils.filterIfFinalized(response), (OrderingModule) ModuleManager.getModule("ordering"), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.RecentsFragment", "access$100", new Object[]{RecentsFragment.this}));
                } else {
                    RecentsFragment.access$000(RecentsFragment.this, false);
                    AsyncException.report(new AsyncException(RecentsFragment.this.getString(C2658R.string.error_unknown)));
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.RecentsFragment$3 */
    class C35773 implements AsyncListener<List<Order>> {
        C35773() {
        }

        public void onResponse(List<Order> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            RecentsFragment.access$000(RecentsFragment.this, false);
            if (exception != null) {
                AsyncException.report(exception);
            } else if (response == null || response.isEmpty()) {
                RecentsFragment.this.showNoRecentOrders();
            } else {
                RecentsFragment.this.mRecentOrders.addAll(response);
                RecentsFragment.this.mPagerAdapter.setOrders(response);
                RecentsFragment.this.mOrderPager.setOffscreenPageLimit(RecentsFragment.this.mPagerAdapter.getCount());
                RecentsFragment.this.mSeeAll.setVisibility(0);
            }
        }
    }

    static /* synthetic */ void access$000(RecentsFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.RecentsFragment", "access$000", new Object[]{x0, new Boolean(x1)});
        x0.setRefreshing(x1);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C2658R.layout.fragment_recents, container, false);
        this.mNoRecentOrders = v.findViewById(C2358R.C2357id.no_orders);
        this.mProgressBar = (ProgressBar) v.findViewById(C2358R.C2357id.refresher);
        this.mOrderPager = (ViewPager) v.findViewById(C2358R.C2357id.order_pager);
        this.mPagerAdapter = new OrderPagerAdapter(getChildFragmentManager());
        this.mOrderPager.setAdapter(this.mPagerAdapter);
        this.mPagerIndicator = (PagerIndicator) v.findViewById(C2358R.C2357id.pager_indicator);
        this.mPagerIndicator.setupWithViewPager(this.mOrderPager);
        this.mSeeAll = (TextView) v.findViewById(C2358R.C2357id.see_all_recents);
        this.mSeeAll.setVisibility(8);
        this.mSeeAll.setOnClickListener(new C35751());
        fetchOrders(OrderUtils.getNumberOfRecentOrder());
        return v;
    }

    private void fetchOrders(int num) {
        Ensighten.evaluateEvent(this, "fetchOrders", new Object[]{new Integer(num)});
        if (!isRefreshing()) {
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            setRefreshing(true);
            this.mRecentOrders.clear();
            customerModule.getRecentOrders(customerModule.getCurrentProfile(), Integer.valueOf(num), this.mCustomerOrderListener);
        }
    }

    /* Access modifiers changed, original: protected */
    public void showNoRecentOrders() {
        Ensighten.evaluateEvent(this, "showNoRecentOrders", null);
        this.mNoRecentOrders.setVisibility(0);
    }

    private void setRefreshing(boolean refreshing) {
        int i = 0;
        Ensighten.evaluateEvent(this, "setRefreshing", new Object[]{new Boolean(refreshing)});
        ProgressBar progressBar = this.mProgressBar;
        if (!refreshing) {
            i = 8;
        }
        progressBar.setVisibility(i);
    }

    private boolean isRefreshing() {
        Ensighten.evaluateEvent(this, "isRefreshing", null);
        return this.mProgressBar.getVisibility() == 0;
    }
}
