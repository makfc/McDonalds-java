package com.mcdonalds.app.ordering.menu;

import android.app.Activity;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
import android.support.p001v7.widget.GridLayoutManager;
import android.support.p001v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.p001v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.customer.SignUpActivity;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.ordering.menu.FavoritesAdapter.OnMenuGridItemClickListener;
import com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.FavoriteItem.FavoriteProductType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.services.data.provider.Contract.Favorites;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Instrumented
public class FavoritesFragment extends Fragment implements OnClickListener, OnMenuGridItemClickListener, TraceFieldInterface {
    public Trace _nr_trace;
    private boolean isRefreshed;
    ContentObserver mContentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange)});
            super.onChange(selfChange);
            FavoritesFragment.access$002(FavoritesFragment.this, false);
        }
    };
    private Comparator<FavoriteItem> mFavoriteItemComparator = new C35296();
    FavoritesAdapter mFavoritesAdapter;
    RecyclerView mFavoritesGrid;
    View mNoFavoritesView;
    ProgressBar mProgressBar;
    View mRegisterButton;
    View mSignInButton;
    View mSignedOutView;
    SpanSizeLookup mSpanSizeLookup = new C35307();

    /* renamed from: com.mcdonalds.app.ordering.menu.FavoritesFragment$2 */
    class C35252 implements AsyncListener<List<FavoriteItem>> {
        C35252() {
        }

        public void onResponse(List<FavoriteItem> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            FavoritesFragment.access$100(FavoritesFragment.this);
            UIUtils.stopActivityIndicator();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.FavoritesFragment$3 */
    class C35263 implements AsyncListener<List<Order>> {
        C35263() {
        }

        public void onResponse(List<Order> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (FavoritesFragment.this.getActivity() != null) {
                FavoritesFragment.access$200(FavoritesFragment.this, false);
                if (exception == null) {
                    FavoritesFragment.this.mFavoritesAdapter.setOrders(response);
                } else {
                    FavoritesFragment.access$002(FavoritesFragment.this, false);
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.FavoritesFragment$4 */
    class C35274 implements AsyncListener<List<OrderProduct>> {
        C35274() {
        }

        public void onResponse(List<OrderProduct> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            FavoritesFragment.access$200(FavoritesFragment.this, false);
            if (exception == null) {
                List<OrderProduct> favorites = new ArrayList();
                for (OrderProduct product : response) {
                    if (product != null) {
                        favorites.add(product);
                    }
                }
                FavoritesFragment.this.mFavoritesAdapter.setProducts(favorites);
                return;
            }
            FavoritesFragment.access$002(FavoritesFragment.this, false);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.FavoritesFragment$6 */
    class C35296 implements Comparator<FavoriteItem> {
        C35296() {
        }

        public int compare(FavoriteItem lhs, FavoriteItem rhs) {
            Ensighten.evaluateEvent(this, "compare", new Object[]{lhs, rhs});
            return rhs.getFavoriteId().intValue() - lhs.getFavoriteId().intValue();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.FavoritesFragment$7 */
    class C35307 extends SpanSizeLookup {
        C35307() {
        }

        public int getSpanSize(int position) {
            Ensighten.evaluateEvent(this, "getSpanSize", new Object[]{new Integer(position)});
            return FavoritesFragment.this.mFavoritesAdapter.getSpan(position);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{bundle});
        super.onActivityCreated(bundle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("FavoritesFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "FavoritesFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "FavoritesFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    public void onDetach() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDetach", null);
        super.onDetach();
    }

    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        Ensighten.processView((Object) this, "onPause");
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
        super.onStart();
        Ensighten.processView((Object) this, "onStart");
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
    }

    public void onViewStateRestored(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onViewStateRestored", new Object[]{bundle});
        super.onViewStateRestored(bundle);
        Ensighten.processView((Object) this, "onViewStateRestored");
    }

    static /* synthetic */ boolean access$002(FavoritesFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoritesFragment", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.isRefreshed = x1;
        return x1;
    }

    static /* synthetic */ void access$100(FavoritesFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoritesFragment", "access$100", new Object[]{x0});
        x0.processFavoriteItems();
    }

    static /* synthetic */ void access$200(FavoritesFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.FavoritesFragment", "access$200", new Object[]{x0, new Boolean(x1)});
        x0.setRefreshing(x1);
    }

    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        if (!this.isRefreshed) {
            refreshData();
        }
        if (DataLayerManager.isEnabled(Configuration.getSharedInstance())) {
            DataLayerManager.getInstance().setPageSection("ProductsFilterFavoritesSubview");
            DataLayerManager.getInstance().logPageLoad("ProductsFilterFavoritesSubview", "PageViewed");
        }
        Ensighten.processView((Object) this, "onResume");
    }

    public void onDestroyView() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroyView", null);
        super.onDestroyView();
        getContext().getContentResolver().unregisterContentObserver(this.mContentObserver);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "FavoritesFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "FavoritesFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View v = layoutInflater.inflate(C2658R.layout.fragment_favorites, viewGroup, false);
        this.mProgressBar = (ProgressBar) v.findViewById(C2358R.C2357id.refresher);
        this.mSignedOutView = v.findViewById(C2358R.C2357id.signed_out_view);
        this.mSignInButton = v.findViewById(C2358R.C2357id.favorites_sign_in);
        this.mSignInButton.setOnClickListener(this);
        this.mRegisterButton = v.findViewById(C2358R.C2357id.favorites_register);
        this.mRegisterButton.setOnClickListener(this);
        this.mNoFavoritesView = v.findViewById(C2358R.C2357id.no_favorites_view);
        this.mFavoritesGrid = (RecyclerView) v.findViewById(C2358R.C2357id.favorites_grid);
        this.mFavoritesAdapter = new FavoritesAdapter(getChildFragmentManager(), getContext());
        this.mFavoritesAdapter.setOnMenuGridItemClickListener(this);
        this.mFavoritesGrid.setAdapter(this.mFavoritesAdapter);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setSpanSizeLookup(this.mSpanSizeLookup);
        this.mFavoritesGrid.setLayoutManager(manager);
        getContext().getContentResolver().registerContentObserver(Favorites.CONTENT_URI, true, this.mContentObserver);
        TraceMachine.exitMethod();
        return v;
    }

    public void onMenuGridItemClicked(View view, OrderProduct product) {
        Ensighten.evaluateEvent(this, "onMenuGridItemClicked", new Object[]{view, product});
        Bundle args = new Bundle();
        DataPasser.getInstance().putData("ARG_PRODUCT", product);
        args.putBoolean("arg_favorite_product", true);
        MenuActivity activity = (MenuActivity) getActivity();
        Intent intent = new Intent(activity, ProductDetailsActivity.class);
        intent.putExtras(args);
        activity.startActivity(intent);
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        int id = v.getId();
        if (id == C2358R.C2357id.favorites_sign_in) {
            startActivity(new Intent(getContext(), SignInActivity.class));
        } else if (id == C2358R.C2357id.favorites_register) {
            AnalyticsUtils.trackOnClickEvent("/order/favorites", "Start Registration");
            startActivity(new Intent(getContext(), SignUpActivity.class));
        }
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

    private void refreshData() {
        Ensighten.evaluateEvent(this, "refreshData", null);
        this.isRefreshed = true;
        setRefreshing(true);
        processFavoriteItems();
    }

    private void processFavoriteItems() {
        Ensighten.evaluateEvent(this, "processFavoriteItems", null);
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (customerModule.isLoggedIn()) {
            this.mFavoritesGrid.setVisibility(0);
            this.mSignedOutView.setVisibility(8);
            if (customerModule.needsFavoriteProductsRefresh()) {
                UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.label_loading_product);
                ServiceUtils.getSharedInstance().retrieveFavoriteProducts(new C35252());
                return;
            }
            List<FavoriteItem> favoriteItems = customerModule.getFavoriteProducts();
            List<FavoriteItem> favoriteProductItems = new ArrayList();
            List<FavoriteItem> favoriteOrderItems = new ArrayList();
            for (FavoriteItem favoriteItem : favoriteItems) {
                if (favoriteItem.getType() == FavoriteProductType.FAVORITE_PRODUCT_TYPE_ORDER) {
                    favoriteOrderItems.add(favoriteItem);
                } else {
                    favoriteProductItems.add(favoriteItem);
                }
            }
            Collections.sort(favoriteOrderItems, this.mFavoriteItemComparator);
            OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
            if (favoriteOrderItems.isEmpty() && favoriteProductItems.isEmpty()) {
                setRefreshing(false);
                this.mNoFavoritesView.setVisibility(0);
                this.mFavoritesGrid.setVisibility(8);
                return;
            }
            this.mNoFavoritesView.setVisibility(8);
            this.mFavoritesGrid.setVisibility(0);
            if (!(getActivity() == null || favoriteOrderItems.isEmpty())) {
                OrderUtils.ordersFromFavoriteItems(favoriteOrderItems, orderingModule, new C35263());
            }
            if (getActivity() != null && !favoriteProductItems.isEmpty()) {
                final AsyncCounter<OrderProduct> favoriteProductsCounter = new AsyncCounter(favoriteProductItems.size(), new C35274());
                for (final FavoriteItem favoriteItem2 : favoriteProductItems) {
                    ProductUtils.createOrderProduct((CustomerOrderProduct) favoriteItem2.getProducts().get(0), orderingModule, new AsyncListener<OrderProduct>() {
                        public void onResponse(OrderProduct response, AsyncToken token, AsyncException exception) {
                            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                            if (exception == null) {
                                if (response != null) {
                                    response.setFavoriteId(favoriteItem2.getFavoriteId());
                                    response.setFavoriteName(favoriteItem2.getName());
                                }
                                favoriteProductsCounter.success(response);
                                return;
                            }
                            favoriteProductsCounter.error(exception);
                            FavoritesFragment.access$002(FavoritesFragment.this, false);
                        }
                    });
                }
                return;
            } else if (getActivity() != null && ListUtils.isEmpty(favoriteOrderItems)) {
                this.mFavoritesAdapter.setProducts(null);
                return;
            } else {
                return;
            }
        }
        setRefreshing(false);
        this.mSignedOutView.setVisibility(0);
        this.mFavoritesGrid.setVisibility(8);
    }
}
