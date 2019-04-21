package com.mcdonalds.app.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface CollectionViewCallbacks {
    void bindCollectionHeaderView(Context context, View view, int i, String str);

    void bindCollectionItemView(Context context, View view, int i, int i2, int i3, Object obj);

    View newCollectionHeaderView(Context context, ViewGroup viewGroup);

    View newCollectionItemView(Context context, int i, ViewGroup viewGroup);
}
