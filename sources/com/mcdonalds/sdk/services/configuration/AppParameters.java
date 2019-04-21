package com.mcdonalds.sdk.services.configuration;

import android.support.annotation.Nullable;
import com.facebook.internal.ServerProtocol;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mcdonalds.sdk.modules.models.OffersOperationType;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AppParameters {
    public static final String ALLOWED_LOGIN_FIELDS = "AllowedLoginFields";
    public static final String APNS_CERTIFICATE_FILE_PASSWORD = "ApnsCertificateFilePassword";
    public static final String APNS_CERTIFICATE_FILE_PATH = "ApnsCertificateFilePath";
    public static final String APNS_CONNECTION_TIMEOUT = "ApnsConnectionTimeout";
    public static final String APNS_DISABLE_CERTIFICATE_CHECK = "ApnsDisableCertificateCheck";
    public static final String APNS_FEEDBACK_HOST = "ApnsFeedbackHost";
    public static final String APNS_FEEDBACK_PORT = "ApnsFeedbackPort";
    public static final String APNS_HOST = "ApnsHost";
    public static final String APNS_PORT = "ApnsPort";
    public static final String APNS_SKIP_SSL = "ApnsSkipSsl";
    public static final String APPLICATION_TOKEN_TIMEOUT = "applicationTokenTimeout";
    public static final String AVAILABLE_CHECK_INMETHODS = "availableCheckInMethods";
    public static final String AVAILABLE_MARKET_SERVICE_TYPE = "AvailableMarketServiceType";
    public static final String AVOID_CHARGED_CUST_ONPROMO_ITEMS = "avoidChargedCustOnPromoItems";
    public static final String BAR_CODE_SCREEN_TIMEOUT = "barCodeScreenTimeout";
    public static final String CANCEL_PAYMENTDT_ADDRESS = "CancelPaymentDT_Address";
    public static final String DAYS_BEFORE_CARD_EXPIRATION = "daysBeforeCardExpiration";
    public static final String DEFAULT_DISPLAY_CATEGORY_SCREEN = "defaultDisplayCategoryScreen";
    public static final String DEFAULT_ORDER_TRACKING_NUMBER_DAYS = "defaultOrderTrackingNumberDays";
    public static final String DEFAULT_POS_STORE_NUMBER = "defaultPosStoreNumber";
    public static final String DELIVERY_DAY_PART_OFFSET_IN_MINUTES = "DeliveryDaypartOffsetInMinutes";
    public static final String DISPLAY_GET_DIRECTION = "displayGetDirection";
    public static final String DISPLAY_GET_DIRECTIONS = "displayGetDirections";
    public static final String DISPLAY_MAP_ORIENTATION = "displayMapOrientation";
    public static final String DISPLAY_MAP_VIEW_STORE_LOCATION = "displayMapViewStoreLocation";
    public static final String DISPLAY_MARKETING_OPT_IN = "displayMarketingOptIn";
    public static final String DISPLAY_ZIP_CODE = "displayZipCode";
    public static final String DOAVM_VALIDATION = "doAVM_Validation";
    public static final String DO_NOT_CHARGE_CAN_ADD_IFLIGHT = "doNotChargeCanAddIfLight";
    public static final String EDT_RANGE = "EDTRange";
    public static final String EMAIL_ADDRESS_CONFIRM_SUBJECT = "EmailAddressConfirmSubject";
    public static final String EMAIL_ADDRESS_CONFIRM_TEMPLATE = "EmailAddressConfirmTemplate";
    public static final String EMAIL_CHANGE_PASSWORD_SUBJECT = "EmailChangePasswordSubject";
    public static final String EMAIL_CHANGE_PASSWORD_TEMPLATE = "EmailChangePasswordTemplate";
    public static final String EMAIL_GENERIC_SUBJECT = "EmailGenericSubject";
    public static final String EMAIL_GENERIC_TEMPLATE = "EmailGenericTemplate";
    public static final String EMAIL_LOGIN_VERIFICATION_SUBJECT = "EmailLoginVerificationSubject";
    public static final String EMAIL_LOGIN_VERIFICATION_TEMPLATE = "EmailLoginVerificationTemplate";
    public static final String EMAIL_ORDER_CONFIRMATION_SUBJECT = "EmailOrderConfirmationSubject";
    public static final String EMAIL_ORDER_CONFIRMATION_TEMPLATE = "EmailOrderConfirmationTemplate";
    public static final String EMAIL_ORDER_STATUS_SUBJECT = "EmailOrderStatusSubject";
    public static final String EMAIL_ORDER_STATUS_TEMPLATE = "EmailOrderStatusTemplate";
    public static final String EMAIL_REGISTER_SUBJECT = "EmailRegisterSubject";
    public static final String EMAIL_REGISTER_TEMPLATE = "EmailRegisterTemplate";
    public static final String EMAIL_VALIDATION_REG_EX = "EmailValidationRegEx";
    public static final String ENABLE_ANALYTICS = "enableAnalytics";
    public static final String ENABLE_MULTIPLE_MENU_TYPES = "enableMultipleMenuTypes";
    public static final String ENABLE_NEW_RELIC_REPORT = "enableNewRelicReport";
    public static final String ENABLE_STORE_LEVEL_CHECKIN_CONFIG = "enableStoreLevelCheckinConfig";
    public static final String ESP_ENABLE_CUSTOMER_ID = "ESP_EnableCustomerId";
    public static final String ETONENETSMS_PASSWORD = "EtonenetSMS_Password";
    public static final String ETONENETSMS_USERNAME = "EtonenetSMS_Username";
    public static final String ETONENETURL = "EtonenetURL";
    public static final String EXECUTE_PAYMENTDT_ADDRESS = "ExecutePaymentDT_Address";
    public static final String EXPIRED_OFFERS_THRESHOLD_MINUTES = "ExpiredOffersThresholdMinutes";
    public static final String FILL_DEFAULTS_ITEMID = "FillDefaultsItemID";
    public static final String GCM_APPLICATION_IDPACKAGE_NAME = "GcmApplicationIdPackageName";
    public static final String GCM_AUTH_TOKEN = "GcmAuthToken";
    public static final String GCM_DRY_RUN = "GcmDryRun";
    public static final String GCM_SENDER_ID = "GcmSenderId";
    public static final String GCM_URL = "GcmUrl";
    public static final String IGNORE_ERRORS_ONTOTALIZE = "ignoreErrorsOnTotalize";
    public static final String IMAGE_BYTES_TODELETE_ONLIMIT = "imageBytesToDeleteOnLimit";
    public static final String IMAGE_CACHE_BYTES_LIMIT = "imageCacheBytesLimit";
    public static final String IMAGE_CODE_TYPE = "imageCodeType";
    public static final String IMAGE_DAYS_TOCACHE = "imageDaysToCache";
    public static final String IMPORT_ITEM_CATEGORY = "ImportItemCategory";
    public static final String IMPORT_KIOSK_IMAGES = "ImportKioskImages";
    public static final String INTERVAL_MINUTES_TOADV_ORDER = "intervalMinutesToAdvOrder";
    public static final String INVALID_MOBILE_ORDER_PRODUCT = "invalidMobileOrderProduct";
    public static final String LOGIN_MODE = "LoginMode";
    public static final String LOG_FILE_BYTES_LIMIT = "logFileBytesLimit";
    public static final String MANDATORY_OFFERS_SUBSCRIPTION = "mandatoryOffersSubscription";
    public static final String MAX_AUTHENTICATION_TRIES = "maxAuthenticationTries";
    public static final String MAX_CHOICE_OPTIONS = "maxChoiceOptions";
    public static final String MAX_DECLINES_PER_CARD = "maxDeclinesPerCard";
    public static final String MAX_DECLINES_PER_ORDER = "maxDeclinesPerOrder";
    public static final String MAX_FAVORITE_ELEMENTS_PER_CUSTOMER = "maxFavoriteElementsPerCustomer";
    public static final String MAX_ITEM_GRILL_QUANTITY = "maxItemGrillQuantity";
    public static final String MAX_ITEM_QUANTITY = "maxItemQuantity";
    public static final String MAX_LOCATION_BASED_STORES = "maxLocationBasedStores";
    public static final String MAX_LOCATION_ELEMENTS_PER_CUSTOMER = "maxLocationElementsPerCustomer";
    public static final String MAX_MINUTES_TOADV_ORDER = "maxMinutesToAdvOrder";
    public static final String MAX_NEARBY_RESTAURANTS_PAGES = "maxNearbyRestaurantsPages";
    public static final String MAX_NEARBY_RESTAURANTS_PER_SCREEN = "maxNearbyRestaurantsPerScreen";
    public static final String MAX_NICKNAME_LENGHT = "maxNicknameLenght";
    public static final String MAX_NICKNAME_LENGTH = "maxNicknameLength";
    public static final String MAX_ORDER_ENTRIES = "maxOrderEntries";
    public static final String MAX_PASSWORD_LENGHT = "maxPasswordLenght";
    public static final String MAX_PASSWORD_LENGTH = "maxPasswordLength";
    public static final String MAX_PAYMENT_CARDS = "maxPaymentCards";
    public static final String MAX_PRE_SELECTED_CARDS = "maxPreSelectedCards";
    public static final String MAX_PROMOTIONS_PER_RESTAURANT = "maxPromotionsPerRestaurant";
    public static final String MAX_QTTY_MULT = "maxQttyMult";
    public static final String MAX_QTTY_OFPAYMENT_METHODS = "maxQttyOfPaymentMethods";
    public static final String MAX_QTTY_ONBASKET = "maxQttyOnBasket";
    public static final String MAX_QTY_OFFER_ITEMS_PER_LIST = "maxQtyOfferItemsPerList";
    public static final String MAX_RECENT_ORDER_PER_CUSTOMER = "maxRecentOrderPerCustomer";
    public static final String MAX_RESTAURANTS_ONSEARCH = "maxRestaurantsOnSearch";
    public static final String MAX_ZOOM_MAP = "maxZoomMap";
    public static final String MINUTES_BEFORE_RESTAURANT_CLOSE = "minutesBeforeRestaurantClose";
    public static final String MIN_ITEM_GRILL_QUANTITY = "minItemGrillQuantity";
    public static final String MIN_MINUTES_TOADV_ORDER = "minMinutesToAdvOrder";
    public static final String MIN_PASSWORD_LENGHT = "minPasswordLenght";
    public static final String MIN_PASSWORD_LENGTH = "minPasswordLength";
    public static final String NORMALIZE_CHOICES = "NormalizeChoices";
    public static final String NOTIFICATION_EMAIL_FROM = "notificationEmailFrom";
    public static final String NOTIFICATION_PLATFORM = "notificationPlatform";
    public static final String OFFERS_ENABLE_PUSH_NOTIFICATIONS = "OffersEnablePushNotifications";
    public static final String OFFER_EXPIRATION_THRESHOLD_HOURS = "OfferExpirationThresholdHours";
    public static final String OFFER_OPERATION_MODE = "offerOperationMode";
    public static final String OFFER_REDEEM_MODEIN_REGISTER = "offerRedeemModeinRegister";
    public static final String OPERATION_MODE = "operationMode";
    public static final String ORDER_BARCODE_TIMEOUT = "orderBarcodeTimeout";
    public static final String PASSWORD_ENCRYPTER_TYPE = "PasswordEncrypterType";
    public static final String PERFORM_ZIP_CODE_VALIDATION = "performZipCodeValidation";
    public static final String POS_FILE_FORMAT_VERSIONNP6X = "POS_FileFormatVersionNP6x";
    private static final String PREF_APP_PARAMETERS = "PREF_APP_PARAMETERS";
    public static final String PRICE_TYPE = "priceType";
    public static final String QTY_EXPIRED_OFFERS = "qtyExpiredOffers";
    public static final String QTY_REDEEMED_OFFERS = "qtyRedeemedOffers";
    public static final String RECENT_ORDERS_TOREMEMBER = "recentOrdersToRemember";
    public static final String RECENT_ORDER_IF_FINALIZED = "recentOrderIfFinalized";
    public static final String REGISTER_CONFIRMATION_LINK = "registerConfirmationLink";
    public static final String REQUIRED_PROFILE_FIELDS = "RequiredProfileFields";
    public static final String RESTAURANTS_ONSEARCH = "restaurantsOnSearch";
    public static final String RESTAURANTS_TOCACHE = "restaurantsToCache";
    public static final String SET_ERROR_ONROOT_PRODUCT = "setErrorOnRootProduct";
    public static final String SHOWSMS = "showSMS";
    public static final String SHOW_CARD_EXPIRATION = "showCardExpiration";
    public static final String SHOW_MAKE_MEAL_BUTTON = "showMakeMealButton";
    public static final String SHOW_NUTRITION_BUTTON = "showNutritionButton";
    public static final String SHOW_PHONE_NUMBER = "showPhoneNumber";
    public static final String SHOW_PRIVACY_POLICY = "showPrivacyPolicy";
    public static final String SHOW_TAX_TOCUSTOMER = "showTaxToCustomer";
    public static final String SPECIAL_CHARS_CONVERTION_TABLE = "specialCharsConvertionTable";
    public static final String STATIC_DATA_BASEURL = "staticDataBaseURL";
    public static final String STORE_ABOUT_TOCLOSE_TIME = "storeAboutToCloseTime";
    public static final String STORE_LOCATION_MAX_DISTANCE = "storeLocationMaxDistance";
    public static final String STORE_STATUS_CHECK_TIME = "storeStatusCheckTime";
    public static final String SUPPRESS_CARD_PRESENTATION_ALERT = "suppressCardPresentationAlert";
    public static final String TIMEOUTDT_SCAN_FAIL = "timeoutDT_ScanFail";
    public static final String TIME_BETWEEN_ATTEMPTSDT_SCAN_FAIL = "timeBetweenAttemptsDT_ScanFail";
    public static final String TIME_SHOW_DAY_PART_CHANGE_ORCLOSE = "timeShowDayPartChangeOrClose";
    public static final String TIME_TOASK_FOR_NEWER_VERSION = "timeToAskForNewerVersion";
    public static final String TIME_TOREMOVE_TEMPORARY_CUSTOMERS_INHOURS = "TimeToRemoveTemporaryCustomersInHours";
    public static final String TIME_TOREMOVE_TEMPORARY_CUSTOMERS_PAYMENT_METHODS_INMINUTES = "TimeToRemoveTemporaryCustomersPaymentMethodsInMinutes";
    public static final String TIME_TOSHOW_CARD_EXPIRATION = "timeToShowCardExpiration";
    public static final String TOTALIZATION_MODE = "totalizationMode";
    public static final String USE_EXTERNAL_ADDRESS_PROVIDER = "useExternalAddressProvider";
    public static final String VERIFIED_EMAIL_REQUIRED = "VerifiedEmailRequired";
    public static final String VERIFIED_PHONE_NUMBER_REQUIRED = "VerifiedPhoneNumberRequired";
    private static Map<String, String> appParameters = new HashMap();

    /* renamed from: com.mcdonalds.sdk.services.configuration.AppParameters$1 */
    static class C41091 extends TypeToken<HashMap<String, String>> {
        C41091() {
        }
    }

    public static void setAppParameters(@Nullable Map<String, String> appParameters) {
        appParameters.clear();
        if (appParameters != null) {
            appParameters.putAll(appParameters);
        }
        saveToPreferences(appParameters);
    }

    private static void saveToPreferences(Map<String, String> appParameters) {
        LocalDataManager dataManager = LocalDataManager.getSharedInstance();
        String str = PREF_APP_PARAMETERS;
        Gson gson = new Gson();
        dataManager.set(str, !(gson instanceof Gson) ? gson.toJson((Object) appParameters) : GsonInstrumentation.toJson(gson, (Object) appParameters));
    }

    public static Map<String, String> getAppParameters() {
        if (!appParameters.isEmpty()) {
            return appParameters;
        }
        String map = LocalDataManager.getSharedInstance().getString(PREF_APP_PARAMETERS, null);
        Type type = new C41091().getType();
        Gson gson = new Gson();
        Map<String, String> params = (Map) (!(gson instanceof Gson) ? gson.fromJson(map, type) : GsonInstrumentation.fromJson(gson, map, type));
        if (params != null) {
            appParameters = params;
        }
        return appParameters;
    }

    public static String getAppParameter(String parameter) {
        return (String) getAppParameters().get(parameter);
    }

    public static boolean getBooleanForParameter(String parameter) {
        String value = getAppParameter(parameter);
        return value != null && value.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    }

    public static int getOfferOperationMode() {
        String param = getAppParameter(OFFER_OPERATION_MODE);
        if (param != null) {
            return Integer.parseInt(param);
        }
        return OffersOperationType.AllModes.getValue();
    }
}
