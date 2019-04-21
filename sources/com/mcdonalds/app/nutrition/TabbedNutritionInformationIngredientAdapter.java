package com.mcdonalds.app.nutrition;

import android.support.p001v7.widget.RecyclerView.Adapter;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.gma.hongkong.C2658R;
import com.mcdonalds.sdk.modules.models.RecipeComponent;
import java.util.List;

public class TabbedNutritionInformationIngredientAdapter extends Adapter<ViewHolder> {
    private List<RecipeComponent> mComponents;
    private List<String> mFooters;
    private boolean showAllergens;

    public class ViewHolder extends android.support.p001v7.widget.RecyclerView.ViewHolder {
        public TextView additionalAllergens;
        public TextView allergens;
        public TextView description;
        public View itemView;
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.name = (TextView) itemView.findViewById(C2358R.C2357id.ingredient_name);
            this.description = (TextView) itemView.findViewById(C2358R.C2357id.ingredient_description);
            this.allergens = (TextView) itemView.findViewById(C2358R.C2357id.ingredient_allergens);
            this.additionalAllergens = (TextView) itemView.findViewById(C2358R.C2357id.ingredient_additional_allergens);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C2658R.layout.ingredient_list_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Ensighten.evaluateEvent(this, "onBindViewHolder", new Object[]{holder, new Integer(position)});
        if (this.mComponents != null && position < this.mComponents.size()) {
            String allergensText;
            RecipeComponent component = (RecipeComponent) this.mComponents.get(position);
            holder.name.setText(component.getProductName());
            if (component.getIngredientStatement() != null) {
                holder.description.setText(Html.fromHtml(component.getIngredientStatement()));
            }
            String allergens = component.getProductAllergen();
            if (this.showAllergens && !TextUtils.isEmpty(allergens)) {
                allergensText = holder.itemView.getContext().getString(C2658R.string.text_allergens_prefix, new Object[]{allergens});
                holder.allergens.setVisibility(0);
                holder.allergens.setText(allergensText);
            }
            String additionalAllergens = component.getProductAdditionalAllergen();
            if (this.showAllergens && !TextUtils.isEmpty(additionalAllergens)) {
                allergensText = holder.itemView.getContext().getString(C2658R.string.text_additional_allergens_prefix, new Object[]{additionalAllergens});
                holder.additionalAllergens.setVisibility(0);
                holder.additionalAllergens.setText(allergensText);
            }
        } else if (this.mFooters != null) {
            if (this.mComponents != null) {
                position -= this.mComponents.size();
            }
            String footer = (String) this.mFooters.get(position);
            holder.name.setVisibility(8);
            holder.description.setText(Html.fromHtml(footer));
        }
        DataLayerClickListener.setDataLayerTag(holder.itemView, ViewHolder.class, position);
    }

    public int getItemCount() {
        int i = 0;
        Ensighten.evaluateEvent(this, "getItemCount", null);
        int size = this.mComponents != null ? this.mComponents.size() : 0;
        if (this.mFooters != null) {
            i = this.mFooters.size();
        }
        return size + i;
    }
}
