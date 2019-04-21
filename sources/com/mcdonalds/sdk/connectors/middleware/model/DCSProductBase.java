package com.mcdonalds.sdk.connectors.middleware.model;

import com.google.gson.annotations.SerializedName;
import com.mcdonalds.sdk.modules.models.CustomerOrderProduct;
import com.mcdonalds.sdk.modules.models.OrderProduct;

public class DCSProductBase {
    @SerializedName("ProductCode")
    public int productCode;
    @SerializedName("Quantity")
    public int quantity;

    public void populate(CustomerOrderProduct customerOrderProduct) {
        this.productCode = customerOrderProduct.getProductCode().intValue();
        this.quantity = customerOrderProduct.getQuantity().intValue();
    }

    public void populate(OrderProduct orderProduct) {
        this.productCode = Integer.parseInt(orderProduct.getProductCode());
        this.quantity = orderProduct.getQuantity();
    }

    public CustomerOrderProduct toCustomerOrderProduct() {
        CustomerOrderProduct customerOrderProduct = new CustomerOrderProduct();
        customerOrderProduct.setProductCode(Integer.valueOf(this.productCode));
        customerOrderProduct.setQuantity(Integer.valueOf(this.quantity));
        return customerOrderProduct;
    }

    public OrderProduct toOrderProduct() {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductCode(String.valueOf(this.productCode));
        orderProduct.setQuantity(this.quantity);
        return orderProduct;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DCSProductBase that = (DCSProductBase) o;
        if (this.productCode != that.productCode) {
            return false;
        }
        if (this.quantity != that.quantity) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.productCode * 31) + this.quantity;
    }
}
