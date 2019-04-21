package com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mcdonalds.app.p043ui.dateTime.materialdatetimepicker.time.Timepoint.TYPE;

/* renamed from: com.mcdonalds.app.ui.dateTime.materialdatetimepicker.time.TimepointLimiter */
public interface TimepointLimiter extends Parcelable {
    boolean isAmDisabled();

    boolean isOutOfRange(@Nullable Timepoint timepoint, int i, @NonNull TYPE type);

    boolean isPmDisabled();

    @NonNull
    Timepoint roundToNearest(@NonNull Timepoint timepoint, @Nullable TYPE type, @NonNull TYPE type2);
}
