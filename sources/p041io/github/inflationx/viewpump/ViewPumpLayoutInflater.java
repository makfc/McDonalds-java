package p041io.github.inflationx.viewpump;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: io.github.inflationx.viewpump.ViewPumpLayoutInflater */
class ViewPumpLayoutInflater extends LayoutInflater implements ViewPumpActivityFactory {
    private static final String[] sClassPrefixList = new String[]{"android.widget.", "android.webkit."};
    private Field mConstructorArgs = null;
    private boolean mSetPrivateFactory = false;
    private FallbackViewCreator nameAndAttrsViewCreator = new NameAndAttrsViewCreator(this);
    private FallbackViewCreator parentAndNameAndAttrsViewCreator = new ParentAndNameAndAttrsViewCreator(this);

    /* renamed from: io.github.inflationx.viewpump.ViewPumpLayoutInflater$ActivityViewCreator */
    private static class ActivityViewCreator implements FallbackViewCreator {
        private final ViewPumpLayoutInflater inflater;
        private final View view;

        public ActivityViewCreator(ViewPumpLayoutInflater inflater, View view) {
            this.inflater = inflater;
            this.view = view;
        }

        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            return this.inflater.createCustomViewInternal(parent, this.view, name, context, attrs);
        }
    }

    /* renamed from: io.github.inflationx.viewpump.ViewPumpLayoutInflater$NameAndAttrsViewCreator */
    private static class NameAndAttrsViewCreator implements FallbackViewCreator {
        private final ViewPumpLayoutInflater inflater;

        public NameAndAttrsViewCreator(ViewPumpLayoutInflater inflater) {
            this.inflater = inflater;
        }

        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            View view = null;
            String[] access$200 = ViewPumpLayoutInflater.sClassPrefixList;
            int length = access$200.length;
            int i = 0;
            while (i < length) {
                try {
                    view = this.inflater.createView(name, access$200[i], attrs);
                    if (view != null) {
                        break;
                    }
                    i++;
                } catch (ClassNotFoundException e) {
                }
            }
            if (view == null) {
                return this.inflater.superOnCreateView(name, attrs);
            }
            return view;
        }
    }

    /* renamed from: io.github.inflationx.viewpump.ViewPumpLayoutInflater$ParentAndNameAndAttrsViewCreator */
    private static class ParentAndNameAndAttrsViewCreator implements FallbackViewCreator {
        private final ViewPumpLayoutInflater inflater;

        public ParentAndNameAndAttrsViewCreator(ViewPumpLayoutInflater inflater) {
            this.inflater = inflater;
        }

        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            return this.inflater.superOnCreateView(parent, name, attrs);
        }
    }

    /* renamed from: io.github.inflationx.viewpump.ViewPumpLayoutInflater$WrapperFactory2 */
    private static class WrapperFactory2 implements Factory2 {
        protected final Factory2 mFactory2;
        private final WrapperFactory2ViewCreator mViewCreator;

        public WrapperFactory2(Factory2 factory2) {
            this.mFactory2 = factory2;
            this.mViewCreator = new WrapperFactory2ViewCreator(factory2);
        }

        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return onCreateView(null, name, context, attrs);
        }

        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            return ViewPump.get().inflate(InflateRequest.builder().name(name).context(context).attrs(attrs).parent(parent).fallbackViewCreator(this.mViewCreator).build()).view();
        }
    }

    /* renamed from: io.github.inflationx.viewpump.ViewPumpLayoutInflater$PrivateWrapperFactory2 */
    private static class PrivateWrapperFactory2 extends WrapperFactory2 {
        private final PrivateWrapperFactory2ViewCreator mViewCreator;

        public PrivateWrapperFactory2(Factory2 factory2, ViewPumpLayoutInflater inflater) {
            super(factory2);
            this.mViewCreator = new PrivateWrapperFactory2ViewCreator(factory2, inflater);
        }

        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            return ViewPump.get().inflate(InflateRequest.builder().name(name).context(context).attrs(attrs).parent(parent).fallbackViewCreator(this.mViewCreator).build()).view();
        }
    }

    /* renamed from: io.github.inflationx.viewpump.ViewPumpLayoutInflater$WrapperFactory2ViewCreator */
    private static class WrapperFactory2ViewCreator implements FallbackViewCreator {
        protected final Factory2 mFactory2;

        public WrapperFactory2ViewCreator(Factory2 factory2) {
            this.mFactory2 = factory2;
        }

        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            return this.mFactory2.onCreateView(parent, name, context, attrs);
        }
    }

    /* renamed from: io.github.inflationx.viewpump.ViewPumpLayoutInflater$PrivateWrapperFactory2ViewCreator */
    private static class PrivateWrapperFactory2ViewCreator extends WrapperFactory2ViewCreator implements FallbackViewCreator {
        private final ViewPumpLayoutInflater mInflater;

        public PrivateWrapperFactory2ViewCreator(Factory2 factory2, ViewPumpLayoutInflater mInflater) {
            super(factory2);
            this.mInflater = mInflater;
        }

        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            return this.mInflater.createCustomViewInternal(parent, this.mFactory2.onCreateView(parent, name, context, attrs), name, context, attrs);
        }
    }

    /* renamed from: io.github.inflationx.viewpump.ViewPumpLayoutInflater$WrapperFactory */
    private static class WrapperFactory implements Factory {
        private final FallbackViewCreator mViewCreator;

        public WrapperFactory(Factory factory) {
            this.mViewCreator = new WrapperFactoryViewCreator(factory);
        }

        public View onCreateView(String name, Context context, AttributeSet attrs) {
            return ViewPump.get().inflate(InflateRequest.builder().name(name).context(context).attrs(attrs).fallbackViewCreator(this.mViewCreator).build()).view();
        }
    }

    /* renamed from: io.github.inflationx.viewpump.ViewPumpLayoutInflater$WrapperFactoryViewCreator */
    private static class WrapperFactoryViewCreator implements FallbackViewCreator {
        protected final Factory mFactory;

        public WrapperFactoryViewCreator(Factory factory) {
            this.mFactory = factory;
        }

        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            return this.mFactory.onCreateView(name, context, attrs);
        }
    }

    protected ViewPumpLayoutInflater(Context context) {
        super(context);
        setUpLayoutFactories(false);
    }

    protected ViewPumpLayoutInflater(LayoutInflater original, Context newContext, boolean cloned) {
        super(original, newContext);
        setUpLayoutFactories(cloned);
    }

    public LayoutInflater cloneInContext(Context newContext) {
        return new ViewPumpLayoutInflater(this, newContext, true);
    }

    public View inflate(XmlPullParser parser, ViewGroup root, boolean attachToRoot) {
        setPrivateFactoryInternal();
        return super.inflate(parser, root, attachToRoot);
    }

    private void setUpLayoutFactories(boolean cloned) {
        if (!cloned) {
            if (!(getFactory2() == null || (getFactory2() instanceof WrapperFactory2))) {
                setFactory2(getFactory2());
            }
            if (getFactory() != null && !(getFactory() instanceof WrapperFactory)) {
                setFactory(getFactory());
            }
        }
    }

    public void setFactory(Factory factory) {
        if (factory instanceof WrapperFactory) {
            super.setFactory(factory);
        } else {
            super.setFactory(new WrapperFactory(factory));
        }
    }

    public void setFactory2(Factory2 factory2) {
        if (factory2 instanceof WrapperFactory2) {
            super.setFactory2(factory2);
        } else {
            super.setFactory2(new WrapperFactory2(factory2));
        }
    }

    private void setPrivateFactoryInternal() {
        if (this.mSetPrivateFactory || !ViewPump.get().isReflection()) {
            return;
        }
        if (getContext() instanceof Factory2) {
            Method setPrivateFactoryMethod = ReflectionUtils.getMethod(LayoutInflater.class, "setPrivateFactory");
            if (setPrivateFactoryMethod != null) {
                ReflectionUtils.invokeMethod(this, setPrivateFactoryMethod, new PrivateWrapperFactory2((Factory2) getContext(), this));
            }
            this.mSetPrivateFactory = true;
            return;
        }
        this.mSetPrivateFactory = true;
    }

    public View onActivityCreateView(View parent, View view, String name, Context context, AttributeSet attrs) {
        return ViewPump.get().inflate(InflateRequest.builder().name(name).context(context).attrs(attrs).parent(parent).fallbackViewCreator(new ActivityViewCreator(this, view)).build()).view();
    }

    /* Access modifiers changed, original: protected */
    public View onCreateView(View parent, String name, AttributeSet attrs) throws ClassNotFoundException {
        return ViewPump.get().inflate(InflateRequest.builder().name(name).context(getContext()).attrs(attrs).parent(parent).fallbackViewCreator(this.parentAndNameAndAttrsViewCreator).build()).view();
    }

    /* Access modifiers changed, original: protected */
    public View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        return ViewPump.get().inflate(InflateRequest.builder().name(name).context(getContext()).attrs(attrs).fallbackViewCreator(this.nameAndAttrsViewCreator).build()).view();
    }

    private View createCustomViewInternal(View parent, View view, String name, Context viewContext, AttributeSet attrs) {
        if (!ViewPump.get().isCustomViewCreation()) {
            return view;
        }
        if (view == null && name.indexOf(46) > -1) {
            if (this.mConstructorArgs == null) {
                this.mConstructorArgs = ReflectionUtils.getField(LayoutInflater.class, "mConstructorArgs");
            }
            Object[] mConstructorArgsArr = (Object[]) ReflectionUtils.getValue(this.mConstructorArgs, this);
            Object lastContext = mConstructorArgsArr[0];
            mConstructorArgsArr[0] = viewContext;
            ReflectionUtils.setValue(this.mConstructorArgs, this, mConstructorArgsArr);
            try {
                view = createView(name, null, attrs);
            } catch (ClassNotFoundException e) {
            } finally {
                mConstructorArgsArr[0] = lastContext;
                ReflectionUtils.setValue(this.mConstructorArgs, this, mConstructorArgsArr);
            }
        }
        return view;
    }

    private View superOnCreateView(View parent, String name, AttributeSet attrs) {
        try {
            return super.onCreateView(parent, name, attrs);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private View superOnCreateView(String name, AttributeSet attrs) {
        try {
            return super.onCreateView(name, attrs);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
