package com.mcdonalds.sdk.connectors.middleware.helpers;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.mcdonalds.app.startup.SplashActivity;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.CustomerConnector;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.connectors.middleware.model.MWAccessData;
import com.mcdonalds.sdk.connectors.middleware.model.MWAddressBookEntry;
import com.mcdonalds.sdk.connectors.middleware.model.MWAddressElement;
import com.mcdonalds.sdk.connectors.middleware.model.MWAddressElementsResult;
import com.mcdonalds.sdk.connectors.middleware.model.MWAddressValidationResult;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerData;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerFavoriteData;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerFavoriteProductResponse;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerFavoriteStoreItem;
import com.mcdonalds.sdk.connectors.middleware.model.MWCustomerRecentOrderData;
import com.mcdonalds.sdk.connectors.middleware.model.MWGetFavoriteProductsResponse;
import com.mcdonalds.sdk.connectors.middleware.model.MWLoginInfo;
import com.mcdonalds.sdk.connectors.middleware.model.MWLoginToAdd;
import com.mcdonalds.sdk.connectors.middleware.model.MWNotificationPreferences;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentCardData;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentURLInfo;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentURLPostInfo;
import com.mcdonalds.sdk.connectors.middleware.model.MWProductView;
import com.mcdonalds.sdk.connectors.middleware.model.MWSocialLoginInfo;
import com.mcdonalds.sdk.connectors.middleware.request.AdobeTrackNotificationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWAddAdressRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWAddFavoriteLocationsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWAddFavoriteProductsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWAssociateDeviceRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWCustomerActivationCodeRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWCustomerChangePasswordRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWCustomerDeregisterRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWCustomerRegisterRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWCustomerResendActivationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWCustomerResetPasswordRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWDeleteFavoriteLocationsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWDeleteFavoriteProductsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetAddressBookRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetAddressElementsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetCustomerDataRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetFavoriteLocationsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetFavoriteProductsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetRecentOrdersRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWPaymentTypePostRegistrationUrlRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWPaymentTypeRegistrationURLRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWRegisterCardRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWRemoveAddressRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWRenameFavoriteLocationsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSendRatingRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSetDefaultAddressRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSetNotificationPreferencesRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSignInAndAuthenticateRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSignOutRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSocialDeregisterRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSocialForceRegisterRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSocialRegisterRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSocialSignInMergeRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSocialSignInRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSocialUpdateProfileRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWTrackNotificationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWUpdateAddressBookRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWUpdatePaymentRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWUpdateProfileRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWUpdateTermsAndConditionsRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWValidateAddressRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWWechatTokenRequest;
import com.mcdonalds.sdk.connectors.middleware.response.MWAddAddressResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWAddFavoriteProductsResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWCustomerDeregisterResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWCustomerRegisterResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWDeleteFavoriteProductsResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWFavoriteLocationsResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetAddressBookResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetAddressElementsResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetCustomerDataResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetRecentOrdersResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWPaymentTypeRegistrationPostUrlResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWPaymentTypeRegistrationURLResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWRegisterPaymentResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWRemoveAddressResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWSetDefaultAddressResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWSetNotificationPreferencesResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWSignInAndAuthenticateResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWSignInData;
import com.mcdonalds.sdk.connectors.middleware.response.MWUpdateAddressBookResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWUpdateProfileResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWUpdateTermsAndConditionsResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWValidateAddressResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWWechatTokenResponse;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.customer.CustomerProfile.AccountVerificationType;
import com.mcdonalds.sdk.modules.models.AddressValidationResult;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.Catalog;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.FeedBackType;
import com.mcdonalds.sdk.modules.models.GetAddressElementsResult;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.RequestProvider;
import com.mcdonalds.sdk.services.network.SessionManager;
import com.newrelic.agent.android.instrumentation.GsonInstrumentation;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class MWCustomerConnectorHelper implements CustomerConnector {
    private static final String ADOBE_TRACKING_CUSTOM_URL_KEY = "modules.customer.pushNotificationTrackingBaseURL";
    private static final String ASYNC_TOKEN_PREFIX = MWCustomerConnectorHelper.class.getSimpleName();
    protected MWConnectorShared mSharedData;

    public MWCustomerConnectorHelper(MWConnectorShared sharedData) {
        this.mSharedData = sharedData;
    }

    public AsyncToken authenticate(final AuthenticationParameters parameters, final AsyncListener<CustomerProfile> listener) {
        AsyncListener<MWSignInAndAuthenticateResponse> requestListener = new AsyncListener<MWSignInAndAuthenticateResponse>() {
            public void onResponse(MWSignInAndAuthenticateResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = exception;
                CustomerProfile customer = null;
                if (response == null || response.getResultCode() >= 0) {
                    if (localException == null) {
                        customer = MWCustomerConnectorHelper.this.buildCustomerProfile(response, parameters);
                        if (parameters.isAllowSocialLoginWithoutEmail()) {
                            customer.setUsingSocialLogin(true);
                            customer.setUsingSocialLoginWithoutEmail(parameters.isAllowSocialLoginWithoutEmail());
                            customer.setSocialAccountLoginRegistered(true);
                            customer.setVerificationType(AccountVerificationType.SMS);
                        }
                        customer.setSocialAccountLoginRegistered(true);
                        customer.setEmailActivated(true);
                    } else {
                        SessionManager.getInstance().setTokenAuthenticated(false);
                    }
                } else if (response.getResultCode() == -1012) {
                    localException = null;
                    customer = new CustomerProfile();
                    customer.setUserName(parameters.getUserName());
                    customer.setPassword(parameters.getPassword());
                    customer.setPasswordChangeRequired(true);
                } else if (response.getResultCode() == -1091) {
                    localException = null;
                    customer = new CustomerProfile();
                    customer.setUserName(parameters.getUserName());
                    customer.setPassword(parameters.getPassword());
                    customer.setFirstName(parameters.getFirstName());
                    customer.setLastName(parameters.getLastName());
                    if (parameters.isUsingSocialLogin()) {
                        customer.setSocialAccountLoginRegistered(true);
                    } else {
                        customer.setSocialAccountLoginRegistered(false);
                    }
                    customer.setEmailActivated(false);
                } else if (response.getResultCode() == SplashActivity.SOCIAL_LOGIN_LOGIN_FAIL) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                    customer = new CustomerProfile();
                    customer.setUsingSocialLogin(true);
                    customer.setUsingSocialLoginWithoutEmail(parameters.isAllowSocialLoginWithoutEmail());
                    customer.setSocialAccountLoginRegistered(true);
                } else if (response.getResultCode() == -2219) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                    customer = new CustomerProfile();
                    customer.setUserName(parameters.getUserName());
                    customer.setFirstName(parameters.getFirstName());
                    customer.setActivationOption(1);
                } else if (response.getResultCode() == -2213) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                    customer = new CustomerProfile();
                    customer.setUserName(parameters.getUserName());
                    customer.setFirstName(parameters.getFirstName());
                    customer.setActivationOption(2);
                } else if (response.getResultCode() == -2119) {
                    localException = null;
                    customer = new CustomerProfile();
                    customer.setUserName(parameters.getUserName());
                    customer.setPassword(parameters.getPassword());
                    customer.setUsingSocialLogin(true);
                    customer.setUsingSocialLoginWithoutEmail(parameters.isAllowSocialLoginWithoutEmail());
                    customer.setSocialAccountLoginRegistered(false);
                    customer.setFirstName(parameters.getFirstName());
                    customer.setLastName(parameters.getLastName());
                } else if (response.getResultCode() == -2112) {
                    localException = null;
                    customer = MWCustomerConnectorHelper.this.buildCustomerProfile(response, parameters);
                    customer.setEmailActivated(true);
                } else if (response.getResultCode() == -1032) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                    customer = new CustomerProfile();
                    customer.setUserName(parameters.getUserName());
                    customer.setPassword(parameters.getPassword());
                    customer.setUsingSocialLogin(true);
                    customer.setUsingSocialLoginWithoutEmail(parameters.isAllowSocialLoginWithoutEmail());
                    customer.setSocialAccountLoginRegistered(false);
                    customer.setFirstName(parameters.getFirstName());
                    customer.setLastName(parameters.getLastName());
                    customer.setEmailAddress(parameters.getEmailAddress());
                    customer.setSocialUserID(parameters.getSocialUserID());
                    customer.setSocialProvider(parameters.getSocialProvider());
                    customer.setSocialAuthenticationToken(parameters.getSocialAuthenticationToken());
                    customer.setSocialServiceAuthenticationID(parameters.getSocialServiceID());
                } else {
                    localException = MWException.fromErrorCode(response.getResultCode());
                }
                listener.onResponse(customer, token, localException);
            }
        };
        if (parameters.isUsingSocialLogin()) {
            this.mSharedData.getNetworkConnection().processRequest(new MWSocialSignInRequest(new MWSocialLoginInfo(parameters)), requestListener);
        } else {
            this.mSharedData.getNetworkConnection().processRequest(new MWSignInAndAuthenticateRequest(parameters.getUserName(), parameters.getPassword(), parameters.getNewPassword()), requestListener);
        }
        return null;
    }

    @NonNull
    private CustomerProfile buildCustomerProfile(MWSignInAndAuthenticateResponse response, AuthenticationParameters parameters) {
        MWCustomerData customerData = null;
        SessionManager manager = SessionManager.getInstance();
        if (!(response == null || response.getData() == null)) {
            MWAccessData accessData = ((MWSignInData) response.getData()).getAccessData();
            manager.setToken(accessData.token);
            manager.setTokenAuthenticated(true);
            manager.notifyTokenRefreshed();
            this.mSharedData.setAppParams(accessData.appParameter);
            customerData = ((MWSignInData) response.getData()).getCustomerData();
        }
        CustomerProfile customer = MWCustomerData.toCustomer(customerData);
        customer.setPassword(parameters.getNewPassword() != null ? parameters.getNewPassword() : parameters.getPassword());
        return customer;
    }

    public AsyncToken signOut(final AsyncListener listener) {
        this.mSharedData.setAppParams(null);
        this.mSharedData.getNetworkConnection().processRequest(new MWSignOutRequest(SessionManager.getInstance().getToken()), new AsyncListener() {
            public void onResponse(Object response, AsyncToken token, AsyncException exception) {
                MWCustomerConnectorHelper.this.mSharedData.setAppParams(null);
                listener.onResponse(response, token, exception);
            }
        });
        return null;
    }

    public AsyncToken register(final CustomerProfile customer, final AsyncListener<CustomerProfile> listener) {
        if (customer == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            MWCustomerData customerData = MWCustomerData.fromCustomer(customer);
            AsyncListener<MWCustomerRegisterResponse> requestListenerNormal = new AsyncListener<MWCustomerRegisterResponse>() {
                public void onResponse(MWCustomerRegisterResponse response, AsyncToken token, AsyncException exception) {
                    CustomerProfile profile;
                    AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                    if (localException != null || response == null || response.getData() == null) {
                        profile = customer;
                    } else {
                        profile = MWCustomerData.toCustomer((MWCustomerData) response.getData());
                    }
                    listener.onResponse(profile, token, localException);
                }
            };
            AsyncListener<MWSignInAndAuthenticateResponse> requestListenerSocial = new AsyncListener<MWSignInAndAuthenticateResponse>() {
                public void onResponse(MWSignInAndAuthenticateResponse response, AsyncToken token, AsyncException exception) {
                    CustomerProfile profile;
                    AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                    if (localException != null || response == null || response.getData() == null || ((MWSignInData) response.getData()).getCustomerData() == null) {
                        profile = customer;
                    } else {
                        profile = MWCustomerData.toCustomer(((MWSignInData) response.getData()).getCustomerData());
                    }
                    listener.onResponse(profile, token, localException);
                }
            };
            if (customer.isUsingSocialLogin()) {
                this.mSharedData.getNetworkConnection().processRequest(new MWSocialRegisterRequest(SessionManager.getInstance().getToken(), customerData, true), requestListenerSocial);
            } else {
                this.mSharedData.getNetworkConnection().processRequest(new MWCustomerRegisterRequest(SessionManager.getInstance().getToken(), customerData, true), requestListenerNormal);
            }
        }
        return null;
    }

    public AsyncToken resendActivationCode(String code, final AsyncListener<Void> listener) {
        final AsyncToken connectorToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#resendActivationCode");
        this.mSharedData.getNetworkConnection().processRequest(new MWCustomerActivationCodeRequest(SessionManager.getInstance().getToken(), code), new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    listener.onResponse(null, connectorToken, exception);
                    AsyncException.report(exception);
                } else if (response.getResultCode() < 0) {
                    listener.onResponse(null, connectorToken, MWException.fromErrorCode(response.getResultCode()));
                } else {
                    listener.onResponse(null, connectorToken, null);
                }
            }
        });
        return connectorToken;
    }

    public AsyncToken resendActivation(final CustomerProfile customer, final AsyncListener<Void> listener) {
        final AsyncToken connectorToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#resendActivation");
        if (customer == null) {
            listener.onResponse(null, null, new AsyncException("Invalid arguments: customer cannot be null"));
        } else {
            this.mSharedData.getNetworkConnection().processRequest(new MWCustomerResendActivationRequest(SessionManager.getInstance().getToken(), customer), new AsyncListener<MWJSONResponse>() {
                public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                    if (exception == null) {
                        listener.onResponse(null, connectorToken, null);
                    } else if ((exception instanceof MWException) && ((MWException) exception).getErrorCode() == -1091) {
                        customer.setEmailActivated(false);
                        listener.onResponse(null, connectorToken, null);
                    } else {
                        AsyncException.report(exception);
                    }
                }
            });
        }
        return connectorToken;
    }

    public AsyncToken changePassword(String username, String oldPassword, String newPassword, String authorizationCode, final AsyncListener<Void> listener) {
        if (username == null || oldPassword == null || newPassword == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
            return null;
        }
        final AsyncToken connectorToken = new AsyncToken("changePassword");
        this.mSharedData.getNetworkConnection().processRequest(new MWCustomerChangePasswordRequest(SessionManager.getInstance().getToken(), username, oldPassword, newPassword), new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                listener.onResponse(null, connectorToken, MWCustomerConnectorHelper.this.getException(exception, response));
            }
        });
        return connectorToken;
    }

    public AsyncToken resetPassword(String username, final AsyncListener<Void> listener) {
        if (username == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
            return null;
        }
        final AsyncToken connectorToken = new AsyncToken("resetPassword");
        this.mSharedData.getNetworkConnection().processRequest(new MWCustomerResetPasswordRequest(SessionManager.getInstance().getToken(), username), new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                listener.onResponse(null, connectorToken, MWCustomerConnectorHelper.this.getException(exception, response));
            }
        });
        return connectorToken;
    }

    public AsyncToken resetPassword(String username, String emailAddress, String mobilePhone, final AsyncListener<Void> listener) {
        if (username == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
            return null;
        }
        final AsyncToken connectorToken = new AsyncToken("resetPassword");
        this.mSharedData.getNetworkConnection().processRequest(new MWCustomerResetPasswordRequest(SessionManager.getInstance().getToken(), username, emailAddress, mobilePhone), new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                listener.onResponse(null, connectorToken, MWCustomerConnectorHelper.this.getException(exception, response));
            }
        });
        return connectorToken;
    }

    public AsyncToken registerExtSocialNetworkForced(CustomerProfile customer, final AsyncListener<MWJSONResponse> listener) {
        if (customer == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
            return null;
        }
        AsyncToken connectorToken = new AsyncToken("registerExtSocialNetworkForced");
        MWCustomerData customerData = MWCustomerData.fromCustomer(customer);
        AsyncListener<MWJSONResponse> requestListener = new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                Log.d("ConnectorHelper", "register response: " + response.toString());
                listener.onResponse(null, token, MWCustomerConnectorHelper.this.getException(exception, response));
            }
        };
        this.mSharedData.getNetworkConnection().processRequest(new MWSocialForceRegisterRequest(SessionManager.getInstance().getToken(), customerData, true), requestListener);
        return connectorToken;
    }

    public AsyncToken getSocialNetworkAccessToken(int socialLoginId, String code, final AsyncListener<MWWechatTokenResponse> listener) {
        final AsyncToken connectorToken = new AsyncToken("addLoginMethod");
        this.mSharedData.getNetworkConnection().processRequest(new MWWechatTokenRequest(SessionManager.getInstance().getToken(), socialLoginId, code), new AsyncListener<MWWechatTokenResponse>() {
            public void onResponse(MWWechatTokenResponse response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, connectorToken, exception);
            }
        });
        return null;
    }

    public AsyncToken addLoginMethod(CustomerProfile customer, final AsyncListener<MWJSONResponse> listener) {
        if (customer == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
            return null;
        }
        final AsyncToken connectorToken = new AsyncToken("addLoginMethod");
        MWLoginInfo mLoginInfo = new MWLoginInfo();
        mLoginInfo.loginType = 0;
        mLoginInfo.username = customer.getUserName();
        mLoginInfo.password = customer.getPassword();
        MWLoginToAdd mLoginToAdd = new MWLoginToAdd();
        mLoginToAdd.loginType = 1;
        mLoginToAdd.username = customer.getUserName();
        mLoginToAdd.accessToken = customer.getSocialAuthenticationToken();
        mLoginToAdd.internalID = customer.getSocialUserID();
        mLoginToAdd.socialNetworkId = customer.getSocialServiceAuthenticationID();
        this.mSharedData.getNetworkConnection().processRequest(new MWSocialSignInMergeRequest(SessionManager.getInstance().getToken(), mLoginInfo, mLoginToAdd), new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, connectorToken, exception);
            }
        });
        return connectorToken;
    }

    public AsyncToken updateProfile(final CustomerProfile customer, final AsyncListener<CustomerProfile> listener) {
        if (customer == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            final AsyncToken connectorToken = new AsyncToken("updateProfile");
            AsyncListener<MWUpdateProfileResponse> requestListener = new AsyncListener<MWUpdateProfileResponse>() {
                public void onResponse(MWUpdateProfileResponse response, AsyncToken token, AsyncException exception) {
                    CustomerProfile customerProfile = null;
                    AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                    if (localException == null) {
                        customerProfile = MWCustomerData.toCustomer((MWCustomerData) response.getData());
                        customerProfile.setPassword(customer.getPassword());
                    }
                    listener.onResponse(customerProfile, connectorToken, localException);
                }
            };
            if (customer.isUsingSocialLogin()) {
                this.mSharedData.getNetworkConnection().processRequest(new MWSocialUpdateProfileRequest(SessionManager.getInstance().getToken(), customer), requestListener);
            } else {
                this.mSharedData.getNetworkConnection().processRequest(new MWUpdateProfileRequest(SessionManager.getInstance().getToken(), customer), requestListener);
            }
        }
        return null;
    }

    public AsyncToken setNotificationPreferences(CustomerProfile customer, NotificationPreferences preferences, final AsyncListener<NotificationPreferences> listener) {
        if (customer == null || preferences == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            final AsyncToken connectorToken = new AsyncToken("setPreferencesMap");
            this.mSharedData.getNetworkConnection().processRequest(new MWSetNotificationPreferencesRequest(SessionManager.getInstance().getToken(), customer.getUserName(), MWNotificationPreferences.fromNotificationPreferences(preferences)), new AsyncListener<MWSetNotificationPreferencesResponse>() {
                public void onResponse(MWSetNotificationPreferencesResponse response, AsyncToken token, AsyncException exception) {
                    NotificationPreferences updatedNotification = null;
                    AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                    if (localException == null) {
                        updatedNotification = MWNotificationPreferences.toNotificationPreferences((MWNotificationPreferences) response.getData());
                    }
                    listener.onResponse(updatedNotification, connectorToken, localException);
                }
            });
        }
        return null;
    }

    public AsyncToken trackNotification(CustomerProfile customer, String messageId, String deliveryID, int tagID, AsyncListener<Void> listener) {
        AsyncToken connectorToken = null;
        if (customer == null || messageId == null || deliveryID == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            RequestProvider request;
            connectorToken = new AsyncToken("trackNotification");
            if (Configuration.getSharedInstance().hasKey(ADOBE_TRACKING_CUSTOM_URL_KEY)) {
                request = new AdobeTrackNotificationRequest(Configuration.getSharedInstance().getStringForKey(ADOBE_TRACKING_CUSTOM_URL_KEY), messageId, deliveryID, tagID);
            } else {
                request = new MWTrackNotificationRequest(SessionManager.getInstance().getToken(), messageId, deliveryID, tagID);
            }
            this.mSharedData.getNetworkConnection().processRequest(request, listener);
        }
        return connectorToken;
    }

    public AsyncToken deregister(CustomerProfile customer, String cancellationReason, final AsyncListener<String> listener) {
        AsyncToken connectorToken = null;
        if (customer == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            connectorToken = new AsyncToken("deregister");
            AsyncListener<MWCustomerDeregisterResponse> requestListener = new AsyncListener<MWCustomerDeregisterResponse>() {
                public void onResponse(MWCustomerDeregisterResponse response, AsyncToken token, AsyncException exception) {
                    if (exception != null) {
                        listener.onResponse(null, null, exception);
                        AsyncException.report(exception);
                    } else if (response.getResultCode() == 1) {
                        listener.onResponse("Pass", token, MWException.fromErrorCode(response.getResultCode()));
                    } else {
                        listener.onResponse("Fail", token, MWException.fromErrorCode(response.getResultCode()));
                    }
                }
            };
            if (customer.isUsingSocialLogin()) {
                this.mSharedData.getNetworkConnection().processRequest(new MWSocialDeregisterRequest(SessionManager.getInstance().getToken(), MWCustomerData.fromCustomer(customer), cancellationReason), requestListener);
            } else {
                this.mSharedData.getNetworkConnection().processRequest(new MWCustomerDeregisterRequest(SessionManager.getInstance().getToken(), String.valueOf(customer.getCustomerId()), customer.getUserName(), customer.getPassword(), cancellationReason), requestListener);
            }
        }
        return connectorToken;
    }

    public AsyncToken getCustomerData(String username, final AsyncListener<CustomerProfile> listener) {
        final AsyncToken requestToken = new AsyncToken("getCustomerData");
        this.mSharedData.getNetworkConnection().processRequest(new MWGetCustomerDataRequest(SessionManager.getInstance().getToken(), username), new AsyncListener<MWGetCustomerDataResponse>() {
            public void onResponse(MWGetCustomerDataResponse response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    listener.onResponse(MWCustomerData.toCustomer((MWCustomerData) response.getData()), requestToken, null);
                } else {
                    listener.onResponse(null, requestToken, exception);
                }
            }
        });
        return requestToken;
    }

    public AsyncToken getSocialLoginCatalogUpdate(AsyncListener<List<SocialNetwork>> listener) {
        AsyncToken connectorToken = new AsyncToken("getSocialLoginCatalogUpdate");
        this.mSharedData.getCatalogManager().updateSocialNetworkCatalog(connectorToken, listener);
        return connectorToken;
    }

    public AsyncToken getCatalogUpdated(String username, String storeId, AsyncListener<Catalog> listener) {
        AsyncToken connectorToken = new AsyncToken("getCatalogUpdated");
        this.mSharedData.getCatalogManager().updateCatalogs(username, storeId, connectorToken, listener);
        return connectorToken;
    }

    public AsyncToken addFavoriteLocations(List<Store> favoriteLocations, String username, final AsyncListener<List<Store>> listener) {
        final AsyncToken connectorToken = new AsyncToken("addFavoriteLocations");
        this.mSharedData.getNetworkConnection().processRequest(new MWAddFavoriteLocationsRequest(SessionManager.getInstance().getToken(), username, locationsArrayFromFavoriteLocations(favoriteLocations)), new AsyncListener<MWFavoriteLocationsResponse>() {
            public void onResponse(MWFavoriteLocationsResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    AsyncException.report(exception);
                    return;
                }
                AsyncException localException = null;
                List<Store> favoriteStores = null;
                if (response.getResultCode() < 0) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                }
                if (localException == null) {
                    if (response.getData() != null) {
                        favoriteStores = MWCustomerFavoriteStoreItem.toCustomerFavoriteStoreList((List) response.getData(), MWCustomerConnectorHelper.this.mSharedData.getContext());
                    } else {
                        localException = new AsyncException("Error adding store to Favorites");
                    }
                }
                listener.onResponse(favoriteStores, connectorToken, localException);
            }
        });
        return connectorToken;
    }

    public AsyncToken deleteFavoriteLocations(List<Integer> favoriteLocationIds, String username, final AsyncListener<List<Store>> listener) {
        final AsyncToken connectorToken = new AsyncToken("deleteFavoriteLocations");
        this.mSharedData.getNetworkConnection().processRequest(new MWDeleteFavoriteLocationsRequest(SessionManager.getInstance().getToken(), username, favoriteLocationIds), new AsyncListener<MWFavoriteLocationsResponse>() {
            public void onResponse(MWFavoriteLocationsResponse response, AsyncToken token, AsyncException exception) {
                List<Store> favoriteStores = null;
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                if (localException == null) {
                    favoriteStores = MWCustomerFavoriteStoreItem.toCustomerFavoriteStoreList((List) response.getData(), MWCustomerConnectorHelper.this.mSharedData.getContext());
                }
                listener.onResponse(favoriteStores, connectorToken, localException);
            }
        });
        return connectorToken;
    }

    public AsyncToken retrieveFavoriteStores(String username, final AsyncListener<List<Store>> listener) {
        final AsyncToken connectorToken = new AsyncToken("retrieveFavoriteStores");
        this.mSharedData.getNetworkConnection().processRequest(new MWGetFavoriteLocationsRequest(SessionManager.getInstance().getToken(), username), new AsyncListener<MWFavoriteLocationsResponse>() {
            public void onResponse(MWFavoriteLocationsResponse response, AsyncToken token, AsyncException exception) {
                List<Store> favoriteStores = null;
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                if (localException == null) {
                    favoriteStores = MWCustomerFavoriteStoreItem.toCustomerFavoriteStoreList((List) response.getData(), MWCustomerConnectorHelper.this.mSharedData.getContext());
                }
                MWCustomerConnectorHelper.this.mSharedData.cacheStores(favoriteStores);
                listener.onResponse(favoriteStores, connectorToken, localException);
            }
        });
        return connectorToken;
    }

    public AsyncToken renameFavoriteLocations(List<Store> favoriteLocations, String username, final AsyncListener<List<Store>> listener) {
        final AsyncToken connectorToken = new AsyncToken("renameFavoriteStores");
        this.mSharedData.getNetworkConnection().processRequest(new MWRenameFavoriteLocationsRequest(SessionManager.getInstance().getToken(), username, locationsArrayFromFavoriteLocations(favoriteLocations)), new AsyncListener<MWFavoriteLocationsResponse>() {
            public void onResponse(MWFavoriteLocationsResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    listener.onResponse(null, connectorToken, exception);
                } else if (response.getData() != null) {
                    listener.onResponse(MWCustomerFavoriteStoreItem.toCustomerFavoriteStoreList((List) response.getData(), MWCustomerConnectorHelper.this.mSharedData.getContext()), connectorToken, null);
                } else {
                    listener.onResponse(new ArrayList(), connectorToken, null);
                }
            }
        });
        return connectorToken;
    }

    public AsyncToken getPaymentTypeRegistrationURL(int paymentID, Boolean oneTimePayment, CustomerProfile customerProfile, final AsyncListener<String> listener) {
        if (this.mSharedData == null || customerProfile == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
            return null;
        }
        final AsyncToken connectorToken = new AsyncToken("paymentTypeRegistrationURL");
        this.mSharedData.getNetworkConnection().processRequest(new MWPaymentTypeRegistrationURLRequest(SessionManager.getInstance().getToken(), customerProfile.getUserName(), Long.valueOf(customerProfile.getCustomerId()), Integer.valueOf(paymentID), oneTimePayment), new AsyncListener<MWPaymentTypeRegistrationURLResponse>() {
            public void onResponse(MWPaymentTypeRegistrationURLResponse response, AsyncToken token, AsyncException exception) {
                String urlStr = null;
                AsyncException localException = exception;
                if (response != null) {
                    try {
                        urlStr = ((MWPaymentURLInfo) response.getData()).getURL();
                    } catch (NullPointerException e) {
                        localException = new AsyncException("No Payment Provider");
                    }
                }
                listener.onResponse(urlStr, connectorToken, localException);
            }
        });
        return connectorToken;
    }

    public AsyncToken paymentTypePostRegistrationURL(int paymentID, Boolean oneTimePayment, int storeId, CustomerProfile customerProfile, final AsyncListener<MWPaymentURLPostInfo> listener) {
        if (this.mSharedData == null || customerProfile == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
            return null;
        }
        final AsyncToken connectorToken = new AsyncToken("paymentTypeRegistrationURL");
        this.mSharedData.getNetworkConnection().processRequest(new MWPaymentTypePostRegistrationUrlRequest(SessionManager.getInstance().getToken(), storeId, Long.valueOf(customerProfile.getCustomerId()), Integer.valueOf(paymentID), oneTimePayment), new AsyncListener<MWPaymentTypeRegistrationPostUrlResponse>() {
            public void onResponse(MWPaymentTypeRegistrationPostUrlResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null || response == null) {
                    listener.onResponse(null, connectorToken, exception);
                    return;
                }
                listener.onResponse((MWPaymentURLPostInfo) response.getData(), connectorToken, exception);
            }
        });
        return connectorToken;
    }

    public AsyncToken updatePayment(String userName, String paymentProviderData, boolean isPreferred, final AsyncListener<CustomerProfile> listener) {
        if (this.mSharedData == null || userName == null || paymentProviderData == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
            return null;
        }
        try {
            MWRegisterPaymentResponse paymentData = getPaymentData(paymentProviderData);
            if (paymentData == null || paymentData.getResultCode() != 1) {
                listener.onResponse(null, null, getException(null, paymentData));
                return null;
            }
            final AsyncToken connectorToken = new AsyncToken("paymentTypeRegistrationURL");
            this.mSharedData.getNetworkConnection().processRequest(new MWUpdatePaymentRequest(SessionManager.getInstance().getToken(), userName, paymentData, isPreferred), new AsyncListener<MWGetCustomerDataResponse>() {
                public void onResponse(MWGetCustomerDataResponse response, AsyncToken token, AsyncException exception) {
                    AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                    MWCustomerData customerData = null;
                    if (localException == null) {
                        customerData = (MWCustomerData) response.getData();
                    }
                    listener.onResponse(MWCustomerData.toCustomer(customerData), connectorToken, localException);
                }
            });
            return connectorToken;
        } catch (JsonParseException e) {
            listener.onResponse(null, null, new AsyncException());
            return null;
        }
    }

    public AsyncToken getPaymentData(String paymentProviderData, AsyncListener<PaymentCard> listener) {
        AsyncToken connectorToken = new AsyncToken("getPaymentData");
        MWRegisterPaymentResponse paymentData = getPaymentData(paymentProviderData);
        if (paymentData == null || paymentData.getResultCode() != 1) {
            listener.onResponse(null, null, getException(null, paymentData));
        } else {
            PaymentCard addedCard = null;
            if (paymentData.getData() != null) {
                addedCard = ((MWPaymentCardData) paymentData.getData()).toPaymentCard();
            }
            listener.onResponse(addedCard, null, null);
        }
        return connectorToken;
    }

    private MWRegisterPaymentResponse getPaymentData(String paymentProviderData) {
        try {
            Gson gson = new Gson();
            Class cls = MWRegisterPaymentResponse.class;
            return (MWRegisterPaymentResponse) (!(gson instanceof Gson) ? gson.fromJson(paymentProviderData, cls) : GsonInstrumentation.fromJson(gson, paymentProviderData, cls));
        } catch (JsonSyntaxException e) {
            return null;
        } catch (JsonParseException e2) {
            return null;
        }
    }

    public AsyncToken getFavoriteProducts(String userName, final AsyncListener<List<FavoriteItem>> listener) {
        final AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#getFavoriteProducts");
        this.mSharedData.getNetworkConnection().processRequest(new MWGetFavoriteProductsRequest(SessionManager.getInstance().getToken(), userName), new AsyncListener<MWGetFavoriteProductsResponse>() {
            public void onResponse(MWGetFavoriteProductsResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                if (localException != null) {
                    listener.onResponse(null, asyncToken, localException);
                    return;
                }
                listener.onResponse(MWCustomerFavoriteProductResponse.toFavoriteItemList((List) response.getData()), asyncToken, null);
            }
        });
        return asyncToken;
    }

    public AsyncToken addFavoriteProducts(String username, String favoriteName, List<OrderProduct> products, Boolean isProduct, AsyncListener<List<FavoriteItem>> listener) {
        final AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#addFavoriteProducts");
        List<MWCustomerFavoriteData> ecpCustomerFavoriteDataList = new ArrayList();
        int ordersSize = products.size();
        List<MWProductView> ecpCustomerFavoriteOrderProducts = new ArrayList(ordersSize);
        for (int i = 0; i < ordersSize; i++) {
            OrderProduct orderProduct = (OrderProduct) products.get(i);
            MWProductView mwProductView = MWProductView.fromOrderProduct(orderProduct);
            mwProductView.setProductCode(orderProduct.getBaseProductCode());
            ecpCustomerFavoriteOrderProducts.add(mwProductView);
        }
        ecpCustomerFavoriteDataList.add(new MWCustomerFavoriteData(ecpCustomerFavoriteOrderProducts, isProduct.booleanValue(), favoriteName));
        final AsyncListener<List<FavoriteItem>> asyncListener = listener;
        this.mSharedData.getNetworkConnection().processRequest(new MWAddFavoriteProductsRequest(SessionManager.getInstance().getToken(), username, ecpCustomerFavoriteDataList), new AsyncListener<MWAddFavoriteProductsResponse>() {
            public void onResponse(MWAddFavoriteProductsResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                if (localException == null) {
                    asyncListener.onResponse(MWCustomerFavoriteProductResponse.toFavoriteItemList((List) response.getData()), asyncToken, null);
                    return;
                }
                asyncListener.onResponse(null, asyncToken, localException);
            }
        });
        return asyncToken;
    }

    public AsyncToken deleteFavoriteProducts(String username, List<FavoriteItem> orders, final AsyncListener<List<FavoriteItem>> listener) {
        AsyncToken asyncToken = null;
        if (this.mSharedData == null || orders == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#deleteFavoriteProducts");
            List<String> favoriteOrderProductIds = new ArrayList();
            int size = orders.size();
            for (int i = 0; i < size; i++) {
                FavoriteItem order = (FavoriteItem) orders.get(i);
                if (!(order == null || order.getFavoriteId() == null)) {
                    favoriteOrderProductIds.add(String.valueOf(order.getFavoriteId()));
                }
            }
            this.mSharedData.getNetworkConnection().processRequest(new MWDeleteFavoriteProductsRequest(SessionManager.getInstance().getToken(), username, favoriteOrderProductIds), new AsyncListener<MWDeleteFavoriteProductsResponse>() {
                public AsyncException mAsyncException;
                public boolean mSuccess;

                public void onResponse(MWDeleteFavoriteProductsResponse response, AsyncToken token, AsyncException exception) {
                    if (exception == null) {
                        List<FavoriteItem> favoriteItemList = new ArrayList();
                        if (response.getResultCode() == 1) {
                            this.mAsyncException = null;
                            this.mSuccess = true;
                            favoriteItemList.addAll(MWCustomerFavoriteProductResponse.toFavoriteItemList((List) response.getData()));
                        }
                        listener.onResponse(favoriteItemList, asyncToken, null);
                    }
                }
            });
        }
        return asyncToken;
    }

    public AsyncToken updatePayments(String userName, List<PaymentCard> paymentCards, final AsyncListener<CustomerProfile> listener) {
        if (this.mSharedData == null || userName == null || paymentCards == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
            return null;
        }
        final AsyncToken connectorToken = new AsyncToken("paymentTypeRegistrationURL");
        this.mSharedData.getNetworkConnection().processRequest(new MWUpdatePaymentRequest(SessionManager.getInstance().getToken(), userName, paymentCards), new AsyncListener<MWGetCustomerDataResponse>() {
            public void onResponse(MWGetCustomerDataResponse response, AsyncToken token, AsyncException exception) {
                MWCustomerData customerData = null;
                AsyncException localException = exception;
                if (response != null) {
                    if (response.getResultCode() != 1) {
                        localException = MWException.fromErrorCode(response.getResultCode());
                    } else {
                        customerData = (MWCustomerData) response.getData();
                    }
                }
                listener.onResponse(MWCustomerData.toCustomer(customerData), connectorToken, localException);
            }
        });
        return connectorToken;
    }

    public AsyncToken getRecentOrders(String username, Integer numRecents, final AsyncListener<List<CustomerOrder>> listener) {
        final AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#getRecentOrders");
        this.mSharedData.getNetworkConnection().processRequest(new MWGetRecentOrdersRequest(SessionManager.getInstance().getToken(), username, numRecents), new AsyncListener<MWGetRecentOrdersResponse>() {
            public void onResponse(MWGetRecentOrdersResponse response, AsyncToken token, AsyncException exception) {
                if (exception == null && response.getResultCode() == 1) {
                    listener.onResponse(MWCustomerRecentOrderData.toCustomerOrderList((List) response.getData()), asyncToken, null);
                }
            }
        });
        return asyncToken;
    }

    public String getGcmSenderId() {
        return this.mSharedData.getAppParameter(AppParameters.GCM_SENDER_ID);
    }

    public AsyncToken associateDevice(String username, String deviceToken, final AsyncListener<Boolean> listener) {
        final AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#associateDevice");
        this.mSharedData.getNetworkConnection().processRequest(new MWAssociateDeviceRequest(SessionManager.getInstance().getToken(), username, deviceToken), new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                if (exception != null) {
                    listener.onResponse(Boolean.valueOf(false), asyncToken, exception);
                } else if (response != null && response.getResultCode() == 1) {
                    listener.onResponse(Boolean.valueOf(true), asyncToken, null);
                }
            }
        });
        return asyncToken;
    }

    public int getMaxItemQuantity() {
        String maxItemQuantity = this.mSharedData.getAppParameter(AppParameters.MAX_QTTY_ONBASKET);
        return maxItemQuantity == null ? 255 : Integer.parseInt(maxItemQuantity);
    }

    public AsyncToken getAddressBook(String username, final AsyncListener<List<CustomerAddress>> listener) {
        final AsyncToken requestToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#getAddressBook");
        this.mSharedData.getNetworkConnection().processRequest(new MWGetAddressBookRequest(SessionManager.getInstance().getToken(), username), new AsyncListener<MWGetAddressBookResponse>() {
            public void onResponse(MWGetAddressBookResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                if (localException == null) {
                    listener.onResponse(MWAddressBookEntry.toCustomerAddressList((List) response.getData()), requestToken, null);
                } else {
                    listener.onResponse(null, requestToken, localException);
                }
            }
        });
        return requestToken;
    }

    public AsyncToken getAddressElements(String username, final AsyncListener<GetAddressElementsResult> listener) {
        final AsyncToken requestToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#getAddressElements");
        this.mSharedData.getNetworkConnection().processRequest(new MWGetAddressElementsRequest(SessionManager.getInstance().getToken(), username), new AsyncListener<MWGetAddressElementsResponse>() {
            public void onResponse(MWGetAddressElementsResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                if (localException == null) {
                    listener.onResponse(MWAddressElementsResult.toAddressElementsResult((MWAddressElementsResult) response.getData()), requestToken, null);
                    return;
                }
                listener.onResponse(null, requestToken, localException);
            }
        });
        return requestToken;
    }

    public AsyncToken getDefaultAddress(String username, final AsyncListener<CustomerAddress> listener) {
        AsyncToken requestToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#getDefaultAddress");
        getAddressBook(username, new AsyncListener<List<CustomerAddress>>() {
            public void onResponse(List<CustomerAddress> addresses, AsyncToken token, AsyncException exception) {
                CustomerAddress result = null;
                if (addresses != null) {
                    for (CustomerAddress address : addresses) {
                        if (address.isDefaultAddress()) {
                            result = address;
                            break;
                        }
                    }
                }
                listener.onResponse(result, null, null);
            }
        });
        return requestToken;
    }

    public AsyncToken updateAddressBook(String username, List<CustomerAddress> addresses, final AsyncListener<Boolean> listener) {
        final AsyncToken requestToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#updateAddressBook");
        this.mSharedData.getNetworkConnection().processRequest(new MWUpdateAddressBookRequest(SessionManager.getInstance().getToken(), username, MWAddressBookEntry.createListFromCustomerAddressList(addresses)), new AsyncListener<MWUpdateAddressBookResponse>() {
            public void onResponse(MWUpdateAddressBookResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                if (localException == null) {
                    listener.onResponse(Boolean.valueOf(true), requestToken, null);
                } else {
                    listener.onResponse(null, requestToken, localException);
                }
            }
        });
        return requestToken;
    }

    public AsyncToken validateAddress(String username, CustomerAddress address, final AsyncListener<AddressValidationResult> listener) {
        final AsyncToken requestToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#validateAddress");
        this.mSharedData.getNetworkConnection().processRequest(new MWValidateAddressRequest(SessionManager.getInstance().getToken(), username, MWAddressElement.createListFromCustomerAddress(address)), new AsyncListener<MWValidateAddressResponse>() {
            public void onResponse(MWValidateAddressResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                if (localException == null) {
                    listener.onResponse(MWAddressValidationResult.toValidateResult((MWAddressValidationResult) response.getData()), requestToken, null);
                    return;
                }
                listener.onResponse(null, requestToken, localException);
            }
        });
        return requestToken;
    }

    public AsyncToken addAddress(String username, CustomerAddress address, final AsyncListener<Boolean> listener) {
        final AsyncToken requestToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#addAddress");
        this.mSharedData.getNetworkConnection().processRequest(new MWAddAdressRequest(SessionManager.getInstance().getToken(), username, MWAddressBookEntry.fromCustomerAddress(address)), new AsyncListener<MWAddAddressResponse>() {
            public void onResponse(MWAddAddressResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                if (localException == null) {
                    listener.onResponse(Boolean.valueOf(true), requestToken, null);
                } else {
                    listener.onResponse(null, requestToken, localException);
                }
            }
        });
        return requestToken;
    }

    public AsyncToken removeAddress(String username, CustomerAddress address, final AsyncListener<Boolean> listener) {
        final AsyncToken requestToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#removeAddress");
        this.mSharedData.getNetworkConnection().processRequest(new MWRemoveAddressRequest(SessionManager.getInstance().getToken(), username, MWAddressBookEntry.fromCustomerAddress(address)), new AsyncListener<MWRemoveAddressResponse>() {
            public void onResponse(MWRemoveAddressResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                if (localException == null) {
                    listener.onResponse(Boolean.valueOf(true), requestToken, null);
                } else {
                    listener.onResponse(null, requestToken, localException);
                }
            }
        });
        return requestToken;
    }

    public AsyncToken sendRating(String username, int rating, AsyncListener<Boolean> listener) {
        return sendRating(username, "", rating, listener);
    }

    public AsyncToken sendRating(String username, String comment, int rating, final AsyncListener<Boolean> listener) {
        final AsyncToken requestToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#rating");
        int feedbackTypeId = 5;
        FeedBackType feedBackType = ((CustomerModule) ModuleManager.getModule(CustomerModule.NAME)).getFeedBackTypeForRating();
        if (feedBackType != null) {
            feedbackTypeId = feedBackType.getID();
        }
        this.mSharedData.getNetworkConnection().processRequest(new MWSendRatingRequest(SessionManager.getInstance().getToken(), username, comment, rating, feedbackTypeId), new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                listener.onResponse(Boolean.valueOf(localException == null), requestToken, localException);
            }
        });
        return requestToken;
    }

    public AsyncToken sendSMSCode(CustomerProfile customer, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken verifyAccount(CustomerProfile customer, AccountVerificationType verificationType, String verificationCode, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    private AsyncException getException(AsyncException exception, MWJSONResponse response) {
        AsyncException localException = exception;
        if (localException != null || response == null || response.getResultCode() == 1) {
            return localException;
        }
        return MWException.fromErrorCode(response.getResultCode());
    }

    public AsyncToken setDefaultAddress(String username, CustomerAddress address, final AsyncListener<Boolean> listener) {
        final AsyncToken requestToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#setDefaultAddress");
        this.mSharedData.getNetworkConnection().processRequest(new MWSetDefaultAddressRequest(SessionManager.getInstance().getToken(), username, MWAddressBookEntry.fromCustomerAddress(address)), new AsyncListener<MWSetDefaultAddressResponse>() {
            public void onResponse(MWSetDefaultAddressResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                if (localException == null) {
                    listener.onResponse(Boolean.valueOf(true), requestToken, null);
                } else {
                    listener.onResponse(null, requestToken, localException);
                }
            }
        });
        return requestToken;
    }

    /* Access modifiers changed, original: protected */
    public List<Map<String, Object>> locationsArrayFromFavoriteLocations(List<Store> favoriteLocations) {
        if (favoriteLocations == null) {
            return new ArrayList();
        }
        int size = favoriteLocations.size();
        List<Map<String, Object>> locations = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            Store store = (Store) favoriteLocations.get(i);
            Map<String, Object> storeMap = new HashMap();
            if (store.getStoreFavoriteId() != null) {
                storeMap.put("FavoriteLocationID", store.getStoreFavoriteId());
            }
            if (store.getStoreFavoriteName() != null) {
                storeMap.put("Name", store.getStoreFavoriteName());
            } else if (store.getAddress1() != null) {
                storeMap.put("Name", store.getAddress1());
            }
            storeMap.put("StoreNumber", Integer.valueOf(store.getStoreId()));
            locations.add(storeMap);
        }
        return locations;
    }

    public AsyncToken updateTermsAndConditions(final CustomerProfile profile, boolean isPrivacyPolicyAccepted, boolean isTermsOfUseAccepted, final AsyncListener<CustomerProfile> listener) {
        if (profile == null) {
            listener.onResponse(null, null, new AsyncException("Invalid Arguments"));
        } else {
            final AsyncToken connectorToken = new AsyncToken("updateTermsAndConditions");
            this.mSharedData.getNetworkConnection().processRequest(new MWUpdateTermsAndConditionsRequest(SessionManager.getInstance().getToken(), profile, Boolean.valueOf(isPrivacyPolicyAccepted), Boolean.valueOf(isTermsOfUseAccepted)), new AsyncListener<MWUpdateTermsAndConditionsResponse>() {
                public void onResponse(MWUpdateTermsAndConditionsResponse response, AsyncToken token, AsyncException exception) {
                    AsyncException localException = MWCustomerConnectorHelper.this.getException(exception, response);
                    if (localException == null) {
                        profile.setTermsAndConditionAcceptanceDate(MWCustomerData.toCustomer((MWCustomerData) response.getData()).getTermsAndConditionAcceptanceDate());
                    }
                    listener.onResponse(profile, connectorToken, localException);
                }
            });
        }
        return null;
    }

    public AsyncToken registerCard(String url, String sessionId, String customerId, boolean isOneTimePayment, final AsyncListener<PaymentCard> listener) {
        final AsyncToken requestToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#registerCard");
        this.mSharedData.getNetworkConnection().processRequest(new MWRegisterCardRequest(SessionManager.getInstance().getToken(), url, sessionId, customerId, isOneTimePayment), new AsyncListener<String>() {

            /* renamed from: com.mcdonalds.sdk.connectors.middleware.helpers.MWCustomerConnectorHelper$40$1 */
            class C39371 extends TypeToken<MWJSONResponse<MWPaymentCardData>> {
                C39371() {
                }
            }

            public void onResponse(String response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    String cardDataStr = null;
                    try {
                        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                        parser.setInput(new StringReader(response));
                        for (int eventType = parser.getEventType(); eventType != 1; eventType = parser.next()) {
                            if (eventType == 2) {
                                String id = parser.getAttributeValue(null, "id");
                                if (id != null && id.equals("hiddenResult")) {
                                    cardDataStr = parser.getAttributeValue(null, "value");
                                }
                            }
                        }
                    } catch (IOException | XmlPullParserException e) {
                    }
                    if (cardDataStr != null) {
                        Gson gson = new Gson();
                        Type type = new C39371().getType();
                        listener.onResponse(MWPaymentCardData.toPaymentCard((MWPaymentCardData) (!(gson instanceof Gson) ? gson.fromJson(cardDataStr, type) : GsonInstrumentation.fromJson(gson, cardDataStr, type)).getData()), requestToken, null);
                        return;
                    }
                    listener.onResponse(null, requestToken, null);
                    return;
                }
                listener.onResponse(null, requestToken, exception);
            }
        });
        return requestToken;
    }
}
