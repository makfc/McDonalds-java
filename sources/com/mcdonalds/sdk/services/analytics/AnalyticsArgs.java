package com.mcdonalds.sdk.services.analytics;

import android.util.SparseArray;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.storelocator.StoreLocatorModule;
import com.mcdonalds.sdk.services.analytics.conversionmaster.Action;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AnalyticsArgs extends HashMap<String, Object> {
    public static final String DATAKEY_ACTION = "ACTION";
    public static final String DATAKEY_BUSINESS = "DATAKEY_BUSINESS";
    public static final String DATAKEY_CATEGORY = "CATEGORY";
    public static final String DATAKEY_CONVERSION_MASTER = "CONVERSION_MASTER";
    public static final String DATAKEY_CUSTOM = "DATAKEY_CUSTOM";
    public static final String DATAKEY_IMPRESSION = "IMPRESSION";
    public static final String DATAKEY_JICE = "JICE";
    public static final String DATAKEY_LABEL = "LABEL";
    public static final String DATAKEY_THROWABLE = "DATAKEY_THROWABLE";
    public static final String DATAKEY_VALUE = "VALUE";
    public static final String ECOMMERCE_PROMOTION = "In app offers";
    public static final String PRODUCT_LIST = "PRODUCT_LIST";
    public static final String TRANSACTION_AFFILIATION = "affliation";
    public static final String TRANSACTION_CURRENCY = "CurrencyCode";
    public static final String TRANSACTION_ID = "transactionId";
    public static final String TRANSACTION_ITEM_CATEGORY = "category";
    public static final String TRANSACTION_ITEM_CURRENCY = "currency";
    public static final String TRANSACTION_ITEM_NAME = "name";
    public static final String TRANSACTION_ITEM_PRICE = "price";
    public static final String TRANSACTION_ITEM_QUANTITY = "quantity";
    public static final String TRANSACTION_ITEM_SKU = "sku";
    public static final String TRANSACTION_MAP = "TRANSACTION_MAP";
    public static final String TRANSACTION_PRODUCTS = "transactionProducts";
    public static final String TRANSACTION_REVENUE = "revenue";
    public static final String TRANSACTION_SHIPPING = "Shipping";
    public static final String TRANSACTION_TAX = "Tax";
    public static final String TRANSACTION_TOTAL = "transactionTotal";

    public static final class ArgBuilder {
        private AnalyticsArgs args = new AnalyticsArgs();

        public AnalyticsArgs build() {
            return this.args;
        }

        public ArgBuilder setMapping(String key, Object value) {
            if (value != null) {
                this.args.put(key, value);
            }
            return this;
        }

        public ArgBuilder setThrowable(Throwable exception) {
            return setMapping(AnalyticsArgs.DATAKEY_THROWABLE, exception);
        }

        public ArgBuilder setCategory(String category) {
            return setMapping(AnalyticsArgs.DATAKEY_CATEGORY, category);
        }

        public ArgBuilder setAction(String action) {
            return setMapping("ACTION", action);
        }

        public ArgBuilder setLabel(String label) {
            return setMapping(AnalyticsArgs.DATAKEY_LABEL, label);
        }

        public ArgBuilder setValue(Long value) {
            return setMapping(AnalyticsArgs.DATAKEY_VALUE, value);
        }

        public ArgBuilder setValue(String value) {
            return setMapping(AnalyticsArgs.DATAKEY_VALUE, value);
        }

        public ArgBuilder setCustom(Object custom) {
            return setMapping(AnalyticsArgs.DATAKEY_CUSTOM, custom);
        }

        public ArgBuilder addCustom(int cd, String value) {
            SparseArray<String> custom = this.args.containsKey(AnalyticsArgs.DATAKEY_CUSTOM) ? (SparseArray) this.args.get(AnalyticsArgs.DATAKEY_CUSTOM) : new SparseArray();
            custom.put(cd, value);
            return setMapping(AnalyticsArgs.DATAKEY_CUSTOM, custom);
        }

        public ArgBuilder setBusiness(Object business) {
            return setMapping(AnalyticsArgs.DATAKEY_BUSINESS, business);
        }

        public ArgBuilder setTransactionMap(Map<String, Object> transactionMap) {
            return setMapping(AnalyticsArgs.TRANSACTION_MAP, transactionMap);
        }

        public ArgBuilder setProductList(List<Map<String, Object>> productList) {
            return setMapping(AnalyticsArgs.PRODUCT_LIST, productList);
        }

        public ArgBuilder setECommercePromotion(Offer offer) {
            return setMapping(AnalyticsArgs.DATAKEY_IMPRESSION, offer);
        }

        public static String getFormattedLocation() {
            StoreLocatorModule storeLocatorModule = (StoreLocatorModule) ModuleManager.getModule(StoreLocatorModule.NAME);
            try {
                if (LocationServicesManager.getInstance().getCurrentUserLocation() == null) {
                    return null;
                }
                return String.format("%f,%f", new Object[]{Double.valueOf(LocationServicesManager.getInstance().getCurrentUserLocation().getLatitude()), Double.valueOf(LocationServicesManager.getInstance().getCurrentUserLocation().getLongitude())});
            } catch (IllegalStateException e) {
                return null;
            }
        }

        public ArgBuilder setJice(Map<String, ?> map) {
            return setMapping(AnalyticsArgs.DATAKEY_JICE, map);
        }

        public ArgBuilder setConversionMaster(Action a) {
            return setMapping(AnalyticsArgs.DATAKEY_CONVERSION_MASTER, a);
        }
    }

    public static class CustomDimensions {
        public static final int BasketItems = 27;
        public static final int BuildNumber = 15;
        public static final int CarouselItemSelection = 50;
        public static final int Communications = 35;
        public static final int ConfigurationSelection = 46;
        public static final int CostChange = 54;
        public static final int CountryID = 44;
        public static final int CustomerEmail = 3;
        public static final int CustomerID = 2;
        public static final int CustomerNFCPayOptIn = 36;
        public static final int CustomerOffersOpt = 34;
        public static final int CustomerZip = 33;
        public static final int CustomizationItem = 37;
        public static final int CustomizationType = 38;
        public static final int CustomizationValue = 39;
        public static final int EmojiPageSelection = 52;
        public static final int EnvironmentId = 14;
        public static final int FavoriteCategory = 30;
        public static final int FavoriteItem = 31;
        public static final int FavoriteName = 32;
        public static final int FavoriteOrder = 12;
        public static final int Feedback = 11;
        public static final int FirstItemBasketTimestamp = 45;
        public static final int FoodOrderCategory = 20;
        public static final int FoodOrderItem = 21;
        public static final int LatLong = 6;
        public static final int MainMenuSelection = 51;
        public static final int MarketID = 4;
        public static final int MenuDayPart = 23;
        public static final int NutritionCalories = 8;
        public static final int NutritionCategory = 28;
        public static final int NutritionItem = 29;
        public static final int Offer = 25;
        public static final int OfferCategory = 24;
        public static final int OrderSubTotal = 42;
        public static final int PaymentMethodID = 55;
        public static final int QRCode = 41;
        public static final int QRScanLocation = 5;
        public static final int Rating = 10;
        public static final int RestaurantMenuViewType = 18;
        public static final int RestaurantSearchFilter = 26;
        public static final int RestaurantSearchLocation = 19;
        public static final int RestaurantSelection = 53;
        public static final int RestaurantType = 9;
        public static final int ScreenPath = 13;
        public static final int ShoppingMode = 40;
        public static final int SignedInState = 22;
        public static final int StoreID = 1;
        public static final int TimeStamp = 7;
        public static final int UserLanguage = 16;
        public static final int nmUsers = 17;
    }

    public static final class ProductMapBuilder {
        private Map<String, Object> mProductMap = new HashMap();

        public ProductMapBuilder setTransactionId(String transactionId) {
            this.mProductMap.put(AnalyticsArgs.TRANSACTION_ID, transactionId);
            return this;
        }

        public ProductMapBuilder setTransactionItemName(String transactionItemName) {
            this.mProductMap.put("name", transactionItemName);
            return this;
        }

        public ProductMapBuilder setTransactionSKU(String transactionSKU) {
            this.mProductMap.put(AnalyticsArgs.TRANSACTION_ITEM_SKU, transactionSKU);
            return this;
        }

        public ProductMapBuilder setTransactionCategory(String transactionCategory) {
            this.mProductMap.put("category", transactionCategory);
            return this;
        }

        public ProductMapBuilder setTransactionPrice(Double transactionPrice) {
            this.mProductMap.put(AnalyticsArgs.TRANSACTION_ITEM_PRICE, transactionPrice);
            return this;
        }

        public ProductMapBuilder setTransactionQuantity(Integer transactionQuantity) {
            this.mProductMap.put(AnalyticsArgs.TRANSACTION_ITEM_QUANTITY, transactionQuantity);
            return this;
        }

        public ProductMapBuilder setTransactionCurrency(String currency) {
            this.mProductMap.put(AnalyticsArgs.TRANSACTION_ITEM_CURRENCY, currency);
            return this;
        }

        public Map<String, Object> build() {
            return this.mProductMap;
        }
    }

    public static final class TransactionMapBuilder {
        private Map<String, Object> mTransactionMap = new HashMap();

        public TransactionMapBuilder setTransactionId(String transactionId) {
            this.mTransactionMap.put(AnalyticsArgs.TRANSACTION_ID, transactionId);
            return this;
        }

        public TransactionMapBuilder setTransactionAffiliation(String transactionAffiliation) {
            this.mTransactionMap.put(AnalyticsArgs.TRANSACTION_AFFILIATION, transactionAffiliation);
            return this;
        }

        public TransactionMapBuilder setTransactionRevenue(Double transactionRevenue) {
            this.mTransactionMap.put(AnalyticsArgs.TRANSACTION_REVENUE, transactionRevenue);
            return this;
        }

        public TransactionMapBuilder setTransactionTax(Double transactionTax) {
            this.mTransactionMap.put(AnalyticsArgs.TRANSACTION_TAX, transactionTax);
            return this;
        }

        public TransactionMapBuilder setTransactionShipping(Double transactionShipping) {
            this.mTransactionMap.put(AnalyticsArgs.TRANSACTION_SHIPPING, transactionShipping);
            return this;
        }

        public TransactionMapBuilder setTransactionCurrencyCode(String transactionCurrencyCode) {
            this.mTransactionMap.put(AnalyticsArgs.TRANSACTION_CURRENCY, transactionCurrencyCode);
            return this;
        }

        public TransactionMapBuilder setTransactionTotal(double total) {
            this.mTransactionMap.put(AnalyticsArgs.TRANSACTION_TOTAL, Double.valueOf(total));
            return this;
        }

        public Map<String, Object> build() {
            return this.mTransactionMap;
        }
    }
}
