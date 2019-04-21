package com.mcdonalds.app.home.dashboard.viewholder;

import android.support.p000v4.view.ViewPager;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import com.mcdonalds.app.C2358R;

public class HeaderPagerViewHolder extends ViewHolder {
    public ViewPager mViewPager;

    public HeaderPagerViewHolder(View itemView) {
        super(itemView);
        this.mViewPager = (ViewPager) itemView.findViewById(C2358R.C2357id.header_view_pager);
    }
}
