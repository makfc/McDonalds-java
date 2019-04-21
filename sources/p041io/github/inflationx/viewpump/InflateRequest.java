package p041io.github.inflationx.viewpump;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/* renamed from: io.github.inflationx.viewpump.InflateRequest */
public class InflateRequest {
    private final AttributeSet attrs;
    private final Context context;
    private final FallbackViewCreator fallbackViewCreator;
    private final String name;
    private final View parent;

    /* renamed from: io.github.inflationx.viewpump.InflateRequest$Builder */
    public static final class Builder {
        private AttributeSet attrs;
        private Context context;
        private FallbackViewCreator fallbackViewCreator;
        private String name;
        private View parent;

        private Builder() {
        }

        private Builder(InflateRequest request) {
            this.name = request.name;
            this.context = request.context;
            this.attrs = request.attrs;
            this.parent = request.parent;
            this.fallbackViewCreator = request.fallbackViewCreator;
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

        public Builder parent(@Nullable View parent) {
            this.parent = parent;
            return this;
        }

        public Builder fallbackViewCreator(@NonNull FallbackViewCreator fallbackViewCreator) {
            this.fallbackViewCreator = fallbackViewCreator;
            return this;
        }

        public InflateRequest build() {
            if (this.name == null) {
                throw new IllegalStateException("name == null");
            } else if (this.context == null) {
                throw new IllegalStateException("context == null");
            } else if (this.fallbackViewCreator != null) {
                return new InflateRequest(this);
            } else {
                throw new IllegalStateException("fallbackViewCreator == null");
            }
        }
    }

    private InflateRequest(Builder builder) {
        this.name = builder.name;
        this.context = builder.context;
        this.attrs = builder.attrs;
        this.parent = builder.parent;
        this.fallbackViewCreator = builder.fallbackViewCreator;
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

    @Nullable
    public View parent() {
        return this.parent;
    }

    @NonNull
    public FallbackViewCreator fallbackViewCreator() {
        return this.fallbackViewCreator;
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
        return "InflateRequest{name='" + this.name + '\'' + ", context=" + this.context + ", attrs=" + this.attrs + ", parent=" + this.parent + ", fallbackViewCreator=" + this.fallbackViewCreator + '}';
    }
}
