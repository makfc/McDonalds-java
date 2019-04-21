package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.Freezable;

public interface Person extends Freezable<Person> {

    public interface AgeRange extends Freezable<AgeRange> {
    }

    public interface Cover extends Freezable<Cover> {

        public interface CoverInfo extends Freezable<CoverInfo> {
        }

        public interface CoverPhoto extends Freezable<CoverPhoto> {
        }

        public static final class Layout {
            private Layout() {
            }
        }
    }

    public interface Image extends Freezable<Image> {
    }

    public interface Name extends Freezable<Name> {
        String getFamilyName();

        String getGivenName();
    }

    public interface Organizations extends Freezable<Organizations> {

        public static final class Type {
            private Type() {
            }
        }
    }

    public interface PlacesLived extends Freezable<PlacesLived> {
    }

    public static final class Gender {
        private Gender() {
        }
    }

    public static final class ObjectType {
        private ObjectType() {
        }
    }

    public static final class RelationshipStatus {
        private RelationshipStatus() {
        }
    }

    public interface Urls extends Freezable<Urls> {

        public static final class Type {
            private Type() {
            }
        }
    }

    String getId();

    Name getName();
}
