package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class MWMenuItemRelatedItem implements Serializable {
    @SerializedName("display_order")
    public int displayOrder;
    @SerializedName("id")
    /* renamed from: id */
    public String f6068id;
    @SerializedName("is_default")
    public Boolean isDefault;
    @SerializedName("label")
    public String label;

    public RelationItem toRelationItem() {
        RelationItem relationItem = new RelationItem();
        relationItem.setId(this.f6068id);
        relationItem.setLabel(this.label);
        relationItem.setDisplayOrder(this.displayOrder);
        relationItem.setIsDefault(this.isDefault);
        return relationItem;
    }
}
