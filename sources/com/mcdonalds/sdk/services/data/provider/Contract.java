package com.mcdonalds.sdk.services.data.provider;

import android.net.Uri;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotionEntity;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.modules.models.FeedBackType;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.modules.models.StoreCatalog;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.modules.models.StoreProductCategory;
import com.mcdonalds.sdk.modules.models.TenderType;
import com.mcdonalds.sdk.services.data.database.DatabaseModel;

public final class Contract {
    public static final String AUTHORITY = "com.mcdonalds.gma.hongkong.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://com.mcdonalds.gma.hongkong.provider");

    public static final class AdvertisablePromotions {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, AdvertisablePromotionEntity.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, AdvertisablePromotionEntity.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, AdvertisablePromotionEntity.TABLE_NAME);
    }

    public static final class Categories {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, Category.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, Category.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, Category.TABLE_NAME);
    }

    public static final class CurrentStore {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, PATH);
        public static final String PATH = "current_store";
    }

    public static final class Facilities {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, Facility.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, Facility.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, Facility.TABLE_NAME);
    }

    public static final class Favorites {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, PATH);
        public static final String PATH = "favorites";
    }

    public static final class FeedBackTypes {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, FeedBackType.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, FeedBackType.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, FeedBackType.TABLE_NAME);
    }

    public static final class Ingredients {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, Ingredient.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, Ingredient.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, Ingredient.TABLE_NAME);
    }

    public static final class MenuTypes {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, MenuType.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, MenuType.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, MenuType.TABLE_NAME);
    }

    public static final class Ordering {
        public static final Uri CHOICES_CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, "choices");
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, "ordering");
        public static final Uri DIMENSIONS_CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, ProductDimension.TABLE_NAME);
        public static final Uri EXTRAS_CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, "extras");
        public static final Uri INGREDIENTS_CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, Ingredient.TABLE_NAME);
    }

    public static final class PaymentMethods {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, PaymentMethod.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, PaymentMethod.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, PaymentMethod.TABLE_NAME);
    }

    public static final class Pods {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, Pod.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, Pod.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, Pod.TABLE_NAME);
    }

    public static final class Products {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, Product.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, Product.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, Product.TABLE_NAME);
        public static final Uri WOTD_CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, String.format("%s/wotd", new Object[]{Product.TABLE_NAME}));
    }

    public static final class Promotions {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, Promotion.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, Promotion.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, Promotion.TABLE_NAME);
    }

    public static final class RecipeDimensions {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, ProductDimension.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, ProductDimension.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, ProductDimension.TABLE_NAME);
    }

    public static final class SocialNetworks {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, SocialNetwork.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, SocialNetwork.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, SocialNetwork.TABLE_NAME);
    }

    public static final class StoreCatalogs {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, StoreCatalog.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, StoreCatalog.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, StoreCatalog.TABLE_NAME);
    }

    public static final class StoreProductCat {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, StoreProductCategory.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, StoreProductCategory.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, StoreProductCategory.TABLE_NAME);
    }

    public static final class StoreProductCategories {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, StoreProductCategory.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, StoreProductCategory.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, StoreProductCategory.TABLE_NAME);
    }

    public static final class StoreProducts {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, StoreProduct.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, StoreProduct.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, StoreProduct.TABLE_NAME);
    }

    public static final class TenderTypes {
        public static final String CONTENT_ITEM_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.item", Contract.CONTENT_URI, TenderType.TABLE_NAME});
        public static final String CONTENT_TYPE = String.format("%s/%s_%s", new Object[]{"vnd.android.cursor.dir", Contract.CONTENT_URI, TenderType.TABLE_NAME});
        public static final Uri CONTENT_URI = Uri.withAppendedPath(Contract.CONTENT_URI, TenderType.TABLE_NAME);
    }

    public static Uri getContentUri(DatabaseModel model) {
        return getContentUri(model.getTableName());
    }

    public static Uri getContentUri(String tableName) {
        return Uri.withAppendedPath(CONTENT_URI, tableName);
    }

    public static String getTableName(Uri uri) {
        return uri.getPath().replace("/", "");
    }
}
