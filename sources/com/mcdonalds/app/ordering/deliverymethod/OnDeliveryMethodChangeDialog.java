package com.mcdonalds.app.ordering.deliverymethod;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.gma.hongkong.C2658R;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
public class OnDeliveryMethodChangeDialog extends DialogFragment implements TraceFieldInterface {
    public Trace _nr_trace;
    OnDeliveryMethodChangeDialogListener mListener;

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OnDeliveryMethodChangeDialog$1 */
    class C34721 implements OnClickListener {
        C34721() {
        }

        public void onClick(DialogInterface dialog, int id) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(id)});
            OnDeliveryMethodChangeDialog.this.mListener.onDialogNegativeClick(OnDeliveryMethodChangeDialog.this);
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.deliverymethod.OnDeliveryMethodChangeDialog$2 */
    class C34732 implements OnClickListener {
        C34732() {
        }

        public void onClick(DialogInterface dialog, int id) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(id)});
            OnDeliveryMethodChangeDialog.this.mListener.onDialogPositiveClick(OnDeliveryMethodChangeDialog.this);
        }
    }

    public interface OnDeliveryMethodChangeDialogListener {
        void onDialogNegativeClick(DialogFragment dialogFragment);

        void onDialogPositiveClick(DialogFragment dialogFragment);
    }

    public void onActivityCreated(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{bundle});
        super.onActivityCreated(bundle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("OnDeliveryMethodChangeDialog");
        try {
            TraceMachine.enterMethod(this._nr_trace, "OnDeliveryMethodChangeDialog#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "OnDeliveryMethodChangeDialog#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "OnDeliveryMethodChangeDialog#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "OnDeliveryMethodChangeDialog#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        TraceMachine.exitMethod();
        return onCreateView;
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

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Ensighten.evaluateEvent(this, "onCreateDialog", new Object[]{savedInstanceState});
        Builder builder = new Builder(getActivity());
        builder.setMessage("The cart will be cleared when you change delivery method. Do you want to conitnue").setPositiveButton(C2658R.string.continue_button, new C34732()).setNegativeButton(C2658R.string.cancel, new C34721());
        return builder.create();
    }

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
        try {
            this.mListener = (OnDeliveryMethodChangeDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }
}
