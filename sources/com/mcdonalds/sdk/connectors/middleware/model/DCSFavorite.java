package com.mcdonalds.sdk.connectors.middleware.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import com.mcdonalds.sdk.modules.models.FavoriteItem.FavoriteProductType;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class DCSFavorite {
    public static final String SOURCE_ID = "MOT";
    public static final String TYPE_ITEM = "Item";
    public static final String TYPE_ITEM_ID = "2";
    public static final String TYPE_LOCATION = "Location";
    public static final String TYPE_LOCATION_ID = "3";
    public static final String TYPE_ORDER = "Order";
    public static final String TYPE_ORDER_ID = "1";
    @SerializedName("details")
    public DCSFavoriteDetailsList details;
    @SerializedName("favoriteId")
    public String favoriteId;
    @SerializedName("sourceId")
    public String sourceId = "MOT";
    @SerializedName("type")
    public String type;

    public static class DCSFavoriteDetails {
        @SerializedName("Data")
        public List<DCSProduct> data;
        @SerializedName("Name")
        public String name;
        @SerializedName("StoreNumber")
        public int storeNumber;

        @NonNull
        public static DCSFavoriteDetails fromFavoriteItem(FavoriteItem item) {
            DCSFavoriteDetails details = new DCSFavoriteDetails();
            details.name = item.getName();
            List<CustomerOrderProduct> products = item.getProducts();
            if (!ListUtils.isEmpty(products)) {
                details.data = new ArrayList();
                for (CustomerOrderProduct customerOrderProduct : products) {
                    details.data.add(DCSProduct.fromCustomerOrderProduct(customerOrderProduct));
                }
            }
            return details;
        }

        public boolean equals(Object o) {
            boolean z = true;
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            DCSFavoriteDetails details = (DCSFavoriteDetails) o;
            if (this.name != null) {
                if (!this.name.equals(details.name)) {
                    return false;
                }
            } else if (details.name == null) {
                return false;
            }
            if (this.data != null) {
                return this.data.equals(details.data);
            }
            if (this.storeNumber != details.storeNumber) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            int result = this.name != null ? this.name.hashCode() : 0;
            if (this.data != null) {
                return (result * 31) + this.data.hashCode();
            }
            return (result * 31) + this.storeNumber;
        }
    }

    public static class DCSFavoriteDetailsList extends HashSet<DCSFavoriteDetails> {
        public boolean add(DCSFavoriteDetails dcsFavoriteDetails) {
            if (dcsFavoriteDetails.data == null && contains(dcsFavoriteDetails)) {
                remove(dcsFavoriteDetails);
            }
            super.add(dcsFavoriteDetails);
            return true;
        }
    }

    public static List<DCSFavorite> fromFavoriteItems(List<FavoriteItem> items) {
        DCSFavorite dcsFavoriteItem = new DCSFavorite();
        dcsFavoriteItem.favoriteId = "2";
        dcsFavoriteItem.type = TYPE_ITEM;
        DCSFavoriteDetailsList itemDetailsList = new DCSFavoriteDetailsList();
        DCSFavorite dcsFavoriteOrder = new DCSFavorite();
        dcsFavoriteOrder.favoriteId = "1";
        dcsFavoriteOrder.type = TYPE_ORDER;
        DCSFavoriteDetailsList orderDetailsList = new DCSFavoriteDetailsList();
        for (FavoriteItem item : items) {
            DCSFavoriteDetails details = DCSFavoriteDetails.fromFavoriteItem(item);
            if (item.getType().equals(FavoriteProductType.FAVORITE_PRODUCT_TYPE_ITEM)) {
                itemDetailsList.add(details);
            } else {
                orderDetailsList.add(details);
            }
        }
        dcsFavoriteItem.details = itemDetailsList;
        dcsFavoriteOrder.details = orderDetailsList;
        return Arrays.asList(new DCSFavorite[]{dcsFavoriteItem, dcsFavoriteOrder});
    }

    public List<FavoriteItem> toFavoriteItems() {
        if (!this.type.equals(TYPE_ITEM) && !this.type.equals(TYPE_ORDER)) {
            return null;
        }
        List<FavoriteItem> favoriteItems = new ArrayList();
        Iterator it = this.details.iterator();
        while (it.hasNext()) {
            FavoriteProductType favoriteProductType;
            DCSFavoriteDetails favoriteDetails = (DCSFavoriteDetails) it.next();
            FavoriteItem item = new FavoriteItem();
            if (this.type.equals(TYPE_ITEM)) {
                favoriteProductType = FavoriteProductType.FAVORITE_PRODUCT_TYPE_ITEM;
            } else {
                favoriteProductType = FavoriteProductType.FAVORITE_PRODUCT_TYPE_ORDER;
            }
            item.setType(favoriteProductType);
            item.setName(favoriteDetails.name);
            if (!ListUtils.isEmpty(favoriteDetails.data)) {
                List<CustomerOrderProduct> products = new ArrayList();
                for (DCSProduct dcsProduct : favoriteDetails.data) {
                    products.add(dcsProduct.toCustomerOrderProduct());
                }
                item.setProducts(products);
            }
            item.setFavoriteId(Integer.valueOf(0));
            favoriteItems.add(item);
        }
        return favoriteItems;
    }

    public List<Store> toStores() {
        if (!this.type.equals(TYPE_LOCATION)) {
            return null;
        }
        List<Store> favoriteStores = new ArrayList();
        Iterator it = this.details.iterator();
        while (it.hasNext()) {
            DCSFavoriteDetails favoriteDetails = (DCSFavoriteDetails) it.next();
            Store store = new Store();
            store.setStoreId(favoriteDetails.storeNumber);
            store.setStoreFavoriteName(favoriteDetails.name);
            store.setStoreFavoriteId(Integer.valueOf(favoriteDetails.storeNumber));
            favoriteStores.add(store);
        }
        return favoriteStores;
    }
}
