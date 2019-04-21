package com.google.android.gms.plus.internal.model.people;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.Person.Name;

public final class zzk extends zzc implements Person {
    public zzk(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public String getId() {
        return getString("personId");
    }

    public Name getName() {
        return null;
    }
}
