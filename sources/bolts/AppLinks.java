package bolts;

import android.content.Intent;
import android.os.Bundle;

public final class AppLinks {
    public static Bundle getAppLinkData(Intent intent) {
        return intent.getBundleExtra("al_applink_data");
    }
}
