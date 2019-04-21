package com.mcdonalds.app.ordering.instorepickup;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.LatestOrderActivity;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.checkin.OrderCheckinFragment;
import com.mcdonalds.app.ordering.start.PickupLocationHolder;
import com.mcdonalds.app.storelocator.MapManager;
import com.mcdonalds.app.storelocator.MapManager.Callback;
import com.mcdonalds.app.storelocator.StoreDetailsActivity;
import com.mcdonalds.app.storelocator.StoreDetailsFragment;
import com.mcdonalds.app.storelocator.StoreLocatorSection;
import com.mcdonalds.app.storelocator.maps.McdMap;
import com.mcdonalds.app.util.AnalyticsUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.MapUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.OrderPayment;
import com.mcdonalds.sdk.modules.models.PointOfDistribution;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.LocalDataManager;
import com.mcdonalds.sdk.services.location.LocationServicesManager;
import java.util.Date;

public class DriveThruConfirmationFragment extends OrderCheckinFragment implements OnClickListener, Callback {
    private Store currentStore;
    private CustomerModule customerModule;
    private MapManager mapManager;
    private PickupLocationHolder pickupLocationHolder;

    /* renamed from: com.mcdonalds.app.ordering.instorepickup.DriveThruConfirmationFragment$1 */
    class C35091 implements DialogInterface.OnClickListener {
        C35091() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            DriveThruConfirmationFragment.access$000(DriveThruConfirmationFragment.this);
        }
    }

    static /* synthetic */ void access$000(DriveThruConfirmationFragment x0) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.instorepickup.DriveThruConfirmationFragment", "access$000", new Object[]{x0});
        x0.onConfirmClick();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C2658R.layout.fragment_drive_thru_confirmation, container, false);
        this.mShouldLaunchQRCodeScanner = false;
        this.mMainViewVisible = true;
        this.pickupLocationHolder = new PickupLocationHolder(view.findViewById(C2358R.C2357id.pickup_location));
        this.pickupLocationHolder.getExtraView().setVisibility(0);
        this.pickupLocationHolder.getDisclosureIcon().setVisibility(8);
        this.pickupLocationHolder.getInfoIcon().setOnClickListener(this);
        this.mapManager = new MapManager(getActivity(), this);
        getChildFragmentManager().beginTransaction().add(C2358R.C2357id.map_container, this.mapManager.getFragment(), "MAP").commit();
        view.findViewById(C2358R.C2357id.confirm_button).setOnClickListener(this);
        this.currentStore = this.customerModule.getCurrentStore();
        updateStore();
        return view;
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        switch (v.getId()) {
            case C2358R.C2357id.confirm_button /*2131821214*/:
                if (!ConfigurationUtils.isDuplicateOrderCheckinFlow() || !LocalDataManager.getSharedInstance().hasObjectInCache(LocalDataManager.KEY_CHECKIN_TIMER)) {
                    onConfirmClick();
                    return;
                } else if (ConfigurationUtils.isDuplicateOrderCheckinAllowOrdering()) {
                    UIUtils.showCheckinFlowAlert(getContext(), true, null, new C35091());
                    return;
                } else {
                    UIUtils.showCheckinFlowAlert(getContext(), false, null, null);
                    return;
                }
            case C2358R.C2357id.pickup_store_info_button /*2131821819*/:
                Bundle bundle = new Bundle();
                bundle.putParcelable("extra_store_detail", this.currentStore);
                bundle.putInt("extra_store_section", StoreLocatorSection.Current.ordinal());
                startActivity(StoreDetailsActivity.class, StoreDetailsFragment.NAME, bundle);
                return;
            default:
                return;
        }
    }

    private void onConfirmClick() {
        Ensighten.evaluateEvent(this, "onConfirmClick", null);
        UIUtils.startActivityIndicator(getNavigationActivity(), getString(C2658R.string.dialog_preparing_payment));
        OrderingManager.getInstance().getCurrentOrder().setPriceType(PriceType.TakeOut);
        OrderPayment payment = OrderingManager.getInstance().getCurrentOrder().getPayment();
        if (payment != null) {
            payment.setPOD(PointOfDistribution.DriveThru);
        }
        LocalDataManager.getSharedInstance().setLatestOrderIsDriveThru(true);
        preparePaymentAndCheckin();
    }

    /* Access modifiers changed, original: protected */
    public String getTitle() {
        Ensighten.evaluateEvent(this, "getTitle", null);
        return getString(C2658R.string.title_activity_drive_thru_confirm);
    }

    public void onMapAvailable() {
        Ensighten.evaluateEvent(this, "onMapAvailable", null);
        McdMap map = this.mapManager.getMap();
        this.mapManager.setCallback(null);
        map.setMyLocationEnabled(LocationServicesManager.isLocationEnabled(getContext()));
        MapUtils.updateMap(getActivity(), map, this.currentStore);
    }

    public void onMapError(Dialog dialog) {
        Ensighten.evaluateEvent(this, "onMapError", new Object[]{dialog});
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
            try {
                this.pickupLocationHolder.getDistance().setText(UIUtils.distanceFromStore(getContext(), this.currentStore));
            } catch (IllegalStateException e) {
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void continueToOrderSummary() {
        Ensighten.evaluateEvent(this, "continueToOrderSummary", null);
        OrderingManager manager = OrderingManager.getInstance();
        AnalyticsUtils.trackEvent(new ArgBuilder().setLabel("payment_confirm").setMapping("order_id", this.mOrder.getOrderNumber()).setMapping("fop_type", this.mOrder.getPayment().getOrderPaymentId()).setMapping("curr_cd", Configuration.getSharedInstance().getCurrencyCode()).setMapping("order_amt", Double.valueOf(this.mOrder.getTotalValue())).setMapping("local_timestamp", UIUtils.formatTime(getContext(), new Date())).build());
        String latestOrderNumber = manager.getCurrentOrder().getCheckinResult().getDisplayOrderNumber();
        manager.deleteCurrentOrder();
        Bundle attributes = new Bundle();
        attributes.putString("EXTRA_ORDER_NUMBER", latestOrderNumber);
        attributes.putBoolean("EXTRA_IS_DRIVE_THRU", true);
        startActivity(LatestOrderActivity.class, "latest_order", attributes);
    }
}
