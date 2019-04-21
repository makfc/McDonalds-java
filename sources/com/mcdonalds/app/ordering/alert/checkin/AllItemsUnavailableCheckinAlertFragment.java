package com.mcdonalds.app.ordering.alert.checkin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p001v7.widget.LinearLayoutManager;
import android.support.p001v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.alert.EditBasketAlertFragment;
import com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter.ProblematicProduct;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.ordering.basket.BasketFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import java.util.ArrayList;
import java.util.List;

public class AllItemsUnavailableCheckinAlertFragment extends EditBasketAlertFragment {
    protected CheckinAlertFragmentAdapter mAdapter;
    protected RecyclerView mItemList;
    protected List<OrderOffer> mProblematicOffers;
    protected List<String> mProblematicOffersCodes;
    protected List<OrderProduct> mProblematicProducts;
    protected int mProductErrorCode;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNavigationActivity().setTitle(getString(C2658R.string.title_activity_main));
        this.mProblematicProducts = getOutOfStockAndUnavailableProducts();
        this.mProblematicOffersCodes = this.mBundle.getStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_OFFERS_CODES);
        this.mProblematicOffers = OrderUtils.getProblematicOffers(this.mProblematicOffersCodes, this.mOrder);
        this.mProductErrorCode = this.mBundle.getInt(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, -1);
    }

    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        this.mItemList = (RecyclerView) v.findViewById(C2358R.C2357id.item_list);
        this.mItemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<ProblematicProduct> dataSet = new ArrayList();
        dataSet.addAll(this.mProblematicProductsInfo);
        dataSet.addAll(getProblematicOffersNames(this.mProblematicOffersCodes));
        if (this.mProductErrorCode == OrderResponse.PRODUCT_OUT_OF_STOCK_CODE || this.mProductErrorCode == OrderResponse.OFFERS_ERROR) {
            ((TextView) v.findViewById(C2358R.C2357id.text_title)).setText(C2658R.string.all_items_out_of_stock_checkin);
        }
        this.mAdapter = new CheckinAlertFragmentAdapter(dataSet);
        this.mItemList.setAdapter(this.mAdapter);
        return v;
    }

    public int getFragmentResourceId() {
        Ensighten.evaluateEvent(this, "getFragmentResourceId", null);
        return C2658R.layout.fragment_check_in_all_items_unavailable;
    }

    public void onPositiveButtonClicked() {
        Ensighten.evaluateEvent(this, "onPositiveButtonClicked", null);
        startActivityForResult(BasketActivity.class, "basket", this.mBundle, 19);
    }
}
