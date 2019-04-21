package com.bumptech.glide.load.data;

import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

public class StreamAssetPathFetcher extends AssetPathFetcher<InputStream> {
    public StreamAssetPathFetcher(AssetManager assetManager, String assetPath) {
        super(assetManager, assetPath);
    }

    /* Access modifiers changed, original: protected */
    public InputStream loadResource(AssetManager assetManager, String path) throws IOException {
        return assetManager.open(path);
    }

    /* Access modifiers changed, original: protected */
    public void close(InputStream data) throws IOException {
        data.close();
    }
}
