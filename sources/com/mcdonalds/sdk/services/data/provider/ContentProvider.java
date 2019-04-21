package com.mcdonalds.sdk.services.data.provider;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.mcdonalds.sdk.modules.models.StoreCatalog;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.modules.models.TenderType;
import com.mcdonalds.sdk.services.data.database.DatabaseHelper;
import com.mcdonalds.sdk.services.data.provider.Contract.Categories;
import com.mcdonalds.sdk.services.data.provider.Contract.Facilities;
import com.mcdonalds.sdk.services.data.provider.Contract.FeedBackTypes;
import com.mcdonalds.sdk.services.data.provider.Contract.Ingredients;
import com.mcdonalds.sdk.services.data.provider.Contract.MenuTypes;
import com.mcdonalds.sdk.services.data.provider.Contract.Ordering;
import com.mcdonalds.sdk.services.data.provider.Contract.PaymentMethods;
import com.mcdonalds.sdk.services.data.provider.Contract.Pods;
import com.mcdonalds.sdk.services.data.provider.Contract.Products;
import com.mcdonalds.sdk.services.data.provider.Contract.Promotions;
import com.mcdonalds.sdk.services.data.provider.Contract.RecipeDimensions;
import com.mcdonalds.sdk.services.data.provider.Contract.SocialNetworks;
import com.mcdonalds.sdk.services.data.provider.Contract.StoreCatalogs;
import com.mcdonalds.sdk.services.data.provider.Contract.StoreProducts;
import com.mcdonalds.sdk.services.data.provider.Contract.TenderTypes;
import com.mcdonalds.sdk.services.data.provider.helper.OrderingHelper;
import com.mcdonalds.sdk.services.data.provider.helper.ProductHelper;
import com.newrelic.agent.android.instrumentation.SQLiteInstrumentation;
import java.util.ArrayList;

public class ContentProvider extends android.content.ContentProvider {
    private static final int CATEGORY_ID = 1;
    private static final int CATEGORY_LIST = 2;
    private static final int DIMENSION_ID = 13;
    private static final int DIMENSION_LIST = 14;
    private static final int FACILITY_ID = 3;
    private static final int FACILITY_LIST = 4;
    private static final int FEEDBACK_TYPE_ID = 9;
    private static final int FEEDBACK_TYPE_LIST = 10;
    private static final int INGREDIENT_ID = 15;
    private static final int INGREDIENT_LIST = 16;
    private static final int MENU_TYPE_ID = 5;
    private static final int MENU_TYPE_LIST = 6;
    private static final int PAYMENT_METHOD_ID = 7;
    private static final int PAYMENT_METHOD_LIST = 8;
    private static final int POD_ID = 11;
    private static final int POD_LIST = 12;
    private static final int PROMOTION_ID = 19;
    private static final int PROMOTION_LIST = 20;
    private static final int RECIPE_ID = 17;
    private static final int RECIPE_LIST = 18;
    private static final int SOCIAL_NETWORK_ID = 27;
    private static final int SOCIAL_NETWORK_LIST = 28;
    private static final int STORE_CATALOG_ID = 23;
    private static final int STORE_CATALOG_LIST = 24;
    private static final int STORE_PRODUCT_ID = 25;
    private static final int STORE_PRODUCT_LIST = 26;
    private static final int TENDER_TYPE_ID = 21;
    private static final int TENDER_TYPE_LIST = 22;
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);
    DatabaseHelper mDatabaseHelper;

    static {
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{Category.TABLE_NAME}), 1);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", Category.TABLE_NAME, 2);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{Facility.TABLE_NAME}), 3);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", Facility.TABLE_NAME, 4);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{MenuType.TABLE_NAME}), 5);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", MenuType.TABLE_NAME, 6);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{PaymentMethod.TABLE_NAME}), 7);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", PaymentMethod.TABLE_NAME, 8);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{Pod.TABLE_NAME}), 11);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", Pod.TABLE_NAME, 12);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{ProductDimension.TABLE_NAME}), 13);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", ProductDimension.TABLE_NAME, 14);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{Ingredient.TABLE_NAME}), 15);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", Ingredient.TABLE_NAME, 16);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{Product.TABLE_NAME}), 17);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", Product.TABLE_NAME, 18);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{Promotion.TABLE_NAME}), 19);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", Promotion.TABLE_NAME, 20);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{TenderType.TABLE_NAME}), 21);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", TenderType.TABLE_NAME, 22);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{StoreCatalog.TABLE_NAME}), 23);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", StoreCatalog.TABLE_NAME, 23);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{StoreProduct.TABLE_NAME}), 25);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", StoreProduct.TABLE_NAME, 26);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", String.format("%s/#", new Object[]{StoreProduct.TABLE_NAME}), 27);
        URI_MATCHER.addURI("com.mcdonalds.gma.hongkong.provider", StoreProduct.TABLE_NAME, 28);
    }

    public boolean onCreate() {
        this.mDatabaseHelper = DatabaseHelper.getInstance(getContext());
        return true;
    }

    @Nullable
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (uri.equals(Ordering.CONTENT_URI)) {
            return OrderingHelper.getRecipeMap(this.mDatabaseHelper, selectionArgs);
        }
        if (uri.equals(Ordering.DIMENSIONS_CONTENT_URI)) {
            return OrderingHelper.getDimensions(this.mDatabaseHelper, selectionArgs);
        }
        if (uri.equals(Ordering.INGREDIENTS_CONTENT_URI)) {
            return OrderingHelper.getIngredients(this.mDatabaseHelper, String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, Ingredient.TABLE_NAME}), selectionArgs);
        } else if (uri.equals(Ordering.CHOICES_CONTENT_URI)) {
            return OrderingHelper.getIngredients(this.mDatabaseHelper, StoreProduct.RELATION_CHOICES, selectionArgs);
        } else if (uri.equals(Ordering.EXTRAS_CONTENT_URI)) {
            return OrderingHelper.getIngredients(this.mDatabaseHelper, StoreProduct.RELATION_EXTRAS, selectionArgs);
        } else if (uri.equals(Products.WOTD_CONTENT_URI)) {
            return ProductHelper.getProduct(this.mDatabaseHelper, selectionArgs);
        } else {
            String tableName = Contract.getTableName(uri);
            SQLiteDatabase readableDatabase = this.mDatabaseHelper.getReadableDatabase();
            Cursor cursor = !(readableDatabase instanceof SQLiteDatabase) ? readableDatabase.query(tableName, projection, selection, selectionArgs, null, null, sortOrder, null) : SQLiteInstrumentation.query(readableDatabase, tableName, projection, selection, selectionArgs, null, null, sortOrder, null);
            if (getContext() == null) {
                return cursor;
            }
            cursor.setNotificationUri(getContext().getContentResolver(), Contract.CONTENT_URI);
            return cursor;
        }
    }

    @Nullable
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case 1:
                return Categories.CONTENT_ITEM_TYPE;
            case 2:
                return Categories.CONTENT_TYPE;
            case 3:
                return Facilities.CONTENT_ITEM_TYPE;
            case 4:
                return Facilities.CONTENT_TYPE;
            case 5:
                return MenuTypes.CONTENT_ITEM_TYPE;
            case 6:
                return MenuTypes.CONTENT_TYPE;
            case 7:
                return PaymentMethods.CONTENT_ITEM_TYPE;
            case 8:
                return PaymentMethods.CONTENT_TYPE;
            case 9:
                return FeedBackTypes.CONTENT_ITEM_TYPE;
            case 10:
                return FeedBackTypes.CONTENT_TYPE;
            case 11:
                return Pods.CONTENT_ITEM_TYPE;
            case 12:
                return Pods.CONTENT_TYPE;
            case 13:
                return RecipeDimensions.CONTENT_ITEM_TYPE;
            case 14:
                return RecipeDimensions.CONTENT_TYPE;
            case 15:
                return Ingredients.CONTENT_ITEM_TYPE;
            case 16:
                return Ingredients.CONTENT_TYPE;
            case 17:
                return Products.CONTENT_ITEM_TYPE;
            case 18:
                return Products.CONTENT_TYPE;
            case 19:
                return Promotions.CONTENT_ITEM_TYPE;
            case 20:
                return Promotions.CONTENT_TYPE;
            case 21:
                return TenderTypes.CONTENT_ITEM_TYPE;
            case 22:
                return TenderTypes.CONTENT_TYPE;
            case 23:
                return StoreCatalogs.CONTENT_ITEM_TYPE;
            case 24:
                return StoreCatalogs.CONTENT_TYPE;
            case 25:
                return StoreProducts.CONTENT_ITEM_TYPE;
            case 26:
                return StoreProducts.CONTENT_TYPE;
            case 27:
                return SocialNetworks.CONTENT_ITEM_TYPE;
            case 28:
                return SocialNetworks.CONTENT_TYPE;
            default:
                return null;
        }
    }

    @Nullable
    public synchronized Uri insert(@NonNull Uri uri, ContentValues values) {
        Uri withAppendedId;
        try {
            String tableName = Contract.getTableName(uri);
            SQLiteDatabase writableDatabase = this.mDatabaseHelper.getWritableDatabase();
            withAppendedId = ContentUris.withAppendedId(uri, !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insert(tableName, null, values) : SQLiteInstrumentation.insert(writableDatabase, tableName, null, values));
        } catch (SQLiteException e) {
            Log.e("SQLite", "Database was deleted");
            withAppendedId = null;
        }
        return withAppendedId;
    }

    public synchronized int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int result;
        String tableName = Contract.getTableName(uri);
        result = 0;
        try {
            SQLiteDatabase database = this.mDatabaseHelper.getWritableDatabase();
            String str = "PRAGMA foreign_keys=ON;";
            if (database instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(database, str);
            } else {
                database.execSQL(str);
            }
            result = !(database instanceof SQLiteDatabase) ? database.delete(tableName, selection, selectionArgs) : SQLiteInstrumentation.delete(database, tableName, selection, selectionArgs);
            String str2 = "PRAGMA foreign_keys=OFF;";
            if (database instanceof SQLiteDatabase) {
                SQLiteInstrumentation.execSQL(database, str2);
            } else {
                database.execSQL(str2);
            }
        } catch (SQLiteException e) {
            Log.e("SQLite", "Database was deleted");
        }
        return result;
    }

    public synchronized int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int i;
        if (uri.equals(Contract.CONTENT_URI)) {
            i = 1;
        } else {
            try {
                String tableName = Contract.getTableName(uri);
                SQLiteDatabase writableDatabase = this.mDatabaseHelper.getWritableDatabase();
                i = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.update(tableName, values, selection, selectionArgs) : SQLiteInstrumentation.update(writableDatabase, tableName, values, selection, selectionArgs);
            } catch (SQLiteException e) {
                Log.e("SQLite", "Database was deleted");
                i = 0;
            }
        }
        return i;
    }

    @NonNull
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        SQLiteDatabase db = this.mDatabaseHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            int numOperations = operations.size();
            ContentProviderResult[] results = new ContentProviderResult[numOperations];
            for (int i = 0; i < numOperations; i++) {
                results[i] = ((ContentProviderOperation) operations.get(i)).apply(this, results, i);
            }
            db.setTransactionSuccessful();
            return results;
        } finally {
            db.endTransaction();
        }
    }

    public synchronized int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] valuesList) {
        int length;
        String tableName = Contract.getTableName(uri);
        SQLiteDatabase db = this.mDatabaseHelper.getWritableDatabase();
        db.beginTransaction();
        length = 0;
        try {
            for (ContentValues values : valuesList) {
                if (db instanceof SQLiteDatabase) {
                    SQLiteInstrumentation.insert(db, tableName, null, values);
                } else {
                    db.insert(tableName, null, values);
                }
                length++;
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Throwable th) {
            db.endTransaction();
        }
        return length;
    }
}
