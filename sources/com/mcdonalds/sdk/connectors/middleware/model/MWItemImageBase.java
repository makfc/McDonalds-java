package com.mcdonalds.sdk.connectors.middleware.model;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.connectors.utils.Utils;
import com.mcdonalds.sdk.modules.models.ImageInfo;
import java.io.Serializable;

public class MWItemImageBase implements Serializable {
    @SerializedName("alt_text")
    public String altText;
    @SerializedName("description")
    public String description;
    @SerializedName("image_name")
    public String imageName;
    @SerializedName("url")
    public String url;

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.imageName);
    }

    public ImageInfo toImageInfo(String baseImagePath) {
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setImageName(this.imageName);
        imageInfo.setAltText(this.altText);
        imageInfo.setDescription(this.description);
        if (!isEmpty()) {
            String imageUrl = baseImagePath;
            if (this.imageName.contains("/")) {
                String[] parsingImageName = this.imageName.split("/");
                for (int i = 0; i < parsingImageName.length; i++) {
                    imageUrl = imageUrl + Utils.urlEncode(parsingImageName[i].trim());
                    if (i != parsingImageName.length - 1) {
                        imageUrl = imageUrl + "/";
                    }
                }
            } else {
                imageUrl = imageUrl + Utils.urlEncode(this.imageName);
            }
            imageInfo.setUrl(imageUrl);
        }
        return imageInfo;
    }
}
