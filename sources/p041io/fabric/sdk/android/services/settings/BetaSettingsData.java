package p041io.fabric.sdk.android.services.settings;

/* renamed from: io.fabric.sdk.android.services.settings.BetaSettingsData */
public class BetaSettingsData {
    public final int updateSuspendDurationSeconds;
    public final String updateUrl;

    public BetaSettingsData(String updateUrl, int updateSuspendDurationSeconds) {
        this.updateUrl = updateUrl;
        this.updateSuspendDurationSeconds = updateSuspendDurationSeconds;
    }
}
