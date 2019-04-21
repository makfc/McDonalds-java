package p041io.github.inflationx.viewpump;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/* renamed from: io.github.inflationx.viewpump.ViewPumpContextWrapper */
public final class ViewPumpContextWrapper extends ContextWrapper {
    private ViewPumpLayoutInflater mInflater;

    public static ContextWrapper wrap(@NonNull Context base) {
        return new ViewPumpContextWrapper(base);
    }

    @Nullable
    public static View onActivityCreateView(Activity activity, View parent, View view, String name, Context context, AttributeSet attr) {
        return ViewPumpContextWrapper.get(activity).onActivityCreateView(parent, view, name, context, attr);
    }

    static ViewPumpActivityFactory get(@NonNull Activity activity) {
        if (activity.getLayoutInflater() instanceof ViewPumpLayoutInflater) {
            return (ViewPumpActivityFactory) activity.getLayoutInflater();
        }
        throw new RuntimeException("This activity does not wrap the Base Context! See ViewPumpContextWrapper.wrap(Context)");
    }

    private ViewPumpContextWrapper(Context base) {
        super(base);
    }

    public Object getSystemService(String name) {
        if (!"layout_inflater".equals(name)) {
            return super.getSystemService(name);
        }
        if (this.mInflater == null) {
            this.mInflater = new ViewPumpLayoutInflater(LayoutInflater.from(getBaseContext()), this, false);
        }
        return this.mInflater;
    }
}
