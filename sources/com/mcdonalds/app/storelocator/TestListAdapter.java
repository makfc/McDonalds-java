package com.mcdonalds.app.storelocator;

import android.view.View;
import android.view.ViewGroup;
import com.ensighten.Ensighten;
import com.mcdonalds.app.p043ui.listview.ExpandableListItemAdapter;

public class TestListAdapter<T> extends ExpandableListItemAdapter<T> {
    public View getTitleView(int position, View convertView, ViewGroup parent) {
        Ensighten.evaluateEvent(this, "getTitleView", new Object[]{new Integer(position), convertView, parent});
        return null;
    }

    public View getContentView(int position, View convertView, ViewGroup parent) {
        Ensighten.evaluateEvent(this, "getContentView", new Object[]{new Integer(position), convertView, parent});
        return null;
    }
}
