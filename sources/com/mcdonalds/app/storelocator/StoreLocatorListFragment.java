package com.mcdonalds.app.storelocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.ListFragment;
import android.support.p000v4.widget.SwipeRefreshLayout;
import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.listview.ExpandCollapseListener;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Instrumented
public class StoreLocatorListFragment extends ListFragment implements StoreLocationListener, ExpandCollapseListener, TraceFieldInterface {
    private static final String LOG_TAG = StoreLocatorListFragment.class.getSimpleName();
    public Trace _nr_trace;
    private StoreLocatorController mController;
    private StoreLocatorInteractionListener mInteractionListener;
    private int mLastExpandedPosition = -1;
    private StoreLocatorListAdapter mListAdapter;
    private ListView mListView;
    private Boolean mOffersOnlyMode = Boolean.valueOf(false);
    private Integer mPreviousId;
    private StoreLocatorDataProvider mProvider;
    private SwipeRefreshLayout mRefreshLayout;
    private boolean mStoreDelayRefresh = false;

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorListFragment$1 */
    class C37521 implements OnRefreshListener {
        C37521() {
        }

        public void onRefresh() {
            Ensighten.evaluateEvent(this, "onRefresh", null);
            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorListFragment", "access$000", new Object[]{StoreLocatorListFragment.this})) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorListFragment", "access$100", new Object[]{StoreLocatorListFragment.this}).requestUpdateStoresByCurrentLocation(true);
                StoreLocatorListFragment.access$200(StoreLocatorListFragment.this);
                StoreLocatorContainerFragment containerFragment = (StoreLocatorContainerFragment) StoreLocatorListFragment.this.getParentFragment();
                containerFragment.getSearchView().setText("");
                containerFragment.mMobileOrderFilterLayout.setVisibility(8);
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorListFragment", "access$300", new Object[]{StoreLocatorListFragment.this}).setRefreshing(false);
        }
    }

    /* renamed from: com.mcdonalds.app.storelocator.StoreLocatorListFragment$2 */
    class C37532 extends TimerTask {
        C37532() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            StoreLocatorListFragment.access$002(StoreLocatorListFragment.this, false);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{bundle});
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
    }

    public void onDestroyView() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroyView", null);
        super.onDestroyView();
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

    static /* synthetic */ boolean access$002(StoreLocatorListFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorListFragment", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.mStoreDelayRefresh = x1;
        return x1;
    }

    static /* synthetic */ void access$200(StoreLocatorListFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.storelocator.StoreLocatorListFragment", "access$200", new Object[]{x0});
        x0.scheduleMinRefreshDelay();
    }

    public void setController(StoreLocatorController controller) {
        Ensighten.evaluateEvent(this, "setController", new Object[]{controller});
        if (controller != null) {
            Log.i(LOG_TAG, "setController");
            this.mController = controller;
            this.mProvider = controller;
            this.mInteractionListener = controller;
            this.mController.addListener(this);
        }
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("StoreLocatorListFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "StoreLocatorListFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "StoreLocatorListFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        Log.i(LOG_TAG, "onCreate");
        if (this.mController == null) {
            if (((StoreLocatorContainerFragment) getParentFragment()).getController() == null) {
                getActivity().finish();
                Ensighten.processView((Object) this, "onCreate");
                TraceMachine.exitMethod();
            }
            setController(((StoreLocatorContainerFragment) getParentFragment()).getController());
        }
        if (!ModuleManager.isModuleEnabled("ordering").booleanValue()) {
            this.mOffersOnlyMode = Boolean.valueOf(true);
        }
        this.mListAdapter = new StoreLocatorListAdapter(getActivity());
        this.mListAdapter.setLimit(1);
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
        if (this.mController != null) {
            this.mController.removeListener(this);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "StoreLocatorListFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "StoreLocatorListFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        Log.i(LOG_TAG, "onCreateView");
        ViewGroup group = (ViewGroup) layoutInflater.inflate(C2658R.layout.fragment_storelocator_list, viewGroup, false);
        this.mRefreshLayout = (SwipeRefreshLayout) group.findViewById(C2358R.C2357id.refresh_layout);
        UIUtils.setDefaultRefreshColors(this.mRefreshLayout);
        scheduleMinRefreshDelay();
        this.mRefreshLayout.setOnRefreshListener(new C37521());
        this.mListView = (ListView) group.findViewById(16908298);
        this.mListView.setAdapter(this.mListAdapter);
        this.mListView.setDivider(null);
        this.mListView.setDividerHeight(UIUtils.dpAsPixels(getActivity(), 5));
        this.mListAdapter.setAbsListView(this.mListView);
        this.mListAdapter.setOffersOnly(this.mOffersOnlyMode);
        this.mListAdapter.setExpandCollapseListener(this);
        TraceMachine.exitMethod();
        return group;
    }

    public void onItemExpanded(int position) {
        Ensighten.evaluateEvent(this, "onItemExpanded", new Object[]{new Integer(position)});
        Log.i(LOG_TAG, "onItemExpanded: " + position);
        this.mLastExpandedPosition = position;
    }

    public void onItemCollapsed(int position) {
        Ensighten.evaluateEvent(this, "onItemCollapsed", new Object[]{new Integer(position)});
        Log.i(LOG_TAG, "onItemCollapsed: " + position);
        if (isPositionVisible(position)) {
            this.mListAdapter.refreshContent(position, (StoreItemViewHolder) this.mListView.getChildAt(position - this.mListView.getFirstVisiblePosition()).getTag());
        }
    }

    private boolean isPositionVisible(int position) {
        Ensighten.evaluateEvent(this, "isPositionVisible", new Object[]{new Integer(position)});
        return this.mListView != null && position >= this.mListView.getFirstVisiblePosition() && position <= this.mListView.getLastVisiblePosition();
    }

    public void onChange(StoreLocatorDataProvider provider) {
        Ensighten.evaluateEvent(this, "onChange", new Object[]{provider});
        Log.i(LOG_TAG, "onChange");
        if (this.mRefreshLayout != null && this.mRefreshLayout.isRefreshing()) {
            this.mRefreshLayout.setRefreshing(false);
        }
        if (this.mListAdapter != null) {
            this.mListAdapter.setCurrentStore(this.mProvider.getCurrentStore());
            this.mListAdapter.setFavoriteStores(this.mProvider.getFavoriteStores());
            this.mListAdapter.setDataProvider(this.mController);
            this.mListAdapter.setListener(this.mController);
            List<Store> nearby = new ArrayList();
            List<Store> stores = this.mProvider.getNearByStores();
            boolean sortOnlyByUserLocation = Configuration.getSharedInstance().getBooleanForKey("modules.storeLocator.sortStoresDistanceOnlyRelativeToUserLocation", true);
            boolean ignoreSortingForUserDistanceOnFinalStoreList = Configuration.getSharedInstance().getBooleanForKey("interface.storelocator.ignoreSortingForUserDistanceOnFinalStoreList", false);
            if (sortOnlyByUserLocation) {
                this.mController.sortStoresByDistance(stores);
            }
            if (stores != null) {
                if (stores.size() > 20) {
                    nearby.addAll(stores.subList(0, 20));
                } else {
                    nearby.addAll(stores);
                }
                ((StoreLocatorContainerFragment) getParentFragment()).mMobileOrderFilterLayout.setVisibility(0);
            } else {
                ((StoreLocatorContainerFragment) getParentFragment()).mMobileOrderFilterLayout.setVisibility(8);
            }
            if (!(sortOnlyByUserLocation || ignoreSortingForUserDistanceOnFinalStoreList)) {
                this.mController.sortStoresByDistance(nearby);
            }
            this.mListAdapter.setNearByStores(nearby);
            this.mListAdapter.storesUpdated();
        }
    }

    public void onSelectedStoreChange(StoreLocatorDataProvider provider, String previousSelectionId, StoreLocatorSection previousSection, boolean shouldExpand) {
        Ensighten.evaluateEvent(this, "onSelectedStoreChange", new Object[]{provider, previousSelectionId, previousSection, new Boolean(shouldExpand)});
        Integer storeId = provider.getSelectedStoreId();
        Log.i(LOG_TAG, "onSelectedStoreChange: " + storeId);
        if (this.mListAdapter != null) {
            this.mListAdapter.setDataProvider(this.mController);
            if (storeId != null) {
                int position = this.mListAdapter.getPosition(this.mProvider.getSelectedStoreId(), this.mProvider.getSelectedStoreSection());
                if (!isPositionVisible(position)) {
                    this.mListView.smoothScrollToPosition(position);
                }
                if (shouldExpand) {
                    if (!this.mListAdapter.isExpanded(position)) {
                        this.mListAdapter.expand(position);
                    }
                } else if (previousSelectionId != null) {
                    int previousPosition = this.mListAdapter.getPosition(Integer.valueOf(previousSelectionId), previousSection);
                    if (this.mListAdapter.isExpanded(previousPosition)) {
                        this.mListAdapter.collapse(previousPosition);
                    }
                }
                this.mPreviousId = storeId;
                return;
            }
            for (int i = 0; i < this.mListAdapter.getCount(); i++) {
                this.mListAdapter.collapse(i);
            }
        }
    }

    public void onCurrentStoreChange(StoreLocatorDataProvider provider, String previousCurrentStoreId) {
        Ensighten.evaluateEvent(this, "onCurrentStoreChange", new Object[]{provider, previousCurrentStoreId});
        Log.i(LOG_TAG, "onCurrentStoreChange");
        if (this.mListAdapter != null) {
            this.mListAdapter.setDataProvider(this.mController);
            this.mListAdapter.setCurrentStore(this.mProvider.getCurrentStore());
            this.mListAdapter.storesUpdated();
            clearPreviousCurrentStoreState(previousCurrentStoreId);
        }
    }

    public void refreshSelectedStore() {
        Ensighten.evaluateEvent(this, "refreshSelectedStore", null);
        Log.i(LOG_TAG, "refreshSelectedStore");
        int position = this.mListAdapter.getPosition(this.mProvider.getSelectedStoreId(), this.mProvider.getSelectedStoreSection());
        if (isPositionVisible(position)) {
            this.mListAdapter.refreshContent(position, (StoreItemViewHolder) this.mListView.getChildAt(position - this.mListView.getFirstVisiblePosition()).getTag());
        }
    }

    public void clearZoomFlag() {
        Ensighten.evaluateEvent(this, "clearZoomFlag", null);
    }

    private void clearPreviousCurrentStoreState(String previousStoreId) {
        Ensighten.evaluateEvent(this, "clearPreviousCurrentStoreState", new Object[]{previousStoreId});
        Log.i(LOG_TAG, "clearPreviousCurrentStoreState");
        int previousId = -1;
        try {
            previousId = Integer.valueOf(previousStoreId).intValue();
        } catch (NullPointerException e) {
            Log.e(LOG_TAG, "no previous id", e);
        } catch (NumberFormatException e2) {
            Log.e(LOG_TAG, "invalid id", e2);
        }
        int i = 0;
        while (i < this.mListAdapter.getCount()) {
            if (((Store) this.mListAdapter.getItem(i)).getStoreId() == previousId && !isPositionVisible(i)) {
                View view = this.mListView.getChildAt(i - this.mListView.getFirstVisiblePosition());
                if (view != null) {
                    this.mListAdapter.refreshContent(i, (StoreItemViewHolder) view.getTag());
                }
            }
            i++;
        }
    }

    private void scheduleMinRefreshDelay() {
        Ensighten.evaluateEvent(this, "scheduleMinRefreshDelay", null);
        this.mStoreDelayRefresh = true;
        new Timer().schedule(new C37532(), 1500);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(requestCode), new Integer(resultCode), data});
        Log.i(LOG_TAG, "onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 22 && resultCode == -1) {
            String newName = data.getStringExtra("name");
            if (newName != null && !newName.isEmpty()) {
                if (this.mController.getSelectedStoreSection() == StoreLocatorSection.Nearby) {
                    this.mInteractionListener.addStoreToFavorites(this.mController.getSelectedStoreId(), this.mController.getSelectedStoreSection(), newName, null);
                } else {
                    this.mInteractionListener.renameStoreInFavorites(this.mController.getSelectedStoreId(), this.mController.getSelectedStoreSection(), newName, null);
                }
                int position = this.mListAdapter.getPosition(this.mController.getSelectedStoreId(), this.mController.getSelectedStoreSection());
                if (isPositionVisible(position)) {
                    ((StoreItemViewHolder) this.mListView.getChildAt(position - this.mListView.getFirstVisiblePosition()).getTag()).getNickName().setText(newName);
                }
            }
        }
    }
}
