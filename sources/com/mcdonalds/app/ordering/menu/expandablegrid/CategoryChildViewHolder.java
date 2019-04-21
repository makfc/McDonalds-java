package com.mcdonalds.app.ordering.menu.expandablegrid;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mcdonalds.app.C2358R;

public class CategoryChildViewHolder extends ChildViewHolder {
    public RelativeLayout container;
    public ImageView favorite;
    public ImageView image;
    public ImageView offer;
    public TextView title;

    public CategoryChildViewHolder(View view) {
        super(view);
        this.container = (RelativeLayout) view.findViewById(C2358R.C2357id.grid_item);
        this.title = (TextView) view.findViewById(C2358R.C2357id.name);
        this.image = (ImageView) view.findViewById(2131820643);
        this.offer = (ImageView) view.findViewById(C2358R.C2357id.offer_icon);
        this.favorite = (ImageView) view.findViewById(C2358R.C2357id.favorite_icon);
    }
}
