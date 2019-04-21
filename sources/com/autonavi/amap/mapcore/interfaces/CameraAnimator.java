package com.autonavi.amap.mapcore.interfaces;

import android.content.Context;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

public class CameraAnimator {
    public static final int CHANGE_CAMERA_MODE = 2;
    private static float DECELERATION_RATE = ((float) (Math.log(0.78d) / Math.log(0.9d)));
    public static final int DEFAULT_DURATION = 250;
    private static final float END_TENSION = 1.0f;
    static final int FLING_MODE = 1;
    private static final float INFLEXION = 0.35f;
    private static final int NB_SAMPLES = 100;
    /* renamed from: P1 */
    private static final float f4582P1 = 0.175f;
    /* renamed from: P2 */
    private static final float f4583P2 = 0.35000002f;
    private static final float[] SPLINE_POSITION = new float[101];
    private static final float[] SPLINE_TIME = new float[101];
    private static final float START_TENSION = 0.5f;
    private static float sViscousFluidNormalize;
    private static float sViscousFluidScale = 8.0f;
    private boolean isUseAnchor;
    private float mCurrBearing;
    private float mCurrTilt;
    private float mCurrVelocity;
    private int mCurrX;
    private int mCurrY;
    private float mCurrZ;
    private float mDeceleration;
    private float mDeltaBearing;
    private float mDeltaTilt;
    private float mDeltaX;
    private float mDeltaY;
    private float mDeltaZ;
    private int mDistance;
    private long mDuration;
    private float mDurationReciprocal;
    private float mFinalBearing;
    private float mFinalTilt;
    private int mFinalX;
    private int mFinalY;
    private float mFinalZ;
    private boolean mFinished;
    private float mFlingFriction;
    private boolean mFlywheel;
    private Interpolator mInterpolator;
    private int mMaxX;
    private int mMaxY;
    private int mMinX;
    private int mMinY;
    private int mMode;
    private float mPhysicalCoeff;
    private final float mPpi;
    private float mStartBearing;
    private float mStartTilt;
    private long mStartTime;
    private int mStartX;
    private int mStartY;
    private float mStartZ;
    private float mVelocity;

    static {
        float f = 0.0f;
        int i = 0;
        float f2 = 0.0f;
        while (i < 100) {
            float f3;
            float f4;
            float f5 = ((float) i) / 100.0f;
            float f6 = END_TENSION;
            float f7 = f2;
            while (true) {
                f2 = ((f6 - f7) / 2.0f) + f7;
                f3 = (3.0f * f2) * (END_TENSION - f2);
                f4 = ((((END_TENSION - f2) * f4582P1) + (f4583P2 * f2)) * f3) + ((f2 * f2) * f2);
                if (((double) Math.abs(f4 - f5)) < 1.0E-5d) {
                    break;
                } else if (f4 > f5) {
                    f6 = f2;
                } else {
                    f7 = f2;
                }
            }
            SPLINE_POSITION[i] = (f2 * (f2 * f2)) + (f3 * (((END_TENSION - f2) * START_TENSION) + f2));
            f6 = END_TENSION;
            while (true) {
                f2 = ((f6 - f) / 2.0f) + f;
                f3 = (3.0f * f2) * (END_TENSION - f2);
                f4 = ((((END_TENSION - f2) * START_TENSION) + f2) * f3) + ((f2 * f2) * f2);
                if (((double) Math.abs(f4 - f5)) < 1.0E-5d) {
                    break;
                } else if (f4 > f5) {
                    f6 = f2;
                } else {
                    f = f2;
                }
            }
            SPLINE_TIME[i] = (f2 * (f2 * f2)) + ((((END_TENSION - f2) * f4582P1) + (f4583P2 * f2)) * f3);
            i++;
            f2 = f7;
        }
        float[] fArr = SPLINE_POSITION;
        SPLINE_TIME[100] = END_TENSION;
        fArr[100] = END_TENSION;
        sViscousFluidNormalize = END_TENSION;
        sViscousFluidNormalize = END_TENSION / viscousFluid(END_TENSION);
    }

    public CameraAnimator(Context context) {
        this(context, null);
    }

    public CameraAnimator(Context context, Interpolator interpolator) {
        this(context, interpolator, context.getApplicationInfo().targetSdkVersion >= 11);
    }

    public CameraAnimator(Context context, Interpolator interpolator, boolean z) {
        this.mFlingFriction = ViewConfiguration.getScrollFriction();
        this.mFinished = true;
        this.mInterpolator = interpolator;
        this.mPpi = context.getResources().getDisplayMetrics().density * 160.0f;
        this.mDeceleration = computeDeceleration(ViewConfiguration.getScrollFriction());
        this.mFlywheel = z;
        this.mPhysicalCoeff = computeDeceleration(0.84f);
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public final void setFriction(float f) {
        this.mDeceleration = computeDeceleration(f);
        this.mFlingFriction = f;
    }

    private float computeDeceleration(float f) {
        return (386.0878f * this.mPpi) * f;
    }

    public final boolean isFinished() {
        return this.mFinished;
    }

    public final void forceFinished(boolean z) {
        this.mFinished = z;
    }

    public final long getDuration() {
        return this.mDuration;
    }

    public final int getCurrX() {
        return this.mCurrX;
    }

    public final int getCurrY() {
        return this.mCurrY;
    }

    public final float getCurrZ() {
        return this.mCurrZ;
    }

    public final float getCurrBearing() {
        return this.mCurrBearing;
    }

    public final float getCurrTilt() {
        return this.mCurrTilt;
    }

    public float getCurrVelocity() {
        if (this.mMode == 1) {
            return this.mCurrVelocity;
        }
        return this.mVelocity - ((this.mDeceleration * ((float) timePassed())) / 2000.0f);
    }

    public final int getStartX() {
        return this.mStartX;
    }

    public final int getStartY() {
        return this.mStartY;
    }

    public final float getStartZ() {
        return this.mStartZ;
    }

    public final float getStartbearing() {
        return this.mStartBearing;
    }

    public final float getStartTilt() {
        return this.mStartTilt;
    }

    public final int getFinalX() {
        return this.mFinalX;
    }

    public final int getFinalY() {
        return this.mFinalY;
    }

    public final float getFinalZ() {
        return this.mFinalZ;
    }

    public final float getFinalBearing() {
        return this.mFinalBearing;
    }

    public final float getFinalTilt() {
        return this.mFinalTilt;
    }

    public boolean computeScrollOffset() {
        if (this.mFinished) {
            return false;
        }
        int currentAnimationTimeMillis = (int) (AnimationUtils.currentAnimationTimeMillis() - this.mStartTime);
        if (((long) currentAnimationTimeMillis) < this.mDuration) {
            float f;
            switch (this.mMode) {
                case 1:
                    float f2 = ((float) currentAnimationTimeMillis) / ((float) this.mDuration);
                    int i = (int) (100.0f * f2);
                    float f3 = END_TENSION;
                    f = 0.0f;
                    if (i < 100) {
                        f3 = ((float) i) / 100.0f;
                        f = ((float) (i + 1)) / 100.0f;
                        float f4 = SPLINE_POSITION[i];
                        f = (SPLINE_POSITION[i + 1] - f4) / (f - f3);
                        f3 = ((f2 - f3) * f) + f4;
                    }
                    this.mCurrVelocity = ((f * ((float) this.mDistance)) / ((float) this.mDuration)) * 1000.0f;
                    this.mCurrX = this.mStartX + Math.round(((float) (this.mFinalX - this.mStartX)) * f3);
                    this.mCurrX = Math.min(this.mCurrX, this.mMaxX);
                    this.mCurrX = Math.max(this.mCurrX, this.mMinX);
                    this.mCurrY = this.mStartY + Math.round(f3 * ((float) (this.mFinalY - this.mStartY)));
                    this.mCurrY = Math.min(this.mCurrY, this.mMaxY);
                    this.mCurrY = Math.max(this.mCurrY, this.mMinY);
                    if (this.mCurrX == this.mFinalX && this.mCurrY == this.mFinalY) {
                        this.mFinished = true;
                        break;
                    }
                case 2:
                    f = ((float) currentAnimationTimeMillis) * this.mDurationReciprocal;
                    if (this.mInterpolator == null) {
                        f = viscousFluid(f);
                    } else {
                        f = this.mInterpolator.getInterpolation(f);
                    }
                    this.mCurrX = this.mStartX + Math.round(this.mDeltaX * f);
                    this.mCurrY = this.mStartY + Math.round(this.mDeltaY * f);
                    this.mCurrZ = this.mStartZ + (this.mDeltaZ * f);
                    this.mCurrBearing = this.mStartBearing + (this.mDeltaBearing * f);
                    this.mCurrTilt = (f * this.mDeltaTilt) + this.mStartTilt;
                    break;
            }
        }
        this.mCurrX = this.mFinalX;
        this.mCurrY = this.mFinalY;
        this.mCurrZ = this.mFinalZ;
        this.mCurrBearing = this.mFinalBearing;
        this.mCurrTilt = this.mFinalTilt;
        this.mFinished = true;
        return true;
    }

    public void startChangeCamera(int i, int i2, float f, float f2, float f3, int i3, int i4, float f4, float f5, float f6) {
        startChangeCamera(i, i2, f, f2, f3, i3, i4, f4, f5, f6, 250);
    }

    public void startChangeCamera(int i, int i2, float f, float f2, float f3, int i3, int i4, float f4, float f5, float f6, long j) {
        this.mMode = 2;
        this.mFinished = false;
        this.mDuration = j;
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mStartX = i;
        this.mStartY = i2;
        this.mStartZ = f;
        this.mStartBearing = f2;
        this.mStartTilt = f3;
        this.mFinalX = i + i3;
        this.mFinalY = i2 + i4;
        this.mFinalZ = f + f4;
        this.mFinalBearing = f2 + f5;
        this.mFinalTilt = f3 + f6;
        this.mDeltaX = (float) i3;
        this.mDeltaY = (float) i4;
        this.mDeltaZ = f4;
        this.mDeltaBearing = f5;
        this.mDeltaTilt = f6;
        this.mDurationReciprocal = END_TENSION / ((float) this.mDuration);
    }

    public void fling(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        float currVelocity;
        float f;
        float f2 = END_TENSION;
        if (this.mFlywheel && !this.mFinished) {
            currVelocity = getCurrVelocity();
            f = (float) (this.mFinalX - this.mStartX);
            float f3 = (float) (this.mFinalY - this.mStartY);
            float sqrt = (float) Math.sqrt((double) ((f * f) + (f3 * f3)));
            f = (f / sqrt) * currVelocity;
            currVelocity *= f3 / sqrt;
            if (Math.signum((float) i3) == Math.signum(f) && Math.signum((float) i4) == Math.signum(currVelocity)) {
                i3 = (int) (f + ((float) i3));
                i4 = (int) (currVelocity + ((float) i4));
            }
        }
        this.mMode = 1;
        this.mFinished = false;
        f = (float) Math.sqrt((double) ((i3 * i3) + (i4 * i4)));
        this.mVelocity = f;
        this.mDuration = (long) getSplineFlingDuration(f);
        this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
        this.mStartX = i;
        this.mStartY = i2;
        currVelocity = f == 0.0f ? END_TENSION : ((float) i3) / f;
        if (f != 0.0f) {
            f2 = ((float) i4) / f;
        }
        double splineFlingDistance = getSplineFlingDistance(f);
        this.mDistance = (int) (((double) Math.signum(f)) * splineFlingDistance);
        this.mMinX = i5;
        this.mMaxX = i6;
        this.mMinY = i7;
        this.mMaxY = i8;
        this.mFinalX = ((int) Math.round(((double) currVelocity) * splineFlingDistance)) + i;
        this.mFinalX = Math.min(this.mFinalX, this.mMaxX);
        this.mFinalX = Math.max(this.mFinalX, this.mMinX);
        this.mFinalY = ((int) Math.round(((double) f2) * splineFlingDistance)) + i2;
        this.mFinalY = Math.min(this.mFinalY, this.mMaxY);
        this.mFinalY = Math.max(this.mFinalY, this.mMinY);
    }

    private double getSplineDeceleration(float f) {
        return Math.log((double) ((INFLEXION * Math.abs(f)) / (this.mFlingFriction * this.mPhysicalCoeff)));
    }

    private int getSplineFlingDuration(float f) {
        return (int) (Math.exp(getSplineDeceleration(f) / (((double) DECELERATION_RATE) - 1.0d)) * 1000.0d);
    }

    private double getSplineFlingDistance(float f) {
        return Math.exp(getSplineDeceleration(f) * (((double) DECELERATION_RATE) / (((double) DECELERATION_RATE) - 1.0d))) * ((double) (this.mFlingFriction * this.mPhysicalCoeff));
    }

    static float viscousFluid(float f) {
        float f2 = sViscousFluidScale * f;
        if (f2 < END_TENSION) {
            f2 -= END_TENSION - ((float) Math.exp((double) (-f2)));
        } else {
            f2 = ((END_TENSION - ((float) Math.exp((double) (END_TENSION - f2)))) * (END_TENSION - 0.36787945f)) + 0.36787945f;
        }
        return f2 * sViscousFluidNormalize;
    }

    public void abortAnimation() {
        this.mCurrX = this.mFinalX;
        this.mCurrY = this.mFinalY;
        this.mCurrZ = this.mFinalZ;
        this.mCurrBearing = this.mFinalBearing;
        this.mCurrTilt = this.mFinalTilt;
        this.mFinished = true;
    }

    public void extendDuration(int i) {
        this.mDuration = (long) (timePassed() + i);
        this.mDurationReciprocal = END_TENSION / ((float) this.mDuration);
        this.mFinished = false;
    }

    public int timePassed() {
        return (int) (AnimationUtils.currentAnimationTimeMillis() - this.mStartTime);
    }

    public void setFinalX(int i) {
        this.mFinalX = i;
        this.mDeltaX = (float) (this.mFinalX - this.mStartX);
        this.mFinished = false;
    }

    public void setFinalY(int i) {
        this.mFinalY = i;
        this.mDeltaY = (float) (this.mFinalY - this.mStartY);
        this.mFinished = false;
    }

    public boolean isScrollingInDirection(float f, float f2) {
        return !this.mFinished && Math.signum(f) == Math.signum((float) (this.mFinalX - this.mStartX)) && Math.signum(f2) == Math.signum((float) (this.mFinalY - this.mStartY));
    }

    public final int getMode() {
        return this.mMode;
    }

    public void setUseAnchor(boolean z) {
        this.isUseAnchor = z;
    }

    public boolean isUseAnchor() {
        return this.isUseAnchor;
    }
}
