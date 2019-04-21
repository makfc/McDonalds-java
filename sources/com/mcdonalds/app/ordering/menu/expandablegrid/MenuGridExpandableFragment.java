package com.mcdonalds.app.ordering.menu.expandablegrid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.p001v7.widget.GridLayoutManager;
import android.support.p001v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.p001v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.nutrition.tasks.BuildProductMapTask;
import com.mcdonalds.app.nutrition.tasks.BuildProductMapTask.BuildProductMapListener;
import com.mcdonalds.app.nutrition.tasks.BuildProductMapTask.BuildProductMapParams;
import com.mcdonalds.app.ordering.menu.MenuActivity;
import com.mcdonalds.app.ordering.menu.MenuActivity.OnDayPartChangeListener;
import com.mcdonalds.app.ordering.menu.MenuActivity.OnPodChangeListener;
import com.mcdonalds.app.ordering.menu.MenuGridFragment;
import com.mcdonalds.app.ordering.menu.expandablegrid.ExpandableRecyclerAdapter.ExpandCollapseListener;
import com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableAdapter.OnMenuGridItemClickListener;
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
import com.mcdonalds.sdk.services.data.CatalogManager;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class MenuGridExpandableFragment extends MenuGridFragment implements OnDayPartChangeListener, OnPodChangeListener, ExpandCollapseListener, OnMenuGridItemClickListener {
    private List<Category> categoryList;
    private MenuActivity mActivity;
    BuildProductMapParams mBuildProductMapParams;
    private int mCurrentMenuTypeId;
    CustomerModule mCustomerModule;
    private GridLayoutManager mLayoutManager;
    RecyclerView mMenuGrid;
    private MenuGridExpandableAdapter mMenuGridAdapter;
    TextView mNoItemFound;
    OrderingModule mOrderingModule;
    private String mPod = Pod.PICKUP;
    private String mSearchQuery;
    SpanSizeLookup mSpanSizeLookup = new C35791();

    /* renamed from: com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableFragment$1 */
    class C35791 extends SpanSizeLookup {
        C35791() {
        }

        public int getSpanSize(int position) {
            Ensighten.evaluateEvent(this, "getSpanSize", new Object[]{new Integer(position)});
            return Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableFragment", "access$000", new Object[]{MenuGridExpandableFragment.this}).getSpan(position);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableFragment$2 */
    class C35802 implements AsyncListener<List<FavoriteItem>> {
        C35802() {
        }

        public void onResponse(List<FavoriteItem> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            MenuGridExpandableFragment.this.updateFavorites();
            UIUtils.stopActivityIndicator();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableFragment$3 */
    class C35813 implements BuildProductMapListener {
        C35813() {
        }

        public void onBuildProductMapComplete(Map<Category, List<Product>> productMap) {
            Ensighten.evaluateEvent(this, "onBuildProductMapComplete", new Object[]{productMap});
            MenuGridExpandableFragment.access$100(MenuGridExpandableFragment.this, false);
            MenuGridExpandableFragment.access$200(MenuGridExpandableFragment.this, productMap);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableFragment", "access$300", new Object[]{MenuGridExpandableFragment.this}).firstLoadproducts();
        }
    }

    private class UpdateOfferFlagsTask extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
        public Trace _nr_trace;

        /* renamed from: com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableFragment$UpdateOfferFlagsTask$1 */
        class C35821 implements AsyncListener<List<Offer>> {
            C35821() {
            }

            public void onResponse(List<Offer> response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                if (response != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableFragment", "access$000", new Object[]{MenuGridExpandableFragment.this}).refreshOffersFlags(response);
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

        /* synthetic */ UpdateOfferFlagsTask(MenuGridExpandableFragment x0, C35791 x1) {
            this();
        }

        /* Access modifiers changed, original: protected|varargs */
        public Void doInBackground(Void... params) {
            Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
            if (MenuGridExpandableFragment.this.mCustomerModule.isLoggedIn()) {
                String userName = MenuGridExpandableFragment.this.mCustomerModule.getCurrentProfile().getUserName();
                Store store = OrderManager.getInstance().getCurrentStore();
                if (!(TextUtils.isEmpty(userName) || store == null)) {
                    ServiceUtils.getSharedInstance().retrieveOffers(userName, String.valueOf(store.getStoreId()), null, null, new C35821());
                }
            }
            return null;
        }
    }

    static /* synthetic */ void access$100(MenuGridExpandableFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableFragment", "access$100", new Object[]{x0, new Boolean(x1)});
        x0.setIsDataRefreshing(x1);
    }

    static /* synthetic */ void access$200(MenuGridExpandableFragment x0, Map x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableFragment", "access$200", new Object[]{x0, x1});
        x0.onProductMapCompleted(x1);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mSearchQuery = getArguments().getString("SEARCH_QUERY");
        }
        setupBuildRecipeMapParams();
        this.mActivity = (MenuActivity) getActivity();
        setupModules();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C2658R.layout.fragment_menu_grid, container, false);
        this.mMenuGrid = (RecyclerView) v.findViewById(C2358R.C2357id.menu_grid);
        this.mNoItemFound = (TextView) v.findViewById(C2358R.C2357id.no_item);
        this.mLayoutManager = new GridLayoutManager(getContext(), 2);
        this.mLayoutManager.setSpanSizeLookup(this.mSpanSizeLookup);
        this.mMenuGrid.setLayoutManager(this.mLayoutManager);
        this.mMenuGridAdapter = new MenuGridExpandableAdapter(getActivity());
        this.mMenuGridAdapter.setOnMenuGridItemClickListener(this);
        this.mMenuGridAdapter.setExpandCollapseListener(this);
        this.mMenuGrid.setAdapter(this.mMenuGridAdapter);
        this.mActivity.setSearchFilter(new MenuGridExpandableAdapterFilter(this.mBuildProductMapParams, this.mMenuGridAdapter, this.mNoItemFound));
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mActivity.addOnDayPartChangeListener(this);
        this.mActivity.addOnPodChangeListener(this);
        this.mActivity.addCatalogListener(this);
    }

    public void onDayPartChange(MenuType newMenuType) {
        Ensighten.evaluateEvent(this, "onDayPartChange", new Object[]{newMenuType});
        if (newMenuType != null) {
            this.mCurrentMenuTypeId = newMenuType.getID();
            this.mBuildProductMapParams.currentMenuPartId = this.mCurrentMenuTypeId;
        }
        refreshData();
    }

    public void onStop() {
        super.onStop();
        if (this.mMenuGridAdapter != null) {
            this.mMenuGridAdapter.dispatchEvents();
        }
    }

    /* Access modifiers changed, original: protected */
    public void updateFavorites() {
        Ensighten.evaluateEvent(this, "updateFavorites", null);
        if (this.mCustomerModule != null && this.mMenuGridAdapter != null && this.mCustomerModule.isLoggedIn() && getActivity() != null && !getActivity().isFinishing()) {
            if (this.mCustomerModule.needsFavoriteProductsRefresh()) {
                UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.label_loading_product);
                ServiceUtils.getSharedInstance().retrieveFavoriteProducts(new C35802());
                return;
            }
            List<FavoriteItem> favoriteItems = new ArrayList();
            for (FavoriteItem favoriteItem : this.mCustomerModule.getFavoriteProducts()) {
                if (favoriteItem.getType() == FavoriteProductType.FAVORITE_PRODUCT_TYPE_ITEM) {
                    favoriteItems.add(favoriteItem);
                }
            }
            this.mMenuGridAdapter.updateFavorites(favoriteItems);
        }
    }

    /* Access modifiers changed, original: protected */
    public void refreshData() {
        Ensighten.evaluateEvent(this, "refreshData", null);
        if (CatalogManager.getSyncStatus() == 1 && !isDataRefreshing()) {
            setIsDataRefreshing(true);
            BuildProductMapTask buildProductMapTask = new BuildProductMapTask();
            buildProductMapTask.setListener(new C35813());
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

    /* Access modifiers changed, original: protected */
    public void setupModules() {
        Ensighten.evaluateEvent(this, "setupModules", null);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
    }

    private void onProductMapCompleted(Map<Category, List<Product>> productMap) {
        Ensighten.evaluateEvent(this, "onProductMapCompleted", new Object[]{productMap});
        updateMenuGridAdapterData(productMap, null);
        updateFavorites();
        this.categoryList = new ArrayList(productMap.keySet());
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
        this.mMenuGridAdapter.setFilterFlag(false);
        this.mMenuGridAdapter.setProductMap(productMap);
        UpdateOfferFlagsTask task = new UpdateOfferFlagsTask(this, null);
        Void[] voidArr = new Void[0];
        if (task instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(task, voidArr);
        } else {
            task.execute(voidArr);
        }
    }

    public void onListItemExpanded(int position) {
        Ensighten.evaluateEvent(this, "onListItemExpanded", new Object[]{new Integer(position)});
        this.mActivity.collapseHeader(true);
        this.mLayoutManager.scrollToPositionWithOffset(position, 0);
        if (this.categoryList != null && position < this.categoryList.size()) {
            DataLayerManager.getInstance().setCurrentPageSection(((Category) this.categoryList.get(position)).getName());
        }
    }

    public void onListItemCollapsed(int position) {
        Ensighten.evaluateEvent(this, "onListItemCollapsed", new Object[]{new Integer(position)});
    }

    public void onPodChange(String newPod) {
        Ensighten.evaluateEvent(this, "onPodChange", new Object[]{newPod});
        this.mPod = newPod;
        this.mBuildProductMapParams.pod = newPod;
        refreshData();
    }
}
