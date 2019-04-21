package com.mcdonalds.app.nutrition;

import com.mcdonalds.sdk.modules.models.Order;
import java.util.List;

public interface GridListener {
    void orderListAvailable(List<Order> list);
}
