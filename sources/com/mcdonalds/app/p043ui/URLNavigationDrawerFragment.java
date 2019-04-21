package com.mcdonalds.app.p043ui;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.ActionBarDrawerToggle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.ensighten.model.activity.EnsightenActivityHandler;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.home.dashboard.DashboardViewModel;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.p043ui.models.DrawerItem;
import com.mcdonalds.app.startup.SplashActivity;
import com.mcdonalds.app.util.LanguageUtil;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.ConnectorManager;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import com.mcdonalds.sdk.services.network.SessionManager;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.List;

@Instrumented
/* renamed from: com.mcdonalds.app.ui.URLNavigationDrawerFragment */
public abstract class URLNavigationDrawerFragment extends Fragment implements TraceFieldInterface {
    public Trace _nr_trace;
    private List<DrawerItem> logInStateRequiredItems;
    private NavigationDrawerCallbacks mCallbacks;
    private int mCurrentSelectedPosition = 0;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private View mFragmentContainerView;
    private boolean mFromSavedInstanceState;
    private TextView mTvChina;
    private TextView mTvEng;
    private TextView mWelcomeMessage;

    /* renamed from: com.mcdonalds.app.ui.URLNavigationDrawerFragment$NavigationDrawerCallbacks */
    public interface NavigationDrawerCallbacks {
        void onNavigationDrawerItemSelected(DrawerItem drawerItem);
    }

    /* renamed from: com.mcdonalds.app.ui.URLNavigationDrawerFragment$1 */
    class C38051 implements OnItemClickListener {
        C38051() {
        }

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Ensighten.evaluateEvent(this, "onItemClick", new Object[]{parent, view, new Integer(position), new Long(id)});
            URLNavigationDrawerFragment.this.selectItem(position);
        }
    }

    /* renamed from: com.mcdonalds.app.ui.URLNavigationDrawerFragment$2 */
    class C38062 implements OnClickListener {
        C38062() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            URLNavigationDrawerFragment.access$000(URLNavigationDrawerFragment.this, C2358R.C2357id.tv_china_language);
        }
    }

    /* renamed from: com.mcdonalds.app.ui.URLNavigationDrawerFragment$3 */
    class C38073 implements OnClickListener {
        C38073() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            URLNavigationDrawerFragment.access$000(URLNavigationDrawerFragment.this, C2358R.C2357id.tv_eng_language);
        }
    }

    /* renamed from: com.mcdonalds.app.ui.URLNavigationDrawerFragment$4 */
    class C38084 implements DialogInterface.OnClickListener {
        C38084() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            dialog.cancel();
        }
    }

    /* renamed from: com.mcdonalds.app.ui.URLNavigationDrawerFragment$6 */
    class C38106 implements OnCancelListener {
        C38106() {
        }

        public void onCancel(DialogInterface dialog) {
            Ensighten.evaluateEvent(this, "onCancel", new Object[]{dialog});
            dialog.cancel();
        }
    }

    /* renamed from: com.mcdonalds.app.ui.URLNavigationDrawerFragment$8 */
    class C38128 implements Runnable {
        C38128() {
        }

        public void run() {
            Ensighten.evaluateEvent(this, "run", null);
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.URLNavigationDrawerFragment", "access$600", new Object[]{URLNavigationDrawerFragment.this}).syncState();
        }
    }

    public abstract ActionBarDrawerToggle getActionBarDrawerToggle();

    public abstract int getConfigurationChina();

    public abstract int getConfigurationENG();

    public abstract int getDrawerItemLayoutResource();

    public abstract ArrayAdapter<DrawerItem> getDrawerListAdapter();

    public abstract int getDrawerListViewResource();

    public abstract int getMenuResource();

    public abstract int getRootLayoutResource();

    public abstract int getWelcomeMessageResource();

    public void onActivityResult(int i, int i2, Intent intent) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityResult", new Object[]{new Integer(i), new Integer(i2), intent});
        super.onActivityResult(i, i2, intent);
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

    public void onResume() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onResume", null);
        super.onResume();
        Ensighten.processView((Object) this, "onResume");
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

    public abstract void styleDrawerLayout(DrawerLayout drawerLayout);

    static /* synthetic */ void access$000(URLNavigationDrawerFragment x0, int x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.URLNavigationDrawerFragment", "access$000", new Object[]{x0, new Integer(x1)});
        x0.showDialog(x1);
    }

    static /* synthetic */ void access$200(URLNavigationDrawerFragment x0, TextView x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.URLNavigationDrawerFragment", "access$200", new Object[]{x0, x1});
        x0.setTextLine(x1);
    }

    static /* synthetic */ void access$400(URLNavigationDrawerFragment x0, TextView x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.URLNavigationDrawerFragment", "access$400", new Object[]{x0, x1});
        x0.setTextNoLine(x1);
    }

    static /* synthetic */ void access$500(URLNavigationDrawerFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.URLNavigationDrawerFragment", "access$500", new Object[]{x0});
        x0.restart();
    }

    public void onCreate(Bundle bundle) {
        TraceMachine.startTracing("URLNavigationDrawerFragment");
        try {
            TraceMachine.enterMethod(this._nr_trace, "URLNavigationDrawerFragment#onCreate", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "URLNavigationDrawerFragment#onCreate", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreate", new Object[]{bundle});
        super.onCreate(bundle);
        if (bundle != null) {
            this.mCurrentSelectedPosition = bundle.getInt("selected_navigation_drawer_position");
            this.mFromSavedInstanceState = true;
        }
        Ensighten.processView((Object) this, "onCreate");
        TraceMachine.exitMethod();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            TraceMachine.enterMethod(this._nr_trace, "URLNavigationDrawerFragment#onCreateView", null);
        } catch (NoSuchFieldError e) {
            while (true) {
                TraceMachine.enterMethod(null, "URLNavigationDrawerFragment#onCreateView", null);
            }
        }
        EnsightenActivityHandler.onLifecycleMethod(this, "onCreateView", new Object[]{layoutInflater, viewGroup, bundle});
        View rootView = layoutInflater.inflate(getRootLayoutResource(), viewGroup, false);
        this.mDrawerListView = (ListView) rootView.findViewById(getDrawerListViewResource());
        this.mDrawerListView.setOnItemClickListener(new C38051());
        this.mWelcomeMessage = (TextView) rootView.findViewById(getWelcomeMessageResource());
        this.mTvChina = (TextView) rootView.findViewById(getConfigurationChina());
        this.mTvEng = (TextView) rootView.findViewById(getConfigurationENG());
        String savedLanguage = LocalDataManager.getSharedInstance().getDeviceLanguage();
        if (!TextUtils.isEmpty(savedLanguage)) {
            if (savedLanguage.equals("zh")) {
                setTextLine(this.mTvChina);
                setTextNoLine(this.mTvEng);
            } else if (savedLanguage.equals("en")) {
                setTextLine(this.mTvEng);
                setTextNoLine(this.mTvChina);
            } else {
                setTextLine(this.mTvEng);
                setTextNoLine(this.mTvChina);
            }
        }
        this.mTvChina.setOnClickListener(new C38062());
        this.mTvEng.setOnClickListener(new C38073());
        this.logInStateRequiredItems = new ArrayList();
        getDrawerListView().setAdapter(getDrawerListAdapter());
        getDrawerListView().setItemChecked(getCurrentSelectedPosition(), true);
        TraceMachine.exitMethod();
        return rootView;
    }

    private void showDialog(final int id) {
        Ensighten.evaluateEvent(this, "showDialog", new Object[]{new Integer(id)});
        MCDAlertDialogBuilder.withContext(getActivity()).setCancelable(true).setOnCancelListener(new C38106()).setPositiveButton((int) C2658R.string.language_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
                dialog.cancel();
                if (id == C2358R.C2357id.tv_china_language) {
                    LanguageUtil.changeAppLanguage(URLNavigationDrawerFragment.this.getResources(), "zh");
                    URLNavigationDrawerFragment.access$200(URLNavigationDrawerFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.URLNavigationDrawerFragment", "access$100", new Object[]{URLNavigationDrawerFragment.this}));
                    URLNavigationDrawerFragment.access$400(URLNavigationDrawerFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.URLNavigationDrawerFragment", "access$300", new Object[]{URLNavigationDrawerFragment.this}));
                    URLNavigationDrawerFragment.access$500(URLNavigationDrawerFragment.this);
                    return;
                }
                LanguageUtil.changeAppLanguage(URLNavigationDrawerFragment.this.getResources(), "en");
                URLNavigationDrawerFragment.access$200(URLNavigationDrawerFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.URLNavigationDrawerFragment", "access$300", new Object[]{URLNavigationDrawerFragment.this}));
                URLNavigationDrawerFragment.access$400(URLNavigationDrawerFragment.this, Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.URLNavigationDrawerFragment", "access$100", new Object[]{URLNavigationDrawerFragment.this}));
                URLNavigationDrawerFragment.access$500(URLNavigationDrawerFragment.this);
            }
        }).setNegativeButton((int) C2658R.string.language_cancel, new C38084()).setTitle((int) C2658R.string.language_heading).setMessage((int) C2658R.string.language_message).create().show();
    }

    @SuppressLint({"ResourceAsColor"})
    private void setTextLine(TextView textLine) {
        Ensighten.evaluateEvent(this, "setTextLine", new Object[]{textLine});
        textLine.setClickable(false);
        textLine.setEnabled(false);
        textLine.getPaint().setFlags(8);
        textLine.getPaint().setAntiAlias(true);
        textLine.setTextColor(getResources().getColor(17170443));
    }

    @SuppressLint({"ResourceAsColor"})
    private void setTextNoLine(TextView textLine) {
        Ensighten.evaluateEvent(this, "setTextNoLine", new Object[]{textLine});
        textLine.setClickable(true);
        textLine.setEnabled(true);
        textLine.getPaint().setFlags(0);
        textLine.setTextColor(getResources().getColor(C2658R.color.dark_gray_2));
    }

    private void restart() {
        Ensighten.evaluateEvent(this, "restart", null);
        final String prefSavedLogin = LocalDataManager.getSharedInstance().getPrefSavedLogin();
        final String prefSavedLoginPass = LocalDataManager.getSharedInstance().getPrefSavedLoginPass();
        final int prefSavedSocialID = LocalDataManager.getSharedInstance().getPrefSavedSocialNetworkId();
        final String tutorialLastShownVersion = LocalDataManager.getSharedInstance().getTutorialLastShownVersionName();
        UIUtils.startActivityIndicator(getActivity(), getActivity().getString(C2658R.string.title_loading));
        final CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        customerModule.logout(new AsyncListener<Void>() {
            public void onResponse(Void response, AsyncToken token, AsyncException exception) {
                Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                UIUtils.stopActivityIndicator();
                if (URLNavigationDrawerFragment.this.getActivity() != null) {
                    OrderManager.getInstance().cleanBagsFromOrder();
                    OrderingManager.getInstance().deleteCurrentOrder();
                    LanguageUtil.setClear(true);
                    LanguageUtil.setPrefRememberLogin(true);
                    customerModule.clearCurrentStore();
                    customerModule.setCurrentProfile(null);
                    SessionManager.getInstance().clearToken();
                    DashboardViewModel.destroy();
                    LoginManager.destroy();
                    ConnectorManager.getSharedInstance().clearConnectors();
                    ModuleManager.getSharedInstance().clearModules();
                    LocationServicesManager.getInstance().disableUpdates();
                    LocationServicesManager.destroy();
                    LocalDataManager.getSharedInstance().clearCache();
                    LocalDataManager.getSharedInstance().clear();
                    LocalDataManager.getSharedInstance().setPrefSavedLogin(prefSavedLogin);
                    LocalDataManager.getSharedInstance().setPrefSavedLoginPass(prefSavedLoginPass);
                    LocalDataManager.getSharedInstance().setPrefSavedSocialNetworkId(prefSavedSocialID);
                    LocalDataManager.getSharedInstance().setTutorialLastShownVersionName(tutorialLastShownVersion);
                    Intent intent = new Intent(URLNavigationDrawerFragment.this.getActivity(), SplashActivity.class);
                    intent.setFlags(268468224);
                    URLNavigationDrawerFragment.this.getActivity().startActivity(intent);
                }
            }
        });
    }

    public void onAttach(Activity activity) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onAttach", new Object[]{activity});
        super.onAttach(activity);
        try {
            this.mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    public void onDetach() {
        EnsightenActivityHandler.onLifecycleMethod(this, "onDetach", null);
        super.onDetach();
        this.mCallbacks = null;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        Ensighten.evaluateEvent(this, "setUp", new Object[]{new Integer(fragmentId), drawerLayout});
        this.mFragmentContainerView = getActivity().findViewById(fragmentId);
        this.mDrawerLayout = drawerLayout;
        styleDrawerLayout(this.mDrawerLayout);
        this.mDrawerToggle = getActionBarDrawerToggle();
        this.mDrawerLayout.post(new C38128());
        this.mDrawerLayout.setDrawerListener(this.mDrawerToggle);
    }

    public boolean isDrawerOpen() {
        Ensighten.evaluateEvent(this, "isDrawerOpen", null);
        return this.mDrawerLayout != null && this.mDrawerLayout.isDrawerOpen(this.mFragmentContainerView);
    }

    public void selectItem(int position) {
        Ensighten.evaluateEvent(this, "selectItem", new Object[]{new Integer(position)});
        if (!((DrawerItem) getDrawerListAdapter().getItem(position)).isHeading()) {
            this.mCurrentSelectedPosition = position;
            if (this.mDrawerListView != null) {
                this.mDrawerListView.setItemChecked(position, true);
            }
            if (this.mDrawerLayout != null) {
                this.mDrawerLayout.closeDrawer(this.mFragmentContainerView);
            }
            if (this.mCallbacks != null) {
                this.mCallbacks.onNavigationDrawerItemSelected((DrawerItem) getDrawerListAdapter().getItem(position));
            }
        }
    }

    public void toggleDrawerState() {
        Ensighten.evaluateEvent(this, "toggleDrawerState", null);
        if (this.mDrawerLayout.isDrawerOpen(this.mFragmentContainerView)) {
            this.mDrawerLayout.closeDrawer(this.mFragmentContainerView);
            return;
        }
        this.mDrawerLayout.openDrawer(this.mFragmentContainerView);
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.mDrawerLayout.getWindowToken(), 0);
        DataLayerManager.getInstance().logPageLoad("SideMenu", "PageViewed");
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        EnsightenActivityHandler.onLifecycleMethod(this, "onActivityCreated", new Object[]{savedInstanceState});
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onSaveInstanceState(Bundle outState) {
        Ensighten.evaluateEvent(this, "onSaveInstanceState", new Object[]{outState});
        super.onSaveInstanceState(outState);
        outState.putInt("selected_navigation_drawer_position", this.mCurrentSelectedPosition);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        if (this.mDrawerLayout != null && isDrawerOpen()) {
            inflater.inflate(getMenuResource(), menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void showGlobalContextActionBar() {
        ActionBar actionBar = null;
        Ensighten.evaluateEvent(this, "showGlobalContextActionBar", null);
        if (getActivity() != null) {
            actionBar = getActivity().getActionBar();
        }
        if (actionBar != null) {
            actionBar.setNavigationMode(0);
        }
    }

    /* Access modifiers changed, original: protected */
    public void setWelcomeMessage(String message) {
        Ensighten.evaluateEvent(this, "setWelcomeMessage", new Object[]{message});
        this.mWelcomeMessage.setText(message);
    }

    /* Access modifiers changed, original: protected */
    public void setWelcomeMessageVisibility(int visibility) {
        Ensighten.evaluateEvent(this, "setWelcomeMessageVisibility", new Object[]{new Integer(visibility)});
        this.mWelcomeMessage.setVisibility(visibility);
    }

    public int getCurrentSelectedPosition() {
        Ensighten.evaluateEvent(this, "getCurrentSelectedPosition", null);
        return this.mCurrentSelectedPosition;
    }

    public final ListView getDrawerListView() {
        Ensighten.evaluateEvent(this, "getDrawerListView", null);
        return this.mDrawerListView;
    }

    public final DrawerLayout getDrawerLayout() {
        Ensighten.evaluateEvent(this, "getDrawerLayout", null);
        return this.mDrawerLayout;
    }

    public List<DrawerItem> getLogInStateRequiredItems() {
        Ensighten.evaluateEvent(this, "getLogInStateRequiredItems", null);
        return this.logInStateRequiredItems;
    }

    public final boolean isUserLearnedDrawer() {
        Ensighten.evaluateEvent(this, "isUserLearnedDrawer", null);
        return LocalDataManager.getSharedInstance().getBoolean("navigation_drawer_learned", false);
    }

    public final void setUserLearnedDrawer(boolean userLearnedDrawer) {
        Ensighten.evaluateEvent(this, "setUserLearnedDrawer", new Object[]{new Boolean(userLearnedDrawer)});
        LocalDataManager.getSharedInstance().set("navigation_drawer_learned", userLearnedDrawer);
    }
}
