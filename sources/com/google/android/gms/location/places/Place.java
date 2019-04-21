package com.google.android.gms.location.places;

import com.google.android.gms.common.data.Freezable;

public interface Place extends Freezable<Place> {
    CharSequence getName();
}
