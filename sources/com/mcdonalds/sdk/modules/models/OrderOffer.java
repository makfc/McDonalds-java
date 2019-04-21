package com.mcdonalds.sdk.modules.models;

import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.AsyncCounter;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderOffer extends AppModel implements Parcelable {
    public static final Creator<OrderOffer> CREATOR = new C25847();
    private Offer mOffer;
    private List<OrderOfferProduct> mOrderOfferProducts;

    /* renamed from: com.mcdonalds.sdk.modules.models.OrderOffer$7 */
    static class C25847 implements Creator<OrderOffer> {
        C25847() {
        }

        public OrderOffer createFromParcel(Parcel source) {
            return new OrderOffer(source);
        }

        public OrderOffer[] newArray(int size) {
            return new OrderOffer[size];
        }
    }

    public static void createOrderOffer(Offer offer, boolean retrieveFullProductDetails, OrderingModule orderingModule, final AsyncListener<OrderOffer> listener) {
        final OrderOffer ret = new OrderOffer();
        ret.setOffer(offer);
        if (!retrieveFullProductDetails || offer.getProductSets().isEmpty()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    listener.onResponse(ret, null, null);
                }
            });
            return;
        }
        final AsyncCounter<OrderOfferProduct> asyncCounter = new AsyncCounter(offer.getProductSets().size(), new AsyncListener<List<OrderOfferProduct>>(ret) {
            final /* synthetic */ OrderOffer val$ret;

            public void onResponse(List<OrderOfferProduct> response, AsyncToken token, AsyncException exception) {
                if (exception == null) {
                    this.val$ret.setOrderOfferProducts(response);
                    listener.onResponse(this.val$ret, null, null);
                    return;
                }
                listener.onResponse(null, null, exception);
            }
        });
        for (OfferProduct offerProduct : offer.getProductSets()) {
            OrderOfferProduct.createOrderOfferProduct(offerProduct, orderingModule, new AsyncListener<OrderOfferProduct>() {
                public void onResponse(OrderOfferProduct response, AsyncToken token, AsyncException exception) {
                    if (exception == null) {
                        asyncCounter.success(response);
                    } else {
                        asyncCounter.error(exception);
                    }
                }
            });
        }
    }

    public OrderOffer clone() {
        OrderOffer newOrderOffer = new OrderOffer();
        newOrderOffer.setOffer(getOffer());
        List<OrderOfferProduct> newOrderOfferProducts = new ArrayList();
        if (getOrderOfferProducts() != null) {
            for (OrderOfferProduct orderOfferProduct : getOrderOfferProducts()) {
                newOrderOfferProducts.add(orderOfferProduct.clone());
            }
        }
        newOrderOffer.setOrderOfferProducts(newOrderOfferProducts);
        return newOrderOffer;
    }

    public void revalidate(OrderingModule orderingModule, final AsyncListener<Void> listener) {
        List<OrderOfferProduct> products = getOrderOfferProducts();
        if (products != null) {
            final AsyncCounter<Void> asyncCounter = new AsyncCounter(products.size(), new AsyncListener<List<Void>>() {
                public void onResponse(List<Void> list, AsyncToken token, AsyncException exception) {
                    listener.onResponse(null, null, exception);
                }
            });
            for (OrderOfferProduct offerOfferProduct : products) {
                offerOfferProduct.revalidate(orderingModule, new AsyncListener<Void>() {
                    public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                        if (exception == null) {
                            asyncCounter.success(response);
                        } else {
                            asyncCounter.error(exception);
                        }
                    }
                });
            }
            return;
        }
        listener.onResponse(null, null, null);
    }

    public Offer getOffer() {
        return this.mOffer;
    }

    public void setOffer(Offer offer) {
        this.mOffer = offer;
    }

    public List<OrderOfferProduct> getOrderOfferProducts() {
        return this.mOrderOfferProducts;
    }

    public void setOrderOfferProducts(List<OrderOfferProduct> orderOfferProducts) {
        this.mOrderOfferProducts = orderOfferProducts;
    }

    public double getTotalSecondaryEnergy() {
        double ret = 0.0d;
        for (OrderOfferProduct orderOfferProduct : getOrderOfferProducts()) {
            if (!(orderOfferProduct.getSelectedProductOption() == null || orderOfferProduct.getSelectedProductOption().getProductCode() == null)) {
                ret += orderOfferProduct.getSelectedProductOption().getTotalSecondaryEnergy();
            }
        }
        return ret;
    }

    public double getTotalEnergy() {
        double ret = 0.0d;
        for (OrderOfferProduct orderOfferProduct : getOrderOfferProducts()) {
            if (!(orderOfferProduct.getSelectedProductOption() == null || orderOfferProduct.getSelectedProductOption().getProductCode() == null)) {
                ret += orderOfferProduct.getSelectedProductOption().getTotalEnergy();
            }
        }
        return ret;
    }

    public double getTotalPrice(PriceType priceType) {
        double ret = 0.0d;
        if (getOrderOfferProducts() != null) {
            for (OrderOfferProduct product : getOrderOfferProducts()) {
                ret += product.getTotalValue(priceType).doubleValue();
            }
        }
        return ret;
    }

    public void reorderOfferOrderProductsForBuyNGetM(final PriceType priceType) {
        if (this.mOffer.isBuyNGetMOffer() && this.mOrderOfferProducts.size() >= 2) {
            List<OrderOfferProduct> allOfferOptions = new ArrayList();
            List<OrderOfferProduct> buyOfferOptions = new ArrayList();
            List<OrderOfferProduct> getOfferOptions = new ArrayList();
            List<OrderProduct> selectedOptions = new ArrayList();
            for (OrderOfferProduct orderOfferProduct : this.mOrderOfferProducts) {
                if (orderOfferProduct.getOfferProduct().isPromoItem().booleanValue()) {
                    getOfferOptions.add(orderOfferProduct);
                } else {
                    buyOfferOptions.add(orderOfferProduct);
                }
                selectedOptions.add(orderOfferProduct.getSelectedProductOption());
            }
            allOfferOptions.addAll(buyOfferOptions);
            allOfferOptions.addAll(getOfferOptions);
            final boolean allowDownCharge = OrderManager.getInstance().allowDownCharge();
            Collections.sort(selectedOptions, new Comparator<OrderProduct>() {
                public int compare(OrderProduct p1, OrderProduct p2) {
                    double totalPrice;
                    double d = 0.0d;
                    if (p1 != null) {
                        totalPrice = p1.getTotalPrice(priceType, allowDownCharge);
                    } else {
                        totalPrice = 0.0d;
                    }
                    Double value1 = Double.valueOf(totalPrice);
                    if (p2 != null) {
                        d = p2.getTotalPrice(priceType, allowDownCharge);
                    }
                    return Double.valueOf(d).compareTo(value1);
                }
            });
            int index = 0;
            while (index < selectedOptions.size()) {
                ((OrderOfferProduct) allOfferOptions.get(index)).setSelectedProductOption((OrderProduct) selectedOptions.get(index));
                index++;
            }
            while (index < allOfferOptions.size()) {
                ((OrderOfferProduct) allOfferOptions.get(index)).setSelectedProductOption(null);
                index++;
            }
        }
    }

    public String toString() {
        return "OrderOffer{mOffer=" + this.mOffer + ", mOrderOfferProducts=" + this.mOrderOfferProducts + "}";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mOffer, 0);
        dest.writeList(this.mOrderOfferProducts);
    }

    protected OrderOffer(Parcel in) {
        this.mOffer = (Offer) in.readParcelable(Offer.class.getClassLoader());
        this.mOrderOfferProducts = new ArrayList();
        in.readList(this.mOrderOfferProducts, OrderOfferProduct.class.getClassLoader());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderOffer that = (OrderOffer) o;
        if (this.mOffer != null) {
            return this.mOffer.equals(that.mOffer);
        }
        if (that.mOffer != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mOffer != null ? this.mOffer.hashCode() : 0;
    }
}
