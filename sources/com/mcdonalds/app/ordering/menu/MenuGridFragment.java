package com.mcdonalds.app.ordering.menu;

import android.app.Activity;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.app.Fragment;
import android.support.p001v7.widget.GridLayoutManager;
import android.support.p001v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.p001v7.widget.RecyclerView;
import android.support.p001v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.nutrition.tasks.BuildProductMapTask;
import com.mcdonalds.app.nutrition.tasks.BuildProductMapTask.BuildProductMapListener;
import com.mcdonalds.app.nutrition.tasks.BuildProductMapTask.BuildProductMapParams;
import com.mcdonalds.app.ordering.menu.MenuActivity.CatalogListener;
import com.mcdonalds.app.ordering.menu.MenuActivity.OnDayPartChangeListener;
import com.mcdonalds.app.ordering.menu.MenuActivity.OnPodChangeListener;
import com.mcdonalds.app.ordering.menu.MenuGridAdapter.OnMenuGridItemClickListener;
import com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.FavoriteItem.FavoriteProductType;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.CatalogManager;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.services.data.provider.Contract.Favorites;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

@Instrumented
public class MenuGridFragment extends Fragment implements CatalogListener, OnDayPartChangeListener, OnPodChangeListener, OnMenuGridItemClickListener, TraceFieldInterface {
    public Trace _nr_trace;
    private boolean isProductMapEmpty = true;
    private MenuActivity mActivity;
    BuildProductMapParams mBuildProductMapParams;
    private String mCategoryId;
    private int mCurrentMenuTypeId;
    CustomerModule mCustomerModule;
    ContentObserver mFavoritesContentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange)});
            super.onChange(selfChange);
            MenuGridFragment.this.updateFavorites();
        }
    };
    private boolean mIsDataRefreshing = false;
    private GridLayoutManager mLayoutManager;
    RecyclerView mMenuGrid;
    private MenuGridAdapter mMenuGridAdapter;
    TextView mNoItemFound;
    OrderingModule mOrderingModule;
    private List<String> mOutageProductCodes;
    private String mPod = Pod.PICKUP;
    OnScrollListener mScrollListener = new C35542();
    private String mSearchQuery;
    SpanSizeLookup mSpanSizeLookup = new C35531();

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuGridFragment$1 */
    class C35531 extends SpanSizeLookup {
        C35531() {
        }

        public int getSpanSize(int position) {
            Ensighten.evaluateEvent(this, "getSpanSize", new Object[]{new Integer(position)});
            return Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridFragment", "access$000", new Object[]{MenuGridFragment.this}).getSpan(position);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuGridFragment$2 */
    class C35542 extends OnScrollListener {
        C35542() {
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            Ensighten.evaluateEvent(this, "onScrolled", new Object[]{recyclerView, new Integer(dx), new Integer(dy)});
            super.onScrolled(recyclerView, dx, dy);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridFragment", "access$100", new Object[]{MenuGridFragment.this}).getSupportActionBar() != null) {
                int position = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridFragment", "access$200", new Object[]{MenuGridFragment.this}).findFirstVisibleItemPosition();
                if (position == 0) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridFragment", "access$100", new Object[]{MenuGridFragment.this}).setTitle(MenuGridFragment.this.getTitle());
                }
                int last = position - 2;
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridFragment", "access$000", new Object[]{MenuGridFragment.this}).isHeader(position - 1) || Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridFragment", "access$000", new Object[]{MenuGridFragment.this}).isHeader(last)) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridFragment", "access$100", new Object[]{MenuGridFragment.this}).setTitle(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridFragment", "access$000", new Object[]{MenuGridFragment.this}).getCategoryForPosition(position).getName());
                }
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuGridFragment$4 */
    class C35564 implements BuildProductMapListener {
        C35564() {
        }

        public void onBuildProductMapComplete(Map<Category, List<Product>> productMap) {
            Ensighten.evaluateEvent(this, "onBuildProductMapComplete", new Object[]{productMap});
            MenuGridFragment.this.setIsDataRefreshing(false);
            MenuGridFragment.access$300(MenuGridFragment.this, productMap);
        }
    }

    private class UpdateOfferFlagsTask extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
        public Trace _nr_trace;

        /* renamed from: com.mcdonalds.app.ordering.menu.MenuGridFragment$UpdateOfferFlagsTask$1 */
        class C35581 implements AsyncListener<List<Offer>> {
            C35581() {
            }

            public void onResponse(List<Offer> response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (response != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridFragment", "access$000", new Object[]{MenuGridFragment.this}).refreshOffersFlags(response);
                }
            }
        }

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        private UpdateOfferFlagsTask() {
        }

        /* synthetic */ UpdateOfferFlagsTask(MenuGridFragment x0, C35531 x1) {
            this();
        }

        /* Access modifiers changed, original: protected|varargs */
        public Void doInBackground(Void... params) {
            Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
            if (MenuGridFragment.this.mCustomerModule.isLoggedIn()) {
                String userName = MenuGridFragment.this.mCustomerModule.getCurrentProfile().getUserName();
                Store store = OrderManager.getInstance().getCurrentStore();
                if (!(TextUtils.isEmpty(userName) || store == null)) {
                    ServiceUtils.getSharedInstance().retrieveOffers(userName, String.valueOf(store.getStoreId()), null, null, new C35581());
                }
            }
            return null;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
    }

    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    public void onDestroyView() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroyView", null);
        super.onDestroyView();
    }

    public void onDetach() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDetach", null);
        super.onDetach();
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
        super.onStart();
        Ensighten.processView((Object) this, "onStart");
    }

    public void onViewStateRestored(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onViewStateRestored", new Object[]{bundle});
        super.onViewStateRestored(bundle);
        Ensighten.processView((Object) this, "onViewStateRestored");
    }

    static /* synthetic */ void access$300(MenuGridFragment x0, Map x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridFragment", "access$300", new Object[]{x0, x1});
        x0.onProductMapCompleted(x1);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("MenuGridFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "MenuGridFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "MenuGridFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mSearchQuery = getArguments().getString("SEARCH_QUERY");
            this.mCategoryId = getArguments().getString("arg_category_id");
        }
        setupBuildRecipeMapParams();
        this.mActivity = (MenuActivity) getActivity();
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "MenuGridFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "MenuGridFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View v = layoutInflater.inflate(C2658R.layout.fragment_menu_grid, viewGroup, false);
        this.mMenuGrid = (RecyclerView) v.findViewById(C2358R.C2357id.menu_grid);
        this.mNoItemFound = (TextView) v.findViewById(C2358R.C2357id.no_item);
        this.mLayoutManager = new GridLayoutManager(getContext(), 2);
        this.mLayoutManager.setSpanSizeLookup(this.mSpanSizeLookup);
        this.mMenuGrid.setLayoutManager(this.mLayoutManager);
        this.mMenuGridAdapter = new MenuGridAdapter(getContext());
        this.mMenuGridAdapter.setOnMenuGridItemClickListener(this);
        this.mMenuGrid.setAdapter(this.mMenuGridAdapter);
        this.mMenuGrid.addOnScrollListener(this.mScrollListener);
        this.mActivity.setSearchFilter(new MenuGridAdapterFilter(this.mBuildProductMapParams, this.mMenuGridAdapter, this.mNoItemFound));
        setupModules();
        TraceMachine.exitMethod();
        return v;
    }

    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        if (this.mOrderingModule == null || this.mCustomerModule == null) {
            this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
            this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        }
        updateFavorites();
        getContext().getContentResolver().registerContentObserver(Favorites.CONTENT_URI, true, this.mFavoritesContentObserver);
        Ensighten.processView((Object) this, "onResume");
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{savedInstanceState});
        super.onActivityCreated(savedInstanceState);
        this.mActivity.addOnDayPartChangeListener(this);
        this.mActivity.addOnPodChangeListener(this);
        this.mActivity.addCatalogListener(this);
    }

    public void onDayPartChange(MenuType newMenuType) {
        Ensighten.evaluateEvent(this, "onDayPartChange", new Object[]{newMenuType});
        this.mCurrentMenuTypeId = newMenuType.getID();
        this.mBuildProductMapParams.currentMenuPartId = this.mCurrentMenuTypeId;
        refreshData();
        refreshFilter();
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
        getContext().getContentResolver().unregisterContentObserver(this.mFavoritesContentObserver);
    }

    /* Access modifiers changed, original: protected */
    public void updateFavorites() {
        Ensighten.evaluateEvent(this, "updateFavorites", null);
        if (this.mCustomerModule != null && this.mMenuGridAdapter != null && this.mCustomerModule.isLoggedIn()) {
            final List<FavoriteItem> favoriteItems = new ArrayList();
            if (this.mCustomerModule.needsFavoriteProductsRefresh()) {
                UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.label_loading_product);
                ServiceUtils.getSharedInstance().retrieveFavoriteProducts(new AsyncListener<List<FavoriteItem>>() {
                    public void onResponse(List<FavoriteItem> response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        UIUtils.stopActivityIndicator();
                        for (FavoriteItem favoriteItem : MenuGridFragment.this.mCustomerModule.getFavoriteProducts()) {
                            if (favoriteItem.getType() == FavoriteProductType.FAVORITE_PRODUCT_TYPE_ITEM) {
                                favoriteItems.add(favoriteItem);
                            }
                        }
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuGridFragment", "access$000", new Object[]{MenuGridFragment.this}).updateFavorites(favoriteItems);
                    }
                });
                return;
            }
            for (FavoriteItem favoriteItem : this.mCustomerModule.getFavoriteProducts()) {
                if (favoriteItem.getType() == FavoriteProductType.FAVORITE_PRODUCT_TYPE_ITEM) {
                    favoriteItems.add(favoriteItem);
                }
            }
            this.mMenuGridAdapter.updateFavorites(favoriteItems);
        }
    }

    private void updateOutageProducts() {
        Ensighten.evaluateEvent(this, "updateOutageProducts", null);
        if (this.mCustomerModule != null && this.mMenuGridAdapter != null && this.mCustomerModule.isLoggedIn()) {
            Store store = OrderManager.getInstance().getCurrentStore();
            if (store != null) {
                this.mOutageProductCodes = store.getOutageProducts();
                if (this.mOutageProductCodes != null && !this.mOutageProductCodes.isEmpty()) {
                    this.mMenuGridAdapter.setOutageProduct(this.mOutageProductCodes);
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void setIsDataRefreshing(boolean isRefreshing) {
        Ensighten.evaluateEvent(this, "setIsDataRefreshing", new Object[]{new Boolean(isRefreshing)});
        this.mIsDataRefreshing = isRefreshing;
    }

    /* Access modifiers changed, original: protected */
    public boolean isDataRefreshing() {
        Ensighten.evaluateEvent(this, "isDataRefreshing", null);
        return this.mIsDataRefreshing;
    }

    /* Access modifiers changed, original: protected */
    public void refreshData() {
        Ensighten.evaluateEvent(this, "refreshData", null);
        if (CatalogManager.getSyncStatus() == 1 && !isDataRefreshing()) {
            setIsDataRefreshing(true);
            BuildProductMapTask buildProductMapTask = new BuildProductMapTask();
            buildProductMapTask.setListener(new C35564());
            setupBuildRecipeMapParams();
            Executor executor = AsyncTask.THREAD_POOL_EXECUTOR;
            BuildProductMapParams[] buildProductMapParamsArr = new BuildProductMapParams[]{this.mBuildProductMapParams};
            if (buildProductMapTask instanceof AsyncTask) {
                AsyncTaskInstrumentation.executeOnExecutor(buildProductMapTask, executor, buildProductMapParamsArr);
            } else {
                buildProductMapTask.executeOnExecutor(executor, buildProductMapParamsArr);
            }
        }
    }

    private void setupBuildRecipeMapParams() {
        Ensighten.evaluateEvent(this, "setupBuildRecipeMapParams", null);
        this.mBuildProductMapParams = new BuildProductMapParams();
        this.mBuildProductMapParams.context = getContext();
        this.mBuildProductMapParams.pod = this.mPod;
        this.mBuildProductMapParams.searchQuery = this.mSearchQuery;
        this.mBuildProductMapParams.enableMultipleMenuTypes = AppParameters.getBooleanForParameter(AppParameters.ENABLE_MULTIPLE_MENU_TYPES);
        this.mBuildProductMapParams.currentMenuPartId = this.mCurrentMenuTypeId;
    }

    public void onCatalogReady() {
        Ensighten.evaluateEvent(this, "onCatalogReady", null);
        refreshData();
    }

    /* Access modifiers changed, original: protected */
    public void setupModules() {
        Ensighten.evaluateEvent(this, "setupModules", null);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        finishSetup();
    }

    private void finishSetup() {
        Ensighten.evaluateEvent(this, "finishSetup", null);
        if (this.mOrderingModule == null || this.mCustomerModule == null) {
        }
    }

    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        getContext().getContentResolver().unregisterContentObserver(this.mFavoritesContentObserver);
        super.onPause();
        Ensighten.processView((Object) this, "onPause");
    }

    public void onMenuGridItemClicked(View view, Product product, String categoryName, int categoryId) {
        Ensighten.evaluateEvent(this, "onMenuGridItemClicked", new Object[]{view, product, categoryName, new Integer(categoryId)});
        Bundle args = new Bundle();
        args.putInt("ARG_RECIPE_ID", product.getExternalId().intValue());
        args.putInt("ARG_CAT_ID", categoryId);
        DataPasser.getInstance().putData("ARG_PRODUCT_PASSED", product);
        args.putInt("ARG_ANALYTICS_DAYPART_INDEX", this.mCurrentMenuTypeId);
        args.putString("ARG_ANALYTICS_CATEGORY_NAME", categoryName);
        DataLayerManager.getInstance().reportCurrentPageSection();
        Intent intent = new Intent(this.mActivity, ProductDetailsActivity.class);
        intent.putExtras(args);
        if (VERSION.SDK_INT < 16) {
            this.mActivity.startActivity(intent);
        } else if (Configuration.getSharedInstance().getBooleanForKey("interface.ordering.disableTransitionAnimation")) {
            this.mActivity.startActivity(intent);
        } else {
            this.mActivity.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this.mActivity, view, "image").toBundle());
        }
    }

    private void refreshFilter() {
        Ensighten.evaluateEvent(this, "refreshFilter", null);
        MenuGridAdapterFilter filter = new MenuGridAdapterFilter(this.mBuildProductMapParams, this.mMenuGridAdapter, this.mNoItemFound);
        this.mActivity.removeSearchFilter();
        this.mActivity.setSearchFilter(filter);
    }

    private void onProductMapCompleted(Map<Category, List<Product>> productMap) {
        Ensighten.evaluateEvent(this, "onProductMapCompleted", new Object[]{productMap});
        updateOutageProducts();
        updateMenuGridAdapterData(productMap, null);
        updateFavorites();
    }

    public boolean isProductMapEmpty() {
        Ensighten.evaluateEvent(this, "isProductMapEmpty", null);
        return this.isProductMapEmpty;
    }

    private void updateMenuGridAdapterData(Map<Category, List<Product>> productMap, List<FavoriteItem> favoriteItemList) {
        Ensighten.evaluateEvent(this, "updateMenuGridAdapterData", new Object[]{productMap, favoriteItemList});
        if (productMap == null || productMap.size() == 0) {
            setIsProductMapEmpty(true);
            return;
        }
        UIUtils.stopActivityIndicator();
        this.mActivity.setCatalogSync(false);
        setIsProductMapEmpty(false);
        this.mMenuGridAdapter.setProductMap(productMap);
        UpdateOfferFlagsTask task = new UpdateOfferFlagsTask(this, null);
        Void[] voidArr = new Void[0];
        if (task instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(task, voidArr);
        } else {
            task.execute(voidArr);
        }
        goToReceivedCategory();
    }

    private void goToReceivedCategory() {
        Ensighten.evaluateEvent(this, "goToReceivedCategory", null);
        if (this.mCategoryId != null) {
            int position = this.mMenuGridAdapter.getPositionForCategoryId(Integer.parseInt(this.mCategoryId));
            if (position > 0) {
                this.mMenuGrid.scrollToPosition(position);
            }
        }
        this.mCategoryId = null;
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getResources().getString(C2658R.string.title_activity_order_products);
    }

    public void onPodChange(String newPod) {
        Ensighten.evaluateEvent(this, "onPodChange", new Object[]{newPod});
        this.mPod = newPod;
        this.mBuildProductMapParams.pod = newPod;
        refreshData();
        refreshFilter();
    }

    /* Access modifiers changed, original: protected */
    public void setIsProductMapEmpty(boolean isEmpty) {
        Ensighten.evaluateEvent(this, "setIsProductMapEmpty", new Object[]{new Boolean(isEmpty)});
        this.isProductMapEmpty = isEmpty;
    }
}
