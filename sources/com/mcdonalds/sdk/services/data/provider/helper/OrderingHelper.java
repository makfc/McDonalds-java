package com.mcdonalds.sdk.services.data.provider.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotionEntity;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.modules.models.StoreProductCategory;
import com.mcdonalds.sdk.services.data.database.DatabaseHelper;
import com.mcdonalds.sdk.services.data.database.DatabaseQueryBuilder;
import com.mcdonalds.sdk.services.data.database.DatabaseQueryBuilder.ComparisonColumn;
import com.mcdonalds.sdk.services.data.database.DatabaseQueryBuilder.QueryColumn;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;

public class OrderingHelper {
    public static Cursor getRecipeMap(DatabaseHelper helper, String[] selectionArgs) {
        String advertisableBaseProductIdAlias = String.format("p_%s", new Object[]{Product.COLUMN_ADVERTISABLE_BASE_PRODUCT_ID});
        String storeProductPODTableName = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, Pod.TABLE_NAME});
        String storeProductPODProductId = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, "id"});
        String storeProductPODStoreId = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, "store_id"});
        String storeProductPODSaleTypeId = String.format("%s_%s", new Object[]{Pod.TABLE_NAME, Pod.COLUMN_SALE_TYPE_ID});
        String storeProductMenuTypeTableName = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, MenuType.TABLE_NAME});
        String storeProductMenuTypeProductId = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, "id"});
        String storeProductMenuTypeStoreId = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, "store_id"});
        String storeProductMenuTypeId = String.format("%s_%s", new Object[]{MenuType.TABLE_NAME, "id"});
        DatabaseQueryBuilder builder = DatabaseHelper.select();
        builder.addAllFromTable(new Product(), "p").addAllFromTable(new Product(), "pb").addAllFromTable(new StoreProduct(), "sp").addAllFromTable(new StoreProduct(), "spb").addAllFromTable(new Category(), "c").addAllFromTable(new StoreProductCategory(), "spc");
        builder.addColumn(new QueryColumn(AdvertisablePromotionEntity.TABLE_NAME, AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID, advertisableBaseProductIdAlias, "ap").setNullFallBackValue("0"));
        builder.from(Product.TABLE_NAME, "p");
        builder.innerJoin(StoreProduct.TABLE_NAME, "sp").onEqual("p", "external_id", "sp", "id");
        builder.leftJoin(AdvertisablePromotionEntity.TABLE_NAME, "ap").onIn("p", "external_id", new ComparisonColumn("ap", AdvertisablePromotionEntity.COLUMN_SWAP_PRODUCT_ID), new ComparisonColumn("ap", AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID)).onEqual("sp", "store_id", "ap", "store_id");
        builder.onEqualArgument("ap", AdvertisablePromotionEntity.COLUMN_WEEKDAY);
        builder.leftJoin(Product.TABLE_NAME, "pb").onEqual("pb", "external_id", "ap", AdvertisablePromotionEntity.COLUMN_SWAP_PRODUCT_ID);
        builder.leftJoin(StoreProduct.TABLE_NAME, "spb").onEqual("spb", "id", "pb", "external_id").onEqual("spb", "store_id", "sp", "store_id");
        builder.innerJoin(storeProductPODTableName, "sppod").onEqual("sp", "id", "sppod", storeProductPODProductId).onEqual("sp", "store_id", "sppod", storeProductPODStoreId).innerJoin(Pod.TABLE_NAME, "pod").onEqual("sppod", storeProductPODSaleTypeId, "pod", Pod.COLUMN_SALE_TYPE_ID);
        builder.innerJoin(storeProductMenuTypeTableName, "m").onEqual("sp", "id", "m", storeProductMenuTypeProductId).onEqual("sp", "store_id", "m", storeProductMenuTypeStoreId);
        builder.from(Category.TABLE_NAME, "c").innerJoin(StoreProductCategory.TABLE_NAME, "spc").onEqual("spc", "category_id", "c", "category_id").onIn("spc", "product_id", new ComparisonColumn("sp", "id"), new ComparisonColumn("ap", AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID)).onEqual("spc", "store_id", "sp", "store_id");
        builder.whereEqualArgument("pod", Pod.COLUMN_TYPE_NAME);
        builder.whereEqualArgument("m", storeProductMenuTypeId);
        builder.whereEqual("c", "type", "m", storeProductMenuTypeId);
        builder.whereEqualArgument("sp", "store_id");
        builder.whereLikeArgument("p", "name");
        builder.groupBy(new QueryColumn("ap", AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID).setNullFallBackColumn(new QueryColumn("p", "external_id")));
        builder.groupBy("c", "category_id");
        builder.orderBy("c", "category_id");
        builder.orderBy("spc", "display_order");
        String sql = builder.getSQL();
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        return !(readableDatabase instanceof SQLiteDatabase) ? readableDatabase.rawQuery(sql, selectionArgs) : SQLiteInstrumentation.rawQuery(readableDatabase, sql, selectionArgs);
    }

    public static Cursor getIngredients(DatabaseHelper helper, String relationName, String[] selectionArgs) {
        String relationIngredientId = String.format("%s_%s", new Object[]{Ingredient.TABLE_NAME, "id"});
        String relationProductId = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, "id"});
        String relationStoreId = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, "store_id"});
        String sql = DatabaseHelper.select().addAllFromTable(new Ingredient(), "i").addAllFromTable(new Product(), "p").addAllFromTable(new Product(), "pb").addAllFromTable(new StoreProduct(), "sp").addAllFromTable(new StoreProduct(), "spb").addColumn(new QueryColumn(AdvertisablePromotionEntity.TABLE_NAME, AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID, String.format("p_%s", new Object[]{Product.COLUMN_ADVERTISABLE_BASE_PRODUCT_ID}), "ap").setNullFallBackValue("0")).from(Ingredient.TABLE_NAME, "i").innerJoin(relationName, "spi").onEqual("spi", relationIngredientId, "i", "id").innerJoin(StoreProduct.TABLE_NAME, "sp").onEqual("i", "product_id", "sp", "id").onEqual("spi", relationStoreId, "sp", "store_id").innerJoin(Product.TABLE_NAME, "p").onEqual("p", "external_id", "i", "product_id").leftJoin(AdvertisablePromotionEntity.TABLE_NAME, "ap").onEqual("ap", AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID, "p", "external_id").onEqualArgument("ap", AdvertisablePromotionEntity.COLUMN_WEEKDAY).leftJoin(Product.TABLE_NAME, "pb").onEqual("pb", "external_id", "ap", AdvertisablePromotionEntity.COLUMN_SWAP_PRODUCT_ID).leftJoin(StoreProduct.TABLE_NAME, "spb").onEqual("pb", "external_id", "spb", "id").onEqual("spi", relationStoreId, "spb", "store_id").whereEqualArgument("spi", relationProductId).whereEqualArgument("sp", "store_id").groupBy(new QueryColumn("ap", AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID).setNullFallBackColumn(new QueryColumn("p", "external_id"))).orderByAsc("i", "display_order").getSQL();
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        return !(readableDatabase instanceof SQLiteDatabase) ? readableDatabase.rawQuery(sql, selectionArgs) : SQLiteInstrumentation.rawQuery(readableDatabase, sql, selectionArgs);
    }

    public static Cursor getDimensions(DatabaseHelper helper, String[] selectionArgs) {
        String relationName = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, ProductDimension.TABLE_NAME});
        String relationDimensionId = String.format("%s_%s", new Object[]{ProductDimension.TABLE_NAME, "product_id"});
        String relationProductId = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, "id"});
        String relationStoreId = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, "store_id"});
        String sql = DatabaseHelper.select().addAllFromTable(new ProductDimension(), "d").addAllFromTable(new Product(), "p").addAllFromTable(new Product(), "pb").addAllFromTable(new StoreProduct(), "sp").addAllFromTable(new StoreProduct(), "spb").addColumn(new QueryColumn(AdvertisablePromotionEntity.TABLE_NAME, AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID, String.format("p_%s", new Object[]{Product.COLUMN_ADVERTISABLE_BASE_PRODUCT_ID}), "ap").setNullFallBackValue("0")).from(ProductDimension.TABLE_NAME, "d").innerJoin(relationName, "spd").onEqual("spd", relationDimensionId, "d", "product_id").innerJoin(StoreProduct.TABLE_NAME, "sp").onEqual("d", "product_id", "sp", "id").onEqual("spd", relationStoreId, "sp", "store_id").innerJoin(Product.TABLE_NAME, "p").onEqual("p", "external_id", "d", "product_id").leftJoin(AdvertisablePromotionEntity.TABLE_NAME, "ap").onEqual("ap", AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID, "p", "external_id").onEqualArgument("ap", AdvertisablePromotionEntity.COLUMN_WEEKDAY).leftJoin(Product.TABLE_NAME, "pb").onEqual("pb", "external_id", "ap", AdvertisablePromotionEntity.COLUMN_SWAP_PRODUCT_ID).leftJoin(StoreProduct.TABLE_NAME, "spb").onEqual("pb", "external_id", "spb", "id").onEqual("spd", relationStoreId, "spb", "store_id").whereEqualArgument("sp", "store_id").whereEqualArgument("spd", relationProductId).groupBy(new QueryColumn("ap", AdvertisablePromotionEntity.COLUMN_BASE_PRODUCT_ID).setNullFallBackColumn(new QueryColumn("p", "external_id"))).orderByAsc("d", ProductDimension.COLUMN_SIZE_CODE).getSQL();
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        return !(readableDatabase instanceof SQLiteDatabase) ? readableDatabase.rawQuery(sql, selectionArgs) : SQLiteInstrumentation.rawQuery(readableDatabase, sql, selectionArgs);
    }
}
