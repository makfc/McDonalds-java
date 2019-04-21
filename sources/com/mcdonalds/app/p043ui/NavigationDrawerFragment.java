package com.mcdonalds.app.p043ui;

import android.os.Bundle;
import android.support.p000v4.app.ActionBarDrawerToggle;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import com.ensighten.Ensighten;
import com.google.gson.internal.LinkedTreeMap;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.navigation.DrawerListAdapter;
import com.mcdonalds.app.p043ui.models.DrawerItem;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/* renamed from: com.mcdonalds.app.ui.NavigationDrawerFragment */
public class NavigationDrawerFragment extends URLNavigationDrawerFragment implements Observer {
    private CustomerModule mCustomerModule;
    private DrawerListAdapter mDrawerListAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginManager.getInstance().addObserver(this);
    }

    public void onDestroy() {
        super.onDestroy();
        LoginManager.getInstance().deleteObserver(this);
    }

    /* Access modifiers changed, original: protected */
    public int getRootLayoutResource() {
        Ensighten.evaluateEvent(this, "getRootLayoutResource", null);
        return C2658R.layout.fragment_navigation_drawer;
    }

    /* Access modifiers changed, original: protected */
    public int getMenuResource() {
        Ensighten.evaluateEvent(this, "getMenuResource", null);
        return C2358R.C2360menu.main;
    }

    /* Access modifiers changed, original: protected */
    public int getDrawerItemLayoutResource() {
        Ensighten.evaluateEvent(this, "getDrawerItemLayoutResource", null);
        return C2658R.layout.drawer_item;
    }

    /* Access modifiers changed, original: protected */
    public int getDrawerListViewResource() {
        Ensighten.evaluateEvent(this, "getDrawerListViewResource", null);
        return C2358R.C2357id.navigation_list;
    }

    /* Access modifiers changed, original: protected */
    public int getWelcomeMessageResource() {
        Ensighten.evaluateEvent(this, "getWelcomeMessageResource", null);
        return C2358R.C2357id.welcome_message;
    }

    /* Access modifiers changed, original: protected */
    public int getConfigurationChina() {
        Ensighten.evaluateEvent(this, "getConfigurationChina", null);
        return C2358R.C2357id.tv_china_language;
    }

    /* Access modifiers changed, original: protected */
    public int getConfigurationENG() {
        Ensighten.evaluateEvent(this, "getConfigurationENG", null);
        return C2358R.C2357id.tv_eng_language;
    }

    /* Access modifiers changed, original: protected */
    public void styleDrawerLayout(DrawerLayout drawerLayout) {
        Ensighten.evaluateEvent(this, "styleDrawerLayout", new Object[]{drawerLayout});
        if (drawerLayout != null) {
            drawerLayout.setDrawerShadow((int) C2358R.C2359drawable.drawer_shadow, (int) GravityCompat.START);
        }
    }

    public ActionBarDrawerToggle getActionBarDrawerToggle() {
        Ensighten.evaluateEvent(this, "getActionBarDrawerToggle", null);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), getDrawerLayout(), C2358R.C2359drawable.transparent, C2658R.string.navigation_drawer_open, C2658R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                Ensighten.evaluateEvent(this, "onDrawerOpened", new Object[]{drawerView});
                super.onDrawerOpened(drawerView);
                HashMap<String, Object> jiceMap = new HashMap();
                jiceMap.put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_DRAWER_OPEN);
                Analytics.track(AnalyticType.ScreenLoad, new ArgBuilder().setAction(NavigationDrawerFragment.this.getActivity().getString(C2658R.string.analytics_screen_side_menu)).setJice(jiceMap).build());
                if (NavigationDrawerFragment.this.isAdded()) {
                    if (!NavigationDrawerFragment.this.isUserLearnedDrawer()) {
                        NavigationDrawerFragment.this.setUserLearnedDrawer(true);
                    }
                    NavigationDrawerFragment.this.getActivity().invalidateOptionsMenu();
                }
            }

            public void onDrawerClosed(View drawerView) {
                Ensighten.evaluateEvent(this, "onDrawerClosed", new Object[]{drawerView});
                super.onDrawerClosed(drawerView);
                if (NavigationDrawerFragment.this.isAdded()) {
                    NavigationDrawerFragment.this.getActivity().invalidateOptionsMenu();
                }
            }

            public void onDrawerStateChanged(int newState) {
                Ensighten.evaluateEvent(this, "onDrawerStateChanged", new Object[]{new Integer(newState)});
                super.onDrawerStateChanged(newState);
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(false);
        return drawerToggle;
    }

    public DrawerListAdapter getDrawerListAdapter() {
        if (this.mDrawerListAdapter == null) {
            this.mDrawerListAdapter = new DrawerListAdapter(getActivity(), getDrawerItemLayoutResource(), getDrawerItemList());
        }
        return this.mDrawerListAdapter;
    }

    public void onStart() {
        super.onStart();
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        setLoggedInDrawerState(this.mCustomerModule.isLoggedIn());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        if (getActionBarDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() != C2358R.C2357id.action_hamburger_menu) {
            return super.onOptionsItemSelected(item);
        }
        AnalyticsUtils.trackHamburgerMenu();
        toggleDrawerState();
        return true;
    }

    /* Access modifiers changed, original: protected */
    public List<DrawerItem> getDrawerItemList() {
        Ensighten.evaluateEvent(this, "getDrawerItemList", null);
        List<DrawerItem> drawerItemList = new ArrayList();
        Iterator it = ((ArrayList) Configuration.getSharedInstance().getValueForKey("interface.appmenu.sections")).iterator();
        while (it.hasNext()) {
            LinkedTreeMap linkedHashMap = (LinkedTreeMap) it.next();
            String sectionTitleKey = (String) linkedHashMap.get("sectionTitle");
            if (!Configuration.DEBUG_CONFIG_KEY.equalsIgnoreCase(sectionTitleKey)) {
                if (!TextUtils.isEmpty(sectionTitleKey)) {
                    DrawerItem drawerHeader = new DrawerItem();
                    drawerHeader.setHeading(true);
                    drawerHeader.setSectionTitle(UIUtils.getStringByName(getActivity(), sectionTitleKey));
                    drawerHeader.setLeftHandImageName("itemLeftImage");
                    drawerHeader.setRightHandImageName("itemRightImage");
                    drawerHeader.setAttributes((Map) linkedHashMap.get("itemAttr"));
                    drawerItemList.add(drawerHeader);
                }
                Iterator it2 = ((ArrayList) linkedHashMap.get("items")).iterator();
                while (it2.hasNext()) {
                    LinkedTreeMap item = (LinkedTreeMap) it2.next();
                    DrawerItem drawerItem = new DrawerItem();
                    drawerItem.setHeading(false);
                    String itemTitleKey = (String) item.get("itemName");
                    String itemLeftImageKey = (String) item.get("itemLeftImage");
                    String itemRightImageKey = (String) item.get("itemRightImage");
                    Boolean requiresLoginState = (Boolean) item.get("itemRequiresLoginState");
                    Boolean requiresLogutState = (Boolean) item.get("itemRequiresLogoutState");
                    drawerItem.setSectionTitle(UIUtils.getStringByName(getActivity(), itemTitleKey));
                    drawerItem.setAnalyticsTitle(UIUtils.getStringByName(getActivity(), "analytics_" + itemTitleKey));
                    drawerItem.setDataLayerTitle(itemTitleKey + "ItemPressed");
                    drawerItem.setLeftHandImageName(itemLeftImageKey);
                    drawerItem.setRightHandImageName(itemRightImageKey);
                    drawerItem.setUrl((String) item.get("itemLink"));
                    drawerItem.setAttributes((Map) item.get("itemAttr"));
                    drawerItem.setRequiresLoginState(requiresLoginState);
                    drawerItem.setRequiresLogoutState(requiresLogutState);
                    drawerItemList.add(drawerItem);
                    if (requiresLoginState != null) {
                        getLogInStateRequiredItems().add(drawerItem);
                    }
                }
            }
        }
        return drawerItemList;
    }

    public void setLoggedInDrawerState(boolean loggedIn) {
        Ensighten.evaluateEvent(this, "setLoggedInDrawerState", new Object[]{new Boolean(loggedIn)});
        if (getDrawerListAdapter() != null && getLogInStateRequiredItems() != null) {
            getDrawerListAdapter().getFilter().filter(loggedIn ? "logged_in_state" : "logged_out_state");
            getDrawerListAdapter().notifyDataSetChanged();
            String welcomeMsg = "";
            if (this.mCustomerModule != null && loggedIn) {
                if (this.mCustomerModule.getCurrentProfile() != null) {
                    welcomeMsg = String.format(getResources().getString(C2658R.string.nav_drawer_welcome_message), new Object[]{this.mCustomerModule.getCurrentProfile().getFirstName()});
                }
                setWelcomeMessageVisibility(0);
            }
            setWelcomeMessage(welcomeMsg);
        }
    }

    public void update(Observable observable, Object data) {
        Ensighten.evaluateEvent(this, "update", new Object[]{observable, data});
        if (((Integer) data).intValue() == 3001) {
            setLoggedInDrawerState(LoginManager.getInstance().isLoggedIn());
        }
    }
}
