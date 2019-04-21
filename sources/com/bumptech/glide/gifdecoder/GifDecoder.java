package com.bumptech.glide.gifdecoder;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.support.p000v4.app.FragmentTransaction;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class GifDecoder {
    private static final Config BITMAP_CONFIG = Config.ARGB_8888;
    private static final String TAG = GifDecoder.class.getSimpleName();
    private int[] act;
    private BitmapProvider bitmapProvider;
    private final byte[] block = new byte[256];
    private byte[] data;
    private int framePointer;
    private GifHeader header;
    private byte[] mainPixels;
    private int[] mainScratch;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public interface BitmapProvider {
        Bitmap obtain(int i, int i2, Config config);

        void release(Bitmap bitmap);
    }

    public GifDecoder(BitmapProvider provider) {
        this.bitmapProvider = provider;
        this.header = new GifHeader();
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public int getDelay(int n) {
        if (n < 0 || n >= this.header.frameCount) {
            return -1;
        }
        return ((GifFrame) this.header.frames.get(n)).delay;
    }

    public int getNextDelay() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            return -1;
        }
        return getDelay(this.framePointer);
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public int getLoopCount() {
        return this.header.loopCount;
    }

    public synchronized Bitmap getNextFrame() {
        Bitmap result = null;
        synchronized (this) {
            if (this.header.frameCount <= 0 || this.framePointer < 0) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "unable to decode frame, frameCount=" + this.header.frameCount + " framePointer=" + this.framePointer);
                }
                this.status = 1;
            }
            if (this.status != 1 && this.status != 2) {
                this.status = 0;
                GifFrame currentFrame = (GifFrame) this.header.frames.get(this.framePointer);
                GifFrame previousFrame = null;
                int previousIndex = this.framePointer - 1;
                if (previousIndex >= 0) {
                    previousFrame = (GifFrame) this.header.frames.get(previousIndex);
                }
                if (currentFrame.lct == null) {
                    this.act = this.header.gct;
                } else {
                    this.act = currentFrame.lct;
                    if (this.header.bgIndex == currentFrame.transIndex) {
                        this.header.bgColor = 0;
                    }
                }
                int save = 0;
                if (currentFrame.transparency) {
                    save = this.act[currentFrame.transIndex];
                    this.act[currentFrame.transIndex] = 0;
                }
                if (this.act == null) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "No Valid Color Table");
                    }
                    this.status = 1;
                } else {
                    result = setPixels(currentFrame, previousFrame);
                    if (currentFrame.transparency) {
                        this.act[currentFrame.transIndex] = save;
                    }
                }
            } else if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Unable to decode frame, status=" + this.status);
            }
        }
        return result;
    }

    public void clear() {
        this.header = null;
        this.data = null;
        this.mainPixels = null;
        this.mainScratch = null;
        if (this.previousImage != null) {
            this.bitmapProvider.release(this.previousImage);
        }
        this.previousImage = null;
        this.rawData = null;
    }

    public void setData(GifHeader header, byte[] data) {
        this.header = header;
        this.data = data;
        this.status = 0;
        this.framePointer = -1;
        this.rawData = ByteBuffer.wrap(data);
        this.rawData.rewind();
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        for (GifFrame frame : header.frames) {
            if (frame.dispose == 3) {
                this.savePrevious = true;
                break;
            }
        }
        this.mainPixels = new byte[(header.width * header.height)];
        this.mainScratch = new int[(header.width * header.height)];
    }

    private Bitmap setPixels(GifFrame currentFrame, GifFrame previousFrame) {
        int c;
        int width = this.header.width;
        int height = this.header.height;
        int[] dest = this.mainScratch;
        if (previousFrame != null && previousFrame.dispose > 0) {
            if (previousFrame.dispose == 2) {
                c = 0;
                if (!currentFrame.transparency) {
                    c = this.header.bgColor;
                }
                Arrays.fill(dest, c);
            } else if (previousFrame.dispose == 3 && this.previousImage != null) {
                this.previousImage.getPixels(dest, 0, width, 0, 0, width, height);
            }
        }
        decodeBitmapData(currentFrame);
        int pass = 1;
        int inc = 8;
        int iline = 0;
        for (int i = 0; i < currentFrame.f5569ih; i++) {
            int line = i;
            if (currentFrame.interlace) {
                if (iline >= currentFrame.f5569ih) {
                    pass++;
                    switch (pass) {
                        case 2:
                            iline = 4;
                            break;
                        case 3:
                            iline = 2;
                            inc = 4;
                            break;
                        case 4:
                            iline = 1;
                            inc = 2;
                            break;
                    }
                }
                line = iline;
                iline += inc;
            }
            line += currentFrame.f5572iy;
            if (line < this.header.height) {
                int k = line * this.header.width;
                int dx = k + currentFrame.f5571ix;
                int dlim = dx + currentFrame.f5570iw;
                if (this.header.width + k < dlim) {
                    dlim = k + this.header.width;
                }
                int sx = i * currentFrame.f5570iw;
                while (dx < dlim) {
                    int sx2 = sx + 1;
                    c = this.act[this.mainPixels[sx] & 255];
                    if (c != 0) {
                        dest[dx] = c;
                    }
                    dx++;
                    sx = sx2;
                }
            }
        }
        if (this.savePrevious && (currentFrame.dispose == 0 || currentFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            this.previousImage.setPixels(dest, 0, width, 0, 0, width, height);
        }
        Bitmap result = getNextBitmap();
        result.setPixels(dest, 0, width, 0, 0, width, height);
        return result;
    }

    private void decodeBitmapData(GifFrame frame) {
        int npix;
        int code;
        if (frame != null) {
            this.rawData.position(frame.bufferFrameStart);
        }
        if (frame == null) {
            npix = this.header.width * this.header.height;
        } else {
            npix = frame.f5570iw * frame.f5569ih;
        }
        if (this.mainPixels == null || this.mainPixels.length < npix) {
            this.mainPixels = new byte[npix];
        }
        if (this.prefix == null) {
            this.prefix = new short[4096];
        }
        if (this.suffix == null) {
            this.suffix = new byte[4096];
        }
        if (this.pixelStack == null) {
            this.pixelStack = new byte[FragmentTransaction.TRANSIT_FRAGMENT_OPEN];
        }
        int dataSize = read();
        int clear = 1 << dataSize;
        int endOfInformation = clear + 1;
        int available = clear + 2;
        int oldCode = -1;
        int codeSize = dataSize + 1;
        int codeMask = (1 << codeSize) - 1;
        for (code = 0; code < clear; code++) {
            this.prefix[code] = (short) 0;
            this.suffix[code] = (byte) code;
        }
        int bi = 0;
        int pi = 0;
        int top = 0;
        int first = 0;
        int count = 0;
        int bits = 0;
        int datum = 0;
        int i = 0;
        while (i < npix) {
            if (count == 0) {
                count = readBlock();
                if (count <= 0) {
                    this.status = 3;
                    break;
                }
                bi = 0;
            }
            datum += (this.block[bi] & 255) << bits;
            bits += 8;
            bi++;
            count--;
            int top2 = top;
            while (bits >= codeSize) {
                code = datum & codeMask;
                datum >>= codeSize;
                bits -= codeSize;
                if (code == clear) {
                    codeSize = dataSize + 1;
                    codeMask = (1 << codeSize) - 1;
                    available = clear + 2;
                    oldCode = -1;
                } else if (code > available) {
                    this.status = 3;
                    top = top2;
                    break;
                } else if (code == endOfInformation) {
                    top = top2;
                    break;
                } else if (oldCode == -1) {
                    top = top2 + 1;
                    this.pixelStack[top2] = this.suffix[code];
                    oldCode = code;
                    first = code;
                    top2 = top;
                } else {
                    int inCode = code;
                    if (code >= available) {
                        top = top2 + 1;
                        this.pixelStack[top2] = (byte) first;
                        code = oldCode;
                        top2 = top;
                    }
                    while (code >= clear) {
                        top = top2 + 1;
                        this.pixelStack[top2] = this.suffix[code];
                        code = this.prefix[code];
                        top2 = top;
                    }
                    first = this.suffix[code] & 255;
                    top = top2 + 1;
                    this.pixelStack[top2] = (byte) first;
                    if (available < 4096) {
                        this.prefix[available] = (short) oldCode;
                        this.suffix[available] = (byte) first;
                        available++;
                        if ((available & codeMask) == 0 && available < 4096) {
                            codeSize++;
                            codeMask += available;
                        }
                    }
                    oldCode = inCode;
                    int pi2 = pi;
                    while (top > 0) {
                        top--;
                        pi = pi2 + 1;
                        this.mainPixels[pi2] = this.pixelStack[top];
                        i++;
                        pi2 = pi;
                    }
                    pi = pi2;
                    top2 = top;
                }
            }
            top = top2;
        }
        for (i = pi; i < npix; i++) {
            this.mainPixels[i] = (byte) 0;
        }
    }

    private int read() {
        try {
            return this.rawData.get() & 255;
        } catch (Exception e) {
            this.status = 1;
            return 0;
        }
    }

    private int readBlock() {
        int blockSize = read();
        int n = 0;
        if (blockSize > 0) {
            while (n < blockSize) {
                int count = blockSize - n;
                try {
                    this.rawData.get(this.block, n, count);
                    n += count;
                } catch (Exception e) {
                    Log.w(TAG, "Error Reading Block", e);
                    this.status = 1;
                }
            }
        }
        return n;
    }

    private Bitmap getNextBitmap() {
        Bitmap result = this.bitmapProvider.obtain(this.header.width, this.header.height, BITMAP_CONFIG);
        if (result == null) {
            result = Bitmap.createBitmap(this.header.width, this.header.height, BITMAP_CONFIG);
        }
        setAlpha(result);
        return result;
    }

    @TargetApi(12)
    private static void setAlpha(Bitmap bitmap) {
        if (VERSION.SDK_INT >= 12) {
            bitmap.setHasAlpha(true);
        }
    }
}
