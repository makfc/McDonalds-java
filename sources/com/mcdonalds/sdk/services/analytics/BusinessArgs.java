package com.mcdonalds.sdk.services.analytics;

import android.text.format.DateFormat;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import java.util.Date;
import java.util.HashMap;

public class BusinessArgs {
    public static final String EVENT_ACCEPT_PRIVACY_TERMS = "accept_privacy_terms";
    public static final String EVENT_ADD_FAVORITE = "Add order to favorite";
    public static final String EVENT_APPLY_TO_ORDER = "Apply to mobile order";
    public static final String EVENT_APP_OPEN_COUNT = "app_open_count";
    public static final String EVENT_CHECKOUT = "Check out";
    public static final String EVENT_ORDER_CONFIRM = "Order Confirm";
    public static final String EVENT_PRODUCT_OFFER_CLICK = "offer_click";
    public static final String EVENT_PRODUCT_OFFER_OPT = "product_offer_opt";
    public static final String EVENT_PRODUCT_OFFER_REDEEM = "offer_redeem_intent";
    public static final String EVENT_REGISTRATION_OFFERS_OPT = "registration_offers_opt";
    public static final String EVENT_REGISTRATION_SUCCESS = "registration_success";
    public static final String KEY_CART_ID = "cartID";
    public static final String KEY_CUSTOMER_ID = "customer_id";
    public static final String KEY_ECP_ID = "ecp_id";
    public static final String KEY_EVENT_TITLE = "eventTitle";
    public static final String KEY_EVENT_VALUE = "eventValue";
    public static final String KEY_OFFER_CREATIVE = "offer_creative";
    public static final String KEY_OFFER_ID = "offer_id";
    public static final String KEY_OFFER_NAME = "offer_name";
    public static final String KEY_OPT_STATUS = "opt_status";
    public static final String KEY_ORDER_ID = "orderID";
    public static final String KEY_ORDER_TOTAL = "orderTotal";
    public static final String KEY_PRODUCT_CATEGORY = "product_category";
    public static final String KEY_PRODUCT_ID = "productID";
    public static final String KEY_PRODUCT_NAME = "productName";
    public static final String KEY_PRODUCT_QUANTITY = "productQuantity";
    public static final String KEY_SIGNIN_TYPE = "sign_in_type";
    public static final String KEY_SOCIAL_SITE = "social_site";
    public static final String VALUE_ALL = "All";
    public static final String VALUE_IN = "IN";
    public static final String VALUE_MCD = "mcd";
    public static final String VALUE_OUT = "OUT";
    public static final String VALUE_SOCIAL = "social";

    public static HashMap<String, String> getRegistrationOffersOpt(boolean optIn) {
        HashMap<String, String> values = new HashMap();
        values.put(KEY_EVENT_TITLE, EVENT_REGISTRATION_OFFERS_OPT);
        values.put(KEY_EVENT_VALUE, optIn ? VALUE_IN : VALUE_OUT);
        return values;
    }

    public static HashMap<String, String> getAgreedConfirmation() {
        HashMap<String, String> values = new HashMap();
        values.put(KEY_EVENT_TITLE, EVENT_ACCEPT_PRIVACY_TERMS);
        values.put(KEY_EVENT_VALUE, "");
        return values;
    }

    public static HashMap<String, Object> getRegistrationSuccess(boolean isSocial, String socialType, String ecpId, String customerId) {
        HashMap<String, Object> values = new HashMap();
        values.put(KEY_EVENT_TITLE, EVENT_REGISTRATION_SUCCESS);
        HashMap<String, String> eventValues = new HashMap();
        eventValues.put(KEY_SIGNIN_TYPE, isSocial ? "social" : VALUE_MCD);
        eventValues.put(KEY_SOCIAL_SITE, socialType);
        eventValues.put(KEY_ECP_ID, ecpId);
        eventValues.put(KEY_CUSTOMER_ID, customerId);
        values.put(KEY_EVENT_VALUE, eventValues);
        return values;
    }

    public static HashMap<String, Object> getProductOfferOpt(String productCategory, String optStatus) {
        HashMap<String, Object> values = new HashMap();
        values.put(KEY_EVENT_TITLE, EVENT_PRODUCT_OFFER_OPT);
        HashMap<String, String> eventValues = new HashMap();
        eventValues.put(KEY_PRODUCT_CATEGORY, productCategory);
        eventValues.put(KEY_OPT_STATUS, optStatus);
        values.put(KEY_EVENT_VALUE, eventValues);
        return values;
    }

    public static HashMap<String, Object> getAppOpen() {
        HashMap<String, Object> values = new HashMap();
        HashMap<String, Object> eventValues = new HashMap();
        eventValues.put(KEY_EVENT_TITLE, EVENT_APP_OPEN_COUNT);
        eventValues.put(KEY_EVENT_VALUE, "1");
        values.put(KEY_EVENT_VALUE, eventValues);
        return values;
    }

    private static HashMap<String, Object> getOffers(String eventTitle, Offer offer) {
        if (offer == null) {
            return null;
        }
        HashMap<String, Object> values = new HashMap();
        values.put(KEY_EVENT_TITLE, eventTitle);
        HashMap<String, String> eventValues = new HashMap();
        eventValues.put(KEY_OFFER_ID, String.valueOf(offer.getOfferId()));
        eventValues.put(KEY_OFFER_NAME, offer.getName());
        eventValues.put(KEY_OFFER_CREATIVE, "");
        values.put(KEY_EVENT_VALUE, eventValues);
        return values;
    }

    public static HashMap<String, Object> getOfferClick(Offer offer) {
        return getOffers(EVENT_PRODUCT_OFFER_CLICK, offer);
    }

    public static HashMap<String, Object> getOfferRedeem(Offer offer) {
        return getOffers(EVENT_PRODUCT_OFFER_REDEEM, offer);
    }

    public static HashMap<String, Object> getAddToMobileOrder(Offer offer) {
        return getOffers(EVENT_APPLY_TO_ORDER, offer);
    }

    public static HashMap<String, Object> getFavoriteOrderProduct(OrderProduct product) {
        HashMap<String, Object> values = new HashMap();
        values.put(KEY_EVENT_TITLE, EVENT_ADD_FAVORITE);
        HashMap<String, String> eventValues = new HashMap();
        eventValues.put(KEY_PRODUCT_NAME, product.getDisplayName());
        eventValues.put(KEY_PRODUCT_ID, product.getRecipeId());
        values.put(KEY_EVENT_VALUE, eventValues);
        return values;
    }

    public static HashMap<String, Object> getProductFromBasket(OrderProduct product) {
        HashMap<String, Object> values = new HashMap();
        values.put(KEY_EVENT_TITLE, EVENT_CHECKOUT);
        HashMap<String, String> eventValues = new HashMap();
        eventValues.put(KEY_PRODUCT_NAME, product.getDisplayName());
        eventValues.put(KEY_PRODUCT_ID, product.getRecipeId());
        eventValues.put(KEY_PRODUCT_QUANTITY, String.valueOf(product.getQuantity()));
        eventValues.put(KEY_CART_ID, getTimeStamp());
        values.put(KEY_EVENT_VALUE, eventValues);
        return values;
    }

    public static HashMap<String, Object> getOrderConfirmation(String total) {
        HashMap<String, Object> values = new HashMap();
        values.put(KEY_EVENT_TITLE, EVENT_ORDER_CONFIRM);
        HashMap<String, String> eventValues = new HashMap();
        eventValues.put(KEY_ORDER_ID, getTimeStamp());
        eventValues.put(KEY_ORDER_TOTAL, total);
        values.put(KEY_EVENT_VALUE, eventValues);
        return values;
    }

    private static String getTimeStamp() {
        return DateFormat.format("yyyy-MM-ddhh:mm:ss", new Date()).toString();
    }
}
