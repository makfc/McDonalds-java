package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.modules.models.ProductView;
import com.mcdonalds.sdk.modules.models.PromotionView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MWPromotionView {
    @SerializedName("Action")
    public MWPromotionAction action;
    @SerializedName("Id")
    /* renamed from: id */
    public int f6073id;
    @SerializedName("ProductSets")
    public List<MWPromotionProductSet> promotionProductSets;
    @SerializedName("Type")
    public int type;
    @SerializedName("ValidationErrorCode")
    public int validationCode;

    public static PromotionView toPromotionView(MWPromotionView mwPromotionView) {
        PromotionView promotion = new PromotionView();
        promotion.setPromotionId(Integer.valueOf(mwPromotionView.f6073id));
        promotion.setValidationErrorCode(Integer.valueOf(mwPromotionView.validationCode));
        ArrayList<ProductView> productViews = new ArrayList();
        for (MWPromotionProductSet mwPromotionProductSet : mwPromotionView.promotionProductSets) {
            for (MWProductView mwProductView : mwPromotionProductSet.products) {
                productViews.add(MWProductView.toProductView(mwProductView));
            }
        }
        promotion.setProductSet(productViews);
        return promotion;
    }

    public static MWPromotionView fromOrderOffer(OrderOffer orderOffer) {
        Offer offer = orderOffer.getOffer();
        MWPromotionView promotionView = new MWPromotionView();
        promotionView.f6073id = offer.getOfferId().intValue();
        promotionView.type = 2;
        promotionView.promotionProductSets = new ArrayList();
        if (orderOffer.getOrderOfferProducts() != null) {
            for (OrderOfferProduct product : orderOffer.getOrderOfferProducts()) {
                MWPromotionProductSet productSet = new MWPromotionProductSet();
                productSet.alias = product.getOfferProduct().getAlias();
                productSet.quantity = product.getSelectedProductOption().getQuantity();
                productSet.products = Arrays.asList(new MWProductView[]{MWProductView.fromOrderProduct(product.getSelectedProductOption())});
                promotionView.promotionProductSets.add(productSet);
            }
        }
        return promotionView;
    }
}
