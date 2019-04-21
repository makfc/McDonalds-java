package com.mcdonalds.app.ordering.alert.checkout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p001v7.widget.LinearLayoutManager;
import android.support.p001v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter;
import com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter.ProblematicProduct;
import com.mcdonalds.app.ordering.basket.BasketFragment;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.OfferProductOption;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.util.ArrayList;
import java.util.List;

public class ItemsOutOfStockCheckoutAlertFragment extends CheckoutAlertFragment {
    protected CheckinAlertFragmentAdapter mAdapter;
    protected RecyclerView mItemList;
    protected List<OrderOffer> mProblematicOffers;
    protected List<String> mProblematicOffersCodes;
    protected List<OrderProduct> mProblematicProducts;
    protected List<String> mProblematicProductsCodes;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mProblematicProductsCodes = this.mBundle.getStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_PRODUCTS_CODES);
        this.mProblematicProducts = getOutOfStockAndUnavailableProducts();
        this.mProblematicOffersCodes = this.mBundle.getStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_OFFERS_CODES);
        this.mProblematicOffers = getProblematicOffers();
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

    public List<OrderOffer> getProblematicOffers() {
        int i;
        Ensighten.evaluateEvent(this, "getProblematicOffers", null);
        ArrayList<OrderOffer> orderOffers = new ArrayList();
        if (this.mProblematicOffersCodes != null) {
            int size = this.mProblematicOffersCodes.size();
            for (i = 0; i < size; i++) {
                String offerCode = (String) this.mProblematicOffersCodes.get(i);
                for (OrderOffer orderOffer : this.mOrder.getOffers()) {
                    if (orderOffer.getOffer().getOfferId().toString().equals(offerCode)) {
                        orderOffers.add(orderOffer);
                    }
                }
            }
        }
        if (this.mProblematicProductsCodes != null) {
            for (i = 0; i < this.mProblematicProductsCodes.size(); i++) {
                String productCode = (String) this.mProblematicProductsCodes.get(i);
                if (!ListUtils.isEmpty(this.mOrder.getOffers())) {
                    for (OrderOffer orderOffer2 : this.mOrder.getOffers()) {
                        if (!ListUtils.isEmpty(orderOffer2.getOrderOfferProducts())) {
                            for (OrderOfferProduct orderOfferProduct : orderOffer2.getOrderOfferProducts()) {
                                if (!ListUtils.isEmpty(orderOfferProduct.getOfferProduct().getProducts())) {
                                    for (OfferProductOption option : orderOfferProduct.getOfferProduct().getProducts()) {
                                        if (option.getProductCode().equals(productCode) && !orderOffers.contains(orderOffer2)) {
                                            orderOffers.add(orderOffer2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return orderOffers;
    }

    public int getFragmentResourceId() {
        Ensighten.evaluateEvent(this, "getFragmentResourceId", null);
        return C2658R.layout.fragment_check_out_items_out_of_stock;
    }

    public void onPositiveButtonClicked() {
        Ensighten.evaluateEvent(this, "onPositiveButtonClicked", null);
        if (Configuration.getSharedInstance().getBooleanForKey("interface.ordering.removedOutOfStockProductsInCheckout")) {
            OrderingManager orderingManager = OrderingManager.getInstance();
            Order mOrder = orderingManager.getCurrentOrder();
            List<String> problematicProductCodes = orderingManager.getProblematicProductsCodes();
            for (OrderProduct orderProduct : new ArrayList(mOrder.getProducts())) {
                if (!ListUtils.isEmpty(problematicProductCodes) && problematicProductCodes.contains(orderProduct.getProductCode())) {
                    mOrder.removeProduct(orderProduct);
                }
            }
            for (OrderOffer orderOffer : this.mProblematicOffers) {
                mOrder.removeOffer(orderOffer);
            }
            if (mOrder.isEmpty()) {
                getNavigationActivity().setResult(39);
            } else {
                getNavigationActivity().setResult(-1);
            }
        }
        getActivity().finish();
    }
}
