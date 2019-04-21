package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderView extends AppModel implements Parcelable {
    public static final Creator<OrderView> CREATOR = new C40731();
    private String mCheckInCode;
    private boolean mConfirmationNeeded;
    private Double mDeliveryFee;
    private Date mEstimatedDeliveryTime;
    private List<FulfillmentFacilityOpeningHour> mFacilityOpeningHours;
    private boolean mIsLargeOrder;
    private String mNickName;
    private Date mOrderDate;
    private String mOrderDt;
    private String mOrderNumber;
    private String mOrderPaymentId;
    private OrderProductionResponse mOrderProductionResponse;
    private Double mOrderValue;
    private PaymentCard mPaymentCard;
    private List<ProductView> mProducts;
    private List<PromotionView> mPromotionalItems;
    private String mStoreID;
    private Double mTotalDiscount;
    private Double mTotalDue;
    private Integer mTotalEnergy;
    private Double mTotalTax;
    private Double mTotalValue;

    /* renamed from: com.mcdonalds.sdk.modules.models.OrderView$1 */
    static class C40731 implements Creator<OrderView> {
        C40731() {
        }

        public OrderView createFromParcel(Parcel source) {
            return new OrderView(source);
        }

        public OrderView[] newArray(int size) {
            return new OrderView[size];
        }
    }

    public String getOrderDt() {
        return this.mOrderDt;
    }

    public void setOrderDt(String mOrderDt) {
        this.mOrderDt = mOrderDt;
    }

    public String getStoreID() {
        return this.mStoreID;
    }

    public void setStoreID(String storeID) {
        this.mStoreID = storeID;
    }

    public String getNickName() {
        return this.mNickName;
    }

    public void setNickName(String nickName) {
        this.mNickName = nickName;
    }

    public String getOrderNumber() {
        return this.mOrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.mOrderNumber = orderNumber;
    }

    public Double getOrderValue() {
        return this.mOrderValue;
    }

    public void setOrderValue(Double orderValue) {
        this.mOrderValue = orderValue;
    }

    public Double getTotalValue() {
        return this.mTotalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.mTotalValue = totalValue;
    }

    public Double getTotalTax() {
        return this.mTotalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.mTotalTax = totalTax;
    }

    public Integer getTotalEnergy() {
        return this.mTotalEnergy;
    }

    public void setTotalEnergy(Integer totalEnergy) {
        this.mTotalEnergy = totalEnergy;
    }

    public Double getTotalDiscount() {
        return this.mTotalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.mTotalDiscount = totalDiscount;
    }

    public Double getTotalDue() {
        return this.mTotalDue;
    }

    public void setTotalDue(Double totalDue) {
        this.mTotalDue = totalDue;
    }

    public Double getDeliveryFee() {
        return this.mDeliveryFee;
    }

    public void setDeliveryFee(Double mDeliveryFee) {
        this.mDeliveryFee = mDeliveryFee;
    }

    public List<ProductView> getProducts() {
        return this.mProducts;
    }

    public void setProducts(List<ProductView> products) {
        this.mProducts = products;
    }

    public OrderProductionResponse getOrderProductionResponse() {
        return this.mOrderProductionResponse;
    }

    public void setOrderProductionResponse(OrderProductionResponse orderProductionResponse) {
        this.mOrderProductionResponse = orderProductionResponse;
    }

    public PaymentCard getPaymentCard() {
        return this.mPaymentCard;
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        this.mPaymentCard = paymentCard;
    }

    public List<PromotionView> getPromotionalItems() {
        return this.mPromotionalItems;
    }

    public void setPromotionalItems(List<PromotionView> promotionalItems) {
        this.mPromotionalItems = promotionalItems;
    }

    public void setPromotions(List<PromotionView> promotionsList) {
        this.mPromotionalItems = promotionsList;
    }

    public Date getOrderDate() {
        return this.mOrderDate;
    }

    public void setOrderDate(Date mOrderDate) {
        this.mOrderDate = mOrderDate;
    }

    public Date getEstimatedDeliveryTime() {
        return this.mEstimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(Date mEstimatedDeliveryTime) {
        this.mEstimatedDeliveryTime = mEstimatedDeliveryTime;
    }

    public boolean isIsLargeOrder() {
        return this.mIsLargeOrder;
    }

    public void setIsLargeOrder(boolean value) {
        this.mIsLargeOrder = value;
    }

    public boolean isConfirmationNeeded() {
        return this.mConfirmationNeeded;
    }

    public void setConfirmationNeeded(boolean value) {
        this.mConfirmationNeeded = value;
    }

    public int getProductValidationErrorCode(OrderProduct orderProduct) {
        for (ProductView productView : getProducts()) {
            if (orderProduct.equals(productView, true)) {
                return productView.getValidationErrorCode().intValue();
            }
            ProductView subProductView = productView.getSubProductView(orderProduct);
            if (subProductView != null) {
                return subProductView.getValidationErrorCode().intValue();
            }
        }
        return 1;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        long time;
        long j = -1;
        dest.writeString(this.mStoreID);
        dest.writeString(this.mNickName);
        dest.writeString(this.mOrderNumber);
        dest.writeValue(this.mOrderValue);
        dest.writeValue(this.mTotalValue);
        dest.writeValue(this.mTotalTax);
        dest.writeValue(this.mTotalEnergy);
        dest.writeValue(this.mTotalDiscount);
        dest.writeValue(this.mTotalDue);
        dest.writeValue(this.mDeliveryFee);
        dest.writeList(this.mProducts);
        dest.writeParcelable(this.mOrderProductionResponse, 0);
        dest.writeParcelable(this.mPaymentCard, 0);
        dest.writeList(this.mPromotionalItems);
        if (this.mOrderDate != null) {
            time = this.mOrderDate.getTime();
        } else {
            time = -1;
        }
        dest.writeLong(time);
        if (this.mEstimatedDeliveryTime != null) {
            j = this.mEstimatedDeliveryTime.getTime();
        }
        dest.writeLong(j);
        dest.writeValue(Boolean.valueOf(this.mIsLargeOrder));
        dest.writeValue(Boolean.valueOf(this.mConfirmationNeeded));
    }

    protected OrderView(Parcel in) {
        Date date = null;
        this.mStoreID = in.readString();
        this.mNickName = in.readString();
        this.mOrderNumber = in.readString();
        this.mOrderValue = (Double) in.readValue(Double.class.getClassLoader());
        this.mTotalValue = (Double) in.readValue(Double.class.getClassLoader());
        this.mTotalTax = (Double) in.readValue(Double.class.getClassLoader());
        this.mTotalEnergy = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mTotalDiscount = (Double) in.readValue(Double.class.getClassLoader());
        this.mTotalDue = (Double) in.readValue(Double.class.getClassLoader());
        this.mDeliveryFee = (Double) in.readValue(Double.class.getClassLoader());
        this.mProducts = new ArrayList();
        in.readList(this.mProducts, ProductView.class.getClassLoader());
        this.mOrderProductionResponse = (OrderProductionResponse) in.readParcelable(OrderProductionResponse.class.getClassLoader());
        this.mPaymentCard = (PaymentCard) in.readParcelable(PaymentCard.class.getClassLoader());
        this.mPromotionalItems = new ArrayList();
        in.readList(this.mPromotionalItems, PromotionView.class.getClassLoader());
        long tmpMOrderDate = in.readLong();
        this.mOrderDate = tmpMOrderDate == -1 ? null : new Date(tmpMOrderDate);
        long tmpMEstimatedDeliveryTime = in.readLong();
        if (tmpMEstimatedDeliveryTime != -1) {
            date = new Date(tmpMEstimatedDeliveryTime);
        }
        this.mEstimatedDeliveryTime = date;
    }

    public String getCheckInCode() {
        return this.mCheckInCode;
    }

    public void setCheckInCode(String checkInCode) {
        this.mCheckInCode = checkInCode;
    }

    public void setFacilityOpeningHours(List<FulfillmentFacilityOpeningHour> facilityOpeningHours) {
        this.mFacilityOpeningHours = facilityOpeningHours;
    }

    public List<FulfillmentFacilityOpeningHour> getFacilityOpeningHours() {
        return this.mFacilityOpeningHours;
    }

    public void setOrderPaymentId(String orderPaymentId) {
        this.mOrderPaymentId = orderPaymentId;
    }

    public String getOrderPaymentId() {
        return this.mOrderPaymentId;
    }
}
