package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MWCustomerFavoriteData {
    @SerializedName("FavoriteId")
    private Integer favoriteId;
    @SerializedName("Name")
    private String name;
    @SerializedName("Data")
    private List<MWProductView> orderProducts;
    @SerializedName("Type")
    private int type;

    public enum MWCustomerFavoriteProductType {
        CUSTOMER_FAVORITE_PRODUCT_TYPE_ITEM(1),
        CUSTOMER_FAVORITE_PRODUCT_TYPE_ORDER(2);
        
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
                default:
                    return null;
            }
        }

        public int getTypeValue() {
            return this.mTypeValue;
        }
    }

    public MWCustomerFavoriteData(List<MWProductView> ecpCustomerFavoriteOrderProducts, boolean isProduct, String favoriteName) {
        setOrderProducts(ecpCustomerFavoriteOrderProducts);
        setFavoriteId(Integer.valueOf(0));
        setType(isProduct ? MWCustomerFavoriteProductType.CUSTOMER_FAVORITE_PRODUCT_TYPE_ITEM : MWCustomerFavoriteProductType.CUSTOMER_FAVORITE_PRODUCT_TYPE_ORDER);
        setName(favoriteName);
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

    public void setType(int type) {
        this.type = type;
    }

    public List<MWProductView> getOrderProducts() {
        return this.orderProducts;
    }

    public void setOrderProducts(List<MWProductView> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
