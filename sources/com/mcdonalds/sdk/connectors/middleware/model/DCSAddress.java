package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.AddressAliasType;
import com.mcdonalds.sdk.modules.models.AddressAliasValue;
import com.mcdonalds.sdk.modules.models.AddressElement;
import com.mcdonalds.sdk.modules.models.AddressElementType;
import com.mcdonalds.sdk.modules.models.AddressType;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DCSAddress {
    private static final String CHINESE_LANGUAGE_CODE = "zh";
    private static final String KEY_CONFIG_LANGUAGE_NAME = "connectors.Middleware.languageName";
    private static final String TYPE_BILLING = "billing";
    private static final String TYPE_DELIVERY = "delivery";
    private static final String TYPE_HOME1 = "home1";
    private static final String TYPE_HOME2 = "home2";
    private static final String TYPE_OFFICE1 = "ofice1";
    private static final String TYPE_OFFICE2 = "ofice2";
    private static final String TYPE_OTHER = "other";
    @SerializedName("activeInd")
    public String activeInd;
    @SerializedName("allowPromotions")
    public String allowPromotions;
    @SerializedName("details")
    public List<DCSAddressDetails> details;
    @SerializedName("primaryInd")
    public String primaryInd;
    @SerializedName("addressType")
    public String type;

    public static class DCSAddressDetails {
        @SerializedName("addressLineDetails")
        public DCSAddressLineDetails addressLineDetails;
        @SerializedName("addressLocale")
        public String locale;

        public static class DCSAddressLineDetails {
            @SerializedName("addressLine1")
            public String addressLine1;
            @SerializedName("addressLine2")
            public String addressLine2;
            @SerializedName("addressLine3")
            public String addressLine3;
            @SerializedName("addressLine4")
            public String addressLine4;
            @SerializedName("addressPreferenceTypeID")
            public String addressPreferenceTypeID;
            @SerializedName("area")
            public String area;
            @SerializedName("block")
            public String block;
            @SerializedName("building")
            public String building;
            @SerializedName("city")
            public String city;
            @SerializedName("company")
            public String company;
            @SerializedName("country")
            public String country;
            @SerializedName("county")
            public String county;
            @SerializedName("department")
            public String department;
            @SerializedName("district")
            public String district;
            @SerializedName("garden")
            public String garden;
            @SerializedName("houseNumber")
            public String houseNumber;
            @SerializedName("isAmberZone")
            public String isAmberZone;
            @SerializedName("isRedZone")
            public String isRedZone;
            @SerializedName("landmark")
            public String landmark;
            @SerializedName("latitude")
            public String latitude;
            @SerializedName("level")
            public String level;
            @SerializedName("longitude")
            public String longitude;
            @SerializedName("remark")
            public String remark;
            @SerializedName("shortZipCode")
            public String shortZipCode;
            @SerializedName("state")
            public String state;
            @SerializedName("streetLonNumber")
            public String streetLonNumber;
            @SerializedName("streetType")
            public String streetType;
            @SerializedName("suburb")
            public String suburb;
            @SerializedName("unit")
            public String unit;
            @SerializedName("zipCode")
            public String zipCode;
        }
    }

    public static DCSAddress fromCustomerAddress(CustomerAddress customerAddress) {
        DCSAddress dcsAddress = new DCSAddress();
        dcsAddress.type = getDCSAddressType(customerAddress.getAddressType());
        dcsAddress.primaryInd = customerAddress.isDefaultAddress() ? DCSProfile.INDICATOR_TRUE : DCSProfile.INDICATOR_FALSE;
        dcsAddress.allowPromotions = customerAddress.isAllowPromotionsToAddress() ? DCSProfile.INDICATOR_TRUE : DCSProfile.INDICATOR_FALSE;
        dcsAddress.details = getDCSAddressDetails(customerAddress.getAddressElements());
        return dcsAddress;
    }

    public static DCSAddress fromZipCode(String zipCode) {
        DCSAddress dcsAddress = new DCSAddress();
        dcsAddress.type = TYPE_OTHER;
        dcsAddress.details = getDCSAddressDetails(zipCode);
        return dcsAddress;
    }

    public CustomerAddress toCustomerAddress() {
        boolean allowPromotionsToAddress;
        boolean z = true;
        CustomerAddress address = new CustomerAddress();
        address.setAddressType(getAddressType());
        if (this.allowPromotions == null || !this.allowPromotions.equals(DCSProfile.INDICATOR_TRUE)) {
            allowPromotionsToAddress = false;
        } else {
            allowPromotionsToAddress = true;
        }
        address.setAllowPromotionsToAddress(allowPromotionsToAddress);
        List<AddressElement> addressElements = new ArrayList();
        for (DCSAddressDetails dcsAddressDetails : this.details) {
            addressElements.addAll(getAddressElements(dcsAddressDetails));
        }
        address.setAddressElements(addressElements);
        if (this.primaryInd == null || !this.primaryInd.equals(DCSProfile.INDICATOR_TRUE)) {
            z = false;
        }
        address.setDefaultAddress(z);
        return address;
    }

    private static String getDCSAddressType(AddressType addressType) {
        switch (addressType) {
            case Home1:
                return TYPE_HOME1;
            case Office1:
                return TYPE_OFFICE1;
            case Home2:
                return TYPE_HOME2;
            case Office2:
                return TYPE_OFFICE2;
            case Billing:
                return TYPE_BILLING;
            case Delivery:
                return "delivery";
            default:
                return TYPE_OTHER;
        }
    }

    private static List<DCSAddressDetails> getDCSAddressDetails(String zipCode) {
        DCSAddressDetails details = new DCSAddressDetails();
        details.locale = Configuration.getSharedInstance().getCurrentLanguageTag();
        DCSAddressLineDetails addressLineDetails = new DCSAddressLineDetails();
        addressLineDetails.zipCode = zipCode;
        details.addressLineDetails = addressLineDetails;
        return Collections.singletonList(details);
    }

    private static List<DCSAddressDetails> getDCSAddressDetails(List<AddressElement> addressElements) {
        DCSAddressDetails details = new DCSAddressDetails();
        details.locale = Configuration.getSharedInstance().getCurrentLanguageTag();
        DCSAddressLineDetails addressLineDetails = new DCSAddressLineDetails();
        for (AddressElement element : addressElements) {
            String value = getAddressElementValue(element);
            switch (element.getAddressElementType()) {
                case Area:
                    addressLineDetails.area = value;
                    break;
                case Building:
                    addressLineDetails.building = value;
                    break;
                case City:
                    addressLineDetails.city = value;
                    break;
                case Company:
                    addressLineDetails.company = value;
                    break;
                case Department:
                    addressLineDetails.department = value;
                    break;
                case District:
                    addressLineDetails.district = value;
                    break;
                case Garden:
                    addressLineDetails.garden = value;
                    break;
                case State:
                    addressLineDetails.state = value;
                    break;
                case OneLineAddress:
                    addressLineDetails.addressLine1 = value;
                    break;
                case StreetType:
                    addressLineDetails.streetType = value;
                    break;
                case Suburb:
                    addressLineDetails.suburb = value;
                    break;
                case ZipCode:
                    addressLineDetails.zipCode = value;
                    break;
                case Block:
                    addressLineDetails.block = value;
                    break;
                case Level:
                    addressLineDetails.level = value;
                    break;
                case Unit:
                    addressLineDetails.unit = value;
                    break;
                case HouseNumber:
                    addressLineDetails.houseNumber = value;
                    break;
                case AddressPreferenceTypeID:
                    addressLineDetails.addressPreferenceTypeID = value;
                    break;
                case StreetLonNumber:
                    addressLineDetails.streetLonNumber = value;
                    break;
                case Remark:
                    addressLineDetails.remark = value;
                    break;
                case Latitude:
                    addressLineDetails.latitude = value;
                    break;
                case Longitude:
                    addressLineDetails.longitude = value;
                    break;
                case Landmark:
                    addressLineDetails.landmark = value;
                    break;
                default:
                    break;
            }
        }
        details.addressLineDetails = addressLineDetails;
        return Collections.singletonList(details);
    }

    private static String getAddressElementValue(AddressElement element) {
        List<AddressAliasValue> aliasValues = element.getValue();
        if (ListUtils.isEmpty(aliasValues)) {
            return null;
        }
        return ((AddressAliasValue) aliasValues.get(0)).getAlias();
    }

    private AddressType getAddressType() {
        String str = this.type;
        Object obj = -1;
        switch (str.hashCode()) {
            case -1019706019:
                if (str.equals(TYPE_OFFICE1)) {
                    obj = 1;
                    break;
                }
                break;
            case -1019706018:
                if (str.equals(TYPE_OFFICE2)) {
                    obj = 3;
                    break;
                }
                break;
            case -109829509:
                if (str.equals(TYPE_BILLING)) {
                    obj = 4;
                    break;
                }
                break;
            case 99460914:
                if (str.equals(TYPE_HOME1)) {
                    obj = null;
                    break;
                }
                break;
            case 99460915:
                if (str.equals(TYPE_HOME2)) {
                    obj = 2;
                    break;
                }
                break;
            case 823466996:
                if (str.equals("delivery")) {
                    obj = 5;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return AddressType.Home1;
            case 1:
                return AddressType.Office1;
            case 2:
                return AddressType.Home2;
            case 3:
                return AddressType.Office2;
            case 4:
                return AddressType.Billing;
            case 5:
                return AddressType.Delivery;
            default:
                return AddressType.Other;
        }
    }

    private List<AddressElement> getAddressElements(DCSAddressDetails dcsAddressDetails) {
        AddressAliasType type;
        if (dcsAddressDetails.locale.contains(CHINESE_LANGUAGE_CODE)) {
            type = AddressAliasType.Kanji;
        } else {
            type = AddressAliasType.English;
        }
        DCSAddressLineDetails addressLineDetails = dcsAddressDetails.addressLineDetails;
        return Arrays.asList(new AddressElement[]{getAddressElement(AddressElementType.Area, type, addressLineDetails.area), getAddressElement(AddressElementType.Building, type, addressLineDetails.building), getAddressElement(AddressElementType.City, type, addressLineDetails.city), getAddressElement(AddressElementType.Company, type, addressLineDetails.company), getAddressElement(AddressElementType.Department, type, addressLineDetails.department), getAddressElement(AddressElementType.District, type, addressLineDetails.district), getAddressElement(AddressElementType.Garden, type, addressLineDetails.garden), getAddressElement(AddressElementType.State, type, addressLineDetails.state), getAddressElement(AddressElementType.OneLineAddress, type, addressLineDetails.addressLine1), getAddressElement(AddressElementType.StreetType, type, addressLineDetails.streetType), getAddressElement(AddressElementType.Suburb, type, addressLineDetails.suburb), getAddressElement(AddressElementType.ZipCode, type, addressLineDetails.zipCode), getAddressElement(AddressElementType.Block, type, addressLineDetails.block), getAddressElement(AddressElementType.Level, type, addressLineDetails.level), getAddressElement(AddressElementType.Unit, type, addressLineDetails.unit), getAddressElement(AddressElementType.HouseNumber, type, addressLineDetails.houseNumber), getAddressElement(AddressElementType.AddressPreferenceTypeID, type, addressLineDetails.addressPreferenceTypeID), getAddressElement(AddressElementType.StreetLonNumber, type, addressLineDetails.streetLonNumber), getAddressElement(AddressElementType.Remark, type, addressLineDetails.remark), getAddressElement(AddressElementType.Latitude, type, addressLineDetails.latitude), getAddressElement(AddressElementType.Longitude, type, addressLineDetails.longitude), getAddressElement(AddressElementType.Landmark, type, addressLineDetails.landmark)});
    }

    private AddressElement getAddressElement(AddressElementType elementType, AddressAliasType aliasType, String value) {
        AddressElement element = new AddressElement();
        element.setAddressElementType(elementType);
        element.setValue(getAddressAliasValueList(aliasType, value));
        return element;
    }

    private List<AddressAliasValue> getAddressAliasValueList(AddressAliasType type, String value) {
        AddressAliasValue aliasValue = new AddressAliasValue();
        aliasValue.setAliasType(type);
        aliasValue.setAlias(value);
        return Collections.singletonList(aliasValue);
    }
}
