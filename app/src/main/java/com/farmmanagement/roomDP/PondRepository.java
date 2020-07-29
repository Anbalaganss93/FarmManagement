package com.farmmanagement.roomDP;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.farmmanagement.pondlistpage.PondMainActivity;

import java.util.List;

public class PondRepository {
    private UserDao userDao;
    private LiveData<List<UserDetail>> allUserdata;
    private LiveData<List<UserDetail>> userPresence;
    private LiveData<List<PondDetails>> allpondsdata;
    private Application application;

    public PondRepository(Application application, String phonenumber) {
        pondRoomDatabase db = pondRoomDatabase.getDatabase(application);
        userDao = db.userDao();
        this.application = application;
        allUserdata = userDao.getAllUserDetails();
        allpondsdata = userDao.getAllPonds(phonenumber);
        userPresence = userDao.isDataExist(phonenumber);
    }

    public LiveData<List<UserDetail>> userPresence(){
           return userPresence;
    }

    public LiveData<List<UserDetail>> getAllUserDetails() {
        return allUserdata;
    }

    public LiveData<List<PondDetails>> getAllPondDetails() {
        return allpondsdata;
    }

    public void insert(UserDetail userDetail) {

        new insertAsyncTask(userDao, application).execute(userDetail);
    }

    public void insertPond(PondDetails pondModel) {
        new insertPondAsyncTask(userDao, application).execute(pondModel);
    }


    public void deletePond(PondDetails pondModel) {
        new deletePondAsyncTask(userDao).execute(pondModel);
    }

    public void updatePond(PondDetails pondModel) {
        new updatePondAsyncTask(userDao).execute(pondModel);
    }

    private static class insertPondAsyncTask extends AsyncTask<PondDetails, Void, Void> {

        private UserDao mAsyncTaskDao2;
        private Application application;

        insertPondAsyncTask(UserDao userDao, Application application) {
            mAsyncTaskDao2 = userDao;
            this.application = application;
        }


        @Override
        protected Void doInBackground(final PondDetails... params) {
            try {
                mAsyncTaskDao2.insertPond(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private static class deletePondAsyncTask extends AsyncTask<PondDetails, Void, Void> {

        private UserDao mAsyncTaskDao;

        deletePondAsyncTask(UserDao userDao) {
            mAsyncTaskDao = userDao;
        }


        @Override
        protected Void doInBackground(final PondDetails... params) {
            try {
                mAsyncTaskDao.delete(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private static class updatePondAsyncTask extends AsyncTask<PondDetails, Void, Void> {

        private UserDao mAsyncTaskDao;

        updatePondAsyncTask(UserDao userDao) {
            mAsyncTaskDao = userDao;
        }


        @Override
        protected Void doInBackground(final PondDetails... params) {
            try {
                mAsyncTaskDao.update(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private static class insertAsyncTask extends AsyncTask<UserDetail, Void, Void> {

        private UserDao mAsyncTaskDao;
        private Application application;

        insertAsyncTask(UserDao userDao, Application application) {
            mAsyncTaskDao = userDao;
            this.application = application;
        }

        @Override
        protected Void doInBackground(final UserDetail... params) {
            try {
                mAsyncTaskDao.insert(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(application, PondMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            application.startActivity(intent);
        }

    }
}
