package com.mcdonalds.app.p043ui.dateTime;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
/* renamed from: com.mcdonalds.app.ui.dateTime.TimeFragment */
public class TimeFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private TimeChangedListener mCallback;
    private TimePicker mTimePicker;

    /* renamed from: com.mcdonalds.app.ui.dateTime.TimeFragment$TimeChangedListener */
    public interface TimeChangedListener {
        void onTimeChanged(int i, int i2);
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.TimeFragment$1 */
    class C38221 implements OnTimeChangedListener {
        C38221() {
        }

        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            Ensighten.evaluateEvent(this, "onTimeChanged", new Object[]{view, new Integer(hourOfDay), new Integer(minute)});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.TimeFragment", "access$000", new Object[]{TimeFragment.this}).onTimeChanged(hourOfDay, minute);
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.TimeFragment$2 */
    class C38232 implements OnValueChangeListener {
        C38232() {
        }

        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            Ensighten.evaluateEvent(this, "onValueChange", new Object[]{picker, new Integer(oldVal), new Integer(newVal)});
            if (picker.getValue() == 1) {
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.TimeFragment", "access$100", new Object[]{TimeFragment.this}).getCurrentHour().intValue() < 12) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.TimeFragment", "access$100", new Object[]{TimeFragment.this}).setCurrentHour(Integer.valueOf(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.TimeFragment", "access$100", new Object[]{TimeFragment.this}).getCurrentHour().intValue() + 12));
                }
            } else if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.TimeFragment", "access$100", new Object[]{TimeFragment.this}).getCurrentHour().intValue() >= 12) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.TimeFragment", "access$100", new Object[]{TimeFragment.this}).setCurrentHour(Integer.valueOf(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.TimeFragment", "access$100", new Object[]{TimeFragment.this}).getCurrentHour().intValue() - 12));
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.TimeFragment", "access$000", new Object[]{TimeFragment.this}).onTimeChanged(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.TimeFragment", "access$100", new Object[]{TimeFragment.this}).getCurrentHour().intValue(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.TimeFragment", "access$100", new Object[]{TimeFragment.this}).getCurrentMinute().intValue());
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
        TraceMachine.startTracing("TimeFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "TimeFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TimeFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        try {
            this.mCallback = (TimeChangedListener) getTargetFragment();
            Ensighten.processView((Object) this, "onCreate");
            TraceMachine.exitMethod();
        } catch (ClassCastException e2) {
            ClassCastException classCastException = new ClassCastException("Calling fragment must implement TimeFragment.TimeChangedListener interface");
            TraceMachine.exitMethod();
            throw classCastException;
        }
    }

    public static final TimeFragment newInstance(int theme, int hour, int minute, boolean isClientSpecified24HourTime, boolean is24HourTime) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.TimeFragment", "newInstance", new Object[]{new Integer(theme), new Integer(hour), new Integer(minute), new Boolean(isClientSpecified24HourTime), new Boolean(is24HourTime)});
        TimeFragment f = new TimeFragment();
        Bundle b = new Bundle();
        b.putInt("theme", theme);
        b.putInt("hour", hour);
        b.putInt("minute", minute);
        b.putBoolean("isClientSpecified24HourTime", isClientSpecified24HourTime);
        b.putBoolean("is24HourTime", is24HourTime);
        f.setArguments(b);
        return f;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "TimeFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TimeFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        int theme = getArguments().getInt("theme");
        int initialHour = getArguments().getInt("hour");
        int initialMinute = getArguments().getInt("minute");
        boolean isClientSpecified24HourTime = getArguments().getBoolean("isClientSpecified24HourTime");
        boolean is24HourTime = getArguments().getBoolean("is24HourTime");
        View v = layoutInflater.cloneInContext(new ContextThemeWrapper(getActivity(), theme == 1 ? 16973931 : 16973934)).inflate(C2658R.layout.fragment_time, viewGroup, false);
        this.mTimePicker = (TimePicker) v.findViewById(C2358R.C2357id.timePicker);
        this.mTimePicker.setDescendantFocusability(393216);
        this.mTimePicker.setOnTimeChangedListener(new C38221());
        if (isClientSpecified24HourTime) {
            this.mTimePicker.setIs24HourView(Boolean.valueOf(is24HourTime));
        } else {
            this.mTimePicker.setIs24HourView(Boolean.valueOf(DateFormat.is24HourFormat(getTargetFragment().getActivity())));
        }
        this.mTimePicker.setCurrentHour(Integer.valueOf(initialHour));
        this.mTimePicker.setCurrentMinute(Integer.valueOf(initialMinute));
        if (VERSION.SDK_INT >= 14 && VERSION.SDK_INT <= 15) {
            fixTimePickerBug18982();
        }
        TraceMachine.exitMethod();
        return v;
    }

    private void fixTimePickerBug18982() {
        Ensighten.evaluateEvent(this, "fixTimePickerBug18982", null);
        View amPmView = ((ViewGroup) this.mTimePicker.getChildAt(0)).getChildAt(3);
        if (amPmView instanceof NumberPicker) {
            ((NumberPicker) amPmView).setOnValueChangedListener(new C38232());
        }
    }
}
