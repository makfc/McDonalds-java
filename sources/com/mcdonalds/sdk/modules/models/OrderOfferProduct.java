package com.mcdonalds.sdk.modules.models;

import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.text.DecimalFormat;

public class OrderOfferProduct extends AppModel implements Parcelable {
    public static final Creator<OrderOfferProduct> CREATOR = new C40674();
    public static final String ITEM_REQUIRED_CODE = "Item required";
    public static final String KEY_HUNDRED_PERCENT_OFFERS_CONSIDERED_ZERO_PRICED_OFFERS = "modules.offers.100PercentOfferConsideredZeroPriceOffer";
    private OfferProduct mOfferProduct;
    private OrderProduct mSelectedProductOption;
    private Product mSubstituteProduct;

    /* renamed from: com.mcdonalds.sdk.modules.models.OrderOfferProduct$4 */
    static class C40674 implements Creator<OrderOfferProduct> {
        C40674() {
        }

        public OrderOfferProduct createFromParcel(Parcel source) {
            return new OrderOfferProduct(source);
        }

        public OrderOfferProduct[] newArray(int size) {
            return new OrderOfferProduct[size];
        }
    }

    public static void createOrderOfferProduct(OfferProduct offerProduct, OrderingModule orderingModule, AsyncListener<OrderOfferProduct> listener) {
        createOrderOfferProduct(offerProduct, orderingModule, true, listener);
    }

    public static void createOrderOfferProduct(final OfferProduct offerProduct, OrderingModule orderingModule, boolean autoSelectDefaultProductOption, final AsyncListener<OrderOfferProduct> listener) {
        final OrderOfferProduct ret = new OrderOfferProduct();
        ret.setOfferProduct(offerProduct);
        if (!(offerProduct.getAction() == null || offerProduct.getAction().getPriceFromCode() == null || offerProduct.getAction().getPriceFromCode().isEmpty())) {
            Product substituteProduct = orderingModule.getProductForExternalId(offerProduct.getAction().getPriceFromCode(), true);
            if (substituteProduct != null) {
                ret.setSubstituteProduct(substituteProduct);
            }
        }
        if (autoSelectDefaultProductOption && offerProduct.getProducts().size() == 1) {
            orderingModule.getProductForExternalId(((OfferProductOption) offerProduct.getProducts().get(0)).getProductCode(), new AsyncListener<Product>() {
                public void onResponse(Product product, AsyncToken token, AsyncException exception) {
                    ret.setSelectedProductOption(OrderProduct.createProduct(product, offerProduct.getQuantity()));
                    listener.onResponse(ret, null, null);
                }
            }, true);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    listener.onResponse(ret, null, null);
                }
            });
        }
    }

    public OrderOfferProduct clone() {
        OrderOfferProduct newOrderOfferProduct = new OrderOfferProduct();
        newOrderOfferProduct.setSelectedProductOption(OrderProduct.cloneProductForEditing(getSelectedProductOption()));
        newOrderOfferProduct.setSubstituteProduct(getSubstituteProduct());
        newOrderOfferProduct.setOfferProduct(getOfferProduct().clone());
        return newOrderOfferProduct;
    }

    public void revalidate(OrderingModule orderingModule, final AsyncListener<Void> listener) {
        final OrderProduct selectedOption = getSelectedProductOption();
        if (selectedOption != null) {
            orderingModule.getProductForExternalId(selectedOption.getProductCode(), new AsyncListener<Product>() {
                public void onResponse(Product product, AsyncToken token, AsyncException exception) {
                    OrderProduct orderProduct = OrderProduct.createProduct(product, Integer.valueOf(selectedOption.getQuantity()));
                    orderProduct.setCustomizations(selectedOption.getCustomizations());
                    orderProduct.setChoiceSolutions(selectedOption.getChoiceSolutions());
                    OrderOfferProduct.this.setSelectedProductOption(orderProduct);
                    listener.onResponse(null, null, null);
                }
            }, true);
        }
    }

    public OfferProduct getOfferProduct() {
        return this.mOfferProduct;
    }

    public void setOfferProduct(OfferProduct offerProduct) {
        this.mOfferProduct = offerProduct;
    }

    public OrderProduct getSelectedProductOption() {
        return this.mSelectedProductOption;
    }

    public void setSelectedProductOption(OrderProduct selectedProductOption) {
        this.mSelectedProductOption = selectedProductOption;
    }

    public Product getSubstituteProduct() {
        return this.mSubstituteProduct;
    }

    public void setSubstituteProduct(Product substituteProduct) {
        this.mSubstituteProduct = substituteProduct;
    }

    public boolean hasMultipleProducts() {
        return getOfferProduct().getProducts().size() > 1;
    }

    public Double getTotalValue(PriceType priceType) {
        boolean allowDownCharge = OrderManager.getInstance().allowDownCharge();
        if (this.mOfferProduct.getAction() == null) {
            return Double.valueOf(this.mSelectedProductOption.getTotalPrice(priceType, allowDownCharge));
        }
        switch (this.mOfferProduct.getAction().getOfferRedemptionType()) {
            case OFFER_REDEMPTION_TYPE_ABSOLUTE:
                if (this.mOfferProduct.getAction().getPriceFromCode() != null && !this.mOfferProduct.getAction().getPriceFromCode().isEmpty() && this.mSubstituteProduct != null) {
                    return Double.valueOf(this.mSubstituteProduct.getPrice(priceType));
                }
                if (this.mSelectedProductOption == null || this.mSelectedProductOption.getProduct() == null) {
                    return this.mOfferProduct.getAction().getValue();
                }
                return Double.valueOf(this.mOfferProduct.getAction().getValue().doubleValue() + this.mSelectedProductOption.getTotalCustomizationsPrice(priceType, allowDownCharge).doubleValue());
            case OFFER_REDEMPTION_TYPE_PERCENT:
                return Double.valueOf(calculateTotalWithPercent(priceType, allowDownCharge));
            case OFFER_REDEMPTION_TYPE_RELATIVE:
                return Double.valueOf(this.mSelectedProductOption.getTotalPrice(priceType, allowDownCharge) + this.mOfferProduct.getAction().getValue().doubleValue());
            default:
                return null;
        }
    }

    private double calculateTotalWithPercent(PriceType priceType, boolean allowDownCharge) {
        if (this.mSelectedProductOption == null || this.mSelectedProductOption.getProduct() == null) {
            return 0.0d;
        }
        double offerValue = this.mOfferProduct.getAction().getValue().doubleValue();
        if (consider100PercentOffersZeroPriceOffer() && offerValue == 0.0d) {
            return 0.0d;
        }
        double totalPrice = this.mSelectedProductOption.getTotalPrice(priceType, allowDownCharge);
        double customizationsPrice = this.mSelectedProductOption.getTotalCustomizationsPrice(priceType, allowDownCharge).doubleValue();
        return Double.valueOf(new DecimalFormat("#.##").format(((totalPrice - customizationsPrice) * (this.mOfferProduct.getAction().getValue().doubleValue() / 100.0d)) + customizationsPrice)).doubleValue();
    }

    private boolean consider100PercentOffersZeroPriceOffer() {
        return Configuration.getSharedInstance().getBooleanForKey(KEY_HUNDRED_PERCENT_OFFERS_CONSIDERED_ZERO_PRICED_OFFERS);
    }

    public boolean isBuyOneGetSame() {
        return ITEM_REQUIRED_CODE.equals(this.mOfferProduct.getCodesFromAlias());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mOfferProduct, 0);
        dest.writeParcelable(this.mSelectedProductOption, flags);
    }

    protected OrderOfferProduct(Parcel in) {
        this.mOfferProduct = (OfferProduct) in.readParcelable(OfferProduct.class.getClassLoader());
        this.mSelectedProductOption = (OrderProduct) in.readParcelable(OrderProduct.class.getClassLoader());
    }
}
