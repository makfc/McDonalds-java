package com.mcdonalds.app.ordering.summary;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.OrderProductUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.Offer.OfferType;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderOffer;
import com.mcdonalds.sdk.modules.models.OrderOfferProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.OrderResponse;
import com.mcdonalds.sdk.modules.models.Product.ProductType;
import com.mcdonalds.sdk.modules.models.ProductView;
import com.mcdonalds.sdk.modules.models.Promotion;
import com.mcdonalds.sdk.modules.models.PromotionView;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderReceipt {
    public static void configureOrderReceiptForDisplay(Order order, Context context, LinearLayout layout, String storeAddress, String paymentName) {
        OrderProduct product;
        Double total;
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderReceipt", "configureOrderReceiptForDisplay", new Object[]{order, context, layout, storeAddress, paymentName});
        LayoutInflater inflater = LayoutInflater.from(context);
        NumberFormat currencyFormatter = UIUtils.getLocalizedCurrencyFormatter();
        OrderDetailsElement orderDetailsElement = new OrderDetailsElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_bordered_label, layout, false));
        orderDetailsElement.getQuantity().setVisibility(8);
        orderDetailsElement.getTitle().setText(storeAddress);
        Store currentStore = OrderManager.getInstance().getCurrentStore();
        if (!order.isDelivery()) {
            String storeFavoriteName = currentStore != null ? currentStore.getStoreFavoriteName() : "";
            String currentStoreName = currentStore != null ? currentStore.getAddress1() : "";
            if (TextUtils.isEmpty(storeFavoriteName)) {
                orderDetailsElement.getTitle().setText(currentStoreName);
            } else {
                orderDetailsElement.getTitle().setText(storeFavoriteName);
                orderDetailsElement.getInfo().setText(currentStoreName);
            }
        }
        orderDetailsElement.getPrice().setVisibility(4);
        layout.addView(orderDetailsElement.getContainer());
        addDashedDivider(context, layout);
        OrderProduct bagProduct = null;
        if (currentStore.isBagChargeEnabled()) {
            for (OrderProduct product2 : order.getProducts()) {
                if (currentStore.getNoBagProductCode() == Integer.parseInt(product2.getProductCode())) {
                    bagProduct = product2;
                }
            }
        }
        if (bagProduct != null) {
            order.removeProduct(bagProduct);
        }
        List<ProductView> totalizeProducts = getProductsFromOrderResponse(order.getMostRecentOrderResponse(), false);
        List<ProductView> totalizeProductsForOffer = getProductsFromOrderResponse(order.getMostRecentOrderResponse(), true);
        List<OrderProduct> orderProducts = new ArrayList();
        orderProducts.addAll(order.getProducts());
        ArrayList<OrderProduct> offerProductsList = new ArrayList();
        for (OrderOffer orderOffer : order.getOffers()) {
            List<OrderOfferProduct> orderOfferProducts = orderOffer.getOrderOfferProducts();
            if (!(orderOfferProducts == null || orderOffer.getOffer().getOfferType() == OfferType.OFFER_TYPE_DELIVERY_FEE)) {
                int size = orderOfferProducts.size();
                for (int i = 0; i < size; i++) {
                    OrderProduct selectedProductOption = ((OrderOfferProduct) orderOfferProducts.get(i)).getSelectedProductOption();
                    if (selectedProductOption != null) {
                        offerProductsList.add(selectedProductOption);
                    }
                }
            }
        }
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        for (ProductView productView : totalizeProducts) {
            boolean isNoBagProduct = false;
            OrderProduct orderProduct = getOrderProduct(orderProducts, productView, order.getMostRecentOrderResponse().getOrderDate(), orderingModule);
            if (OrderManager.getInstance().allowBagCharges() && orderProduct.getProduct().getExternalId().intValue() == currentStore.getNoBagProductCode()) {
                isNoBagProduct = true;
            }
            if (orderProduct.getProduct().getProductType() == ProductType.DeliveryFee) {
                isNoBagProduct = true;
            }
            if (orderProduct != null) {
                orderProduct.attachProductViewToOrderProduct(productView);
            }
            if (!isNoBagProduct) {
                layoutIndividualProduct(context, inflater, layout, order, currencyFormatter, orderProduct, productView);
            }
        }
        List<OrderProduct> arrayList = new ArrayList(offerProductsList);
        for (ProductView productView2 : totalizeProductsForOffer) {
            product2 = findOrderProduct(arrayList, productView2);
            arrayList.remove(product2);
            layoutIndividualProduct(context, inflater, layout, order, currencyFormatter, product2, productView2);
        }
        if (!Configuration.getSharedInstance().getBooleanForKey("modules.ordering.doNotShowTaxWithTotal", false)) {
            orderDetailsElement = new OrderDetailsElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_bordered_label, layout, false));
            orderDetailsElement.getTopBorder().setVisibility(8);
            orderDetailsElement.getBottomBorder().setVisibility(8);
            orderDetailsElement.getQuantity().setVisibility(8);
            orderDetailsElement.getPrice().setVisibility(4);
            orderDetailsElement.getTitle().setText(C2658R.string.order_tax_label);
            orderDetailsElement.getInfo().setText(currencyFormatter.format(order.getTotalTax()));
            layout.addView(orderDetailsElement.getContainer());
            addDashedDivider(context, layout);
        }
        if (order.isDelivery()) {
            orderDetailsElement = new OrderDetailsElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_bordered_label, layout, false));
            orderDetailsElement.getTopBorder().setVisibility(0);
            orderDetailsElement.getBottomBorder().setVisibility(0);
            orderDetailsElement.getQuantity().setVisibility(8);
            orderDetailsElement.getInfo().setVisibility(8);
            total = order.getTotalizeResult().getDeliveryFee();
            orderDetailsElement.getTitle().setText(C2658R.string.delivery_label_fee);
            orderDetailsElement.getPrice().setText(currencyFormatter.format(total));
            layout.addView(orderDetailsElement.getContainer());
            if (order.hasDeliveryFeeOffer()) {
                orderDetailsElement = new OrderDetailsElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_bordered_label, layout, false));
                orderDetailsElement.getTopBorder().setVisibility(8);
                orderDetailsElement.getBottomBorder().setVisibility(8);
                orderDetailsElement.getQuantity().setVisibility(8);
                orderDetailsElement.getInfo().setVisibility(8);
                Double deliveryFeeDiscount = Double.valueOf(order.getDiscountedDeliveryCharge().doubleValue() - total.doubleValue());
                orderDetailsElement.getTitle().setText(order.getDeliveryChargeOfferName());
                orderDetailsElement.getPrice().setText(currencyFormatter.format(deliveryFeeDiscount));
                layout.addView(orderDetailsElement.getContainer());
            }
        }
        for (OrderOffer offer : order.getOffers()) {
            if (!ListUtils.isEmpty(offer.getOrderOfferProducts())) {
                double discount;
                orderDetailsElement = new OrderDetailsElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_bordered_label, layout, false));
                orderDetailsElement.getTopBorder().setVisibility(8);
                orderDetailsElement.getBottomBorder().setVisibility(8);
                orderDetailsElement.getQuantity().setVisibility(8);
                orderDetailsElement.getInfo().setVisibility(8);
                if (order.isDelivery()) {
                    discount = (order.getMostRecentOrderResponse().getTotalValue().doubleValue() - order.getTotalValue()) - order.getTotalizeResult().getDeliveryFee().doubleValue();
                } else {
                    discount = order.getMostRecentOrderResponse().getTotalValue().doubleValue() - order.getTotalValue();
                }
                orderDetailsElement.getTitle().setText(offer.getOffer().getName());
                orderDetailsElement.getPrice().setText(currencyFormatter.format(discount));
                if (discount == 0.0d) {
                    orderDetailsElement.getPrice().setVisibility(4);
                }
                layout.addView(orderDetailsElement.getContainer());
            }
        }
        orderDetailsElement = new OrderDetailsElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_bordered_label, layout, false));
        orderDetailsElement.getTopBorder().setVisibility(8);
        orderDetailsElement.getBottomBorder().setVisibility(0);
        orderDetailsElement.getQuantity().setVisibility(8);
        orderDetailsElement.getInfo().setVisibility(8);
        total = order.getMostRecentOrderResponse().getTotalValue();
        if (order.getMostRecentOrderResponse().getOrderView().getTotalDiscount().doubleValue() != 0.0d) {
            orderDetailsElement.getTitle().setText(context.getString(C2658R.string.order_total_label) + context.getString(C2658R.string.conditional_order_discount_included));
        } else {
            orderDetailsElement.getTitle().setText(C2658R.string.order_total_label);
        }
        orderDetailsElement.getPrice().setText(currencyFormatter.format(total));
        layout.addView(orderDetailsElement.getContainer());
        if (!order.isZeroPriced()) {
            orderDetailsElement = new OrderDetailsElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_bordered_label, layout, false));
            orderDetailsElement.getQuantity().setVisibility(8);
            orderDetailsElement.getPrice().setText("");
            orderDetailsElement.getTitle().setText(C2658R.string.order_payment_label);
            orderDetailsElement.getInfo().setText(paymentName);
            layout.addView(orderDetailsElement.getContainer());
        }
    }

    private static OrderProduct getOrderProduct(List<OrderProduct> orderProducts, ProductView productView, Date orderDate, OrderingModule orderingModule) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderReceipt", "getOrderProduct", new Object[]{orderProducts, productView, orderDate, orderingModule});
        OrderProduct orderProduct = findOrderProduct(orderProducts, productView);
        if (orderProduct == null) {
            orderProduct = OrderProduct.createProduct(orderingModule.getProductForExternalId(String.valueOf(productView.getProductCode()), orderDate), productView.getQuantity());
            if (orderProduct != null) {
                int i;
                if (!ListUtils.isEmpty(productView.getChoices())) {
                    ArrayList<ProductView> choices = productView.getChoices();
                    for (i = 0; i < choices.size(); i++) {
                        Choice choice = (Choice) orderProduct.getRealChoices().get(i);
                        choice.setSelection(getOrderProduct(choice.getOptions(), ((ProductView) choices.get(i)).getActualChoice(), orderDate, orderingModule));
                    }
                }
                if (!ListUtils.isEmpty(productView.getCustomizations())) {
                    ArrayList<ProductView> productViewCustomizations = productView.getCustomizations();
                    for (i = 0; i < productViewCustomizations.size(); i++) {
                        ProductView customizationProductView = (ProductView) productViewCustomizations.get(i);
                        OrderProduct customizationOrderProduct = getOrderProduct(null, customizationProductView, orderDate, orderingModule);
                        Map<Integer, OrderProduct> customizations = orderProduct.getCustomizations();
                        if (customizations == null) {
                            customizations = new HashMap();
                            orderProduct.setCustomizations(customizations);
                        }
                        customizations.put(customizationProductView.getProductCode(), customizationOrderProduct);
                    }
                }
            }
        }
        return orderProduct;
    }

    private static OrderProduct findOrderProduct(List<OrderProduct> orderProducts, ProductView productView) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderReceipt", "findOrderProduct", new Object[]{orderProducts, productView});
        if (ListUtils.isEmpty(orderProducts)) {
            return null;
        }
        for (OrderProduct product : orderProducts) {
            if (product.equals(productView)) {
                return product;
            }
        }
        return null;
    }

    private static void layoutIndividualProduct(Context context, LayoutInflater inflater, LinearLayout layout, Order order, NumberFormat currencyFormatter, OrderProduct product, @Nullable ProductView productView) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderReceipt", "layoutIndividualProduct", (Object[]) new Object[]{context, inflater, layout, order, currencyFormatter, product, productView});
        if (product != null) {
            OrderDetailsElement element = new OrderDetailsElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_bordered_label, layout, false));
            element.getTopBorder().setVisibility(8);
            element.getBottomBorder().setVisibility(8);
            String offerIndication = context.getString(C2658R.string.indicator_offer);
            element.getQuantity().setText(product.getQuantity() == 0 ? "" : String.valueOf(product.getQuantity()));
            if (productView.getPromotion() == null || productView.getPromotion().getActionName() == null) {
                element.getTitle().setText(Html.fromHtml(" <b>" + product.getDisplayName() + "</b> <br />" + ProductUtils.getNameDetailsString(product) + "</b> <br />" + OrderProductUtils.getCustomizationsString(product)));
            } else {
                element.getTitle().setText(Html.fromHtml(" <b>" + product.getProduct().getLongName() + offerIndication + "</b> <br />" + OrderProductUtils.getCustomizationsString(product)));
            }
            if (AppUtils.hideNutritionOnOrderingPages()) {
                element.getInfo().setVisibility(8);
            } else {
                element.getInfo().setText(AppUtils.getEnergyTextForOrderProduct(product, OrderProductUtils.getTotalEnergyUnit(product)));
            }
            double price = ProductUtils.getProductTotalPrice(product);
            if (productView != null) {
                Promotion promotion = productView.getPromotion();
                if (promotion != null && promotion.getDiscountAmount().doubleValue() > 0.0d) {
                    price = productView.getTotalValue().doubleValue();
                    if (promotion.getOriginalPrice().doubleValue() - promotion.getDiscountAmount().doubleValue() == 0.0d) {
                        element.getPrice().setText(context.getResources().getString(C2658R.string.free));
                        price = product.getTotalCustomizationsPrice(OrderManager.getInstance().getCurrentOrder().getPriceType(), OrderManager.getInstance().allowDownCharge()).doubleValue();
                    }
                }
            }
            String priceString = currencyFormatter.format(price);
            Store currentStore = OrderManager.getInstance().getCurrentStore();
            if (currentStore != null && currentStore.isBagChargeEnabled() && OrderManager.getInstance().isBagChargeAdded() && currentStore.getBagProductCode() == Integer.parseInt(product.getProductCode())) {
                element.getInfo().setVisibility(8);
                element.getPrice().setText(priceString);
            } else {
                element.getPrice().setText(priceString);
            }
            layout.addView(element.getContainer());
            if (product.isMeal()) {
                OrderDetailsSubElement subElement;
                String nameDetails;
                if (product.getIngredients() != null) {
                    for (OrderProduct ingredient : product.getIngredients()) {
                        subElement = new OrderDetailsSubElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_sub_element, layout, false));
                        subElement.getTitle().setText(ingredient.getProduct().getName());
                        nameDetails = ProductUtils.getNameDetailsString(ingredient);
                        if (TextUtils.isEmpty(nameDetails)) {
                            subElement.getDetails().setVisibility(8);
                        } else {
                            subElement.getDetails().setText(nameDetails);
                        }
                        subElement.getInfo().setText(OrderProductUtils.getCustomizationsString(ingredient));
                        layout.addView(subElement.getContainer());
                    }
                }
                if (product.getRealChoices() != null) {
                    OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
                    boolean showUplift = ConfigurationUtils.shouldShowUpLiftPrice();
                    for (OrderProduct choice : product.getRealChoices()) {
                        OrderProduct choiceSolution = ProductUtils.getActualChoice(choice.getSelection());
                        if (!(choiceSolution == null || choiceSolution.getProduct() == null)) {
                            if (!choice.isSingleChoice() || !ProductUtils.hideSingleChoice()) {
                                subElement = new OrderDetailsSubElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_sub_element, layout, false));
                                subElement.getTitle().setText(choiceSolution.getProduct().getName());
                                nameDetails = ProductUtils.getNameDetailsString(choiceSolution);
                                if (TextUtils.isEmpty(nameDetails)) {
                                    subElement.getDetails().setVisibility(8);
                                } else {
                                    subElement.getDetails().setText(nameDetails);
                                }
                                subElement.getInfo().setText(OrderProductUtils.getCustomizationsString(choiceSolution));
                                double uplift = ProductUtils.getProductTotalPrice(OrderProduct.getChoiceWithinChoiceProduct(choice)) - orderingModule.getProductBasePrice(choice);
                                if (showUplift && uplift >= 0.01d) {
                                    subElement.getPriceUplift().setVisibility(8);
                                    subElement.getPriceUplift().setText(String.format("+ %s", new Object[]{UIUtils.getLocalizedCurrencyFormatter().format(uplift)}));
                                }
                                layout.addView(subElement.getContainer());
                            }
                        }
                    }
                }
            } else if (com.mcdonalds.app.util.ListUtils.isNotEmpty(product.getRealChoices())) {
                StringBuilder builder = new StringBuilder("");
                builder.append(OrderProductUtils.getIngredientChoiceString(product));
                element.getCustomSpecialInstructions().setVisibility(0);
                element.getCustomSpecialInstructions().setText(builder);
            }
            addDashedDivider(context, layout);
        }
    }

    private static List<ProductView> getProductsFromOrderResponse(OrderResponse orderResponse, boolean IsOffer) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderReceipt", "getProductsFromOrderResponse", new Object[]{orderResponse, new Boolean(IsOffer)});
        ArrayList<ProductView> promotionalProducts = new ArrayList();
        if (IsOffer) {
            for (PromotionView promotionView : orderResponse.getOrderView().getPromotionalItems()) {
                addOrIncrement(promotionalProducts, promotionView.getProductSet());
            }
        } else if (orderResponse.getOrderView().getProducts() != null) {
            addOrIncrement(promotionalProducts, orderResponse.getOrderView().getProducts());
        }
        return promotionalProducts;
    }

    private static void addOrIncrement(ArrayList<ProductView> promotionalProducts, List<ProductView> productViews) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderReceipt", "addOrIncrement", new Object[]{promotionalProducts, productViews});
        for (ProductView productView : productViews) {
            if (promotionalProducts.contains(productView)) {
                ProductView existing = (ProductView) promotionalProducts.get(promotionalProducts.indexOf(productView));
                existing.setQuantity(Integer.valueOf(existing.getQuantity().intValue() + productView.getQuantity().intValue()));
                existing.setTotalValue(Double.valueOf(existing.getTotalValue().doubleValue() + productView.getTotalValue().doubleValue()));
            } else {
                promotionalProducts.add(productView);
            }
        }
    }

    private static void addDashedDivider(Context context, LinearLayout layout) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.summary.OrderReceipt", "addDashedDivider", new Object[]{context, layout});
        ImageView dashedLine = new ImageView(context);
        dashedLine.setImageResource(C2358R.C2359drawable.dashed_line_horiz);
        dashedLine.setLayoutParams(new LayoutParams(-1, UIUtils.dpAsPixels(context, 2)));
        layout.addView(dashedLine);
    }
}
