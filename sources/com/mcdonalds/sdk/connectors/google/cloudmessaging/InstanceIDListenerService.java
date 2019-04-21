package com.mcdonalds.sdk.connectors.google.cloudmessaging;

import android.content.Intent;
import android.os.Bundle;
import com.mcdonalds.sdk.services.notifications.NotificationIntentService;

public class InstanceIDListenerService extends com.google.android.gms.iid.InstanceIDListenerService {
    public void onTokenRefresh() {
        Intent intent = new Intent(this, NotificationIntentService.class);
        Bundle extras = new Bundle();
        extras.putInt(NotificationIntentService.PARAM_ACTION, 0);
        intent.putExtras(extras);
        startService(intent);
    }
}
