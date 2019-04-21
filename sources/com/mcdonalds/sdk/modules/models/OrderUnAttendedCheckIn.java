package com.mcdonalds.sdk.modules.models;

import com.mcdonalds.sdk.modules.AppModel;
import java.util.ArrayList;
import java.util.List;

public class OrderUnAttendedCheckIn extends AppModel {
    private List<OrderPayment> mAdditionalPayments = new ArrayList();
    private String mCheckInData;
    private OrderPayment mOrderPayment;
    private boolean mPriceChaged = false;
    private Integer mPriceType;
    private String mStoreID;

    public OrderPayment getOrderPayment() {
        return this.mOrderPayment;
    }

    public void setOrderPayment(OrderPayment orderPayment) {
        this.mOrderPayment = orderPayment;
    }

    public String getStoreID() {
        return this.mStoreID;
    }

    public void setStoreID(String storeID) {
        this.mStoreID = storeID;
    }

    public String getCheckInData() {
        return this.mCheckInData;
    }

    public void setCheckInData(String checkInData) {
        this.mCheckInData = checkInData;
    }

    public Integer getPriceType() {
        return this.mPriceType;
    }

    public void setPriceType(Integer priceType) {
        this.mPriceType = priceType;
    }

    public boolean isPriceChaged() {
        return this.mPriceChaged;
    }

    public void setPriceChanged(boolean mPriceChaged) {
        this.mPriceChaged = mPriceChaged;
    }

    public List<OrderPayment> getAdditionalPayments() {
        return this.mAdditionalPayments;
    }

    public void setAdditionalPayments(List<OrderPayment> additionalPayments) {
        this.mAdditionalPayments = additionalPayments;
    }
}
