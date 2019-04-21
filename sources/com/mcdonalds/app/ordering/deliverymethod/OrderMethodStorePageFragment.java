package com.mcdonalds.app.ordering.deliverymethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class OrderMethodStorePageFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private TextView mAdreess;
    private TextView mDistance;
    private TextView mHours;
    private View mIcon;
    private DeliveryMethodStorePageFragmentListener mListener;
    private TextView mName;
    private final OnClickListener mOnClickInfo = new C35032();
    private final OnClickListener mOnClickSelect = new C35021();
    private OrderMethodSelectorFragment mParentFragment;
    private View mSelectButton;
    private Store mStore;

    public interface DeliveryMethodStorePageFragmentListener {
        void onInfoButtonPressed(Store store);

        void onStoreSelected(Store store);
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodStorePageFragment$1 */
    class C35021 implements OnClickListener {
        C35021() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodStorePageFragment", "access$000", new Object[]{OrderMethodStorePageFragment.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodStorePageFragment", "access$000", new Object[]{OrderMethodStorePageFragment.this}).onStoreSelected(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodStorePageFragment", "access$100", new Object[]{OrderMethodStorePageFragment.this}));
            }
            OrderMethodStorePageFragment.this.onStateChange();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OrderMethodStorePageFragment$2 */
    class C35032 implements OnClickListener {
        C35032() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodStorePageFragment", "access$000", new Object[]{OrderMethodStorePageFragment.this}) != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodStorePageFragment", "access$000", new Object[]{OrderMethodStorePageFragment.this}).onInfoButtonPressed(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodStorePageFragment", "access$100", new Object[]{OrderMethodStorePageFragment.this}));
            }
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

    public static OrderMethodStorePageFragment newInstance(Store store, DeliveryMethodStorePageFragmentListener listener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.deliverymethod.OrderMethodStorePageFragment", "newInstance", new Object[]{store, listener});
        OrderMethodStorePageFragment fragment = new OrderMethodStorePageFragment();
        fragment.setStore(store);
        fragment.setListener(listener);
        return fragment;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("OrderMethodStorePageFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "OrderMethodStorePageFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "OrderMethodStorePageFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        this.mParentFragment = (OrderMethodSelectorFragment) getParentFragment();
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "OrderMethodStorePageFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "OrderMethodStorePageFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View view = layoutInflater.inflate(C2658R.layout.view_store_item, viewGroup, false);
        view.findViewById(C2358R.C2357id.info_button).setOnClickListener(this.mOnClickInfo);
        this.mName = (TextView) view.findViewById(C2358R.C2357id.label_store_title);
        this.mAdreess = (TextView) view.findViewById(C2358R.C2357id.label_store_subtitle);
        this.mHours = (TextView) view.findViewById(C2358R.C2357id.label_store_hours);
        this.mDistance = (TextView) view.findViewById(C2358R.C2357id.label_store_distance);
        this.mIcon = view.findViewById(C2358R.C2357id.container_my_mcdonalds);
        this.mSelectButton = view.findViewById(C2358R.C2357id.button_eat_here);
        this.mSelectButton.setOnClickListener(this.mOnClickSelect);
        onStateChange();
        TraceMachine.exitMethod();
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        int textColor;
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{savedInstanceState});
        super.onActivityCreated(savedInstanceState);
        if (this.mStore.getStoreFavoriteName() != null) {
            textColor = getResources().getColor(C2658R.color.mcd_red);
        } else {
            textColor = getResources().getColor(C2658R.color.mcd_black);
        }
        this.mName.setTextColor(textColor);
        this.mName.setText(this.mStore.getName());
        this.mAdreess.setText(this.mStore.getAddress1());
        String dailyStoreHoursString = UIUtils.getDailyStoreHoursString(getActivity(), this.mStore);
        if (TextUtils.isEmpty(dailyStoreHoursString)) {
            this.mHours.setVisibility(8);
        } else {
            this.mHours.setText(dailyStoreHoursString);
        }
        String distance = "";
        try {
            distance = UIUtils.distanceFromStore(getContext(), this.mStore);
        } catch (IllegalStateException e) {
        }
        if (distance == null || distance.isEmpty()) {
            this.mDistance.setText(C2658R.string.label_unknown_distance);
        } else {
            this.mDistance.setText(distance);
        }
    }

    public void onStateChange() {
        Ensighten.evaluateEvent(this, "onStateChange", null);
        boolean isSelected = false;
        if (this.mParentFragment != null) {
            Store selected = this.mParentFragment.getSelectedStore();
            if (selected != null) {
                isSelected = this.mStore.getStoreId() == selected.getStoreId();
            }
        }
        if (isSelected) {
            this.mIcon.setVisibility(0);
            this.mSelectButton.setVisibility(4);
        }
    }

    public void setStore(Store store) {
        Ensighten.evaluateEvent(this, "setStore", new Object[]{store});
        this.mStore = store;
    }

    public void setListener(DeliveryMethodStorePageFragmentListener listener) {
        Ensighten.evaluateEvent(this, "setListener", new Object[]{listener});
        this.mListener = listener;
    }
}
