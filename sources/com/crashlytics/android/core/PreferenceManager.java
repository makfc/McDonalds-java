package com.crashlytics.android.core;

import android.annotation.SuppressLint;
import p041io.fabric.sdk.android.services.persistence.PreferenceStore;
import p041io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;

@SuppressLint({"CommitPrefEdits"})
class PreferenceManager {
    private final CrashlyticsCore kit;
    private final PreferenceStore preferenceStore;

    public static PreferenceManager create(PreferenceStore preferenceStore, CrashlyticsCore kit) {
        return new PreferenceManager(preferenceStore, kit);
    }

    public PreferenceManager(PreferenceStore preferenceStore, CrashlyticsCore kit) {
        this.preferenceStore = preferenceStore;
        this.kit = kit;
    }

    /* Access modifiers changed, original: 0000 */
    public void setShouldAlwaysSendReports(boolean send) {
        this.preferenceStore.save(this.preferenceStore.edit().putBoolean("always_send_reports_opt_in", send));
    }

    /* Access modifiers changed, original: 0000 */
    public boolean shouldAlwaysSendReports() {
        if (!this.preferenceStore.get().contains("preferences_migration_complete")) {
            boolean migrationRequired;
            PreferenceStore oldStore = new PreferenceStoreImpl(this.kit);
            if (this.preferenceStore.get().contains("always_send_reports_opt_in") || !oldStore.get().contains("always_send_reports_opt_in")) {
                migrationRequired = false;
            } else {
                migrationRequired = true;
            }
            if (migrationRequired) {
                this.preferenceStore.save(this.preferenceStore.edit().putBoolean("always_send_reports_opt_in", oldStore.get().getBoolean("always_send_reports_opt_in", false)));
            }
            this.preferenceStore.save(this.preferenceStore.edit().putBoolean("preferences_migration_complete", true));
        }
        return this.preferenceStore.get().getBoolean("always_send_reports_opt_in", false);
    }
}
