package com.mcdonalds.sdk.modules.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class CustomerAddress extends AppModel implements Parcelable {
    private static String CFG_KEY_ADDRESS_FORMAT = "interface.address.addressFormat";
    public static final Creator<CustomerAddress> CREATOR = new C40411();
    private boolean isDefaultAddress;
    private List<AddressElement> mAddressElements;
    private AddressType mAddressType;
    private boolean mAllowPromotionsToAddress;
    private int mCustomerAddressPreference;
    private String mPhone;

    /* renamed from: com.mcdonalds.sdk.modules.models.CustomerAddress$1 */
    static class C40411 implements Creator<CustomerAddress> {
        C40411() {
        }

        public CustomerAddress createFromParcel(Parcel source) {
            return new CustomerAddress(source);
        }

        public CustomerAddress[] newArray(int size) {
            return new CustomerAddress[size];
        }
    }

    public static String getAddressLabel(AddressType addressType, Context context) {
        switch (addressType) {
            case Home1:
                return context.getString(C3883R.string.address_type_home1);
            case Home2:
                return context.getString(C3883R.string.address_type_home2);
            case Office1:
                return context.getString(C3883R.string.address_type_office1);
            case Office2:
                return context.getString(C3883R.string.address_type_office2);
            case Other:
                return context.getString(C3883R.string.address_type_other);
            default:
                return "";
        }
    }

    public static String getElementLabel(AddressElement addressElement, Context context) {
        switch (addressElement.getAddressElementType()) {
            case Area:
                return context.getString(C3883R.string.address_element_area);
            case Building:
                return context.getString(C3883R.string.address_element_building);
            case City:
                return context.getString(C3883R.string.address_element_city);
            case Company:
                return context.getString(C3883R.string.address_element_company);
            case Department:
                return context.getString(C3883R.string.address_element_department);
            case District:
                return context.getString(C3883R.string.address_element_district);
            case Garden:
                return context.getString(C3883R.string.address_element_garden);
            case State:
                return context.getString(C3883R.string.address_element_state);
            case Street:
                return context.getString(C3883R.string.address_element_street);
            case StreetType:
                return context.getString(C3883R.string.address_element_street_type);
            case Suburb:
                return context.getString(C3883R.string.address_element_suburb);
            case ZipCode:
                return context.getString(C3883R.string.address_element_zipcode);
            case Block:
                return context.getString(C3883R.string.address_element_block);
            case Level:
                return context.getString(C3883R.string.address_element_level);
            case Unit:
                return context.getString(C3883R.string.address_element_unit);
            case HouseNumber:
                return context.getString(C3883R.string.address_element_house_number);
            case ExternalAddressTypeID:
                return context.getString(C3883R.string.address_element_external_address_type_id);
            case IsDefaultAddress:
                return context.getString(C3883R.string.address_element_is_default_address);
            case AddressPreferenceTypeID:
                return context.getString(C3883R.string.address_element_address_preference_type_id);
            case StreetLonNumber:
                return context.getString(C3883R.string.address_element_street_lon_number);
            case Remark:
                return context.getString(C3883R.string.address_element_remark);
            case Longitude:
                return context.getString(C3883R.string.address_element_longitude);
            case Latitude:
                return context.getString(C3883R.string.address_element_latitude);
            case IsBlackZone:
                return context.getString(C3883R.string.address_element_is_black_zone);
            case IsGoldenZone:
                return context.getString(C3883R.string.address_element_is_golden_zone);
            case Landmark:
                return context.getString(C3883R.string.address_element_landmark);
            case PhoneNumber:
                return context.getString(C3883R.string.address_element_phone_number);
            case PhoneExtension:
                return context.getString(C3883R.string.address_element_phone_extension);
            case OneLineAddress:
                return context.getString(C3883R.string.address_element_one_line_address);
            case RSDSAddressType:
                return context.getString(C3883R.string.address_element_rsds_address_type);
            case RSDSAddressPointID:
                return context.getString(C3883R.string.address_element_rsds_address_point);
            default:
                return "";
        }
    }

    public String getAddressElementValue(AddressElementType elementType) {
        for (AddressElement element : this.mAddressElements) {
            if (element.getAddressElementType() == elementType) {
                return ((AddressAliasValue) element.getValue().get(0)).getAlias();
            }
        }
        return null;
    }

    public boolean isDefaultAddress() {
        return this.isDefaultAddress;
    }

    public void setDefaultAddress(boolean isDefaultAddress) {
        this.isDefaultAddress = isDefaultAddress;
    }

    public String getFullAddress() {
        if (LocalDataManager.getSharedInstance().getDeviceLanguage().equals(Locale.US.getLanguage())) {
            StringBuffer sb = new StringBuffer();
            appendIfExists(sb, this.mAddressElements, AddressElementType.OneLineAddress, "", "");
            appendIfExists(sb, this.mAddressElements, AddressElementType.Level, " ", "");
            appendIfExists(sb, this.mAddressElements, AddressElementType.Unit, " ", "");
            appendIfExists(sb, this.mAddressElements, AddressElementType.Remark, "", "");
            return sb.toString();
        } else if (Configuration.getSharedInstance().hasKey(CFG_KEY_ADDRESS_FORMAT)) {
            return getDisplayAddress();
        } else {
            StringBuilder addressBuilder = new StringBuilder();
            Iterator<AddressElement> iterator = this.mAddressElements.iterator();
            while (iterator.hasNext()) {
                addressBuilder.append(((AddressAliasValue) ((AddressElement) iterator.next()).getValue().get(0)).getAlias());
                if (iterator.hasNext()) {
                    addressBuilder.append(" ");
                }
            }
            return addressBuilder.toString();
        }
    }

    public String getDisplayAddress() {
        LinkedTreeMap<String, Object> displayFormat = (LinkedTreeMap) Configuration.getSharedInstance().getValueForKey(CFG_KEY_ADDRESS_FORMAT);
        StringBuffer sb = new StringBuffer();
        for (LinkedTreeMap<String, String> f : (List) displayFormat.get("fields")) {
            appendIfExists(sb, this.mAddressElements, AddressElementType.valueOf((String) f.get("elementType")), (String) f.get("preText"), (String) f.get("postText"));
        }
        return sb.toString();
    }

    private void appendIfExists(StringBuffer sb, List<AddressElement> addressElements, AddressElementType elementType, String preText, String postText) {
        String val = null;
        for (AddressElement element : addressElements) {
            if (element.getAddressElementType().equals(elementType)) {
                val = ((AddressAliasValue) element.getValue().get(0)).getAlias();
                break;
            }
        }
        if (val != null && !val.isEmpty()) {
            if (preText != null) {
                sb.append(preText);
            }
            sb.append(val.trim());
            if (postText != null) {
                sb.append(postText);
            }
        }
    }

    public int getCustomerAddressPreference() {
        return this.mCustomerAddressPreference;
    }

    public void setCustomerAddressPreference(int customerAddressPreference) {
        this.mCustomerAddressPreference = customerAddressPreference;
    }

    public AddressType getAddressType() {
        return this.mAddressType;
    }

    public void setAddressType(AddressType addressType) {
        this.mAddressType = addressType;
    }

    public List<AddressElement> getAddressElements() {
        return this.mAddressElements;
    }

    public void setAddressElements(List<AddressElement> addressElements) {
        this.mAddressElements = addressElements;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public void setPhone(String phone) {
        this.mPhone = phone;
    }

    public boolean isAllowPromotionsToAddress() {
        return this.mAllowPromotionsToAddress;
    }

    public void setAllowPromotionsToAddress(boolean allowPromotionsToAddress) {
        this.mAllowPromotionsToAddress = allowPromotionsToAddress;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b;
        byte b2 = (byte) 1;
        dest.writeInt(this.mCustomerAddressPreference);
        dest.writeInt(this.mAddressType == null ? -1 : this.mAddressType.ordinal());
        if (this.isDefaultAddress) {
            b = (byte) 1;
        } else {
            b = (byte) 0;
        }
        dest.writeByte(b);
        dest.writeTypedList(this.mAddressElements);
        dest.writeString(this.mPhone);
        if (!this.mAllowPromotionsToAddress) {
            b2 = (byte) 0;
        }
        dest.writeByte(b2);
    }

    protected CustomerAddress(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.mCustomerAddressPreference = in.readInt();
        int tmpMAddressType = in.readInt();
        this.mAddressType = tmpMAddressType == -1 ? null : AddressType.values()[tmpMAddressType];
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.isDefaultAddress = z;
        this.mAddressElements = in.createTypedArrayList(AddressElement.CREATOR);
        this.mPhone = in.readString();
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.mAllowPromotionsToAddress = z2;
    }
}
