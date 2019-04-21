package com.facebook.stetho.inspector.network;

import com.facebook.stetho.inspector.protocol.module.Page.ResourceType;

public class ResourceTypeHelper {
    private final MimeMatcher<ResourceType> mMimeMatcher = new MimeMatcher();

    public ResourceTypeHelper() {
        this.mMimeMatcher.addRule("text/css", ResourceType.STYLESHEET);
        this.mMimeMatcher.addRule("image/*", ResourceType.IMAGE);
        this.mMimeMatcher.addRule("application/x-javascript", ResourceType.SCRIPT);
        this.mMimeMatcher.addRule("text/javascript", ResourceType.XHR);
        this.mMimeMatcher.addRule("application/json", ResourceType.XHR);
        this.mMimeMatcher.addRule("text/*", ResourceType.DOCUMENT);
        this.mMimeMatcher.addRule("*", ResourceType.OTHER);
    }

    public ResourceType determineResourceType(String contentType) {
        return (ResourceType) this.mMimeMatcher.match(stripContentExtras(contentType));
    }

    public String stripContentExtras(String contentType) {
        int index = contentType.indexOf(59);
        return index >= 0 ? contentType.substring(0, index) : contentType;
    }
}
