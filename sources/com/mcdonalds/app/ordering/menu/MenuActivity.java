package com.mcdonalds.app.ordering.menu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.support.p001v7.app.AlertDialog;
import android.support.p001v7.app.AlertDialog.Builder;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.google.gson.reflect.TypeToken;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.menu.expandablegrid.MenuGridExpandableFragment;
import com.mcdonalds.app.ordering.methodselection.OrderMethodSelectionActivity;
import com.mcdonalds.app.p043ui.NonSwipeableViewPager;
import com.mcdonalds.app.p043ui.URLBasketNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.TextChangeFilter;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.PagerIndicator;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.CatalogManager;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.data.provider.Contract;
import com.mcdonalds.sdk.services.data.provider.Contract.CurrentStore;
import com.mcdonalds.sdk.services.network.RequestManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MenuActivity extends URLBasketNavigationActivity implements OnPageChangeListener {
    public static final List<String> ENDPOINTS = Arrays.asList(new String[]{"start_order", "start_order_delivery", "recents_grid", "favorites_grid", "menu_grid"});
    private boolean isCatalogSync = false;
    private Tab mAllTab;
    private List<CatalogListener> mCatalogListeners = new ArrayList();
    ContentObserver mCatalogObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange)});
            Log.i("catUpdate", "onCatalogChange");
            if (CatalogManager.getSyncStatus() != 0) {
                Log.i("catUpdate", "checkCatalogStatus");
                MenuActivity.access$100(MenuActivity.this, true);
            }
        }
    };
    AlertDialog mCatalogSyncErrorAlert;
    CustomerModule mCustomerModule;
    private ViewPager mDayPartPager;
    private DayPartPagerAdapter mDayPartPagerAdapter;
    private Tab mFavoritesTab;
    private boolean mFirstLoadProducts;
    private OnEditorActionListener mFoodSearchEditorListener = new C35421();
    private String mLastKnownPOD;
    MenuGridFragment mMenuGridFragment;
    private List<OnDayPartChangeListener> mOnDayPartChangeListeners = new ArrayList();
    private List<OnPodChangeListener> mOnPodChangeListeners = new ArrayList();
    private Tab mRecentsTab;
    private EditText mSearchBar;
    private View mSearchButton;
    private View mSearchContainer;
    private boolean mSkipFirstLoadAddressWorkflow;
    private Store mStore;
    ContentObserver mStoreObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange)});
            super.onChange(selfChange);
            MenuActivity.access$700(MenuActivity.this);
        }
    };
    private TextView mStoreTitle;
    private TabLayout mTabs;
    private TextWatcher mWatcher;

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$10 */
    class C353210 implements Runnable {
        C353210() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$300", new Object[]{MenuActivity.this}).select();
            if (OrderManager.getInstance().getCurrentStore() != null) {
                MenuActivity.access$100(MenuActivity.this, true);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$11 */
    class C353311 implements Runnable {
        C353311() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$300", new Object[]{MenuActivity.this}).select();
            if (OrderManager.getInstance().getCurrentStore() != null) {
                MenuActivity.this.showOrderMethodSelector();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$12 */
    class C353412 implements Runnable {
        C353412() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$300", new Object[]{MenuActivity.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$300", new Object[]{MenuActivity.this}).select();
            }
            if (OrderManager.getInstance().getCurrentStore() != null) {
                MenuActivity.this.showOrderMethodSelector();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$13 */
    class C353513 implements Runnable {
        C353513() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$300", new Object[]{MenuActivity.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$300", new Object[]{MenuActivity.this}).select();
            }
            if (OrderManager.getInstance().getCurrentStore() != null) {
                MenuActivity.this.showOrderMethodSelector();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$14 */
    class C353614 implements OnTouchListener {
        C353614() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            Ensighten.evaluateEvent(this, "onTouch", new Object[]{view, motionEvent});
            return true;
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$16 */
    class C353816 implements OnClickListener {
        C353816() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            MenuActivity.access$500(MenuActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$17 */
    class C353917 implements OnClickListener {
        C353917() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            MenuActivity.access$600(MenuActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$19 */
    class C354119 implements Runnable {
        C354119() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            if (MenuActivity.this.isCatalogSync()) {
                Log.i("catUpdate", "catalog is syncing");
                MenuActivity.this.catalogSyncWithTimeout();
            } else if (MenuActivity.this.mMenuGridFragment != null && MenuActivity.this.mMenuGridFragment.isProductMapEmpty()) {
                Log.i("catUpdate", "requestSyncReload");
                MenuActivity.access$200(MenuActivity.this);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$1 */
    class C35421 implements OnEditorActionListener {
        C35421() {
        }

        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{textView, new Integer(actionId), keyEvent});
            String searchString = textView.getText().toString();
            if (actionId != 3) {
                return false;
            }
            MenuActivity.access$000(MenuActivity.this, searchString);
            return true;
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$20 */
    class C354320 implements Runnable {
        C354320() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            if (MenuActivity.this.isCatalogSync()) {
                Log.i("catUpdate", "catalogSyncTimeout");
                UIUtils.stopActivityIndicator();
                MenuActivity.this.setCatalogSync(false);
                MenuActivity.this.showCatalogSyncErrorAlert();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$2 */
    class C35442 implements Runnable {
        C35442() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            MenuActivity.this.showOrderMethodSelector();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$3 */
    class C35453 implements OnClickListener {
        C35453() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            MenuActivity.this.showOrderMethodSelector();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$4 */
    class C35464 extends TypeToken<String> {
        C35464() {
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$6 */
    class C35486 implements DialogInterface.OnClickListener {
        C35486() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            MenuActivity.access$200(MenuActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$7 */
    class C35497 implements DialogInterface.OnClickListener {
        C35497() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            MenuActivity.this.finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$8 */
    class C35508 implements Runnable {
        C35508() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            CatalogManager.reloadCatalog(MenuActivity.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.menu.MenuActivity$9 */
    class C35519 implements Runnable {
        C35519() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            if (OrderManager.getInstance().getCurrentStore() != null) {
                MenuActivity.this.showFavoriteMethodSelector();
            }
        }
    }

    public interface CatalogListener {
        void onCatalogReady();
    }

    public interface OnDayPartChangeListener {
        void onDayPartChange(MenuType menuType);
    }

    public interface OnPodChangeListener {
        void onPodChange(String str);
    }

    static /* synthetic */ void access$000(MenuActivity x0, String x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$000", new Object[]{x0, x1});
        x0.trackFoodPageSearch(x1);
    }

    static /* synthetic */ void access$100(MenuActivity x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$100", new Object[]{x0, new Boolean(x1)});
        x0.checkCatalogStatus(x1);
    }

    static /* synthetic */ void access$200(MenuActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$200", new Object[]{x0});
        x0.requestSyncReload();
    }

    static /* synthetic */ void access$500(MenuActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$500", new Object[]{x0});
        x0.showSearchBar();
    }

    static /* synthetic */ void access$600(MenuActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$600", new Object[]{x0});
        x0.hideSearchBar();
    }

    static /* synthetic */ void access$700(MenuActivity x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$700", new Object[]{x0});
        x0.setStore();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mSkipFirstLoadAddressWorkflow = false;
        if (Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection")) {
            if (!this.mCustomerModule.isLoggedIn()) {
                startActivity(SignInActivity.class);
                finish();
                return;
            } else if (OrderManager.getInstance().getCurrentStore() == null) {
                this.mSkipFirstLoadAddressWorkflow = true;
                new Handler().postDelayed(new C35442(), 500);
            }
        }
        setTitle(C2658R.string.title_activity_menu);
        this.mDayPartPager = (ViewPager) findViewById(C2358R.C2357id.day_part_pager);
        View methodSelectorButton = findViewById(C2358R.C2357id.delivery_method_selector_button);
        methodSelectorButton.setOnClickListener(new C35453());
        DataLayerClickListener.setDataLayerTag(methodSelectorButton, "PickUpAtRestaurantItemPressed");
        this.mStoreTitle = (TextView) findViewById(2131820647);
        if (!this.mSkipFirstLoadAddressWorkflow) {
            setupDaypart();
            setupTabs();
            setStore();
            goToReceivedFragment(getIntent());
        }
        setupSearch();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        String languageResponse = (String) LocalDataManager.getSharedInstance().getObjectFromCache("CATALOG_CURRENT_LANGUAGE", new C35464().getType());
        String currentLanguage = Configuration.getSharedInstance().getCurrentLanguage();
        if (!currentLanguage.equals(languageResponse)) {
            LocalDataManager.getSharedInstance().addObjectToCache("CATALOG_CURRENT_LANGUAGE", currentLanguage, 0);
            CatalogManager.clearMarketCache(getApplicationContext());
            CatalogManager.clearStoreCache(getApplicationContext());
            LocalDataManager.getSharedInstance().deleteObjectFromCache(CatalogManager.CACHE_MARKET_CATALOG);
            checkCatalogStatus(true);
        }
        getContentResolver().registerContentObserver(Contract.CONTENT_URI, false, this.mCatalogObserver);
        getContentResolver().registerContentObserver(CurrentStore.CONTENT_URI, true, this.mStoreObserver);
        if (this.mStore == null || OrderManager.getInstance().getCurrentStore() == null || OrderManager.getInstance().getCurrentStore() == this.mStore) {
            checkCatalogStatus(false);
        } else {
            setStore();
            checkCatalogStatus(true);
        }
        if (this.mDayPartPagerAdapter != null) {
            this.mDayPartPagerAdapter.registerObserver();
        }
        if (!(this.mSearchBar == null || this.mSearchBar.getText() == null || this.mSearchBar.getText().length() == 0)) {
            this.mSearchBar.setText(this.mSearchBar.getText());
        }
        String currPOD = getCurrentPod();
        if (this.mLastKnownPOD != null && !this.mLastKnownPOD.equals(currPOD)) {
            for (OnPodChangeListener listener : this.mOnPodChangeListeners) {
                listener.onPodChange(currPOD);
            }
            setStore();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        this.mLastKnownPOD = getCurrentPod();
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(this.mCatalogObserver);
        getContentResolver().unregisterContentObserver(this.mStoreObserver);
        if (this.mDayPartPagerAdapter != null) {
            this.mDayPartPagerAdapter.deregisterObserver();
        }
    }

    public void setCatalogSync(boolean catalogSync) {
        Ensighten.evaluateEvent(this, "setCatalogSync", new Object[]{new Boolean(catalogSync)});
        Log.i("cataUpdate", "setCatalogSync" + catalogSync);
        this.isCatalogSync = catalogSync;
    }

    public boolean isCatalogSync() {
        Ensighten.evaluateEvent(this, "isCatalogSync", null);
        return this.isCatalogSync;
    }

    private void checkCatalogStatus(boolean shouldNotifyCatalog) {
        Ensighten.evaluateEvent(this, "checkCatalogStatus", new Object[]{new Boolean(shouldNotifyCatalog)});
        if (CatalogManager.hasCatalogDownloaded(this) && CatalogManager.hasMarketCatalogDownloaded(this)) {
            UIUtils.stopActivityIndicator("updating_catalog");
            if (this.mDayPartPagerAdapter != null) {
                this.mDayPartPagerAdapter.refresh();
            }
            if (this.mCatalogSyncErrorAlert != null) {
                this.mCatalogSyncErrorAlert.dismiss();
                setCatalogSync(false);
            }
            if (shouldNotifyCatalog) {
                notifyCatalogReady();
                return;
            }
            return;
        }
        int syncStatus = CatalogManager.getSyncStatus();
        if (syncStatus == 0) {
            UIUtils.startActivityIndicator(this, "updating_catalog", C2658R.string.progress_update_products_msg);
            setCatalogSync(true);
        } else if (syncStatus == 2) {
            showCatalogSyncErrorAlert();
        } else {
            Log.i("catUpdate", "requestSync");
            if (OrderManager.getInstance().getCurrentStore() != null) {
                requestSync();
            }
        }
    }

    private void notifyCatalogReady() {
        Ensighten.evaluateEvent(this, "notifyCatalogReady", null);
        for (CatalogListener catalogListener : this.mCatalogListeners) {
            catalogListener.onCatalogReady();
        }
    }

    public void showCatalogSyncErrorAlert() {
        Ensighten.evaluateEvent(this, "showCatalogSyncErrorAlert", null);
        if (!isFinishing()) {
            if (this.mCatalogSyncErrorAlert == null) {
                this.mCatalogSyncErrorAlert = new Builder(this, C2658R.style.f8039AppTheme.Dialog).setTitle((int) C2658R.string.catalog_update_error_title).setMessage((int) C2658R.string.catalog_update_error).setCancelable(true).setNegativeButton((int) C2658R.string.cancel, new C35497()).setPositiveButton((int) C2658R.string.retry, new C35486()).create();
            }
            this.mCatalogSyncErrorAlert.show();
        }
    }

    private void requestSync() {
        Ensighten.evaluateEvent(this, "requestSync", null);
        UIUtils.startActivityIndicator(this, "updating_catalog", C2658R.string.progress_update_products_msg);
        setCatalogSync(true);
        CatalogManager.reloadCatalog(this);
    }

    private void requestSyncReload() {
        Ensighten.evaluateEvent(this, "requestSyncReload", null);
        UIUtils.startActivityIndicator((Context) this, (int) C2658R.string.progress_update_products_msg);
        setCatalogSync(true);
        new Thread(new C35508()).start();
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        super.onNewIntent(intent);
        goToReceivedFragment(intent);
    }

    public void showFragment(String fragmentName) {
        Ensighten.evaluateEvent(this, "showFragment", new Object[]{fragmentName});
        showFragment(fragmentName, null);
    }

    public void showFragment(String fragmentName, Bundle extras) {
        Ensighten.evaluateEvent(this, "showFragment", new Object[]{fragmentName, extras});
        goToFragment(fragmentName);
    }

    private void goToReceivedFragment(Intent intent) {
        Ensighten.evaluateEvent(this, "goToReceivedFragment", new Object[]{intent});
        Bundle extras = intent.getExtras();
        if (extras != null) {
            goToFragment(extras.getString(URLNavigationActivity.ARG_FRAGMENT, ""));
        }
    }

    /* JADX WARNING: Missing block: B:5:0x002c, code skipped:
            if (r10.equals("recents_grid") != false) goto L_0x0022;
     */
    private void goToFragment(java.lang.String r10) {
        /*
        r9 = this;
        r4 = 1;
        r2 = 0;
        r8 = 2131298441; // 0x7f090889 float:1.8214855E38 double:1.0530013407E-314;
        r6 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r3 = "goToFragment";
        r5 = new java.lang.Object[r4];
        r5[r2] = r10;
        com.ensighten.Ensighten.evaluateEvent(r9, r3, r5);
        r3 = com.mcdonalds.app.ordering.OrderingManager.getInstance();
        r1 = r3.getCurrentOrder();
        r3 = -1;
        r5 = r10.hashCode();
        switch(r5) {
            case -2022086227: goto L_0x0026;
            case -1527227087: goto L_0x0043;
            case -1527139802: goto L_0x0039;
            case -866404306: goto L_0x002f;
            case 413742914: goto L_0x004d;
            default: goto L_0x0021;
        };
    L_0x0021:
        r2 = r3;
    L_0x0022:
        switch(r2) {
            case 0: goto L_0x0057;
            case 1: goto L_0x0067;
            case 2: goto L_0x008a;
            case 3: goto L_0x009f;
            case 4: goto L_0x00f9;
            default: goto L_0x0025;
        };
    L_0x0025:
        return;
    L_0x0026:
        r4 = "recents_grid";
        r4 = r10.equals(r4);
        if (r4 == 0) goto L_0x0021;
    L_0x002e:
        goto L_0x0022;
    L_0x002f:
        r2 = "favorites_grid";
        r2 = r10.equals(r2);
        if (r2 == 0) goto L_0x0021;
    L_0x0037:
        r2 = r4;
        goto L_0x0022;
    L_0x0039:
        r2 = "menu_grid";
        r2 = r10.equals(r2);
        if (r2 == 0) goto L_0x0021;
    L_0x0041:
        r2 = 2;
        goto L_0x0022;
    L_0x0043:
        r2 = "start_order";
        r2 = r10.equals(r2);
        if (r2 == 0) goto L_0x0021;
    L_0x004b:
        r2 = 3;
        goto L_0x0022;
    L_0x004d:
        r2 = "start_order_delivery";
        r2 = r10.equals(r2);
        if (r2 == 0) goto L_0x0021;
    L_0x0055:
        r2 = 4;
        goto L_0x0022;
    L_0x0057:
        r2 = 2131298460; // 0x7f09089c float:1.8214894E38 double:1.05300135E-314;
        r2 = r9.getString(r2);
        com.mcdonalds.app.util.AnalyticsUtils.trackScreenLoad(r2);
        r2 = r9.mRecentsTab;
        r2.select();
        goto L_0x0025;
    L_0x0067:
        r2 = 2131298424; // 0x7f090878 float:1.821482E38 double:1.0530013323E-314;
        r2 = r9.getString(r2);
        com.mcdonalds.app.util.AnalyticsUtils.trackScreenLoad(r2);
        r2 = r9.mFavoritesTab;
        r2.select();
        r2 = r1.isDelivery();
        if (r2 == 0) goto L_0x0025;
    L_0x007c:
        r2 = new android.os.Handler;
        r2.<init>();
        r3 = new com.mcdonalds.app.ordering.menu.MenuActivity$9;
        r3.<init>();
        r2.postDelayed(r3, r6);
        goto L_0x0025;
    L_0x008a:
        r2 = r9.getString(r8);
        com.mcdonalds.app.util.AnalyticsUtils.trackScreenLoad(r2);
        r2 = new android.os.Handler;
        r2.<init>();
        r3 = new com.mcdonalds.app.ordering.menu.MenuActivity$10;
        r3.<init>();
        r2.postDelayed(r3, r6);
        goto L_0x0025;
    L_0x009f:
        r2 = r9.getString(r8);
        com.mcdonalds.app.util.AnalyticsUtils.trackScreenLoad(r2);
        r2 = "catUpdate";
        r3 = "start_order";
        android.util.Log.i(r2, r3);
        r2 = com.mcdonalds.sdk.modules.ordering.OrderManager.getInstance();
        r0 = r2.getCurrentStore();
        if (r0 == 0) goto L_0x00d0;
    L_0x00b7:
        r2 = r0.hasMobileOrdering();
        r2 = r2.booleanValue();
        if (r2 != 0) goto L_0x00d0;
    L_0x00c1:
        r2 = new android.os.Handler;
        r2.<init>();
        r3 = new com.mcdonalds.app.ordering.menu.MenuActivity$11;
        r3.<init>();
        r2.postDelayed(r3, r6);
        goto L_0x0025;
    L_0x00d0:
        r2 = r9.mAllTab;
        if (r2 == 0) goto L_0x00d9;
    L_0x00d4:
        r2 = r9.mAllTab;
        r2.select();
    L_0x00d9:
        r2 = r1.isEmpty();
        if (r2 != 0) goto L_0x00ea;
    L_0x00df:
        r2 = r1.isDelivery();
        if (r2 != 0) goto L_0x00ea;
    L_0x00e5:
        r9.checkIfCatalogFail();
        goto L_0x0025;
    L_0x00ea:
        r2 = new android.os.Handler;
        r2.<init>();
        r3 = new com.mcdonalds.app.ordering.menu.MenuActivity$12;
        r3.<init>();
        r2.postDelayed(r3, r6);
        goto L_0x0025;
    L_0x00f9:
        r2 = r9.getString(r8);
        com.mcdonalds.app.util.AnalyticsUtils.trackScreenLoad(r2);
        r2 = "catUpdate";
        r3 = "start_order_delivery";
        android.util.Log.i(r2, r3);
        r2 = r9.mAllTab;
        if (r2 == 0) goto L_0x0110;
    L_0x010b:
        r2 = r9.mAllTab;
        r2.select();
    L_0x0110:
        r2 = r1.isEmpty();
        if (r2 != 0) goto L_0x0121;
    L_0x0116:
        r2 = r1.isDelivery();
        if (r2 == 0) goto L_0x0121;
    L_0x011c:
        r9.checkIfCatalogFail();
        goto L_0x0025;
    L_0x0121:
        r2 = new android.os.Handler;
        r2.<init>();
        r3 = new com.mcdonalds.app.ordering.menu.MenuActivity$13;
        r3.<init>();
        r2.postDelayed(r3, r6);
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.app.ordering.menu.MenuActivity.goToFragment(java.lang.String):void");
    }

    /* Access modifiers changed, original: protected */
    public Fragment getScreenFragment(@NonNull String screen) {
        Ensighten.evaluateEvent(this, "getScreenFragment", new Object[]{screen});
        return null;
    }

    /* Access modifiers changed, original: protected */
    public int getContentViewResource() {
        Ensighten.evaluateEvent(this, "getContentViewResource", null);
        return C2658R.layout.activity_menu;
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldShowHamburgerMenu() {
        Ensighten.evaluateEvent(this, "shouldShowHamburgerMenu", null);
        return true;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Ensighten.evaluateEvent(this, "onPageScrolled", new Object[]{new Integer(position), new Float(positionOffset), new Integer(positionOffsetPixels)});
    }

    public void firstLoadproducts() {
        Ensighten.evaluateEvent(this, "firstLoadproducts", null);
        if (!this.mFirstLoadProducts) {
            this.mFirstLoadProducts = true;
            MenuType menuType = this.mDayPartPagerAdapter.getMenuTypeForPosition(this.mDayPartPager.getCurrentItem());
            for (OnDayPartChangeListener listener : this.mOnDayPartChangeListeners) {
                listener.onDayPartChange(menuType);
            }
        }
    }

    public void onPageSelected(int position) {
        Ensighten.evaluateEvent(this, "onPageSelected", new Object[]{new Integer(position)});
        this.mDayPartPager.setCurrentItem(position);
        MenuType menuType = this.mDayPartPagerAdapter.getMenuTypeForPosition(position);
        for (OnDayPartChangeListener listener : this.mOnDayPartChangeListeners) {
            listener.onDayPartChange(menuType);
        }
    }

    public void onPageScrollStateChanged(int state) {
        Ensighten.evaluateEvent(this, "onPageScrollStateChanged", new Object[]{new Integer(state)});
    }

    public void addOnDayPartChangeListener(OnDayPartChangeListener listener) {
        Ensighten.evaluateEvent(this, "addOnDayPartChangeListener", new Object[]{listener});
        if (this.mOnDayPartChangeListeners != null) {
            this.mOnDayPartChangeListeners.add(listener);
            MenuType currentMenuType = this.mDayPartPagerAdapter.getMenuTypeForPosition(this.mDayPartPager.getCurrentItem());
            if (currentMenuType != null) {
                listener.onDayPartChange(currentMenuType);
            }
        }
    }

    public void addOnPodChangeListener(OnPodChangeListener listener) {
        Ensighten.evaluateEvent(this, "addOnPodChangeListener", new Object[]{listener});
        this.mOnPodChangeListeners.add(listener);
        listener.onPodChange(getCurrentPod());
    }

    public void addCatalogListener(CatalogListener listener) {
        Ensighten.evaluateEvent(this, "addCatalogListener", new Object[]{listener});
        this.mCatalogListeners.add(listener);
        listener.onCatalogReady();
    }

    private String getCurrentPod() {
        Ensighten.evaluateEvent(this, "getCurrentPod", null);
        if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
            return Pod.DELIVERY;
        }
        return Pod.PICKUP;
    }

    /* Access modifiers changed, original: protected|final */
    public final void showOrderMethodSelector() {
        Ensighten.evaluateEvent(this, "showOrderMethodSelector", null);
        startActivityForResult(OrderMethodSelectionActivity.class, 1);
    }

    /* Access modifiers changed, original: protected|final */
    public final void showFavoriteMethodSelector() {
        Ensighten.evaluateEvent(this, "showFavoriteMethodSelector", null);
        Intent intent = new Intent(getBaseContext(), OrderMethodSelectionActivity.class);
        intent.putExtra("favorite", true);
        startActivityForResult(intent, 5);
    }

    private void setDaypartIndicator() {
        Ensighten.evaluateEvent(this, "setDaypartIndicator", null);
        PagerIndicator indicator = (PagerIndicator) findViewById(C2358R.C2357id.pager_indicator);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.ordering.disableDeliveryMenuScroll")) {
            if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                indicator.setupOneDotPager(this.mDayPartPager);
            } else {
                indicator.setupWithViewPager(this.mDayPartPager);
            }
            if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                this.mDayPartPager.setOnTouchListener(new C353614());
            } else {
                this.mDayPartPager.setOnTouchListener(null);
            }
        }
    }

    private void setupDaypart() {
        Ensighten.evaluateEvent(this, "setupDaypart", null);
        this.mDayPartPagerAdapter = new DayPartPagerAdapter(this, RequestManager.register(this));
        this.mDayPartPager.setAdapter(this.mDayPartPagerAdapter);
        this.mDayPartPager.addOnPageChangeListener(this);
        setDaypartIndicator();
    }

    private void setupTabs() {
        Ensighten.evaluateEvent(this, "setupTabs", null);
        this.mTabs = (TabLayout) findViewById(C2358R.C2357id.fragment_tabs);
        final NonSwipeableViewPager fragmentPager = (NonSwipeableViewPager) findViewById(C2358R.C2357id.fragment_pager);
        MenuPagerAdapter adapter = new MenuPagerAdapter(getSupportFragmentManager());
        this.mRecentsTab = this.mTabs.newTab().setIcon((int) C2358R.C2359drawable.icon_ordertabs_recents);
        DataLayerClickListener.setDataLayerTag(this.mRecentsTab, "FilterRecentsTabPressed");
        boolean hideRecents = Configuration.getSharedInstance().getBooleanForKey("interface.ordering.hideRecentOrders");
        if (this.mCustomerModule.isLoggedIn() && !hideRecents) {
            this.mTabs.addTab(this.mRecentsTab);
            adapter.addFragment(new RecentsFragment());
            LayoutParams params = (LayoutParams) this.mTabs.getLayoutParams();
            params.weight = 3.0f;
            this.mTabs.setLayoutParams(params);
        }
        this.mFavoritesTab = this.mTabs.newTab().setIcon((int) C2358R.C2359drawable.icon_ordertabs_favorites);
        DataLayerClickListener.setDataLayerTag(this.mFavoritesTab, "FilterFavoritesTabPressed");
        this.mTabs.addTab(this.mFavoritesTab);
        adapter.addFragment(new FavoritesFragment());
        this.mAllTab = this.mTabs.newTab().setText((int) C2658R.string.all);
        this.mTabs.addTab(this.mAllTab);
        DataLayerClickListener.setDataLayerTag(this.mAllTab, "FilterAllTabPressed");
        Tab tab = this.mTabs.getTabAt(this.mTabs.getTabCount() - 1);
        if (tab != null) {
            tab.setCustomView((int) C2658R.layout.tab_header);
        }
        if (Configuration.getSharedInstance().getBooleanForKey("interface.useExpandableCategoriesInGridMenu")) {
            this.mMenuGridFragment = new MenuGridExpandableFragment();
            this.mMenuGridFragment.setArguments(getIntent().getExtras());
            adapter.addFragment(this.mMenuGridFragment);
        } else {
            this.mMenuGridFragment = new MenuGridFragment();
            this.mMenuGridFragment.setArguments(getIntent().getExtras());
            adapter.addFragment(this.mMenuGridFragment);
        }
        fragmentPager.setAdapter(adapter);
        fragmentPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(this.mTabs));
        this.mTabs.setOnTabSelectedListener(new OnTabSelectedListener() {
            public void onTabSelected(Tab tab) {
                Ensighten.evaluateEvent(this, "onTabSelected", new Object[]{tab});
                fragmentPager.setCurrentItem(tab.getPosition(), true);
                MenuActivity.this.setTitle(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$400", new Object[]{MenuActivity.this, new Integer(tab.getPosition())}));
            }

            public void onTabUnselected(Tab tab) {
                Ensighten.evaluateEvent(this, "onTabUnselected", new Object[]{tab});
                fragmentPager.setCurrentItem(tab.getPosition(), true);
                MenuActivity.this.setTitle(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$400", new Object[]{MenuActivity.this, new Integer(tab.getPosition())}));
            }

            public void onTabReselected(Tab tab) {
                Ensighten.evaluateEvent(this, "onTabReselected", new Object[]{tab});
                fragmentPager.setCurrentItem(tab.getPosition(), true);
                MenuActivity.this.setTitle(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.MenuActivity", "access$400", new Object[]{MenuActivity.this, new Integer(tab.getPosition())}));
            }
        });
    }

    private int getTabNameResId(int position) {
        Ensighten.evaluateEvent(this, "getTabNameResId", new Object[]{new Integer(position)});
        switch (position) {
            case 1:
                return C2658R.string.my_favorite;
            default:
                return C2658R.string.title_activity_menu;
        }
    }

    private void setupSearch() {
        Ensighten.evaluateEvent(this, "setupSearch", null);
        this.mSearchButton = findViewById(2131820678);
        this.mSearchContainer = findViewById(C2358R.C2357id.search_bar_container);
        this.mSearchBar = (EditText) findViewById(2131820676);
        View cancelSearchButton = findViewById(C2358R.C2357id.cancel_search);
        this.mSearchButton.setOnClickListener(new C353816());
        cancelSearchButton.setOnClickListener(new C353917());
        this.mSearchBar.setOnEditorActionListener(this.mFoodSearchEditorListener);
        DataLayerClickListener.setDataLayerTag(this.mSearchButton, "FilterSearchTabPressed");
        DataLayerClickListener.setDataLayerTag(cancelSearchButton, "CancelSearchPressed");
    }

    public void setSearchFilter(Filter filter) {
        Ensighten.evaluateEvent(this, "setSearchFilter", new Object[]{filter});
        this.mWatcher = new TextChangeFilter(filter);
        if (this.mSearchBar != null) {
            this.mSearchBar.addTextChangedListener(this.mWatcher);
        }
    }

    public void removeSearchFilter() {
        Ensighten.evaluateEvent(this, "removeSearchFilter", null);
        if (this.mSearchBar != null) {
            this.mSearchBar.removeTextChangedListener(this.mWatcher);
        }
    }

    private void hideSearchBar() {
        Ensighten.evaluateEvent(this, "hideSearchBar", null);
        this.mSearchContainer.setVisibility(8);
        this.mSearchButton.setVisibility(0);
        this.mSearchBar.setText("");
        this.mSearchBar.clearFocus();
        this.mTabs.setVisibility(0);
        UIUtils.dismissKeyboard(this, this.mSearchBar);
    }

    private void showSearchBar() {
        Ensighten.evaluateEvent(this, "showSearchBar", null);
        collapseHeader(false);
        this.mAllTab.select();
        this.mTabs.setVisibility(8);
        this.mSearchButton.setVisibility(8);
        this.mSearchContainer.setVisibility(0);
        this.mSearchBar.requestFocus();
        UIUtils.showKeyboard(this, this.mSearchBar, false);
        DataLayerManager.getInstance().logPageLoad("ProductsFilterSearchSubview", "PageViewed");
    }

    public void collapseHeader(boolean animate) {
        Ensighten.evaluateEvent(this, "collapseHeader", new Object[]{new Boolean(animate)});
        ((AppBarLayout) findViewById(C2358R.C2357id.app_bar)).setExpanded(false, animate);
    }

    private void setStore() {
        Ensighten.evaluateEvent(this, "setStore", null);
        this.mStore = OrderManager.getInstance().getCurrentStore();
        int currentDayPart = this.mStore == null ? 0 : this.mStore.getCurrentMenuTypeID(OrderingManager.getInstance().getCurrentOrder().isDelivery());
        if (this.mDayPartPagerAdapter != null) {
            this.mDayPartPager.setCurrentItem(this.mDayPartPagerAdapter.getPositionForMenuTypeId(currentDayPart));
        }
        if (!(!Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection") || this.mStore == null || this.mDayPartPagerAdapter == null)) {
            this.mDayPartPagerAdapter.setStore(this.mStore);
        }
        setupStoreTitle();
    }

    private void setupStoreTitle() {
        Ensighten.evaluateEvent(this, "setupStoreTitle", null);
        if (this.mStoreTitle != null) {
            String storeTitleString = " ";
            String dateString = " ";
            if (OrderingManager.getInstance().getCurrentOrder().isDelivery() && !LocalDataManager.getSharedInstance().isFirstTimeDelivery()) {
                Order deliveryOrder = OrderingManager.getInstance().getCurrentOrder();
                String customerAddressString = null;
                if (deliveryOrder.getDeliveryAddress() != null) {
                    customerAddressString = deliveryOrder.getDeliveryAddress().getFullAddress();
                }
                if (this.mStore == null || !TextUtils.isEmpty(this.mStore.getStoreFavoriteName())) {
                    this.mStoreTitle.setTextColor(getResources().getColor(C2658R.color.mcd_red));
                } else {
                    this.mStoreTitle.setTextColor(getResources().getColor(17170444));
                }
                SimpleDateFormat deliveryTimeFormat = new SimpleDateFormat("hh:mm");
                SimpleDateFormat deliveryDateFormat = new SimpleDateFormat("dd/MM/yy");
                SimpleDateFormat deliveryDateFormatCN = new SimpleDateFormat("yyyy/MM/dd");
                if (deliveryOrder.isNormalOrder() && Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes")) {
                    String time = setAsapDeliveryDate(deliveryOrder.getDeliveryStore());
                    storeTitleString = getString(C2658R.string.estimated_delivery_range_5, new Object[]{"<b>" + time + "</b>", "<b>" + customerAddressString + "</b>"});
                } else {
                    Calendar deliveryCal = Calendar.getInstance();
                    Date deliveryDate = deliveryOrder.getDeliveryDate();
                    if (deliveryDate != null) {
                        deliveryCal.setTime(deliveryDate);
                        if (Configuration.getSharedInstance().getCurrentLanguageTag().equals("zh-CHS")) {
                            dateString = deliveryDateFormatCN.format(deliveryCal.getTime()) + " " + (deliveryCal.get(9) == 0 ? "上午" : "下午") + deliveryTimeFormat.format(deliveryCal.getTime());
                            storeTitleString = getString(C2658R.string.estimated_delivery_range_2, new Object[]{"<b>" + dateString + "</b>", "<b>" + customerAddressString + "</b>"});
                        } else {
                            dateString = deliveryDateFormat.format(deliveryCal.getTime()) + ", " + deliveryTimeFormat.format(deliveryCal.getTime()) + " " + (deliveryCal.get(9) == 0 ? "am" : "pm");
                            storeTitleString = getString(C2658R.string.estimated_delivery_range_2, new Object[]{"<b>" + dateString + "</b>", "<b>" + customerAddressString + "</b>"});
                        }
                    }
                }
            } else if (this.mStore == null || this.mStore.getStoreFavoriteName() != null) {
                this.mStoreTitle.setTextColor(getResources().getColor(C2658R.color.mcd_red));
                storeTitleString = getString(C2658R.string.pickup_at_restaurant_2) + "<b> " + (this.mStore == null ? "" : this.mStore.getStoreFavoriteName()) + "</b>";
            } else {
                this.mStoreTitle.setTextColor(getResources().getColor(17170444));
                storeTitleString = getString(C2658R.string.pickup_at_restaurant_2) + "<b> " + (this.mStore == null ? "" : this.mStore.getPublicName()) + "</b>";
            }
            this.mStoreTitle.setText(Html.fromHtml(storeTitleString));
        }
    }

    private String setAsapDeliveryDate(Store store) {
        Ensighten.evaluateEvent(this, "setAsapDeliveryDate", new Object[]{store});
        String time = getString(C2658R.string.edt_minutes, new Object[]{Integer.valueOf(30)});
        if (store == null) {
            return time;
        }
        Date nowInStoreTime = UIUtils.getDateFromISO8601(store.getNowInStoreLocalTime(), TimeZone.getDefault());
        Date edtInStoreTime = UIUtils.getDateFromISO8601(store.getExpectedDeliveryTime());
        if (Configuration.getSharedInstance().getBooleanForKey("interface.showEstimatedDeliveryTimeInMinutes")) {
            return UIUtils.formatDeliveryTimeInMinutes(this, nowInStoreTime, edtInStoreTime);
        }
        return time;
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String pod;
        if ((requestCode == 1 || requestCode == 5) && resultCode == 0) {
            finish();
        } else if (requestCode == 1 && resultCode == -1) {
            if (Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection") && this.mSkipFirstLoadAddressWorkflow) {
                setupDaypart();
                setupTabs();
                setStore();
                goToFragment("menu_grid");
                getContentResolver().registerContentObserver(CurrentStore.CONTENT_URI, true, this.mStoreObserver);
                getContentResolver().registerContentObserver(Contract.CONTENT_URI, true, this.mCatalogObserver);
                this.mSkipFirstLoadAddressWorkflow = false;
            } else {
                setStore();
                setDaypartIndicator();
                goToFragment("menu_grid");
                pod = getCurrentPod();
                for (OnPodChangeListener listener : this.mOnPodChangeListeners) {
                    listener.onPodChange(pod);
                }
            }
            checkIfCatalogFail();
        } else if (requestCode == 2 && resultCode == -1) {
            if (Configuration.getSharedInstance().getBooleanForKey("interface.ordering.showCartAfterOrderAgain")) {
                navigateToBasket();
            }
        } else if (requestCode != 5 || resultCode != -1) {
        } else {
            if (Configuration.getSharedInstance().getBooleanForKey("interface.skipFirstLoadAddressSelection") && this.mSkipFirstLoadAddressWorkflow) {
                setupDaypart();
                setupTabs();
                setStore();
                this.mFavoritesTab.select();
                getContentResolver().registerContentObserver(CurrentStore.CONTENT_URI, true, this.mStoreObserver);
                getContentResolver().registerContentObserver(Contract.CONTENT_URI, true, this.mCatalogObserver);
                this.mSkipFirstLoadAddressWorkflow = false;
                return;
            }
            setStore();
            setDaypartIndicator();
            this.mFavoritesTab.select();
            pod = getCurrentPod();
            for (OnPodChangeListener listener2 : this.mOnPodChangeListeners) {
                listener2.onPodChange(pod);
            }
        }
    }

    private void trackFoodPageSearch(String searchString) {
        Ensighten.evaluateEvent(this, "trackFoodPageSearch", new Object[]{searchString});
        this.mSearchBar.clearFocus();
        UIUtils.dismissKeyboard(this, this.mSearchBar);
        if (!TextUtils.isEmpty(searchString)) {
            AnalyticsUtils.trackFoodSearch("/food/food-search", searchString);
        }
    }

    public void checkIfCatalogFail() {
        Ensighten.evaluateEvent(this, "checkIfCatalogFail", null);
        new Handler().postDelayed(new C354119(), 5000);
    }

    public void catalogSyncWithTimeout() {
        Ensighten.evaluateEvent(this, "catalogSyncWithTimeout", null);
        new Handler().postDelayed(new C354320(), 20000);
    }
}
