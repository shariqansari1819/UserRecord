package com.codebosses.roomdatabase.room;

import android.content.Context;

import androidx.room.Room;

public class AppDatabase {

    private volatile static AppDatabase appDatabase;
    private UserDatabase userDatabase;

    private AppDatabase(Context context) {
        if (appDatabase != null) {
            throw new RuntimeException("Use getAppDatabase() method to initialize object.");
        }
        userDatabase = Room.
                databaseBuilder(context, UserDatabase.class, "my_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static AppDatabase getAppDatabase(Context context) {
        if (appDatabase == null) {
            synchronized (AppDatabase.class) {
                if (appDatabase == null)
                    appDatabase = new AppDatabase(context);
            }
        }
        return appDatabase;
    }

    public UserDatabase getUserDatabase() {
        return userDatabase;
    }

}
