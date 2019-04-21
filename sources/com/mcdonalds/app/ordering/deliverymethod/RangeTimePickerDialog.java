package com.mcdonalds.app.ordering.deliverymethod;

import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.widget.TimePicker;
import com.ensighten.Ensighten;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.Calendar;

public class RangeTimePickerDialog extends TimePickerDialog {
    private Calendar calendar = Calendar.getInstance();
    private int currentHour = 0;
    private int currentMinute = 0;
    private DateFormat dateFormat;
    private int maxHour = 25;
    private int maxMinute = 25;
    private int minHour = -1;
    private int minMinute = -1;

    public RangeTimePickerDialog(Context context, int theme, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView) {
        super(context, theme, callBack, hourOfDay, minute, is24HourView);
        this.currentHour = hourOfDay;
        this.currentMinute = minute;
        this.dateFormat = DateFormat.getTimeInstance(3);
        try {
            Field mTimePickerField = getClass().getSuperclass().getDeclaredField("mTimePicker");
            mTimePickerField.setAccessible(true);
            ((TimePicker) mTimePickerField.get(this)).setOnTimeChangedListener(this);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException e) {
        }
    }

    public void setMin(int hour, int minute) {
        Ensighten.evaluateEvent(this, "setMin", new Object[]{new Integer(hour), new Integer(minute)});
        this.minHour = hour;
        this.minMinute = minute;
    }

    public void setMax(int hour, int minute) {
        Ensighten.evaluateEvent(this, "setMax", new Object[]{new Integer(hour), new Integer(minute)});
        this.maxHour = hour;
        this.maxMinute = minute;
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        Ensighten.evaluateEvent(this, "onTimeChanged", new Object[]{view, new Integer(hourOfDay), new Integer(minute)});
        boolean validTime = true;
        if (hourOfDay < this.minHour || (hourOfDay == this.minHour && minute < this.minMinute)) {
            validTime = false;
        }
        if (hourOfDay > this.maxHour || (hourOfDay == this.maxHour && minute > this.maxMinute)) {
            validTime = false;
        }
        if (validTime) {
            this.currentHour = hourOfDay;
            this.currentMinute = minute;
        }
        updateTime(this.currentHour, this.currentMinute);
        updateDialogTitle(view, this.currentHour, this.currentMinute);
    }

    private void updateDialogTitle(TimePicker timePicker, int hourOfDay, int minute) {
        Ensighten.evaluateEvent(this, "updateDialogTitle", new Object[]{timePicker, new Integer(hourOfDay), new Integer(minute)});
        this.calendar.set(11, hourOfDay);
        this.calendar.set(12, minute);
        setTitle(this.dateFormat.format(this.calendar.getTime()));
    }
}
