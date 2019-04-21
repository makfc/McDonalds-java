package com.mcdonalds.app.p043ui.dateTime;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.dateTime.DateFragment.DateChangedListener;
import com.mcdonalds.app.p043ui.dateTime.TimeFragment.TimeChangedListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Calendar;
import java.util.Date;

@Instrumented
/* renamed from: com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment */
public class SlideDateTimeDialogFragment extends DialogFragment implements DateChangedListener, TimeChangedListener, TraceFieldInterface {
    private static SlideDateTimeListener mListener;
    public Trace _nr_trace;
    private View mButtonHorizontalDivider;
    private View mButtonVerticalDivider;
    private Calendar mCalendar;
    private Button mCancelButton;
    private Context mContext;
    private int mDateFlags = 524306;
    private int mIndicatorColor;
    private Date mInitialDate;
    private boolean mIs24HourTime;
    private boolean mIsClientSpecified24HourTime;
    private Date mMaxDate;
    private Date mMinDate;
    private Button mOkButton;
    private int mTheme;
    private CustomViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    /* renamed from: com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment$1 */
    class C38201 implements OnClickListener {
        C38201() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$000", null) == null) {
                throw new NullPointerException("Listener no longer exists for mOkButton");
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$000", null).onDateTimeSet(new Date(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$100", new Object[]{SlideDateTimeDialogFragment.this}).getTimeInMillis()));
            SlideDateTimeDialogFragment.this.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment$2 */
    class C38212 implements OnClickListener {
        C38212() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$000", null) == null) {
                throw new NullPointerException("Listener no longer exists for mCancelButton");
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$000", null).onDateTimeCancel();
            SlideDateTimeDialogFragment.this.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment$ViewPagerAdapter */
    private class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
            switch (position) {
                case 0:
                    Fragment dateFragment = DateFragment.newInstance(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$200", new Object[]{SlideDateTimeDialogFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$100", new Object[]{SlideDateTimeDialogFragment.this}).get(1), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$100", new Object[]{SlideDateTimeDialogFragment.this}).get(2), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$100", new Object[]{SlideDateTimeDialogFragment.this}).get(5), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$300", new Object[]{SlideDateTimeDialogFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$400", new Object[]{SlideDateTimeDialogFragment.this}));
                    dateFragment.setTargetFragment(SlideDateTimeDialogFragment.this, 100);
                    return dateFragment;
                case 1:
                    Fragment timeFragment = TimeFragment.newInstance(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$200", new Object[]{SlideDateTimeDialogFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$100", new Object[]{SlideDateTimeDialogFragment.this}).get(11), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$100", new Object[]{SlideDateTimeDialogFragment.this}).get(12), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$500", new Object[]{SlideDateTimeDialogFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.SlideDateTimeDialogFragment", "access$600", new Object[]{SlideDateTimeDialogFragment.this}));
                    timeFragment.setTargetFragment(SlideDateTimeDialogFragment.this, 200);
                    return timeFragment;
                default:
                    return null;
            }
        }

        public int getCount() {
            Ensighten.evaluateEvent(this, "getCount", null);
            return 1;
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

    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
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

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
        this.mContext = activity;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("SlideDateTimeDialogFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "SlideDateTimeDialogFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "SlideDateTimeDialogFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        setRetainInstance(true);
        unpackBundle();
        this.mCalendar = Calendar.getInstance();
        this.mCalendar.setTime(this.mInitialDate);
        switch (this.mTheme) {
            case 1:
                setStyle(1, 16973937);
                break;
            case 2:
                setStyle(1, 16973941);
                break;
            default:
                setStyle(1, 16973941);
                break;
        }
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "SlideDateTimeDialogFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "SlideDateTimeDialogFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View view = layoutInflater.inflate(C2658R.layout.slide_date_time_picker, viewGroup);
        setupViews(view);
        customizeViews();
        initViewPager();
        initButtons();
        TraceMachine.exitMethod();
        return view;
    }

    public void onDestroyView() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroyView", null);
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    private void unpackBundle() {
        Ensighten.evaluateEvent(this, "unpackBundle", null);
        Bundle args = getArguments();
        this.mInitialDate = (Date) args.getSerializable("initialDate");
        this.mMinDate = (Date) args.getSerializable("minDate");
        this.mMaxDate = (Date) args.getSerializable("maxDate");
        this.mIsClientSpecified24HourTime = args.getBoolean("isClientSpecified24HourTime");
        this.mIs24HourTime = args.getBoolean("is24HourTime");
        this.mTheme = args.getInt("theme");
        this.mIndicatorColor = args.getInt("indicatorColor");
    }

    private void setupViews(View v) {
        Ensighten.evaluateEvent(this, "setupViews", new Object[]{v});
        this.mViewPager = (CustomViewPager) v.findViewById(C2358R.C2357id.viewPager);
        this.mButtonHorizontalDivider = v.findViewById(C2358R.C2357id.buttonHorizontalDivider);
        this.mButtonVerticalDivider = v.findViewById(C2358R.C2357id.buttonVerticalDivider);
        this.mOkButton = (Button) v.findViewById(C2358R.C2357id.okButton);
        this.mCancelButton = (Button) v.findViewById(C2358R.C2357id.cancelButton);
    }

    private void customizeViews() {
        int lineColor;
        Ensighten.evaluateEvent(this, "customizeViews", null);
        if (this.mTheme == 1) {
            lineColor = getResources().getColor(C2658R.color.dark_gray_1);
        } else {
            lineColor = getResources().getColor(C2658R.color.dark_gray_2);
        }
        switch (this.mTheme) {
            case 1:
            case 2:
                this.mButtonHorizontalDivider.setBackgroundColor(lineColor);
                this.mButtonVerticalDivider.setBackgroundColor(lineColor);
                return;
            default:
                this.mButtonHorizontalDivider.setBackgroundColor(getResources().getColor(C2658R.color.dark_gray_1));
                this.mButtonVerticalDivider.setBackgroundColor(getResources().getColor(C2658R.color.dark_gray_1));
                return;
        }
    }

    private void initViewPager() {
        Ensighten.evaluateEvent(this, "initViewPager", null);
        this.mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        this.mViewPager.setAdapter(this.mViewPagerAdapter);
    }

    private void initButtons() {
        Ensighten.evaluateEvent(this, "initButtons", null);
        this.mOkButton.setOnClickListener(new C38201());
        this.mCancelButton.setOnClickListener(new C38212());
    }

    public void onDateChanged(int year, int month, int day) {
        Ensighten.evaluateEvent(this, "onDateChanged", new Object[]{new Integer(year), new Integer(month), new Integer(day)});
        this.mCalendar.set(year, month, day);
    }

    public void onCancel(DialogInterface dialog) {
        Ensighten.evaluateEvent(this, "onCancel", new Object[]{dialog});
        super.onCancel(dialog);
        if (mListener == null) {
            throw new NullPointerException("Listener no longer exists in onCancel()");
        }
        mListener.onDateTimeCancel();
    }

    public void onTimeChanged(int hour, int minute) {
        Ensighten.evaluateEvent(this, "onTimeChanged", new Object[]{new Integer(hour), new Integer(minute)});
        this.mCalendar.set(11, hour);
        this.mCalendar.set(12, minute);
    }
}
