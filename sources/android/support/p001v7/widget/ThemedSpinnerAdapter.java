package android.support.p001v7.widget;

import android.content.res.Resources.Theme;
import android.support.annotation.Nullable;
import android.widget.SpinnerAdapter;

/* renamed from: android.support.v7.widget.ThemedSpinnerAdapter */
public interface ThemedSpinnerAdapter extends SpinnerAdapter {

    /* renamed from: android.support.v7.widget.ThemedSpinnerAdapter$Helper */
    public static final class Helper {
    }

    @Nullable
    Theme getDropDownViewTheme();

    void setDropDownViewTheme(@Nullable Theme theme);
}
