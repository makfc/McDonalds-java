package com.bumptech.glide.load.data;

import com.bumptech.glide.Priority;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayFetcher implements DataFetcher<InputStream> {
    private final byte[] bytes;
    /* renamed from: id */
    private final String f5574id;

    public ByteArrayFetcher(byte[] bytes, String id) {
        this.bytes = bytes;
        this.f5574id = id;
    }

    public InputStream loadData(Priority priority) {
        return new ByteArrayInputStream(this.bytes);
    }

    public void cleanup() {
    }

    public String getId() {
        return this.f5574id;
    }

    public void cancel() {
    }
}
