package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.TenderType;
import java.io.Serializable;
import java.util.List;

public class MWTenderType implements Serializable {
    @SerializedName("DefaultTenderAmountDisplay")
    public int defaultTenderAmountDisplay;
    @SerializedName("IsDefault")
    public boolean isDefault;
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("LastModification")
    public String lastModification;
    @SerializedName("MarketID")
    public int marketID;
    @SerializedName("MinimumTenderAmount")
    public int minimumTenderAmount;
    @SerializedName("StaticsData")
    public List<Integer> staticsData;
    @SerializedName("TenderTypeCode")
    public int tenderTypeCode;
    @SerializedName("TenderTypeDisplayName")
    public String tenderTypeDisplayName;
    @SerializedName("TenderTypeId")
    public int tenderTypeId;

    public TenderType toTenderType() {
        TenderType tenderType = new TenderType();
        tenderType.setID(this.tenderTypeId);
        tenderType.setCode(this.tenderTypeCode);
        tenderType.setDisplayName(this.tenderTypeDisplayName);
        tenderType.setMarketID(this.marketID);
        tenderType.setMinimumTenderAmount(this.minimumTenderAmount);
        tenderType.setDefaultTenderAmountDisplay(this.defaultTenderAmountDisplay);
        tenderType.setDefault(this.isDefault);
        tenderType.setLastModification(this.lastModification);
        tenderType.setValid(this.isValid);
        tenderType.setStaticsData(this.staticsData);
        return tenderType;
    }
}
