package com.facebook.stetho.websocket;

import com.facebook.stetho.common.Utf8Charset;

class FrameHelper {
    FrameHelper() {
    }

    public static Frame createTextFrame(String payload) {
        return createSimpleFrame((byte) 1, Utf8Charset.encodeUTF8(payload));
    }

    public static Frame createBinaryFrame(byte[] payload) {
        return createSimpleFrame((byte) 2, payload);
    }

    public static Frame createCloseFrame(int closeCode, String reasonPhrase) {
        byte[] reasonPhraseEncoded = null;
        int payloadLen = 2;
        if (reasonPhrase != null) {
            reasonPhraseEncoded = Utf8Charset.encodeUTF8(reasonPhrase);
            payloadLen = 2 + reasonPhraseEncoded.length;
        }
        byte[] payload = new byte[payloadLen];
        payload[0] = (byte) ((closeCode >> 8) & 255);
        payload[1] = (byte) (closeCode & 255);
        if (reasonPhraseEncoded != null) {
            System.arraycopy(reasonPhraseEncoded, 0, payload, 2, reasonPhraseEncoded.length);
        }
        return createSimpleFrame((byte) 8, payload);
    }

    public static Frame createPingFrame(byte[] payload, int payloadLen) {
        return createSimpleFrame((byte) 9, payload, payloadLen);
    }

    public static Frame createPongFrame(byte[] payload, int payloadLen) {
        return createSimpleFrame((byte) 10, payload, payloadLen);
    }

    private static Frame createSimpleFrame(byte opcode, byte[] payload) {
        return createSimpleFrame(opcode, payload, payload.length);
    }

    private static Frame createSimpleFrame(byte opCode, byte[] payload, int payloadLen) {
        Frame frame = new Frame();
        frame.fin = true;
        frame.hasMask = false;
        frame.opcode = opCode;
        frame.payloadLen = (long) payloadLen;
        frame.payloadData = payload;
        return frame;
    }
}
