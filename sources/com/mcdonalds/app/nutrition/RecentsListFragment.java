package com.mcdonalds.app.nutrition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.menu.ReceiptListAdapter;
import com.mcdonalds.app.p043ui.URLNavigationFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.services.data.DataPasser;
import java.util.List;

public class RecentsListFragment extends URLNavigationFragment {
    public static final String NAME = RecentsListFragment.class.getSimpleName();
    OrderingModule mOrderingModule;
    List<Order> mOrders;
    ListView mReceiptListView;
    ReceiptListAdapter receiptListAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int recentOrderListKey = getArguments().getInt("ARG_RECENT_ORDERS_LIST_KEY", -1);
        if (recentOrderListKey != -1) {
            this.mOrders = (List) DataPasser.getInstance().getData(recentOrderListKey);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(C2658R.layout.fragment_recents_list, container, false);
        this.mReceiptListView = (ListView) rootView.findViewById(C2358R.C2357id.receipt_list);
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getNavigationActivity() != null) {
            this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
            this.receiptListAdapter = new ReceiptListAdapter(this.mOrders, this.mOrderingModule, getNavigationActivity());
            this.mReceiptListView.setAdapter(this.receiptListAdapter);
        }
        ((RecentsListActivity) getActivity()).bringBasketToFront();
    }
}
