package p049hk.com.aisoft.easyaddrui;

/* renamed from: hk.com.aisoft.easyaddrui.Validate */
class Validate {
    private Validate() {
    }

    static void isTrue(boolean test, String msg, Object... args) {
        if (!test) {
            throw new IllegalArgumentException(String.format(msg, args));
        }
    }

    static void notNull(Object object, String msg, Object... args) {
        if (object == null) {
            throw new NullPointerException(String.format(msg, args));
        }
    }

    static void isCorrectLength(byte[] object, int length, String name) {
        boolean z;
        Validate.notNull(object, "%s cannot be null.", name);
        if (object.length == length) {
            z = true;
        } else {
            z = false;
        }
        Validate.isTrue(z, "%s should be %d bytes, found %d bytes.", name, Integer.valueOf(length), Integer.valueOf(object.length));
    }
}
