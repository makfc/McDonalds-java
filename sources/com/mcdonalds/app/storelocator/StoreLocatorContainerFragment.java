package com.mcdonalds.app.storelocator;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.amap.api.location.LocationManagerProxy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity.PermissionListener;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.storelocator.maps.model.CameraPosition;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.widget.GeoSuggestionsEditText;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.connectors.autonavi.AutoNavi.Methods;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class StoreLocatorContainerFragment extends URLNavigationFragment implements OnFocusChangeListener, OnEditorActionListener {
    private static final String TAG = StoreLocatorContainerFragment.class.getCanonicalName();
    private ActiveFragment mActiveFragment = ActiveFragment.List;
    boolean mAutoSelectClosestStore;
    private StoreLocatorController mController;
    private Fragment mCurrentFragment;
    boolean mCurrentStoreSelectionMode;
    boolean mDismissOnStartOrder;
    private StoreLocatorInteractionListener mInteractionListener;
    public boolean mIsFirstLoad;
    private boolean mIsRefreshLoad = true;
    private StoreLocatorListFragment mListFragment;
    private StoreLocatorMapFragment mMapFragment;
    boolean mMapOnly = false;
    public LinearLayout mMobileOrderFilterLayout;
    private FrameLayout mMobileOrderFilterLayoutContainer;
    String mNearbySearchAddress;
    private boolean mOffersMode;
    private OnCheckedChangeListener mOnCheckedChangeListener = new C37403();
    public LinearLayout mSearchAndFilterLayout;
    private StoreLocatorSearchFragment mSearchFragment;
    private GeoSuggestionsEditText mSearchView;
    private int mSelectorCheckedID;
    private Switch mStoreFilterSwitch;
    private TabLayout mTabLayout;

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorContainerFragment$1 */
    class C37381 implements OnTabSelectedListener {
        C37381() {
        }

        public void onTabSelected(Tab tab) {
            int i = 0;
            Ensighten.evaluateEvent(this, "onTabSelected", new Object[]{tab});
            if (tab.getTag() != null) {
                StoreLocatorContainerFragment storeLocatorContainerFragment = StoreLocatorContainerFragment.this;
                if (!tab.getTag().equals(Methods.LIST)) {
                    i = 1;
                }
                StoreLocatorContainerFragment.access$002(storeLocatorContainerFragment, i);
                StoreLocatorContainerFragment.access$100(StoreLocatorContainerFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorContainerFragment", "access$000", new Object[]{StoreLocatorContainerFragment.this}));
            }
        }

        public void onTabUnselected(Tab tab) {
            Ensighten.evaluateEvent(this, "onTabUnselected", new Object[]{tab});
        }

        public void onTabReselected(Tab tab) {
            Ensighten.evaluateEvent(this, "onTabReselected", new Object[]{tab});
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorContainerFragment$2 */
    class C37392 implements Runnable {
        C37392() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            UIUtils.showKeyboard(StoreLocatorContainerFragment.this.getActivity(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorContainerFragment", "access$200", new Object[]{StoreLocatorContainerFragment.this}).getField(), false);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorContainerFragment$3 */
    class C37403 implements OnCheckedChangeListener {
        C37403() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{buttonView, new Boolean(isChecked)});
            if (isChecked) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorContainerFragment", "access$300", new Object[]{StoreLocatorContainerFragment.this}).filterNearbyBasedOnMobileOrdering();
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorContainerFragment", "access$300", new Object[]{StoreLocatorContainerFragment.this}).undoFilterBasedOnMobileOrdering();
            }
            DataLayerManager.getInstance().setStoreFilterToggled(isChecked);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorContainerFragment$4 */
    class C37424 implements PermissionListener {

        /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorContainerFragment$4$1 */
        class C37411 implements OnClickListener {
            C37411() {
            }

            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                dialog.dismiss();
            }
        }

        C37424() {
        }

        public void onRequestPermissionsResult(int requestCode, String permission, int grantResult) {
            boolean z = true;
            Ensighten.evaluateEvent(this, "onRequestPermissionsResult", new Object[]{new Integer(requestCode), permission, new Integer(grantResult)});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorContainerFragment", "access$400", new Object[]{StoreLocatorContainerFragment.this})) {
                StoreLocatorController access$300 = Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorContainerFragment", "access$300", new Object[]{StoreLocatorContainerFragment.this});
                if (grantResult != 0) {
                    z = false;
                }
                access$300.start(z);
            }
            if (grantResult == 0 && !((LocationManager) StoreLocatorContainerFragment.this.getActivity().getSystemService(LocationManagerProxy.KEY_LOCATION_CHANGED)).isProviderEnabled("gps")) {
                MCDAlertDialogBuilder.withContext(StoreLocatorContainerFragment.this.getActivity()).setTitle((int) C2658R.string.gps_not_enabled_title).setMessage((int) C2658R.string.location_services_nearby_stores_alert).setPositiveButton((int) C2658R.string.f6083ok, new C37411()).setCancelable(false).create().show();
            }
        }
    }

    private enum ActiveFragment {
        List,
        Map,
        Search
    }

    static /* synthetic */ int access$002(StoreLocatorContainerFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorContainerFragment", "access$002", new Object[]{x0, new Integer(x1)});
        x0.mSelectorCheckedID = x1;
        return x1;
    }

    static /* synthetic */ void access$100(StoreLocatorContainerFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorContainerFragment", "access$100", new Object[]{x0, new Integer(x1)});
        x0.switchFragment(x1);
    }

    public StoreLocatorController getController() {
        Ensighten.evaluateEvent(this, "getController", null);
        return this.mController;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        if (this.mCurrentFragment == this.mListFragment) {
            Analytics.trackCustom(18, getString(C2658R.string.analytics_screen_restaurant_view_type_list));
        } else {
            Analytics.trackCustom(18, getString(C2658R.string.analytics_screen_restaurant_view_type_map));
        }
        return getString(C2658R.string.analytics_screen_restaurant_locator);
    }

    public void onSaveInstanceState(Bundle outState) {
        Ensighten.evaluateEvent(this, "onSaveInstanceState", new Object[]{outState});
        super.onSaveInstanceState(outState);
        outState.putInt("CHECKED_SELECTOR_ID", this.mSelectorCheckedID);
        outState.putSerializable("VISIBLE_FRAGMENT", this.mActiveFragment);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mMapOnly = false;
        this.mCurrentStoreSelectionMode = false;
        this.mNearbySearchAddress = null;
        this.mDismissOnStartOrder = false;
        OffersStoreLocatorController offersStoreLocatorController = null;
        Boolean allowsSelection = null;
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.getBoolean("OFFERS_MODE", false)) {
                this.mOffersMode = true;
                offersStoreLocatorController = (OffersStoreLocatorController) DataPasser.getInstance().getData(arguments.getInt(URLNavigationActivity.DATA_PASSER_KEY));
                allowsSelection = Boolean.valueOf(arguments.getBoolean("EXTRA_ALLOWS_SELECTION", false));
            } else {
                this.mMapOnly = arguments.getBoolean("map_only");
                this.mCurrentStoreSelectionMode = arguments.getBoolean("set_current_store");
                this.mAutoSelectClosestStore = arguments.getBoolean("select_closest");
                this.mDismissOnStartOrder = arguments.getBoolean("dismiss_on_place_order");
                this.mNearbySearchAddress = arguments.getString("search_by_address", null);
                this.mIsFirstLoad = arguments.getBoolean("EXTRA_FIRST_LOAD");
                if (this.mNearbySearchAddress != null) {
                    this.mAutoSelectClosestStore = true;
                }
                allowsSelection = (Boolean) arguments.getSerializable(URLNavigationActivity.DATA_PASSER_KEY);
            }
        }
        if (!this.mOffersMode) {
            StoreLocatorController offersStoreLocatorController2 = new StoreLocatorController(this, this.mMapOnly, this.mCurrentStoreSelectionMode, this.mAutoSelectClosestStore, this.mNearbySearchAddress, this.mDismissOnStartOrder);
        }
        this.mController = offersStoreLocatorController;
        if (this.mController != null) {
            this.mCurrentStoreSelectionMode = this.mController.isCurrentStoreSelectionMode();
            if (allowsSelection != null) {
                this.mController.setAllowsSelectionWithoutMobileOrdering(allowsSelection.booleanValue());
            }
            this.mInteractionListener = this.mController;
        }
        if (savedInstanceState != null) {
            this.mSelectorCheckedID = savedInstanceState.getInt("CHECKED_SELECTOR_ID");
            this.mActiveFragment = (ActiveFragment) savedInstanceState.getSerializable("VISIBLE_FRAGMENT");
        } else if (Configuration.getSharedInstance().getBooleanForKey("interface.storelocator.storeMapAsDefaultView") || this.mOffersMode || this.mCurrentStoreSelectionMode) {
            this.mSelectorCheckedID = 1;
            this.mActiveFragment = ActiveFragment.Map;
        } else {
            this.mSelectorCheckedID = 0;
            this.mActiveFragment = ActiveFragment.List;
        }
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getChildFragmentManager();
            this.mMapFragment = new StoreLocatorMapFragment();
            this.mMapFragment.setController(this.mController);
            this.mMapFragment.setIsFirstLoad(this.mIsFirstLoad);
            if (arguments != null) {
                CameraPosition position = (CameraPosition) arguments.getSerializable("EXTRA_INITIAL_CAMERA_POSITION");
                if (position != null) {
                    this.mMapFragment.setInitialCameraPosition(position);
                }
            }
            fragmentManager.beginTransaction().add(C2358R.C2357id.stores_container, this.mMapFragment, "MAP_FRAGMENT").commit();
            this.mListFragment = new StoreLocatorListFragment();
            this.mListFragment.setController(this.mController);
            fragmentManager.beginTransaction().add(C2358R.C2357id.stores_container, this.mListFragment, "LIST_FRAGMENT").commit();
            this.mSearchFragment = new StoreLocatorSearchFragment();
            this.mSearchFragment.setController(this.mController);
            fragmentManager.beginTransaction().add(C2358R.C2357id.stores_container, this.mSearchFragment, "SEARCH_FRAGMENT").commit();
            fragmentManager.executePendingTransactions();
        }
        getNavigationActivity().setTitle(getString(C2658R.string.title_activity_start_order_find_store));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup group = (ViewGroup) inflater.inflate(C2658R.layout.fragment_storelocator_container, container, false);
        this.mSearchView = (GeoSuggestionsEditText) group.findViewById(C2358R.C2357id.searchText);
        this.mTabLayout = (TabLayout) group.findViewById(C2358R.C2357id.tabs);
        this.mSearchAndFilterLayout = (LinearLayout) group.findViewById(C2358R.C2357id.search_and_filter_layout);
        this.mMobileOrderFilterLayoutContainer = (FrameLayout) group.findViewById(C2358R.C2357id.mobile_order_filter_layout_container);
        this.mMobileOrderFilterLayout = (LinearLayout) group.findViewById(C2358R.C2357id.mobile_order_filter_layout);
        this.mStoreFilterSwitch = (Switch) group.findViewById(C2358R.C2357id.filter_selected);
        this.mStoreFilterSwitch.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.storelocator.showMobileOrderingStoreQuickSelect")) {
            this.mMobileOrderFilterLayoutContainer.setVisibility(0);
            this.mController.filterNearbyBasedOnMobileOrdering();
            this.mStoreFilterSwitch.setChecked(true);
            DataLayerManager.getInstance().setStoreFilterToggled(true);
        } else {
            this.mMobileOrderFilterLayoutContainer.setVisibility(8);
        }
        if (this.mController.isMapOnly()) {
            this.mTabLayout.setVisibility(8);
        } else {
            this.mTabLayout.addTab(this.mTabLayout.newTab().setText((int) C2658R.string.list_radio_btn).setTag(Methods.LIST));
            this.mTabLayout.addTab(this.mTabLayout.newTab().setText((int) C2658R.string.map_radio_btn).setTag("map"));
            Tab tab = this.mTabLayout.getTabAt(this.mSelectorCheckedID);
            if (tab != null) {
                tab.select();
            }
            this.mTabLayout.setOnTabSelectedListener(new C37381());
        }
        if (this.mController != null) {
            this.mController.setMapOnly(this.mMapOnly);
            if (this.mController.isMapOnly()) {
                this.mActiveFragment = ActiveFragment.Map;
                this.mSearchAndFilterLayout.setVisibility(8);
            }
        }
        if (this.mSearchView.getVisibility() == 0) {
            this.mSearchView.clearFocus();
            this.mSearchView.setOnFocusChangeListener(this);
            this.mSearchView.setOnEditorActionListener(this);
        }
        return group;
    }

    public void onStart() {
        super.onStart();
        if (!ModuleManager.isModuleEnabled(StoreLocatorModule.NAME).booleanValue()) {
            AsyncException.report("The StoreLocator module is not enabled");
            getActivity().onBackPressed();
        }
        checkLocationPermission();
        checkForPendingAddToFavorites();
        getNavigationActivity().showNavigateUp(true);
    }

    public void onStop() {
        super.onStop();
        getNavigationActivity().showNavigateUp(false);
    }

    private void checkForPendingAddToFavorites() {
        Ensighten.evaluateEvent(this, "checkForPendingAddToFavorites", null);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getBoolean("EXTRA_SAVING_FAVORITE", false)) {
            int pendingStoreId = arguments.getInt("StoreLocatorFragment.SAVING_FAVORITE_ID", -1);
            if (pendingStoreId != -1) {
                StoreLocatorSection pendingSection = (StoreLocatorSection) arguments.getSerializable("saving_fav_section");
                this.mController.setSelectedStoreNickName(arguments.getString("StoreLocatorFragment.SAVING_FAVORITE_NICKNAME", null));
                this.mController.addToFavoritesClicked(Integer.valueOf(pendingStoreId), pendingSection);
                arguments.remove("EXTRA_SAVING_FAVORITE");
                arguments.remove("StoreLocatorFragment.SAVING_FAVORITE_ID");
                arguments.remove("saving_fav_section");
                arguments.remove("StoreLocatorFragment.SAVING_FAVORITE_NICKNAME");
            }
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mCurrentFragment == this.mSearchFragment) {
            this.mSearchView.setEnabled(false);
            UIUtils.dismissKeyboard(getActivity(), this.mSearchView.getField());
            if (getActivity() != null && getActivity().getActionBar() != null) {
                getActivity().getActionBar().show();
            }
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mCurrentFragment == this.mSearchFragment) {
            this.mSearchView.setEnabled(true);
            new Handler(Looper.myLooper()).postDelayed(new C37392(), 500);
        }
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Fragment showFragment;
        Fragment hideFragment1;
        Fragment hideFragment2;
        super.onActivityCreated(savedInstanceState);
        FragmentManager fragmentManager = getChildFragmentManager();
        this.mMapFragment = (StoreLocatorMapFragment) fragmentManager.findFragmentByTag("MAP_FRAGMENT");
        this.mListFragment = (StoreLocatorListFragment) fragmentManager.findFragmentByTag("LIST_FRAGMENT");
        this.mSearchFragment = (StoreLocatorSearchFragment) fragmentManager.findFragmentByTag("SEARCH_FRAGMENT");
        switch (this.mActiveFragment) {
            case List:
                showFragment = this.mListFragment;
                hideFragment1 = this.mMapFragment;
                hideFragment2 = this.mSearchFragment;
                this.mCurrentFragment = this.mListFragment;
                break;
            case Map:
                showFragment = this.mMapFragment;
                hideFragment1 = this.mListFragment;
                hideFragment2 = this.mSearchFragment;
                this.mCurrentFragment = this.mMapFragment;
                break;
            default:
                showFragment = this.mSearchFragment;
                hideFragment1 = this.mListFragment;
                hideFragment2 = this.mMapFragment;
                this.mCurrentFragment = this.mSearchFragment;
                break;
        }
        fragmentManager.beginTransaction().hide(hideFragment1).hide(hideFragment2).show(showFragment).commit();
    }

    public void onDetach() {
        super.onDetach();
        UIUtils.stopActivityIndicator();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    public void onFocusChange(View view, boolean hasFocus) {
        Ensighten.evaluateEvent(this, "onFocusChange", new Object[]{view, new Boolean(hasFocus)});
        if (hasFocus && this.mCurrentFragment != this.mSearchFragment) {
            getChildFragmentManager().beginTransaction().show(this.mSearchFragment).hide(this.mListFragment).hide(this.mMapFragment).commit();
            this.mActiveFragment = ActiveFragment.Search;
            this.mCurrentFragment = this.mSearchFragment;
            this.mMobileOrderFilterLayout.setVisibility(8);
        }
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{textView, new Integer(i), keyEvent});
        if (i == 3 || keyEvent.getKeyCode() == 66) {
            showCurrentView();
            searchByname();
            this.mSearchFragment.updateViewItems();
            return true;
        } else if (i != 0) {
            return false;
        } else {
            this.mSearchView.clearFocus();
            return true;
        }
    }

    private void switchFragment(int tabId) {
        Ensighten.evaluateEvent(this, "switchFragment", new Object[]{new Integer(tabId)});
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (tabId == 0) {
            ft.show(this.mListFragment);
            ft.hide(this.mMapFragment);
            ft.hide(this.mSearchFragment);
            this.mCurrentFragment = this.mListFragment;
            this.mActiveFragment = ActiveFragment.List;
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "List");
        } else {
            ft.show(this.mMapFragment);
            ft.hide(this.mListFragment);
            ft.hide(this.mSearchFragment);
            this.mCurrentFragment = this.mMapFragment;
            this.mActiveFragment = ActiveFragment.Map;
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Map");
        }
        ft.commitAllowingStateLoss();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 22:
                switch (resultCode) {
                    case -1:
                        String newName = data.getStringExtra("name");
                        if (newName != null && !newName.isEmpty()) {
                            this.mController.nickNameSelected(newName);
                            this.mIsRefreshLoad = false;
                            return;
                        }
                        return;
                    default:
                        super.onActivityResult(requestCode, resultCode, data);
                        return;
                }
            case 29:
                if (resultCode == 0 && data != null) {
                    String name = data.getStringExtra("name");
                    int storeId = data.getIntExtra("EXTRA_STORE_ID", 0);
                    if (!(name == null || storeId == 0)) {
                        this.mController.nickNameChangedOnStoreId(name, storeId);
                    }
                }
                this.mIsRefreshLoad = false;
                return;
            case 30001:
                switch (resultCode) {
                    case -1:
                        this.mSearchView.setEnabled(true);
                        this.mSearchView.clearFocus();
                        return;
                    default:
                        super.onActivityResult(requestCode, resultCode, data);
                        return;
                }
            default:
                return;
        }
    }

    private void showCurrentView() {
        Fragment hideFragment;
        Fragment showFragment;
        Ensighten.evaluateEvent(this, "showCurrentView", null);
        this.mSearchView.clearFocus();
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mSearchView.getWindowToken(), 0);
        if (this.mSelectorCheckedID == 0) {
            hideFragment = this.mMapFragment;
            showFragment = this.mListFragment;
            this.mActiveFragment = ActiveFragment.List;
        } else {
            hideFragment = this.mListFragment;
            showFragment = this.mMapFragment;
            this.mActiveFragment = ActiveFragment.Map;
        }
        getChildFragmentManager().beginTransaction().show(showFragment).hide(hideFragment).hide(this.mSearchFragment).commit();
        if (getActivity().getActionBar() != null) {
            getActivity().getActionBar().show();
        }
        this.mCurrentFragment = showFragment;
    }

    private void searchByname() {
        Ensighten.evaluateEvent(this, "searchByname", null);
        String trimmedString = this.mSearchView.getText().trim();
        AnalyticsUtils.trackSearch(getAnalyticsTitle(), trimmedString);
        if (this.mSearchView.isEmpty()) {
            this.mInteractionListener.requestUpdateStoresByCurrentLocation(true);
            return;
        }
        this.mInteractionListener.requestUpdateStoresByAddress(trimmedString, true);
        if (!trimmedString.isEmpty()) {
            List searchTitleList = LocalDataManager.getSharedInstance().getStringList(LocalDataManager.PREF_LOCATION_SEARCH_TITLE, null);
            if (searchTitleList == null) {
                searchTitleList = Collections.singletonList(trimmedString);
            } else {
                searchTitleList.add(0, this.mSearchView.getCleanText());
            }
            if (searchTitleList.size() > 50) {
                searchTitleList.remove(searchTitleList.size() - 1);
            }
            LocalDataManager.getSharedInstance().set(LocalDataManager.PREF_LOCATION_SEARCH_TITLE, searchTitleList);
        }
    }

    public GeoSuggestionsEditText getSearchView() {
        Ensighten.evaluateEvent(this, "getSearchView", null);
        return this.mSearchView;
    }

    public void checkLocationPermission() {
        Ensighten.evaluateEvent(this, "checkLocationPermission", null);
        if (getNavigationActivity() != null) {
            getNavigationActivity().requestPermission("android.permission.ACCESS_FINE_LOCATION", 0, C2658R.string.permission_explanation_gps, new C37424());
        }
    }
}
