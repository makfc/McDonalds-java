package com.mcdonalds.app.ordering.preparepayment;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentTransaction;
import com.google.gson.Gson;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.util.HashMap;
import java.util.Map;

public class PaymentSelectionActivity extends URLActionBarActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(C2658R.string.title_activity_payment_selection));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerResource(), passIntentExtrasAsArgument(new PaymentSelectionFragment()));
        transaction.commit();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PaymentSelectionFragment fragment = (PaymentSelectionFragment) getDisplayedFragment();
        if (resultCode == -1) {
            boolean isOneTimePayment = false;
            String jsonCardInfo = null;
            PaymentCard card = null;
            if (requestCode == 42803) {
                if (data != null) {
                    jsonCardInfo = data.getStringExtra("EXTRA_ONE_TIME_PAYMENT");
                    isOneTimePayment = jsonCardInfo != null;
                }
            } else if (requestCode == 42807) {
                if (data != null) {
                    isOneTimePayment = data.getBooleanExtra("one_time_payment", false);
                    jsonCardInfo = data.getStringExtra("json_card_info");
                }
            } else if (requestCode == 42804) {
                card = (PaymentCard) DataPasser.getInstance().getData("EXTRA_ONE_TIME_PAYMENT_CARD_DATA");
                isOneTimePayment = card != null;
            }
            if (!isOneTimePayment || (jsonCardInfo == null && card == null)) {
                fragment.paymentsUpdated();
            } else if (card != null) {
                fragment.proceedWithOneTimePayment(card);
            } else {
                Gson gson = new Gson();
                Class cls = HashMap.class;
                Map<String, Object> resultMap = !(gson instanceof Gson) ? gson.fromJson(jsonCardInfo, cls) : GsonInstrumentation.fromJson(gson, jsonCardInfo, cls);
                Double resultCodeDouble = (Double) resultMap.get("ResultCode");
                if (resultCodeDouble.intValue() == 1) {
                    Map<String, Object> cardMap = (Map) resultMap.get("Data");
                    if (cardMap != null) {
                        PaymentCard oneTimeCard = new PaymentCard();
                        oneTimeCard.setAlias((String) cardMap.get("CardAlias"));
                        oneTimeCard.setExpiration((String) cardMap.get("CardExpiration"));
                        oneTimeCard.setHolderName((String) cardMap.get("CardHolderName"));
                        oneTimeCard.setIdentifier(cardMap.get("CustomerPaymentMethodId") != null ? Integer.valueOf(((Double) cardMap.get("CustomerPaymentMethodId")).intValue()) : null);
                        oneTimeCard.setIsPreferred((Boolean) cardMap.get("IsPreferred"));
                        oneTimeCard.setIsValid(Boolean.valueOf(!((Boolean) cardMap.get("IsExpired")).booleanValue()));
                        oneTimeCard.setNickName((String) cardMap.get("NickName"));
                        oneTimeCard.setPaymentMethodId(cardMap.get("PaymentMethodId") != null ? Integer.valueOf(((Double) cardMap.get("PaymentMethodId")).intValue()) : null);
                        fragment.proceedWithOneTimePayment(oneTimeCard);
                        return;
                    }
                    return;
                }
                AsyncException.report(MWException.fromErrorCode(resultCodeDouble.intValue()));
            }
        }
    }
}
