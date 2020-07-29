package com.farmmanagement.pondlistpage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PondsViewModelFactory implements ViewModelProvider.Factory {
    Application application;
    String phonenumber;

    public PondsViewModelFactory(Application application, String phonenumber) {
        this.application = application;
        this.phonenumber = phonenumber;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PondsViewModel(application,phonenumber);
    }
}
