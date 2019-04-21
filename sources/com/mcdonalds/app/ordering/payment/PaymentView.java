package com.mcdonalds.app.ordering.payment;

import android.app.Activity;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import java.util.LinkedHashSet;
import java.util.List;

public interface PaymentView {
    void backToBasket();

    void continueToBagCharges();

    void continueToOrderSummary();

    void continueToTableServices();

    void finish();

    Activity getActivityForAlipay();

    void openThirdPartyPaymentUrl(String str);

    void requestCVV(int i);

    void requestPassword();

    void requestPaymentInfo(String str, String str2);

    void scanQRCode();

    void showActivityIndicator(int i);

    void showCashNotAcceptedAtCurbsideError();

    void showFatalError(String str);

    void showFatalError(String str, String str2);

    void showInvalidQRCodeError();

    void showInvalidRestaurantError();

    void showLargeOrderAlert();

    void showOrderErrors(int i, int i2, List<String> list, List<String> list2, boolean z);

    void showOrderNotReadyError();

    void showOrderNotReadyToAcceptError();

    void showOrderUnavailableAtPODError();

    void showPaymentChooser(String str);

    void showPaymentError(String str, String str2);

    void showPaymentSelection(LinkedHashSet<PaymentMode> linkedHashSet);

    void showRestaurantMismatchError();

    void stopActivityIndicator();
}
