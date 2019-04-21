package com.mcdonalds.app.nutrition;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ensighten.Ensighten;
import com.mcdonalds.app.C2358R;
import com.mcdonalds.app.analytics.datalayer.view.DataLayerClickListener;
import com.mcdonalds.sdk.AsyncException;
import com.mcdonalds.sdk.AsyncListener;
import com.mcdonalds.sdk.AsyncToken;
import com.mcdonalds.sdk.modules.models.Category;
import com.mcdonalds.sdk.services.configuration.Configuration;
import com.mcdonalds.sdk.services.network.RequestManagerServiceConnection;

public class NutritionCategoryListAdapter extends ArrayAdapter<Category> {
    private final AsyncListener<CategoryImagesResponse> mCategoryImagesListener = new C33061();
    private SparseArray<String> mImagesMap;
    private boolean mImagesUpdateCalled;
    private LayoutInflater mInflater;
    private final int mResource;
    private RequestManagerServiceConnection mServiceConnection;

    /* renamed from: com.mcdonalds.app.nutrition.NutritionCategoryListAdapter$1 */
    class C33061 implements AsyncListener<CategoryImagesResponse> {
        C33061() {
        }

        public void onResponse(CategoryImagesResponse response, AsyncToken asyncToken, AsyncException e) {
            Ensighten.evaluateEvent(this, "onResponse", new Object[]{response, asyncToken, e});
            if (response != null) {
                NutritionCategoryListAdapter.access$302(NutritionCategoryListAdapter.this, new SparseArray());
                for (CategoryImage item : response.getCategories()) {
                    Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryListAdapter", "access$300", new Object[]{NutritionCategoryListAdapter.this}).put(item.getCategory(), item.getUrl());
                }
            }
            NutritionCategoryListAdapter.this.notifyDataSetChanged();
        }
    }

    public static class ViewHolder {
        private ImageView image;
        private TextView name;

        /* synthetic */ ViewHolder(View x0, C33061 x1) {
            this(x0);
        }

        private ViewHolder(View view) {
            this.image = (ImageView) view.findViewById(C2358R.C2357id.category_image);
            this.name = (TextView) view.findViewById(C2358R.C2357id.category_name);
        }
    }

    static /* synthetic */ SparseArray access$302(NutritionCategoryListAdapter x0, SparseArray x1) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryListAdapter", "access$302", new Object[]{x0, x1});
        x0.mImagesMap = x1;
        return x1;
    }

    public NutritionCategoryListAdapter(Context context, int resource) {
        super(context, resource);
        this.mInflater = LayoutInflater.from(context);
        this.mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = this.mInflater.inflate(this.mResource, parent, false);
            holder = new ViewHolder(convertView, null);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DataLayerClickListener.setDataLayerTag(convertView, ViewHolder.class, position);
        Category category = (Category) getItem(position);
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryListAdapter$ViewHolder", "access$100", new Object[]{holder}).setText(category.getName());
        loadImage(holder, category);
        Ensighten.getViewReturnValue(convertView, position);
        Ensighten.processView((Object) this, "getView");
        Ensighten.getViewReturnValue(null, -1);
        return convertView;
    }

    private void loadImage(ViewHolder holder, Category category) {
        Ensighten.evaluateEvent(this, "loadImage", new Object[]{holder, category});
        if (this.mServiceConnection != null && this.mImagesMap != null && getContext() != null) {
            Glide.with(getContext()).load((String) this.mImagesMap.get(category.getID())).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder((int) C2358R.C2359drawable.transparent).error((int) C2358R.C2359drawable.icon_large_meal).into(Ensighten.evaluateEvent(null, "com.mcdonalds.app.nutrition.NutritionCategoryListAdapter$ViewHolder", "access$200", new Object[]{holder}));
        }
    }

    public void setServiceConnection(RequestManagerServiceConnection serviceConnection) {
        Ensighten.evaluateEvent(this, "setServiceConnection", new Object[]{serviceConnection});
        this.mServiceConnection = serviceConnection;
        if (this.mServiceConnection != null && !this.mImagesUpdateCalled) {
            updateImages();
        }
    }

    private void updateImages() {
        Ensighten.evaluateEvent(this, "updateImages", null);
        this.mImagesUpdateCalled = true;
        String categories = (String) Configuration.getSharedInstance().getValueForKey("connectors.Middleware.nutritionCategoryMapping");
        if (categories != null) {
            this.mServiceConnection.processRequest(new CategoryImagesRequest(categories), this.mCategoryImagesListener);
        }
    }
}
