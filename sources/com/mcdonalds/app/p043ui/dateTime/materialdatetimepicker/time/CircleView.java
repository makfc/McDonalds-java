package com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.p000v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.TimePickerDialog.Version;
import com.mcdonalds.gma.hongkong.C2658R;

/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.CircleView */
public class CircleView extends View {
    private float mAmPmCircleRadiusMultiplier;
    private int mCircleColor;
    private int mCircleRadius;
    private float mCircleRadiusMultiplier;
    private int mDotColor;
    private boolean mDrawValuesReady;
    private boolean mIs24HourMode;
    private boolean mIsInitialized = false;
    private final Paint mPaint = new Paint();
    private int mXCenter;
    private int mYCenter;

    public CircleView(Context context) {
        super(context);
    }

    public void initialize(Context context, TimePickerController controller) {
        Ensighten.evaluateEvent(this, "initialize", new Object[]{context, controller});
        if (this.mIsInitialized) {
            Log.e("CircleView", "CircleView may only be initialized once.");
            return;
        }
        Resources res = context.getResources();
        this.mCircleColor = ContextCompat.getColor(context, controller.isThemeDark() ? C2658R.color.mdtp_circle_background_dark_theme : C2658R.color.mdtp_circle_color);
        this.mDotColor = controller.getAccentColor();
        this.mPaint.setAntiAlias(true);
        this.mIs24HourMode = controller.is24HourMode();
        if (this.mIs24HourMode || controller.getVersion() != Version.VERSION_1) {
            this.mCircleRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_circle_radius_multiplier_24HourMode));
        } else {
            this.mCircleRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_circle_radius_multiplier));
            this.mAmPmCircleRadiusMultiplier = Float.parseFloat(res.getString(C2658R.string.mdtp_ampm_circle_radius_multiplier));
        }
        this.mIsInitialized = true;
    }

    public void onDraw(Canvas canvas) {
        Ensighten.evaluateEvent(this, "onDraw", new Object[]{canvas});
        if (getWidth() != 0 && this.mIsInitialized) {
            if (!this.mDrawValuesReady) {
                this.mXCenter = getWidth() / 2;
                this.mYCenter = getHeight() / 2;
                this.mCircleRadius = (int) (((float) Math.min(this.mXCenter, this.mYCenter)) * this.mCircleRadiusMultiplier);
                if (!this.mIs24HourMode) {
                    this.mYCenter = (int) (((double) this.mYCenter) - (((double) ((int) (((float) this.mCircleRadius) * this.mAmPmCircleRadiusMultiplier))) * 0.75d));
                }
                this.mDrawValuesReady = true;
            }
            this.mPaint.setColor(this.mCircleColor);
            canvas.drawCircle((float) this.mXCenter, (float) this.mYCenter, (float) this.mCircleRadius, this.mPaint);
            this.mPaint.setColor(this.mDotColor);
            canvas.drawCircle((float) this.mXCenter, (float) this.mYCenter, 8.0f, this.mPaint);
        }
    }
}
