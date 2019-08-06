package com.codebosses.roomdatabase.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.codebosses.roomdatabase.pojo.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getMyDao();

}
