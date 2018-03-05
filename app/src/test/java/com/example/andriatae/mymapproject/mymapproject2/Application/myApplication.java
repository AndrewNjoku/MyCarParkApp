package com.example.andriatae.mymapproject.mymapproject2.Application;


import android.app.Application;
import android.content.SharedPreferences;

import io.realm.Realm;
import io.realm.RealmConfiguration;

// import android.arch.persistence.room.Room;


public class myApplication extends Application {
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!

    public static myApplication INSTANCE;
  //  private static final String DATABASE_NAME = "MyDatabase";
     static final String PREFERENCES = "RoomDemo.preferences";
     static final String KEY_FORCE_UPDATE = "force_update";
     public static RealmConfiguration realmConfiguration;
     public static RealmConfiguration realmConfiguration2;


    // private appDatabase database;

    public static myApplication get() {
        return INSTANCE;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        // create database ROOM
        //    database = Room.databaseBuilder(getApplicationContext(), appDatabase.class, DATABASE_NAME)


        //Room provides an abstraction layer to ease SQLite migrations in the form of the Migration class.
        // A Migration class defines the actions that should be performed when migrating from
        // one specific version to another.

        //     .addMigrations(appDatabase.MIGRATION_1_2)
        //      .build();

        INSTANCE = this;
        // Required initialization logic here!


        //Create database REALM
        Realm.init(getApplicationContext());

        realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

       realmConfiguration2 = new RealmConfiguration.Builder()
                .name("ResRealm")
                .build();
    }

    public RealmConfiguration getRealm(String name){

        if (name.equals("ResRealm"))
        {

            return realmConfiguration2;

        }

        else if (name.equals("default")){

            return realmConfiguration;
        }

        return null;

    }


    //  public appDatabase getDB() {
    //    return database;
    //   }

    public boolean isForceUpdate() {
        return getSP().getBoolean(KEY_FORCE_UPDATE, true);
    }

    public void setForceUpdate(boolean force) {
        SharedPreferences.Editor edit = getSP().edit();
        edit.putBoolean(KEY_FORCE_UPDATE, force);
        edit.apply();
    }

    private SharedPreferences getSP() {
        return getSharedPreferences(PREFERENCES, MODE_PRIVATE);
    }



    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

}