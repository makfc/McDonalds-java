package com.mcdonalds.sdk.connectors.easyaddress;

import android.content.Context;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.BaseConnector;
import com.mcdonalds.sdk.connectors.DeliveryConnector;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.AddressAliasType;
import com.mcdonalds.sdk.modules.models.AddressAliasValue;
import com.mcdonalds.sdk.modules.models.AddressElement;
import com.mcdonalds.sdk.modules.models.CustomerAddress;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import p049hk.com.aisoft.easyaddrui.Address;
import p049hk.com.aisoft.easyaddrui.GetStoreCallbackInterface;
import p049hk.com.aisoft.easyaddrui.StoreReturn;
import p049hk.com.aisoft.easyaddrui.eaView;

public class EasyAddressConnector extends BaseConnector implements DeliveryConnector {
    public static final String NAME = "easyaddress";

    public EasyAddressConnector(Context context) {
        setContext(context);
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, Date deliveryTime, AsyncListener<Store> listener) {
        return getDeliveryStore(address, username, deliveryTime, false, listener);
    }

    public AsyncToken getDeliveryStore(CustomerAddress address, String username, final Date deliveryTime, final boolean isNormalOrder, final AsyncListener<Store> listener) {
        new eaView(getContext()).getStore(customerToEasyAddress(address), new GetStoreCallbackInterface() {
            public void onGetStoreReturn(StoreReturn storeReturn) {
                if (storeReturn.sCode.equals("1")) {
                    OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
                    List<String> storeIds = new ArrayList();
                    storeIds.add(storeReturn.sHub1.replaceAll("[a-zA-Z]", ""));
                    storeIds.add(storeReturn.sHub2.replaceAll("[a-zA-Z]", ""));
                    storeIds.add(storeReturn.sHub3.replaceAll("[a-zA-Z]", ""));
                    storeIds.add(storeReturn.sHub4.replaceAll("[a-zA-Z]", ""));
                    orderingModule.getStoreFromList(storeIds, deliveryTime, isNormalOrder, listener);
                    return;
                }
                listener.onResponse(null, null, new AsyncException(storeReturn.sCode));
            }
        });
        return null;
    }

    private Address customerToEasyAddress(CustomerAddress address) {
        Address easyAddress = new Address();
        List<AddressElement> addressElements = address.getAddressElements();
        int size = addressElements.size();
        for (int i = 0; i < size; i++) {
            AddressElement element = (AddressElement) addressElements.get(i);
            String elementAlias = getElementAlias(element);
            switch (element.getAddressElementType()) {
                case Building:
                    easyAddress.sBldgC = elementAlias;
                    break;
                case Area:
                    easyAddress.sAreaC = elementAlias;
                    break;
                case District:
                    easyAddress.sDistrictC = elementAlias;
                    break;
                case Garden:
                    easyAddress.sEstateC = elementAlias;
                    break;
                case Street:
                    easyAddress.sStreetC = elementAlias;
                    break;
                case Block:
                    easyAddress.sBlockC = elementAlias;
                    break;
                case Level:
                    easyAddress.sFloor = elementAlias;
                    break;
                case Unit:
                    easyAddress.sFlat = elementAlias;
                    break;
                case OneLineAddress:
                    easyAddress.sAddrE = elementAlias;
                    break;
                case HouseNumber:
                    easyAddress.sStreetNo = elementAlias;
                    break;
                case StreetLonNumber:
                    easyAddress.sStreetLon = elementAlias;
                    break;
                case Remark:
                    easyAddress.sRemarks = elementAlias;
                    break;
                default:
                    break;
            }
        }
        return easyAddress;
    }

    private String getElementAlias(AddressElement element) {
        List<AddressAliasValue> addressAliasValues = element.getValue();
        int size = addressAliasValues.size();
        for (int i = 0; i < size; i++) {
            AddressAliasValue value = (AddressAliasValue) addressAliasValues.get(i);
            if (value.getAliasType() == AddressAliasType.Kanji) {
                return value.getAlias();
            }
        }
        return "";
    }
}
