package com.codebosses.roomdatabase.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.codebosses.roomdatabase.pojo.User;

@Dao
public interface UserDao {

    @Insert
    public void insertUser(User user);

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    public User getUserData(String email, String password);

    @Query("SELECT * FROM user WHERE email = :email")
    public User getUserByEmail(String email);

}
