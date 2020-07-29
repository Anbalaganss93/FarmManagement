package com.farmmanagement.loginpage;

import androidx.lifecycle.ViewModel;

import com.farmmanagement.registerpage.RegisterValidationModel;

public class LoginViewModel extends ViewModel {
    public LoginViewModel() {

    }
    public RegisterValidationModel loginValidation(String mobilenumber) {
        RegisterValidationModel model = null;
        if (mobilenumber.length() != 10) {
            String message = mobilenumber.length() != 0 ? "Enter valid mobile number" : "Enter mobile number";
            model = new RegisterValidationModel(message, false);
        }else {
            model = new RegisterValidationModel("", true);
        }
        return model;
    }

}
