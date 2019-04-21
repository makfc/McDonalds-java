package com.mcdonalds.app.p043ui.listview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.support.p000v4.widget.ExploreByTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.ensighten.Ensighten;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.mcdonalds.app.ui.listview.ExpandableListItemAdapter */
public abstract class ExpandableListItemAdapter<T> extends ArrayAdapter<T> {
    protected AbsListView mAbsListView;
    private final int mContentParentResId;
    private final Context mContext;
    private ExpandCollapseListener mExpandCollapseListener;
    private final List<Long> mExpandedIds = new ArrayList();
    private final List<T> mItems;
    private int mLimit;
    private final int mTitleParentResId;
    private int mViewLayoutResId;

    /* renamed from: com.mcdonalds.app.ui.listview.ExpandableListItemAdapter$ViewHolder */
    public interface ViewHolder {
        ViewGroup getContentParent();

        View getContentView();

        ViewGroup getTitleParent();

        View getTitleView();

        void setContentParent(ViewGroup viewGroup);

        void setContentView(View view);

        void setTitleParent(ViewGroup viewGroup);

        void setTitleView(View view);
    }

    /* renamed from: com.mcdonalds.app.ui.listview.ExpandableListItemAdapter$ExpandCollapseHelper */
    private static class ExpandCollapseHelper {
        private ExpandCollapseHelper() {
        }

        public static void animateCollapsing(final View view) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.listview.ExpandableListItemAdapter$ExpandCollapseHelper", "animateCollapsing", new Object[]{view});
            ValueAnimator animator = ExpandCollapseHelper.createHeightAnimator(view, view.getHeight(), 0);
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    Ensighten.evaluateEvent(this, "onAnimationEnd", new Object[]{animator});
                    view.setVisibility(8);
                }
            });
            animator.start();
        }

        public static void animateExpanding(final View view, final AbsListView listView) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.listview.ExpandableListItemAdapter$ExpandCollapseHelper", "animateExpanding", new Object[]{view, listView});
            view.setVisibility(0);
            View parent = (View) view.getParent();
            view.measure(MeasureSpec.makeMeasureSpec((parent.getMeasuredWidth() - parent.getPaddingLeft()) - parent.getPaddingRight(), ExploreByTouchHelper.INVALID_ID), MeasureSpec.makeMeasureSpec(0, 0));
            ValueAnimator animator = ExpandCollapseHelper.createHeightAnimator(view, 0, view.getMeasuredHeight());
            animator.addUpdateListener(new AnimatorUpdateListener() {
                final int listViewBottomPadding = listView.getPaddingBottom();
                final int listViewHeight = listView.getHeight();
                /* renamed from: v */
                final View f6666v = Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.listview.ExpandableListItemAdapter$ExpandCollapseHelper", "access$200", new Object[]{view, listView});

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Ensighten.evaluateEvent(this, "onAnimationUpdate", new Object[]{valueAnimator});
                    int bottom = this.f6666v.getBottom();
                    if (bottom > this.listViewHeight) {
                        int top = this.f6666v.getTop();
                        if (top > 0) {
                            listView.smoothScrollBy(Math.min((bottom - this.listViewHeight) + this.listViewBottomPadding, top), 0);
                        }
                    }
                }
            });
            animator.start();
        }

        private static View findDirectChild(View view, AbsListView listView) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.listview.ExpandableListItemAdapter$ExpandCollapseHelper", "findDirectChild", new Object[]{view, listView});
            View result = view;
            View parent = (View) result.getParent();
            while (parent != listView) {
                result = parent;
                parent = (View) result.getParent();
            }
            return result;
        }

        public static ValueAnimator createHeightAnimator(final View view, int start, int end) {
            Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.listview.ExpandableListItemAdapter$ExpandCollapseHelper", "createHeightAnimator", new Object[]{view, new Integer(start), new Integer(end)});
            ValueAnimator animator = ValueAnimator.ofInt(new int[]{start, end});
            animator.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Ensighten.evaluateEvent(this, "onAnimationUpdate", new Object[]{valueAnimator});
                    int value = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.height = value;
                    view.setLayoutParams(layoutParams);
                }
            });
            return animator;
        }
    }

    /* renamed from: com.mcdonalds.app.ui.listview.ExpandableListItemAdapter$RootView */
    private static class RootView extends LinearLayout {
        private ViewGroup mContentViewGroup;
        private ViewGroup mTitleViewGroup;

        public RootView(Context context) {
            super(context);
            init();
        }

        private void init() {
            Ensighten.evaluateEvent(this, "init", null);
            setOrientation(1);
            this.mTitleViewGroup = new FrameLayout(getContext());
            this.mTitleViewGroup.setId(10000);
            addView(this.mTitleViewGroup);
            this.mContentViewGroup = new FrameLayout(getContext());
            this.mContentViewGroup.setId(10001);
            addView(this.mContentViewGroup);
        }
    }

    /* renamed from: com.mcdonalds.app.ui.listview.ExpandableListItemAdapter$TitleViewOnClickListener */
    private class TitleViewOnClickListener implements OnClickListener {
        private final View mContentParent;
        final /* synthetic */ ExpandableListItemAdapter this$0;

        public void onClick(View view) {
            Ensighten.evaluateEvent(this, "onClick", new Object[]{view});
            ExpandableListItemAdapter.access$100(this.this$0, this.mContentParent);
        }
    }

    /* renamed from: com.mcdonalds.app.ui.listview.ExpandableListItemAdapter$ViewHolderImpl */
    private static class ViewHolderImpl implements ViewHolder {
        private ViewGroup contentParent;
        private View contentView;
        private ViewGroup titleParent;
        private View titleView;

        private ViewHolderImpl() {
        }

        public ViewGroup getTitleParent() {
            Ensighten.evaluateEvent(this, "getTitleParent", null);
            return this.titleParent;
        }

        public void setTitleParent(ViewGroup titleParent) {
            Ensighten.evaluateEvent(this, "setTitleParent", new Object[]{titleParent});
            this.titleParent = titleParent;
        }

        public ViewGroup getContentParent() {
            Ensighten.evaluateEvent(this, "getContentParent", null);
            return this.contentParent;
        }

        public void setContentParent(ViewGroup contentParent) {
            Ensighten.evaluateEvent(this, "setContentParent", new Object[]{contentParent});
            this.contentParent = contentParent;
        }

        public View getTitleView() {
            Ensighten.evaluateEvent(this, "getTitleView", null);
            return this.titleView;
        }

        public void setTitleView(View titleView) {
            Ensighten.evaluateEvent(this, "setTitleView", new Object[]{titleView});
            this.titleView = titleView;
        }

        public View getContentView() {
            Ensighten.evaluateEvent(this, "getContentView", null);
            return this.contentView;
        }

        public void setContentView(View contentView) {
            Ensighten.evaluateEvent(this, "setContentView", new Object[]{contentView});
            this.contentView = contentView;
        }
    }

    public abstract View getContentView(int i, View view, ViewGroup viewGroup);

    public abstract View getTitleView(int i, View view, ViewGroup viewGroup);

    static /* synthetic */ void access$100(ExpandableListItemAdapter x0, View x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.ui.listview.ExpandableListItemAdapter", "access$100", new Object[]{x0, x1});
        x0.toggle(x1);
    }

    public ExpandableListItemAdapter(Context context, int layoutResId, int titleParentResId, int contentParentResId, List<T> items) {
        super(context, layoutResId);
        this.mContext = context;
        this.mViewLayoutResId = layoutResId;
        this.mTitleParentResId = titleParentResId;
        this.mContentParentResId = contentParentResId;
        if (items == null) {
            items = new ArrayList();
        }
        this.mItems = items;
    }

    public void setAbsListView(AbsListView listView) {
        Ensighten.evaluateEvent(this, "setAbsListView", new Object[]{listView});
        this.mAbsListView = listView;
    }

    public void setLimit(int limit) {
        Ensighten.evaluateEvent(this, "setLimit", new Object[]{new Integer(limit)});
        this.mLimit = limit;
        this.mExpandedIds.clear();
        notifyDataSetChanged();
    }

    public void setExpandCollapseListener(ExpandCollapseListener expandCollapseListener) {
        Ensighten.evaluateEvent(this, "setExpandCollapseListener", new Object[]{expandCollapseListener});
        this.mExpandCollapseListener = expandCollapseListener;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ViewGroup view = (ViewGroup) convertView;
        if (view == null) {
            view = createView(parent);
            viewHolder = createViewHolder(view);
            viewHolder.setTitleParent((ViewGroup) view.findViewById(this.mTitleParentResId));
            viewHolder.setContentParent((ViewGroup) view.findViewById(this.mContentParentResId));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DataLayerClickListener.setDataLayerTag(convertView, ViewHolder.class, position);
        View titleView = getTitleView(position, viewHolder.getTitleView(), viewHolder.getTitleParent());
        if (titleView != viewHolder.getTitleView()) {
            viewHolder.getTitleParent().removeAllViews();
            viewHolder.getTitleParent().addView(titleView);
        }
        viewHolder.setTitleView(titleView);
        View contentView = getContentView(position, viewHolder.getContentView(), viewHolder.getContentParent());
        if (contentView != viewHolder.getContentView()) {
            viewHolder.getContentParent().removeAllViews();
            viewHolder.getContentParent().addView(contentView);
        }
        viewHolder.setContentView(contentView);
        viewHolder.getContentParent().setVisibility(this.mExpandedIds.contains(Long.valueOf(getItemId(position))) ? 0 : 8);
        viewHolder.getContentParent().setTag(Long.valueOf(getItemId(position)));
        LayoutParams layoutParams = viewHolder.getContentParent().getLayoutParams();
        layoutParams.height = -2;
        viewHolder.getContentParent().setLayoutParams(layoutParams);
        Ensighten.getViewReturnValue(view, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return view;
    }

    private ViewGroup createView(ViewGroup parent) {
        Ensighten.evaluateEvent(this, "createView", new Object[]{parent});
        if (this.mViewLayoutResId == 0) {
            return new RootView(getContext());
        }
        return (ViewGroup) LayoutInflater.from(getContext()).inflate(this.mViewLayoutResId, parent, false);
    }

    public boolean isExpanded(int position) {
        Ensighten.evaluateEvent(this, "isExpanded", new Object[]{new Integer(position)});
        return this.mExpandedIds.contains(Long.valueOf(getItemId(position)));
    }

    /* Access modifiers changed, original: protected */
    public ViewHolder createViewHolder(View view) {
        Ensighten.evaluateEvent(this, "createViewHolder", new Object[]{view});
        return new ViewHolderImpl();
    }

    public void notifyDataSetChanged() {
        Ensighten.evaluateEvent(this, "notifyDataSetChanged", null);
        super.notifyDataSetChanged();
        Set<Long> removedIds = new HashSet(this.mExpandedIds);
        for (int i = 0; i < getCount(); i++) {
            removedIds.remove(Long.valueOf(getItemId(i)));
        }
    }

    private View getContentParent(int position) {
        Ensighten.evaluateEvent(this, "getContentParent", new Object[]{new Integer(position)});
        View parentView = findViewForPosition(position);
        if (parentView == null) {
            return null;
        }
        Object tag = parentView.getTag();
        if (tag instanceof ViewHolder) {
            return ((ViewHolder) tag).getContentParent();
        }
        return null;
    }

    public void expand(int position) {
        Ensighten.evaluateEvent(this, "expand", new Object[]{new Integer(position)});
        if (!this.mExpandedIds.contains(Long.valueOf(getItemId(position)))) {
            toggle(position);
        }
    }

    public void collapse(int position) {
        Ensighten.evaluateEvent(this, "collapse", new Object[]{new Integer(position)});
        if (this.mExpandedIds.contains(Long.valueOf(getItemId(position)))) {
            toggle(position);
        }
    }

    private View findViewForPosition(int position) {
        Ensighten.evaluateEvent(this, "findViewForPosition", new Object[]{new Integer(position)});
        View result = null;
        for (int i = 0; i < this.mAbsListView.getChildCount() && result == null; i++) {
            View childView = this.mAbsListView.getChildAt(i);
            if (AdapterViewUtil.getPositionForView(this.mAbsListView, childView) == position) {
                result = childView;
            }
        }
        return result;
    }

    private int findPositionForId(long id) {
        Ensighten.evaluateEvent(this, "findPositionForId", new Object[]{new Long(id)});
        for (int i = 0; i < getCount(); i++) {
            if (getItemId(i) == id) {
                return i;
            }
        }
        return -1;
    }

    public void toggle(int position) {
        Ensighten.evaluateEvent(this, "toggle", new Object[]{new Integer(position)});
        long itemId = getItemId(position);
        boolean isExpanded = this.mExpandedIds.contains(Long.valueOf(itemId));
        View contentParent = getContentParent(position);
        if (contentParent != null) {
            toggle(contentParent);
        }
        if (contentParent == null && isExpanded) {
            this.mExpandedIds.remove(Long.valueOf(itemId));
        } else if (contentParent == null && !isExpanded) {
            this.mExpandedIds.add(Long.valueOf(itemId));
        }
    }

    private void toggle(View contentParent) {
        boolean isVisible;
        boolean shouldCollapseOther = true;
        Ensighten.evaluateEvent(this, "toggle", new Object[]{contentParent});
        if (contentParent.getVisibility() == 0) {
            isVisible = true;
        } else {
            isVisible = false;
        }
        if (isVisible || this.mLimit <= 0 || this.mExpandedIds.size() < this.mLimit) {
            shouldCollapseOther = false;
        }
        if (shouldCollapseOther) {
            Long firstId = (Long) this.mExpandedIds.get(0);
            int firstPosition = findPositionForId(firstId.longValue());
            View firstEV = getContentParent(firstPosition);
            if (firstEV != null) {
                ExpandCollapseHelper.animateCollapsing(firstEV);
            }
            this.mExpandedIds.remove(firstId);
            if (this.mExpandCollapseListener != null) {
                this.mExpandCollapseListener.onItemCollapsed(firstPosition);
            }
        }
        Long id = (Long) contentParent.getTag();
        int position = findPositionForId(id.longValue());
        if (isVisible) {
            ExpandCollapseHelper.animateCollapsing(contentParent);
            this.mExpandedIds.remove(id);
            if (this.mExpandCollapseListener != null) {
                this.mExpandCollapseListener.onItemCollapsed(position);
                return;
            }
            return;
        }
        ExpandCollapseHelper.animateExpanding(contentParent, this.mAbsListView);
        this.mExpandedIds.add(id);
        if (this.mExpandCollapseListener != null) {
            this.mExpandCollapseListener.onItemExpanded(position);
        }
    }
}
