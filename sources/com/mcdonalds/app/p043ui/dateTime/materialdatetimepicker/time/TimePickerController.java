package com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time;

import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.TimePickerDialog.Version;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.Timepoint.TYPE;

/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimePickerController */
interface TimePickerController {
    int getAccentColor();

    Version getVersion();

    boolean is24HourMode();

    boolean isAmDisabled();

    boolean isOutOfRange(Timepoint timepoint, int i);

    boolean isPmDisabled();

    boolean isThemeDark();

    Timepoint roundToNearest(Timepoint timepoint, TYPE type);

    void tryVibrate();
}
