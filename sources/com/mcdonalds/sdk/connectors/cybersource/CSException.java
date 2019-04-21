package com.mcdonalds.sdk.connectors.cybersource;

import android.content.Context;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.McDonalds;

public class CSException extends AsyncException {
    int mErrorCode;

    public CSException(int errorCode) {
        this.mErrorCode = errorCode;
    }

    public String getLocalizedMessage() {
        Context context = McDonalds.getContext();
        if (context == null) {
            return "";
        }
        int resourceId = context.getResources().getIdentifier("cybersource_error_" + Math.abs(this.mErrorCode), "string", context.getPackageName());
        if (resourceId > 0) {
            return context.getString(resourceId);
        }
        return context.getString(C3883R.string.error_generic);
    }
}
