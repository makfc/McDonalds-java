package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.Facility;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.io.Serializable;
import java.util.List;

public class MWFacility implements Serializable {
    @SerializedName("FacilityID")
    public int facilityID;
    @SerializedName("FacilityName")
    public String facilityName;
    @SerializedName("IsValid")
    public boolean isValid;
    @SerializedName("Names")
    public List<MWName> names;

    public Facility toFacility() {
        Facility facility = new Facility();
        facility.setID(this.facilityID);
        facility.setFacilityName(this.facilityName);
        facility.setIsValid(this.isValid);
        String languageId = Configuration.getSharedInstance().getCurrentLanguageTag();
        for (MWName name : this.names) {
            if (name.languageID.equals(languageId)) {
                facility.setName(name.name);
            }
        }
        return facility;
    }
}
