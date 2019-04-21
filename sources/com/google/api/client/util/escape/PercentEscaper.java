package com.google.api.client.util.escape;

import android.support.p000v4.internal.view.SupportMenu;
import android.support.p000v4.media.TransportMediator;

public class PercentEscaper extends UnicodeEscaper {
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private static final char[] URI_ESCAPED_SPACE = new char[]{'+'};
    private final boolean plusForSpace;
    private final boolean[] safeOctets;

    public PercentEscaper(String safeChars, boolean plusForSpace) {
        if (safeChars.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        } else if (plusForSpace && safeChars.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        } else if (safeChars.contains("%")) {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        } else {
            this.plusForSpace = plusForSpace;
            this.safeOctets = createSafeOctets(safeChars);
        }
    }

    private static boolean[] createSafeOctets(String safeChars) {
        int c;
        int maxChar = 122;
        char[] safeCharArray = safeChars.toCharArray();
        for (char c2 : safeCharArray) {
            maxChar = Math.max(c2, maxChar);
        }
        boolean[] octets = new boolean[(maxChar + 1)];
        for (c = 48; c <= 57; c++) {
            octets[c] = true;
        }
        for (c = 65; c <= 90; c++) {
            octets[c] = true;
        }
        for (c = 97; c <= 122; c++) {
            octets[c] = true;
        }
        for (char c22 : safeCharArray) {
            octets[c22] = true;
        }
        return octets;
    }

    /* Access modifiers changed, original: protected */
    public int nextEscapeIndex(CharSequence csq, int index, int end) {
        while (index < end) {
            char c = csq.charAt(index);
            if (c >= this.safeOctets.length || !this.safeOctets[c]) {
                break;
            }
            index++;
        }
        return index;
    }

    public String escape(String s) {
        int slen = s.length();
        for (int index = 0; index < slen; index++) {
            char c = s.charAt(index);
            if (c >= this.safeOctets.length || !this.safeOctets[c]) {
                return escapeSlow(s, index);
            }
        }
        return s;
    }

    /* Access modifiers changed, original: protected */
    public char[] escape(int cp) {
        if (cp < this.safeOctets.length && this.safeOctets[cp]) {
            return null;
        }
        if (cp == 32 && this.plusForSpace) {
            return URI_ESCAPED_SPACE;
        }
        char[] dest;
        if (cp <= TransportMediator.KEYCODE_MEDIA_PAUSE) {
            return new char[]{'%', UPPER_HEX_DIGITS[cp & 15], UPPER_HEX_DIGITS[cp >>> 4]};
        } else if (cp <= 2047) {
            dest = new char[6];
            dest[0] = '%';
            dest[3] = '%';
            dest[5] = UPPER_HEX_DIGITS[cp & 15];
            cp >>>= 4;
            dest[4] = UPPER_HEX_DIGITS[(cp & 3) | 8];
            cp >>>= 2;
            dest[2] = UPPER_HEX_DIGITS[cp & 15];
            dest[1] = UPPER_HEX_DIGITS[(cp >>> 4) | 12];
            return dest;
        } else if (cp <= SupportMenu.USER_MASK) {
            dest = new char[9];
            cp >>>= 4;
            dest[7] = UPPER_HEX_DIGITS[(cp & 3) | 8];
            cp >>>= 2;
            dest[5] = UPPER_HEX_DIGITS[cp & 15];
            cp >>>= 4;
            dest[4] = UPPER_HEX_DIGITS[(cp & 3) | 8];
            dest[2] = UPPER_HEX_DIGITS[cp >>> 2];
            return dest;
        } else if (cp <= 1114111) {
            dest = new char[12];
            cp >>>= 4;
            dest[10] = UPPER_HEX_DIGITS[(cp & 3) | 8];
            cp >>>= 2;
            dest[8] = UPPER_HEX_DIGITS[cp & 15];
            cp >>>= 4;
            dest[7] = UPPER_HEX_DIGITS[(cp & 3) | 8];
            cp >>>= 2;
            dest[5] = UPPER_HEX_DIGITS[cp & 15];
            cp >>>= 4;
            dest[4] = UPPER_HEX_DIGITS[(cp & 3) | 8];
            dest[2] = UPPER_HEX_DIGITS[(cp >>> 2) & 7];
            return dest;
        } else {
            throw new IllegalArgumentException("Invalid unicode character value " + cp);
        }
    }
}
