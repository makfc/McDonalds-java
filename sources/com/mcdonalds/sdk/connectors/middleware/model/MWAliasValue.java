package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.AddressAliasType;
import com.mcdonalds.sdk.modules.models.AddressAliasValue;
import com.mcdonalds.sdk.modules.models.AddressElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MWAliasValue implements MWGetQueryArgsComplexObject {
    @SerializedName("Alias")
    public String alias;
    @SerializedName("AliasTypeCode")
    public int aliasType;

    public static AddressAliasValue toAddressAliasValue(MWAliasValue mwAliasValue) {
        AddressAliasValue ret = new AddressAliasValue();
        ret.setAliasType(AddressAliasType.values()[mwAliasValue.aliasType]);
        ret.setAlias(mwAliasValue.alias);
        return ret;
    }

    public static List<AddressAliasValue> toAddressAliasValueList(MWAddressElement mwAddressElement) {
        int size = mwAddressElement.value.size();
        List<AddressAliasValue> addressAliasValues = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            addressAliasValues.add(toAddressAliasValue((MWAliasValue) mwAddressElement.value.get(i)));
        }
        return addressAliasValues;
    }

    public static MWAliasValue fromAddressAliasValue(AddressAliasValue addressAliasValue) {
        MWAliasValue ret = new MWAliasValue();
        ret.aliasType = Arrays.asList(AddressAliasType.values()).indexOf(addressAliasValue.getAliasType());
        ret.alias = addressAliasValue.getAlias();
        return ret;
    }

    public static List<MWAliasValue> createListFromAddressElement(AddressElement addressElement) {
        List<AddressAliasValue> addressAliasValues = addressElement.getValue();
        int size = addressAliasValues.size();
        List<MWAliasValue> mwAliasValues = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            mwAliasValues.add(fromAddressAliasValue((AddressAliasValue) addressAliasValues.get(i)));
        }
        return mwAliasValues;
    }

    public List<String> getQueryPropertyNames() {
        return Arrays.asList(new String[]{"AliasTypeCode", "Alias"});
    }

    public Object getQueryProperty(String name) {
        if (name.equals("AliasTypeCode")) {
            return Integer.valueOf(this.aliasType);
        }
        if (name.equals("Alias")) {
            return this.alias;
        }
        return null;
    }
}
