package com.autonavi.amap.mapcore;

import android.text.TextUtils;
import com.amap.api.mapcore.util.ProxyUtil;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

public abstract class BaseMapLoader {
    long createtime;
    int datasource = 0;
    public HttpURLConnection httpURLConnection = null;
    volatile boolean inRequest = false;
    volatile boolean isFinished = false;
    volatile boolean mCanceled = false;
    int mCapaticy = 30720;
    int mCapaticyExt = 10240;
    MapCore mGLMapEngine;
    BaseMapCallImplement mMapCallback;
    long m_reqestStartLen;
    int mapLevel;
    public ArrayList<MapSourceGridData> mapTiles = new ArrayList();
    int nextImgDataLength = 0;
    byte[] recievedDataBuffer;
    int recievedDataSize = 0;
    boolean recievedHeader = false;

    public abstract String getGridParma();

    public abstract String getMapAddress();

    public abstract String getMapSvrPath();

    public abstract boolean isNeedProcessReturn();

    public abstract boolean isRequestValid();

    public abstract boolean processReceivedDataHeader(int i);

    public abstract void processRecivedDataByType();

    public abstract void processRecivedVersionOrScenicWidgetData();

    /* Access modifiers changed, original: protected */
    public void processReceivedTileDataV4(byte[] bArr, int i, int i2) {
        String str;
        int i3 = i + 4;
        int i4 = i3 + 1;
        byte b = bArr[i3];
        String str2 = "";
        if (b <= (byte) 0 || (i4 + b) - 1 >= i2) {
            str = str2;
        } else {
            str = new String(bArr, i4, b);
        }
        i3 = i4 + b;
        if (this.mGLMapEngine.isMapEngineValid() && i2 > i) {
            int i5 = !this.mMapCallback.isGridInScreen(this.datasource, str) ? 1 : 0;
            if (this.mGLMapEngine.putMapData(bArr, i, i2 - i, this.datasource, 0)) {
                VMapDataCache.getInstance().putRecoder(null, str, this.datasource);
            }
            if (i5 != 0) {
                doCancel();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public String getURL(String str, String str2, String str3) {
        String str4 = "";
        str4 = "";
        return str + str2 + str3;
    }

    /* Access modifiers changed, original: protected */
    public void initTestTime() {
        this.m_reqestStartLen = System.currentTimeMillis();
    }

    /* Access modifiers changed, original: protected */
    public void privteTestTime(String str, String str2) {
    }

    /* Access modifiers changed, original: protected */
    public boolean isAssic(String str) {
        if (str == null) {
            return false;
        }
        char[] toCharArray = str.toCharArray();
        int i = 0;
        while (i < toCharArray.length) {
            if (toCharArray[i] >= 256 || toCharArray[i] <= 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    /* Access modifiers changed, original: protected */
    public boolean containllegal(String str) {
        if (str.contains("<") || str.contains("[")) {
            return true;
        }
        return false;
    }

    public void OnException(int i) {
        privteTestTime("", " network error:" + i);
        this.isFinished = true;
        if (this.datasource == 6 || this.datasource == 4 || this.datasource == 1 || this.mCanceled) {
        }
        this.isFinished = true;
    }

    public synchronized boolean hasFinished() {
        boolean z;
        z = this.mCanceled || this.isFinished;
        return z;
    }

    public synchronized void doCancel() {
        if (!(this.mCanceled || this.isFinished)) {
            this.mCanceled = true;
            try {
                if (this.httpURLConnection != null && this.inRequest) {
                    this.httpURLConnection.disconnect();
                }
            } catch (Throwable th) {
            } finally {
                onConnectionOver();
            }
        }
    }

    private synchronized void onConnectionOver() {
        int i = 0;
        synchronized (this) {
            processRecivedVersionOrScenicWidgetData();
            this.recievedDataBuffer = null;
            this.nextImgDataLength = 0;
            this.recievedDataSize = 0;
            while (true) {
                try {
                    int i2 = i;
                    if (i2 >= this.mapTiles.size()) {
                        break;
                    }
                    this.mMapCallback.tileProcessCtrl.mo13426a(((MapSourceGridData) this.mapTiles.get(i2)).keyGridName);
                    i = i2 + 1;
                } catch (Exception e) {
                }
            }
            this.isFinished = true;
        }
    }

    public void doRequest() {
        Throwable th;
        if (!this.mCanceled && !this.isFinished) {
            if (isRequestValid()) {
                String mapAddress = getMapAddress();
                String mapSvrPath = getMapSvrPath();
                if (mapSvrPath != null && mapSvrPath.length() != 0 && mapAddress != null) {
                    InputStream inputStream = null;
                    String gridParma = getGridParma();
                    if (!TextUtils.isEmpty(gridParma)) {
                        this.inRequest = true;
                        try {
                            InputStream inputStream2;
                            Proxy a = ProxyUtil.m2471a(this.mMapCallback.getContext());
                            mapAddress = getURL(mapAddress, mapSvrPath, gridParma);
                            if (a != null) {
                                this.httpURLConnection = (HttpURLConnection) HttpInstrumentation.openConnectionWithProxy(new URL(mapAddress).openConnection(a));
                            } else {
                                this.httpURLConnection = (HttpURLConnection) HttpInstrumentation.openConnection(new URL(mapAddress).openConnection());
                            }
                            this.httpURLConnection.setConnectTimeout(20000);
                            this.httpURLConnection.setRequestMethod("GET");
                            if (this.httpURLConnection != null) {
                                this.httpURLConnection.connect();
                                if (this.httpURLConnection.getResponseCode() == 200) {
                                    inputStream2 = this.httpURLConnection.getInputStream();
                                    try {
                                        onConnectionOpened();
                                        byte[] bArr = new byte[512];
                                        boolean z = true;
                                        while (true) {
                                            int read = inputStream2.read(bArr);
                                            if (read <= -1) {
                                                break;
                                            }
                                            if (z) {
                                                privteTestTime("recievedFirstByte:", "");
                                                z = false;
                                            }
                                            if (this.mCanceled) {
                                                break;
                                            }
                                            onConnectionRecieveData(bArr, read);
                                        }
                                    } catch (IllegalArgumentException e) {
                                        inputStream = inputStream2;
                                        onConnectionOver();
                                        if (inputStream != null && !this.mCanceled) {
                                            try {
                                                inputStream.close();
                                                return;
                                            } catch (IOException e2) {
                                                OnException(1002);
                                                return;
                                            }
                                        }
                                        return;
                                    } catch (SecurityException e3) {
                                        inputStream = inputStream2;
                                        onConnectionOver();
                                        if (inputStream != null) {
                                            return;
                                        }
                                        return;
                                    } catch (OutOfMemoryError e4) {
                                        inputStream = inputStream2;
                                        onConnectionOver();
                                        if (inputStream != null) {
                                            return;
                                        }
                                        return;
                                    } catch (IllegalStateException e5) {
                                        inputStream = inputStream2;
                                        onConnectionOver();
                                        if (inputStream != null) {
                                            return;
                                        }
                                        return;
                                    } catch (IOException e6) {
                                        inputStream = inputStream2;
                                        try {
                                            OnException(1002);
                                            onConnectionOver();
                                            if (inputStream != null) {
                                                return;
                                            }
                                            return;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            onConnectionOver();
                                            if (!(inputStream == null || this.mCanceled)) {
                                                try {
                                                    inputStream.close();
                                                } catch (IOException e7) {
                                                    OnException(1002);
                                                }
                                            }
                                            throw th;
                                        }
                                    } catch (NullPointerException e8) {
                                        inputStream = inputStream2;
                                        onConnectionOver();
                                        if (inputStream != null) {
                                            return;
                                        }
                                        return;
                                    } catch (Throwable th3) {
                                        Throwable th4 = th3;
                                        inputStream = inputStream2;
                                        th = th4;
                                        onConnectionOver();
                                        inputStream.close();
                                        throw th;
                                    }
                                }
                                OnException(1002);
                                inputStream2 = null;
                            } else {
                                OnException(1002);
                                inputStream2 = null;
                            }
                            onConnectionOver();
                            if (inputStream2 != null && !this.mCanceled) {
                                try {
                                    inputStream2.close();
                                    return;
                                } catch (IOException e9) {
                                    OnException(1002);
                                    return;
                                }
                            }
                            return;
                        } catch (IllegalArgumentException e10) {
                        } catch (SecurityException e11) {
                            onConnectionOver();
                            if (inputStream != null && !this.mCanceled) {
                                try {
                                    inputStream.close();
                                    return;
                                } catch (IOException e12) {
                                    OnException(1002);
                                    return;
                                }
                            }
                            return;
                        } catch (OutOfMemoryError e13) {
                            onConnectionOver();
                            if (inputStream != null && !this.mCanceled) {
                                try {
                                    inputStream.close();
                                    return;
                                } catch (IOException e14) {
                                    OnException(1002);
                                    return;
                                }
                            }
                            return;
                        } catch (IllegalStateException e15) {
                            onConnectionOver();
                            if (inputStream != null && !this.mCanceled) {
                                try {
                                    inputStream.close();
                                    return;
                                } catch (IOException e16) {
                                    OnException(1002);
                                    return;
                                }
                            }
                            return;
                        } catch (IOException e17) {
                            OnException(1002);
                            onConnectionOver();
                            if (inputStream != null && !this.mCanceled) {
                                try {
                                    inputStream.close();
                                    return;
                                } catch (IOException e18) {
                                    OnException(1002);
                                    return;
                                }
                            }
                            return;
                        } catch (NullPointerException e19) {
                            onConnectionOver();
                            if (inputStream != null && !this.mCanceled) {
                                try {
                                    inputStream.close();
                                    return;
                                } catch (IOException e20) {
                                    OnException(1002);
                                    return;
                                }
                            }
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            doCancel();
        }
    }

    public void onConnectionError(BaseMapLoader baseMapLoader, int i, String str) {
    }

    /* Access modifiers changed, original: protected */
    public void onConnectionOpened() {
        this.recievedDataBuffer = new byte[this.mCapaticy];
        this.nextImgDataLength = 0;
        this.recievedDataSize = 0;
        this.recievedHeader = false;
    }

    public void addReuqestTiles(MapSourceGridData mapSourceGridData) {
        this.mapTiles.add(mapSourceGridData);
    }

    private void onConnectionRecieveData(byte[] bArr, int i) {
        if (this.mCapaticy < this.recievedDataSize + i) {
            try {
                this.mCapaticy += this.mCapaticyExt;
                byte[] bArr2 = new byte[this.mCapaticy];
                System.arraycopy(this.recievedDataBuffer, 0, bArr2, 0, this.recievedDataSize);
                this.recievedDataBuffer = bArr2;
            } catch (OutOfMemoryError e) {
                doCancel();
                return;
            }
        }
        try {
            System.arraycopy(bArr, 0, this.recievedDataBuffer, this.recievedDataSize, i);
            this.recievedDataSize += i;
            if (!isNeedProcessReturn()) {
                if (this.recievedHeader || processReceivedDataHeader(i)) {
                    processRecivedDataByType();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e2) {
            doCancel();
        } catch (Exception e3) {
            doCancel();
        }
    }

    /* Access modifiers changed, original: protected */
    public void processRecivedData() {
        if (this.nextImgDataLength == 0) {
            if (this.recievedDataSize >= 8) {
                this.nextImgDataLength = Convert.getInt(this.recievedDataBuffer, 0) + 8;
                processRecivedData();
            }
        } else if (this.recievedDataSize >= this.nextImgDataLength) {
            int i = Convert.getInt(this.recievedDataBuffer, 0);
            int i2 = Convert.getInt(this.recievedDataBuffer, 4);
            if (i2 == 0) {
                processRecivedTileData(this.recievedDataBuffer, 8, i + 8);
            } else {
                try {
                    GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(this.recievedDataBuffer, 8, i));
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[128];
                    while (true) {
                        int read = gZIPInputStream.read(bArr);
                        if (read <= -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    processRecivedTileData(byteArrayOutputStream.toByteArray(), 0, i2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.nextImgDataLength > 0) {
                Convert.moveArray(this.recievedDataBuffer, this.nextImgDataLength, this.recievedDataBuffer, 0, this.recievedDataSize - this.nextImgDataLength);
            }
            this.recievedDataSize -= this.nextImgDataLength;
            this.nextImgDataLength = 0;
            processRecivedData();
        }
    }

    /* Access modifiers changed, original: protected */
    public void processReceivedDataV4() {
        if (this.nextImgDataLength == 0) {
            if (this.recievedDataSize >= 8) {
                this.nextImgDataLength = Convert.getInt(this.recievedDataBuffer, 0) + 8;
                processReceivedDataV4();
            }
        } else if (this.recievedDataSize >= this.nextImgDataLength) {
            processReceivedTileDataV4(this.recievedDataBuffer, 8, this.nextImgDataLength);
            Convert.moveArray(this.recievedDataBuffer, this.nextImgDataLength, this.recievedDataBuffer, 0, this.recievedDataSize - this.nextImgDataLength);
            this.recievedDataSize -= this.nextImgDataLength;
            this.nextImgDataLength = 0;
            processReceivedDataV4();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void processRecivedTileData(byte[] bArr, int i, int i2) {
        int i3 = ((i + 2) + 2) + 4;
        int i4 = i3 + 1;
        byte b = bArr[i3];
        String str = "";
        if (b > (byte) 0 && (i4 + b) - 1 < i2) {
            str = new String(bArr, i4, b);
        }
        i4 += b;
        if (this.mGLMapEngine.isMapEngineValid() && i2 > i) {
            int i5 = !this.mMapCallback.isGridInScreen(this.datasource, str) ? 1 : 0;
            VMapDataCache.getInstance().putRecoder(null, str, this.datasource);
            this.mGLMapEngine.putMapData(bArr, i, i2 - i, this.datasource, 0);
            if (i5 != 0) {
                doCancel();
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void processRecivedVersionData(byte[] bArr, int i, int i2) {
        if (i2 > 0 && i2 <= bArr.length && Convert.getInt(bArr, 0) == 0 && Convert.getInt(bArr, 4) == 0) {
            int i3;
            int i4 = Convert.getInt(bArr, 8);
            int i5 = 1;
            ArrayList arrayList = new ArrayList();
            int i6 = 12;
            for (i3 = 0; i3 < i4; i3++) {
                String str = "";
                if (i6 >= i2) {
                    i5 = 0;
                    break;
                }
                int i7 = i6 + 1;
                byte b = bArr[i6];
                if (b <= (byte) 0 || i7 + b >= i2) {
                    i5 = 0;
                    break;
                }
                arrayList.add(new String(bArr, i7, b));
                i6 = (b + i7) + 4;
            }
            if (i5 != 0) {
                for (i3 = 0; i3 < arrayList.size(); i3++) {
                    VMapDataCache.getInstance().putRecoder(null, (String) arrayList.get(i3), this.datasource);
                }
                this.mGLMapEngine.putMapData(bArr, 0, i2, this.datasource, 0);
            }
        }
    }
}
