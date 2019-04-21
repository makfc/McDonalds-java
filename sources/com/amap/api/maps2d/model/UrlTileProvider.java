package com.amap.api.maps2d.model;

import com.amap.api.mapcore2d.C0955ck;
import com.amap.api.mapcore2d.LogManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public abstract class UrlTileProvider implements TileProvider {
    /* renamed from: a */
    private final int f3488a;
    /* renamed from: b */
    private final int f3489b;

    public abstract URL getTileUrl(int i, int i2, int i3);

    public UrlTileProvider(int i, int i2) {
        this.f3488a = i;
        this.f3489b = i2;
    }

    public final Tile getTile(int i, int i2, int i3) {
        String str = "getTile";
        URL tileUrl = getTileUrl(i, i2, i3);
        if (tileUrl == null) {
            return NO_TILE;
        }
        LogManager.m3875a("UrlTileProvider", "url: " + tileUrl.toString(), 111);
        try {
            return new Tile(this.f3488a, this.f3489b, m4582a(tileUrl.openStream()));
        } catch (IOException e) {
            C0955ck.m3888a(e, "UrlTileProvider", str);
            return NO_TILE;
        }
    }

    /* renamed from: a */
    private static byte[] m4582a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        m4581a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    private static long m4581a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    public int getTileWidth() {
        return this.f3488a;
    }

    public int getTileHeight() {
        return this.f3489b;
    }
}
