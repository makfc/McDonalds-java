package p041io.github.inflationx.viewpump;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/* renamed from: io.github.inflationx.viewpump.InflateResult */
public class InflateResult {
    private final AttributeSet attrs;
    private final Context context;
    private final String name;
    private final View view;

    /* renamed from: io.github.inflationx.viewpump.InflateResult$Builder */
    public static final class Builder {
        private AttributeSet attrs;
        private Context context;
        private String name;
        private View view;

        private Builder() {
        }

        private Builder(InflateResult result) {
            this.view = result.view;
            this.name = result.name;
            this.context = result.context;
            this.attrs = result.attrs;
        }

        public Builder view(@Nullable View view) {
            this.view = view;
            return this;
        }

        public Builder name(@NonNull String name) {
            this.name = name;
            return this;
        }

        public Builder context(@NonNull Context context) {
            this.context = context;
            return this;
        }

        public Builder attrs(@Nullable AttributeSet attrs) {
            this.attrs = attrs;
            return this;
        }

        public InflateResult build() {
            if (this.name == null) {
                throw new IllegalStateException("name == null");
            } else if (this.context == null) {
                throw new IllegalStateException("context == null");
            } else if (this.view == null || this.name.equals(this.view.getClass().getName())) {
                return new InflateResult(this);
            } else {
                throw new IllegalStateException("name (" + this.name + ") must be the view's fully qualified name (" + this.view.getClass().getName() + ")");
            }
        }
    }

    private InflateResult(Builder builder) {
        this.view = builder.view;
        this.name = builder.name;
        this.context = builder.context;
        this.attrs = builder.attrs;
    }

    @Nullable
    public View view() {
        return this.view;
    }

    @NonNull
    public String name() {
        return this.name;
    }

    @NonNull
    public Context context() {
        return this.context;
    }

    @Nullable
    public AttributeSet attrs() {
        return this.attrs;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder();
    }

    @NonNull
    public String toString() {
        return "InflateResult{view=" + this.view + ", name=" + this.name + ", context=" + this.context + ", attrs=" + this.attrs + '}';
    }
}
