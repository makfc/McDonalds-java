package com.mcdonalds.app.ordering.choiceselector;

import com.mcdonalds.sdk.modules.models.OrderProduct;
import java.util.List;

public interface ChoiceSelectorView {
    void cancel();

    void finalize(OrderProduct orderProduct, int i, int i2);

    void setDoneEnabled(boolean z);

    void setSelected(int i);

    void showOptions(List<OrderProduct> list, List<Integer> list2);

    void updateCustomization(int i);
}
