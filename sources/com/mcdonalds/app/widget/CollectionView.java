package com.mcdonalds.app.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.sdk.services.log.MCDLog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CollectionView extends ListView {
    private CollectionViewCallbacks mCallbacks;
    private int mContentTopClearance;
    private int mInternalPadding;
    private Inventory mInventory;
    private MultiScrollListener mMultiScrollListener;
    RowComputeResult mRowComputeResult;

    private static class EmptyView extends View {
        private EmptyView(Context ctx) {
            super(ctx);
        }
    }

    public static class Inventory {
        private ArrayList<InventoryGroup> mGroups = new ArrayList();

        public int getGroupIndex(int groupId) {
            Ensighten.evaluateEvent(this, "getGroupIndex", new Object[]{new Integer(groupId)});
            for (int i = 0; i < this.mGroups.size(); i++) {
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$InventoryGroup", "access$200", new Object[]{(InventoryGroup) this.mGroups.get(i)}) == groupId) {
                    return i;
                }
            }
            return -1;
        }
    }

    public static class InventoryGroup implements Cloneable {
        private int mDataIndexStart;
        private int mDisplayCols;
        private int mGroupId;
        private String mHeaderLabel;
        private int mItemCount;
        private SparseArray<Integer> mItemCustomDataIndices;
        private SparseArray<Object> mItemTag;
        private boolean mShowHeader;

        public int getDataIndex(int indexInGroup) {
            Ensighten.evaluateEvent(this, "getDataIndex", new Object[]{new Integer(indexInGroup)});
            return ((Integer) this.mItemCustomDataIndices.get(indexInGroup, Integer.valueOf(this.mDataIndexStart + indexInGroup))).intValue();
        }

        public int getRowCount() {
            int i = 1;
            Ensighten.evaluateEvent(this, "getRowCount", null);
            int i2 = (this.mShowHeader ? 1 : 0) + (this.mItemCount / this.mDisplayCols);
            if (this.mItemCount % this.mDisplayCols <= 0) {
                i = 0;
            }
            return i2 + i;
        }

        public Object getItemTag(int i) {
            Ensighten.evaluateEvent(this, "getItemTag", new Object[]{new Integer(i)});
            return this.mItemTag.get(i, null);
        }
    }

    private static class MultiScrollListener implements OnScrollListener {
        private final Set<OnScrollListener> children;

        private MultiScrollListener() {
            this.children = new HashSet();
        }

        public void addOnScrollListener(OnScrollListener listener) {
            Ensighten.evaluateEvent(this, "addOnScrollListener", new Object[]{listener});
            this.children.add(listener);
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            Ensighten.evaluateEvent(this, "onScrollStateChanged", new Object[]{absListView, new Integer(i)});
            for (OnScrollListener listener : this.children) {
                listener.onScrollStateChanged(absListView, i);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            Ensighten.evaluateEvent(this, "onScroll", new Object[]{absListView, new Integer(i), new Integer(i2), new Integer(i3)});
            for (OnScrollListener listener : this.children) {
                listener.onScroll(absListView, i, i2, i3);
            }
        }
    }

    protected class MyListAdapter extends BaseAdapter {
        protected MyListAdapter() {
        }

        public int getCount() {
            Ensighten.evaluateEvent(this, "getCount", null);
            int rowCount = 0;
            Iterator it = Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$Inventory", "access$000", new Object[]{Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView", "access$500", new Object[]{CollectionView.this})}).iterator();
            while (it.hasNext()) {
                rowCount += ((InventoryGroup) it.next()).getRowCount();
            }
            return rowCount;
        }

        public Object getItem(int position) {
            Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
            return Integer.valueOf(position);
        }

        public long getItemId(int position) {
            Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
            return (long) position;
        }

        public View getView(int row, View convertView, ViewGroup parent) {
            View access$600 = Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView", "access$600", new Object[]{CollectionView.this, new Integer(row), convertView, parent});
            Ensighten.getViewReturnValue(access$600, row);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return access$600;
        }

        public int getItemViewType(int row) {
            Ensighten.evaluateEvent(this, "getItemViewType", new Object[]{new Integer(row)});
            return Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView", "access$700", new Object[]{CollectionView.this, new Integer(row)});
        }

        public int getViewTypeCount() {
            Ensighten.evaluateEvent(this, "getViewTypeCount", null);
            return Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$Inventory", "access$000", new Object[]{Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView", "access$500", new Object[]{CollectionView.this})}).size() + 1;
        }
    }

    private class RowComputeResult {
        InventoryGroup group;
        int groupId;
        int groupOffset;
        boolean isHeader;
        int row;

        private RowComputeResult() {
        }
    }

    public CollectionView(Context context) {
        this(context, null);
    }

    public CollectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollectionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mInventory = new Inventory();
        this.mCallbacks = null;
        this.mContentTopClearance = 0;
        this.mRowComputeResult = new RowComputeResult();
        setAdapter(new MyListAdapter());
        setDivider(null);
        setDividerHeight(0);
        setItemsCanFocus(false);
        setChoiceMode(0);
        setSelector(17170445);
        if (attrs != null) {
            TypedArray xmlArgs = context.obtainStyledAttributes(attrs, C2358R.styleable.CollectionView, defStyle, 0);
            this.mInternalPadding = xmlArgs.getDimensionPixelSize(0, 0);
            this.mContentTopClearance = xmlArgs.getDimensionPixelSize(1, 0);
        }
    }

    public void setCollectionAdapter(CollectionViewCallbacks adapter) {
        Ensighten.evaluateEvent(this, "setCollectionAdapter", new Object[]{adapter});
        this.mCallbacks = adapter;
    }

    private void notifyAdapterDataSetChanged() {
        Ensighten.evaluateEvent(this, "notifyAdapterDataSetChanged", null);
        setAdapter(new MyListAdapter());
    }

    public void setContentTopClearance(int clearance) {
        Ensighten.evaluateEvent(this, "setContentTopClearance", new Object[]{new Integer(clearance)});
        if (this.mContentTopClearance != clearance) {
            this.mContentTopClearance = clearance;
            setPadding(getPaddingLeft(), this.mContentTopClearance, getPaddingRight(), getPaddingBottom());
            notifyAdapterDataSetChanged();
        }
    }

    private boolean computeRowContent(int row, RowComputeResult result) {
        Ensighten.evaluateEvent(this, "computeRowContent", new Object[]{new Integer(row), result});
        int curRow = 0;
        Iterator it = Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$Inventory", "access$000", new Object[]{this.mInventory}).iterator();
        while (it.hasNext()) {
            InventoryGroup group = (InventoryGroup) it.next();
            if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$InventoryGroup", "access$100", new Object[]{group})) {
                if (curRow == row) {
                    result.row = row;
                    result.isHeader = true;
                    result.groupId = Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$InventoryGroup", "access$200", new Object[]{group});
                    result.group = group;
                    result.groupOffset = -1;
                    return true;
                }
                curRow++;
            }
            int posInGroup = 0;
            while (posInGroup < Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$InventoryGroup", "access$300", new Object[]{group})) {
                if (curRow == row) {
                    result.row = row;
                    result.isHeader = false;
                    result.groupId = Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$InventoryGroup", "access$200", new Object[]{group});
                    result.group = group;
                    result.groupOffset = posInGroup;
                    return true;
                }
                posInGroup += Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$InventoryGroup", "access$400", new Object[]{group});
                curRow++;
            }
        }
        return false;
    }

    private View getRowView(int row, View convertView, ViewGroup parent) {
        Ensighten.evaluateEvent(this, "getRowView", new Object[]{new Integer(row), convertView, parent});
        if (computeRowContent(row, this.mRowComputeResult)) {
            return makeRow(convertView, this.mRowComputeResult, parent);
        }
        Log.e("CollectionView", "Invalid row passed to getView: " + row);
        return convertView == null ? new View(getContext()) : convertView;
    }

    private int getRowViewType(int row) {
        Ensighten.evaluateEvent(this, "getRowViewType", new Object[]{new Integer(row)});
        if (!computeRowContent(row, this.mRowComputeResult)) {
            Log.e("CollectionView", "Invalid row passed to getItemViewType: " + row);
            return 0;
        } else if (this.mRowComputeResult.isHeader) {
            return 0;
        } else {
            return this.mInventory.getGroupIndex(this.mRowComputeResult.groupId) + 1;
        }
    }

    public void setOnScrollListener(OnScrollListener listener) {
        Ensighten.evaluateEvent(this, "setOnScrollListener", new Object[]{listener});
        if (this.mMultiScrollListener == null) {
            this.mMultiScrollListener = new MultiScrollListener();
            super.setOnScrollListener(this.mMultiScrollListener);
        }
        this.mMultiScrollListener.addOnScrollListener(listener);
    }

    private View makeRow(View view, RowComputeResult rowInfo, ViewGroup parent) {
        Ensighten.evaluateEvent(this, "makeRow", new Object[]{view, rowInfo, parent});
        if (this.mCallbacks == null) {
            Log.e("CollectionView", "Call to makeRow without an adapter installed");
            if (view != null) {
                return view;
            }
            return new View(getContext());
        }
        String desiredViewType = this.mInventory.hashCode() + "." + getRowViewType(rowInfo.row);
        String actualViewType = (view == null || view.getTag() == null) ? "" : view.getTag().toString();
        if (!desiredViewType.equals(actualViewType)) {
            view = null;
        }
        if (rowInfo.isHeader) {
            if (view == null) {
                view = this.mCallbacks.newCollectionHeaderView(getContext(), parent);
            }
            this.mCallbacks.bindCollectionHeaderView(getContext(), view, rowInfo.groupId, Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$InventoryGroup", "access$1000", new Object[]{rowInfo.group}));
        } else {
            view = makeItemRow(view, rowInfo);
        }
        view.setTag(desiredViewType);
        return view;
    }

    private View makeItemRow(View convertView, RowComputeResult rowInfo) {
        Ensighten.evaluateEvent(this, "makeItemRow", new Object[]{convertView, rowInfo});
        return convertView == null ? makeNewItemRow(rowInfo) : recycleItemRow(convertView, rowInfo);
    }

    private View getItemView(RowComputeResult rowInfo, int column, View view, ViewGroup parent) {
        Ensighten.evaluateEvent(this, "getItemView", new Object[]{rowInfo, new Integer(column), view, parent});
        int indexInGroup = rowInfo.groupOffset + column;
        if (indexInGroup < Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$InventoryGroup", "access$300", new Object[]{rowInfo.group})) {
            if (view == null || (view instanceof EmptyView)) {
                view = this.mCallbacks.newCollectionItemView(getContext(), rowInfo.groupId, parent);
            }
            this.mCallbacks.bindCollectionItemView(getContext(), view, rowInfo.groupId, indexInGroup, rowInfo.group.getDataIndex(indexInGroup), rowInfo.group.getItemTag(rowInfo.groupOffset + column));
            return view;
        } else if (view != null && (view instanceof EmptyView)) {
            return view;
        } else {
            view = new EmptyView(getContext());
            view.setLayoutParams(new LayoutParams(-1, -2));
            return view;
        }
    }

    private LayoutParams setupLayoutParams(View view) {
        LayoutParams viewLayoutParams;
        Ensighten.evaluateEvent(this, "setupLayoutParams", new Object[]{view});
        if (view.getLayoutParams() instanceof LayoutParams) {
            viewLayoutParams = (LayoutParams) view.getLayoutParams();
        } else {
            MCDLog.warning("Unexpected class for collection view item's layout params: " + view.getLayoutParams().getClass().getName());
            viewLayoutParams = new LayoutParams(-1, -2);
        }
        viewLayoutParams.leftMargin = this.mInternalPadding / 2;
        viewLayoutParams.rightMargin = this.mInternalPadding / 2;
        viewLayoutParams.bottomMargin = this.mInternalPadding;
        viewLayoutParams.width = -1;
        viewLayoutParams.weight = 1.0f;
        view.setLayoutParams(viewLayoutParams);
        return viewLayoutParams;
    }

    private View makeNewItemRow(RowComputeResult rowInfo) {
        Ensighten.evaluateEvent(this, "makeNewItemRow", new Object[]{rowInfo});
        LinearLayout ll = new LinearLayout(getContext());
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(-1, -2);
        ll.setOrientation(0);
        ll.setLayoutParams(params);
        for (int i = 0; i < Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$InventoryGroup", "access$400", new Object[]{rowInfo.group}); i++) {
            View view = getItemView(rowInfo, i, null, ll);
            ll.addView(view, setupLayoutParams(view));
        }
        return ll;
    }

    private View recycleItemRow(View convertView, RowComputeResult rowInfo) {
        Ensighten.evaluateEvent(this, "recycleItemRow", new Object[]{convertView, rowInfo});
        LinearLayout ll = (LinearLayout) convertView;
        for (int i = 0; i < Ensighten.evaluateEvent(null, "com.mcdonalds.app.widget.CollectionView$InventoryGroup", "access$400", new Object[]{rowInfo.group}); i++) {
            View view = ll.getChildAt(i);
            View newView = getItemView(rowInfo, i, view, ll);
            if (view != newView) {
                LayoutParams thisViewParams = setupLayoutParams(newView);
                ll.removeViewAt(i);
                ll.addView(newView, i, thisViewParams);
            }
        }
        return ll;
    }
}
