package com.mcdonalds.app.ordering.alert;

import android.os.Bundle;
import com.ensighten.Ensighten;
import com.mcdonalds.app.McDonaldsApplication;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.ordering.alert.checkin.CheckinAlertFragmentAdapter.ProblematicProduct;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.OrderProductUtils;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.services.data.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class EditBasketAlertFragment extends AlertFragment {
    protected Bundle mBundle;
    protected Order mOrder;
    protected List<ProblematicProduct> mProblematicProductsInfo = new ArrayList();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBundle = getActivity().getIntent().getExtras();
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
    }

    public void onResume() {
        super.onResume();
        if (!OrderingManager.getInstance().getCurrentOrder().haveProducts()) {
            getActivity().setResult(39);
            getActivity().finish();
        }
    }

    public void onNegativeButtonClicked() {
        Ensighten.evaluateEvent(this, "onNegativeButtonClicked", null);
        if (this.mBundle == null) {
            this.mBundle = new Bundle();
        }
        startActivityForResult(BasketActivity.class, "basket", this.mBundle, 19);
    }

    /* Access modifiers changed, original: protected */
    public List<OrderProduct> getOutOfStockAndUnavailableProducts() {
        Ensighten.evaluateEvent(this, "getOutOfStockAndUnavailableProducts", null);
        List<OrderProduct> orderProducts = new ArrayList();
        List<OrderProduct> problematicProducts = new ArrayList();
        orderProducts.addAll(this.mOrder.getProducts());
        if (this.mOrder != null) {
            for (OrderProduct orderProduct : orderProducts) {
                List<ProblematicProduct> problematicSubProductsInfo = new ArrayList();
                if (ListUtils.isNotEmpty(orderProduct.getRealChoices())) {
                    for (Choice choice : orderProduct.getRealChoices()) {
                        OrderProduct subChoice = ProductUtils.getActualChoice(choice);
                        if (subChoice != null) {
                            if (subChoice.isUnavailable() || isOutOfStock(subChoice)) {
                                problematicProducts.add(subChoice);
                                problematicSubProductsInfo.add(new ProblematicProduct("(" + subChoice.getProduct().getLongName() + ")", true));
                            } else {
                                for (OrderProduct component : OrderProductUtils.getAllChoices(subChoice)) {
                                    if (!component.isUnavailable()) {
                                        if (isOutOfStock(component)) {
                                        }
                                    }
                                    problematicProducts.add(subChoice);
                                    problematicSubProductsInfo.add(new ProblematicProduct("(" + subChoice.getProduct().getLongName() + ")", true));
                                }
                            }
                        }
                    }
                }
                if (!problematicSubProductsInfo.isEmpty() || isOutOfStock(orderProduct) || orderProduct.isUnavailable()) {
                    problematicProducts.add(orderProduct);
                    this.mProblematicProductsInfo.add(new ProblematicProduct(orderProduct.getProduct().getLongName(), problematicSubProductsInfo.isEmpty(), true));
                }
                this.mProblematicProductsInfo.addAll(problematicSubProductsInfo);
            }
        }
        return problematicProducts;
    }

    /* Access modifiers changed, original: protected */
    public List<ProblematicProduct> getProblematicOffersNames(List<String> problematicOfferCodes) {
        Ensighten.evaluateEvent(this, "getProblematicOffersNames", new Object[]{problematicOfferCodes});
        ArrayList<ProblematicProduct> names = new ArrayList();
        ArrayList<ProblematicProduct> productNames = new ArrayList();
        ArrayList<String> choiceNames = new ArrayList();
        if (problematicOfferCodes != null) {
            int size = problematicOfferCodes.size();
            for (int i = 0; i < size; i++) {
                String offerCode = (String) problematicOfferCodes.get(i);
                for (OrderOffer orderOffer : this.mOrder.getOffers()) {
                    for (int j = 0; j < orderOffer.getOrderOfferProducts().size(); j++) {
                        OrderProduct orderProduct = ((OrderOfferProduct) orderOffer.getOrderOfferProducts().get(j)).getSelectedProductOption();
                        if (orderProduct.getProductCode().equals(offerCode)) {
                            productNames.add(new ProblematicProduct(orderProduct.getDisplayName(), true, true));
                        } else {
                            choiceNames.add(offerCode);
                        }
                    }
                    if (orderOffer.getOffer().getOfferId().toString().equals(offerCode)) {
                        names.add(new ProblematicProduct(orderOffer.getOffer().getName(), productNames.isEmpty(), true));
                    }
                    names.addAll(productNames);
                }
            }
            if (ListUtils.isNotEmpty(choiceNames)) {
                Iterator it = choiceNames.iterator();
                while (it.hasNext()) {
                    Product outOfStockChoiceProduct = ProductRepository.getByProductCode(McDonaldsApplication.getInstance().getBaseContext(), Integer.parseInt((String) it.next()), false);
                    if (outOfStockChoiceProduct != null) {
                        names.add(new ProblematicProduct("(" + outOfStockChoiceProduct.getLongName() + ")", true));
                    }
                }
            }
        }
        return names;
    }

    private boolean isOutOfStock(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "isOutOfStock", new Object[]{orderProduct});
        return (this.mOrder.getTotalizeResult() == null || this.mOrder.getTotalizeResult().getOrderView() == null || this.mOrder.getTotalizeResult().getOrderView().getProductValidationErrorCode(orderProduct) != OrderResponse.PRODUCT_OUT_OF_STOCK_CODE) ? false : true;
    }
}
