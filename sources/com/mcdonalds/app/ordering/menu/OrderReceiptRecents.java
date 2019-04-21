package com.mcdonalds.app.ordering.menu;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.ordering.ProductUtils;
import com.mcdonalds.app.ordering.summary.OrderDetailsElement;
import com.mcdonalds.app.ordering.summary.OrderDetailsSubElement;
import com.mcdonalds.app.util.AppUtils;
import com.mcdonalds.app.util.ConfigurationUtils;
import com.mcdonalds.app.util.ListUtils;
import com.mcdonalds.app.util.OrderProductUtils;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import java.text.NumberFormat;

public class OrderReceiptRecents {
    public static void configureOrderReceiptForDisplay(Order order, Context context, LinearLayout layout, LayoutInflater inflater) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderReceiptRecents", "configureOrderReceiptForDisplay", new Object[]{order, context, layout, inflater});
        NumberFormat currencyFormatter = UIUtils.getLocalizedCurrencyFormatter();
        boolean showPrice = Configuration.getSharedInstance().getBooleanForKey("interface.willDisplayPriceDetails");
        boolean showUplift = ConfigurationUtils.shouldShowUpLiftPrice();
        View receiptHeader = (RelativeLayout) inflater.inflate(C2658R.layout.order_details_title, layout, false);
        updateFavoriteName(order, (TextView) receiptHeader.findViewById(2131820647), (TextView) receiptHeader.findViewById(C2358R.C2357id.subtitle), context);
        layout.addView(receiptHeader);
        addDashedDivider(context, layout);
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        Store currentStore = OrderManager.getInstance().getCurrentStore();
        for (OrderProduct product : order.getProducts()) {
            if (!(currentStore.isBagChargeEnabled() && (currentStore.getBagProductCode() == Integer.parseInt(product.getProductCode()) || currentStore.getNoBagProductCode() == Integer.parseInt(product.getProductCode())))) {
                OrderDetailsElement element = new OrderDetailsElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_bordered_label, layout, false));
                element.getTopBorder().setVisibility(8);
                element.getBottomBorder().setVisibility(8);
                element.getQuantity().setText(String.valueOf(product.getQuantity()));
                element.getTitle().setText(product.getProduct().getLongName());
                if (AppUtils.hideNutritionOnOrderingPages()) {
                    element.getInfo().setVisibility(8);
                } else {
                    element.getInfo().setText(AppUtils.getEnergyTextForOrderProduct(product, OrderProductUtils.getTotalEnergyUnit(product)));
                }
                if (showPrice) {
                    String productPriceString = currencyFormatter.format(ProductUtils.getProductTotalPrice(product));
                    element.getPrice().setText(String.format("%s*", new Object[]{productPriceString}));
                }
                layout.addView(element.getContainer());
                StringBuilder stringBuilder = new StringBuilder("");
                if (product.isMeal()) {
                    OrderDetailsSubElement orderDetailsSubElement;
                    if (product.getIngredients() != null) {
                        for (OrderProduct ingredient : product.getIngredients()) {
                            if (ingredient != null) {
                                StringBuilder builder = new StringBuilder("");
                                orderDetailsSubElement = new OrderDetailsSubElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_sub_element, layout, false));
                                orderDetailsSubElement.getTitle().setText(ingredient.getProduct().getName());
                                builder.append(OrderProductUtils.getCustomizationsString(ingredient));
                                if (builder.length() == 0 && ListUtils.isNotEmpty(product.getRealChoices())) {
                                    builder = OrderProductUtils.getIngredientChoiceString(ingredient);
                                }
                                if (builder.length() != 0) {
                                    orderDetailsSubElement.getDetails().setText(builder);
                                } else {
                                    orderDetailsSubElement.getDetails().setVisibility(8);
                                }
                                layout.addView(orderDetailsSubElement.getContainer());
                            }
                        }
                    }
                    if (product.getRealChoices() != null) {
                        for (OrderProduct choice : product.getRealChoices()) {
                            OrderProduct choiceSolution = OrderProduct.getChoiceWithinChoiceProduct(choice.getSelection());
                            orderDetailsSubElement = new OrderDetailsSubElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_sub_element, layout, false));
                            if (choiceSolution != null) {
                                if (choiceSolution.getProduct() != null) {
                                    orderDetailsSubElement.getTitle().setText(choiceSolution.getProduct().getName());
                                }
                                String customizationStiong = OrderProductUtils.getCustomizationsString(choiceSolution);
                                if (TextUtils.isEmpty(customizationStiong)) {
                                    orderDetailsSubElement.getInfo().setVisibility(8);
                                } else {
                                    orderDetailsSubElement.getInfo().setText(customizationStiong);
                                }
                                if (ListUtils.isNotEmpty(choiceSolution.getRealChoices())) {
                                    for (Choice subChoice : choiceSolution.getRealChoices()) {
                                        if (subChoice != null) {
                                            OrderProduct subChoiceSolution = OrderProduct.getChoiceWithinChoiceProduct(subChoice.getSelection());
                                            if (subChoiceSolution != null) {
                                                orderDetailsSubElement.getDetails().setText(subChoiceSolution.getProduct().getName());
                                            }
                                        }
                                    }
                                }
                                if (showPrice) {
                                    double uplift = ProductUtils.getProductTotalPrice(choiceSolution) - orderingModule.getProductBasePrice(choice);
                                    if (showUplift && uplift > 0.0d) {
                                        orderDetailsSubElement.getPriceUplift().setVisibility(0);
                                        orderDetailsSubElement.getPriceUplift().setText(String.format("+ %s", new Object[]{UIUtils.getLocalizedCurrencyFormatter().format(uplift)}));
                                    }
                                }
                                layout.addView(orderDetailsSubElement.getContainer());
                            }
                        }
                    }
                } else if (ListUtils.isNotEmpty(product.getRealChoices())) {
                    stringBuilder.append(OrderProductUtils.getIngredientChoiceString(product));
                }
                if (product.getCustomizations() != null) {
                    stringBuilder = stringBuilder;
                    stringBuilder.append("\n" + OrderProductUtils.getCustomizationsString(product));
                }
                if (stringBuilder.length() > 1) {
                    element.getCustomSpecialInstructions().setVisibility(0);
                    element.getCustomSpecialInstructions().setText(stringBuilder);
                }
                addDashedDivider(context, layout);
            }
        }
        OrderDetailsElement orderDetailsElement = new OrderDetailsElement((RelativeLayout) inflater.inflate(C2658R.layout.order_details_bordered_label, layout, false));
        orderDetailsElement.getTopBorder().setVisibility(8);
        orderDetailsElement.getBottomBorder().setVisibility(8);
        orderDetailsElement.getQuantity().setVisibility(4);
        orderDetailsElement.getInfo().setVisibility(8);
        if (showPrice) {
            orderDetailsElement.getTitle().setText(C2658R.string.order_total_label);
            orderDetailsElement.getPrice().setText(currencyFormatter.format(order.getTotalValue()) + "*");
        }
        layout.addView(orderDetailsElement.getContainer());
    }

    private static void addDashedDivider(Context context, LinearLayout layout) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderReceiptRecents", "addDashedDivider", new Object[]{context, layout});
        if (context != null) {
            ImageView dashedLine = new ImageView(context);
            dashedLine.setImageResource(C2358R.C2359drawable.dashed_line_horiz);
            LayoutParams params = new LayoutParams(-1, UIUtils.dpAsPixels(context, 3));
            params.setMargins(UIUtils.dpAsPixels(context, 3), 0, UIUtils.dpAsPixels(context, 3), 0);
            dashedLine.setLayoutParams(params);
            layout.addView(dashedLine);
        }
    }

    public static void updateFavoriteName(Order order, TextView title, TextView subTitle, Context context) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.OrderReceiptRecents", "updateFavoriteName", new Object[]{order, title, subTitle, context});
        if (order.getOrderNumber() != null && context != null) {
            title.setText(String.format(context.getString(C2658R.string.order_details_number_label), new Object[]{order.getOrderNumber()}));
            if (order.getFavoriteName() != null) {
                subTitle.setText(order.getFavoriteName());
            } else {
                subTitle.setText(order.getRecentName());
            }
        } else if (order.getFavoriteName() != null) {
            title.setText(order.getFavoriteName());
            subTitle.setText(order.getRecentName());
        } else {
            title.setText(order.getRecentName());
            subTitle.setText("");
        }
    }
}
