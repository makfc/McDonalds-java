package com.mcdonalds.sdk.services.data.repository;

import android.content.Context;
import android.database.Cursor;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.modules.models.StoreProductCategory;
import com.mcdonalds.sdk.services.data.provider.Contract.StoreProductCat;
import java.util.ArrayList;
import java.util.List;

public class StoreProductCategoryRepository {
    public static List<StoreProductCategory> getCategoryByStoreProduct(Context context, StoreProduct storeProduct) {
        Cursor cursor = context.getContentResolver().query(StoreProductCat.CONTENT_URI, null, String.format("%s=? and %s=?", new Object[]{"product_id", "store_id"}), new String[]{Integer.toString(storeProduct.getProductId()), Integer.toString(storeProduct.getStoreId())}, null);
        List<StoreProductCategory> ret = new ArrayList();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                StoreProductCategory category = new StoreProductCategory();
                category.populateFromCursor(cursor);
                ret.add(category);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return ret;
    }
}
