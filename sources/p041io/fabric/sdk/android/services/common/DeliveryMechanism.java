package p041io.fabric.sdk.android.services.common;

/* renamed from: io.fabric.sdk.android.services.common.DeliveryMechanism */
public enum DeliveryMechanism {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);
    
    /* renamed from: id */
    private final int f7864id;

    private DeliveryMechanism(int id) {
        this.f7864id = id;
    }

    public int getId() {
        return this.f7864id;
    }

    public String toString() {
        return Integer.toString(this.f7864id);
    }

    public static DeliveryMechanism determineFrom(String installerPackageName) {
        if ("io.crash.air".equals(installerPackageName)) {
            return TEST_DISTRIBUTION;
        }
        if (installerPackageName != null) {
            return APP_STORE;
        }
        return DEVELOPER;
    }
}
