package com.mcdonalds.sdk.connectors;

import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.middleware.model.MWPaymentURLPostInfo;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWWechatTokenResponse;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.customer.CustomerProfile.AccountVerificationType;
import com.mcdonalds.sdk.modules.models.AddressValidationResult;
import com.mcdonalds.sdk.modules.models.AuthenticationParameters;
import com.mcdonalds.sdk.modules.models.Catalog;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.GetAddressElementsResult;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.List;

public interface CustomerConnector {
    AsyncToken addAddress(String str, CustomerAddress customerAddress, AsyncListener<Boolean> asyncListener);

    AsyncToken addFavoriteLocations(List<Store> list, String str, AsyncListener<List<Store>> asyncListener);

    AsyncToken addFavoriteProducts(String str, String str2, List<OrderProduct> list, Boolean bool, AsyncListener<List<FavoriteItem>> asyncListener);

    AsyncToken addLoginMethod(CustomerProfile customerProfile, AsyncListener<MWJSONResponse> asyncListener);

    AsyncToken associateDevice(String str, String str2, AsyncListener<Boolean> asyncListener);

    AsyncToken authenticate(AuthenticationParameters authenticationParameters, AsyncListener<CustomerProfile> asyncListener);

    AsyncToken changePassword(String str, String str2, String str3, String str4, AsyncListener<Void> asyncListener);

    AsyncToken deleteFavoriteLocations(List<Integer> list, String str, AsyncListener<List<Store>> asyncListener);

    AsyncToken deleteFavoriteProducts(String str, List<FavoriteItem> list, AsyncListener<List<FavoriteItem>> asyncListener);

    AsyncToken deregister(CustomerProfile customerProfile, String str, AsyncListener<String> asyncListener);

    AsyncToken getAddressBook(String str, AsyncListener<List<CustomerAddress>> asyncListener);

    AsyncToken getAddressElements(String str, AsyncListener<GetAddressElementsResult> asyncListener);

    AsyncToken getCatalogUpdated(String str, String str2, AsyncListener<Catalog> asyncListener);

    AsyncToken getCustomerData(String str, AsyncListener<CustomerProfile> asyncListener);

    AsyncToken getDefaultAddress(String str, AsyncListener<CustomerAddress> asyncListener);

    AsyncToken getFavoriteProducts(String str, AsyncListener<List<FavoriteItem>> asyncListener);

    String getGcmSenderId();

    int getMaxItemQuantity();

    AsyncToken getPaymentData(String str, AsyncListener<PaymentCard> asyncListener);

    AsyncToken getPaymentTypeRegistrationURL(int i, Boolean bool, CustomerProfile customerProfile, AsyncListener<String> asyncListener);

    AsyncToken getRecentOrders(String str, Integer num, AsyncListener<List<CustomerOrder>> asyncListener);

    AsyncToken getSocialLoginCatalogUpdate(AsyncListener<List<SocialNetwork>> asyncListener);

    AsyncToken getSocialNetworkAccessToken(int i, String str, AsyncListener<MWWechatTokenResponse> asyncListener);

    AsyncToken paymentTypePostRegistrationURL(int i, Boolean bool, int i2, CustomerProfile customerProfile, AsyncListener<MWPaymentURLPostInfo> asyncListener);

    AsyncToken register(CustomerProfile customerProfile, AsyncListener<CustomerProfile> asyncListener);

    AsyncToken registerCard(String str, String str2, String str3, boolean z, AsyncListener<PaymentCard> asyncListener);

    AsyncToken registerExtSocialNetworkForced(CustomerProfile customerProfile, AsyncListener<MWJSONResponse> asyncListener);

    AsyncToken removeAddress(String str, CustomerAddress customerAddress, AsyncListener<Boolean> asyncListener);

    AsyncToken renameFavoriteLocations(List<Store> list, String str, AsyncListener<List<Store>> asyncListener);

    AsyncToken resendActivation(CustomerProfile customerProfile, AsyncListener<Void> asyncListener);

    AsyncToken resendActivationCode(String str, AsyncListener<Void> asyncListener);

    AsyncToken resetPassword(String str, AsyncListener<Void> asyncListener);

    AsyncToken resetPassword(String str, String str2, String str3, AsyncListener<Void> asyncListener);

    AsyncToken retrieveFavoriteStores(String str, AsyncListener<List<Store>> asyncListener);

    AsyncToken sendRating(String str, int i, AsyncListener<Boolean> asyncListener);

    AsyncToken sendRating(String str, String str2, int i, AsyncListener<Boolean> asyncListener);

    AsyncToken sendSMSCode(CustomerProfile customerProfile, AsyncListener<Boolean> asyncListener);

    AsyncToken setDefaultAddress(String str, CustomerAddress customerAddress, AsyncListener<Boolean> asyncListener);

    AsyncToken setNotificationPreferences(CustomerProfile customerProfile, NotificationPreferences notificationPreferences, AsyncListener<NotificationPreferences> asyncListener);

    AsyncToken signOut(AsyncListener asyncListener);

    AsyncToken trackNotification(CustomerProfile customerProfile, String str, String str2, int i, AsyncListener<Void> asyncListener);

    AsyncToken updateAddressBook(String str, List<CustomerAddress> list, AsyncListener<Boolean> asyncListener);

    AsyncToken updatePayment(String str, String str2, boolean z, AsyncListener<CustomerProfile> asyncListener);

    AsyncToken updatePayments(String str, List<PaymentCard> list, AsyncListener<CustomerProfile> asyncListener);

    AsyncToken updateProfile(CustomerProfile customerProfile, AsyncListener<CustomerProfile> asyncListener);

    AsyncToken updateTermsAndConditions(CustomerProfile customerProfile, boolean z, boolean z2, AsyncListener<CustomerProfile> asyncListener);

    AsyncToken validateAddress(String str, CustomerAddress customerAddress, AsyncListener<AddressValidationResult> asyncListener);

    AsyncToken verifyAccount(CustomerProfile customerProfile, AccountVerificationType accountVerificationType, String str, AsyncListener<Boolean> asyncListener);
}
