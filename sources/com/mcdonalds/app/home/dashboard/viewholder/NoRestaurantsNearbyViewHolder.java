package com.mcdonalds.app.home.dashboard.viewholder;

import android.support.p001v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import com.ensighten.Ensighten;
import com.mcdonalds.gma.hongkong.C2658R;

public class NoRestaurantsNearbyViewHolder extends ViewHolder {
    public NoRestaurantsNearbyViewHolder(View view) {
        super(view);
    }

    public static ViewHolder create(LayoutInflater inflater) {
        Ensighten.evaluateEvent(null, "com.mcdonalds.app.home.dashboard.viewholder.NoRestaurantsNearbyViewHolder", "create", new Object[]{inflater});
        return new NoRestaurantsNearbyViewHolder(inflater.inflate(C2658R.layout.view_no_restaurants_nearby, null));
    }
}
