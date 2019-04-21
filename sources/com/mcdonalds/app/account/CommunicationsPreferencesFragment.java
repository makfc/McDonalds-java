package com.mcdonalds.app.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.NotificationPreferences;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CommunicationsPreferencesFragment extends URLNavigationFragment {
    public static final String NAME = CommunicationsPreferencesFragment.class.getSimpleName();
    private NotificationPreferences mAllPreferences;
    private boolean mChangesMade = false;
    CustomerModule mCustomerModule;
    private TreeMap<String, NotificationEntry> mDisplayNamesMap;
    private Boolean mEmailEnabled;
    private LinearLayout mListContainer;
    private ListView mListview;
    private LinearLayout mMasterToggleContainer;

    /* renamed from: com.mcdonalds.app.account.CommunicationsPreferencesFragment$1 */
    class C29551 implements OnCheckedChangeListener {
        C29551() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{buttonView, new Boolean(isChecked)});
            CommunicationsPreferencesFragment.access$002(CommunicationsPreferencesFragment.this, true);
            if (isChecked) {
                Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.CommunicationsPreferencesFragment", "access$100", new Object[]{CommunicationsPreferencesFragment.this}).setVisibility(0);
                CommunicationsPreferencesFragment.access$202(CommunicationsPreferencesFragment.this, Boolean.valueOf(true));
                return;
            }
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.CommunicationsPreferencesFragment", "access$100", new Object[]{CommunicationsPreferencesFragment.this}).setVisibility(8);
            CommunicationsPreferencesFragment.access$202(CommunicationsPreferencesFragment.this, Boolean.valueOf(false));
        }
    }

    /* renamed from: com.mcdonalds.app.account.CommunicationsPreferencesFragment$2 */
    class C29562 implements AsyncListener<NotificationPreferences> {
        C29562() {
        }

        public void onResponse(NotificationPreferences response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (CommunicationsPreferencesFragment.this.getNavigationActivity() != null) {
                CommunicationsPreferencesFragment.this.getNavigationActivity().finish();
            }
        }
    }

    private class NotificationEntry {
        public String analytics;
        public String name;
        public boolean state;

        public NotificationEntry(String name, boolean state, String analytics) {
            this.name = name;
            this.state = state;
            this.analytics = analytics;
        }
    }

    public class PreferencesAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private List<NotificationEntry> mPreferencesList = new ArrayList();

        PreferencesAdapter(TreeMap<String, NotificationEntry> data) {
            this.mLayoutInflater = CommunicationsPreferencesFragment.this.getNavigationActivity().getLayoutInflater();
            for (Entry<String, NotificationEntry> entry : data.entrySet()) {
                this.mPreferencesList.add(entry.getValue());
            }
        }

        public int getCount() {
            Ensighten.evaluateEvent(this, "getCount", null);
            return this.mPreferencesList.size();
        }

        public Object getItem(int position) {
            Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
            return null;
        }

        public long getItemId(int position) {
            Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = this.mLayoutInflater.inflate(C2658R.layout.settings_toggle_row, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            DataLayerClickListener.setDataLayerTag(convertView, ViewHolder.class, position);
            holder.bind((NotificationEntry) this.mPreferencesList.get(position));
            Ensighten.getViewReturnValue(convertView, position);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return convertView;
        }
    }

    private class ViewHolder {
        private NotificationEntry entry;
        private final OnCheckedChangeListener onCheckedChangeListener = new C29571();
        private TextView title;
        private Switch toggle;

        /* renamed from: com.mcdonalds.app.account.CommunicationsPreferencesFragment$ViewHolder$1 */
        class C29571 implements OnCheckedChangeListener {
            C29571() {
            }

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{buttonView, new Boolean(isChecked)});
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.CommunicationsPreferencesFragment$ViewHolder", "access$300", new Object[]{ViewHolder.this}) != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.CommunicationsPreferencesFragment$ViewHolder", "access$300", new Object[]{ViewHolder.this}).state = isChecked;
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.CommunicationsPreferencesFragment", "access$400", new Object[]{CommunicationsPreferencesFragment.this}).put(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.CommunicationsPreferencesFragment$ViewHolder", "access$300", new Object[]{ViewHolder.this}).name, Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.CommunicationsPreferencesFragment$ViewHolder", "access$300", new Object[]{ViewHolder.this}));
                    CommunicationsPreferencesFragment.access$002(CommunicationsPreferencesFragment.this, true);
                    Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(CommunicationsPreferencesFragment.this.getAnalyticsTitle()).setAction("On click").setLabel(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.CommunicationsPreferencesFragment$ViewHolder", "access$300", new Object[]{ViewHolder.this}).analytics).addCustom(35, isChecked ? "Opt-In" : "Opt-Out").build());
                }
            }
        }

        public ViewHolder(View view) {
            this.title = (TextView) view.findViewById(2131820647);
            this.toggle = (Switch) view.findViewById(C2358R.C2357id.switch1);
            this.toggle.setOnCheckedChangeListener(this.onCheckedChangeListener);
        }

        public void bind(NotificationEntry entry) {
            Ensighten.evaluateEvent(this, "bind", new Object[]{entry});
            this.entry = entry;
            this.title.setText(entry.name);
            this.toggle.setChecked(entry.state);
        }
    }

    static /* synthetic */ boolean access$002(CommunicationsPreferencesFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.CommunicationsPreferencesFragment", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.mChangesMade = x1;
        return x1;
    }

    static /* synthetic */ Boolean access$202(CommunicationsPreferencesFragment x0, Boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.CommunicationsPreferencesFragment", "access$202", new Object[]{x0, x1});
        x0.mEmailEnabled = x1;
        return x1;
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_communication);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getNavigationActivity().setTitle(getResources().getString(C2658R.string.communications));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_base_preferences_upd, container, false);
        this.mMasterToggleContainer = (LinearLayout) rootView.findViewById(C2358R.C2357id.master_toggle);
        this.mListContainer = (LinearLayout) rootView.findViewById(C2358R.C2357id.list_container);
        this.mListview = (ListView) rootView.findViewById(C2358R.C2357id.listview1);
        ((TextView) rootView.findViewById(C2358R.C2357id.instructions_text)).setText(getResources().getString(C2658R.string.email_category_title));
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.dialog_changing_Preferences);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mAllPreferences = this.mCustomerModule.getCurrentProfile().getNotificationPreferences();
        this.mDisplayNamesMap = new TreeMap();
        this.mDisplayNamesMap.put(getResources().getString(C2658R.string.email_limited_time_offers), new NotificationEntry(getResources().getString(C2658R.string.email_limited_time_offers), this.mAllPreferences.getEmailNotificationPreferencesLimitedTimeOffers(), "Limited time offers"));
        if (shouldShowPunchcardOffers()) {
            this.mDisplayNamesMap.put(getResources().getString(C2658R.string.email_punchcard_offers), new NotificationEntry(getResources().getString(C2658R.string.email_punchcard_offers), this.mAllPreferences.getEmailNotificationPreferencesPunchcardOffers(), "Punchcard offers"));
        }
        this.mEmailEnabled = this.mAllPreferences.getEmailNotificationPreferencesEnabled();
        View emailEnabledView = getNavigationActivity().getLayoutInflater().inflate(C2658R.layout.settings_toggle_row, this.mMasterToggleContainer, false);
        ((TextView) emailEnabledView.findViewById(2131820647)).setText(getResources().getString(C2658R.string.email_communications_enabled));
        Switch emailEnabledSwitch = (Switch) emailEnabledView.findViewById(C2358R.C2357id.switch1);
        emailEnabledSwitch.setOnCheckedChangeListener(new C29551());
        if (this.mEmailEnabled == null || !this.mEmailEnabled.booleanValue()) {
            emailEnabledSwitch.setChecked(false);
        } else {
            emailEnabledSwitch.setChecked(true);
            this.mListContainer.setVisibility(0);
        }
        this.mMasterToggleContainer.addView(emailEnabledView);
        this.mListview.setAdapter(new PreferencesAdapter(this.mDisplayNamesMap));
        UIUtils.stopActivityIndicator();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Ensighten.evaluateEvent(this, "onCreateOptionsMenu", new Object[]{menu, inflater});
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C2358R.C2360menu.save, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Ensighten.evaluateEvent(this, "onOptionsItemSelected", new Object[]{item});
        switch (item.getItemId()) {
            case C2358R.C2357id.action_save /*2131821893*/:
                if (!this.mChangesMade) {
                    getNavigationActivity().finish();
                    break;
                }
                AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Communications");
                this.mAllPreferences.setEmailNotificationPreferencesEnabled(this.mEmailEnabled);
                this.mAllPreferences.setEmailNotificationPreferencesLimitedTimeOffers(Boolean.valueOf(((NotificationEntry) this.mDisplayNamesMap.get(getResources().getString(C2658R.string.email_limited_time_offers))).state));
                if (shouldShowPunchcardOffers()) {
                    this.mAllPreferences.setEmailNotificationPreferencesPunchcardOffers(Boolean.valueOf(((NotificationEntry) this.mDisplayNamesMap.get(getResources().getString(C2658R.string.email_punchcard_offers))).state));
                }
                CustomerProfile customerProfile = this.mCustomerModule.getCurrentProfile();
                UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.dialog_changing_Preferences);
                this.mCustomerModule.setNotificationPreferences(this.mAllPreferences, new C29562());
                break;
        }
        return true;
    }

    private boolean shouldShowPunchcardOffers() {
        Ensighten.evaluateEvent(this, "shouldShowPunchcardOffers", null);
        Boolean shouldShowPunchcardOffers = (Boolean) Configuration.getSharedInstance().getValueForKey("interface.availableCommunicationPreferences.EmailNotificationPreferences_PunchcardOffers");
        return shouldShowPunchcardOffers == null || shouldShowPunchcardOffers.booleanValue();
    }
}
