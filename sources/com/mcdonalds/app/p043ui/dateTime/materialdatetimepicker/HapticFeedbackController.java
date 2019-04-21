package com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker;

import android.content.Context;
import android.database.ContentObserver;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings.System;
import com.ensighten.Ensighten;

/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.HapticFeedbackController */
public class HapticFeedbackController {
    private final ContentObserver mContentObserver = new ContentObserver(null) {
        public void onChange(boolean selfChange) {
            Ensighten.evaluateEvent(this, "onChange", new Object[]{new Boolean(selfChange)});
            HapticFeedbackController.access$002(HapticFeedbackController.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.HapticFeedbackController", "access$200", new Object[]{Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.HapticFeedbackController", "access$100", new Object[]{HapticFeedbackController.this})}));
        }
    };
    private final Context mContext;
    private boolean mIsGloballyEnabled;
    private long mLastVibrate;
    private Vibrator mVibrator;

    static /* synthetic */ boolean access$002(HapticFeedbackController x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.HapticFeedbackController", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.mIsGloballyEnabled = x1;
        return x1;
    }

    private static boolean checkGlobalSetting(Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.HapticFeedbackController", "checkGlobalSetting", new Object[]{context});
        return System.getInt(context.getContentResolver(), "haptic_feedback_enabled", 0) == 1;
    }

    public HapticFeedbackController(Context context) {
        this.mContext = context;
    }

    public void start() {
        Ensighten.evaluateEvent(this, "start", null);
        if (hasVibratePermission(this.mContext)) {
            this.mVibrator = (Vibrator) this.mContext.getSystemService("vibrator");
        }
        this.mIsGloballyEnabled = HapticFeedbackController.checkGlobalSetting(this.mContext);
        this.mContext.getContentResolver().registerContentObserver(System.getUriFor("haptic_feedback_enabled"), false, this.mContentObserver);
    }

    private boolean hasVibratePermission(Context context) {
        Ensighten.evaluateEvent(this, "hasVibratePermission", new Object[]{context});
        if (context.getPackageManager().checkPermission("android.permission.VIBRATE", context.getPackageName()) == 0) {
            return true;
        }
        return false;
    }

    public void stop() {
        Ensighten.evaluateEvent(this, "stop", null);
        this.mVibrator = null;
        this.mContext.getContentResolver().unregisterContentObserver(this.mContentObserver);
    }

    public void tryVibrate() {
        Ensighten.evaluateEvent(this, "tryVibrate", null);
        if (this.mVibrator != null && this.mIsGloballyEnabled) {
            long now = SystemClock.uptimeMillis();
            if (now - this.mLastVibrate >= 125) {
                this.mVibrator.vibrate(50);
                this.mLastVibrate = now;
            }
        }
    }
}
