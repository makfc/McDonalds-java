package com.mcdonalds.app.p043ui.dateTime;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import java.lang.reflect.Field;

/* renamed from: com.mcdonalds.app.ui.dateTime.CustomDatePicker */
public class CustomDatePicker extends DatePicker {
    public CustomDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {
            Class<?> idClass = Class.forName("com.android.internal.R$id");
            Field monthField = idClass.getField("month");
            Field dayField = idClass.getField("day");
            Field yearField = idClass.getField("year");
            ((NumberPicker) findViewById(dayField.getInt(null))).setVisibility(8);
        } catch (ClassNotFoundException e) {
            Log.e("CustomDatePicker", "ClassNotFoundException in CustomDatePicker", e);
        } catch (NoSuchFieldException e2) {
            Log.e("CustomDatePicker", "NoSuchFieldException in CustomDatePicker", e2);
        } catch (IllegalAccessException e3) {
            Log.e("CustomDatePicker", "IllegalAccessException in CustomDatePicker", e3);
        } catch (IllegalArgumentException e4) {
            Log.e("CustomDatePicker", "IllegalArgumentException in CustomDatePicker", e4);
        }
    }
}
