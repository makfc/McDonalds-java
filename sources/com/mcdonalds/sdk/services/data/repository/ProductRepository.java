package com.mcdonalds.sdk.services.data.repository;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.SparseArray;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.modules.models.StoreProductCategory;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.data.database.DatabaseModel.ForeignKey;
import com.mcdonalds.sdk.services.data.provider.Contract.Ordering;
import com.mcdonalds.sdk.services.data.provider.Contract.Products;
import com.mcdonalds.sdk.services.data.provider.Contract.StoreProductCategories;
import com.mcdonalds.sdk.services.data.provider.Contract.StoreProducts;
import com.mcdonalds.sdk.services.data.proxy.ProductProxy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository extends DatabaseModelRepository {
    public static Map<Category, List<Product>> getProductMap(Context context, String pod, int menuTypeId, String searchQuery) {
        Store currentStore = OrderManager.getInstance().getCurrentStore();
        if (currentStore == null) {
            return null;
        }
        if (searchQuery == null) {
            searchQuery = "";
        }
        int weekday = Calendar.getInstance().get(7);
        String[] selectionArgs = new String[5];
        selectionArgs[0] = Integer.toString(weekday);
        selectionArgs[1] = pod;
        selectionArgs[2] = Integer.toString(menuTypeId);
        selectionArgs[3] = Integer.toString(currentStore.getStoreId());
        selectionArgs[4] = String.format("%%%s%%", new Object[]{searchQuery});
        Map<Category, List<Product>> productMap = new LinkedHashMap();
        Cursor cursor = context.getContentResolver().query(Ordering.CONTENT_URI, null, null, selectionArgs, null);
        if (cursor == null) {
            return productMap;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = new Category();
            category.setID(cursor.getInt(cursor.getColumnIndex("c_category_id")));
            List<Product> categoryProducts = (List) productMap.get(category);
            if (categoryProducts == null) {
                category.populateFromCursor(cursor, "c");
                categoryProducts = new ArrayList();
                productMap.put(category, categoryProducts);
            }
            StoreProduct storeProduct = new StoreProduct();
            storeProduct.populateFromCursor(cursor, "sp");
            Product product = new ProductProxy();
            product.populateFromCursor(cursor, "p");
            product.setStoreProduct(storeProduct);
            StoreProductCategory spc = new StoreProductCategory();
            spc.populateFromCursor(cursor, "spc");
            category.setDisplaySizeSelection(spc.getDisplaySizeSelection());
            product.setDisplayCategory(category);
            if (product.getAdvertisableBaseProductId() != 0) {
                StoreProduct advertisableStoreProduct = new StoreProduct();
                advertisableStoreProduct.populateFromCursor(cursor, "spb");
                Product advertisableProduct = new ProductProxy();
                advertisableProduct.setStoreProduct(advertisableStoreProduct);
                advertisableProduct.populateFromCursor(cursor, "pb");
                product.setAdvertisableProduct(advertisableProduct);
                product.setAdvertisableWeekDay(weekday);
            }
            categoryProducts.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        return productMap;
    }

    public static List<Product> getAll(Context context) {
        Cursor cursor = context.getContentResolver().query(Products.CONTENT_URI, null, null, null, null);
        List<Product> productList = null;
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                productList = new ArrayList(cursor.getCount());
                while (!cursor.isAfterLast()) {
                    Product product = new ProductProxy();
                    product.populateFromCursor(cursor);
                    productList.add(product);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        return productList;
    }

    public static SparseArray<Product> getProductMap(Context context) {
        Cursor cursor = context.getContentResolver().query(Products.CONTENT_URI, null, null, null, null);
        SparseArray<Product> productMap = null;
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                productMap = new SparseArray(cursor.getCount());
                while (!cursor.isAfterLast()) {
                    Product product = new ProductProxy();
                    product.populateFromCursor(cursor);
                    productMap.put(product.getExternalId().intValue(), product);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        return productMap;
    }

    public static List<Product> getByCategory(Context context, int categoryId) {
        String storeProductCategoryTableName = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, Category.TABLE_NAME});
        String columnStoreProductId = String.format("%s_%s", new Object[]{StoreProduct.TABLE_NAME, "id"});
        String columnCategoryId = String.format("%s_%s", new Object[]{Category.TABLE_NAME, "category_id"});
        String categorySubQuery = String.format("select %s from %s where %s = ?", new Object[]{columnStoreProductId, storeProductCategoryTableName, columnCategoryId});
        String storeProductSubQuery = String.format("select %s from %s where %s in (%s)", new Object[]{"id", StoreProduct.TABLE_NAME, "id", categorySubQuery});
        Cursor cursor = context.getContentResolver().query(Products.CONTENT_URI, null, String.format("%s in (%s)", new Object[]{"external_id", storeProductSubQuery}), new String[]{Integer.toString(categoryId)}, null);
        if (cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        List<Product> products = new ArrayList(cursor.getCount());
        while (!cursor.isAfterLast()) {
            Product product = new ProductProxy();
            product.populateFromCursor(cursor);
            products.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        return products;
    }

    public static Product getByProductCode(Context context, int productCode, boolean resolveRecipeDetails) {
        return getByProductCode(context, productCode, new SparseArray(), resolveRecipeDetails, null);
    }

    public static Product getByProductCode(Context context, int productCode, boolean resolveRecipeDetails, Date orderDate) {
        return getByProductCode(context, productCode, new SparseArray(), resolveRecipeDetails, orderDate);
    }

    private static Product getByProductCode(Context context, int productCode, SparseArray<Product> products, boolean resolveProductDetails, Date orderDate) {
        Product product = (Product) products.get(productCode);
        if (product != null) {
            return product;
        }
        Store store = OrderManager.getInstance().getCurrentStore();
        if (store != null) {
            int storeId = store.getStoreId();
            Calendar calendar = Calendar.getInstance();
            if (orderDate != null) {
                calendar.setTime(orderDate);
            }
            String[] selectionArgs = new String[]{Integer.toString(calendar.get(7)), Integer.toString(productCode), Integer.toString(storeId)};
            Cursor cursor = context.getContentResolver().query(Products.WOTD_CONTENT_URI, null, String.format("%s=?", new Object[]{"external_id"}), selectionArgs, null);
            if (cursor != null) {
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    StoreProduct storeProduct = new StoreProduct();
                    storeProduct.populateFromCursor(cursor, "sp");
                    product = new ProductProxy();
                    product.setStoreProduct(storeProduct);
                    populateProductWithCursor(context, product, cursor, "p", products, resolveProductDetails);
                    if (product.getAdvertisableBaseProductId() != 0) {
                        StoreProduct advertisableStoreProduct = new StoreProduct();
                        advertisableStoreProduct.populateFromCursor(cursor, "spb");
                        Product advertisableProduct = new ProductProxy();
                        advertisableProduct.setStoreProduct(advertisableStoreProduct);
                        populateProductWithCursor(context, advertisableProduct, cursor, "pb", products, resolveProductDetails);
                        product.setAdvertisableProduct(advertisableProduct);
                        product.setAdvertisableWeekDay(weekday);
                    }
                }
                cursor.close();
            }
        }
        return product;
    }

    public static List<Product> getByProductCodes(Context context, int[] productCodes) {
        List<Product> products = null;
        if (productCodes != null) {
            StringBuilder selectionBuilder = new StringBuilder(String.format("%s in (", new Object[]{"external_id"}));
            String[] selectionArgs = new String[productCodes.length];
            for (int i = 0; i < productCodes.length; i++) {
                if (i > 0) {
                    selectionBuilder.append(",");
                }
                selectionArgs[i] = String.valueOf(productCodes[i]);
                selectionBuilder.append("?");
            }
            selectionBuilder.append(")");
            Cursor cursor = context.getContentResolver().query(Products.CONTENT_URI, null, selectionBuilder.toString(), selectionArgs, null);
            if (cursor != null) {
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    products = new ArrayList();
                    while (!cursor.isAfterLast()) {
                        Product product = new ProductProxy();
                        product.populateFromCursor(cursor);
                        products.add(product);
                        cursor.moveToNext();
                    }
                }
                cursor.close();
            }
        }
        return products;
    }

    public static Product getByRecipeId(Context context, int recipeId) {
        return getByRecipeId(context, recipeId, new SparseArray(), true);
    }

    private static Product getByRecipeId(Context context, int recipeId, SparseArray<Product> products, boolean resolveProductDetails) {
        Product product = null;
        Cursor cursor = context.getContentResolver().query(Products.CONTENT_URI, null, String.format("%s=?", new Object[]{"recipe_id"}), new String[]{Integer.toString(recipeId)}, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                product = new ProductProxy();
                populateProductWithCursor(context, product, cursor, products, resolveProductDetails);
            }
            cursor.close();
        }
        return product;
    }

    private static void populateProductWithCursor(Context context, Product product, Cursor cursor, SparseArray<Product> products, boolean resolveProductDetails) {
        populateProductWithCursor(context, product, cursor, null, products, resolveProductDetails);
    }

    private static void populateProductWithCursor(Context context, Product product, Cursor cursor, String prefix, SparseArray<Product> products, boolean resolveProductDetails) {
        product.populateFromCursor(cursor, prefix);
        products.put(product.getExternalId().intValue(), product);
        if (resolveProductDetails) {
            product.setIngredients(getProductIngredients(context, product, null, products, true));
            product.setExtras(getProductIngredients(context, product, Ordering.EXTRAS_CONTENT_URI, products, true));
            product.setChoices(getProductIngredients(context, product, Ordering.CHOICES_CONTENT_URI, products, true));
            StoreProduct storeProduct = getStoreProduct(context, product.getExternalId().intValue(), products, true);
            if (storeProduct != null) {
                product.setMenuTypes(storeProduct.getMenuTypes());
                product.setPriceEatIn(storeProduct.getPriceEatIn());
                product.setPriceTakeOut(storeProduct.getPriceTakeOut());
                product.setPriceDelivery(storeProduct.getPriceDelivery());
                product.setBasePriceEatIn(storeProduct.getBasePriceEatIn());
                product.setBasePriceTakeOut(storeProduct.getBasePriceTakeOut());
                product.setBasePriceDelivery(storeProduct.getBasePriceDelivery());
                product.setEnergy(storeProduct.getEnergy().doubleValue());
                product.setKCal(storeProduct.getKCal());
                product.setPODObjects(storeProduct.getPODs());
                product.setDimensions(storeProduct.getDimensions());
            }
        }
    }

    public static StoreProduct getStoreProduct(Context context, int productCode) {
        return getStoreProduct(context, productCode, new SparseArray(), false);
    }

    private static StoreProduct getStoreProduct(Context context, int productCode, SparseArray<Product> products, boolean resolveDetails) {
        Store currentStore = OrderManager.getInstance().getCurrentStore();
        if (currentStore == null) {
            return null;
        }
        int storeId = currentStore.getStoreId();
        StoreProduct storeProduct = null;
        Cursor cursor = context.getContentResolver().query(StoreProducts.CONTENT_URI, null, new StoreProduct().getSelection(), new String[]{Integer.toString(productCode), Integer.toString(storeId)}, null);
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                storeProduct = new StoreProduct();
                storeProduct.populateFromCursor(cursor);
                storeProduct.setPODs(getProductPods(context, storeProduct));
                storeProduct.setMenuTypes(getProductMenuTypes(context, storeProduct));
                if (resolveDetails) {
                    storeProduct.setDimensions(getProductDimensions(context, storeProduct, (SparseArray) products));
                }
            }
            cursor.close();
        }
        return storeProduct;
    }

    public static List<MenuType> getProductMenuTypes(Context context, StoreProduct storeProduct) {
        ForeignKey dimensionForeignKey = new ForeignKey(new String[]{"id", "store_id"}, MenuType.TABLE_NAME, "id");
        Cursor cursor = DatabaseModelRepository.getRelatedModels(context, StoreProduct.TABLE_NAME, null, new String[]{String.valueOf(storeProduct.getProductId()), String.valueOf(storeProduct.getStoreId())}, dimensionForeignKey);
        List<MenuType> menuTypes = null;
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                menuTypes = new ArrayList(cursor.getCount());
                while (!cursor.isAfterLast()) {
                    MenuType menuType = new MenuType();
                    menuType.populateFromCursor(cursor);
                    menuTypes.add(menuType);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        return menuTypes;
    }

    public static List<Pod> getProductPods(Context context, StoreProduct storeProduct) {
        ForeignKey podForeignKey = new ForeignKey(new String[]{"id", "store_id"}, Pod.TABLE_NAME, Pod.COLUMN_SALE_TYPE_ID);
        Cursor cursor = DatabaseModelRepository.getRelatedModels(context, StoreProduct.TABLE_NAME, null, new String[]{String.valueOf(storeProduct.getProductId()), String.valueOf(storeProduct.getStoreId())}, podForeignKey);
        List<Pod> pods = null;
        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                pods = new ArrayList(cursor.getCount());
                while (!cursor.isAfterLast()) {
                    Pod pod = new Pod();
                    pod.populateFromCursor(cursor);
                    pods.add(pod);
                    cursor.moveToNext();
                }
            }
            cursor.close();
        }
        return pods;
    }

    public static void populateProductWithStoreSpecificData(Context context, Product product) {
        populateProductWithStoreSpecificData(product, getStoreProduct(context, product.getExternalId().intValue(), null, false));
    }

    private static void populateProductWithStoreSpecificData(Product product, StoreProduct storeProduct) {
        if (storeProduct != null) {
            product.setMenuTypes(storeProduct.getMenuTypes());
            product.setPODObjects(storeProduct.getPODs());
            product.setPriceEatIn(storeProduct.getPriceEatIn());
            product.setPriceTakeOut(storeProduct.getPriceTakeOut());
            product.setPriceDelivery(storeProduct.getPriceDelivery());
            product.setBasePriceEatIn(storeProduct.getBasePriceEatIn());
            product.setBasePriceTakeOut(storeProduct.getBasePriceTakeOut());
            product.setBasePriceDelivery(storeProduct.getBasePriceDelivery());
            product.setEnergy(storeProduct.getEnergy().doubleValue());
            product.setKCal(storeProduct.getKCal());
        }
    }

    public static boolean productIsInCurrentStore(Context context, int externalId) {
        if (getStoreProduct(context, externalId, null, false) != null) {
            return true;
        }
        return false;
    }

    public static void cleanStoreProducts(Context context, int storeId) {
        context.getContentResolver().delete(StoreProducts.CONTENT_URI, String.format("%s=?", new Object[]{"store_id"}), new String[]{Integer.toString(storeId)});
        context.getContentResolver().delete(StoreProductCategories.CONTENT_URI, String.format("%s=?", new Object[]{"store_id"}), new String[]{Integer.toString(storeId)});
    }

    public static void cleanOrphanedProducts(Context context) {
        context.getContentResolver().delete(Products.CONTENT_URI, String.format("%1$s.%2$s not in (select %3$s from %4$s)", new Object[]{Product.TABLE_NAME, "external_id", "id", StoreProduct.TABLE_NAME}), null);
    }

    public static List<ProductDimension> getProductDimensions(Context context, Product product, int storeId) {
        StoreProduct storeProduct = new StoreProduct();
        storeProduct.setProductId(product.getExternalId().intValue());
        storeProduct.setStoreId(storeId);
        return getProductDimensions(context, storeProduct, new SparseArray());
    }

    public static List<ProductDimension> getProductDimensions(Context context, StoreProduct storeProduct) {
        return getProductDimensions(context, storeProduct, new SparseArray());
    }

    private static List<ProductDimension> getProductDimensions(Context context, StoreProduct storeProduct, SparseArray<Product> products) {
        Cursor cursor = context.getContentResolver().query(Ordering.DIMENSIONS_CONTENT_URI, null, null, new String[]{String.valueOf(Calendar.getInstance().get(7)), String.valueOf(storeProduct.getStoreId()), String.valueOf(storeProduct.getProductId())}, null);
        List<ProductDimension> dimensions = new ArrayList();
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ProductDimension dimension = new ProductDimension();
                dimension.populateFromCursor(cursor, "d");
                Product ingredientProduct = new ProductProxy();
                populateProductWithCursor(context, ingredientProduct, cursor, "p", products, false);
                new StoreProduct().populateFromCursor(cursor, "sp");
                if (ingredientProduct.getAdvertisableBaseProductId() != 0) {
                    StoreProduct advertisableStoreProduct = new StoreProduct();
                    advertisableStoreProduct.populateFromCursor(cursor, "spb");
                    Product advertisableProduct = new ProductProxy();
                    advertisableProduct.setStoreProduct(advertisableStoreProduct);
                    populateProductWithCursor(context, advertisableProduct, cursor, "pb", products, false);
                    ingredientProduct.setAdvertisableProduct(advertisableProduct);
                    ingredientProduct.setAdvertisableWeekDay(weekday);
                }
                dimension.setProduct(ingredientProduct);
                dimensions.add(dimension);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return dimensions;
    }

    public static List<Ingredient> getProductIngredients(Context context, Product product) {
        return getProductIngredients(context, product, null, new SparseArray());
    }

    public static List<Ingredient> getProductChoices(Context context, Product product) {
        return getProductChoices(context, product, false);
    }

    public static List<Ingredient> getProductChoices(Context context, Product product, boolean getChoiceIngredients) {
        SparseArray<Product> products = new SparseArray();
        List<Ingredient> choices = getProductIngredients(context, product, Ordering.CHOICES_CONTENT_URI, products);
        if (getChoiceIngredients && choices != null) {
            for (Ingredient choice : choices) {
                choice.getProduct().setIngredients(getProductIngredients(context, choice.getProduct(), null, products));
            }
        }
        return choices;
    }

    public static List<Ingredient> getProductExtras(Context context, Product product) {
        return getProductIngredients(context, product, Ordering.EXTRAS_CONTENT_URI, new SparseArray());
    }

    private static List<Ingredient> getProductIngredients(Context context, Product product, Uri uri, SparseArray<Product> products, boolean resolveDetails) {
        if (OrderManager.getInstance().getCurrentStore() == null) {
            return null;
        }
        String[] selectionArgs = new String[]{String.valueOf(Calendar.getInstance().get(7)), String.valueOf(product.getExternalId()), String.valueOf(currentStore.getStoreId())};
        if (uri == null) {
            uri = Ordering.INGREDIENTS_CONTENT_URI;
        }
        Cursor cursor = context.getContentResolver().query(uri, null, null, selectionArgs, null);
        List<Ingredient> ingredients = new ArrayList();
        if (cursor == null) {
            return ingredients;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ingredient ingredient = new Ingredient();
            ingredient.populateFromCursor(cursor, "i");
            Product ingredientProduct = new ProductProxy();
            populateProductWithCursor(context, ingredientProduct, cursor, "p", products, resolveDetails);
            StoreProduct storeProduct = new StoreProduct();
            storeProduct.populateFromCursor(cursor, "sp");
            storeProduct.setPODs(getProductPods(context, storeProduct));
            if (ingredientProduct.getAdvertisableBaseProductId() != 0) {
                StoreProduct advertisableStoreProduct = new StoreProduct();
                advertisableStoreProduct.populateFromCursor(cursor, "spb");
                Product advertisableProduct = new ProductProxy();
                advertisableProduct.setStoreProduct(advertisableStoreProduct);
                populateProductWithCursor(context, advertisableProduct, cursor, "pb", products, resolveDetails);
                ingredientProduct.setAdvertisableProduct(advertisableProduct);
                ingredientProduct.setAdvertisableWeekDay(weekday);
            }
            ingredient.setProduct(ingredientProduct);
            ingredients.add(ingredient);
            cursor.moveToNext();
        }
        cursor.close();
        return ingredients;
    }

    private static List<Ingredient> getProductIngredients(Context context, Product product, Uri uri, SparseArray<Product> products) {
        return getProductIngredients(context, product, uri, products, false);
    }
}
