package com.mcdonalds.app.storelocator;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class StoreLocatorPagerFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private StoreLocatorDataProvider mDataProvider;
    private boolean mIsExpanded;
    private StoreLocatorPagerListAdapter mListAdapter;
    private ScrollDisabledListView mListView;
    private PagerItemListener mListener;
    private StoreLocatorSection mSection;
    private Store mStore;

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

    public void setExpanded(boolean expanded) {
        Ensighten.evaluateEvent(this, "setExpanded", new Object[]{new Boolean(expanded)});
        if (this.mListAdapter != null) {
            this.mIsExpanded = expanded;
            if (this.mIsExpanded) {
                this.mListAdapter.expand(0);
            } else {
                this.mListAdapter.collapse(0);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("StoreLocatorPagerFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "StoreLocatorPagerFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "StoreLocatorPagerFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        this.mListAdapter = new StoreLocatorPagerListAdapter(getActivity());
        this.mListAdapter.setLimit(1);
        this.mListAdapter.setDataProvider(this.mDataProvider);
        this.mListAdapter.setListener(this.mListener);
        this.mListAdapter.setSection(this.mSection);
        this.mListAdapter.setOffersOnly(Boolean.valueOf(!ModuleManager.isModuleEnabled("ordering").booleanValue()));
        Log.d("PagerAdapter", String.format("StoreLocatorPagerList Adapter = %s for Parent %s", new Object[]{this.mListAdapter, getParentFragment()}));
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "StoreLocatorPagerFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "StoreLocatorPagerFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        ViewGroup vg = (ViewGroup) layoutInflater.inflate(C2658R.layout.fragment_storelocator_pager_item, viewGroup, false);
        this.mListView = (ScrollDisabledListView) vg.findViewById(C2358R.C2357id.list);
        this.mListView.setVerticalScrollBarEnabled(false);
        this.mListView.setAdapter(this.mListAdapter);
        this.mListAdapter.setAbsListView(this.mListView);
        updateStore(this.mStore);
        TraceMachine.exitMethod();
        return vg;
    }

    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        Ensighten.processView((Object) this, "onResume");
    }

    private void refreshAdapter() {
        Ensighten.evaluateEvent(this, "refreshAdapter", null);
        if (this.mListAdapter != null) {
            this.mListAdapter.setStore(this.mStore);
            if (this.mListView != null) {
                this.mListView.setAdapter(this.mListView.getAdapter());
            }
            this.mListAdapter.storesUpdated();
        }
        if (VERSION.SDK_INT < 16 && this.mListView != null) {
            this.mListView.forceLayout();
        }
    }

    public void reset() {
        Ensighten.evaluateEvent(this, "reset", null);
        refreshAdapter();
    }

    public void updateStore(Store store) {
        Ensighten.evaluateEvent(this, "updateStore", new Object[]{store});
        this.mStore = store;
        refreshAdapter();
    }

    public void setDataProvider(StoreLocatorDataProvider dataProvider) {
        Ensighten.evaluateEvent(this, "setDataProvider", new Object[]{dataProvider});
        this.mDataProvider = dataProvider;
    }

    public void setListener(PagerItemListener listener) {
        Ensighten.evaluateEvent(this, "setListener", new Object[]{listener});
        this.mListener = listener;
        if (this.mListAdapter != null) {
            this.mListAdapter.setListener(listener);
        }
    }

    public void setStore(Store mStore) {
        Ensighten.evaluateEvent(this, "setStore", new Object[]{mStore});
        updateStore(mStore);
    }

    public Store getStore() {
        Ensighten.evaluateEvent(this, "getStore", null);
        return this.mStore;
    }

    public void setSection(StoreLocatorSection section) {
        Ensighten.evaluateEvent(this, "setSection", new Object[]{section});
        this.mSection = section;
        if (this.mListAdapter != null) {
            this.mListAdapter.setSection(section);
        }
    }

    public boolean isExpanded() {
        Ensighten.evaluateEvent(this, "isExpanded", null);
        return this.mIsExpanded;
    }
}
