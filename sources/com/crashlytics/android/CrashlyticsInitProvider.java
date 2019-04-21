package com.crashlytics.android;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.services.common.FirebaseInfo;

public class CrashlyticsInitProvider extends ContentProvider {

    interface EnabledCheckStrategy {
        boolean isCrashlyticsEnabled(Context context);
    }

    public boolean onCreate() {
        Context context = getContext();
        if (shouldInitializeFabric(context, new FirebaseInfo(), new ManifestEnabledCheckStrategy())) {
            try {
                Fabric.with(context, new Crashlytics());
                Fabric.getLogger().mo34407i("CrashlyticsInitProvider", "CrashlyticsInitProvider initialization successful");
            } catch (IllegalStateException e) {
                Fabric.getLogger().mo34407i("CrashlyticsInitProvider", "CrashlyticsInitProvider initialization unsuccessful");
                return false;
            }
        }
        Fabric.getLogger().mo34407i("CrashlyticsInitProvider", "CrashlyticsInitProvider skipping initialization");
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean shouldInitializeFabric(Context context, FirebaseInfo firebaseInfo, EnabledCheckStrategy enabledCheckStrategy) {
        if (firebaseInfo.isFirebaseCrashlyticsEnabled(context)) {
            return enabledCheckStrategy.isCrashlyticsEnabled(context);
        }
        return firebaseInfo.isAutoInitializeFlagEnabled(context);
    }
}
