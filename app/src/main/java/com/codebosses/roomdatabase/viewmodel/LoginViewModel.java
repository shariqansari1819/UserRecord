package com.codebosses.roomdatabase.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.codebosses.roomdatabase.pojo.User;
import com.codebosses.roomdatabase.repository.RoomDatabaseRepository;

public class LoginViewModel extends AndroidViewModel {

    private RoomDatabaseRepository roomDatabaseRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        roomDatabaseRepository = new RoomDatabaseRepository(application);
    }

    public void insertUser(User user) {
        roomDatabaseRepository.insertUser(user);
    }

    public LiveData<User> getUser(String email, String password) {
        return roomDatabaseRepository.getUser(email, password);
    }

    public LiveData<User> getUserByEmail(String email) {
        return roomDatabaseRepository.getUserByEmail(email);
    }

}
