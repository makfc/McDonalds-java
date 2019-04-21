package com.mcdonalds.app.p043ui.dateTime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.gma.hongkong.C2658R;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Date;

@Instrumented
/* renamed from: com.mcdonalds.app.ui.dateTime.DateFragment */
public class DateFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private DateChangedListener mCallback;
    private CustomDatePicker mDatePicker;

    /* renamed from: com.mcdonalds.app.ui.dateTime.DateFragment$1 */
    class C38191 implements OnDateChangedListener {
        C38191() {
        }

        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Ensighten.evaluateEvent(this, "onDateChanged", new Object[]{view, new Integer(year), new Integer(monthOfYear), new Integer(dayOfMonth)});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.DateFragment", "access$000", new Object[]{DateFragment.this}).onDateChanged(year, monthOfYear, dayOfMonth);
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.DateFragment$DateChangedListener */
    public interface DateChangedListener {
        void onDateChanged(int i, int i2, int i3);
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
        TraceMachine.startTracing("DateFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "DateFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "DateFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        try {
            this.mCallback = (DateChangedListener) getTargetFragment();
            Ensighten.processView((Object) this, "onCreate");
            TraceMachine.exitMethod();
        } catch (ClassCastException e2) {
            ClassCastException classCastException = new ClassCastException("Calling fragment must implement DateFragment.DateChangedListener interface");
            TraceMachine.exitMethod();
            throw classCastException;
        }
    }

    public static final DateFragment newInstance(int theme, int year, int month, int day, Date minDate, Date maxDate) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.DateFragment", "newInstance", new Object[]{new Integer(theme), new Integer(year), new Integer(month), new Integer(day), minDate, maxDate});
        DateFragment f = new DateFragment();
        Bundle b = new Bundle();
        b.putInt("theme", theme);
        b.putInt("year", year);
        b.putInt("month", month);
        b.putInt("day", day);
        b.putSerializable("minDate", minDate);
        b.putSerializable("maxDate", maxDate);
        f.setArguments(b);
        return f;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "DateFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "DateFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        int theme = getArguments().getInt("theme");
        int initialYear = getArguments().getInt("year");
        int initialMonth = getArguments().getInt("month");
        int initialDay = getArguments().getInt("day");
        Date minDate = (Date) getArguments().getSerializable("minDate");
        Date maxDate = (Date) getArguments().getSerializable("maxDate");
        View v = layoutInflater.cloneInContext(new ContextThemeWrapper(getActivity(), theme == 1 ? 16973931 : 16973934)).inflate(C2658R.layout.fragment_date, viewGroup, false);
        this.mDatePicker = (CustomDatePicker) v.findViewById(C2358R.C2357id.datePicker);
        this.mDatePicker.setDescendantFocusability(393216);
        this.mDatePicker.init(initialYear, initialMonth, initialDay, new C38191());
        if (minDate != null) {
            this.mDatePicker.setMinDate(minDate.getTime());
        }
        if (maxDate != null) {
            this.mDatePicker.setMaxDate(maxDate.getTime());
        }
        TraceMachine.exitMethod();
        return v;
    }
}
