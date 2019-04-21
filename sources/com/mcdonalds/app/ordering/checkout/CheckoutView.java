package com.mcdonalds.app.ordering.checkout;

import com.mcdonalds.sdk.modules.models.PaymentMethod;
import java.util.LinkedHashSet;
import java.util.List;

public interface CheckoutView {
    void navigateToAccountCardsPage();

    void navigateToDashboard();

    void navigateToSignIn();

    void setZeroPricedOrder(boolean z);

    void showFatalError(String str);

    void showLargeOrderWarning();

    void showMaxCardsAlert();

    void showNoPaymentSelectedError();

    void showOrderErrors(int i, int i2, List<String> list, boolean z);

    void showOutOfStockAlert(int i);

    void showPaymentSelection(LinkedHashSet<PaymentMethod> linkedHashSet);

    void showPickupMethodSelector();

    void showZeroOrNegativePriceError();

    void startInterinCheckinFlow();

    void startOneTimePaymentCheckinFlow();

    void startRegularCheckinFlow();
}
