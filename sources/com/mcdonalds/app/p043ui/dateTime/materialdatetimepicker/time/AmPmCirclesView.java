package com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time;

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
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.Utils;
import com.mcdonalds.gma.hongkong.C2658R;
import java.text.DateFormatSymbols;

/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.AmPmCirclesView */
public class AmPmCirclesView extends View {
    private boolean mAmDisabled;
    private int mAmOrPm;
    private int mAmOrPmPressed;
    private int mAmPmCircleRadius;
    private float mAmPmCircleRadiusMultiplier;
    private int mAmPmDisabledTextColor;
    private int mAmPmSelectedTextColor;
    private int mAmPmTextColor;
    private int mAmPmYCenter;
    private String mAmText;
    private int mAmXCenter;
    private float mCircleRadiusMultiplier;
    private boolean mDrawValuesReady;
    private boolean mIsInitialized = false;
    private final Paint mPaint = new Paint();
    private boolean mPmDisabled;
    private String mPmText;
    private int mPmXCenter;
    private int mSelectedAlpha;
    private int mSelectedColor;
    private int mTouchedColor;
    private int mUnselectedColor;

    public AmPmCirclesView(Context context) {
        super(context);
    }

    public void initialize(Context context, TimePickerController controller, int amOrPm) {
        Ensighten.evaluateEvent(this, "initialize", new Object[]{context, controller, new Integer(amOrPm)});
        if (this.mIsInitialized) {
            Log.e("AmPmCirclesView", "AmPmCirclesView may only be initialized once.");
            return;
        }
        Resources res = context.getResources();
        if (controller.isThemeDark()) {
            this.mUnselectedColor = ContextCompat.getColor(context, C2658R.color.mdtp_circle_background_dark_theme);
            this.mAmPmTextColor = ContextCompat.getColor(context, C2658R.color.mdtp_white);
            this.mAmPmDisabledTextColor = ContextCompat.getColor(context, C2658R.color.mdtp_date_picker_text_disabled_dark_theme);
            this.mSelectedAlpha = 255;
        } else {
            this.mUnselectedColor = ContextCompat.getColor(context, C2658R.color.mdtp_white);
            this.mAmPmTextColor = ContextCompat.getColor(context, C2658R.color.mdtp_ampm_text_color);
            this.mAmPmDisabledTextColor = ContextCompat.getColor(context, C2658R.color.mdtp_date_picker_text_disabled);
            this.mSelectedAlpha = 255;
        }
        this.mSelectedColor = controller.getAccentColor();
        this.mTouchedColor = Utils.darkenColor(this.mSelectedColor);
        this.mAmPmSelectedTextColor = ContextCompat.getColor(context, C2658R.color.mdtp_white);
        this.mPaint.setTypeface(Typeface.create(res.getString(C2658R.string.mdtp_sans_serif), 0));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextAlign(Align.CENTER);
        this.mCircleRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_circle_radius_multiplier));
        this.mAmPmCircleRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_ampm_circle_radius_multiplier));
        String[] amPmTexts = new DateFormatSymbols().getAmPmStrings();
        this.mAmText = amPmTexts[0];
        this.mPmText = amPmTexts[1];
        this.mAmDisabled = controller.isAmDisabled();
        this.mPmDisabled = controller.isPmDisabled();
        setAmOrPm(amOrPm);
        this.mAmOrPmPressed = -1;
        this.mIsInitialized = true;
    }

    public void setAmOrPm(int amOrPm) {
        Ensighten.evaluateEvent(this, "setAmOrPm", new Object[]{new Integer(amOrPm)});
        this.mAmOrPm = amOrPm;
    }

    public void setAmOrPmPressed(int amOrPmPressed) {
        Ensighten.evaluateEvent(this, "setAmOrPmPressed", new Object[]{new Integer(amOrPmPressed)});
        this.mAmOrPmPressed = amOrPmPressed;
    }

    public int getIsTouchingAmOrPm(float xCoord, float yCoord) {
        Ensighten.evaluateEvent(this, "getIsTouchingAmOrPm", new Object[]{new Float(xCoord), new Float(yCoord)});
        if (!this.mDrawValuesReady) {
            return -1;
        }
        int squaredYDistance = (int) ((yCoord - ((float) this.mAmPmYCenter)) * (yCoord - ((float) this.mAmPmYCenter)));
        if (((int) Math.sqrt((double) (((xCoord - ((float) this.mAmXCenter)) * (xCoord - ((float) this.mAmXCenter))) + ((float) squaredYDistance)))) <= this.mAmPmCircleRadius && !this.mAmDisabled) {
            return 0;
        }
        if (((int) Math.sqrt((double) (((xCoord - ((float) this.mPmXCenter)) * (xCoord - ((float) this.mPmXCenter))) + ((float) squaredYDistance)))) > this.mAmPmCircleRadius || this.mPmDisabled) {
            return -1;
        }
        return 1;
    }

    public void onDraw(Canvas canvas) {
        Ensighten.evaluateEvent(this, "onDraw", new Object[]{canvas});
        if (getWidth() != 0 && this.mIsInitialized) {
            if (!this.mDrawValuesReady) {
                int layoutXCenter = getWidth() / 2;
                int layoutYCenter = getHeight() / 2;
                int circleRadius = (int) (((float) Math.min(layoutXCenter, layoutYCenter)) * this.mCircleRadiusMultiplier);
                this.mAmPmCircleRadius = (int) (((float) circleRadius) * this.mAmPmCircleRadiusMultiplier);
                layoutYCenter = (int) (((double) layoutYCenter) + (((double) this.mAmPmCircleRadius) * 0.75d));
                this.mPaint.setTextSize((float) ((this.mAmPmCircleRadius * 3) / 4));
                this.mAmPmYCenter = (layoutYCenter - (this.mAmPmCircleRadius / 2)) + circleRadius;
                this.mAmXCenter = (layoutXCenter - circleRadius) + this.mAmPmCircleRadius;
                this.mPmXCenter = (layoutXCenter + circleRadius) - this.mAmPmCircleRadius;
                this.mDrawValuesReady = true;
            }
            int amColor = this.mUnselectedColor;
            int amAlpha = 255;
            int amTextColor = this.mAmPmTextColor;
            int pmColor = this.mUnselectedColor;
            int pmAlpha = 255;
            int pmTextColor = this.mAmPmTextColor;
            if (this.mAmOrPm == 0) {
                amColor = this.mSelectedColor;
                amAlpha = this.mSelectedAlpha;
                amTextColor = this.mAmPmSelectedTextColor;
            } else if (this.mAmOrPm == 1) {
                pmColor = this.mSelectedColor;
                pmAlpha = this.mSelectedAlpha;
                pmTextColor = this.mAmPmSelectedTextColor;
            }
            if (this.mAmOrPmPressed == 0) {
                amColor = this.mTouchedColor;
                amAlpha = this.mSelectedAlpha;
            } else if (this.mAmOrPmPressed == 1) {
                pmColor = this.mTouchedColor;
                pmAlpha = this.mSelectedAlpha;
            }
            if (this.mAmDisabled) {
                amColor = this.mUnselectedColor;
                amTextColor = this.mAmPmDisabledTextColor;
            }
            if (this.mPmDisabled) {
                pmColor = this.mUnselectedColor;
                pmTextColor = this.mAmPmDisabledTextColor;
            }
            this.mPaint.setColor(amColor);
            this.mPaint.setAlpha(amAlpha);
            canvas.drawCircle((float) this.mAmXCenter, (float) this.mAmPmYCenter, (float) this.mAmPmCircleRadius, this.mPaint);
            this.mPaint.setColor(pmColor);
            this.mPaint.setAlpha(pmAlpha);
            canvas.drawCircle((float) this.mPmXCenter, (float) this.mAmPmYCenter, (float) this.mAmPmCircleRadius, this.mPaint);
            this.mPaint.setColor(amTextColor);
            int textYCenter = this.mAmPmYCenter - (((int) (this.mPaint.descent() + this.mPaint.ascent())) / 2);
            canvas.drawText(this.mAmText, (float) this.mAmXCenter, (float) textYCenter, this.mPaint);
            this.mPaint.setColor(pmTextColor);
            canvas.drawText(this.mPmText, (float) this.mPmXCenter, (float) textYCenter, this.mPaint);
        }
    }
}
