package com.mcdonalds.sdk.services.notifications;

import android.app.IntentService;
import android.content.Intent;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.BlockingListener;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.connectors.NotificationConnector;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.modules.notification.NotificationModule;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import java.io.IOException;

public class NotificationIntentService extends IntentService {
    public static final int ACTION_REGISTER = 0;
    public static final int ACTION_UNREGISTER = 1;
    public static final String PARAM_ACTION = "param_action";
    LocalDataManager mLocalDataManager = LocalDataManager.getSharedInstance();
    NotificationConnector mNotificationConnector = ((NotificationConnector) ConnectorManager.getConnector((String) Configuration.getSharedInstance().getValueForKey(NotificationModule.CONNECTOR_KEY)));

    public NotificationIntentService() {
        super("NotificationIntent");
        setIntentRedelivery(true);
    }

    /* Access modifiers changed, original: protected */
    public void onHandleIntent(Intent intent) {
        try {
            if (intent.getExtras() != null) {
                switch (intent.getExtras().getInt(PARAM_ACTION, 0)) {
                    case 0:
                        register();
                        return;
                    case 1:
                        unregister();
                        return;
                    default:
                        return;
                }
                e.printStackTrace();
            }
        } catch (AsyncException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void unregister() throws IOException {
        this.mNotificationConnector.unregister();
    }

    private void register() throws IOException, InterruptedException, AsyncException {
        String token = this.mNotificationConnector.register();
        if (token != null) {
            this.mLocalDataManager.setNotificationRegId(token);
            CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
            CustomerProfile profile = customerModule.getCurrentProfile();
            if (profile != null) {
                BlockingListener<NotificationPreferences> preferenceListener = new BlockingListener();
                customerModule.setNotificationPreferences(profile.getNotificationPreferences(), preferenceListener);
                preferenceListener.getResponse();
            }
        }
    }
}
