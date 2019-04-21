package com.mcdonalds.app.ordering.alert;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.MenuType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.utils.DateUtils;
import java.util.Calendar;
import java.util.List;

public class DayPartAlertFragment extends AlertFragment {
    OrderProduct mProduct;

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentResourceId(), null);
        this.mNegativeButton = (Button) view.findViewById(C2358R.C2357id.button_negative);
        this.mNegativeButton.setOnClickListener(this);
        this.mPositiveButton = (Button) view.findViewById(C2358R.C2357id.button_positive);
        this.mPositiveButton.setOnClickListener(this);
        OrderingModule mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        OrderManager orderManager = OrderManager.getInstance();
        Store store = orderManager.getCurrentStore();
        int currentDayPartID = store.getCurrentMenuTypeID(orderManager.getCurrentOrder().isDelivery());
        List<MenuType> menuTypes = mOrderingModule.getMenuTypes();
        String currentDayPart = "";
        this.mProduct = (OrderProduct) DataPasser.getInstance().getData(getArguments().getInt("arg_edit_product"));
        if (store.isStoreClosed()) {
            displayStoreClosed(view, store);
        } else {
            if (menuTypes != null) {
                for (MenuType menuType : menuTypes) {
                    if (menuType.getID() == currentDayPartID) {
                        currentDayPart = menuType.getShortName();
                    }
                }
            } else if (currentDayPartID == 0) {
                currentDayPart = getString(C2658R.string.breakfast);
            } else {
                currentDayPart = getString(C2658R.string.lunch_and_dinner);
            }
            displayWrongDayPart(view, store, currentDayPart);
        }
        return view;
    }

    private void displayStoreClosed(View parent, Store store) {
        Ensighten.evaluateEvent(this, "displayStoreClosed", new Object[]{parent, store});
        ((TextView) parent.findViewById(2131820647)).setText(getString(C2658R.string.store_closed_title));
        TextView description = (TextView) parent.findViewById(C2358R.C2357id.instructions);
        store.getStoreHours();
        if (ListUtils.isEmpty(store.getStoreOperatingHours())) {
            description.setText("");
            return;
        }
        String[] operatingHours = (String[]) store.getStoreOperatingHours().get(Calendar.getInstance().get(7) - 1);
        String fromTime = "";
        String toTime = "";
        if (!TextUtils.isEmpty(operatingHours[0])) {
            fromTime = DateUtils.formatTimeToSystemFormat(operatingHours[0], getContext());
        }
        if (!TextUtils.isEmpty(operatingHours[1])) {
            toTime = DateUtils.formatTimeToSystemFormat(operatingHours[1], getContext());
        }
        description.setText(getString(C2658R.string.store_closed_message, fromTime, toTime));
    }

    private void displayWrongDayPart(View parent, Store store, String currentDayPart) {
        Ensighten.evaluateEvent(this, "displayWrongDayPart", new Object[]{parent, store, currentDayPart});
        store.getStoreHours();
        ((TextView) parent.findViewById(2131820647)).setText(getString(C2658R.string.currently_serving, currentDayPart));
        TextView description = (TextView) parent.findViewById(C2358R.C2357id.instructions);
        List<MenuType> menuTypes = this.mProduct.getProduct().getMenuTypes();
        if (menuTypes == null || menuTypes.isEmpty()) {
            description.setText(getActivity().getString(C2658R.string.currently_serving_invalid_menutype));
            return;
        }
        description.setText(String.format(getActivity().getString(C2658R.string.currently_serving_details), new Object[]{currentDayPart, AppUtils.getDayPartInitialTime(((MenuType) menuTypes.get(0)).getID(), store)}));
    }

    public int getFragmentResourceId() {
        Ensighten.evaluateEvent(this, "getFragmentResourceId", null);
        return C2658R.layout.fragment_day_part_warning;
    }

    public void onNegativeButtonClicked() {
        Ensighten.evaluateEvent(this, "onNegativeButtonClicked", null);
        getActivity().finish();
    }

    public void onPositiveButtonClicked() {
        Ensighten.evaluateEvent(this, "onPositiveButtonClicked", null);
        Order currentOrder = OrderingManager.getInstance().getCurrentOrder();
        if (currentOrder == null) {
            return;
        }
        if (currentOrder.addProduct(this.mProduct)) {
            getActivity().setResult(-1);
            getActivity().finish();
            return;
        }
        UIUtils.showInvalidDayPartsError(getNavigationActivity());
    }
}
