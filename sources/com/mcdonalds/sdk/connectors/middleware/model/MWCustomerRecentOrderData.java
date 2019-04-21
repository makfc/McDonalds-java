package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.CustomerOrder;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import java.util.ArrayList;
import java.util.List;

public class MWCustomerRecentOrderData {
    @SerializedName("Data")
    public List<MWProductView> data;
    @SerializedName("IsFinalized")
    private boolean isFinalized;
    @SerializedName("Name")
    public String name;
    @SerializedName("DisplayOrderNumber")
    private String orderNumber;
    @SerializedName("RecentOrderID")
    public int recentOrderID;
    @SerializedName("ServiceMode")
    private int serviceMode;

    public static CustomerOrder toCustomerOrder(MWCustomerRecentOrderData ecpRecentOrder) {
        CustomerOrder ret = new CustomerOrder();
        ret.setOrderId(Integer.valueOf(ecpRecentOrder.recentOrderID));
        ret.setName(ecpRecentOrder.name);
        ret.setOrderNumber(ecpRecentOrder.orderNumber);
        ret.setServiceMode(Integer.valueOf(ecpRecentOrder.serviceMode));
        ret.setFinalized(ecpRecentOrder.isFinalized);
        List<CustomerOrderProduct> products = new ArrayList();
        if (ecpRecentOrder.data != null) {
            for (MWProductView productView : ecpRecentOrder.data) {
                products.add(productView.toCustomerOrderProduct());
            }
        }
        ret.setProducts(products);
        return ret;
    }

    public static List<CustomerOrder> toCustomerOrderList(List<MWCustomerRecentOrderData> ecpRecentOrders) {
        List<CustomerOrder> customerOrders = new ArrayList();
        int size = ecpRecentOrders.size();
        for (int i = 0; i < size; i++) {
            MWCustomerRecentOrderData ecpOrder = (MWCustomerRecentOrderData) ecpRecentOrders.get(i);
            if (ecpOrder.data != null) {
                customerOrders.add(toCustomerOrder(ecpOrder));
            }
        }
        return customerOrders;
    }
}
