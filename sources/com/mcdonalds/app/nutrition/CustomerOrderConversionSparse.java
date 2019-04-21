package com.mcdonalds.app.nutrition;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.SparseArray;
import com.ensighten.Ensighten;
import com.mcdonalds.app.util.UIUtils;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.connectors.utils.SerializableSparseArray;
import com.mcdonalds.sdk.modules.customer.CustomerModule;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.Order;
import com.mcdonalds.sdk.modules.models.OrderProduct;
import com.mcdonalds.sdk.modules.models.Product;
import com.mcdonalds.sdk.modules.ordering.OrderingModule;
import com.newrelic.agent.android.api.p047v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderConversionSparse {
    Activity mActivity;
    CustomerModule mCustomerModule;
    GridListener mGridListener;
    OrderingModule mOrderingModule;
    List<Order> mOrders;
    private SparseArray<Product> recipeLookup;

    private class PopulateIndices extends AsyncTask<List<CustomerOrder>, Void, Void> implements TraceFieldInterface {
        public Trace _nr_trace;
        final /* synthetic */ CustomerOrderConversionSparse this$0;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        /* Access modifiers changed, original: protected|varargs */
        public Void doInBackground(List<CustomerOrder>... params) {
            Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
            for (CustomerOrder customerOrder : params[0]) {
                Order order = new Order();
                order.setProfile(this.this$0.mCustomerModule.getCurrentProfile());
                for (CustomerOrderProduct customerProductForOrder : customerOrder.getProducts()) {
                    order.addProduct(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.CustomerOrderConversionSparse", "access$100", new Object[]{this.this$0, customerProductForOrder}));
                }
                this.this$0.mOrders.add(order);
            }
            return null;
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(Void aVoid) {
            Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{aVoid});
            super.onPostExecute(aVoid);
            for (int i = 0; i < Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.CustomerOrderConversionSparse", "access$200", new Object[]{this.this$0}).size(); i++) {
                int done;
                int key = Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.CustomerOrderConversionSparse", "access$200", new Object[]{this.this$0}).keyAt(i);
                final int index = i;
                if (i == Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.CustomerOrderConversionSparse", "access$200", new Object[]{this.this$0}).size() - 1) {
                    done = 1;
                } else {
                    done = 0;
                }
                this.this$0.mOrderingModule.getProductForExternalId(String.valueOf(key), new AsyncListener<Product>() {
                    public void onResponse(Product response, AsyncToken token, AsyncException exception) {
                        Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, token, exception});
                        if (exception == null && PopulateIndices.this.this$0.mActivity != null) {
                            Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.CustomerOrderConversionSparse", "access$200", new Object[]{PopulateIndices.this.this$0}).setValueAt(index, response);
                            if (done == 1) {
                                PopulateOrdersWithRecipes populateOrdersWithRecipes = new PopulateOrdersWithRecipes();
                                Void[] voidArr = new Void[0];
                                if (populateOrdersWithRecipes instanceof AsyncTask) {
                                    AsyncTaskInstrumentation.execute(populateOrdersWithRecipes, voidArr);
                                } else {
                                    populateOrdersWithRecipes.execute(voidArr);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    public class PopulateOrdersWithRecipes extends AsyncTask<Void, Void, Void> implements TraceFieldInterface {
        public Trace _nr_trace;

        public void _nr_setTrace(Trace trace) {
            try {
                this._nr_trace = trace;
            } catch (Exception e) {
            }
        }

        /* Access modifiers changed, original: protected|varargs */
        public Void doInBackground(Void... params) {
            Ensighten.evaluateEvent(this, "doInBackground", new Object[]{params});
            for (Order order : CustomerOrderConversionSparse.this.mOrders) {
                for (OrderProduct productForOrder : order.getProducts()) {
                    if (productForOrder.getProductCode() != null) {
                        productForOrder.setProduct((Product) Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.CustomerOrderConversionSparse", "access$200", new Object[]{CustomerOrderConversionSparse.this}).get(Integer.valueOf(productForOrder.getProductCode()).intValue()));
                        for (OrderProduct ingredient : productForOrder.getIngredients()) {
                            if (ingredient.getProductCode() != null) {
                                ingredient.setProduct((Product) Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.CustomerOrderConversionSparse", "access$200", new Object[]{CustomerOrderConversionSparse.this}).get(Integer.valueOf(ingredient.getProductCode()).intValue()));
                            }
                            SerializableSparseArray<OrderProduct> choiceSolutions = productForOrder.getChoiceSolutions();
                            for (int l = 0; l < choiceSolutions.size(); l++) {
                                int key = choiceSolutions.keyAt(l);
                                if (((OrderProduct) choiceSolutions.valueAt(key)).getProductCode() != null) {
                                    ((OrderProduct) choiceSolutions.valueAt(key)).setProduct((Product) Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.CustomerOrderConversionSparse", "access$200", new Object[]{CustomerOrderConversionSparse.this}).get(Integer.valueOf(((OrderProduct) choiceSolutions.valueAt(key)).getProductCode()).intValue()));
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(Void aVoid) {
            Ensighten.evaluateEvent(this, "onPostExecute", new Object[]{aVoid});
            super.onPostExecute(aVoid);
            UIUtils.stopActivityIndicator();
            if (CustomerOrderConversionSparse.this.mGridListener != null) {
                CustomerOrderConversionSparse.this.mGridListener.orderListAvailable(CustomerOrderConversionSparse.this.mOrders);
            }
        }
    }

    private class RecipeIndex {
    }

    private OrderProduct customerOrderProductToOrderProduct(CustomerOrderProduct customerOrderProduct) {
        Ensighten.evaluateEvent(this, "customerOrderProductToOrderProduct", new Object[]{customerOrderProduct});
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductCode(customerOrderProduct.getProductCode().toString());
        orderProduct.setQuantity(customerOrderProduct.getQuantity().intValue());
        orderProduct.setIsLight(customerOrderProduct.getIsLight().booleanValue());
        orderProduct.setPromoQuantity(customerOrderProduct.getPromoQuantity().intValue());
        if (this.recipeLookup.indexOfKey(Integer.valueOf(orderProduct.getProductCode()).intValue()) > 0) {
            this.recipeLookup.append(Integer.valueOf(orderProduct.getProductCode()).intValue(), null);
        }
        int i = 0;
        List<CustomerOrderProduct> customerChoices = customerOrderProduct.getChoices();
        if (customerChoices == null) {
            customerOrderProduct.setChoices(new ArrayList());
        } else {
            for (CustomerOrderProduct customerChoice : customerChoices) {
                orderProduct.addChoice(customerOrderProductToOrderProduct(customerChoice));
                if (customerChoice.getChoiceSolution() != null) {
                    orderProduct.getChoiceSolutions().append(i, customerOrderProductToOrderProduct(customerChoice.getChoiceSolution()));
                }
                i++;
            }
        }
        List<CustomerOrderProduct> components = customerOrderProduct.getComponents();
        if (components == null) {
            customerOrderProduct.setComponents(new ArrayList());
        } else {
            for (CustomerOrderProduct component : components) {
                orderProduct.addIngredient(customerOrderProductToOrderProduct(component));
            }
        }
        if (customerOrderProduct.getCustomizations() == null) {
            customerOrderProduct.setCustomizations(new ArrayList());
        } else {
            for (CustomerOrderProduct customization : customerOrderProduct.getCustomizations()) {
                orderProduct.addCustomization(customization.getProductCode(), customerOrderProductToOrderProduct(customization));
            }
        }
        return orderProduct;
    }
}
