package com.crashlytics.android.answers;

import java.math.BigDecimal;

public class StartCheckoutEvent extends PredefinedEvent<StartCheckoutEvent> {
    static final BigDecimal MICRO_CONSTANT = BigDecimal.valueOf(1000000);

    /* Access modifiers changed, original: 0000 */
    public String getPredefinedType() {
        return "startCheckout";
    }
}
