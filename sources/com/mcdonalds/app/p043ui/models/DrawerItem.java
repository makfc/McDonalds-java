package com.mcdonalds.app.p043ui.models;

import com.ensighten.Ensighten;
import java.io.Serializable;
import java.util.Map;

/* renamed from: com.mcdonalds.app.ui.models.DrawerItem */
public class DrawerItem {
    private String mAnalyticsTitle;
    private Map<String, ? extends Serializable> mAttributes;
    private String mDataLayerTitle;
    private boolean mHeading;
    private String mLeftHandImageName;
    private Boolean mRequiresLoginState;
    private Boolean mRequiresLogoutState;
    private String mRightHandImageName;
    private String mSectionTitle;
    private String mUrl;

    public boolean isHeading() {
        Ensighten.evaluateEvent(this, "isHeading", null);
        return this.mHeading;
    }

    public void setHeading(boolean heading) {
        Ensighten.evaluateEvent(this, "setHeading", new Object[]{new Boolean(heading)});
        this.mHeading = heading;
    }

    public Boolean isRequiresLoginState() {
        Ensighten.evaluateEvent(this, "isRequiresLoginState", null);
        return this.mRequiresLoginState;
    }

    public void setRequiresLoginState(Boolean RequiresLoginState) {
        Ensighten.evaluateEvent(this, "setRequiresLoginState", new Object[]{RequiresLoginState});
        this.mRequiresLoginState = RequiresLoginState;
    }

    public Boolean isRequiresLogoutState() {
        Ensighten.evaluateEvent(this, "isRequiresLogoutState", null);
        return this.mRequiresLogoutState;
    }

    public void setRequiresLogoutState(Boolean requiresLogoutState) {
        Ensighten.evaluateEvent(this, "setRequiresLogoutState", new Object[]{requiresLogoutState});
        this.mRequiresLogoutState = requiresLogoutState;
    }

    public String getSectionTitle() {
        Ensighten.evaluateEvent(this, "getSectionTitle", null);
        return this.mSectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        Ensighten.evaluateEvent(this, "setSectionTitle", new Object[]{sectionTitle});
        this.mSectionTitle = sectionTitle;
    }

    public String getLeftHandImageName() {
        Ensighten.evaluateEvent(this, "getLeftHandImageName", null);
        return this.mLeftHandImageName;
    }

    public void setLeftHandImageName(String leftHandImageName) {
        Ensighten.evaluateEvent(this, "setLeftHandImageName", new Object[]{leftHandImageName});
        this.mLeftHandImageName = leftHandImageName;
    }

    public String getRightHandImageName() {
        Ensighten.evaluateEvent(this, "getRightHandImageName", null);
        return this.mRightHandImageName;
    }

    public void setRightHandImageName(String rightHandImageName) {
        Ensighten.evaluateEvent(this, "setRightHandImageName", new Object[]{rightHandImageName});
        this.mRightHandImageName = rightHandImageName;
    }

    public String getUrl() {
        Ensighten.evaluateEvent(this, "getUrl", null);
        return this.mUrl;
    }

    public void setUrl(String url) {
        Ensighten.evaluateEvent(this, "setUrl", new Object[]{url});
        this.mUrl = url;
    }

    public Map<String, ? extends Serializable> getAttributes() {
        Ensighten.evaluateEvent(this, "getAttributes", null);
        return this.mAttributes;
    }

    public void setAttributes(Map<String, ? extends Serializable> attributes) {
        Ensighten.evaluateEvent(this, "setAttributes", new Object[]{attributes});
        this.mAttributes = attributes;
    }

    public void setAnalyticsTitle(String analyticsTitle) {
        Ensighten.evaluateEvent(this, "setAnalyticsTitle", new Object[]{analyticsTitle});
        this.mAnalyticsTitle = analyticsTitle;
    }

    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return this.mAnalyticsTitle;
    }

    public String getDataLayerTitle() {
        Ensighten.evaluateEvent(this, "getDataLayerTitle", null);
        return this.mDataLayerTitle;
    }

    public void setDataLayerTitle(String dataLayerTitle) {
        Ensighten.evaluateEvent(this, "setDataLayerTitle", new Object[]{dataLayerTitle});
        this.mDataLayerTitle = dataLayerTitle;
    }
}
