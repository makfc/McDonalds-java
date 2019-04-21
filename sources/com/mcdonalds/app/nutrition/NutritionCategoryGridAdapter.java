package com.mcdonalds.app.nutrition;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.sdk.modules.models.NutritionRecipe;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NutritionCategoryGridAdapter extends ArrayAdapter<NutritionRecipe> {
    private final Context mContext;
    private RecipeFilter mFilter;
    private List<NutritionRecipe> mList = new ArrayList();
    private GridItemClickListener mOnGridItemClickListener;
    private final int mResource;

    public interface GridItemClickListener {
        void onItemClick(NutritionRecipe nutritionRecipe);
    }

    class RecipeFilter extends Filter {
        RecipeFilter() {
        }

        /* Access modifiers changed, original: protected */
        public FilterResults performFiltering(CharSequence constraint) {
            Ensighten.evaluateEvent(this, "performFiltering", new Object[]{constraint});
            FilterResults filterResults = new FilterResults();
            List<NutritionRecipe> filteredList = new ArrayList();
            for (int i = 0; i < Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridAdapter", "access$000", new Object[]{NutritionCategoryGridAdapter.this}).size(); i++) {
                String normalizedName = Normalizer.normalize(((NutritionRecipe) Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridAdapter", "access$000", new Object[]{NutritionCategoryGridAdapter.this}).get(i)).getName().toLowerCase(), Form.NFD);
                String marketingName = Normalizer.normalize(((NutritionRecipe) Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridAdapter", "access$000", new Object[]{NutritionCategoryGridAdapter.this}).get(i)).getMarketingName().toLowerCase(), Form.NFD);
                if (normalizedName.contains(constraint.toString().toLowerCase()) || marketingName.contains(constraint.toString().toLowerCase())) {
                    filteredList.add(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridAdapter", "access$000", new Object[]{NutritionCategoryGridAdapter.this}).get(i));
                }
            }
            filterResults.values = filteredList;
            filterResults.count = filteredList.size();
            return filterResults;
        }

        /* Access modifiers changed, original: protected */
        public void publishResults(CharSequence constraint, FilterResults results) {
            Ensighten.evaluateEvent(this, "publishResults", new Object[]{constraint, results});
            NutritionCategoryGridAdapter.this.clear();
            NutritionCategoryGridAdapter.this.addAll((List) results.values);
            NutritionCategoryGridAdapter.this.notifyDataSetChanged();
        }
    }

    class ViewHolder {
        View mContainer;
        ImageView mImageView;
        TextView mName;

        ViewHolder(View view) {
            this.mContainer = view;
            this.mImageView = (ImageView) view.findViewById(2131820643);
            this.mName = (TextView) view.findViewById(C2358R.C2357id.name);
        }
    }

    public NutritionCategoryGridAdapter(Context context, int resource, GridItemClickListener listener) {
        super(context, resource);
        this.mContext = context;
        this.mResource = resource;
        this.mOnGridItemClickListener = listener;
    }

    public void addAll(@NonNull Collection<? extends NutritionRecipe> collection) {
        Ensighten.evaluateEvent(this, "addAll", new Object[]{collection});
        super.addAll(collection);
        if (this.mList.isEmpty()) {
            this.mList.addAll(collection);
        }
    }

    public Filter getFilter() {
        Ensighten.evaluateEvent(this, "getFilter", null);
        if (this.mFilter == null) {
            this.mFilter = new RecipeFilter();
        }
        return this.mFilter;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        final NutritionRecipe nutritionRecipe = (NutritionRecipe) getItem(position);
        if (convertView == null) {
            convertView = inflater.inflate(this.mResource, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DataLayerClickListener.setDataLayerTag(viewHolder.mContainer, "NutritionalRecipeItemPressed", position);
        viewHolder.mContainer.setVisibility(0);
        viewHolder.mContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Ensighten.evaluateEvent(this, "onClick", new Object[]{v});
                if (Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridAdapter", "access$100", new Object[]{NutritionCategoryGridAdapter.this}) != null) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryGridAdapter", "access$100", new Object[]{NutritionCategoryGridAdapter.this}).onItemClick(nutritionRecipe);
                }
            }
        });
        if (TextUtils.isEmpty(nutritionRecipe.getMarketingName())) {
            viewHolder.mName.setText(nutritionRecipe.getName());
        } else {
            viewHolder.mName.setText(nutritionRecipe.getMarketingName());
        }
        String imageUrl = nutritionRecipe.getImageUrl();
        if (TextUtils.isEmpty(imageUrl)) {
            viewHolder.mImageView.setImageResource(C2358R.C2359drawable.icon_large_meal);
        } else {
            viewHolder.mImageView.setBackgroundResource(0);
            Glide.with(this.mContext).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).error((int) C2358R.C2359drawable.icon_large_meal).into(viewHolder.mImageView);
        }
        Ensighten.getViewReturnValue(convertView, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return convertView;
    }
}
