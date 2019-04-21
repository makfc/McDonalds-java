package com.mcdonalds.sdk.services;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.widget.TextView;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.C3883R;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.notifications.NotificationCenter;

public class RonaldService extends Service implements ActivityLifecycleCallbacks {
    private static final String EXTRA_CONFIG_RELOAD_INTENT = "EXTRA_CONFIG_RELOAD_INTENT";
    public static final int GLOBAL_DIALOG_SHOW_INTERVAL = 5000;
    private static Activity mCurrentActivity;
    private BroadcastReceiver mAPIKeyInvalidNotificationListener = null;
    private BroadcastReceiver mAlertNotificationListener = null;
    private BroadcastReceiver mConnectivityReceiver = null;
    private ConnectorManager mConnectorManager = null;
    private String mLastMessage;
    private long mLastNotification;
    private ModuleManager mModuleManager = null;
    private NotificationListener mNotificationListener = null;
    private final OnClickListener mOnClickGlobalDialog = new C26144();
    private Intent mReloadIntent = null;

    /* renamed from: com.mcdonalds.sdk.services.RonaldService$1 */
    class C26111 extends BroadcastReceiver {
        C26111() {
        }

        public void onReceive(Context context, Intent intent) {
            RonaldService.this.showGlobalAlertDialog(RonaldService.this.getString(C3883R.string.error_title), intent.getStringExtra(NotificationCenter.EXTRA_MESSAGE));
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.RonaldService$2 */
    class C26122 extends BroadcastReceiver {
        C26122() {
        }

        public void onReceive(Context context, Intent intent) {
            RonaldService.this.showInvalidAPIKeyAlertDialog(intent.getStringExtra(NotificationCenter.EXTRA_MESSAGE));
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.RonaldService$3 */
    class C26133 extends BroadcastReceiver {
        C26133() {
        }

        public void onReceive(Context context, Intent intent) {
            NotificationCenter.getSharedInstance().postNotification(Configuration.NOTIFICATION_CONNECTIVITY_CHANGED);
        }
    }

    /* renamed from: com.mcdonalds.sdk.services.RonaldService$4 */
    class C26144 implements OnClickListener {
        C26144() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    private static class NotificationListener extends BroadcastReceiver {
        private final RonaldService mService;

        public NotificationListener(RonaldService service) {
            this.mService = service;
        }

        public void onReceive(Context context, Intent intent) {
            if (Configuration.MCD_NOTIFICATION_CONFIGURATION_CHANGED.equals(intent.getAction())) {
                McDonalds.clean(this.mService, this.mService.mReloadIntent, this.mService.mConnectorManager, this.mService.mModuleManager);
            }
        }
    }

    public static class ServiceBinder extends Binder {
        private final Service mService;

        public ServiceBinder(Service service) {
            this.mService = service;
        }

        public Service getService() {
            return this.mService;
        }
    }

    public static RonaldServiceConnection getServiceConnection(Context context, Intent reloadIntent) {
        if (!McDonalds.isInitialized()) {
            McDonalds.initialize(context.getApplicationContext());
        }
        Configuration.getSharedInstance();
        LocalDataManager.getSharedInstance();
        NotificationCenter.getSharedInstance();
        Analytics.getSharedInstance();
        RonaldServiceConnection connection = new RonaldServiceConnection();
        context.getApplicationContext().startService(getServiceIntent(context, reloadIntent));
        return connection;
    }

    private static Intent getServiceIntent(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context.getApplicationContext(), RonaldService.class);
        if (intent != null) {
            serviceIntent.putExtra(EXTRA_CONFIG_RELOAD_INTENT, intent);
        }
        return serviceIntent;
    }

    public void onCreate() {
        super.onCreate();
        getApplication().registerActivityLifecycleCallbacks(this);
        if (!McDonalds.isInitialized()) {
            McDonalds.initialize(getApplicationContext());
        }
        this.mNotificationListener = new NotificationListener(this);
        NotificationCenter.getSharedInstance().addObserver(Configuration.MCD_NOTIFICATION_CONFIGURATION_CHANGED, this.mNotificationListener);
        if (!Configuration.getSharedInstance().getBooleanForKey("notifyForUIAlertRendering")) {
            this.mAlertNotificationListener = NotificationCenter.getSharedInstance().addObserver(AsyncException.NOTIFICATION_ASYNC_ERROR, new C26111());
        }
        this.mAPIKeyInvalidNotificationListener = NotificationCenter.getSharedInstance().addObserver(AsyncException.NOTIFICATION_API_INVALID_ERROR, new C26122());
        this.mConnectivityReceiver = new C26133();
        registerReceiver(this.mConnectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.mModuleManager = ModuleManager.getSharedInstance();
        this.mConnectorManager = ConnectorManager.getSharedInstance();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra(EXTRA_CONFIG_RELOAD_INTENT)) {
            this.mReloadIntent = (Intent) intent.getParcelableExtra(EXTRA_CONFIG_RELOAD_INTENT);
        }
        return 2;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mNotificationListener != null) {
            NotificationCenter.getSharedInstance().removeObserver(this.mNotificationListener);
            this.mNotificationListener = null;
        }
        if (this.mAlertNotificationListener != null) {
            NotificationCenter.getSharedInstance().removeObserver(this.mAlertNotificationListener);
            this.mAlertNotificationListener = null;
        }
        if (this.mAPIKeyInvalidNotificationListener != null) {
            NotificationCenter.getSharedInstance().removeObserver(this.mAPIKeyInvalidNotificationListener);
            this.mAPIKeyInvalidNotificationListener = null;
        }
        if (this.mConnectivityReceiver != null) {
            unregisterReceiver(this.mConnectivityReceiver);
            this.mConnectivityReceiver = null;
        }
        this.mModuleManager.clearModules();
        this.mConnectorManager.clearConnectors();
    }

    public IBinder onBind(Intent intent) {
        return new ServiceBinder(this);
    }

    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        stopSelf();
    }

    @Deprecated
    public <T> T getModule(String moduleName) {
        return ModuleManager.getModule(moduleName);
    }

    public <T> T getConnector(String connectorName) {
        return ConnectorManager.getConnector(connectorName);
    }

    private void showGlobalAlertDialog(String title, String message) {
        if (TextUtils.isEmpty(message)) {
            message = getApplicationContext().getString(C3883R.string.error_generic);
        }
        if (mCurrentActivity != null && shouldShowDialog(message)) {
            AlertDialog dialog = new Builder(mCurrentActivity, 16973939).setTitle(title).setMessage(message).setCancelable(true).setNeutralButton(getString(C3883R.string.okay), this.mOnClickGlobalDialog).create();
            dialog.getWindow().setBackgroundDrawableResource(17170445);
            dialog.show();
            ((TextView) dialog.findViewById(16908299)).setGravity(17);
            TextView titleView = (TextView) dialog.findViewById(getResources().getIdentifier("alertTitle", "id", "android"));
            if (titleView != null) {
                titleView.setGravity(17);
            }
            this.mLastMessage = message;
            this.mLastNotification = System.currentTimeMillis();
        }
    }

    private void showInvalidAPIKeyAlertDialog(String message) {
        if (TextUtils.isEmpty(message)) {
            message = getApplicationContext().getString(C3883R.string.error_generic);
        }
        if (mCurrentActivity != null && shouldShowDialog(message)) {
            AlertDialog dialog = new Builder(mCurrentActivity, 16973939).setMessage(message).setCancelable(false).create();
            dialog.getWindow().setBackgroundDrawableResource(17170445);
            dialog.show();
            this.mLastMessage = message;
            this.mLastNotification = System.currentTimeMillis();
        }
    }

    private boolean shouldShowDialog(String message) {
        return !(message == null || message.equals(this.mLastMessage)) || this.mLastNotification <= System.currentTimeMillis() - 5000;
    }

    public static void setCurrentActivity(Activity activity) {
        mCurrentActivity = activity;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
        mCurrentActivity = activity;
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
        if (mCurrentActivity != null && mCurrentActivity.equals(activity)) {
            mCurrentActivity = null;
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
        if (mCurrentActivity != null && mCurrentActivity.equals(activity)) {
            mCurrentActivity = null;
        }
    }
}
