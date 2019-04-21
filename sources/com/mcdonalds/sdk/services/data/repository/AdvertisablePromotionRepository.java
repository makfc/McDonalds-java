package com.mcdonalds.sdk.services.data.repository;

import android.content.Context;
import com.mcdonalds.sdk.services.data.provider.Contract.AdvertisablePromotions;

public class AdvertisablePromotionRepository {
    public static void cleanAdvertisablePromotionsForStore(Context context, int storeId) {
        context.getContentResolver().delete(AdvertisablePromotions.CONTENT_URI, String.format("%s=?", new Object[]{"store_id"}), new String[]{String.valueOf(storeId)});
    }
}
