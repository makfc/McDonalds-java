package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.plus.internal.model.people.PersonEntity;
import com.google.android.gms.plus.internal.model.people.zzk;

public final class PersonBuffer extends AbstractDataBuffer<Person> {
    private final zzd<PersonEntity> zzbmD;

    public PersonBuffer(DataHolder dataHolder) {
        super(dataHolder);
        if (dataHolder.zzsO() == null || !dataHolder.zzsO().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)) {
            this.zzbmD = null;
        } else {
            this.zzbmD = new zzd(dataHolder, PersonEntity.CREATOR);
        }
    }

    public Person get(int i) {
        return this.zzbmD != null ? (Person) ((SafeParcelable) this.zzbmD.get(i)) : new zzk(this.zzamz, i);
    }
}
