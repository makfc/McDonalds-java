package com.mcdonalds.sdk.connectors.mwcustomersecurity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.connectors.BaseConnector;
import com.mcdonalds.sdk.connectors.CustomerConnector;
import com.mcdonalds.sdk.connectors.middleware.helpers.MWConnectorShared;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerData;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentURLPostInfo;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWWechatTokenResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityAccountDeleteRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityAccountForgotPasswordRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityAccountNativeRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityAccountResendEmailRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityAccountResendSMSRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityAccountResetPasswordRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityAccountSocialRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityAccountUpdateRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityAccountVerificationEmailRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityAccountVerificationSMSRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityAuthenticationRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityConfigServiceRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecurityRefreshTokenRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.request.MWCustomerSecuritySocialAuthenticationRequest;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAccountDetailsResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAccountForgotPasswordResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAccountNativeResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAccountResetPasswordResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAccountUpdateResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAccountVerificationSMSResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAuthenticationDetailsResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityAuthenticationResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.MWCustomerSecurityConfigServiceResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.janrain.MWCustomerSecurityJanrainAccountDetailsInvalidFieldsResponse;
import com.mcdonalds.sdk.connectors.mwcustomersecurity.response.janrain.MWCustomerSecurityJanrainAuthenticationNativeResponse;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.customer.CustomerProfile.AccountDeleteType;
import com.mcdonalds.sdk.modules.customer.CustomerProfile.AccountVerificationType;
import com.mcdonalds.sdk.modules.models.AddressValidationResult;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.Catalog;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.CustomerLoginInfo;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.GetAddressElementsResult;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.RequestManager;
import com.mcdonalds.sdk.utils.DateUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MWCustomerSecurityConnector extends BaseConnector implements CustomerConnector {
    public static final String CONFIG = "connectors.MiddlewareCustomerSecurity.customerSecurity";
    public static final String CONFIG_API_KEY = "connectors.MiddlewareCustomerSecurity.customerSecurity.mcd_apikey";
    public static final String CONFIG_BASE_URL = "connectors.MiddlewareCustomerSecurity.customerSecurity.baseUrl";
    public static final String CONFIG_LOCALE = "connectors.MiddlewareCustomerSecurity.customerSecurity.languageName";
    public static final String CONFIG_MARKET_ID = "connectors.MiddlewareCustomerSecurity.customerSecurity.marketId";
    public static final String CONFIG_VERSIONING_SERVICE_BASE_URL = "connectors.MiddlewareCustomerSecurity.customerSecurity.versioningService";
    private static final String MIDDLEWARE_ACCESS_TOKEN_EXPIRED_CODE = "414";
    private static final String MIDDLEWARE_ACCESS_TOKEN_INVALID_CODE = "413";
    private static final String MIDDLEWARE_ACCOUNT_DEACTIVATED_CODE = "6910";
    private static final String MIDDLEWARE_ACCOUNT_DEACTIVATED_ERROR_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_6910);
    private static final String MIDDLEWARE_ACCOUNT_UPDATE_PASSWORD_ERROR_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_350);
    private static final String MIDDLEWARE_AUTHENTICATION_ERROR_GENERIC_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_210);
    private static final String MIDDLEWARE_CHANGE_PASSWORD_EMAIL_EXPIRED_FAILURE_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_413);
    private static final String MIDDLEWARE_CHANGE_PASSWORD_FAILED_CODE = "6906";
    private static final String MIDDLEWARE_CHANGE_PASSWORD_INCORRECT_CODE = "350";
    private static final String MIDDLEWARE_DELETE_ACCOUNT_ERROR_CODE = "310";
    private static final String MIDDLEWARE_DOWN_ERROR_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.offline_warning);
    private static final String MIDDLEWARE_EMAIL_ALREADY_VERIFIED_ERROR_CODE = "540";
    private static final String MIDDLEWARE_EMAIL_ALREADY_VERIFIED_FAILURE_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_540);
    private static final String MIDDLEWARE_EMAIL_NOT_VERIFIED_CODE = "499";
    private static final String MIDDLEWARE_EMAIL_VERIFICATION_LINK_EXPIRED_CODE = "6911";
    private static final String MIDDLEWARE_EMAIL_VERIFY_LINK_EXPIRED_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_6911);
    private static final String MIDDLEWARE_FIELDS_INVALID_CODE = "210";
    private static final String MIDDLEWARE_INTERNAL_SERVER_ERROR_CODE = "6900";
    private static final String MIDDLEWARE_REFRESH_TOKEN_FAILED_CODE = "6908";
    private static final String MIDDLEWARE_REGISTRATION_EMAIL_INVALID_CODE = "6901";
    private static final String MIDDLEWARE_REGISTRATION_EMAIL_INVALID_FAILURE_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_6901);
    private static final String MIDDLEWARE_REGISTRATION_FAILURE_CODE = "390";
    /* renamed from: MIDDLEWARE_REGISTRATION_FAILURE_EMAIL_AND_PHONE_NUMBER_IN_USE_MESSAGE */
    private static final String f6683x563cf3e1 = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_3903);
    private static final String MIDDLEWARE_REGISTRATION_FAILURE_EMAIL_IN_USE_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_3901);
    private static final String MIDDLEWARE_REGISTRATION_FAILURE_PHONE_NUMBER_IN_USE_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_3902);
    private static final String MIDDLEWARE_RESET_PASSWORD_EMAIL_INVALID_FAILURE_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_212);
    private static final String MIDDLEWARE_SERVER_ERROR_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_6900);
    private static final String MIDDLEWARE_SMS_VERIFICATION_FAILED_CODE = "6802";
    private static final String MIDDLEWARE_SMS_VERIFICATION_FAILURE_MESSAGE = McDonalds.getContext().getResources().getString(C3883R.string.mw_cs_err_6802);
    private static final String MIDDLEWARE_SUCCESS_CODE = "6011";
    private static final String MIDDLEWARE_TOKEN_UNKNOWN_CODE = "200";
    public static final String NAME = "mwcustomersecurity";
    private String mAccessToken;
    private String mApiBaseUrl;
    private String mApiKey;
    private boolean mCalledRefreshToken;
    private String mLocale;
    private String mMarket;
    private String mRefreshToken;
    private boolean mRefreshTokenFailed;
    private MWConnectorShared mSharedData = new MWConnectorShared();
    private AccountVerificationType mVerificationType;

    public MWCustomerSecurityConnector(Context context) {
        setContext(context);
        setConnection(RequestManager.register(context));
        setBaseUrl();
    }

    private void setBaseUrl() {
        Configuration config = Configuration.getSharedInstance();
        String base = (String) config.getValueForKey(CONFIG_BASE_URL);
        this.mApiKey = (String) config.getValueForKey(CONFIG_API_KEY);
        this.mMarket = (String) config.getValueForKey(CONFIG_MARKET_ID);
        this.mLocale = (String) config.getValueForKey(CONFIG_LOCALE);
    }

    public String getConfigBasePath() {
        return CONFIG;
    }

    public String getConfigBaseUrl() {
        return Configuration.getSharedInstance().getStringForKey(CONFIG_BASE_URL);
    }

    public String getURLStringForEndpoint(String endpoint) {
        return Configuration.getSharedInstance().getValueForKey(CONFIG_BASE_URL) + endpoint;
    }

    public AsyncToken authenticate(final AuthenticationParameters parameters, final AsyncListener<CustomerProfile> listener) {
        String locale = Configuration.getSharedInstance().getLocalizedStringForKey("connectors.MiddlewareCustomerSecurity.customerSecurity.locale");
        AsyncListener<MWCustomerSecurityJanrainAuthenticationNativeResponse> requestListener = new AsyncListener<MWCustomerSecurityJanrainAuthenticationNativeResponse>() {
            public void onResponse(MWCustomerSecurityJanrainAuthenticationNativeResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = exception;
                if (response == null) {
                    return;
                }
                CustomerProfile customer;
                if (response.getStatusCode().equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                    customer = MWCustomerSecurityConnector.this.fromJanrainCustomerResponse(response);
                    MWCustomerSecurityConnector.this.mAccessToken = response.getAccessToken();
                    MWCustomerSecurityConnector.this.mRefreshToken = response.getRefreshToken();
                    customer.setPassword(parameters.getPassword());
                    MWCustomerSecurityConnector.this.getVersioningService(customer, listener);
                } else if (response.getStatusCode().equals(MWCustomerSecurityConnector.MIDDLEWARE_ACCOUNT_DEACTIVATED_CODE)) {
                    listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_ACCOUNT_DEACTIVATED_ERROR_MESSAGE));
                } else if (response.getStatusCode().equals(MWCustomerSecurityConnector.MIDDLEWARE_EMAIL_NOT_VERIFIED_CODE)) {
                    customer = new CustomerProfile();
                    customer.setUserName(parameters.getEmailAddress());
                    customer.setEmailAddress(parameters.getEmailAddress());
                    customer.setPassword(parameters.getPassword());
                    customer.setEmailActivated(false);
                    listener.onResponse(customer, token, localException);
                } else if (!response.getStatusCode().equals(MWCustomerSecurityConnector.MIDDLEWARE_FIELDS_INVALID_CODE)) {
                    listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                } else if (response.getDetails().getInvalidFields().getSignInForm() != null) {
                    listener.onResponse(null, token, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_AUTHENTICATION_ERROR_GENERIC_MESSAGE));
                } else {
                    listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                }
            }
        };
        if (parameters.isUsingSocialLogin()) {
            String socialProvider = "";
            switch (parameters.getSocialServiceID() < 5 ? parameters.getSocialServiceID() : -1) {
                case 1:
                    socialProvider = "googleplus";
                    break;
                case 2:
                    socialProvider = "facebook";
                    break;
            }
            getNetworkConnection().processRequest(new MWCustomerSecuritySocialAuthenticationRequest(this, locale, parameters.getEmailAddress(), parameters.getFirstName(), parameters.getLastName(), socialProvider, parameters.getSocialAuthenticationToken()), requestListener);
        } else {
            getNetworkConnection().processRequest(new MWCustomerSecurityAuthenticationRequest(this, parameters.getUserName(), parameters.getPassword()), requestListener);
        }
        return null;
    }

    private void getVersioningService(final CustomerProfile customer, final AsyncListener<CustomerProfile> listener) {
        getNetworkConnection().processRequest(new MWCustomerSecurityConfigServiceRequest(this), new AsyncListener<MWCustomerSecurityConfigServiceResponse>() {
            public void onResponse(MWCustomerSecurityConfigServiceResponse response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    DateFormat dateFormat = DateFormat.getDateInstance(1, new Locale(response.getConfiguration().getLocale()));
                    if (TextUtils.isEmpty(customer.getmTermsAndConditionVersion()) || TextUtils.isEmpty(customer.getmPrivacyPolicyVersion())) {
                        customer.setShouldUpdateTermsAndCondition(true);
                        customer.setShouldUpdatePrivacyPolicy(true);
                    } else {
                        try {
                            Date acceptedTncDate = DateUtils.parseFromISO8631(customer.getmTermsAndConditionVersion(), false);
                            Date acceptedPPDate = DateUtils.parseFromISO8631(customer.getmPrivacyPolicyVersion(), false);
                            Date latestTncDate = DateUtils.parseFromISO8631(response.getConfiguration().getTncMobileDate(), false);
                            Date latestPPDate = DateUtils.parseFromISO8631(response.getConfiguration().getPpMobileDate(), false);
                            if (acceptedTncDate.getTime() < latestTncDate.getTime()) {
                                customer.setShouldUpdateTermsAndCondition(true);
                            } else {
                                customer.setShouldUpdateTermsAndCondition(false);
                            }
                            if (acceptedPPDate.getTime() < latestPPDate.getTime()) {
                                customer.setShouldUpdatePrivacyPolicy(true);
                            } else {
                                customer.setShouldUpdatePrivacyPolicy(false);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    String verificationType = response.getConfiguration().getVerificationType();
                    Object obj = -1;
                    switch (verificationType.hashCode()) {
                        case 114009:
                            if (verificationType.equals("sms")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 96619420:
                            if (verificationType.equals("email")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            customer.setVerificationType(AccountVerificationType.SMS);
                            break;
                        case 1:
                            customer.setVerificationType(AccountVerificationType.EMAIL);
                            break;
                        default:
                            customer.setVerificationType(AccountVerificationType.NONE);
                            break;
                    }
                    customer.setCustomerLoginInfo(new CustomerLoginInfo(false, customer.isEmailVerified(), false, customer.isSmsVerified()));
                    String accountDeleteType = response.getConfiguration().getAccountDeleteType();
                    obj = -1;
                    switch (accountDeleteType.hashCode()) {
                        case -1335458389:
                            if (accountDeleteType.equals("delete")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1671308008:
                            if (accountDeleteType.equals("disable")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            customer.setAccountDeleteType(AccountDeleteType.DELETE);
                            break;
                        case 1:
                            customer.setAccountDeleteType(AccountDeleteType.DEACTIVATE);
                            break;
                        default:
                            customer.setAccountDeleteType(AccountDeleteType.NONE);
                            break;
                    }
                    listener.onResponse(customer, null, null);
                    return;
                }
                listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
            }
        });
    }

    private CustomerProfile fromJanrainCustomerResponse(MWCustomerSecurityJanrainAuthenticationNativeResponse response) {
        boolean z;
        boolean z2 = true;
        CustomerProfile customer = new CustomerProfile();
        customer.setFirstName(response.getDetails().getCapturedUserResponse().getFirstName());
        customer.setLastName(response.getDetails().getCapturedUserResponse().getLastName());
        customer.setEmailAddress(response.getDetails().getCapturedUserResponse().getEmailAddress());
        customer.setBirthDate(response.getDetails().getCapturedUserResponse().getBirthdateCalendar());
        customer.setGender(response.getDetails().getCapturedUserResponse().getGender());
        customer.setMobileNumber(response.getDetails().getCapturedUserResponse().getPrimaryAddressResponse().getMobilePhone());
        customer.setZipCode(response.getDetails().getCapturedUserResponse().getPrimaryAddressResponse().getZipCode());
        customer.setUserName(response.getDetails().getCapturedUserResponse().getEmailAddress());
        customer.setmTermsAndConditionVersion(response.getDetails().getCapturedUserResponse().getTermsAndConditionVersion());
        customer.setmPrivacyPolicyVersion(response.getDetails().getCapturedUserResponse().getPrivacyPolicyVersion());
        if (TextUtils.isEmpty(response.getDetails().getCapturedUserResponse().getSmsVerified())) {
            z = false;
        } else {
            z = true;
        }
        customer.setSmsVerified(z);
        if (TextUtils.isEmpty(response.getDetails().getCapturedUserResponse().getEmailVerified())) {
            z = false;
        } else {
            z = true;
        }
        customer.setEmailActivated(z);
        if (response.getDetails().getCapturedUserResponse().isDeactivateAccount()) {
            z2 = false;
        }
        customer.setIsActive(z2);
        return customer;
    }

    public AsyncToken register(final CustomerProfile customer, final AsyncListener<CustomerProfile> listener) {
        if (customer == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            MWCustomerData customerData = MWCustomerData.fromCustomer(customer);
            AsyncListener<MWCustomerSecurityAccountNativeResponse> requestListener = new AsyncListener<MWCustomerSecurityAccountNativeResponse>() {
                public void onResponse(MWCustomerSecurityAccountNativeResponse response, AsyncToken token, AsyncException exception) {
                    AsyncException localException = exception;
                    if (response != null && response.getStatusCode().equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                        CustomerProfile customerProfile = new CustomerProfile();
                        customerProfile.setMobileNumber(response.getDetails().getCapturedUserResponse().getPrimaryAddressResponse().getMobilePhone());
                        customerProfile.setEmailAddress(customer.getEmailAddress());
                        customerProfile.setPassword(customer.getPassword());
                        customerProfile.setFirstName(customer.getFirstName());
                        MWCustomerSecurityConnector.this.mAccessToken = response.getAccessToken();
                        MWCustomerSecurityConnector.this.mRefreshToken = response.getRefreshToken();
                        MWCustomerSecurityConnector.this.getVersioningService(customer, listener);
                    } else if (response != null && response.getStatusCode().equals(MWCustomerSecurityConnector.MIDDLEWARE_REGISTRATION_FAILURE_CODE)) {
                        MWCustomerSecurityJanrainAccountDetailsInvalidFieldsResponse errorResponse = response.getDetails().getInvalidFields();
                        if (errorResponse.getEmailAddress() != null && errorResponse.getMobile() != null) {
                            listener.onResponse(null, token, new AsyncException(MWCustomerSecurityConnector.f6683x563cf3e1));
                        } else if (errorResponse.getEmailAddress() != null) {
                            listener.onResponse(null, token, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_REGISTRATION_FAILURE_EMAIL_IN_USE_MESSAGE));
                        } else if (errorResponse.getMobile() != null) {
                            listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_REGISTRATION_FAILURE_PHONE_NUMBER_IN_USE_MESSAGE));
                        } else {
                            listener.onResponse(null, token, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                        }
                    } else if (response != null && response.getStatusCode().equals(MWCustomerSecurityConnector.MIDDLEWARE_REGISTRATION_EMAIL_INVALID_CODE)) {
                        listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_REGISTRATION_EMAIL_INVALID_FAILURE_MESSAGE));
                    } else if (response == null || !response.getStatusCode().equals(MWCustomerSecurityConnector.MIDDLEWARE_INTERNAL_SERVER_ERROR_CODE)) {
                        listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                    } else {
                        listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_SERVER_ERROR_MESSAGE));
                    }
                }
            };
            if (customer.isUsingSocialLogin()) {
                String socialAuthToken = customer.getSocialAuthenticationToken();
                getNetworkConnection().processRequest(new MWCustomerSecurityAccountSocialRequest(this, customerData, Configuration.getSharedInstance().getLocalizedStringForKey("connectors.MiddlewareCustomerSecurity.customerSecurity.locale"), socialAuthToken, true), requestListener);
            } else {
                getNetworkConnection().processRequest(new MWCustomerSecurityAccountNativeRequest(this, customer, customer.getmTermsAndConditionVersion(), customer.getmPrivacyPolicyVersion()), requestListener);
            }
        }
        return null;
    }

    public AsyncToken signOut(AsyncListener listener) {
        listener.onResponse(null, null, null);
        return null;
    }

    private CustomerProfile getCustomerFromResponse(MWCustomerSecurityAccountDetailsResponse response) {
        CustomerProfile customerProfile = new CustomerProfile();
        customerProfile.setFirstName(response.getFirstName());
        customerProfile.setLastName(response.getLastName());
        customerProfile.setBirthDate(response.getBirthdateCalendar());
        customerProfile.setGender(response.getGender());
        customerProfile.setEmailAddress(response.getEmailAddress());
        customerProfile.setMobileNumber(response.getMobilePhone());
        return customerProfile;
    }

    private CustomerProfile getCustomerFromResponse(MWCustomerSecurityAuthenticationDetailsResponse response) {
        CustomerProfile customerProfile = new CustomerProfile();
        customerProfile.setFirstName(response.getFirstName());
        customerProfile.setLastName(response.getLastName());
        customerProfile.setBirthDate(response.getBirthdateCalendar());
        customerProfile.setGender(response.getGender());
        customerProfile.setEmailAddress(response.getEmailAddress());
        customerProfile.setMobileNumber(response.getMobilePhone());
        return customerProfile;
    }

    public AsyncToken resendActivation(CustomerProfile customer, final AsyncListener<Void> listener) {
        AsyncListener<MWCustomerSecurityAuthenticationResponse> requestListener = new AsyncListener<MWCustomerSecurityAuthenticationResponse>() {
            public void onResponse(MWCustomerSecurityAuthenticationResponse response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    String statusCode = response.getStatusCode();
                    Object obj = -1;
                    switch (statusCode.hashCode()) {
                        case 49617:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_FIELDS_INVALID_CODE)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 52593:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_EMAIL_ALREADY_VERIFIED_ERROR_CODE)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1656410:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            listener.onResponse(null, null, null);
                            return;
                        case 1:
                            MCDLog.debug("Email is already verified");
                            listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_EMAIL_ALREADY_VERIFIED_FAILURE_MESSAGE));
                            return;
                        case 2:
                            MCDLog.debug("Email Address not valid/not recognized");
                            listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                            return;
                        default:
                            listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                            return;
                    }
                }
            }
        };
        String email = customer.getEmailAddress();
        if (TextUtils.isEmpty(email)) {
            email = "";
        }
        getNetworkConnection().processRequest(new MWCustomerSecurityAccountResendEmailRequest(this, email), requestListener);
        return null;
    }

    public AsyncToken resendActivationCode(String code, AsyncListener<Void> asyncListener) {
        return null;
    }

    public AsyncToken changePassword(String username, String oldPassword, String newPassword, String authorizationCode, final AsyncListener<Void> listener) {
        getNetworkConnection().processRequest(new MWCustomerSecurityAccountResetPasswordRequest(this, newPassword, authorizationCode), new AsyncListener<MWCustomerSecurityAccountResetPasswordResponse>() {
            public void onResponse(MWCustomerSecurityAccountResetPasswordResponse response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    String statusCode = response.getStatusCode();
                    Object obj = -1;
                    switch (statusCode.hashCode()) {
                        case 1656410:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            listener.onResponse(null, null, null);
                            return;
                        default:
                            listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_CHANGE_PASSWORD_EMAIL_EXPIRED_FAILURE_MESSAGE));
                            return;
                    }
                }
            }
        });
        return null;
    }

    public AsyncToken resetPassword(String username, final AsyncListener<Void> listener) {
        AsyncToken token = new AsyncToken("");
        getNetworkConnection().processRequest(new MWCustomerSecurityAccountForgotPasswordRequest(this, username), new AsyncListener<MWCustomerSecurityAccountForgotPasswordResponse>() {
            public void onResponse(MWCustomerSecurityAccountForgotPasswordResponse response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    String statusCode = response.getStatusCode();
                    Object obj = -1;
                    switch (statusCode.hashCode()) {
                        case 1656410:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1665058:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_ACCOUNT_DEACTIVATED_CODE)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            listener.onResponse(null, token, null);
                            return;
                        case 1:
                            listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_ACCOUNT_DEACTIVATED_ERROR_MESSAGE));
                            return;
                        default:
                            listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_RESET_PASSWORD_EMAIL_INVALID_FAILURE_MESSAGE));
                            return;
                    }
                }
            }
        });
        return token;
    }

    public AsyncToken addLoginMethod(CustomerProfile customer, AsyncListener<MWJSONResponse> asyncListener) {
        return null;
    }

    public AsyncToken registerExtSocialNetworkForced(CustomerProfile customer, AsyncListener<MWJSONResponse> asyncListener) {
        return null;
    }

    public AsyncToken getSocialNetworkAccessToken(int socialLoginId, String code, AsyncListener<MWWechatTokenResponse> asyncListener) {
        return null;
    }

    public AsyncToken resetPassword(String username, String emailAddress, String mobilePhone, AsyncListener<Void> asyncListener) {
        return null;
    }

    public AsyncToken updateProfile(final CustomerProfile customer, final AsyncListener<CustomerProfile> listener) {
        if (customer == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            getNetworkConnection().processRequest(new MWCustomerSecurityAccountUpdateRequest(this, customer, Configuration.getSharedInstance().getLocalizedStringForKey(CONFIG_MARKET_ID), this.mAccessToken), new AsyncListener<MWCustomerSecurityAccountUpdateResponse>() {
                public void onResponse(MWCustomerSecurityAccountUpdateResponse response, AsyncToken token, AsyncException exception) {
                    AsyncException localException = exception;
                    if (response != null) {
                        String statusCode = response.getStatusCode();
                        boolean z = true;
                        switch (statusCode.hashCode()) {
                            case 49586:
                                if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_TOKEN_UNKNOWN_CODE)) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 50702:
                                if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_CHANGE_PASSWORD_INCORRECT_CODE)) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 50826:
                                if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_REGISTRATION_FAILURE_CODE)) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 51542:
                                if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_ACCESS_TOKEN_INVALID_CODE)) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 51543:
                                if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_ACCESS_TOKEN_EXPIRED_CODE)) {
                                    z = true;
                                    break;
                                }
                                break;
                            case 1656410:
                                if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                                    z = false;
                                    break;
                                }
                                break;
                            case 1665033:
                                if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_CHANGE_PASSWORD_FAILED_CODE)) {
                                    z = true;
                                    break;
                                }
                                break;
                        }
                        switch (z) {
                            case false:
                                listener.onResponse(customer, token, null);
                                return;
                            case true:
                            case true:
                            case true:
                                if (MWCustomerSecurityConnector.this.mRefreshTokenFailed) {
                                    MWCustomerSecurityConnector.this.mRefreshTokenFailed = false;
                                    listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                                    return;
                                } else if (MWCustomerSecurityConnector.this.mCalledRefreshToken) {
                                    MWCustomerSecurityConnector.this.mCalledRefreshToken = false;
                                    MWCustomerSecurityConnector.this.getNewAccessAndRefreshTokensAccountUpdate(customer, listener);
                                    return;
                                } else {
                                    MWCustomerSecurityConnector.this.getRefreshTokenAccountUpdate(customer, listener);
                                    return;
                                }
                            case true:
                            case true:
                                listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_ACCOUNT_UPDATE_PASSWORD_ERROR_MESSAGE));
                                return;
                            case true:
                                listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_REGISTRATION_FAILURE_PHONE_NUMBER_IN_USE_MESSAGE));
                                return;
                            default:
                                MCDLog.debug("Default Error Response: " + response.toString());
                                listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                                return;
                        }
                    }
                }
            });
        }
        return null;
    }

    public AsyncToken setNotificationPreferences(CustomerProfile customer, NotificationPreferences preferences, AsyncListener<NotificationPreferences> asyncListener) {
        return null;
    }

    public AsyncToken trackNotification(CustomerProfile customer, String messageId, String deliveryID, int tagID, AsyncListener<Void> asyncListener) {
        return null;
    }

    public AsyncToken deregister(CustomerProfile customer, String cancellationReason, final AsyncListener<String> listener) {
        getNetworkConnection().processRequest(new MWCustomerSecurityAccountDeleteRequest(this, customer, this.mAccessToken), new AsyncListener<MWCustomerSecurityAuthenticationResponse>() {
            public void onResponse(MWCustomerSecurityAuthenticationResponse response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    String statusCode = response.getStatusCode();
                    Object obj = -1;
                    switch (statusCode.hashCode()) {
                        case 50578:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_DELETE_ACCOUNT_ERROR_CODE)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1656410:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            MCDLog.debug("Delete Account Success");
                            listener.onResponse(null, null, null);
                            return;
                        case 1:
                            MCDLog.debug("Deregister Error: Record not found.\n" + response);
                            listener.onResponse(null, null, new AsyncException("Record not found temp message"));
                            return;
                        default:
                            MCDLog.debug("Deregister Error: " + response);
                            listener.onResponse(null, null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                            return;
                    }
                }
            }
        });
        return null;
    }

    public AsyncToken getCustomerData(String username, AsyncListener<CustomerProfile> asyncListener) {
        return null;
    }

    public AsyncToken getSocialLoginCatalogUpdate(AsyncListener<List<SocialNetwork>> listener) {
        List<SocialNetwork> socialNetworks = new ArrayList();
        SocialNetwork facebook = new SocialNetwork();
        facebook.setSocialNetworkName(SocialNetwork.getNameForType(2));
        facebook.setType(2);
        facebook.setValid(true);
        socialNetworks.add(facebook);
        listener.onResponse(socialNetworks, null, null);
        return null;
    }

    public AsyncToken getCatalogUpdated(String username, String storeId, AsyncListener<Catalog> asyncListener) {
        return new AsyncToken("");
    }

    public AsyncToken addFavoriteLocations(List<Store> list, String username, AsyncListener<List<Store>> asyncListener) {
        return null;
    }

    public AsyncToken deleteFavoriteLocations(List<Integer> list, String username, AsyncListener<List<Store>> asyncListener) {
        return null;
    }

    public AsyncToken retrieveFavoriteStores(String username, AsyncListener<List<Store>> asyncListener) {
        return null;
    }

    public AsyncToken renameFavoriteLocations(List<Store> list, String username, AsyncListener<List<Store>> asyncListener) {
        return null;
    }

    public AsyncToken getPaymentTypeRegistrationURL(int paymentID, Boolean oneTimePayment, CustomerProfile customerProfile, AsyncListener<String> asyncListener) {
        return null;
    }

    public AsyncToken paymentTypePostRegistrationURL(int paymentID, Boolean oneTimePayment, int storeId, CustomerProfile customerProfile, AsyncListener<MWPaymentURLPostInfo> asyncListener) {
        return null;
    }

    public AsyncToken updatePayment(String userName, String paymentProviderData, boolean isPreferred, AsyncListener<CustomerProfile> asyncListener) {
        return null;
    }

    public AsyncToken getFavoriteProducts(String userName, AsyncListener<List<FavoriteItem>> asyncListener) {
        return null;
    }

    public AsyncToken addFavoriteProducts(String username, String favoriteName, List<OrderProduct> list, Boolean isProduct, AsyncListener<List<FavoriteItem>> asyncListener) {
        return null;
    }

    public AsyncToken deleteFavoriteProducts(String username, List<FavoriteItem> list, AsyncListener<List<FavoriteItem>> asyncListener) {
        return null;
    }

    public AsyncToken updatePayments(String userName, List<PaymentCard> list, AsyncListener<CustomerProfile> asyncListener) {
        return null;
    }

    public AsyncToken getPaymentData(String paymentProviderData, AsyncListener<PaymentCard> asyncListener) {
        return null;
    }

    public AsyncToken getRecentOrders(String username, Integer numRecents, AsyncListener<List<CustomerOrder>> asyncListener) {
        return null;
    }

    public String getGcmSenderId() {
        return null;
    }

    public AsyncToken associateDevice(String username, String deviceToken, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken getAddressBook(String username, AsyncListener<List<CustomerAddress>> asyncListener) {
        return null;
    }

    public AsyncToken getAddressElements(String username, AsyncListener<GetAddressElementsResult> asyncListener) {
        return null;
    }

    public AsyncToken getDefaultAddress(String username, AsyncListener<CustomerAddress> asyncListener) {
        return null;
    }

    public AsyncToken validateAddress(String username, CustomerAddress address, AsyncListener<AddressValidationResult> asyncListener) {
        return null;
    }

    public AsyncToken updateAddressBook(String username, List<CustomerAddress> list, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken addAddress(String username, CustomerAddress address, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken removeAddress(String username, CustomerAddress address, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public int getMaxItemQuantity() {
        return 0;
    }

    public AsyncToken sendRating(String username, int rating, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken sendRating(String username, String comment, int rating, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken sendSMSCode(final CustomerProfile customer, final AsyncListener<Boolean> listener) {
        AsyncToken token = new AsyncToken("");
        getNetworkConnection().processRequest(new MWCustomerSecurityAccountResendSMSRequest(this, this.mAccessToken, customer.getMobileNumber()), new AsyncListener<MWCustomerSecurityAccountVerificationSMSResponse>() {
            public void onResponse(MWCustomerSecurityAccountVerificationSMSResponse response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    String statusCode = response.getStatusCode();
                    boolean z = true;
                    switch (statusCode.hashCode()) {
                        case 49586:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_TOKEN_UNKNOWN_CODE)) {
                                z = true;
                                break;
                            }
                            break;
                        case 51543:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_ACCESS_TOKEN_EXPIRED_CODE)) {
                                z = true;
                                break;
                            }
                            break;
                        case 1656410:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                                z = false;
                                break;
                            }
                            break;
                    }
                    switch (z) {
                        case false:
                            listener.onResponse(Boolean.valueOf(true), token, null);
                            return;
                        case true:
                        case true:
                            if (!MWCustomerSecurityConnector.this.mRefreshTokenFailed) {
                                if (!MWCustomerSecurityConnector.this.mCalledRefreshToken) {
                                    MWCustomerSecurityConnector.this.getRefreshTokenAccountVerification(customer, null, listener);
                                    break;
                                }
                                MWCustomerSecurityConnector.this.mCalledRefreshToken = false;
                                MWCustomerSecurityConnector.this.getNewAccessAndRefreshTokensAccountVerification(customer, null, listener);
                                break;
                            }
                            MWCustomerSecurityConnector.this.mRefreshTokenFailed = false;
                            listener.onResponse(Boolean.valueOf(false), null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                            break;
                    }
                    listener.onResponse(Boolean.valueOf(false), null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                }
            }
        });
        return token;
    }

    public AsyncToken verifyAccount(final CustomerProfile customer, AccountVerificationType verificationType, final String verificationCode, final AsyncListener<Boolean> listener) {
        AsyncToken token = new AsyncToken("");
        this.mVerificationType = verificationType;
        AsyncListener<MWCustomerSecurityAccountVerificationSMSResponse> requestListener = new AsyncListener<MWCustomerSecurityAccountVerificationSMSResponse>() {
            public void onResponse(MWCustomerSecurityAccountVerificationSMSResponse response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    String statusCode = response.getStatusCode();
                    boolean z = true;
                    switch (statusCode.hashCode()) {
                        case 49586:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_TOKEN_UNKNOWN_CODE)) {
                                z = true;
                                break;
                            }
                            break;
                        case 51543:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_ACCESS_TOKEN_EXPIRED_CODE)) {
                                z = true;
                                break;
                            }
                            break;
                        case 1656410:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                                z = false;
                                break;
                            }
                            break;
                        case 1664068:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SMS_VERIFICATION_FAILED_CODE)) {
                                z = true;
                                break;
                            }
                            break;
                        case 1665059:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_EMAIL_VERIFICATION_LINK_EXPIRED_CODE)) {
                                z = true;
                                break;
                            }
                            break;
                    }
                    switch (z) {
                        case false:
                            listener.onResponse(Boolean.valueOf(true), token, null);
                            return;
                        case true:
                            listener.onResponse(Boolean.valueOf(false), null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_SMS_VERIFICATION_FAILURE_MESSAGE));
                            return;
                        case true:
                            listener.onResponse(Boolean.valueOf(false), null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_EMAIL_VERIFY_LINK_EXPIRED_MESSAGE));
                            return;
                        case true:
                        case true:
                            if (MWCustomerSecurityConnector.this.mRefreshTokenFailed) {
                                MWCustomerSecurityConnector.this.mRefreshTokenFailed = false;
                                listener.onResponse(Boolean.valueOf(false), null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                                return;
                            } else if (MWCustomerSecurityConnector.this.mCalledRefreshToken) {
                                MWCustomerSecurityConnector.this.mCalledRefreshToken = false;
                                MWCustomerSecurityConnector.this.getNewAccessAndRefreshTokensAccountVerification(customer, verificationCode, listener);
                                return;
                            } else {
                                MWCustomerSecurityConnector.this.getRefreshTokenAccountVerification(customer, verificationCode, listener);
                                return;
                            }
                        default:
                            listener.onResponse(Boolean.valueOf(false), null, new AsyncException(MWCustomerSecurityConnector.MIDDLEWARE_DOWN_ERROR_MESSAGE));
                            return;
                    }
                }
            }
        };
        switch (verificationType) {
            case SMS:
                getNetworkConnection().processRequest(new MWCustomerSecurityAccountVerificationSMSRequest(this, this.mAccessToken, verificationCode), requestListener);
                break;
            case EMAIL:
                getNetworkConnection().processRequest(new MWCustomerSecurityAccountVerificationEmailRequest(this, verificationCode), requestListener);
                break;
            default:
                listener.onResponse(Boolean.valueOf(true), null, null);
                break;
        }
        return token;
    }

    public AsyncToken updateTermsAndConditions(CustomerProfile profile, boolean isPrivacyPolicyAccepted, boolean isTermsOfUseAccepted, AsyncListener<CustomerProfile> asyncListener) {
        return null;
    }

    private void getRefreshTokenAccountUpdate(final CustomerProfile customer, final AsyncListener<CustomerProfile> accountUpdateListener) {
        AsyncListener<MWCustomerSecurityJanrainAuthenticationNativeResponse> listener = new AsyncListener<MWCustomerSecurityJanrainAuthenticationNativeResponse>() {
            public void onResponse(MWCustomerSecurityJanrainAuthenticationNativeResponse response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    String statusCode = response.getStatusCode();
                    Object obj = -1;
                    switch (statusCode.hashCode()) {
                        case 49586:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_TOKEN_UNKNOWN_CODE)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1656410:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1665035:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_REFRESH_TOKEN_FAILED_CODE)) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            MCDLog.debug("Refresh Token Success");
                            break;
                        case 1:
                            MCDLog.debug("Unknown Refresh Token");
                            break;
                        case 2:
                            MCDLog.debug("Unable To Refresh Token");
                            break;
                    }
                    MWCustomerSecurityConnector.this.mAccessToken = response.getAccessToken();
                    MWCustomerSecurityConnector.this.mRefreshToken = response.getRefreshToken();
                }
                MWCustomerSecurityConnector.this.updateProfile(customer, accountUpdateListener);
            }
        };
        this.mCalledRefreshToken = true;
        getNetworkConnection().processRequest(new MWCustomerSecurityRefreshTokenRequest(this, this.mRefreshToken), listener);
    }

    private void getRefreshTokenAccountVerification(final CustomerProfile customer, @Nullable final String verificationCode, final AsyncListener<Boolean> smsListener) {
        AsyncListener<MWCustomerSecurityJanrainAuthenticationNativeResponse> listener = new AsyncListener<MWCustomerSecurityJanrainAuthenticationNativeResponse>() {
            public void onResponse(MWCustomerSecurityJanrainAuthenticationNativeResponse response, AsyncToken token, AsyncException exception) {
                if (response != null) {
                    String statusCode = response.getStatusCode();
                    Object obj = -1;
                    switch (statusCode.hashCode()) {
                        case 49586:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_TOKEN_UNKNOWN_CODE)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1656410:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1665035:
                            if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_REFRESH_TOKEN_FAILED_CODE)) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            MCDLog.debug("Refresh Token Success");
                            break;
                        case 1:
                            MCDLog.debug("Unknown Refresh Token");
                            break;
                        case 2:
                            MCDLog.debug("Unable To Refresh Token");
                            break;
                    }
                    MWCustomerSecurityConnector.this.mAccessToken = response.getAccessToken();
                    MWCustomerSecurityConnector.this.mRefreshToken = response.getRefreshToken();
                }
                if (!TextUtils.isEmpty(verificationCode)) {
                    MWCustomerSecurityConnector.this.verifyAccount(customer, MWCustomerSecurityConnector.this.mVerificationType, verificationCode, smsListener);
                } else if (MWCustomerSecurityConnector.this.mVerificationType == AccountVerificationType.SMS) {
                    MWCustomerSecurityConnector.this.sendSMSCode(customer, smsListener);
                } else if (MWCustomerSecurityConnector.this.mVerificationType == AccountVerificationType.EMAIL) {
                    MCDLog.debug("Resend Email Verification");
                }
            }
        };
        this.mCalledRefreshToken = true;
        getNetworkConnection().processRequest(new MWCustomerSecurityRefreshTokenRequest(this, this.mRefreshToken), listener);
    }

    private void getNewAccessAndRefreshTokensAccountUpdate(final CustomerProfile customer, final AsyncListener<CustomerProfile> listener) {
        AsyncListener<MWCustomerSecurityJanrainAuthenticationNativeResponse> requestListener = new AsyncListener<MWCustomerSecurityJanrainAuthenticationNativeResponse>() {
            public void onResponse(MWCustomerSecurityJanrainAuthenticationNativeResponse response, AsyncToken token, AsyncException exception) {
                String statusCode = response.getStatusCode();
                Object obj = -1;
                switch (statusCode.hashCode()) {
                    case 1656410:
                        if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        MWCustomerSecurityConnector.this.mAccessToken = response.getAccessToken();
                        MWCustomerSecurityConnector.this.mRefreshToken = response.getRefreshToken();
                        break;
                }
                MWCustomerSecurityConnector.this.updateProfile(customer, listener);
            }
        };
        this.mRefreshTokenFailed = true;
        getNetworkConnection().processRequest(new MWCustomerSecurityAuthenticationRequest(this, customer.getUserName(), customer.getPassword()), requestListener);
    }

    private void getNewAccessAndRefreshTokensAccountVerification(final CustomerProfile customer, @Nullable final String verificationCode, final AsyncListener<Boolean> listener) {
        AsyncListener<MWCustomerSecurityJanrainAuthenticationNativeResponse> requestListener = new AsyncListener<MWCustomerSecurityJanrainAuthenticationNativeResponse>() {
            public void onResponse(MWCustomerSecurityJanrainAuthenticationNativeResponse response, AsyncToken token, AsyncException exception) {
                String statusCode = response.getStatusCode();
                Object obj = -1;
                switch (statusCode.hashCode()) {
                    case 1656410:
                        if (statusCode.equals(MWCustomerSecurityConnector.MIDDLEWARE_SUCCESS_CODE)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case null:
                        MWCustomerSecurityConnector.this.mAccessToken = response.getAccessToken();
                        MWCustomerSecurityConnector.this.mRefreshToken = response.getRefreshToken();
                        break;
                }
                if (TextUtils.isEmpty(verificationCode)) {
                    MWCustomerSecurityConnector.this.sendSMSCode(customer, listener);
                } else {
                    MWCustomerSecurityConnector.this.verifyAccount(customer, MWCustomerSecurityConnector.this.mVerificationType, verificationCode, listener);
                }
            }
        };
        this.mRefreshTokenFailed = true;
        getNetworkConnection().processRequest(new MWCustomerSecurityAuthenticationRequest(this, customer.getUserName(), customer.getPassword()), requestListener);
    }

    public AsyncToken setDefaultAddress(String username, CustomerAddress address, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public String getVersioningServiceUrl() {
        String versioningServiceUrl = (((String) Configuration.getSharedInstance().getValueForKey("connectors.MiddlewareCustomerSecurity.customerSecurity.versioningService.baseUrl")) + Configuration.getSharedInstance().getValueForKey("connectors.MiddlewareCustomerSecurity.customerSecurity.versioningService.configPath")) + Configuration.getSharedInstance().getValueForKey("connectors.MiddlewareCustomerSecurity.customerSecurity.versioningService.configQueries");
        return TextUtils.isEmpty(versioningServiceUrl) ? "" : versioningServiceUrl;
    }

    public AsyncToken registerCard(String url, String sessionId, String customerId, boolean isOneTimePayment, AsyncListener<PaymentCard> asyncListener) {
        return null;
    }
}
