package com.mcdonalds.app.p043ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.content.ContextCompat;
import android.support.p001v7.app.ActionBar;
import android.support.p001v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.EnsightenGestureRecognizerFactory;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.mapping.DataLayerPageMapping;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.p043ui.URLNavigationDrawerFragment.NavigationDrawerCallbacks;
import com.mcdonalds.app.p043ui.models.DrawerItem;
import com.mcdonalds.app.util.SafeLog;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.app.widget.AutoResizeTextView;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.McDonalds;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.services.RonaldService;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.log.MCDLog;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import p041io.github.inflationx.viewpump.ViewPumpContextWrapper;

@Instrumented
/* renamed from: com.mcdonalds.app.ui.URLNavigationActivity */
public abstract class URLNavigationActivity extends AppCompatActivity implements NavigationDrawerCallbacks, TraceFieldInterface {
    public static final String ARG_FRAGMENT = "fragment";
    @Deprecated
    public static final String CONTROLLER_PASSER_KEY = "CONTROLLER_PASSER_KEY";
    @Deprecated
    public static final String DATA_PASSER_KEY = "DATA_PASSER_KEY";
    private static final String ENABLE_SECURE_WINDOW = "interface.enableSecureWindow";
    private static final String LOG_TAG = URLNavigationActivity.class.getSimpleName();
    public static final String MODAL = "modal";
    public static final String SHOW_ACTIVATION_DIALOG = "SHOW_ACTIVATION_DIALOG";
    public static final String URI_SCHEME = "mcdmobileapp://";
    public Trace _nr_trace;
    private final HashMap<Integer, PermissionListener> mPermissionListenerMap = new HashMap();

    /* renamed from: com.mcdonalds.app.ui.URLNavigationActivity$PermissionListener */
    public interface PermissionListener {
        void onRequestPermissionsResult(int i, String str, int i2);
    }

    /* renamed from: com.mcdonalds.app.ui.URLNavigationActivity$1 */
    class C24301 implements OnClickListener {
        C24301() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            URLNavigationActivity.this.onBackPressed();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        EnsightenGestureRecognizerFactory.getFourFingerGestureRecognizer().process(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    public abstract int getContainerResource();

    public abstract int getContentViewResource();

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    /* Access modifiers changed, original: protected */
    public Dialog onCreateDialog(int i, Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateDialog", new Object[]{new Integer(i), bundle});
        return super.onCreateDialog(i);
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        Ensighten.processView((Object) this, "onPause");
    }

    /* Access modifiers changed, original: protected */
    public void onRestart() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onRestart", null);
        super.onRestart();
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
        super.onStart();
        Ensighten.processView((Object) this, "onStart");
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("URLNavigationActivity");
        try {
            TraceMachine.enterMethod(this._nr_trace, "URLNavigationActivity#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "URLNavigationActivity#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        setContentView(getContentViewResource());
        if (McDonalds.isInitialized()) {
            RonaldService.setCurrentActivity(this);
        } else {
            McDonalds.initialize(this);
        }
        if (Configuration.getSharedInstance().isLocaleChanged(getResources().getConfiguration().locale)) {
            Configuration.getSharedInstance().setPreferredLanguage();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            boolean showUp = shouldNavigateUp();
            actionBar.setDisplayHomeAsUpEnabled(showUp);
            actionBar.setDisplayShowHomeEnabled(showUp);
        }
        SafeLog.m7739i(LOG_TAG, String.format("Create: %s", new Object[]{getClass().getSimpleName()}));
        if (Configuration.getSharedInstance().getBooleanForKey(ENABLE_SECURE_WINDOW)) {
            getWindow().setFlags(8192, 8192);
        }
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        if ((getDisplayedFragment() == null || !(getDisplayedFragment() instanceof URLNavigationFragment)) && DataLayerManager.isEnabled(Configuration.getSharedInstance())) {
            String pageSection = getDataLayerPageSection();
            if (pageSection != null) {
                DataLayerManager.getInstance().setPageSection(pageSection);
            }
            String pageName = getDataLayerPage();
            String eventName = getDataLayerEvent();
            if (!(pageName == null || eventName == null)) {
                DataLayerManager.getInstance().logPageLoad(pageName, eventName);
            }
        }
        Ensighten.processView((Object) this, "onResume");
    }

    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
        Ensighten.evaluateEvent(this, "onConfigurationChanged", new Object[]{newConfig});
        super.onConfigurationChanged(newConfig);
        if (this instanceof MainActivity) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(268533760);
            startActivity(intent);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldNavigateUp() {
        Ensighten.evaluateEvent(this, "shouldNavigateUp", null);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context newBase) {
        Ensighten.evaluateEvent(this, "attachBaseContext", new Object[]{newBase});
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
        SafeLog.m7739i(LOG_TAG, String.format("Destroy: %s", new Object[]{getClass().getSimpleName()}));
    }

    /* Access modifiers changed, original: protected */
    public Fragment getScreenFragment(@NonNull String screen) {
        Ensighten.evaluateEvent(this, "getScreenFragment", new Object[]{screen});
        return new FragmentNotFoundFragment();
    }

    /* Access modifiers changed, original: protected */
    public boolean shouldAutoSetParentIntent() {
        Ensighten.evaluateEvent(this, "shouldAutoSetParentIntent", null);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void navigationItemClicked(DrawerItem item) {
        Ensighten.evaluateEvent(this, "navigationItemClicked", new Object[]{item});
    }

    public void showNavigateUp(boolean show) {
        Ensighten.evaluateEvent(this, "showNavigateUp", new Object[]{new Boolean(show)});
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(show);
            actionBar.setDisplayShowHomeEnabled(show);
        }
    }

    public void showLabelInsteadOfNavigateUp(boolean show, String title) {
        Ensighten.evaluateEvent(this, "showLabelInsteadOfNavigateUp", new Object[]{new Boolean(show), title});
        showLabelInsteadOfNavigateUp(show, title, -1);
    }

    public void showLabelInsteadOfNavigateUp(boolean show, String title, int closeLabelId) {
        Ensighten.evaluateEvent(this, "showLabelInsteadOfNavigateUp", new Object[]{new Boolean(show), title, new Integer(closeLabelId)});
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        if (show) {
            actionBar.setDisplayHomeAsUpEnabled(show);
            actionBar.setDisplayShowHomeEnabled(show);
            actionBar.setCustomView((int) C2658R.layout.action_bar_basket_custom_view);
            ((TextView) actionBar.getCustomView().findViewById(C2358R.C2357id.action_title)).setText(title);
            ((TextView) actionBar.getCustomView().findViewById(C2358R.C2357id.action_cancel)).setVisibility(8);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setTitle((CharSequence) title);
            return;
        }
        actionBar.setDisplayHomeAsUpEnabled(show);
        actionBar.setDisplayShowHomeEnabled(show);
        actionBar.setCustomView((int) C2658R.layout.action_bar_basket_custom_view);
        ((TextView) actionBar.getCustomView().findViewById(C2358R.C2357id.action_title)).setText(title);
        TextView closeView = (TextView) actionBar.getCustomView().findViewById(C2358R.C2357id.action_cancel);
        closeView.setVisibility(0);
        if (closeLabelId == -1) {
            closeLabelId = C2658R.string.cancel;
        }
        closeView.setText(closeLabelId);
        closeView.setOnClickListener(new C24301());
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle((CharSequence) title);
    }

    public void showFragment(String fragmentName) {
        Ensighten.evaluateEvent(this, "showFragment", new Object[]{fragmentName});
        showFragment(fragmentName, null);
    }

    public void showFragment(Fragment fragment, Bundle extras) {
        Ensighten.evaluateEvent(this, "showFragment", new Object[]{fragment, extras});
        getSupportFragmentManager().beginTransaction().replace(getContainerResource(), fragment).commit();
    }

    public void showFragment(String fragmentName, Bundle extras) {
        Ensighten.evaluateEvent(this, "showFragment", new Object[]{fragmentName, extras});
        if (TextUtils.isEmpty(fragmentName)) {
            throw new RuntimeException("Cannot show an empty fragment name");
        }
        FragmentManager manager = getSupportFragmentManager();
        if (!manager.popBackStackImmediate(fragmentName, 0)) {
            if (manager.findFragmentByTag(fragmentName) != null) {
                manager.popBackStackImmediate(null, 1);
                return;
            }
            FragmentTransaction transaction = manager.beginTransaction();
            Fragment fragment = getScreenFragment(fragmentName);
            String home = null;
            if (extras != null) {
                fragment.setArguments(extras);
                home = extras.getString("interface.home");
            }
            transaction.replace(getContainerResource(), fragment);
            if (!TextUtils.isEmpty(home)) {
                transaction.addToBackStack(home);
            }
            if (fragmentName.equals("dashboard")) {
                transaction.addToBackStack(null);
            } else {
                transaction.addToBackStack(fragmentName);
            }
            transaction.commitAllowingStateLoss();
        }
    }

    public Fragment getDisplayedFragment() {
        Ensighten.evaluateEvent(this, "getDisplayedFragment", null);
        return getSupportFragmentManager().findFragmentById(getContainerResource());
    }

    public void startActivity(Class<?> activityClass) {
        Ensighten.evaluateEvent(this, "startActivity", new Object[]{activityClass});
        startActivity(activityClass, null, null);
    }

    public void startActivity(Class<?> activityClass, String fragmentName) {
        Ensighten.evaluateEvent(this, "startActivity", new Object[]{activityClass, fragmentName});
        startActivity(activityClass, fragmentName, null);
    }

    public void startActivity(Class<?> activityClass, Bundle extras) {
        Ensighten.evaluateEvent(this, "startActivity", new Object[]{activityClass, extras});
        startActivity(activityClass, null, extras);
    }

    public void startActivity(Class<?> activityClass, String fragmentName, Bundle extras) {
        Ensighten.evaluateEvent(this, "startActivity", new Object[]{activityClass, fragmentName, extras});
        startActivity(createStartActivityIntent(activityClass, fragmentName, extras));
    }

    public void startActivityForResult(Class<?> activityClass, int requestCode) {
        Ensighten.evaluateEvent(this, "startActivityForResult", new Object[]{activityClass, new Integer(requestCode)});
        startActivityForResult(activityClass, null, null, requestCode);
    }

    public void startActivityForResult(Class<?> activityClass, String fragmentName, int requestCode) {
        Ensighten.evaluateEvent(this, "startActivityForResult", new Object[]{activityClass, fragmentName, new Integer(requestCode)});
        startActivityForResult(activityClass, fragmentName, null, requestCode);
    }

    public void startActivityForResult(Class<?> activityClass, Bundle extras, int requestCode) {
        Ensighten.evaluateEvent(this, "startActivityForResult", new Object[]{activityClass, extras, new Integer(requestCode)});
        startActivityForResult(activityClass, null, extras, requestCode);
    }

    public void startActivityForResult(Class<?> activityClass, String fragmentName, Bundle extras, int requestCode) {
        Ensighten.evaluateEvent(this, "startActivityForResult", new Object[]{activityClass, fragmentName, extras, new Integer(requestCode)});
        startActivityForResult(createStartActivityIntent(activityClass, fragmentName, extras), requestCode);
    }

    @NonNull
    private Intent createStartActivityIntent(Class<?> activityClass, String fragmentName, Bundle extras) {
        Ensighten.evaluateEvent(this, "createStartActivityIntent", new Object[]{activityClass, fragmentName, extras});
        Intent intent = new Intent(this, activityClass);
        intent.addFlags(603979776);
        if (extras != null) {
            intent.putExtras(extras);
        }
        if (!TextUtils.isEmpty(fragmentName)) {
            intent.putExtra(ARG_FRAGMENT, fragmentName);
        }
        return intent;
    }

    public void openSelfUrl(String url, Bundle extras) {
        Ensighten.evaluateEvent(this, "openSelfUrl", new Object[]{url, extras});
        if (url == null || !url.startsWith(URI_SCHEME)) {
            throw new RuntimeException("That's not an internal uri");
        }
        if (url.equalsIgnoreCase("mcdmobileapp://start_order")) {
            OrderingManager.getInstance().getCurrentOrder().setIsDelivery(false);
            LocalDataManager.getSharedInstance().setModuleName("start_order");
        } else if (url.equalsIgnoreCase("mcdmobileapp://start_order_delivery")) {
            OrderingManager.getInstance().getCurrentOrder().setIsDelivery(true);
            LocalDataManager.getSharedInstance().setModuleName("start_order_delivery");
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        intent.addFlags(603979776);
        if (extras != null) {
            intent.putExtras(extras);
        }
        startActivity(intent);
    }

    /* Access modifiers changed, original: protected */
    public Fragment passIntentExtrasAsArgument(Fragment fragment) {
        Ensighten.evaluateEvent(this, "passIntentExtrasAsArgument", new Object[]{fragment});
        if (getIntent().getExtras() != null) {
            fragment.setArguments(getIntent().getExtras());
        } else {
            fragment.setArguments(new Bundle());
        }
        return fragment;
    }

    public final void setTitleView(View view) {
        Ensighten.evaluateEvent(this, "setTitleView", new Object[]{view});
        setTitle(null);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setCustomView(view);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case 16908332:
                backToParent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void backToParent() {
        Ensighten.evaluateEvent(this, "backToParent", null);
        onBackPressed();
    }

    public final void setTitle(CharSequence title) {
        Ensighten.evaluateEvent(this, "setTitle", new Object[]{title});
        super.setTitle(title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setCustomView((int) C2658R.layout.action_bar_default_custom_view);
            ((AutoResizeTextView) actionBar.getCustomView()).setText(title);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setTitle(title);
        }
    }

    public final void onNavigationDrawerItemSelected(final DrawerItem drawerItem) {
        Ensighten.evaluateEvent(this, "onNavigationDrawerItemSelected", new Object[]{drawerItem});
        Bundle extras = new Bundle();
        Map<String, ? extends Serializable> attributes = drawerItem.getAttributes();
        if (attributes != null) {
            for (String key : attributes.keySet()) {
                extras.putSerializable(key, (Serializable) attributes.get(key));
            }
        }
        if (Configuration.getSharedInstance().getBooleanForKey("log.logsToConsole")) {
            MCDLog.info(drawerItem.getUrl());
        }
        navigationItemClicked(drawerItem);
        if (drawerItem.getUrl() == null) {
            return;
        }
        final Order currentOrder;
        final Bundle finalExtras;
        if (drawerItem.getUrl().contains("start_order_delivery")) {
            currentOrder = OrderingManager.getInstance().getCurrentOrder();
            if (currentOrder.isEmpty() || currentOrder.isDelivery()) {
                currentOrder.setIsDelivery(true);
                currentOrder.setPriceType(PriceType.Delivery);
                LocalDataManager.getSharedInstance().setModuleName("start_order_delivery");
                openSelfUrl(drawerItem.getUrl(), extras);
                return;
            }
            finalExtras = extras;
            MCDAlertDialogBuilder.withContext(this).setMessage((int) C2658R.string.cart_will_be_cleared).setPositiveButton(getString(C2658R.string.continue_button), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    currentOrder.clearOffers();
                    currentOrder.clearProducts();
                    currentOrder.setIsDelivery(true);
                    currentOrder.setPriceType(PriceType.Delivery);
                    LocalDataManager.getSharedInstance().setModuleName("start_order_delivery");
                    URLNavigationActivity.this.openSelfUrl(drawerItem.getUrl(), finalExtras);
                }
            }).setNegativeButton(getString(C2658R.string.button_cancel), null).setCancelable(false).create().show();
        } else if (drawerItem.getUrl().contains("start_order")) {
            currentOrder = OrderingManager.getInstance().getCurrentOrder();
            if (currentOrder.isEmpty() || !currentOrder.isDelivery()) {
                currentOrder.setIsDelivery(false);
                currentOrder.setPriceType(PriceType.TakeOut);
                LocalDataManager.getSharedInstance().setModuleName("start_order");
                openSelfUrl(drawerItem.getUrl(), extras);
                return;
            }
            finalExtras = extras;
            MCDAlertDialogBuilder.withContext(this).setMessage((int) C2658R.string.cart_will_be_cleared).setPositiveButton(getString(C2658R.string.continue_button), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    currentOrder.clearOffers();
                    currentOrder.clearProducts();
                    currentOrder.setIsDelivery(false);
                    currentOrder.setPriceType(PriceType.TakeOut);
                    LocalDataManager.getSharedInstance().setModuleName("start_order");
                    URLNavigationActivity.this.openSelfUrl(drawerItem.getUrl(), finalExtras);
                }
            }).setNegativeButton(getString(C2658R.string.button_cancel), null).setCancelable(false).create().show();
        } else {
            openSelfUrl(drawerItem.getUrl(), extras);
        }
    }

    public void onBackPressed() {
        Ensighten.evaluateEvent(this, "onBackPressed", null);
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory("/side-menu").setAction("On click").setLabel("Cancel").build());
        DataLayerManager.getInstance().logButtonClick("BackButtonPressed");
        super.onBackPressed();
    }

    /* Access modifiers changed, original: protected */
    public String getDataLayerPageSection() {
        Ensighten.evaluateEvent(this, "getDataLayerPageSection", null);
        return getDataLayerPage();
    }

    /* Access modifiers changed, original: protected */
    public String getDataLayerPage() {
        Ensighten.evaluateEvent(this, "getDataLayerPage", null);
        String className = getClass().getName();
        String pageName = DataLayerPageMapping.get(className);
        if (pageName != null) {
            return pageName;
        }
        DataLayerManager.reportWarning("Page name not set for this page. Using " + className + " as a default");
        return className;
    }

    /* Access modifiers changed, original: protected */
    public String getDataLayerEvent() {
        Ensighten.evaluateEvent(this, "getDataLayerEvent", null);
        return "PageViewed";
    }

    public String getVisibleDataLayerPage() {
        Ensighten.evaluateEvent(this, "getVisibleDataLayerPage", null);
        if (getDisplayedFragment() == null || !(getDisplayedFragment() instanceof URLNavigationFragment)) {
            return getDataLayerPage();
        }
        return ((URLNavigationFragment) getDisplayedFragment()).getDataLayerPage();
    }

    public boolean isPermissionGranted(String permission) {
        Ensighten.evaluateEvent(this, "isPermissionGranted", new Object[]{permission});
        if (VERSION.SDK_INT < 23) {
            return true;
        }
        if (ContextCompat.checkSelfPermission(this, permission) == 0) {
            return true;
        }
        return false;
    }

    public void requestPermission(final String permission, final int requestCode, int rationaleTextResource, final PermissionListener listener) {
        Ensighten.evaluateEvent(this, "requestPermission", new Object[]{permission, new Integer(requestCode), new Integer(rationaleTextResource), listener});
        if (isPermissionGranted(permission)) {
            listener.onRequestPermissionsResult(requestCode, permission, 0);
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            MCDAlertDialogBuilder.withContext(this).setTitle((int) C2658R.string.permission_explanation_title).setMessage(rationaleTextResource).setPositiveButton((int) C2658R.string.f6083ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                    URLNavigationActivity.this.requestPermission(permission, requestCode, listener);
                    dialog.dismiss();
                }
            }).create().show();
        } else {
            requestPermission(permission, requestCode, new PermissionListener() {
                public void onRequestPermissionsResult(final int requestCode, final String permission, final int grantResult) {
                    Ensighten.evaluateEvent(this, "onRequestPermissionsResult", new Object[]{new Integer(requestCode), permission, new Integer(grantResult)});
                    if (grantResult != -1 || ActivityCompat.shouldShowRequestPermissionRationale(URLNavigationActivity.this, permission)) {
                        listener.onRequestPermissionsResult(requestCode, permission, grantResult);
                        return;
                    }
                    String permissionName;
                    try {
                        permissionName = URLNavigationActivity.this.getPackageManager().getPermissionGroupInfo(URLNavigationActivity.this.getPackageManager().getPermissionInfo(permission, 128).group, 128).loadLabel(URLNavigationActivity.this.getPackageManager()).toString();
                    } catch (NameNotFoundException e) {
                        e.printStackTrace();
                        permissionName = permission.replace("android.permission.", "");
                    }
                    MCDAlertDialogBuilder.withContext(URLNavigationActivity.this).setTitle((int) C2658R.string.permission_denied_title).setMessage(URLNavigationActivity.this.getString(C2658R.string.permission_denied_message, new Object[]{permissionName})).setPositiveButton((int) C2658R.string.f6083ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                            listener.onRequestPermissionsResult(requestCode, permission, grantResult);
                            dialog.dismiss();
                        }
                    }).create().show();
                }
            });
        }
    }

    public void requestPermission(String permission, int requestCode, @Nullable PermissionListener listener) {
        Ensighten.evaluateEvent(this, "requestPermission", new Object[]{permission, new Integer(requestCode), listener});
        if (listener != null) {
            this.mPermissionListenerMap.put(Integer.valueOf(requestCode), listener);
        }
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Ensighten.evaluateEvent(this, "onRequestPermissionsResult", new Object[]{new Integer(requestCode), permissions, grantResults});
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionListener listener = (PermissionListener) this.mPermissionListenerMap.get(Integer.valueOf(requestCode));
        if (listener != null && permissions.length > 0 && grantResults.length > 0) {
            listener.onRequestPermissionsResult(requestCode, permissions[0], grantResults[0]);
            this.mPermissionListenerMap.remove(Integer.valueOf(requestCode));
        }
    }
}
