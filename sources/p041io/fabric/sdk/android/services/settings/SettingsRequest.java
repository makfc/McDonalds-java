package p041io.fabric.sdk.android.services.settings;

/* renamed from: io.fabric.sdk.android.services.settings.SettingsRequest */
public class SettingsRequest {
    public final String apiKey;
    public final String buildVersion;
    public final String deviceModel;
    public final String displayVersion;
    public final String iconHash;
    public final String installationId;
    public final String instanceId;
    public final String osBuildVersion;
    public final String osDisplayVersion;
    public final int source;

    public SettingsRequest(String apiKey, String deviceModel, String osBuildVersion, String osDisplayVersion, String installationId, String instanceId, String displayVersion, String buildVersion, int source, String iconHash) {
        this.apiKey = apiKey;
        this.deviceModel = deviceModel;
        this.osBuildVersion = osBuildVersion;
        this.osDisplayVersion = osDisplayVersion;
        this.installationId = installationId;
        this.instanceId = instanceId;
        this.displayVersion = displayVersion;
        this.buildVersion = buildVersion;
        this.source = source;
        this.iconHash = iconHash;
    }
}
