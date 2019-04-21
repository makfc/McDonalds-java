package com.mcdonalds.app.firstload;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.MainActivity;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.ordering.start.FindStoreActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity.PermissionListener;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.widget.GeoSuggestionsEditText;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.SocialNetwork;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import java.util.List;

public class ChooseSearchMethodFragment extends URLNavigationFragment {
    public static final String NAME = ChooseSearchMethodFragment.class.getSimpleName();
    private final String SUPPORT_ZIP_CODE_KEY = "interface.register.supportsZipCode";

    /* renamed from: com.mcdonalds.app.firstload.ChooseSearchMethodFragment$1 */
    class C31161 implements AsyncListener<List<SocialNetwork>> {
        C31161() {
        }

        public void onResponse(List<SocialNetwork> socialNetworks, AsyncToken asyncToken, AsyncException e) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{socialNetworks, asyncToken, e});
        }
    }

    /* renamed from: com.mcdonalds.app.firstload.ChooseSearchMethodFragment$2 */
    class C31182 implements OnClickListener {

        /* renamed from: com.mcdonalds.app.firstload.ChooseSearchMethodFragment$2$1 */
        class C31171 implements PermissionListener {
            C31171() {
            }

            public void onRequestPermissionsResult(int requestCode, String permission, int grantResult) {
                Ensighten.evaluateEvent(this, "onRequestPermissionsResult", new Object[]{new Integer(requestCode), permission, new Integer(grantResult)});
                if (grantResult == 0) {
                    ChooseSearchMethodFragment.access$100(ChooseSearchMethodFragment.this);
                }
            }
        }

        C31182() {
        }

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            AnalyticsUtils.trackOnClickEvent(ChooseSearchMethodFragment.this.getAnalyticsTitle(), "Choose closest");
            if (LocationServicesManager.isLocationEnabled(ChooseSearchMethodFragment.this.getContext())) {
                URLNavigationActivity a = ChooseSearchMethodFragment.this.getNavigationActivity();
                String permission = "android.permission.ACCESS_FINE_LOCATION";
                if (a.isPermissionGranted(permission)) {
                    ChooseSearchMethodFragment.access$100(ChooseSearchMethodFragment.this);
                    return;
                } else {
                    a.requestPermission(permission, 1, C2658R.string.permission_explanation_gps, new C31171());
                    return;
                }
            }
            ChooseSearchMethodFragment.access$000(ChooseSearchMethodFragment.this);
        }
    }

    /* renamed from: com.mcdonalds.app.firstload.ChooseSearchMethodFragment$4 */
    class C31204 implements OnClickListener {
        C31204() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            ChooseSearchMethodFragment.this.startActivity(MainActivity.class, "config_select");
        }
    }

    static /* synthetic */ void access$000(ChooseSearchMethodFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.firstload.ChooseSearchMethodFragment", "access$000", new Object[]{x0});
        x0.showLocationServicesNotEnabledWarning();
    }

    static /* synthetic */ void access$100(ChooseSearchMethodFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.firstload.ChooseSearchMethodFragment", "access$100", new Object[]{x0});
        x0.startFindStoreActivity();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        if (customerModule != null) {
            customerModule.getSocialLoginCatalog(new C31161());
        }
    }

    /* Access modifiers changed, original: protected */
    public String getAnalyticsTitle() {
        Ensighten.evaluateEvent(this, "getAnalyticsTitle", null);
        return getString(C2658R.string.analytics_screen_first_load);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_choose_search_method, container, false);
        TextView addressSearchPrompt = (TextView) rootView.findViewById(C2358R.C2357id.address_search_prompt);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.register.supportsZipCode", true)) {
            addressSearchPrompt.setText(C2658R.string.address_search_prompt);
        } else {
            addressSearchPrompt.setText(C2658R.string.address_search_prompt_no_zip);
        }
        ((Button) rootView.findViewById(C2358R.C2357id.button_choose_closest)).setOnClickListener(new C31182());
        final GeoSuggestionsEditText searchView = (GeoSuggestionsEditText) rootView.findViewById(C2358R.C2357id.geoSearchText);
        if (getResources().getBoolean(C2658R.bool.prefill_address_first_load)) {
            searchView.setText(getResources().getString(C2658R.string.first_load_address_default));
        }
        searchView.getField().setDropDownVerticalOffset(0);
        searchView.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                Ensighten.evaluateEvent(this, "onEditorAction", new Object[]{textView, new Integer(actionId), keyEvent});
                String searchString = textView.getText().toString();
                if (actionId == 3 || keyEvent.getKeyCode() == 66) {
                    if (textView.getText().length() <= 0) {
                        return false;
                    }
                    if (AppUtils.isNetworkConnected(ChooseSearchMethodFragment.this.getNavigationActivity())) {
                        searchView.clearFocus();
                        ((InputMethodManager) ChooseSearchMethodFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(searchView.getWindowToken(), 0);
                        AnalyticsUtils.trackSearch(ChooseSearchMethodFragment.this.getAnalyticsTitle(), textView.getText().toString());
                        Bundle extras = new Bundle();
                        extras.putBoolean("map_only", true);
                        extras.putString("search_by_address", searchString);
                        extras.putBoolean("set_current_store", true);
                        ChooseSearchMethodFragment.this.startActivity(FindStoreActivity.class, extras);
                    } else {
                        UIUtils.showNoNetworkAlert(ChooseSearchMethodFragment.this.getNavigationActivity());
                    }
                }
                return true;
            }
        });
        if (getResources().getBoolean(C2658R.bool.config_change_option)) {
            View changeConfig = rootView.findViewById(C2358R.C2357id.button_change_config);
            changeConfig.setVisibility(8);
            changeConfig.setOnClickListener(new C31204());
        }
        return rootView;
    }

    private void showLocationServicesNotEnabledWarning() {
        Ensighten.evaluateEvent(this, "showLocationServicesNotEnabledWarning", null);
        UIUtils.showGlobalAlertDialog(getNavigationActivity(), getNavigationActivity().getString(C2658R.string.unable_to_get_location), getNavigationActivity().getString(C2658R.string.location_services_disabled_warning), null);
        DataLayerManager.getInstance().recordError("Unable to get location");
    }

    private void startFindStoreActivity() {
        Ensighten.evaluateEvent(this, "startFindStoreActivity", null);
        Bundle extras = new Bundle();
        extras.putBoolean("map_only", true);
        extras.putBoolean("select_closest", true);
        extras.putBoolean("set_current_store", true);
        extras.putBoolean("EXTRA_FIRST_LOAD", true);
        startActivity(FindStoreActivity.class, extras);
    }
}
