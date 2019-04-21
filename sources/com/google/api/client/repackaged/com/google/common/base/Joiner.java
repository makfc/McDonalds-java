package com.google.api.client.repackaged.com.google.common.base;

import com.google.api.client.repackaged.com.google.common.annotations.GwtCompatible;
import java.io.IOException;
import java.util.AbstractList;
import java.util.Iterator;

@GwtCompatible
public class Joiner {
    private final String separator;

    /* renamed from: com.google.api.client.repackaged.com.google.common.base.Joiner$1 */
    class C27601 extends Joiner {
        final /* synthetic */ Joiner this$0;
        final /* synthetic */ String val$nullText;

        /* Access modifiers changed, original: 0000 */
        public CharSequence toString(Object part) {
            return part == null ? this.val$nullText : this.this$0.toString(part);
        }
    }

    /* renamed from: com.google.api.client.repackaged.com.google.common.base.Joiner$2 */
    class C27612 extends Joiner {
        final /* synthetic */ Joiner this$0;

        public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
            Object part;
            Preconditions.checkNotNull(appendable, "appendable");
            Preconditions.checkNotNull(parts, "parts");
            while (parts.hasNext()) {
                part = parts.next();
                if (part != null) {
                    appendable.append(this.this$0.toString(part));
                    break;
                }
            }
            while (parts.hasNext()) {
                part = parts.next();
                if (part != null) {
                    appendable.append(this.this$0.separator);
                    appendable.append(this.this$0.toString(part));
                }
            }
            return appendable;
        }
    }

    /* renamed from: com.google.api.client.repackaged.com.google.common.base.Joiner$3 */
    static class C27623 extends AbstractList<Object> {
        final /* synthetic */ Object val$first;
        final /* synthetic */ Object[] val$rest;
        final /* synthetic */ Object val$second;

        public int size() {
            return this.val$rest.length + 2;
        }

        public Object get(int index) {
            switch (index) {
                case 0:
                    return this.val$first;
                case 1:
                    return this.val$second;
                default:
                    return this.val$rest[index - 2];
            }
        }
    }

    public static final class MapJoiner {
    }

    /* renamed from: on */
    public static Joiner m7507on(char separator) {
        return new Joiner(String.valueOf(separator));
    }

    private Joiner(String separator) {
        this.separator = (String) Preconditions.checkNotNull(separator);
    }

    public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
        Preconditions.checkNotNull(appendable);
        if (parts.hasNext()) {
            appendable.append(toString(parts.next()));
            while (parts.hasNext()) {
                appendable.append(this.separator);
                appendable.append(toString(parts.next()));
            }
        }
        return appendable;
    }

    public final StringBuilder appendTo(StringBuilder builder, Iterator<?> parts) {
        try {
            appendTo((Appendable) builder, (Iterator) parts);
            return builder;
        } catch (IOException impossible) {
            throw new AssertionError(impossible);
        }
    }

    public final String join(Iterable<?> parts) {
        return join(parts.iterator());
    }

    public final String join(Iterator<?> parts) {
        return appendTo(new StringBuilder(), (Iterator) parts).toString();
    }

    /* Access modifiers changed, original: 0000 */
    public CharSequence toString(Object part) {
        Preconditions.checkNotNull(part);
        return part instanceof CharSequence ? (CharSequence) part : part.toString();
    }
}
