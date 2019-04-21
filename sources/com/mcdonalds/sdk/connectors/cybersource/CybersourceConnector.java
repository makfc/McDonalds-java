package com.mcdonalds.sdk.connectors.cybersource;

import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import com.facebook.stetho.common.Utf8Charset;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.BaseConnector;
import com.mcdonalds.sdk.connectors.PaymentRegistrationConnector;
import com.mcdonalds.sdk.connectors.cybersource.request.CSCreateTokenRequest;
import com.mcdonalds.sdk.connectors.cybersource.request.CSRegisterReturnRequest;
import com.mcdonalds.sdk.connectors.middleware.model.DCSPreference;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.CreditCard;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs;
import com.mcdonalds.sdk.services.analytics.tagmanager.Parameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.RequestManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CybersourceConnector extends BaseConnector implements PaymentRegistrationConnector {
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String KEY_ACCESS_KEY = "connectors.Cybersource.accessKey";
    private static final String KEY_BILLING_CITY = "connectors.Cybersource.data.city";
    private static final String KEY_BILLING_COUNTRY = "connectors.Cybersource.data.country";
    private static final String KEY_BILLING_EMAIL = "connectors.Cybersource.data.email";
    private static final String KEY_BILLING_FIRST_NAME = "connectors.Cybersource.data.firstName";
    private static final String KEY_BILLING_LAST_NAME = "connectors.Cybersource.data.lastName";
    private static final String KEY_BILLING_POSTAL_CODE = "connectors.Cybersource.data.postalCode";
    private static final String KEY_BILLING_STATE = "connectors.Cybersource.data.state";
    private static final String KEY_BILLING_STREET = "connectors.Cybersource.data.street";
    private static final String KEY_DECISION_MANAGER_ENABLED = "connectors.Cybersource.data.decisionManager_enabled";
    private static final String KEY_PROFILE_ID = "connectors.Cybersource.profileId";
    private static final String KEY_SECRET_KEY = "connectors.Cybersource.secretKey";
    private static final String KEY_SEND_MERCHANT_DEFINED_DATA2AS_ZIP_CODE = "connectors.Cybersource.sendMerchantDefinedData2AsZipCode";
    private static final String KEY_USE_PROFILE_NAME = "connectors.Cybersource.useProfileName";
    public static final String NAME = "cybersource";
    private static final String PAYMENT_METHOD = "card";
    private static final int REASON_CODE_SUCCESS = 100;
    private static final String TRANSACTION_TYPE = "create_payment_token";
    private static final String UNSIGNED_FIELD_NAMES = "card_type,card_number,card_expiry_date,card_cvn";
    private Configuration mConfiguration = Configuration.getSharedInstance();

    public CybersourceConnector(Context context) {
        setContext(context);
        setConnection(RequestManager.register(context));
    }

    public void saveCard(String customerId, int paymentMethodId, String providerUrl, String registerReturnUrl, boolean oneTimePayment, CreditCard creditCard, AsyncListener<String> registerReturnListener) {
        LinkedHashMap<String, String> params = populateParametersForRequest(customerId, paymentMethodId, providerUrl, creditCard, registerReturnUrl, oneTimePayment);
        Uri uri = Uri.parse(providerUrl);
        for (String key : uri.getQueryParameterNames()) {
            params.put(key, uri.getQueryParameter(key));
        }
        LinkedHashMap<String, String> cleanedParams = cleanNullValues(params);
        cleanedParams.put("signed_field_names", commaSeparate(cleanedParams.keySet()) + ",signed_field_names");
        cleanedParams.put("signature", sign(cleanedParams));
        final String str = registerReturnUrl;
        final AsyncListener<String> asyncListener = registerReturnListener;
        getNetworkConnection().processRequest(new CSCreateTokenRequest(cleanedParams, uri.buildUpon().clearQuery().build().toString()), new AsyncListener<String>() {
            public void onResponse(String html, AsyncToken token, AsyncException exception) {
                CybersourceConnector.this.handleCreateTokenResponse(html, str, asyncListener);
            }
        });
    }

    public void saveCard(CustomerProfile customerProfile, int paymentMethodId, String providerUrl, String registerReturnUrl, boolean oneTimePayment, CreditCard creditCard, AsyncListener<String> registerReturnListener) {
        LinkedHashMap<String, String> params = populateParametersForRequest(String.valueOf(customerProfile.getCustomerId()), paymentMethodId, providerUrl, creditCard, registerReturnUrl, oneTimePayment);
        Uri uri = Uri.parse(providerUrl);
        for (String key : uri.getQueryParameterNames()) {
            params.put(key, uri.getQueryParameter(key));
        }
        String decisionManagerEnabled = this.mConfiguration.getStringForKey(KEY_DECISION_MANAGER_ENABLED);
        if (decisionManagerEnabled != null) {
            params.put("decisionManager_enabled", decisionManagerEnabled);
        }
        if (this.mConfiguration.getBooleanForKey(KEY_SEND_MERCHANT_DEFINED_DATA2AS_ZIP_CODE)) {
            params.put("merchant_defined_data2", customerProfile.getZipCode());
        }
        if (this.mConfiguration.getBooleanForKey(KEY_USE_PROFILE_NAME)) {
            params.put("consumer_id", String.valueOf(customerProfile.getCustomerId()));
            params.put("bill_to_forename", customerProfile.getFirstName());
            params.put("bill_to_surname", customerProfile.getLastName());
            params.put("bill_to_email", customerProfile.getEmailAddress());
            params.put("bill_to_phone", customerProfile.getMobileNumber());
        }
        LinkedHashMap<String, String> cleanedParams = cleanNullValues(params);
        cleanedParams.put("signed_field_names", commaSeparate(cleanedParams.keySet()) + ",signed_field_names");
        cleanedParams.put("signature", sign(cleanedParams));
        final String str = registerReturnUrl;
        final AsyncListener<String> asyncListener = registerReturnListener;
        getNetworkConnection().processRequest(new CSCreateTokenRequest(cleanedParams, uri.buildUpon().clearQuery().build().toString()), new AsyncListener<String>() {
            public void onResponse(String html, AsyncToken token, AsyncException exception) {
                CybersourceConnector.this.handleCreateTokenResponse(html, str, asyncListener);
            }
        });
    }

    private void handleCreateTokenResponse(String html, String registerReturnUrl, AsyncListener<String> registerReturnListener) {
        if (html == null) {
            registerReturnListener.onResponse(null, null, null);
        }
        try {
            Document document = getDocument(html);
            Node form = document.getElementById("custom_redirect");
            if (form != null) {
                NodeList list = document.getElementById("custom_redirect").getElementsByTagName("input");
                int listSize = list.getLength();
                HashMap<String, String> fields = new HashMap();
                for (int i = 0; i < listSize; i++) {
                    Node field = list.item(i);
                    if (field.getNodeType() == (short) 1) {
                        Node typeAttribute = field.getAttributes().getNamedItem("type");
                        if (typeAttribute != null && typeAttribute.getNodeValue().equals("hidden")) {
                            fields.put(field.getAttributes().getNamedItem("name").getNodeValue(), field.getAttributes().getNamedItem("value").getNodeValue());
                        }
                    }
                }
                int reasonCode = Integer.parseInt((String) fields.get("reason_code"));
                if (reasonCode == 100) {
                    String actionUrl = registerReturnUrl;
                    Node action = form.getAttributes().getNamedItem(Parameters.ACTION);
                    if (action != null) {
                        actionUrl = action.getNodeValue();
                    }
                    CSRegisterReturnRequest cSRegisterReturnRequest = new CSRegisterReturnRequest(actionUrl, fields);
                    final AsyncListener<String> asyncListener = registerReturnListener;
                    getNetworkConnection().processRequest(cSRegisterReturnRequest, new AsyncListener<String>() {
                        public void onResponse(String response, AsyncToken token, AsyncException exception) {
                            CybersourceConnector.this.handleRegisterReturnResponse(response, asyncListener);
                        }
                    });
                    return;
                }
                registerReturnListener.onResponse(null, null, new CSException(reasonCode));
                return;
            }
            registerReturnListener.onResponse(null, null, null);
        } catch (IOException | NumberFormatException | ParserConfigurationException | SAXException e) {
            registerReturnListener.onResponse(null, null, null);
        }
    }

    private void handleRegisterReturnResponse(String html, AsyncListener<String> registerReturnListener) {
        if (html == null) {
            registerReturnListener.onResponse(null, null, null);
            return;
        }
        try {
            Node result = getDocument(html).getElementById("hiddenResult");
            if (result != null) {
                registerReturnListener.onResponse(result.getAttributes().getNamedItem("value").getNodeValue(), null, null);
            } else {
                registerReturnListener.onResponse(null, null, null);
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            registerReturnListener.onResponse(null, null, null);
        }
    }

    private Document getDocument(String html) throws ParserConfigurationException, SAXException, IOException {
        if (html == null) {
            throw new IOException("This String is not a valid XML Document");
        }
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(html.replaceAll("(?<=<!--)(-)*|(-)*(?=-->)", "").getBytes()));
    }

    private LinkedHashMap<String, String> cardToHashMap(CreditCard creditCard) {
        LinkedHashMap<String, String> hashMap = new LinkedHashMap();
        if (!this.mConfiguration.getBooleanForKey(KEY_USE_PROFILE_NAME)) {
            hashMap.put("bill_to_forename", this.mConfiguration.getStringForKey(KEY_BILLING_FIRST_NAME, creditCard.getForename()));
            hashMap.put("bill_to_surname", this.mConfiguration.getStringForKey(KEY_BILLING_LAST_NAME, creditCard.getSurname()));
            hashMap.put("bill_to_email", this.mConfiguration.getStringForKey(KEY_BILLING_EMAIL, creditCard.getEmail()));
            hashMap.put("bill_to_address_phone", creditCard.getAddressPhone());
        }
        hashMap.put("bill_to_address_line1", this.mConfiguration.getStringForKey(KEY_BILLING_STREET, creditCard.getAddressStreet()));
        hashMap.put("bill_to_address_city", this.mConfiguration.getStringForKey(KEY_BILLING_CITY, creditCard.getAddressCity()));
        hashMap.put("bill_to_address_state", this.mConfiguration.getStringForKey(KEY_BILLING_STATE, creditCard.getAddressState()));
        hashMap.put("bill_to_address_country", this.mConfiguration.getStringForKey(KEY_BILLING_COUNTRY, creditCard.getAddressCountry()));
        hashMap.put("bill_to_address_postal_code", this.mConfiguration.getStringForKey(KEY_BILLING_POSTAL_CODE, creditCard.getAddressPostalCode()));
        hashMap.put("payment_token_title", creditCard.getNickname());
        hashMap.put("card_type", creditCard.getCardType());
        hashMap.put("card_number", creditCard.getCardNumber());
        hashMap.put("card_expiry_date", creditCard.getCardExpiryDate());
        hashMap.put("card_cvn", creditCard.getCardSecurityCode());
        return hashMap;
    }

    private String sign(HashMap<String, String> params) {
        String secretKey = this.mConfiguration.getStringForKey(KEY_SECRET_KEY);
        try {
            String data = getDataString(params);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(secretKeySpec);
            return Base64.encodeToString(mac.doFinal(data.getBytes(Utf8Charset.NAME)), 16);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String getDataString(HashMap<String, String> params) {
        ArrayList<String> dataToSign = new ArrayList();
        for (String signedFieldName : params.keySet()) {
            dataToSign.add(signedFieldName + "=" + String.valueOf(params.get(signedFieldName)));
        }
        return commaSeparate(dataToSign);
    }

    private String commaSeparate(Collection<String> stringList) {
        StringBuilder csv = new StringBuilder();
        Iterator<String> it = stringList.iterator();
        while (it.hasNext()) {
            csv.append((String) it.next());
            if (it.hasNext()) {
                csv.append(",");
            }
        }
        return csv.toString();
    }

    private LinkedHashMap<String, String> cleanNullValues(LinkedHashMap<String, String> origin) {
        LinkedHashMap<String, String> result = new LinkedHashMap();
        for (String key : origin.keySet()) {
            String value = (String) origin.get(key);
            if (value != null) {
                result.put(key, value);
            }
        }
        return result;
    }

    private LinkedHashMap<String, String> populateParametersForRequest(String customerId, int paymentMethodId, String providerUrl, CreditCard creditCard, String registerReturnUrl, boolean oneTimePayment) {
        LinkedHashMap<String, String> params = cardToHashMap(creditCard);
        params.put("access_key", this.mConfiguration.getStringForKey(KEY_ACCESS_KEY));
        params.put("profile_id", this.mConfiguration.getStringForKey(KEY_PROFILE_ID));
        params.put("transaction_uuid", UUID.randomUUID().toString());
        params.put("unsigned_field_names", UNSIGNED_FIELD_NAMES);
        params.put("locale", this.mConfiguration.getCurrentLanguageTag());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        params.put("signed_date_time", dateFormat.format(new Date()));
        params.put("transaction_type", TRANSACTION_TYPE);
        params.put("reference_number", customerId);
        params.put(AnalyticsArgs.TRANSACTION_ITEM_CURRENCY, this.mConfiguration.getCurrencyFormatter().getCurrency().getCurrencyCode());
        params.put("payment_method", PAYMENT_METHOD);
        params.put("override_custom_receipt_page", Uri.parse(registerReturnUrl).buildUpon().appendQueryParameter("Parameters", String.valueOf(paymentMethodId)).appendQueryParameter("PaymentMethodId", String.valueOf(paymentMethodId)).appendQueryParameter("CustomerId", customerId).appendQueryParameter("OneTimePayment", oneTimePayment ? DCSPreference.ECP_LEGACY_STATUS_ENABLED : DCSPreference.ECP_LEGACY_STATUS_DISABLED).build().toString());
        return params;
    }
}
