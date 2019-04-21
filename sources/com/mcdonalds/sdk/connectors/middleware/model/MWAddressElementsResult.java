package com.mcdonalds.sdk.connectors.middleware.model;

import android.util.SparseArray;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.AddressElementDependency;
import com.mcdonalds.sdk.modules.models.AddressElementType;
import com.mcdonalds.sdk.modules.models.AddressElementValidationRule;
import com.mcdonalds.sdk.modules.models.GetAddressElementsResult;
import java.util.ArrayList;
import java.util.List;

public class MWAddressElementsResult {
    @SerializedName("AddressElementDependencies")
    public List<MWAddressElementDependency> addressElementDependencies;
    @SerializedName("AddressElementTypes")
    public List<List<Integer>> addressElementTypes;
    @SerializedName("AddressElementValidationRules")
    public List<MWAddressElementValidationRule> addressElementValidationRules;

    public static GetAddressElementsResult toAddressElementsResult(MWAddressElementsResult result) {
        GetAddressElementsResult ret = new GetAddressElementsResult();
        List<AddressElementDependency> dependencies = new ArrayList();
        for (MWAddressElementDependency dependency : result.addressElementDependencies) {
            dependencies.add(MWAddressElementDependency.toAddressElementDependency(dependency));
        }
        ret.setAddressElementDependencies(dependencies);
        List<AddressElementValidationRule> rules = new ArrayList();
        SparseArray<AddressElementValidationRule> rulesArray = new SparseArray();
        for (MWAddressElementValidationRule rule : result.addressElementValidationRules) {
            AddressElementValidationRule validationRule = MWAddressElementValidationRule.toValidationRule(rule);
            rules.add(validationRule);
            rulesArray.put(rule.addressElementTypeCode, validationRule);
        }
        ret.setAddressElementValidationRules(rules);
        List<AddressElementType> types = new ArrayList();
        for (List<Integer> elementList : result.addressElementTypes) {
            for (Integer typeCode : elementList) {
                AddressElementType type = AddressElementType.values()[typeCode.intValue()];
                if (!(types.contains(type) || rulesArray.get(typeCode.intValue()) == null)) {
                    types.add(Math.min(((AddressElementValidationRule) rulesArray.get(typeCode.intValue())).getDisplayOrder(), types.size()), type);
                }
            }
        }
        ret.setAddressElementTypes(types);
        return ret;
    }
}
