package com.example.forexample.Services.Realm;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class Migration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if(oldVersion == 0) {
            DynamicRealmObject door = realm.createObject("Door");
            oldVersion++;
        }
    }

    @Override public boolean equals(Object object) {
        return object != null && object instanceof Migration;
    }

    @Override public int hashCode() {
        return Migration.class.hashCode();
    }
}