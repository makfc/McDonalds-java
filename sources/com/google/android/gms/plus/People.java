package com.google.android.gms.plus;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.plus.model.people.Person;

public interface People {

    public interface LoadPeopleResult extends Releasable, Result {
    }

    public interface OrderBy {
    }

    @Deprecated
    Person getCurrentPerson(GoogleApiClient googleApiClient);
}
