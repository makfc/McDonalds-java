package com.facebook.internal;

import android.content.Context;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import java.net.URI;
import java.net.URISyntaxException;

public class ImageRequest {
    private static final String HEIGHT_PARAM = "height";
    private static final String MIGRATION_PARAM = "migration_overrides";
    private static final String MIGRATION_VALUE = "{october_2012:true}";
    private static final String PROFILEPIC_URL_FORMAT = "https://graph.facebook.com/%s/picture";
    public static final int UNSPECIFIED_DIMENSION = 0;
    private static final String WIDTH_PARAM = "width";
    private boolean allowCachedRedirects;
    private Callback callback;
    private Object callerTag;
    private Context context;
    private URI imageUri;

    public static class Builder {
        private boolean allowCachedRedirects;
        private Callback callback;
        private Object callerTag;
        private Context context;
        private URI imageUrl;

        public Builder(Context context, URI imageUrl) {
            Validate.notNull(imageUrl, "imageUrl");
            this.context = context;
            this.imageUrl = imageUrl;
        }

        public Builder setCallback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public Builder setCallerTag(Object callerTag) {
            this.callerTag = callerTag;
            return this;
        }

        public Builder setAllowCachedRedirects(boolean allowCachedRedirects) {
            this.allowCachedRedirects = allowCachedRedirects;
            return this;
        }

        public ImageRequest build() {
            return new ImageRequest(this);
        }
    }

    public interface Callback {
        void onCompleted(ImageResponse imageResponse);
    }

    public static URI getProfilePictureUrl(String userId, int width, int height) throws URISyntaxException {
        Validate.notNullOrEmpty(userId, AnalyticAttribute.USER_ID_ATTRIBUTE);
        width = Math.max(width, 0);
        height = Math.max(height, 0);
        if (width == 0 && height == 0) {
            throw new IllegalArgumentException("Either width or height must be greater than 0");
        }
        android.net.Uri.Builder builder = new android.net.Uri.Builder().encodedPath(String.format(PROFILEPIC_URL_FORMAT, new Object[]{userId}));
        if (height != 0) {
            builder.appendQueryParameter(HEIGHT_PARAM, String.valueOf(height));
        }
        if (width != 0) {
            builder.appendQueryParameter(WIDTH_PARAM, String.valueOf(width));
        }
        builder.appendQueryParameter(MIGRATION_PARAM, MIGRATION_VALUE);
        return new URI(builder.toString());
    }

    private ImageRequest(Builder builder) {
        this.context = builder.context;
        this.imageUri = builder.imageUrl;
        this.callback = builder.callback;
        this.allowCachedRedirects = builder.allowCachedRedirects;
        this.callerTag = builder.callerTag == null ? new Object() : builder.callerTag;
    }

    public Context getContext() {
        return this.context;
    }

    public URI getImageUri() {
        return this.imageUri;
    }

    public Callback getCallback() {
        return this.callback;
    }

    public boolean isCachedRedirectAllowed() {
        return this.allowCachedRedirects;
    }

    public Object getCallerTag() {
        return this.callerTag;
    }
}
