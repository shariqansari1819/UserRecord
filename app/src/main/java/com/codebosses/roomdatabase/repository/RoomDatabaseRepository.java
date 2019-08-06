package com.codebosses.roomdatabase.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.codebosses.roomdatabase.pojo.User;
import com.codebosses.roomdatabase.room.AppDatabase;
import com.codebosses.roomdatabase.room.UserDao;

public class RoomDatabaseRepository {

    private UserDao userDao;
    private MutableLiveData<User> user = new MutableLiveData<>();
    private MutableLiveData<User> userByEmail = new MutableLiveData<>();

    public RoomDatabaseRepository(Context context) {
        userDao = AppDatabase.getAppDatabase(context).getUserDatabase().getMyDao();
    }

    public void insertUser(User user) {
        new InsertUserTask(userDao).execute(user);
    }

    public LiveData<User> getUser(String email, String password) {
        new GetSingleUserTask(userDao, email, password).execute();
        return user;
    }

    public LiveData<User> getUserByEmail(String email) {
        new GetUserByEmailTask(userDao, email).execute();
        return userByEmail;
    }

    class GetUserByEmailTask extends AsyncTask<Void, Void, Void> {

        private UserDao userDao;
        private String email;

        public GetUserByEmailTask(UserDao userDao, String email) {
            this.userDao = userDao;
            this.email = email;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            RoomDatabaseRepository.this.userByEmail.postValue(userDao.getUserByEmail(email));
            return null;
        }
    }

    class GetSingleUserTask extends AsyncTask<Void, Void, Void> {

        private String email, password;
        private UserDao userDao;

        GetSingleUserTask(UserDao userDao, String email, String password) {
            this.email = email;
            this.password = password;
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            RoomDatabaseRepository.this.user.postValue(userDao.getUserData(email, password));
            return null;
        }

    }

    static class InsertUserTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        InsertUserTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }

}
