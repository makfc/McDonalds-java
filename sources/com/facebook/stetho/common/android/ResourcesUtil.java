package com.facebook.stetho.common.android;

import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.support.p000v4.media.TransportMediator;
import com.facebook.stetho.common.LogUtil;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ResourcesUtil {
    private ResourcesUtil() {
    }

    @Nonnull
    public static String getIdStringQuietly(Object idContext, @Nullable Resources r, int resourceId) {
        try {
            return getIdString(r, resourceId);
        } catch (NotFoundException e) {
            String idString = getFallbackIdString(resourceId);
            LogUtil.m7459w("Unknown identifier encountered on " + idContext + ": " + idString);
            return idString;
        }
    }

    public static String getIdString(@Nullable Resources r, int resourceId) throws NotFoundException {
        if (r == null) {
            return getFallbackIdString(resourceId);
        }
        String prefix;
        String prefixSeparator;
        switch (getResourcePackageId(resourceId)) {
            case TransportMediator.KEYCODE_MEDIA_PAUSE /*127*/:
                prefix = "";
                prefixSeparator = "";
                break;
            default:
                prefix = r.getResourcePackageName(resourceId);
                prefixSeparator = ":";
                break;
        }
        String typeName = r.getResourceTypeName(resourceId);
        String entryName = r.getResourceEntryName(resourceId);
        StringBuilder sb = new StringBuilder(((((prefix.length() + 1) + prefixSeparator.length()) + typeName.length()) + 1) + entryName.length());
        sb.append("@");
        sb.append(prefix);
        sb.append(prefixSeparator);
        sb.append(typeName);
        sb.append("/");
        sb.append(entryName);
        return sb.toString();
    }

    private static String getFallbackIdString(int resourceId) {
        return "#" + Integer.toHexString(resourceId);
    }

    private static int getResourcePackageId(int id) {
        return (id >>> 24) & 255;
    }
}
