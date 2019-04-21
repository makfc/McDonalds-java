package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.Descriptor.Host;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.annotation.Nullable;

public final class DescriptorMap {
    private Host mHost;
    private boolean mIsInitializing;
    private final Map<Class<?>, Descriptor> mMap = new IdentityHashMap();

    public DescriptorMap beginInit() {
        Util.throwIf(this.mIsInitializing);
        this.mIsInitializing = true;
        return this;
    }

    public DescriptorMap register(Class<?> elementClass, Descriptor descriptor) {
        Util.throwIfNull(elementClass);
        Util.throwIfNull(descriptor);
        Util.throwIf(descriptor.isInitialized());
        Util.throwIfNot(this.mIsInitializing);
        if (this.mMap.containsKey(elementClass)) {
            throw new UnsupportedOperationException();
        } else if (this.mMap.containsValue(descriptor)) {
            throw new UnsupportedOperationException();
        } else {
            this.mMap.put(elementClass, descriptor);
            return this;
        }
    }

    public DescriptorMap setHost(Host host) {
        Util.throwIfNull(host);
        Util.throwIfNot(this.mIsInitializing);
        Util.throwIfNotNull(this.mHost);
        this.mHost = host;
        return this;
    }

    public DescriptorMap endInit() {
        Util.throwIfNot(this.mIsInitializing);
        Util.throwIfNull(this.mHost);
        this.mIsInitializing = false;
        for (Class<?> elementClass : this.mMap.keySet()) {
            Descriptor descriptor = (Descriptor) this.mMap.get(elementClass);
            if (descriptor instanceof ChainedDescriptor) {
                ((ChainedDescriptor) descriptor).setSuper(getImpl(elementClass.getSuperclass()));
            }
            descriptor.initialize(this.mHost);
        }
        return this;
    }

    @Nullable
    public Descriptor get(Class<?> elementClass) {
        Util.throwIfNull(elementClass);
        Util.throwIf(this.mIsInitializing);
        return getImpl(elementClass);
    }

    @Nullable
    private Descriptor getImpl(Class<?> elementClass) {
        for (Class<?> theClass = elementClass; theClass != null; theClass = theClass.getSuperclass()) {
            Descriptor descriptor = (Descriptor) this.mMap.get(theClass);
            if (descriptor != null) {
                return descriptor;
            }
        }
        return null;
    }
}
