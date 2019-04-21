package com.p044ta.utdid2.p057b.p058a;

import com.newrelic.agent.android.util.SafeJsonPrimitive;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import org.xmlpull.v1.XmlSerializer;

/* renamed from: com.ta.utdid2.b.a.a */
class C4323a implements XmlSerializer {
    /* renamed from: a */
    private static final String[] f6719a = new String[]{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&quot;", null, null, null, "&amp;", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "&lt;", null, "&gt;", null};
    /* renamed from: a */
    private OutputStream f6720a;
    /* renamed from: a */
    private Writer f6721a;
    /* renamed from: a */
    private ByteBuffer f6722a = ByteBuffer.allocate(8192);
    /* renamed from: a */
    private CharsetEncoder f6723a;
    /* renamed from: a */
    private final char[] f6724a = new char[8192];
    /* renamed from: b */
    private boolean f6725b;
    private int mPos;

    C4323a() {
    }

    private void append(char c) throws IOException {
        int i = this.mPos;
        if (i >= 8191) {
            flush();
            i = this.mPos;
        }
        this.f6724a[i] = c;
        this.mPos = i + 1;
    }

    /* renamed from: a */
    private void m7765a(String str, int i, int i2) throws IOException {
        if (i2 > 8192) {
            int i3 = i + i2;
            while (i < i3) {
                int i4 = i + 8192;
                m7765a(str, i, i4 < i3 ? 8192 : i3 - i);
                i = i4;
            }
            return;
        }
        int i5 = this.mPos;
        if (i5 + i2 > 8192) {
            flush();
            i5 = this.mPos;
        }
        str.getChars(i, i + i2, this.f6724a, i5);
        this.mPos = i5 + i2;
    }

    private void append(char[] cArr, int i, int i2) throws IOException {
        if (i2 > 8192) {
            int i3 = i + i2;
            while (i < i3) {
                int i4 = i + 8192;
                append(cArr, i, i4 < i3 ? 8192 : i3 - i);
                i = i4;
            }
            return;
        }
        int i5 = this.mPos;
        if (i5 + i2 > 8192) {
            flush();
            i5 = this.mPos;
        }
        System.arraycopy(cArr, i, this.f6724a, i5, i2);
        this.mPos = i5 + i2;
    }

    private void append(String str) throws IOException {
        m7765a(str, 0, str.length());
    }

    /* renamed from: a */
    private void m7764a(String str) throws IOException {
        int i = 0;
        int length = str.length();
        char length2 = (char) f6719a.length;
        String[] strArr = f6719a;
        int i2 = 0;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt < length2) {
                String str2 = strArr[charAt];
                if (str2 != null) {
                    if (i < i2) {
                        m7765a(str, i, i2 - i);
                    }
                    i = i2 + 1;
                    append(str2);
                }
            }
            i2++;
        }
        if (i < i2) {
            m7765a(str, i, i2 - i);
        }
    }

    /* renamed from: a */
    private void m7766a(char[] cArr, int i, int i2) throws IOException {
        char length = (char) f6719a.length;
        String[] strArr = f6719a;
        int i3 = i + i2;
        int i4 = i;
        while (i < i3) {
            char c = cArr[i];
            if (c < length) {
                String str = strArr[c];
                if (str != null) {
                    if (i4 < i) {
                        append(cArr, i4, i - i4);
                    }
                    i4 = i + 1;
                    append(str);
                }
            }
            i++;
        }
        if (i4 < i) {
            append(cArr, i4, i - i4);
        }
    }

    public XmlSerializer attribute(String str, String str2, String str3) throws IOException, IllegalArgumentException, IllegalStateException {
        append((char) SafeJsonPrimitive.NULL_CHAR);
        if (str != null) {
            append(str);
            append(':');
        }
        append(str2);
        append("=\"");
        m7764a(str3);
        append('\"');
        return this;
    }

    public void cdsect(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public void comment(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public void docdecl(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public void endDocument() throws IOException, IllegalArgumentException, IllegalStateException {
        flush();
    }

    public XmlSerializer endTag(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException {
        if (this.f6725b) {
            append(" />\n");
        } else {
            append("</");
            if (str != null) {
                append(str);
                append(':');
            }
            append(str2);
            append(">\n");
        }
        this.f6725b = false;
        return this;
    }

    public void entityRef(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    private void m7763a() throws IOException {
        int position = this.f6722a.position();
        if (position > 0) {
            this.f6722a.flip();
            this.f6720a.write(this.f6722a.array(), 0, position);
            this.f6722a.clear();
        }
    }

    public void flush() throws IOException {
        if (this.mPos > 0) {
            if (this.f6720a != null) {
                CharBuffer wrap = CharBuffer.wrap(this.f6724a, 0, this.mPos);
                CoderResult encode = this.f6723a.encode(wrap, this.f6722a, true);
                while (!encode.isError()) {
                    if (encode.isOverflow()) {
                        m7763a();
                        encode = this.f6723a.encode(wrap, this.f6722a, true);
                    } else {
                        m7763a();
                        this.f6720a.flush();
                    }
                }
                throw new IOException(encode.toString());
            }
            this.f6721a.write(this.f6724a, 0, this.mPos);
            this.f6721a.flush();
            this.mPos = 0;
        }
    }

    public int getDepth() {
        throw new UnsupportedOperationException();
    }

    public boolean getFeature(String str) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getNamespace() {
        throw new UnsupportedOperationException();
    }

    public String getPrefix(String str, boolean z) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    public Object getProperty(String str) {
        throw new UnsupportedOperationException();
    }

    public void ignorableWhitespace(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public void processingInstruction(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public void setFeature(String str, boolean z) throws IllegalArgumentException, IllegalStateException {
        if (!str.equals("http://xmlpull.org/v1/doc/features.html#indent-output")) {
            throw new UnsupportedOperationException();
        }
    }

    public void setOutput(OutputStream outputStream, String str) throws IOException, IllegalArgumentException, IllegalStateException {
        if (outputStream == null) {
            throw new IllegalArgumentException();
        }
        try {
            this.f6723a = Charset.forName(str).newEncoder();
            this.f6720a = outputStream;
        } catch (IllegalCharsetNameException e) {
            throw ((UnsupportedEncodingException) new UnsupportedEncodingException(str).initCause(e));
        } catch (UnsupportedCharsetException e2) {
            throw ((UnsupportedEncodingException) new UnsupportedEncodingException(str).initCause(e2));
        }
    }

    public void setOutput(Writer writer) throws IOException, IllegalArgumentException, IllegalStateException {
        this.f6721a = writer;
    }

    public void setPrefix(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public void setProperty(String str, Object obj) throws IllegalArgumentException, IllegalStateException {
        throw new UnsupportedOperationException();
    }

    public void startDocument(String str, Boolean bool) throws IOException, IllegalArgumentException, IllegalStateException {
        append("<?xml version='1.0' encoding='utf-8' standalone='" + (bool.booleanValue() ? "yes" : "no") + "' ?>\n");
    }

    public XmlSerializer startTag(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException {
        if (this.f6725b) {
            append(">\n");
        }
        append('<');
        if (str != null) {
            append(str);
            append(':');
        }
        append(str2);
        this.f6725b = true;
        return this;
    }

    public XmlSerializer text(char[] cArr, int i, int i2) throws IOException, IllegalArgumentException, IllegalStateException {
        if (this.f6725b) {
            append(">");
            this.f6725b = false;
        }
        m7766a(cArr, i, i2);
        return this;
    }

    public XmlSerializer text(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        if (this.f6725b) {
            append(">");
            this.f6725b = false;
        }
        m7764a(str);
        return this;
    }
}
