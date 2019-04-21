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
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.support.p000v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.TimePickerDialog.Version;
import com.mcdonalds.gma.hongkong.C2658R;

/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialTextsView */
public class RadialTextsView extends View {
    private float mAmPmCircleRadiusMultiplier;
    private float mAnimationRadiusMultiplier;
    private float mCircleRadius;
    private float mCircleRadiusMultiplier;
    ObjectAnimator mDisappearAnimator;
    private boolean mDrawValuesReady;
    private boolean mHasInnerCircle;
    private final Paint mInactivePaint = new Paint();
    private float mInnerNumbersRadiusMultiplier;
    private float[] mInnerTextGridHeights;
    private float[] mInnerTextGridWidths;
    private float mInnerTextSize;
    private float mInnerTextSizeMultiplier;
    private String[] mInnerTexts;
    private InvalidateUpdateListener mInvalidateUpdateListener;
    private boolean mIs24HourMode;
    private boolean mIsInitialized = false;
    private float mNumbersRadiusMultiplier;
    private final Paint mPaint = new Paint();
    ObjectAnimator mReappearAnimator;
    private final Paint mSelectedPaint = new Paint();
    private float[] mTextGridHeights;
    private boolean mTextGridValuesDirty;
    private float[] mTextGridWidths;
    private float mTextSize;
    private float mTextSizeMultiplier;
    private String[] mTexts;
    private float mTransitionEndRadiusMultiplier;
    private float mTransitionMidRadiusMultiplier;
    private Typeface mTypefaceLight;
    private Typeface mTypefaceRegular;
    private SelectionValidator mValidator;
    private int mXCenter;
    private int mYCenter;
    private int selection = -1;

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialTextsView$SelectionValidator */
    interface SelectionValidator {
        boolean isValidSelection(int i);
    }

    /* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.RadialTextsView$InvalidateUpdateListener */
    private class InvalidateUpdateListener implements AnimatorUpdateListener {
        private InvalidateUpdateListener() {
        }

        public void onAnimationUpdate(ValueAnimator animation) {
            Ensighten.evaluateEvent(this, "onAnimationUpdate", new Object[]{animation});
            RadialTextsView.this.invalidate();
        }
    }

    public RadialTextsView(Context context) {
        super(context);
    }

    public void initialize(Context context, String[] texts, String[] innerTexts, TimePickerController controller, SelectionValidator validator, boolean disappearsOut) {
        Ensighten.evaluateEvent(this, "initialize", new Object[]{context, texts, innerTexts, controller, validator, new Boolean(disappearsOut)});
        if (this.mIsInitialized) {
            Log.e("RadialTextsView", "This RadialTextsView may only be initialized once.");
            return;
        }
        Resources res = context.getResources();
        this.mPaint.setColor(ContextCompat.getColor(context, controller.isThemeDark() ? C2658R.color.mdtp_white : C2658R.color.mdtp_numbers_text_color));
        this.mTypefaceLight = Typeface.create(res.getString(C2658R.string.mdtp_radial_numbers_typeface), 0);
        this.mTypefaceRegular = Typeface.create(res.getString(C2658R.string.mdtp_sans_serif), 0);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextAlign(Align.CENTER);
        this.mSelectedPaint.setColor(ContextCompat.getColor(context, C2658R.color.mdtp_white));
        this.mSelectedPaint.setAntiAlias(true);
        this.mSelectedPaint.setTextAlign(Align.CENTER);
        this.mInactivePaint.setColor(ContextCompat.getColor(context, controller.isThemeDark() ? C2658R.color.mdtp_date_picker_text_disabled_dark_theme : C2658R.color.mdtp_date_picker_text_disabled));
        this.mInactivePaint.setAntiAlias(true);
        this.mInactivePaint.setTextAlign(Align.CENTER);
        this.mTexts = texts;
        this.mInnerTexts = innerTexts;
        this.mIs24HourMode = controller.is24HourMode();
        this.mHasInnerCircle = innerTexts != null;
        if (this.mIs24HourMode || controller.getVersion() != Version.VERSION_1) {
            this.mCircleRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_circle_radius_multiplier_24HourMode));
        } else {
            this.mCircleRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_circle_radius_multiplier));
            this.mAmPmCircleRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_ampm_circle_radius_multiplier));
        }
        this.mTextGridHeights = new float[7];
        this.mTextGridWidths = new float[7];
        if (this.mHasInnerCircle) {
            this.mNumbersRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_numbers_radius_multiplier_outer));
            this.mInnerNumbersRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_numbers_radius_multiplier_inner));
            if (controller.getVersion() == Version.VERSION_1) {
                this.mTextSizeMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_text_size_multiplier_outer));
                this.mInnerTextSizeMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_text_size_multiplier_inner));
            } else {
                this.mTextSizeMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_text_size_multiplier_outer_v2));
                this.mInnerTextSizeMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_text_size_multiplier_inner_v2));
            }
            this.mInnerTextGridHeights = new float[7];
            this.mInnerTextGridWidths = new float[7];
        } else {
            this.mNumbersRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_numbers_radius_multiplier_normal));
            this.mTextSizeMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_text_size_multiplier_normal));
        }
        this.mAnimationRadiusMultiplier = 1.0f;
        this.mTransitionMidRadiusMultiplier = (((float) (disappearsOut ? -1 : 1)) * 0.05f) + 1.0f;
        this.mTransitionEndRadiusMultiplier = (((float) (disappearsOut ? 1 : -1)) * 0.3f) + 1.0f;
        this.mInvalidateUpdateListener = new InvalidateUpdateListener();
        this.mValidator = validator;
        this.mTextGridValuesDirty = true;
        this.mIsInitialized = true;
    }

    /* Access modifiers changed, original: protected */
    public void setSelection(int selection) {
        Ensighten.evaluateEvent(this, "setSelection", new Object[]{new Integer(selection)});
        this.selection = selection;
    }

    public boolean hasOverlappingRendering() {
        Ensighten.evaluateEvent(this, "hasOverlappingRendering", null);
        return false;
    }

    public void setAnimationRadiusMultiplier(float animationRadiusMultiplier) {
        Ensighten.evaluateEvent(this, "setAnimationRadiusMultiplier", new Object[]{new Float(animationRadiusMultiplier)});
        this.mAnimationRadiusMultiplier = animationRadiusMultiplier;
        this.mTextGridValuesDirty = true;
    }

    public void onDraw(Canvas canvas) {
        Ensighten.evaluateEvent(this, "onDraw", new Object[]{canvas});
        if (getWidth() != 0 && this.mIsInitialized) {
            if (!this.mDrawValuesReady) {
                this.mXCenter = getWidth() / 2;
                this.mYCenter = getHeight() / 2;
                this.mCircleRadius = ((float) Math.min(this.mXCenter, this.mYCenter)) * this.mCircleRadiusMultiplier;
                if (!this.mIs24HourMode) {
                    this.mYCenter = (int) (((double) this.mYCenter) - (((double) (this.mCircleRadius * this.mAmPmCircleRadiusMultiplier)) * 0.75d));
                }
                this.mTextSize = this.mCircleRadius * this.mTextSizeMultiplier;
                if (this.mHasInnerCircle) {
                    this.mInnerTextSize = this.mCircleRadius * this.mInnerTextSizeMultiplier;
                }
                renderAnimations();
                this.mTextGridValuesDirty = true;
                this.mDrawValuesReady = true;
            }
            if (this.mTextGridValuesDirty) {
                calculateGridSizes((this.mCircleRadius * this.mNumbersRadiusMultiplier) * this.mAnimationRadiusMultiplier, (float) this.mXCenter, (float) this.mYCenter, this.mTextSize, this.mTextGridHeights, this.mTextGridWidths);
                if (this.mHasInnerCircle) {
                    calculateGridSizes((this.mCircleRadius * this.mInnerNumbersRadiusMultiplier) * this.mAnimationRadiusMultiplier, (float) this.mXCenter, (float) this.mYCenter, this.mInnerTextSize, this.mInnerTextGridHeights, this.mInnerTextGridWidths);
                }
                this.mTextGridValuesDirty = false;
            }
            drawTexts(canvas, this.mTextSize, this.mTypefaceLight, this.mTexts, this.mTextGridWidths, this.mTextGridHeights);
            if (this.mHasInnerCircle) {
                drawTexts(canvas, this.mInnerTextSize, this.mTypefaceRegular, this.mInnerTexts, this.mInnerTextGridWidths, this.mInnerTextGridHeights);
            }
        }
    }

    private void calculateGridSizes(float numbersRadius, float xCenter, float yCenter, float textSize, float[] textGridHeights, float[] textGridWidths) {
        Ensighten.evaluateEvent(this, "calculateGridSizes", new Object[]{new Float(numbersRadius), new Float(xCenter), new Float(yCenter), new Float(textSize), textGridHeights, textGridWidths});
        float offset1 = numbersRadius;
        float offset2 = (((float) Math.sqrt(3.0d)) * numbersRadius) / 2.0f;
        float offset3 = numbersRadius / 2.0f;
        this.mPaint.setTextSize(textSize);
        this.mSelectedPaint.setTextSize(textSize);
        this.mInactivePaint.setTextSize(textSize);
        yCenter -= (this.mPaint.descent() + this.mPaint.ascent()) / 2.0f;
        textGridHeights[0] = yCenter - offset1;
        textGridWidths[0] = xCenter - offset1;
        textGridHeights[1] = yCenter - offset2;
        textGridWidths[1] = xCenter - offset2;
        textGridHeights[2] = yCenter - offset3;
        textGridWidths[2] = xCenter - offset3;
        textGridHeights[3] = yCenter;
        textGridWidths[3] = xCenter;
        textGridHeights[4] = yCenter + offset3;
        textGridWidths[4] = xCenter + offset3;
        textGridHeights[5] = yCenter + offset2;
        textGridWidths[5] = xCenter + offset2;
        textGridHeights[6] = yCenter + offset1;
        textGridWidths[6] = xCenter + offset1;
    }

    private Paint[] assignTextColors(String[] texts) {
        Ensighten.evaluateEvent(this, "assignTextColors", new Object[]{texts});
        Paint[] paints = new Paint[texts.length];
        for (int i = 0; i < texts.length; i++) {
            int text = Integer.parseInt(texts[i]);
            if (text == this.selection) {
                paints[i] = this.mSelectedPaint;
            } else if (this.mValidator.isValidSelection(text)) {
                paints[i] = this.mPaint;
            } else {
                paints[i] = this.mInactivePaint;
            }
        }
        return paints;
    }

    private void drawTexts(Canvas canvas, float textSize, Typeface typeface, String[] texts, float[] textGridWidths, float[] textGridHeights) {
        Ensighten.evaluateEvent(this, "drawTexts", new Object[]{canvas, new Float(textSize), typeface, texts, textGridWidths, textGridHeights});
        this.mPaint.setTextSize(textSize);
        this.mPaint.setTypeface(typeface);
        Paint[] textPaints = assignTextColors(texts);
        canvas.drawText(texts[0], textGridWidths[3], textGridHeights[0], textPaints[0]);
        canvas.drawText(texts[1], textGridWidths[4], textGridHeights[1], textPaints[1]);
        canvas.drawText(texts[2], textGridWidths[5], textGridHeights[2], textPaints[2]);
        canvas.drawText(texts[3], textGridWidths[6], textGridHeights[3], textPaints[3]);
        canvas.drawText(texts[4], textGridWidths[5], textGridHeights[4], textPaints[4]);
        canvas.drawText(texts[5], textGridWidths[4], textGridHeights[5], textPaints[5]);
        canvas.drawText(texts[6], textGridWidths[3], textGridHeights[6], textPaints[6]);
        canvas.drawText(texts[7], textGridWidths[2], textGridHeights[5], textPaints[7]);
        canvas.drawText(texts[8], textGridWidths[1], textGridHeights[4], textPaints[8]);
        canvas.drawText(texts[9], textGridWidths[0], textGridHeights[3], textPaints[9]);
        canvas.drawText(texts[10], textGridWidths[1], textGridHeights[2], textPaints[10]);
        canvas.drawText(texts[11], textGridWidths[2], textGridHeights[1], textPaints[11]);
    }

    private void renderAnimations() {
        Ensighten.evaluateEvent(this, "renderAnimations", null);
        Keyframe kf0 = Keyframe.ofFloat(0.0f, 1.0f);
        Keyframe kf1 = Keyframe.ofFloat(0.2f, this.mTransitionMidRadiusMultiplier);
        Keyframe kf2 = Keyframe.ofFloat(1.0f, this.mTransitionEndRadiusMultiplier);
        PropertyValuesHolder radiusDisappear = PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{kf0, kf1, kf2});
        kf0 = Keyframe.ofFloat(0.0f, 1.0f);
        kf1 = Keyframe.ofFloat(1.0f, 0.0f);
        PropertyValuesHolder fadeOut = PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{kf0, kf1});
        this.mDisappearAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{radiusDisappear, fadeOut}).setDuration((long) 500);
        this.mDisappearAnimator.addUpdateListener(this.mInvalidateUpdateListener);
        int totalDuration = (int) (((float) 500) * (1.0f + 0.25f));
        float delayPoint = (((float) 500) * 0.25f) / ((float) totalDuration);
        float midwayPoint = 1.0f - ((1.0f - delayPoint) * 0.2f);
        kf0 = Keyframe.ofFloat(0.0f, this.mTransitionEndRadiusMultiplier);
        kf1 = Keyframe.ofFloat(delayPoint, this.mTransitionEndRadiusMultiplier);
        kf2 = Keyframe.ofFloat(midwayPoint, this.mTransitionMidRadiusMultiplier);
        Keyframe kf3 = Keyframe.ofFloat(1.0f, 1.0f);
        PropertyValuesHolder radiusReappear = PropertyValuesHolder.ofKeyframe("animationRadiusMultiplier", new Keyframe[]{kf0, kf1, kf2, kf3});
        kf0 = Keyframe.ofFloat(0.0f, 0.0f);
        kf1 = Keyframe.ofFloat(delayPoint, 0.0f);
        kf2 = Keyframe.ofFloat(1.0f, 1.0f);
        PropertyValuesHolder fadeIn = PropertyValuesHolder.ofKeyframe("alpha", new Keyframe[]{kf0, kf1, kf2});
        this.mReappearAnimator = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{radiusReappear, fadeIn}).setDuration((long) totalDuration);
        this.mReappearAnimator.addUpdateListener(this.mInvalidateUpdateListener);
    }

    public ObjectAnimator getDisappearAnimator() {
        Ensighten.evaluateEvent(this, "getDisappearAnimator", null);
        if (this.mIsInitialized && this.mDrawValuesReady && this.mDisappearAnimator != null) {
            return this.mDisappearAnimator;
        }
        Log.e("RadialTextsView", "RadialTextView was not ready for animation.");
        return null;
    }

    public ObjectAnimator getReappearAnimator() {
        Ensighten.evaluateEvent(this, "getReappearAnimator", null);
        if (this.mIsInitialized && this.mDrawValuesReady && this.mReappearAnimator != null) {
            return this.mReappearAnimator;
        }
        Log.e("RadialTextsView", "RadialTextView was not ready for animation.");
        return null;
    }
}
