package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class CommunicationCategory extends AppModel implements Parcelable {
    public static final Creator<CommunicationCategory> CREATOR = new C40401();
    private String communicationCategoryDescription;
    private String communicationCategoryDetailText;
    private Boolean communicationCategoryID;

    /* renamed from: com.mcdonalds.sdk.modules.models.CommunicationCategory$1 */
    static class C40401 implements Creator<CommunicationCategory> {
        C40401() {
        }

        public CommunicationCategory createFromParcel(Parcel source) {
            return new CommunicationCategory(source);
        }

        public CommunicationCategory[] newArray(int size) {
            return new CommunicationCategory[size];
        }
    }

    public String getCommunicationCategoryDescription() {
        return this.communicationCategoryDescription;
    }

    public void setCommunicationCategoryDescription(String communicationCategoryDescription) {
        this.communicationCategoryDescription = communicationCategoryDescription;
    }

    public String getCommunicationCategoryDetailText() {
        return this.communicationCategoryDetailText;
    }

    public void setCommunicationCategoryDetailText(String communicationCategoryDetailText) {
        this.communicationCategoryDetailText = communicationCategoryDetailText;
    }

    public Boolean getCommunicationCategoryID() {
        return this.communicationCategoryID;
    }

    public void setCommunicationCategoryID(Boolean communicationCategoryID) {
        this.communicationCategoryID = communicationCategoryID;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.communicationCategoryDescription);
        dest.writeString(this.communicationCategoryDetailText);
        dest.writeValue(this.communicationCategoryID);
    }

    protected CommunicationCategory(Parcel in) {
        this.communicationCategoryDescription = in.readString();
        this.communicationCategoryDetailText = in.readString();
        this.communicationCategoryID = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }
}
