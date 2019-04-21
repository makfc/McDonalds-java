package com.mcdonalds.sdk.connectors;

import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotion;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotionEntity;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.OfferBarCodeData;
import com.mcdonalds.sdk.modules.models.OfferCategory;
import java.util.Date;
import java.util.List;

public interface OffersConnector {
    AsyncToken archiveOffer(Integer num, String str, AsyncListener<Boolean> asyncListener);

    AsyncToken getAdvertisablePromotionEntities(String str, int i, AsyncListener<List<AdvertisablePromotionEntity>> asyncListener);

    AsyncToken getAdvertisablePromotions(String str, int i, AsyncListener<List<AdvertisablePromotion>> asyncListener);

    AsyncToken getCustomerIdentificationCode(String str, Integer num, AsyncListener<OfferBarCodeData> asyncListener);

    AsyncToken getCustomerOffers(String str, Double d, Double d2, List<String> list, AsyncListener<List<Offer>> asyncListener);

    AsyncToken getOfferCategories(AsyncListener<List<OfferCategory>> asyncListener);

    AsyncToken selectOffersForPurchase(String str, Integer num, AsyncListener asyncListener);

    AsyncToken selectToRedeem(String str, List<String> list, Integer num, AsyncListener<OfferBarCodeData> asyncListener);

    AsyncToken setOfferExpiration(Integer num, Date date, Date date2, AsyncListener asyncListener);

    AsyncToken subscribe(String str, AsyncListener<Boolean> asyncListener);

    AsyncToken unarchiveOffer(String str, String str2, AsyncListener<Boolean> asyncListener);

    AsyncToken unsubscribe(String str, AsyncListener<Boolean> asyncListener);
}
