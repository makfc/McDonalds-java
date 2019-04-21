package com.mcdonalds.app.storelocator;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLayoutChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import com.amap.api.location.LocationManagerProxy;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.p043ui.animation.AnimatorEndListener;
import com.mcdonalds.app.storelocator.MapManager.Callback;
import com.mcdonalds.app.storelocator.SimpleLocationListener.LocationListener;
import com.mcdonalds.app.storelocator.maps.McdMap;
import com.mcdonalds.app.storelocator.maps.McdMap.OnCameraChangeListener;
import com.mcdonalds.app.storelocator.maps.McdMap.OnMapClickListener;
import com.mcdonalds.app.storelocator.maps.McdMap.OnMarkerClickListener;
import com.mcdonalds.app.storelocator.maps.model.CameraPosition;
import com.mcdonalds.app.storelocator.maps.model.LatLng;
import com.mcdonalds.app.storelocator.maps.model.Marker;
import com.mcdonalds.app.storelocator.maps.model.MarkerOptions;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.LocationUtils;
import com.mcdonalds.app.util.MapUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Instrumented
public class StoreLocatorMapFragment extends Fragment implements OnClickListener, OnTouchListener, Callback, LocationListener, StoreLocationListener, TraceFieldInterface {
    private static final String LOG_TAG = StoreLocatorMapFragment.class.getSimpleName();
    public Trace _nr_trace;
    private boolean mClosestStoreRefreshed;
    private ImageButton mContractSymbol;
    private StoreLocatorController mController;
    private StoreLocatorDataProvider mDataProvider;
    private boolean mIgnoreViewPagerChange;
    private CameraPosition mInitialCameraPosition;
    private StoreLocatorInteractionListener mInteractionListener;
    private boolean mIsFirstLoad;
    private boolean mIsOffersMode;
    private Button mLocationButton;
    private McdMap mMap;
    private MapManager mMapFragment;
    private boolean mMapPanBegan;
    private ViewGroup mMapRootViewGroup;
    private final Map<String, Store> mMarkerStoresMap = new HashMap();
    private final AnimatorEndListener mNewLocationFadeInEndListener = new C37679();
    private final OnCameraChangeListener mOnCameraChangeListener = new C37668();
    private final OnMapClickListener mOnMapClickListener = new C37646();
    private final OnMarkerClickListener mOnMarkerClickListener = new C37657();
    private boolean mPagerAnimating;
    private boolean mPagerAnimatingNonFavorite;
    private View mPagerClickCatcher;
    private ViewGroup mPagerContainer;
    private ViewGroup mPagerContainerNonFavorite;
    protected StoreLocatorPagerFragment mPagerFragmentNonFavorite;
    private float mPagerHeightNonFavorite;
    private float mPagerY;
    private LatLng mPriorLatLng;
    private Button mSearchHereButton;
    private boolean mShowingNonPager;
    private boolean mSkipZoom;
    private List<Store> mSortedStores;
    private final SparseArray<Marker> mStoreMarkersMap = new SparseArray();
    private ViewPager mViewPager;
    private StoreLocatorPagerAdapter mViewPagerAdapter;

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorMapFragment$10 */
    class C375410 implements Comparator<Store> {
        C375410() {
        }

        public int compare(Store store, Store store2) {
            Ensighten.evaluateEvent(this, "compare", new Object[]{store, store2});
            double d1 = store.getDistance();
            double d2 = store2.getDistance();
            if (d1 == d2) {
                return 0;
            }
            if (d1 < d2) {
                return -1;
            }
            return 1;
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorMapFragment$1 */
    class C37591 implements OnClickListener {
        C37591() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$000", new Object[]{StoreLocatorMapFragment.this}).setVisibility(8);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$100", new Object[]{StoreLocatorMapFragment.this}).isMapOnly()) {
                StoreLocatorMapFragment.access$200(StoreLocatorMapFragment.this);
                return;
            }
            boolean expand = false;
            StoreLocatorPagerFragment fragment = Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$400", new Object[]{StoreLocatorMapFragment.this}).getItem(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$300", new Object[]{StoreLocatorMapFragment.this}).getCurrentItem());
            if (fragment != null) {
                expand = fragment.isExpanded();
            }
            StoreLocatorMapFragment.access$500(StoreLocatorMapFragment.this, expand);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorMapFragment$2 */
    class C37602 implements OnLayoutChangeListener {
        C37602() {
        }

        public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            Ensighten.evaluateEvent(this, "onLayoutChange", new Object[]{view, new Integer(left), new Integer(top), new Integer(right), new Integer(bottom), new Integer(oldLeft), new Integer(oldTop), new Integer(oldRight), new Integer(oldBottom)});
            View parent = (View) Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$700", new Object[]{StoreLocatorMapFragment.this}).getParent();
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$700", new Object[]{StoreLocatorMapFragment.this}).removeOnLayoutChangeListener(this);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$700", new Object[]{StoreLocatorMapFragment.this}).setY((float) (parent.getBottom() - UIUtils.dpAsPixels(StoreLocatorMapFragment.this.getActivity(), 180)));
            StoreLocatorMapFragment.access$802(StoreLocatorMapFragment.this, 180.0f);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorMapFragment$3 */
    class C37613 implements OnLayoutChangeListener {
        C37613() {
        }

        public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            Ensighten.evaluateEvent(this, "onLayoutChange", new Object[]{view, new Integer(left), new Integer(top), new Integer(right), new Integer(bottom), new Integer(oldLeft), new Integer(oldTop), new Integer(oldRight), new Integer(oldBottom)});
            View parent = (View) Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$900", new Object[]{StoreLocatorMapFragment.this}).getParent();
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$900", new Object[]{StoreLocatorMapFragment.this}).removeOnLayoutChangeListener(this);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$900", new Object[]{StoreLocatorMapFragment.this}).setY((float) parent.getBottom());
            StoreLocatorMapFragment.access$1002(StoreLocatorMapFragment.this, 0.0f);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorMapFragment$4 */
    class C37624 extends AnimatorEndListener {
        C37624() {
        }

        public void onAnimationEnd(Animator animation) {
            Ensighten.evaluateEvent(this, "onAnimationEnd", new Object[]{animation});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1100", new Object[]{StoreLocatorMapFragment.this}).setVisibility(8);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorMapFragment$5 */
    class C37635 implements DialogInterface.OnClickListener {
        C37635() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorMapFragment$6 */
    class C37646 implements OnMapClickListener {
        C37646() {
        }

        public void onMapClick(LatLng point) {
            Ensighten.evaluateEvent(this, "onMapClick", new Object[]{point});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$100", new Object[]{StoreLocatorMapFragment.this}).isMapOnly()) {
                StoreLocatorMapFragment.access$1200(StoreLocatorMapFragment.this);
                return;
            }
            StoreLocatorMapFragment.access$1300(StoreLocatorMapFragment.this);
            StoreLocatorMapFragment.access$1400(StoreLocatorMapFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorMapFragment$7 */
    class C37657 implements OnMarkerClickListener {
        C37657() {
        }

        public boolean onMarkerClick(Marker marker) {
            Ensighten.evaluateEvent(this, "onMarkerClick", new Object[]{marker});
            Store store = (Store) Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1500", new Object[]{StoreLocatorMapFragment.this}).get(marker.getId());
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1700", new Object[]{StoreLocatorMapFragment.this}).selectStore(Integer.valueOf(store.getStoreId()), Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1600", new Object[]{StoreLocatorMapFragment.this, store}));
            StoreLocatorMapFragment.access$1802(StoreLocatorMapFragment.this, false);
            return false;
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorMapFragment$8 */
    class C37668 implements OnCameraChangeListener {
        C37668() {
        }

        public void onCameraChange(CameraPosition position) {
            Ensighten.evaluateEvent(this, "onCameraChange", new Object[]{position});
            if (((double) Math.abs(position.zoom - Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1900", new Object[]{StoreLocatorMapFragment.this}).getCurrentZoom())) < 0.1d && Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2000", new Object[]{StoreLocatorMapFragment.this}) != null && Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1800", new Object[]{StoreLocatorMapFragment.this})) {
                if (LocationUtils.distanceBetween(position.target, Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2000", new Object[]{StoreLocatorMapFragment.this})) > 5.0d) {
                    StoreLocatorMapFragment.access$2100(StoreLocatorMapFragment.this);
                }
                StoreLocatorMapFragment.access$2202(StoreLocatorMapFragment.this, null);
            }
            StoreLocatorMapFragment.access$2002(StoreLocatorMapFragment.this, position.target);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1900", new Object[]{StoreLocatorMapFragment.this}).setCurrentZoom(position.zoom);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorMapFragment$9 */
    class C37679 extends AnimatorEndListener {
        C37679() {
        }

        public void onAnimationEnd(Animator animation) {
            Ensighten.evaluateEvent(this, "onAnimationEnd", new Object[]{animation});
            StoreLocatorMapFragment.access$1802(StoreLocatorMapFragment.this, false);
        }
    }

    private class PageChangeListener implements OnPageChangeListener {
        private PageChangeListener() {
        }

        /* synthetic */ PageChangeListener(StoreLocatorMapFragment x0, C37591 x1) {
            this();
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Ensighten.evaluateEvent(this, "onPageScrolled", new Object[]{new Integer(position), new Float(positionOffset), new Integer(positionOffsetPixels)});
        }

        public void onPageSelected(int position) {
            Ensighten.evaluateEvent(this, "onPageSelected", new Object[]{new Integer(position)});
            AnalyticsUtils.trackOnSlideEvent("/restaurant-locator", "Store");
            Store store = Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$400", new Object[]{StoreLocatorMapFragment.this}).getStore(position);
            StoreLocatorSection section = Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2300", new Object[]{StoreLocatorMapFragment.this, store}) ? StoreLocatorSection.Current : StoreLocatorSection.Favorites;
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$400", new Object[]{StoreLocatorMapFragment.this}).clearStoresState(position);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2400", new Object[]{StoreLocatorMapFragment.this})) {
                StoreLocatorMapFragment.access$2402(StoreLocatorMapFragment.this, false);
            } else if (store != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1700", new Object[]{StoreLocatorMapFragment.this}).selectStore(Integer.valueOf(store.getStoreId()), section);
            }
        }

        public void onPageScrollStateChanged(int state) {
            Ensighten.evaluateEvent(this, "onPageScrollStateChanged", new Object[]{new Integer(state)});
            if (state == 1 && Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$300", new Object[]{StoreLocatorMapFragment.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$400", new Object[]{StoreLocatorMapFragment.this}).getItem(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$300", new Object[]{StoreLocatorMapFragment.this}).getCurrentItem()).reset();
            }
        }
    }

    public void onAttach(Context context) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{context});
        super.onAttach(context);
    }

    public void onDetach() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDetach", null);
        super.onDetach();
    }

    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        Ensighten.processView((Object) this, "onResume");
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

    static /* synthetic */ float access$1002(StoreLocatorMapFragment x0, float x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1002", new Object[]{x0, new Float(x1)});
        x0.mPagerHeightNonFavorite = x1;
        return x1;
    }

    static /* synthetic */ void access$1200(StoreLocatorMapFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1200", new Object[]{x0});
        x0.toggleNonPager();
    }

    static /* synthetic */ void access$1300(StoreLocatorMapFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1300", new Object[]{x0});
        x0.hideNonFavoritePager();
    }

    static /* synthetic */ void access$1400(StoreLocatorMapFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1400", new Object[]{x0});
        x0.hidePager();
    }

    static /* synthetic */ boolean access$1802(StoreLocatorMapFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$1802", new Object[]{x0, new Boolean(x1)});
        x0.mMapPanBegan = x1;
        return x1;
    }

    static /* synthetic */ void access$200(StoreLocatorMapFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$200", new Object[]{x0});
        x0.showNonFavorite();
    }

    static /* synthetic */ LatLng access$2002(StoreLocatorMapFragment x0, LatLng x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2002", new Object[]{x0, x1});
        x0.mPriorLatLng = x1;
        return x1;
    }

    static /* synthetic */ void access$2100(StoreLocatorMapFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2100", new Object[]{x0});
        x0.showSearchHereButton();
    }

    static /* synthetic */ CameraPosition access$2202(StoreLocatorMapFragment x0, CameraPosition x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2202", new Object[]{x0, x1});
        x0.mInitialCameraPosition = x1;
        return x1;
    }

    static /* synthetic */ boolean access$2402(StoreLocatorMapFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2402", new Object[]{x0, new Boolean(x1)});
        x0.mIgnoreViewPagerChange = x1;
        return x1;
    }

    static /* synthetic */ boolean access$2502(StoreLocatorMapFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2502", new Object[]{x0, new Boolean(x1)});
        x0.mPagerAnimating = x1;
        return x1;
    }

    static /* synthetic */ boolean access$2702(StoreLocatorMapFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2702", new Object[]{x0, new Boolean(x1)});
        x0.mPagerAnimatingNonFavorite = x1;
        return x1;
    }

    static /* synthetic */ void access$500(StoreLocatorMapFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$500", new Object[]{x0, new Boolean(x1)});
        x0.showPager(x1);
    }

    static /* synthetic */ float access$802(StoreLocatorMapFragment x0, float x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$802", new Object[]{x0, new Float(x1)});
        x0.mPagerY = x1;
        return x1;
    }

    public void onHiddenChanged(boolean hidden) {
        Ensighten.evaluateEvent(this, "onHiddenChanged", new Object[]{new Boolean(hidden)});
        super.onHiddenChanged(hidden);
    }

    public void setController(StoreLocatorController controller) {
        Ensighten.evaluateEvent(this, "setController", new Object[]{controller});
        if (controller != null) {
            this.mDataProvider = controller;
            this.mInteractionListener = controller;
            this.mController = controller;
            this.mController.addListener(this);
        }
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("StoreLocatorMapFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "StoreLocatorMapFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "StoreLocatorMapFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        if (this.mController == null) {
            setController(((StoreLocatorContainerFragment) getParentFragment()).getController());
        }
        this.mIsOffersMode = this.mController instanceof OffersStoreLocatorController;
        this.mPagerFragmentNonFavorite = new StoreLocatorPagerFragment();
        this.mPagerFragmentNonFavorite.setDataProvider(this.mController);
        this.mPagerFragmentNonFavorite.setListener(this.mController);
        this.mPagerFragmentNonFavorite.setSection(StoreLocatorSection.Nearby);
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "StoreLocatorMapFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "StoreLocatorMapFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        ViewGroup mRootViewGroup = (ViewGroup) layoutInflater.inflate(C2658R.layout.fragment_storelocator_map, viewGroup, false);
        this.mPagerClickCatcher = mRootViewGroup.findViewById(C2358R.C2357id.clickCatcher);
        this.mPagerClickCatcher.setOnClickListener(new C37591());
        this.mPagerContainer = (ViewGroup) mRootViewGroup.findViewById(C2358R.C2357id.pager_container);
        this.mPagerContainerNonFavorite = (ViewGroup) mRootViewGroup.findViewById(C2358R.C2357id.pager_container_non_favorite);
        this.mViewPager = (ViewPager) mRootViewGroup.findViewById(C2358R.C2357id.stores_pager);
        this.mViewPager.setPageMargin(10);
        this.mViewPager.setClipToPadding(false);
        this.mViewPagerAdapter = new StoreLocatorPagerAdapter(getActivity(), getChildFragmentManager(), this.mController, this.mController, this.mController);
        PageChangeListener mOnPageChangeListener = new PageChangeListener(this, null);
        this.mViewPager.setAdapter(this.mViewPagerAdapter);
        this.mViewPager.setOnPageChangeListener(mOnPageChangeListener);
        this.mPagerContainer.addOnLayoutChangeListener(new C37602());
        this.mLocationButton = (Button) mRootViewGroup.findViewById(C2358R.C2357id.location_button);
        this.mLocationButton.setOnClickListener(this);
        DataLayerClickListener.setDataLayerTag(this.mLocationButton, "LocationButtonPressed");
        this.mSearchHereButton = (Button) mRootViewGroup.findViewById(C2358R.C2357id.track_new_location);
        this.mSearchHereButton.setOnClickListener(this);
        this.mSearchHereButton.setVisibility(8);
        DataLayerClickListener.setDataLayerTag(this.mSearchHereButton, "FetchStoresInMapPressed");
        if (this.mIsOffersMode) {
            this.mContractSymbol = (ImageButton) mRootViewGroup.findViewById(C2358R.C2357id.return_to_offer);
            this.mContractSymbol.setVisibility(0);
            this.mContractSymbol.setOnClickListener(this);
            LayoutParams params = (LayoutParams) this.mLocationButton.getLayoutParams();
            params.gravity = GravityCompat.START;
            params.leftMargin = 10;
            this.mLocationButton.setLayoutParams(params);
            this.mPagerContainer.setVisibility(4);
        }
        if (this.mController.isCurrentStoreSelectionMode()) {
            this.mPagerContainer.setVisibility(8);
        }
        if (getChildFragmentManager().findFragmentByTag("PAGER_FRAGMENT") == null) {
            getChildFragmentManager().beginTransaction().add(this.mPagerContainerNonFavorite.getId(), this.mPagerFragmentNonFavorite, "PAGER_FRAGMENT").commit();
        }
        this.mPagerContainerNonFavorite.addOnLayoutChangeListener(new C37613());
        mRootViewGroup.findViewById(C2358R.C2357id.map_overlay).setOnTouchListener(this);
        TraceMachine.exitMethod();
        return mRootViewGroup;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{savedInstanceState});
        super.onActivityCreated(savedInstanceState);
        this.mMapFragment = new MapManager(getActivity(), this);
        getChildFragmentManager().beginTransaction().replace(C2358R.C2357id.map_container, this.mMapFragment.getFragment()).commit();
        this.mMapFragment.setCallback(this);
        if (this.mMap != null) {
            this.mMap.setOnCameraChangeListener(this.mOnCameraChangeListener);
        }
    }

    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        if (this.mMap != null) {
            this.mMap.onPause();
        }
        Ensighten.processView((Object) this, "onPause");
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
        this.mMapRootViewGroup = (ViewGroup) this.mMapFragment.getFragment().getView();
    }

    public void onDestroyView() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroyView", null);
        super.onDestroyView();
        if (this.mMapRootViewGroup != null && this.mMapRootViewGroup.getChildCount() > 0) {
            this.mMapRootViewGroup.removeAllViews();
        }
    }

    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    public void onMapError(Dialog dialog) {
        Ensighten.evaluateEvent(this, "onMapError", new Object[]{dialog});
        if (dialog != null && getActivity() != null) {
            dialog.show();
        }
    }

    public void onMapAvailable() {
        Ensighten.evaluateEvent(this, "onMapAvailable", null);
        this.mMap = this.mMapFragment.getMap();
        if (this.mMap != null) {
            configMap();
            refresh();
        }
    }

    public void setInitialCameraPosition(CameraPosition initialCameraPosition) {
        Ensighten.evaluateEvent(this, "setInitialCameraPosition", new Object[]{initialCameraPosition});
        this.mInitialCameraPosition = initialCameraPosition;
    }

    @SuppressLint({"MissingPermission"})
    private void configMap() {
        Ensighten.evaluateEvent(this, "configMap", null);
        this.mMap.setInfoWindowAdapter(new MapWindowAdapter(getActivity()));
        this.mMapFragment.setCallback(null);
        initMapListeners();
        this.mMap.configure();
        if (this.mController instanceof OffersStoreLocatorController) {
            this.mMap.setMyLocationEnabled(LocationServicesManager.isLocationEnabled(getContext()));
        }
        if (this.mDataProvider.locationServicesEnabled()) {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(1);
            ((LocationManager) getActivity().getSystemService(LocationManagerProxy.KEY_LOCATION_CHANGED)).requestSingleUpdate(criteria, new SimpleLocationListener(this, 1), null);
        }
    }

    private void initMapListeners() {
        Ensighten.evaluateEvent(this, "initMapListeners", null);
        this.mMap.setOnMapClickListener(this.mOnMapClickListener);
        this.mMap.setOnMarkerClickListener(this.mOnMarkerClickListener);
        this.mMap.setOnCameraChangeListener(this.mOnCameraChangeListener);
    }

    private void refresh() {
        Ensighten.evaluateEvent(this, "refresh", null);
        if (getActivity() != null && this.mMap != null) {
            if (this.mViewPagerAdapter != null) {
                refreshAdapterStores();
            }
            boolean lookingForClosestStore = this.mController.isAutoSelectClosestStore();
            if (!(lookingForClosestStore && this.mClosestStoreRefreshed) && lookingForClosestStore) {
                this.mClosestStoreRefreshed = true;
                return;
            }
            refreshMarkers();
            if (this.mSkipZoom) {
                this.mSkipZoom = false;
                this.mSearchHereButton.setVisibility(8);
                return;
            }
            updateMapZoom();
        }
    }

    public void clearZoomFlag() {
        Ensighten.evaluateEvent(this, "clearZoomFlag", null);
        this.mSkipZoom = false;
    }

    private void refreshAdapterStores() {
        boolean includesCurrent;
        Ensighten.evaluateEvent(this, "refreshAdapterStores", null);
        List<Store> stores = new ArrayList();
        if (this.mDataProvider.getCurrentStore() != null) {
            includesCurrent = true;
        } else {
            includesCurrent = false;
        }
        if (includesCurrent) {
            stores.add(this.mDataProvider.getCurrentStore());
            stores.addAll(filterCurrentLocation(this.mDataProvider));
        } else if (this.mDataProvider.getFavoriteStores() != null) {
            stores.addAll(this.mDataProvider.getFavoriteStores());
        }
        if (ListUtils.isNotEmpty(stores)) {
            ((StoreLocatorContainerFragment) getParentFragment()).mMobileOrderFilterLayout.setVisibility(0);
        } else {
            ((StoreLocatorContainerFragment) getParentFragment()).mMobileOrderFilterLayout.setVisibility(8);
        }
        this.mViewPagerAdapter.setIncludesCurrent(includesCurrent);
        this.mViewPagerAdapter.setStores(stores);
        this.mViewPagerAdapter.notifyDataSetChanged();
    }

    private List<Store> filterCurrentLocation(StoreLocatorDataProvider provider) {
        Ensighten.evaluateEvent(this, "filterCurrentLocation", new Object[]{provider});
        List<Store> favorites = new ArrayList();
        if (provider.getFavoriteStores() != null) {
            favorites.addAll(provider.getFavoriteStores());
            int currentId = provider.getCurrentStore().getStoreId();
            Iterator<Store> iterator = favorites.iterator();
            while (iterator.hasNext()) {
                if (((Store) iterator.next()).getStoreId() == currentId) {
                    iterator.remove();
                }
            }
        }
        return favorites;
    }

    public void onChange(StoreLocatorDataProvider provider) {
        Ensighten.evaluateEvent(this, "onChange", new Object[]{provider});
        refresh();
    }

    public void onSelectedStoreChange(StoreLocatorDataProvider provider, String previousSelectionId, StoreLocatorSection previousSection, boolean shouldExpand) {
        Integer num;
        boolean isCurrent = true;
        Ensighten.evaluateEvent(this, "onSelectedStoreChange", new Object[]{provider, previousSelectionId, previousSection, new Boolean(shouldExpand)});
        if (previousSelectionId == null) {
            num = null;
        } else {
            num = Integer.valueOf(Integer.parseInt(previousSelectionId));
        }
        updateMapSelection(num, this.mDataProvider.getSelectedStore());
        Store selected = this.mDataProvider.getSelectedStore();
        StoreLocatorPagerFragment fragment;
        if (selected == null) {
            if (this.mViewPagerAdapter != null) {
                fragment = this.mViewPagerAdapter.getItem(this.mViewPager.getCurrentItem());
                if (fragment != null) {
                    fragment.setExpanded(shouldExpand);
                }
            }
            hideNonFavoritePager();
            showPager(shouldExpand);
        } else if (this.mController.isMapOnly()) {
            this.mPagerFragmentNonFavorite.setStore(selected);
            this.mPagerFragmentNonFavorite.setExpanded(shouldExpand);
            showNonFavorite();
        } else {
            Store current = this.mDataProvider.getCurrentStore();
            if (!(current == null || this.mIsOffersMode)) {
                if (current.getStoreId() != selected.getStoreId()) {
                    isCurrent = false;
                }
                if (isCurrent) {
                    this.mViewPager.setCurrentItem(0);
                    showPager(shouldExpand);
                    fragment = this.mViewPagerAdapter.getItem(0);
                    if (fragment != null) {
                        fragment.setExpanded(shouldExpand);
                        return;
                    }
                    return;
                }
            }
            if (!isFavorite(selected) || this.mIsOffersMode) {
                this.mPagerFragmentNonFavorite.setStore(selected);
                hidePager();
                showNonFavorite();
                return;
            }
            int position = this.mViewPagerAdapter.getPosition(Integer.valueOf(selected.getStoreId()));
            if (position != -1) {
                this.mViewPager.setCurrentItem(position);
                fragment = this.mViewPagerAdapter.getItem(position);
                if (fragment != null) {
                    fragment.setExpanded(shouldExpand);
                }
            }
            showPager(shouldExpand);
        }
    }

    private void showPager(boolean shouldExpand) {
        Ensighten.evaluateEvent(this, "showPager", new Object[]{new Boolean(shouldExpand)});
        this.mPagerClickCatcher.setVisibility(8);
        hideNonFavoritePager();
        updatePagerHeight(shouldExpand ? 240 : 180);
    }

    private void updateMapSelection(Integer previousStoreId, Store selectedStore) {
        Marker marker;
        Ensighten.evaluateEvent(this, "updateMapSelection", new Object[]{previousStoreId, selectedStore});
        if (previousStoreId != null) {
            marker = (Marker) this.mStoreMarkersMap.get(previousStoreId.intValue());
            if (marker != null) {
                try {
                    setMarkerIcon(marker, Integer.valueOf(previousStoreId.intValue()).intValue(), false);
                } catch (NumberFormatException e) {
                }
            }
        }
        marker = null;
        if (selectedStore != null) {
            marker = (Marker) this.mStoreMarkersMap.get(selectedStore.getStoreId());
        }
        if (selectedStore != null && marker != null) {
            setMarkerIcon(marker, selectedStore.getStoreId(), true);
            marker.showInfoWindow();
            Location userLocation = null;
            try {
                userLocation = AppUtils.getUserLocation();
            } catch (IllegalStateException e2) {
            }
            if (userLocation != null) {
                MapUtils.with(getActivity()).map(this.mMap).store(selectedStore).userLocation(userLocation).margin(60).move();
                this.mInitialCameraPosition = this.mMap.getCameraPosition();
            } else {
                this.mMap.animateCamera(marker.getPosition(), 14.0f);
            }
            this.mIsFirstLoad = false;
        }
    }

    private void setMarkerIcon(Marker marker, int id, boolean selected) {
        Ensighten.evaluateEvent(this, "setMarkerIcon", new Object[]{marker, new Integer(id), new Boolean(selected)});
        marker.setIcon(selected ? this.mDataProvider.getSelectMapPinResID(Integer.valueOf(id)) : this.mDataProvider.getMapPinResID(Integer.valueOf(id)));
    }

    public void onCurrentStoreChange(StoreLocatorDataProvider provider, String previousCurrentStoreId) {
        Ensighten.evaluateEvent(this, "onCurrentStoreChange", new Object[]{provider, previousCurrentStoreId});
        this.mViewPagerAdapter.setCurrentStore(this.mDataProvider.getCurrentStore());
        this.mIgnoreViewPagerChange = true;
    }

    public void refreshSelectedStore() {
        Ensighten.evaluateEvent(this, "refreshSelectedStore", null);
        StoreLocatorPagerFragment visibleFragment = null;
        if (this.mController.isMapOnly()) {
            visibleFragment = this.mPagerFragmentNonFavorite;
        } else {
            int position = this.mViewPagerAdapter.getPosition(this.mDataProvider.getSelectedStoreId());
            if (position > -1) {
                visibleFragment = this.mViewPagerAdapter.getItem(position);
            }
        }
        if (visibleFragment != null) {
            visibleFragment.reset();
        }
    }

    public void onClick(View view) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
        if (view == this.mLocationButton) {
            searchByCurrentLocation();
        } else if (view == this.mSearchHereButton) {
            searchHere();
        } else if (view == this.mContractSymbol) {
            getActivity().finish();
        }
    }

    private void searchHere() {
        Ensighten.evaluateEvent(this, "searchHere", null);
        this.mSearchHereButton.animate().setDuration(500).alpha(0.0f).setListener(new C37624());
        LatLng mapCenter = this.mMap.getCameraPosition().target;
        if (mapCenter != null) {
            this.mSkipZoom = true;
            this.mInteractionListener.requestUpdateStoresByScrolledLocation(mapCenter);
        }
    }

    private void searchByCurrentLocation() {
        Ensighten.evaluateEvent(this, "searchByCurrentLocation", null);
        if (LocationServicesManager.isLocationEnabled(getContext())) {
            this.mInteractionListener.requestUpdateStoresByCurrentLocation(true);
        } else {
            MCDAlertDialogBuilder.withContext(getContext()).setTitle((int) C2658R.string.gps_not_enabled_title).setMessage((int) C2658R.string.location_services_nearby_stores_alert).setPositiveButton((int) C2658R.string.f6083ok, new C37635()).create().show();
        }
    }

    private void toggleNonPager() {
        Ensighten.evaluateEvent(this, "toggleNonPager", null);
        if (this.mShowingNonPager) {
            hideNonFavoritePager();
        } else {
            showNonFavorite();
        }
    }

    private void showNonFavorite() {
        Ensighten.evaluateEvent(this, "showNonFavorite", null);
        int yTarget = (this.mPagerFragmentNonFavorite == null || !this.mPagerFragmentNonFavorite.isExpanded()) ? 180 : 240;
        updateNonFavoritePagerHeight(yTarget);
        this.mShowingNonPager = true;
    }

    public boolean onTouch(View v, MotionEvent event) {
        Ensighten.evaluateEvent(this, "onTouch", new Object[]{v, event});
        if (event.getAction() == 0) {
            this.mMapPanBegan = true;
        }
        return false;
    }

    private void showSearchHereButton() {
        Ensighten.evaluateEvent(this, "showSearchHereButton", null);
        if (!this.mIsOffersMode && !Configuration.getSharedInstance().getBooleanForKey("interface.storelocator.hideSearchHere") && this.mSearchHereButton.getVisibility() != 0) {
            this.mSearchHereButton.setAlpha(0.0f);
            this.mSearchHereButton.setVisibility(0);
            this.mSearchHereButton.animate().setDuration(500).alpha(1.0f).setListener(this.mNewLocationFadeInEndListener);
        }
    }

    public void onLocationUpdated(Location location, int tag) {
        Ensighten.evaluateEvent(this, "onLocationUpdated", new Object[]{location, new Integer(tag)});
        if (tag == 1 && location == null && this.mDataProvider.getCurrentStore() == null) {
            MCDLog.fatal("No Current Location defined!");
        }
    }

    private void refreshMarkers() {
        Ensighten.evaluateEvent(this, "refreshMarkers", null);
        if (this.mMap != null) {
            this.mMap.clear();
            Set<Store> storeSet = new TreeSet(new C375410());
            List<Store> nearByStores = this.mDataProvider.getNearByStores();
            List<String> nearByStoreIds = new ArrayList();
            SparseArray<Store> nearByMap = new SparseArray();
            if (nearByStores != null && nearByStores.size() > 0) {
                for (Store store : nearByStores) {
                    nearByStoreIds.add(Integer.toString(store.getStoreId()));
                    nearByMap.put(store.getStoreId(), store);
                    storeSet.add(store);
                }
            }
            List<Store> favoriteStores = this.mDataProvider.getFavoriteStores();
            if (favoriteStores != null && favoriteStores.size() > 0) {
                for (Store store2 : favoriteStores) {
                    String storeId = Integer.toString(store2.getStoreId());
                    if (nearByStoreIds.contains(storeId)) {
                        nearByStoreIds.remove(storeId);
                        storeSet.remove(nearByMap.get(store2.getStoreId()));
                    }
                    storeSet.add(store2);
                }
            }
            if (this.mController.getCurrentStore() != null) {
                storeSet.add(this.mController.getCurrentStore());
            }
            this.mSortedStores = new ArrayList(storeSet);
            createMarkers(this.mSortedStores);
        }
    }

    private StoreLocatorSection getStoreSection(Store current) {
        Ensighten.evaluateEvent(this, "getStoreSection", new Object[]{current});
        return isFavorite(current) ? StoreLocatorSection.Favorites : StoreLocatorSection.Nearby;
    }

    private boolean isFavorite(Store store) {
        Ensighten.evaluateEvent(this, "isFavorite", new Object[]{store});
        return (store.getStoreFavoriteName() == null || store.getStoreFavoriteId() == null) ? false : true;
    }

    private void updateMapZoom() {
        Ensighten.evaluateEvent(this, "updateMapZoom", null);
        if (this.mMap != null) {
            LatLng epicenter = null;
            Store store;
            if (this.mInitialCameraPosition != null) {
                store = (Store) this.mSortedStores.get(this.mSortedStores.size() / 2);
                if (!(store == null || this.mStoreMarkersMap.get(store.getStoreId()) == null)) {
                    epicenter = ((Marker) this.mStoreMarkersMap.get(store.getStoreId())).getPosition();
                }
                if (epicenter != null) {
                    this.mMap.animateCamera(epicenter, getZoomLevel(epicenter));
                    return;
                }
                this.mMap.moveCamera(this.mInitialCameraPosition);
                return;
            }
            if (this.mSortedStores.size() > 0) {
                if (this.mMap.getCameraPosition() != null) {
                    epicenter = this.mMap.getCameraPosition().target;
                }
                if (this.mController.shouldIgnoreUserLocation()) {
                    store = (Store) this.mSortedStores.get(this.mSortedStores.size() / 2);
                    if (!(store == null || this.mStoreMarkersMap.get(store.getStoreId()) == null)) {
                        epicenter = ((Marker) this.mStoreMarkersMap.get(store.getStoreId())).getPosition();
                    }
                } else {
                    LatLng location = getUserLocation();
                    if (location != null) {
                        epicenter = location;
                    }
                }
            } else {
                epicenter = getUserLocation();
            }
            if (epicenter != null) {
                this.mMap.animateCamera(epicenter, getZoomLevel(epicenter));
            }
            this.mController.setIgnoreUserLocation(false);
        }
    }

    private float getZoomLevel(LatLng location) {
        Ensighten.evaluateEvent(this, "getZoomLevel", new Object[]{location});
        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        return (float) LocationUtils.getZoomLevelForRadius(9000.0d, (double) size.x, location.latitude);
    }

    private LatLng getUserLocation() {
        Ensighten.evaluateEvent(this, "getUserLocation", null);
        if (this.mMap.getUserLocation() != null) {
            return this.mMap.getUserLocation();
        }
        Location location = null;
        try {
            location = AppUtils.getUserLocation();
        } catch (IllegalStateException e) {
        }
        if (location != null) {
            return LocationUtils.toLatLng(location);
        }
        return null;
    }

    private List<MarkerOptions> createMarkers(List<? extends Store> stores) {
        Ensighten.evaluateEvent(this, "createMarkers", new Object[]{stores});
        this.mMarkerStoresMap.clear();
        this.mStoreMarkersMap.clear();
        List<MarkerOptions> markers = new ArrayList();
        Marker targetMarker = null;
        for (Store store : stores) {
            int selectMapPinResID;
            boolean selected = store.equals(this.mDataProvider.getSelectedStore());
            MarkerOptions options = new MarkerOptions();
            options.position(new LatLng(store.getLatitude(), store.getLongitude()));
            if (selected) {
                selectMapPinResID = this.mDataProvider.getSelectMapPinResID(Integer.valueOf(store.getStoreId()));
            } else {
                selectMapPinResID = this.mDataProvider.getMapPinResID(Integer.valueOf(store.getStoreId()));
            }
            options.icon(selectMapPinResID);
            Marker marker = this.mMap.addMarker(options);
            if (marker != null) {
                this.mMarkerStoresMap.put(marker.getId(), store);
                this.mStoreMarkersMap.put(store.getStoreId(), marker);
                if (this.mDataProvider.getSelectedStore() != null && this.mDataProvider.getSelectedStore().equals(store)) {
                    targetMarker = marker;
                }
            }
        }
        if (targetMarker != null) {
            targetMarker.showInfoWindow();
        }
        return markers;
    }

    private boolean isCurrent(Store store) {
        Ensighten.evaluateEvent(this, "isCurrent", new Object[]{store});
        return store != null && this.mViewPagerAdapter.includesCurrent() && store.equals(this.mDataProvider.getCurrentStore());
    }

    public void setIsFirstLoad(boolean isFirstLoad) {
        Ensighten.evaluateEvent(this, "setIsFirstLoad", new Object[]{new Boolean(isFirstLoad)});
        this.mIsFirstLoad = isFirstLoad;
    }

    private void updatePagerHeight(final int heightDP) {
        Ensighten.evaluateEvent(this, "updatePagerHeight", new Object[]{new Integer(heightDP)});
        if (!this.mPagerAnimating && this.mPagerY != ((float) heightDP)) {
            View parent = (View) this.mPagerContainer.getParent();
            float origY = this.mPagerContainer.getY();
            final float newY = (float) (parent.getBottom() - UIUtils.dpAsPixels(getActivity(), heightDP));
            ObjectAnimator transAnimation = ObjectAnimator.ofFloat(this.mPagerContainer, "y", new float[]{origY, newY});
            AnimatorSet set = new AnimatorSet();
            set.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationStart", new Object[]{animator});
                    StoreLocatorMapFragment.access$2502(StoreLocatorMapFragment.this, true);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$300", new Object[]{StoreLocatorMapFragment.this}).setEnabled(false);
                }

                public void onAnimationEnd(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationEnd", new Object[]{animator});
                    StoreLocatorMapFragment.access$2502(StoreLocatorMapFragment.this, false);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$300", new Object[]{StoreLocatorMapFragment.this}).setEnabled(true);
                    StoreLocatorMapFragment.access$802(StoreLocatorMapFragment.this, (float) heightDP);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$700", new Object[]{StoreLocatorMapFragment.this}).setY(newY);
                    Log.i(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2600", null), "mPagerContainer y = " + Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$700", new Object[]{StoreLocatorMapFragment.this}).getY());
                }

                public void onAnimationCancel(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationCancel", new Object[]{animator});
                    StoreLocatorMapFragment.access$2502(StoreLocatorMapFragment.this, false);
                }

                public void onAnimationRepeat(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationRepeat", new Object[]{animator});
                }
            });
            set.setDuration(300);
            set.play(transAnimation);
            set.start();
        }
    }

    private void updateNonFavoritePagerHeight(final int heightDP) {
        Ensighten.evaluateEvent(this, "updateNonFavoritePagerHeight", new Object[]{new Integer(heightDP)});
        if (!this.mPagerAnimatingNonFavorite && this.mPagerHeightNonFavorite != ((float) heightDP)) {
            View parent = (View) this.mPagerContainerNonFavorite.getParent();
            float origY = this.mPagerContainerNonFavorite.getY();
            final float newY = (float) (parent.getBottom() - UIUtils.dpAsPixels(getActivity(), heightDP));
            ObjectAnimator transAnimation = ObjectAnimator.ofFloat(this.mPagerContainerNonFavorite, "y", new float[]{origY, newY});
            AnimatorSet set = new AnimatorSet();
            set.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationStart", new Object[]{animator});
                    StoreLocatorMapFragment.access$2702(StoreLocatorMapFragment.this, true);
                }

                public void onAnimationEnd(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationEnd", new Object[]{animator});
                    StoreLocatorMapFragment.access$2702(StoreLocatorMapFragment.this, false);
                    StoreLocatorMapFragment.access$1002(StoreLocatorMapFragment.this, (float) heightDP);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$900", new Object[]{StoreLocatorMapFragment.this}).setY(newY);
                    Log.i(Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$2600", null), "mPagerContainerNonFavorite y = " + Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$900", new Object[]{StoreLocatorMapFragment.this}).getY());
                }

                public void onAnimationCancel(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationCancel", new Object[]{animator});
                    StoreLocatorMapFragment.access$2702(StoreLocatorMapFragment.this, false);
                }

                public void onAnimationRepeat(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationRepeat", new Object[]{animator});
                }
            });
            set.setDuration(300);
            set.play(transAnimation);
            set.start();
        }
    }

    private void hidePager() {
        Ensighten.evaluateEvent(this, "hidePager", null);
        if (!this.mPagerAnimating && this.mPagerY != 0.0f) {
            View parent = (View) this.mPagerContainer.getParent();
            float origY = this.mPagerContainer.getY();
            final float newY = (float) (parent.getBottom() - UIUtils.dpAsPixels(getActivity(), 40));
            ObjectAnimator transAnimation = ObjectAnimator.ofFloat(this.mPagerContainer, "y", new float[]{origY, newY});
            transAnimation.setDuration(300);
            transAnimation.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationStart", new Object[]{animator});
                    StoreLocatorMapFragment.access$2502(StoreLocatorMapFragment.this, true);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$300", new Object[]{StoreLocatorMapFragment.this}).setEnabled(false);
                }

                public void onAnimationEnd(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationEnd", new Object[]{animator});
                    StoreLocatorMapFragment.access$2502(StoreLocatorMapFragment.this, false);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$300", new Object[]{StoreLocatorMapFragment.this}).setEnabled(true);
                    StoreLocatorMapFragment.access$802(StoreLocatorMapFragment.this, 0.0f);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$700", new Object[]{StoreLocatorMapFragment.this}).setY(newY);
                }

                public void onAnimationCancel(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationCancel", new Object[]{animator});
                    StoreLocatorMapFragment.access$2502(StoreLocatorMapFragment.this, false);
                }

                public void onAnimationRepeat(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationRepeat", new Object[]{animator});
                }
            });
            transAnimation.start();
            this.mPagerClickCatcher.setVisibility(0);
        }
    }

    private void hideNonFavoritePager() {
        Ensighten.evaluateEvent(this, "hideNonFavoritePager", null);
        if (!this.mPagerAnimatingNonFavorite && this.mPagerHeightNonFavorite != 0.0f) {
            int i;
            View parent = (View) this.mPagerContainerNonFavorite.getParent();
            float origY = this.mPagerContainerNonFavorite.getY();
            int bottom = parent.getBottom();
            FragmentActivity activity = getActivity();
            if (this.mDataProvider.isCurrentStoreSelectionMode()) {
                i = 40;
            } else {
                i = 0;
            }
            final float newY = (float) (bottom - UIUtils.dpAsPixels(activity, i));
            ObjectAnimator transAnimation = ObjectAnimator.ofFloat(this.mPagerContainerNonFavorite, "y", new float[]{origY, newY});
            transAnimation.setDuration(300);
            transAnimation.addListener(new AnimatorListener() {
                public void onAnimationStart(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationStart", new Object[]{animator});
                    StoreLocatorMapFragment.access$2702(StoreLocatorMapFragment.this, true);
                }

                public void onAnimationEnd(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationEnd", new Object[]{animator});
                    StoreLocatorMapFragment.access$2702(StoreLocatorMapFragment.this, false);
                    StoreLocatorMapFragment.access$1002(StoreLocatorMapFragment.this, 0.0f);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$900", new Object[]{StoreLocatorMapFragment.this}).setY(newY);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorMapFragment", "access$900", new Object[]{StoreLocatorMapFragment.this}).setVisibility(0);
                }

                public void onAnimationCancel(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationCancel", new Object[]{animator});
                    StoreLocatorMapFragment.access$2702(StoreLocatorMapFragment.this, false);
                }

                public void onAnimationRepeat(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationRepeat", new Object[]{animator});
                }
            });
            transAnimation.start();
            if (this.mController.isMapOnly()) {
                this.mPagerClickCatcher.setVisibility(0);
                this.mPagerClickCatcher.bringToFront();
            }
            this.mShowingNonPager = false;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(requestCode), new Integer(resultCode), data});
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22 && resultCode == -1) {
            String newName = data.getStringExtra("name");
            if (newName != null && !newName.isEmpty()) {
                if (this.mController.getSelectedStoreSection() == StoreLocatorSection.Nearby) {
                    this.mInteractionListener.addStoreToFavorites(this.mController.getSelectedStoreId(), this.mController.getSelectedStoreSection(), newName, null);
                } else {
                    this.mInteractionListener.renameStoreInFavorites(this.mController.getSelectedStoreId(), this.mController.getSelectedStoreSection(), newName, null);
                }
            }
        }
    }
}
