package com.mcdonalds.app.account;

import android.text.TextUtils;
import com.amap.api.services.district.DistrictSearchQuery;
import com.ensighten.Ensighten;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.model.FormField;
import com.mcdonalds.app.widget.ValidationListener;
import com.mcdonalds.app.widget.ValidationListener.Callback;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.CreditCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.paymentRegistration.PaymentRegistrationModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.agentdata.HexAttributes;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EditCardPresenter implements Callback {
    private static final String[] ADDRESS_FIELDS = new String[]{"streetAddress", DistrictSearchQuery.KEYWORDS_CITY, HexAttributes.HEX_ATTR_THREAD_STATE, DistrictSearchQuery.KEYWORDS_COUNTRY, "zipCode"};
    private Configuration mConfiguration;
    private HashMap<String, FormField> mExtraFields;
    private boolean mOneTimePayment;
    private PaymentMethod mPaymentMethod;
    private AsyncListener<String> mSaveCardListener = new C29732();
    private AsyncListener<CustomerProfile> mUpdatePaymentListener = new C29743();
    private List<ValidationListener> mValidations;
    private EditCardView mView;

    /* renamed from: com.mcdonalds.app.account.EditCardPresenter$2 */
    class C29732 implements AsyncListener<String> {
        C29732() {
        }

        public void onResponse(String response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (response != null) {
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$100", new Object[]{EditCardPresenter.this})) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$300", new Object[]{EditCardPresenter.this}).notifyOneTimePaymentSuccess(response);
                } else {
                    ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).updatePayment(response, false, Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$200", new Object[]{EditCardPresenter.this}));
                }
            } else if (exception != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$300", new Object[]{EditCardPresenter.this}).stopSavingCardIndicator();
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$300", new Object[]{EditCardPresenter.this}).notifyCardSavingError(exception);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$300", new Object[]{EditCardPresenter.this}).stopSavingCardIndicator();
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$300", new Object[]{EditCardPresenter.this}).notifyCardSavingError((int) C2658R.string.ecp_error_1001);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.EditCardPresenter$3 */
    class C29743 implements AsyncListener<CustomerProfile> {
        C29743() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$300", new Object[]{EditCardPresenter.this}).stopSavingCardIndicator();
            if (exception == null && response != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$300", new Object[]{EditCardPresenter.this}).notifyCardSaved();
            } else if (exception != null) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$300", new Object[]{EditCardPresenter.this}).notifyCardSavingError(exception);
            } else {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$300", new Object[]{EditCardPresenter.this}).notifyCardSavingError((int) C2658R.string.ecp_error_1001);
            }
        }
    }

    static /* synthetic */ void access$000(EditCardPresenter x0, CustomerProfile x1, CreditCard x2, URL x3, AsyncException x4) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.EditCardPresenter", "access$000", new Object[]{x0, x1, x2, x3, x4});
        x0.handlePaymentUrlResponse(x1, x2, x3, x4);
    }

    public EditCardPresenter(EditCardView view, PaymentMethod paymentMethod, boolean oneTimePayment) {
        this.mView = view;
        this.mPaymentMethod = paymentMethod;
        this.mOneTimePayment = oneTimePayment;
        this.mValidations = new ArrayList();
        this.mConfiguration = Configuration.getSharedInstance();
        this.mExtraFields = new HashMap();
        ArrayList<LinkedTreeMap> config = (ArrayList) this.mConfiguration.getValueForKey("interface.paymentRegistration.extraFields");
        if (config != null) {
            Gson gson = new Gson();
            Iterator it = config.iterator();
            while (it.hasNext()) {
                String linkedTreeMap = ((LinkedTreeMap) it.next()).toString();
                Class cls = FormField.class;
                FormField field = !(gson instanceof Gson) ? gson.fromJson(linkedTreeMap, cls) : GsonInstrumentation.fromJson(gson, linkedTreeMap, cls);
                this.mExtraFields.put(field.getName(), field);
            }
        }
    }

    public boolean isFieldEnabled(String fieldName) {
        Ensighten.evaluateEvent(this, "isFieldEnabled", new Object[]{fieldName});
        return this.mExtraFields.keySet().contains(fieldName);
    }

    public boolean hasAddressFields() {
        Ensighten.evaluateEvent(this, "hasAddressFields", null);
        for (String field : ADDRESS_FIELDS) {
            if (isFieldEnabled(field)) {
                return true;
            }
        }
        return false;
    }

    public String getCardType(String cardNumberString) {
        Ensighten.evaluateEvent(this, "getCardType", new Object[]{cardNumberString});
        if (TextUtils.isEmpty(cardNumberString)) {
            return null;
        }
        cardNumberString = cardNumberString.replace(" ", "");
        if (cardNumberString.matches("^4.*")) {
            return CreditCard.TYPE_VISA;
        }
        if (cardNumberString.matches("^3[47].*")) {
            return CreditCard.TYPE_AMERICAN_EXPRESS;
        }
        if (cardNumberString.matches("^(2131|1800|35).*")) {
            return CreditCard.TYPE_JCB;
        }
        if (cardNumberString.matches("^(5[1-5]|222[1-9]|22[3-9]|2[3-6]|27[01]|2720).*")) {
            return CreditCard.TYPE_MASTER_CARD;
        }
        return null;
    }

    /* JADX WARNING: Missing block: B:8:0x002a, code skipped:
            if (r0.equals(com.mcdonalds.sdk.modules.models.CreditCard.TYPE_VISA) != false) goto L_0x001c;
     */
    public int getCardTypeDrawableResourceId(java.lang.String r7) {
        /*
        r6 = this;
        r3 = 1;
        r2 = 0;
        r1 = -1;
        r4 = "getCardTypeDrawableResourceId";
        r5 = new java.lang.Object[r3];
        r5[r2] = r7;
        com.ensighten.Ensighten.evaluateEvent(r6, r4, r5);
        r0 = r6.getCardType(r7);
        if (r0 != 0) goto L_0x0014;
    L_0x0013:
        return r1;
    L_0x0014:
        r4 = r0.hashCode();
        switch(r4) {
            case 47665: goto L_0x0024;
            case 47666: goto L_0x0037;
            case 47667: goto L_0x002d;
            case 47668: goto L_0x001b;
            case 47669: goto L_0x001b;
            case 47670: goto L_0x001b;
            case 47671: goto L_0x0041;
            default: goto L_0x001b;
        };
    L_0x001b:
        r2 = r1;
    L_0x001c:
        switch(r2) {
            case 0: goto L_0x0020;
            case 1: goto L_0x004b;
            case 2: goto L_0x004f;
            case 3: goto L_0x0053;
            default: goto L_0x001f;
        };
    L_0x001f:
        goto L_0x0013;
    L_0x0020:
        r1 = 2130838108; // 0x7f02025c float:1.728119E38 double:1.052773906E-314;
        goto L_0x0013;
    L_0x0024:
        r3 = "001";
        r3 = r0.equals(r3);
        if (r3 == 0) goto L_0x001b;
    L_0x002c:
        goto L_0x001c;
    L_0x002d:
        r2 = "003";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x001b;
    L_0x0035:
        r2 = r3;
        goto L_0x001c;
    L_0x0037:
        r2 = "002";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x001b;
    L_0x003f:
        r2 = 2;
        goto L_0x001c;
    L_0x0041:
        r2 = "007";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x001b;
    L_0x0049:
        r2 = 3;
        goto L_0x001c;
    L_0x004b:
        r1 = 2130837588; // 0x7f020054 float:1.7280134E38 double:1.052773649E-314;
        goto L_0x0013;
    L_0x004f:
        r1 = 2130837967; // 0x7f0201cf float:1.7280903E38 double:1.0527738363E-314;
        goto L_0x0013;
    L_0x0053:
        r1 = 2130837957; // 0x7f0201c5 float:1.7280883E38 double:1.0527738314E-314;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.app.account.EditCardPresenter.getCardTypeDrawableResourceId(java.lang.String):int");
    }

    /* JADX WARNING: Missing block: B:6:0x0021, code skipped:
            if (r5.equals(com.mcdonalds.sdk.modules.models.CreditCard.TYPE_VISA) != false) goto L_0x0015;
     */
    public java.lang.String getCardNameForType(java.lang.String r5) {
        /*
        r4 = this;
        r2 = 1;
        r0 = 0;
        r1 = "getCardNameForType";
        r3 = new java.lang.Object[r2];
        r3[r0] = r5;
        com.ensighten.Ensighten.evaluateEvent(r4, r1, r3);
        r1 = -1;
        r3 = r5.hashCode();
        switch(r3) {
            case 47665: goto L_0x001b;
            case 47666: goto L_0x002e;
            case 47667: goto L_0x0024;
            case 47668: goto L_0x0014;
            case 47669: goto L_0x0014;
            case 47670: goto L_0x0014;
            case 47671: goto L_0x0038;
            default: goto L_0x0014;
        };
    L_0x0014:
        r0 = r1;
    L_0x0015:
        switch(r0) {
            case 0: goto L_0x0042;
            case 1: goto L_0x0045;
            case 2: goto L_0x0048;
            case 3: goto L_0x004b;
            default: goto L_0x0018;
        };
    L_0x0018:
        r0 = "";
    L_0x001a:
        return r0;
    L_0x001b:
        r2 = "001";
        r2 = r5.equals(r2);
        if (r2 == 0) goto L_0x0014;
    L_0x0023:
        goto L_0x0015;
    L_0x0024:
        r0 = "003";
        r0 = r5.equals(r0);
        if (r0 == 0) goto L_0x0014;
    L_0x002c:
        r0 = r2;
        goto L_0x0015;
    L_0x002e:
        r0 = "002";
        r0 = r5.equals(r0);
        if (r0 == 0) goto L_0x0014;
    L_0x0036:
        r0 = 2;
        goto L_0x0015;
    L_0x0038:
        r0 = "007";
        r0 = r5.equals(r0);
        if (r0 == 0) goto L_0x0014;
    L_0x0040:
        r0 = 3;
        goto L_0x0015;
    L_0x0042:
        r0 = "visa";
        goto L_0x001a;
    L_0x0045:
        r0 = "american-express";
        goto L_0x001a;
    L_0x0048:
        r0 = "mastercard";
        goto L_0x001a;
    L_0x004b:
        r0 = "jcb";
        goto L_0x001a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mcdonalds.app.account.EditCardPresenter.getCardNameForType(java.lang.String):java.lang.String");
    }

    public int[] getAcceptedCardsDrawableResourceIds() {
        Ensighten.evaluateEvent(this, "getAcceptedCardsDrawableResourceIds", null);
        List<String> acceptedCardNames = (List) this.mConfiguration.getValueForKey("interface.paymentRegistration.acceptedCards");
        int[] acceptedCardsDrawableResourceIds = new int[acceptedCardNames.size()];
        for (int i = 0; i < acceptedCardNames.size(); i++) {
            String cardName = (String) acceptedCardNames.get(i);
            Object obj = -1;
            switch (cardName.hashCode()) {
                case -2038717326:
                    if (cardName.equals("mastercard")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 105033:
                    if (cardName.equals("jcb")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 3619905:
                    if (cardName.equals("visa")) {
                        obj = null;
                        break;
                    }
                    break;
                case 61060803:
                    if (cardName.equals("american-express")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    acceptedCardsDrawableResourceIds[i] = C2358R.C2359drawable.visa;
                    break;
                case 1:
                    acceptedCardsDrawableResourceIds[i] = C2358R.C2359drawable.mastercard;
                    break;
                case 2:
                    acceptedCardsDrawableResourceIds[i] = C2358R.C2359drawable.amex;
                    break;
                case 3:
                    acceptedCardsDrawableResourceIds[i] = C2358R.C2359drawable.jcb;
                    break;
                default:
                    break;
            }
        }
        return acceptedCardsDrawableResourceIds;
    }

    public void addValidation(ValidationListener validationListener) {
        Ensighten.evaluateEvent(this, "addValidation", new Object[]{validationListener});
        this.mValidations.add(validationListener);
        validationListener.setValidationCallback(this);
        checkValidations();
    }

    public String getCardHolderNameRegex() {
        Ensighten.evaluateEvent(this, "getCardHolderNameRegex", null);
        return this.mConfiguration.getStringForKey("textValidation.cardholderNameRegex");
    }

    public String getCardNickNameRegex() {
        Ensighten.evaluateEvent(this, "getCardNickNameRegex", null);
        return this.mConfiguration.getStringForKey("textValidation.cardNickNameRegex");
    }

    public void saveCard(String cardholderName, String cardNumber, String expirationMonth, String expirationYear, String cvv, String nickName, String streetAddress, String city, String state, String country, String zipCode) {
        Ensighten.evaluateEvent(this, "saveCard", new Object[]{cardholderName, cardNumber, expirationMonth, expirationYear, cvv, nickName, streetAddress, city, state, country, zipCode});
        final CreditCard creditCard = new CreditCard();
        String[] splittedName = cardholderName.split(" ", 2);
        if (splittedName.length > 0) {
            creditCard.setForename(splittedName[0]);
            if (splittedName.length > 1) {
                creditCard.setSurname(splittedName[1]);
            }
        }
        creditCard.setCardNumber(cardNumber.replace(" ", ""));
        creditCard.setCardExpiryDate(String.format("%s-20%s", new Object[]{expirationMonth, expirationYear}));
        creditCard.setCardSecurityCode(cvv);
        String cardType = getCardType(cardNumber);
        creditCard.setCardType(cardType);
        if (TextUtils.isEmpty(nickName)) {
            String cardName = getCardNameForType(cardType).toUpperCase();
            int lastFourDigitsIndex = cardNumber.length() - 4;
            if (lastFourDigitsIndex > 0) {
                String lastFourDigits = cardNumber.substring(lastFourDigitsIndex);
                creditCard.setNickname(String.format("%s-%s", new Object[]{cardName, lastFourDigits}));
            } else {
                creditCard.setNickname(cardName);
            }
        } else {
            creditCard.setNickname(nickName);
        }
        creditCard.setAddressStreet(streetAddress);
        creditCard.setAddressCity(city);
        creditCard.setAddressState(state);
        creditCard.setAddressCountry(country);
        creditCard.setAddressPostalCode(zipCode);
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (customerModule != null) {
            final CustomerProfile profile = customerModule.getCurrentProfile();
            if (profile != null) {
                this.mView.startSavingCardIndicator();
                customerModule.getPaymentTypeRegistrationURL(this.mPaymentMethod.getID().intValue(), Boolean.valueOf(this.mOneTimePayment), new AsyncListener<String>() {
                    public void onResponse(String urlStr, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{urlStr, token, exception});
                        URL url = null;
                        if (urlStr != null) {
                            try {
                                url = new URL(urlStr);
                            } catch (MalformedURLException e) {
                                exception = new AsyncException("Invalid Payment Provider URL");
                            }
                        }
                        EditCardPresenter.access$000(EditCardPresenter.this, profile, creditCard, url, exception);
                    }
                });
            }
        }
    }

    private void handlePaymentUrlResponse(CustomerProfile customerProfile, CreditCard creditCard, URL url, AsyncException exception) {
        Ensighten.evaluateEvent(this, "handlePaymentUrlResponse", new Object[]{customerProfile, creditCard, url, exception});
        PaymentRegistrationModule module = (PaymentRegistrationModule) ModuleManager.getModule(PaymentRegistrationModule.NAME);
        if (module == null || url == null || exception != null) {
            this.mSaveCardListener.onResponse(null, null, exception);
            return;
        }
        module.saveCard(customerProfile, this.mPaymentMethod, url.toString(), this.mOneTimePayment, creditCard, this.mSaveCardListener);
    }

    public void onFieldValidationStateChanged(boolean isValidated) {
        Ensighten.evaluateEvent(this, "onFieldValidationStateChanged", new Object[]{new Boolean(isValidated)});
        checkValidations();
    }

    private void checkValidations() {
        Ensighten.evaluateEvent(this, "checkValidations", null);
        boolean validated = true;
        for (ValidationListener validationListener : this.mValidations) {
            if (!validationListener.validate()) {
                validated = false;
                break;
            }
        }
        if (validated) {
            this.mView.enableSaveCard();
        } else {
            this.mView.disableSaveCard();
        }
    }
}
