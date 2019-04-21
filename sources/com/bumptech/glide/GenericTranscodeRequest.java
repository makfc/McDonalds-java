package com.bumptech.glide;

import android.content.Context;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.load.resource.transcode.UnitTranscoder;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.provider.FixedLoadProvider;
import com.bumptech.glide.provider.LoadProvider;

public class GenericTranscodeRequest<ModelType, DataType, ResourceType> extends GenericRequestBuilder<ModelType, DataType, ResourceType, ResourceType> implements DownloadOptions {
    private final Class<DataType> dataClass;
    private final ModelLoader<ModelType, DataType> modelLoader;
    private final OptionsApplier optionsApplier;
    private final Class<ResourceType> resourceClass;

    private static <A, T, Z, R> LoadProvider<A, T, Z, R> build(Glide glide, ModelLoader<A, T> modelLoader, Class<T> dataClass, Class<Z> resourceClass, ResourceTranscoder<Z, R> transcoder) {
        return new FixedLoadProvider(modelLoader, transcoder, glide.buildDataProvider(dataClass, resourceClass));
    }

    GenericTranscodeRequest(Context context, Glide glide, Class<ModelType> modelClass, ModelLoader<ModelType, DataType> modelLoader, Class<DataType> dataClass, Class<ResourceType> resourceClass, RequestTracker requestTracker, Lifecycle lifecycle, OptionsApplier optionsApplier) {
        super(context, modelClass, build(glide, modelLoader, dataClass, resourceClass, UnitTranscoder.get()), resourceClass, glide, requestTracker, lifecycle);
        this.modelLoader = modelLoader;
        this.dataClass = dataClass;
        this.resourceClass = resourceClass;
        this.optionsApplier = optionsApplier;
    }
}
