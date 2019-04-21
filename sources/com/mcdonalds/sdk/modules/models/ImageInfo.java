package com.mcdonalds.sdk.modules.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mcdonalds.sdk.modules.AppModel;

public class ImageInfo extends AppModel implements Parcelable {
    public static final Creator<ImageInfo> CREATOR = new C40521();
    private String mAltText;
    private String mDescription;
    private String mImageName;
    private String mUrl;

    /* renamed from: com.mcdonalds.sdk.modules.models.ImageInfo$1 */
    static class C40521 implements Creator<ImageInfo> {
        C40521() {
        }

        public ImageInfo createFromParcel(Parcel source) {
            return new ImageInfo(source);
        }

        public ImageInfo[] newArray(int size) {
            return new ImageInfo[size];
        }
    }

    public String toString() {
        return "DEPCarouselImage{mImageName='" + this.mImageName + '\'' + ", mDescription='" + this.mDescription + '\'' + ", mAltText='" + this.mAltText + '\'' + ", mUrl='" + this.mUrl + '\'' + '}';
    }

    public String getImageName() {
        return this.mImageName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getAltText() {
        return this.mAltText;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setImageName(String imageName) {
        this.mImageName = imageName;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setAltText(String altText) {
        this.mAltText = altText;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mImageName);
        dest.writeString(this.mDescription);
        dest.writeString(this.mAltText);
        dest.writeString(this.mUrl);
    }

    protected ImageInfo(Parcel in) {
        this.mImageName = in.readString();
        this.mDescription = in.readString();
        this.mAltText = in.readString();
        this.mUrl = in.readString();
    }
}
