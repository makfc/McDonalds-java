package com.mcdonalds.app.storelocator;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.ordering.start.StoreSelectionController;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.Utils;
import com.mcdonalds.app.storelocator.MapManager.Callback;
import com.mcdonalds.app.storelocator.maps.McdMap;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.app.storelocator.maps.model.MarkerOptions;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.MapUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.StoreCapabilties;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.Calendar;

public class StoreDetailsFragment extends URLNavigationFragment implements Callback {
    public static final String NAME = StoreDetailsFragment.class.getCanonicalName();
    private final int ADD_TO_FAVORITES_REQUEST_CODE = 23;
    private final AsyncListener<Boolean> mAddToFavoritesListener = new C37357();
    private TextView mAddressDetails;
    private TextView mAddressTitle;
    private View mCurrentRestaurantBanner;
    private CustomerModule mCustomerModule;
    private StoreLocatorDataProvider mDataProvider;
    private int mDayOfWeek;
    private View mEatHereButton;
    private TextView mFacilities;
    private Button mFavoritesButton;
    private StoreLocatorInteractionListener mInteractionListener;
    private boolean mIsCurrentStore = false;
    private boolean mIsFavorite = false;
    private PagerItemListener mItemListener;
    private MapManager mMapFragment;
    private TextView mPhoneNumber;
    private final AsyncListener<Boolean> mRemoveStoreListener = new C37368();
    private Button mRenameButton;
    private String mSelectedNickName;
    private Store mSelectedStore = null;
    private Integer mSelectedStoreId = null;
    private StoreLocatorSection mSelectedStoreSection = null;
    private TextView mStoreDistance;
    private TextView mStoreHours;

    /* renamed from: com.mcdonalds.app.storelocator.StoreDetailsFragment$1 */
    class C37291 implements AsyncListener<StoreCapabilties> {
        C37291() {
        }

        public void onResponse(StoreCapabilties capabilties, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{capabilties, token, exception});
            Log.d(Configuration.DEBUG_CONFIG_KEY, "capabilties called");
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreDetailsFragment$2 */
    class C37302 implements OnClickListener {
        C37302() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            StoreDetailsFragment.access$002(StoreDetailsFragment.this, true);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$200", new Object[]{StoreDetailsFragment.this}).setCurrentStore(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$100", new Object[]{StoreDetailsFragment.this}));
            DataLayerManager.getInstance().setStore(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$100", new Object[]{StoreDetailsFragment.this}));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$500", new Object[]{StoreDetailsFragment.this}).eatHereClicked(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$300", new Object[]{StoreDetailsFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$400", new Object[]{StoreDetailsFragment.this}));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$600", new Object[]{StoreDetailsFragment.this}).setVisibility(0);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$700", new Object[]{StoreDetailsFragment.this}).setVisibility(8);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreDetailsFragment$3 */
    class C37313 implements OnClickListener {
        C37313() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(StoreDetailsFragment.this.getAnalyticsTitle(), "Save to favorites");
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$200", new Object[]{StoreDetailsFragment.this}).isLoggedIn() && Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$800", new Object[]{StoreDetailsFragment.this})) {
                UIUtils.startActivityIndicator(StoreDetailsFragment.this.getActivity(), (int) C2658R.string.dialog_remove_favorite_store);
                AnalyticsUtils.trackOnClickEvent(StoreDetailsFragment.this.getAnalyticsTitle(), "Remove from favorites");
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1000", new Object[]{StoreDetailsFragment.this}).removeStoreFromFavorites(Integer.valueOf(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$100", new Object[]{StoreDetailsFragment.this}).getStoreId()), StoreLocatorSection.Favorites, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$900", new Object[]{StoreDetailsFragment.this}));
                return;
            }
            StoreDetailsFragment.access$1100(StoreDetailsFragment.this, true);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreDetailsFragment$4 */
    class C37324 implements OnClickListener {
        C37324() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            StoreDetailsFragment.access$1100(StoreDetailsFragment.this, false);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreDetailsFragment$5 */
    class C37335 implements OnClickListener {
        C37335() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            String uri = "http://maps.google.com/maps?f=d&daddr=" + String.valueOf(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$100", new Object[]{StoreDetailsFragment.this}).getLatitude()) + ", " + String.valueOf(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$100", new Object[]{StoreDetailsFragment.this}).getLongitude()) + "&dirflg=d";
            if (Configuration.getSharedInstance().hasKey("connectors.Middleware.country") && ((String) Configuration.getSharedInstance().getValueForKey("connectors.Middleware.country")).equalsIgnoreCase("CN")) {
                uri = "geo:" + String.valueOf(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$100", new Object[]{StoreDetailsFragment.this}).getLatitude()) + "," + String.valueOf(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$100", new Object[]{StoreDetailsFragment.this}).getLongitude());
            }
            try {
                StoreDetailsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(uri)));
            } catch (ActivityNotFoundException e) {
                try {
                    StoreDetailsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(uri)));
                } catch (ActivityNotFoundException e2) {
                }
            }
            AnalyticsUtils.trackOnClickEvent(StoreDetailsFragment.this.getAnalyticsTitle(), "Get directions");
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreDetailsFragment$6 */
    class C37346 implements OnLongClickListener {
        C37346() {
        }

        public boolean onLongClick(View v) {
            Ensighten.evaluateEvent(this, "onLongClick", new Object[]{v});
            AnalyticsUtils.trackOnClickEvent(StoreDetailsFragment.this.getAnalyticsTitle(), "Call Restaurant");
            Intent dialIntent = new Intent("android.intent.action.DIAL");
            dialIntent.setData(Uri.parse("tel:" + Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1200", new Object[]{StoreDetailsFragment.this}).getText()));
            StoreDetailsFragment.this.startActivity(dialIntent);
            return true;
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreDetailsFragment$7 */
    class C37357 implements AsyncListener<Boolean> {
        C37357() {
        }

        public void onResponse(Boolean success, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{success, token, exception});
            Context context = StoreDetailsFragment.this.getActivity();
            if (context != null) {
                if (success != null && success.booleanValue()) {
                    StoreDetailsFragment.access$802(StoreDetailsFragment.this, true);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1300", new Object[]{StoreDetailsFragment.this}).setText(StoreDetailsFragment.this.getResources().getString(C2658R.string.remove_from_favorites));
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1300", new Object[]{StoreDetailsFragment.this}).setTextAppearance(context, 16973894);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1300", new Object[]{StoreDetailsFragment.this}).setTextColor(StoreDetailsFragment.this.getResources().getColor(17170443));
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1400", new Object[]{StoreDetailsFragment.this}).setVisibility(0);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$100", new Object[]{StoreDetailsFragment.this}).setStoreFavoriteName(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1500", new Object[]{StoreDetailsFragment.this}));
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1600", new Object[]{StoreDetailsFragment.this}).setText(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1500", new Object[]{StoreDetailsFragment.this}));
                    StoreDetailsFragment.access$402(StoreDetailsFragment.this, StoreLocatorSection.Favorites);
                }
                UIUtils.stopActivityIndicator();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreDetailsFragment$8 */
    class C37368 implements AsyncListener<Boolean> {
        C37368() {
        }

        public void onResponse(Boolean success, AsyncToken token, AsyncException exception) {
            boolean z = true;
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{success, token, exception});
            if (StoreDetailsFragment.this.getNavigationActivity() != null) {
                if (success != null && success.booleanValue()) {
                    StoreDetailsFragment storeDetailsFragment = StoreDetailsFragment.this;
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$800", new Object[]{StoreDetailsFragment.this})) {
                        z = false;
                    }
                    StoreDetailsFragment.access$802(storeDetailsFragment, z);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1300", new Object[]{StoreDetailsFragment.this}).setText(StoreDetailsFragment.this.getResources().getString(C2658R.string.favorites_btn_txt));
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1300", new Object[]{StoreDetailsFragment.this}).setTextAppearance(StoreDetailsFragment.this.getNavigationActivity(), 16973892);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1300", new Object[]{StoreDetailsFragment.this}).setTextColor(StoreDetailsFragment.this.getNavigationActivity().getResources().getColor(17170443));
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1400", new Object[]{StoreDetailsFragment.this}).setVisibility(8);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$100", new Object[]{StoreDetailsFragment.this}).setStoreFavoriteName(null);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1600", new Object[]{StoreDetailsFragment.this}).setText(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$100", new Object[]{StoreDetailsFragment.this}).getStoreName());
                    StoreDetailsFragment.access$402(StoreDetailsFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$000", new Object[]{StoreDetailsFragment.this}) ? StoreLocatorSection.Current : StoreLocatorSection.Nearby);
                }
                UIUtils.stopActivityIndicator();
            }
        }
    }

    static /* synthetic */ boolean access$002(StoreDetailsFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.mIsCurrentStore = x1;
        return x1;
    }

    static /* synthetic */ void access$1100(StoreDetailsFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$1100", new Object[]{x0, new Boolean(x1)});
        x0.goToRenameStoreFragment(x1);
    }

    static /* synthetic */ StoreLocatorSection access$402(StoreDetailsFragment x0, StoreLocatorSection x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$402", new Object[]{x0, x1});
        x0.mSelectedStoreSection = x1;
        return x1;
    }

    static /* synthetic */ boolean access$802(StoreDetailsFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreDetailsFragment", "access$802", new Object[]{x0, new Boolean(x1)});
        x0.mIsFavorite = x1;
        return x1;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_restaurant_locator_store);
    }

    public void onResume() {
        super.onResume();
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        if (orderingModule != null) {
            orderingModule.getStoreOrderingCapabilties(this.mSelectedStore, new C37291());
        }
    }

    public void onStart() {
        super.onStart();
        checkForPendingAddToFavorites();
    }

    private void checkForPendingAddToFavorites() {
        Ensighten.evaluateEvent(this, "checkForPendingAddToFavorites", null);
        Bundle arguments = getArguments();
        if (getArguments() != null && arguments.getBoolean("EXTRA_SAVING_FAVORITE", false)) {
            addStoreToFavorites();
            arguments.remove("EXTRA_SAVING_FAVORITE");
            arguments.remove("StoreLocatorFragment.SAVING_FAVORITE_ID");
            arguments.remove("saving_fav_section");
            arguments.remove("saving_fav_detail");
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        StoreLocatorController storeLocatorController;
        Object storeLocatorController2;
        super.onCreate(savedInstanceState);
        StoreLocatorController storeLocatorController3 = StoresManager.getInstance().getController();
        StoreSelectionController controller = new StoreSelectionController(getContext());
        Store store = (Store) getArguments().getParcelable("extra_store_detail");
        int section = getArguments().getInt("extra_store_section", -1);
        boolean isLocatorController = getArguments().getBoolean("extra_store_controller", false);
        if (section == StoreLocatorSection.Nearby.ordinal()) {
            ArrayList<Store> stores = new ArrayList();
            stores.add(store);
            controller.setNearbyStores(stores);
        }
        this.mDayOfWeek = Calendar.getInstance().get(7);
        if (isLocatorController) {
            storeLocatorController2 = storeLocatorController3;
        } else {
            storeLocatorController2 = controller;
        }
        this.mDataProvider = storeLocatorController2;
        if (isLocatorController) {
            storeLocatorController2 = storeLocatorController3;
        } else {
            storeLocatorController2 = controller;
        }
        this.mInteractionListener = storeLocatorController2;
        this.mItemListener = (PagerItemListener) this.mInteractionListener;
        if (store != null) {
            this.mSelectedStore = store;
            this.mSelectedStoreId = Integer.valueOf(store.getStoreId());
            if (section != -1) {
                this.mSelectedStoreSection = StoreLocatorSection.values()[section];
            }
        }
        boolean z = (this.mDataProvider == null || this.mDataProvider.getCurrentStore() == null || this.mSelectedStore == null || this.mSelectedStore.getStoreId() != this.mDataProvider.getCurrentStore().getStoreId()) ? false : true;
        this.mIsCurrentStore = z;
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (this.mSelectedStore != null) {
            Analytics.trackCustom(19, this.mSelectedStore.getAddress1());
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_store_details, container, false);
        this.mCurrentRestaurantBanner = rootView.findViewById(C2358R.C2357id.banner_current_restaurant);
        this.mAddressTitle = (TextView) rootView.findViewById(C2358R.C2357id.label_large_address);
        this.mAddressDetails = (TextView) rootView.findViewById(C2358R.C2357id.label_address_details);
        this.mPhoneNumber = (TextView) rootView.findViewById(C2358R.C2357id.label_phone_number);
        this.mStoreHours = (TextView) rootView.findViewById(C2358R.C2357id.label_store_hours);
        this.mStoreDistance = (TextView) rootView.findViewById(C2358R.C2357id.label_store_distance);
        this.mFacilities = (TextView) rootView.findViewById(C2358R.C2357id.label_facility_item);
        Button mDirectionsButton = (Button) rootView.findViewById(C2358R.C2357id.button_directions);
        this.mEatHereButton = rootView.findViewById(C2358R.C2357id.button_eat_here);
        this.mRenameButton = (Button) rootView.findViewById(C2358R.C2357id.button_rename);
        this.mFavoritesButton = (Button) rootView.findViewById(C2358R.C2357id.button_one);
        if (!this.mSelectedStore.canBeFavorited() || this.mDataProvider.isCurrentStoreSelectionMode()) {
            this.mFavoritesButton.setVisibility(8);
        } else {
            this.mFavoritesButton.setVisibility(0);
        }
        this.mMapFragment = new MapManager(getActivity(), this);
        getChildFragmentManager().beginTransaction().add((int) C2358R.C2357id.map_container, this.mMapFragment.getFragment()).addToBackStack(null).commit();
        detailLogic();
        if (!this.mIsCurrentStore) {
            this.mCurrentRestaurantBanner.setVisibility(8);
        }
        if (this.mSelectedStore.hasMobileOrdering() != null) {
            if (this.mIsCurrentStore || !(this.mSelectedStore.hasMobileOrdering().booleanValue() || this.mDataProvider.isCurrentStoreSelectionMode())) {
                this.mEatHereButton.setVisibility(8);
            } else {
                this.mEatHereButton.setVisibility(0);
            }
        }
        this.mEatHereButton.setOnClickListener(new C37302());
        this.mEatHereButton.setVisibility(this.mIsCurrentStore ? 8 : 0);
        if (!(ListUtils.isEmpty(this.mSelectedStore.getStoreOperatingHours()) || TextUtils.isEmpty(Utils.getCloseStatus(this.mSelectedStore, getActivity())))) {
            this.mEatHereButton.setVisibility(8);
        }
        if (this.mSelectedStore.getStoreFavoriteId() != null) {
            this.mIsFavorite = true;
            this.mFavoritesButton.setText(getResources().getString(C2658R.string.remove_from_favorites));
            this.mFavoritesButton.setTextAppearance(getNavigationActivity(), 16973894);
            this.mFavoritesButton.setTextColor(ContextCompat.getColor(getNavigationActivity(), 17170443));
        } else {
            this.mRenameButton.setVisibility(8);
        }
        this.mFavoritesButton.setOnClickListener(new C37313());
        this.mRenameButton.setOnClickListener(new C37324());
        mDirectionsButton.setOnClickListener(new C37335());
        this.mPhoneNumber.setLongClickable(true);
        this.mPhoneNumber.setOnLongClickListener(new C37346());
        return rootView;
    }

    private void goToRenameStoreFragment(boolean addToFavorites) {
        Ensighten.evaluateEvent(this, "goToRenameStoreFragment", new Object[]{new Boolean(addToFavorites)});
        int requestCode = 22;
        if (addToFavorites) {
            requestCode = 23;
        } else {
            AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Rename this restaurant");
        }
        startStoreNicknamingActivity(requestCode);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 23 || requestCode == 22) && resultCode == -1) {
            this.mSelectedNickName = data.getStringExtra("name");
            if (!TextUtils.isEmpty(this.mSelectedNickName)) {
                if (this.mCustomerModule.isLoggedIn()) {
                    Store store = (Store) getArguments().getParcelable("extra_store_detail");
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("name", this.mSelectedNickName);
                    resultIntent.putExtra("EXTRA_STORE_ID", store.getStoreId());
                    getActivity().setResult(0, resultIntent);
                    addStoreToFavorites();
                    return;
                }
                Bundle arguments = new Bundle();
                arguments.putBoolean("EXTRA_SAVING_FAVORITE", true);
                arguments.putBoolean("saving_fav_detail", true);
                arguments.putInt("StoreLocatorFragment.SAVING_FAVORITE_ID", this.mSelectedStore.getStoreId());
                arguments.putSerializable("saving_fav_section", this.mSelectedStoreSection);
                startSignInActivity(arguments);
            }
        }
    }

    private void startStoreNicknamingActivity(int requestCode) {
        Ensighten.evaluateEvent(this, "startStoreNicknamingActivity", new Object[]{new Integer(requestCode)});
        startActivityForResult(StoreNicknamingActivity.class, StoreNicknamingFragment.NAME, requestCode);
    }

    private void startSignInActivity(Bundle arguments) {
        Ensighten.evaluateEvent(this, "startSignInActivity", new Object[]{arguments});
        startActivity(SignInActivity.class, JiceArgs.EVENT_CHECK_IN, arguments);
    }

    private void addStoreToFavorites() {
        Ensighten.evaluateEvent(this, "addStoreToFavorites", null);
        if (this.mSelectedStore.getStoreFavoriteName() != null) {
            UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.dialog_renaming_favorite_store);
            this.mInteractionListener.renameStoreInFavorites(Integer.valueOf(this.mSelectedStore.getStoreId()), this.mSelectedStoreSection, this.mSelectedNickName, this.mAddToFavoritesListener);
            return;
        }
        UIUtils.startActivityIndicator(getActivity(), (int) C2658R.string.dialog_adding_favorite_store);
        this.mInteractionListener.addStoreToFavorites(Integer.valueOf(this.mSelectedStore.getStoreId()), this.mSelectedStoreSection, this.mSelectedNickName, this.mAddToFavoritesListener);
    }

    public void onMapError(Dialog dialog) {
        Ensighten.evaluateEvent(this, "onMapError", new Object[]{dialog});
        if (dialog != null && getNavigationActivity() != null) {
            dialog.show();
        }
    }

    public void onMapAvailable() {
        Ensighten.evaluateEvent(this, "onMapAvailable", null);
        if (this.mMapFragment != null) {
            McdMap map = this.mMapFragment.getMap();
            if (map != null) {
                map.configure();
                if (this.mSelectedStore != null) {
                    map.addMarker(new MarkerOptions().position(new LatLng(this.mSelectedStore.getLatitude(), this.mSelectedStore.getLongitude())).snippet(Integer.toString(this.mSelectedStore.getStoreId())).icon(C2358R.C2359drawable.map_pin_red));
                    MapUtils.with(getActivity()).map(map).store(this.mSelectedStore).move(13.0f);
                }
            }
        }
    }

    private void detailLogic() {
        Ensighten.evaluateEvent(this, "detailLogic", null);
        if (TextUtils.isEmpty(this.mSelectedStore.getStoreFavoriteName())) {
            getNavigationActivity().setTitle(this.mSelectedStore.getPublicName());
        } else {
            getNavigationActivity().setTitle(this.mSelectedStore.getStoreFavoriteName());
        }
        this.mAddressTitle.setText(this.mSelectedStore.getAddress1());
        String addrDetails = "";
        if (this.mSelectedStore.getAddress2() != null) {
            addrDetails = addrDetails + this.mSelectedStore.getAddress2() + ", ";
        }
        if (this.mSelectedStore.getCity() != null) {
            addrDetails = addrDetails + this.mSelectedStore.getCity() + "\n";
        }
        if (this.mSelectedStore.getState() != null) {
            addrDetails = addrDetails + this.mSelectedStore.getState() + ", ";
        }
        if (this.mSelectedStore.getPostalCode() != null) {
            addrDetails = addrDetails + this.mSelectedStore.getPostalCode();
        }
        if (addrDetails.isEmpty()) {
            this.mAddressDetails.setVisibility(8);
        } else {
            this.mAddressDetails.setText(addrDetails);
        }
        if (this.mSelectedStore.getPhoneNumber() != null) {
            Spannable word = new SpannableString(this.mSelectedStore.getPhoneNumber().replace(" ", ""));
            word.setSpan(new ForegroundColorSpan(-16776961), 0, word.length(), 33);
            this.mPhoneNumber.setText(word);
        } else {
            this.mPhoneNumber.setVisibility(8);
        }
        String storeHoursString = UIUtils.getDailyStoreHoursString(getContext(), this.mSelectedStore);
        if (storeHoursString != null) {
            this.mStoreHours.setText(storeHoursString);
        } else {
            this.mStoreHours.setVisibility(8);
        }
        this.mStoreDistance.setText(UIUtils.distanceFromStore(getActivity(), this.mSelectedStore));
        boolean firstPass = true;
        StringBuilder sb = new StringBuilder();
        if (this.mSelectedStore.getFacilityNames() != null && this.mSelectedStore.getFacilityNames().size() > 0) {
            for (String facilityName : this.mSelectedStore.getFacilityNames()) {
                if (!firstPass) {
                    sb.append("\n");
                }
                sb.append(facilityName);
                firstPass = false;
            }
        }
        if (sb.length() > 0) {
            this.mFacilities.setText(sb.toString());
        }
    }
}
