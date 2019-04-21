package com.mcdonalds.sdk.services.data.sync;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;
import com.mcdonalds.sdk.services.data.CatalogManager;

public class SyncAdapter extends AbstractThreadedSyncAdapter {
    public static final String PREF_LAST_ACTIVE = "last_active";
    public static final String SYNC_OPTION_SOCIAL_NETWORK = "sync_option_social_network";
    public static final String SYNC_OPTION_STORE_ID = "sync_option_store_id";
    public static final String SYNC_OPTION_STORE_INFO_REQUIRED = "sync_option_store_info_required";
    public static final String SYNC_PREFERENCES = "sync_prefs";

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    public SyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
    }

    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        boolean isSocialSync = extras.getBoolean(SYNC_OPTION_SOCIAL_NETWORK, false);
        CatalogManager.updateCatalog(extras.getInt(SYNC_OPTION_STORE_ID), getContext(), extras.getBoolean(SYNC_OPTION_STORE_INFO_REQUIRED, false));
    }
}
