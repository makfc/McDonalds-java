package com.facebook.stetho.websocket;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

class ReadHandler {
    private final BufferedInputStream mBufferedInput;
    private final ByteArrayOutputStream mCurrentPayload = new ByteArrayOutputStream();
    private final SimpleEndpoint mEndpoint;

    public ReadHandler(InputStream bufferedInput, SimpleEndpoint endpoint) {
        this.mBufferedInput = new BufferedInputStream(bufferedInput, 1024);
        this.mEndpoint = endpoint;
    }

    public void readLoop(ReadCallback readCallback) throws IOException {
        Frame frame = new Frame();
        do {
            frame.readFrom(this.mBufferedInput);
            this.mCurrentPayload.write(frame.payloadData, 0, (int) frame.payloadLen);
            if (frame.fin) {
                byte[] completePayload = this.mCurrentPayload.toByteArray();
                readCallback.onCompleteFrame(frame.opcode, completePayload, completePayload.length);
                this.mCurrentPayload.reset();
            }
        } while (frame.opcode != (byte) 8);
    }
}
