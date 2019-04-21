package com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.p000v4.content.ContextCompat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.widget.FrameLayout;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.RadialTextsView.SelectionValidator;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.TimePickerDialog.Version;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.Timepoint.TYPE;
import com.mcdonalds.gma.hongkong.C2658R;
import java.util.Calendar;
import java.util.Locale;

/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout */
public class RadialPickerLayout extends FrameLayout implements OnTouchListener {
    private final int TAP_TIMEOUT;
    private final int TOUCH_SLOP;
    private AccessibilityManager mAccessibilityManager;
    private AmPmCirclesView mAmPmCirclesView;
    private CircleView mCircleView;
    private TimePickerController mController;
    private int mCurrentItemShowing;
    private Timepoint mCurrentTime;
    private boolean mDoingMove;
    private boolean mDoingTouch;
    private int mDownDegrees;
    private float mDownX;
    private float mDownY;
    private View mGrayBox;
    private Handler mHandler = new Handler();
    private RadialSelectorView mHourRadialSelectorView;
    private RadialTextsView mHourRadialTextsView;
    private boolean mInputEnabled;
    private boolean mIs24HourMode;
    private int mIsTouchingAmOrPm = -1;
    private Timepoint mLastValueSelected;
    private OnValueSelectedListener mListener;
    private RadialSelectorView mMinuteRadialSelectorView;
    private RadialTextsView mMinuteRadialTextsView;
    private RadialSelectorView mSecondRadialSelectorView;
    private RadialTextsView mSecondRadialTextsView;
    private int[] mSnapPrefer30sMap;
    private boolean mTimeInitialized;
    private AnimatorSet mTransition;

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout$1 */
    class C38261 implements SelectionValidator {
        C38261() {
        }

        public boolean isValidSelection(int selection) {
            Ensighten.evaluateEvent(this, "isValidSelection", new Object[]{new Integer(selection)});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$100", new Object[]{RadialPickerLayout.this}).isOutOfRange(new Timepoint(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$000", new Object[]{RadialPickerLayout.this}).getHour(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$000", new Object[]{RadialPickerLayout.this}).getMinute(), selection), 2)) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout$2 */
    class C38272 implements SelectionValidator {
        C38272() {
        }

        public boolean isValidSelection(int selection) {
            Ensighten.evaluateEvent(this, "isValidSelection", new Object[]{new Integer(selection)});
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$100", new Object[]{RadialPickerLayout.this}).isOutOfRange(new Timepoint(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$000", new Object[]{RadialPickerLayout.this}).getHour(), selection, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$000", new Object[]{RadialPickerLayout.this}).getSecond()), 1)) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout$3 */
    class C38283 implements SelectionValidator {
        C38283() {
        }

        public boolean isValidSelection(int selection) {
            Ensighten.evaluateEvent(this, "isValidSelection", new Object[]{new Integer(selection)});
            Timepoint newTime = new Timepoint(selection, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$000", new Object[]{RadialPickerLayout.this}).getMinute(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$000", new Object[]{RadialPickerLayout.this}).getSecond());
            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$200", new Object[]{RadialPickerLayout.this}) && RadialPickerLayout.this.getIsCurrentlyAmOrPm() == 1) {
                newTime.setPM();
            }
            if (!Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$200", new Object[]{RadialPickerLayout.this}) && RadialPickerLayout.this.getIsCurrentlyAmOrPm() == 0) {
                newTime.setAM();
            }
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$100", new Object[]{RadialPickerLayout.this}).isOutOfRange(newTime, 0)) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout$4 */
    class C38294 implements Runnable {
        C38294() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$400", new Object[]{RadialPickerLayout.this}).setAmOrPmPressed(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$300", new Object[]{RadialPickerLayout.this}));
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$400", new Object[]{RadialPickerLayout.this}).invalidate();
        }
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout$OnValueSelectedListener */
    public interface OnValueSelectedListener {
        void advancePicker(int i);

        void enablePicker();

        void onValueSelected(Timepoint timepoint);
    }

    static /* synthetic */ void access$1000(RadialPickerLayout x0, Timepoint x1, boolean x2, int x3) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$1000", new Object[]{x0, x1, new Boolean(x2), new Integer(x3)});
        x0.reselectSelector(x1, x2, x3);
    }

    static /* synthetic */ boolean access$502(RadialPickerLayout x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$502", new Object[]{x0, new Boolean(x1)});
        x0.mDoingMove = x1;
        return x1;
    }

    static /* synthetic */ Timepoint access$602(RadialPickerLayout x0, Timepoint x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$602", new Object[]{x0, x1});
        x0.mLastValueSelected = x1;
        return x1;
    }

    public RadialPickerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        this.TOUCH_SLOP = ViewConfiguration.get(context).getScaledTouchSlop();
        this.TAP_TIMEOUT = ViewConfiguration.getTapTimeout();
        this.mDoingMove = false;
        this.mCircleView = new CircleView(context);
        addView(this.mCircleView);
        this.mAmPmCirclesView = new AmPmCirclesView(context);
        addView(this.mAmPmCirclesView);
        this.mHourRadialSelectorView = new RadialSelectorView(context);
        addView(this.mHourRadialSelectorView);
        this.mMinuteRadialSelectorView = new RadialSelectorView(context);
        addView(this.mMinuteRadialSelectorView);
        this.mSecondRadialSelectorView = new RadialSelectorView(context);
        addView(this.mSecondRadialSelectorView);
        this.mHourRadialTextsView = new RadialTextsView(context);
        addView(this.mHourRadialTextsView);
        this.mMinuteRadialTextsView = new RadialTextsView(context);
        addView(this.mMinuteRadialTextsView);
        this.mSecondRadialTextsView = new RadialTextsView(context);
        addView(this.mSecondRadialTextsView);
        preparePrefer30sMap();
        this.mLastValueSelected = null;
        this.mInputEnabled = true;
        this.mGrayBox = new View(context);
        this.mGrayBox.setLayoutParams(new LayoutParams(-1, -1));
        this.mGrayBox.setBackgroundColor(ContextCompat.getColor(context, C2658R.color.mdtp_transparent_black));
        this.mGrayBox.setVisibility(4);
        addView(this.mGrayBox);
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        this.mTimeInitialized = false;
    }

    public void setOnValueSelectedListener(OnValueSelectedListener listener) {
        Ensighten.evaluateEvent(this, "setOnValueSelectedListener", new Object[]{listener});
        this.mListener = listener;
    }

    public void initialize(Context context, TimePickerController timePickerController, Timepoint initialTime, boolean is24HourMode) {
        Ensighten.evaluateEvent(this, "initialize", new Object[]{context, timePickerController, initialTime, new Boolean(is24HourMode)});
        if (this.mTimeInitialized) {
            Log.e("RadialPickerLayout", "Time has already been initialized.");
            return;
        }
        int hour;
        this.mController = timePickerController;
        boolean z = this.mAccessibilityManager.isTouchExplorationEnabled() || is24HourMode;
        this.mIs24HourMode = z;
        this.mCircleView.initialize(context, this.mController);
        this.mCircleView.invalidate();
        if (!this.mIs24HourMode && this.mController.getVersion() == Version.VERSION_1) {
            this.mAmPmCirclesView.initialize(context, this.mController, initialTime.isAM() ? 0 : 1);
            this.mAmPmCirclesView.invalidate();
        }
        SelectionValidator c38261 = new C38261();
        SelectionValidator minuteValidator = new C38272();
        SelectionValidator hourValidator = new C38283();
        int[] iArr = new int[12];
        iArr = new int[]{12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int[] iArr2 = new int[12];
        iArr2 = new int[]{0, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
        int[] iArr3 = new int[12];
        iArr3 = new int[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
        int[] iArr4 = new int[12];
        iArr4 = new int[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55};
        String[] hoursTexts = new String[12];
        String[] innerHoursTexts = new String[12];
        String[] minutesTexts = new String[12];
        String[] secondsTexts = new String[12];
        for (int i = 0; i < 12; i++) {
            hoursTexts[i] = is24HourMode ? String.format(Locale.getDefault(), "%02d", new Object[]{Integer.valueOf(iArr2[i])}) : String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(iArr[i])});
            innerHoursTexts[i] = String.format(Locale.getDefault(), "%d", new Object[]{Integer.valueOf(iArr[i])});
            minutesTexts[i] = String.format(Locale.getDefault(), "%02d", new Object[]{Integer.valueOf(iArr3[i])});
            secondsTexts[i] = String.format(Locale.getDefault(), "%02d", new Object[]{Integer.valueOf(iArr4[i])});
        }
        if (this.mController.getVersion() == Version.VERSION_2) {
            String[] temp = hoursTexts;
            hoursTexts = innerHoursTexts;
            innerHoursTexts = temp;
        }
        this.mHourRadialTextsView.initialize(context, hoursTexts, is24HourMode ? innerHoursTexts : null, this.mController, hourValidator, true);
        RadialTextsView radialTextsView = this.mHourRadialTextsView;
        if (is24HourMode) {
            hour = initialTime.getHour();
        } else {
            hour = iArr[initialTime.getHour() % 12];
        }
        radialTextsView.setSelection(hour);
        this.mHourRadialTextsView.invalidate();
        this.mMinuteRadialTextsView.initialize(context, minutesTexts, null, this.mController, minuteValidator, false);
        this.mMinuteRadialTextsView.setSelection(initialTime.getMinute());
        this.mMinuteRadialTextsView.invalidate();
        this.mSecondRadialTextsView.initialize(context, secondsTexts, null, this.mController, c38261, false);
        this.mSecondRadialTextsView.setSelection(initialTime.getSecond());
        this.mSecondRadialTextsView.invalidate();
        this.mCurrentTime = initialTime;
        this.mHourRadialSelectorView.initialize(context, this.mController, is24HourMode, true, (initialTime.getHour() % 12) * 30, isHourInnerCircle(initialTime.getHour()));
        this.mMinuteRadialSelectorView.initialize(context, this.mController, false, false, initialTime.getMinute() * 6, false);
        this.mSecondRadialSelectorView.initialize(context, this.mController, false, false, initialTime.getSecond() * 6, false);
        this.mTimeInitialized = true;
    }

    public void setTime(Timepoint time) {
        Ensighten.evaluateEvent(this, "setTime", new Object[]{time});
        setItem(0, time);
    }

    private void setItem(int index, Timepoint time) {
        Ensighten.evaluateEvent(this, "setItem", new Object[]{new Integer(index), time});
        time = roundToValidTime(time, index);
        this.mCurrentTime = time;
        reselectSelector(time, false, index);
    }

    private boolean isHourInnerCircle(int hourOfDay) {
        boolean isMorning;
        Ensighten.evaluateEvent(this, "isHourInnerCircle", new Object[]{new Integer(hourOfDay)});
        if (hourOfDay > 12 || hourOfDay == 0) {
            isMorning = false;
        } else {
            isMorning = true;
        }
        if (this.mController.getVersion() != Version.VERSION_1) {
            if (isMorning) {
                isMorning = false;
            } else {
                isMorning = true;
            }
        }
        if (this.mIs24HourMode && isMorning) {
            return true;
        }
        return false;
    }

    public int getHours() {
        Ensighten.evaluateEvent(this, "getHours", null);
        return this.mCurrentTime.getHour();
    }

    public int getMinutes() {
        Ensighten.evaluateEvent(this, "getMinutes", null);
        return this.mCurrentTime.getMinute();
    }

    public int getSeconds() {
        Ensighten.evaluateEvent(this, "getSeconds", null);
        return this.mCurrentTime.getSecond();
    }

    public Timepoint getTime() {
        Ensighten.evaluateEvent(this, "getTime", null);
        return this.mCurrentTime;
    }

    private int getCurrentlyShowingValue() {
        Ensighten.evaluateEvent(this, "getCurrentlyShowingValue", null);
        switch (getCurrentItemShowing()) {
            case 0:
                return this.mCurrentTime.getHour();
            case 1:
                return this.mCurrentTime.getMinute();
            case 2:
                return this.mCurrentTime.getSecond();
            default:
                return -1;
        }
    }

    public int getIsCurrentlyAmOrPm() {
        Ensighten.evaluateEvent(this, "getIsCurrentlyAmOrPm", null);
        if (this.mCurrentTime.isAM()) {
            return 0;
        }
        if (this.mCurrentTime.isPM()) {
            return 1;
        }
        return -1;
    }

    public void setAmOrPm(int amOrPm) {
        Ensighten.evaluateEvent(this, "setAmOrPm", new Object[]{new Integer(amOrPm)});
        this.mAmPmCirclesView.setAmOrPm(amOrPm);
        this.mAmPmCirclesView.invalidate();
        Timepoint newSelection = new Timepoint(this.mCurrentTime);
        if (amOrPm == 0) {
            newSelection.setAM();
        } else if (amOrPm == 1) {
            newSelection.setPM();
        }
        newSelection = roundToValidTime(newSelection, 0);
        reselectSelector(newSelection, false, 0);
        this.mCurrentTime = newSelection;
        this.mListener.onValueSelected(newSelection);
    }

    private void preparePrefer30sMap() {
        Ensighten.evaluateEvent(this, "preparePrefer30sMap", null);
        this.mSnapPrefer30sMap = new int[361];
        int snappedOutputDegrees = 0;
        int count = 1;
        int expectedCount = 8;
        for (int degrees = 0; degrees < 361; degrees++) {
            this.mSnapPrefer30sMap[degrees] = snappedOutputDegrees;
            if (count == expectedCount) {
                snappedOutputDegrees += 6;
                if (snappedOutputDegrees == 360) {
                    expectedCount = 7;
                } else if (snappedOutputDegrees % 30 == 0) {
                    expectedCount = 14;
                } else {
                    expectedCount = 4;
                }
                count = 1;
            } else {
                count++;
            }
        }
    }

    private int snapPrefer30s(int degrees) {
        Ensighten.evaluateEvent(this, "snapPrefer30s", new Object[]{new Integer(degrees)});
        if (this.mSnapPrefer30sMap == null) {
            return -1;
        }
        return this.mSnapPrefer30sMap[degrees];
    }

    private static int snapOnly30s(int degrees, int forceHigherOrLower) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "snapOnly30s", new Object[]{new Integer(degrees), new Integer(forceHigherOrLower)});
        int floor = (degrees / 30) * 30;
        int ceiling = floor + 30;
        if (forceHigherOrLower == 1) {
            return ceiling;
        }
        if (forceHigherOrLower == -1) {
            if (degrees == floor) {
                floor -= 30;
            }
            return floor;
        } else if (degrees - floor < ceiling - degrees) {
            return floor;
        } else {
            return ceiling;
        }
    }

    private Timepoint roundToValidTime(Timepoint newSelection, int currentItemShowing) {
        Ensighten.evaluateEvent(this, "roundToValidTime", new Object[]{newSelection, new Integer(currentItemShowing)});
        switch (currentItemShowing) {
            case 0:
                return this.mController.roundToNearest(newSelection, null);
            case 1:
                return this.mController.roundToNearest(newSelection, TYPE.HOUR);
            default:
                return this.mController.roundToNearest(newSelection, TYPE.MINUTE);
        }
    }

    private void reselectSelector(Timepoint newSelection, boolean forceDrawDot, int index) {
        Ensighten.evaluateEvent(this, "reselectSelector", new Object[]{newSelection, new Boolean(forceDrawDot), new Integer(index)});
        switch (index) {
            case 0:
                int hour = newSelection.getHour();
                boolean isInnerCircle = isHourInnerCircle(hour);
                int degrees = ((hour % 12) * 360) / 12;
                if (!this.mIs24HourMode) {
                    hour %= 12;
                }
                if (!this.mIs24HourMode && hour == 0) {
                    hour += 12;
                }
                this.mHourRadialSelectorView.setSelection(degrees, isInnerCircle, forceDrawDot);
                this.mHourRadialTextsView.setSelection(hour);
                if (newSelection.getMinute() != this.mCurrentTime.getMinute()) {
                    this.mMinuteRadialSelectorView.setSelection(newSelection.getMinute() * 6, isInnerCircle, forceDrawDot);
                    this.mMinuteRadialTextsView.setSelection(newSelection.getMinute());
                }
                if (newSelection.getSecond() != this.mCurrentTime.getSecond()) {
                    this.mSecondRadialSelectorView.setSelection(newSelection.getSecond() * 6, isInnerCircle, forceDrawDot);
                    this.mSecondRadialTextsView.setSelection(newSelection.getSecond());
                    break;
                }
                break;
            case 1:
                this.mMinuteRadialSelectorView.setSelection(newSelection.getMinute() * 6, false, forceDrawDot);
                this.mMinuteRadialTextsView.setSelection(newSelection.getMinute());
                if (newSelection.getSecond() != this.mCurrentTime.getSecond()) {
                    this.mSecondRadialSelectorView.setSelection(newSelection.getSecond() * 6, false, forceDrawDot);
                    this.mSecondRadialTextsView.setSelection(newSelection.getSecond());
                    break;
                }
                break;
            case 2:
                this.mSecondRadialSelectorView.setSelection(newSelection.getSecond() * 6, false, forceDrawDot);
                this.mSecondRadialTextsView.setSelection(newSelection.getSecond());
                break;
        }
        switch (getCurrentItemShowing()) {
            case 0:
                this.mHourRadialSelectorView.invalidate();
                this.mHourRadialTextsView.invalidate();
                return;
            case 1:
                this.mMinuteRadialSelectorView.invalidate();
                this.mMinuteRadialTextsView.invalidate();
                return;
            case 2:
                this.mSecondRadialSelectorView.invalidate();
                this.mSecondRadialTextsView.invalidate();
                return;
            default:
                return;
        }
    }

    private Timepoint getTimeFromDegrees(int degrees, boolean isInnerCircle, boolean forceToVisibleValue) {
        Ensighten.evaluateEvent(this, "getTimeFromDegrees", new Object[]{new Integer(degrees), new Boolean(isInnerCircle), new Boolean(forceToVisibleValue)});
        if (degrees == -1) {
            return null;
        }
        int stepSize;
        int currentShowing = getCurrentItemShowing();
        boolean allowFineGrained = !forceToVisibleValue && (currentShowing == 1 || currentShowing == 2);
        if (allowFineGrained) {
            degrees = snapPrefer30s(degrees);
        } else {
            degrees = RadialPickerLayout.snapOnly30s(degrees, 0);
        }
        switch (currentShowing) {
            case 0:
                stepSize = 30;
                break;
            case 1:
                stepSize = 6;
                break;
            default:
                stepSize = 6;
                break;
        }
        if (currentShowing == 0) {
            if (this.mIs24HourMode) {
                if (degrees == 0 && isInnerCircle) {
                    degrees = 360;
                } else if (degrees == 360 && !isInnerCircle) {
                    degrees = 0;
                }
            } else if (degrees == 0) {
                degrees = 360;
            }
        } else if (degrees == 360 && (currentShowing == 1 || currentShowing == 2)) {
            degrees = 0;
        }
        int value = degrees / stepSize;
        if (currentShowing == 0 && this.mIs24HourMode && !isInnerCircle && degrees != 0) {
            value += 12;
        }
        if (currentShowing == 0 && this.mController.getVersion() != Version.VERSION_1 && this.mIs24HourMode) {
            value = (value + 12) % 24;
        }
        switch (currentShowing) {
            case 0:
                int hour = value;
                if (!(this.mIs24HourMode || getIsCurrentlyAmOrPm() != 1 || degrees == 360)) {
                    hour += 12;
                }
                if (!this.mIs24HourMode && getIsCurrentlyAmOrPm() == 0 && degrees == 360) {
                    hour = 0;
                }
                return new Timepoint(hour, this.mCurrentTime.getMinute(), this.mCurrentTime.getSecond());
            case 1:
                return new Timepoint(this.mCurrentTime.getHour(), value, this.mCurrentTime.getSecond());
            case 2:
                return new Timepoint(this.mCurrentTime.getHour(), this.mCurrentTime.getMinute(), value);
            default:
                return this.mCurrentTime;
        }
    }

    private int getDegreesFromCoords(float pointX, float pointY, boolean forceLegal, Boolean[] isInnerCircle) {
        Ensighten.evaluateEvent(this, "getDegreesFromCoords", new Object[]{new Float(pointX), new Float(pointY), new Boolean(forceLegal), isInnerCircle});
        switch (getCurrentItemShowing()) {
            case 0:
                return this.mHourRadialSelectorView.getDegreesFromCoords(pointX, pointY, forceLegal, isInnerCircle);
            case 1:
                return this.mMinuteRadialSelectorView.getDegreesFromCoords(pointX, pointY, forceLegal, isInnerCircle);
            case 2:
                return this.mSecondRadialSelectorView.getDegreesFromCoords(pointX, pointY, forceLegal, isInnerCircle);
            default:
                return -1;
        }
    }

    public int getCurrentItemShowing() {
        Ensighten.evaluateEvent(this, "getCurrentItemShowing", null);
        if (this.mCurrentItemShowing == 0 || this.mCurrentItemShowing == 1 || this.mCurrentItemShowing == 2) {
            return this.mCurrentItemShowing;
        }
        Log.e("RadialPickerLayout", "Current item showing was unfortunately set to " + this.mCurrentItemShowing);
        return -1;
    }

    public void setCurrentItemShowing(int index, boolean animate) {
        Ensighten.evaluateEvent(this, "setCurrentItemShowing", new Object[]{new Integer(index), new Boolean(animate)});
        if (index == 0 || index == 1 || index == 2) {
            int lastIndex = getCurrentItemShowing();
            this.mCurrentItemShowing = index;
            reselectSelector(getTime(), true, index);
            if (!animate || index == lastIndex) {
                transitionWithoutAnimation(index);
                return;
            }
            ObjectAnimator[] anims = new ObjectAnimator[4];
            if (index == 1 && lastIndex == 0) {
                anims[0] = this.mHourRadialTextsView.getDisappearAnimator();
                anims[1] = this.mHourRadialSelectorView.getDisappearAnimator();
                anims[2] = this.mMinuteRadialTextsView.getReappearAnimator();
                anims[3] = this.mMinuteRadialSelectorView.getReappearAnimator();
            } else if (index == 0 && lastIndex == 1) {
                anims[0] = this.mHourRadialTextsView.getReappearAnimator();
                anims[1] = this.mHourRadialSelectorView.getReappearAnimator();
                anims[2] = this.mMinuteRadialTextsView.getDisappearAnimator();
                anims[3] = this.mMinuteRadialSelectorView.getDisappearAnimator();
            } else if (index == 1 && lastIndex == 2) {
                anims[0] = this.mSecondRadialTextsView.getDisappearAnimator();
                anims[1] = this.mSecondRadialSelectorView.getDisappearAnimator();
                anims[2] = this.mMinuteRadialTextsView.getReappearAnimator();
                anims[3] = this.mMinuteRadialSelectorView.getReappearAnimator();
            } else if (index == 0 && lastIndex == 2) {
                anims[0] = this.mSecondRadialTextsView.getDisappearAnimator();
                anims[1] = this.mSecondRadialSelectorView.getDisappearAnimator();
                anims[2] = this.mHourRadialTextsView.getReappearAnimator();
                anims[3] = this.mHourRadialSelectorView.getReappearAnimator();
            } else if (index == 2 && lastIndex == 1) {
                anims[0] = this.mSecondRadialTextsView.getReappearAnimator();
                anims[1] = this.mSecondRadialSelectorView.getReappearAnimator();
                anims[2] = this.mMinuteRadialTextsView.getDisappearAnimator();
                anims[3] = this.mMinuteRadialSelectorView.getDisappearAnimator();
            } else if (index == 2 && lastIndex == 0) {
                anims[0] = this.mSecondRadialTextsView.getReappearAnimator();
                anims[1] = this.mSecondRadialSelectorView.getReappearAnimator();
                anims[2] = this.mHourRadialTextsView.getDisappearAnimator();
                anims[3] = this.mHourRadialSelectorView.getDisappearAnimator();
            }
            if (anims[0] == null || anims[1] == null || anims[2] == null || anims[3] == null) {
                transitionWithoutAnimation(index);
                return;
            }
            if (this.mTransition != null && this.mTransition.isRunning()) {
                this.mTransition.end();
            }
            this.mTransition = new AnimatorSet();
            this.mTransition.playTogether(anims);
            this.mTransition.start();
            return;
        }
        Log.e("RadialPickerLayout", "TimePicker does not support view at index " + index);
    }

    private void transitionWithoutAnimation(int index) {
        int hourAlpha;
        int minuteAlpha;
        int secondAlpha = 1;
        Ensighten.evaluateEvent(this, "transitionWithoutAnimation", new Object[]{new Integer(index)});
        if (index == 0) {
            hourAlpha = 1;
        } else {
            hourAlpha = 0;
        }
        if (index == 1) {
            minuteAlpha = 1;
        } else {
            minuteAlpha = 0;
        }
        if (index != 2) {
            secondAlpha = 0;
        }
        this.mHourRadialTextsView.setAlpha((float) hourAlpha);
        this.mHourRadialSelectorView.setAlpha((float) hourAlpha);
        this.mMinuteRadialTextsView.setAlpha((float) minuteAlpha);
        this.mMinuteRadialSelectorView.setAlpha((float) minuteAlpha);
        this.mSecondRadialTextsView.setAlpha((float) secondAlpha);
        this.mSecondRadialSelectorView.setAlpha((float) secondAlpha);
    }

    public boolean onTouch(View v, MotionEvent event) {
        Ensighten.evaluateEvent(this, "onTouch", new Object[]{v, event});
        float eventX = event.getX();
        float eventY = event.getY();
        final Boolean[] isInnerCircle = new Boolean[]{Boolean.valueOf(false)};
        int degrees;
        Timepoint value;
        switch (event.getAction()) {
            case 0:
                if (!this.mInputEnabled) {
                    return true;
                }
                this.mDownX = eventX;
                this.mDownY = eventY;
                this.mLastValueSelected = null;
                this.mDoingMove = false;
                this.mDoingTouch = true;
                if (this.mIs24HourMode || this.mController.getVersion() != Version.VERSION_1) {
                    this.mIsTouchingAmOrPm = -1;
                } else {
                    this.mIsTouchingAmOrPm = this.mAmPmCirclesView.getIsTouchingAmOrPm(eventX, eventY);
                }
                if (this.mIsTouchingAmOrPm == 0 || this.mIsTouchingAmOrPm == 1) {
                    this.mController.tryVibrate();
                    this.mDownDegrees = -1;
                    this.mHandler.postDelayed(new C38294(), (long) this.TAP_TIMEOUT);
                } else {
                    this.mDownDegrees = getDegreesFromCoords(eventX, eventY, this.mAccessibilityManager.isTouchExplorationEnabled(), isInnerCircle);
                    if (this.mController.isOutOfRange(getTimeFromDegrees(this.mDownDegrees, isInnerCircle[0].booleanValue(), false), getCurrentItemShowing())) {
                        this.mDownDegrees = -1;
                    }
                    if (this.mDownDegrees != -1) {
                        this.mController.tryVibrate();
                        this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                Ensighten.evaluateEvent(this, "run", null);
                                RadialPickerLayout.access$502(RadialPickerLayout.this, true);
                                RadialPickerLayout.access$602(RadialPickerLayout.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$800", new Object[]{RadialPickerLayout.this, new Integer(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$700", new Object[]{RadialPickerLayout.this})), new Boolean(isInnerCircle[0].booleanValue()), new Boolean(false)}));
                                RadialPickerLayout.access$602(RadialPickerLayout.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$900", new Object[]{RadialPickerLayout.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$600", new Object[]{RadialPickerLayout.this}), new Integer(RadialPickerLayout.this.getCurrentItemShowing())}));
                                RadialPickerLayout.access$1000(RadialPickerLayout.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$600", new Object[]{RadialPickerLayout.this}), true, RadialPickerLayout.this.getCurrentItemShowing());
                                Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$1100", new Object[]{RadialPickerLayout.this}).onValueSelected(Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialPickerLayout", "access$600", new Object[]{RadialPickerLayout.this}));
                            }
                        }, (long) this.TAP_TIMEOUT);
                    }
                }
                return true;
            case 1:
                if (this.mInputEnabled) {
                    this.mHandler.removeCallbacksAndMessages(null);
                    this.mDoingTouch = false;
                    if (this.mIsTouchingAmOrPm == 0 || this.mIsTouchingAmOrPm == 1) {
                        int isTouchingAmOrPm = this.mAmPmCirclesView.getIsTouchingAmOrPm(eventX, eventY);
                        this.mAmPmCirclesView.setAmOrPmPressed(-1);
                        this.mAmPmCirclesView.invalidate();
                        if (isTouchingAmOrPm == this.mIsTouchingAmOrPm) {
                            this.mAmPmCirclesView.setAmOrPm(isTouchingAmOrPm);
                            if (getIsCurrentlyAmOrPm() != isTouchingAmOrPm) {
                                Timepoint newSelection = new Timepoint(this.mCurrentTime);
                                if (this.mIsTouchingAmOrPm == 0) {
                                    newSelection.setAM();
                                } else if (this.mIsTouchingAmOrPm == 1) {
                                    newSelection.setPM();
                                }
                                newSelection = roundToValidTime(newSelection, 0);
                                reselectSelector(newSelection, false, 0);
                                this.mCurrentTime = newSelection;
                                this.mListener.onValueSelected(newSelection);
                            }
                        }
                        this.mIsTouchingAmOrPm = -1;
                        break;
                    }
                    if (this.mDownDegrees != -1) {
                        degrees = getDegreesFromCoords(eventX, eventY, this.mDoingMove, isInnerCircle);
                        if (getCurrentItemShowing() == 1) {
                            if ((degrees < 0 || degrees > 90) && degrees < 270) {
                                degrees = 180;
                            } else {
                                degrees = 0;
                            }
                        }
                        if (degrees != -1) {
                            value = roundToValidTime(getTimeFromDegrees(degrees, isInnerCircle[0].booleanValue(), !this.mDoingMove), getCurrentItemShowing());
                            reselectSelector(value, false, getCurrentItemShowing());
                            this.mCurrentTime = value;
                            this.mListener.onValueSelected(value);
                            this.mListener.advancePicker(getCurrentItemShowing());
                        }
                    }
                    this.mDoingMove = false;
                    return true;
                }
                Log.d("RadialPickerLayout", "Input was disabled, but received ACTION_UP.");
                this.mListener.enablePicker();
                return true;
                break;
            case 2:
                if (this.mInputEnabled) {
                    float dY = Math.abs(eventY - this.mDownY);
                    float dX = Math.abs(eventX - this.mDownX);
                    if (this.mDoingMove || dX > ((float) this.TOUCH_SLOP) || dY > ((float) this.TOUCH_SLOP)) {
                        if (this.mIsTouchingAmOrPm == 0 || this.mIsTouchingAmOrPm == 1) {
                            this.mHandler.removeCallbacksAndMessages(null);
                            if (this.mAmPmCirclesView.getIsTouchingAmOrPm(eventX, eventY) != this.mIsTouchingAmOrPm) {
                                this.mAmPmCirclesView.setAmOrPmPressed(-1);
                                this.mAmPmCirclesView.invalidate();
                                this.mIsTouchingAmOrPm = -1;
                                break;
                            }
                        } else if (this.mDownDegrees != -1) {
                            this.mDoingMove = true;
                            this.mHandler.removeCallbacksAndMessages(null);
                            degrees = getDegreesFromCoords(eventX, eventY, true, isInnerCircle);
                            if (getCurrentItemShowing() == 1) {
                                if ((degrees < 0 || degrees > 90) && degrees < 270) {
                                    degrees = 180;
                                } else {
                                    degrees = 0;
                                }
                            }
                            if (degrees != -1) {
                                value = roundToValidTime(getTimeFromDegrees(degrees, isInnerCircle[0].booleanValue(), false), getCurrentItemShowing());
                                reselectSelector(value, true, getCurrentItemShowing());
                                if (value != null && (this.mLastValueSelected == null || !this.mLastValueSelected.equals(value))) {
                                    this.mController.tryVibrate();
                                    this.mLastValueSelected = value;
                                    this.mListener.onValueSelected(value);
                                }
                            }
                            return true;
                        }
                    }
                }
                Log.e("RadialPickerLayout", "Input was disabled, but received ACTION_MOVE.");
                return true;
                break;
        }
        return false;
    }

    public boolean trySettingInputEnabled(boolean inputEnabled) {
        int i = 0;
        Ensighten.evaluateEvent(this, "trySettingInputEnabled", new Object[]{new Boolean(inputEnabled)});
        if (this.mDoingTouch && !inputEnabled) {
            return false;
        }
        this.mInputEnabled = inputEnabled;
        View view = this.mGrayBox;
        if (inputEnabled) {
            i = 4;
        }
        view.setVisibility(i);
        return true;
    }

    public void onInitializeAccessibilityNodeInfo(@NonNull AccessibilityNodeInfo info) {
        Ensighten.evaluateEvent(this, "onInitializeAccessibilityNodeInfo", new Object[]{info});
        super.onInitializeAccessibilityNodeInfo(info);
        if (VERSION.SDK_INT >= 21) {
            info.addAction(AccessibilityAction.ACTION_SCROLL_BACKWARD);
            info.addAction(AccessibilityAction.ACTION_SCROLL_FORWARD);
        } else if (VERSION.SDK_INT >= 16) {
            info.addAction(4096);
            info.addAction(8192);
        } else {
            info.addAction(4096);
            info.addAction(8192);
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        Ensighten.evaluateEvent(this, "dispatchPopulateAccessibilityEvent", new Object[]{event});
        if (event.getEventType() != 32) {
            return super.dispatchPopulateAccessibilityEvent(event);
        }
        event.getText().clear();
        Calendar time = Calendar.getInstance();
        time.set(10, getHours());
        time.set(12, getMinutes());
        time.set(13, getSeconds());
        long millis = time.getTimeInMillis();
        int flags = 1;
        if (this.mIs24HourMode) {
            flags = 1 | 128;
        }
        event.getText().add(DateUtils.formatDateTime(getContext(), millis, flags));
        return true;
    }

    public boolean performAccessibilityAction(int action, Bundle arguments) {
        Ensighten.evaluateEvent(this, "performAccessibilityAction", new Object[]{new Integer(action), arguments});
        if (super.performAccessibilityAction(action, arguments)) {
            return true;
        }
        int forward;
        int backward;
        int changeMultiplier = 0;
        if (VERSION.SDK_INT >= 16) {
            forward = 4096;
            backward = 8192;
        } else {
            forward = 4096;
            backward = 8192;
        }
        if (action == forward) {
            changeMultiplier = 1;
        } else if (action == backward) {
            changeMultiplier = -1;
        }
        if (changeMultiplier == 0) {
            return false;
        }
        int maxValue;
        Timepoint newSelection;
        int value = getCurrentlyShowingValue();
        int stepSize = 0;
        int currentItemShowing = getCurrentItemShowing();
        if (currentItemShowing == 0) {
            stepSize = 30;
            value %= 12;
        } else if (currentItemShowing == 1) {
            stepSize = 6;
        } else if (currentItemShowing == 2) {
            stepSize = 6;
        }
        value = RadialPickerLayout.snapOnly30s(value * stepSize, changeMultiplier) / stepSize;
        int minValue = 0;
        if (currentItemShowing != 0) {
            maxValue = 55;
        } else if (this.mIs24HourMode) {
            maxValue = 23;
        } else {
            maxValue = 12;
            minValue = 1;
        }
        if (value > maxValue) {
            value = minValue;
        } else if (value < minValue) {
            value = maxValue;
        }
        switch (currentItemShowing) {
            case 0:
                newSelection = new Timepoint(value, this.mCurrentTime.getMinute(), this.mCurrentTime.getSecond());
                break;
            case 1:
                newSelection = new Timepoint(this.mCurrentTime.getHour(), value, this.mCurrentTime.getSecond());
                break;
            case 2:
                newSelection = new Timepoint(this.mCurrentTime.getHour(), this.mCurrentTime.getMinute(), value);
                break;
            default:
                newSelection = this.mCurrentTime;
                break;
        }
        setItem(currentItemShowing, newSelection);
        this.mListener.onValueSelected(newSelection);
        return true;
    }
}
