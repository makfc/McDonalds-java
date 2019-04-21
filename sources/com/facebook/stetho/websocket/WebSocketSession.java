package com.facebook.stetho.websocket;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

class WebSocketSession implements SimpleSession {
    private final SimpleEndpoint mEndpoint;
    private final WriteCallback mErrorForwardingWriteCallback = new C20262();
    private AtomicBoolean mIsOpen = new AtomicBoolean(false);
    private final ReadCallback mReadCallback = new C20251();
    private final ReadHandler mReadHandler;
    private volatile boolean mSentClose;
    private final WriteHandler mWriteHandler;

    /* renamed from: com.facebook.stetho.websocket.WebSocketSession$1 */
    class C20251 implements ReadCallback {
        C20251() {
        }

        public void onCompleteFrame(byte opcode, byte[] payload, int payloadLen) {
            switch (opcode) {
                case (byte) 1:
                    handleTextFrame(payload, payloadLen);
                    return;
                case (byte) 2:
                    handleBinaryFrame(payload, payloadLen);
                    return;
                case (byte) 8:
                    handleClose(payload, payloadLen);
                    return;
                case (byte) 9:
                    handlePing(payload, payloadLen);
                    return;
                case (byte) 10:
                    handlePong(payload, payloadLen);
                    return;
                default:
                    WebSocketSession.this.signalError(new IOException("Unsupported frame opcode=" + opcode));
                    return;
            }
        }

        private void handleClose(byte[] payload, int payloadLen) {
            int closeCode;
            String closeReasonPhrase;
            if (payloadLen >= 2) {
                closeCode = ((payload[0] & 255) << 8) | (payload[1] & 255);
                closeReasonPhrase = payloadLen > 2 ? new String(payload, 2, payloadLen - 2) : null;
            } else {
                closeCode = 1006;
                closeReasonPhrase = "Unparseable close frame";
            }
            if (!WebSocketSession.this.mSentClose) {
                WebSocketSession.this.sendClose(1000, "Received close frame");
            }
            WebSocketSession.this.markAndSignalClosed(closeCode, closeReasonPhrase);
        }

        private void handlePing(byte[] payload, int payloadLen) {
            WebSocketSession.this.doWrite(FrameHelper.createPongFrame(payload, payloadLen));
        }

        private void handlePong(byte[] payload, int payloadLen) {
        }

        private void handleTextFrame(byte[] payload, int payloadLen) {
            WebSocketSession.this.mEndpoint.onMessage(WebSocketSession.this, new String(payload, 0, payloadLen));
        }

        private void handleBinaryFrame(byte[] payload, int payloadLen) {
            WebSocketSession.this.mEndpoint.onMessage(WebSocketSession.this, payload, payloadLen);
        }
    }

    /* renamed from: com.facebook.stetho.websocket.WebSocketSession$2 */
    class C20262 implements WriteCallback {
        C20262() {
        }

        public void onFailure(IOException e) {
            WebSocketSession.this.signalError(e);
        }

        public void onSuccess() {
        }
    }

    public WebSocketSession(InputStream rawSocketInput, OutputStream rawSocketOutput, SimpleEndpoint endpoint) {
        this.mReadHandler = new ReadHandler(rawSocketInput, endpoint);
        this.mWriteHandler = new WriteHandler(rawSocketOutput);
        this.mEndpoint = endpoint;
    }

    public void handle() throws IOException {
        markAndSignalOpen();
        try {
            this.mReadHandler.readLoop(this.mReadCallback);
        } catch (EOFException e) {
            markAndSignalClosed(1011, "EOF while reading");
        } catch (IOException e2) {
            markAndSignalClosed(1006, null);
            throw e2;
        }
    }

    public void sendText(String payload) {
        doWrite(FrameHelper.createTextFrame(payload));
    }

    public void sendBinary(byte[] payload) {
        doWrite(FrameHelper.createBinaryFrame(payload));
    }

    public void close(int closeReason, String reasonPhrase) {
        sendClose(closeReason, reasonPhrase);
        markAndSignalClosed(closeReason, reasonPhrase);
    }

    private void sendClose(int closeReason, String reasonPhrase) {
        doWrite(FrameHelper.createCloseFrame(closeReason, reasonPhrase));
        markSentClose();
    }

    /* Access modifiers changed, original: 0000 */
    public void markSentClose() {
        this.mSentClose = true;
    }

    /* Access modifiers changed, original: 0000 */
    public void markAndSignalOpen() {
        if (!this.mIsOpen.getAndSet(true)) {
            this.mEndpoint.onOpen(this);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void markAndSignalClosed(int closeReason, String reasonPhrase) {
        if (this.mIsOpen.getAndSet(false)) {
            this.mEndpoint.onClose(this, closeReason, reasonPhrase);
        }
    }

    public boolean isOpen() {
        return this.mIsOpen.get();
    }

    private void doWrite(Frame frame) {
        if (!signalErrorIfNotOpen()) {
            this.mWriteHandler.write(frame, this.mErrorForwardingWriteCallback);
        }
    }

    private boolean signalErrorIfNotOpen() {
        if (isOpen()) {
            return false;
        }
        signalError(new IOException("Session is closed"));
        return true;
    }

    private void signalError(IOException e) {
        this.mEndpoint.onError(this, e);
    }
}
