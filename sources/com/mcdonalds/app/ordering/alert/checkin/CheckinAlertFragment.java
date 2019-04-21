package com.mcdonalds.app.ordering.alert.checkin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p001v7.widget.LinearLayoutManager;
import android.support.p001v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.alert.EditBasketAlertFragment;
import com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter.ProblematicProduct;
import com.mcdonalds.app.ordering.basket.BasketFragment;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import java.util.ArrayList;
import java.util.List;

public abstract class CheckinAlertFragment extends EditBasketAlertFragment {
    protected CheckinAlertFragmentAdapter mAdapter;
    protected RecyclerView mItemList;
    protected List<OrderOffer> mProblematicOffers;
    protected List<String> mProblematicOffersCodes;
    protected List<OrderProduct> mProblematicProducts;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mProblematicProducts = getOutOfStockAndUnavailableProducts();
        this.mProblematicOffersCodes = this.mBundle.getStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_OFFERS_CODES);
        this.mProblematicOffers = OrderUtils.getProblematicOffers(this.mProblematicOffersCodes, this.mOrder);
    }

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        this.mItemList = (RecyclerView) v.findViewById(C2358R.C2357id.item_list);
        this.mItemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<ProblematicProduct> dataSet = new ArrayList();
        dataSet.addAll(this.mProblematicProductsInfo);
        dataSet.addAll(getProblematicOffersNames(this.mProblematicOffersCodes));
        this.mAdapter = new CheckinAlertFragmentAdapter(dataSet);
        this.mItemList.setAdapter(this.mAdapter);
        return v;
    }

    public void onPositiveButtonClicked() {
        int i;
        Ensighten.evaluateEvent(this, "onPositiveButtonClicked", null);
        List<OrderProduct> orderProducts = getOutOfStockAndUnavailableProducts();
        int size = orderProducts.size();
        for (i = 0; i < size; i++) {
            OrderProduct product = (OrderProduct) orderProducts.get(i);
            if (this.mOrder.getProducts().contains(product)) {
                this.mOrder.removeProduct(product);
            }
        }
        List<OrderOffer> orderOffers = OrderUtils.getProblematicOffers(this.mProblematicOffersCodes, this.mOrder);
        size = orderOffers.size();
        for (i = 0; i < size; i++) {
            OrderOffer orderOffer = (OrderOffer) orderOffers.get(i);
            if (this.mOrder.getOffers().contains(orderOffer)) {
                this.mOrder.removeOffer(orderOffer);
            }
        }
        OrderingManager.getInstance().updateCurrentOrder(this.mOrder);
        getActivity().setResult(-1);
        getActivity().finish();
    }
}
