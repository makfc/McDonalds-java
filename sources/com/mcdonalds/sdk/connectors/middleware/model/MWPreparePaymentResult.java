package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.util.List;

public class MWPreparePaymentResult {
    @SerializedName("AdditionalPayments")
    private List<MWPrepareOrderPaymentResult> mAdditionalPayments;
    @SerializedName("CVVLength")
    private Integer mCVVLength;
    @SerializedName("NowInStoreLocalTime")
    private String mNowInStoreLocalTime;
    @SerializedName("OrderView")
    private MWOrderViewResult mOrderViewResult;
    @SerializedName("PaymentCustomData")
    private List<MWPaymentCustomDataEntry> mPaymentCustomData;
    @SerializedName("PaymentDataId")
    private Integer mPaymentDataId;
    @SerializedName("PaymentUrl")
    private String mPaymentUrl;
    @SerializedName("RequireCVV")
    private Boolean mRequireCVV;
    @SerializedName("RequiresPassword")
    private Boolean mRequiresPassword;

    class MWPaymentCustomDataEntry {
        @SerializedName("Key")
        private String mKey;
        @SerializedName("Value")
        private String mValue;

        public String getKey() {
            return this.mKey;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    public static OrderResponse toOrderResponse(MWPreparePaymentResult result) {
        OrderResponse ret = OrderResponse.fromTotalize(MWOrderViewResult.toOrderView(result.getOrderViewResult()));
        ret.setPaymentDataId(result.getPaymentDataId());
        ret.setOrderPaymentId(result.getOrderViewResult().orderPaymentId);
        ret.setRequiresCVV(result.getRequireCVV());
        ret.setRequiresPassword(result.getRequiresPassword());
        ret.setPaymentUrl(result.getPaymentUrl());
        List<MWPaymentCustomDataEntry> paymentCustomData = result.getPaymentCustomData();
        if (paymentCustomData != null) {
            for (MWPaymentCustomDataEntry e : paymentCustomData) {
                String key = e.getKey();
                String value = e.getValue();
                if (key.equals("partner_id")) {
                    ret.setPartnerId(value);
                } else if (key.equals("merchant_private_key")) {
                    ret.setMerchantPrivateKey(value);
                } else if (key.equals("alipay_public_key")) {
                    ret.setAlipayPublicKey(value);
                } else if (key.equals("notify_url")) {
                    ret.setNotifyUrl(value);
                } else if (key.equals("seller_id")) {
                    ret.setSellerId(value);
                } else if (key.equals("appid")) {
                    ret.setAppId(value);
                } else if (key.equals("noncestr")) {
                    ret.setNoncestr(value);
                } else if (key.equals("partnerid")) {
                    ret.setPartnerId(value);
                } else if (key.equals("prepayid")) {
                    ret.setPrepayid(value);
                } else if (key.equals("package")) {
                    ret.setPackage(value);
                } else if (key.equals(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE)) {
                    ret.setTimeStamp(value);
                } else if (key.equals("sign")) {
                    ret.setSign(value);
                } else if (key.equals("MerchantId")) {
                    ret.setMerchantId(value);
                } else if (key.equals("signature")) {
                    ret.setSignature(value);
                }
            }
        }
        return ret;
    }

    public String getPaymentUrl() {
        return this.mPaymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.mPaymentUrl = paymentUrl;
    }

    public Boolean getRequireCVV() {
        return this.mRequireCVV;
    }

    public void setRequireCVV(Boolean requireCVV) {
        this.mRequireCVV = requireCVV;
    }

    public Integer getCVVLength() {
        return this.mCVVLength;
    }

    public void setCVVLength(Integer CVVLength) {
        this.mCVVLength = CVVLength;
    }

    public Boolean getRequiresPassword() {
        return this.mRequiresPassword;
    }

    public void setRequiresPassword(Boolean requiresPassword) {
        this.mRequiresPassword = requiresPassword;
    }

    public Integer getPaymentDataId() {
        return this.mPaymentDataId;
    }

    public void setPaymentDataId(Integer paymentDataId) {
        this.mPaymentDataId = paymentDataId;
    }

    public MWOrderViewResult getOrderViewResult() {
        return this.mOrderViewResult;
    }

    public void setOrderViewResult(MWOrderViewResult orderViewResult) {
        this.mOrderViewResult = orderViewResult;
    }

    public String getNowInStoreLocalTime() {
        return this.mNowInStoreLocalTime;
    }

    public void setNowInStoreLocalTime(String nowInStoreLocalTime) {
        this.mNowInStoreLocalTime = nowInStoreLocalTime;
    }

    public List<MWPrepareOrderPaymentResult> getAdditionalPayments() {
        return this.mAdditionalPayments;
    }

    public void setAdditionalPayments(List<MWPrepareOrderPaymentResult> additionalPayments) {
        this.mAdditionalPayments = additionalPayments;
    }

    public List<MWPaymentCustomDataEntry> getPaymentCustomData() {
        return this.mPaymentCustomData;
    }

    public void setPaymentCustomData(List<MWPaymentCustomDataEntry> paymentCustomData) {
        this.mPaymentCustomData = paymentCustomData;
    }
}
