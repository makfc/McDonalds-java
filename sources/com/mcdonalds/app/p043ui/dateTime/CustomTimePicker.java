package com.mcdonalds.app.p043ui.dateTime;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import java.lang.reflect.Field;

/* renamed from: com.mcdonalds.app.ui.dateTime.CustomTimePicker */
public class CustomTimePicker extends TimePicker {
    public CustomTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {
            Class<?> idClass = Class.forName("com.android.internal.R$id");
            Field hourField = idClass.getField("hour");
            Field minuteField = idClass.getField("minute");
            NumberPicker hourNumberPicker = (NumberPicker) findViewById(hourField.getInt(null));
            NumberPicker minuteNumberPicker = (NumberPicker) findViewById(minuteField.getInt(null));
            NumberPicker amPmNumberPicker = (NumberPicker) findViewById(idClass.getField("amPm").getInt(null));
            Class.forName("android.widget.NumberPicker").getDeclaredField("mSelectionDivider").setAccessible(true);
        } catch (ClassNotFoundException e) {
            Log.e("CustomTimePicker", "ClassNotFoundException in CustomTimePicker", e);
        } catch (NoSuchFieldException e2) {
            Log.e("CustomTimePicker", "NoSuchFieldException in CustomTimePicker", e2);
        } catch (IllegalAccessException e3) {
            Log.e("CustomTimePicker", "IllegalAccessException in CustomTimePicker", e3);
        } catch (IllegalArgumentException e4) {
            Log.e("CustomTimePicker", "IllegalArgumentException in CustomTimePicker", e4);
        }
    }
}
