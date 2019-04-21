package com.mcdonalds.sdk.services.data.provider.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotionEntity;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.services.data.database.DatabaseHelper;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.DatabaseField;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.List;

public class ProductHelper {
    public static Cursor getProduct(DatabaseHelper helper, String[] selectionArgs) {
        int i;
        DatabaseField field;
        StringBuilder projectionBuilder = new StringBuilder();
        List<DatabaseField> productFields = new Product().getFields();
        for (i = 0; i < productFields.size(); i++) {
            if (i > 0) {
                projectionBuilder.append(",");
            }
            field = (DatabaseField) productFields.get(i);
            projectionBuilder.append(String.format(" p.%1$s p_%1$s,", new Object[]{field.name}));
            projectionBuilder.append(String.format(" pb.%1$s pb_%1$s", new Object[]{field.name}));
        }
        List<DatabaseField> storeProductFields = new StoreProduct().getFields();
        for (i = 0; i < storeProductFields.size(); i++) {
            projectionBuilder.append(",");
            field = (DatabaseField) storeProductFields.get(i);
            projectionBuilder.append(String.format(" sp.%1$s sp_%1$s,", new Object[]{field.name}));
            projectionBuilder.append(String.format(" spb.%1$s spb_%1$s", new Object[]{field.name}));
        }
        projectionBuilder.append(String.format(", ifnull(ap.%1$s, 0) p_%2$s", new Object[]{AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID, Product.COLUMN_ADVERTISABLE_BASE_PRODUCT_ID}));
        String sql = String.format("select %1$s from %2$s p inner join %3$s sp on sp.%4$s = p.%5$s left join %6$s ap on (p.%5$s = ap.%7$s) and sp.%9$s = ap.%10$s and ap.%11$s = ? left join %2$s pb on (pb.%5$s = ap.%8$s) left join %3$s spb on spb.%4$s = pb.%5$s and spb.%9$s = sp.%9$s where cast(? as integer) in (p.%5$s, pb.%5$s) and sp.%9$s = ? group by ifnull(ap.%7$s, p.%5$s) order by pb_id desc limit 1", new Object[]{projectionBuilder.toString(), Product.TABLE_NAME, StoreProduct.TABLE_NAME, "id", "external_id", AdvertisablePromotionEntity.TABLE_NAME, AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID, AdvertisablePromotionEntity.COLUMN_SWAP_PRODUCT_ID, "store_id", "store_id", AdvertisablePromotionEntity.COLUMN_WEEKDAY});
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        return !(readableDatabase instanceof SQLiteDatabase) ? readableDatabase.rawQuery(sql, selectionArgs) : SQLiteInstrumentation.rawQuery(readableDatabase, sql, selectionArgs);
    }
}
