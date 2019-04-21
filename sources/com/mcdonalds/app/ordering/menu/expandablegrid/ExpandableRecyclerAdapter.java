package com.mcdonalds.app.ordering.menu.expandablegrid;

import android.support.p001v7.widget.RecyclerView;
import android.support.p001v7.widget.RecyclerView.Adapter;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.ordering.menu.expandablegrid.ParentViewHolder.ParentListItemExpandCollapseListener;
import com.mcdonalds.sdk.modules.models.Product;
import java.util.ArrayList;
import java.util.List;

public abstract class ExpandableRecyclerAdapter<PVH extends ParentViewHolder, CVH extends ChildViewHolder> extends Adapter<ViewHolder> implements ParentListItemExpandCollapseListener {
    private List<RecyclerView> mAttachedRecyclerViewPool = new ArrayList();
    private ExpandCollapseListener mExpandCollapseListener;
    protected List<Object> mItemList;
    private List<CategoryExpandable> mParentItemList;

    public interface ExpandCollapseListener {
        void onListItemCollapsed(int i);

        void onListItemExpanded(int i);
    }

    public abstract void onBindChildViewHolder(CVH cvh, int i, Object obj);

    public abstract void onBindParentViewHolder(PVH pvh, int i, CategoryExpandable categoryExpandable);

    public abstract CVH onCreateChildViewHolder(ViewGroup viewGroup);

    public abstract PVH onCreateParentViewHolder(ViewGroup viewGroup);

    public void setmParentItemList(List<CategoryExpandable> parentItemList, boolean filterFlag) {
        Ensighten.evaluateEvent(this, "setmParentItemList", new Object[]{parentItemList, new Boolean(filterFlag)});
        this.mParentItemList = parentItemList;
        this.mItemList = ExpandableRecyclerAdapterHelper.generateParentChildItemList(this.mParentItemList, filterFlag);
    }

    public void setExpandCollapseListener(ExpandCollapseListener mExpandCollapseListener) {
        Ensighten.evaluateEvent(this, "setExpandCollapseListener", new Object[]{mExpandCollapseListener});
        this.mExpandCollapseListener = mExpandCollapseListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Ensighten.evaluateEvent(this, "onCreateViewHolder", new Object[]{viewGroup, new Integer(viewType)});
        if (viewType == 0) {
            PVH pvh = onCreateParentViewHolder(viewGroup);
            pvh.setParentListItemExpandCollapseListener(this);
            return pvh;
        } else if (viewType == 1) {
            return onCreateChildViewHolder(viewGroup);
        } else {
            throw new IllegalStateException("Incorrect ViewType found");
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Ensighten.evaluateEvent(this, "onBindViewHolder", new Object[]{holder, new Integer(position)});
        CategoryExpandable listItem = getListItem(position);
        if ((listItem instanceof CategoryExpandable) && listItem.getCategory() != null) {
            ParentViewHolder parentViewHolder = (ParentViewHolder) holder;
            if (parentViewHolder.shouldItemViewClickToggleExpansion()) {
                parentViewHolder.setMainItemClickToExpand();
            }
            CategoryExpandable parentWrapper = listItem;
            parentViewHolder.setExpanded(parentWrapper.isExpanded());
            onBindParentViewHolder(parentViewHolder, position, parentWrapper.getParentListItem());
        } else if (listItem == null) {
            throw new IllegalStateException("Incorrect ViewHolder found");
        } else {
            onBindChildViewHolder((ChildViewHolder) holder, position, listItem);
        }
    }

    public int getItemCount() {
        Ensighten.evaluateEvent(this, "getItemCount", null);
        if (this.mItemList == null) {
            return 0;
        }
        return this.mItemList.size();
    }

    public int getItemViewType(int position) {
        Ensighten.evaluateEvent(this, "getItemViewType", new Object[]{new Integer(position)});
        Object listItem = getListItem(position);
        if (listItem instanceof CategoryExpandable) {
            return 0;
        }
        if (listItem != null) {
            return 1;
        }
        throw new IllegalStateException("Null object added");
    }

    public void onParentListItemExpanded(int position) {
        Ensighten.evaluateEvent(this, "onParentListItemExpanded", new Object[]{new Integer(position)});
        Object listItem = getListItem(position);
        if (listItem instanceof CategoryExpandable) {
            expandParentListItem((CategoryExpandable) listItem, position, true);
        }
    }

    public void onParentListItemCollapsed(int position) {
        Ensighten.evaluateEvent(this, "onParentListItemCollapsed", new Object[]{new Integer(position)});
        Object listItem = getListItem(position);
        if (listItem instanceof CategoryExpandable) {
            collapseParentListItem((CategoryExpandable) listItem, position, true);
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Ensighten.evaluateEvent(this, "onAttachedToRecyclerView", new Object[]{recyclerView});
        super.onAttachedToRecyclerView(recyclerView);
        this.mAttachedRecyclerViewPool.add(recyclerView);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        Ensighten.evaluateEvent(this, "onDetachedFromRecyclerView", new Object[]{recyclerView});
        super.onDetachedFromRecyclerView(recyclerView);
        this.mAttachedRecyclerViewPool.remove(recyclerView);
    }

    /* Access modifiers changed, original: protected */
    public Object getListItem(int position) {
        boolean indexInRange = true;
        Ensighten.evaluateEvent(this, "getListItem", new Object[]{new Integer(position)});
        if (position < 0 || position >= this.mItemList.size()) {
            indexInRange = false;
        }
        if (indexInRange) {
            return this.mItemList.get(position);
        }
        return null;
    }

    private void expandParentListItem(CategoryExpandable parentWrapper, int parentIndex, boolean expansionTriggeredByListItemClick) {
        Ensighten.evaluateEvent(this, "expandParentListItem", new Object[]{parentWrapper, new Integer(parentIndex), new Boolean(expansionTriggeredByListItemClick)});
        if (!parentWrapper.isExpanded()) {
            parentWrapper.setExpanded(true);
            List<Product> childItemList = parentWrapper.getChildItemList();
            if (childItemList != null) {
                int childListItemCount = childItemList.size();
                for (int i = 0; i < childListItemCount; i++) {
                    this.mItemList.add((parentIndex + i) + 1, childItemList.get(i));
                }
                notifyItemRangeInserted(parentIndex + 1, childListItemCount);
            }
            if (expansionTriggeredByListItemClick && this.mExpandCollapseListener != null) {
                int expandedCountBeforePosition = getExpandedItemCount(parentIndex);
                this.mExpandCollapseListener.onListItemExpanded(parentIndex);
            }
        }
    }

    private void collapseParentListItem(CategoryExpandable parentWrapper, int parentIndex, boolean collapseTriggeredByListItemClick) {
        Ensighten.evaluateEvent(this, "collapseParentListItem", new Object[]{parentWrapper, new Integer(parentIndex), new Boolean(collapseTriggeredByListItemClick)});
        if (parentWrapper.isExpanded()) {
            parentWrapper.setExpanded(false);
            List<Product> childItemList = parentWrapper.getChildItemList();
            if (childItemList != null) {
                int childListItemCount = childItemList.size();
                for (int i = childListItemCount - 1; i >= 0; i--) {
                    this.mItemList.remove((parentIndex + i) + 1);
                }
                notifyItemRangeRemoved(parentIndex + 1, childListItemCount);
            }
            if (collapseTriggeredByListItemClick && this.mExpandCollapseListener != null) {
                this.mExpandCollapseListener.onListItemCollapsed(parentIndex - getExpandedItemCount(parentIndex));
            }
        }
    }

    private int getExpandedItemCount(int position) {
        int expandedCount = 0;
        Ensighten.evaluateEvent(this, "getExpandedItemCount", new Object[]{new Integer(position)});
        if (position != 0) {
            expandedCount = 0;
            for (int i = 0; i < position; i++) {
                if (!(getListItem(i) instanceof CategoryExpandable)) {
                    expandedCount++;
                }
            }
        }
        return expandedCount;
    }
}
