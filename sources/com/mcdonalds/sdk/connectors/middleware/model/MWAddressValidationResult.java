package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.AddressElementValidationRule;
import com.mcdonalds.sdk.modules.models.AddressValidationResult;
import java.util.ArrayList;
import java.util.List;

public class MWAddressValidationResult {
    @SerializedName("InvalidAddressElements")
    public List<MWAddressElementValidationRule> invalidAddressElements;
    @SerializedName("IsAddressValid")
    public boolean isAddressValid;
    @SerializedName("ResultCode")
    public int resultCode;

    public static AddressValidationResult toValidateResult(MWAddressValidationResult result) {
        AddressValidationResult ret = new AddressValidationResult();
        ret.setResultCode(result.resultCode);
        ret.setAddressValid(result.isAddressValid);
        if (result.invalidAddressElements != null) {
            List<AddressElementValidationRule> invalidElements = new ArrayList();
            for (MWAddressElementValidationRule rule : result.invalidAddressElements) {
                invalidElements.add(MWAddressElementValidationRule.toValidationRule(rule));
            }
            ret.setInvalidAddressElements(invalidElements);
        }
        return ret;
    }
}
