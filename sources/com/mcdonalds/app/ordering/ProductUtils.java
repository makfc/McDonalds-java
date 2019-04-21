package com.mcdonalds.app.ordering;

import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.SparseArray;
import com.ensighten.Ensighten;
import com.mcdonalds.app.util.ServiceUtils;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.ModuleManager;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.AdvertisablePromotion;
import com.mcdonalds.sdk.modules.models.Choice;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.Ingredient;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.ordering.OrderManager;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.mcdonalds.sdk.modules.storelocator.Store;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.utils.ListUtils;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ProductUtils {
    private static Comparator<Ingredient> INGREDIENT_COMPARATOR = new C24061();

    /* renamed from: com.mcdonalds.app.ordering.ProductUtils$1 */
    static class C24061 implements Comparator<Ingredient> {
        C24061() {
        }

        public int compare(Ingredient lhs, Ingredient rhs) {
            Ensighten.evaluateEvent(this, "compare", new Object[]{lhs, rhs});
            return lhs.getDisplayOrder() - rhs.getDisplayOrder();
        }
    }

    /* renamed from: com.mcdonalds.app.ordering.ProductUtils$3 */
    static class C24083 implements AsyncListener<List<AdvertisablePromotion>> {
        final /* synthetic */ OrderProduct val$baseOrderProduct;
        final /* synthetic */ AsyncListener val$listener;
        final /* synthetic */ int val$productCode;

        public void onResponse(List<AdvertisablePromotion> promotions, AsyncToken token, AsyncException exception) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{promotions, token, exception});
            AdvertisablePromotion advertisableDeal = null;
            if (!ListUtils.isEmpty(promotions)) {
                for (AdvertisablePromotion promotion : promotions) {
                    if (promotion.getBaseProductId() == this.val$productCode) {
                        if (promotion.isValidForToday()) {
                            advertisableDeal = promotion;
                        }
                    } else if (promotion.getSwapProductId() == this.val$productCode) {
                        if (!promotion.isValidForToday()) {
                            ProductUtils.getBaseOrderProductFromAdvertisable(this.val$baseOrderProduct, this.val$listener);
                            return;
                        }
                    }
                }
            }
            if (advertisableDeal != null) {
                Product product = ((OrderingModule) ModuleManager.getModule("ordering")).getProductForExternalId(String.valueOf(advertisableDeal.getSwapProductId()), false);
                if (product != null) {
                    product.setAdvertisablePromotion(advertisableDeal);
                    OrderProduct newOrderProduct = OrderProduct.createProduct(product, Integer.valueOf(this.val$baseOrderProduct.getQuantity()));
                    ProductUtils.access$000(this.val$baseOrderProduct, newOrderProduct);
                    this.val$listener.onResponse(newOrderProduct, null, null);
                    return;
                }
            }
            this.val$listener.onResponse(this.val$baseOrderProduct, null, null);
        }
    }

    static /* synthetic */ void access$000(OrderProduct x0, OrderProduct x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "access$000", new Object[]{x0, x1});
        setOptionsToNewProduct(x0, x1);
    }

    public static void populateProductChoices(OrderProduct orderProduct, OrderingModule orderingModule) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "populateProductChoices", new Object[]{orderProduct, orderingModule});
        if (ListUtils.isEmpty(orderProduct.getRealChoices())) {
            List<Ingredient> choices = orderProduct.getProduct().getChoices();
            if (ListUtils.isEmpty(choices)) {
                choices = orderingModule.getProductChoices(orderProduct.getProduct());
            }
            if (choices != null) {
                Collections.sort(choices, INGREDIENT_COMPARATOR);
                orderProduct.getProduct().setChoices(choices);
                List<Choice> choiceProducts = new ArrayList();
                int size = choices.size();
                for (int i = 0; i < size; i++) {
                    Ingredient choice = (Ingredient) choices.get(i);
                    for (int qty = 0; qty < choice.getDefaultQuantity(); qty++) {
                        choiceProducts.add(getChoice(choice, orderingModule));
                    }
                }
                orderProduct.setRealChoices(choiceProducts);
            }
        }
    }

    public static void populateProductIngredients(OrderProduct orderProduct, OrderingModule orderingModule) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "populateProductIngredients", new Object[]{orderProduct, orderingModule});
        List<Ingredient> ingredients = orderProduct.getProduct().getIngredients();
        if (ListUtils.isEmpty(ingredients)) {
            ingredients = orderingModule.getProductIngredients(orderProduct.getProduct());
        }
        if (ingredients != null) {
            Collections.sort(ingredients, INGREDIENT_COMPARATOR);
            orderProduct.getProduct().setIngredients(ingredients);
            if (ListUtils.isEmpty(orderProduct.getIngredients())) {
                orderProduct.setIngredients(getOrderProducts(ingredients, orderingModule));
            }
        }
    }

    public static double getProductTotalPrice(OrderProduct product) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "getProductTotalPrice", new Object[]{product});
        OrderManager orderManager = OrderManager.getInstance();
        return product.getTotalPrice(orderManager.getCurrentOrder().getPriceType(), orderManager.allowDownCharge());
    }

    private static List<OrderProduct> getOrderProducts(List<Ingredient> ingredients, OrderingModule orderingModule) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "getOrderProducts", new Object[]{ingredients, orderingModule});
        List<OrderProduct> orderProductsIngredients = new ArrayList();
        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                orderProductsIngredients.add(getOrderProduct(ingredient, orderingModule));
            }
        }
        return orderProductsIngredients;
    }

    private static OrderProduct getOrderProduct(Ingredient ingredient, OrderingModule orderingModule) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "getOrderProduct", new Object[]{ingredient, orderingModule});
        return OrderProduct.createProduct(ingredient, Integer.valueOf(1));
    }

    private static Choice getChoice(Ingredient ingredient, OrderingModule orderingModule) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "getChoice", new Object[]{ingredient, orderingModule});
        return Choice.createChoice(ingredient, Integer.valueOf(1));
    }

    public static boolean hideSingleChoice() {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "hideSingleChoice", null);
        return Configuration.getSharedInstance().getBooleanForKey("interface.ordering.hideSingleChoiceSolutions");
    }

    public static void createOrderProduct(final CustomerOrderProduct customerOrderProduct, final OrderingModule orderingModule, final AsyncListener<OrderProduct> listener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "createOrderProduct", new Object[]{customerOrderProduct, orderingModule, listener});
        C24072 c24072 = new TraceFieldInterface() {
            public Trace _nr_trace;

            public void _nr_setTrace(Trace trace) {
                try {
                    this._nr_trace = trace;
                } catch (Exception e) {
                }
            }

            /* Access modifiers changed, original: protected|varargs */
            public OrderProduct doInBackground(Void... params) {
                Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
                return ProductUtils.createOrderProduct(customerOrderProduct, orderingModule);
            }

            /* Access modifiers changed, original: protected */
            public void onPostExecute(OrderProduct product) {
                Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{product});
                listener.onResponse(product, null, null);
            }
        };
        Void[] voidArr = new Void[0];
        if (c24072 instanceof AsyncTask) {
            AsyncTaskInstrumentation.execute(c24072, voidArr);
        } else {
            c24072.execute(voidArr);
        }
    }

    public static OrderProduct createOrderProduct(CustomerOrderProduct customerOrderProduct, OrderingModule orderingModule) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "createOrderProduct", new Object[]{customerOrderProduct, orderingModule});
        Product mainProduct = orderingModule.getProductForExternalId(customerOrderProduct.getProductCode().toString(), false);
        if (mainProduct == null) {
            return null;
        }
        OrderProduct newOrderProduct = OrderProduct.createProduct(mainProduct, customerOrderProduct.getQuantity());
        applyCustomizationsAndChoicesToProduct(newOrderProduct, customerOrderProduct, orderingModule);
        return newOrderProduct;
    }

    private static void addAllComponentProductsToSparseArray(SparseArray<OrderProduct> ingredientSparseArray, List<OrderProduct> ingredientList) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "addAllComponentProductsToSparseArray", new Object[]{ingredientSparseArray, ingredientList});
        if (ingredientList != null) {
            for (OrderProduct ingredient : ingredientList) {
                ingredientSparseArray.put(ingredient.getProduct().getExternalId().intValue(), ingredient);
            }
        }
    }

    private static void applyCustomizationsAndChoicesToProduct(OrderProduct product, CustomerOrderProduct customerOrderProduct, OrderingModule orderingModule) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "applyCustomizationsAndChoicesToProduct", new Object[]{product, customerOrderProduct, orderingModule});
        if (product != null) {
            SparseArray<OrderProduct> componentsArray = new SparseArray();
            addAllComponentProductsToSparseArray(componentsArray, product.getIngredients());
            if (!ListUtils.isEmpty(customerOrderProduct.getComponents())) {
                for (CustomerOrderProduct componentCustomerProduct : customerOrderProduct.getComponents()) {
                    OrderProduct componentOrderProduct = (OrderProduct) componentsArray.get(componentCustomerProduct.getProductCode().intValue());
                    if (componentOrderProduct != null) {
                        applyCustomizationsAndChoicesToProduct(componentOrderProduct, componentCustomerProduct, orderingModule);
                    }
                }
            }
            if (!ListUtils.isEmpty(customerOrderProduct.getCustomizations())) {
                for (CustomerOrderProduct customizationCustomerProduct : customerOrderProduct.getCustomizations()) {
                    OrderProduct customizationOrderProduct = (OrderProduct) componentsArray.get(customizationCustomerProduct.getProductCode().intValue());
                    if (customizationOrderProduct != null) {
                        applyCustomizationsAndChoicesToProduct(customizationOrderProduct, customizationCustomerProduct, orderingModule);
                        product.getCustomizations().put(customizationOrderProduct.getProduct().getExternalId(), customizationOrderProduct);
                    }
                }
            }
            if (!ListUtils.isEmpty(customerOrderProduct.getChoices())) {
                addChoicesToOrderProduct(product, customerOrderProduct.getChoices(), orderingModule);
            }
            if (customerOrderProduct.getQuantity() != null) {
                product.setQuantity(customerOrderProduct.getQuantity().intValue());
            }
            if (customerOrderProduct.getIsLight() != null) {
                product.setIsLight(customerOrderProduct.getIsLight().booleanValue());
            }
            if (customerOrderProduct.getPromoQuantity() != null) {
                product.setPromoQuantity(customerOrderProduct.getPromoQuantity().intValue());
            }
        }
    }

    @VisibleForTesting
    protected static void addChoicesToOrderProduct(OrderProduct orderProduct, List<CustomerOrderProduct> choices, OrderingModule orderingModule) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "addChoicesToOrderProduct", new Object[]{orderProduct, choices, orderingModule});
        if (orderProduct != null && choices != null && !choices.isEmpty()) {
            List<Choice> orderProductChoices = orderProduct.getRealChoices();
            if (!ListUtils.isEmpty(orderProductChoices) && !ListUtils.isEmpty(choices) && orderProductChoices.size() == choices.size()) {
                int size = choices.size();
                for (int ii = 0; ii < size; ii++) {
                    addChoiceToOrderProduct(orderProduct, (Choice) orderProductChoices.get(ii), (CustomerOrderProduct) choices.get(ii), ii, orderingModule);
                }
            }
        }
    }

    @VisibleForTesting
    protected static void addChoiceToOrderProduct(OrderProduct parentProduct, OrderProduct parentChoiceProduct, CustomerOrderProduct choice, int idx, OrderingModule orderingModule) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "addChoiceToOrderProduct", new Object[]{parentProduct, parentChoiceProduct, choice, new Integer(idx), orderingModule});
        if ((parentChoiceProduct instanceof Choice) && choice.getChoiceSolution() != null) {
            OrderProduct choiceSolutionOrderProduct;
            OrderProduct productToCustomize;
            CustomerOrderProduct choiceSolutionCustomerProduct;
            Choice parentChoice = (Choice) parentChoiceProduct;
            if (choice.getChoiceSolution().getProductCode().intValue() != 0 || ListUtils.isEmpty(choice.getChoiceSolution().getChoices())) {
                choiceSolutionOrderProduct = getIngredientOrderProductForProductId(parentProduct, idx, choice.getChoiceSolution().getProductCode().intValue());
                productToCustomize = choiceSolutionOrderProduct;
                choiceSolutionCustomerProduct = choice.getChoiceSolution();
            } else {
                CustomerOrderProduct customerChoiceWithinChoice = (CustomerOrderProduct) choice.getChoiceSolution().getChoices().get(0);
                parentChoice.setQuantity(choice.getQuantity().intValue());
                OrderProduct choiceWithinChoice = null;
                Product parentChoiceIngredientProduct = ((Ingredient) parentProduct.getProduct().getChoices().get(idx)).getProduct();
                if (parentChoiceIngredientProduct.hasChoice().booleanValue()) {
                    for (Ingredient ingredient : parentChoiceIngredientProduct.getChoices()) {
                        if (ingredient.getProduct().getExternalId().equals(customerChoiceWithinChoice.getProductCode())) {
                            Choice choiceWithinChoice2 = Choice.createChoice(ingredient, customerChoiceWithinChoice.getQuantity());
                        }
                    }
                }
                if (choiceWithinChoice != null) {
                    OrderProduct choiceSolutionProduct = createOrderProduct(customerChoiceWithinChoice.getChoiceSolution(), orderingModule);
                    choiceWithinChoice.setSelection(choiceSolutionProduct);
                    choiceSolutionOrderProduct = choiceWithinChoice;
                    productToCustomize = choiceSolutionProduct;
                    choiceSolutionCustomerProduct = customerChoiceWithinChoice.getChoiceSolution();
                } else {
                    return;
                }
            }
            applyCustomizationsAndChoicesToProduct(productToCustomize, choiceSolutionCustomerProduct, orderingModule);
            parentChoice.setSelection(choiceSolutionOrderProduct);
        }
    }

    private static OrderProduct getIngredientOrderProductForProductId(OrderProduct parent, int idx, int productCode) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "getIngredientOrderProductForProductId", new Object[]{parent, new Integer(idx), new Integer(productCode)});
        for (OrderProduct ingredient : ((Choice) parent.getRealChoices().get(idx)).getOptions()) {
            if (ingredient.getProduct().getExternalId().equals(Integer.valueOf(productCode))) {
                return ingredient;
            }
        }
        return null;
    }

    @Deprecated
    public static void getBaseOrderProductFromAdvertisable(final OrderProduct orderProduct, final AsyncListener<OrderProduct> listener) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "getBaseOrderProductFromAdvertisable", new Object[]{orderProduct, listener});
        if (orderProduct == null) {
            listener.onResponse(orderProduct, null, null);
            return;
        }
        final int productCode = Integer.parseInt(orderProduct.getProductCode());
        CustomerModule customerModule = (CustomerModule) ModuleManager.getModule(CustomerModule.NAME);
        String userName = (customerModule == null || !customerModule.isLoggedIn()) ? "" : customerModule.getCurrentProfile().getUserName();
        Store store = OrderManager.getInstance().getCurrentStore();
        if (store != null) {
            ServiceUtils.getSharedInstance().retrieveAdvertisablePromotions(userName, store.getStoreId(), new AsyncListener<List<AdvertisablePromotion>>() {
                public void onResponse(List<AdvertisablePromotion> promotions, AsyncToken token, AsyncException exception) {
                    Ensighten.evaluateEvent(this, "onResponse", new Object[]{promotions, token, exception});
                    if (!ListUtils.isEmpty(promotions)) {
                        for (AdvertisablePromotion advertisableDeal : promotions) {
                            if (advertisableDeal.getSwapProductId() == productCode) {
                                Product product = ((OrderingModule) ModuleManager.getModule("ordering")).getProductForExternalId(String.valueOf(advertisableDeal.getBaseProductId()), false);
                                product.setAdvertisablePromotion(advertisableDeal);
                                OrderProduct newOrderProduct = OrderProduct.createProduct(product, Integer.valueOf(1));
                                ProductUtils.access$000(orderProduct, newOrderProduct);
                                listener.onResponse(newOrderProduct, null, null);
                                break;
                            }
                        }
                    }
                    listener.onResponse(orderProduct, null, null);
                }
            });
        } else {
            listener.onResponse(orderProduct, null, null);
        }
    }

    private static void setOptionsToNewProduct(OrderProduct baseProduct, OrderProduct swapProduct) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "setOptionsToNewProduct", new Object[]{baseProduct, swapProduct});
        swapProduct.setRealChoices(baseProduct.getRealChoices());
        swapProduct.setIngredients(baseProduct.getIngredients());
        Map<Integer, OrderProduct> customizations = baseProduct.getCustomizations();
        for (Integer key : customizations.keySet()) {
            swapProduct.addCustomization(key, (OrderProduct) customizations.get(key));
        }
    }

    public static boolean hasSubChoice(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "hasSubChoice", new Object[]{orderProduct});
        if (orderProduct.isChoice()) {
            if (orderProduct.isSingleChoice() && hideSingleChoice()) {
                return false;
            }
            return true;
        } else if (orderProduct.getProduct().hasChoice().booleanValue()) {
            return checkForSingleChoiceItems(orderProduct);
        } else {
            return false;
        }
    }

    public static boolean checkForSingleChoiceItems(OrderProduct orderProduct) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "checkForSingleChoiceItems", new Object[]{orderProduct});
        return orderProduct.getProduct().hasNonSingleChoiceChoice().booleanValue() || !hideSingleChoice();
    }

    public static String getNameDetailsString(OrderProduct ingredient) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "getNameDetailsString", new Object[]{ingredient});
        ArrayList<String> productNames = new ArrayList();
        if (!hasSubChoice(ingredient)) {
            return "";
        }
        boolean hideSingleChoice = hideSingleChoice();
        if (!ListUtils.isEmpty(ingredient.getRealChoices())) {
            for (Choice choice : ingredient.getRealChoices()) {
                if (!(choice == null || (choice.isSingleChoice() && hideSingleChoice))) {
                    OrderProduct selection = getActualChoice(choice.getSelection());
                    if (selection != null) {
                        productNames.add(selection.getProduct().getName());
                    }
                }
            }
        }
        if (productNames.size() == 0) {
            return "";
        }
        return TextUtils.join(", ", productNames);
    }

    public static boolean productHasIngredientsOrExtras(OrderProduct ingredient) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "productHasIngredientsOrExtras", new Object[]{ingredient});
        OrderingModule orderingModule = (OrderingModule) ModuleManager.getModule("ordering");
        Product product = ingredient.getProduct();
        if (ListUtils.isEmpty(product.getIngredients())) {
            product.setIngredients(orderingModule.getProductIngredients(product));
        }
        if (ListUtils.isEmpty(product.getExtras())) {
            product.setExtras(orderingModule.getProductExtras(product));
        }
        if (ListUtils.isEmpty(product.getIngredients()) && ListUtils.isEmpty(product.getExtras())) {
            return false;
        }
        return true;
    }

    public static OrderProduct getActualChoice(OrderProduct product) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.ProductUtils", "getActualChoice", new Object[]{product});
        if (product == null) {
            return null;
        }
        if (!(product instanceof Choice)) {
            return product;
        }
        Choice choice = (Choice) product;
        return choice.getSelection() != null ? getActualChoice(choice.getSelection()) : null;
    }
}
