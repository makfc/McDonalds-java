package com.mcdonalds.sdk.connectors.middleware.model;

import android.content.Context;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.List;

public class MWCustomerFavoriteStoreItem {
    @SerializedName("FavoriteLocationID")
    public int favoriteLocationID;
    @SerializedName("Name")
    public String name;
    @SerializedName("RestaurantInformation")
    public MWRestaurant restaurant;
    @SerializedName("StoreNumber")
    public int storeNumber;

    public static Store toCustomerFavoriteStore(MWCustomerFavoriteStoreItem favoriteStoreItem, Context context) {
        if (favoriteStoreItem.restaurant == null) {
            return null;
        }
        Store favoriteStore = favoriteStoreItem.restaurant.toStore(context);
        favoriteStore.setStoreFavoriteId(Integer.valueOf(favoriteStoreItem.favoriteLocationID));
        favoriteStore.setStoreFavoriteName(favoriteStoreItem.name);
        return favoriteStore;
    }

    public static List<Store> toCustomerFavoriteStoreList(List<MWCustomerFavoriteStoreItem> favoriteStoreItems, Context context) {
        if (favoriteStoreItems == null) {
            return new ArrayList();
        }
        List<Store> favoriteStores = new ArrayList();
        for (MWCustomerFavoriteStoreItem favoriteStoreItem : favoriteStoreItems) {
            Store customerFavoriteStore = toCustomerFavoriteStore(favoriteStoreItem, context);
            if (customerFavoriteStore != null) {
                favoriteStores.add(customerFavoriteStore);
            }
        }
        return favoriteStores;
    }
}
