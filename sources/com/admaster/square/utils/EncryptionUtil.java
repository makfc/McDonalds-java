package com.admaster.square.utils;

import android.support.p000v4.media.TransportMediator;
import android.support.p000v4.view.InputDeviceCompat;
import android.support.p000v4.view.MotionEventCompat;
import android.text.TextUtils;
import com.autonavi.amap.mapcore.interfaces.CameraAnimator;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.admaster.square.utils.j */
public class EncryptionUtil {
    /* renamed from: a */
    static final /* synthetic */ boolean f276a;
    /* renamed from: b */
    private static final byte[] f277b = new byte[]{(byte) 68, (byte) 67, (byte) 66, (byte) 65, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 82, (byte) 81, (byte) 80, (byte) 79, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 122, (byte) 121, (byte) 120, (byte) 119, (byte) 118, (byte) 117, (byte) 116, (byte) 115, (byte) 114, (byte) 113, (byte) 112, (byte) 111, (byte) 110, (byte) 109, (byte) 108, (byte) 107, (byte) 106, (byte) 105, (byte) 104, (byte) 103, (byte) 102, (byte) 101, (byte) 100, (byte) 99, (byte) 98, (byte) 97, (byte) 57, (byte) 56, (byte) 55, (byte) 54, (byte) 53, (byte) 52, (byte) 51, (byte) 50, (byte) 49, (byte) 48, (byte) 43, (byte) 47};
    /* renamed from: c */
    private static final byte[] f278c;
    /* renamed from: d */
    private static final byte[] f279d = new byte[]{(byte) 68, (byte) 67, (byte) 66, (byte) 65, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 82, (byte) 81, (byte) 80, (byte) 79, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 122, (byte) 121, (byte) 120, (byte) 119, (byte) 118, (byte) 117, (byte) 116, (byte) 115, (byte) 114, (byte) 113, (byte) 112, (byte) 111, (byte) 110, (byte) 109, (byte) 108, (byte) 107, (byte) 106, (byte) 105, (byte) 104, (byte) 103, (byte) 102, (byte) 101, (byte) 100, (byte) 99, (byte) 98, (byte) 97, (byte) 57, (byte) 56, (byte) 55, (byte) 54, (byte) 53, (byte) 52, (byte) 51, (byte) 50, (byte) 49, (byte) 48, (byte) 45, (byte) 95};
    /* renamed from: e */
    private static final byte[] f280e;
    /* renamed from: f */
    private static final byte[] f281f = new byte[]{(byte) 45, (byte) 57, (byte) 56, (byte) 55, (byte) 54, (byte) 53, (byte) 52, (byte) 51, (byte) 50, (byte) 49, (byte) 48, (byte) 68, (byte) 67, (byte) 66, (byte) 65, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 82, (byte) 81, (byte) 80, (byte) 79, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 95, (byte) 122, (byte) 121, (byte) 120, (byte) 119, (byte) 118, (byte) 117, (byte) 116, (byte) 115, (byte) 114, (byte) 113, (byte) 112, (byte) 111, (byte) 110, (byte) 109, (byte) 108, (byte) 107, (byte) 106, (byte) 105, (byte) 104, (byte) 103, (byte) 102, (byte) 101, (byte) 100, (byte) 99, (byte) 98, (byte) 97};
    /* renamed from: g */
    private static final byte[] f282g;

    static {
        boolean z;
        if (EncryptionUtil.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        f276a = z;
        byte[] bArr = new byte[256];
        bArr[0] = (byte) -9;
        bArr[1] = (byte) -9;
        bArr[2] = (byte) -9;
        bArr[3] = (byte) -9;
        bArr[4] = (byte) -9;
        bArr[5] = (byte) -9;
        bArr[6] = (byte) -9;
        bArr[7] = (byte) -9;
        bArr[8] = (byte) -9;
        bArr[9] = (byte) -5;
        bArr[10] = (byte) -5;
        bArr[11] = (byte) -9;
        bArr[12] = (byte) -9;
        bArr[13] = (byte) -5;
        bArr[14] = (byte) -9;
        bArr[15] = (byte) -9;
        bArr[16] = (byte) -9;
        bArr[17] = (byte) -9;
        bArr[18] = (byte) -9;
        bArr[19] = (byte) -9;
        bArr[20] = (byte) -9;
        bArr[21] = (byte) -9;
        bArr[22] = (byte) -9;
        bArr[23] = (byte) -9;
        bArr[24] = (byte) -9;
        bArr[25] = (byte) -9;
        bArr[26] = (byte) -9;
        bArr[27] = (byte) -9;
        bArr[28] = (byte) -9;
        bArr[29] = (byte) -9;
        bArr[30] = (byte) -9;
        bArr[31] = (byte) -9;
        bArr[32] = (byte) -5;
        bArr[33] = (byte) -9;
        bArr[34] = (byte) -9;
        bArr[35] = (byte) -9;
        bArr[36] = (byte) -9;
        bArr[37] = (byte) -9;
        bArr[38] = (byte) -9;
        bArr[39] = (byte) -9;
        bArr[40] = (byte) -9;
        bArr[41] = (byte) -9;
        bArr[42] = (byte) -9;
        bArr[43] = (byte) 62;
        bArr[44] = (byte) -9;
        bArr[45] = (byte) -9;
        bArr[46] = (byte) -9;
        bArr[47] = (byte) 63;
        bArr[48] = (byte) 61;
        bArr[49] = (byte) 60;
        bArr[50] = (byte) 59;
        bArr[51] = (byte) 58;
        bArr[52] = (byte) 57;
        bArr[53] = (byte) 56;
        bArr[54] = (byte) 55;
        bArr[55] = (byte) 54;
        bArr[56] = (byte) 53;
        bArr[57] = (byte) 52;
        bArr[58] = (byte) -9;
        bArr[59] = (byte) -9;
        bArr[60] = (byte) -9;
        bArr[61] = (byte) -1;
        bArr[62] = (byte) -9;
        bArr[63] = (byte) -9;
        bArr[64] = (byte) -9;
        bArr[65] = (byte) 3;
        bArr[66] = (byte) 2;
        bArr[67] = (byte) 1;
        bArr[69] = (byte) 4;
        bArr[70] = (byte) 5;
        bArr[71] = (byte) 6;
        bArr[72] = (byte) 7;
        bArr[73] = (byte) 8;
        bArr[74] = (byte) 9;
        bArr[75] = (byte) 10;
        bArr[76] = (byte) 11;
        bArr[77] = (byte) 12;
        bArr[78] = (byte) 13;
        bArr[79] = (byte) 17;
        bArr[80] = (byte) 16;
        bArr[81] = (byte) 15;
        bArr[82] = (byte) 14;
        bArr[83] = (byte) 18;
        bArr[84] = (byte) 19;
        bArr[85] = (byte) 20;
        bArr[86] = (byte) 21;
        bArr[87] = (byte) 22;
        bArr[88] = (byte) 23;
        bArr[89] = (byte) 24;
        bArr[90] = (byte) 25;
        bArr[91] = (byte) -9;
        bArr[92] = (byte) -9;
        bArr[93] = (byte) -9;
        bArr[94] = (byte) -9;
        bArr[95] = (byte) -9;
        bArr[96] = (byte) -9;
        bArr[97] = (byte) 51;
        bArr[98] = (byte) 50;
        bArr[99] = (byte) 49;
        bArr[100] = (byte) 48;
        bArr[101] = (byte) 47;
        bArr[102] = (byte) 46;
        bArr[103] = (byte) 45;
        bArr[104] = (byte) 44;
        bArr[105] = (byte) 43;
        bArr[106] = (byte) 42;
        bArr[107] = (byte) 41;
        bArr[108] = (byte) 40;
        bArr[109] = (byte) 39;
        bArr[110] = (byte) 38;
        bArr[111] = (byte) 37;
        bArr[112] = (byte) 36;
        bArr[113] = (byte) 35;
        bArr[114] = (byte) 34;
        bArr[115] = (byte) 33;
        bArr[116] = (byte) 32;
        bArr[117] = (byte) 31;
        bArr[118] = (byte) 30;
        bArr[119] = (byte) 29;
        bArr[120] = (byte) 28;
        bArr[121] = (byte) 27;
        bArr[122] = (byte) 26;
        bArr[123] = (byte) -9;
        bArr[124] = (byte) -9;
        bArr[125] = (byte) -9;
        bArr[TransportMediator.KEYCODE_MEDIA_PLAY] = (byte) -9;
        bArr[TransportMediator.KEYCODE_MEDIA_PAUSE] = (byte) -9;
        bArr[128] = (byte) -9;
        bArr[129] = (byte) -9;
        bArr[TransportMediator.KEYCODE_MEDIA_RECORD] = (byte) -9;
        bArr[131] = (byte) -9;
        bArr[132] = (byte) -9;
        bArr[133] = (byte) -9;
        bArr[134] = (byte) -9;
        bArr[135] = (byte) -9;
        bArr[136] = (byte) -9;
        bArr[137] = (byte) -9;
        bArr[138] = (byte) -9;
        bArr[139] = (byte) -9;
        bArr[140] = (byte) -9;
        bArr[141] = (byte) -9;
        bArr[142] = (byte) -9;
        bArr[143] = (byte) -9;
        bArr[144] = (byte) -9;
        bArr[145] = (byte) -9;
        bArr[146] = (byte) -9;
        bArr[147] = (byte) -9;
        bArr[148] = (byte) -9;
        bArr[149] = (byte) -9;
        bArr[150] = (byte) -9;
        bArr[151] = (byte) -9;
        bArr[152] = (byte) -9;
        bArr[153] = (byte) -9;
        bArr[154] = (byte) -9;
        bArr[155] = (byte) -9;
        bArr[156] = (byte) -9;
        bArr[157] = (byte) -9;
        bArr[158] = (byte) -9;
        bArr[159] = (byte) -9;
        bArr[160] = (byte) -9;
        bArr[161] = (byte) -9;
        bArr[162] = (byte) -9;
        bArr[163] = (byte) -9;
        bArr[164] = (byte) -9;
        bArr[165] = (byte) -9;
        bArr[166] = (byte) -9;
        bArr[167] = (byte) -9;
        bArr[168] = (byte) -9;
        bArr[169] = (byte) -9;
        bArr[170] = (byte) -9;
        bArr[171] = (byte) -9;
        bArr[172] = (byte) -9;
        bArr[173] = (byte) -9;
        bArr[174] = (byte) -9;
        bArr[175] = (byte) -9;
        bArr[176] = (byte) -9;
        bArr[177] = (byte) -9;
        bArr[178] = (byte) -9;
        bArr[179] = (byte) -9;
        bArr[180] = (byte) -9;
        bArr[181] = (byte) -9;
        bArr[182] = (byte) -9;
        bArr[183] = (byte) -9;
        bArr[184] = (byte) -9;
        bArr[185] = (byte) -9;
        bArr[186] = (byte) -9;
        bArr[187] = (byte) -9;
        bArr[188] = (byte) -9;
        bArr[189] = (byte) -9;
        bArr[190] = (byte) -9;
        bArr[191] = (byte) -9;
        bArr[192] = (byte) -9;
        bArr[193] = (byte) -9;
        bArr[194] = (byte) -9;
        bArr[195] = (byte) -9;
        bArr[196] = (byte) -9;
        bArr[197] = (byte) -9;
        bArr[198] = (byte) -9;
        bArr[199] = (byte) -9;
        bArr[200] = (byte) -9;
        bArr[201] = (byte) -9;
        bArr[202] = (byte) -9;
        bArr[203] = (byte) -9;
        bArr[204] = (byte) -9;
        bArr[205] = (byte) -9;
        bArr[206] = (byte) -9;
        bArr[207] = (byte) -9;
        bArr[208] = (byte) -9;
        bArr[209] = (byte) -9;
        bArr[210] = (byte) -9;
        bArr[211] = (byte) -9;
        bArr[212] = (byte) -9;
        bArr[213] = (byte) -9;
        bArr[214] = (byte) -9;
        bArr[215] = (byte) -9;
        bArr[216] = (byte) -9;
        bArr[217] = (byte) -9;
        bArr[218] = (byte) -9;
        bArr[219] = (byte) -9;
        bArr[220] = (byte) -9;
        bArr[221] = (byte) -9;
        bArr[222] = (byte) -9;
        bArr[223] = (byte) -9;
        bArr[224] = (byte) -9;
        bArr[225] = (byte) -9;
        bArr[226] = (byte) -9;
        bArr[227] = (byte) -9;
        bArr[228] = (byte) -9;
        bArr[229] = (byte) -9;
        bArr[230] = (byte) -9;
        bArr[231] = (byte) -9;
        bArr[232] = (byte) -9;
        bArr[233] = (byte) -9;
        bArr[234] = (byte) -9;
        bArr[235] = (byte) -9;
        bArr[236] = (byte) -9;
        bArr[237] = (byte) -9;
        bArr[238] = (byte) -9;
        bArr[239] = (byte) -9;
        bArr[240] = (byte) -9;
        bArr[241] = (byte) -9;
        bArr[242] = (byte) -9;
        bArr[243] = (byte) -9;
        bArr[244] = (byte) -9;
        bArr[245] = (byte) -9;
        bArr[246] = (byte) -9;
        bArr[247] = (byte) -9;
        bArr[248] = (byte) -9;
        bArr[249] = (byte) -9;
        bArr[CameraAnimator.DEFAULT_DURATION] = (byte) -9;
        bArr[251] = (byte) -9;
        bArr[252] = (byte) -9;
        bArr[253] = (byte) -9;
        bArr[254] = (byte) -9;
        bArr[255] = (byte) -9;
        f278c = bArr;
        bArr = new byte[256];
        bArr[0] = (byte) -9;
        bArr[1] = (byte) -9;
        bArr[2] = (byte) -9;
        bArr[3] = (byte) -9;
        bArr[4] = (byte) -9;
        bArr[5] = (byte) -9;
        bArr[6] = (byte) -9;
        bArr[7] = (byte) -9;
        bArr[8] = (byte) -9;
        bArr[9] = (byte) -5;
        bArr[10] = (byte) -5;
        bArr[11] = (byte) -9;
        bArr[12] = (byte) -9;
        bArr[13] = (byte) -5;
        bArr[14] = (byte) -9;
        bArr[15] = (byte) -9;
        bArr[16] = (byte) -9;
        bArr[17] = (byte) -9;
        bArr[18] = (byte) -9;
        bArr[19] = (byte) -9;
        bArr[20] = (byte) -9;
        bArr[21] = (byte) -9;
        bArr[22] = (byte) -9;
        bArr[23] = (byte) -9;
        bArr[24] = (byte) -9;
        bArr[25] = (byte) -9;
        bArr[26] = (byte) -9;
        bArr[27] = (byte) -9;
        bArr[28] = (byte) -9;
        bArr[29] = (byte) -9;
        bArr[30] = (byte) -9;
        bArr[31] = (byte) -9;
        bArr[32] = (byte) -5;
        bArr[33] = (byte) -9;
        bArr[34] = (byte) -9;
        bArr[35] = (byte) -9;
        bArr[36] = (byte) -9;
        bArr[37] = (byte) -9;
        bArr[38] = (byte) -9;
        bArr[39] = (byte) -9;
        bArr[40] = (byte) -9;
        bArr[41] = (byte) -9;
        bArr[42] = (byte) -9;
        bArr[43] = (byte) -9;
        bArr[44] = (byte) -9;
        bArr[45] = (byte) 62;
        bArr[46] = (byte) -9;
        bArr[47] = (byte) -9;
        bArr[48] = (byte) 61;
        bArr[49] = (byte) 60;
        bArr[50] = (byte) 59;
        bArr[51] = (byte) 58;
        bArr[52] = (byte) 57;
        bArr[53] = (byte) 56;
        bArr[54] = (byte) 55;
        bArr[55] = (byte) 54;
        bArr[56] = (byte) 53;
        bArr[57] = (byte) 52;
        bArr[58] = (byte) -9;
        bArr[59] = (byte) -9;
        bArr[60] = (byte) -9;
        bArr[61] = (byte) -1;
        bArr[62] = (byte) -9;
        bArr[63] = (byte) -9;
        bArr[64] = (byte) -9;
        bArr[65] = (byte) 3;
        bArr[66] = (byte) 2;
        bArr[67] = (byte) 1;
        bArr[69] = (byte) 4;
        bArr[70] = (byte) 5;
        bArr[71] = (byte) 6;
        bArr[72] = (byte) 7;
        bArr[73] = (byte) 8;
        bArr[74] = (byte) 9;
        bArr[75] = (byte) 10;
        bArr[76] = (byte) 11;
        bArr[77] = (byte) 12;
        bArr[78] = (byte) 13;
        bArr[79] = (byte) 17;
        bArr[80] = (byte) 16;
        bArr[81] = (byte) 15;
        bArr[82] = (byte) 14;
        bArr[83] = (byte) 18;
        bArr[84] = (byte) 19;
        bArr[85] = (byte) 20;
        bArr[86] = (byte) 21;
        bArr[87] = (byte) 22;
        bArr[88] = (byte) 23;
        bArr[89] = (byte) 24;
        bArr[90] = (byte) 25;
        bArr[91] = (byte) -9;
        bArr[92] = (byte) -9;
        bArr[93] = (byte) -9;
        bArr[94] = (byte) -9;
        bArr[95] = (byte) 63;
        bArr[96] = (byte) -9;
        bArr[97] = (byte) 51;
        bArr[98] = (byte) 50;
        bArr[99] = (byte) 49;
        bArr[100] = (byte) 48;
        bArr[101] = (byte) 47;
        bArr[102] = (byte) 46;
        bArr[103] = (byte) 45;
        bArr[104] = (byte) 44;
        bArr[105] = (byte) 43;
        bArr[106] = (byte) 42;
        bArr[107] = (byte) 41;
        bArr[108] = (byte) 40;
        bArr[109] = (byte) 39;
        bArr[110] = (byte) 38;
        bArr[111] = (byte) 37;
        bArr[112] = (byte) 36;
        bArr[113] = (byte) 35;
        bArr[114] = (byte) 34;
        bArr[115] = (byte) 33;
        bArr[116] = (byte) 32;
        bArr[117] = (byte) 31;
        bArr[118] = (byte) 30;
        bArr[119] = (byte) 29;
        bArr[120] = (byte) 28;
        bArr[121] = (byte) 27;
        bArr[122] = (byte) 26;
        bArr[123] = (byte) -9;
        bArr[124] = (byte) -9;
        bArr[125] = (byte) -9;
        bArr[TransportMediator.KEYCODE_MEDIA_PLAY] = (byte) -9;
        bArr[TransportMediator.KEYCODE_MEDIA_PAUSE] = (byte) -9;
        bArr[128] = (byte) -9;
        bArr[129] = (byte) -9;
        bArr[TransportMediator.KEYCODE_MEDIA_RECORD] = (byte) -9;
        bArr[131] = (byte) -9;
        bArr[132] = (byte) -9;
        bArr[133] = (byte) -9;
        bArr[134] = (byte) -9;
        bArr[135] = (byte) -9;
        bArr[136] = (byte) -9;
        bArr[137] = (byte) -9;
        bArr[138] = (byte) -9;
        bArr[139] = (byte) -9;
        bArr[140] = (byte) -9;
        bArr[141] = (byte) -9;
        bArr[142] = (byte) -9;
        bArr[143] = (byte) -9;
        bArr[144] = (byte) -9;
        bArr[145] = (byte) -9;
        bArr[146] = (byte) -9;
        bArr[147] = (byte) -9;
        bArr[148] = (byte) -9;
        bArr[149] = (byte) -9;
        bArr[150] = (byte) -9;
        bArr[151] = (byte) -9;
        bArr[152] = (byte) -9;
        bArr[153] = (byte) -9;
        bArr[154] = (byte) -9;
        bArr[155] = (byte) -9;
        bArr[156] = (byte) -9;
        bArr[157] = (byte) -9;
        bArr[158] = (byte) -9;
        bArr[159] = (byte) -9;
        bArr[160] = (byte) -9;
        bArr[161] = (byte) -9;
        bArr[162] = (byte) -9;
        bArr[163] = (byte) -9;
        bArr[164] = (byte) -9;
        bArr[165] = (byte) -9;
        bArr[166] = (byte) -9;
        bArr[167] = (byte) -9;
        bArr[168] = (byte) -9;
        bArr[169] = (byte) -9;
        bArr[170] = (byte) -9;
        bArr[171] = (byte) -9;
        bArr[172] = (byte) -9;
        bArr[173] = (byte) -9;
        bArr[174] = (byte) -9;
        bArr[175] = (byte) -9;
        bArr[176] = (byte) -9;
        bArr[177] = (byte) -9;
        bArr[178] = (byte) -9;
        bArr[179] = (byte) -9;
        bArr[180] = (byte) -9;
        bArr[181] = (byte) -9;
        bArr[182] = (byte) -9;
        bArr[183] = (byte) -9;
        bArr[184] = (byte) -9;
        bArr[185] = (byte) -9;
        bArr[186] = (byte) -9;
        bArr[187] = (byte) -9;
        bArr[188] = (byte) -9;
        bArr[189] = (byte) -9;
        bArr[190] = (byte) -9;
        bArr[191] = (byte) -9;
        bArr[192] = (byte) -9;
        bArr[193] = (byte) -9;
        bArr[194] = (byte) -9;
        bArr[195] = (byte) -9;
        bArr[196] = (byte) -9;
        bArr[197] = (byte) -9;
        bArr[198] = (byte) -9;
        bArr[199] = (byte) -9;
        bArr[200] = (byte) -9;
        bArr[201] = (byte) -9;
        bArr[202] = (byte) -9;
        bArr[203] = (byte) -9;
        bArr[204] = (byte) -9;
        bArr[205] = (byte) -9;
        bArr[206] = (byte) -9;
        bArr[207] = (byte) -9;
        bArr[208] = (byte) -9;
        bArr[209] = (byte) -9;
        bArr[210] = (byte) -9;
        bArr[211] = (byte) -9;
        bArr[212] = (byte) -9;
        bArr[213] = (byte) -9;
        bArr[214] = (byte) -9;
        bArr[215] = (byte) -9;
        bArr[216] = (byte) -9;
        bArr[217] = (byte) -9;
        bArr[218] = (byte) -9;
        bArr[219] = (byte) -9;
        bArr[220] = (byte) -9;
        bArr[221] = (byte) -9;
        bArr[222] = (byte) -9;
        bArr[223] = (byte) -9;
        bArr[224] = (byte) -9;
        bArr[225] = (byte) -9;
        bArr[226] = (byte) -9;
        bArr[227] = (byte) -9;
        bArr[228] = (byte) -9;
        bArr[229] = (byte) -9;
        bArr[230] = (byte) -9;
        bArr[231] = (byte) -9;
        bArr[232] = (byte) -9;
        bArr[233] = (byte) -9;
        bArr[234] = (byte) -9;
        bArr[235] = (byte) -9;
        bArr[236] = (byte) -9;
        bArr[237] = (byte) -9;
        bArr[238] = (byte) -9;
        bArr[239] = (byte) -9;
        bArr[240] = (byte) -9;
        bArr[241] = (byte) -9;
        bArr[242] = (byte) -9;
        bArr[243] = (byte) -9;
        bArr[244] = (byte) -9;
        bArr[245] = (byte) -9;
        bArr[246] = (byte) -9;
        bArr[247] = (byte) -9;
        bArr[248] = (byte) -9;
        bArr[249] = (byte) -9;
        bArr[CameraAnimator.DEFAULT_DURATION] = (byte) -9;
        bArr[251] = (byte) -9;
        bArr[252] = (byte) -9;
        bArr[253] = (byte) -9;
        bArr[254] = (byte) -9;
        bArr[255] = (byte) -9;
        f280e = bArr;
        bArr = new byte[InputDeviceCompat.SOURCE_KEYBOARD];
        bArr[0] = (byte) -9;
        bArr[1] = (byte) -9;
        bArr[2] = (byte) -9;
        bArr[3] = (byte) -9;
        bArr[4] = (byte) -9;
        bArr[5] = (byte) -9;
        bArr[6] = (byte) -9;
        bArr[7] = (byte) -9;
        bArr[8] = (byte) -9;
        bArr[9] = (byte) -5;
        bArr[10] = (byte) -5;
        bArr[11] = (byte) -9;
        bArr[12] = (byte) -9;
        bArr[13] = (byte) -5;
        bArr[14] = (byte) -9;
        bArr[15] = (byte) -9;
        bArr[16] = (byte) -9;
        bArr[17] = (byte) -9;
        bArr[18] = (byte) -9;
        bArr[19] = (byte) -9;
        bArr[20] = (byte) -9;
        bArr[21] = (byte) -9;
        bArr[22] = (byte) -9;
        bArr[23] = (byte) -9;
        bArr[24] = (byte) -9;
        bArr[25] = (byte) -9;
        bArr[26] = (byte) -9;
        bArr[27] = (byte) -9;
        bArr[28] = (byte) -9;
        bArr[29] = (byte) -9;
        bArr[30] = (byte) -9;
        bArr[31] = (byte) -9;
        bArr[32] = (byte) -5;
        bArr[33] = (byte) -9;
        bArr[34] = (byte) -9;
        bArr[35] = (byte) -9;
        bArr[36] = (byte) -9;
        bArr[37] = (byte) -9;
        bArr[38] = (byte) -9;
        bArr[39] = (byte) -9;
        bArr[40] = (byte) -9;
        bArr[41] = (byte) -9;
        bArr[42] = (byte) -9;
        bArr[43] = (byte) -9;
        bArr[44] = (byte) -9;
        bArr[46] = (byte) -9;
        bArr[47] = (byte) -9;
        bArr[48] = (byte) 10;
        bArr[49] = (byte) 9;
        bArr[50] = (byte) 8;
        bArr[51] = (byte) 7;
        bArr[52] = (byte) 6;
        bArr[53] = (byte) 5;
        bArr[54] = (byte) 4;
        bArr[55] = (byte) 3;
        bArr[56] = (byte) 2;
        bArr[57] = (byte) 1;
        bArr[58] = (byte) -9;
        bArr[59] = (byte) -9;
        bArr[60] = (byte) -9;
        bArr[61] = (byte) -1;
        bArr[62] = (byte) -9;
        bArr[63] = (byte) -9;
        bArr[64] = (byte) -9;
        bArr[65] = (byte) 14;
        bArr[66] = (byte) 13;
        bArr[67] = (byte) 12;
        bArr[68] = (byte) 11;
        bArr[69] = (byte) 15;
        bArr[70] = (byte) 16;
        bArr[71] = (byte) 17;
        bArr[72] = (byte) 18;
        bArr[73] = (byte) 19;
        bArr[74] = (byte) 20;
        bArr[75] = (byte) 21;
        bArr[76] = (byte) 22;
        bArr[77] = (byte) 23;
        bArr[78] = (byte) 24;
        bArr[79] = (byte) 28;
        bArr[80] = (byte) 27;
        bArr[81] = (byte) 26;
        bArr[82] = (byte) 25;
        bArr[83] = (byte) 29;
        bArr[84] = (byte) 30;
        bArr[85] = (byte) 31;
        bArr[86] = (byte) 32;
        bArr[87] = (byte) 33;
        bArr[88] = (byte) 34;
        bArr[89] = (byte) 35;
        bArr[90] = (byte) 36;
        bArr[91] = (byte) -9;
        bArr[92] = (byte) -9;
        bArr[93] = (byte) -9;
        bArr[94] = (byte) -9;
        bArr[95] = (byte) 37;
        bArr[96] = (byte) -9;
        bArr[97] = (byte) 63;
        bArr[98] = (byte) 62;
        bArr[99] = (byte) 61;
        bArr[100] = (byte) 60;
        bArr[101] = (byte) 59;
        bArr[102] = (byte) 58;
        bArr[103] = (byte) 57;
        bArr[104] = (byte) 56;
        bArr[105] = (byte) 55;
        bArr[106] = (byte) 54;
        bArr[107] = (byte) 53;
        bArr[108] = (byte) 52;
        bArr[109] = (byte) 51;
        bArr[110] = (byte) 50;
        bArr[111] = (byte) 49;
        bArr[112] = (byte) 48;
        bArr[113] = (byte) 47;
        bArr[114] = (byte) 46;
        bArr[115] = (byte) 45;
        bArr[116] = (byte) 44;
        bArr[117] = (byte) 43;
        bArr[118] = (byte) 42;
        bArr[119] = (byte) 41;
        bArr[120] = (byte) 40;
        bArr[121] = (byte) 39;
        bArr[122] = (byte) 38;
        bArr[123] = (byte) -9;
        bArr[124] = (byte) -9;
        bArr[125] = (byte) -9;
        bArr[TransportMediator.KEYCODE_MEDIA_PLAY] = (byte) -9;
        bArr[TransportMediator.KEYCODE_MEDIA_PAUSE] = (byte) -9;
        bArr[128] = (byte) -9;
        bArr[129] = (byte) -9;
        bArr[TransportMediator.KEYCODE_MEDIA_RECORD] = (byte) -9;
        bArr[131] = (byte) -9;
        bArr[132] = (byte) -9;
        bArr[133] = (byte) -9;
        bArr[134] = (byte) -9;
        bArr[135] = (byte) -9;
        bArr[136] = (byte) -9;
        bArr[137] = (byte) -9;
        bArr[138] = (byte) -9;
        bArr[139] = (byte) -9;
        bArr[140] = (byte) -9;
        bArr[141] = (byte) -9;
        bArr[142] = (byte) -9;
        bArr[143] = (byte) -9;
        bArr[144] = (byte) -9;
        bArr[145] = (byte) -9;
        bArr[146] = (byte) -9;
        bArr[147] = (byte) -9;
        bArr[148] = (byte) -9;
        bArr[149] = (byte) -9;
        bArr[150] = (byte) -9;
        bArr[151] = (byte) -9;
        bArr[152] = (byte) -9;
        bArr[153] = (byte) -9;
        bArr[154] = (byte) -9;
        bArr[155] = (byte) -9;
        bArr[156] = (byte) -9;
        bArr[157] = (byte) -9;
        bArr[158] = (byte) -9;
        bArr[159] = (byte) -9;
        bArr[160] = (byte) -9;
        bArr[161] = (byte) -9;
        bArr[162] = (byte) -9;
        bArr[163] = (byte) -9;
        bArr[164] = (byte) -9;
        bArr[165] = (byte) -9;
        bArr[166] = (byte) -9;
        bArr[167] = (byte) -9;
        bArr[168] = (byte) -9;
        bArr[169] = (byte) -9;
        bArr[170] = (byte) -9;
        bArr[171] = (byte) -9;
        bArr[172] = (byte) -9;
        bArr[173] = (byte) -9;
        bArr[174] = (byte) -9;
        bArr[175] = (byte) -9;
        bArr[176] = (byte) -9;
        bArr[177] = (byte) -9;
        bArr[178] = (byte) -9;
        bArr[179] = (byte) -9;
        bArr[180] = (byte) -9;
        bArr[181] = (byte) -9;
        bArr[182] = (byte) -9;
        bArr[183] = (byte) -9;
        bArr[184] = (byte) -9;
        bArr[185] = (byte) -9;
        bArr[186] = (byte) -9;
        bArr[187] = (byte) -9;
        bArr[188] = (byte) -9;
        bArr[189] = (byte) -9;
        bArr[190] = (byte) -9;
        bArr[191] = (byte) -9;
        bArr[192] = (byte) -9;
        bArr[193] = (byte) -9;
        bArr[194] = (byte) -9;
        bArr[195] = (byte) -9;
        bArr[196] = (byte) -9;
        bArr[197] = (byte) -9;
        bArr[198] = (byte) -9;
        bArr[199] = (byte) -9;
        bArr[200] = (byte) -9;
        bArr[201] = (byte) -9;
        bArr[202] = (byte) -9;
        bArr[203] = (byte) -9;
        bArr[204] = (byte) -9;
        bArr[205] = (byte) -9;
        bArr[206] = (byte) -9;
        bArr[207] = (byte) -9;
        bArr[208] = (byte) -9;
        bArr[209] = (byte) -9;
        bArr[210] = (byte) -9;
        bArr[211] = (byte) -9;
        bArr[212] = (byte) -9;
        bArr[213] = (byte) -9;
        bArr[214] = (byte) -9;
        bArr[215] = (byte) -9;
        bArr[216] = (byte) -9;
        bArr[217] = (byte) -9;
        bArr[218] = (byte) -9;
        bArr[219] = (byte) -9;
        bArr[220] = (byte) -9;
        bArr[221] = (byte) -9;
        bArr[222] = (byte) -9;
        bArr[223] = (byte) -9;
        bArr[224] = (byte) -9;
        bArr[225] = (byte) -9;
        bArr[226] = (byte) -9;
        bArr[227] = (byte) -9;
        bArr[228] = (byte) -9;
        bArr[229] = (byte) -9;
        bArr[230] = (byte) -9;
        bArr[231] = (byte) -9;
        bArr[232] = (byte) -9;
        bArr[233] = (byte) -9;
        bArr[234] = (byte) -9;
        bArr[235] = (byte) -9;
        bArr[236] = (byte) -9;
        bArr[237] = (byte) -9;
        bArr[238] = (byte) -9;
        bArr[239] = (byte) -9;
        bArr[240] = (byte) -9;
        bArr[241] = (byte) -9;
        bArr[242] = (byte) -9;
        bArr[243] = (byte) -9;
        bArr[244] = (byte) -9;
        bArr[245] = (byte) -9;
        bArr[246] = (byte) -9;
        bArr[247] = (byte) -9;
        bArr[248] = (byte) -9;
        bArr[249] = (byte) -9;
        bArr[CameraAnimator.DEFAULT_DURATION] = (byte) -9;
        bArr[251] = (byte) -9;
        bArr[252] = (byte) -9;
        bArr[253] = (byte) -9;
        bArr[254] = (byte) -9;
        bArr[255] = (byte) -9;
        bArr[256] = (byte) -9;
        f282g = bArr;
    }

    /* renamed from: a */
    public static String m420a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return EncryptionUtil.m421a(str.getBytes());
    }

    /* renamed from: b */
    public static String m428b(String str) {
        try {
            return new String(EncryptionUtil.m433c(str));
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: b */
    private static final byte[] m429b(int i) {
        if ((i & 16) == 16) {
            return f279d;
        }
        if ((i & 32) == 32) {
            return f281f;
        }
        return f277b;
    }

    /* renamed from: c */
    private static final byte[] m432c(int i) {
        if ((i & 16) == 16) {
            return f280e;
        }
        if ((i & 32) == 32) {
            return f282g;
        }
        return f278c;
    }

    /* renamed from: b */
    private static byte[] m431b(byte[] bArr, byte[] bArr2, int i, int i2) {
        EncryptionUtil.m425a(bArr2, 0, i, bArr, 0, i2);
        return bArr;
    }

    /* renamed from: a */
    private static byte[] m425a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        int i5;
        int i6 = 0;
        byte[] b = EncryptionUtil.m429b(i4);
        int i7 = i2 > 0 ? (bArr[i] << 24) >>> 8 : 0;
        if (i2 > 1) {
            i5 = (bArr[i + 1] << 24) >>> 16;
        } else {
            i5 = 0;
        }
        i5 |= i7;
        if (i2 > 2) {
            i6 = (bArr[i + 2] << 24) >>> 24;
        }
        i6 |= i5;
        switch (i2) {
            case 1:
                bArr2[i3] = b[i6 >>> 18];
                bArr2[i3 + 1] = b[(i6 >>> 12) & 63];
                bArr2[i3 + 2] = (byte) 61;
                bArr2[i3 + 3] = (byte) 61;
                break;
            case 2:
                bArr2[i3] = b[i6 >>> 18];
                bArr2[i3 + 1] = b[(i6 >>> 12) & 63];
                bArr2[i3 + 2] = b[(i6 >>> 6) & 63];
                bArr2[i3 + 3] = (byte) 61;
                break;
            case 3:
                bArr2[i3] = b[i6 >>> 18];
                bArr2[i3 + 1] = b[(i6 >>> 12) & 63];
                bArr2[i3 + 2] = b[(i6 >>> 6) & 63];
                bArr2[i3 + 3] = b[i6 & 63];
                break;
        }
        return bArr2;
    }

    /* renamed from: a */
    public static String m421a(byte[] bArr) {
        String str = null;
        try {
            str = EncryptionUtil.m422a(bArr, 0, bArr.length, 0);
        } catch (IOException e) {
            if (!f276a) {
                throw new AssertionError(e.getMessage());
            }
        }
        if (f276a || str != null) {
            return str;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public static String m422a(byte[] bArr, int i, int i2, int i3) throws IOException {
        byte[] b = EncryptionUtil.m430b(bArr, i, i2, i3);
        try {
            return new String(b, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            return new String(b);
        }
    }

    /* renamed from: b */
    public static byte[] m430b(byte[] bArr, int i, int i2, int i3) throws IOException {
        C0492k c0492k;
        IOException e;
        OutputStream outputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream = null;
        if (bArr == null) {
            throw new NullPointerException("Cannot serialize a null array.");
        } else if (i < 0) {
            throw new IllegalArgumentException("Cannot have negative offset: " + i);
        } else if (i2 < 0) {
            throw new IllegalArgumentException("Cannot have length offset: " + i2);
        } else if (i + i2 > bArr.length) {
            throw new IllegalArgumentException(String.format("Cannot have offset of %d and length of %d with array of length %d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bArr.length)}));
        } else if ((i3 & 2) != 0) {
            try {
                OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    c0492k = new C0492k(byteArrayOutputStream2, i3 | 1);
                } catch (IOException e2) {
                    e = e2;
                    c0492k = null;
                    outputStream = byteArrayOutputStream2;
                    try {
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        byteArrayOutputStream2 = outputStream;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    c0492k = null;
                    try {
                        gZIPOutputStream.close();
                    } catch (Exception e3) {
                    }
                    try {
                        c0492k.close();
                    } catch (Exception e4) {
                    }
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e5) {
                    }
                    throw th;
                }
                try {
                    GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(c0492k);
                    try {
                        gZIPOutputStream2.write(bArr, i, i2);
                        gZIPOutputStream2.close();
                        try {
                            gZIPOutputStream2.close();
                        } catch (Exception e6) {
                        }
                        try {
                            c0492k.close();
                        } catch (Exception e7) {
                        }
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e8) {
                        }
                        return byteArrayOutputStream2.toByteArray();
                    } catch (IOException e9) {
                        e = e9;
                        gZIPOutputStream = gZIPOutputStream2;
                        outputStream = byteArrayOutputStream2;
                        throw e;
                    } catch (Throwable th4) {
                        th = th4;
                        gZIPOutputStream = gZIPOutputStream2;
                        gZIPOutputStream.close();
                        c0492k.close();
                        byteArrayOutputStream2.close();
                        throw th;
                    }
                } catch (IOException e10) {
                    e = e10;
                    outputStream = byteArrayOutputStream2;
                    throw e;
                } catch (Throwable th5) {
                    th = th5;
                    gZIPOutputStream.close();
                    c0492k.close();
                    byteArrayOutputStream2.close();
                    throw th;
                }
            } catch (IOException e11) {
                e = e11;
                c0492k = null;
                outputStream = null;
                throw e;
            } catch (Throwable th6) {
                th = th6;
                c0492k = null;
                byteArrayOutputStream2 = null;
                gZIPOutputStream.close();
                c0492k.close();
                byteArrayOutputStream2.close();
                throw th;
            }
        } else {
            int i4;
            int i5 = (i3 & 8) != 0 ? 1 : 0;
            int i6 = (i2 / 3) * 4;
            if (i2 % 3 > 0) {
                i4 = 4;
            } else {
                i4 = 0;
            }
            i4 += i6;
            if (i5 != 0) {
                i4 += i4 / 76;
            }
            byte[] bArr2 = new byte[i4];
            int i7 = i2 - 2;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (i10 < i7) {
                EncryptionUtil.m425a(bArr, i10 + i, 3, bArr2, i9, i3);
                i4 = i8 + 4;
                if (i5 != 0 && i4 >= 76) {
                    bArr2[i9 + 4] = (byte) 10;
                    i9++;
                    i4 = 0;
                }
                i9 += 4;
                i8 = i4;
                i10 += 3;
            }
            if (i10 < i2) {
                EncryptionUtil.m425a(bArr, i10 + i, i2 - i10, bArr2, i9, i3);
                i9 += 4;
            }
            if (i9 > bArr2.length - 1) {
                return bArr2;
            }
            byte[] bArr3 = new byte[i9];
            System.arraycopy(bArr2, 0, bArr3, 0, i9);
            return bArr3;
        }
    }

    /* renamed from: b */
    private static int m427b(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr == null) {
            throw new NullPointerException("Source array was null.");
        } else if (bArr2 == null) {
            throw new NullPointerException("Destination array was null.");
        } else if (i < 0 || i + 3 >= bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i)}));
        } else if (i2 < 0 || i2 + 2 >= bArr2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", new Object[]{Integer.valueOf(bArr2.length), Integer.valueOf(i2)}));
        } else {
            byte[] c = EncryptionUtil.m432c(i3);
            int i4;
            if (bArr[i + 2] == (byte) 61) {
                bArr2[i2] = (byte) ((((c[bArr[i]] & 255) << 18) | ((c[bArr[i + 1]] & 255) << 12)) >>> 16);
                return 1;
            } else if (bArr[i + 3] == (byte) 61) {
                i4 = (((c[bArr[i]] & 255) << 18) | ((c[bArr[i + 1]] & 255) << 12)) | ((c[bArr[i + 2]] & 255) << 6);
                bArr2[i2] = (byte) (i4 >>> 16);
                bArr2[i2 + 1] = (byte) (i4 >>> 8);
                return 2;
            } else {
                i4 = ((((c[bArr[i]] & 255) << 18) | ((c[bArr[i + 1]] & 255) << 12)) | ((c[bArr[i + 2]] & 255) << 6)) | (c[bArr[i + 3]] & 255);
                bArr2[i2] = (byte) (i4 >> 16);
                bArr2[i2 + 1] = (byte) (i4 >> 8);
                bArr2[i2 + 2] = (byte) i4;
                return 3;
            }
        }
    }

    /* renamed from: c */
    public static byte[] m434c(byte[] bArr, int i, int i2, int i3) throws IOException {
        if (bArr == null) {
            throw new NullPointerException("Cannot decode null source array.");
        } else if (i < 0 || i + i2 > bArr.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
        } else if (i2 == 0) {
            return new byte[0];
        } else {
            if (i2 < 4) {
                throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + i2);
            }
            int i4;
            byte[] c = EncryptionUtil.m432c(i3);
            byte[] bArr2 = new byte[((i2 * 3) / 4)];
            byte[] bArr3 = new byte[4];
            int i5 = i;
            int i6 = 0;
            int i7 = 0;
            while (i5 < i + i2) {
                byte b = c[bArr[i5] & 255];
                if (b >= (byte) -5) {
                    if (b >= (byte) -1) {
                        i4 = i6 + 1;
                        bArr3[i6] = bArr[i5];
                        if (i4 > 3) {
                            i4 = EncryptionUtil.m427b(bArr3, 0, bArr2, i7, i3) + i7;
                            if (bArr[i5] == (byte) 61) {
                                break;
                            }
                            i6 = i4;
                            i4 = 0;
                        } else {
                            i6 = i7;
                        }
                    } else {
                        i4 = i6;
                        i6 = i7;
                    }
                    i5++;
                    i7 = i6;
                    i6 = i4;
                } else {
                    throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", new Object[]{Integer.valueOf(bArr[i5] & 255), Integer.valueOf(i5)}));
                }
            }
            i4 = i7;
            byte[] bArr4 = new byte[i4];
            System.arraycopy(bArr2, 0, bArr4, 0, i4);
            return bArr4;
        }
    }

    /* renamed from: c */
    public static byte[] m433c(String str) throws IOException {
        return EncryptionUtil.m424a(str, 0);
    }

    /* renamed from: a */
    public static byte[] m424a(String str, int i) throws IOException {
        IOException e;
        ByteArrayInputStream byteArrayInputStream;
        Throwable th;
        GZIPInputStream gZIPInputStream = null;
        if (str == null) {
            throw new NullPointerException("Input string was null.");
        }
        byte[] bytes;
        try {
            bytes = str.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e2) {
            bytes = str.getBytes();
        }
        bytes = EncryptionUtil.m434c(bytes, 0, bytes.length, i);
        int i2;
        if ((i & 4) != 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (bytes != null && bytes.length >= 4 && i2 == 0 && 35615 == ((bytes[0] & 255) | ((bytes[1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) {
            byte[] bArr = new byte[2048];
            ByteArrayOutputStream byteArrayOutputStream;
            ByteArrayInputStream byteArrayInputStream2;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byteArrayInputStream2 = new ByteArrayInputStream(bytes);
                    try {
                        GZIPInputStream gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream2);
                        while (true) {
                            try {
                                int read = gZIPInputStream2.read(bArr);
                                if (read < 0) {
                                    break;
                                }
                                byteArrayOutputStream.write(bArr, 0, read);
                            } catch (IOException e3) {
                                e = e3;
                                gZIPInputStream = gZIPInputStream2;
                                byteArrayInputStream = byteArrayInputStream2;
                                try {
                                    e.printStackTrace();
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (Exception e4) {
                                    }
                                    try {
                                        gZIPInputStream.close();
                                    } catch (Exception e5) {
                                    }
                                    try {
                                        byteArrayInputStream.close();
                                    } catch (Exception e6) {
                                    }
                                    return bytes;
                                } catch (Throwable th2) {
                                    th = th2;
                                    byteArrayInputStream2 = byteArrayInputStream;
                                    try {
                                        byteArrayOutputStream.close();
                                    } catch (Exception e7) {
                                    }
                                    try {
                                        gZIPInputStream.close();
                                    } catch (Exception e8) {
                                    }
                                    try {
                                        byteArrayInputStream2.close();
                                    } catch (Exception e9) {
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                gZIPInputStream = gZIPInputStream2;
                                byteArrayOutputStream.close();
                                gZIPInputStream.close();
                                byteArrayInputStream2.close();
                                throw th;
                            }
                        }
                        bytes = byteArrayOutputStream.toByteArray();
                        try {
                            byteArrayOutputStream.close();
                        } catch (Exception e10) {
                        }
                        try {
                            gZIPInputStream2.close();
                        } catch (Exception e11) {
                        }
                        try {
                            byteArrayInputStream2.close();
                        } catch (Exception e12) {
                        }
                    } catch (IOException e13) {
                        e = e13;
                        byteArrayInputStream = byteArrayInputStream2;
                        e.printStackTrace();
                        byteArrayOutputStream.close();
                        gZIPInputStream.close();
                        byteArrayInputStream.close();
                        return bytes;
                    } catch (Throwable th4) {
                        th = th4;
                        byteArrayOutputStream.close();
                        gZIPInputStream.close();
                        byteArrayInputStream2.close();
                        throw th;
                    }
                } catch (IOException e14) {
                    e = e14;
                    byteArrayInputStream = null;
                    e.printStackTrace();
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return bytes;
                } catch (Throwable th5) {
                    th = th5;
                    byteArrayInputStream2 = null;
                    byteArrayOutputStream.close();
                    gZIPInputStream.close();
                    byteArrayInputStream2.close();
                    throw th;
                }
            } catch (IOException e15) {
                e = e15;
                byteArrayOutputStream = null;
                byteArrayInputStream = null;
                e.printStackTrace();
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream.close();
                return bytes;
            } catch (Throwable th6) {
                th = th6;
                byteArrayOutputStream = null;
                byteArrayInputStream2 = null;
                byteArrayOutputStream.close();
                gZIPInputStream.close();
                byteArrayInputStream2.close();
                throw th;
            }
        }
        return bytes;
    }
}
