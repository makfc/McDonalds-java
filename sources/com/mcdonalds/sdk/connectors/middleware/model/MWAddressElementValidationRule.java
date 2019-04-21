package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.AddressElementType;
import com.mcdonalds.sdk.modules.models.AddressElementValidationRule;

public class MWAddressElementValidationRule {
    @SerializedName("AddressElementTypeCode")
    public int addressElementTypeCode;
    @SerializedName("DisplayOrder")
    public int displayOrder;
    @SerializedName("ValidationLength")
    public int validationLength;
    @SerializedName("ValidationRegex")
    public String validationRegex;
    @SerializedName("ValidationType")
    public int validationType;

    public static AddressElementValidationRule toValidationRule(MWAddressElementValidationRule rule) {
        AddressElementValidationRule ret = new AddressElementValidationRule();
        ret.setAddressElementType(AddressElementType.values()[rule.addressElementTypeCode]);
        ret.setDisplayOrder(rule.displayOrder);
        ret.setValidationType(rule.validationType);
        ret.setValidationLength(rule.validationLength);
        ret.setValidationRegex(rule.validationRegex);
        return ret;
    }
}
