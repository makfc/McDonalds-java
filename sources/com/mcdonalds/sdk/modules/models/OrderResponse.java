package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderResponse implements Parcelable, Serializable {
    public static final Creator<OrderResponse> CREATOR = new C40711();
    public static final int DEFAULT_ORDER_VALIDATION_CODE = 1;
    public static final int OFFERS_ERROR = -8001;
    public static final int OFFERS_ERROR_DELIVERY_ONLY = -8002;
    public static final int OFFERS_ERROR_PICKUP_ONLY = -8003;
    public static final int ORDER_CAN_NOT_BE_PLACED = -6026;
    public static final int ORDER_IS_NOT_READY_CODE = 2010;
    public static final int ORDER_IS_NOT_READY_TO_ACCEPT_CODE = 47;
    public static final int ORDER_NO_PAYMENT_REGISTERED = -6057;
    public static final int ORDER_WITH_DIFFERENT_DAYPART_PRODUCTS_NOT_PERMITTED_CODE = -1075;
    public static final int PRODUCT_ITEM_TIME_RESTRICTION_NOT_COINCIDE_CODE = -1084;
    public static final int PRODUCT_OUT_OF_STOCK_CODE = -1036;
    public static final int PRODUCT_PRICE_CHANGED = -6027;
    public static final int PRODUCT_SELECT_ANOTHER_PAYMENT_METHOD = -6056;
    public static final int PRODUCT_TIME_RESTRICTION_CODE = -1080;
    public static final int PRODUCT_TIME_RESTRICTION_DAY_PART_CODE = -1078;
    public static final int PRODUCT_TIME_RESTRICTION_NOT_COINCIDE_CODE = -1077;
    public static final int PRODUCT_UNAVAILABLE_AT_RESTAURANT_CODE = -1035;
    public static final int PRODUCT_UNAVAILABLE_CODE = -1023;
    public static final int PROMOTION_NOT_AVAILABLE_CODE = -8015;
    public static final int ZERO_OR_NEGAVTIVE_PRICE_ERROR_CODE = -1606;
    private PointOfDistribution POD;
    private String mAlipayPublicKey;
    private String mAppId;
    private String mCheckInCode;
    private Double mDeliveryFee;
    private String mDisplayOrderNumber;
    private Date mEstimatedDeliveryTime;
    private List<FulfillmentFacilityOpeningHour> mFulfillmentFacilityOpeningHours;
    private String mMajor;
    private String mMdsOrderNumber;
    private String mMerchantId;
    private String mMerchantPrivateKey;
    private String mMinor;
    private String mNoncestr;
    private String mNotifyUrl;
    private Date mOrderDate;
    private String mOrderPaymentId;
    private Double mOrderValue;
    private OrderView mOrderView;
    private String mPackage;
    private String mPartnerId;
    private Integer mPaymentDataId;
    private String mPaymentUrl;
    private String mPrepayid;
    private List<Integer> mProductItemDayPartRestriction;
    private List<Integer> mProductItemNotCoincideRestriction;
    private List<Integer> mProductItemTimeRestriction;
    private List<Integer> mProductsOutOfStock;
    private List<Integer> mProductsTimeRestriction;
    private List<Integer> mProductsUnavailable;
    private List<Integer> mPromotionsError;
    private List<Integer> mPromotionsNotAvailable;
    private List<Integer> mPromotionsOutOfStock;
    private Boolean mRequiresCVV;
    private Boolean mRequiresPassword;
    private String mSellerId;
    private String mSign;
    private String mTimeStamp;
    private Double mTotalDiscount;
    private Double mTotalDue;
    private Integer mTotalEnergy;
    private Double mTotalTax;
    private Double mTotalValue;
    private List<Integer> promotionsTimeRestriction;
    private String signature;

    /* renamed from: com.mcdonalds.sdk.modules.models.OrderResponse$1 */
    static class C40711 implements Creator<OrderResponse> {
        C40711() {
        }

        public OrderResponse createFromParcel(Parcel source) {
            return new OrderResponse(source);
        }

        public OrderResponse[] newArray(int size) {
            return new OrderResponse[size];
        }
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSign() {
        return this.mSign;
    }

    public void setSign(String mSign) {
        this.mSign = mSign;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public void setAppId(String mAppId) {
        this.mAppId = mAppId;
    }

    public String getNoncestr() {
        return this.mNoncestr;
    }

    public void setNoncestr(String mNoncestr) {
        this.mNoncestr = mNoncestr;
    }

    public String getPrepayid() {
        return this.mPrepayid;
    }

    public void setPrepayid(String mPrepayid) {
        this.mPrepayid = mPrepayid;
    }

    public String getPackage() {
        return this.mPackage;
    }

    public void setPackage(String mPackage) {
        this.mPackage = mPackage;
    }

    public String getTimeStamp() {
        return this.mTimeStamp;
    }

    public void setTimeStamp(String mTimeStamp) {
        this.mTimeStamp = mTimeStamp;
    }

    public static OrderResponse fromTotalize(OrderView orderView) {
        OrderResponse ret = new OrderResponse();
        ret.setOrderView(orderView);
        ret.setTotalDue(orderView.getTotalDue());
        ret.setOrderDate(orderView.getOrderDate());
        ret.setTotalEnergy(orderView.getTotalEnergy());
        ret.setTotalTax(orderView.getTotalTax());
        ret.setTotalValue(orderView.getTotalValue());
        ret.setTotalDiscount(orderView.getTotalDiscount());
        ret.setDeliveryFee(orderView.getDeliveryFee());
        double orderValue = orderView.getOrderValue().doubleValue() + orderView.getTotalDiscount().doubleValue();
        if (!Configuration.getSharedInstance().getBooleanForKey("modules.ordering.shouldAddTaxToTotal")) {
            orderValue -= ret.getTotalTax().doubleValue();
        }
        ret.setOrderValue(Double.valueOf(orderValue));
        findErrorsForOrderView(orderView, ret);
        return ret;
    }

    public static OrderResponse fromCheckin(OrderView orderView) {
        OrderResponse ret = fromTotalize(orderView);
        OrderProductionResponse response = orderView.getOrderProductionResponse();
        if (response != null) {
            ret.setDisplayOrderNumber(response.getDisplayOrderNumber());
            ret.setMajor(response.getMajor());
            ret.setMinor(response.getMinor());
            ret.setMdsOrderNumber(response.getMdsOrderNumber());
            ret.setPOD(response.getPointOfDistribution());
        }
        return ret;
    }

    public static OrderResponse fromCheckout(OrderView orderView) {
        OrderResponse response = fromTotalize(orderView);
        response.setDisplayOrderNumber(orderView.getOrderNumber());
        response.setOrderDate(orderView.getOrderDate());
        response.setEstimatedDeliveryTime(orderView.getEstimatedDeliveryTime());
        return response;
    }

    public static OrderResponse fromFoundationalCheckIn(OrderView orderView) {
        OrderResponse ret = fromTotalize(orderView);
        ret.setCheckInCode(orderView.getCheckInCode());
        ret.setFulfillmentFacilityOpeningHours(orderView.getFacilityOpeningHours());
        ret.setDisplayOrderNumber(orderView.getOrderNumber());
        ret.setOrderPaymentId(orderView.getOrderPaymentId());
        if (orderView.getOrderProductionResponse() != null) {
            ret.setPOD(orderView.getOrderProductionResponse().getPointOfDistribution());
        }
        return ret;
    }

    public OrderView getOrderView() {
        return this.mOrderView;
    }

    public void setOrderView(OrderView orderView) {
        this.mOrderView = orderView;
    }

    public String getDisplayOrderNumber() {
        return this.mDisplayOrderNumber;
    }

    public void setDisplayOrderNumber(String displayOrderNumber) {
        this.mDisplayOrderNumber = displayOrderNumber;
    }

    public String getMajor() {
        return this.mMajor;
    }

    public void setMajor(String major) {
        this.mMajor = major;
    }

    public String getMinor() {
        return this.mMinor;
    }

    public void setMinor(String minor) {
        this.mMinor = minor;
    }

    public String getMdsOrderNumber() {
        return this.mMdsOrderNumber;
    }

    public void setMdsOrderNumber(String mdsOrderNumber) {
        this.mMdsOrderNumber = mdsOrderNumber;
    }

    public PointOfDistribution getPOD() {
        return this.POD;
    }

    public void setPOD(PointOfDistribution POD) {
        this.POD = POD;
    }

    public Double getOrderValue() {
        return this.mOrderValue;
    }

    public void setOrderValue(Double orderValue) {
        this.mOrderValue = orderValue;
    }

    public Double getTotalDue() {
        return this.mTotalDue;
    }

    public void setTotalDue(Double totalDue) {
        this.mTotalDue = totalDue;
    }

    public Integer getTotalEnergy() {
        return this.mTotalEnergy;
    }

    public void setTotalEnergy(Integer totalEnergy) {
        this.mTotalEnergy = totalEnergy;
    }

    public Double getTotalTax() {
        return this.mTotalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.mTotalTax = totalTax;
    }

    public Double getTotalValue() {
        return this.mTotalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.mTotalValue = totalValue;
    }

    public Double getTotalDiscount() {
        return this.mTotalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.mTotalDiscount = totalDiscount;
    }

    public Integer getPaymentDataId() {
        return this.mPaymentDataId;
    }

    public void setPaymentDataId(Integer paymentDataId) {
        this.mPaymentDataId = paymentDataId;
    }

    public String getOrderPaymentId() {
        return this.mOrderPaymentId;
    }

    public void setOrderPaymentId(String orderPaymentId) {
        this.mOrderPaymentId = orderPaymentId;
    }

    public Boolean getRequiresPassword() {
        return this.mRequiresPassword;
    }

    public void setRequiresPassword(Boolean requiresPassword) {
        this.mRequiresPassword = requiresPassword;
    }

    public Boolean getRequiresCVV() {
        return this.mRequiresCVV;
    }

    public void setRequiresCVV(Boolean requiresCVV) {
        this.mRequiresCVV = requiresCVV;
    }

    public Double getDeliveryFee() {
        return this.mDeliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.mDeliveryFee = deliveryFee;
    }

    public List<Integer> getProductsOutOfStock() {
        return this.mProductsOutOfStock;
    }

    public void setProductsOutOfStock(List<Integer> productsNotAvailable) {
        this.mProductsOutOfStock = productsNotAvailable;
    }

    public List<Integer> getProductsUnavailable() {
        return this.mProductsUnavailable;
    }

    public void setProductsUnavailable(List<Integer> mProductsUnavailable) {
        this.mProductsUnavailable = mProductsUnavailable;
    }

    public List<Integer> getPromotionsNotAvailable() {
        return this.mPromotionsNotAvailable;
    }

    public List<Integer> getPromotionsError() {
        return this.mPromotionsError;
    }

    public List<Integer> getProductsTimeRestriction() {
        return this.mProductsTimeRestriction;
    }

    public void setPromotionsNotAvailable(List<Integer> promotionsNotAvailable) {
        this.mPromotionsNotAvailable = promotionsNotAvailable;
    }

    public void setPromotionsError(List<Integer> promotionsError) {
        this.mPromotionsError = promotionsError;
    }

    public String getPaymentUrl() {
        return this.mPaymentUrl;
    }

    public void setPaymentUrl(String url) {
        this.mPaymentUrl = url;
    }

    public void setProductsTimeRestriction(List<Integer> productsTimeRestricion) {
        this.mProductsTimeRestriction = productsTimeRestricion;
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

    public List<Integer> getProductItemNotCoincideRestriction() {
        return this.mProductItemNotCoincideRestriction;
    }

    public void setProductItemNotCoincideRestriction(List<Integer> mProductItemNotCoincideRestriction) {
        this.mProductItemNotCoincideRestriction = mProductItemNotCoincideRestriction;
    }

    public List<Integer> getProductItemDayPartRestriction() {
        return this.mProductItemDayPartRestriction;
    }

    public void setProductItemDayPartRestriction(List<Integer> mProductItemDayPartRestriction) {
        this.mProductItemDayPartRestriction = mProductItemDayPartRestriction;
    }

    public List<Integer> getProductItemTimeRestriction() {
        return this.mProductItemTimeRestriction;
    }

    public void setProductItemTimeRestriction(List<Integer> mProductItemTimeRestriction) {
        this.mProductItemTimeRestriction = mProductItemTimeRestriction;
    }

    public String getPartnerId() {
        return this.mPartnerId;
    }

    public void setPartnerId(String partnerId) {
        this.mPartnerId = partnerId;
    }

    public String getMerchantPrivateKey() {
        return this.mMerchantPrivateKey;
    }

    public void setMerchantPrivateKey(String merchantPrivateKey) {
        this.mMerchantPrivateKey = merchantPrivateKey;
    }

    public String getAlipayPublicKey() {
        return this.mAlipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.mAlipayPublicKey = alipayPublicKey;
    }

    public String getNotifyUrl() {
        return this.mNotifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.mNotifyUrl = notifyUrl;
    }

    public String getSellerId() {
        return this.mSellerId;
    }

    public void setSellerId(String sellerId) {
        this.mSellerId = sellerId;
    }

    public String getMerchantId() {
        return this.mMerchantId;
    }

    public void setMerchantId(String merchantId) {
        this.mMerchantId = merchantId;
    }

    public int getErrorCode() {
        if (!getProductsOutOfStock().isEmpty()) {
            return PRODUCT_OUT_OF_STOCK_CODE;
        }
        if (!getProductsUnavailable().isEmpty()) {
            return PRODUCT_UNAVAILABLE_CODE;
        }
        if (!getPromotionsNotAvailable().isEmpty()) {
            return PROMOTION_NOT_AVAILABLE_CODE;
        }
        if (getProductsTimeRestriction().isEmpty() && getPromotionsTimeRestriction().isEmpty()) {
            return 0;
        }
        return PRODUCT_TIME_RESTRICTION_CODE;
    }

    public void setPromotionsTimeRestriction(List<Integer> promotionsTimeRestriction) {
        this.promotionsTimeRestriction = promotionsTimeRestriction;
    }

    public List<Integer> getPromotionsTimeRestriction() {
        return this.promotionsTimeRestriction;
    }

    public void setPromotionsOutOfStock(List<Integer> promotionsOutOfStock) {
        this.mPromotionsOutOfStock = promotionsOutOfStock;
    }

    public List<Integer> getPromotionsOutOfStock() {
        return this.mPromotionsOutOfStock;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        long j = -1;
        dest.writeParcelable(this.mOrderView, flags);
        dest.writeString(this.mDisplayOrderNumber);
        dest.writeString(this.mMajor);
        dest.writeString(this.mMinor);
        dest.writeString(this.mMdsOrderNumber);
        dest.writeString(this.mPaymentUrl);
        if (this.POD == null) {
            i = -1;
        } else {
            i = this.POD.ordinal();
        }
        dest.writeInt(i);
        dest.writeValue(this.mOrderValue);
        dest.writeValue(this.mTotalDue);
        dest.writeValue(this.mTotalEnergy);
        dest.writeValue(this.mTotalTax);
        dest.writeValue(this.mTotalValue);
        dest.writeValue(this.mTotalDiscount);
        dest.writeValue(this.mPaymentDataId);
        dest.writeString(this.mOrderPaymentId);
        dest.writeValue(this.mRequiresPassword);
        dest.writeValue(this.mRequiresCVV);
        dest.writeValue(this.mDeliveryFee);
        dest.writeList(this.mProductsOutOfStock);
        dest.writeList(this.mProductsUnavailable);
        dest.writeList(this.mPromotionsNotAvailable);
        dest.writeList(this.mProductItemTimeRestriction);
        dest.writeList(this.mProductItemDayPartRestriction);
        dest.writeList(this.mProductItemNotCoincideRestriction);
        dest.writeList(this.mPromotionsError);
        dest.writeList(this.mProductsTimeRestriction);
        dest.writeLong(this.mOrderDate != null ? this.mOrderDate.getTime() : -1);
        if (this.mEstimatedDeliveryTime != null) {
            j = this.mEstimatedDeliveryTime.getTime();
        }
        dest.writeLong(j);
    }

    protected OrderResponse(Parcel in) {
        PointOfDistribution pointOfDistribution;
        Date date = null;
        this.mOrderView = (OrderView) in.readParcelable(OrderView.class.getClassLoader());
        this.mDisplayOrderNumber = in.readString();
        this.mMajor = in.readString();
        this.mMinor = in.readString();
        this.mMdsOrderNumber = in.readString();
        this.mPaymentUrl = in.readString();
        int tmpPOD = in.readInt();
        if (tmpPOD == -1) {
            pointOfDistribution = null;
        } else {
            pointOfDistribution = PointOfDistribution.values()[tmpPOD];
        }
        this.POD = pointOfDistribution;
        this.mOrderValue = (Double) in.readValue(Double.class.getClassLoader());
        this.mTotalDue = (Double) in.readValue(Double.class.getClassLoader());
        this.mTotalEnergy = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mTotalTax = (Double) in.readValue(Double.class.getClassLoader());
        this.mTotalValue = (Double) in.readValue(Double.class.getClassLoader());
        this.mTotalDiscount = (Double) in.readValue(Double.class.getClassLoader());
        this.mPaymentDataId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mOrderPaymentId = in.readString();
        this.mRequiresPassword = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mRequiresCVV = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mDeliveryFee = (Double) in.readValue(Double.class.getClassLoader());
        this.mProductsOutOfStock = new ArrayList();
        in.readList(this.mProductsOutOfStock, Integer.class.getClassLoader());
        this.mProductsUnavailable = new ArrayList();
        in.readList(this.mProductsUnavailable, Integer.class.getClassLoader());
        this.mPromotionsNotAvailable = new ArrayList();
        in.readList(this.mPromotionsNotAvailable, Integer.class.getClassLoader());
        this.mPromotionsError = new ArrayList();
        in.readList(this.mPromotionsError, Integer.class.getClassLoader());
        this.mProductsTimeRestriction = new ArrayList();
        in.readList(this.mProductsTimeRestriction, Integer.class.getClassLoader());
        this.mProductItemTimeRestriction = new ArrayList();
        in.readList(this.mProductItemTimeRestriction, Integer.class.getClassLoader());
        this.mProductItemDayPartRestriction = new ArrayList();
        in.readList(this.mProductItemDayPartRestriction, Integer.class.getClassLoader());
        this.mProductItemNotCoincideRestriction = new ArrayList();
        in.readList(this.mProductItemNotCoincideRestriction, Integer.class.getClassLoader());
        long tmpMOrderDate = in.readLong();
        this.mOrderDate = tmpMOrderDate == -1 ? null : new Date(tmpMOrderDate);
        long tmpMEstimatedDeliveryTime = in.readLong();
        if (tmpMEstimatedDeliveryTime != -1) {
            date = new Date(tmpMEstimatedDeliveryTime);
        }
        this.mEstimatedDeliveryTime = date;
    }

    private static boolean fillErrorCodes(ProductView pv, List<Integer> productCodes, int errCode) {
        boolean errorFound = false;
        Integer validationCode = pv.getValidationErrorCode();
        if (validationCode != null && validationCode.intValue() == errCode) {
            productCodes.add(pv.getProductCode());
            errorFound = true;
        }
        List<ProductView> components = pv.getComponents();
        if (components != null) {
            for (ProductView component : components) {
                if (fillErrorCodes(component, productCodes, errCode)) {
                    productCodes.add(pv.getProductCode());
                    errorFound = true;
                }
            }
        }
        List<ProductView> choices = pv.getChoices();
        if (choices != null) {
            for (ProductView choice : choices) {
                if (fillErrorCodes(choice, productCodes, errCode)) {
                    productCodes.add(pv.getProductCode());
                    errorFound = true;
                }
            }
        }
        return errorFound;
    }

    public static void findErrorsForOrderView(OrderView orderView, OrderResponse ret) {
        int size;
        int i;
        List<Integer> productsOutOfStock = new ArrayList();
        List<Integer> productsUnavailable = new ArrayList();
        List<Integer> productsTimeRestriction = new ArrayList();
        List<Integer> productItemTimeRestriction = new ArrayList();
        List<Integer> productItemDayPartRestriction = new ArrayList();
        List<Integer> productItemNotCoincideRestriction = new ArrayList();
        List<ProductView> productViews = orderView.getProducts();
        if (productViews != null) {
            size = productViews.size();
            for (i = 0; i < size; i++) {
                ProductView product = (ProductView) productViews.get(i);
                fillErrorCodes(product, productsOutOfStock, PRODUCT_OUT_OF_STOCK_CODE);
                fillErrorCodes(product, productsUnavailable, PRODUCT_UNAVAILABLE_CODE);
                fillErrorCodes(product, productsUnavailable, PRODUCT_UNAVAILABLE_AT_RESTAURANT_CODE);
                fillErrorCodes(product, productItemDayPartRestriction, ORDER_WITH_DIFFERENT_DAYPART_PRODUCTS_NOT_PERMITTED_CODE);
                fillErrorCodes(product, productItemDayPartRestriction, PRODUCT_TIME_RESTRICTION_DAY_PART_CODE);
                fillErrorCodes(product, productsTimeRestriction, ORDER_WITH_DIFFERENT_DAYPART_PRODUCTS_NOT_PERMITTED_CODE);
                fillErrorCodes(product, productsTimeRestriction, PRODUCT_TIME_RESTRICTION_DAY_PART_CODE);
                fillErrorCodes(product, productItemTimeRestriction, PRODUCT_TIME_RESTRICTION_CODE);
                fillErrorCodes(product, productsTimeRestriction, PRODUCT_TIME_RESTRICTION_CODE);
                fillErrorCodes(product, productsTimeRestriction, PRODUCT_TIME_RESTRICTION_NOT_COINCIDE_CODE);
                fillErrorCodes(product, productItemNotCoincideRestriction, PRODUCT_ITEM_TIME_RESTRICTION_NOT_COINCIDE_CODE);
                fillErrorCodes(product, productsTimeRestriction, PRODUCT_ITEM_TIME_RESTRICTION_NOT_COINCIDE_CODE);
            }
        }
        ret.setProductsOutOfStock(productsOutOfStock);
        ret.setProductsUnavailable(productsUnavailable);
        ret.setProductsTimeRestriction(productsTimeRestriction);
        List<Integer> promotionsNotAvailable = new ArrayList();
        List<Integer> promotionsError = new ArrayList();
        List<Integer> promotionsOutOfStock = new ArrayList();
        List<Integer> promotionsTimeRestriction = new ArrayList();
        List<PromotionView> promotionViews = orderView.getPromotionalItems();
        if (promotionViews != null) {
            size = promotionViews.size();
            for (i = 0; i < size; i++) {
                PromotionView promotionView = (PromotionView) promotionViews.get(i);
                if (promotionView.getValidationErrorCode().equals(Integer.valueOf(PROMOTION_NOT_AVAILABLE_CODE))) {
                    promotionsNotAvailable.add(promotionView.getPromotionId());
                } else if (promotionView.getValidationErrorCode().equals(Integer.valueOf(OFFERS_ERROR))) {
                    promotionsError.add(promotionView.getValidationErrorCode());
                }
                if (promotionView.getProductSet() != null) {
                    productViews = promotionView.getProductSet();
                    int sizeP = productViews.size();
                    for (int j = 0; j < sizeP; j++) {
                        ProductView productView = (ProductView) productViews.get(j);
                        if (productView.getValidationErrorCode().equals(Integer.valueOf(ORDER_WITH_DIFFERENT_DAYPART_PRODUCTS_NOT_PERMITTED_CODE))) {
                            promotionsTimeRestriction.add(promotionView.getPromotionId());
                            break;
                        }
                        if (productView.getValidationErrorCode().equals(Integer.valueOf(PRODUCT_OUT_OF_STOCK_CODE))) {
                            promotionsOutOfStock.add(productView.getProductCode());
                        }
                    }
                }
            }
        }
        ret.setPromotionsNotAvailable(promotionsNotAvailable);
        ret.setPromotionsError(promotionsError);
        ret.setPromotionsTimeRestriction(promotionsTimeRestriction);
        ret.setProductItemTimeRestriction(productItemTimeRestriction);
        ret.setProductItemDayPartRestriction(productItemDayPartRestriction);
        ret.setProductItemNotCoincideRestriction(productItemNotCoincideRestriction);
        ret.setPromotionsOutOfStock(promotionsOutOfStock);
    }

    public String getCheckInCode() {
        return this.mCheckInCode;
    }

    public void setCheckInCode(String checkInCode) {
        this.mCheckInCode = checkInCode;
    }

    public List<FulfillmentFacilityOpeningHour> getFulfillmentFacilityOpeningHours() {
        return this.mFulfillmentFacilityOpeningHours;
    }

    public void setFulfillmentFacilityOpeningHours(List<FulfillmentFacilityOpeningHour> fulfillmentFacilityOpeningHours) {
        this.mFulfillmentFacilityOpeningHours = fulfillmentFacilityOpeningHours;
    }
}
