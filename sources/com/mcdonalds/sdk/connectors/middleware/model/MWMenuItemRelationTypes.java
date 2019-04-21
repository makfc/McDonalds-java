package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class MWMenuItemRelationTypes implements Serializable {
    @SerializedName("relation_type")
    private List<MWMenuItemRelationType> itemRelationType;
    private MWMenuItemRelationType masterChild;
    private MWMenuItemRelationType size;

    public List<MWMenuItemRelationType> getItemRelationType() {
        return this.itemRelationType;
    }

    public void setItemRelationType(List<MWMenuItemRelationType> itemRelationType) {
        this.itemRelationType = itemRelationType;
    }

    public boolean hasMasterChild() {
        for (MWMenuItemRelationType depMenuItemRelationType : this.itemRelationType) {
            if ("master child".equals(depMenuItemRelationType.type)) {
                this.masterChild = depMenuItemRelationType;
                return true;
            }
        }
        return false;
    }

    public boolean hasSize() {
        for (MWMenuItemRelationType depMenuItemRelationType : this.itemRelationType) {
            if ("size".equals(depMenuItemRelationType.type)) {
                this.size = depMenuItemRelationType;
                return true;
            }
        }
        return false;
    }

    public MWMenuItemRelationType getMasterChild() {
        return this.masterChild;
    }

    public MWMenuItemRelationType getSize() {
        return this.size;
    }
}
