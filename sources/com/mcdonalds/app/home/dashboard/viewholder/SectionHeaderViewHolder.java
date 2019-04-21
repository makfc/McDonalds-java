package com.mcdonalds.app.home.dashboard.viewholder;

import android.content.Context;
import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.widget.offers.MCDListSectionHeaderModel;
import com.mcdonalds.gma.hongkong.C2658R;

public class SectionHeaderViewHolder extends ViewHolder {
    public ImageView mHeaderImageView;
    public TextView mHeaderTextView;

    private SectionHeaderViewHolder(View itemView) {
        super(itemView);
        this.mHeaderTextView = (TextView) itemView.findViewById(C2358R.C2357id.section_name);
        this.mHeaderImageView = (ImageView) itemView.findViewById(C2358R.C2357id.section_icon);
    }

    public static ViewHolder create(LayoutInflater layoutInflater) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.SectionHeaderViewHolder", "create", new Object[]{layoutInflater});
        return new SectionHeaderViewHolder(layoutInflater.inflate(C2658R.layout.section_header, null));
    }

    public static void bind(Context context, SectionHeaderViewHolder viewHolder, MCDListSectionHeaderModel mcdListSectionHeaderModel) {
        int color;
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.SectionHeaderViewHolder", "bind", new Object[]{context, viewHolder, mcdListSectionHeaderModel});
        if (mcdListSectionHeaderModel.getTitle() != null) {
            viewHolder.mHeaderTextView.setText(mcdListSectionHeaderModel.getTitle());
            viewHolder.mHeaderTextView.setVisibility(0);
        } else {
            viewHolder.mHeaderTextView.setVisibility(8);
        }
        if (mcdListSectionHeaderModel.isImageVisible()) {
            viewHolder.mHeaderImageView.setVisibility(0);
            viewHolder.mHeaderImageView.setImageResource(mcdListSectionHeaderModel.getImageResource());
            viewHolder.mHeaderImageView.getRootView().setBackgroundResource(mcdListSectionHeaderModel.getBackgroundColorResource());
        } else {
            viewHolder.mHeaderImageView.setVisibility(8);
        }
        TextView textView = viewHolder.mHeaderTextView;
        if (mcdListSectionHeaderModel.getBackgroundColorResource() != 0) {
            color = context.getResources().getColor(mcdListSectionHeaderModel.getBackgroundColorResource());
        } else {
            color = context.getResources().getColor(C2658R.color.light_gray_2);
        }
        textView.setBackgroundColor(color);
        textView = viewHolder.mHeaderTextView;
        if (mcdListSectionHeaderModel.getTextColor() != 0) {
            color = context.getResources().getColor(mcdListSectionHeaderModel.getTextColor());
        } else {
            color = context.getResources().getColor(C2658R.color.dark_gray_2);
        }
        textView.setTextColor(color);
    }
}
