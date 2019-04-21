package com.mcdonalds.app.p043ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p001v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.analytics.datalayer.mapping.DataLayerPageMapping;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;

@Instrumented
/* renamed from: com.mcdonalds.app.ui.URLNavigationFragment */
public class URLNavigationFragment extends Fragment implements TraceFieldInterface {
    private static final String LOG_TAG = URLNavigationFragment.class.getSimpleName();
    public Trace _nr_trace;
    private Integer mCommitTransactionId;
    private Integer mDataPasserIndex;

    public void onActivityCreated(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{bundle});
        super.onActivityCreated(bundle);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "URLNavigationFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "URLNavigationFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        TraceMachine.exitMethod();
        return onCreateView;
    }

    public void onDestroy() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroy", null);
        super.onDestroy();
    }

    public void onDestroyView() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDestroyView", null);
        super.onDestroyView();
    }

    public void onPause() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onPause", null);
        super.onPause();
        Ensighten.processView((Object) this, "onPause");
    }

    public void onStart() {
        ApplicationStateMonitor.getInstance().activityStarted();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStart", null);
        super.onStart();
        Ensighten.processView((Object) this, "onStart");
    }

    public void onStop() {
        ApplicationStateMonitor.getInstance().activityStopped();
        EnsightenActivityHandler.onLifecycleMethod(this, "onStop", null);
        super.onStop();
    }

    public void onViewStateRestored(Bundle bundle) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onViewStateRestored", new Object[]{bundle});
        super.onViewStateRestored(bundle);
        Ensighten.processView((Object) this, "onViewStateRestored");
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("URLNavigationFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "URLNavigationFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "URLNavigationFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        ActionBar actionBar = getNavigationActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        if (getTitle() != null) {
            getNavigationActivity().setTitle(getTitle());
        }
        String analyticsTitle = getAnalyticsTitle();
        if (analyticsTitle != null) {
            AnalyticsUtils.trackScreenLoad(analyticsTitle);
        }
        if (DataLayerManager.isEnabled(Configuration.getSharedInstance())) {
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

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
        Log.i(LOG_TAG, String.format("Attached: %s", new Object[]{getClass().getSimpleName()}));
    }

    public void onDetach() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDetach", null);
        super.onDetach();
        Log.i(LOG_TAG, String.format("Detached: %s", new Object[]{getClass().getSimpleName()}));
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getActivity() != null ? (String) getActivity().getTitle() : null;
    }

    /* Access modifiers changed, original: protected */
    public View getTitleView() {
        Ensighten.evaluateEvent(this, "getTitleView", null);
        return null;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return null;
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

    public URLNavigationActivity getNavigationActivity() {
        Ensighten.evaluateEvent(this, "getNavigationActivity", null);
        FragmentActivity navigationActivity = getActivity();
        if (navigationActivity == null || !(navigationActivity instanceof URLNavigationActivity)) {
            return null;
        }
        return (URLNavigationActivity) navigationActivity;
    }

    /* Access modifiers changed, original: 0000 */
    public Integer getCommitTransactionId() {
        Ensighten.evaluateEvent(this, "getCommitTransactionId", null);
        return this.mCommitTransactionId;
    }

    /* Access modifiers changed, original: 0000 */
    public void setCommitTransactionId(Integer commitId) {
        Ensighten.evaluateEvent(this, "setCommitTransactionId", new Object[]{commitId});
        this.mCommitTransactionId = commitId;
    }

    public Integer getDataPasserIndex() {
        Ensighten.evaluateEvent(this, "getDataPasserIndex", null);
        return this.mDataPasserIndex;
    }

    public void setDataPasserIndex(Integer dataPasserIndex) {
        Ensighten.evaluateEvent(this, "setDataPasserIndex", new Object[]{dataPasserIndex});
        this.mDataPasserIndex = dataPasserIndex;
    }

    public boolean isActivityAlive() {
        Ensighten.evaluateEvent(this, "isActivityAlive", null);
        return (getActivity() == null || getActivity().isFinishing()) ? false : true;
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
        if (activityClass != null && getActivity() != null && isActivityAlive()) {
            getActivity().startActivity(createStartActivityIntent(activityClass, fragmentName, extras));
        }
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
        if (activityClass != null && getActivity() != null && isActivityAlive()) {
            getActivity().startActivityForResult(createStartActivityIntent(activityClass, fragmentName, extras), requestCode);
        }
    }

    @NonNull
    private Intent createStartActivityIntent(Class<?> activityClass, String fragmentName, Bundle extras) {
        Ensighten.evaluateEvent(this, "createStartActivityIntent", new Object[]{activityClass, fragmentName, extras});
        Intent intent = new Intent(getActivity(), activityClass);
        intent.addFlags(603979776);
        if (extras != null) {
            intent.putExtras(extras);
        }
        if (!TextUtils.isEmpty(fragmentName)) {
            intent.putExtra(URLNavigationActivity.ARG_FRAGMENT, fragmentName);
        }
        return intent;
    }

    public void showFragment(String fragmentName) {
        Ensighten.evaluateEvent(this, "showFragment", new Object[]{fragmentName});
        showFragment(fragmentName, null);
    }

    public void showFragment(String fragmentName, Bundle extras) {
        Ensighten.evaluateEvent(this, "showFragment", new Object[]{fragmentName, extras});
        if (isActivityAlive() && (getActivity() instanceof URLNavigationActivity)) {
            ((URLNavigationActivity) getActivity()).showFragment(fragmentName, extras);
        }
    }

    public void showFragment(Fragment fragment, Bundle extras) {
        Ensighten.evaluateEvent(this, "showFragment", new Object[]{fragment, extras});
        if (isActivityAlive() && (getActivity() instanceof URLNavigationActivity)) {
            ((URLNavigationActivity) getActivity()).showFragment(fragment, extras);
        }
    }

    public void openSelfUrl(String url) {
        Ensighten.evaluateEvent(this, "openSelfUrl", new Object[]{url});
        openSelfUrl(url, null);
    }

    public void openSelfUrl(String url, Bundle extras) {
        Ensighten.evaluateEvent(this, "openSelfUrl", new Object[]{url, extras});
        if (url == null || !url.startsWith(URLNavigationActivity.URI_SCHEME)) {
            throw new RuntimeException("That's not an internal uri");
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        intent.addFlags(603979776);
        if (extras != null) {
            intent.putExtras(extras);
        }
        getActivity().startActivity(intent);
    }
}
