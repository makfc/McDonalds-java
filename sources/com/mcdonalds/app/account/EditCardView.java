package com.mcdonalds.app.account;

import com.mcdonalds.sdk.AsyncException;

public interface EditCardView {
    void disableSaveCard();

    void enableSaveCard();

    void notifyCardSaved();

    void notifyCardSavingError(int i);

    void notifyCardSavingError(AsyncException asyncException);

    void notifyOneTimePaymentSuccess(String str);

    void startSavingCardIndicator();

    void stopSavingCardIndicator();
}
