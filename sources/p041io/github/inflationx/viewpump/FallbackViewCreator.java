package p041io.github.inflationx.viewpump;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/* renamed from: io.github.inflationx.viewpump.FallbackViewCreator */
public interface FallbackViewCreator {
    @Nullable
    View onCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @Nullable AttributeSet attributeSet);
}
