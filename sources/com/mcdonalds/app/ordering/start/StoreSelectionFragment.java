package com.mcdonalds.app.ordering.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.storelocator.PagerItemListener;
import com.mcdonalds.app.storelocator.StoreLocationListener;
import com.mcdonalds.app.storelocator.StoreLocatorDataProvider;
import com.mcdonalds.app.storelocator.StoreLocatorInteractionListener;
import com.mcdonalds.app.storelocator.StoreLocatorPagerAdapter;
import com.mcdonalds.app.storelocator.StoreLocatorSection;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Instrumented
public class StoreSelectionFragment extends Fragment implements StoreLocationListener, TraceFieldInterface {
    public Trace _nr_trace;
    private StoreLocatorDataProvider mDataProvider;
    private Button mFindStoreButton;
    private StoreLocatorInteractionListener mInteractionListener;
    private OnActionListener mListener;
    private PagerItemListener mPagerItemListener;
    private StoreSelectionController mStoreSelectionController;
    private ViewPager mViewPager;
    private StoreLocatorPagerAdapter mViewPagerAdapter;

    /* renamed from: com.mcdonalds.app.ordering.start.StoreSelectionFragment$1 */
    class C36771 implements OnClickListener {
        C36771() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.StoreSelectionFragment", "access$000", new Object[]{StoreSelectionFragment.this}).onDisplayPickupFindAnotherStore();
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

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("StoreSelectionFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "StoreSelectionFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "StoreSelectionFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        this.mStoreSelectionController = new StoreSelectionController(getContext());
        this.mDataProvider = this.mStoreSelectionController;
        this.mInteractionListener = this.mStoreSelectionController;
        this.mPagerItemListener = this.mStoreSelectionController;
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "StoreSelectionFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "StoreSelectionFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View view = layoutInflater.inflate(C2658R.layout.fragment_order_store_selection, viewGroup, false);
        this.mViewPager = (ViewPager) view.findViewById(C2358R.C2357id.stores_pager);
        this.mFindStoreButton = (Button) view.findViewById(C2358R.C2357id.find_another_store_button);
        this.mFindStoreButton.setOnClickListener(new C36771());
        TraceMachine.exitMethod();
        return view;
    }

    private void updateCurrentStatus(Integer oldId, Integer currentId) {
        Ensighten.evaluateEvent(this, "updateCurrentStatus", new Object[]{oldId, currentId});
        Store old = null;
        Store current = null;
        for (Store store : this.mViewPagerAdapter.getStores()) {
            if (oldId.equals(Integer.valueOf(store.getStoreId()))) {
                old = store;
            }
            if (currentId.equals(Integer.valueOf(store.getStoreId()))) {
                current = store;
            }
        }
        if (old != null) {
            this.mViewPagerAdapter.getItem(this.mViewPagerAdapter.getStores().indexOf(old)).reset();
        }
        if (current != null) {
            int newIndex = this.mViewPagerAdapter.getStores().indexOf(current);
            this.mViewPagerAdapter.getItem(newIndex).reset();
            this.mViewPager.setCurrentItem(newIndex);
        }
        this.mListener.onSelectedStoreChanged();
    }

    public void onChange(StoreLocatorDataProvider provider) {
        List<Store> favorites;
        boolean includesCurrent = true;
        Ensighten.evaluateEvent(this, "onChange", new Object[]{provider});
        List<Store> stores = new ArrayList();
        if (provider.getFavoriteStores() == null) {
            favorites = new ArrayList();
        } else {
            favorites = new ArrayList(provider.getFavoriteStores());
        }
        if (provider.getCurrentStore() == null) {
            includesCurrent = false;
        }
        if (includesCurrent) {
            stores.add(provider.getCurrentStore());
            stores.addAll(filterCurrentLocation(provider));
        } else {
            stores.addAll(favorites);
        }
        this.mViewPagerAdapter.setIncludesCurrent(includesCurrent);
        this.mViewPagerAdapter.setStores(stores);
        this.mViewPagerAdapter.notifyDataSetChanged();
        if (provider.getCurrentStore() != null) {
            updateCurrentStatus(null, Integer.valueOf(provider.getCurrentStore().getStoreId()));
        } else if (!stores.isEmpty()) {
            this.mInteractionListener.selectStore(Integer.valueOf(((Store) stores.get(0)).getStoreId()), StoreLocatorSection.Current);
        }
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

    public void onSelectedStoreChange(StoreLocatorDataProvider provider, String previousSelectionId, StoreLocatorSection previousSection, boolean shouldExpand) {
        Ensighten.evaluateEvent(this, "onSelectedStoreChange", new Object[]{provider, previousSelectionId, previousSection, new Boolean(shouldExpand)});
    }

    public void onCurrentStoreChange(StoreLocatorDataProvider provider, String previousCurrentStoreId) {
        Ensighten.evaluateEvent(this, "onCurrentStoreChange", new Object[]{provider, previousCurrentStoreId});
        updateCurrentStatus(Integer.valueOf(Integer.parseInt(previousCurrentStoreId)), Integer.valueOf(provider.getCurrentStore().getStoreId()));
    }

    public void refreshSelectedStore() {
        Ensighten.evaluateEvent(this, "refreshSelectedStore", null);
    }

    public void clearZoomFlag() {
        Ensighten.evaluateEvent(this, "clearZoomFlag", null);
    }
}
