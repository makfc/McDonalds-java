package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.sdk.connectors.middleware.model.MWDigitalServices;
import com.mcdonalds.sdk.connectors.middleware.model.MWDigitalServices.TechKey;
import com.mcdonalds.sdk.connectors.middleware.model.MWPointOfDistribution;
import com.mcdonalds.sdk.connectors.middleware.model.MWPointOfDistributionItem;
import com.mcdonalds.sdk.modules.AppModel;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StoreCapabilties extends AppModel implements Parcelable {
    public static final String ACCEPTS_QR_KEY = "RQRC_DYNAMIC";
    private static final String CAPABILITY_ID = "capabilityId";
    private static final String CHECKIN_SELECTION_DISPLAY_NAME_MAPPINGS_KEY = "interface.checkin.checkinSeclectionDisplayNameMappings";
    public static final Creator<StoreCapabilties> CREATOR = new C25871();
    public static final String MOBILE_ORDERING_KEY = "MOBILE_ORDERING";
    public static final String SCANNER_KEY = "SCANNER";
    private List<MWPointOfDistributionItem> storeCapabilities;

    /* renamed from: com.mcdonalds.sdk.modules.models.StoreCapabilties$1 */
    static class C25871 implements Creator<StoreCapabilties> {
        C25871() {
        }

        public StoreCapabilties createFromParcel(Parcel source) {
            return new StoreCapabilties(source);
        }

        public StoreCapabilties[] newArray(int size) {
            return new StoreCapabilties[size];
        }
    }

    public class StoreCapability {
        private int capabilityId;
        private boolean hasScanner;

        public int getCapabilityId() {
            return this.capabilityId;
        }

        public void setCapabilityId(int capabilityId) {
            this.capabilityId = capabilityId;
        }

        public boolean isHasScanner() {
            return this.hasScanner;
        }

        public void setHasScanner(boolean hasScanner) {
            this.hasScanner = hasScanner;
        }
    }

    public StoreCapabilties() {
        this.storeCapabilities = new ArrayList();
    }

    public StoreCapabilties(List<MWPointOfDistributionItem> storeCapabilities) {
        this.storeCapabilities = storeCapabilities;
    }

    public List<StoreCapability> filterAvailableCapabilities() {
        if (ListUtils.isEmpty(this.storeCapabilities)) {
            return null;
        }
        Iterator it;
        StoreCapability capability;
        List<StoreCapability> storeCapabilitiesWithDigitalService = new ArrayList();
        for (MWPointOfDistributionItem item : this.storeCapabilities) {
            for (MWDigitalServices digitalServices : item.digitalServices) {
                if (digitalServices.key.equalsIgnoreCase(MOBILE_ORDERING_KEY)) {
                    capability = new StoreCapability();
                    capability.setCapabilityId(item.pod);
                    for (TechKey techKey : digitalServices.technologies) {
                        if (techKey.key.equalsIgnoreCase(SCANNER_KEY)) {
                            capability.setHasScanner(true);
                        } else {
                            capability.hasScanner = false;
                        }
                    }
                    storeCapabilitiesWithDigitalService.add(capability);
                }
            }
        }
        List<StoreCapability> arrayList = new ArrayList();
        ArrayList<LinkedTreeMap> displayPODsNameList = (ArrayList) Configuration.getSharedInstance().getValueForKey(CHECKIN_SELECTION_DISPLAY_NAME_MAPPINGS_KEY);
        if (ListUtils.isEmpty(displayPODsNameList)) {
            return arrayList;
        }
        List podIds = new ArrayList(displayPODsNameList.size());
        it = displayPODsNameList.iterator();
        while (it.hasNext()) {
            LinkedTreeMap linkedHashMap = (LinkedTreeMap) it.next();
            if (linkedHashMap != null) {
                podIds.add(Integer.valueOf((int) Math.round(((Double) linkedHashMap.get(CAPABILITY_ID)).doubleValue())));
            }
        }
        for (StoreCapability capability2 : storeCapabilitiesWithDigitalService) {
            if (podIds.contains(Integer.valueOf(capability2.getCapabilityId()))) {
                arrayList.add(capability2);
            }
        }
        return arrayList;
    }

    @Deprecated
    public boolean hasKiosk() {
        for (MWPointOfDistributionItem item : this.storeCapabilities) {
            if (item.pod == 8) {
                for (MWDigitalServices ds : item.digitalServices) {
                    if (!ListUtils.isEmpty(ds.technologies) && ds.technologies.get(0) != null && ((TechKey) ds.technologies.get(0)).key != null && ((TechKey) ds.technologies.get(0)).key.equals(SCANNER_KEY)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public boolean isDriveThruAvailable() {
        for (MWPointOfDistributionItem item : this.storeCapabilities) {
            if (item.pod == MWPointOfDistribution.DriveThru.integerValue().intValue()) {
                for (MWDigitalServices ds : item.digitalServices) {
                    if (!ListUtils.isEmpty(ds.technologies) && ds.technologies.get(0) != null && ((TechKey) ds.technologies.get(0)).key != null && ((TechKey) ds.technologies.get(0)).key.equals(ACCEPTS_QR_KEY)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.storeCapabilities);
    }

    protected StoreCapabilties(Parcel in) {
        this.storeCapabilities = new ArrayList();
        in.readList(this.storeCapabilities, MWPointOfDistributionItem.class.getClassLoader());
    }
}
