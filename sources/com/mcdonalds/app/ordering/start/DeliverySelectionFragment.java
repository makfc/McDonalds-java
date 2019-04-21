package com.mcdonalds.app.ordering.start;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.Order;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class DeliverySelectionFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private BaseAdapter mListAdapter = new C36701();
    private OnActionListener mListener;
    private Order mOrder;

    /* renamed from: com.mcdonalds.app.ordering.start.DeliverySelectionFragment$1 */
    class C36701 extends BaseAdapter {

        /* renamed from: com.mcdonalds.app.ordering.start.DeliverySelectionFragment$1$1 */
        class C36681 implements OnClickListener {
            C36681() {
            }

            public void onClick(View view) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                DeliverySelectionFragment.access$100(DeliverySelectionFragment.this);
            }
        }

        /* renamed from: com.mcdonalds.app.ordering.start.DeliverySelectionFragment$1$2 */
        class C36692 implements OnClickListener {
            C36692() {
            }

            public void onClick(View view) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
                DeliverySelectionFragment.access$200(DeliverySelectionFragment.this);
            }
        }

        C36701() {
        }

        public int getCount() {
            Ensighten.evaluateEvent(this, "getCount", null);
            return 2;
        }

        public Object getItem(int i) {
            Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(i)});
            return null;
        }

        public long getItemId(int i) {
            Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(i)});
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View item = LayoutInflater.from(DeliverySelectionFragment.this.getActivity()).inflate(C2658R.layout.order_start_delivery_list_item, null);
            TextView titleView = (TextView) item.findViewById(C2358R.C2357id.title_text_view);
            TextView valueView = (TextView) item.findViewById(C2358R.C2357id.value_text_view);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.DeliverySelectionFragment", "access$000", new Object[]{DeliverySelectionFragment.this}) != null) {
                if (i == 0) {
                    titleView.setText(C2658R.string.delivery_label_when);
                    valueView.setText(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.DeliverySelectionFragment", "access$000", new Object[]{DeliverySelectionFragment.this}).getDeliveryDateString());
                    item.setOnClickListener(new C36681());
                } else {
                    titleView.setText(C2658R.string.delivery_label_where);
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.DeliverySelectionFragment", "access$000", new Object[]{DeliverySelectionFragment.this}).getDeliveryAddress() != null) {
                        valueView.setText(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.DeliverySelectionFragment", "access$000", new Object[]{DeliverySelectionFragment.this}).getDeliveryAddress().getFullAddress());
                    } else {
                        valueView.setText(C2658R.string.delivery_label_where_default);
                    }
                    item.setOnClickListener(new C36692());
                }
            }
            Ensighten.getViewReturnValue(item, i);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return item;
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

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("DeliverySelectionFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "DeliverySelectionFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "DeliverySelectionFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
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

    static /* synthetic */ void access$100(DeliverySelectionFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.DeliverySelectionFragment", "access$100", new Object[]{x0});
        x0.onSelectDeliveryTimeAction();
    }

    static /* synthetic */ void access$200(DeliverySelectionFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.start.DeliverySelectionFragment", "access$200", new Object[]{x0});
        x0.onSelectDeliveryAddressAction();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "DeliverySelectionFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "DeliverySelectionFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View view = layoutInflater.inflate(C2658R.layout.fragment_delivery_store_selection, viewGroup, false);
        ((ListView) view.findViewById(C2358R.C2357id.delivery_list_view)).setAdapter(this.mListAdapter);
        TraceMachine.exitMethod();
        return view;
    }

    private void onSelectDeliveryTimeAction() {
        Ensighten.evaluateEvent(this, "onSelectDeliveryTimeAction", null);
        this.mListener.onDisplayDeliveryDateTime();
    }

    private void onSelectDeliveryAddressAction() {
        Ensighten.evaluateEvent(this, "onSelectDeliveryAddressAction", null);
        this.mListener.onDisplayDeliveryAddresses();
    }
}
