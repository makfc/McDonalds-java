package com.mcdonalds.sdk.services.data.sync;

import java.io.Serializable;

public enum CatalogVersionType implements Serializable {
    StaticData(1, "StaticData"),
    Restaurants(2, "Restaurants"),
    DisplayCategory(3, "DisplayCategory"),
    Facilities(4, "Facilities"),
    Recipes(5, "Recipes"),
    Names(6, "Names"),
    Promotion(7, "Promotion"),
    Products(8, "Products"),
    Language(9, "Language"),
    StoreFacility(10, "StoreFacility"),
    ProductPrices(11, "ProductPrices"),
    Availability(12, "Availability"),
    RecipePrices(13, "RecipePrices"),
    PaymentMethod(14, "PaymentMethod"),
    FeedbackTypeName(15, "FeedbackTypeName"),
    TenderType(16, "TenderType"),
    MenuType(17, "MenuType"),
    SocialMedia(18, "SocialMedia");
    
    private final int mIntegerValue;
    private final String mName;

    private CatalogVersionType(int value, String name) {
        this.mIntegerValue = value;
        this.mName = name;
    }

    public int getIntegerValue() {
        return this.mIntegerValue;
    }

    public String getName() {
        return this.mName;
    }

    public static CatalogVersionType fromValue(int value) {
        for (CatalogVersionType type : values()) {
            if (type.getIntegerValue() == value) {
                return type;
            }
        }
        return null;
    }
}
