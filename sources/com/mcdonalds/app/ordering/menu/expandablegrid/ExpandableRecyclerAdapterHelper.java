package com.mcdonalds.app.ordering.menu.expandablegrid;

import com.ensighten.Ensighten;
import java.util.ArrayList;
import java.util.List;

public class ExpandableRecyclerAdapterHelper {
    public static List<Object> generateParentChildItemList(List<CategoryExpandable> parentItemList, boolean filterFlag) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ordering.menu.expandablegrid.ExpandableRecyclerAdapterHelper", "generateParentChildItemList", new Object[]{parentItemList, new Boolean(filterFlag)});
        List<Object> parentWrapperList = new ArrayList();
        int parentListItemCount = parentItemList.size();
        for (int i = 0; i < parentListItemCount; i++) {
            CategoryExpandable parentWrapper = (CategoryExpandable) parentItemList.get(i);
            parentWrapperList.add(parentWrapper);
            if (parentWrapper.isInitiallyExpanded() || filterFlag) {
                parentWrapper.setExpanded(true);
                int childListItemCount = parentWrapper.getChildItemList().size();
                for (int j = 0; j < childListItemCount; j++) {
                    parentWrapperList.add(parentWrapper.getChildItemList().get(j));
                }
            }
        }
        return parentWrapperList;
    }
}
