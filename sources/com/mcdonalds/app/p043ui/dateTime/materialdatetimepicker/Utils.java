package com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import com.ensighten.Ensighten;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.utils.DateUtils;
import java.util.Calendar;

/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.Utils */
public class Utils {
    public static boolean isJellybeanOrLater() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.Utils", "isJellybeanOrLater", null);
        return VERSION.SDK_INT >= 16;
    }

    @SuppressLint({"NewApi"})
    public static void tryAccessibilityAnnounce(View view, CharSequence text) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.Utils", "tryAccessibilityAnnounce", new Object[]{view, text});
        if (Utils.isJellybeanOrLater() && view != null && text != null) {
            view.announceForAccessibility(text);
        }
    }

    public static ObjectAnimator getPulseAnimator(View labelToAnimate, float decreaseRatio, float increaseRatio) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.Utils", "getPulseAnimator", new Object[]{labelToAnimate, new Float(decreaseRatio), new Float(increaseRatio)});
        Keyframe k0 = Keyframe.ofFloat(0.0f, 1.0f);
        Keyframe k1 = Keyframe.ofFloat(0.275f, decreaseRatio);
        Keyframe k2 = Keyframe.ofFloat(0.69f, increaseRatio);
        Keyframe k3 = Keyframe.ofFloat(1.0f, 1.0f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofKeyframe("scaleX", new Keyframe[]{k0, k1, k2, k3});
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofKeyframe("scaleY", new Keyframe[]{k0, k1, k2, k3});
        ObjectAnimator pulseAnimator = ObjectAnimator.ofPropertyValuesHolder(labelToAnimate, new PropertyValuesHolder[]{scaleX, scaleY});
        pulseAnimator.setDuration(544);
        return pulseAnimator;
    }

    public static int darkenColor(int color) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.Utils", "darkenColor", new Object[]{new Integer(color)});
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] = hsv[2] * 0.8f;
        return Color.HSVToColor(hsv);
    }

    public static int getAccentColorFromThemeIfAvailable(Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.Utils", "getAccentColorFromThemeIfAvailable", new Object[]{context});
        TypedValue typedValue = new TypedValue();
        if (VERSION.SDK_INT >= 21) {
            context.getTheme().resolveAttribute(16843829, typedValue, true);
            return typedValue.data;
        }
        int colorAccentResId = context.getResources().getIdentifier("colorAccent", "attr", context.getPackageName());
        if (colorAccentResId == 0 || !context.getTheme().resolveAttribute(colorAccentResId, typedValue, true)) {
            return ContextCompat.getColor(context, C2658R.color.mdtp_accent_color);
        }
        return typedValue.data;
    }

    public static String getCloseStatus(Store mSelectedStore, Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.Utils", "getCloseStatus", new Object[]{mSelectedStore, context});
        String[] operatingHours = (String[]) mSelectedStore.getStoreOperatingHours().get(Calendar.getInstance().get(7) - 1);
        String fromString = operatingHours[0];
        String toString = operatingHours[1];
        if (TextUtils.isEmpty(fromString) || TextUtils.isEmpty(toString) || DateUtils.areTimesEqualOrWithinAMinute(fromString, toString)) {
            return null;
        }
        Calendar fromCalendar = Utils.calendarFromFormat(fromString, context);
        Calendar toCalendar = Utils.calendarFromFormat(toString, context);
        if (toString.toLowerCase().contains("a") || toString.contains("上")) {
            toCalendar.add(12, 1);
            toCalendar.add(5, 1);
        }
        Calendar currentCalendar = Calendar.getInstance();
        if (currentCalendar.getTimeInMillis() < fromCalendar.getTimeInMillis() || currentCalendar.getTimeInMillis() > toCalendar.getTimeInMillis()) {
            return context.getString(C2658R.string.closed_label);
        }
        return null;
    }

    private static Calendar calendarFromFormat(String time, Context context) {
        int ampm = 0;
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.dateTime.materialdatetimepicker.Utils", "calendarFromFormat", new Object[]{time, context});
        Calendar calendar = Calendar.getInstance();
        String[] timeSplit = time.split(" ");
        String[] split = timeSplit[0].split(":");
        try {
            int hour = Integer.parseInt(split[0]);
            int minute = Integer.parseInt(split[1]);
            if (!timeSplit[1].toUpperCase().contains(context.getString(C2658R.string.f6080am))) {
                ampm = 1;
            }
            calendar.set(9, ampm);
            calendar.set(10, hour);
            calendar.set(12, minute);
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        return calendar;
    }
}
