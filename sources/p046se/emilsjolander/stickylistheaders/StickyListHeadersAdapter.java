package p046se.emilsjolander.stickylistheaders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

/* renamed from: se.emilsjolander.stickylistheaders.StickyListHeadersAdapter */
public interface StickyListHeadersAdapter extends ListAdapter {
    long getHeaderId(int i);

    View getHeaderView(int i, View view, ViewGroup viewGroup);
}
