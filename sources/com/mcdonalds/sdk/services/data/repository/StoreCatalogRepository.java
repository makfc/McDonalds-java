package com.mcdonalds.sdk.services.data.repository;

import android.content.Context;
import android.database.Cursor;
import android.util.SparseArray;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.mcdonalds.sdk.modules.models.StoreCatalog;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import com.mcdonalds.sdk.services.data.provider.Contract.StoreCatalogs;
import java.util.ArrayList;
import java.util.List;

public class StoreCatalogRepository extends DatabaseModelRepository {
    public static SparseArray<StoreCatalog> getMap(Context context) {
        Cursor cursor = context.getContentResolver().query(StoreCatalogs.CONTENT_URI, null, null, null, null);
        SparseArray<StoreCatalog> storeCatalogMap = null;
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                storeCatalogMap = new SparseArray();
                while (!cursor.isAfterLast()) {
                    StoreCatalog storeCatalog = new StoreCatalog();
                    storeCatalog.populateFromCursor(cursor);
                    storeCatalog.setFacilities(getFacilitiesByStoreId(context, storeCatalog.getStoreId()));
                    storeCatalog.setPromotions(getPromotionsByStoreId(context, storeCatalog.getStoreId()));
                    storeCatalogMap.put(storeCatalog.getStoreId(), storeCatalog);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        return storeCatalogMap;
    }

    private static List<Facility> getFacilitiesByStoreId(Context context, int storeId) {
        List<Facility> facilities = null;
        ForeignKey facilityForeignKey = new ForeignKey("store_id", Facility.TABLE_NAME, "id");
        Cursor cursor = DatabaseModelRepository.getRelatedModels(context, StoreCatalog.TABLE_NAME, null, new String[]{String.valueOf(storeId)}, facilityForeignKey);
        if (cursor != null) {
            cursor.moveToFirst();
            facilities = new ArrayList(cursor.getCount());
            while (!cursor.isAfterLast()) {
                Facility facility = new Facility();
                facility.populateFromCursor(cursor);
                facilities.add(facility);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return facilities;
    }

    private static List<Promotion> getPromotionsByStoreId(Context context, int storeId) {
        List<Promotion> promotions = null;
        ForeignKey promotionForeignKey = new ForeignKey("store_id", Promotion.TABLE_NAME, "id");
        Cursor cursor = DatabaseModelRepository.getRelatedModels(context, StoreCatalog.TABLE_NAME, null, new String[]{String.valueOf(storeId)}, promotionForeignKey);
        if (cursor != null) {
            cursor.moveToFirst();
            promotions = new ArrayList(cursor.getCount());
            while (!cursor.isAfterLast()) {
                Promotion promotion = new Promotion();
                promotion.populateFromCursor(cursor);
                promotions.add(promotion);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return promotions;
    }
}
