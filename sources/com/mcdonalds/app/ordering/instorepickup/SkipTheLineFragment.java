package com.mcdonalds.app.ordering.instorepickup;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.start.PickupLocationHolder;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.app.storelocator.MapManager;
import com.mcdonalds.app.storelocator.MapManager.Callback;
import com.mcdonalds.app.storelocator.StoreDetailsActivity;
import com.mcdonalds.app.storelocator.StoreDetailsFragment;
import com.mcdonalds.app.storelocator.StoreLocatorSection;
import com.mcdonalds.app.storelocator.maps.McdMap;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.MapUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.OrderPayment;
import com.mcdonalds.sdk.modules.models.PointOfDistribution;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import com.mcdonalds.sdk.services.location.providers.LocationProvider.LocationUpdateListener;

public class SkipTheLineFragment extends URLNavigationFragment implements OnClickListener, Callback {
    private Location currentLocation;
    private Store currentStore;
    private CustomerModule customerModule;
    private LocationUpdateListener locationUpdateListener = new C35112();
    private McdMap map;
    private MapManager mapManager;
    private PickupLocationHolder pickupLocationHolder;
    private View skipTheLineButton;

    /* renamed from: com.mcdonalds.app.ordering.instorepickup.SkipTheLineFragment$1 */
    class C35101 implements DialogInterface.OnClickListener {
        C35101() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            SkipTheLineFragment.this.getActivity().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.instorepickup.SkipTheLineFragment$2 */
    class C35112 implements LocationUpdateListener {
        C35112() {
        }

        public void onLocationChanged(Location location) {
            Ensighten.evaluateEvent(this, "onLocationChanged", new Object[]{location});
            SkipTheLineFragment.access$002(SkipTheLineFragment.this, location);
            SkipTheLineFragment.access$100(SkipTheLineFragment.this);
            MapUtils.updateMap(SkipTheLineFragment.this.getActivity(), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.instorepickup.SkipTheLineFragment", "access$200", new Object[]{SkipTheLineFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.instorepickup.SkipTheLineFragment", "access$300", new Object[]{SkipTheLineFragment.this}), Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.instorepickup.SkipTheLineFragment", "access$000", new Object[]{SkipTheLineFragment.this}));
            DataLayerManager.getInstance().setLocation(location);
        }
    }

    static /* synthetic */ Location access$002(SkipTheLineFragment x0, Location x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.instorepickup.SkipTheLineFragment", "access$002", new Object[]{x0, x1});
        x0.currentLocation = x1;
        return x1;
    }

    static /* synthetic */ void access$100(SkipTheLineFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.instorepickup.SkipTheLineFragment", "access$100", new Object[]{x0});
        x0.updateStore();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_skip_the_line, container, false);
        this.pickupLocationHolder = new PickupLocationHolder(view.findViewById(C2358R.C2357id.pickup_location));
        this.pickupLocationHolder.getExtraView().setVisibility(0);
        this.pickupLocationHolder.getDisclosureIcon().setVisibility(8);
        this.pickupLocationHolder.getInfoIcon().setOnClickListener(this);
        this.mapManager = new MapManager(getActivity(), this);
        getChildFragmentManager().beginTransaction().add(C2358R.C2357id.map_container, this.mapManager.getFragment(), "MAP").commit();
        this.skipTheLineButton = view.findViewById(C2358R.C2357id.skip_the_line_button);
        this.skipTheLineButton.setOnClickListener(this);
        this.currentStore = this.customerModule.getCurrentStore();
        this.currentLocation = AppUtils.getUserLocation();
        updateStore();
        return view;
    }

    public void onResume() {
        super.onResume();
        LocationServicesManager.getInstance().requestUpdates(this.locationUpdateListener, 1);
    }

    public void onPause() {
        super.onPause();
        LocationServicesManager.getInstance().disableUpdates();
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (v == this.pickupLocationHolder.getInfoIcon()) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("extra_store_detail", this.currentStore);
            bundle.putInt("extra_store_section", StoreLocatorSection.Current.ordinal());
            startActivity(StoreDetailsActivity.class, StoreDetailsFragment.NAME, bundle);
        } else if (v == this.skipTheLineButton) {
            skipTheLine();
        }
    }

    private void skipTheLine() {
        Ensighten.evaluateEvent(this, "skipTheLine", null);
        if (!LocationServicesManager.isLocationEnabled(getActivity())) {
            displayLocationServicesError();
        } else if (isUserCloseEnough()) {
            PointOfDistribution POD = PointOfDistribution.values()[PointOfDistribution.FrontCounter.integerValue().intValue()];
            OrderPayment payment = OrderingManager.getInstance().getCurrentOrder().getPayment();
            if (payment != null) {
                payment.setPOD(POD);
            }
            startActivity(EatInTakeOutActivity.class, "eat_in_take_out");
        } else {
            MCDAlertDialogBuilder.withContext(getNavigationActivity()).setTitle((int) C2658R.string.skip_the_line_alert_user_not_close_enough_title).setMessage((int) C2658R.string.skip_the_line_alert_user_not_close_enough_instruction).setPositiveButton((int) C2658R.string.skip_the_line_alert_user_not_close_enough_ok, null).create().show();
        }
    }

    public void onMapAvailable() {
        Ensighten.evaluateEvent(this, "onMapAvailable", null);
        this.map = this.mapManager.getMap();
        this.mapManager.setCallback(null);
        this.map.setMyLocationEnabled(LocationServicesManager.isLocationEnabled(getContext()));
        MapUtils.updateMap(getActivity(), this.map, this.currentStore, this.currentLocation);
    }

    public void onMapError(Dialog dialog) {
        Ensighten.evaluateEvent(this, "onMapError", new Object[]{dialog});
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_activity_order_checkin);
    }

    private void updateStore() {
        Ensighten.evaluateEvent(this, "updateStore", null);
        if (this.currentStore != null) {
            String name;
            if (this.currentStore.getStoreFavoriteName() != null) {
                name = this.currentStore.getStoreFavoriteName();
            } else {
                name = this.currentStore.getAddress1();
            }
            this.pickupLocationHolder.getStoreName().setText(name);
            this.pickupLocationHolder.getDistance().setText(UIUtils.distanceFromStore(getContext(), this.currentStore));
        }
    }

    private boolean isUserCloseEnough() {
        Ensighten.evaluateEvent(this, "isUserCloseEnough", null);
        if (this.currentStore == null || this.currentLocation == null || UIUtils.metersFromLocation(this.currentStore, this.currentLocation).floatValue() >= ((float) Configuration.getSharedInstance().getDoubleForKey("checkinDistance"))) {
            return false;
        }
        return true;
    }

    private void displayLocationServicesError() {
        Ensighten.evaluateEvent(this, "displayLocationServicesError", null);
        MCDAlertDialogBuilder.withContext(getNavigationActivity()).setMessage((int) C2658R.string.skip_the_line_enable_location_services_dialog).setNegativeButton((int) C2658R.string.skip_the_line_cash_not_supported_ok_dialog, null).setPositiveButton((int) C2658R.string.skip_the_line_enable_location_services_settings_dialog, new C35101()).create().show();
    }
}
