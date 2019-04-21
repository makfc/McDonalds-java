package p041io.fabric.sdk.android.services.common;

import android.content.Context;
import java.lang.reflect.Method;
import p041io.fabric.sdk.android.Fabric;

/* renamed from: io.fabric.sdk.android.services.common.FirebaseAppImpl */
final class FirebaseAppImpl implements FirebaseApp {
    private final Object firebaseAppInstance;
    private final Method isDataCollectionDefaultEnabledMethod;

    public static FirebaseApp getInstance(Context context) {
        try {
            Class firebaseAppClass = context.getClassLoader().loadClass("com.google.firebase.FirebaseApp");
            return new FirebaseAppImpl(firebaseAppClass, firebaseAppClass.getDeclaredMethod("getInstance", new Class[0]).invoke(firebaseAppClass, new Object[0]));
        } catch (ClassNotFoundException e) {
            Fabric.getLogger().mo34403d("Fabric", "Could not find class: com.google.firebase.FirebaseApp");
        } catch (NoSuchMethodException e2) {
            Fabric.getLogger().mo34403d("Fabric", "Could not find method: " + e2.getMessage());
        } catch (Exception e3) {
            Fabric.getLogger().mo34404d("Fabric", "Unexpected error loading FirebaseApp instance.", e3);
        }
        return null;
    }

    private FirebaseAppImpl(Class firebaseAppClass, Object instance) throws NoSuchMethodException {
        this.firebaseAppInstance = instance;
        this.isDataCollectionDefaultEnabledMethod = firebaseAppClass.getDeclaredMethod("isDataCollectionDefaultEnabled", new Class[0]);
    }

    public boolean isDataCollectionDefaultEnabled() {
        try {
            return ((Boolean) this.isDataCollectionDefaultEnabledMethod.invoke(this.firebaseAppInstance, new Object[0])).booleanValue();
        } catch (Exception e) {
            Fabric.getLogger().mo34404d("Fabric", "Cannot check isDataCollectionDefaultEnabled on FirebaseApp.", e);
            return false;
        }
    }
}
