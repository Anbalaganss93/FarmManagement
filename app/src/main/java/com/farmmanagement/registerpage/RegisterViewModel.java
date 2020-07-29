package com.farmmanagement.registerpage;

import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {
    public RegisterViewModel() {
    }

    public RegisterValidationModel loginValidation(String mobilenumber, String userName, String farmName, String farmAddress, String pinCode) {
        RegisterValidationModel model = null;
        if (mobilenumber.length() != 10) {
            String message = mobilenumber.length() != 0 ? "Enter valid mobile number" : "Enter mobile number";
            model = new RegisterValidationModel(message, false);
        } else if (userName.isEmpty()) {
            model = new RegisterValidationModel("Enter user name", false);
        } else if (farmName.isEmpty()) {
            model = new RegisterValidationModel("Enter farm name", false);
        } else if (farmAddress.isEmpty()) {
            model = new RegisterValidationModel("Enter farm address", false);
        } else if (pinCode.isEmpty() || pinCode.length() < 6) {
            String message = pinCode.length() != 0 ? "Enter valid pincode" : "Enter pincode";
            model = new RegisterValidationModel(message, false);
        } else {
            model = new RegisterValidationModel("", true);
        }
        return model;
    }

}
