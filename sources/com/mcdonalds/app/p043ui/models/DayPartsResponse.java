package com.mcdonalds.app.p043ui.models;

import com.ensighten.Ensighten;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/* renamed from: com.mcdonalds.app.ui.models.DayPartsResponse */
public class DayPartsResponse implements Serializable {
    @SerializedName("daypart")
    public List<DayPart> dayParts;

    public String getUrl(int i) {
        Ensighten.evaluateEvent(this, "getUrl", new Object[]{new Integer(i)});
        if (i < this.dayParts.size()) {
            return ((DayPart) this.dayParts.get(i)).url;
        }
        return "";
    }
}
