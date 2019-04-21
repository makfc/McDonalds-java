package org.acra.collector;

import android.util.SparseArray;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class MediaCodecListCollector {
    private static final String[] AAC_TYPES = new String[]{"aac", "AAC"};
    private static final String[] AVC_TYPES = new String[]{"avc", "h264", "AVC", "H264"};
    private static final String[] H263_TYPES = new String[]{"h263", "H263"};
    private static final String[] MPEG4_TYPES = new String[]{"mp4", "mpeg4", "MP4", "MPEG4"};
    private static Class<?> codecCapabilitiesClass;
    private static Field colorFormatsField;
    private static Method getCapabilitiesForTypeMethod;
    private static Method getCodecInfoAtMethod;
    private static Method getNameMethod;
    private static Method getSupportedTypesMethod;
    private static Method isEncoderMethod;
    private static Field levelField;
    private static SparseArray<String> mAACProfileValues = new SparseArray();
    private static SparseArray<String> mAVCLevelValues = new SparseArray();
    private static SparseArray<String> mAVCProfileValues = new SparseArray();
    private static SparseArray<String> mColorFormatValues = new SparseArray();
    private static SparseArray<String> mH263LevelValues = new SparseArray();
    private static SparseArray<String> mH263ProfileValues = new SparseArray();
    private static SparseArray<String> mMPEG4LevelValues = new SparseArray();
    private static SparseArray<String> mMPEG4ProfileValues = new SparseArray();
    private static Class<?> mediaCodecInfoClass;
    private static Class<?> mediaCodecListClass;
    private static Field profileField;
    private static Field profileLevelsField;

    enum CodecType {
        AVC,
        H263,
        MPEG4,
        AAC
    }

    static {
        int i = 0;
        mediaCodecListClass = null;
        getCodecInfoAtMethod = null;
        mediaCodecInfoClass = null;
        getNameMethod = null;
        isEncoderMethod = null;
        getSupportedTypesMethod = null;
        getCapabilitiesForTypeMethod = null;
        codecCapabilitiesClass = null;
        colorFormatsField = null;
        profileLevelsField = null;
        profileField = null;
        levelField = null;
        try {
            Field field;
            Class cls = Class.forName("android.media.MediaCodecList");
            mediaCodecListClass = cls;
            getCodecInfoAtMethod = cls.getMethod("getCodecInfoAt", new Class[]{Integer.TYPE});
            cls = Class.forName("android.media.MediaCodecInfo");
            mediaCodecInfoClass = cls;
            getNameMethod = cls.getMethod("getName", new Class[0]);
            isEncoderMethod = mediaCodecInfoClass.getMethod("isEncoder", new Class[0]);
            getSupportedTypesMethod = mediaCodecInfoClass.getMethod("getSupportedTypes", new Class[0]);
            getCapabilitiesForTypeMethod = mediaCodecInfoClass.getMethod("getCapabilitiesForType", new Class[]{String.class});
            cls = Class.forName("android.media.MediaCodecInfo$CodecCapabilities");
            codecCapabilitiesClass = cls;
            colorFormatsField = cls.getField("colorFormats");
            profileLevelsField = codecCapabilitiesClass.getField("profileLevels");
            for (Field field2 : codecCapabilitiesClass.getFields()) {
                if (Modifier.isStatic(field2.getModifiers()) && Modifier.isFinal(field2.getModifiers()) && field2.getName().startsWith("COLOR_")) {
                    mColorFormatValues.put(field2.getInt(null), field2.getName());
                }
            }
            cls = Class.forName("android.media.MediaCodecInfo$CodecProfileLevel");
            Field[] fields = cls.getFields();
            int length = fields.length;
            while (i < length) {
                field2 = fields[i];
                if (Modifier.isStatic(field2.getModifiers()) && Modifier.isFinal(field2.getModifiers())) {
                    if (field2.getName().startsWith("AVCLevel")) {
                        mAVCLevelValues.put(field2.getInt(null), field2.getName());
                    } else if (field2.getName().startsWith("AVCProfile")) {
                        mAVCProfileValues.put(field2.getInt(null), field2.getName());
                    } else if (field2.getName().startsWith("H263Level")) {
                        mH263LevelValues.put(field2.getInt(null), field2.getName());
                    } else if (field2.getName().startsWith("H263Profile")) {
                        mH263ProfileValues.put(field2.getInt(null), field2.getName());
                    } else if (field2.getName().startsWith("MPEG4Level")) {
                        mMPEG4LevelValues.put(field2.getInt(null), field2.getName());
                    } else if (field2.getName().startsWith("MPEG4Profile")) {
                        mMPEG4ProfileValues.put(field2.getInt(null), field2.getName());
                    } else if (field2.getName().startsWith("AAC")) {
                        mAACProfileValues.put(field2.getInt(null), field2.getName());
                    }
                }
                i++;
            }
            profileField = cls.getField("profile");
            levelField = cls.getField("level");
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e2) {
        } catch (IllegalArgumentException e3) {
        } catch (IllegalAccessException e4) {
        } catch (SecurityException e5) {
        } catch (NoSuchFieldException e6) {
        }
    }

    public static String collecMediaCodecList() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!(mediaCodecListClass == null || mediaCodecInfoClass == null)) {
            try {
                int intValue = ((Integer) mediaCodecListClass.getMethod("getCodecCount", new Class[0]).invoke(null, new Object[0])).intValue();
                for (int i = 0; i < intValue; i++) {
                    stringBuilder.append("\n");
                    Object invoke = getCodecInfoAtMethod.invoke(null, new Object[]{Integer.valueOf(i)});
                    stringBuilder.append(i).append(": ").append(getNameMethod.invoke(invoke, new Object[0])).append("\n");
                    stringBuilder.append("isEncoder: ").append(isEncoderMethod.invoke(invoke, new Object[0])).append("\n");
                    String[] strArr = (String[]) getSupportedTypesMethod.invoke(invoke, new Object[0]);
                    stringBuilder.append("Supported types: ").append(Arrays.toString(strArr)).append("\n");
                    for (String collectCapabilitiesForType : strArr) {
                        stringBuilder.append(collectCapabilitiesForType(invoke, collectCapabilitiesForType));
                    }
                    stringBuilder.append("\n");
                }
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            }
        }
        return stringBuilder.toString();
    }

    private static String collectCapabilitiesForType(Object codecInfo, String type) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        Object invoke = getCapabilitiesForTypeMethod.invoke(codecInfo, new Object[]{type});
        int[] iArr = (int[]) colorFormatsField.get(invoke);
        if (iArr.length > 0) {
            stringBuilder.append(type).append(" color formats:");
            for (int i2 = 0; i2 < iArr.length; i2++) {
                stringBuilder.append((String) mColorFormatValues.get(iArr[i2]));
                if (i2 < iArr.length - 1) {
                    stringBuilder.append(',');
                }
            }
            stringBuilder.append("\n");
        }
        CodecType identifyCodecType = identifyCodecType(codecInfo);
        Object[] objArr = (Object[]) profileLevelsField.get(invoke);
        if (objArr.length > 0) {
            stringBuilder.append(type).append(" profile levels:");
            while (i < objArr.length) {
                int i3 = profileField.getInt(objArr[i]);
                int i4 = levelField.getInt(objArr[i]);
                if (identifyCodecType == null) {
                    stringBuilder.append(i3).append('-').append(i4);
                    stringBuilder.append("\n");
                } else {
                    switch (identifyCodecType) {
                        case AVC:
                            stringBuilder.append(i3).append((String) mAVCProfileValues.get(i3)).append('-').append((String) mAVCLevelValues.get(i4));
                            break;
                        case H263:
                            stringBuilder.append((String) mH263ProfileValues.get(i3)).append('-').append((String) mH263LevelValues.get(i4));
                            break;
                        case MPEG4:
                            stringBuilder.append((String) mMPEG4ProfileValues.get(i3)).append('-').append((String) mMPEG4LevelValues.get(i4));
                            break;
                        case AAC:
                            stringBuilder.append((String) mAACProfileValues.get(i3));
                            break;
                    }
                    if (i < objArr.length - 1) {
                        stringBuilder.append(',');
                    }
                    i++;
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.append("\n").toString();
    }

    private static CodecType identifyCodecType(Object codecInfo) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        int i = 0;
        String str = (String) getNameMethod.invoke(codecInfo, new Object[0]);
        for (CharSequence contains : AVC_TYPES) {
            if (str.contains(contains)) {
                return CodecType.AVC;
            }
        }
        for (CharSequence contains2 : H263_TYPES) {
            if (str.contains(contains2)) {
                return CodecType.H263;
            }
        }
        for (CharSequence contains22 : MPEG4_TYPES) {
            if (str.contains(contains22)) {
                return CodecType.MPEG4;
            }
        }
        String[] strArr = AAC_TYPES;
        int length = strArr.length;
        while (i < length) {
            if (str.contains(strArr[i])) {
                return CodecType.AAC;
            }
            i++;
        }
        return null;
    }
}
