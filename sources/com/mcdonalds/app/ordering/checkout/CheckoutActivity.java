package com.mcdonalds.app.ordering.checkout;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.account.ModifyCardsActivity;
import com.mcdonalds.app.customer.SignInActivity;
import com.mcdonalds.app.databinding.ActivityCheckoutBinding;
import com.mcdonalds.app.offers.OfferActivity;
import com.mcdonalds.app.ordering.OrderUtils;
import com.mcdonalds.app.ordering.OrderingManager;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.ordering.alert.AlertActivity;
import com.mcdonalds.app.ordering.alert.AlertFragment;
import com.mcdonalds.app.ordering.basket.BasketActivity;
import com.mcdonalds.app.ordering.basket.BasketFragment;
import com.mcdonalds.app.ordering.basket.BasketItemActionListener;
import com.mcdonalds.app.ordering.basket.BasketListAdapter;
import com.mcdonalds.app.ordering.basket.BasketListItem;
import com.mcdonalds.app.ordering.instorepickup.ChoosePickUpActivity;
import com.mcdonalds.app.ordering.payment.PaymentActivity;
import com.mcdonalds.app.ordering.pickupmethod.PickupMethodActivity;
import com.mcdonalds.app.ordering.pickupmethod.PickupMethodFragment;
import com.mcdonalds.app.ordering.preparepayment.PaymentSelectionActivity;
import com.mcdonalds.app.ordering.productdetail.ProductDetailsActivity;
import com.mcdonalds.app.ordering.utils.PODUtils;
import com.mcdonalds.app.p043ui.URLActionBarActivity;
import com.mcdonalds.app.p043ui.URLNavigationActivity;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.OrderOfferUtils;
import com.mcdonalds.app.util.OrderProductUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.app.util.UIUtils.MCDAlertDialogBuilder;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.Offer;
import com.mcdonalds.sdk.modules.models.Offer.OfferType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.Order.PriceType;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.modules.models.OrderPayment;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.PaymentCard;
import com.mcdonalds.sdk.modules.models.PaymentMethod;
import com.mcdonalds.sdk.modules.models.PaymentMethod.PaymentMode;
import com.mcdonalds.sdk.modules.models.Pod;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.models.Product.ProductType;
import com.mcdonalds.sdk.modules.models.ProductDimension;
import com.mcdonalds.sdk.modules.models.StoreProduct;
import com.mcdonalds.sdk.modules.models.StoreProductCategory;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.analytics.AnalyticType;
import com.mcdonalds.sdk.services.analytics.Analytics;
import com.mcdonalds.sdk.services.analytics.AnalyticsArgs.ArgBuilder;
import com.mcdonalds.sdk.services.analytics.JiceArgs;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.data.DataPasser;
import com.mcdonalds.sdk.services.data.repository.StoreProductCategoryRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class CheckoutActivity extends URLActionBarActivity implements BasketItemActionListener, CheckoutView {
    private TextView choose_payment_label;
    private ImageView iv_ali_img;
    private ActivityCheckoutBinding mBinding;
    private ListView mListView;
    private BasketListAdapter mListViewAdapter;
    private List<String> mNonProductOfferNames = new ArrayList();
    private Order mOrder;
    private Order mOrderBeforeStoreChange;
    private OrderingModule mOrderingModule;
    private CheckoutPresenter mPresenter;
    private List<String> mUnavailableProductCodes = new ArrayList();

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutActivity$1 */
    class C34551 implements OnClickListener {
        C34551() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutActivity", "access$000", new Object[]{CheckoutActivity.this}).chosePaymentClicked();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutActivity$2 */
    class C34562 implements OnClickListener {
        C34562() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutActivity", "access$000", new Object[]{CheckoutActivity.this}).continueClicked();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutActivity$3 */
    class C34573 implements OnClickListener {
        C34573() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutActivity", "access$000", new Object[]{CheckoutActivity.this}).firstTimeScanDismissed();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutActivity$4 */
    class C34584 implements OnClickListener {
        C34584() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutActivity", "access$000", new Object[]{CheckoutActivity.this}).firstTimeScanDismissed();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutActivity$5 */
    class C34595 implements OnClickListener {
        C34595() {
        }

        public void onClick(View v) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutActivity", "access$000", new Object[]{CheckoutActivity.this}).firstTimeScanContinue();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutActivity$6 */
    class C34606 implements DialogInterface.OnClickListener {
        C34606() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialogInterface, new Integer(i)});
            dialogInterface.dismiss();
            CheckoutActivity.this.finish();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutActivity$7 */
    class C34617 implements DialogInterface.OnClickListener {
        C34617() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.checkout.CheckoutActivity", "access$000", new Object[]{CheckoutActivity.this}).continueWithLargeOrder();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.checkout.CheckoutActivity$8 */
    class C34628 implements DialogInterface.OnClickListener {
        C34628() {
        }

        public void onClick(DialogInterface dialog, int which) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{dialog, new Integer(which)});
            CheckoutActivity.this.finish();
            CheckoutActivity.this.startActivity(BasketActivity.class);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        boolean comingFromBagCharge;
        super.onCreate(savedInstanceState);
        this.mBinding = (ActivityCheckoutBinding) DataBindingUtil.inflate(LayoutInflater.from(this), C2658R.layout.activity_checkout, null, false);
        setContentView(this.mBinding.getRoot());
        setTitle(C2658R.string.title_activity_checkout);
        this.mPresenter = new CheckoutPresenter(this, this);
        Bundle extras = getIntent().getExtras();
        if (extras == null || !extras.getBoolean("FROM_BAG_CHARGE")) {
            comingFromBagCharge = false;
        } else {
            comingFromBagCharge = true;
        }
        this.mPresenter.setComingFromBagCharges(comingFromBagCharge);
        this.mBinding.setPresenter(this.mPresenter);
        this.mPresenter.initialize();
        this.mBinding.paymentContainer.setOnClickListener(new C34551());
        this.mBinding.continueButton.setOnClickListener(new C34562());
        this.mBinding.qrScanFtuView.getRoot().setOnClickListener(new C34573());
        this.mBinding.qrScanFtuView.scanCancelButton.setOnClickListener(new C34584());
        this.mBinding.qrScanFtuView.scanContinueButton.setOnClickListener(new C34595());
        OrderManager orderManager = OrderManager.getInstance();
        orderManager.cleanBagsFromOrder();
        this.mOrder = orderManager.getCurrentOrder();
        this.choose_payment_label = (TextView) findViewById(C2358R.C2357id.choose_payment_label);
        this.iv_ali_img = (ImageView) findViewById(C2358R.C2357id.iv_ali_img);
        this.mListView = (ListView) findViewById(C2358R.C2357id.basket_list);
        this.mListView.setDivider(null);
        this.mListView.setDividerHeight(0);
        this.mListViewAdapter = new BasketListAdapter(this, this, this.mOrder);
        this.mListView.setAdapter(this.mListViewAdapter);
        if (!this.mOrder.isEmpty()) {
            this.mUnavailableProductCodes = new ArrayList();
            refreshStoreInfoAndDeliveryFee();
        }
        this.mListViewAdapter.clear();
        this.mListViewAdapter.addAll(createBasketItems());
        isAli(this.mOrder);
        if (this.mOrder.getPaymentMode() == PaymentMode.ThirdPart) {
            this.iv_ali_img.setVisibility(0);
        } else {
            this.iv_ali_img.setVisibility(8);
        }
    }

    private void isAli(Order order) {
        Ensighten.evaluateEvent(this, "isAli", new Object[]{order});
        String paymentMethodDisplayName = order.getPaymentMethodDisplayName();
        if (paymentMethodDisplayName == null) {
            return;
        }
        if (paymentMethodDisplayName.equals(getString(C2658R.string.alipay)) || paymentMethodDisplayName.equals("支付寶") || paymentMethodDisplayName.equals("Alipay")) {
            order.setPayment(OrderPayment.fromCashPayment(Integer.valueOf(5)));
            order.setPaymentMode(PaymentMode.ThirdPart);
        }
    }

    public void refreshStoreInfoAndDeliveryFee() {
        Ensighten.evaluateEvent(this, "refreshStoreInfoAndDeliveryFee", null);
        if (this.mOrderingModule == null) {
            this.mOrderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        }
    }

    private List<BasketListItem> createBasketItems() {
        Ensighten.evaluateEvent(this, "createBasketItems", null);
        List<BasketListItem> items = new ArrayList();
        if (this.mOrder != null) {
            if (this.mOrder.getProducts() != null) {
                for (OrderProduct orderProduct : this.mOrder.getProducts()) {
                    items.addAll(createProductItems(orderProduct));
                }
            }
            if (this.mOrder.getOffers() != null) {
                for (OrderOffer orderOffer : this.mOrder.getOffers()) {
                    items.addAll(createOfferItems(orderOffer));
                }
            }
            this.mNonProductOfferNames.clear();
        }
        return items;
    }

    private OrderProduct getSubProduct(OrderProduct product) {
        Ensighten.evaluateEvent(this, "getSubProduct", new Object[]{product});
        if (product.getProduct().getProductType() != ProductType.Product) {
            return product;
        }
        return OrderProduct.getChoiceWithinChoiceProduct(product);
    }

    private String buildCustomizationString(OrderProduct product) {
        Ensighten.evaluateEvent(this, "buildCustomizationString", new Object[]{product});
        StringBuilder result = new StringBuilder();
        String separator = ", ";
        boolean hideSingleChoice = ProductUtils.hideSingleChoice();
        for (Choice choice : product.getRealChoices()) {
            if (!hideSingleChoice || !choice.isSingleChoice()) {
                OrderProduct orderProduct = ProductUtils.getActualChoice(choice);
                if (orderProduct != null) {
                    result.append(orderProduct.getDisplayName());
                    result.append(separator);
                    if (ListUtils.isNotEmpty(orderProduct.getRealChoices())) {
                        for (Choice subChoice : orderProduct.getRealChoices()) {
                            OrderProduct choiceSelection = ProductUtils.getActualChoice(subChoice);
                            if (choiceSelection != null) {
                                result.append(choiceSelection.getDisplayName());
                                result.append(separator);
                            }
                        }
                    }
                }
            }
        }
        return result.length() > 0 ? result.substring(0, result.length() - separator.length()) : "";
    }

    private List<OrderProduct> subProducts(OrderProduct product) {
        Ensighten.evaluateEvent(this, "subProducts", new Object[]{product});
        List<OrderProduct> ret = new ArrayList(product.getIngredients());
        if (!ListUtils.isEmpty(product.getRealChoices())) {
            for (Choice choice : product.getRealChoices()) {
                if (choice.getSelection() != null) {
                    ret.add(choice.getSelection());
                }
            }
        }
        return ret;
    }

    private boolean isUnavailableProductItem(OrderProduct product) {
        Ensighten.evaluateEvent(this, "isUnavailableProductItem", new Object[]{product});
        boolean retValue = false;
        if (!product.isMeal()) {
            return this.mUnavailableProductCodes.contains(product.getProductCode());
        }
        List<OrderProduct> subProducts = subProducts(product);
        if (!ListUtils.isNotEmpty(subProducts)) {
            return false;
        }
        for (OrderProduct subProduct : subProducts) {
            if (this.mUnavailableProductCodes.contains(subProduct.getProductCode())) {
                retValue = true;
            }
        }
        return retValue;
    }

    private List<BasketListItem> createOfferItems(OrderOffer orderOffer) {
        Ensighten.evaluateEvent(this, "createOfferItems", new Object[]{orderOffer});
        List<BasketListItem> items = new ArrayList();
        if (orderOffer.getOffer().getOfferType() != OfferType.OFFER_TYPE_DELIVERY_FEE) {
            if (orderOffer.getOrderOfferProducts() != null) {
                for (int i = 0; i < orderOffer.getOrderOfferProducts().size(); i++) {
                    boolean isHeader;
                    boolean isFooter;
                    boolean z;
                    OrderOfferProduct orderOfferProduct = (OrderOfferProduct) orderOffer.getOrderOfferProducts().get(i);
                    if (i == 0) {
                        isHeader = true;
                    } else {
                        isHeader = false;
                    }
                    if (i == orderOffer.getOrderOfferProducts().size() - 1) {
                        isFooter = true;
                    } else {
                        isFooter = false;
                    }
                    BasketListItem newItem = new BasketListItem();
                    newItem.setBasketItem(orderOffer);
                    newItem.setOfferPriceChanged(false);
                    if (isHeader) {
                        z = false;
                    } else {
                        z = true;
                    }
                    newItem.setTopPaddingHidden(Boolean.valueOf(z));
                    if (isHeader) {
                        newItem.setHeaderHidden(Boolean.valueOf(false));
                        newItem.setHeaderIconHidden(Boolean.valueOf(false));
                        newItem.setHeaderText(orderOffer.getOffer().getName());
                    } else {
                        newItem.setHeaderHidden(Boolean.valueOf(true));
                    }
                    newItem.setDividerHidden(Boolean.valueOf(true));
                    if (isFooter) {
                        newItem.setFooterHidden(Boolean.valueOf(true));
                        newItem.setEnergyTotal(AppUtils.getEnergyTextForOrderOffer(orderOffer, OrderOfferUtils.getTotalEnergyUnit(orderOffer)));
                        newItem.setPriceTotal(UIUtils.getLocalizedCurrencyFormatter().format(this.mOrder.getOfferTotalValue()));
                    } else {
                        newItem.setFooterHidden(Boolean.valueOf(true));
                    }
                    newItem.setMakeItAMealHidden(Boolean.valueOf(true));
                    updateItemData(newItem, orderOfferProduct.getSelectedProductOption(), false);
                    OrderProduct imageProduct = orderOfferProduct.getSelectedProductOption();
                    if (imageProduct != null) {
                        newItem.setIconImage(imageProduct.getDisplayThumbnailImage());
                    }
                    items.add(newItem);
                }
            } else {
                this.mNonProductOfferNames.add(orderOffer.getOffer().getName());
            }
        }
        return items;
    }

    private List<BasketListItem> createProductItems(OrderProduct product) {
        int i;
        Ensighten.evaluateEvent(this, "createProductItems", new Object[]{product});
        List<BasketListItem> items = new ArrayList();
        List<OrderProduct> subProducts = !product.isMeal() ? Collections.singletonList(product) : subProducts(product);
        int productErrorCode = 1;
        boolean hasError = false;
        for (i = 0; i < subProducts.size(); i++) {
            OrderUtils.checkProductInCurrentStore(getSubProduct((OrderProduct) subProducts.get(i)), product, this.mOrderingModule, this.mUnavailableProductCodes);
        }
        if (!(this.mOrder.getTotalizeResult() == null || this.mOrder.getTotalizeResult().getOrderView() == null)) {
            int validationErrorCode = this.mOrder.getTotalizeResult().getOrderView().getProductValidationErrorCode(product);
            if (validationErrorCode != 1) {
                hasError = true;
                productErrorCode = validationErrorCode;
            }
        }
        if (ListUtils.isNotEmpty(this.mUnavailableProductCodes) && isUnavailableProductItem(product)) {
            hasError = true;
            productErrorCode = OrderResponse.PRODUCT_UNAVAILABLE_CODE;
        }
        int ingredientsSize = 0;
        if (product.getIngredients() != null) {
            ingredientsSize = product.getIngredients().size();
        }
        i = 0;
        while (i < subProducts.size()) {
            int choiceIndex;
            OrderProduct subProduct = getSubProduct((OrderProduct) subProducts.get(i));
            OrderProduct choiceProduct = OrderProduct.getChoiceWithinChoiceProduct(subProduct);
            if (choiceProduct != null) {
                subProduct = choiceProduct;
            }
            subProduct.setOutOfStock(false);
            if (product.isMeal()) {
                if (ListUtils.isNotEmpty(this.mUnavailableProductCodes) && isUnavailableProductItem(product)) {
                    hasError = true;
                }
                if (!(this.mOrder.getTotalizeResult() == null || this.mOrder.getTotalizeResult().getOrderView() == null || this.mOrder.getTotalizeResult().getOrderView().getProductValidationErrorCode(subProduct) != -1036)) {
                    subProduct.setOutOfStock(true);
                    hasError = true;
                }
                choiceIndex = i;
                if (!(ListUtils.isEmpty(product.getIngredients()) || product.getIngredients().contains(subProduct))) {
                    choiceIndex -= product.getIngredients().size();
                }
                if (product.getRealChoices().size() > choiceIndex) {
                    Choice choiceSubProduct = (Choice) product.getRealChoices().get(choiceIndex);
                    List<OrderProduct> choiceOptions = choiceSubProduct.getOptions();
                    if (!ListUtils.isEmpty(choiceOptions) && choiceOptions.contains(subProduct) && choiceSubProduct.isSingleChoice() && ProductUtils.hideSingleChoice()) {
                        i++;
                    }
                }
            }
            if (productErrorCode == -6027) {
                hasError = true;
            }
            if (this.mOrderBeforeStoreChange != null && !ListUtils.isEmpty(this.mOrderBeforeStoreChange.getProducts()) && productErrorCode == 1) {
                PriceType oldPriceType = this.mOrderBeforeStoreChange.getPriceType();
                PriceType newPriceType = this.mOrder.getPriceType();
                for (OrderProduct oldProduct : this.mOrderBeforeStoreChange.getProducts()) {
                    if (oldProduct.equals(product) && oldProduct.getTotalPrice(oldPriceType) != product.getTotalPrice(newPriceType)) {
                        hasError = true;
                        productErrorCode = OrderResponse.PRODUCT_PRICE_CHANGED;
                        break;
                    }
                }
            }
            boolean isHeader = i == 0 && product.isMeal();
            boolean isDivider = i == 0 && !product.isMeal();
            if (i == subProducts.size() - 1) {
            }
            boolean isUnavailable = false;
            BasketListItem item = new BasketListItem();
            item.setBasketItem(product);
            if ((product.getProduct() == null || product.isUnavailable()) && isHeader) {
                isUnavailable = true;
                setErrorFlag(OrderResponse.PRODUCT_UNAVAILABLE_CODE, item);
            } else if (product.isMeal() && hasError && subProduct.isUnavailable()) {
                setErrorFlag(OrderResponse.PRODUCT_UNAVAILABLE_CODE, item);
                item.setUnavailable(true);
                item.setHasError(true);
                item.setMealErrorItem(true);
            } else if (product.isMeal() && hasError && subProduct.isOutOfStock()) {
                setErrorFlag(OrderResponse.PRODUCT_OUT_OF_STOCK_CODE, item);
                item.setOutOfStock(true);
                item.setHasError(true);
                item.setMealErrorItem(true);
            } else if (!product.isMeal() && hasError) {
                setErrorFlag(productErrorCode, item);
            }
            item.setTimeRestriction(product.getProduct().getTimeRestriction());
            item.setTimeRestrictions(product.getProduct().getTimeRestrictions());
            item.setTopPaddingHidden(Boolean.valueOf(!isHeader));
            if (isHeader) {
                item.setHeaderHidden(Boolean.valueOf(false));
                item.setHeaderIconHidden(Boolean.valueOf(true));
                item.setHeaderText(product.getQuantity() + " " + product.getDisplayName());
                if (hasError) {
                    if (!subProduct.isUnavailable()) {
                        item.setMealHeaderNonErrorWarningItem(true);
                    }
                    setErrorFlag(productErrorCode, item);
                }
            } else {
                item.setHeaderHidden(Boolean.valueOf(true));
            }
            item.setDividerHidden(Boolean.valueOf(!isDivider));
            item.setFooterHidden(Boolean.valueOf(true));
            if (hasNonSingleChoiceItems(subProduct)) {
                item.setHeaderDetailsText(buildCustomizationString(subProduct));
            }
            item.setMakeItAMealHidden(Boolean.valueOf(true));
            Boolean hasMeal = Boolean.valueOf(false);
            String pod;
            if (OrderingManager.getInstance().getCurrentOrder().isDelivery()) {
                pod = Pod.DELIVERY;
            } else {
                pod = Pod.PICKUP;
            }
            boolean filterDimenPod = Configuration.getSharedInstance().getBooleanForKey("interface.ordering.filterDimenPod");
            if (!(product.isMeal() || isUnavailable || !checkDisplaySizeSelection(product))) {
                List<ProductDimension> dimensions = product.getProduct().getDimensions();
                if (dimensions != null) {
                    for (ProductDimension dimension : dimensions) {
                        if (filterDimenPod) {
                            this.mOrderingModule.populateProductWithStoreSpecificData(dimension.getProduct());
                        }
                        if (dimension.getProduct().getProductType() == ProductType.Meal && (!filterDimenPod || dimension.getProduct().getPODs().contains(pod))) {
                            hasMeal = Boolean.valueOf(true);
                            break;
                        }
                    }
                }
                if (hasMeal.booleanValue() && (isHeader || isDivider)) {
                    item.setMakeItAMealHidden(Boolean.valueOf(false));
                }
            }
            boolean includeQuantity = i == 0 && !product.isMeal();
            updateItemData(item, subProduct, includeQuantity);
            choiceIndex = i - ingredientsSize;
            if (product.isMeal() && !isHeader && ListUtils.isNotEmpty(product.getRealChoices()) && product.getRealChoices().size() > choiceIndex && choiceIndex >= 0) {
                item.setItemUplift(ProductUtils.getProductTotalPrice(subProduct) - this.mOrderingModule.getProductBasePrice((Choice) product.getRealChoices().get(choiceIndex)));
            }
            OrderProduct imageProduct = subProduct;
            if (subProduct instanceof Choice) {
                imageProduct = ((Choice) subProduct).getSelection();
            }
            item.setIconImage(imageProduct.getDisplayThumbnailImage());
            if (!product.isUnavailable()) {
                validatePODs(subProduct, item);
            }
            items.add(item);
            i++;
        }
        return items;
    }

    private boolean checkDisplaySizeSelection(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "checkDisplaySizeSelection", new Object[]{orderProduct});
        Store store = OrderManager.getInstance().getCurrentStore();
        Product product = orderProduct.getProduct();
        StoreProduct storeProduct = new StoreProduct();
        storeProduct.setProductId(product.getExternalId().intValue());
        storeProduct.setStoreId(store.getStoreId());
        if (getBaseContext() != null) {
            for (StoreProductCategory c : StoreProductCategoryRepository.getCategoryByStoreProduct(getBaseContext(), storeProduct)) {
                if (c.getDisplaySizeSelection() != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void updateItemData(BasketListItem item, OrderProduct product, boolean includeQuantity) {
        Ensighten.evaluateEvent(this, "updateItemData", new Object[]{item, product, new Boolean(includeQuantity)});
        if (!(product.getProduct() == null || product.getProduct().getLongName() == null)) {
            if (includeQuantity) {
                item.setItemName(product.getQuantity() + " " + product.getProduct().getLongName());
            } else {
                item.setItemName(product.getProduct().getLongName());
            }
        }
        if (product.getCustomizations() != null) {
            item.setItemInstructions(OrderProductUtils.getCustomizationsString(product));
        }
        if (hasNonSingleChoiceItems(product)) {
            item.setHeaderDetailsText(buildCustomizationString(product));
        }
    }

    private void validatePODs(OrderProduct product, BasketListItem item) {
        Ensighten.evaluateEvent(this, "validatePODs", new Object[]{product, item});
        List<String> productPODs = product.getProduct().getPODs();
        if (productPODs.size() < PODUtils.getMainPODsLength()) {
            String message;
            if (productPODs.size() == 1) {
                message = getString(C2658R.string.label_available_pod_only, new Object[]{OrderProduct.getPODDisplayName((String) productPODs.get(0), getResources())});
            } else {
                message = createMultiplePODsUnavailableMessage(productPODs);
            }
            item.setUnavailablePODMessage(message);
        }
    }

    private String createMultiplePODsUnavailableMessage(List<String> pods) {
        Ensighten.evaluateEvent(this, "createMultiplePODsUnavailableMessage", new Object[]{pods});
        String name = OrderProduct.getPODDisplayName((String) PODUtils.getRemainingPODs(pods).get(0), getResources());
        return getString(C2658R.string.label_one_or_more_unavailable_pods, new Object[]{name});
    }

    private boolean hasNonSingleChoiceItems(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(this, "hasNonSingleChoiceItems", new Object[]{orderProduct});
        if (!ProductUtils.hideSingleChoice()) {
            return true;
        }
        Product product = orderProduct.getProduct();
        if (product != null && ListUtils.isNotEmpty(product.getChoices())) {
            for (Ingredient ingredient : product.getChoices()) {
                if (ingredient.getProduct() != null && !ingredient.getProduct().isSingleChoice().booleanValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void setErrorFlag(int productErrorCode, BasketListItem newItem) {
        boolean z;
        Ensighten.evaluateEvent(this, "setErrorFlag", new Object[]{new Integer(productErrorCode), newItem});
        newItem.setHasError(true);
        if (productErrorCode == OrderResponse.PRODUCT_PRICE_CHANGED) {
            z = true;
        } else {
            z = false;
        }
        newItem.setPriceChanged(z);
        if (productErrorCode == OrderResponse.PRODUCT_OUT_OF_STOCK_CODE) {
            z = true;
        } else {
            z = false;
        }
        newItem.setOutOfStock(z);
        if (productErrorCode == OrderResponse.OFFERS_ERROR_DELIVERY_ONLY || productErrorCode == OrderResponse.OFFERS_ERROR_PICKUP_ONLY) {
            newItem.setOfferPODErrorCode(productErrorCode);
        }
        newItem.setUnavailable(Arrays.asList(new Integer[]{Integer.valueOf(OrderResponse.PRODUCT_TIME_RESTRICTION_CODE), Integer.valueOf(OrderResponse.PRODUCT_UNAVAILABLE_CODE), Integer.valueOf(OrderResponse.PRODUCT_UNAVAILABLE_AT_RESTAURANT_CODE)}).contains(Integer.valueOf(productErrorCode)));
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        this.mPresenter.setupPaymentModes();
        this.mPresenter.checkIfUserHasMoreThanMaxCards();
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        boolean comingFromBagCharge = true;
        Ensighten.evaluateEvent(this, "onNewIntent", new Object[]{intent});
        super.onNewIntent(intent);
        Bundle extras = getIntent().getExtras();
        if (extras == null || !extras.getBoolean("FROM_BAG_CHARGE")) {
            comingFromBagCharge = false;
        }
        this.mPresenter.setComingFromBagCharges(comingFromBagCharge);
        this.mPresenter.initialize();
    }

    public void navigateToSignIn() {
        Ensighten.evaluateEvent(this, "navigateToSignIn", null);
        Bundle extras = new Bundle();
        extras.putSerializable("EXTRA_RESULT_CONTAINER_CLASS", CheckoutActivity.class);
        startActivityForResult(SignInActivity.class, JiceArgs.EVENT_CHECK_IN, extras, 4082);
    }

    public void showOutOfStockAlert(int errorCode) {
        Ensighten.evaluateEvent(this, "showOutOfStockAlert", new Object[]{new Integer(errorCode)});
        Bundle bundle = new Bundle();
        bundle.putInt(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, errorCode);
        bundle.putBoolean(AlertFragment.PARAMETER_HIDE_POSITIVE, true);
        startActivityForResult(AlertActivity.class, "check_out_items_unavailable", bundle, 37);
        finish();
    }

    public void showZeroOrNegativePriceError() {
        Ensighten.evaluateEvent(this, "showZeroOrNegativePriceError", null);
        showFatalError(getString(C2658R.string.ecp_error_1606));
    }

    public void showFatalError(String errorMessage) {
        Ensighten.evaluateEvent(this, "showFatalError", new Object[]{errorMessage});
        if (!isFinishing()) {
            MCDAlertDialogBuilder.withContext(this).setTitle((int) C2658R.string.error_title).setMessage(errorMessage).setNeutralButton((int) C2658R.string.f6083ok, new C34606()).create().show();
        }
    }

    public void showLargeOrderWarning() {
        Ensighten.evaluateEvent(this, "showLargeOrderWarning", null);
        MCDAlertDialogBuilder.withContext(this).setTitle((int) C2658R.string.warning).setMessage((int) C2658R.string.large_order_review_order_notification).setNegativeButton((int) C2658R.string.button_review_order, new C34628()).setPositiveButton((int) C2658R.string.f6083ok, new C34617()).create().show();
    }

    public void showOrderErrors(int errorType, int productErrorCode, List<String> problematicProductCodes, boolean hidePositive) {
        Ensighten.evaluateEvent(this, "showOrderErrors", new Object[]{new Integer(errorType), new Integer(productErrorCode), problematicProductCodes, new Boolean(hidePositive)});
        String fragmentName = "";
        switch (errorType) {
            case 1:
                fragmentName = "check_out_items_out_of_stock";
                break;
            case 2:
                fragmentName = "check_out_items_unavailable";
                break;
        }
        if (!fragmentName.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putInt(BasketFragment.PARAMETER_PRODUCT_ERROR_CODE, productErrorCode);
            bundle.putStringArrayList(BasketFragment.PARAMETER_PROBLEMATIC_PRODUCTS_CODES, (ArrayList) problematicProductCodes);
            bundle.putBoolean(AlertFragment.PARAMETER_HIDE_POSITIVE, hidePositive);
            startActivityForResult(AlertActivity.class, fragmentName, bundle, 37);
        }
    }

    public void setZeroPricedOrder(boolean zeroPriced) {
        Ensighten.evaluateEvent(this, "setZeroPricedOrder", new Object[]{new Boolean(zeroPriced)});
        if (zeroPriced) {
            this.mBinding.paymentContainer.setBackgroundColor(ContextCompat.getColor(this, C2658R.color.light_gray_1));
            this.mBinding.payWithLabel.setTextColor(ContextCompat.getColor(this, C2658R.color.light_gray_3));
            this.mBinding.choosePaymentLabel.setTextColor(ContextCompat.getColor(this, C2658R.color.light_gray_3));
            return;
        }
        this.mBinding.paymentContainer.setBackgroundColor(ContextCompat.getColor(this, 17170443));
        this.mBinding.payWithLabel.setTextColor(ContextCompat.getColor(this, C2658R.color.dark_gray_2));
        this.mBinding.choosePaymentLabel.setTextColor(ContextCompat.getColor(this, C2658R.color.menu_dark_gray_1));
    }

    public void showPaymentSelection(LinkedHashSet<PaymentMethod> paymentMethods) {
        Ensighten.evaluateEvent(this, "showPaymentSelection", new Object[]{paymentMethods});
        Bundle bundle = new Bundle();
        bundle.putSerializable(URLNavigationActivity.DATA_PASSER_KEY, paymentMethods);
        startActivityForResult(PaymentSelectionActivity.class, "select_payment", bundle, 4081);
    }

    public void showNoPaymentSelectedError() {
        Ensighten.evaluateEvent(this, "showNoPaymentSelectedError", null);
        MCDAlertDialogBuilder.withContext(this).setTitle((int) C2658R.string.payment_method).setMessage((int) C2658R.string.pick_payment).setPositiveButton((int) C2658R.string.f6083ok, null).create().show();
    }

    public void startOneTimePaymentCheckinFlow() {
        Ensighten.evaluateEvent(this, "startOneTimePaymentCheckinFlow", null);
        navigateToPayment();
    }

    public void startRegularCheckinFlow() {
        Ensighten.evaluateEvent(this, "startRegularCheckinFlow", null);
        navigateToPayment();
    }

    private void navigateToPayment() {
        Ensighten.evaluateEvent(this, "navigateToPayment", null);
        startActivityForResult(PaymentActivity.class, getIntent().getExtras(), 20);
    }

    public void startInterinCheckinFlow() {
        Ensighten.evaluateEvent(this, "startInterinCheckinFlow", null);
        startActivity(ChoosePickUpActivity.class, "choose_pick_up");
    }

    public void showPickupMethodSelector() {
        Ensighten.evaluateEvent(this, "showPickupMethodSelector", null);
        startActivity(PickupMethodActivity.class, PickupMethodFragment.NAME);
    }

    public void showMaxCardsAlert() {
        Ensighten.evaluateEvent(this, "showMaxCardsAlert", null);
        MCDAlertDialogBuilder.withContext(this).setMessage((int) C2658R.string.max_payment_card_alert_message).setPositiveButton((int) C2658R.string.f6083ok, this.mPresenter.onMaxCardAlertPositiveClicked).setNegativeButton((int) C2658R.string.max_payment_card_cancel_button_title, this.mPresenter.onMaxCardsAlertNegativeClicked).setCancelable(false).create().show();
    }

    public void navigateToAccountCardsPage() {
        Ensighten.evaluateEvent(this, "navigateToAccountCardsPage", null);
        startActivity(ModifyCardsActivity.class);
    }

    public void navigateToDashboard() {
        Ensighten.evaluateEvent(this, "navigateToDashboard", null);
        setResult(15207);
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.mOrder = OrderingManager.getInstance().getCurrentOrder();
        OrderManager.getInstance().cleanBagsFromOrder();
        this.mListViewAdapter.clear();
        this.mListViewAdapter.addAll(createBasketItems());
        this.mPresenter.initialize();
        if (requestCode == 4081 && resultCode == -1) {
            if (data.hasExtra("Cash")) {
                this.mPresenter.setCashPayment();
                this.iv_ali_img.setVisibility(8);
            } else if (data.hasExtra("ThirdPart")) {
                this.mPresenter.setAlipayPayment();
                this.iv_ali_img.setVisibility(0);
            } else {
                this.mPresenter.setPaymentCard((PaymentCard) data.getExtras().getParcelable("PaymentSelectionFragment.DATA_KEY"));
                this.iv_ali_img.setVisibility(8);
            }
        } else if (resultCode == 39) {
            setResult(39);
            finish();
        }
    }

    public void onActionRemove(Object item) {
        Ensighten.evaluateEvent(this, "onActionRemove", new Object[]{item});
    }

    public void onActionEdit(Object item) {
        Ensighten.evaluateEvent(this, "onActionEdit", new Object[]{item});
        Analytics.track(AnalyticType.Event, new ArgBuilder().setCategory("/basket").setAction("On click").setLabel("Edit").build());
        if (item instanceof OrderProduct) {
            new SparseArray().put(21, ((OrderProduct) item).getProduct().getName());
            Bundle extras = new Bundle();
            DataPasser.getInstance().putData("ARG_PRODUCT", item);
            extras.putBoolean("arg_editing", true);
            extras.putString("ARG_ANALYTICS_PARENT_NAME", getString(C2658R.string.analytics_screen_basket_item));
            extras.putStringArrayList("ARG_UNAVAILABLE_PRODUCT_CODES", (ArrayList) this.mUnavailableProductCodes);
            Intent intent = new Intent(this, ProductDetailsActivity.class);
            intent.putExtras(extras);
            startActivityForResult(intent, ProductDetailsActivity.REQUEST_CODE.intValue());
        } else if (item instanceof Offer) {
            new SparseArray().put(25, ((Offer) item).getName());
            startActivity(OfferActivity.class, "offer_detail");
        }
    }

    public void onActionMakeItAMeal(Object item) {
        Ensighten.evaluateEvent(this, "onActionMakeItAMeal", new Object[]{item});
        if (item instanceof OrderProduct) {
            new HashMap().put(JiceArgs.EVENT_NAME, JiceArgs.EVENT_BASKET_MAKE_IT_A_MEAL);
            new SparseArray().put(21, ((OrderProduct) item).getProduct().getName());
            onActionEdit(item);
        }
    }
}
