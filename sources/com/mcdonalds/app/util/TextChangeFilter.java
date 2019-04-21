package com.mcdonalds.app.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Filter;
import android.widget.Filter.FilterListener;
import com.ensighten.Ensighten;
import com.mcdonalds.app.analytics.datalayer.DataLayerManager;

public class TextChangeFilter implements TextWatcher {
    private Filter mFilter;

    public TextChangeFilter(Filter filter) {
        this.mFilter = filter;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Ensighten.evaluateEvent(this, "beforeTextChanged", new Object[]{charSequence, new Integer(i), new Integer(i2), new Integer(i3)});
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Ensighten.evaluateEvent(this, "onTextChanged", new Object[]{charSequence, new Integer(i), new Integer(i2), new Integer(i3)});
        final String searchText = charSequence.toString();
        this.mFilter.filter(charSequence, new FilterListener() {
            public void onFilterComplete(int count) {
                Ensighten.evaluateEvent(this, "onFilterComplete", new Object[]{new Integer(count)});
                DataLayerManager.getInstance().setSearchTerm(searchText, null, null, count);
            }
        });
    }

    public void afterTextChanged(Editable editable) {
        Ensighten.evaluateEvent(this, "afterTextChanged", new Object[]{editable});
    }
}
