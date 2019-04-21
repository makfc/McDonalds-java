package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.FavoriteItem;
import java.util.ArrayList;
import java.util.List;

public class MWCustomerFavoriteProductResponse {
    @SerializedName("FavoriteID")
    private Integer favoriteId;
    @SerializedName("Name")
    private String name;
    @SerializedName("Data")
    private List<MWProductView> products;
    @SerializedName("Type")
    private int type;

    public enum MWCustomerFavoriteProductType {
        CUSTOMER_FAVORITE_PRODUCT_TYPE_ITEM(1),
        CUSTOMER_FAVORITE_PRODUCT_TYPE_ORDER(2),
        CUSTOMER_FAVORITE_PRODUCT_TYPE_LOCATION(3);
        
        private int mTypeValue;

        private MWCustomerFavoriteProductType(int type) {
            this.mTypeValue = type;
        }

        public static MWCustomerFavoriteProductType getType(int index) {
            switch (index) {
                case 1:
                    return CUSTOMER_FAVORITE_PRODUCT_TYPE_ITEM;
                case 2:
                    return CUSTOMER_FAVORITE_PRODUCT_TYPE_ORDER;
                case 3:
                    return CUSTOMER_FAVORITE_PRODUCT_TYPE_LOCATION;
                default:
                    return null;
            }
        }

        public int getTypeValue() {
            return this.mTypeValue;
        }
    }

    public static FavoriteItem toFavoriteItem(MWCustomerFavoriteProductResponse ecpFavoriteItem) {
        FavoriteItem ret = new FavoriteItem();
        ret.setFavoriteId(ecpFavoriteItem.getFavoriteId());
        ret.setType(ecpFavoriteItem.getType().getTypeValue());
        ret.setName(ecpFavoriteItem.getName());
        List<CustomerOrderProduct> products = new ArrayList();
        if (ecpFavoriteItem.getProducts() != null) {
            List<MWProductView> ecpFavoriteItemProducts = ecpFavoriteItem.getProducts();
            int size = ecpFavoriteItemProducts.size();
            for (int i = 0; i < size; i++) {
                products.add(((MWProductView) ecpFavoriteItemProducts.get(i)).toCustomerOrderProduct());
            }
        }
        ret.setProducts(products);
        return ret;
    }

    public static List<FavoriteItem> toFavoriteItemList(List<MWCustomerFavoriteProductResponse> ecpCustomerFavoriteProductResponses) {
        int size = ecpCustomerFavoriteProductResponses.size();
        List<FavoriteItem> favoriteItemList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            favoriteItemList.add(toFavoriteItem((MWCustomerFavoriteProductResponse) ecpCustomerFavoriteProductResponses.get(i)));
        }
        return favoriteItemList;
    }

    public Integer getFavoriteId() {
        return this.favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public MWCustomerFavoriteProductType getType() {
        return MWCustomerFavoriteProductType.getType(this.type);
    }

    public void setType(MWCustomerFavoriteProductType type) {
        this.type = type.getTypeValue();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MWProductView> getProducts() {
        return this.products;
    }

    public void setProducts(List<MWProductView> products) {
        this.products = products;
    }
}
