package com.mcdonalds.sdk.connectors.middleware.helpers;

import android.util.Log;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.OffersConnector;
import com.mcdonalds.sdk.connectors.middleware.MWException;
import com.mcdonalds.sdk.connectors.middleware.model.MWAdvertisable;
import com.mcdonalds.sdk.connectors.middleware.model.MWBarCodeData;
import com.mcdonalds.sdk.connectors.middleware.model.MWOffer;
import com.mcdonalds.sdk.connectors.middleware.model.MWOfferCategory;
import com.mcdonalds.sdk.connectors.middleware.model.MWOfferConditions.MWAndCondition;
import com.mcdonalds.sdk.connectors.middleware.model.MWOfferConditions.MWHourOfDayCondition;
import com.mcdonalds.sdk.connectors.middleware.model.MWOfferConditions.MWOrCondition;
import com.mcdonalds.sdk.connectors.middleware.model.MWOfferConditions.MWSaleAmountCondition;
import com.mcdonalds.sdk.connectors.middleware.model.MWProductSet;
import com.mcdonalds.sdk.connectors.middleware.request.MWArchiveOfferRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetCustomerOffersRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetIdentificationCodeRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetOfferCategoriesRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWGetStoreAdvertisableRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWOfferExpirationRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSelectForNextPurchaseRequest;
import com.mcdonalds.sdk.connectors.middleware.request.MWSelectToRedeemRequest;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetCustomerOffersResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetOfferCategoriesResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWGetStoreAdvertisableResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.connectors.middleware.response.MWSelectToRedeemResponse;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotion;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotionEntity;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Offer.AndCondition;
import com.mcdonalds.sdk.modules.models.Offer.SaleAmountCondition;
import com.mcdonalds.sdk.modules.models.OfferAction;
import com.mcdonalds.sdk.modules.models.OfferBarCodeData;
import com.mcdonalds.sdk.modules.models.OfferCategory;
import com.mcdonalds.sdk.modules.models.OfferProduct;
import com.mcdonalds.sdk.modules.models.OfferProductOption;
import com.mcdonalds.sdk.modules.models.OfferRedemptionType;
import com.mcdonalds.sdk.services.configuration.AppParameters;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.mcdonalds.sdk.services.network.SessionManager;
import com.mcdonalds.sdk.utils.DateUtils;
import com.mcdonalds.sdk.utils.ListUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MWOfferConnectorHelper implements OffersConnector {
    private static final String ASYNC_TOKEN_PREFIX = MWOfferConnectorHelper.class.getSimpleName();
    private static final long CACHE_EXP_INTERVAL = 86400;
    public static final String CUSTOMER_OFFERS_CACHE_KEY = "CUSTOMER_OFFERS_CACHE_KEY";
    private MWConnectorShared mConnectorShared;
    private List<Offer> mOffers;

    public MWOfferConnectorHelper(MWConnectorShared connectorShared) {
        this.mConnectorShared = connectorShared;
    }

    public AsyncToken getOfferCategories(final AsyncListener listener) {
        AsyncToken token = new AsyncToken(ASYNC_TOKEN_PREFIX + ".getOfferCategories");
        this.mConnectorShared.getNetworkConnection().processRequest(new MWGetOfferCategoriesRequest(this.mConnectorShared.getBaseConnector(), SessionManager.getInstance().getToken()), new AsyncListener<MWGetOfferCategoriesResponse>() {
            public void onResponse(MWGetOfferCategoriesResponse response, AsyncToken token, AsyncException exception) {
                List<OfferCategory> offerCategories = null;
                AsyncException localException = exception;
                if (!(response == null || response.getResultCode() == 1)) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                }
                if (!(localException != null || response == null || response.getData() == null)) {
                    offerCategories = new ArrayList();
                    Log.d(MWOfferConnectorHelper.ASYNC_TOKEN_PREFIX, response.toString());
                    List<MWOfferCategory> mwOfferCategories = (List) response.getData();
                    int size = mwOfferCategories.size();
                    for (int i = 0; i < size; i++) {
                        offerCategories.add(MWOfferCategory.toOfferCategory((MWOfferCategory) mwOfferCategories.get(i)));
                    }
                }
                listener.onResponse(offerCategories, token, localException);
            }
        });
        return token;
    }

    public AsyncToken getCustomerOffers(String username, Double latitude, Double longitude, List<String> storeIds, final AsyncListener<List<Offer>> listener) {
        AsyncToken token = new AsyncToken(ASYNC_TOKEN_PREFIX + ".getCustomerOffers");
        this.mConnectorShared.getNetworkConnection().processRequest(new MWGetCustomerOffersRequest(SessionManager.getInstance().getToken(), latitude, longitude, (List) storeIds, username), new AsyncListener<MWGetCustomerOffersResponse>() {
            public void onResponse(MWGetCustomerOffersResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = exception;
                if (!(response == null || response.getResultCode() == 1)) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                }
                if (localException == null) {
                    MWOfferConnectorHelper.this.mOffers = new ArrayList();
                    MWOfferConnectorHelper.this.processCustomerOffers(response, MWOfferConnectorHelper.this.mOffers);
                } else if (MWOfferConnectorHelper.this.mOffers != null) {
                    localException = null;
                }
                listener.onResponse(MWOfferConnectorHelper.this.mOffers, token, localException);
            }
        });
        return token;
    }

    private void processCustomerOffers(MWGetCustomerOffersResponse response, List<Offer> offers) {
        String imageBasePath = this.mConnectorShared.getAppParameter(AppParameters.STATIC_DATA_BASEURL);
        String offersOverrideHost = (String) Configuration.getSharedInstance().getValueForKey("modules.offers.imageHost");
        if (offersOverrideHost != null) {
            try {
                imageBasePath = imageBasePath.replace(new URL(imageBasePath).getHost(), offersOverrideHost);
                MCDLog.temp("JAC: new image basePath: " + imageBasePath);
            } catch (MalformedURLException e) {
            }
        } else {
            offersOverrideHost = (String) Configuration.getSharedInstance().getValueForKey("interface.offers.imageHost");
            if (offersOverrideHost != null) {
                try {
                    imageBasePath = imageBasePath.replace(new URL(imageBasePath).getHost(), offersOverrideHost);
                    MCDLog.temp("JAC: new image basePath: " + imageBasePath);
                } catch (MalformedURLException e2) {
                }
            }
        }
        if (response.getData() != null) {
            for (MWOffer ecpOffer : (List) response.getData()) {
                int discountTypeIndex;
                Offer offer = new Offer();
                String[] nameComponents = ecpOffer.name != null ? ecpOffer.name.split("\n") : new String[]{"", ""};
                String offerName = nameComponents[0];
                String offerSubtitle = nameComponents.length > 1 ? nameComponents[1] : "";
                offer.setName(offerName);
                offer.setSubtitle(offerSubtitle);
                offer.setArchived(Boolean.valueOf(ecpOffer.archived));
                String imageBaseName = imageBasePath + ecpOffer.imageBaseName;
                offer.setSmallImagePath(imageBaseName);
                offer.setLargeImagePath(imageBaseName);
                offer.setCurrentPunch(Integer.valueOf(ecpOffer.currentPunch));
                offer.setDuration(Integer.valueOf(ecpOffer.duration));
                offer.setExpirationChanged(Boolean.valueOf(ecpOffer.expirationChanged));
                offer.setExpired(Boolean.valueOf(ecpOffer.expired));
                offer.setLocalValidFrom(ecpOffer.localValidFrom);
                offer.setLocalValidThrough(ecpOffer.localValidThru);
                offer.setLongDescription(ecpOffer.longDescription);
                offer.setShortDescription(ecpOffer.shortDescription);
                offer.setSelected(Boolean.valueOf(ecpOffer.selected));
                offer.setOfferId(Integer.valueOf(ecpOffer.f6070id));
                offer.setOfferType(Integer.valueOf(ecpOffer.offerType));
                offer.setOrderDiscount(Double.valueOf(ecpOffer.orderDiscount));
                offer.setCurrentPunch(Integer.valueOf(ecpOffer.currentPunch));
                offer.setTotalPunch(Integer.valueOf(ecpOffer.totalPunch));
                boolean isDeliveryOffer = ecpOffer.conditions == null || ecpOffer.conditions.podConditions == null || ecpOffer.conditions.podConditions.contains("DELIVERY");
                offer.setIsDeliveryOffer(isDeliveryOffer);
                boolean isPickUpOffer = ecpOffer.conditions == null || ecpOffer.conditions.podConditions == null || ecpOffer.conditions.podConditions.contains("PICKUP");
                offer.setIsPickUpOffer(isPickUpOffer);
                boolean isNoPod = ecpOffer.conditions == null || ecpOffer.conditions.podConditions == null;
                offer.setIsNoPod(isNoPod);
                if (ecpOffer.orderDiscountType == null) {
                    discountTypeIndex = 0;
                } else {
                    discountTypeIndex = ecpOffer.orderDiscountType.intValue();
                }
                offer.setOrderDiscountType(OfferRedemptionType.values()[discountTypeIndex]);
                if (ecpOffer.productSets != null) {
                    List<OfferProduct> offerProducts = new ArrayList();
                    int size = ecpOffer.productSets.size();
                    for (int i = 0; i < size; i++) {
                        MWProductSet ecpProductSet = (MWProductSet) ecpOffer.productSets.get(i);
                        OfferProduct offerProduct = new OfferProduct();
                        OfferAction action = new OfferAction();
                        if (ecpProductSet.action != null) {
                            action.setDiscountType(Integer.valueOf(ecpProductSet.action.discountType));
                            action.setOfferRedemptionType(Integer.valueOf(ecpProductSet.action.type));
                            action.setPriceFromCode(ecpProductSet.action.priceFromCode);
                            action.setValue(Double.valueOf(ecpProductSet.action.value));
                            offerProduct.setAction(action);
                        }
                        List<OfferProductOption> products = new ArrayList();
                        if (ecpProductSet.products != null) {
                            int ecpSize = ecpProductSet.products.size();
                            for (int j = 0; j < ecpSize; j++) {
                                Integer productCode = (Integer) ecpProductSet.products.get(j);
                                OfferProductOption offerProductOption = new OfferProductOption();
                                offerProductOption.setProductCode(String.valueOf(productCode));
                                products.add(offerProductOption);
                            }
                        }
                        offerProduct.setProducts(products);
                        offerProduct.setAlias(ecpProductSet.alias);
                        offerProduct.setAnyProduct(Boolean.valueOf(ecpProductSet.anyProduct));
                        offerProduct.setPromoItem(Boolean.valueOf(ecpProductSet.promoItem));
                        offerProduct.setMaxUnitPrice(Double.valueOf(ecpProductSet.maxUnitPrice));
                        offerProduct.setMaxUnitPriceAlias(ecpProductSet.maxUnitPriceAlias);
                        offerProduct.setMinUnitPrice(Double.valueOf(ecpProductSet.minUnitPrice));
                        offerProduct.setMinUnitPriceAlias(ecpProductSet.minUnitPriceAlias);
                        offerProduct.setQuantity(Integer.valueOf(ecpProductSet.quantity));
                        offerProduct.setExpired(Boolean.valueOf(ecpOffer.expired));
                        offerProduct.setCodesFromAlias(ecpProductSet.codesFromAlias);
                        offerProducts.add(offerProduct);
                    }
                    offer.setProductSets(offerProducts);
                }
                offer.setRestaurants(ecpOffer.restaurants);
                ArrayList<AndCondition> list = new ArrayList();
                if (ecpOffer.conditions != null) {
                    List<MWAndCondition> andConditions = null;
                    if (ecpOffer.conditions.orConditions != null) {
                        MWOrCondition c = (MWOrCondition) ecpOffer.conditions.orConditions.get(0);
                        if (c != null) {
                            andConditions = c.andConditions;
                        }
                    } else if (ecpOffer.conditions.andConditions != null) {
                        andConditions = ecpOffer.conditions.andConditions;
                    }
                    if (andConditions != null) {
                        for (MWAndCondition c2 : andConditions) {
                            if (!(c2 == null || c2.dayOfWeekConditions == null || c2.hourOfDayConditions == null)) {
                                AndCondition toAdd = new AndCondition();
                                toAdd.setDayOfWeek(DateUtils.dayFromStringToInt((String) c2.dayOfWeekConditions.get(0)));
                                toAdd.setHourOfDayFrom(DateUtils.getTimeField(((MWHourOfDayCondition) c2.hourOfDayConditions.get(0)).from, 11));
                                toAdd.setMinuteFrom(DateUtils.getTimeField(((MWHourOfDayCondition) c2.hourOfDayConditions.get(0)).from, 12));
                                toAdd.setHourOfDayTo(DateUtils.getTimeField(((MWHourOfDayCondition) c2.hourOfDayConditions.get(0)).f6072to, 11));
                                toAdd.setMinuteTo(DateUtils.getTimeField(((MWHourOfDayCondition) c2.hourOfDayConditions.get(0)).f6072to, 12));
                                list.add(toAdd);
                            }
                        }
                    }
                }
                offer.setAndConditions(list);
                ArrayList<SaleAmountCondition> saleConds = new ArrayList();
                if (!(ecpOffer.conditions == null || ecpOffer.conditions.saleAmountConditions == null)) {
                    for (MWSaleAmountCondition c3 : ecpOffer.conditions.saleAmountConditions) {
                        saleConds.add(c3.toSaleAmountCondition());
                    }
                }
                offer.setSaleAmountCondition(saleConds);
                offers.add(offer);
            }
        }
    }

    public AsyncToken getAdvertisablePromotions(String userName, int storeId, final AsyncListener<List<AdvertisablePromotion>> listener) {
        AsyncToken token = new AsyncToken(ASYNC_TOKEN_PREFIX + ".getAdvertisablePromotions");
        this.mConnectorShared.getNetworkConnection().processRequest(new MWGetStoreAdvertisableRequest(SessionManager.getInstance().getToken(), storeId, userName), new AsyncListener<MWGetStoreAdvertisableResponse>() {
            public void onResponse(MWGetStoreAdvertisableResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = exception;
                if (!(response == null || response.getResultCode() == 1)) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                }
                listener.onResponse(MWOfferConnectorHelper.this.processAdvertisableResponse(response), token, localException);
            }
        });
        return token;
    }

    private List<AdvertisablePromotion> processAdvertisableResponse(MWGetStoreAdvertisableResponse response) {
        if (response == null || ListUtils.isEmpty((Collection) response.getData())) {
            return null;
        }
        List<AdvertisablePromotion> result = new ArrayList();
        for (MWAdvertisable advertisable : (List) response.getData()) {
            result.add(new AdvertisablePromotion(advertisable));
        }
        return result;
    }

    public AsyncToken getAdvertisablePromotionEntities(String userName, final int storeId, final AsyncListener<List<AdvertisablePromotionEntity>> listener) {
        AsyncToken token = new AsyncToken(ASYNC_TOKEN_PREFIX + ".getAdvertisablePromotions");
        this.mConnectorShared.getNetworkConnection().processRequest(new MWGetStoreAdvertisableRequest(SessionManager.getInstance().getToken(), storeId, userName), new AsyncListener<MWGetStoreAdvertisableResponse>() {
            public void onResponse(MWGetStoreAdvertisableResponse response, AsyncToken token, AsyncException exception) {
                AsyncException localException = exception;
                if (!(response == null || response.getResultCode() == 1)) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                }
                listener.onResponse(MWOfferConnectorHelper.this.processAdvertisableEntitiesResponse(response, storeId), token, localException);
            }
        });
        return token;
    }

    private List<AdvertisablePromotionEntity> processAdvertisableEntitiesResponse(MWGetStoreAdvertisableResponse response, int storeId) {
        if (response == null || ListUtils.isEmpty((Collection) response.getData())) {
            return null;
        }
        List<AdvertisablePromotionEntity> result = new ArrayList();
        for (MWAdvertisable advertisable : (List) response.getData()) {
            result.addAll(advertisable.toAdvertisablePromotionEntity(storeId));
        }
        return result;
    }

    public AsyncToken selectOffersForPurchase(String username, Integer offerId, final AsyncListener listener) {
        AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "selectOffersForPurchase");
        this.mConnectorShared.getNetworkConnection().processRequest(new MWSelectForNextPurchaseRequest(this.mConnectorShared.getBaseConnector(), SessionManager.getInstance().getToken(), offerId, username), new AsyncListener() {
            public void onResponse(Object response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, token, exception);
            }
        });
        return null;
    }

    public AsyncToken selectToRedeem(String username, List<String> offerIds, Integer storeId, final AsyncListener<OfferBarCodeData> listener) {
        this.mConnectorShared.getNetworkConnection().processRequest(new MWSelectToRedeemRequest(this.mConnectorShared.getBaseConnector(), SessionManager.getInstance().getToken(), username, offerIds, storeId), new AsyncListener<MWSelectToRedeemResponse>() {
            public void onResponse(MWSelectToRedeemResponse response, AsyncToken token, AsyncException exception) {
                OfferBarCodeData offerBarCodeData = null;
                AsyncException localException = exception;
                if (!(response == null || response.getResultCode() == 1)) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                }
                if (localException == null && response != null) {
                    MWSelectToRedeemResponse<MWBarCodeData> ecpBarCodeData = response;
                    offerBarCodeData = new OfferBarCodeData();
                    offerBarCodeData.setQrCode(((MWBarCodeData) ecpBarCodeData.getData()).qrCode);
                    offerBarCodeData.setBarCodeContent(((MWBarCodeData) ecpBarCodeData.getData()).barCodeContent);
                    offerBarCodeData.setRandomCode(((MWBarCodeData) ecpBarCodeData.getData()).randomCode);
                }
                listener.onResponse(offerBarCodeData, token, localException);
            }
        });
        return null;
    }

    public AsyncToken getCustomerIdentificationCode(String username, Integer storeId, final AsyncListener<OfferBarCodeData> listener) {
        AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "selectToRedeem");
        this.mConnectorShared.getNetworkConnection().processRequest(new MWGetIdentificationCodeRequest(this.mConnectorShared.getBaseConnector(), SessionManager.getInstance().getToken(), username, storeId.toString()), new AsyncListener<MWSelectToRedeemResponse>() {
            public void onResponse(MWSelectToRedeemResponse response, AsyncToken token, AsyncException exception) {
                OfferBarCodeData offerBarCodeData = null;
                AsyncException localException = exception;
                if (!(response == null || response.getResultCode() == 1)) {
                    localException = MWException.fromErrorCode(response.getResultCode());
                }
                if (localException == null && response != null) {
                    MWSelectToRedeemResponse<MWBarCodeData> ecpBarCodeData = response;
                    offerBarCodeData = new OfferBarCodeData();
                    offerBarCodeData.setQrCode(((MWBarCodeData) ecpBarCodeData.getData()).qrCode);
                    offerBarCodeData.setBarCodeContent(((MWBarCodeData) ecpBarCodeData.getData()).barCodeContent);
                    offerBarCodeData.setRandomCode(((MWBarCodeData) ecpBarCodeData.getData()).randomCode);
                }
                listener.onResponse(offerBarCodeData, token, localException);
            }
        });
        return asyncToken;
    }

    public AsyncToken archiveOffer(Integer offerId, String username, final AsyncListener<Boolean> listener) {
        AsyncToken asyncToken = new AsyncToken(ASYNC_TOKEN_PREFIX + "#archiveOffer");
        this.mConnectorShared.getNetworkConnection().processRequest(new MWArchiveOfferRequest(SessionManager.getInstance().getToken(), username, offerId), new AsyncListener<MWJSONResponse>() {
            public void onResponse(MWJSONResponse response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    Log.d(MWOfferConnectorHelper.ASYNC_TOKEN_PREFIX, response.toString());
                    if (response.getResultCode() == 1) {
                        listener.onResponse(Boolean.valueOf(true), token, null);
                    }
                }
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

    public AsyncToken setOfferExpiration(Integer offerId, Date expStartDate, Date expEndDate, final AsyncListener listener) {
        this.mConnectorShared.getNetworkConnection().processRequest(new MWOfferExpirationRequest(SessionManager.getInstance().getToken(), offerId, expStartDate, expEndDate), new AsyncListener() {
            public void onResponse(Object response, AsyncToken token, AsyncException exception) {
                listener.onResponse(response, token, exception);
            }
        });
        return null;
    }
}
