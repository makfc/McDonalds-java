package com.mcdonalds.app.widget;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import com.ensighten.Ensighten;
import java.util.ArrayList;
import java.util.Iterator;

public class HeaderGridView extends GridView {
    private ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList();

    private static class FixedViewInfo {
        public Object data;
        public boolean isSelectable;
        public View view;
        public ViewGroup viewContainer;

        private FixedViewInfo() {
        }
    }

    private class FullWidthFixedViewLayout extends FrameLayout {
        public FullWidthFixedViewLayout(Context context) {
            super(context);
        }

        /* Access modifiers changed, original: protected */
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            Ensighten.evaluateEvent(this, "onMeasure", new Object[]{new Integer(widthMeasureSpec), new Integer(heightMeasureSpec)});
            super.onMeasure(MeasureSpec.makeMeasureSpec((HeaderGridView.this.getMeasuredWidth() - HeaderGridView.this.getPaddingLeft()) - HeaderGridView.this.getPaddingRight(), MeasureSpec.getMode(widthMeasureSpec)), heightMeasureSpec);
        }
    }

    private static class HeaderViewGridAdapter implements Filterable, WrapperListAdapter {
        private final ListAdapter mAdapter;
        boolean mAreAllFixedViewsSelectable;
        private final DataSetObservable mDataSetObservable = new DataSetObservable();
        ArrayList<FixedViewInfo> mHeaderViewInfos;
        private final boolean mIsFilterable;
        private int mNumColumns = 1;

        public HeaderViewGridAdapter(ArrayList<FixedViewInfo> headerViewInfos, ListAdapter adapter) {
            this.mAdapter = adapter;
            this.mIsFilterable = adapter instanceof Filterable;
            if (headerViewInfos == null) {
                throw new IllegalArgumentException("headerViewInfos cannot be null");
            }
            this.mHeaderViewInfos = headerViewInfos;
            this.mAreAllFixedViewsSelectable = areAllListInfosSelectable(this.mHeaderViewInfos);
        }

        public int getHeadersCount() {
            Ensighten.evaluateEvent(this, "getHeadersCount", null);
            return this.mHeaderViewInfos.size();
        }

        public boolean isEmpty() {
            Ensighten.evaluateEvent(this, "isEmpty", null);
            return (this.mAdapter == null || this.mAdapter.isEmpty()) && getHeadersCount() == 0;
        }

        public void setNumColumns(int numColumns) {
            Ensighten.evaluateEvent(this, "setNumColumns", new Object[]{new Integer(numColumns)});
            if (numColumns < 1) {
                throw new IllegalArgumentException("Number of columns must be 1 or more");
            } else if (this.mNumColumns != numColumns) {
                this.mNumColumns = numColumns;
                notifyDataSetChanged();
            }
        }

        private boolean areAllListInfosSelectable(ArrayList<FixedViewInfo> infos) {
            Ensighten.evaluateEvent(this, "areAllListInfosSelectable", new Object[]{infos});
            if (infos != null) {
                Iterator it = infos.iterator();
                while (it.hasNext()) {
                    if (!((FixedViewInfo) it.next()).isSelectable) {
                        return false;
                    }
                }
            }
            return true;
        }

        public int getCount() {
            Ensighten.evaluateEvent(this, "getCount", null);
            if (this.mAdapter != null) {
                return (getHeadersCount() * this.mNumColumns) + this.mAdapter.getCount();
            }
            return getHeadersCount() * this.mNumColumns;
        }

        public boolean areAllItemsEnabled() {
            Ensighten.evaluateEvent(this, "areAllItemsEnabled", null);
            if (this.mAdapter == null) {
                return true;
            }
            if (this.mAreAllFixedViewsSelectable && this.mAdapter.areAllItemsEnabled()) {
                return true;
            }
            return false;
        }

        public boolean isEnabled(int position) {
            Ensighten.evaluateEvent(this, "isEnabled", new Object[]{new Integer(position)});
            int numHeadersAndPlaceholders = getHeadersCount() * this.mNumColumns;
            if (position < numHeadersAndPlaceholders) {
                return position % this.mNumColumns == 0 && ((FixedViewInfo) this.mHeaderViewInfos.get(position / this.mNumColumns)).isSelectable;
            } else {
                int adjPosition = position - numHeadersAndPlaceholders;
                if (this.mAdapter != null && adjPosition < this.mAdapter.getCount()) {
                    return this.mAdapter.isEnabled(adjPosition);
                }
                throw new ArrayIndexOutOfBoundsException(position);
            }
        }

        public Object getItem(int position) {
            Ensighten.evaluateEvent(this, "getItem", new Object[]{new Integer(position)});
            int numHeadersAndPlaceholders = getHeadersCount() * this.mNumColumns;
            if (position >= numHeadersAndPlaceholders) {
                int adjPosition = position - numHeadersAndPlaceholders;
                if (this.mAdapter != null && adjPosition < this.mAdapter.getCount()) {
                    return this.mAdapter.getItem(adjPosition);
                }
                throw new ArrayIndexOutOfBoundsException(position);
            } else if (position % this.mNumColumns == 0) {
                return ((FixedViewInfo) this.mHeaderViewInfos.get(position / this.mNumColumns)).data;
            } else {
                return null;
            }
        }

        public long getItemId(int position) {
            Ensighten.evaluateEvent(this, "getItemId", new Object[]{new Integer(position)});
            int numHeadersAndPlaceholders = getHeadersCount() * this.mNumColumns;
            if (this.mAdapter != null && position >= numHeadersAndPlaceholders) {
                int adjPosition = position - numHeadersAndPlaceholders;
                if (adjPosition < this.mAdapter.getCount()) {
                    return this.mAdapter.getItemId(adjPosition);
                }
            }
            return -1;
        }

        public boolean hasStableIds() {
            Ensighten.evaluateEvent(this, "hasStableIds", null);
            if (this.mAdapter != null) {
                return this.mAdapter.hasStableIds();
            }
            return false;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View headerViewContainer;
            int numHeadersAndPlaceholders = getHeadersCount() * this.mNumColumns;
            if (position < numHeadersAndPlaceholders) {
                headerViewContainer = ((FixedViewInfo) this.mHeaderViewInfos.get(position / this.mNumColumns)).viewContainer;
                if (position % this.mNumColumns != 0) {
                    if (convertView == null) {
                        convertView = new View(parent.getContext());
                    }
                    convertView.setVisibility(4);
                    convertView.setMinimumHeight(headerViewContainer.getHeight());
                    headerViewContainer = convertView;
                }
            } else {
                int adjPosition = position - numHeadersAndPlaceholders;
                if (this.mAdapter == null || adjPosition >= this.mAdapter.getCount()) {
                    throw new ArrayIndexOutOfBoundsException(position);
                }
                headerViewContainer = this.mAdapter.getView(adjPosition, convertView, parent);
            }
            Ensighten.getViewReturnValue(headerViewContainer, position);
            Ensighten.processView((Object) this, "getView");
            Ensighten.getViewReturnValue(null, -1);
            return headerViewContainer;
        }

        public int getItemViewType(int position) {
            Ensighten.evaluateEvent(this, "getItemViewType", new Object[]{new Integer(position)});
            int numHeadersAndPlaceholders = getHeadersCount() * this.mNumColumns;
            if (position >= numHeadersAndPlaceholders || position % this.mNumColumns == 0) {
                if (this.mAdapter != null && position >= numHeadersAndPlaceholders) {
                    int adjPosition = position - numHeadersAndPlaceholders;
                    if (adjPosition < this.mAdapter.getCount()) {
                        return this.mAdapter.getItemViewType(adjPosition);
                    }
                }
                return -2;
            } else if (this.mAdapter != null) {
                return this.mAdapter.getViewTypeCount();
            } else {
                return 1;
            }
        }

        public int getViewTypeCount() {
            Ensighten.evaluateEvent(this, "getViewTypeCount", null);
            if (this.mAdapter != null) {
                return this.mAdapter.getViewTypeCount() + 1;
            }
            return 2;
        }

        public void registerDataSetObserver(DataSetObserver observer) {
            Ensighten.evaluateEvent(this, "registerDataSetObserver", new Object[]{observer});
            this.mDataSetObservable.registerObserver(observer);
            if (this.mAdapter != null) {
                this.mAdapter.registerDataSetObserver(observer);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver observer) {
            Ensighten.evaluateEvent(this, "unregisterDataSetObserver", new Object[]{observer});
            this.mDataSetObservable.unregisterObserver(observer);
            if (this.mAdapter != null) {
                this.mAdapter.unregisterDataSetObserver(observer);
            }
        }

        public Filter getFilter() {
            Ensighten.evaluateEvent(this, "getFilter", null);
            if (this.mIsFilterable) {
                return ((Filterable) this.mAdapter).getFilter();
            }
            return null;
        }

        public ListAdapter getWrappedAdapter() {
            Ensighten.evaluateEvent(this, "getWrappedAdapter", null);
            return this.mAdapter;
        }

        public void notifyDataSetChanged() {
            Ensighten.evaluateEvent(this, "notifyDataSetChanged", null);
            this.mDataSetObservable.notifyChanged();
        }
    }

    private void initHeaderGridView() {
        Ensighten.evaluateEvent(this, "initHeaderGridView", null);
        super.setClipChildren(false);
    }

    public HeaderGridView(Context context) {
        super(context);
        initHeaderGridView();
    }

    public HeaderGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderGridView();
    }

    public HeaderGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initHeaderGridView();
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Ensighten.evaluateEvent(this, "onMeasure", new Object[]{new Integer(widthMeasureSpec), new Integer(heightMeasureSpec)});
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ListAdapter adapter = getAdapter();
        if (adapter != null && (adapter instanceof HeaderViewGridAdapter)) {
            ((HeaderViewGridAdapter) adapter).setNumColumns(getNumColumns());
        }
    }

    public void setClipChildren(boolean clipChildren) {
        Ensighten.evaluateEvent(this, "setClipChildren", new Object[]{new Boolean(clipChildren)});
    }

    public void addHeaderView(View v, Object data, boolean isSelectable) {
        Ensighten.evaluateEvent(this, "addHeaderView", new Object[]{v, data, new Boolean(isSelectable)});
        ListAdapter adapter = getAdapter();
        if (adapter == null || (adapter instanceof HeaderViewGridAdapter)) {
            FixedViewInfo info = new FixedViewInfo();
            FrameLayout fl = new FullWidthFixedViewLayout(getContext());
            fl.addView(v);
            info.view = v;
            info.viewContainer = fl;
            info.data = data;
            info.isSelectable = isSelectable;
            this.mHeaderViewInfos.add(info);
            if (adapter != null) {
                ((HeaderViewGridAdapter) adapter).notifyDataSetChanged();
                return;
            }
            return;
        }
        throw new IllegalStateException("Cannot add header view to grid -- setAdapter has already been called.");
    }

    public void addHeaderView(View v) {
        Ensighten.evaluateEvent(this, "addHeaderView", new Object[]{v});
        addHeaderView(v, null, true);
    }

    public int getHeaderViewCount() {
        Ensighten.evaluateEvent(this, "getHeaderViewCount", null);
        return this.mHeaderViewInfos.size();
    }

    public void setAdapter(ListAdapter adapter) {
        Ensighten.evaluateEvent(this, "setAdapter", new Object[]{adapter});
        if (this.mHeaderViewInfos.size() > 0) {
            HeaderViewGridAdapter hadapter = new HeaderViewGridAdapter(this.mHeaderViewInfos, adapter);
            int numColumns = getNumColumns();
            if (numColumns > 1) {
                hadapter.setNumColumns(numColumns);
            }
            super.setAdapter(hadapter);
            return;
        }
        super.setAdapter(adapter);
    }
}
