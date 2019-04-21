package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.People.LoadPeopleResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.zze;
import com.google.android.gms.plus.model.people.Person;
import java.util.Collection;

public final class zzti implements People {

    private static abstract class zza extends com.google.android.gms.plus.Plus.zza<LoadPeopleResult> {
        /* renamed from: zzbq */
        public LoadPeopleResult zzc(final Status status) {
            return new LoadPeopleResult() {
                public Status getStatus() {
                    return status;
                }

                public void release() {
                }
            };
        }
    }

    /* renamed from: com.google.android.gms.internal.zzti$1 */
    class C22731 extends zza {
        final /* synthetic */ int zzblL;
        final /* synthetic */ String zzblM;

        /* Access modifiers changed, original: protected */
        public void zza(zze zze) {
            zza(zze.zza(this, this.zzblL, this.zzblM));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzti$2 */
    class C22742 extends zza {
        final /* synthetic */ String zzblM;

        /* Access modifiers changed, original: protected */
        public void zza(zze zze) {
            zza(zze.zzu(this, this.zzblM));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzti$3 */
    class C22753 extends zza {
        /* Access modifiers changed, original: protected */
        public void zza(zze zze) {
            zze.zzt(this);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzti$4 */
    class C22764 extends zza {
        final /* synthetic */ Collection zzblO;

        /* Access modifiers changed, original: protected */
        public void zza(zze zze) {
            zze.zza(this, this.zzblO);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzti$5 */
    class C22775 extends zza {
        final /* synthetic */ String[] zzblP;

        /* Access modifiers changed, original: protected */
        public void zza(zze zze) {
            zze.zzd(this, this.zzblP);
        }
    }

    public Person getCurrentPerson(GoogleApiClient googleApiClient) {
        return Plus.zzf(googleApiClient, true).zzIm();
    }
}
