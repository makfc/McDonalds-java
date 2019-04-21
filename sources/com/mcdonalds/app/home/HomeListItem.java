package com.mcdonalds.app.home;

import com.ensighten.Ensighten;
import java.io.Serializable;
import java.util.Map;

public class HomeListItem {
    private Map<String, ? extends Serializable> mAttributes;
    private String mDataLayerTag;
    private final int mIconImageResource;
    private final String mLink;
    private String mSubTitle;
    private final String mTitle;

    public HomeListItem(int iconImageResource, String title, String subTitle, String link, Map<String, ? extends Serializable> attrs) {
        this(iconImageResource, title, subTitle, link, attrs, null);
    }

    public HomeListItem(int iconImageResource, String title, String subTitle, String link, Map<String, ? extends Serializable> attrs, String dataLayerTag) {
        this.mIconImageResource = iconImageResource;
        this.mTitle = title;
        this.mSubTitle = subTitle;
        this.mLink = link;
        this.mAttributes = attrs;
        this.mDataLayerTag = dataLayerTag;
    }

    public int getIconImageResource() {
        Ensighten.evaluateEvent(this, "getIconImageResource", null);
        return this.mIconImageResource;
    }

    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return this.mTitle;
    }

    public String getSubTitle() {
        Ensighten.evaluateEvent(this, "getSubTitle", null);
        return this.mSubTitle;
    }

    public String getLink() {
        Ensighten.evaluateEvent(this, "getLink", null);
        return this.mLink;
    }

    public Map<String, ? extends Serializable> getAttributes() {
        Ensighten.evaluateEvent(this, "getAttributes", null);
        return this.mAttributes;
    }

    public String getDataLayerTag() {
        Ensighten.evaluateEvent(this, "getDataLayerTag", null);
        return this.mDataLayerTag;
    }

    public void setSubTitle(String subTitle) {
        Ensighten.evaluateEvent(this, "setSubTitle", new Object[]{subTitle});
        this.mSubTitle = subTitle;
    }
}
