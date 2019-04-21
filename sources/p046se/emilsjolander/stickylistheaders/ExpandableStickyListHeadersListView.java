package p046se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.util.AttributeSet;

/* renamed from: se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView */
public class ExpandableStickyListHeadersListView extends StickyListHeadersListView {
    IAnimationExecutor mDefaultAnimExecutor = new C46411();
    ExpandableStickyListHeadersAdapter mExpandableStickyListHeadersAdapter;

    /* renamed from: se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView$IAnimationExecutor */
    public interface IAnimationExecutor {
    }

    /* renamed from: se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView$1 */
    class C46411 implements IAnimationExecutor {
        C46411() {
        }
    }

    public ExpandableStickyListHeadersListView(Context context) {
        super(context);
    }

    public ExpandableStickyListHeadersListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableStickyListHeadersListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ExpandableStickyListHeadersAdapter getAdapter() {
        return this.mExpandableStickyListHeadersAdapter;
    }

    public void setAdapter(StickyListHeadersAdapter adapter) {
        this.mExpandableStickyListHeadersAdapter = new ExpandableStickyListHeadersAdapter(adapter);
        super.setAdapter(this.mExpandableStickyListHeadersAdapter);
    }

    public void setAnimExecutor(IAnimationExecutor animExecutor) {
        this.mDefaultAnimExecutor = animExecutor;
    }
}
