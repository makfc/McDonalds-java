package com.mcdonalds.app.ordering.menu.expandablegrid;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;

public class CategoryParentViewHolder extends ParentViewHolder {
    private static final boolean HONEYCOMB_AND_ABOVE = (VERSION.SDK_INT >= 11);
    public ImageView mParentDropDownArrow;
    public ImageView mParentImage;
    public TextView title;

    public CategoryParentViewHolder(View itemView) {
        super(itemView);
        this.title = (TextView) itemView.findViewById(C2358R.C2357id.section_name);
        this.mParentDropDownArrow = (ImageView) itemView.findViewById(C2358R.C2357id.arrow_expandable);
        this.mParentImage = (ImageView) itemView.findViewById(C2358R.C2357id.section_icon);
    }

    @SuppressLint({"NewApi"})
    public void setExpanded(boolean expanded) {
        Ensighten.evaluateEvent(this, "setExpanded", new Object[]{new Boolean(expanded)});
        super.setExpanded(expanded);
        if (expanded) {
            this.mParentDropDownArrow.setRotation(180.0f);
        } else {
            this.mParentDropDownArrow.setRotation(0.0f);
        }
    }
}
