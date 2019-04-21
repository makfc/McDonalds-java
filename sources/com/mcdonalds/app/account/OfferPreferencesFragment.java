package com.mcdonalds.app.account;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.app.msa.MSASettings;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.customer.CustomerProfile;
import com.mcdonalds.sdk.modules.models.OfferCategory;
import com.mcdonalds.sdk.modules.offers.OffersModule;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.BusinessArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.util.ArrayList;
import java.util.List;

public class OfferPreferencesFragment extends URLNavigationFragment {
    public static final String NAME = OfferPreferencesFragment.class.getSimpleName();
    private List<OfferCategory> mAllCategories = new ArrayList();
    private boolean mChangesMade = false;
    protected CustomerModule mCustomerModule;
    private Button mDisableButton;
    private boolean mFirstPass = true;
    private RelativeLayout mInstructionsLayout;
    private boolean mIsDisableButton = false;
    private LinearLayout mListContainer;
    private ListView mListView;
    private LinearLayout mMasterToggleContainer;
    private List<OfferData> mOffersList = new ArrayList();
    protected OffersModule mOffersModule;
    private PreferencesAdapter mPreferencesAdapter;
    private List<Integer> mPreferredOfferCategories;
    private boolean mSubscribedToOffers;
    private boolean trackFromAll = false;

    /* renamed from: com.mcdonalds.app.account.OfferPreferencesFragment$1 */
    class C30011 implements OnClickListener {
        C30011() {
        }

        public void onClick(View v) {
            boolean z = true;
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            OfferPreferencesFragment.access$002(OfferPreferencesFragment.this, true);
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$100", new Object[]{OfferPreferencesFragment.this})) {
                for (OfferData offerData : Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$200", new Object[]{OfferPreferencesFragment.this})) {
                    offerData.setState(Boolean.valueOf(false));
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$300", new Object[]{OfferPreferencesFragment.this}).notifyDataSetChanged();
                }
            } else {
                for (OfferData offerData2 : Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$200", new Object[]{OfferPreferencesFragment.this})) {
                    offerData2.setState(Boolean.valueOf(true));
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$300", new Object[]{OfferPreferencesFragment.this}).notifyDataSetChanged();
                }
            }
            OfferPreferencesFragment offerPreferencesFragment = OfferPreferencesFragment.this;
            String str = BusinessArgs.VALUE_ALL;
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$100", new Object[]{OfferPreferencesFragment.this})) {
                z = false;
            }
            OfferPreferencesFragment.access$400(offerPreferencesFragment, str, z);
            OfferPreferencesFragment.access$500(OfferPreferencesFragment.this);
            AnalyticsUtils.trackOnClickEvent(OfferPreferencesFragment.this.getAnalyticsTitle(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$100", new Object[]{OfferPreferencesFragment.this}) ? "Enable Offers preference" : "Disable Offers preference");
        }
    }

    /* renamed from: com.mcdonalds.app.account.OfferPreferencesFragment$2 */
    class C30022 implements AsyncListener<List<OfferCategory>> {
        C30022() {
        }

        public void onResponse(List<OfferCategory> response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            if (OfferPreferencesFragment.this.getNavigationActivity() != null) {
                if (ListUtils.isEmpty(response)) {
                    UIUtils.stopActivityIndicator();
                } else {
                    OfferPreferencesFragment.access$602(OfferPreferencesFragment.this, response);
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$200", new Object[]{OfferPreferencesFragment.this}).clear();
                    for (OfferCategory offerCategory : Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$600", new Object[]{OfferPreferencesFragment.this})) {
                        Integer categoryNum = offerCategory.getId();
                        OfferData offerData = new OfferData();
                        offerData.setId(categoryNum);
                        offerData.setDescription(offerCategory.getDescription());
                        offerData.setState(Boolean.valueOf(false));
                        for (int i = 0; i < Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$700", new Object[]{OfferPreferencesFragment.this}).size(); i++) {
                            if (categoryNum.equals(Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$700", new Object[]{OfferPreferencesFragment.this}).get(i))) {
                                offerData.setState(Boolean.valueOf(true));
                                break;
                            }
                        }
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$200", new Object[]{OfferPreferencesFragment.this}).add(offerData);
                    }
                    if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$200", new Object[]{OfferPreferencesFragment.this}).size() > 0) {
                        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$300", new Object[]{OfferPreferencesFragment.this}).notifyDataSetChanged();
                        UIUtils.stopActivityIndicator();
                        OfferPreferencesFragment.access$500(OfferPreferencesFragment.this);
                    }
                }
                OfferPreferencesFragment.access$800(OfferPreferencesFragment.this, Boolean.valueOf(OfferPreferencesFragment.this.mCustomerModule.getCurrentProfile().isSubscribedToOffers()));
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.OfferPreferencesFragment$3 */
    class C30033 implements AsyncListener<CustomerProfile> {
        C30033() {
        }

        public void onResponse(CustomerProfile response, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
            UIUtils.stopActivityIndicator();
            if (OfferPreferencesFragment.this.getNavigationActivity() != null) {
                OfferPreferencesFragment.this.getNavigationActivity().setResult(-1);
                OfferPreferencesFragment.this.getNavigationActivity().finish();
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.OfferPreferencesFragment$4 */
    class C30044 implements OnCheckedChangeListener {
        C30044() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Ensighten.evaluateEvent(this, "onCheckedChanged", new Object[]{buttonView, new Boolean(isChecked)});
            if (!isChecked && Configuration.getSharedInstance().getBooleanForKey("interface.isMSAEnabled") && MSASettings.alarmEnabled(OfferPreferencesFragment.this.getActivity())) {
                OfferPreferencesFragment.access$900(OfferPreferencesFragment.this, buttonView);
            } else {
                OfferPreferencesFragment.access$1000(OfferPreferencesFragment.this, isChecked);
            }
        }
    }

    /* renamed from: com.mcdonalds.app.account.OfferPreferencesFragment$5 */
    class C30055 implements DialogInterface.OnClickListener {
        C30055() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
            OfferPreferencesFragment.access$1000(OfferPreferencesFragment.this, false);
            MSASettings.clearAlarm(OfferPreferencesFragment.this.getActivity());
        }
    }

    public class PreferencesAdapter extends BaseAdapter {
        protected List<OfferData> mOffersList;

        private class ViewHolder {
            Switch stateSwitch;
            TextView title;

            private ViewHolder() {
            }

            /* synthetic */ ViewHolder(PreferencesAdapter x0, C30011 x1) {
                this();
            }
        }

        protected PreferencesAdapter(List<OfferData> offersList) {
            this.mOffersList = offersList;
        }

        public int getCount() {
            Ensighten.evaluateEvent(this, "getCount", null);
            return this.mOffersList.size();
        }

        public Object getItem(int position) {
            Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
            return this.mOffersList.get(position);
        }

        public long getItemId(int position) {
            Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
            return (long) position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            LayoutInflater inflater = OfferPreferencesFragment.this.getNavigationActivity().getLayoutInflater();
            if (convertView == null) {
                convertView = inflater.inflate(C2658R.layout.settings_toggle_row, parent, false);
                holder = new ViewHolder(this, null);
                holder.title = (TextView) convertView.findViewById(2131820647);
                holder.stateSwitch = (Switch) convertView.findViewById(C2358R.C2357id.switch1);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            DataLayerClickListener.setDataLayerTag(convertView, ViewHolder.class, position);
            OfferData adapterOfferData = (OfferData) this.mOffersList.get(position);
            holder.title.setText(adapterOfferData.getDescription());
            holder.stateSwitch.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                    OfferPreferencesFragment.access$002(OfferPreferencesFragment.this, false);
                    OfferData offerData = (OfferData) PreferencesAdapter.this.mOffersList.get(position);
                    offerData.setState(Boolean.valueOf(((Switch) v).isChecked()));
                    OfferPreferencesFragment.access$1202(OfferPreferencesFragment.this, true);
                    OfferPreferencesFragment.access$400(OfferPreferencesFragment.this, offerData.getDescription(), offerData.getState().booleanValue());
                    OfferPreferencesFragment.access$500(OfferPreferencesFragment.this);
                }
            });
            holder.stateSwitch.setChecked(adapterOfferData.getState().booleanValue());
            Ensighten.getViewReturnValue(convertView, position);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return convertView;
        }
    }

    static /* synthetic */ boolean access$002(OfferPreferencesFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$002", new Object[]{x0, new Boolean(x1)});
        x0.trackFromAll = x1;
        return x1;
    }

    static /* synthetic */ void access$1000(OfferPreferencesFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$1000", new Object[]{x0, new Boolean(x1)});
        x0.handleSubscriptionChange(x1);
    }

    static /* synthetic */ boolean access$1202(OfferPreferencesFragment x0, boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$1202", new Object[]{x0, new Boolean(x1)});
        x0.mChangesMade = x1;
        return x1;
    }

    static /* synthetic */ void access$400(OfferPreferencesFragment x0, String x1, boolean x2) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$400", new Object[]{x0, x1, new Boolean(x2)});
        x0.trackProductOfferOpt(x1, x2);
    }

    static /* synthetic */ void access$500(OfferPreferencesFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$500", new Object[]{x0});
        x0.updateDisableButtonState();
    }

    static /* synthetic */ List access$602(OfferPreferencesFragment x0, List x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$602", new Object[]{x0, x1});
        x0.mAllCategories = x1;
        return x1;
    }

    static /* synthetic */ void access$800(OfferPreferencesFragment x0, Boolean x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$800", new Object[]{x0, x1});
        x0.populateSubscribeToOffers(x1);
    }

    static /* synthetic */ void access$900(OfferPreferencesFragment x0, CompoundButton x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.account.OfferPreferencesFragment", "access$900", new Object[]{x0, x1});
        x0.showDisableDialog(x1);
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_account_offer_preferences);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getNavigationActivity().setTitle(getResources().getString(C2658R.string.offers));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_base_preferences_upd, container, false);
        this.mMasterToggleContainer = (LinearLayout) rootView.findViewById(C2358R.C2357id.master_toggle);
        this.mListContainer = (LinearLayout) rootView.findViewById(C2358R.C2357id.list_container);
        this.mInstructionsLayout = (RelativeLayout) rootView.findViewById(C2358R.C2357id.instructions_layout);
        this.mListView = (ListView) rootView.findViewById(C2358R.C2357id.listview1);
        LinearLayout disableLayout = (LinearLayout) inflater.inflate(C2658R.layout.disable_all_btn, this.mListView, false);
        this.mDisableButton = (Button) disableLayout.findViewById(C2358R.C2357id.disable_btn);
        this.mListView.addFooterView(disableLayout);
        this.mDisableButton.setOnClickListener(new C30011());
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIUtils.startActivityIndicator(getNavigationActivity(), getResources().getString(C2658R.string.retrieving_offer_preferences));
        if (Configuration.getSharedInstance().getBooleanForKey("interface.offers.hideOfferCategoryPreferenceSelection")) {
            this.mListContainer.setVisibility(8);
        }
        this.mOffersModule = (OffersModule) ModuleManager.getModule(OffersModule.NAME);
        this.mCustomerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        this.mPreferredOfferCategories = this.mCustomerModule.getCurrentProfile().getPreferredOfferCategories();
        if (this.mPreferredOfferCategories.size() > 0) {
            this.mIsDisableButton = true;
        }
        this.mPreferencesAdapter = new PreferencesAdapter(this.mOffersList);
        this.mListView.setAdapter(this.mPreferencesAdapter);
        this.mOffersModule.getOfferCategories(new C30022());
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
                this.mPreferredOfferCategories.clear();
                for (OfferData offerData : this.mOffersList) {
                    if (offerData.getState().booleanValue()) {
                        this.mPreferredOfferCategories.add(offerData.getId());
                    }
                }
                AnalyticsUtils.trackOnClickEvent(getAnalyticsTitle(), "Offer Preference");
                CustomerProfile customerProfile = this.mCustomerModule.getCurrentProfile();
                customerProfile.setPreferredOfferCategories(this.mPreferredOfferCategories);
                customerProfile.setSubscribedToOffers(this.mSubscribedToOffers);
                UIUtils.startActivityIndicator(getNavigationActivity(), (int) C2658R.string.dialog_changing_Preferences);
                this.mCustomerModule.updateCustomerProfile(customerProfile, new C30033());
                break;
        }
        return true;
    }

    private void populateSubscribeToOffers(Boolean subscribedToOffers) {
        Ensighten.evaluateEvent(this, "populateSubscribeToOffers", new Object[]{subscribedToOffers});
        View subscribeToOffersView = getNavigationActivity().getLayoutInflater().inflate(C2658R.layout.settings_toggle_row, null);
        ((TextView) subscribeToOffersView.findViewById(2131820647)).setText(getResources().getString(C2658R.string.subscribe_to_offers));
        Switch offersEnabledSwitch = (Switch) subscribeToOffersView.findViewById(C2358R.C2357id.switch1);
        offersEnabledSwitch.setOnCheckedChangeListener(new C30044());
        this.mSubscribedToOffers = subscribedToOffers.booleanValue();
        offersEnabledSwitch.setChecked(subscribedToOffers.booleanValue());
        this.mFirstPass = false;
        this.mMasterToggleContainer.addView(subscribeToOffersView);
    }

    private void handleSubscriptionChange(boolean enable) {
        int i = 8;
        Ensighten.evaluateEvent(this, "handleSubscriptionChange", new Object[]{new Boolean(enable)});
        this.mChangesMade = true;
        this.mSubscribedToOffers = enable;
        if (!Configuration.getSharedInstance().getBooleanForKey("interface.offers.hideOfferCategoryPreferenceSelection")) {
            int i2;
            LinearLayout linearLayout = this.mListContainer;
            if (enable) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            linearLayout.setVisibility(i2);
            RelativeLayout relativeLayout = this.mInstructionsLayout;
            if (!enable) {
                i = 0;
            }
            relativeLayout.setVisibility(i);
        }
        this.trackFromAll = true;
        if (enable && !this.mFirstPass) {
            for (int i3 = 0; i3 < this.mPreferencesAdapter.getCount(); i3++) {
                ((OfferData) this.mPreferencesAdapter.getItem(i3)).setState(Boolean.valueOf(true));
            }
            this.mPreferencesAdapter.notifyDataSetChanged();
        }
        if (!this.mFirstPass) {
            trackProductOfferOpt(BusinessArgs.VALUE_ALL, enable);
        }
    }

    private void showDisableDialog(final CompoundButton buttonView) {
        Ensighten.evaluateEvent(this, "showDisableDialog", new Object[]{buttonView});
        DialogInterface.OnClickListener positiveClick = new C30055();
        DialogInterface.OnClickListener negativeClick = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
                dialogInterface.dismiss();
                buttonView.setChecked(true);
            }
        };
        Builder b = new Builder(getActivity());
        b.setTitle(C2658R.string.msa_alert_enable_alarm_title);
        b.setMessage(C2658R.string.msa_alert_enable_alarm_opt_out_msg);
        b.setPositiveButton(C2658R.string.unsubscribe, positiveClick);
        b.setNegativeButton(C2658R.string.cancel, negativeClick);
        b.setCancelable(false);
        b.show();
    }

    private void trackProductOfferOpt(String category, boolean optIn) {
        Ensighten.evaluateEvent(this, "trackProductOfferOpt", new Object[]{category, new Boolean(optIn)});
        if (!this.trackFromAll || BusinessArgs.VALUE_ALL.equals(category)) {
            Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory(getAnalyticsTitle()).setAction("On click").setLabel(category).addCustom(34, optIn ? "Opt-In" : "Opt-Out").setBusiness(BusinessArgs.getProductOfferOpt(category, optIn ? BusinessArgs.VALUE_IN : BusinessArgs.VALUE_OUT)).build());
            AnalyticsUtils.trackEvent(new ArgBuilder().setLabel(BusinessArgs.EVENT_PRODUCT_OFFER_OPT).setMapping(BusinessArgs.KEY_PRODUCT_CATEGORY, category).setMapping(BusinessArgs.KEY_OPT_STATUS, optIn ? BusinessArgs.VALUE_IN : BusinessArgs.VALUE_OUT).build());
        }
    }

    private void updateDisableButtonState() {
        Ensighten.evaluateEvent(this, "updateDisableButtonState", null);
        this.mIsDisableButton = false;
        for (OfferData offerItem : this.mOffersList) {
            if (offerItem.getState().booleanValue()) {
                this.mIsDisableButton = true;
                break;
            }
        }
        this.mDisableButton.setText(getResources().getString(this.mIsDisableButton ? C2658R.string.disable_all : C2658R.string.enable_all));
    }
}
