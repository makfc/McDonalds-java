package com.mcdonalds.app.p043ui.dateTime;

import com.ensighten.Ensighten;
import java.util.Date;

/* renamed from: com.mcdonalds.app.ui.dateTime.SlideDateTimeListener */
public abstract class SlideDateTimeListener {
    public abstract void onDateTimeSet(Date date);

    public void onDateTimeCancel() {
        Ensighten.evaluateEvent(this, "onDateTimeCancel", null);
    }
}
