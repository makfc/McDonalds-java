package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.AddressType;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MWAddressBookEntry {
    @SerializedName("Address")
    public List<MWAddressElement> address;
    @SerializedName("AddressTypeID")
    public int addressTypeID;
    @SerializedName("AllowPromotionsToAddress")
    public boolean allowPromotionsToAddress;
    @SerializedName("CustomerAddressPreference")
    public int customerAddressPreference;
    @SerializedName("IsDefaultAddress")
    public boolean isDefaultAddress;

    public static CustomerAddress toCustomerAddress(MWAddressBookEntry addressBookEntry) {
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCustomerAddressPreference(addressBookEntry.customerAddressPreference);
        customerAddress.setAddressType(AddressType.values()[addressBookEntry.addressTypeID]);
        customerAddress.setDefaultAddress(addressBookEntry.isDefaultAddress);
        customerAddress.setAddressElements(MWAddressElement.toAddressElementList(addressBookEntry));
        customerAddress.setAllowPromotionsToAddress(addressBookEntry.allowPromotionsToAddress);
        return customerAddress;
    }

    public static List<CustomerAddress> toCustomerAddressList(List<MWAddressBookEntry> mwAddressBookEntries) {
        int size = mwAddressBookEntries.size();
        List<CustomerAddress> customerAddresses = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            customerAddresses.add(toCustomerAddress((MWAddressBookEntry) mwAddressBookEntries.get(i)));
        }
        return customerAddresses;
    }

    public static MWAddressBookEntry fromCustomerAddress(CustomerAddress address) {
        MWAddressBookEntry entry = new MWAddressBookEntry();
        entry.customerAddressPreference = address.getCustomerAddressPreference();
        entry.addressTypeID = Arrays.asList(AddressType.values()).indexOf(address.getAddressType());
        entry.isDefaultAddress = address.isDefaultAddress();
        entry.allowPromotionsToAddress = address.isAllowPromotionsToAddress();
        entry.address = MWAddressElement.createListFromCustomerAddress(address);
        return entry;
    }

    public static List<MWAddressBookEntry> createListFromCustomerAddressList(List<CustomerAddress> customerAddresses) {
        int size = customerAddresses.size();
        List<MWAddressBookEntry> mwAddressBookEntries = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            mwAddressBookEntries.add(fromCustomerAddress((CustomerAddress) customerAddresses.get(i)));
        }
        return mwAddressBookEntries;
    }
}
