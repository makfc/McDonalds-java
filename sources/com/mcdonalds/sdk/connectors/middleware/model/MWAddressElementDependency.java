package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.AddressElementDependency;
import com.mcdonalds.sdk.modules.models.AddressElementType;
import java.util.ArrayList;
import java.util.List;

public class MWAddressElementDependency {
    @SerializedName("AddressElementCanFilteredBy")
    public List<Integer> addressElementCanFilteredBy;
    @SerializedName("AddressElementToBeCleared")
    public List<Integer> addressElementToBeCleared;
    @SerializedName("AddressElementTypeCode")
    public int addressElementTypeCode;

    public static AddressElementDependency toAddressElementDependency(MWAddressElementDependency dependency) {
        AddressElementDependency ret = new AddressElementDependency();
        ret.setAddressElementType(AddressElementType.values()[dependency.addressElementTypeCode]);
        List<AddressElementType> filters = new ArrayList();
        if (dependency.addressElementCanFilteredBy != null) {
            for (Integer filterCode : dependency.addressElementCanFilteredBy) {
                filters.add(AddressElementType.values()[filterCode.intValue()]);
            }
        }
        ret.setAddressElementCanFilteredBy(filters);
        List<AddressElementType> cleared = new ArrayList();
        if (dependency.addressElementToBeCleared != null) {
            for (Integer clearCode : dependency.addressElementToBeCleared) {
                cleared.add(AddressElementType.values()[clearCode.intValue()]);
            }
        }
        ret.setAddressToBeCleared(cleared);
        return ret;
    }
}
