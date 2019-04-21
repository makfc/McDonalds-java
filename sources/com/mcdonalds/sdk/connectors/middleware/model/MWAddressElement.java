package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.AddressElement;
import com.mcdonalds.sdk.modules.models.AddressElementType;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MWAddressElement implements MWGetQueryArgsComplexObject {
    @SerializedName("AddressElementTypeCode")
    public int addressElementType;
    @SerializedName("Value")
    public List<MWAliasValue> value;

    public static AddressElement toAddressElement(MWAddressElement mwAddressElement) {
        AddressElement addressElement = new AddressElement();
        addressElement.setAddressElementType(AddressElementType.values()[mwAddressElement.addressElementType]);
        addressElement.setValue(MWAliasValue.toAddressAliasValueList(mwAddressElement));
        return addressElement;
    }

    public static List<AddressElement> toAddressElementList(MWAddressBookEntry mwAddressBookEntry) {
        int size = mwAddressBookEntry.address.size();
        List<AddressElement> addressElements = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            addressElements.add(toAddressElement((MWAddressElement) mwAddressBookEntry.address.get(i)));
        }
        return addressElements;
    }

    public static MWAddressElement fromAddressElement(AddressElement addressElement) {
        MWAddressElement mwAddressElement = new MWAddressElement();
        mwAddressElement.addressElementType = Arrays.asList(AddressElementType.values()).indexOf(addressElement.getAddressElementType());
        mwAddressElement.value = MWAliasValue.createListFromAddressElement(addressElement);
        return mwAddressElement;
    }

    public static List<MWAddressElement> createListFromCustomerAddress(CustomerAddress customerAddress) {
        List<AddressElement> addressElements = customerAddress.getAddressElements();
        int size = addressElements.size();
        List<MWAddressElement> mwAddressElements = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            mwAddressElements.add(fromAddressElement((AddressElement) addressElements.get(i)));
        }
        return mwAddressElements;
    }

    public List<String> getQueryPropertyNames() {
        return Arrays.asList(new String[]{"AddressElementTypeCode", "Value"});
    }

    public Object getQueryProperty(String name) {
        if (name.equals("AddressElementTypeCode")) {
            return Integer.valueOf(this.addressElementType);
        }
        if (name.equals("Value")) {
            return this.value;
        }
        return null;
    }
}
