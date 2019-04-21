package com.google.android.gms.plus;

import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Account {
    @Deprecated
    void clearDefaultAccount(GoogleApiClient googleApiClient);

    @RequiresPermission
    String getAccountName(GoogleApiClient googleApiClient);
}
