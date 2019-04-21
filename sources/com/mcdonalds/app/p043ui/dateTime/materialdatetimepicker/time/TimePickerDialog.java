package com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.HapticFeedbackController;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.Utils;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.RadialPickerLayout.OnValueSelectedListener;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.Timepoint.TYPE;
import com.mcdonalds.gma.hongkong.C2658R;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

@Instrumented
/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog */
public class TimePickerDialog extends DialogFragment implements OnValueSelectedListener, TimePickerController, TraceFieldInterface {
    public Trace _nr_trace;
    private int mAccentColor = -1;
    private boolean mAllowAutoAdvance;
    private int mAmKeyCode;
    private View mAmPmLayout;
    private String mAmText;
    private TextView mAmTextView;
    private OnTimeSetListener mCallback;
    private Button mCancelButton;
    private int mCancelColor;
    private int mCancelResid;
    private String mCancelString;
    private DefaultTimepointLimiter mDefaultLimiter = new DefaultTimepointLimiter();
    private String mDeletedKeyFormat;
    private boolean mDismissOnPause;
    private String mDoublePlaceholderText;
    private boolean mEnableMinutes;
    private boolean mEnableSeconds;
    private HapticFeedbackController mHapticFeedbackController;
    private String mHourPickerDescription;
    private TextView mHourSpaceView;
    private TextView mHourView;
    private boolean mInKbMode;
    private String mInitialMessage;
    private Timepoint mInitialTime;
    private boolean mIs24HourMode;
    private Node mLegalTimesTree;
    private TimepointLimiter mLimiter = this.mDefaultLimiter;
    private String mMinutePickerDescription;
    private TextView mMinuteSpaceView;
    private TextView mMinuteView;
    private Button mOkButton;
    private int mOkColor;
    private int mOkResid;
    private String mOkString;
    private OnCancelListener mOnCancelListener;
    private OnDismissListener mOnDismissListener;
    private char mPlaceholderText;
    private int mPmKeyCode;
    private String mPmText;
    private TextView mPmTextView;
    private String mSecondPickerDescription;
    private TextView mSecondSpaceView;
    private TextView mSecondView;
    private String mSelectHours;
    private String mSelectMinutes;
    private String mSelectSeconds;
    private int mSelectedColor;
    private boolean mThemeDark;
    private boolean mThemeDarkChanged;
    private OnTimeChangedListener mTimeChangedListener;
    private RadialPickerLayout mTimePicker;
    private String mTitle;
    private ArrayList<Integer> mTypedTimes;
    private int mUnselectedColor;
    private Version mVersion;
    private boolean mVibrate;
    private TextView timePickerHeader;

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog$OnTimeSetListener */
    public interface OnTimeSetListener {
        void onTimeSet(TimePickerDialog timePickerDialog, int i, int i2, int i3);
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog$OnTimeChangedListener */
    public interface OnTimeChangedListener {
        void onTimeChanged(int i, int i2);
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog$1 */
    class C38331 implements OnClickListener {
        C38331() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            TimePickerDialog.access$100(TimePickerDialog.this, 0, true, false, true);
            TimePickerDialog.this.tryVibrate();
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog$2 */
    class C38342 implements OnClickListener {
        C38342() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            TimePickerDialog.access$100(TimePickerDialog.this, 1, true, false, true);
            TimePickerDialog.this.tryVibrate();
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog$3 */
    class C38353 implements OnClickListener {
        C38353() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            TimePickerDialog.access$100(TimePickerDialog.this, 2, true, false, true);
            TimePickerDialog.this.tryVibrate();
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog$4 */
    class C38364 implements OnClickListener {
        C38364() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog", "access$200", new Object[]{TimePickerDialog.this}) && Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog", "access$300", new Object[]{TimePickerDialog.this})) {
                TimePickerDialog.access$400(TimePickerDialog.this, false);
            } else {
                TimePickerDialog.this.tryVibrate();
            }
            TimePickerDialog.this.notifyOnDateListener();
            TimePickerDialog.this.dismiss();
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog$5 */
    class C38375 implements OnClickListener {
        C38375() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            TimePickerDialog.this.tryVibrate();
            if (TimePickerDialog.this.getDialog() != null) {
                TimePickerDialog.this.getDialog().cancel();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog$6 */
    class C38386 implements OnClickListener {
        C38386() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            if (!TimePickerDialog.this.isAmDisabled() && !TimePickerDialog.this.isPmDisabled()) {
                TimePickerDialog.this.tryVibrate();
                int amOrPm = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog", "access$500", new Object[]{TimePickerDialog.this}).getIsCurrentlyAmOrPm();
                if (amOrPm == 0) {
                    amOrPm = 1;
                } else if (amOrPm == 1) {
                    amOrPm = 0;
                }
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog", "access$500", new Object[]{TimePickerDialog.this}).setAmOrPm(amOrPm);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog$KeyboardListener */
    private class KeyboardListener implements OnKeyListener {
        private KeyboardListener() {
        }

        /* synthetic */ KeyboardListener(TimePickerDialog x0, C38331 x1) {
            this();
        }

        public boolean onKey(View v, int keyCode, KeyEvent event) {
            Ensighten.evaluateEvent(this, "onKey", new Object[]{v, new Integer(keyCode), event});
            if (event.getAction() == 1) {
                return Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog", "access$600", new Object[]{TimePickerDialog.this, new Integer(keyCode)});
            }
            return false;
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog$Node */
    private static class Node {
        private ArrayList<Node> mChildren = new ArrayList();
        private int[] mLegalKeys;

        public Node(int... legalKeys) {
            this.mLegalKeys = legalKeys;
        }

        public void addChild(Node child) {
            Ensighten.evaluateEvent(this, "addChild", new Object[]{child});
            this.mChildren.add(child);
        }

        public boolean containsKey(int key) {
            Ensighten.evaluateEvent(this, "containsKey", new Object[]{new Integer(key)});
            for (int legalKey : this.mLegalKeys) {
                if (legalKey == key) {
                    return true;
                }
            }
            return false;
        }

        public Node canReach(int key) {
            Ensighten.evaluateEvent(this, "canReach", new Object[]{new Integer(key)});
            if (this.mChildren == null) {
                return null;
            }
            Iterator it = this.mChildren.iterator();
            while (it.hasNext()) {
                Node child = (Node) it.next();
                if (child.containsKey(key)) {
                    return child;
                }
            }
            return null;
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog$Version */
    public enum Version {
        VERSION_1,
        VERSION_2
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

    static /* synthetic */ void access$100(TimePickerDialog x0, int x1, boolean x2, boolean x3, boolean x4) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog", "access$100", new Object[]{x0, new Integer(x1), new Boolean(x2), new Boolean(x3), new Boolean(x4)});
        x0.setCurrentItemShowing(x1, x2, x3, x4);
    }

    static /* synthetic */ void access$400(TimePickerDialog x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog", "access$400", new Object[]{x0, new Boolean(x1)});
        x0.finishKbMode(x1);
    }

    public static TimePickerDialog newInstance(OnTimeSetListener callback, OnTimeChangedListener timeChangedListener, int hourOfDay, int minute, int second, boolean is24HourMode) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog", "newInstance", new Object[]{callback, timeChangedListener, new Integer(hourOfDay), new Integer(minute), new Integer(second), new Boolean(is24HourMode)});
        TimePickerDialog ret = new TimePickerDialog();
        ret.initialize(callback, timeChangedListener, hourOfDay, minute, second, is24HourMode);
        return ret;
    }

    public static TimePickerDialog newInstance(OnTimeSetListener callback, OnTimeChangedListener timeChangedListener, int hourOfDay, int minute, boolean is24HourMode) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog", "newInstance", new Object[]{callback, timeChangedListener, new Integer(hourOfDay), new Integer(minute), new Boolean(is24HourMode)});
        return TimePickerDialog.newInstance(callback, timeChangedListener, hourOfDay, minute, 0, is24HourMode);
    }

    public void initialize(OnTimeSetListener callback, OnTimeChangedListener timeChangedListener, int hourOfDay, int minute, int second, boolean is24HourMode) {
        Ensighten.evaluateEvent(this, "initialize", new Object[]{callback, timeChangedListener, new Integer(hourOfDay), new Integer(minute), new Integer(second), new Boolean(is24HourMode)});
        this.mCallback = callback;
        this.mTimeChangedListener = timeChangedListener;
        this.mInitialTime = new Timepoint(hourOfDay, minute, second);
        this.mIs24HourMode = is24HourMode;
        this.mInKbMode = false;
        this.mTitle = "";
        this.mThemeDark = false;
        this.mThemeDarkChanged = false;
        this.mAccentColor = -1;
        this.mVibrate = true;
        this.mDismissOnPause = false;
        this.mEnableSeconds = false;
        this.mEnableMinutes = true;
        this.mOkResid = C2658R.string.mdtp_ok;
        this.mOkColor = -1;
        this.mCancelResid = C2658R.string.mdtp_cancel;
        this.mCancelColor = -1;
        this.mVersion = VERSION.SDK_INT < 23 ? Version.VERSION_1 : Version.VERSION_2;
        this.mTimePicker = null;
    }

    public boolean isThemeDark() {
        Ensighten.evaluateEvent(this, "isThemeDark", null);
        return this.mThemeDark;
    }

    public boolean is24HourMode() {
        Ensighten.evaluateEvent(this, "is24HourMode", null);
        return this.mIs24HourMode;
    }

    public int getAccentColor() {
        Ensighten.evaluateEvent(this, "getAccentColor", null);
        return this.mAccentColor;
    }

    public Version getVersion() {
        Ensighten.evaluateEvent(this, "getVersion", null);
        return this.mVersion;
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("TimePickerDialog");
        try {
            TraceMachine.enterMethod(this._nr_trace, "TimePickerDialog#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TimePickerDialog#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        if (bundle != null && bundle.containsKey("initial_time") && bundle.containsKey("is_24_hour_view")) {
            this.mInitialTime = (Timepoint) bundle.getParcelable("initial_time");
            this.mIs24HourMode = bundle.getBoolean("is_24_hour_view");
            this.mInKbMode = bundle.getBoolean("in_kb_mode");
            this.mTitle = bundle.getString("dialog_title");
            this.mThemeDark = bundle.getBoolean("theme_dark");
            this.mThemeDarkChanged = bundle.getBoolean("theme_dark_changed");
            this.mAccentColor = bundle.getInt("accent");
            this.mVibrate = bundle.getBoolean("vibrate");
            this.mDismissOnPause = bundle.getBoolean("dismiss");
            this.mEnableSeconds = bundle.getBoolean("enable_seconds");
            this.mEnableMinutes = bundle.getBoolean("enable_minutes");
            this.mOkResid = bundle.getInt("ok_resid");
            this.mOkString = bundle.getString("ok_string");
            this.mOkColor = bundle.getInt("ok_color");
            this.mCancelResid = bundle.getInt("cancel_resid");
            this.mCancelString = bundle.getString("cancel_string");
            this.mCancelColor = bundle.getInt("cancel_color");
            this.mVersion = (Version) bundle.getSerializable("version");
            this.mLimiter = (TimepointLimiter) bundle.getParcelable("timepoint_limiter");
            this.mDefaultLimiter = this.mLimiter instanceof DefaultTimepointLimiter ? (DefaultTimepointLimiter) this.mLimiter : new DefaultTimepointLimiter();
        }
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "TimePickerDialog#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "TimePickerDialog#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View view = layoutInflater.inflate(C2658R.layout.mdtp_time_picker_dialog, viewGroup, false);
        KeyboardListener keyboardListener = new KeyboardListener(this, null);
        view.findViewById(C2358R.C2357id.mdtp_time_picker_dialog).setOnKeyListener(keyboardListener);
        if (this.mAccentColor == -1) {
            this.mAccentColor = Utils.getAccentColorFromThemeIfAvailable(getActivity());
        }
        Resources res = getResources();
        Context context = getActivity();
        this.mHourPickerDescription = res.getString(C2658R.string.mdtp_hour_picker_description);
        this.mSelectHours = res.getString(C2658R.string.mdtp_select_hours);
        this.mMinutePickerDescription = res.getString(C2658R.string.mdtp_minute_picker_description);
        this.mSelectMinutes = res.getString(C2658R.string.mdtp_select_minutes);
        this.mSecondPickerDescription = res.getString(C2658R.string.mdtp_second_picker_description);
        this.mSelectSeconds = res.getString(C2658R.string.mdtp_select_seconds);
        this.mSelectedColor = ContextCompat.getColor(context, C2658R.color.mdtp_white);
        this.mUnselectedColor = ContextCompat.getColor(context, C2658R.color.mdtp_accent_color_focused);
        this.mHourView = (TextView) view.findViewById(C2358R.C2357id.mdtp_hours);
        this.mHourView.setOnKeyListener(keyboardListener);
        this.mHourSpaceView = (TextView) view.findViewById(C2358R.C2357id.mdtp_hour_space);
        this.mMinuteSpaceView = (TextView) view.findViewById(C2358R.C2357id.mdtp_minutes_space);
        this.mMinuteView = (TextView) view.findViewById(C2358R.C2357id.mdtp_minutes);
        this.mMinuteView.setOnKeyListener(keyboardListener);
        this.mSecondSpaceView = (TextView) view.findViewById(C2358R.C2357id.mdtp_seconds_space);
        this.mSecondView = (TextView) view.findViewById(C2358R.C2357id.mdtp_seconds);
        this.mSecondView.setOnKeyListener(keyboardListener);
        this.mAmTextView = (TextView) view.findViewById(C2358R.C2357id.mdtp_am_label);
        this.mAmTextView.setOnKeyListener(keyboardListener);
        this.mPmTextView = (TextView) view.findViewById(C2358R.C2357id.mdtp_pm_label);
        this.mPmTextView.setOnKeyListener(keyboardListener);
        this.mAmPmLayout = view.findViewById(C2358R.C2357id.mdtp_ampm_layout);
        String[] amPmTexts = new DateFormatSymbols().getAmPmStrings();
        this.mAmText = amPmTexts[0];
        this.mPmText = amPmTexts[1];
        this.mHapticFeedbackController = new HapticFeedbackController(getActivity());
        if (this.mTimePicker != null) {
            this.mInitialTime = new Timepoint(this.mTimePicker.getHours(), this.mTimePicker.getMinutes(), this.mTimePicker.getSeconds());
        }
        this.mInitialTime = roundToNearest(this.mInitialTime);
        this.mTimePicker = (RadialPickerLayout) view.findViewById(C2358R.C2357id.mdtp_time_picker);
        this.mTimePicker.setOnValueSelectedListener(this);
        this.mTimePicker.setOnKeyListener(keyboardListener);
        this.mTimePicker.initialize(getActivity(), this, this.mInitialTime, this.mIs24HourMode);
        int currentItemShowing = 0;
        if (bundle != null && bundle.containsKey("current_item_showing")) {
            currentItemShowing = bundle.getInt("current_item_showing");
        }
        setCurrentItemShowing(currentItemShowing, false, true, true);
        this.mTimePicker.invalidate();
        this.mHourView.setOnClickListener(new C38331());
        this.mMinuteView.setOnClickListener(new C38342());
        this.mSecondView.setOnClickListener(new C38353());
        this.mOkButton = (Button) view.findViewById(C2358R.C2357id.mdtp_ok);
        this.mOkButton.setOnClickListener(new C38364());
        this.mOkButton.setOnKeyListener(keyboardListener);
        if (this.mOkString != null) {
            this.mOkButton.setText(this.mOkString);
        } else {
            this.mOkButton.setText(this.mOkResid);
        }
        this.mCancelButton = (Button) view.findViewById(C2358R.C2357id.mdtp_cancel);
        this.mCancelButton.setOnClickListener(new C38375());
        if (this.mCancelString != null) {
            this.mCancelButton.setText(this.mCancelString);
        } else {
            this.mCancelButton.setText(this.mCancelResid);
        }
        this.mCancelButton.setVisibility(isCancelable() ? 0 : 8);
        if (this.mIs24HourMode) {
            this.mAmPmLayout.setVisibility(8);
        } else {
            OnClickListener listener = new C38386();
            this.mAmTextView.setVisibility(8);
            this.mPmTextView.setVisibility(0);
            this.mAmPmLayout.setOnClickListener(listener);
            if (this.mVersion == Version.VERSION_2) {
                this.mAmTextView.setText(this.mAmText);
                this.mPmTextView.setText(this.mPmText);
                this.mAmTextView.setVisibility(0);
            }
            updateAmPmDisplay(this.mInitialTime.isAM() ? 0 : 1);
        }
        if (!this.mEnableSeconds) {
            this.mSecondView.setVisibility(8);
            view.findViewById(C2358R.C2357id.mdtp_separator_seconds).setVisibility(8);
        }
        if (!this.mEnableMinutes) {
            this.mMinuteSpaceView.setVisibility(8);
            view.findViewById(C2358R.C2357id.mdtp_separator).setVisibility(8);
        }
        LayoutParams layoutParams;
        RelativeLayout.LayoutParams paramsAmPm;
        if (getResources().getConfiguration().orientation == 2) {
            if (!this.mEnableMinutes && !this.mEnableSeconds) {
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(2, C2358R.C2357id.mdtp_center_view);
                layoutParams.addRule(14);
                this.mHourSpaceView.setLayoutParams(layoutParams);
                if (this.mIs24HourMode) {
                    paramsAmPm = new RelativeLayout.LayoutParams(-2, -2);
                    paramsAmPm.addRule(1, C2358R.C2357id.mdtp_hour_space);
                    this.mAmPmLayout.setLayoutParams(paramsAmPm);
                }
            } else if (!this.mEnableSeconds && this.mIs24HourMode) {
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(14);
                layoutParams.addRule(2, C2358R.C2357id.mdtp_center_view);
                ((TextView) view.findViewById(C2358R.C2357id.mdtp_separator)).setLayoutParams(layoutParams);
            } else if (!this.mEnableSeconds) {
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(14);
                layoutParams.addRule(2, C2358R.C2357id.mdtp_center_view);
                ((TextView) view.findViewById(C2358R.C2357id.mdtp_separator)).setLayoutParams(layoutParams);
                paramsAmPm = new RelativeLayout.LayoutParams(-2, -2);
                paramsAmPm.addRule(13);
                paramsAmPm.addRule(3, C2358R.C2357id.mdtp_center_view);
                this.mAmPmLayout.setLayoutParams(paramsAmPm);
            } else if (this.mIs24HourMode) {
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(14);
                layoutParams.addRule(2, C2358R.C2357id.mdtp_seconds_space);
                ((TextView) view.findViewById(C2358R.C2357id.mdtp_separator)).setLayoutParams(layoutParams);
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(13);
                this.mSecondSpaceView.setLayoutParams(layoutParams);
            } else {
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(13);
                this.mSecondSpaceView.setLayoutParams(layoutParams);
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(14);
                layoutParams.addRule(2, C2358R.C2357id.mdtp_seconds_space);
                ((TextView) view.findViewById(C2358R.C2357id.mdtp_separator)).setLayoutParams(layoutParams);
                paramsAmPm = new RelativeLayout.LayoutParams(-2, -2);
                paramsAmPm.addRule(14);
                paramsAmPm.addRule(3, C2358R.C2357id.mdtp_seconds_space);
                this.mAmPmLayout.setLayoutParams(paramsAmPm);
            }
        } else if (this.mIs24HourMode && !this.mEnableSeconds && this.mEnableMinutes) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            ((TextView) view.findViewById(C2358R.C2357id.mdtp_separator)).setLayoutParams(layoutParams);
        } else if (!this.mEnableMinutes && !this.mEnableSeconds) {
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.mHourSpaceView.setLayoutParams(layoutParams);
            if (!this.mIs24HourMode) {
                paramsAmPm = new RelativeLayout.LayoutParams(-2, -2);
                paramsAmPm.addRule(1, C2358R.C2357id.mdtp_hour_space);
                paramsAmPm.addRule(4, C2358R.C2357id.mdtp_hour_space);
                this.mAmPmLayout.setLayoutParams(paramsAmPm);
            }
        } else if (this.mEnableSeconds) {
            View separator = view.findViewById(C2358R.C2357id.mdtp_separator);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(0, C2358R.C2357id.mdtp_minutes_space);
            layoutParams.addRule(15, -1);
            separator.setLayoutParams(layoutParams);
            if (this.mIs24HourMode) {
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(1, C2358R.C2357id.mdtp_center_view);
                this.mMinuteSpaceView.setLayoutParams(layoutParams);
            } else {
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(13);
                this.mMinuteSpaceView.setLayoutParams(layoutParams);
            }
        }
        this.mAllowAutoAdvance = true;
        setHour(this.mInitialTime.getHour(), true);
        setMinute(this.mInitialTime.getMinute());
        setSecond(this.mInitialTime.getSecond());
        this.mDoublePlaceholderText = res.getString(C2658R.string.mdtp_time_placeholder);
        this.mDeletedKeyFormat = res.getString(C2658R.string.mdtp_deleted_key);
        this.mPlaceholderText = this.mDoublePlaceholderText.charAt(0);
        this.mPmKeyCode = -1;
        this.mAmKeyCode = -1;
        generateLegalTimesTree();
        if (this.mInKbMode) {
            this.mTypedTimes = bundle.getIntegerArrayList("typed_times");
            tryStartingKbMode(-1);
            this.mHourView.invalidate();
        } else if (this.mTypedTimes == null) {
            this.mTypedTimes = new ArrayList();
        }
        this.timePickerHeader = (TextView) view.findViewById(C2358R.C2357id.mdtp_time_picker_header);
        this.timePickerHeader.setVisibility(0);
        view.findViewById(C2358R.C2357id.mdtp_time_display_background).setBackgroundColor(this.mAccentColor);
        view.findViewById(C2358R.C2357id.mdtp_time_display).setBackgroundColor(this.mAccentColor);
        if (getDialog() == null) {
            view.findViewById(C2358R.C2357id.mdtp_done_background).setVisibility(8);
        }
        int circleBackground = ContextCompat.getColor(context, C2658R.color.mdtp_circle_background);
        int backgroundColor = ContextCompat.getColor(context, C2658R.color.mdtp_background_color);
        int darkBackgroundColor = ContextCompat.getColor(context, C2658R.color.mdtp_light_gray);
        int lightGray = ContextCompat.getColor(context, C2658R.color.mdtp_light_gray);
        RadialPickerLayout radialPickerLayout = this.mTimePicker;
        if (!this.mThemeDark) {
            lightGray = circleBackground;
        }
        radialPickerLayout.setBackgroundColor(lightGray);
        View findViewById = view.findViewById(C2358R.C2357id.mdtp_time_picker_dialog);
        if (!this.mThemeDark) {
            darkBackgroundColor = backgroundColor;
        }
        findViewById.setBackgroundColor(darkBackgroundColor);
        setTitlebutton(this.mInitialMessage);
        TraceMachine.exitMethod();
        return view;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        Ensighten.evaluateEvent(this, "onConfigurationChanged", new Object[]{newConfig});
        super.onConfigurationChanged(newConfig);
        ViewGroup viewGroup = (ViewGroup) getView();
        if (viewGroup != null) {
            viewGroup.removeAllViewsInLayout();
            viewGroup.addView(onCreateView(getActivity().getLayoutInflater(), viewGroup, null));
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Ensighten.evaluateEvent(this, "onCreateDialog", new Object[]{savedInstanceState});
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(1);
        return dialog;
    }

    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        this.mHapticFeedbackController.start();
        Ensighten.processView((Object) this, "onResume");
    }

    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        this.mHapticFeedbackController.stop();
        if (this.mDismissOnPause) {
            dismiss();
        }
        Ensighten.processView((Object) this, "onPause");
    }

    public void onCancel(DialogInterface dialog) {
        Ensighten.evaluateEvent(this, "onCancel", new Object[]{dialog});
        super.onCancel(dialog);
        if (this.mOnCancelListener != null) {
            this.mOnCancelListener.onCancel(dialog);
        }
    }

    public void onDismiss(DialogInterface dialog) {
        Ensighten.evaluateEvent(this, "onDismiss", new Object[]{dialog});
        super.onDismiss(dialog);
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss(dialog);
        }
    }

    public void tryVibrate() {
        Ensighten.evaluateEvent(this, "tryVibrate", null);
        if (this.mVibrate) {
            this.mHapticFeedbackController.tryVibrate();
        }
    }

    private void updateAmPmDisplay(int amOrPm) {
        Ensighten.evaluateEvent(this, "updateAmPmDisplay", new Object[]{new Integer(amOrPm)});
        if (this.mVersion == Version.VERSION_2) {
            if (amOrPm == 0) {
                this.mAmTextView.setTextColor(this.mSelectedColor);
                this.mPmTextView.setTextColor(this.mUnselectedColor);
                Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mAmText);
                return;
            }
            this.mAmTextView.setTextColor(this.mUnselectedColor);
            this.mPmTextView.setTextColor(this.mSelectedColor);
            Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mPmText);
        } else if (amOrPm == 0) {
            this.mPmTextView.setText(this.mAmText);
            Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mAmText);
            this.mPmTextView.setContentDescription(this.mAmText);
        } else if (amOrPm == 1) {
            this.mPmTextView.setText(this.mPmText);
            Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mPmText);
            this.mPmTextView.setContentDescription(this.mPmText);
        } else {
            this.mPmTextView.setText(this.mDoublePlaceholderText);
        }
    }

    public void onSaveInstanceState(@NonNull Bundle outState) {
        Ensighten.evaluateEvent(this, "onSaveInstanceState", new Object[]{outState});
        if (this.mTimePicker != null) {
            outState.putParcelable("initial_time", this.mTimePicker.getTime());
            outState.putBoolean("is_24_hour_view", this.mIs24HourMode);
            outState.putInt("current_item_showing", this.mTimePicker.getCurrentItemShowing());
            outState.putBoolean("in_kb_mode", this.mInKbMode);
            if (this.mInKbMode) {
                outState.putIntegerArrayList("typed_times", this.mTypedTimes);
            }
            outState.putString("dialog_title", this.mTitle);
            outState.putBoolean("theme_dark", this.mThemeDark);
            outState.putBoolean("theme_dark_changed", this.mThemeDarkChanged);
            outState.putInt("accent", this.mAccentColor);
            outState.putBoolean("vibrate", this.mVibrate);
            outState.putBoolean("dismiss", this.mDismissOnPause);
            outState.putBoolean("enable_seconds", this.mEnableSeconds);
            outState.putBoolean("enable_minutes", this.mEnableMinutes);
            outState.putInt("ok_resid", this.mOkResid);
            outState.putString("ok_string", this.mOkString);
            outState.putInt("ok_color", this.mOkColor);
            outState.putInt("cancel_resid", this.mCancelResid);
            outState.putString("cancel_string", this.mCancelString);
            outState.putInt("cancel_color", this.mCancelColor);
            outState.putSerializable("version", this.mVersion);
            outState.putParcelable("timepoint_limiter", this.mLimiter);
        }
    }

    public void onValueSelected(Timepoint newValue) {
        int i = 0;
        Ensighten.evaluateEvent(this, "onValueSelected", new Object[]{newValue});
        this.mTimeChangedListener.onTimeChanged(newValue.getHour(), newValue.getMinute());
        setHour(newValue.getHour(), false);
        this.mTimePicker.setContentDescription(this.mHourPickerDescription + ": " + newValue.getHour());
        setMinute(newValue.getMinute());
        this.mTimePicker.setContentDescription(this.mMinutePickerDescription + ": " + newValue.getMinute());
        setSecond(newValue.getSecond());
        this.mTimePicker.setContentDescription(this.mSecondPickerDescription + ": " + newValue.getSecond());
        if (!this.mIs24HourMode) {
            if (!newValue.isAM()) {
                i = 1;
            }
            updateAmPmDisplay(i);
        }
    }

    public void setTitlebutton(String message) {
        Ensighten.evaluateEvent(this, "setTitlebutton", new Object[]{message});
        this.mInitialMessage = message;
        if (this.timePickerHeader != null) {
            this.timePickerHeader.setText(message);
        }
        if (this.mOkButton != null) {
            this.mOkButton.setEnabled(TextUtils.isEmpty(message));
        }
    }

    public void advancePicker(int index) {
        Ensighten.evaluateEvent(this, "advancePicker", new Object[]{new Integer(index)});
        if (!this.mAllowAutoAdvance) {
            return;
        }
        if (index == 0 && this.mEnableMinutes) {
            setCurrentItemShowing(1, true, true, false);
            Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mSelectHours + ". " + this.mTimePicker.getMinutes());
        } else if (index == 1 && this.mEnableSeconds) {
            setCurrentItemShowing(2, true, true, false);
            Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mSelectMinutes + ". " + this.mTimePicker.getSeconds());
        }
    }

    public void enablePicker() {
        Ensighten.evaluateEvent(this, "enablePicker", null);
        if (!isTypedTimeFullyLegal()) {
            this.mTypedTimes.clear();
        }
        finishKbMode(true);
    }

    public boolean isOutOfRange(Timepoint current, int index) {
        Ensighten.evaluateEvent(this, "isOutOfRange", new Object[]{current, new Integer(index)});
        return this.mLimiter.isOutOfRange(current, index, getPickerResolution());
    }

    public boolean isAmDisabled() {
        Ensighten.evaluateEvent(this, "isAmDisabled", null);
        return this.mLimiter.isAmDisabled();
    }

    public boolean isPmDisabled() {
        Ensighten.evaluateEvent(this, "isPmDisabled", null);
        return this.mLimiter.isPmDisabled();
    }

    private Timepoint roundToNearest(@NonNull Timepoint time) {
        Ensighten.evaluateEvent(this, "roundToNearest", new Object[]{time});
        return roundToNearest(time, null);
    }

    public Timepoint roundToNearest(@NonNull Timepoint time, @Nullable TYPE type) {
        Ensighten.evaluateEvent(this, "roundToNearest", new Object[]{time, type});
        return this.mLimiter.roundToNearest(time, type, getPickerResolution());
    }

    /* Access modifiers changed, original: 0000 */
    @NonNull
    public TYPE getPickerResolution() {
        Ensighten.evaluateEvent(this, "getPickerResolution", null);
        if (this.mEnableSeconds) {
            return TYPE.SECOND;
        }
        if (this.mEnableMinutes) {
            return TYPE.MINUTE;
        }
        return TYPE.HOUR;
    }

    private void setHour(int value, boolean announce) {
        String format;
        Ensighten.evaluateEvent(this, "setHour", new Object[]{new Integer(value), new Boolean(announce)});
        if (this.mIs24HourMode) {
            format = "%02d";
        } else {
            format = "%d";
            value %= 12;
            if (value == 0) {
                value = 12;
            }
        }
        CharSequence text = String.format(format, new Object[]{Integer.valueOf(value)});
        this.mHourView.setText(text);
        this.mHourSpaceView.setText(text);
        if (announce) {
            Utils.tryAccessibilityAnnounce(this.mTimePicker, text);
        }
    }

    private void setMinute(int value) {
        Ensighten.evaluateEvent(this, "setMinute", new Object[]{new Integer(value)});
        if (value == 60) {
            value = 0;
        }
        CharSequence text = String.format(Locale.getDefault(), "%02d", new Object[]{Integer.valueOf(value)});
        Utils.tryAccessibilityAnnounce(this.mTimePicker, text);
        this.mMinuteView.setText(text);
        this.mMinuteSpaceView.setText(text);
    }

    private void setSecond(int value) {
        Ensighten.evaluateEvent(this, "setSecond", new Object[]{new Integer(value)});
        if (value == 60) {
            value = 0;
        }
        CharSequence text = String.format(Locale.getDefault(), "%02d", new Object[]{Integer.valueOf(value)});
        Utils.tryAccessibilityAnnounce(this.mTimePicker, text);
        this.mSecondView.setText(text);
        this.mSecondSpaceView.setText(text);
    }

    private void setCurrentItemShowing(int index, boolean animateCircle, boolean delayLabelAnimate, boolean announce) {
        TextView labelToAnimate;
        Ensighten.evaluateEvent(this, "setCurrentItemShowing", new Object[]{new Integer(index), new Boolean(animateCircle), new Boolean(delayLabelAnimate), new Boolean(announce)});
        this.mTimePicker.setCurrentItemShowing(index, animateCircle);
        switch (index) {
            case 0:
                int hours = this.mTimePicker.getHours();
                if (!this.mIs24HourMode) {
                    hours %= 12;
                }
                this.mTimePicker.setContentDescription(this.mHourPickerDescription + ": " + hours);
                if (announce) {
                    Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mSelectHours);
                }
                labelToAnimate = this.mHourView;
                break;
            case 1:
                this.mTimePicker.setContentDescription(this.mMinutePickerDescription + ": " + this.mTimePicker.getMinutes());
                if (announce) {
                    Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mSelectMinutes);
                }
                labelToAnimate = this.mMinuteView;
                break;
            default:
                this.mTimePicker.setContentDescription(this.mSecondPickerDescription + ": " + this.mTimePicker.getSeconds());
                if (announce) {
                    Utils.tryAccessibilityAnnounce(this.mTimePicker, this.mSelectSeconds);
                }
                labelToAnimate = this.mSecondView;
                break;
        }
        int hourColor = index == 0 ? this.mSelectedColor : this.mUnselectedColor;
        int minuteColor = index == 1 ? this.mSelectedColor : this.mUnselectedColor;
        int secondColor = index == 2 ? this.mSelectedColor : this.mUnselectedColor;
        this.mHourView.setTextColor(hourColor);
        this.mMinuteView.setTextColor(minuteColor);
        this.mSecondView.setTextColor(secondColor);
        ObjectAnimator pulseAnimator = Utils.getPulseAnimator(labelToAnimate, 0.85f, 1.1f);
        if (delayLabelAnimate) {
            pulseAnimator.setStartDelay(300);
        }
        pulseAnimator.start();
    }

    private boolean processKeyUp(int keyCode) {
        Ensighten.evaluateEvent(this, "processKeyUp", new Object[]{new Integer(keyCode)});
        if (keyCode != 111 && keyCode != 4) {
            if (keyCode == 61) {
                if (this.mInKbMode) {
                    if (!isTypedTimeFullyLegal()) {
                        return true;
                    }
                    finishKbMode(true);
                    return true;
                }
            } else if (keyCode == 66) {
                if (this.mInKbMode) {
                    if (!isTypedTimeFullyLegal()) {
                        return true;
                    }
                    finishKbMode(false);
                }
                if (this.mCallback != null) {
                    this.mCallback.onTimeSet(this, this.mTimePicker.getHours(), this.mTimePicker.getMinutes(), this.mTimePicker.getSeconds());
                }
                dismiss();
                return true;
            } else if (keyCode == 67) {
                if (this.mInKbMode && !this.mTypedTimes.isEmpty()) {
                    String deletedKeyStr;
                    int deleted = deleteLastTypedKey();
                    if (deleted == getAmOrPmKeyCode(0)) {
                        deletedKeyStr = this.mAmText;
                    } else if (deleted == getAmOrPmKeyCode(1)) {
                        deletedKeyStr = this.mPmText;
                    } else {
                        deletedKeyStr = String.format("%d", new Object[]{Integer.valueOf(TimePickerDialog.getValFromKeyCode(deleted))});
                    }
                    Utils.tryAccessibilityAnnounce(this.mTimePicker, String.format(this.mDeletedKeyFormat, new Object[]{deletedKeyStr}));
                    updateDisplay(true);
                }
            } else if (keyCode == 7 || keyCode == 8 || keyCode == 9 || keyCode == 10 || keyCode == 11 || keyCode == 12 || keyCode == 13 || keyCode == 14 || keyCode == 15 || keyCode == 16 || (!this.mIs24HourMode && (keyCode == getAmOrPmKeyCode(0) || keyCode == getAmOrPmKeyCode(1)))) {
                if (this.mInKbMode) {
                    if (!addKeyIfLegal(keyCode)) {
                        return true;
                    }
                    updateDisplay(false);
                    return true;
                } else if (this.mTimePicker == null) {
                    Log.e("TimePickerDialog", "Unable to initiate keyboard mode, TimePicker was null.");
                    return true;
                } else {
                    this.mTypedTimes.clear();
                    tryStartingKbMode(keyCode);
                    return true;
                }
            }
            return false;
        } else if (!isCancelable()) {
            return true;
        } else {
            dismiss();
            return true;
        }
    }

    private void tryStartingKbMode(int keyCode) {
        Ensighten.evaluateEvent(this, "tryStartingKbMode", new Object[]{new Integer(keyCode)});
        if (!this.mTimePicker.trySettingInputEnabled(false)) {
            return;
        }
        if (keyCode == -1 || addKeyIfLegal(keyCode)) {
            this.mInKbMode = true;
            this.mOkButton.setEnabled(false);
            updateDisplay(false);
        }
    }

    private boolean addKeyIfLegal(int keyCode) {
        Ensighten.evaluateEvent(this, "addKeyIfLegal", new Object[]{new Integer(keyCode)});
        int textSize = 6;
        if (this.mEnableMinutes && !this.mEnableSeconds) {
            textSize = 4;
        }
        if (!(this.mEnableMinutes || this.mEnableSeconds)) {
            textSize = 2;
        }
        if (this.mIs24HourMode && this.mTypedTimes.size() == textSize) {
            return false;
        }
        if (!this.mIs24HourMode && isTypedTimeFullyLegal()) {
            return false;
        }
        this.mTypedTimes.add(Integer.valueOf(keyCode));
        if (isTypedTimeLegalSoFar()) {
            int val = TimePickerDialog.getValFromKeyCode(keyCode);
            Utils.tryAccessibilityAnnounce(this.mTimePicker, String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(val)}));
            if (isTypedTimeFullyLegal()) {
                if (!this.mIs24HourMode && this.mTypedTimes.size() <= textSize - 1) {
                    this.mTypedTimes.add(this.mTypedTimes.size() - 1, Integer.valueOf(7));
                    this.mTypedTimes.add(this.mTypedTimes.size() - 1, Integer.valueOf(7));
                }
                this.mOkButton.setEnabled(true);
            }
            return true;
        }
        deleteLastTypedKey();
        return false;
    }

    private boolean isTypedTimeLegalSoFar() {
        Ensighten.evaluateEvent(this, "isTypedTimeLegalSoFar", null);
        Node node = this.mLegalTimesTree;
        Iterator it = this.mTypedTimes.iterator();
        while (it.hasNext()) {
            node = node.canReach(((Integer) it.next()).intValue());
            if (node == null) {
                return false;
            }
        }
        return true;
    }

    private boolean isTypedTimeFullyLegal() {
        boolean z = false;
        Ensighten.evaluateEvent(this, "isTypedTimeFullyLegal", null);
        if (this.mIs24HourMode) {
            int[] values = getEnteredTime(new Boolean[]{Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)});
            if (values[0] < 0 || values[1] < 0 || values[1] >= 60 || values[2] < 0 || values[2] >= 60) {
                return false;
            }
            return true;
        }
        if (this.mTypedTimes.contains(Integer.valueOf(getAmOrPmKeyCode(0))) || this.mTypedTimes.contains(Integer.valueOf(getAmOrPmKeyCode(1)))) {
            z = true;
        }
        return z;
    }

    private int deleteLastTypedKey() {
        Ensighten.evaluateEvent(this, "deleteLastTypedKey", null);
        int deleted = ((Integer) this.mTypedTimes.remove(this.mTypedTimes.size() - 1)).intValue();
        if (!isTypedTimeFullyLegal()) {
            this.mOkButton.setEnabled(false);
        }
        return deleted;
    }

    private void finishKbMode(boolean updateDisplays) {
        Ensighten.evaluateEvent(this, "finishKbMode", new Object[]{new Boolean(updateDisplays)});
        this.mInKbMode = false;
        if (!this.mTypedTimes.isEmpty()) {
            int[] values = getEnteredTime(new Boolean[]{Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)});
            this.mTimePicker.setTime(new Timepoint(values[0], values[1], values[2]));
            if (!this.mIs24HourMode) {
                this.mTimePicker.setAmOrPm(values[3]);
            }
            this.mTypedTimes.clear();
        }
        if (updateDisplays) {
            updateDisplay(false);
            this.mTimePicker.trySettingInputEnabled(true);
        }
    }

    private void updateDisplay(boolean allowEmptyDisplay) {
        Ensighten.evaluateEvent(this, "updateDisplay", new Object[]{new Boolean(allowEmptyDisplay)});
        if (allowEmptyDisplay || !this.mTypedTimes.isEmpty()) {
            String hourStr;
            String minuteStr;
            String secondStr;
            Boolean[] enteredZeros = new Boolean[]{Boolean.valueOf(false), Boolean.valueOf(false), Boolean.valueOf(false)};
            int[] values = getEnteredTime(enteredZeros);
            String hourFormat = enteredZeros[0].booleanValue() ? "%02d" : "%2d";
            String minuteFormat = enteredZeros[1].booleanValue() ? "%02d" : "%2d";
            String secondFormat = enteredZeros[1].booleanValue() ? "%02d" : "%2d";
            if (values[0] == -1) {
                hourStr = this.mDoublePlaceholderText;
            } else {
                hourStr = String.format(hourFormat, new Object[]{Integer.valueOf(values[0])}).replace(SafeJsonPrimitive.NULL_CHAR, this.mPlaceholderText);
            }
            if (values[1] == -1) {
                minuteStr = this.mDoublePlaceholderText;
            } else {
                minuteStr = String.format(minuteFormat, new Object[]{Integer.valueOf(values[1])}).replace(SafeJsonPrimitive.NULL_CHAR, this.mPlaceholderText);
            }
            if (values[2] == -1) {
                secondStr = this.mDoublePlaceholderText;
            } else {
                secondStr = String.format(secondFormat, new Object[]{Integer.valueOf(values[1])}).replace(SafeJsonPrimitive.NULL_CHAR, this.mPlaceholderText);
            }
            this.mHourView.setText(hourStr);
            this.mHourSpaceView.setText(hourStr);
            this.mHourView.setTextColor(this.mUnselectedColor);
            this.mMinuteView.setText(minuteStr);
            this.mMinuteSpaceView.setText(minuteStr);
            this.mMinuteView.setTextColor(this.mUnselectedColor);
            this.mSecondView.setText(secondStr);
            this.mSecondSpaceView.setText(secondStr);
            this.mSecondView.setTextColor(this.mUnselectedColor);
            if (!this.mIs24HourMode) {
                updateAmPmDisplay(values[3]);
                return;
            }
            return;
        }
        int hour = this.mTimePicker.getHours();
        int minute = this.mTimePicker.getMinutes();
        int second = this.mTimePicker.getSeconds();
        setHour(hour, true);
        setMinute(minute);
        setSecond(second);
        if (!this.mIs24HourMode) {
            updateAmPmDisplay(hour < 12 ? 0 : 1);
        }
        setCurrentItemShowing(this.mTimePicker.getCurrentItemShowing(), true, true, true);
        this.mOkButton.setEnabled(true);
    }

    private static int getValFromKeyCode(int keyCode) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerDialog", "getValFromKeyCode", new Object[]{new Integer(keyCode)});
        switch (keyCode) {
            case 7:
                return 0;
            case 8:
                return 1;
            case 9:
                return 2;
            case 10:
                return 3;
            case 11:
                return 4;
            case 12:
                return 5;
            case 13:
                return 6;
            case 14:
                return 7;
            case 15:
                return 8;
            case 16:
                return 9;
            default:
                return -1;
        }
    }

    @NonNull
    private int[] getEnteredTime(@NonNull Boolean[] enteredZeros) {
        int shift;
        Ensighten.evaluateEvent(this, "getEnteredTime", new Object[]{enteredZeros});
        int amOrPm = -1;
        int startIndex = 1;
        if (!this.mIs24HourMode && isTypedTimeFullyLegal()) {
            int keyCode = ((Integer) this.mTypedTimes.get(this.mTypedTimes.size() - 1)).intValue();
            if (keyCode == getAmOrPmKeyCode(0)) {
                amOrPm = 0;
            } else if (keyCode == getAmOrPmKeyCode(1)) {
                amOrPm = 1;
            }
            startIndex = 2;
        }
        int minute = -1;
        int hour = -1;
        int second = 0;
        if (this.mEnableSeconds) {
            shift = 2;
        } else {
            shift = 0;
        }
        for (int i = startIndex; i <= this.mTypedTimes.size(); i++) {
            int val = TimePickerDialog.getValFromKeyCode(((Integer) this.mTypedTimes.get(this.mTypedTimes.size() - i)).intValue());
            if (this.mEnableSeconds) {
                if (i == startIndex) {
                    second = val;
                } else if (i == startIndex + 1) {
                    second += val * 10;
                    if (enteredZeros != null && val == 0) {
                        enteredZeros[2] = Boolean.valueOf(true);
                    }
                }
            }
            if (this.mEnableMinutes) {
                if (i == startIndex + shift) {
                    minute = val;
                } else if (i == (startIndex + shift) + 1) {
                    minute += val * 10;
                    if (enteredZeros != null && val == 0) {
                        enteredZeros[1] = Boolean.valueOf(true);
                    }
                } else if (i == (startIndex + shift) + 2) {
                    hour = val;
                } else if (i == (startIndex + shift) + 3) {
                    hour += val * 10;
                    if (enteredZeros != null && val == 0) {
                        enteredZeros[0] = Boolean.valueOf(true);
                    }
                }
            } else if (i == startIndex + shift) {
                hour = val;
            } else if (i == (startIndex + shift) + 1) {
                hour += val * 10;
                if (enteredZeros != null && val == 0) {
                    enteredZeros[0] = Boolean.valueOf(true);
                }
            }
        }
        return new int[]{hour, minute, second, amOrPm};
    }

    private int getAmOrPmKeyCode(int amOrPm) {
        Ensighten.evaluateEvent(this, "getAmOrPmKeyCode", new Object[]{new Integer(amOrPm)});
        if (this.mAmKeyCode == -1 || this.mPmKeyCode == -1) {
            KeyCharacterMap kcm = KeyCharacterMap.load(-1);
            int i = 0;
            while (i < Math.max(this.mAmText.length(), this.mPmText.length())) {
                if (this.mAmText.toLowerCase(Locale.getDefault()).charAt(i) != this.mPmText.toLowerCase(Locale.getDefault()).charAt(i)) {
                    KeyEvent[] events = kcm.getEvents(new char[]{this.mAmText.toLowerCase(Locale.getDefault()).charAt(i), this.mPmText.toLowerCase(Locale.getDefault()).charAt(i)});
                    if (events == null || events.length != 4) {
                        Log.e("TimePickerDialog", "Unable to find keycodes for AM and PM.");
                    } else {
                        this.mAmKeyCode = events[0].getKeyCode();
                        this.mPmKeyCode = events[2].getKeyCode();
                    }
                } else {
                    i++;
                }
            }
        }
        if (amOrPm == 0) {
            return this.mAmKeyCode;
        }
        if (amOrPm == 1) {
            return this.mPmKeyCode;
        }
        return -1;
    }

    private void generateLegalTimesTree() {
        Ensighten.evaluateEvent(this, "generateLegalTimesTree", null);
        this.mLegalTimesTree = new Node(new int[0]);
        Node firstDigit;
        Node ampm;
        Node node;
        if (!this.mEnableMinutes && this.mIs24HourMode) {
            firstDigit = new Node(7, 8);
            this.mLegalTimesTree.addChild(firstDigit);
            firstDigit.addChild(new Node(7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
            firstDigit = new Node(9);
            this.mLegalTimesTree.addChild(firstDigit);
            firstDigit.addChild(new Node(7, 8, 9, 10));
        } else if (!this.mEnableMinutes && !this.mIs24HourMode) {
            ampm = new Node(getAmOrPmKeyCode(0), getAmOrPmKeyCode(1));
            firstDigit = new Node(8);
            this.mLegalTimesTree.addChild(firstDigit);
            firstDigit.addChild(ampm);
            node = new Node(7, 8, 9);
            firstDigit.addChild(node);
            node.addChild(ampm);
            firstDigit = new Node(9, 10, 11, 12, 13, 14, 15, 16);
            this.mLegalTimesTree.addChild(firstDigit);
            firstDigit.addChild(ampm);
        } else if (this.mIs24HourMode) {
            node = new Node(7, 8, 9, 10, 11, 12);
            node = new Node(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
            node.addChild(node);
            if (this.mEnableSeconds) {
                node = new Node(7, 8, 9, 10, 11, 12);
                node.addChild(new Node(7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
                node.addChild(node);
            }
            firstDigit = new Node(7, 8);
            this.mLegalTimesTree.addChild(firstDigit);
            node = new Node(7, 8, 9, 10, 11, 12);
            firstDigit.addChild(node);
            node.addChild(node);
            node.addChild(new Node(13, 14, 15, 16));
            node = new Node(13, 14, 15, 16);
            firstDigit.addChild(node);
            node.addChild(node);
            firstDigit = new Node(9);
            this.mLegalTimesTree.addChild(firstDigit);
            node = new Node(7, 8, 9, 10);
            firstDigit.addChild(node);
            node.addChild(node);
            node = new Node(11, 12);
            firstDigit.addChild(node);
            node.addChild(node);
            firstDigit = new Node(10, 11, 12, 13, 14, 15, 16);
            this.mLegalTimesTree.addChild(firstDigit);
            firstDigit.addChild(node);
        } else {
            ampm = new Node(getAmOrPmKeyCode(0), getAmOrPmKeyCode(1));
            node = new Node(7, 8, 9, 10, 11, 12);
            node = new Node(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
            node.addChild(ampm);
            node.addChild(node);
            firstDigit = new Node(8);
            this.mLegalTimesTree.addChild(firstDigit);
            firstDigit.addChild(ampm);
            node = new Node(7, 8, 9);
            firstDigit.addChild(node);
            node.addChild(ampm);
            Node thirdDigit = new Node(7, 8, 9, 10, 11, 12);
            node.addChild(thirdDigit);
            thirdDigit.addChild(ampm);
            Node fourthDigit = new Node(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
            thirdDigit.addChild(fourthDigit);
            fourthDigit.addChild(ampm);
            if (this.mEnableSeconds) {
                fourthDigit.addChild(node);
            }
            thirdDigit = new Node(13, 14, 15, 16);
            node.addChild(thirdDigit);
            thirdDigit.addChild(ampm);
            if (this.mEnableSeconds) {
                thirdDigit.addChild(node);
            }
            node = new Node(10, 11, 12);
            firstDigit.addChild(node);
            thirdDigit = new Node(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
            node.addChild(thirdDigit);
            thirdDigit.addChild(ampm);
            if (this.mEnableSeconds) {
                thirdDigit.addChild(node);
            }
            firstDigit = new Node(9, 10, 11, 12, 13, 14, 15, 16);
            this.mLegalTimesTree.addChild(firstDigit);
            firstDigit.addChild(ampm);
            node = new Node(7, 8, 9, 10, 11, 12);
            firstDigit.addChild(node);
            thirdDigit = new Node(7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
            node.addChild(thirdDigit);
            thirdDigit.addChild(ampm);
            if (this.mEnableSeconds) {
                thirdDigit.addChild(node);
            }
        }
    }

    public void notifyOnDateListener() {
        Ensighten.evaluateEvent(this, "notifyOnDateListener", null);
        if (this.mCallback != null) {
            this.mCallback.onTimeSet(this, this.mTimePicker.getHours(), this.mTimePicker.getMinutes(), this.mTimePicker.getSeconds());
        }
    }
}
