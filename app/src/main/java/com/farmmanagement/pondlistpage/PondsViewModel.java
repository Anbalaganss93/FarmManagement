package com.farmmanagement.pondlistpage;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.farmmanagement.roomDP.PondDetails;
import com.farmmanagement.roomDP.PondRepository;
import com.farmmanagement.roomDP.UserDetail;

import java.util.List;

public class PondsViewModel extends ViewModel {
    private PondRepository mRepository;
    public LiveData<List<PondDetails>> mAllPonds;
    public LiveData<List<UserDetail>> mAllUser;
    public LiveData<List<UserDetail>> mUserPresence;

    public PondsViewModel(Application application, String phonenumber) {
        mRepository = new PondRepository(application, phonenumber);
        mAllUser = mRepository.getAllUserDetails();
        mUserPresence = mRepository.userPresence();
        mAllPonds = mRepository.getAllPondDetails();
    }

    public LiveData<List<UserDetail>> checkUserPresence(String mobileno) {
        return mUserPresence;
    }

    public LiveData<List<UserDetail>> getAllUsers() {
        return mAllUser;
    }

    public LiveData<List<PondDetails>> getAllPonds(String phonenumber) {
        return mAllPonds;
    }

    public void insert(UserDetail word) {
        mRepository.insert(word);
    }

    public void insertPond(PondDetails pond) {
        mRepository.insertPond(pond);
    }

    public void updatePond(PondDetails pondDetails) {
        mRepository.updatePond(pondDetails);
    }

    public void deletePond(PondDetails pondDetails) {
        mRepository.deletePond(pondDetails);
    }

    public boolean createPondValidation(String name, String size) {
        if (name != null && name.length() > 0 && !size.isEmpty()) {
            return !(Float.parseFloat(size) <= 0);
        }
        return false;
    }

}
