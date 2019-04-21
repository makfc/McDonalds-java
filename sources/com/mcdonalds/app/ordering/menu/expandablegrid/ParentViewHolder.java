package com.mcdonalds.app.ordering.menu.expandablegrid;

import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import com.ensighten.Ensighten;

public class ParentViewHolder extends ViewHolder implements OnClickListener {
    private boolean mExpanded = false;
    private ParentListItemExpandCollapseListener mParentListItemExpandCollapseListener;

    public interface ParentListItemExpandCollapseListener {
        void onParentListItemCollapsed(int i);

        void onParentListItemExpanded(int i);
    }

    public ParentViewHolder(View itemView) {
        super(itemView);
    }

    public void setMainItemClickToExpand() {
        Ensighten.evaluateEvent(this, "setMainItemClickToExpand", null);
        this.itemView.setOnClickListener(this);
    }

    public void setExpanded(boolean expanded) {
        Ensighten.evaluateEvent(this, "setExpanded", new Object[]{new Boolean(expanded)});
        this.mExpanded = expanded;
    }

    public void onExpansionToggled(boolean expanded) {
        Ensighten.evaluateEvent(this, "onExpansionToggled", new Object[]{new Boolean(expanded)});
    }

    public void setParentListItemExpandCollapseListener(ParentListItemExpandCollapseListener parentListItemExpandCollapseListener) {
        Ensighten.evaluateEvent(this, "setParentListItemExpandCollapseListener", new Object[]{parentListItemExpandCollapseListener});
        this.mParentListItemExpandCollapseListener = parentListItemExpandCollapseListener;
    }

    public void onClick(View v) {
        Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
        if (this.mExpanded) {
            collapseView();
        } else {
            expandView();
        }
    }

    public boolean shouldItemViewClickToggleExpansion() {
        Ensighten.evaluateEvent(this, "shouldItemViewClickToggleExpansion", null);
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void expandView() {
        Ensighten.evaluateEvent(this, "expandView", null);
        setExpanded(true);
        onExpansionToggled(false);
        if (this.mParentListItemExpandCollapseListener != null) {
            this.mParentListItemExpandCollapseListener.onParentListItemExpanded(getAdapterPosition());
        }
    }

    /* Access modifiers changed, original: protected */
    public void collapseView() {
        Ensighten.evaluateEvent(this, "collapseView", null);
        setExpanded(false);
        onExpansionToggled(true);
        if (this.mParentListItemExpandCollapseListener != null) {
            this.mParentListItemExpandCollapseListener.onParentListItemCollapsed(getAdapterPosition());
        }
    }
}
