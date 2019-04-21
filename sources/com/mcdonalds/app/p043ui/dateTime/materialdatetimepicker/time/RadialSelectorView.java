package com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.TimePickerDialog.Version;
import com.mcdonalds.gma.hongkong.C2658R;

/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialSelectorView */
public class RadialSelectorView extends View {
    private float mAmPmCircleRadiusMultiplier;
    private float mAnimationRadiusMultiplier;
    private int mCircleRadius;
    private float mCircleRadiusMultiplier;
    private boolean mDrawValuesReady;
    private boolean mForceDrawDot;
    private boolean mHasInnerCircle;
    private float mInnerNumbersRadiusMultiplier;
    private InvalidateUpdateListener mInvalidateUpdateListener;
    private boolean mIs24HourMode;
    private boolean mIsInitialized = false;
    private int mLineLength;
    private float mNumbersRadiusMultiplier;
    private float mOuterNumbersRadiusMultiplier;
    private final Paint mPaint = new Paint();
    private int mSelectionAlpha;
    private int mSelectionDegrees;
    private double mSelectionRadians;
    private int mSelectionRadius;
    private float mSelectionRadiusMultiplier;
    private float mTransitionEndRadiusMultiplier;
    private float mTransitionMidRadiusMultiplier;
    private int mXCenter;
    private int mYCenter;

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialSelectorView$InvalidateUpdateListener */
    private class InvalidateUpdateListener implements AnimatorUpdateListener {
        private InvalidateUpdateListener() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            Ensighten.evaluateEvent(this, "onAnimationUpdate", new Object[]{animation});
            RadialSelectorView.this.invalidate();
        }
    }

    public RadialSelectorView(Context context) {
        super(context);
    }

    public void initialize(Context context, TimePickerController controller, boolean hasInnerCircle, boolean disappearsOut, int selectionDegrees, boolean isInnerCircle) {
        Ensighten.evaluateEvent(this, "initialize", new Object[]{context, controller, new Boolean(hasInnerCircle), new Boolean(disappearsOut), new Integer(selectionDegrees), new Boolean(isInnerCircle)});
        if (this.mIsInitialized) {
            Log.e("RadialSelectorView", "This RadialSelectorView may only be initialized once.");
            return;
        }
        Resources res = context.getResources();
        this.mPaint.setColor(controller.getAccentColor());
        this.mPaint.setAntiAlias(true);
        this.mSelectionAlpha = controller.isThemeDark() ? 255 : 255;
        this.mIs24HourMode = controller.is24HourMode();
        if (this.mIs24HourMode || controller.getVersion() != Version.VERSION_1) {
            this.mCircleRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_circle_radius_multiplier_24HourMode));
        } else {
            this.mCircleRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_circle_radius_multiplier));
            this.mAmPmCircleRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_ampm_circle_radius_multiplier));
        }
        this.mHasInnerCircle = hasInnerCircle;
        if (hasInnerCircle) {
            this.mInnerNumbersRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_numbers_radius_multiplier_inner));
            this.mOuterNumbersRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_numbers_radius_multiplier_outer));
        } else {
            this.mNumbersRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_numbers_radius_multiplier_normal));
        }
        this.mSelectionRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_selection_radius_multiplier));
        this.mAnimationRadiusMultiplier = 1.0f;
        this.mTransitionMidRadiusMultiplier = (((float) (disappearsOut ? -1 : 1)) * 0.05f) + 1.0f;
        this.mTransitionEndRadiusMultiplier = (((float) (disappearsOut ? 1 : -1)) * 0.3f) + 1.0f;
        this.mInvalidateUpdateListener = new InvalidateUpdateListener();
        setSelection(selectionDegrees, isInnerCircle, false);
        this.mIsInitialized = true;
    }

    public void setSelection(int selectionDegrees, boolean isInnerCircle, boolean forceDrawDot) {
        Ensighten.evaluateEvent(this, "setSelection", new Object[]{new Integer(selectionDegrees), new Boolean(isInnerCircle), new Boolean(forceDrawDot)});
        this.mSelectionDegrees = selectionDegrees;
        this.mSelectionRadians = (((double) selectionDegrees) * 3.141592653589793d) / 180.0d;
        this.mForceDrawDot = forceDrawDot;
        if (!this.mHasInnerCircle) {
            return;
        }
        if (isInnerCircle) {
            this.mNumbersRadiusMultiplier = this.mInnerNumbersRadiusMultiplier;
        } else {
            this.mNumbersRadiusMultiplier = this.mOuterNumbersRadiusMultiplier;
        }
    }

    public boolean hasOverlappingRendering() {
        Ensighten.evaluateEvent(this, "hasOverlappingRendering", null);
        return false;
    }

    public void setAnimationRadiusMultiplier(float animationRadiusMultiplier) {
        Ensighten.evaluateEvent(this, "setAnimationRadiusMultiplier", new Object[]{new Float(animationRadiusMultiplier)});
        this.mAnimationRadiusMultiplier = animationRadiusMultiplier;
    }

    public int getDegreesFromCoords(float pointX, float pointY, boolean forceLegal, Boolean[] isInnerCircle) {
        Ensighten.evaluateEvent(this, "getDegreesFromCoords", new Object[]{new Float(pointX), new Float(pointY), new Boolean(forceLegal), isInnerCircle});
        if (!this.mDrawValuesReady) {
            return -1;
        }
        double hypotenuse = Math.sqrt((double) (((pointY - ((float) this.mYCenter)) * (pointY - ((float) this.mYCenter))) + ((pointX - ((float) this.mXCenter)) * (pointX - ((float) this.mXCenter)))));
        if (this.mHasInnerCircle) {
            if (forceLegal) {
                boolean z;
                if (((int) Math.abs(hypotenuse - ((double) ((int) (((float) this.mCircleRadius) * this.mInnerNumbersRadiusMultiplier))))) <= ((int) Math.abs(hypotenuse - ((double) ((int) (((float) this.mCircleRadius) * this.mOuterNumbersRadiusMultiplier)))))) {
                    z = true;
                } else {
                    z = false;
                }
                isInnerCircle[0] = Boolean.valueOf(z);
            } else {
                int maxAllowedHypotenuseForOuterNumber = ((int) (((float) this.mCircleRadius) * this.mOuterNumbersRadiusMultiplier)) + this.mSelectionRadius;
                int halfwayHypotenusePoint = (int) (((float) this.mCircleRadius) * ((this.mOuterNumbersRadiusMultiplier + this.mInnerNumbersRadiusMultiplier) / 2.0f));
                if (hypotenuse >= ((double) (((int) (((float) this.mCircleRadius) * this.mInnerNumbersRadiusMultiplier)) - this.mSelectionRadius)) && hypotenuse <= ((double) halfwayHypotenusePoint)) {
                    isInnerCircle[0] = Boolean.valueOf(true);
                } else if (hypotenuse > ((double) maxAllowedHypotenuseForOuterNumber) || hypotenuse < ((double) halfwayHypotenusePoint)) {
                    return -1;
                } else {
                    isInnerCircle[0] = Boolean.valueOf(false);
                }
            }
        } else if (!forceLegal && ((int) Math.abs(hypotenuse - ((double) this.mLineLength))) > ((int) (((float) this.mCircleRadius) * (1.0f - this.mNumbersRadiusMultiplier)))) {
            return -1;
        }
        int degrees = (int) ((180.0d * Math.asin(((double) Math.abs(pointY - ((float) this.mYCenter))) / hypotenuse)) / 3.141592653589793d);
        boolean rightSide = pointX > ((float) this.mXCenter);
        boolean topSide = pointY < ((float) this.mYCenter);
        if (rightSide && topSide) {
            return 90 - degrees;
        }
        if (rightSide && !topSide) {
            return degrees + 90;
        }
        if (!rightSide && !topSide) {
            return 270 - degrees;
        }
        if (rightSide || !topSide) {
            return degrees;
        }
        return degrees + 270;
    }

    public void onDraw(Canvas canvas) {
        int i = 1;
        Ensighten.evaluateEvent(this, "onDraw", new Object[]{canvas});
        if (getWidth() != 0 && this.mIsInitialized) {
            if (!this.mDrawValuesReady) {
                this.mXCenter = getWidth() / 2;
                this.mYCenter = getHeight() / 2;
                this.mCircleRadius = (int) (((float) Math.min(this.mXCenter, this.mYCenter)) * this.mCircleRadiusMultiplier);
                if (!this.mIs24HourMode) {
                    this.mYCenter = (int) (((double) this.mYCenter) - (((double) ((int) (((float) this.mCircleRadius) * this.mAmPmCircleRadiusMultiplier))) * 0.75d));
                }
                this.mSelectionRadius = (int) (((float) this.mCircleRadius) * this.mSelectionRadiusMultiplier);
                this.mDrawValuesReady = true;
            }
            this.mLineLength = (int) ((((float) this.mCircleRadius) * this.mNumbersRadiusMultiplier) * this.mAnimationRadiusMultiplier);
            int pointX = this.mXCenter + ((int) (((double) this.mLineLength) * Math.sin(this.mSelectionRadians)));
            int pointY = this.mYCenter - ((int) (((double) this.mLineLength) * Math.cos(this.mSelectionRadians)));
            this.mPaint.setAlpha(this.mSelectionAlpha);
            canvas.drawCircle((float) pointX, (float) pointY, (float) this.mSelectionRadius, this.mPaint);
            boolean z = this.mForceDrawDot;
            if (this.mSelectionDegrees % 30 == 0) {
                i = 0;
            }
            if ((i | z) != 0) {
                this.mPaint.setAlpha(255);
                canvas.drawCircle((float) pointX, (float) pointY, (float) ((this.mSelectionRadius * 2) / 7), this.mPaint);
            } else {
                int lineLength = this.mLineLength - this.mSelectionRadius;
                pointX = this.mXCenter + ((int) (((double) lineLength) * Math.sin(this.mSelectionRadians)));
                pointY = this.mYCenter - ((int) (((double) lineLength) * Math.cos(this.mSelectionRadians)));
            }
            this.mPaint.setAlpha(255);
            this.mPaint.setStrokeWidth(3.0f);
            canvas.drawLine((float) this.mXCenter, (float) this.mYCenter, (float) pointX, (float) pointY, this.mPaint);
        }
    }

    public ObjectAnimator getDisappearAnimator() {
        Ensighten.evaluateEvent(this, "getDisappearAnimator", null);
        if (this.mIsInitialized && this.mDrawValuesReady) {
            Keyframe kf0 = Keyframe.ofFloat(0.0f, 1.0f);
            Keyframe kf1 = Keyframe.ofFloat(0.2f, this.mTransitionMidRadiusMultiplier);
            Keyframe kf2 = Keyframe.ofFloat(1.0f, this.mTransitionEndRadiusMultiplier);
            PropertyValuesHolder radiusDisappear = PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{kf0, kf1, kf2});
            kf0 = Keyframe.ofFloat(0.0f, 1.0f);
            kf1 = Keyframe.ofFloat(1.0f, 0.0f);
            PropertyValuesHolder fadeOut = PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{kf0, kf1});
            ObjectAnimator disappearAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{radiusDisappear, fadeOut}).setDuration((long) VTMCDataCache.MAXSIZE);
            disappearAnimator.addUpdateListener(this.mInvalidateUpdateListener);
            return disappearAnimator;
        }
        Log.e("RadialSelectorView", "RadialSelectorView was not ready for animation.");
        return null;
    }

    public ObjectAnimator getReappearAnimator() {
        Ensighten.evaluateEvent(this, "getReappearAnimator", null);
        if (this.mIsInitialized && this.mDrawValuesReady) {
            int totalDuration = (int) (((float) 500) * (1.0f + 0.25f));
            float delayPoint = (((float) 500) * 0.25f) / ((float) totalDuration);
            float midwayPoint = 1.0f - ((1.0f - delayPoint) * 0.2f);
            Keyframe kf0 = Keyframe.ofFloat(0.0f, this.mTransitionEndRadiusMultiplier);
            Keyframe kf1 = Keyframe.ofFloat(delayPoint, this.mTransitionEndRadiusMultiplier);
            Keyframe kf2 = Keyframe.ofFloat(midwayPoint, this.mTransitionMidRadiusMultiplier);
            Keyframe kf3 = Keyframe.ofFloat(1.0f, 1.0f);
            PropertyValuesHolder radiusReappear = PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{kf0, kf1, kf2, kf3});
            kf0 = Keyframe.ofFloat(0.0f, 0.0f);
            kf1 = Keyframe.ofFloat(delayPoint, 0.0f);
            kf2 = Keyframe.ofFloat(1.0f, 1.0f);
            PropertyValuesHolder fadeIn = PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{kf0, kf1, kf2});
            ObjectAnimator reappearAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{radiusReappear, fadeIn}).setDuration((long) totalDuration);
            reappearAnimator.addUpdateListener(this.mInvalidateUpdateListener);
            return reappearAnimator;
        }
        Log.e("RadialSelectorView", "RadialSelectorView was not ready for animation.");
        return null;
    }
}
