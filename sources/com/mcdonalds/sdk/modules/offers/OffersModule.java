package com.mcdonalds.sdk.modules.offers;

import android.content.Context;
import android.support.annotation.NonNull;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.OffersConnector;
import com.mcdonalds.sdk.modules.BaseModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotion;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotionEntity;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.OfferBarCodeData;
import com.mcdonalds.sdk.modules.models.OfferCategory;
import com.mcdonalds.sdk.modules.models.OfferProduct;
import com.mcdonalds.sdk.modules.models.OfferProductOption;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OffersModule extends BaseModule {
    public static final String CONNECTOR_KEY = "connector";
    public static final String NAME = "offers";
    public static final String TOKEN_PREFIX = OffersModule.class.getSimpleName();
    private final String ADVERTISABLE_PROMOTIONS_ENABLED;
    private final String mConnectorImpl;

    @Deprecated
    public OffersModule(Context context) {
        this();
    }

    public OffersModule() {
        this.ADVERTISABLE_PROMOTIONS_ENABLED = "modules.ordering.advertisablePromotionsEnabled";
        this.mConnectorImpl = (String) Configuration.getSharedInstance().getValueForKey("modules.offers.connector");
    }

    private Boolean shouldShowOffers() {
        boolean z = Configuration.getSharedInstance().getBooleanForKey("interface.supportedModules.offers") || Configuration.getSharedInstance().getBooleanForKey("interface.supportedModules.all");
        return Boolean.valueOf(z);
    }

    public AsyncToken getOfferCategories(final AsyncListener<List<OfferCategory>> listener) {
        final AsyncToken asyncToken = new AsyncToken(TOKEN_PREFIX + "getOfferCategories");
        ((OffersConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getOfferCategories(new AsyncListener<List<OfferCategory>>() {
            public void onResponse(List<OfferCategory> response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, asyncToken, exception);
            }
        });
        return asyncToken;
    }

    public AsyncToken getCustomerOffers(String username, Double latitude, Double longitude, List<String> storeIds, @NonNull final AsyncListener<List<Offer>> listener) {
        final AsyncToken asyncToken = new AsyncToken(TOKEN_PREFIX + "getCustomerOffers");
        ((OffersConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getCustomerOffers(username, latitude, longitude, storeIds, new AsyncListener<List<Offer>>() {
            public void onResponse(List<Offer> response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, asyncToken, exception);
            }
        });
        return asyncToken;
    }

    @Deprecated
    public AsyncToken getAdvertisablePromotions(String userName, int storeId, final AsyncListener<List<AdvertisablePromotion>> listener) {
        AsyncToken moduleToken = new AsyncToken("getAdvertisablePromotions");
        if (Configuration.getSharedInstance().getBooleanForKey("modules.ordering.advertisablePromotionsEnabled", false)) {
            ((OffersConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getAdvertisablePromotions(userName, storeId, new AsyncListener<List<AdvertisablePromotion>>() {
                public void onResponse(List<AdvertisablePromotion> response, AsyncToken token, AsyncException exception) {
                    listener.onResponse(response, token, exception);
                }
            });
        } else {
            listener.onResponse(null, null, new AsyncException("No config param: \"modules.ordering.advertisablePromotionsEnabled\""));
        }
        return moduleToken;
    }

    public AsyncToken getAdvertisablePromotionEntities(String username, int storeId, final AsyncListener<List<AdvertisablePromotionEntity>> listener) {
        AsyncToken moduleToken = new AsyncToken("getAdvertisablePromotions");
        if (Configuration.getSharedInstance().getBooleanForKey("modules.ordering.advertisablePromotionsEnabled", false)) {
            ((OffersConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getAdvertisablePromotionEntities(username, storeId, new AsyncListener<List<AdvertisablePromotionEntity>>() {
                public void onResponse(List<AdvertisablePromotionEntity> response, AsyncToken token, AsyncException exception) {
                    listener.onResponse(response, token, exception);
                }
            });
        } else {
            listener.onResponse(null, null, new AsyncException("No config param: \"modules.ordering.advertisablePromotionsEnabled\""));
        }
        return moduleToken;
    }

    public AsyncToken getProductOptions(OfferProduct offerProduct, @NonNull AsyncListener<List<OfferProductOption>> asyncListener) {
        return null;
    }

    public AsyncToken selectOffersForPurchase(String username, Integer offerId, @NonNull final AsyncListener listener) {
        AsyncToken asyncToken = new AsyncToken(TOKEN_PREFIX + "selectOffersForPurchase");
        ((OffersConnector) ConnectorManager.getConnector(this.mConnectorImpl)).selectOffersForPurchase(username, offerId, new AsyncListener() {
            public void onResponse(Object response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, token, exception);
            }
        });
        return asyncToken;
    }

    public AsyncToken selectToRedeem(CustomerProfile customerProfile, List<Offer> offers, Integer storeId, @NonNull final AsyncListener<OfferBarCodeData> listener) {
        final AsyncToken asyncToken = new AsyncToken(TOKEN_PREFIX + "selectToRedeem");
        OffersConnector connector = (OffersConnector) ConnectorManager.getConnector(this.mConnectorImpl);
        List<String> offerIds = new ArrayList();
        for (Offer offer : offers) {
            offerIds.add(String.valueOf(offer.getOfferId()));
        }
        connector.selectToRedeem(customerProfile.getUserName(), offerIds, storeId, new AsyncListener<OfferBarCodeData>() {
            public void onResponse(OfferBarCodeData response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, asyncToken, exception);
            }
        });
        return asyncToken;
    }

    public AsyncToken getCustomerIdentificationCode(CustomerProfile customerProfile, Integer storeId, @NonNull final AsyncListener<OfferBarCodeData> listener) {
        final AsyncToken asyncToken = new AsyncToken(TOKEN_PREFIX + "getCustomerIdentificationCode");
        ((OffersConnector) ConnectorManager.getConnector(this.mConnectorImpl)).getCustomerIdentificationCode(customerProfile.getUserName(), storeId, new AsyncListener<OfferBarCodeData>() {
            public void onResponse(OfferBarCodeData response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, asyncToken, exception);
            }
        });
        return asyncToken;
    }

    public AsyncToken archiveOffer(Offer offer, CustomerProfile customerProfile, @NonNull final AsyncListener<Boolean> listener) {
        final AsyncToken asyncToken = new AsyncToken(TOKEN_PREFIX + "#archiveOffer");
        ((OffersConnector) ConnectorManager.getConnector(this.mConnectorImpl)).archiveOffer(offer.getOfferId(), customerProfile.getUserName(), new AsyncListener<Boolean>() {
            public void onResponse(Boolean response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, asyncToken, exception);
            }
        });
        return asyncToken;
    }

    public AsyncToken unarchiveOffer(String offerId, String username, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken subscribe(String username, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken unsubscribe(String username, AsyncListener<Boolean> asyncListener) {
        return null;
    }

    public AsyncToken setOfferExpiration(Integer offerId, Date expStartDate, Date expEndDate, @NonNull final AsyncListener listener) {
        AsyncToken asyncToken = new AsyncToken(TOKEN_PREFIX + "setOfferExpiration");
        ((OffersConnector) ConnectorManager.getConnector(this.mConnectorImpl)).setOfferExpiration(offerId, expStartDate, expEndDate, new AsyncListener() {
            public void onResponse(Object response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, token, exception);
            }
        });
        return asyncToken;
    }
}
