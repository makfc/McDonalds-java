package com.mcdonalds.app.p043ui;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.AutoLoadedConfigurations;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.home.dashboard.DashboardViewModel;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.LoginManager;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p046se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/* renamed from: com.mcdonalds.app.ui.ConfigSwitcherFragment */
public class ConfigSwitcherFragment extends URLNavigationFragment {
    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return "/configuration-selection";
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_config_switcher, container, false);
        StickyListHeadersListView configListView = (StickyListHeadersListView) rootView.findViewById(C2358R.C2357id.config_listview);
        final List<String> configKeys = new ArrayList();
        configKeys.addAll(AutoLoadedConfigurations.getSharedInstance().keySet());
        Collections.sort(configKeys);
        configListView.setAdapter(new ConfigSwitcherListAdapter(getContext(), configKeys));
        configListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ensighten.evaluateEvent(this, "onItemClick", new Object[]{parent, view, new Integer(position), new Long(id)});
                CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
                customerModule.clearCurrentStore();
                customerModule.setCurrentProfile(null);
                LocalDataManager.getSharedInstance().setPrefSavedLogin("");
                LocalDataManager.getSharedInstance().setPrefSavedLoginPass("");
                LocalDataManager.getSharedInstance().setPrefSavedSocialNetworkId(-1);
                LocalDataManager.getSharedInstance().setTutorialLastShownVersionName(null);
                DashboardViewModel.destroy();
                LoginManager.destroy();
                LocationServicesManager.getInstance().disableUpdates();
                LocationServicesManager.destroy();
                String key = (String) configKeys.get(position);
                Configuration.getSharedInstance().loadConfigurationWithJsonString((String) AutoLoadedConfigurations.getSharedInstance().get(key));
                SparseArray custom = new SparseArray();
                custom.put(46, (String) Configuration.getSharedInstance().getValueForKey("longDescription"));
                AnalyticsUtils.trackOnClickEvent(ConfigSwitcherFragment.this.getAnalyticsTitle(), "Configuration Changed", custom);
                Analytics.destroy();
                Editor editor = ConfigSwitcherFragment.this.getActivity().getApplicationContext().getSharedPreferences("config", 0).edit();
                editor.putString(Configuration.PREF_CONFIG_KEY, key);
                if (!editor.commit()) {
                    AsyncException.report("Couldn't save prefs");
                }
            }
        });
        return rootView;
    }
}
