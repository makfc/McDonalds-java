package p046se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.widget.Checkable;

/* renamed from: se.emilsjolander.stickylistheaders.CheckableWrapperView */
class CheckableWrapperView extends WrapperView implements Checkable {
    public CheckableWrapperView(Context context) {
        super(context);
    }

    public boolean isChecked() {
        return ((Checkable) this.mItem).isChecked();
    }

    public void setChecked(boolean checked) {
        ((Checkable) this.mItem).setChecked(checked);
    }

    public void toggle() {
        setChecked(!isChecked());
    }
}
